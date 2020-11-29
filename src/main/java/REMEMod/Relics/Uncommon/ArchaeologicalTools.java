package REMEMod.Relics.Uncommon;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class ArchaeologicalTools extends CustomRelic implements CustomSavable<ArrayList> {

    public ArrayList<String> strings = new ArrayList<>();

    public ArchaeologicalTools() {
        super("REME_ArchaeologicalTools", ImageMaster.loadImage("img/relics/ArchaeologicalTools.png"),
                RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ArchaeologicalTools();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        super.onUseCard(targetCard, useCardAction);
        if(!strings.contains(targetCard.cardID)){
            strings.add(targetCard.cardID);
        }
    }

    @Override
    public void onLoad(ArrayList arrayList) {
        strings.addAll(arrayList);
    }

    @Override
    public ArrayList onSave() {
        return strings;
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "addToHand"
    )
    public static class ArchaeologicalToolsPatch{
        public ArchaeologicalToolsPatch(){}

        public static void Postfix(CardGroup _inst, AbstractCard c){
            if(AbstractDungeon.player.hasRelic("REME_ArchaeologicalTools")){
                ArchaeologicalTools tmp = (ArchaeologicalTools) AbstractDungeon.player.getRelic("REME_ArchaeologicalTools");
                if(!tmp.strings.contains(c.cardID)){
                    c.setCostForTurn(-9);
                    c.superFlash();
                    c.glowColor = Color.WHITE.cpy();
                    AbstractDungeon.player.gainGold(3);
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "addToTop"
    )
    public static class ArchaeologicalTools2Patch{
        public ArchaeologicalTools2Patch(){}

        public static void Postfix(CardGroup _inst, AbstractCard c){
            if(_inst.type == CardGroup.CardGroupType.HAND && AbstractDungeon.player.hasRelic("REME_ArchaeologicalTools")){
                ArchaeologicalTools tmp = (ArchaeologicalTools) AbstractDungeon.player.getRelic("REME_ArchaeologicalTools");
                if(!tmp.strings.contains(c.cardID)){
                    c.setCostForTurn(-9);
                    c.superFlash();
                    c.glowColor = Color.WHITE.cpy();
                    AbstractDungeon.player.gainGold(3);
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "addToBottom"
    )
    public static class ArchaeologicalTools3Patch{
        public ArchaeologicalTools3Patch(){}

        public static void Postfix(CardGroup _inst, AbstractCard c){
            if(_inst.type == CardGroup.CardGroupType.HAND && AbstractDungeon.player.hasRelic("REME_ArchaeologicalTools")){
                ArchaeologicalTools tmp = (ArchaeologicalTools) AbstractDungeon.player.getRelic("REME_ArchaeologicalTools");
                if(!tmp.strings.contains(c.cardID)){
                    c.setCostForTurn(-9);
                    c.superFlash();
                    c.glowColor = Color.WHITE.cpy();
                    AbstractDungeon.player.gainGold(3);
                }
            }
        }
    }
    @SpirePatch(
            clz = CardGroup.class,
            method = "addToRandomSpot"
    )
    public static class ArchaeologicalTools4Patch{
        public ArchaeologicalTools4Patch(){}

        public static void Postfix(CardGroup _inst, AbstractCard c){
            if(_inst.type == CardGroup.CardGroupType.HAND && AbstractDungeon.player.hasRelic("REME_ArchaeologicalTools")){
                ArchaeologicalTools tmp = (ArchaeologicalTools) AbstractDungeon.player.getRelic("REME_ArchaeologicalTools");
                if(!tmp.strings.contains(c.cardID)){
                    c.setCostForTurn(-9);
                    c.superFlash();
                    c.glowColor = Color.WHITE.cpy();
                    AbstractDungeon.player.gainGold(3);
                }
            }
        }
    }
}
