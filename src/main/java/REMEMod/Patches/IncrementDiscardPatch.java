//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package REMEMod.Patches;

import REMEMod.Powers.AbstractREMEPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.Iterator;

public class IncrementDiscardPatch {
    public static int DiscardCardsThisBattle;
    public IncrementDiscardPatch() {
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "incrementDiscard",
            paramtypez = {boolean.class}
    )
    public static class DiscardPatch {
        public DiscardPatch() {
        }

        public static SpireReturn<Void> Prefix(boolean endOfTurn) {
            DiscardCardsThisBattle++;
            Iterator var1 = AbstractDungeon.player.powers.iterator();

            while(var1.hasNext()) {
                AbstractPower p = (AbstractPower)var1.next();
                if (p instanceof AbstractREMEPower) {
                    ((AbstractREMEPower)p).onManualDiscard(endOfTurn);
                }
            }

            return SpireReturn.Continue();
        }
    }
}
