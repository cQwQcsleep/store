package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyK2JSCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JSCompilerArgumentsCopyGeneratedKt {
    public static final K2JSCompilerArguments copyK2JSCompilerArguments(K2JSCompilerArguments k2JSCompilerArguments, K2JSCompilerArguments k2JSCompilerArguments2) {
        k2JSCompilerArguments.getClass();
        k2JSCompilerArguments2.getClass();
        K2WasmCompilerArgumentsCopyGeneratedKt.copyK2WasmCompilerArguments(k2JSCompilerArguments, k2JSCompilerArguments2);
        k2JSCompilerArguments2.setAllowExportingSuspendFunctions(k2JSCompilerArguments.getAllowExportingSuspendFunctions());
        k2JSCompilerArguments2.setAllowImplementableInterfacesExporting(k2JSCompilerArguments.getAllowImplementableInterfacesExporting());
        k2JSCompilerArguments2.setCompileLongAsBigInt(k2JSCompilerArguments.getCompileLongAsBigInt());
        k2JSCompilerArguments2.setExtensionFunctionsInExternals(k2JSCompilerArguments.getExtensionFunctionsInExternals());
        k2JSCompilerArguments2.setGeneratePolyfills(k2JSCompilerArguments.getGeneratePolyfills());
        k2JSCompilerArguments2.setIrBuildCache(k2JSCompilerArguments.getIrBuildCache());
        k2JSCompilerArguments2.setIrGenerateInlineAnonymousFunctions(k2JSCompilerArguments.getIrGenerateInlineAnonymousFunctions());
        k2JSCompilerArguments2.setIrKeep(k2JSCompilerArguments.getIrKeep());
        k2JSCompilerArguments2.setIrMinimizedMemberNames(k2JSCompilerArguments.getIrMinimizedMemberNames());
        k2JSCompilerArguments2.setIrPerFile(k2JSCompilerArguments.getIrPerFile());
        k2JSCompilerArguments2.setIrPerModule(k2JSCompilerArguments.getIrPerModule());
        k2JSCompilerArguments2.setIrSafeExternalBoolean(k2JSCompilerArguments.getIrSafeExternalBoolean());
        k2JSCompilerArguments2.setIrSafeExternalBooleanDiagnostic(k2JSCompilerArguments.getIrSafeExternalBooleanDiagnostic());
        k2JSCompilerArguments2.setModuleKind(k2JSCompilerArguments.getModuleKind());
        k2JSCompilerArguments2.setOptimizeGeneratedJs(k2JSCompilerArguments.getOptimizeGeneratedJs());
        k2JSCompilerArguments2.setOutputFile(k2JSCompilerArguments.getOutputFile());
        k2JSCompilerArguments2.setPlatformArgumentsProviderJsExpression(k2JSCompilerArguments.getPlatformArgumentsProviderJsExpression());
        k2JSCompilerArguments2.setTarget(k2JSCompilerArguments.getTarget());
        k2JSCompilerArguments2.setUseEsArrowFunctions(k2JSCompilerArguments.getUseEsArrowFunctions());
        k2JSCompilerArguments2.setUseEsClasses(k2JSCompilerArguments.getUseEsClasses());
        k2JSCompilerArguments2.setUseEsGenerators(k2JSCompilerArguments.getUseEsGenerators());
        return k2JSCompilerArguments2;
    }
}
