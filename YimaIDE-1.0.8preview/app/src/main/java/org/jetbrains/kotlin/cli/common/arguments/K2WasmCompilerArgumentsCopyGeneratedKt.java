package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyK2WasmCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2WasmCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2WasmCompilerArgumentsCopyGeneratedKt {
    public static final K2WasmCompilerArguments copyK2WasmCompilerArguments(K2WasmCompilerArguments k2WasmCompilerArguments, K2WasmCompilerArguments k2WasmCompilerArguments2) {
        k2WasmCompilerArguments.getClass();
        k2WasmCompilerArguments2.getClass();
        CommonJsAndWasmCompilerArgumentsCopyGeneratedKt.copyCommonJsAndWasmCompilerArguments(k2WasmCompilerArguments, k2WasmCompilerArguments2);
        k2WasmCompilerArguments2.setDebuggerCustomFormatters(k2WasmCompilerArguments.getDebuggerCustomFormatters());
        k2WasmCompilerArguments2.setForceDebugFriendlyCompilation(k2WasmCompilerArguments.getForceDebugFriendlyCompilation());
        k2WasmCompilerArguments2.setGenerateDwarf(k2WasmCompilerArguments.getGenerateDwarf());
        k2WasmCompilerArguments2.setIncludeUnavailableSourcesIntoSourceMap(k2WasmCompilerArguments.getIncludeUnavailableSourcesIntoSourceMap());
        k2WasmCompilerArguments2.setIrDceDumpDeclarationIrSizesToFile(k2WasmCompilerArguments.getIrDceDumpDeclarationIrSizesToFile());
        k2WasmCompilerArguments2.setIrDceDumpReachabilityInfoToFile(k2WasmCompilerArguments.getIrDceDumpReachabilityInfoToFile());
        k2WasmCompilerArguments2.setWasm(k2WasmCompilerArguments.getWasm());
        k2WasmCompilerArguments2.setWasmDebug(k2WasmCompilerArguments.getWasmDebug());
        k2WasmCompilerArguments2.setWasmEnableArrayRangeChecks(k2WasmCompilerArguments.getWasmEnableArrayRangeChecks());
        k2WasmCompilerArguments2.setWasmEnableAsserts(k2WasmCompilerArguments.getWasmEnableAsserts());
        k2WasmCompilerArguments2.setWasmGenerateClosedWorldMultimodule(k2WasmCompilerArguments.getWasmGenerateClosedWorldMultimodule());
        k2WasmCompilerArguments2.setWasmGenerateWat(k2WasmCompilerArguments.getWasmGenerateWat());
        k2WasmCompilerArguments2.setWasmIncludedModuleOnly(k2WasmCompilerArguments.getWasmIncludedModuleOnly());
        k2WasmCompilerArguments2.setWasmInternalLocalVariablePrefix(k2WasmCompilerArguments.getWasmInternalLocalVariablePrefix());
        k2WasmCompilerArguments2.setWasmKClassFqn(k2WasmCompilerArguments.getWasmKClassFqn());
        k2WasmCompilerArguments2.setWasmNoJsTag(k2WasmCompilerArguments.getWasmNoJsTag());
        k2WasmCompilerArguments2.setWasmTarget(k2WasmCompilerArguments.getWasmTarget());
        k2WasmCompilerArguments2.setWasmUseNewExceptionProposal(k2WasmCompilerArguments.getWasmUseNewExceptionProposal());
        k2WasmCompilerArguments2.setWasmUseTrapsInsteadOfExceptions(k2WasmCompilerArguments.getWasmUseTrapsInsteadOfExceptions());
        return k2WasmCompilerArguments2;
    }
}
