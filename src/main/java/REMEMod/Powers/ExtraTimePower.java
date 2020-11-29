package REMEMod.Powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class ExtraTimePower extends AbstractPower {
    private static final String POWER_ID = "REME_ExtraTimePower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public ExtraTimePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        String path128 = "img/powers/ExtraTime84.png";
        String path48 = "img/powers/ExtraTime32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());
            this.addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
            this.addToBot(new SkipEnemiesTurnAction());
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
