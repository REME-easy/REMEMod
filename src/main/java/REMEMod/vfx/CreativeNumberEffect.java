package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CreativeNumberEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private String num;

    public CreativeNumberEffect(float x, float y) {
        this.num = MathUtils.randomBoolean() ? "0" : "1";
        this.x = x + MathUtils.random(-50.0F, 50.0F) * Settings.xScale;
        this.y = y + MathUtils.random(-50.0F, 50.0F) * Settings.yScale;
        this.duration = MathUtils.random(1.2F, 1.5F);
        this.color = new Color(MathUtils.random(0.0F, 0.2F), MathUtils.random(0.0F, 0.2F), MathUtils.random(0.0F, 0.2F), 0.0F);
        this.scale = MathUtils.random(0.5F, 0.7F);
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else {
            if (this.duration < 1.0F) {
                this.color.a = Interpolation.bounceOut.apply(0.0F, 1.0F, this.duration);
            } else {
                this.color.a = MathHelper.mouseLerpSnap(this.color.a, 1.0F);
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        FontHelper.energyNumFontBlue.getData().setScale(this.scale);
        FontHelper.renderFont(sb, FontHelper.energyNumFontBlue, this.num, this.x, this.y, this.color);
        FontHelper.energyNumFontBlue.getData().setScale(1.0F);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
