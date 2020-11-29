package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CompressDrawAction extends AbstractGameAction {

    private AbstractPlayer p;

    public CompressDrawAction() {
        this.p = AbstractDungeon.player;
        this.duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() && this.p.drawPile.isEmpty()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                for(AbstractCard c: DrawCardAction.drawnCards){
                    if(c.cost == 0){
                        this.addToBot(new DiscardSpecificCardAction(c));
                        this.addToBot(new GainEnergyAction(1));
                    }
                }
            }
            this.tickDuration();

        }
    }
}
