package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.ArrayList;
import java.util.Iterator;

public class SingleHoldingAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    private static final String[] TEXT;
    private int num;
    private AbstractPlayer p;
    private AbstractMonster m;
    private ArrayList<AbstractCard> cannotExhaust = new ArrayList<>();

    public SingleHoldingAction(int num, AbstractMonster m) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.num = num;
        this.p = AbstractDungeon.player;
        this.m = m;
    }

    public void update() {
        Iterator var1;
        AbstractCard c;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            var1 = this.p.hand.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (!this.isDualWieldable(c)) {
                    this.cannotExhaust.add(c);
                }
            }

            if (this.cannotExhaust.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            int i;
            if (this.p.hand.group.size() - this.cannotExhaust.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.isDualWieldable(c)) {
                        for(i = 0; i < this.num; ++i) {
                            this.p.hand.moveToExhaustPile(c);
                            this.addToBot(new DamageAction(this.m, new DamageInfo(this.p, c.damage + 3, DamageType.NORMAL), AttackEffect.FIRE));
                        }

                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotExhaust);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[1], 1, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                for(i = 0; i < this.num; ++i) {
                    AbstractCard card = this.p.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(card);
                    this.addToBot(new DamageAction(this.m, new DamageInfo(this.p, card.damage + 3, DamageType.NORMAL), AttackEffect.FIRE));
                }

                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                this.p.hand.moveToExhaustPile(c);
                this.addToBot(new DamageAction(this.m, new DamageInfo(this.p, c.damage + 3, DamageType.NORMAL), AttackEffect.FIRE));
            }

            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        for(AbstractCard c:this.cannotExhaust) {
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    private boolean isDualWieldable(AbstractCard card) {
        return card.type.equals(CardType.ATTACK);
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("REME_CARD_SELECT");
        TEXT = uiStrings.TEXT;
    }
}
