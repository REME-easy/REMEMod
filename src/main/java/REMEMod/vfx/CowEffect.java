package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CowEffect extends AbstractGameEffect {
    private static final Texture cowImg = ImageMaster.loadImage("img/vfx/cow.png");
    private float x;
    private float y;
    private float vY;
    private float vX;
    private int frame;
    private float animTimer = 0.05F;

    public CowEffect() {
        this.x = 0.0F;
        this.y = MathUtils.random(0.0F, Settings.HEIGHT) * Settings.scale;
        this.frame = MathUtils.random(7);
        this.scale = MathUtils.random(1.0F, 2.5F);
        this.vY = MathUtils.random(-50.0F, 50.0F) * this.scale * Settings.scale;
        this.vX = MathUtils.random(800.0F, 1200.0F) * this.scale * Settings.scale;
        this.scale *= Settings.scale;

        this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
        this.duration = 2.0F;
        CardCrawlGame.sound.play("graze_finale");
    }

    public void update() {
        this.y -= this.vY * Gdx.graphics.getDeltaTime();
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.animTimer -= Gdx.graphics.getDeltaTime() / this.scale;
        if (this.animTimer < 0.0F) {
            this.animTimer += 0.05F;
            ++this.frame;
            if (this.frame > 11) {
                this.frame = 0;
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else if (this.duration < 1.0F) {
            this.color.a = this.duration;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        this.renderImg(sb);

    }

    public void dispose() {
    }

    private void renderImg(SpriteBatch sb) {
        sb.draw(cowImg, this.x, this.y, 62.5F, 79.5F, 125.0F, 159.0F, this.scale, this.scale, this.rotation, 0, 0, 250, 318, false, false);
    }
}