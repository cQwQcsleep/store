package org.jetbrains.kotlin.cli.common.arguments;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArgumentsConfigurator;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArgumentsConfigurator;", "<init>", "()V", "configureAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "configureLanguageFeatures", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "isSecondStage", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JSCompilerArgumentsConfigurator extends CommonKlibBasedCompilerArgumentsConfigurator {
    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonKlibBasedCompilerArgumentsConfigurator, org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter, LanguageVersion languageVersion) {
        arguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        if (!(arguments instanceof K2JSCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        K2JSCompilerArguments k2JSCompilerArguments = (K2JSCompilerArguments) arguments;
        if (k2JSCompilerArguments.getIrPerFile() && !Intrinsics.areEqual(k2JSCompilerArguments.getModuleKind(), K2JsArgumentConstants.MODULE_ES) && !Intrinsics.areEqual(k2JSCompilerArguments.getTarget(), K2JsArgumentConstants.ES_2015)) {
            reporter.reportError("Per-file compilation can't be used with any `moduleKind` except `es` (ECMAScript Modules)");
        }
        Map<AnalysisFlag<?>, Object> mapConfigureAnalysisFlags = super.configureAnalysisFlags(arguments, reporter, languageVersion);
        putAnalysisFlag(mapConfigureAnalysisFlags, AnalysisFlags.getAllowFullyQualifiedNameInKClass(), Boolean.valueOf(k2JSCompilerArguments.getWasm() && k2JSCompilerArguments.getWasmKClassFqn()));
        return mapConfigureAnalysisFlags;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<LanguageFeature, LanguageFeature.State> configureLanguageFeatures(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        arguments.getClass();
        reporter.getClass();
        if (!(arguments instanceof K2JSCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<LanguageFeature, LanguageFeature.State> mapConfigureLanguageFeatures = super.configureLanguageFeatures(arguments, reporter);
        K2JSCompilerArguments k2JSCompilerArguments = (K2JSCompilerArguments) arguments;
        ConfigureJsLanguageFeaturesKt.configureJsLanguageFeatures(mapConfigureLanguageFeatures, k2JSCompilerArguments);
        LanguageFeature languageFeature = LanguageFeature.AllowAnyAsAnActualTypeForExpectInterface;
        LanguageFeature.State state = LanguageFeature.State.ENABLED;
        mapConfigureLanguageFeatures.put(languageFeature, state);
        if (k2JSCompilerArguments.getWasm()) {
            mapConfigureLanguageFeatures.put(LanguageFeature.JsAllowImplementingFunctionInterface, state);
        }
        return mapConfigureLanguageFeatures;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonKlibBasedCompilerArgumentsConfigurator
    public boolean isSecondStage(CommonCompilerArguments arguments) {
        arguments.getClass();
        if (arguments instanceof K2JSCompilerArguments) {
            return ((K2JSCompilerArguments) arguments).getIncludes() != null;
        }
        w01.a("Failed requirement.");
        return false;
    }
}
