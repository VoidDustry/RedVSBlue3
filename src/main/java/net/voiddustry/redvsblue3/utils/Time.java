package net.voiddustry.redvsblue3.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date date = new Date();
        return "[gray][" + formatter.format(date) + "]";
    }
}
