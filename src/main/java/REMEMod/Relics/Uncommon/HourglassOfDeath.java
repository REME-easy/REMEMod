package REMEMod.Relics.Uncommon;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class HourglassOfDeath extends CustomRelic {
    private boolean timing = false;
    private float time = 0.0F;

    public HourglassOfDeath() {
        super("REME_HourglassOfDeath", ImageMaster.loadImage("remeImg/relics/HourglassOfDeath.png"),
                RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.time = 0.0F;
        this.timing = false;
    }

    @Override
    public void update() {
        super.update();
        if(timing && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NONE){
            time += Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if(time > 1.0F){
            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(Math.round(time), true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        this.timing = false;
        this.time = 0.0F;
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
        this.timing = true;
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont,  String.format("%02d:%02d", Math.round(time) / 60, Math.round(time) % 60) , this.currentX + 15.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);

    }

    public AbstractRelic makeCopy() {
        return new HourglassOfDeath();
    }
}
