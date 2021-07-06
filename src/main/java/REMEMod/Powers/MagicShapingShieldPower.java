package REMEMod.Powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MagicShapingShieldPower extends AbstractPower {
    private static final String POWER_ID = "REME_MagicShapingShieldPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    private static int postfix = 0;
    public int curAmt;
    public int maxAmt;
    private Color redColor2;
    private Color greenColor2;

    public MagicShapingShieldPower(AbstractCreature owner, int Amount, int max) {
        this.name = NAME;
        this.ID = POWER_ID + "[" + postfix++ + "]";
        if(AbstractDungeon.player.powers.size() > 0)
            for (AbstractPower p : AbstractDungeon.player.powers){
                if(p instanceof MagicShapingShieldPower && ((MagicShapingShieldPower) p).curAmt == 0){
                    this.ID = p.ID;
                    break;
                }
            }
        this.owner = owner;
        this.amount = Amount;
        this.curAmt = 0;
        this.maxAmt = max;
        this.type = PowerType.BUFF;
        String path128 = "remeImg/powers/MagicShapingShield84.png";
        String path48 = "remeImg/powers/MagicShapingShield32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.redColor2 = Color.RED.cpy();
        this.greenColor2 = Color.GREEN.cpy();
        this.updateDescription();
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                flashWithoutSound();
                curAmt += damageAmount;
                if (curAmt >= maxAmt) {
                    AbstractPlayer p = AbstractDungeon.player;
                    addToBot(new GainBlockAction(p, p, MagicShapingShieldPower.this.amount));
                    curAmt = 0;
                }
                isDone = true;
            }
        });
        return super.onAttackToChangeDamage(info, damageAmount);
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        if (!this.isTurnBased) {
            this.greenColor2.a = c.a;

            if (curAmt >= maxAmt)
                c = this.redColor2;
            else
                c = this.greenColor2;
        }
        super.renderAmount(sb, x, y, c);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, this.curAmt + "/" + this.maxAmt, x, y + 17.0F * Settings.scale, this.fontScale, c);
    }

    public void updateDescription() {
        this.description = String.format("%s%d%s%d%s%d%s", DESCRIPTIONS[0], this.maxAmt, DESCRIPTIONS[1], this.amount, DESCRIPTIONS[2], this.curAmt, DESCRIPTIONS[3]);
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
