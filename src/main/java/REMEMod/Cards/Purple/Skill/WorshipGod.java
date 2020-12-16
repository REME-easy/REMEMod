package REMEMod.Cards.Purple.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class WorshipGod extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_WorshipGod");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public WorshipGod() {
        super("REME_WorshipGod", NAME, "remeImg/cards/WorshipGod.png", 1, DESCRIPTION,
                CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean can = false;
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.addAll(srcCommonCardPool.group);
        list.addAll(srcUncommonCardPool.group);
        list.addAll(srcRareCardPool.group);
        for(AbstractCard c:list){
            if(c.color == p.getCardColor()){
                can = true;
                break;
            }
        }
        if(can){
            Iterator var3 = p.drawPile.group.iterator();

            AbstractCard c;
            AbstractCard card;
            while(var3.hasNext()) {
                c = (AbstractCard)var3.next();
                if (c.color != p.getCardColor()) {
                    this.addToBot(new ExhaustSpecificCardAction(c, p.drawPile, true));
                    card = getYourColorCard().makeStatEquivalentCopy();
                    if(c.cost >= 0){
                        card.costForTurn = card.cost = Integer.max(0, card.cost - 1);
                        card.isCostModified = true;
                    }
                    this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
                }
            }

            var3 = p.hand.group.iterator();

            while(var3.hasNext()) {
                c = (AbstractCard)var3.next();
                if (c.color != p.getCardColor()) {
                    this.addToBot(new ExhaustSpecificCardAction(c, p.hand, true));
                    card = getYourColorCard().makeStatEquivalentCopy();
                    card.costForTurn = card.cost = Integer.max(0, card.cost - 1);
                    card.isCostModified = true;
                    this.addToBot(new MakeTempCardInHandAction(card));
                }
            }

            var3 = p.discardPile.group.iterator();

            while(var3.hasNext()) {
                c = (AbstractCard)var3.next();
                if (c.color != p.getCardColor()) {
                    this.addToBot(new ExhaustSpecificCardAction(c, p.discardPile, true));
                    card = getYourColorCard().makeStatEquivalentCopy();
                    card.costForTurn = card.cost = Integer.max(0, card.cost - 1);
                    card.isCostModified = true;
                    this.addToBot(new MakeTempCardInDiscardAction(card, 1));
                }
            }
        }else{
            this.addToBot(new TalkAction(p,EXTENDED_DESCRIPTION[0]));
        }


    }

    private AbstractCard getYourColorCard(){
        AbstractCard c;
        while(true){
            c = AbstractDungeon.returnTrulyRandomCardInCombat();
            if(c.color == AbstractDungeon.player.getCardColor())
                return c.makeCopy();
        }
    }

    public AbstractCard makeCopy() {
        return new WorshipGod();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

}
