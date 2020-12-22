package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FloatingCardEffect extends AbstractGameEffect {
    private AbstractCard card;
    private String text;

    private static final float DURATION = 3.5F;
    private static final float MAX_SIZE = 0.55F;

    public FloatingCardEffect(AbstractCard card, Vector2 pos) {
        this(card, "", pos);
    }

    public FloatingCardEffect(AbstractCard card, String text, Vector2 pos){
        this.card = card.makeCopy();
        this.card.applyPowers();
        this.card.drawScale = 0.1F;
        this.card.current_x = pos.x;
        this.card.current_y = pos.y;
        this.text = text;
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.1F);
        this.duration = DURATION;
    }

    public void update() {
        if(DURATION - duration < 1.0F){
            this.color.a = MathUtils.lerp(this.color.a, 1.0F, DURATION - duration);
            this.card.drawScale = MathUtils.lerp(this.card.drawScale, MAX_SIZE, DURATION - duration);
        }else if(duration < 1.0F){
            this.color.a = MathUtils.lerp(0.1F, this.color.a, duration);
            this.card.drawScale = MathUtils.lerp(0.01F, this.card.drawScale, duration);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        if(this.card.drawScale <= 0.8F){
            this.card.render(sb);
        }
        if(text != null){
            FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, text, this.card.current_x, this.card.current_y, Color.SKY, this.card.drawScale * 2.0F);
        }
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
        card = null;
    }
}
