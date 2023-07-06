package net.voiddustry.redvsblue3.game.domain;

import mindustry.world.Tile;

public record MapScriptData(int blueSpawnX, int blueSpawnY, boolean saveCore, boolean verticalBorder, int borderWidth) {}
