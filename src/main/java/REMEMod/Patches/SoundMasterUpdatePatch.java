package REMEMod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.SoundInfo;
import com.megacrit.cardcrawl.audio.SoundMaster;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.audio.SoundMaster",
        method = "update"
)
public class SoundMasterUpdatePatch {

    public SoundMasterUpdatePatch() {
    }

    @SpireInsertPatch(
            rloc = 4,
            localvars = {"e", "sfx"}
    )
    public static void Insert(SoundMaster _inst, SoundInfo e, @ByRef(type = "com.megacrit.cardcrawl.audio.Sfx") Object[] sfx) {
        if (sfx[0] == null) {
            sfx[0] = SoundMasterplayPatch.map.get(e.name);
        }

    }
}
