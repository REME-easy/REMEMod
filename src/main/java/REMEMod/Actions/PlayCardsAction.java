package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayCardsAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(PlayCardsAction.class);
    private ArrayList<AbstractCard> cards;
    public PlayCardsAction(ArrayList<AbstractCard> cards) {
        this.cards = cards;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            Iterator var1 = cards.iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                Iterator var3 = AbstractDungeon.actionManager.cardQueue.iterator();

                while(var3.hasNext()) {
                    CardQueueItem q = (CardQueueItem)var3.next();
                    if (q.card == c) {
                    }
                }
                c.purgeOnUse = true;
                c.freeToPlayOnce = true;
                logger.info("打出了" + c.name);
                switch(c.target) {
                    case SELF_AND_ENEMY:
                    case ENEMY:
                        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, AbstractDungeon.getRandomMonster(), c.energyOnUse, true, true), true);

                        break;
                    case SELF:
                    case ALL:
                    case ALL_ENEMY:
                    case NONE:
                    default:
                        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, null, c.energyOnUse, true, true), true);

                }
            }
        }

        this.tickDuration();
    }
}

