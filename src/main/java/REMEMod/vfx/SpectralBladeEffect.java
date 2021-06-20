package REMEMod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SpectralBladeEffect extends AbstractGameEffect {

    private ParticleEffect pe = new ParticleEffect();
    private static final String PATH = "remeImg/vfx/test";

    public SpectralBladeEffect(float x, float y) {
        pe.load(Gdx.files.internal(PATH), Gdx.files.internal(""));
        pe.getEmitters().first().setPosition(x, y);
        pe.start();
        this.duration = 0.5F;
    }

    @Override
    public void update() {
        super.update();
        if(pe.isComplete())
            this.isDone = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(0, 0, 0, 1);
        pe.draw(sb);
    }

    @Override
    public void dispose() {

    }
}
