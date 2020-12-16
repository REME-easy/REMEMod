package REMEMod.Cards.Colorless.Power;


import REMEMod.Actions.FirmDeterminationAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class FirmDetermination extends CustomCard {
    private static final String ID = "REME_FirmDetermination";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/FirmDetermination.png";
    private static final int COST = 0;
    public FirmDetermination() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.COLORLESS,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseBlock = this.block = 14;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FirmDeterminationAction(magicNumber, block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FirmDetermination();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeBlock(5);
        }
    }


}