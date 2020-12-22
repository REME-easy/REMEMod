package REMEMod.Potions;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class GoldPotion extends AbstractREMEPotion {
    public static final String POTION_ID = "REME_GoldPotion";
    private static final PotionStrings PS = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final String NAME = PS.NAME;
    private static final String[] DESCRIPTIONS = PS.DESCRIPTIONS;

    public GoldPotion() {
        super(NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, PotionSize.FAIRY, PotionColor.ENERGY);
        this.potency = this.getPotency();
        this.description = this.getDesc();
        this.isThrown = false;
        this.targetRequired = false;
    }

    public String getDesc() {
        return DESCRIPTIONS[0] + this.getPotency() + DESCRIPTIONS[1];
    }

    public void use(AbstractCreature target) {
        this.addToBot(new GainGoldAction(this.potency));
    }

    public AbstractPotion makeCopy() {
        return new GoldPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 30;
    }
}
