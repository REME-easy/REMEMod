package REMEMod.Cards.Blue.Attack;


import REMEMod.Actions.ShoggothAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class Shoggoth extends CustomCard {
    private static final String ID = "REME_Shoggoth";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/Shoggoth.png";
    private static final int COST = 0;
    public boolean sutured = false;
    public AbstractCard sutureOwner = null;

    public Shoggoth() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            ATTACK, CardColor.BLUE,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.damage = this.baseDamage = 3;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ShoggothAction(sutured ? sutureOwner : this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shoggoth();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
}
