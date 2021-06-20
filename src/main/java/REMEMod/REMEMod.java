package REMEMod;

import REMEMod.Cards.Blue.Attack.DataAnalysis;
import REMEMod.Cards.Blue.Attack.ForAll;
import REMEMod.Cards.Blue.Attack.Shoggoth;
import REMEMod.Cards.Blue.Power.SelfUpgrade;
import REMEMod.Cards.Blue.Skill.Compress;
import REMEMod.Cards.Blue.Skill.CreativeMind;
import REMEMod.Cards.Blue.Skill.Whirling;
import REMEMod.Cards.Colorless.Attack.AllRoundStrike;
import REMEMod.Cards.Colorless.Attack.CthunTheShattered;
import REMEMod.Cards.Colorless.Attack.EyeOfCthun;
import REMEMod.Cards.Colorless.Attack.HeartOfCthun;
import REMEMod.Cards.Colorless.Power.AccumulateSteadily;
import REMEMod.Cards.Colorless.Power.BattleFury;
import REMEMod.Cards.Colorless.Power.FirmDetermination;
import REMEMod.Cards.Colorless.Power.Flogging;
import REMEMod.Cards.Colorless.Skill.*;
import REMEMod.Cards.Curses.CurseOfMatryoshka;
import REMEMod.Cards.Green.Attack.GrazeFinale;
import REMEMod.Cards.Green.Attack.ShadowStrike;
import REMEMod.Cards.Green.Attack.SpectralBlade;
import REMEMod.Cards.Green.Power.GreenPollution;
import REMEMod.Cards.Green.Power.ShadowForm;
import REMEMod.Cards.Green.Skill.CopyPotion;
import REMEMod.Cards.Green.Skill.LeechingPoison;
import REMEMod.Cards.Green.Skill.Seeding;
import REMEMod.Cards.Purple.Power.MusicOfDeath;
import REMEMod.Cards.Purple.Skill.*;
import REMEMod.Cards.Red.Attack.NightmareStrike;
import REMEMod.Cards.Red.Attack.ResidualAnger;
import REMEMod.Cards.Red.Attack.TheLastStrike;
import REMEMod.Cards.Red.Power.Cut;
import REMEMod.Cards.Red.Power.FearOfPain;
import REMEMod.Cards.Red.Skill.*;
import REMEMod.Helpers.REMEHelper;
import REMEMod.Helpers.SecondaryMagicVariable;
import REMEMod.Monsters.Revenger;
import REMEMod.Patches.IncrementDiscardPatch;
import REMEMod.Potions.GoldPotion;
import REMEMod.Relics.Boss.ChargedStone;
import REMEMod.Relics.Boss.DemonsWealth;
import REMEMod.Relics.Boss.DuelDisc;
import REMEMod.Relics.Boss.LowDesire;
import REMEMod.Relics.Common.HandOfSuture;
import REMEMod.Relics.Common.PuzzleBoxRelic;
import REMEMod.Relics.Rare.IWantAll;
import REMEMod.Relics.Shop.CardOfGreed;
import REMEMod.Relics.Shop.OnlineShoppingPlatform;
import REMEMod.Relics.Special.ComplimentaryCards;
import REMEMod.Relics.Uncommon.ArchaeologicalTools;
import REMEMod.Relics.Uncommon.HourglassOfDeath;
import REMEMod.Relics.Uncommon.LimitBreaker;
import REMEMod.Relics.Uncommon.PoisonedDagger;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SpireInitializer
public class REMEMod implements EditRelicsSubscriber, EditStringsSubscriber, EditCardsSubscriber, PostInitializeSubscriber, EditKeywordsSubscriber, OnStartBattleSubscriber {
    private static final Logger logger = LogManager.getLogger(REMEMod.class);

    public REMEMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new REMEMod();

