package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BloodShotParticleEffect;

public class CthunDamageAction extends AbstractGameAction {
    private int dmg;
    private boolean isPercent;

    public CthunDamageAction(int dmg) {
        this(dmg, false);
    }

    public CthunDamageAction(int dmg, boolean isPercent){
        this.dmg = dmg;
        this.isPercent = isPercent;
        this.duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                int i;
                AbstractMonster m;
                AbstractPlayer p = AbstractDungeon.player;
                for(i = 0 ; i < dmg ; i++){
                    m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    int damage = isPercent ? Math.max(1, m.maxHealth / 100) : 1;
                    if(m != null && !m.isDead && !m.isDeadOrEscaped()){
                        this.addToBot(new VFXAction(new BloodShotParticleEffect(p.drawX, p.drawY + 50.0F, m.drawX, m.drawY), 0.05F));
                        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.HP_LOSS), AttackEffect.FIRE));
                    }
                }
            }
            this.isDone = true;
        }
    }
}
