package REMEMod.Cards.Green.Attack;

import REMEMod.vfx.GrazeFinaleEffect;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class GrazeFinale extends CustomCard {
    private static final String ID = "REME_GrazeFinale";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "remeImg/cards/GrazeFinale.png";
    private static final int COST = 0;
    private static final int MAGIC_NUM = 50;
    private static final int UPGRADE_MAGIC_NUM = 10;

    public GrazeFinale() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                ATTACK, CardColor.GREEN,
                CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new GrazeFinaleEffect(), 0.7F));
        } else {
            this.addToBot(new VFXAction(new GrazeFinaleEffect(), 1.0F));
        }
        this.addToBot(new HealAction(p, p, magicNumber));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (p.drawPile.size() > 0) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return canUse;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GrazeFinale();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
        }
    }
}
