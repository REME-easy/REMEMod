package REMEMod.Actions;

import REMEMod.vfx.FloggingFireBallEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class FloggingAction extends AbstractGameAction {
    private int num;
    private int dmg;
    public FloggingAction(int num, int dmg){
        this.duration = 0.01F;
        this.actionType = ActionType.DAMAGE;
        this.num = num;
        this.dmg = dmg;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
        } else {
            if(num > 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
                int i;
                AbstractMonster m;
                ArrayList<AbstractMonster> monsters = new ArrayList<>();
                for(i = 0 ; i < num ; i++){
                    m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    if(m != null && !m.isDead && !m.isDeadOrEscaped()){
                        this.addToBot(new SFXAction("ATTACK_WHIFF_2", 0.3F));
                        this.addToBot(new VFXAction(new FloggingFireBallEffect(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY + 200.0F, m.drawX, m.drawY)));
                        monsters.add(m);
                    }
                }
                if(monsters.size() > 0)
                    for(AbstractMonster m1:monsters){
                        if(m1 != null && !m1.isDead && !m1.isDeadOrEscaped()){
                            this.addToBot(new DamageAction(m1, new DamageInfo(AbstractDungeon.player, dmg, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
                        }
                    }


            }
            this.isDone = true;
        }
    }
}
