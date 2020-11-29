package REMEMod.Helpers;

import basemod.abstracts.DynamicVariable;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondaryMagicVariable extends DynamicVariable {
    public SecondaryMagicVariable() {
    }

    public String key() {
        return "RSM2";
    }

    public boolean isModified(AbstractCard card) {
        return SMFields.SMagicModified.get(card);
    }

    public int value(AbstractCard card) {
        return SMFields.sMagicNum.get(card);
    }

    public int baseValue(AbstractCard card) {
        return SMFields.baseSMagicNum.get(card);
    }

    public boolean upgraded(AbstractCard card) {
        return SMFields.SMagicUpgraded.get(card);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "<class>"
    )
    public static class SMFields {
        public static SpireField<Integer> baseSMagicNum = new SpireField(() -> {
            return -1;
        });
        public static SpireField<Integer> sMagicNum = new SpireField(() -> {
            return -1;
        });
        public static SpireField<Boolean> SMagicModified = new SpireField(() -> {
            return false;
        });
        public static SpireField<Boolean> SMagicUpgraded = new SpireField(() -> {
            return false;
        });

        public SMFields() {
        }
    }
}
