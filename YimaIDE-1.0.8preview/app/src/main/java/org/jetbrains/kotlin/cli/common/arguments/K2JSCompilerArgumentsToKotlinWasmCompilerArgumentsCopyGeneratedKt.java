package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, d2 = {"copyK2JSCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "from", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JSCompilerArgumentsToKotlinWasmCompilerArgumentsCopyGeneratedKt {
    public static final KotlinWasmCompilerArguments copyK2JSCompilerArguments(K2JSCompilerArguments k2JSCompilerArguments, KotlinWasmCompilerArguments kotlinWasmCompilerArguments) {
        k2JSCompilerArguments.getClass();
        kotlinWasmCompilerArguments.getClass();
        CommonJsAndWasmCompilerArgumentsCopyGeneratedKt.copyCommonJsAndWasmCompilerArguments(k2JSCompilerArguments, kotlinWasmCompilerArguments);
        kotlinWasmCompilerArguments.setDebuggerCustomFormatters(k2JSCompilerArguments.getDebuggerCustomFormatters());
        kotlinWasmCompilerArguments.setForceDebugFriendlyCompilation(k2JSCompilerArguments.getForceDebugFriendlyCompilation());
        kotlinWasmCompilerArguments.setGenerateDwarf(k2JSCompilerArguments.getGenerateDwarf());
        kotlinWasmCompilerArguments.setIncludeUnavailableSourcesIntoSourceMap(k2JSCompilerArguments.getIncludeUnavailableSourcesIntoSourceMap());
        kotlinWasmCompilerArguments.setIrDceDumpDeclarationIrSizesToFile(k2JSCompilerArguments.getIrDceDumpDeclarationIrSizesToFile());
        kotlinWasmCompilerArguments.setIrDceDumpReachabilityInfoToFile(k2JSCompilerArguments.getIrDceDumpReachabilityInfoToFile());
        kotlinWasmCompilerArguments.setWasm(k2JSCompilerArguments.getWasm());
        kotlinWasmCompilerArguments.setWasmDebug(k2JSCompilerArguments.getWasmDebug());
        kotlinWasmCompilerArguments.setWasmEnableArrayRangeChecks(k2JSCompilerArguments.getWasmEnableArrayRangeChecks());
        kotlinWasmCompilerArguments.setWasmEnableAsserts(k2JSCompilerArguments.getWasmEnableAsserts());
        kotlinWasmCompilerArguments.setWasmGenerateClosedWorldMultimodule(k2JSCompilerArguments.getWasmGenerateClosedWorldMultimodule());
        kotlinWasmCompilerArguments.setWasmGenerateWat(k2JSCompilerArguments.getWasmGenerateWat());
        kotlinWasmCompilerArguments.setWasmIncludedModuleOnly(k2JSCompilerArguments.getWasmIncludedModuleOnly());
        kotlinWasmCompilerArguments.setWasmInternalLocalVariablePrefix(k2JSCompilerArguments.getWasmInternalLocalVariablePrefix());
        kotlinWasmCompilerArguments.setWasmKClassFqn(k2JSCompilerArguments.getWasmKClassFqn());
        kotlinWasmCompilerArguments.setWasmNoJsTag(k2JSCompilerArguments.getWasmNoJsTag());
        kotlinWasmCompilerArguments.setWasmTarget(k2JSCompilerArguments.getWasmTarget());
        kotlinWasmCompilerArguments.setWasmUseNewExceptionProposal(k2JSCompilerArguments.getWasmUseNewExceptionProposal());
        kotlinWasmCompilerArguments.setWasmUseTrapsInsteadOfExceptions(k2JSCompilerArguments.getWasmUseTrapsInsteadOfExceptions());
        return kotlinWasmCompilerArguments;
    }
}
