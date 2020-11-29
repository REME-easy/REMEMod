package REMEMod.Cards.Blue.Attack;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;

public class DataAnalysis extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_DataAnalysis");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public DataAnalysis() {
        super("REME_DataAnalysis", NAME, "img/cards/DataAnalysis.png", 0, DESCRIPTION,
                CardType.ATTACK, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = EnergyPanel.totalCount + this.magicNumber;
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(new Color(0.2F, 0.2F, 1.0F, 1.0F), true));
        CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(p.hb.cX, p.hb.cY));
        this.addToBot(new GainBlockAction(p, p, amt));
        if (m != null){
            AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(m.hb.cX, m.hb.cY));
            this.addToBot(new DamageAction(m, new DamageInfo(p, amt), AttackEffect.FIRE));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, m.hb.cX, m.hb.cY), 0.1F));
        }
    }

    public void applyPowers() {
        int amt = EnergyPanel.totalCount + this.magicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + amt + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int amt = EnergyPanel.totalCount + this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + amt + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new DataAnalysis();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }

    static {



    }
}
