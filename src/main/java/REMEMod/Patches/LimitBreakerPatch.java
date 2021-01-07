//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package REMEMod.Patches;


import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

public class LimitBreakerPatch {
    private static int amount = 0;
    private static int amount2 = 0;
    private static int amount3 = 0;
    private static int amount4 = 0;

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
            clz = FocusPower.class,
            method = "stackPower"
    )
    public static class stackPrefPatch {
        public stackPrefPatch() {
        }

        public static SpireReturn<Void> Prefix(FocusPower _inst, int stackAmount) {
            amount4 = _inst.amount;
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = FocusPower.class,
            method = "stackPower"
    )
    public static class stackPostfPatch {
        public stackPostfPatch() {
        }

        public static SpireReturn<Void> Postfix(FocusPower _inst, int stackAmount) {
            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")){
                if(amount4 + stackAmount < 2147483646){
                    _inst.amount = amount4 + stackAmount;
                }else{
                    _inst.amount = 2147483646;
                }

                if(amount4 + stackAmount > -2147483647){
                    _inst.amount = amount4 + stackAmount;
                }else{
                    _inst.amount = -2147483647;
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = FocusPower.class,
            method = "reducePower"
    )
    public static class reducePrefPatch {
        public reducePrefPatch() {
        }

        public static SpireReturn<Void> Prefix(FocusPower _inst, int stackAmount) {
            amount4 = _inst.amount;
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = FocusPower.class,
            method = "reducePower"
    )
    public static class reducePostfPatch {
        public reducePostfPatch() {
        }

        public static SpireReturn<Void> Postfix(FocusPower _inst, int stackAmount) {

            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                if (amount4 - stackAmount < 2147483646) {
                    _inst.amount = amount4 - stackAmount;
                } else {
                    _inst.amount = 2147483646;
                }

                if (amount4 - stackAmount > -2147483647) {
                    _inst.amount = amount4 - stackAmount;
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

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "increaseMaxOrbSlots"
    )
    public static class MaxOrbsPatch {
        public MaxOrbsPatch() {
        }

        public static SpireReturn<Void> Prefix(AbstractPlayer _inst, int amount, boolean playSfx) {
            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                if(_inst.maxOrbs + amount < 2147483640){
                    if (playSfx) {
                        CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
                    }

                    _inst.maxOrbs += amount;

                    int i;
                    for(i = 0; i < amount; ++i) {
                        _inst.orbs.add(new EmptyOrbSlot());
                    }

                    for(i = 0; i < _inst.orbs.size(); ++i) {
                        (_inst.orbs.get(i)).setSlot(i, _inst.maxOrbs);
                    }
                }
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }


    }

    @SpirePatch(
            clz = AbstractOrb.class,
            method = "setSlot"
    )
    public static class setSlotPatch {
        public setSlotPatch() {
        }

        public static SpireReturn<Void> Prefix(AbstractOrb _inst, int slotNum, int maxOrbs) {
            if(AbstractDungeon.player.hasRelic("REME_LimitBreaker")) {
                float dist = 160.0F * Settings.scale + (float)slotNum * 3.0F * Settings.scale;
                float angle = 100.0F + (float)maxOrbs * 12.0F;
                float offsetAngle = angle / 2.0F;
                angle *= (float)slotNum / ((float)maxOrbs - 1.0F);
                angle += 90.0F - offsetAngle;
                _inst.tX = dist * MathUtils.cosDeg(angle) + AbstractDungeon.player.drawX;
                _inst.tY = dist * MathUtils.sinDeg(angle) + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;
                if (maxOrbs == 1) {
                    _inst.tX = AbstractDungeon.player.drawX;
                    _inst.tY = 160.0F * Settings.scale + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;
                }

                _inst.hb.move(_inst.tX, _inst.tY);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }




    }
}
