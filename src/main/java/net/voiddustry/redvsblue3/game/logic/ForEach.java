package net.voiddustry.redvsblue3.game.logic;

import arc.Events;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.world.Tile;
import net.voiddustry.redvsblue3.game.domain.PlayerData;
import net.voiddustry.redvsblue3.player.Players;

public class ForEach {
    public static void init() {
        Events.run(EventType.Trigger.update, () -> {

            for (Player player : Groups.player) {
                if (Game.loaded) {
                    Players.updateHud(player);

                    PlayerData playerData = Players.getPlayer(player);

                    if (playerData.unit != null && player.unit() != playerData.unit) {
                        player.unit(playerData.unit);
                    }

                    if (player.team() != playerData.getTeam()) {
                        player.team(playerData.getTeam());
                    }

                    if (player.team() == Team.blue) {
                        Tile tile = Vars.world.tile(Math.round(player.mouseX/8), Math.round(player.mouseY/8));
                        if (tile != null) {
                            Players.inspectBlock(tile, player.con);
                        }
                    }
                }

                if (Crystals.isPlayerStandsOnCrystal(player) && player.shooting) {
                    Crystals.openTeleportMenu(player);
                }
            }
        });
    }
}
