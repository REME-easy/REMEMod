package REMEMod.Cards.Colorless.Skill;

import REMEMod.Actions.WheelOfFateAction;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class WheelOfFate extends CustomCard {
    private static final String ID = "REME_WheelOfFate";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/WheelOfFate.png";
    private static final int COST = 3;

    public WheelOfFate() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.COLORLESS,
                CardRarity.RARE, CardTarget.NONE);
        this.baseDamage = this.damage = 6;
        this.baseMagicNumber = this.magicNumber = 6;
        this.exhaust = true;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> val = new ArrayList<>();
        val.add(new TooltipInfo(EXTENDED_DESCRIPTION[1],
                EXTENDED_DESCRIPTION[2] + WheelOfFateAction.goldAmt + EXTENDED_DESCRIPTION[3] + WheelOfFateAction.healAmt + EXTENDED_DESCRIPTION[4] + WheelOfFateAction.healAmt + EXTENDED_DESCRIPTION[5]));
        return val;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new WheelOfFateAction(damage, magicNumber));
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new WheelOfFate();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }
}
