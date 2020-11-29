package REMEMod.Powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class SelfUpgradePower extends AbstractREMEPower {
    private static final String POWER_ID = "REME_SelfUpgradePower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public SelfUpgradePower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.BUFF;
        this.loadRegion("repair");
        this.updateDescription();
    }

    @Override
    public int changeCardRewardsNum(int num) {
        return num + this.amount;
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
