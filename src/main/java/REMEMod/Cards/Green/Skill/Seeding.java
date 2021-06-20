package REMEMod.Cards.Green.Skill;

import REMEMod.Cards.Colorless.Skill.Light;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class Seeding extends CustomCard {
    private static final String ID = "REME_Seeding";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/Seeding.png";
    private static final int COST = 1;

    public Seeding() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.GREEN,
                CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
        this.cardsToPreview = new Light();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(new Light(), this.magicNumber, true, true));
        this.addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Seeding();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
