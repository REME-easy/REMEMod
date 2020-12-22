package REMEMod.Potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public abstract class AbstractREMEPotion extends AbstractPotion {

    public AbstractREMEPotion(String name, String id, PotionRarity rarity, PotionSize size, AbstractPotion.PotionColor color) {
        super(name, id, rarity, size, color);
        this.potency = this.getPotency();
        this.description = this.getDesc();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public abstract void use(AbstractCreature abstractCreature);

    protected abstract String getDesc();
}