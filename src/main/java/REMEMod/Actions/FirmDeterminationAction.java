package REMEMod.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FirmDeterminationAction extends AbstractGameAction {
    private int num;
    private int block;

    public FirmDeterminationAction(int num, int block) {
        this.duration = DEFAULT_DURATION;
        this.num = num;
        this.block = block;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                AbstractPlayer p = AbstractDungeon.player;
                if(Settings.hasRubyKey){
                    this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, num),num));
                }
                if(Settings.hasEmeraldKey){
                    this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, num),num));
                }
                if(Settings.hasSapphireKey){
                    this.addToBot(new GainBlockAction(p, block));
                }
            }
            this.tickDuration();
        }
    }
}
