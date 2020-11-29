package REMEMod.Cards.Purple.Skill;

import REMEMod.Cards.Red.Skill.ACTH;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AdrenalineEffect;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class CRH extends CustomCard {
    private static final String ID = "REME_CRH";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/cards/CRH.png";
    private static final int COST = 2;

    public CRH() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.PURPLE,
                CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new ACTH();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new AdrenalineEffect(), 0.15F));
        this.addToBot(new MakeTempCardInHandAction(new ACTH(), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CRH();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
