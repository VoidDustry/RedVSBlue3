package net.voiddustry.redvsblue3.utils;

import static net.voiddustry.redvsblue3.utils.Time.getTime;

@SuppressWarnings("unused")
public class Log {

    static String stamp = " [gray][[red]R[gray]vs[blue]B[gray]]";

    public static void info(String text) {
        String textFinal = getTime() + stamp + " [blue][I] " + text;
        System.out.println(Colors.parseColors(textFinal));
    }

    public static void debug(String text) {
        if (!Utils.debug) return;

        String textFinal = getTime() + stamp + " [cyan][D] " + text;
        System.out.println(Colors.parseColors(textFinal));
    }

    public static void err(String text) {
        String textFinal = getTime() + stamp + " [red][E] " + text;
        System.out.println(Colors.parseColors(textFinal));
    }

    public static void warn(String text) {
        String textFinal = getTime() + stamp + " [lyellow][W] " + text;
        System.out.println(Colors.parseColors(textFinal));
    }
}
