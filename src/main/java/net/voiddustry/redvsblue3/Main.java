package net.voiddustry.redvsblue3;

import arc.util.CommandHandler;
import mindustry.mod.Plugin;
import net.voiddustry.redvsblue3.commands.Commands;

public class Main extends Plugin {
    @Override
    public void init() {
        Loader.load();
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        Commands.registerClientCommands(handler);
    }

    @Override
    public void registerServerCommands(CommandHandler handler) {
        Commands.registerServerCommands(handler);
    }
}
