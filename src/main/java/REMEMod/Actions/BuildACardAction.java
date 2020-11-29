package REMEMod.Actions;

import REMEMod.Cards.Colorless.Skill.BuildACard;
import REMEMod.Cards.Colorless.Skill.SutureCard;
import REMEMod.Helpers.REMECardHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import java.util.ArrayList;
import java.util.Iterator;

public class BuildACardAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    private static final String[] TEXT;
    private int num;
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotChoose = new ArrayList<>();

    public BuildACardAction(int num) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.num = num;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        Iterator var1;
        AbstractCard c;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            var1 = this.p.hand.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (!this.isChooseAble(c)) {
                    this.cannotChoose.add(c);
                }
            }

            if (this.cannotChoose.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            this.p.hand.group.removeAll(this.cannotChoose);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[2], this.num, true, true);
                this.tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            ArrayList<AbstractCard> cards = new ArrayList<>();
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (AbstractDungeon.player.hoveredCard == c) {
                        AbstractDungeon.player.releaseCard();
                    }

                    AbstractDungeon.actionManager.removeFromQueue(c);
                    c.unhover();
                    c.untip();
                    c.stopGlowing();
                    this.p.hand.removeCard(c);
                    AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
                    this.p.onCardDrawOrDiscard();
                    if (c instanceof SutureCard) {
                        for(AbstractCard card:((SutureCard) c).sutureCards) {
                            cards.add(REMECardHelper.makeStatEquivalentCopy(card));
                        }
                    } else {
                        cards.add(REMECardHelper.makeStatEquivalentCopy(c));
                    }
                }

                if(cards.size() > 0)
                    this.addToBot(new MakeTempCardInHandSAction(new SutureCard(cards)));
                this.returnCards();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                this.isDone = true;

        }

        this.tickDuration();
    }

    private void returnCards() {
        for(AbstractCard c:this.cannotChoose) {
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    private boolean isChooseAble(AbstractCard card) {
        return !(card instanceof BuildACard) && card.cost != -1;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("REME_CARD_SELECT");
        TEXT = uiStrings.TEXT;
    }
}
