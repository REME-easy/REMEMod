package REMEMod.Cards.Blue.Attack;

import REMEMod.Actions.DrawPileToHandSAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.Iterator;

public class ForAll extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_ForAll");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    public ForAll() {
        super("REME_ForAll", NAME, "remeImg/cards/ForAll.png", 2, DESCRIPTION,
                CardType.ATTACK, CardColor.BLUE, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
        if (p.drawPile.size() > 0) {
            Iterator var3 = p.drawPile.group.iterator();

            while(true) {
                AbstractCard c;
                do {
                    if (!var3.hasNext()) {
                        return;
                    }

                    c = (AbstractCard)var3.next();
                } while(c.cost != 0 && !c.freeToPlayOnce);

                this.addToBot(new DrawPileToHandSAction(c));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new ForAll();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }
}
