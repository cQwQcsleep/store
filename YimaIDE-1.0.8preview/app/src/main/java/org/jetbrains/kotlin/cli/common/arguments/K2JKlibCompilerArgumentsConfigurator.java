package org.jetbrains.kotlin.cli.common.arguments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettingsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00132\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2JKlibCompilerArgumentsConfigurator;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "<init>", "()V", "configureAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "configureLanguageFeatures", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "configureJvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JKlibCompilerArguments;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JKlibCompilerArgumentsConfigurator extends CommonCompilerArgumentsConfigurator {
    private final JvmDefaultMode configureJvmDefaultMode(K2JKlibCompilerArguments k2JKlibCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        if (k2JKlibCompilerArguments.getJvmDefault() == null) {
            return null;
        }
        JvmDefaultMode jvmDefaultModeFromStringOrNull = JvmDefaultMode.INSTANCE.fromStringOrNull(k2JKlibCompilerArguments.getJvmDefault());
        if (jvmDefaultModeFromStringOrNull == null && reporter != null) {
            StringBuilder sb = new StringBuilder("Unknown -jvm-default mode: ");
            sb.append(k2JKlibCompilerArguments.getJvmDefault());
            sb.append(", supported modes: ");
            EnumEntries<JvmDefaultMode> entries = JvmDefaultMode.getEntries();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                arrayList.add(((JvmDefaultMode) it.next()).getDescription());
            }
            sb.append(arrayList);
            reporter.reportError(sb.toString());
        }
        return jvmDefaultModeFromStringOrNull;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter, LanguageVersion languageVersion) {
        arguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        if (!(arguments instanceof K2JKlibCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<AnalysisFlag<?>, Object> mapConfigureAnalysisFlags = super.configureAnalysisFlags(arguments, reporter, languageVersion);
        K2JKlibCompilerArguments k2JKlibCompilerArguments = (K2JKlibCompilerArguments) arguments;
        mapConfigureAnalysisFlags.put(JvmAnalysisFlags.getJavaTypeEnhancementState(), new JavaTypeEnhancementStateParser(reporter, LanguageVersionSettingsKt.toKotlinVersion(languageVersion)).parse(k2JKlibCompilerArguments.getJsr305(), k2JKlibCompilerArguments.getSupportCompatqualCheckerFrameworkAnnotations(), k2JKlibCompilerArguments.getJspecifyAnnotations(), k2JKlibCompilerArguments.getNullabilityAnnotations()));
        JvmDefaultMode jvmDefaultModeConfigureJvmDefaultMode = configureJvmDefaultMode(k2JKlibCompilerArguments, reporter);
        if (jvmDefaultModeConfigureJvmDefaultMode != null) {
            mapConfigureAnalysisFlags.put(JvmAnalysisFlags.getJvmDefaultMode(), jvmDefaultModeConfigureJvmDefaultMode);
        }
        mapConfigureAnalysisFlags.put(JvmAnalysisFlags.getInheritMultifileParts(), Boolean.valueOf(k2JKlibCompilerArguments.getInheritMultifileParts()));
        mapConfigureAnalysisFlags.put(JvmAnalysisFlags.getOutputBuiltinsMetadata(), Boolean.valueOf(k2JKlibCompilerArguments.getOutputBuiltinsMetadata()));
        return mapConfigureAnalysisFlags;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<LanguageFeature, LanguageFeature.State> configureLanguageFeatures(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        arguments.getClass();
        reporter.getClass();
        if (!(arguments instanceof K2JKlibCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<LanguageFeature, LanguageFeature.State> mapConfigureLanguageFeatures = super.configureLanguageFeatures(arguments, reporter);
        K2JKlibCompilerArguments k2JKlibCompilerArguments = (K2JKlibCompilerArguments) arguments;
        if (k2JKlibCompilerArguments.getTypeEnhancementImprovementsInStrictMode()) {
            mapConfigureLanguageFeatures.put(LanguageFeature.TypeEnhancementImprovementsInStrictMode, LanguageFeature.State.ENABLED);
        }
        if (k2JKlibCompilerArguments.getEnhanceTypeParameterTypesToDefNotNull()) {
            mapConfigureLanguageFeatures.put(LanguageFeature.ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated, LanguageFeature.State.ENABLED);
        }
        if (k2JKlibCompilerArguments.getValueClasses()) {
            mapConfigureLanguageFeatures.put(LanguageFeature.JvmInlineMultiFieldValueClasses, LanguageFeature.State.ENABLED);
        }
        JvmDefaultMode jvmDefaultModeConfigureJvmDefaultMode = configureJvmDefaultMode(k2JKlibCompilerArguments, null);
        if (jvmDefaultModeConfigureJvmDefaultMode != null && jvmDefaultModeConfigureJvmDefaultMode.isEnabled()) {
            mapConfigureLanguageFeatures.put(LanguageFeature.ForbidSuperDelegationToAbstractFakeOverride, LanguageFeature.State.ENABLED);
        }
        return mapConfigureLanguageFeatures;
    }
}
