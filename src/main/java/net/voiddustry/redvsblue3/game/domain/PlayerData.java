package net.voiddustry.redvsblue3.game.domain;

import mindustry.game.Team;
import mindustry.gen.Player;
import mindustry.gen.Unit;
import mindustry.type.Item;
import mindustry.type.UnitType;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private String name;
    private final String uuid;
    private final String ip;
    private boolean inMenu;
    private Team team;
    public Unit unit;
    public float hp, ammo, rotation, x, y;
    private UnitType unitType;
    private Map<Item, Integer> inv;

    public PlayerData(Player player) {
        this.name = player.name;
        this.uuid = player.uuid();
        this.ip = player.ip();
        this.inMenu = false;
        this.team = player.team();
        this.unit = null;
        this.unitType = player.unit().type;
        this.inv = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getIp() {
        return this.ip;
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean value) {
        this.inMenu = value;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Map<Item, Integer> getInv() {
        return inv;
    }

    public int getItemsCount(Item item) {
        if (inv.get(item) != null) {
            return inv.get(item);
        } else {
            return 0;
        }
    }

    public void addItem(Item item, int count) {
        inv.merge(item, count, Integer::sum);
    }

    public void setInv(Map<Item, Integer> inv) {
        this.inv = inv;
    }


}
