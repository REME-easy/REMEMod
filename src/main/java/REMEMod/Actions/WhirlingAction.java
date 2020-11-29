package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WhirlingAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(WhirlingAction.class);

    private AbstractPlayer p;

    public WhirlingAction() {
        this.p = AbstractDungeon.player;
        this.duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() && this.p.drawPile.isEmpty()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for(AbstractCard c:AbstractDungeon.player.drawPile.group){
                    if(c.cost == 1){
                        tmp.addToTop(c);
                        break;
                    }
                }
                for(AbstractCard c:AbstractDungeon.player.drawPile.group){
                    if(c.cost == 2){
                        tmp.addToTop(c);
                        break;
                    }
                }
                for(AbstractCard c:AbstractDungeon.player.drawPile.group){
                    if(c.cost == 3){
                        tmp.addToTop(c);
                        break;
                    }
                }
                logger.info("将会抽取" + tmp.group.size());

                for(AbstractCard card:tmp.group) {
                    if (!tmp.isEmpty()) {
                        if (this.p.hand.size() == 10) {
                            this.p.drawPile.moveToDiscardPile(card);
                            this.p.createHandIsFullDialog();
                        } else {
                            card.unhover();
                            card.lighten(true);
                            card.setAngle(0.0F);
                            card.drawScale = 0.12F;
                            card.targetDrawScale = 0.75F;
                            card.current_x = CardGroup.DRAW_PILE_X;
                            card.current_y = CardGroup.DRAW_PILE_Y;
                            this.p.drawPile.removeCard(card);
                            AbstractDungeon.player.hand.addToTop(card);
                            AbstractDungeon.player.hand.refreshHandLayout();
                            AbstractDungeon.player.hand.applyPowers();
                        }
                    }
                }
                tmp.clear();

            }
            this.tickDuration();

        }
    }
}
