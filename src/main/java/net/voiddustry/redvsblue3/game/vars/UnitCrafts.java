package net.voiddustry.redvsblue3.game.vars;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.type.UnitType;
import net.voiddustry.redvsblue3.game.domain.CraftIngIngredient;

public enum UnitCrafts {
    DAGGER(UnitTypes.dagger, new Seq<CraftIngIngredient>().add( new CraftIngIngredient(Items.copper, 2), new CraftIngIngredient(Items.silicon, 1) ) ),
    NOVA(UnitTypes.nova, new Seq<CraftIngIngredient>().add( new CraftIngIngredient(Items.lead, 8), new CraftIngIngredient(Items.titanium, 2), new CraftIngIngredient(Items.silicon, 6))),

    SPIROCT(UnitTypes.spiroct, new Seq<CraftIngIngredient>().add( new CraftIngIngredient(Items.copper, 5), new CraftIngIngredient(Items.silicon, 2)));

    public final UnitType unitType;
    public final Seq<CraftIngIngredient> ingredients;

    UnitCrafts(mindustry.type.UnitType unitType, Seq<CraftIngIngredient> craftingIngredients) {
        this.unitType = unitType;
        this.ingredients = craftingIngredients;
    }
}
