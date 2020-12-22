package REMEMod.Actions;

import REMEMod.vfx.BorderFlashAlwaysEffect;
import REMEMod.vfx.PuzzleBoxOrbEffect;
import REMEMod.vfx.WheelOfFateEffect;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WheelOfFateAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(WheelOfFateAction.class);
    public static int goldAmt = 80;
    public static int healAmt = 20;
    private int dmgAmt;
    private int dmg;
    public WheelOfFateAction(int dmg, int amt) {
        this.duration = DEFAULT_DURATION;
        this.dmg = dmg;
        this.dmgAmt = amt;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                int i;
                AbstractDungeon.effectsQueue.add(new BorderFlashAlwaysEffect(new Color(1.0F, 0.2F, 1.0F, 1.0F),true, 2.0F));
                for(i = 0 ; i < 36 ; i++){
                    AbstractDungeon.effectsQueue.add(new PuzzleBoxOrbEffect(Settings.WIDTH / 2, Settings.HEIGHT / 2, i));
                }
                AbstractDungeon.topLevelEffectsQueue.add(new WheelOfFateEffect());

                this.tickDuration();
            }else if(WheelOfFateEffect.isDoneWheel){
                int i;
                applyResult();
                for(i = 0 ; i < 36 ; i++){
                    AbstractDungeon.topLevelEffectsQueue.add(new PuzzleBoxOrbEffect(Settings.WIDTH / 2, Settings.HEIGHT / 2, i));
                }
                this.isDone = true;
                WheelOfFateEffect.isDoneWheel = false;
            }
        }


    }

    private void applyResult() {
        switch (WheelOfFateEffect.result){
            case 0:
                this.addToBot(new GainGoldAction(goldAmt));
                break;
            case 1:
                AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, relic);
                break;
            case 2:
                this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, healAmt));
                this.addToBot(new GainBlockAction(AbstractDungeon.player, healAmt));
                break;
            case 3:
                this.addToBot(new PuzzleBoxAction(10));
                break;
            case 4:
                exhaustCards();
                break;
            default:
                for(int i = 0; i < this.dmgAmt; ++i) {
                    AbstractMonster m = AbstractDungeon.getRandomMonster();
                    if (m != null && !m.isDeadOrEscaped() && !m.isDead) {
                        this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
                        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.dmg, DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
                    }
                }

        }
    }

    private void exhaustCards() {
        if(AbstractDungeon.player.hand.group != null){
            int amt = AbstractDungeon.player.hand.size();
            for(AbstractCard c:AbstractDungeon.player.hand.group){
                this.addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
            for(int i = 0 ; i < amt ; i++){
                AbstractCard c1 = AbstractDungeon.returnTrulyRandomCard().makeCopy();
                c1.modifyCostForCombat(-9);
                c1.isCostModified = true;
                c1.isEthereal = true;
                c1.exhaust = true;
                c1.glowColor = Color.GREEN;
                this.addToBot(new MakeTempCardInHandSAction(c1));
            }
        }
    }
}
