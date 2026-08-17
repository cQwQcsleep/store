package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyKotlinWasmCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinWasmCompilerArgumentsCopyGeneratedKt {
    public static final KotlinWasmCompilerArguments copyKotlinWasmCompilerArguments(KotlinWasmCompilerArguments kotlinWasmCompilerArguments, KotlinWasmCompilerArguments kotlinWasmCompilerArguments2) {
        kotlinWasmCompilerArguments.getClass();
        kotlinWasmCompilerArguments2.getClass();
        CommonJsAndWasmCompilerArgumentsCopyGeneratedKt.copyCommonJsAndWasmCompilerArguments(kotlinWasmCompilerArguments, kotlinWasmCompilerArguments2);
        kotlinWasmCompilerArguments2.setDebuggerCustomFormatters(kotlinWasmCompilerArguments.getDebuggerCustomFormatters());
        kotlinWasmCompilerArguments2.setForceDebugFriendlyCompilation(kotlinWasmCompilerArguments.getForceDebugFriendlyCompilation());
        kotlinWasmCompilerArguments2.setGenerateDwarf(kotlinWasmCompilerArguments.getGenerateDwarf());
        kotlinWasmCompilerArguments2.setIncludeUnavailableSourcesIntoSourceMap(kotlinWasmCompilerArguments.getIncludeUnavailableSourcesIntoSourceMap());
        kotlinWasmCompilerArguments2.setIrDceDumpDeclarationIrSizesToFile(kotlinWasmCompilerArguments.getIrDceDumpDeclarationIrSizesToFile());
        kotlinWasmCompilerArguments2.setIrDceDumpReachabilityInfoToFile(kotlinWasmCompilerArguments.getIrDceDumpReachabilityInfoToFile());
        kotlinWasmCompilerArguments2.setWasm(kotlinWasmCompilerArguments.getWasm());
        kotlinWasmCompilerArguments2.setWasmDebug(kotlinWasmCompilerArguments.getWasmDebug());
        kotlinWasmCompilerArguments2.setWasmEnableArrayRangeChecks(kotlinWasmCompilerArguments.getWasmEnableArrayRangeChecks());
        kotlinWasmCompilerArguments2.setWasmEnableAsserts(kotlinWasmCompilerArguments.getWasmEnableAsserts());
        kotlinWasmCompilerArguments2.setWasmGenerateClosedWorldMultimodule(kotlinWasmCompilerArguments.getWasmGenerateClosedWorldMultimodule());
        kotlinWasmCompilerArguments2.setWasmGenerateWat(kotlinWasmCompilerArguments.getWasmGenerateWat());
        kotlinWasmCompilerArguments2.setWasmIncludedModuleOnly(kotlinWasmCompilerArguments.getWasmIncludedModuleOnly());
        kotlinWasmCompilerArguments2.setWasmInternalLocalVariablePrefix(kotlinWasmCompilerArguments.getWasmInternalLocalVariablePrefix());
        kotlinWasmCompilerArguments2.setWasmKClassFqn(kotlinWasmCompilerArguments.getWasmKClassFqn());
        kotlinWasmCompilerArguments2.setWasmNoJsTag(kotlinWasmCompilerArguments.getWasmNoJsTag());
        kotlinWasmCompilerArguments2.setWasmTarget(kotlinWasmCompilerArguments.getWasmTarget());
        kotlinWasmCompilerArguments2.setWasmUseNewExceptionProposal(kotlinWasmCompilerArguments.getWasmUseNewExceptionProposal());
        kotlinWasmCompilerArguments2.setWasmUseTrapsInsteadOfExceptions(kotlinWasmCompilerArguments.getWasmUseTrapsInsteadOfExceptions());
        return kotlinWasmCompilerArguments2;
    }
}
