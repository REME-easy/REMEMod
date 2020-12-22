package REMEMod.Cards.Colorless.Attack;

import REMEMod.Actions.CthunPiecesAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;


public class HeartOfCthun extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_HeartOfCthun");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    public HeartOfCthun() {
        super("REME_HeartOfCthun", NAME, "remeImg/cards/HeartOfCthun.png", 2,
                DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 25;
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster m1: AbstractDungeon.getMonsters().monsters) {
            if (m1 != null && !m1.isDeadOrEscaped() && !m1.isDead) {
                this.addToBot(new VFXAction(new BiteEffect(m1.hb.cX, m1.hb.cY - 40.0F * Settings.scale, Settings.RED_RELIC_COLOR.cpy()), 0.1F));
            }
        }
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new CthunPiecesAction(this));

    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + CthunTheShattered.GetCthunExtendedDes();
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new HeartOfCthun();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(7);
        }

    }
}
