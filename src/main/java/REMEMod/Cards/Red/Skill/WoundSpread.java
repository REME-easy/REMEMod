package REMEMod.Cards.Red.Skill;

import REMEMod.Helpers.REMEHelper;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class WoundSpread extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("REME_WoundSpread");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static int postfix = 0;

    public WoundSpread() {
        super("REME_WoundSpread", NAME, "remeImg/cards/WoundSpread.png", 0, DESCRIPTION,
                CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.getPower(VulnerablePower.POWER_ID) != null && m.getPower(VulnerablePower.POWER_ID).amount > 1) {
                    int amt = m.getPower(VulnerablePower.POWER_ID).amount / 2;
                    int rest = m.getPower(VulnerablePower.POWER_ID).amount - amt;
                    this.addToBot(new ReducePowerAction(m, p, VulnerablePower.POWER_ID, amt));
                    AbstractPower po = new VulnerablePower(m, rest, false);
                    po.ID = VulnerablePower.POWER_ID + postfix++;
                    this.addToBot(new ApplyPowerAction(m, p, po));
                }
                this.isDone = true;
            }
        });
    }

    public AbstractCard makeCopy() {
        return new WoundSpread();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }

    }

    @SpirePatch(
            clz = VulnerablePower.class,
            method = "atEndOfRound"
    )
    public static class WoundSpreadPatch {
        public static SpireReturn<Void> Prefix(VulnerablePower _inst) {
            if (VulnerablePower.POWER_ID.equals(_inst.ID)) {
                return SpireReturn.Continue();
            }
            boolean justApplied = ReflectionHacks.getPrivate(_inst, VulnerablePower.class, "justApplied");
            if (justApplied) {
                ReflectionHacks.setPrivate(_inst, VulnerablePower.class, "justApplied", false);
            }else {
                REMEHelper.addToBot(new ReducePowerAction(_inst.owner, _inst.owner, _inst.ID, 1));
            }
            return SpireReturn.Return(null);
        }
    }

}
