package net.voiddustry.redvsblue3.game;

import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.UnitTypes;
import mindustry.gen.Call;
public class Rules {
    public static void init() {
        Vars.state.rules.canGameOver = false;
        Vars.state.rules.unitCap = 100;

        Vars.state.rules.bannedBlocks.addAll(Vars.content.blocks());
        Vars.state.rules.bannedUnits.add(UnitTypes.alpha);
        Vars.state.rules.bannedUnits.add(UnitTypes.beta);
        Vars.state.rules.bannedUnits.add(UnitTypes.gamma);

//        Vars.state.rules.waveTimer = false;
        Vars.state.rules.hideBannedBlocks = true;
//        Vars.state.rules.unitAmmo = true;
        Vars.state.rules.schematicsAllowed = false;

        Call.setRules(Vars.state.rules);
    }

    public static void initStats() {
        Blocks.tungstenWall.health = Integer.MAX_VALUE;
        Blocks.tungstenWall.targetable = false;
    }
}
