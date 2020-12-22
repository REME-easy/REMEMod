package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CthunAreaEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private TextureAtlas.AtlasRegion img;

    public CthunAreaEffect(float x, float y) {
        this.img = ImageMaster.EXHAUST_L;
        this.duration = 3.0F;
        this.scale = MathUtils.random(4.0F, 4.5F) * Settings.scale;
        this.color = new Color(MathUtils.random(0.6F, 0.7F), MathUtils.random(0.0F, 0.1F), MathUtils.random(0.6F, 0.7F), 0.0F);

        this.x = x + MathUtils.random(-AbstractDungeon.player.hb.width / 16.0F, AbstractDungeon.player.hb.width / 16.0F);
        this.y = y + MathUtils.random(-AbstractDungeon.player.hb.height / 16.0F, AbstractDungeon.player.hb.height / 12.0F);
        this.x -= (float)this.img.packedWidth / 2.0F;
        this.y -= (float)this.img.packedHeight / 2.0F;
        this.renderBehind = true;
        this.rotation = MathUtils.random(360.0F);

    }

    public void update() {
        if (3.0F - this.duration < 0.25F) {
            this.color.a = MathUtils.lerp(this.color.a, 0.3F, (3.0F - this.duration) * 4.0F);
        } else if(this.duration < 0.5F){
            this.color.a = MathUtils.lerp(0.0F, this.color.a, this.duration * 2.0F);
        }

        this.rotation += Gdx.graphics.getDeltaTime();
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
