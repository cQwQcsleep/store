package org.jetbrains.kotlin.cli.common.arguments;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"configureJsLanguageFeatures", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConfigureJsLanguageFeaturesKt {
    public static final void configureJsLanguageFeatures(Map<LanguageFeature, LanguageFeature.State> map, K2JSCompilerArguments k2JSCompilerArguments) {
        map.getClass();
        k2JSCompilerArguments.getClass();
        if (k2JSCompilerArguments.getExtensionFunctionsInExternals()) {
            map.put(LanguageFeature.JsEnableExtensionFunctionInExternals, LanguageFeature.State.ENABLED);
        }
        if (k2JSCompilerArguments.getAllowImplementableInterfacesExporting()) {
            map.put(LanguageFeature.JsExportInterfacesInImplementableWay, LanguageFeature.State.ENABLED);
        }
        if (k2JSCompilerArguments.getAllowExportingSuspendFunctions()) {
            map.put(LanguageFeature.JsAllowExportingSuspendFunctions, LanguageFeature.State.ENABLED);
        }
    }
}
