package net.voiddustry.redvsblue3;

import arc.Events;

import mindustry.game.EventType;
import net.voiddustry.redvsblue3.config.MapScripts;
import net.voiddustry.redvsblue3.config.Timers;
import net.voiddustry.redvsblue3.game.Rules;
import net.voiddustry.redvsblue3.game.ai.InitAI;
import net.voiddustry.redvsblue3.game.logic.Game;
import net.voiddustry.redvsblue3.game.logic.UnitCraft;
import net.voiddustry.redvsblue3.utils.Utils;

public class Loader {
    public static void load() {

        Utils.initTimer();
        MapScripts.loadScripts();
        Timers.init();
        net.voiddustry.redvsblue3.game.Events.load();
        Rules.initStats();
        UnitCraft.initTimer();

        InitAI.init();

        Events.on(EventType.WorldLoadEndEvent.class, event -> Game.start());
    }
}
