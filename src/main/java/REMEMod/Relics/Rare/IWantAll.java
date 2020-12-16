package REMEMod.Relics.Rare;

import REMEMod.Screens.WantAllButton;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;

import java.util.ArrayList;

public class IWantAll extends CustomRelic {
    private static String ID = "REME_IWantAll";
    private static WantAllButton wantAllButton;

    public IWantAll() {
        super(ID, ImageMaster.loadImage("remeImg/relics/IWantAll.png"),
                RelicTier.RARE, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new IWantAll();
    }

    @SpirePatch(
            clz = CardRewardScreen.class,
            method = "open"
    )
    public static class WantAllOpenPatch{
        public WantAllOpenPatch(){}

        public static void Prefix(CardRewardScreen _inst, ArrayList<AbstractCard> cards, RewardItem rItem, String header){
            if(AbstractDungeon.player.hasRelic(ID)){
                wantAllButton = new WantAllButton();
                IWantAll.wantAllButton.show(rItem);
            }
        }
    }

    @SpirePatch(
            clz = CardRewardScreen.class,
            method = "update"
    )
    public static class WantAllUpdatePatch{
        public WantAllUpdatePatch(){}

        public static void Prefix(CardRewardScreen _inst){
            if(AbstractDungeon.player.hasRelic(ID) && !PeekButton.isPeeking){
                wantAllButton.update();
            }
        }
    }

    @SpirePatch(
            clz = CardRewardScreen.class,
            method = "render"
    )
    public static class WantAllRenderPatch{
        public WantAllRenderPatch(){}

        public static void Prefix(CardRewardScreen _inst, SpriteBatch sb){
            if(AbstractDungeon.player.hasRelic(ID) && !PeekButton.isPeeking){
                wantAllButton.render(sb);
            }
        }
    }
}
