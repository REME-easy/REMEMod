package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BorderFlashAlwaysEffect extends AbstractGameEffect {
    private float startAlpha;
    private boolean additive;

    public BorderFlashAlwaysEffect(Color color) {
        this(color, true,2.0F);
    }

    public BorderFlashAlwaysEffect(Color color, boolean additive,float duration) {
        this.additive = true;
        this.duration = duration;
        this.startAlpha = color.a;
        this.color = color.cpy();
        this.color.a = 0.0F;
        this.additive = additive;
    }

    public void update() {
        if (2.0F - this.duration < 0.2F) {
            this.color.a = Interpolation.fade.apply(0.0F, this.startAlpha, (2.0F - this.duration) * 10.0F);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, this.startAlpha, this.duration / 2.0F);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        if (this.additive) {
            sb.setBlendFunction(770, 1);
        }

        sb.setColor(this.color);
        sb.draw(ImageMaster.BORDER_GLOW_2, 0.0F, 0.0F, (float) Settings.WIDTH, (float)Settings.HEIGHT);
        if (this.additive) {
            sb.setBlendFunction(770, 771);
        }

    }

    public void dispose() {
    }
}

