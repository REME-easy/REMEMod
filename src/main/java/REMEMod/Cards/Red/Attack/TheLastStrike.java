package REMEMod.Cards.Red.Attack;

import REMEMod.Helpers.REMECardHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheLastStrike extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_TheLastStrike");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    public TheLastStrike() {
        super("REME_TheLastStrike", NAME, "remeImg/cards/TheLastStrike.png", 1, DESCRIPTION,
                CardType.ATTACK, CardColor.RED, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 12;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
    }

    public void triggerOnExhaust() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (m != null && !m.isDeadOrEscaped() && !m.isDead) {
            this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
        }

        this.addToBot(new MakeTempCardInHandAction(REMECardHelper.makeStatEquivalentCopy(this)));
    }

    public AbstractCard makeCopy() {
        return new TheLastStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }

    }

}

