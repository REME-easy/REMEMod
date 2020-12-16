package REMEMod.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MusicOfDeathPower extends AbstractPower {
    private static final String POWER_ID = "REME_MusicOfDeathPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    public MusicOfDeathPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.BUFF;
        String path128 = "remeImg/powers/MusicOfDeath84.png";
        String path48 = "remeImg/powers/MusicOfDeath32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @SpirePatch(
            clz = MakeTempCardInDrawPileAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, int.class, boolean.class, boolean.class, boolean.class, float.class, float.class}
    )
    public static class MusicOfDeathPatch{
        public MusicOfDeathPatch(){}

        public static void Postfix(MakeTempCardInDrawPileAction _inst, AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom, float cardX, float cardY){
            if(!AbstractDungeon.actionManager.turnHasEnded && AbstractDungeon.player.hasPower("REME_MusicOfDeathPower")){
                _inst.amount += AbstractDungeon.player.getPower("REME_MusicOfDeathPower").amount;
                AbstractDungeon.player.getPower("REME_MusicOfDeathPower").flash();
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
