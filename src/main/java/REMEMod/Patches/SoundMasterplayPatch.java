package REMEMod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;

import java.util.HashMap;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.audio.SoundMaster",
        method = "play",
        paramtypes = {"java.lang.String", "boolean"}
)
public class SoundMasterplayPatch {
    public static HashMap<String, Sfx> map = new HashMap<>();

    public SoundMasterplayPatch() {
    }

    public static long Postfix(long res, SoundMaster _inst, String key, boolean useBgmVolume) {
        if (map.containsKey(key)) {
            return useBgmVolume ? (map.get(key)).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME) : (map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        } else {
            return res;
        }
    }

    private static Sfx load(String filename) {
        return new Sfx("audios/" + filename, false);
    }

    static {
        map.put("graze_finale", load("reme_graze_finale.ogg"));
    }
}
