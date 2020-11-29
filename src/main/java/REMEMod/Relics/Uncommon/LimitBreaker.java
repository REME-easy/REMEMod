package REMEMod.Relics.Uncommon;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class LimitBreaker extends CustomRelic {

    public LimitBreaker() {
        super("REME_LimitBreaker", ImageMaster.loadImage("img/relics/LimitBreaker.png"),
                RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new LimitBreaker();
    }
}
