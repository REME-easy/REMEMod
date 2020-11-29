//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package REMEMod.Patches;


import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class LimitBreakerPatch {
    private static int amount = 0;
    private static int amount2 = 0;
    private static int amount3 = 0;

    public LimitBreakerPatch() {
    }

    @SpirePatch(
            clz = StrengthPower.class,
            method = "stackPower"
            )
    public static class stackPrePatch {
        public stackPrePatch() {
        }

        public static SpireReturn<Void> Prefix(StrengthPower _inst, int stackAmount) {
            amount = _inst.amount;
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = StrengthPower.class,
            method = "stackPower"
    )
    public static class stackPostPatch {
        public stackPostPatch() {
        }

        public static SpireReturn<Void> Postfix(StrengthPower _inst, int stackAmount) {
            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")){
                if(amount + stackAmount < 2147483646){
                    _inst.amount = amount + stackAmount;
                }else{
                    _inst.amount = 2147483646;
                }

                if(amount + stackAmount > -2147483647){
                    _inst.amount = amount + stackAmount;
                }else{
                    _inst.amount = -2147483647;
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = StrengthPower.class,
            method = "reducePower"
    )
    public static class reducePrePatch {
        public reducePrePatch() {
        }

        public static SpireReturn<Void> Prefix(StrengthPower _inst, int stackAmount) {
            amount = _inst.amount;
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = StrengthPower.class,
            method = "reducePower"
    )
    public static class reducePostPatch {
        public reducePostPatch() {
        }

        public static SpireReturn<Void> Postfix(StrengthPower _inst, int stackAmount) {

            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                if (amount - stackAmount < 2147483646) {
                    _inst.amount = amount - stackAmount;
                } else {
                    _inst.amount = 2147483646;
                }

                if (amount - stackAmount > -2147483647) {
                    _inst.amount = amount - stackAmount;
                } else {
                    _inst.amount = -2147483647;
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = DexterityPower.class,
            method = "stackPower"
    )
    public static class stackPredPatch {
        public stackPredPatch() {
        }

        public static SpireReturn<Void> Prefix(DexterityPower _inst, int stackAmount) {
            amount2 = _inst.amount;
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = DexterityPower.class,
            method = "stackPower"
    )
    public static class stackPostdPatch {
        public stackPostdPatch() {
        }

        public static SpireReturn<Void> Postfix(DexterityPower _inst, int stackAmount) {
            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")){
                if(amount2 + stackAmount < 2147483646){
                    _inst.amount = amount2 + stackAmount;
                }else{
                    _inst.amount = 2147483646;
                }

                if(amount2 + stackAmount > -2147483647){
                    _inst.amount = amount2 + stackAmount;
                }else{
                    _inst.amount = -2147483647;
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = DexterityPower.class,
            method = "reducePower"
    )
    public static class reducePredPatch {
        public reducePredPatch() {
        }

        public static SpireReturn<Void> Prefix(DexterityPower _inst, int stackAmount) {
            amount2 = _inst.amount;
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = DexterityPower.class,
            method = "reducePower"
    )
    public static class reducePostdPatch {
        public reducePostdPatch() {
        }

        public static SpireReturn<Void> Postfix(DexterityPower _inst, int stackAmount) {

            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                if (amount2 - stackAmount < 2147483646) {
                    _inst.amount = amount2 - stackAmount;
                } else {
                    _inst.amount = 2147483646;
                }

                if (amount2 - stackAmount > -2147483647) {
                    _inst.amount = amount2 - stackAmount;
                } else {
                    _inst.amount = -2147483647;
                }
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractCreature.class,
            method = "addBlock"
    )
    public static class blockPrePatch {
        public blockPrePatch() {
        }

        public static SpireReturn<Void> Prefix(AbstractCreature _inst, int blockAmount) {
            if(_inst.isPlayer && AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                amount3 = _inst.currentBlock;
            }
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "addBlock"
    )
    public static class blockPostPatch {
        public blockPostPatch() {
        }

        public static SpireReturn<Void> Postfix(AbstractCreature _inst, int blockAmount) {
            if(_inst.isPlayer && AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                float tmp = (float)blockAmount;
                    Iterator var3;
                    AbstractRelic r;
                    for(var3 = AbstractDungeon.player.relics.iterator(); var3.hasNext(); tmp = (float)r.onPlayerGainedBlock(tmp)) {
                        r = (AbstractRelic)var3.next();
                    }
                if(_inst.currentBlock + tmp > 999){
                    if(_inst.currentBlock > 999){
                        tmp += _inst.currentBlock - 999;
                    }
                    if (amount3 + tmp < 2147483640) {
                        _inst.currentBlock = amount3 + MathUtils.floor(tmp);
                    } else {
                        _inst.currentBlock = 2147483640;
                    }
                }
            }
            return SpireReturn.Continue();
        }


    }
}
