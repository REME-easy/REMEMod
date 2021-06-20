package REMEMod.Cards.Colorless.Power;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class AccumulateSteadily extends CustomCard {
    private static final String ID = "REME_AccumulateSteadily";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/AccumulateSteadily.png";
    private static final int COST = 1;
    public AccumulateSteadily() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.COLORLESS,
                CardRarity.RARE, CardTarget.SELF);
        this.misc = 1;
        this.baseMagicNumber = this.magicNumber = this.misc;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, 1));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber = this.magicNumber = this.misc;
    }

    @Override
    public AbstractCard makeCopy() {
        return new AccumulateSteadily();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }


}