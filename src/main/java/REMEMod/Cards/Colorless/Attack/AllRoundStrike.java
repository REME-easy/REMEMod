package REMEMod.Cards.Colorless.Attack;

import REMEMod.Actions.MakeTempCardInHandSAction;
import REMEMod.Helpers.REMECardHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;


public class AllRoundStrike extends CustomCard {
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_AllRoundStrike");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public AllRoundStrike() {
        super("REME_AllRoundStrike", NAME, "remeImg/cards/AllRoundStrike.png", 1,
                DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
        this.damage = this.baseDamage = 4;
        this.tags.add(CardTags.STRIKE);
        this.isEthereal = true;
        this.isInnate = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.Effect();
    }

    public void triggerOnManualDiscard() {
        this.Effect();
    }

    public void onRemoveFromMasterDeck() {
        AbstractCard c = new AllRoundStrike();
        if(upgraded)
            c.upgrade();
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
    }

    public void triggerOnExhaust() {
        this.Effect();
        this.addToBot(new MakeTempCardInHandSAction(REMECardHelper.makeStatEquivalentCopy(this)));
    }

    private void Effect() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (m != null && !m.isDeadOrEscaped() && !m.isDead) {
            this.calculateCardDamage(m);
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            this.addToBot(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY), 0.1F));
            this.addToBot(new WaitAction(0.8F));
            this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AttackEffect.FIRE));

        }

    }

    public AbstractCard makeCopy() {
        return new AllRoundStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }

    }
}
