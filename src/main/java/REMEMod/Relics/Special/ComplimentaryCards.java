package REMEMod.Relics.Special;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.gridSelectScreen;

public class ComplimentaryCards extends CustomRelic {
    private boolean RclickStart = false;
    private boolean Rclick = false;
    private CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public ComplimentaryCards() {
        super("REME_ComplimentaryCards", ImageMaster.loadImage("img/relics/ComplimentaryCards.png"),
                RelicTier.SPECIAL, LandingSound.FLAT);
        this.counter = 1;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    private void onRightClick(){
        if(this.counter == 1){
            this.counter = -1;
            AbstractCard card;
            do{
                int roll = AbstractDungeon.cardRandomRng.random(99);
                CardRarity cardRarity;
                if (roll < 1) {
                    cardRarity = CardRarity.COMMON;
                } else if (roll < 35) {
                    cardRarity = CardRarity.UNCOMMON;
                } else {
                    cardRarity = CardRarity.RARE;
                }

                card = CardLibrary.getAnyColorCard(cardRarity).makeStatEquivalentCopy();
                boolean canadd = true;
                for(AbstractCard c:tmp.group){
                    if(c.cardID.equals(card.cardID)){
                        canadd = false;
                    }
                }
                if(canadd)
                    tmp.group.add(card);
            }while(tmp.size() != 5);
            if (!AbstractDungeon.isScreenUp) {
                AbstractDungeon.gridSelectScreen.open(tmp, 1, false, this.DESCRIPTIONS[1]);
            } else {
                AbstractDungeon.dynamicBanner.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.screen;
                AbstractDungeon.gridSelectScreen.open(tmp, 1, false, this.DESCRIPTIONS[1]);
            }
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
        if (this.counter == -1 && tmp.group != null && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.counter = -2;

            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(gridSelectScreen.selectedCards.get(0), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

            this.tmp.clear();

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    public AbstractRelic makeCopy() {
        return new ComplimentaryCards();
    }
}
