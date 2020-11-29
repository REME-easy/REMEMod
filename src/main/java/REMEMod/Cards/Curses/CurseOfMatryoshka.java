package REMEMod.Cards.Curses;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.CURSE;

public class CurseOfMatryoshka extends CustomCard {
    private static final String ID = "REME_CurseOfMatryoshka";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/cards/CurseOfMatryoshka.png";
    private static final int COST = -2;

    public CurseOfMatryoshka() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CURSE, CardColor.CURSE,
                CardRarity.SPECIAL, CardTarget.NONE);
        this.isEthereal = true;
    }

    public CurseOfMatryoshka(int num){
        this();
        int i;
        for(i = 0 ; i < num ; i++){
            this.upgrade();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand, true));
    }

    @Override
    public void triggerOnExhaust() {
        if(timesUpgraded > 1){
            this.addToBot(new MakeTempCardInDiscardAction(new CurseOfMatryoshka(timesUpgraded - 1), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CurseOfMatryoshka(timesUpgraded);
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }

}
