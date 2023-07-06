package net.voiddustry.redvsblue3.game.ai;

import mindustry.Vars;
import mindustry.ai.Pathfinder;

import mindustry.type.UnitType;

public class InitAI {
    public static int bluePlayerTargeting;

    static {
        Pathfinder.fieldTypes.add(BluePlayerTarget::new);
        bluePlayerTargeting = Pathfinder.fieldTypes.size - 1;
    }
    public static void init() {

        for (UnitType unit : Vars.content.units()) {
            if (!unit.flying) {
                unit.aiController = StalkerGroundAI::new;
            }
            if (unit.flying) {
                unit.aiController = AirAI::new;
            }
            if (unit.naval) {
                unit.flying = true;
            }
        }
    }
}
