package net.voiddustry.redvsblue3.game;

import arc.struct.Seq;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Unit;
import net.voiddustry.redvsblue3.game.domain.PlayerData;
import net.voiddustry.redvsblue3.game.domain.UnitLoot;
import net.voiddustry.redvsblue3.game.domain.UnitLoots;
import net.voiddustry.redvsblue3.game.logic.Crystals;
import net.voiddustry.redvsblue3.game.logic.Game;
import net.voiddustry.redvsblue3.player.Players;
import net.voiddustry.redvsblue3.utils.Log;
import net.voiddustry.redvsblue3.utils.Utils;

public class Events {
    public static void load() {
        arc.Events.on(EventType.GameOverEvent.class, event -> Crystals.clearCrystals());

        arc.Events.on(EventType.PlayerJoin.class, event -> {
            mindustry.gen.Player player = event.player;
            Players.loadPlayer(player, false);

            String name = player.name;

            StringBuilder futureName = new StringBuilder();

            if (player.uuid().equals("voiddustrycAAAAAd7OpXA==")) {
                futureName.append("[cyan][\uE80F] ");
            } else if (player.admin) {
                futureName.append("[scarlet][\uE817] ");
            } else {
                futureName.append("[white][\uE872] ");
            }

            futureName.append(name);

            player.name = futureName.toString();


            Call.openURI(event.player.con, "https://rvlt.gg/WQaMNMZG");
        });

        arc.Events.on(EventType.PlayerLeave.class, event -> {
            PlayerData data = Players.getPlayer(event.player);

            data.setX(event.player.x);
            data.setY(event.player.y);

            data.rotation = event.player.unit().rotation;
            data.hp = event.player.unit().health;
            data.ammo = event.player.unit().ammo;

            if (data.unit != null) data.unit.kill();
        });

        arc.Events.on(EventType.UnitBulletDestroyEvent.class, event -> {
            if (event.unit.isPlayer()) {
                Players.getPlayer(event.unit.getPlayer()).setTeam(Team.crux);
                Players.getPlayer(event.unit.getPlayer()).setUnit(null);
            }

            if (event.unit != null && event.bullet.owner() instanceof Unit killer && Game.loaded) {
                Unit victim = event.unit;
                if (killer.team() == Team.blue && killer.isPlayer()) {
                    PlayerData data = Players.getPlayer(killer.getPlayer());

                    if (UnitLoots.loots.get(victim.type.toString()) != null) {
                        Seq<UnitLoot> lootSeq = UnitLoots.loots.get(victim.type.toString()).loots;
                        StringBuilder loot = new StringBuilder();

                        loot.append("[gray][ [white]");

                        lootSeq.forEach((item) -> {
                            int ran = Utils.getRandomInt(item.min(), item.max()+1);
                            data.addItem(item.item(), ran);

                            if (ran != 0) {
                                loot.append("+").append(ran).append(item.item().emoji()).append(" ");
                            }
                        });

                        loot.append("[gray]]");

                        if (loot.toString().contains("+")) {
                            Call.label(killer.getPlayer().con, loot.toString(), 3, victim.x, victim.y);
                        }
                    }
                }
            }

            if (Utils.BluePlayersDied()) {
                Game.over();
            }
        });
    }
}
