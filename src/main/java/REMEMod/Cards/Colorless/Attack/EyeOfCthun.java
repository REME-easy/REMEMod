package REMEMod.Cards.Colorless.Attack;

import REMEMod.Actions.CthunDamageAction;
import REMEMod.Actions.CthunPiecesAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class EyeOfCthun extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_EyeOfCthun");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public EyeOfCthun() {
        super("REME_EyeOfCthun", NAME, "remeImg/cards/EyeOfCthun.png", 2,
                DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 30;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new CthunDamageAction(damage));
        this.addToBot(new CthunPiecesAction(this));
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + CthunTheShattered.GetCthunExtendedDes();
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new EyeOfCthun();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(12);
        }

    }
}
