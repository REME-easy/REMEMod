package REMEMod.Powers;

import REMEMod.Actions.CutPowerAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CutPower extends AbstractPower {
    private static final String POWER_ID = "REME_CutPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    private static int postfix = 0;
    public int amount3;
    private Color redColor2;
    private Color greenColor2;

    public CutPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID + "[" + postfix++ + "]";
        if(AbstractDungeon.player.powers.size() > 0)
            for (AbstractPower p : AbstractDungeon.player.powers){
                if(p instanceof CutPower && ((CutPower) p).amount3 == 0){
                    this.ID = p.ID;
                    break;
                }
            }
        this.owner = owner;
        this.amount = Amount;
        this.amount3 = 0;
        this.type = PowerType.BUFF;
        String path128 = "remeImg/powers/Cut84.png";
        String path48 = "remeImg/powers/Cut32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.redColor2 = Color.RED.cpy();
        this.greenColor2 = Color.GREEN.cpy();
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
/*        if (card.type == AbstractCard.CardType.ATTACK) {
            amount3++;
            this.updateDescription();
            if(amount3 == 2){
                boolean anim = true;
                for(AbstractGameEffect age:AbstractDungeon.effectList){
                    if(age instanceof InflameEffect){
                        anim = false;
                        break;
                    }
                }
                if (anim)
                    this.addToBot(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 0.1F));
                this.flashWithoutSound();
            }
            if(amount3 == 3){
                amount3 = 0;
                if(action.target != null && action.target.isDead && !action.target.isDeadOrEscaped())
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new GoldenSlashEffect(action.target.hb.cX, action.target.hb.cY, true), 0.1F));
                this.flash();

            }
        }*/
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.addToBot(new CutPowerAction(this, action));
        }

    }

    @Override
    public float atDamageGive(float damage, DamageType type, AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK && amount3 == 2){
            return super.atDamageGive(damage + amount, type, card);
        }
        else
            return super.atDamageGive(damage, type, card);
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        if (!this.isTurnBased) {
            this.greenColor2.a = c.a;

            if (amount3 == 2)
                c = this.redColor2;
            else
                c = this.greenColor2;
        }
        super.renderAmount(sb, x, y, c);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, this.amount3 + "/" + 3, x, y + 17.0F * Settings.scale, this.fontScale, c);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount3 + DESCRIPTIONS[2];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("REME_CutPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
