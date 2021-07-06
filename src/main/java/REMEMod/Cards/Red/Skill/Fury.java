package REMEMod.Cards.Red.Skill;

import REMEMod.Actions.IncreaseMiscFuryAction;
import REMEMod.Cards.Red.Attack.ResidualAnger;
import REMEMod.Helpers.SecondaryMagicVariable;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class Fury extends CustomCard {
    private static final String ID = "REME_Fury";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/Fury.png";
    private static final int COST = 1;

    public Fury() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.RED,
                CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
        this.misc = 1;
        SecondaryMagicVariable.SMFields.baseSMagicNum.set(this, misc);
        SecondaryMagicVariable.SMFields.sMagicNum.set(this, misc);
        this.baseMagicNumber = this.magicNumber = 1;
        this.cardsToPreview = new ResidualAnger();
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMiscFuryAction(this.uuid, magicNumber));
        this.addToBot(new MakeTempCardInDrawPileAction(new ResidualAnger(), misc, true, true));
    }

    public void applyPowers() {
        super.applyPowers();
        SecondaryMagicVariable.SMFields.baseSMagicNum.set(this, misc);
        SecondaryMagicVariable.SMFields.sMagicNum.set(this, misc);
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fury();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}
