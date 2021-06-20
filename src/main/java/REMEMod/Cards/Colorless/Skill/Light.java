package REMEMod.Cards.Colorless.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class Light extends CustomCard {
    private static final String ID = "REME_Light";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/Light.png";
    private static final int COST = -2;
    public Light() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.COLORLESS,
                AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
        this.baseBlock = this.block = 6;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Light();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Effect();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeBlock(3);
        }
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        Effect();
        this.addToBot(new DrawCardAction(1));
        this.addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    private void Effect(){
        this.applyPowers();
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

}
