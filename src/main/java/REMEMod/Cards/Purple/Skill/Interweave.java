package REMEMod.Cards.Purple.Skill;

import REMEMod.Cards.Colorless.Skill.Dark;
import REMEMod.Cards.Colorless.Skill.Light;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Interweave extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_Interweave");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private float timer = 2.0F;

    public Interweave() {
        super("REME_Interweave", NAME, "remeImg/cards/Interweave.png", 1, DESCRIPTION,
                CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
        this.cardsToPreview = new Dark();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(new Light(), this.magicNumber, true, true));
        this.addToBot(new MakeTempCardInDrawPileAction(new Dark(), this.magicNumber, true, true));
    }

    @Override
    public void update() {
        super.update();
        this.timer -= Gdx.graphics.getDeltaTime();
        if (this.timer < 0.0F) {
            this.cardsToPreview = this.cardsToPreview.cardID.equals("REME_Dark") ? new Light() : new Dark();
            this.timer = 2.0F;
        }
    }

    public AbstractCard makeCopy() {
        return new Interweave();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }

}
