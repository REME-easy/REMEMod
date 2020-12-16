package REMEMod.Relics.Boss;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class DemonsWealth extends CustomRelic {

    public DemonsWealth() {
        super("REME_DemonsWealth", ImageMaster.loadImage("remeImg/relics/DemonsWealth.png"),
                RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        CardCrawlGame.sound.play("NECRONOMICON");

    }

    public void onObtainCard(AbstractCard card) {
        if (card.color == AbstractCard.CardColor.CURSE) {
            AbstractDungeon.player.gainGold(200);
            CardCrawlGame.sound.play("NECRONOMICON");
        }

    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 35;
    }


    public AbstractRelic makeCopy() {
        return new DemonsWealth();
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
            if(AbstractDungeon.player.hasRelic("REME_DemonsWealth"))
                retVal[0].add(AbstractDungeon.returnRandomCurse().makeCopy());
        }
    }
}
