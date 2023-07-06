package net.voiddustry.redvsblue3.game.domain;

import mindustry.type.UnitType;

public record UnitCraftTicket(int createTime, int createDuration, UnitType unitType, boolean changeUnitWhenDone) {}
