package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawPileToHandSAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard card;

    public DrawPileToHandSAction(AbstractCard card) {
        this.p = AbstractDungeon.player;
        this.card = card;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() == 10) {
                this.p.drawPile.moveToDiscardPile(this.card);
                this.p.createHandIsFullDialog();
            } else {
                this.card.unhover();
                this.card.lighten(true);
                this.card.setAngle(0.0F);
                this.card.drawScale = 0.12F;
                this.card.targetDrawScale = 0.75F;
                this.card.current_x = CardGroup.DRAW_PILE_X;
                this.card.current_y = CardGroup.DRAW_PILE_Y;
                this.p.drawPile.removeCard(this.card);
                AbstractDungeon.player.hand.addToTop(this.card);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}
