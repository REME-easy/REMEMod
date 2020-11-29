package REMEMod.Relics.Shop;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;

import java.util.ArrayList;

public class OnlineShoppingPlatform extends CustomRelic {
    private static final int PRICE = 20;
    private static final UIStrings uistrings = CardCrawlGame.languagePack.getUIString("REME_P");

    public OnlineShoppingPlatform() {
        super("REME_OnlineShoppingPlatform", ImageMaster.loadImage("img/relics/OnlineShoppingPlatform.png"),
                RelicTier.SHOP, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public static AbstractPotion getRandomPotion(){
        AbstractPotion p;
        do{
            p = AbstractDungeon.returnRandomPotion(false);
        }while (p instanceof FairyPotion);
        return p;
    }

    @Override
    public void onEquip() {
        super.onEquip();
        if(!AbstractDungeon.player.hasRelic("Sozu")){
            int i = 0;
            for(AbstractPotion p:AbstractDungeon.player.potions){
                if(p instanceof PotionSlot){
                    i++;
                }
            }
            for(int j = i ; j > 0 ; j--){
                AbstractDungeon.player.obtainPotion(getRandomPotion());
            }
        }

    }

    @Override
    public void update() {
        super.update();
    }

    public AbstractRelic makeCopy() {
        return new OnlineShoppingPlatform();
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "destroyPotion"
    )
    public static class UsePotionPatch{
        public UsePotionPatch(){}

        public static void Postfix(TopPanel _inst){
            if(AbstractDungeon.player.hasRelic("REME_OnlineShoppingPlatform") && !AbstractDungeon.player.hasRelic("Sozu")){
                int i = 0;
                for(AbstractPotion p:AbstractDungeon.player.potions){
                    if(p instanceof PotionSlot){
                        i++;
                    }
                }
                for(int j = i ; j > 0 ; j--){
                    AbstractDungeon.player.obtainPotion(getRandomPotion());
                }
            }
        }


    }

    @SpirePatch(
            clz = PotionPopUp.class,
            method = "updateInput"
    )
    public static class OpenPotionPopUpPatch{
        public OpenPotionPopUpPatch(){}

        @SpireInsertPatch(
                locator = OpenPotionPopUpPatch.Locator.class
        )
        public static SpireReturn<Void> Insert(PotionPopUp _inst){
            if(AbstractDungeon.player.hasRelic("REME_OnlineShoppingPlatform") && AbstractDungeon.player.gold < PRICE){
                AbstractDungeon.actionManager.addToBottom(new TalkAction(true, uistrings.TEXT[4], 1.0F, 2.0F));

                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "add");
                int[] i = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                return new int[]{i[0]};
            }
        }
    }

    @SpirePatch(
            clz = PotionPopUp.class,
            method = "updateInput"
    )
    public static class SpendMoneyPatch{
        public SpendMoneyPatch(){}

        @SpireInsertPatch(
                locator = SpendMoneyPatch.Locator.class
        )
        public static void Insert(PotionPopUp _inst){
            if(AbstractDungeon.player.hasRelic("REME_OnlineShoppingPlatform") && AbstractDungeon.player.gold >= PRICE){
                AbstractDungeon.player.loseGold(PRICE);
            }
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPotion.class, "use");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = PotionPopUp.class,
            method = "updateTargetMode"
    )
    public static class SpendMoneyPatch2{
        public SpendMoneyPatch2(){}

        @SpireInsertPatch(
                locator = SpendMoneyPatch2.Locator.class
        )
        public static void Insert(PotionPopUp _inst){
            if(AbstractDungeon.player.hasRelic("REME_OnlineShoppingPlatform") && AbstractDungeon.player.gold >= PRICE){
                AbstractDungeon.player.loseGold(PRICE);
            }
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPotion.class, "use");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = PotionPopUp.class,
            method = "close"
    )
    public static class UsePotionPatch2{
        public UsePotionPatch2(){}

        public static void Postfix(PotionPopUp _inst){
            if(AbstractDungeon.player.hasRelic("REME_OnlineShoppingPlatform") && AbstractDungeon.player.gold < PRICE && _inst.targetMode){
                AbstractDungeon.actionManager.addToBottom(new TalkAction(true, uistrings.TEXT[4], 1.0F, 2.0F));
                _inst.targetMode = false;
            }
        }
    }

}
