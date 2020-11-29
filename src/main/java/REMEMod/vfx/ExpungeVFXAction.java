package REMEMod.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExpungeVFXAction extends AbstractGameAction {
    private int maxnum;

    public ExpungeVFXAction(AbstractMonster m, int maxnum) {
        this.source = m;
        this.maxnum = maxnum;
    }

    public void update() {
        if (!this.source.isDeadOrEscaped()) {
            int index = MathUtils.random(maxnum);
            float angle = (index + 1) * (360.0F / maxnum);
            this.addToTop(new VFXAction(new AnimatedSlashEffect(
                    this.source.hb.cX - 60.0F * Settings.scale,
                    this.source.hb.cY - 60.0F * Settings.scale,
                    MathUtils.cos(angle * MathUtils.PI / 180.0F) * -500.0F,
                    MathUtils.sin(angle * MathUtils.PI / 180.0F) * -500.0F,
                    angle + 90.0F, 6.0F, Color.VIOLET, Color.MAGENTA)));
            angle = (index + 2) * (360.0F / maxnum);
            this.addToTop(new VFXAction(new AnimatedSlashEffect(
                    this.source.hb.cX - 60.0F * Settings.scale,
                    this.source.hb.cY - 60.0F * Settings.scale,
                    MathUtils.cos(angle * MathUtils.PI / 180.0F) * -500.0F,
                    MathUtils.sin(angle * MathUtils.PI / 180.0F) * -500.0F,
                    angle + 90.0F, 5.5F, Color.MAGENTA, Color.MAGENTA)));
            angle = (index + 3) * (360.0F / maxnum);
            this.addToTop(new VFXAction(new AnimatedSlashEffect(
                    this.source.hb.cX - 60.0F * Settings.scale,
                    this.source.hb.cY - 60.0F * Settings.scale,
                    MathUtils.cos(angle * MathUtils.PI / 180.0F) * -500.0F,
                    MathUtils.sin(angle * MathUtils.PI / 180.0F) * -500.0F,
                    angle + 90.0F, 5.0F, Color.MAGENTA, Color.MAGENTA)));
            this.addToTop(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.3F, true));
            this.addToTop(new SFXAction("ATTACK_IRON_3", 0.1F));
        }

        this.isDone = true;
    }
}
