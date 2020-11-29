package REMEMod.Powers;

import REMEMod.Actions.MakeTempCardInHandSAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class ShadowFormPower extends AbstractREMEPower {
    private static final String POWER_ID = "REME_ShadowFormPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public ShadowFormPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = "REME_ShadowFormPower";
        this.owner = owner;
        this.amount = Amount;
        this.amount2 = 0;
        this.type = PowerType.BUFF;
        String path128 = "img/powers/pmmod.png";
        String path48 = "img/powers/pmmod.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.amount2 = 0;
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if ((card.glowColor != Color.GREEN || !card.isEthereal || !card.exhaust) && !AbstractDungeon.actionManager.turnHasEnded && this.amount2 >= 0 && this.amount2 < this.amount) {
            this.flash();
            ++this.amount2;
            this.updateDescription();
            AbstractCard c = card.makeStatEquivalentCopy();
            c.modifyCostForCombat(-9);
            c.isCostModified = true;
            c.isEthereal = true;
            c.exhaust = true;
            c.glowColor = Color.GREEN;
            c.rawDescription = c.rawDescription + DESCRIPTIONS[3];
            c.initializeDescription();
            this.addToBot(new MakeTempCardInHandSAction(c));
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount - this.amount2) + DESCRIPTIONS[2];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("REME_ShadowFormPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
