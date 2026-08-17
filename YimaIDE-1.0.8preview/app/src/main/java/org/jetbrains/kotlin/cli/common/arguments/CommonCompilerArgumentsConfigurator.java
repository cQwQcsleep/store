package org.jetbrains.kotlin.cli.common.arguments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.ExplicitApiMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageFeatureBehaviorAfterSinceVersion;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;
import org.jetbrains.kotlin.config.WarningLevel;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J0\u0010\r\u001a\u00020\u000e*\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u00052\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0010\u001a\u00020\u0001H\u0004J$\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J<\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\"\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0016j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013`\u00172\u0006\u0010\t\u001a\u00020\nH\u0014J8\u0010\u0018\u001a\u00020\u000e*\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0016j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013`\u00172\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J@\u0010\u0019\u001a\u00020\u000e*&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0016j\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0001`\u00172\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", Argument.Delimiters.none, "<init>", "()V", "configureAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "putAnalysisFlag", Argument.Delimiters.none, "flag", "value", "configureLanguageFeatures", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "configureExtraLanguageFeatures", "map", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "configureLanguageFeaturesFromInternalArgs", "fillWarningLevelMap", "Reporter", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class CommonCompilerArgumentsConfigurator {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", Argument.Delimiters.none, "reportWarning", Argument.Delimiters.none, "message", Argument.Delimiters.none, "reportError", "report", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "info", "withLanguageVersionSettings", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "DoNothing", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Reporter {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter$Companion;", Argument.Delimiters.none, "<init>", "()V", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter$DoNothing;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "<init>", "()V", "reportWarning", Argument.Delimiters.none, "message", Argument.Delimiters.none, "reportError", "report", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "info", "withLanguageVersionSettings", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class DoNothing implements Reporter {
            public static final DoNothing INSTANCE = new DoNothing();

            private DoNothing() {
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void info(String message) {
                message.getClass();
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void report(KtSourcelessDiagnosticFactory factory, String message) {
                factory.getClass();
                message.getClass();
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void reportError(String message) {
                message.getClass();
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public void reportWarning(String message) {
                message.getClass();
            }

            @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator.Reporter
            public Reporter withLanguageVersionSettings(LanguageVersionSettings languageVersionSettings) {
                languageVersionSettings.getClass();
                return this;
            }
        }

        void info(String message);

        void report(KtSourcelessDiagnosticFactory factory, String message);

        void reportError(String message);

        void reportWarning(String message);

        Reporter withLanguageVersionSettings(LanguageVersionSettings languageVersionSettings);
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LanguageFeature.values().length];
            try {
                iArr[LanguageFeature.SamConversionPerArgument.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LanguageFeature.FunctionReferenceWithDefaultValueAsOtherType.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final void configureLanguageFeaturesFromInternalArgs(HashMap<LanguageFeature, LanguageFeature.State> map, CommonCompilerArguments commonCompilerArguments, Reporter reporter) {
        LanguageVersion sinceVersion;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        boolean z2 = false;
        for (ManualLanguageFeatureSetting manualLanguageFeatureSetting : commonCompilerArguments.getInternalArguments()) {
            LanguageFeature languageFeature = manualLanguageFeatureSetting.getLanguageFeature();
            LanguageFeature.State state = manualLanguageFeatureSetting.getState();
            map.put(languageFeature, state);
            if (state == LanguageFeature.State.ENABLED && LanguageVersionSettingsKt.forcesPreReleaseBinariesIfEnabled(languageFeature)) {
                arrayList.add(languageFeature);
            }
            if (state == LanguageFeature.State.DISABLED && (sinceVersion = languageFeature.getSinceVersion()) != null && sinceVersion.isUnsupported() && Intrinsics.areEqual(languageFeature.getBehaviorAfterSinceVersion(), LanguageFeatureBehaviorAfterSinceVersion.CannotBeDisabled.INSTANCE)) {
                arrayList2.add(languageFeature);
            }
            int i = WhenMappings.$EnumSwitchMapping$0[languageFeature.ordinal()];
            if (i == 1) {
                z = true;
            } else if (i == 2) {
                z2 = true;
            }
        }
        LanguageFeature.State state2 = map.get(LanguageFeature.NewInference);
        LanguageFeature.State state3 = LanguageFeature.State.ENABLED;
        if (state2 == state3) {
            if (!z) {
                map.put(LanguageFeature.SamConversionPerArgument, state3);
            }
            if (!z2) {
                map.put(LanguageFeature.FunctionReferenceWithDefaultValueAsOtherType, state3);
            }
            map.put(LanguageFeature.DisableCompatibilityModeForNewInference, state3);
        }
        boolean z3 = map.get(LanguageFeature.IrCrossModuleInlinerBeforeKlibSerialization) == state3;
        boolean z4 = map.get(LanguageFeature.IrIntraModuleInlinerBeforeKlibSerialization) == state3;
        if (z3 && !z4) {
            reporter.reportError("-XXLanguage:+IrCrossModuleInlinerBeforeKlibSerialization requires -XXLanguage:+IrIntraModuleInlinerBeforeKlibSerialization. Enable the intra-module inliner as well to avoid inconsistent configuration.");
        }
        if (!arrayList.isEmpty()) {
            reporter.reportWarning("Following manually enabled features will force generation of pre-release binaries: " + CollectionsKt.joinToString$default(arrayList, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        reporter.reportError("The following features cannot be disabled manually, because the version they first appeared in is no longer supported:\n" + CollectionsKt.joinToString$default(arrayList2, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }

    private final void fillWarningLevelMap(HashMap<AnalysisFlag<?>, Object> map, CommonCompilerArguments commonCompilerArguments, Reporter reporter) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        String[] suppressedDiagnostics = commonCompilerArguments.getSuppressedDiagnostics();
        if (suppressedDiagnostics == null) {
            suppressedDiagnostics = new String[0];
        }
        for (String str : suppressedDiagnostics) {
            mapCreateMapBuilder.put(str, WarningLevel.Disabled);
        }
        if (!(suppressedDiagnostics.length == 0)) {
            reporter.reportWarning("Argument \"-Xsuppress-warning\" is deprecated. Use \"" + ("-Xwarning-level=" + ((String) ArraysKt.first(suppressedDiagnostics)) + ":disabled") + "\" instead" + (suppressedDiagnostics.length > 1 ? " (and the same for other warnings)" : Argument.Delimiters.none));
        }
        String[] warningLevels = commonCompilerArguments.getWarningLevels();
        if (warningLevels == null) {
            warningLevels = new String[0];
        }
        for (String str2 : warningLevels) {
            List listSplit$default = StringsKt.split$default(str2, new String[]{":"}, false, 2, 2, (Object) null);
            if (listSplit$default.size() < 2) {
                reporter.reportError("Invalid argument for -Xwarning-level=" + str2);
            } else {
                String str3 = (String) listSplit$default.get(0);
                String str4 = (String) listSplit$default.get(1);
                WarningLevel warningLevelFromString = WarningLevel.INSTANCE.fromString(str4);
                if (warningLevelFromString == null) {
                    reporter.reportError("Incorrect value for warning level: " + str4 + ". Available values are: " + CollectionsKt.joinToString$default(WarningLevel.getEntries(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: o72
                        public final Object invoke(Object obj) {
                            return CommonCompilerArgumentsConfigurator.fillWarningLevelMap$lambda$0$1$0((WarningLevel) obj);
                        }
                    }, 31, (Object) null));
                } else if (((WarningLevel) mapCreateMapBuilder.put(str3, warningLevelFromString)) != null) {
                    reporter.reportError(ArraysKt.contains(suppressedDiagnostics, str3) ? "Severity of " + str3 + " is configured both with -Xwarning-level and -Xsuppress-warning flags" : "-Xwarning-level is duplicated for warning " + str3);
                }
            }
        }
        putAnalysisFlag(map, AnalysisFlags.INSTANCE.getWarningLevels(), MapsKt.build(mapCreateMapBuilder));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence fillWarningLevelMap$lambda$0$1$0(WarningLevel warningLevel) {
        warningLevel.getClass();
        return warningLevel.getCliOption();
    }

    public Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments arguments, Reporter reporter, LanguageVersion languageVersion) {
        arguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        HashMap<AnalysisFlag<?>, Object> map = new HashMap<>();
        putAnalysisFlag(map, AnalysisFlags.getSkipMetadataVersionCheck(), Boolean.valueOf(arguments.getSkipMetadataVersionCheck()));
        putAnalysisFlag(map, AnalysisFlags.getSkipPrereleaseCheck(), Boolean.valueOf(arguments.getSkipPrereleaseCheck() || arguments.getSkipMetadataVersionCheck()));
        putAnalysisFlag(map, AnalysisFlags.getMultiPlatformDoNotCheckActual(), Boolean.valueOf(arguments.getNoCheckActual()));
        AnalysisFlag<?> optIn = AnalysisFlags.getOptIn();
        String[] optIn2 = arguments.getOptIn();
        Object list = optIn2 != null ? ArraysKt.toList(optIn2) : null;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        putAnalysisFlag(map, optIn, list);
        putAnalysisFlag(map, AnalysisFlags.getSkipExpectedActualDeclarationChecker(), Boolean.valueOf(arguments.getMetadataKlib()));
        putAnalysisFlag(map, AnalysisFlags.getExplicitApiVersion(), Boolean.valueOf(arguments.getApiVersion() != null));
        ExplicitApiMode.Companion companion = ExplicitApiMode.INSTANCE;
        Object objFromString = companion.fromString(arguments.getExplicitApi());
        if (objFromString != null) {
            putAnalysisFlag(map, AnalysisFlags.getExplicitApiMode(), objFromString);
        } else {
            reporter.reportError("Unknown value for parameter -Xexplicit-api: '" + arguments.getExplicitApi() + "'. Value should be one of " + companion.availableValues());
            Unit unit = Unit.INSTANCE;
        }
        Object objFromString2 = companion.fromString(arguments.getExplicitReturnTypes());
        if (objFromString2 != null) {
            putAnalysisFlag(map, AnalysisFlags.getExplicitReturnTypes(), objFromString2);
        } else {
            reporter.reportError("Unknown value for parameter -XXexplicit-return-types: '" + arguments.getExplicitReturnTypes() + "'. Value should be one of " + companion.availableValues());
            Unit unit2 = Unit.INSTANCE;
        }
        putAnalysisFlag(map, AnalysisFlags.getAllowKotlinPackage(), Boolean.valueOf(arguments.getAllowKotlinPackage()));
        putAnalysisFlag(map, AnalysisFlags.getStdlibCompilation(), Boolean.valueOf(arguments.getStdlibCompilation()));
        putAnalysisFlag(map, AnalysisFlags.getMuteExpectActualClassesWarning(), Boolean.valueOf(arguments.getExpectActualClasses()));
        putAnalysisFlag(map, AnalysisFlags.getAllowFullyQualifiedNameInKClass(), Boolean.TRUE);
        putAnalysisFlag(map, AnalysisFlags.getDontWarnOnErrorSuppression(), Boolean.valueOf(arguments.getDontWarnOnErrorSuppression()));
        AnalysisFlags analysisFlags = AnalysisFlags.INSTANCE;
        putAnalysisFlag(map, analysisFlags.getLenientMode(), Boolean.valueOf(arguments.getLenientMode()));
        putAnalysisFlag(map, analysisFlags.getHeaderMode(), Boolean.valueOf(arguments.getHeaderMode()));
        putAnalysisFlag(map, analysisFlags.getHeaderModeType(), arguments.getHeaderModeType());
        putAnalysisFlag(map, analysisFlags.getHierarchicalMultiplatformCompilation(), Boolean.valueOf(arguments.getSeparateKmpCompilationScheme() && arguments.getMultiPlatform()));
        fillWarningLevelMap(map, arguments, reporter);
        ReturnValueCheckerMode.Companion companion2 = ReturnValueCheckerMode.INSTANCE;
        Object objFromString3 = companion2.fromString(arguments.getReturnValueChecker());
        if (objFromString3 != null) {
            putAnalysisFlag(map, AnalysisFlags.getReturnValueCheckerMode(), objFromString3);
            return map;
        }
        reporter.reportError("Unknown value for parameter -Xreturn-value-checker: '" + arguments.getReturnValueChecker() + "'. Value should be one of " + companion2.availableValues());
        Unit unit3 = Unit.INSTANCE;
        return map;
    }

    public void configureExtraLanguageFeatures(CommonCompilerArguments arguments, HashMap<LanguageFeature, LanguageFeature.State> map, Reporter reporter) {
        arguments.getClass();
        map.getClass();
        reporter.getClass();
    }

    public Map<LanguageFeature, LanguageFeature.State> configureLanguageFeatures(CommonCompilerArguments arguments, Reporter reporter) {
        arguments.getClass();
        reporter.getClass();
        HashMap<LanguageFeature, LanguageFeature.State> map = new HashMap<>();
        ConfigureCommonLanguageFeaturesKt.configureCommonLanguageFeatures(map, arguments);
        if (arguments.getProgressiveMode()) {
            EnumEntries<LanguageFeature> entries = LanguageFeature.getEntries();
            ArrayList<LanguageFeature> arrayList = new ArrayList();
            for (Object obj : entries) {
                if (((LanguageFeature) obj).getActuallyEnabledInProgressiveMode()) {
                    arrayList.add(obj);
                }
            }
            for (LanguageFeature languageFeature : arrayList) {
                if (!map.containsKey(languageFeature)) {
                    map.put(languageFeature, LanguageFeature.State.ENABLED);
                }
            }
        }
        ReturnValueCheckerMode returnValueCheckerModeFromString = ReturnValueCheckerMode.INSTANCE.fromString(arguments.getReturnValueChecker());
        if (returnValueCheckerModeFromString != null && returnValueCheckerModeFromString != ReturnValueCheckerMode.DISABLED) {
            map.put(LanguageFeature.UnnamedLocalVariables, LanguageFeature.State.ENABLED);
        }
        if (!arguments.getInternalArguments().isEmpty()) {
            configureLanguageFeaturesFromInternalArgs(map, arguments, reporter);
        }
        configureExtraLanguageFeatures(arguments, map, reporter);
        return map;
    }

    public final void putAnalysisFlag(Map<AnalysisFlag<?>, Object> map, AnalysisFlag<?> analysisFlag, Object obj) {
        map.getClass();
        analysisFlag.getClass();
        obj.getClass();
        if (Intrinsics.areEqual(obj, analysisFlag.getDefaultValue())) {
            map.remove(analysisFlag);
        } else {
            map.put(analysisFlag, obj);
        }
    }
}
