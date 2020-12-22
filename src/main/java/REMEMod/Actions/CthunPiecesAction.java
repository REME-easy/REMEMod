package REMEMod.Actions;

import REMEMod.Cards.Colorless.Attack.CthunTheShattered;
import REMEMod.vfx.CthunAreaEffect;
import REMEMod.vfx.CthunPiecesEffect;
import REMEMod.vfx.FloatingCardEffect;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CthunPiecesAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(CthunPiecesAction.class);
    private AbstractCard card;

    public CthunPiecesAction(AbstractCard card) {
        this.card = card;
        this.duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            if (this.duration == DEFAULT_DURATION) {
                CardCrawlGame.sound.play("NECRONOMICON");
                AbstractPlayer p = AbstractDungeon.player;
                Vector2 pos = new Vector2(p.drawX - 260.0F, p.drawY + 200.0F);
                if(!CthunTheShattered.Pieces.contains(card.cardID)){
                    CthunTheShattered.Pieces.add(card.cardID);
                }
                logger.info("克苏恩碎片个数：" + CthunTheShattered.Pieces.size());
                for(int i = 0 ; i < 6 ; i++){
                    this.addToBot(new VFXAction(new CthunPiecesEffect(pos.x, pos.y,i * 60.0F + 20.0F, 1.5F)));
                }
                this.addToBot(new VFXAction(new CthunAreaEffect(pos.x, pos.y)));
                this.addToBot(new VFXAction(new FloatingCardEffect(new CthunTheShattered(),  CthunTheShattered.Pieces.size() + "/4", pos)));
                if(CthunTheShattered.Pieces.size() == 4){
                    Vector2 pos2 = new Vector2(Settings.WIDTH / 2, Settings.HEIGHT / 2);
                    for(int i = 0 ; i < 6 ; i++){
                        this.addToBot(new VFXAction(new CthunPiecesEffect(pos2.x, pos2.y,i * 60.0F + 20.0F, 1.5F)));
                    }
                    this.addToBot(new VFXAction(new CthunAreaEffect(pos2.x, pos2.y)));
                    this.addToBot(new VFXAction(new FloatingCardEffect(new CthunTheShattered(),CthunTheShattered.Pieces.size() + "/4", pos2)));
                    this.addToBot(new WaitAction(4.0F));
                    this.addToBot(new MakeTempCardInDrawPileAction(new CthunTheShattered(), 1, true, true));
                }

            }
            this.isDone = true;
        }
    }
}
