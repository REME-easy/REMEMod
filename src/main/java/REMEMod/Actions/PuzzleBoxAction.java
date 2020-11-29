package REMEMod.Actions;

import REMEMod.Cards.Colorless.Skill.PuzzleBox;
import REMEMod.vfx.BorderFlashAlwaysEffect;
import REMEMod.vfx.PuzzleBoxOrbEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PuzzleBoxAction extends AbstractGameAction {
    private float startingDuration;
    private int num;
    private ArrayList<AbstractCard> cards = new ArrayList<>();
    private Logger logger = LogManager.getLogger(PuzzleBoxAction.class);

    public PuzzleBoxAction(int num) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = 0.1F;
        this.duration = this.startingDuration;
        this.num = num;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startingDuration) {
                int i;
                AbstractDungeon.effectsQueue.add(new BorderFlashAlwaysEffect(new Color(1.0F, 0.2F, 1.0F, 1.0F),true, 2.0F));
                for(i = 0 ; i < 36 ; i++){
                    AbstractDungeon.effectsQueue.add(new PuzzleBoxOrbEffect(Settings.WIDTH / 2, Settings.HEIGHT / 2, i));
                }
                while (cards.size() < num) {
                    boolean dupe = false;
                    int roll = AbstractDungeon.cardRandomRng.random(99);
                    CardRarity cardRarity;
                    if (roll < 30) {
                        cardRarity = CardRarity.COMMON;
                    } else if (roll < 60) {
                        cardRarity = CardRarity.UNCOMMON;
                    } else if (roll < 90){
                        cardRarity = CardRarity.RARE;
                    } else {
                        cardRarity = CardRarity.CURSE;
                    }
                    AbstractCard tmp;
                    if(cardRarity == CardRarity.CURSE){
                        tmp = CardLibrary.getCurse();
                    }else{
                        tmp = CardLibrary.getAnyColorCard(cardRarity);
                    }
                    for(AbstractCard c:cards) {
                        if (c.cardID.equals(tmp.cardID) || tmp.cardID.equals("REME_PuzzleBox")) {
                            dupe = true;
                            break;
                        }
                    }
                    logger.info("REMEMod:PuzzleBox:" + tmp.name + " size:" + cards.size());
                    if (!dupe) {
                        cards.add(tmp.makeCopy());
                    }
                }
                this.addToBot(new PlayCardsAction(cards));
            }
            this.tickDuration();
        }

    }

}
