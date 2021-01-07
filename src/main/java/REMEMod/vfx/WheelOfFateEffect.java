package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class WheelOfFateEffect extends AbstractGameEffect {
    public static int result;
    public static boolean isDoneWheel;
    private float resultAngle;
    private float tmpAngle;
    private boolean startSpin;
    private boolean finishSpin;
    private boolean doneSpinning;
    private boolean bounceIn;
    private boolean finalStop;
    private float bounceTimer;
    private float animTimer;
    private float autoButtonTimer;
    private float spinVelocity;
    private boolean buttonPressed;
    private Hitbox buttonHb;
    private Texture wheelImg;
    private Texture arrowImg;
    private Texture buttonImg;
    private float imgX;
    private float imgY;
    private float size;
    private float wheelAngle;
    private static final float ARROW_OFFSET_X = 300.0F * Settings.scale;
    private Color color;

    public WheelOfFateEffect() {
        this.startSpin = true;
        this.finishSpin = false;
        this.doneSpinning = false;
        this.bounceIn = true;
        this.bounceTimer = 1.0F;
        this.animTimer = 2.0F;
        this.autoButtonTimer = 0.5F;
        this.spinVelocity = 2000.0F;
        this.buttonPressed = false;
        this.buttonHb = new Hitbox(450.0F * Settings.scale, 300.0F * Settings.scale);
        this.imgX = (float)Settings.WIDTH / 2.0F;
        this.imgY = (float)Settings.HEIGHT / 2.0F;
        this.size = 0.0F;
        this.wheelAngle = 0.0F;
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        this.wheelImg = ImageMaster.loadImage("images/events/wheel.png");
        this.arrowImg = ImageMaster.loadImage("images/events/wheelArrow.png");
        this.buttonImg = ImageMaster.loadImage("images/events/spinButton.png");
        this.buttonHb.move(500.0F * Settings.scale, -500.0F * Settings.scale);

        result = AbstractDungeon.miscRng.random(0, 5);
        this.resultAngle = (float)result * 60.0F + MathUtils.random(-10.0F, 10.0F);
        isDoneWheel = false;
    }

    public void update() {
        this.updatePosition();
        if (this.bounceTimer == 0.0F && this.startSpin) {
            if (!this.buttonPressed) {
                if(this.autoButtonTimer > 0.0F){
                    this.autoButtonTimer -= Gdx.graphics.getDeltaTime();
                }else{
                    this.buttonPressed = true;
                    this.buttonHb.hovered = false;
                    CardCrawlGame.sound.play("WHEEL");
                }
                this.buttonHb.cY = MathHelper.cardLerpSnap(this.buttonHb.cY, Settings.OPTION_Y - 330.0F * Settings.scale);
                this.buttonHb.move(this.buttonHb.cX, this.buttonHb.cY);
                this.buttonHb.update();
            } else {
                this.buttonHb.cY = MathHelper.cardLerpSnap(this.buttonHb.cY, -500.0F * Settings.scale);
            }
        }

        if (this.startSpin && this.bounceTimer == 0.0F && this.buttonPressed) {
            this.size = 1.0F;
            if (this.animTimer > 0.0F) {
                this.animTimer -= Gdx.graphics.getDeltaTime();
                this.wheelAngle += this.spinVelocity * Gdx.graphics.getDeltaTime();
            } else {
                this.finishSpin = true;
                this.animTimer = 2.0F;
                this.startSpin = false;
                this.tmpAngle = this.resultAngle;
            }
        } else if (this.finishSpin) {
            if (this.animTimer > 0.0F) {
                this.animTimer -= Gdx.graphics.getDeltaTime();
                if (this.animTimer < 0.0F) {
                    this.animTimer = 1.0F;
                    this.finishSpin = false;
                    this.doneSpinning = true;
                }

                if(!this.finalStop){
                    this.spinVelocity -= 300.0F * Gdx.graphics.getDeltaTime();
                    this.wheelAngle += this.spinVelocity * Gdx.graphics.getDeltaTime();
                    if(this.wheelAngle == this.tmpAngle){
                        this.spinVelocity = 0.0F;
                        this.wheelAngle = this.tmpAngle;
                        this.finalStop = true;
                    }
                }

            }
        } else if (this.doneSpinning) {
            if (this.animTimer > 0.0F) {
                this.animTimer -= Gdx.graphics.getDeltaTime();
                if (this.animTimer <= 0.0F) {
                    this.bounceTimer = 1.0F;
                    this.bounceIn = false;
                }
            } else if (this.bounceTimer == 0.0F) {
                this.doneSpinning = false;
                //this.preApplyResult();
                this.isDone = true;
                isDoneWheel = true;
            }
        }
    }

    private void updatePosition() {
        if (this.bounceTimer != 0.0F) {
            this.bounceTimer -= Gdx.graphics.getDeltaTime();
            if (this.bounceTimer < 0.0F) {
                this.bounceTimer = 0.0F;
            }

            if (this.bounceIn && this.startSpin) {
                this.color.a = MathUtils.lerp(1.0F, this.color.a, this.bounceTimer);
                this.size = MathUtils.lerp(1.0F, this.size, this.bounceTimer);
            } else if (this.doneSpinning) {
                this.color.a = MathUtils.lerp(0.0F, this.color.a, this.bounceTimer);
                this.size = MathUtils.lerp(0.0F, this.size, this.bounceTimer);
            }
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.wheelImg, this.imgX - 512.0F, this.imgY - 512.0F, 512.0F, 512.0F, 1024.0F, 1024.0F, Settings.scale * size, Settings.scale * size, this.wheelAngle, 0, 0, 1024, 1024, false, false);
        sb.draw(this.arrowImg, this.imgX - 256.0F + ARROW_OFFSET_X + 180.0F * Settings.scale, this.imgY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * size, Settings.scale * size, 0.0F, 0, 0, 512, 512, false, false);
        if (this.buttonHb.hovered) {
            sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.05F, Settings.scale * 1.05F, 0.0F, 0, 0, 512, 512, false, false);
        } else {
            sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
        }

        sb.setBlendFunction(770, 1);
        if (this.buttonHb.hovered) {
            sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
        } else {
            sb.setColor(new Color(1.0F, 1.0F, 1.0F, (MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) + 1.25F) / 3.5F));
        }

        if (this.buttonHb.hovered) {
            sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.05F, Settings.scale * 1.05F, 0.0F, 0, 0, 512, 512, false, false);
        } else {
            sb.draw(this.buttonImg, this.buttonHb.cX - 256.0F, this.buttonHb.cY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
        }

        if (Settings.isControllerMode) {
            sb.draw(CInputActionSet.proceed.getKeyImg(), this.buttonHb.cX - 32.0F - 160.0F * Settings.scale, this.buttonHb.cY - 32.0F - 70.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
        }

        sb.setBlendFunction(770, 771);
        this.buttonHb.render(sb);
    }

    public void dispose() {
        if (this.wheelImg != null) {
            this.wheelImg.dispose();
        }

        if (this.arrowImg != null) {
            this.arrowImg.dispose();
        }

        if (this.buttonImg != null) {
            this.buttonImg.dispose();
        }

    }
}
