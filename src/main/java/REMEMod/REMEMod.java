package REMEMod;

import REMEMod.Cards.Blue.Attack.DataAnalysis;
import REMEMod.Cards.Blue.Attack.ForAll;
import REMEMod.Cards.Blue.Attack.Shoggoth;
import REMEMod.Cards.Blue.Power.SelfUpgrade;
import REMEMod.Cards.Blue.Skill.Compress;
import REMEMod.Cards.Blue.Skill.Whirling;
import REMEMod.Cards.Colorless.Attack.AllRoundStrike;
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
import REMEMod.Cards.Purple.Power.MusicOfDeath;
import REMEMod.Cards.Purple.Skill.CRH;
import REMEMod.Cards.Purple.Skill.EmptyShield;
import REMEMod.Cards.Purple.Skill.Repentance;
import REMEMod.Cards.Purple.Skill.WorshipGod;
import REMEMod.Cards.Red.Attack.ResidualAnger;
import REMEMod.Cards.Red.Attack.TheLastStrike;
import REMEMod.Cards.Red.Power.Cut;
import REMEMod.Cards.Red.Power.FearOfPain;
import REMEMod.Cards.Red.Skill.ACTH;
import REMEMod.Cards.Red.Skill.Fury;
import REMEMod.Cards.Red.Skill.Scribble;
import REMEMod.Cards.Red.Skill.SingleHolding;
import REMEMod.Helpers.SecondaryMagicVariable;
import REMEMod.Monsters.Revenger;
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
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class REMEMod implements EditRelicsSubscriber, EditStringsSubscriber, EditCardsSubscriber, PostInitializeSubscriber, EditKeywordsSubscriber, PostDungeonInitializeSubscriber, OnStartBattleSubscriber {
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

        BaseMod.addCard(new CurseOfMatryoshka());
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
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Localization/REMEPowers_" + lang + ".json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "Localization/REMEMonsters_" + lang + ".json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "Localization/REMEUI_" + lang + ".json");
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

    public void receivePostDungeonInitialize(){
        if(AbstractDungeon.getCurrRoom() != null){
            obtain(AbstractDungeon.player,new ComplimentaryCards(),false);
        }
    }

    public void receiveOnBattleStart(AbstractRoom var1){

    }

    public static boolean obtain(AbstractPlayer p, AbstractRelic r, boolean canDuplicate) {
        if (r == null) {
            return false;
        } else if (p.hasRelic(r.relicId) && !canDuplicate) {
            return false;
        } else {
            int slot = p.relics.size();
            r.makeCopy().instantObtain(p, slot, true);
            return true;
        }
    }
}

