package org.jetbrains.kotlin.cli.common.arguments;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.AnalysisFlag;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J<\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\"\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013`\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArgumentsConfigurator;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "<init>", "()V", "configureAnalysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "reporter", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator$Reporter;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "configureExtraLanguageFeatures", Argument.Delimiters.none, "map", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "Lkotlin/collections/HashMap;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2MetadataCompilerArgumentsConfigurator extends CommonCompilerArgumentsConfigurator {
    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public Map<AnalysisFlag<?>, Object> configureAnalysisFlags(CommonCompilerArguments arguments, CommonCompilerArgumentsConfigurator.Reporter reporter, LanguageVersion languageVersion) {
        arguments.getClass();
        reporter.getClass();
        languageVersion.getClass();
        if (!(arguments instanceof K2MetadataCompilerArguments)) {
            w01.a("Failed requirement.");
            return null;
        }
        Map<AnalysisFlag<?>, Object> mapConfigureAnalysisFlags = super.configureAnalysisFlags(arguments, reporter, languageVersion);
        mapConfigureAnalysisFlags.put(AnalysisFlags.getMetadataCompilation(), Boolean.TRUE);
        return mapConfigureAnalysisFlags;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArgumentsConfigurator
    public void configureExtraLanguageFeatures(CommonCompilerArguments arguments, HashMap<LanguageFeature, LanguageFeature.State> map, CommonCompilerArgumentsConfigurator.Reporter reporter) {
        arguments.getClass();
        map.getClass();
        reporter.getClass();
        map.put(LanguageFeature.MultiPlatformProjects, LanguageFeature.State.ENABLED);
    }
}
