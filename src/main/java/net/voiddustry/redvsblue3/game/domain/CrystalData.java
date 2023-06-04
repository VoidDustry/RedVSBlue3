package net.voiddustry.redvsblue3.game.domain;

import mindustry.game.Team;
import mindustry.world.Tile;

public class CrystalData {
    private String type;
    private int number;
    private Team teamOwner;
    private Tile tileOn;
    private Integer infectPercentage;

    public CrystalData(String type, int i, Team team, Tile tile) {
        this.type = type;
        this.number = i;
        this.teamOwner = team;
        this.tileOn = tile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTeamOwner(Team team) {
        this.teamOwner = team;
    }

    public void setTileOn(Tile tile) {
        this.tileOn = tile;
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
}
