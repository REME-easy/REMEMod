package REMEMod.Relics.Boss;


import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;

public class LowDesire extends CustomRelic {
    private boolean cardsReceived = true;

    public LowDesire() {
        super("REME_LowDesire", ImageMaster.loadImage("img/relics/LowDesire.png"),
                RelicTier.BOSS, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.cardsReceived = false;
    }

    public void update() {
        super.update();
        if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.COMMON)));
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.COMMON)));
            AbstractDungeon.combatRewardScreen.positionRewards();
            AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[2]);
            this.cardsReceived = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }
    }

    public AbstractRelic makeCopy() {
        return new LowDesire();
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class DemonsWealthPatch{
        @SpireInsertPatch(
                rloc = 60,
                localvars = {"retVal"}
        )
        public static void Insert(@ByRef ArrayList<AbstractCard>[] retVal){
            if(AbstractDungeon.player.hasRelic("REME_LowDesire"))
                retVal[0].add(AbstractDungeon.returnTrulyRandomColorlessCardFromAvailable(new CurseOfTheBell(), AbstractDungeon.cardRandomRng).makeCopy());
        }
    }
}
