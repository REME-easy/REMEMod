package REMEMod.Relics.Boss;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DuelDisc extends CustomRelic {

    public DuelDisc() {
        super("REME_DuelDisc", ImageMaster.loadImage("remeImg/relics/DuelDisc.png"),
                RelicTier.BOSS, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        super.atBattleStart();
        this.addToBot(new DrawCardAction(8));
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, 1), 1));
    }

    public void onEquip() {
        AbstractDungeon.player.masterHandSize = 3;
    }

    public void onUnequip() {
        AbstractDungeon.player.masterHandSize = 5;
    }

    public AbstractRelic makeCopy() {
        return new DuelDisc();
    }
}
