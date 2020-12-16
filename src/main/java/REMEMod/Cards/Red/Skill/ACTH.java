package REMEMod.Cards.Red.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Adrenaline;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AdrenalineEffect;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class ACTH extends CustomCard {
    private static final String ID = "REME_ACTH";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/ACTH.png";
    private static final int COST = 1;

    public ACTH() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.RED,
                CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Adrenaline();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new AdrenalineEffect(), 0.15F));
        this.addToBot(new MakeTempCardInHandAction(new Adrenaline(), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ACTH();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
