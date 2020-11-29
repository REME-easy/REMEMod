package REMEMod.Actions;

import REMEMod.Powers.CutPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class CutPowerAction extends AbstractGameAction {
    private CutPower power;
    private AbstractGameAction action;

    public CutPowerAction(CutPower power, AbstractGameAction action) {
        this.duration = DEFAULT_DURATION;
        this.power = power;
        this.action = action;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                power.amount3++;
                power.updateDescription();
                if(power.amount3 == 2){
                    boolean anim = true;
                    for(AbstractGameEffect age:AbstractDungeon.effectList){
                        if(age instanceof InflameEffect){
                            anim = false;
                            break;
                        }
                    }
                    if (anim)
                        this.addToBot(new VFXAction(AbstractDungeon.player, new InflameEffect(AbstractDungeon.player), 0.1F));
                    power.flashWithoutSound();
                }
                if(power.amount3 == 3){
                    power.amount3 = 0;
                    if(action.target != null && action.target.isDead && !action.target.isDeadOrEscaped())
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new GoldenSlashEffect(action.target.hb.cX, action.target.hb.cY, true), 0.1F));
                    power.flash();
                }
            }
            this.tickDuration();
        }
    }
}
