package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CthunPiecesEffect extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private Vector2[] points = new Vector2[SIZE];
    private Vector2 source;
    private float scale;
    private float targetScale;

    private float passTime = 0.0F;
    private float startX;
    private float rotation;

    private float length;

    private static final float DURATION = 3.0F;
    private static final int SIZE = 90;

    public CthunPiecesEffect(float X, float Y,float rotation, float targetScale) {
        this.img = ImageMaster.GLOW_SPARK_2;
        this.source = new Vector2(X, Y);
        this.targetScale = targetScale;
        this.scale = 0.1F;
        this.rotation = rotation;
        this.length = 50.0F;
        this.startX = MathUtils.random(0.0F, 30.0F);
        this.color = new Color(1.0F, 0.15F, 0.8F, 1.0F);
        this.duration = DURATION;
    }

    public void update() {
        if (DURATION - this.duration < 0.5F) {
            this.color.a = Interpolation.exp10.apply(0.0F, 1.0F, (DURATION - this.duration) * 5.0F);
            this.length = Interpolation.exp10.apply(0.0F, 175.0F, (DURATION - this.duration) * 5.0F);
        } else if(this.duration < 0.5F){
            this.color.a = Interpolation.exp10.apply(0.0F, 1.0F, this.duration / 2.0F);
            this.length = Interpolation.exp10.apply(0.0F, 175.0F, this.duration / 2.0F);
        }

        this.passTime += Gdx.graphics.getDeltaTime();
        if(this.scale <= this.targetScale){
            this.scale += Gdx.graphics.getDeltaTime();
        }

        float delta = length / SIZE;
        this.rotation += Gdx.graphics.getDeltaTime() * 3.0F;
        for(int i = 0 ; i < SIZE ; i++){
            Vector2 tmp = new Vector2();
            tmp.x = i * delta;
            tmp.y = 55.0F * ((float)(SIZE - i) / SIZE) * (float)Math.cos(0.1F * tmp.x - 9.0F * passTime + startX);
            tmp.rotate(rotation);
            tmp.add(source);
            points[i] = tmp;
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.color);
            float scale = Settings.scale * this.scale;
            for(int j = 0 ; j < 6 ; j++)
                for(int i = 0; i < SIZE; i++) {
                    if (this.points[i] != null) {
                        sb.draw(this.img, this.points[i].x - (float)(this.img.packedWidth / 2), this.points[i].y - (float)(this.img.packedHeight / 2), (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, scale, scale, this.rotation);
                        scale *= 0.975F;
                    }
                }
            sb.setBlendFunction(770, 771);
        }
    }

    public void dispose() {
    }
}