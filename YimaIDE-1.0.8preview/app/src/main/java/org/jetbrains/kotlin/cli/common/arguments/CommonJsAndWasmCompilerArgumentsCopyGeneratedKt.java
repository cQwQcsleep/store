package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"copyCommonJsAndWasmCompilerArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "from", "to", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonJsAndWasmCompilerArgumentsCopyGeneratedKt {
    public static final CommonJsAndWasmCompilerArguments copyCommonJsAndWasmCompilerArguments(CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments, CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments2) {
        commonJsAndWasmCompilerArguments.getClass();
        commonJsAndWasmCompilerArguments2.getClass();
        CommonKlibBasedCompilerArgumentsCopyGeneratedKt.copyCommonKlibBasedCompilerArguments(commonJsAndWasmCompilerArguments, commonJsAndWasmCompilerArguments2);
        commonJsAndWasmCompilerArguments2.setCacheDirectory(commonJsAndWasmCompilerArguments.getCacheDirectory());
        commonJsAndWasmCompilerArguments2.setFakeOverrideValidator(commonJsAndWasmCompilerArguments.getFakeOverrideValidator());
        commonJsAndWasmCompilerArguments2.setFriendModules(commonJsAndWasmCompilerArguments.getFriendModules());
        commonJsAndWasmCompilerArguments2.setFriendModulesDisabled(commonJsAndWasmCompilerArguments.getFriendModulesDisabled());
        commonJsAndWasmCompilerArguments2.setGenerateDts(commonJsAndWasmCompilerArguments.getGenerateDts());
        commonJsAndWasmCompilerArguments2.setIncludes(commonJsAndWasmCompilerArguments.getIncludes());
        commonJsAndWasmCompilerArguments2.setIrDce(commonJsAndWasmCompilerArguments.getIrDce());
        commonJsAndWasmCompilerArguments2.setIrDcePrintReachabilityInfo(commonJsAndWasmCompilerArguments.getIrDcePrintReachabilityInfo());
        commonJsAndWasmCompilerArguments2.setIrDceRuntimeDiagnostic(commonJsAndWasmCompilerArguments.getIrDceRuntimeDiagnostic());
        commonJsAndWasmCompilerArguments2.setIrModuleName(commonJsAndWasmCompilerArguments.getIrModuleName());
        commonJsAndWasmCompilerArguments2.setIrPerModuleOutputName(commonJsAndWasmCompilerArguments.getIrPerModuleOutputName());
        commonJsAndWasmCompilerArguments2.setIrProduceJs(commonJsAndWasmCompilerArguments.getIrProduceJs());
        commonJsAndWasmCompilerArguments2.setIrProduceKlibDir(commonJsAndWasmCompilerArguments.getIrProduceKlibDir());
        commonJsAndWasmCompilerArguments2.setIrProduceKlibFile(commonJsAndWasmCompilerArguments.getIrProduceKlibFile());
        commonJsAndWasmCompilerArguments2.setIrPropertyLazyInitialization(commonJsAndWasmCompilerArguments.getIrPropertyLazyInitialization());
        commonJsAndWasmCompilerArguments2.setLibraries(commonJsAndWasmCompilerArguments.getLibraries());
        commonJsAndWasmCompilerArguments2.setMain(commonJsAndWasmCompilerArguments.getMain());
        commonJsAndWasmCompilerArguments2.setModuleName(commonJsAndWasmCompilerArguments.getModuleName());
        commonJsAndWasmCompilerArguments2.setOutputDir(commonJsAndWasmCompilerArguments.getOutputDir());
        commonJsAndWasmCompilerArguments2.setSourceMap(commonJsAndWasmCompilerArguments.getSourceMap());
        commonJsAndWasmCompilerArguments2.setSourceMapBaseDirs(commonJsAndWasmCompilerArguments.getSourceMapBaseDirs());
        commonJsAndWasmCompilerArguments2.setSourceMapEmbedSources(commonJsAndWasmCompilerArguments.getSourceMapEmbedSources());
        commonJsAndWasmCompilerArguments2.setSourceMapNamesPolicy(commonJsAndWasmCompilerArguments.getSourceMapNamesPolicy());
        commonJsAndWasmCompilerArguments2.setSourceMapPrefix(commonJsAndWasmCompilerArguments.getSourceMapPrefix());
        commonJsAndWasmCompilerArguments2.setStrictImplicitExportType(commonJsAndWasmCompilerArguments.getStrictImplicitExportType());
        return commonJsAndWasmCompilerArguments2;
    }
}
