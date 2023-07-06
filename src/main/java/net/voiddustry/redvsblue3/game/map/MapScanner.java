package net.voiddustry.redvsblue3.game.map;

import arc.struct.Seq;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.world.Tile;

public class MapScanner {
    public static Seq<Tile> scanCruxSpawns() {
        Seq<Tile> spawns = new Seq<>();

        for (int x = 0; x < Vars.state.map.width; x++) {
            for (int y = 0; y < Vars.state.map.height; y++) {
                Tile tile = Vars.world.tile(x, y);
                if (tile.overlay() == Blocks.spawn) {
                    spawns.add(tile);
                }
            }
        }
        return spawns;
    }
}
