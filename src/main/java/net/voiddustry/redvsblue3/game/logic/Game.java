package net.voiddustry.redvsblue3.game.logic;

import arc.Core;
import arc.Events;
import arc.struct.Seq;
import arc.util.Timer;
import mindustry.Vars;
import mindustry.content.UnitTypes;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.world.Tile;
import net.voiddustry.redvsblue3.config.MapScripts;
import net.voiddustry.redvsblue3.game.Rules;
import net.voiddustry.redvsblue3.game.domain.MapScriptData;
import net.voiddustry.redvsblue3.game.domain.PlayerData;
import net.voiddustry.redvsblue3.game.map.MapScanner;
import net.voiddustry.redvsblue3.player.Players;
import net.voiddustry.redvsblue3.utils.Log;

public class Game {

    public static Seq<Tile> cruxSpawns = new Seq<>();
    public static Tile blueSpawn;

    public static boolean loaded;
    public static boolean playing;

    public static void start() {

        Crystals.spawnCrystals();

        cruxSpawns = MapScanner.scanCruxSpawns();

        Timer.schedule(() -> {
            Rules.init();

            MapScriptData mapScript = MapScripts.loadScript(Vars.dataDirectory + "/maps/script/" + Vars.state.map.file.name().replace(".msav", ".rvsb"));

            if (mapScript != null) {
                blueSpawn = new Tile(mapScript.blueSpawnX(), mapScript.blueSpawnY());
            } else {
                Log.err("[scarlet]Map Script is Empty!");
                Core.app.exit();
            }

            Groups.player.forEach(player -> {
                PlayerData data = Players.getPlayer(player);

                data.setTeam(Team.blue);

                Unit playerUnit = UnitTypes.elude.spawn(Team.blue, blueSpawn);
                data.setUnit(playerUnit);

                Call.setRules(player.con, Vars.state.rules);
            });

            loaded = true;
        }, 3);
    }

    public static void over() {
//        MapVote.callVote(true);
        loaded = false;
        playing = false;

        Players.clearPlayersMap();
        Log.info("[yellow]Game Over! Crux win");
        Events.fire(EventType.GameOverEvent.class);
    }

    public static void spawnPlayer(mindustry.gen.Player player) {
        PlayerData data = Players.getPlayer(player);

        data.setTeam(Team.blue);

        Unit playerUnit = UnitTypes.elude.spawn(Team.blue, blueSpawn);
        data.setUnit(playerUnit);
    }
}
