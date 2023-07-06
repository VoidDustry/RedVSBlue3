package net.voiddustry.redvsblue3.commands;

import arc.util.CommandHandler;
import mindustry.gen.Player;
import net.voiddustry.redvsblue3.game.logic.UnitCraft;
import net.voiddustry.redvsblue3.utils.Log;
import net.voiddustry.redvsblue3.utils.Utils;

public class Commands {
    public static void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("c", "Opens units craft menu", (args, player) -> {
            UnitCraft.openMenu(player, 1);
        });
    }

    public static void registerServerCommands(CommandHandler handler) {
        handler.register("debug", "Enables debug mode. &rCannot be turned off", (args) -> {
            Utils.debug = true;
            Log.info("[purple]Enabled debugging");
        });
    }
}
