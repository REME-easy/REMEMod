package REMEMod.Cards.Colorless.Skill;

import REMEMod.Actions.DrawPileToDiscardAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class ShortnessOfBreath extends CustomCard {
    private static final String ID = "REME_ShortnessOfBreath";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/cards/ShortnessOfBreath.png";
    private static final int COST = 0;
    private static final int MAGIC_NUM = 1;
    private static final int UPGRADE_MAGIC_NUM = 1;

    public ShortnessOfBreath() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.COLORLESS,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new DrawPileToDiscardAction());

    }

    @Override
    public AbstractCard makeCopy() {
        return new ShortnessOfBreath();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
