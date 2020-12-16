package REMEMod.Cards.Purple.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class EmptyShield extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_EmptyShield");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public EmptyShield() {
        super("REME_EmptyShield", NAME, "remeImg/cards/EmptyShield.png", 1, DESCRIPTION,
                CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyPowers();
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    public void applyPowers() {
        int count = 0;
        int maxhand = 10;
        if(AbstractDungeon.player.hasRelic("ResearcherNote")){
            this.baseBlock = 999;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
            return;
        }
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c != this) {
                ++count;
            }
        }
        count = maxhand - count;

        this.baseBlock = count * this.magicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new EmptyShield();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }

    }

}
