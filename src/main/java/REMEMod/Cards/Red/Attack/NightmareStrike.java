package REMEMod.Cards.Red.Attack;

import REMEMod.Cards.Colorless.Skill.Dark;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NightmareStrike extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_NightmareStrike");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    public NightmareStrike() {
        super("REME_NightmareStrike", NAME, "remeImg/cards/NightmareStrike.png", 1, DESCRIPTION,
                CardType.ATTACK, CardColor.RED, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 11;
        this.baseMagicNumber = this.magicNumber = 1;
        this.tags.add(CardTags.STRIKE);
        this.cardsToPreview = new Dark();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new MakeTempCardInDrawPileAction(new Dark(), this.magicNumber, true, true));
    }

    public AbstractCard makeCopy() {
        return new NightmareStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }

    }

}

