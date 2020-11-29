package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class MagicPocketWatchDrawAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    private static final String[] TEXT;

    private AbstractPlayer p;

    public MagicPocketWatchDrawAction() {
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
                    if(c.retain = false){
                        c.retain = true;
                        c.rawDescription = TEXT[3].concat(c.rawDescription);
                        c.initializeDescription();
                    }
                    c.superFlash();

                }
            }
            this.tickDuration();

        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("REME_CARD_SELECT");
        TEXT = uiStrings.TEXT;
    }
}
