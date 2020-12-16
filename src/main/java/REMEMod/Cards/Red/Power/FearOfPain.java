package REMEMod.Cards.Red.Power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class FearOfPain extends CustomCard {
    private static final String ID = "REME_FearOfPain";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/FearOfPain.png";
    private static final int COST = 1;
    private static final int MAGIC_NUM = 3;
    private static final int UPGRADE_MAGIC_NUM = 1;

    public FearOfPain() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.RED,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUM;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new REMEMod.Powers.FearOfPainPower(p,this.magicNumber),this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FearOfPain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
        }
    }
}
