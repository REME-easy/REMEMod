package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfessionalPlagiarismAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    private static final String[] TEXT;
    private float startingDuration;
    private int num;
    private Logger logger = LogManager.getLogger(ProfessionalPlagiarismAction.class);

    public ProfessionalPlagiarismAction(int num) {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.num = num;
    }

    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == this.startingDuration) {
                CardGroup tmpGroup = new CardGroup(CardGroupType.UNSPECIFIED);
                ArrayList<AbstractCard> derp = new ArrayList<>();

                while(true) {
                    if (derp.size() == this.num) {
                        this.logger.info("cards.size = " + derp.size());
                        for(AbstractCard c:derp){
                            tmpGroup.addToTop(c);
                        }

                        if (tmpGroup.size() > 0) {
                            this.logger.info("open grid");
                            AbstractDungeon.gridSelectScreen.open(tmpGroup, 1, true, TEXT[0] + 1 + TEXT[1]);
                        }
                        break;
                    }

                    boolean dupe = false;
                    int roll = AbstractDungeon.cardRandomRng.random(99);
                    CardRarity cardRarity;
                    if (roll < 55) {
                        cardRarity = CardRarity.COMMON;
                    } else if (roll < 85) {
                        cardRarity = CardRarity.UNCOMMON;
                    } else {
                        cardRarity = CardRarity.RARE;
                    }

                    AbstractCard tmp = CardLibrary.getAnyColorCard(cardRarity);

                    for(AbstractCard c:derp) {
                        if (c.cardID.equals(tmp.cardID)) {
                            dupe = true;
                            break;
                        }
                    }

                    if (!dupe) {
                        derp.add(tmp.makeCopy());
                    }
                }
            } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(AbstractCard card:AbstractDungeon.gridSelectScreen.selectedCards) {
                    AbstractCard c = card.makeCopy();
                    GameActionManager.queueExtraCard(c, AbstractDungeon.getRandomMonster());
                }

                AbstractDungeon.closeCurrentScreen();
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone = true;
            }

            this.tickDuration();
        }

    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("REME_CARD_SELECT");
        TEXT = uiStrings.TEXT;
    }
}
