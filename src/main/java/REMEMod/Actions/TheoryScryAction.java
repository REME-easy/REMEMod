package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class TheoryScryAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private float startingDuration;

    public TheoryScryAction(int numCards) {
        this.amount = numCards;
        if (AbstractDungeon.player.hasRelic("GoldenEye")) {
            AbstractDungeon.player.getRelic("GoldenEye").flash();
            this.amount += 2;
        }

        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            Iterator var1;
            AbstractCard c;
            if (this.duration == this.startingDuration) {
                var1 = AbstractDungeon.player.powers.iterator();

                while(var1.hasNext()) {
                    AbstractPower p = (AbstractPower)var1.next();
                    p.onScry();
                }

                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                if (this.amount != -1) {
                    for(int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); ++i) {
                        tmpGroup.addToTop(AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
                    }
                } else {
                    Iterator var5 = AbstractDungeon.player.drawPile.group.iterator();

                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        tmpGroup.addToBottom(c);
                    }
                }

                AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, TEXT[0]);
            } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    this.addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile, true));
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            var1 = AbstractDungeon.player.discardPile.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                c.triggerOnScry();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ReprogramAction");
        TEXT = uiStrings.TEXT;
    }
}
