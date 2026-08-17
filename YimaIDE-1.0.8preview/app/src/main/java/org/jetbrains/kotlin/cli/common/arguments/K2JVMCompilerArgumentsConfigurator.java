package org.jetbrains.kotlin.cli.common.arguments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.JVMAssertionsMode;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettingsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J$\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArgumentsConfigurator;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "<init>", "()V", "configureAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "configureJvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "configureLanguageFeatures", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JVMCompilerArgumentsConfigurator extends CommonCompilerArgumentsConfigurator {
    private final JvmDefaultMode configureJvmDefaultMode(K2JVMCompilerArguments k2JVMCompilerArguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        if (k2JVMCompilerArguments.getJvmDefaultStable() != null) {
            JvmDefaultMode jvmDefaultModeFromStringOrNull = JvmDefaultMode.INSTANCE.fromStringOrNull(k2JVMCompilerArguments.getJvmDefaultStable());
            if (jvmDefaultModeFromStringOrNull == null && reporter != null) {
                StringBuilder sb = new StringBuilder("Unknown -jvm-default mode: ");
                sb.append(k2JVMCompilerArguments.getJvmDefaultStable());
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
        if (k2JVMCompilerArguments.getJvmDefault() == null) {
            return null;
        }
        JvmDefaultMode jvmDefaultModeFromStringOrNullOld = JvmDefaultMode.INSTANCE.fromStringOrNullOld(k2JVMCompilerArguments.getJvmDefault());
        if (jvmDefaultModeFromStringOrNullOld == null && reporter != null) {
            StringBuilder sb2 = new StringBuilder("Unknown -Xjvm-default mode: ");
            sb2.append(k2JVMCompilerArguments.getJvmDefault());
            sb2.append(", supported modes: ");
            EnumEntries<JvmDefaultMode> entries2 = JvmDefaultMode.getEntries();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries2, 10));
            Iterator it2 = entries2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((JvmDefaultMode) it2.next()).getOldDescription());
            }
            sb2.append(arrayList2);
            reporter.reportError(sb2.toString());
        }
        return jvmDefaultModeFromStringOrNullOld;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter, LanguageVersion languageVersion) {
        arguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        if (!(arguments instanceof K2JVMCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<AnalysisFlag<?>, Object> mapConfigureAnalysisFlags = super.configureAnalysisFlags(arguments, reporter, languageVersion);
        K2JVMCompilerArguments k2JVMCompilerArguments = (K2JVMCompilerArguments) arguments;
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getStrictMetadataVersionSemantics(), Boolean.valueOf(k2JVMCompilerArguments.getStrictMetadataVersionSemantics()));
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getJavaTypeEnhancementState(), new JavaTypeEnhancementStateParser(reporter, LanguageVersionSettingsKt.toKotlinVersion(languageVersion)).parse(k2JVMCompilerArguments.getJsr305(), k2JVMCompilerArguments.getSupportCompatqualCheckerFrameworkAnnotations(), k2JVMCompilerArguments.getJspecifyAnnotations(), k2JVMCompilerArguments.getNullabilityAnnotations()));
        putAnalysisFlag(mapConfigureAnalysisFlags, AnalysisFlags.getIgnoreDataFlowInAssert(), Boolean.valueOf(JVMAssertionsMode.INSTANCE.fromString(k2JVMCompilerArguments.getAssertionsMode()) != JVMAssertionsMode.LEGACY));
        JvmDefaultMode jvmDefaultModeConfigureJvmDefaultMode = configureJvmDefaultMode(k2JVMCompilerArguments, reporter);
        if (jvmDefaultModeConfigureJvmDefaultMode != null) {
            putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getJvmDefaultMode(), jvmDefaultModeConfigureJvmDefaultMode);
            if (k2JVMCompilerArguments.getJvmDefault() != null) {
                reporter.reportWarning("-Xjvm-default is deprecated. Use -jvm-default instead.");
            }
        }
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getInheritMultifileParts(), Boolean.valueOf(k2JVMCompilerArguments.getInheritMultifileParts()));
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getSanitizeParentheses(), Boolean.valueOf(k2JVMCompilerArguments.getSanitizeParentheses()));
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getSuppressMissingBuiltinsError(), Boolean.valueOf(k2JVMCompilerArguments.getSuppressMissingBuiltinsError()));
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getEnableJvmPreview(), Boolean.valueOf(k2JVMCompilerArguments.getEnableJvmPreview()));
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getImplicitJvmExposeBoxed(), Boolean.valueOf(k2JVMCompilerArguments.getJvmExposeBoxed()));
        putAnalysisFlag(mapConfigureAnalysisFlags, AnalysisFlags.getAllowUnstableDependencies(), Boolean.valueOf(k2JVMCompilerArguments.getAllowUnstableDependencies()));
        putAnalysisFlag(mapConfigureAnalysisFlags, JvmAnalysisFlags.getOutputBuiltinsMetadata(), Boolean.valueOf(k2JVMCompilerArguments.getOutputBuiltinsMetadata()));
        return mapConfigureAnalysisFlags;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<LanguageFeature, LanguageFeature.State> configureLanguageFeatures(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        arguments.getClass();
        reporter.getClass();
        if (!(arguments instanceof K2JVMCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<LanguageFeature, LanguageFeature.State> mapConfigureLanguageFeatures = super.configureLanguageFeatures(arguments, reporter);
        K2JVMCompilerArguments k2JVMCompilerArguments = (K2JVMCompilerArguments) arguments;
        ConfigureJvmLanguageFeaturesKt.configureJvmLanguageFeatures(mapConfigureLanguageFeatures, k2JVMCompilerArguments);
        if (Intrinsics.areEqual(k2JVMCompilerArguments.getIndyAllowAnnotatedLambdas(), Boolean.TRUE)) {
            mapConfigureLanguageFeatures.put(LanguageFeature.JvmIndyAllowLambdasWithAnnotations, LanguageFeature.State.ENABLED);
        } else if (Intrinsics.areEqual(k2JVMCompilerArguments.getIndyAllowAnnotatedLambdas(), Boolean.FALSE)) {
            mapConfigureLanguageFeatures.put(LanguageFeature.JvmIndyAllowLambdasWithAnnotations, LanguageFeature.State.DISABLED);
        }
        JvmDefaultMode jvmDefaultModeConfigureJvmDefaultMode = configureJvmDefaultMode(k2JVMCompilerArguments, null);
        if (jvmDefaultModeConfigureJvmDefaultMode != null && jvmDefaultModeConfigureJvmDefaultMode.isEnabled()) {
            mapConfigureLanguageFeatures.put(LanguageFeature.ForbidSuperDelegationToAbstractFakeOverride, LanguageFeature.State.ENABLED);
        }
        return mapConfigureLanguageFeatures;
    }
}
