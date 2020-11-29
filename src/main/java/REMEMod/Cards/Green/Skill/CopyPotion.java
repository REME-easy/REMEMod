package REMEMod.Cards.Green.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class CopyPotion extends CustomCard {
    private static final String ID = "REME_CopyPotion";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/cards/CopyPotion.png";
    private static final int COST = 1;

    private AbstractPotion potion;

    public CopyPotion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, CardColor.GREEN,
                CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(potion != null && potion.canUse()){
            if(potion.targetRequired){
                potion.use(m);
            }else{
                potion.use(null);
            }
        }

    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        getPotion();
    }

    private void getPotion(){
        for(int i = 0 ; i < AbstractDungeon.player.potions.size() ; i++){
            AbstractPotion p = AbstractDungeon.player.potions.get(i);
            if(!(p instanceof PotionSlot)){
                potion = p;
                break;
            }
        }
        if(potion != null){
            if(potion.targetRequired){
                target = CardTarget.ENEMY;
            }else{
                target = CardTarget.SELF;
            }
            this.rawDescription = DESCRIPTION  + " NL " + potion.description;
        }else{
            this.rawDescription = DESCRIPTION;

        }
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new CopyPotion();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(0);
            this.upgradeName();
        }
    }
}
