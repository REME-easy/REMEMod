package REMEMod.Cards.Green.Power;

import REMEMod.Powers.ShadowFormPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShadowForm extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_ShadowForm");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public ShadowForm() {
        super("REME_ShadowForm", NAME, "img/cards/ShadowForm.png", 4, DESCRIPTION,
                CardType.POWER, CardColor.GREEN, CardRarity.RARE, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ShadowFormPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ShadowForm();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }

    static {



    }
}
