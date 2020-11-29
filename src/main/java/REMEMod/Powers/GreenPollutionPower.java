package REMEMod.Powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

import java.util.Iterator;

public class GreenPollutionPower extends AbstractREMEPower {
    private static final String POWER_ID = "REME_GreenPollutionPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public GreenPollutionPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = "REME_GreenPollutionPower";
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.BUFF;
        this.loadRegion("fumes");
        this.updateDescription();
    }

    public void onManualDiscard(boolean endOfTurn) {
        if (!endOfTurn && AbstractDungeon.getMonsters().monsters != null && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
            this.flash();
            Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var2.hasNext()) {
                AbstractMonster m = (AbstractMonster)var2.next();
                if (m != null && !m.isDeadOrEscaped() && !m.isDead) {
                    this.addToBot(new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount), this.amount));
                }
            }
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("REME_GreenPollutionPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
