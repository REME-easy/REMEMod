package REMEMod.Cards.Colorless.Skill;

import REMEMod.Actions.ProfessionalPlagiarismAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class ProfessionalPlagiarism extends CustomCard {
    private static final String ID = "REME_ProfessionalPlagiarism";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/ProfessionalPlagiarism.png";
    private static final int COST = 1;
    private static final int MAGIC_NUM = 3;
    private static final int UPGRADE_MAGIC_NUM = 1;

    public ProfessionalPlagiarism() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.COLORLESS,
                CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ProfessionalPlagiarismAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ProfessionalPlagiarism();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
        }
    }
}
