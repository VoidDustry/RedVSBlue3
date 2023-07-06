package net.voiddustry.redvsblue3.game.domain;

import arc.struct.ObjectMap;
import net.voiddustry.redvsblue3.game.logic.UnitCraft;
import net.voiddustry.redvsblue3.game.vars.UnitCrafts;

public class UnitCraftList {
    public final static ObjectMap<String, UnitCrafts> limitedUnits = ObjectMap.of(
            "dagger", UnitCrafts.DAGGER,
            "spiroct", UnitCrafts.SPIROCT
    );
    public final static ObjectMap<String, UnitCrafts> standardUnits = ObjectMap.of(
            "dagger", UnitCrafts.DAGGER,
            "spiroct", UnitCrafts.SPIROCT
    );
    public final static ObjectMap<String, UnitCrafts> researchUnits = ObjectMap.of(
            "dagger", UnitCrafts.DAGGER,
            "spiroct", UnitCrafts.SPIROCT
    );
    public final static ObjectMap<String, UnitCrafts> premiumUnits = ObjectMap.of(
            "dagger", UnitCrafts.DAGGER,
            "spiroct", UnitCrafts.SPIROCT
    );
}
