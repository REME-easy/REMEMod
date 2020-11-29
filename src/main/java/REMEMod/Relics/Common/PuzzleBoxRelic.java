package REMEMod.Relics.Common;

import REMEMod.Actions.PlayACardAction;
import REMEMod.vfx.BorderFlashAlwaysEffect;
import REMEMod.vfx.PuzzleBoxOrbEffect;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PuzzleBoxRelic extends CustomRelic {

    public PuzzleBoxRelic() {
        super("REME_PuzzleBoxRelic", ImageMaster.loadImage("img/relics/PuzzleBoxRelic.png"),
                RelicTier.COMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        int i;
        AbstractDungeon.effectsQueue.add(new BorderFlashAlwaysEffect(new Color(1.0F, 0.2F, 1.0F, 1.0F),true, 2.0F));
        for(i = 0 ; i < 36 ; i++){
            AbstractDungeon.effectsQueue.add(new PuzzleBoxOrbEffect(Settings.WIDTH / 2, Settings.HEIGHT / 2, i));
        }
        int roll = AbstractDungeon.cardRandomRng.random(99);
        AbstractCard.CardRarity cardRarity;
        if (roll < 30) {
            cardRarity = AbstractCard.CardRarity.COMMON;
        } else if (roll < 65) {
            cardRarity = AbstractCard.CardRarity.UNCOMMON;
        } else if (roll < 95){
            cardRarity = AbstractCard.CardRarity.RARE;
        } else {
            cardRarity = AbstractCard.CardRarity.CURSE;
        }
        AbstractCard tmp;
        if(cardRarity == AbstractCard.CardRarity.CURSE){
            tmp = CardLibrary.getCurse();
        }else{
            tmp = CardLibrary.getAnyColorCard(cardRarity);
        }
        this.addToBot(new PlayACardAction(tmp.makeCopy()));
    }

    public AbstractRelic makeCopy() {
        return new PuzzleBoxRelic();
    }
}
