package REMEMod.Powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbstractREMEPower extends AbstractPower {
    public int amount2 = -1;
    public boolean canGoNegative2 = false;
    private Color redColor2;
    private Color greenColor2;

    public AbstractREMEPower() {
        this.redColor2 = Color.RED.cpy();
        this.greenColor2 = Color.GREEN.cpy();
    }

    public void onManualDiscard(boolean endOfTurn) {
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (this.amount2 > 0) {
            if (!this.isTurnBased) {
                this.greenColor2.a = c.a;
                c = this.greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount2), x, y + 15.0F * Settings.scale, this.fontScale, c);
        } else if (this.amount2 < 0 && this.canGoNegative2) {
            this.redColor2.a = c.a;
            c = this.redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount2), x, y + 15.0F * Settings.scale, this.fontScale, c);
        }

    }

    public int changeCardRewardsNum(int num){
        return num;
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class CardRewardsPatch{
        @SpireInsertPatch(
                rloc = 4,
                localvars = {"numCards"}

        )
        public static void Insert(@ByRef int[] numCards){
            for(AbstractPower p:AbstractDungeon.player.powers){
                if(p instanceof AbstractREMEPower){
                    numCards[0] = ((AbstractREMEPower) p).changeCardRewardsNum(numCards[0]);
                }
            }
        }
    }
}
