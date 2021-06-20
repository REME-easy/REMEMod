package REMEMod.Potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

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

    public boolean canUse() {
        if (AbstractDungeon.actionManager.turnHasEnded && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            return false;
        } else {
            return AbstractDungeon.getCurrRoom().event == null || !(AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain);
        }
    }

    public String getDesc() {
        return DESCRIPTIONS[0] + this.getPotency() + DESCRIPTIONS[1];
    }

    public void use(AbstractCreature target) {
        AbstractDungeon.player.gainGold(this.getPotency());
    }

    public AbstractPotion makeCopy() {
        return new GoldPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 30;
    }
}
