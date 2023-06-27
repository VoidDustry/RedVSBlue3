package net.voiddustry.redvsblue3.commands;

import arc.util.CommandHandler;
import net.voiddustry.redvsblue3.utils.Log;
import net.voiddustry.redvsblue3.utils.Utils;

public class Commands {
    public static void registerClientCommands(CommandHandler handler) {

    }

    public static void registerServerCommands(CommandHandler handler) {
        handler.register("debug", "Enables debug mode. &rCannot be turned off", (args) -> {
            Utils.debug = true;
            Log.info("[purple]Enabled debugging");
        });
    }
}
