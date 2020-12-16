package REMEMod.Cards.Colorless.Power;


import REMEMod.Powers.FloggingPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class Flogging extends CustomCard {
    private static final String ID = "REME_Flogging";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/Flogging.png";
    private static final int COST = 0;
    public Flogging() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.COLORLESS,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new FloggingPower(p, this.magicNumber), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flogging();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }


}