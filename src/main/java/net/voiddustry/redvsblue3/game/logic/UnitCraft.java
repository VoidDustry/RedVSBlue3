package net.voiddustry.redvsblue3.game.logic;

import arc.util.Timer;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.ui.Menus;
import net.voiddustry.redvsblue3.game.domain.UnitCraftList;
import net.voiddustry.redvsblue3.game.domain.UnitCraftTicket;
import net.voiddustry.redvsblue3.player.Bundle;
import net.voiddustry.redvsblue3.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UnitCraft {
    private static Map<String, UnitCraftTicket> craftTicketMap = new HashMap<>();

    public static void openMenu(Player player, int category) {
        String[][] unitsInRow = null;

        int menu = Menus.registerMenu((player1, option) -> {

        });

        String[] categories = {
                "[royal]S",
                "[purple]R",
                "[lime]L",
                "[gold]P"
        };



        switch (category) {
            case 1 -> {
                // standardUnits

                ArrayList<String[]> units = new ArrayList<>();

                units.add(categories);

                UnitCraftList.standardUnits.forEach(object -> {
                    String unitNameBundled = "[#B00BA5]" + Bundle.get("units." + object.key, player.locale);
                    units.add(new String[]{ unitNameBundled });
                });

                unitsInRow = units.toArray(String[][]::new);
            }

            case 2 -> {
                // resei use arch btwUnits


            }
            case 3 -> {
                // limitedUnits


            }
            case 4 -> {
                // premiumUnits


            }
        }



        Call.menu(player.con, menu, "todo", "todo", unitsInRow);
    }

    private static void renderTickets() {
        craftTicketMap.forEach((uuid, ticket) -> {
             if (Utils.timer >= ticket.createTime() + ticket.createDuration()) {

             }
        });
    }

    public static void initTimer() {
        Timer.schedule(UnitCraft::renderTickets, 0, 1);
    }
}
