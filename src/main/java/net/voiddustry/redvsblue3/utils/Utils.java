package net.voiddustry.redvsblue3.utils;

import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.world.Tile;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Unit> getUnitsCountOnTile(Tile tile) {
        List<Unit> units = new ArrayList<>();

        int i = 0;
        for (Unit unit : Groups.unit) {
            if (unit.tileX() == tile.x && unit.tileY() == tile.y) {
                units.add(unit);
            }
        }

        return units;
    }

    public static boolean isTileHaveUnits(Tile tile) {
        for (Unit unit : Groups.unit) {
            if (unit.tileX() == tile.x && unit.tileY() == tile.y) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnitTeamOnTile(Tile tile, Team team) {
        for (Unit unit : Groups.unit) {
            if (unit.tileX() == tile.x && unit.tileY() == tile.y && unit.team == team) {
                return true;
            }
        }
        return false;
    }

    public static Team getOppositeTeam (Team team) {
        return team == Team.blue ? Team.crux : team == Team.crux ? Team.blue : null;
    }

    public static void teleportPlayer(Player player, Tile tile) {
        Unit oldUnit = player.unit();
        Unit newUnit = oldUnit.type.spawn(oldUnit.team, tile.x * 8, tile.y * 8);

        newUnit.health = oldUnit.health;
        newUnit.ammo = oldUnit.ammo;
        newUnit.spawnedByCore = oldUnit.spawnedByCore;
        newUnit.rotation = oldUnit.rotation;
        newUnit.type = oldUnit.type;

        player.unit(newUnit);
        oldUnit.kill();
    }
}
