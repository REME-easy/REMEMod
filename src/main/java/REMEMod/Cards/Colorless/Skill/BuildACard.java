package REMEMod.Cards.Colorless.Skill;

import REMEMod.Actions.BuildACardAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BuildACard extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_BuildACard");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public BuildACard() {
        super("REME_BuildACard", NAME, "remeImg/cards/BuildACard.png", 1,
                DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BuildACardAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new BuildACard();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }
}
