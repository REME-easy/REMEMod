package REMEMod.Cards.Colorless.Skill;

import REMEMod.Actions.CthunPiecesAction;
import REMEMod.Cards.Colorless.Attack.CthunTheShattered;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class BodyOfCthun extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_BodyOfCthun");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public BodyOfCthun() {
        super("REME_BodyOfCthun", NAME, "remeImg/cards/BodyOfCthun.png", 2,
                DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = this.block = 30;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, block));
        this.addToBot(new CthunPiecesAction(this));

    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + CthunTheShattered.GetCthunExtendedDes();
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new BodyOfCthun();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(10);
        }

    }
}
