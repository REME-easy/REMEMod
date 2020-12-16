package REMEMod.Cards.Colorless.Skill;

import REMEMod.Actions.MagicPocketWatchDrawAction;
import REMEMod.Helpers.SecondaryMagicVariable;
import REMEMod.Powers.ExtraTimePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class MagicPocketWatch extends CustomCard {
    private static final String ID = "REME_MagicPocketWatch";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/MagicPocketWatch.png";
    private static final int COST = 2;
    private static final int MAGIC_NUM = 2;
    private static final int SECOND_MAGIC_NUM = 30;

    public MagicPocketWatch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.COLORLESS,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
        SecondaryMagicVariable.SMFields.baseSMagicNum.set(this, SECOND_MAGIC_NUM);
        SecondaryMagicVariable.SMFields.sMagicNum.set(this, SECOND_MAGIC_NUM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber));
        this.addToBot(new MagicPocketWatchDrawAction());
        if(AbstractDungeon.cardRandomRng.random(0,100) < SecondaryMagicVariable.SMFields.sMagicNum.get(this)){
            this.addToBot(new ApplyPowerAction(p, p, new ExtraTimePower(p)));
            SecondaryMagicVariable.SMFields.sMagicNum.set(this, SecondaryMagicVariable.SMFields.sMagicNum.get(this) - 5);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard c = new MagicPocketWatch();
        SecondaryMagicVariable.SMFields.sMagicNum.set(c, SecondaryMagicVariable.SMFields.sMagicNum.get(this));
        return c;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
