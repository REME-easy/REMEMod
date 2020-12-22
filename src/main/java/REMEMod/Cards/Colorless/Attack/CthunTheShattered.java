package REMEMod.Cards.Colorless.Attack;

import REMEMod.Actions.CthunDamageAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;


public class CthunTheShattered extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_CthunTheShattered");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static ArrayList<String> Pieces = new ArrayList<>();

    public CthunTheShattered() {
        super("REME_CthunTheShattered", NAME, "remeImg/cards/CthunTheShattered.png", 3,
                DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 70;
        this.exhaust = true;
        this.setDisplayRarity(CardRarity.RARE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new CthunDamageAction(damage, true));
    }

    public static String GetCthunExtendedDes(){
        return cardStrings.EXTENDED_DESCRIPTION[0] + Pieces.size() + cardStrings.EXTENDED_DESCRIPTION[1];
    }


    public AbstractCard makeCopy() {
        return new CthunTheShattered();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(12);
        }

    }
}
