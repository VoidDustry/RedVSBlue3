package net.voiddustry.redvsblue3.utils;

import arc.struct.ObjectMap;
import arc.struct.StringMap;

public class Colors {
    private static final StringMap colors = StringMap.of(
            "[white]", "\u001B[37m",
            "[gray]", "\u001b[90m",

            "[scarlet]", "\u001B[31m",
            "[brown]", "\u001B[33m",
            "[green]", "\u001B[32m",
            "[blue]", "\u001B[34m",
            "[cyan]", "\u001B[36m",
            "[purple]", "\u001B[35m",

            "[red]", "\u001B[91m",
            "[yellow]", "\u001B[93m",
            "[lime]", "\u001B[92m",
            "[lblue]", "\u001B[94m",
            "[lcyan]", "\u001B[96m",
            "[lpurple]", "\u001B[95m",

            "[r]", "\u001B[0m",
            "[b]", "\u001B[1m",
            "[i]", "\u001B[3m",
            "[u]", "\u001B[4m"
    );

    public static String parseColors(String text) {
        String string = text;

        for (ObjectMap.Entry<String, String> color : colors) {
            string = string.replace(color.key, color.value);
        }

        return string;
    }
}
