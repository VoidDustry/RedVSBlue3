package net.voiddustry.redvsblue3.game.logic;

import arc.struct.ObjectMap;
import arc.util.Timer;

import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.ui.Menus;
import mindustry.world.Tile;

import net.voiddustry.redvsblue3.game.domain.CrystalData;
import net.voiddustry.redvsblue3.player.Players;
import net.voiddustry.redvsblue3.utils.Random;
import net.voiddustry.redvsblue3.utils.Log;
import net.voiddustry.redvsblue3.utils.Utils;

public class Crystals {

    private static final ObjectMap<Integer, CrystalData> crystals = new ObjectMap<>();

    public static void spawnCrystals() {
        Log.info("[red]Spawning crystals...");
        for(int i = 1; i <= 3;i++) {
            String type = "N/A";

            switch (i) {
                case 1 -> type = "A";
                case 2 -> type = "B";
                case 3 -> type = "C";
            }

            Tile tile = Random.getRandomTile(true);

            tile.setFloorNet(Blocks.darkPanel6);

            CrystalData crystal = new CrystalData(type, i, Team.blue, tile, 0);
            crystals.put(i, crystal);
            Log.debug("[purple]Spawned crystal at [cyan]" + crystal.getTileOn().x + " " + crystal.getTileOn().y);
        }
    }

    public static void initTimer() {
        Timer.schedule(() -> crystals.forEach(object -> {
            StringBuilder text = new StringBuilder();

            if (object.value.getTeamOwner() == Team.blue) {
                text.append("[royal]");
            } else {
                text.append("[scarlet]");
            }

            text.append(object.value.getType());

            if (object.value.getInfectPercentage() != 0) {

            }

            Tile tile = object.value.getTileOn();

//            if (Utils.isUnitTeamOnTile(tile, Utils.getOppositeTeam(crystal.getTeamOwner()))) {
//
//            }

            Call.labelReliable(text.toString(), 1, tile.x*8, tile.y*8);
        }), 0, 1);
    }

    public static void clearCrystals() {
        crystals.clear();
    }

    public static boolean isPlayerStandsOnCrystal(Player player) {
        for (ObjectMap.Entry<Integer, CrystalData> crystal : crystals) {
            if (player.tileOn() == crystal.value.getTileOn()) return true;
        }
        return false;
    }

    public static void openTeleportMenu(Player player) {
        if(Players.getPlayer(player).isInMenu()) {
           return;
        }

        int menu = Menus.registerMenu(((player1, option) -> {
            switch (option) {
                case 0 -> {
                    Tile tile = crystals.get(1).getTileOn();
                    Utils.teleportPlayer(player, tile);
                }

                case 1 -> {
                    Tile tile = crystals.get(2).getTileOn();
                    Utils.teleportPlayer(player, tile);
                }

                case 2 -> {
                    Tile tile = crystals.get(3).getTileOn();
                    Utils.teleportPlayer(player, tile);
                }
            }
            Players.getPlayer(player).setInMenu(false);
        }));

        CrystalData[] crystals1 = new CrystalData[4];
        int i = 1;

        for (ObjectMap.Entry<Integer, CrystalData> crystal : crystals) {
            crystals1[i++] = crystal.value;
        }

        String[] first = crystals1[1].getTeamOwner() == Team.blue ? new String[]{"[royal]A"} : null;
        String[] second = crystals1[2].getTeamOwner() == Team.blue ? new String[]{"[royal]B"} : null;
        String[] third = crystals1[3].getTeamOwner() == Team.blue ? new String[]{"[royal]C"} : null;

        String[][] buttons = {
                first,
                second,
                third
        };

        Players.getPlayer(player).setInMenu(true);
        Call.menu(player.con, menu, "[cyan]Teleport Menu", "Choose Crystal to teleport", buttons);
    }
}
