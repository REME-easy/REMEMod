package REMEMod.Cards.Purple.Skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmptyShield extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_EmptyShield");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    public EmptyShield() {
        super("REME_EmptyShield", NAME, "remeImg/cards/EmptyShield.png", 0, DESCRIPTION,
                CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyPowers();
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    public void applyPowers() {
        int count = 0;
        int maxhand = 10;
        if(AbstractDungeon.player.hasRelic("ResearcherNote")){
            this.baseBlock = 99;
            super.applyPowers();
            this.rawDescription = upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
            return;
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c != this) {
                ++count;
            }
        }
        count = maxhand - count;

        this.baseBlock = count * this.magicNumber;
        super.applyPowers();
        this.rawDescription = upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new EmptyShield();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

}
