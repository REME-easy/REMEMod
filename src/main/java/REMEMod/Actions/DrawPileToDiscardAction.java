package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class DrawPileToDiscardAction extends AbstractGameAction {

    public DrawPileToDiscardAction() {
        this.duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                if (AbstractDungeon.player.drawPile.size() > 0){
                    AbstractDungeon.player.drawPile.shuffle();
                    ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.player.drawPile.group);
                    Iterator var1 = cards.iterator();
                    AbstractCard c;
                    while(var1.hasNext()) {
                        c = (AbstractCard)var1.next();
                        AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                    }
                }


            }
            this.tickDuration();
        }
    }
}
