package REMEMod.Relics;

import REMEMod.REMEMod;
import REMEMod.Screens.VIPCardPopup;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RunicPyramid;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.gridSelectScreen;

public class VIPCard extends CustomRelic {
    public static VIPCardPopup popup = new VIPCardPopup();
    private boolean RclickStart = false;
    private boolean Rclick = false;

    public VIPCard() {
        super("REME_VIPCard", ImageMaster.loadImage("remeImg/relics/HandOfSuture.png"),
                RelicTier.SHOP, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    private void onRightClick(){
        if (AbstractDungeon.getCurrRoom() instanceof ShopRoom){
            popup.open(this);
        }
    }

    public void update() {
        super.update();
        if (this.RclickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Rclick = true;
            }
            this.RclickStart = false;
        }
        if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
            this.RclickStart = true;
        }
        if (this.Rclick) {
            this.Rclick = false;
            this.onRightClick();
        }
    }

    public AbstractRelic makeCopy() {
        return new VIPCard();
    }
}
