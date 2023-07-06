package net.voiddustry.redvsblue3.game.domain;

import mindustry.type.Item;

public record UnitLoot(Item item, int min, int max) {}