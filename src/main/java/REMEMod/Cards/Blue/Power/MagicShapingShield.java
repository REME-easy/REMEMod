package REMEMod.Cards.Blue.Power;


import REMEMod.Helpers.SecondaryMagicVariable;
import REMEMod.Powers.MagicShapingShieldPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class MagicShapingShield extends CustomCard {
    private static final String ID = "REME_MagicShapingShield";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/MagicShapingShield.png";
    private static final int COST = 2;
    public MagicShapingShield() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.BLUE,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.misc = 20;
        SecondaryMagicVariable.SMFields.baseSMagicNum.set(this, misc);
        SecondaryMagicVariable.SMFields.sMagicNum.set(this, misc);
        this.baseMagicNumber = this.magicNumber = 8;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MagicShapingShieldPower(p, this.magicNumber, SecondaryMagicVariable.SMFields.sMagicNum.get(this))));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicShapingShield();
    }

    public void applyPowers() {
        super.applyPowers();
        SecondaryMagicVariable.SMFields.baseSMagicNum.set(this, misc);
        SecondaryMagicVariable.SMFields.sMagicNum.set(this, misc);
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.misc -= 2;
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }


}