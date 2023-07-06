package net.voiddustry.redvsblue3.player;

import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Unit;
import mindustry.net.NetConnection;
import mindustry.world.Tile;
import net.voiddustry.redvsblue3.game.domain.PlayerData;
import net.voiddustry.redvsblue3.game.logic.Game;
import net.voiddustry.redvsblue3.utils.Log;

import java.util.HashMap;
import java.util.Map;

public class Players {
    private static Map<String, PlayerData> players = new HashMap<>();

    public static Map<String, PlayerData> getPlayersMap() {
        return players;
    }

    public static void clearPlayersMap() {
        players.clear();
    }

    public static PlayerData getPlayer(mindustry.gen.Player player) {
        if (!players.containsKey(player.uuid())) {
            Log.info(player.plainName() + " does not have a PlayerData. Creating new...");
            loadPlayer(player, false);
        }
        return players.get(player.uuid());
    }

    public static void loadPlayer(mindustry.gen.Player player, boolean force) {
        if (force) {

        } else {
            if (players.containsKey(player.uuid())) {
                PlayerData data = players.get(player.uuid());

                Unit spawned = data.unit.type.spawn(data.getTeam(), data.getX(), data.getY());

                spawned.rotation = data.rotation;
                spawned.health = data.hp;
                spawned.ammo = data.ammo;

                if (!spawned.dead()) {
                    data.setUnit(spawned);
                } else {
                    Log.err("Spawned unit for " + player.plainName() + " is dead");
                }
            } else {
                players.put(player.uuid(), new PlayerData(player));
                if (Game.loaded) {
                    Game.spawnPlayer(player);
                }
            }
        }
    }


    public static void inspectBlock(Tile tile, NetConnection con) {
        if (tile.block().canBeBuilt()) {
            Building building = tile.build;
            Call.label(con, "[scarlet]"+Math.round(building.health),0.01F, building.tile.centerX()*8, building.tile.centerY()*8);
        }
    }

    public static void updateHud(mindustry.gen.Player player) {
        if (!Game.loaded) {
            return;
        }

        PlayerData data = Players.getPlayer(player);

        StringBuilder hud = new StringBuilder();
        
        int hpPercentage = (int) (player.unit().health / player.unit().type.health * 100);

        hud.append("[gray][ [lime]");
        hud.append("| ".repeat(hpPercentage / 5));

        hud.append("[red]");
        hud.append("| ".repeat(20 - hpPercentage / 5));

        hud.append("[gray]] [yellow]").append(hpPercentage).append("%").append("\n[white]");

        data.getInv().forEach((item, count) -> {
            hud.append(count).append(item.emoji()).append(" ");
        });

        Call.setHudText(player.con, hud.toString());
    }
}
