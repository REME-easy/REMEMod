package REMEMod.Actions;

import REMEMod.Helpers.REMEHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;

import java.util.ArrayList;

public class ShoggothAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("REME_CARD_SELECT");
    private static final String[] TEXT = uiStrings.TEXT;
    private AbstractCard owner;

    public ShoggothAction(AbstractCard owner) {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.DAMAGE;
        this.owner = owner;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startDuration) {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                if (AbstractDungeon.player.discardPile.size() > 0) {
                    for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                        if (c.cost != -2)
                            tmp.addToTop(c);
                    }
                }


                if (tmp.size() > 0) {
                    AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[2], false, false, true, false);
                }


            } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                ArrayList<AbstractCard> cards = new ArrayList<>();
                cards.add(owner);
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                cards.add(c);
                if (AbstractDungeon.player.hoveredCard == c) {
                    AbstractDungeon.player.releaseCard();
                }
                AbstractDungeon.actionManager.removeFromQueue(c);
                c.unhover();
                c.untip();
                c.stopGlowing();
                AbstractDungeon.player.discardPile.removeCard(c);
                AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
                AbstractDungeon.player.onCardDrawOrDiscard();
                ArrayList<AbstractCard> cards1 = new ArrayList<>();
                for (AbstractCard c1 : cards) {
                    cards1.add(c1.makeStatEquivalentCopy());
                }
                if(cards1.size() > 0)
                    this.addToBot(new MakeTempCardInHandAction(REMEHelper.makeSutureCard(cards1)));
                AbstractDungeon.closeCurrentScreen();
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone = true;
            }
            this.tickDuration();
        }
    }
}




