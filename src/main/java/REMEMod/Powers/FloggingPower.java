package REMEMod.Powers;

import REMEMod.Actions.FloggingAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FloggingPower extends AbstractREMEPower {
    private static final String POWER_ID = "REME_FloggingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FloggingPower(AbstractCreature owner, int Amount2){
        this.name =NAME;
        this.owner = owner;
        this.amount = 1;
        this.amount2 = Amount2;
        this.ID = POWER_ID + "[" + amount2 + "]";
        this.type = AbstractPower.PowerType.BUFF;
        String path128 = "remeImg/powers/Brilliance84.png";
        String path48 = "remeImg/powers/Brilliance32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void onAfterCardPlayed(AbstractCard card) {
        this.flash();
        this.addToBot(new FloggingAction(amount, amount2));
    }

    public void updateDescription(){
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
    }
}
