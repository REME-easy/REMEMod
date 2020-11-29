package REMEMod.Powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UploadPower extends AbstractPower {
    private static final String POWER_ID = "REME_UploadPower";
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;

    private boolean upgraded;

    public UploadPower(AbstractCreature owner, boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID + upgraded;
        this.owner = owner;
        this.amount = 1;
        this.upgraded = upgraded;
        this.type = PowerType.BUFF;
        String path128 = "img/powers/pmmod.png";
        String path48 = "img/powers/pmmod.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if(target == AbstractDungeon.player){

        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("REME_FearOfPainPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
