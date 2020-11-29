//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package REMEMod.Patches;

import REMEMod.Powers.AbstractREMEPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.GremlinWheelGame;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Objects;

public class ShopPatch {
    public ShopPatch() {
    }

    @SpirePatch(
            clz = ShopScreen.class,
            method = "updatePurge"
    )
    public static class PurgePatch {
        public PurgePatch() {
        }

        public static SpireReturn<Void> Prefix(ShopScreen _inst) {
            if(!_inst.purgeAvailable && AbstractDungeon.player.hasRelic("REME_VIPCard")) {
                _inst.purgeAvailable = true;
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = ShopRoom.class,
            method = "update"
    )
    public static class WheelPatch {
        private static final Logger logger = LogManager.getLogger(GremlinWheelGame.class.getName());
        private static int result;
        private static float resultAngle;
        private static float tmpAngle;
        private static boolean startSpin;
        private static boolean finishSpin;
        private static boolean doneSpinning;
        private static boolean bounceIn;
        private static float bounceTimer;
        private static float animTimer;
        private static float spinVelocity;
        private static int goldAmount;
        private static boolean purgeResult;
        private static boolean buttonPressed;
        private static Hitbox buttonHb;
        private static Texture wheelImg = ImageMaster.loadImage("images/events/wheel.png");
        private static Texture arrowImg = ImageMaster.loadImage("images/events/wheelArrow.png");
        private static Texture buttonImg = ImageMaster.loadImage("images/events/spinButton.png");
        private static final float START_Y;
        private static final float TARGET_Y;
        private static float imgX;
        private static float imgY;
        private static float wheelAngle;
        private static final int WHEEL_W = 1024;
        private static final int ARROW_W = 512;
        private static final float ARROW_OFFSET_X;
        private static Color color;
        private static float hpLossPercent;
        private static final float A_2_HP_LOSS = 0.15F;
        public WheelPatch() {
        }

        public static SpireReturn<Void> Prefix(ShopRoom _inst) {
            if(startSpin){

            }
            return SpireReturn.Continue();
        }

        private void init() {
            this.startSpin = false;
            this.finishSpin = false;
            this.doneSpinning = false;
            this.bounceIn = true;
            this.bounceTimer = 1.0F;
            this.animTimer = 3.0F;
            this.spinVelocity = 200.0F;
            this.purgeResult = false;
            this.buttonPressed = false;
            this.buttonHb = new Hitbox(450.0F * Settings.scale, 300.0F * Settings.scale);
            this.imgX = (float)Settings.WIDTH / 2.0F;
            this.imgY = START_Y;
            this.wheelAngle = 0.0F;
            this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
            this.hpLossPercent = 0.1F;
            if (AbstractDungeon.ascensionLevel >= 15) {
                this.hpLossPercent = 0.15F;
            }

            if (Objects.equals(AbstractDungeon.id, "Exordium")) {
                this.goldAmount = 100;
            } else if (Objects.equals(AbstractDungeon.id, "TheCity")) {
                this.goldAmount = 200;
            } else if (Objects.equals(AbstractDungeon.id, "TheBeyond")) {
                this.goldAmount = 300;
            }

            this.buttonHb.move(500.0F * Settings.scale, -500.0F * Settings.scale);
        }


        private void updatePosition() {
            if (this.bounceTimer != 0.0F) {
                this.bounceTimer -= Gdx.graphics.getDeltaTime();
                if (this.bounceTimer < 0.0F) {
                    this.bounceTimer = 0.0F;
                }

                if (this.bounceIn && this.startSpin) {
                    this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.bounceTimer);
                    this.imgY = Interpolation.bounceIn.apply(TARGET_Y, START_Y, this.bounceTimer);
                } else if (this.doneSpinning) {
                    this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.bounceTimer);
                    this.imgY = Interpolation.swingOut.apply(START_Y, TARGET_Y, this.bounceTimer);
                }
            }

        }

        static {
            START_Y = Settings.OPTION_Y + 1000.0F * Settings.scale;
            TARGET_Y = Settings.OPTION_Y;
            ARROW_OFFSET_X = 300.0F * Settings.scale;
        }
    }


}
