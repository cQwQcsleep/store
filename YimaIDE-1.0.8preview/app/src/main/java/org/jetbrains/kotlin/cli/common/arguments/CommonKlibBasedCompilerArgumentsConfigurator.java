package org.jetbrains.kotlin.cli.common.arguments;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.KlibIrInlinerMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.PartialLinkageLogLevel;
import org.jetbrains.kotlin.config.WarningLevel;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J0\u0010\b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J,\u0010\u0010\u001a\u00020\u0011*\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\u0006\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0002J0\u0010\u0013\u001a\u00020\u0011*\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\t2\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0002J<\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\"\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bj\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d`\u001e2\u0006\u0010\f\u001a\u00020\rH\u0014¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArgumentsConfigurator;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "<init>", "()V", "isSecondStage", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "configureAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "addPartialLinkageToWarningLevelMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "putPartialLinkageIssueWarningLevel", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/WarningLevel;", "isMinorIssue", "logLevel", "Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", "configureExtraLanguageFeatures", "map", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "Lkotlin/collections/HashMap;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CommonKlibBasedCompilerArgumentsConfigurator extends CommonCompilerArgumentsConfigurator {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PartialLinkageLogLevel.values().length];
            try {
                iArr[PartialLinkageLogLevel.SILENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PartialLinkageLogLevel.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PartialLinkageLogLevel.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PartialLinkageLogLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[KlibIrInlinerMode.values().length];
            try {
                iArr2[KlibIrInlinerMode.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[KlibIrInlinerMode.INTRA_MODULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[KlibIrInlinerMode.FULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[KlibIrInlinerMode.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private final void addPartialLinkageToWarningLevelMap(Map<AnalysisFlag<?>, Object> map, CommonKlibBasedCompilerArguments commonKlibBasedCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        String partialLinkageLogLevel = commonKlibBasedCompilerArguments.getPartialLinkageLogLevel();
        PartialLinkageLogLevel partialLinkageLogLevelResolveLogLevel = partialLinkageLogLevel != null ? PartialLinkageLogLevel.INSTANCE.resolveLogLevel(partialLinkageLogLevel) : PartialLinkageLogLevel.INSTANCE.getDEFAULT();
        if (partialLinkageLogLevelResolveLogLevel == null) {
            reporter.reportError("Unknown value for parameter -Xpartial-linkage-loglevel: '" + partialLinkageLogLevel + "'. Value should be one of " + PartialLinkageLogLevel.INSTANCE.availableValues());
            return;
        }
        AnalysisFlags analysisFlags = AnalysisFlags.INSTANCE;
        Object obj = map.get(analysisFlags.getWarningLevels());
        Map mapEmptyMap = obj instanceof Map ? (Map) obj : null;
        if (mapEmptyMap == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        Map<String, WarningLevel> mutableMap = MapsKt.toMutableMap(mapEmptyMap);
        putPartialLinkageIssueWarningLevel(mutableMap, true, partialLinkageLogLevelResolveLogLevel, reporter);
        putPartialLinkageIssueWarningLevel(mutableMap, false, partialLinkageLogLevelResolveLogLevel, reporter);
        putAnalysisFlag(map, analysisFlags.getWarningLevels(), mutableMap);
    }

    private final void putPartialLinkageIssueWarningLevel(Map<String, WarningLevel> map, boolean z, PartialLinkageLogLevel partialLinkageLogLevel, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        WarningLevel warningLevel;
        int i = WhenMappings.$EnumSwitchMapping$0[partialLinkageLogLevel.ordinal()];
        if (i == 1) {
            warningLevel = WarningLevel.Disabled;
        } else {
            if (i == 2) {
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    bu8.a();
                    return;
                }
                warningLevel = WarningLevel.Error;
            } else if (!z) {
                return;
            } else {
                warningLevel = WarningLevel.Disabled;
            }
        }
        String strConcat = (z ? "MINOR" : "MAJOR").concat("_PARTIAL_LINKAGE_ISSUE");
        if (map.put(strConcat, warningLevel) != null) {
            reporter.reportError("Severity of " + strConcat + " is configured both with -Xpartial-linkage-loglevel and -Xwarning-level or -Xsuppress-warning flags");
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter, LanguageVersion languageVersion) {
        arguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        if (!(arguments instanceof CommonKlibBasedCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<AnalysisFlag<?>, Object> mapConfigureAnalysisFlags = super.configureAnalysisFlags(arguments, reporter, languageVersion);
        if (isSecondStage(arguments)) {
            addPartialLinkageToWarningLevelMap(mapConfigureAnalysisFlags, (CommonKlibBasedCompilerArguments) arguments, reporter);
        }
        return mapConfigureAnalysisFlags;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public void configureExtraLanguageFeatures(CommonCompilerArguments arguments, HashMap<LanguageFeature, LanguageFeature.State> map, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        arguments.getClass();
        map.getClass();
        reporter.getClass();
        if (!(arguments instanceof CommonKlibBasedCompilerArguments)) {
            w01.a("Failed requirement.");
            return;
        }
        KlibIrInlinerMode.Companion companion = KlibIrInlinerMode.INSTANCE;
        CommonKlibBasedCompilerArguments commonKlibBasedCompilerArguments = (CommonKlibBasedCompilerArguments) arguments;
        KlibIrInlinerMode klibIrInlinerModeFromString = companion.fromString(commonKlibBasedCompilerArguments.getIrInlinerBeforeKlibSerialization());
        int i = klibIrInlinerModeFromString == null ? -1 : WhenMappings.$EnumSwitchMapping$1[klibIrInlinerModeFromString.ordinal()];
        if (i == -1) {
            reporter.reportError("Unknown value for parameter -Xklib-ir-inliner: '" + commonKlibBasedCompilerArguments.getIrInlinerBeforeKlibSerialization() + "'. Value should be one of " + companion.availableValues());
            return;
        }
        if (i != 1) {
            if (i == 2) {
                map.put(LanguageFeature.IrIntraModuleInlinerBeforeKlibSerialization, LanguageFeature.State.ENABLED);
                map.put(LanguageFeature.IrCrossModuleInlinerBeforeKlibSerialization, LanguageFeature.State.DISABLED);
                return;
            }
            if (i == 3) {
                LanguageFeature languageFeature = LanguageFeature.IrIntraModuleInlinerBeforeKlibSerialization;
                LanguageFeature.State state = LanguageFeature.State.ENABLED;
                map.put(languageFeature, state);
                map.put(LanguageFeature.IrCrossModuleInlinerBeforeKlibSerialization, state);
                reporter.info("`-Xklib-ir-inliner=full` will trigger setting the `pre-release` flag for the compiled library.");
                return;
            }
            if (i != 4) {
                bu8.a();
                return;
            }
            LanguageFeature languageFeature2 = LanguageFeature.IrIntraModuleInlinerBeforeKlibSerialization;
            LanguageFeature.State state2 = LanguageFeature.State.DISABLED;
            map.put(languageFeature2, state2);
            map.put(LanguageFeature.IrCrossModuleInlinerBeforeKlibSerialization, state2);
        }
    }

    public abstract boolean isSecondStage(CommonCompilerArguments arguments);
}
