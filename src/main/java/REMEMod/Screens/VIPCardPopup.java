package REMEMod.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.GameCursor.CursorType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class VIPCardPopup {
    private AbstractRelic relic;
    public boolean isHidden = true;
    public boolean targetMode = false;
    private Hitbox hbTop;
    private Hitbox hbBot;
    private Color topHoverColor;
    private Color botHoverColor;
    private float x;
    private float y;
    private static final UIStrings uiStrings;
    private static final String WHEEL_LABEL;
    private static final String HEAL_LABEL;
    public static final String WHEEL_MESSAGE;
    public static final String HEAL_MESSAGE;
    private static final String CANT_LABEL;

    public VIPCardPopup() {
        this.hbTop = new Hitbox(286.0F * Settings.scale, 120.0F * Settings.scale);
        this.hbBot = new Hitbox(286.0F * Settings.scale, 90.0F * Settings.scale);
        this.topHoverColor = new Color(0.5F, 0.9F, 1.0F, 0.0F);
        this.botHoverColor = new Color(1.0F, 0.4F, 0.3F, 0.0F);
    }

    public void open(AbstractRelic relic) {
        this.topHoverColor.a = 0.0F;
        this.botHoverColor.a = 0.0F;
        AbstractDungeon.topPanel.selectPotionMode = false;
        this.relic = relic;
        this.x = relic.hb.cX;
        this.y = relic.hb.cY - 164.0F * Settings.scale;
        this.isHidden = false;
        this.hbTop.move(this.x, this.y + 44.0F * Settings.scale);
        this.hbBot.move(this.x, this.y - 76.0F * Settings.scale);
        this.hbTop.clickStarted = false;
        this.hbBot.clickStarted = false;
        this.hbTop.clicked = false;
        this.hbBot.clicked = false;
    }

    public void close() {
        this.isHidden = true;
    }

    public void update() {
        if (!this.isHidden) {
            this.updateControllerInput();
            this.hbTop.update();
            this.hbBot.update();
            this.updateInput();
        }

    }

    private void updateControllerInput() {
        if (Settings.isControllerMode) {
            if (CInputActionSet.cancel.isJustPressed()) {
                CInputActionSet.cancel.unpress();
                this.close();
            } else {
                if (!this.hbTop.hovered && !this.hbBot.hovered) {
                    Gdx.input.setCursorPosition((int)this.hbTop.cX, Settings.HEIGHT - (int)this.hbTop.cY);
                } else if (this.hbTop.hovered) {
                    if (CInputActionSet.up.isJustPressed() || CInputActionSet.down.isJustPressed() || CInputActionSet.altUp.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
                        Gdx.input.setCursorPosition((int)this.hbBot.cX, Settings.HEIGHT - (int)this.hbBot.cY);
                    }
                } else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.down.isJustPressed() || CInputActionSet.altUp.isJustPressed() || CInputActionSet.altDown.isJustPressed())) {
                    Gdx.input.setCursorPosition((int)this.hbTop.cX, Settings.HEIGHT - (int)this.hbTop.cY);
                }

            }
        }
    }

    private void updateInput() {
        if (InputHelper.justClickedLeft) {
            InputHelper.justClickedLeft = false;
            if (this.hbTop.hovered) {
                this.hbTop.clickStarted = true;
                InputHelper.justClickedLeft = false;
            } else if (this.hbBot.hovered) {
                this.hbBot.clickStarted = true;
                InputHelper.justClickedLeft = false;
            } else {
                this.close();
            }
        }

        if (this.hbTop.clicked || this.hbTop.hovered && CInputActionSet.select.isJustPressed()) {
            CInputActionSet.select.unpress();
            this.hbTop.clicked = false;
            CardCrawlGame.relicPopup.open(this.relic, AbstractDungeon.player.relics);
            CInputActionSet.select.unpress();
            InputHelper.justClickedLeft = false;
            this.close();
        } else if ((this.hbBot.clicked || this.hbBot.hovered && CInputActionSet.select.isJustPressed())) {
            CInputActionSet.select.unpress();
            this.hbBot.clicked = false;
            CInputActionSet.select.unpress();
            InputHelper.justClickedLeft = false;
            this.close();
        }

    }

    public void render(SpriteBatch sb) {
        if (!this.isHidden) {
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.POTION_UI_BG, this.x - 200.0F, this.y - 169.0F, 200.0F, 169.0F, 400.0F, 338.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 400, 338, false, false);
            this.topHoverColor.a = this.hbTop.hovered ? 0.5F : MathHelper.fadeLerpSnap(this.topHoverColor.a, 0.0F);
            this.botHoverColor.a = this.hbBot.hovered ? 0.5F : MathHelper.fadeLerpSnap(this.botHoverColor.a, 0.0F);
            sb.setBlendFunction(770, 1);
            sb.setColor(this.topHoverColor);
            sb.draw(ImageMaster.POTION_UI_TOP, this.x - 200.0F, this.y - 169.0F, 200.0F, 169.0F, 400.0F, 338.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 400, 338, false, false);
            sb.setColor(this.botHoverColor);
            sb.draw(ImageMaster.POTION_UI_BOT, this.x - 200.0F, this.y - 169.0F, 200.0F, 169.0F, 400.0F, 338.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 400, 338, false, false);
            sb.setBlendFunction(770, 771);
            Color c = Settings.CREAM_COLOR;

            FontHelper.renderFontCenteredWidth(sb, FontHelper.buttonLabelFont, WHEEL_LABEL, this.x, this.hbTop.cY + 4.0F * Settings.scale, Settings.CREAM_COLOR);
            FontHelper.renderFontCenteredWidth(sb, FontHelper.buttonLabelFont, HEAL_LABEL, this.x, this.hbBot.cY + 12.0F * Settings.scale, c);
            this.hbTop.render(sb);
            this.hbBot.render(sb);
            if (this.hbBot.hovered) {
                float tipX = this.x > (float)Settings.WIDTH * 0.75F ? this.x - 174.0F * Settings.scale : this.x + 174.0F * Settings.scale;
                TipHelper.renderGenericTip(tipX, this.y + 20.0F * Settings.scale, CANT_LABEL, CANT_LABEL);
            }
        }

    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("REME_VIPCARD");
        WHEEL_LABEL = uiStrings.TEXT[0];
        HEAL_LABEL = uiStrings.TEXT[1];
        WHEEL_MESSAGE = uiStrings.TEXT[2];
        HEAL_MESSAGE = uiStrings.TEXT[3];
        CANT_LABEL = uiStrings.TEXT[4];
    }
}
