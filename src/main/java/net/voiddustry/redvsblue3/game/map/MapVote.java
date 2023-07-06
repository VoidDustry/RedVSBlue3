package net.voiddustry.redvsblue3.game.map;

import arc.struct.Seq;

import arc.util.Timer;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.maps.Map;
import mindustry.maps.MapException;
import mindustry.net.WorldReloader;
import mindustry.ui.Menus;
import net.voiddustry.redvsblue3.player.Bundle;
import net.voiddustry.redvsblue3.utils.Log;
import net.voiddustry.redvsblue3.utils.Utils;

import java.util.HashMap;

public class MapVote {
    private static final java.util.Map<Integer, Map> maps = new HashMap<>();
    private static final java.util.Map<Integer, Integer> votesMap = new HashMap<>();

    private static final int timer = 30;

    private static final Timer.Task task = new Timer.Task() {
        @Override
        public void run() {
            int[] i = {timer};
            Timer.schedule(() -> {
                i[0]--;
                if (i[0] >= 0) {
                    Utils.sendBundled("mapvote.timeleft", i[0]);
                } else if (i[0] == -3) {
                    Map map = getMostVotedMap();
                    Utils.sendBundled("mapvote.mapwon", map.name());
                } else if (i[0] == -5) {
                    Map map = getMostVotedMap();
                    Log.info("[cyan]Changing map to " + map.name() + "...");
                    changeMap(map);
                    task.cancel();
                }
            }, 0, 1);
        }
    };

    public static void callVote() {
        int id = 0;

        for (Map map : getMaps()) {
            maps.put(id++, map);
        }

        for (Player player : Groups.player) {
            openMenu(player);
        }

        task.run();
    }

    private static void openMenu(Player player) {
        int menu = Menus.registerMenu((player1, option) -> {
            if (option == -1) {
                openMenu(player);
            } else {
                registerVote(option);
                Map selectedmap = getMap(option);
                Utils.sendBundled("mapvote.playervoted", player.name, selectedmap.name());
            }
        });

        String[][] buttons = getMapsButtons();

        Call.menu(player.con, menu, Bundle.get("mapvote.title", player.locale), "", buttons);
    }

    private static void registerVote(int mapId) {
        votesMap.merge(mapId, 1, Integer::sum);
    }

    private static Seq<Map> getMaps() {
        return Vars.maps.customMaps();
    }

    private static Map getMap(int index) {
        Seq<Map> mapsSeq = getMaps();
        return mapsSeq.get(index);
    }

    private static String[][] getMapsButtons() {
        Seq<String> mapsNames = new Seq<>();

        for (Map map : getMaps()) {
            mapsNames.add(map.name());
        }

        String[][] text = new String[mapsNames.size][1];

        int i = 0;
        for (String map : mapsNames) {
            text[i++][0] = map;
        }

        return text;
    }

    private static Map getMostVotedMap() {
        final int[] mostVotedMapId = {0};
        final int[] biggestVotesCount = {0};

        votesMap.forEach((mapId, count) -> {
            if (count > biggestVotesCount[0]) {
                biggestVotesCount[0] = count;
                mostVotedMapId[0] = mapId;
            }
        });

        Seq<Map> mapSeq = new Seq<>();
        mapSeq.add(getMaps());
        return mapSeq.get(mostVotedMapId[0]);
    }

    private static void changeMap(Map map) {
        reloadWorld(() -> Vars.world.loadMap(map, map.applyRules(Vars.state.rules.mode())));
    }

    private static void reloadWorld(Runnable runnable) {
        try {
            var reloader = new WorldReloader();
            reloader.begin();

            runnable.run();
            Vars.state.rules = Vars.state.map.applyRules(Vars.state.rules.mode());
            Vars.logic.play();

            reloader.end();
        } catch (MapException ignored) {

        }
    }
}
