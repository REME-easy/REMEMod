package REMEMod.Helpers;

import REMEMod.Cards.Colorless.Skill.SutureCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class REMEHelper {
    public REMEHelper() {
    }

    private static final Logger logger = LogManager.getLogger(REMEHelper.class);

    public static void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public static void GainRelic(AbstractRelic r) {
        AbstractDungeon.player.relics.add(r);
        r.onEquip();
        AbstractDungeon.player.reorganizeRelics();
    }

    public static void info(String s) {
        logger.info(s);
    }

    public static AbstractCard makeStatEquivalentCopy(AbstractCard c) {
        AbstractCard card = c.makeStatEquivalentCopy();
        card.retain = c.retain;
        card.selfRetain = c.selfRetain;
        card.purgeOnUse = c.purgeOnUse;
        card.isEthereal = c.isEthereal;
        card.exhaust = c.exhaust;
        card.glowColor = c.glowColor;
        card.rawDescription = c.rawDescription;
        card.cardsToPreview = c.cardsToPreview;
        card.initializeDescription();
        return card;
    }

    public static AbstractMonster getRandomMonsterSafe(){
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if(m != null && !m.isDeadOrEscaped() && !m.isDead){
            return m;
        }else
            return null;
    }

    public static AbstractCard makeSutureCard(ArrayList<AbstractCard> tmp){
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for(AbstractCard c:tmp){
            if(c instanceof SutureCard){
                for(AbstractCard c1:((SutureCard) c).sutureCards){
                    cards.add(c1.makeStatEquivalentCopy());
                }
                ((SutureCard) c).sutureCards.clear();
            }else{
                cards.add(c.makeStatEquivalentCopy());
            }
        }
        return new SutureCard(cards);
    }
}
