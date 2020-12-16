package REMEMod.Cards.Purple.Power;

import REMEMod.Powers.MusicOfDeathPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class MusicOfDeath extends CustomCard {
    private static final String ID = "REME_MusicOfDeath";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/MusicOfDeath.png";
    private static final int COST = 1;
    private static final int MAGIC_NUM = 1;
    private static final int UPGRADE_MAGIC_NUM = 1;

    public MusicOfDeath() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                POWER, CardColor.PURPLE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUM;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new MusicOfDeathPower(p,this.magicNumber),this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MusicOfDeath();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
        }
    }
}
