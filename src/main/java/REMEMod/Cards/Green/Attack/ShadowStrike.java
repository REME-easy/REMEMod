package REMEMod.Cards.Green.Attack;

import REMEMod.vfx.ShadowStrikeEffect;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShadowStrike extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_ShadowStrike");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public ShadowStrike() {
        super("REME_ShadowStrike", NAME, "img/cards/ShadowStrike.png", 1,
                DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.NONE);
        this.damage = this.baseDamage = 10;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.Effect();
    }

    public void triggerOnManualDiscard() {
        this.Effect();
    }

    private void Effect() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (!m.isDeadOrEscaped() && !m.isDead) {
            this.addToBot(new VFXAction(new ShadowStrikeEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2F));
            this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AttackEffect.FIRE));
        }

    }

    public AbstractCard makeCopy() {
        return new ShadowStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }

    }
}
