package REMEMod.Relics.Uncommon;

import REMEMod.Patches.BottledRelicPatch.BottledField;
import basemod.BaseMod;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.ISubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

import java.util.ArrayList;
import java.util.function.Predicate;

public class BottledBoat extends CustomRelic implements ISubscriber, CustomSavable<Integer>, CustomBottleRelic {
    private boolean cardSelected = true;
    public AbstractCard card = null;

    public BottledBoat() {
        super("REME_BottledBoat", ImageMaster.loadImage("remeImg/relics/BottledBoat.png"),
                RelicTier.UNCOMMON, LandingSound.CLINK);
        BaseMod.subscribe(this);
        BaseMod.addSaveField("REME_BottledBoat", this);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = RoomPhase.INCOMPLETE;
        CardGroup group = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck);
        ArrayList<AbstractCard> abstractCards = group.group;
        for (int i = abstractCards.size() - 1; i >= 0; i--) {
            AbstractCard c = abstractCards.get(i);
            if (c.type != AbstractCard.CardType.POWER) {
                group.removeCard(c);
            }
        }
        if (group.size() > 0)
            AbstractDungeon.gridSelectScreen.open(group, 1, "", false, false, true, true);
    }

    public void onUnequip() {
        if (this.card != null) {
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(this.card);
            if (cardInDeck != null) {
                BottledField.inBottledBoat.set(cardInDeck, false);
            }
        }
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (BottledField.inBottledBoat.get(c) && c.type == AbstractCard.CardType.POWER) {
                        c.type = AbstractCard.CardType.SKILL;
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (BottledField.inBottledBoat.get(c) && c.type == AbstractCard.CardType.POWER) {
                        c.type = AbstractCard.CardType.SKILL;
                        c.flash();
                    }
                }
                isDone = true;
            }
        });

    }

    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.GRID) {
            this.cardSelected = true;
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                this.card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                BottledField.inBottledBoat.set(this.card, true);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
        }

    }

    public AbstractRelic makeCopy() {
        return new BottledBoat();
    }

    @Override
    public Predicate<AbstractCard> isOnCard() {
        return BottledField.inBottledBoat::get;
    }

    public Class<Integer> savedType() {
        return Integer.class;
    }

    public Integer onSave() {
        return this.card != null ? AbstractDungeon.player.masterDeck.group.indexOf(this.card) : -1;
    }

    public void onLoad(Integer index) {
        if (index != null && index >= 0 && index < AbstractDungeon.player.masterDeck.group.size()) {
            this.card = AbstractDungeon.player.masterDeck.group.get(index);
            BottledField.inBottledBoat.set(this.card, true);
        }
    }
}
