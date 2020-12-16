package REMEMod.Relics.Common;

import REMEMod.Actions.MakeTempCardInHandSAction;
import REMEMod.Cards.Colorless.Skill.SutureCard;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;

public class HandOfSuture extends CustomRelic {

    public HandOfSuture() {
        super("REME_HandOfSuture", ImageMaster.loadImage("remeImg/relics/HandOfSuture.png"),
                RelicTier.COMMON, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        ArrayList<AbstractCard> cards = new ArrayList<>();

        AbstractCard card;
        do {
            int roll = AbstractDungeon.cardRandomRng.random(99);
            CardRarity cardRarity;
            if (roll < 55) {
                cardRarity = CardRarity.COMMON;
            } else if (roll < 85) {
                cardRarity = CardRarity.UNCOMMON;
            } else {
                cardRarity = CardRarity.RARE;
            }

            card = CardLibrary.getAnyColorCard(cardRarity).makeStatEquivalentCopy();
        } while(card.cost == -1);

        cards.add(card);
        AbstractCard topcard = cards.get(0);

        while(cards.size() != 2) {
            int roll = AbstractDungeon.cardRandomRng.random(99);
            CardRarity cardRarity;
            if (roll < 55) {
                cardRarity = CardRarity.COMMON;
            } else if (roll < 85) {
                cardRarity = CardRarity.UNCOMMON;
            } else {
                cardRarity = CardRarity.RARE;
            }

            AbstractCard card2 = CardLibrary.getAnyColorCard(cardRarity).makeStatEquivalentCopy();
            if (card2.cost != -1 && card2.cost + topcard.cost <= AbstractDungeon.player.energy.energyMaster) {
                cards.add(card);
            }
        }

        AbstractCard c = new SutureCard(cards);
        c.costForTurn = 1;
        this.addToBot(new MakeTempCardInHandSAction(c));
    }

    public AbstractRelic makeCopy() {
        return new HandOfSuture();
    }
}
