package REMEMod.Actions;

import REMEMod.REMEMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayACardAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(PlayACardAction.class);
    private AbstractCard card;

    public PlayACardAction(AbstractCard card) {
        this.duration = DEFAULT_DURATION;
        this.card = card;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                GameActionManager.queueExtraCard(card, AbstractDungeon.getRandomMonster());
                logger.info("打出了" + card.name);
            }
            this.tickDuration();
        }
    }
}
