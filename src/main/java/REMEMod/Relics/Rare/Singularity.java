package REMEMod.Relics.Rare;

import REMEMod.Helpers.REMEHelper;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Singularity extends CustomRelic implements CustomSavable<ArrayList<String>> {
    private static final Logger logger = LogManager.getLogger(Singularity.class);
    private static String ID = "REME_Singularity";
    private ArrayList<String> potions = new ArrayList<>();

    public Singularity() {
        super(ID, ImageMaster.loadImage("remeImg/relics/Singularity.png"),
                RelicTier.RARE, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Singularity();
    }

    @Override
    public void onUsePotion() {
        super.onUsePotion();
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (String s : potions) {
                AbstractPotion p = PotionHelper.getPotion(s).makeCopy();
                logger.info("使用了药水" + p.name);
                if (p.targetRequired) {
                    p.use(REMEHelper.getRandomMonsterSafe());
                }else {
                    p.use(null);
                }
            }
        }
        AbstractPotion po = ReflectionHacks.getPrivate(AbstractDungeon.topPanel.potionUi, PotionPopUp.class, "potion");
        potions.add(po.ID);
    }

    @Override
    public ArrayList<String> onSave() {
        return potions;
    }

    @Override
    public void onLoad(ArrayList<String> strings) {
        this.potions = strings;
    }
}
