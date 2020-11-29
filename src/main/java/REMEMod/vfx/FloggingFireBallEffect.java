package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

public class FloggingFireBallEffect extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private CatmullRomSpline<Vector2> crs = new CatmullRomSpline<>();
    private ArrayList<Vector2> controlPoints = new ArrayList<>();
    private Vector2[] points = new Vector2[60];
    private Vector2 pos;
    private Vector2 target;
    private float currentSpeed;
    private static final float START_VELOCITY;
    private static final float MAX_VELOCITY;
    private static final float VELOCITY_RAMP_RATE;
    private static final float DST_THRESHOLD;
    private static final float HOME_IN_THRESHOLD;
    private boolean rotateClockwise;
    private float rotation;
    private boolean stopRotating = false;
    private float rotationRate;

    public FloggingFireBallEffect(float sx, float sy, float dx, float dy) {
        this.img = ImageMaster.GLOW_SPARK_2;
        this.pos = new Vector2(sx, sy);
        this.target = new Vector2(dx, dy);
        this.crs.controlPoints = new Vector2[1];
        this.rotation = MathUtils.random(0, 359);
        this.rotationRate = MathUtils.random(500.0F, 700.0F) * Settings.scale;
        this.rotateClockwise = MathUtils.randomBoolean();
        this.currentSpeed = START_VELOCITY * MathUtils.random(0.2F, 1.0F);
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.4F);
        this.duration = 1.0F;
    }

    public void update() {
        this.updateMovement();
    }

    private void updateMovement() {
        Vector2 tmp = new Vector2(this.pos.x - this.target.x, this.pos.y - this.target.y);
        tmp.nor();
        float targetAngle = tmp.angle();
        if (!this.stopRotating) {
            if (this.rotateClockwise) {
                this.rotation += Gdx.graphics.getDeltaTime() * this.rotationRate;
            } else {
                this.rotation -= Gdx.graphics.getDeltaTime() * this.rotationRate;
                if (this.rotation < 0.0F) {
                    this.rotation += 360.0F;
                }
            }

            this.rotation %= 360.0F;
            if (!this.stopRotating) {
                if (this.target.dst(this.pos) < HOME_IN_THRESHOLD || duration < 0.4F) {
                    this.rotation = targetAngle;
                    this.stopRotating = true;
                } else if (Math.abs(this.rotation - targetAngle) < Gdx.graphics.getDeltaTime() * this.rotationRate) {
                    this.rotation = targetAngle;
                    this.stopRotating = true;
                }
            }
        }

        tmp.setAngle(this.rotation);
        tmp.x *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
        tmp.y *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
        this.pos.sub(tmp);
        if (this.stopRotating) {
            this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 5.0F;
        } else {
            this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5F;
        }

        if (this.currentSpeed > MAX_VELOCITY) {
            this.currentSpeed = MAX_VELOCITY;
        }

        if ((this.target.x >= (float)Settings.WIDTH / 2.0F || this.pos.x >= 0.0F) && (this.target.x <= (float)Settings.WIDTH / 2.0F || this.pos.x <= (float)Settings.WIDTH) && this.target.dst(this.pos) >= DST_THRESHOLD) {
            if (!this.controlPoints.isEmpty()) {
                if (!(this.controlPoints.get(0)).equals(this.pos)) {
                    this.controlPoints.add(this.pos.cpy());
                }
            } else {
                this.controlPoints.add(this.pos.cpy());
            }

            if (this.controlPoints.size() > 3) {
                Vector2[] vec2Array = new Vector2[0];
                this.crs.set(this.controlPoints.toArray(vec2Array), false);

                for(int i = 0; i < 60; ++i) {
                    this.points[i] = new Vector2();
                    this.crs.valueAt(this.points[i], (float)i / 59.0F);
                }
            }

            if (this.controlPoints.size() > 10) {
                this.controlPoints.remove(0);
            }

            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration < 0.0F) {
                this.isDone = true;
            }

        } else {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.color);
            float scale = Settings.scale * 2.5F;

            for(int i = this.points.length - 1; i > 0; --i) {
                if (this.points[i] != null) {
                    sb.draw(this.img, this.points[i].x - (float)(this.img.packedWidth / 2), this.points[i].y - (float)(this.img.packedHeight / 2), (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, scale, scale, this.rotation);
                    scale *= 0.975F;
                    if(i % 6 == 0){
                        AbstractDungeon.effectsQueue.add(new FloggingParticleWhiteEffect(points[i].x, points[i].y, target.x, target.y));
                    }
                }
            }

            sb.setBlendFunction(770, 771);
        }

    }

    public void dispose() {
    }
    static {
        START_VELOCITY = 1500.0F * Settings.scale;
        MAX_VELOCITY = 10000.0F * Settings.scale;
        VELOCITY_RAMP_RATE = 2000.0F * Settings.scale;
        DST_THRESHOLD = 36.0F * Settings.scale;
        HOME_IN_THRESHOLD = 36.0F * Settings.scale;
    }
}
