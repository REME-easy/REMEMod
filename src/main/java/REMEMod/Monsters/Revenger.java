package REMEMod.Monsters;

import REMEMod.Actions.PlayAnimationAction;
import REMEMod.Powers.RevengePower;
import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Bone;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameParticleEffect;

public class Revenger extends CustomMonster {
    private static final String ID = "REME_Revenger";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public  static final String NAME = monsterStrings.NAME;
    private static final String ATLAS = "enemies/revenger/revenger.atlas";
    private static final String JSON = "enemies/revenger/revenger.json";
    private boolean firstMove = true;
    private boolean isAngry = false;
    private Bone weapon;
    private float Timer = 0.0F;
    public Revenger(float x, float y){
        super(NAME,ID,140,0.0F,0.0F,250.0F,270.0F,null, x, y);
        this.loadAnimation(ATLAS, JSON, 1.4F);
        this.weapon = this.skeleton.findBone("C_Weapon");
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        int slashDmg;
        int multiDmg;
        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(162, 170);
        } else {
            this.setHp(156, 164);
        }

        if (AbstractDungeon.ascensionLevel >= 2) {
            slashDmg = 22;
            multiDmg = 6;
        }else{
            slashDmg = 19;
            multiDmg = 8;
        }
        this.damage.add(new DamageInfo(this, slashDmg));
        this.damage.add(new DamageInfo(this, multiDmg));
    }

    public Revenger(){
        this(0.0F, 0.0F);
    }

    @Override
    public void usePreBattleAction() {
        super.usePreBattleAction();
        this.addToBot(new ApplyPowerAction(this, this, new RevengePower(this)));
        if(Loader.isModLoaded("PowerfulMonsterMod")){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new RitualPower(this, 3, false)));
        }
    }

    public void takeTurn(){

        switch(this.nextMove) {
            case 1:
                if(AbstractDungeon.ascensionLevel >= 17) {
                    this.addToBot(new GainBlockAction(this, 11));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1)));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new PlayAnimationAction(this, "Attack"));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                break;
            case 3:
                for(int i = 0 ; i < 2 ; i++){
                    AbstractDungeon.actionManager.addToBottom(new PlayAnimationAction(this, "Attack"));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }
                AbstractDungeon.actionManager.addToBottom(new PlayAnimationAction(this, "Attack"));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));

        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void getMove(int num){
        int move;
        if(firstMove){
            firstMove = false;
            if(AbstractDungeon.ascensionLevel >= 17){
                move = 0;
            }
            move = 1;
        }else if(num < 50){
            move = 2;
        }else{
            move = 3;
        }
        getMoveAction(move);
    }

    private void getMoveAction(int move){
        switch (move){
            case 0:
                this.setMove((byte)1, Intent.DEFEND_BUFF);
                break;
            case 1:
                this.setMove((byte)1, Intent.BUFF);
                break;
            case 2:
                this.setMove((byte)2, Intent.ATTACK, damage.get(0).base);
                break;
            case 3:
                this.setMove((byte)3, Intent.ATTACK, damage.get(1).base, 3, true);
        }
    }

    public void changeState(String stateName) {
        switch (stateName){
            case "ATTACK":
                this.state.setAnimation(0, "Attack", false);
                this.state.addAnimation(0, "Idle", true, 0.0F);
                break;
                default:
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if(this.currentHealth < this.maxHealth / 2 && !isAngry){
            isAngry = true;
            this.addToBot(new VFXAction(this, new FlameBarrierEffect(hb.cX, hb.cY), 0.1F));
            this.applyPowers();
        }
    }

    @Override
    public void update() {
        super.update();
        if(this.isAngry){
            this.Timer -= Gdx.graphics.getDeltaTime();
            if(this.Timer <= 0.0F){
                this.Timer = MathUtils.random(0.05F, 0.10F);
                AbstractDungeon.effectList.add(new FlameParticleEffect(this.skeleton.getX() + this.weapon.getWorldX() + 75.0F, this.skeleton.getY() + this.weapon.getWorldY() + 75.0F));
            }
        }
    }

    public void die(){
        super.die();
        this.state.setAnimation(0, "Die", false);
    }

}
