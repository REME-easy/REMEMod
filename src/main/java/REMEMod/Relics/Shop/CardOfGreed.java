package REMEMod.Relics.Shop;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CardOfGreed extends CustomRelic {
    private int counter2 = 0;
    private static float OffsetX2 = 0.0F;

    public CardOfGreed() {
        super("REME_CardOfGreed", ImageMaster.loadImage("remeImg/relics/CardOfGreed.png"),
                RelicTier.SHOP, LandingSound.MAGICAL);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(counter + 1 <= this.counter2){
            this.addToBot(new DrawCardAction(1));
            this.counter++;
            this.flash();
        }
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
        this.counter2 = getCount() / 10;
    }

    private int getCount(){
        return AbstractDungeon.player.masterDeck.size();
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        float target = (float)(-relicPage * Settings.WIDTH) + (float)relicPage * (PAD_X + 36.0F * Settings.scale);
        if (AbstractDungeon.player.relics.size() >= 26) {
            target += 36.0F * Settings.scale;
        }

        if (OffsetX2 != target) {
            OffsetX2 = MathHelper.uiLerpSnap(OffsetX2, target);
        }

        if (this.counter > -1) {
            if (inTopPanel) {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, this.counter + "/" + this.counter2,  OffsetX2 + this.currentX + 15.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
            } else {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, this.counter + "/" + this.counter2, this.currentX + 15.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new CardOfGreed();
    }
}
