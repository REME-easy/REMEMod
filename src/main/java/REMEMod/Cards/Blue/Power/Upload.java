package REMEMod.Cards.Blue.Power;


import REMEMod.Actions.FirmDeterminationAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class Upload extends CustomCard {
    private static final String ID = "REME_Upload";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/FirmDetermination.png";
    private static final int COST = 1;
    public Upload() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.BLUE,
                CardRarity.UNCOMMON, CardTarget.SELF);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FirmDeterminationAction(magicNumber, block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Upload();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


}