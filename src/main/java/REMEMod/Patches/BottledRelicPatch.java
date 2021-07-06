package REMEMod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BottledRelicPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "<class>"
    )
    public static class BottledField {
        public static SpireField<Boolean> inBottledBoat = new SpireField(() -> {
            return false;
        });
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy {
        public MakeStatEquivalentCopy() {
        }

        public static AbstractCard Postfix(AbstractCard result, AbstractCard self) {
            BottledField.inBottledBoat.set(result, BottledField.inBottledBoat.get(self));
            return result;
        }
    }
}
