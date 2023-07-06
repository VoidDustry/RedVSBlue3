package net.voiddustry.redvsblue3.game.vars;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.type.UnitType;
import net.voiddustry.redvsblue3.game.domain.UnitLoot;

public enum UnitResources {
    CRAWLER(UnitTypes.crawler, new Seq<UnitLoot>().add( new UnitLoot(Items.copper, 0, 1), new UnitLoot(Items.coal, 2, 4), new UnitLoot(Items.blastCompound, 0, 1))),
    DAGGER(UnitTypes.dagger, new Seq<UnitLoot>().add( new UnitLoot(Items.copper, 0, 2), new UnitLoot(Items.lead, 0, 2), new UnitLoot(Items.silicon, 0, 1)) );

    public final UnitType unitType;
    public final Seq<UnitLoot> loots;

    UnitResources(mindustry.type.UnitType unitType, Seq<UnitLoot> loots) {
        this.unitType = unitType;
        this.loots = loots;
    }
}
