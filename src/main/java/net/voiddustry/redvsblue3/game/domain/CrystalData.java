package net.voiddustry.redvsblue3.game.domain;

import mindustry.game.Team;
import mindustry.world.Tile;

@SuppressWarnings("unused")
public class CrystalData {
    private String type;
    private int number;
    private Team teamOwner;
    private Tile tileOn;
    private Integer infectPercentage;

    public CrystalData(String type, int i, Team team, Tile tile, int infectPercentage) {
        this.type = type;
        this.number = i;
        this.teamOwner = team;
        this.tileOn = tile;
        this.infectPercentage = infectPercentage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setTeamOwner(Team team) {
        this.teamOwner = team;
    }

    public Team getTeamOwner() {
        return this.teamOwner;
    }

    public void setTileOn(Tile tile) {
        this.tileOn = tile;
    }

    public Tile getTileOn() {
        return this.tileOn;
    }

    public void setInfectPercentage(int i) {
        this.infectPercentage = i;
    }

    public void addInfectPercentage(int i) {
        this.infectPercentage += i;
    }

    public void substractInfectPercentage(int i) {
        this.infectPercentage -= i;
    }

    public int getInfectPercentage() {
        return this.infectPercentage;
    }
}
