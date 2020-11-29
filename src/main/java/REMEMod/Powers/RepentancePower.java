package REMEMod.Powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class RepentancePower extends AbstractPower {
    private static final String POWER_ID = "REME_RepentancePower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public RepentancePower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.BUFF;
        String path128 = "img/powers/Repentance84.png";
        String path48 = "img/powers/Repentance32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.flash();
            int i;

            for(AbstractMonster m:AbstractDungeon.getMonsters().monsters){
                if (m != null && !m.isDeadOrEscaped()) {
                    if (Settings.FAST_MODE) {
                        this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
                        this.addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
                    } else {
                        this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
                        this.addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));
                    }
                }
            }

            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageType.THORNS, AttackEffect.NONE, true));
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
