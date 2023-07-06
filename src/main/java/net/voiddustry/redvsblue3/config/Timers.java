package net.voiddustry.redvsblue3.config;

import net.voiddustry.redvsblue3.game.logic.ForEach;
import net.voiddustry.redvsblue3.game.logic.Crystals;

public class Timers {
    public static void init() {
        Crystals.initTimer();
        ForEach.init();
    }
}
