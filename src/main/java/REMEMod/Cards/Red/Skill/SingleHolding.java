package REMEMod.Cards.Red.Skill;

import REMEMod.Actions.SingleHoldingAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SingleHolding extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_SingleHolding");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public SingleHolding() {
        super("REME_SingleHolding", NAME, "img/cards/SingleHolding.png", 1, DESCRIPTION,
                CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SingleHoldingAction(1, m));
    }

    public AbstractCard makeCopy() {
        return new SingleHolding();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }

    }

}
