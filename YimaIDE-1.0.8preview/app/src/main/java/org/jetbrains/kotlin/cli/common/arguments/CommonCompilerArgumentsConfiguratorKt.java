package org.jetbrains.kotlin.cli.common.arguments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.ExplicitApiMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageOrApiVersion;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004\u001a$\u0010\u000f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a$\u0010\u0013\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a.\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0019\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u001a\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a(\u0010\u001b\u001a\u0004\u0018\u00010\f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u001a*\u0010\u001f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0 *\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\f\u001a\u001e\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0 *\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006$²\u0006\n\u0010%\u001a\u00020\bX\u008a\u0084\u0002"}, d2 = {"toLanguageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "additionalAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "checkApiAndLanguageVersion", Argument.Delimiters.none, "language", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "api", "Lorg/jetbrains/kotlin/config/ApiVersion;", "checkApiVersionIsNotGreaterThenLanguageVersion", "languageVersion", "apiVersion", "checkLanguageVersionIsStable", "checkOutdatedVersions", "findOutdatedVersion", "Lkotlin/Triple;", "Lorg/jetbrains/kotlin/config/LanguageOrApiVersion;", "Lorg/jetbrains/kotlin/cli/common/arguments/VersionKind;", "checkProgressiveMode", "checkExplicitApiAndExplicitReturnTypesAtTheSameTime", "parseOrConfigureLanguageVersion", "parseVersion", "value", Argument.Delimiters.none, "versionOf", "configureAnalysisFlags", Argument.Delimiters.none, "configureLanguageFeatures", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "org.jetbrains.kotlin:cli-base", "firstNonDeprecated"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonCompilerArgumentsConfiguratorKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VersionKind.values().length];
            try {
                iArr[VersionKind.LANGUAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VersionKind.API.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static Object a(VersionKind versionKind) {
        int i = WhenMappings.$EnumSwitchMapping$0[versionKind.ordinal()];
        if (i == 1) {
            return LanguageVersion.FIRST_NON_DEPRECATED;
        }
        if (i == 2) {
            return ApiVersion.FIRST_NON_DEPRECATED;
        }
        bu8.a();
        return null;
    }

    public static final void checkApiAndLanguageVersion(CommonCompilerArguments commonCompilerArguments, LanguageVersion languageVersion, ApiVersion apiVersion, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        commonCompilerArguments.getClass();
        languageVersion.getClass();
        apiVersion.getClass();
        reporter.getClass();
        checkApiVersionIsNotGreaterThenLanguageVersion(commonCompilerArguments, languageVersion, apiVersion, reporter);
        checkLanguageVersionIsStable(commonCompilerArguments, languageVersion, reporter);
        checkOutdatedVersions(commonCompilerArguments, languageVersion, apiVersion, reporter);
        checkProgressiveMode(commonCompilerArguments, languageVersion, reporter);
    }

    private static final void checkApiVersionIsNotGreaterThenLanguageVersion(CommonCompilerArguments commonCompilerArguments, LanguageVersion languageVersion, ApiVersion apiVersion, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        if (apiVersion.compareTo(ApiVersion.INSTANCE.createByLanguageVersion(languageVersion)) > 0) {
            if (commonCompilerArguments.getSuppressApiVersionGreaterThanLanguageVersionError()) {
                return;
            }
            reporter.reportError("-api-version (" + apiVersion.getVersionString() + ") cannot be greater than -language-version (" + languageVersion.getVersionString() + ").");
            return;
        }
        if (commonCompilerArguments.getSuppressApiVersionGreaterThanLanguageVersionError()) {
            reporter.reportWarning("-Xsuppress-api-version-greater-than-language-version-error was passed, but the API version (" + apiVersion.getVersionString() + ") is not greater than the language version (" + languageVersion.getVersionString() + ").");
        }
    }

    private static final void checkExplicitApiAndExplicitReturnTypesAtTheSameTime(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        String explicitApi = commonCompilerArguments.getExplicitApi();
        ExplicitApiMode explicitApiMode = ExplicitApiMode.DISABLED;
        if (Intrinsics.areEqual(explicitApi, explicitApiMode.getState()) || Intrinsics.areEqual(commonCompilerArguments.getExplicitReturnTypes(), explicitApiMode.getState()) || Intrinsics.areEqual(commonCompilerArguments.getExplicitApi(), commonCompilerArguments.getExplicitReturnTypes())) {
            return;
        }
        reporter.reportError(StringsKt.trimIndent("\n                    '-Xexplicit-api' and '-XXexplicit-return-types' flags cannot have different values at the same time.\n                    Consider use only one of those flags\n                    Passed:\n                      '-Xexplicit-api=" + commonCompilerArguments.getExplicitApi() + "'\n                      '-XXexplicit-return-types=" + commonCompilerArguments.getExplicitReturnTypes() + "'\n                    "));
    }

    private static final void checkLanguageVersionIsStable(CommonCompilerArguments commonCompilerArguments, LanguageVersion languageVersion, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        if (languageVersion.isStable() || commonCompilerArguments.getSuppressVersionWarnings()) {
            return;
        }
        reporter.report(CliDiagnostics.INSTANCE.getEXPERIMENTAL_LANGUAGE_VERSION(), "Language version " + languageVersion.getVersionString() + " is experimental, there are no backwards compatibility guarantees for new language and library features. Use the stable version " + LanguageVersion.LATEST_STABLE + " instead.");
    }

    private static final void checkOutdatedVersions(CommonCompilerArguments commonCompilerArguments, LanguageVersion languageVersion, ApiVersion apiVersion, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        Triple<LanguageOrApiVersion, LanguageOrApiVersion, VersionKind> tripleFindOutdatedVersion = findOutdatedVersion(languageVersion, apiVersion);
        if (tripleFindOutdatedVersion == null) {
            return;
        }
        LanguageOrApiVersion languageOrApiVersion = (LanguageOrApiVersion) tripleFindOutdatedVersion.component1();
        LanguageOrApiVersion languageOrApiVersion2 = (LanguageOrApiVersion) tripleFindOutdatedVersion.component2();
        final VersionKind versionKind = (VersionKind) tripleFindOutdatedVersion.component3();
        Lazy lazy = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.cli.common.arguments.a
            public final Object invoke() {
                return CommonCompilerArgumentsConfiguratorKt.a(versionKind);
            }
        });
        if (languageOrApiVersion.isUnsupported()) {
            KtSourcelessDiagnosticFactory unsupported_language_version = CliDiagnostics.INSTANCE.getUNSUPPORTED_LANGUAGE_VERSION();
            StringBuilder sb = new StringBuilder();
            sb.append(versionKind.getText());
            sb.append(" version ");
            sb.append(languageOrApiVersion.getVersionString());
            sb.append(" is no longer supported; use version ");
            languageOrApiVersion2.getClass();
            sb.append(languageOrApiVersion2.getVersionString());
            sb.append(" or greater instead.");
            reporter.report(unsupported_language_version, sb.toString());
            return;
        }
        if (!languageOrApiVersion.isDeprecated() || commonCompilerArguments.getSuppressVersionWarnings()) {
            return;
        }
        reporter.report(CliDiagnostics.INSTANCE.getDEPRECATED_LANGUAGE_VERSION(), versionKind.getText() + " version " + languageOrApiVersion.getVersionString() + " is deprecated and its support will be removed in a future version of Kotlin. Update the version to " + lazy.getValue() + '.');
    }

    private static final void checkProgressiveMode(CommonCompilerArguments commonCompilerArguments, LanguageVersion languageVersion, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        if (commonCompilerArguments.getProgressiveMode()) {
            LanguageVersion languageVersion2 = LanguageVersion.LATEST_STABLE;
            if (languageVersion.compareTo(languageVersion2) >= 0 || commonCompilerArguments.getSuppressVersionWarnings()) {
                return;
            }
            reporter.reportWarning("'-progressive' is meaningful only for the latest language version (" + languageVersion2 + "), while this build uses " + languageVersion + "\nCompiler behavior in such mode is undefined; consider moving to the latest stable version or turning off progressive mode.");
        }
    }

    public static final Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter, LanguageVersion languageVersion) {
        commonCompilerArguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        return commonCompilerArguments.getConfigurator().configureAnalysisFlags(commonCompilerArguments, reporter, languageVersion);
    }

    public static final Map<LanguageFeature, LanguageFeature.State> configureLanguageFeatures(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        commonCompilerArguments.getClass();
        reporter.getClass();
        return commonCompilerArguments.getConfigurator().configureLanguageFeatures(commonCompilerArguments, reporter);
    }

    private static final Triple<LanguageOrApiVersion, LanguageOrApiVersion, VersionKind> findOutdatedVersion(LanguageVersion languageVersion, ApiVersion apiVersion) {
        if (languageVersion.isUnsupported()) {
            return new Triple<>(languageVersion, LanguageVersion.FIRST_SUPPORTED, VersionKind.LANGUAGE);
        }
        if (apiVersion.isUnsupported()) {
            return new Triple<>(apiVersion, ApiVersion.FIRST_SUPPORTED, VersionKind.API);
        }
        if (languageVersion.isDeprecated()) {
            return new Triple<>(languageVersion, (Object) null, VersionKind.LANGUAGE);
        }
        if (apiVersion.isDeprecated()) {
            return new Triple<>(apiVersion, (Object) null, VersionKind.API);
        }
        return null;
    }

    private static final LanguageVersion parseOrConfigureLanguageVersion(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        if (commonCompilerArguments.getUseK2()) {
            reporter.reportError("Compiler flag -Xuse-k2 is no more supported. Compiler versions 2.0+ use K2 by default, unless the language version is set to 1.9 or earlier");
        }
        LanguageVersion version = parseVersion(commonCompilerArguments, reporter, commonCompilerArguments.getLanguageVersion(), "language");
        return version == null ? LanguageVersion.LATEST_STABLE : version;
    }

    private static final LanguageVersion parseVersion(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter, String str, String str2) {
        if (str == null) {
            return null;
        }
        LanguageVersion languageVersionFromVersionString = LanguageVersion.INSTANCE.fromVersionString(str);
        if (languageVersionFromVersionString != null) {
            return languageVersionFromVersionString;
        }
        EnumEntries<LanguageVersion> entries = LanguageVersion.getEntries();
        ArrayList arrayList = new ArrayList();
        for (Object obj : entries) {
            if (!((LanguageVersion) obj).isUnsupported()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((LanguageVersion) it.next()).getDescription());
        }
        reporter.reportError("Unknown " + str2 + " version: " + str + "\nSupported " + str2 + " versions: " + CollectionsKt.joinToString$default(arrayList2, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        return null;
    }

    public static final LanguageVersionSettings toLanguageVersionSettings(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter, Map<AnalysisFlag<?>, ? extends Object> map) {
        commonCompilerArguments.getClass();
        reporter.getClass();
        map.getClass();
        LanguageVersion orConfigureLanguageVersion = parseOrConfigureLanguageVersion(commonCompilerArguments, reporter);
        ApiVersion.Companion companion = ApiVersion.INSTANCE;
        LanguageVersion version = parseVersion(commonCompilerArguments, reporter, commonCompilerArguments.getApiVersion(), "API");
        if (version == null) {
            version = orConfigureLanguageVersion;
        }
        ApiVersion apiVersionCreateByLanguageVersion = companion.createByLanguageVersion(version);
        LanguageVersionSettingsImpl languageVersionSettingsImpl = new LanguageVersionSettingsImpl(orConfigureLanguageVersion, apiVersionCreateByLanguageVersion, MapsKt.plus(configureAnalysisFlags(commonCompilerArguments, reporter, orConfigureLanguageVersion), map), configureLanguageFeatures(commonCompilerArguments, reporter));
        CommonCompilerArgumentsConfigurator.Reporter reporterWithLanguageVersionSettings = reporter.withLanguageVersionSettings(languageVersionSettingsImpl);
        checkApiAndLanguageVersion(commonCompilerArguments, orConfigureLanguageVersion, apiVersionCreateByLanguageVersion, reporterWithLanguageVersionSettings);
        checkExplicitApiAndExplicitReturnTypesAtTheSameTime(commonCompilerArguments, reporterWithLanguageVersionSettings);
        return languageVersionSettingsImpl;
    }

    public static final LanguageVersionSettings toLanguageVersionSettings(CommonCompilerArguments commonCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        commonCompilerArguments.getClass();
        reporter.getClass();
        return toLanguageVersionSettings(commonCompilerArguments, reporter, MapsKt.emptyMap());
    }
}
