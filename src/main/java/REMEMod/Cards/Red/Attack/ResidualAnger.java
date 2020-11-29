package REMEMod.Cards.Red.Attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ResidualAnger extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_ResidualAnger");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    public ResidualAnger() {
        super("REME_ResidualAnger", NAME, "img/cards/ResidualAnger.png", 0, DESCRIPTION,
                CardType.ATTACK, CardColor.RED, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.damage = this.baseDamage = 1;
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.FIRE));
        this.addToBot(new DrawCardAction(1));
    }

    public AbstractCard makeCopy() {
        return new ResidualAnger();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
        }

    }

}

