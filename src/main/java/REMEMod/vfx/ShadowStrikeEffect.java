//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package REMEMod.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ShadowStrikeEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int timesUpgraded;

    public ShadowStrikeEffect(float x, float y, int timesUpgraded) {
        this.x = x;
        this.y = y;
        this.timesUpgraded = timesUpgraded;
        CardCrawlGame.screenShake.shake(ShakeIntensity.HIGH, ShakeDur.SHORT, true);
    }

    public void update() {
        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.3F);
        float dst = 220.0F + (float)this.timesUpgraded * 3.0F;
        AbstractDungeon.effectsQueue.add(new ShadowBallEffect(this.x - dst * Settings.scale, this.y, this.x + dst * Settings.scale, this.y - 50.0F * Settings.scale, this.timesUpgraded));
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