        BaseMod.addDynamicVariable(new SecondaryMagicVariable());
    }

    public void receiveEditCards() {
        BaseMod.addCard(new ProfessionalPlagiarism());
        BaseMod.addCard(new SingleHolding());
        BaseMod.addCard(new FearOfPain());
        BaseMod.addCard(new Scribble());
        BaseMod.addCard(new ForAll());
        BaseMod.addCard(new TheLastStrike());
        BaseMod.addCard(new GreenPollution());
        BaseMod.addCard(new ShadowForm());
        BaseMod.addCard(new ShadowStrike());
        BaseMod.addCard(new DataAnalysis());
        BaseMod.addCard(new BuildACard());
        BaseMod.addCard(new WorshipGod());
        BaseMod.addCard(new AllRoundStrike());
        BaseMod.addCard(new PuzzleBox());
        BaseMod.addCard(new GrazeFinale());
        BaseMod.addCard(new ShortnessOfBreath());
        BaseMod.addCard(new AllRoundStrike());
        BaseMod.addCard(new Cut());
        BaseMod.addCard(new Shoggoth());
        BaseMod.addCard(new TwoStrings());
        BaseMod.addCard(new Flogging());
        BaseMod.addCard(new CopyPotion());
        BaseMod.addCard(new FirmDetermination());
        BaseMod.addCard(new ACTH());
        BaseMod.addCard(new CRH());
        BaseMod.addCard(new Repentance());
        BaseMod.addCard(new BattleFury());
        BaseMod.addCard(new Whirling());
        BaseMod.addCard(new MagicPocketWatch());
        BaseMod.addCard(new MusicOfDeath());
        BaseMod.addCard(new ResidualAnger());
        BaseMod.addCard(new Fury());
        BaseMod.addCard(new Compress());
        BaseMod.addCard(new SpectralBlade());
        BaseMod.addCard(new TheoryOfElimination());
        BaseMod.addCard(new EmptyShield());
        BaseMod.addCard(new SelfUpgrade());
        BaseMod.addCard(new WheelOfFate());
        BaseMod.addCard(new CthunTheShattered());
        BaseMod.addCard(new EyeOfCthun());
        BaseMod.addCard(new MawOfCthun());
        BaseMod.addCard(new HeartOfCthun());
        BaseMod.addCard(new BodyOfCthun());
        BaseMod.addCard(new LeechingPoison());
        BaseMod.addCard(new CreativeMind());
        BaseMod.addCard(new WoundSpread());
        BaseMod.addCard(new AccumulateSteadily());
        BaseMod.addCard(new Interweave());
        BaseMod.addCard(new NightmareStrike());
        BaseMod.addCard(new Seeding());

        BaseMod.addCard(new CurseOfMatryoshka());
        receiveEditPotions();
    }

    private void receiveEditPotions() {
        Object[] potions = new Object[]{GoldPotion.class};
        Object[] var4 = potions;
        int var3 = potions.length;

        for(int var2 = 0; var2 < var3; ++var2) {
            Object o = var4[var2];
            Class c = (Class)o;

            try {
                BaseMod.addPotion(c, null, null, null, (String)c.getField("POTION_ID").get(null));
            } catch (IllegalAccessException | NoSuchFieldException | SecurityException | IllegalArgumentException var7) {
                var7.printStackTrace();
            }
        }
    }

    public void receiveEditRelics() {
        BaseMod.addRelic(new HandOfSuture(), RelicType.SHARED);
        BaseMod.addRelic(new ComplimentaryCards(), RelicType.SHARED);
        BaseMod.addRelic(new DuelDisc(), RelicType.SHARED);
        BaseMod.addRelic(new PuzzleBoxRelic(), RelicType.SHARED);
        BaseMod.addRelic(new LimitBreaker(), RelicType.SHARED);
        BaseMod.addRelic(new DemonsWealth(), RelicType.SHARED);
        BaseMod.addRelic(new CardOfGreed(), RelicType.SHARED);
        BaseMod.addRelic(new HourglassOfDeath(), RelicType.SHARED);
        BaseMod.addRelic(new OnlineShoppingPlatform(), RelicType.SHARED);
        BaseMod.addRelic(new ArchaeologicalTools(), RelicType.SHARED);
        BaseMod.addRelic(new LowDesire(), RelicType.SHARED);
        BaseMod.addRelic(new IWantAll(), RelicType.SHARED);
        BaseMod.addRelic(new ChargedStone(), RelicType.SHARED);

        BaseMod.addRelic(new PoisonedDagger(), RelicType.GREEN);
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == GameLanguage.ZHS) {
            lang = "zh";
        } else {
            lang = "eng";
        }
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Localization/REMERelics_" + lang + ".json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "Localization/REMECards_" + lang + ".json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "Localization/REMEPotion_" + lang + ".json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Localization/REMEPowers_" + lang + ".json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "Localization/REMEMonsters_" + lang + ".json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "Localization/REMEUI_" + lang + ".json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, "Localization/REMEOrb_" + lang + ".json");
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "eng";
        if (Settings.language == GameLanguage.ENG) {
            lang = "eng";
        } else if (Settings.language == GameLanguage.ZHS) {
            lang = "zh";
        }

        logger.info("begin editing strings");
        String json = Gdx.files.internal("Localization/REMEKeywords_" + lang + ".json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (gson.fromJson(json, Keyword[].class));
        if (keywords != null) {
            int var7 = keywords.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Keyword keyword = keywords[var8];
                BaseMod.addKeyword(keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }

    }

    public void receivePostInitialize() {
        receiveEditMonsters();
    }

    private void receiveEditMonsters(){
        BaseMod.addMonster("REME_1_Revenger", Revenger.NAME, () -> {
            return new Revenger(0.0F, 0.0F);
        });
        BaseMod.addEliteEncounter("TheCity", new MonsterInfo("REME_1_Revenger", 1.0F));
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {String.class, String.class, AbstractPlayer.class, ArrayList.class}
    )
    public static class GameStartPatch {
        public static void Postfix(AbstractDungeon _inst, String name, String levelId, AbstractPlayer p, ArrayList<String> newSpecialOneTimeEventList) {
            if (AbstractDungeon.id.equals("Exordium")) {
                REMEHelper.GainRelic(new ComplimentaryCards());
            }
        }
    }

    public void receiveOnBattleStart(AbstractRoom var1){
        IncrementDiscardPatch.DiscardCardsThisBattle = 0;
        CthunTheShattered.Pieces.clear();
    }
}

