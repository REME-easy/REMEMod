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
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BattleFuryPower extends AbstractPower {
    private static final String POWER_ID = "REME_BattleFuryPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public BattleFuryPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.BUFF;
        String path128 = "remeImg/powers/BattleFury84.png";
        String path48 = "remeImg/powers/BattleFury32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        for(int i = amount ; i > 0 ; i--)
            if (!(card.isEthereal && card.exhaust) && !AbstractDungeon.actionManager.turnHasEnded && card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
                this.flash();
                this.updateDescription();
                AbstractCard c = card.makeStatEquivalentCopy();
                c.modifyCostForCombat(-9);
                c.isCostModified = true;
                c.isEthereal = true;
                c.exhaust = true;
                c.glowColor = Color.GREEN;
                c.rawDescription = c.rawDescription.concat(DESCRIPTIONS[2]);
                c.initializeDescription();
                this.addToBot(new MakeTempCardInHandSAction(c));
            }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
