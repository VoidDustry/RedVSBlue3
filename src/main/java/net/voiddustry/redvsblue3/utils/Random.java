package net.voiddustry.redvsblue3.utils;

import mindustry.Vars;
import mindustry.world.Tile;

import java.util.List;
import java.util.stream.StreamSupport;

@SuppressWarnings("unused")
public class Random {

    public static int getRandomInt(int min, int max) {
        return (int) Math.floor(Math.random() *(max - min + 1) + min);
    }

    public static Tile getRandomTile(boolean airOnly) {
        // if airOnly == true, method would return empty tile.

        if (!airOnly) {
            return Vars.world.tiles.geti(getRandomInt(0, Vars.world.width() * Vars.world.height()));
        }

        List<Tile> tileList = StreamSupport.stream(Vars.world.tiles.spliterator(), false)
                .filter(tile -> tile.block().isAir())
                .toList();
        return tileList.get(getRandomInt(0, tileList.size()));
    }
}