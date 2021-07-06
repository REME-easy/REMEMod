package REMEMod.Relics.Boss;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BlackStar;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class WhiteStar extends CustomRelic {

    public WhiteStar() {
        super("REME_WhiteStar", ImageMaster.loadImage("remeImg/relics/WhiteStar.png"),
                RelicTier.BOSS, LandingSound.CLINK);
        this.counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onVictory() {
        this.counter++;
        this.flash();
        this.pulse = false;
        if (this.counter >= 5) {
            if (AbstractDungeon.getCurrRoom() != null) {
                RelicTier t;
                int i = AbstractDungeon.relicRng.random(0, 99);
                if (i <= 40) {
                    t = RelicTier.COMMON;
                }else if (i <= 70) {
                    t = RelicTier.UNCOMMON;
                }else {
                    t = RelicTier.RARE;
                }
                AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(t)));
                if (AbstractDungeon.player.hasRelic(BlackStar.ID)) {
                    i = AbstractDungeon.relicRng.random(0, 99);
                    if (i <= 40) {
                        t = RelicTier.COMMON;
                    }else if (i <= 70) {
                        t = RelicTier.UNCOMMON;
                    }else {
                        t = RelicTier.RARE;
                    }
                    AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(t)));
                }
            }
            this.counter = 0;
        }
    }

    public AbstractRelic makeCopy() {
        return new WhiteStar();
    }
}
