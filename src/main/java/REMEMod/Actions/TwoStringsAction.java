package REMEMod.Actions;

import REMEMod.Helpers.REMEHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TwoStringsAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(TwoStringsAction.class);

    public TwoStringsAction() {
        this.duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                for(AbstractCard c:AbstractDungeon.player.hand.group){
                    this.addToBot(new MakeTempCardInHandAction(REMEHelper.makeStatEquivalentCopy(c)));

                }
            }
            this.tickDuration();

        }
    }
}
