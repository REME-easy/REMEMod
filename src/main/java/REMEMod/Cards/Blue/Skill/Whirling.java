package REMEMod.Cards.Blue.Skill;

import REMEMod.Actions.WhirlingAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class Whirling extends CustomCard {
    private static final String ID = "REME_Whirling";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/cards/Whirling.png";
    private static final int COST = 0;

    public Whirling() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new WhirlingAction());

    }

    @Override
    public AbstractCard makeCopy() {
        return new Whirling();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
