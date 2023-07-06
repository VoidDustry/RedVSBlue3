package net.voiddustry.redvsblue3.game.domain;

import arc.struct.ObjectMap;
import net.voiddustry.redvsblue3.game.vars.UnitResources;

public class UnitLoots {
    public final static ObjectMap<String, UnitResources> loots = ObjectMap.of(
            "dagger", UnitResources.DAGGER
    );
}
