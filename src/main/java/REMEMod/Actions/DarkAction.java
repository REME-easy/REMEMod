package REMEMod.Actions;

import REMEMod.Helpers.REMEHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DarkAction extends AbstractGameAction {
    private int dmg;
    private AbstractCard card;
    public DarkAction(int dmg, AbstractCard card){
        this.duration = 0.01F;
        this.actionType = ActionType.DAMAGE;
        this.dmg = dmg;
        this.card = card;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
        } else {
            if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
                AbstractMonster m = REMEHelper.getRandomMonsterSafe();
                if(m != null){
                    card.applyPowers();
                    card.calculateCardDamage(m);
                    this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.dmg, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
                }
            }
            this.isDone = true;
        }
    }
}
