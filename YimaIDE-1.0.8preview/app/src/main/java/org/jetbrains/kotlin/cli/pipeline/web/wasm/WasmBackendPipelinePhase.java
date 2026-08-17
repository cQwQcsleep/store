package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.backend.common.IrModuleInfo;
import org.jetbrains.kotlin.backend.wasm.LoweredIrWithExtraArtifacts;
import org.jetbrains.kotlin.backend.wasm.WasmCompilerKt;
import org.jetbrains.kotlin.backend.wasm.WasmCompilerResult;
import org.jetbrains.kotlin.backend.wasm.WasmIrModuleConfiguration;
import org.jetbrains.kotlin.backend.wasm.ic.IrFactoryImplForWasmIC;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.js.IcCachesArtifacts;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.pipeline.web.WasmBackendPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.ir.backend.js.WholeWorldStageController;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.KotlinLibraryKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u000b\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmBackendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WasmBackendPipelineArtifact;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/wasm/WasmIrModuleConfiguration;", "<init>", "()V", "configFiles", "Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "getConfigFiles", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "compileIntermediate", "intermediateResult", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "compileIncrementally", "icCaches", "Lorg/jetbrains/kotlin/cli/js/IcCachesArtifacts;", "compileNonIncrementally", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "mainCallArguments", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WasmBackendPipelinePhase extends WebBackendPipelinePhase<WasmBackendPipelineArtifact, List<? extends WasmIrModuleConfiguration>> {
    public static final WasmBackendPipelinePhase INSTANCE = new WasmBackendPipelinePhase();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WasmCompilationMode.values().length];
            try {
                iArr[WasmCompilationMode.MULTI_MODULE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WasmCompilationMode.SINGLE_MODULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WasmCompilationMode.REGULAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private WasmBackendPipelinePhase() {
        super("WasmBackendPipelinePhase");
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public List<? extends WasmIrModuleConfiguration> compileIncrementally(IcCachesArtifacts icCaches, CompilerConfiguration configuration) {
        Function2 function2;
        icCaches.getClass();
        configuration.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[WasmCompilationMode.INSTANCE.wasmCompilationMode(configuration).ordinal()];
        if (i == 1) {
            function2 = WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$1.INSTANCE;
        } else if (i == 2) {
            function2 = WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$2.INSTANCE;
        } else {
            if (i != 3) {
                bu8.a();
                return null;
            }
            function2 = WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$3.INSTANCE;
        }
        return (List) function2.invoke(icCaches.getArtifacts(), configuration);
    }

    /* JADX INFO: renamed from: compileIntermediate, reason: avoid collision after fix types in other method */
    public WasmBackendPipelineArtifact compileIntermediate2(List<WasmIrModuleConfiguration> intermediateResult, CompilerConfiguration configuration) {
        intermediateResult.getClass();
        configuration.getClass();
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        PhaseType phaseType = PhaseType.Backend;
        if (perfManager == null) {
            File outputDir = JSConfigurationKeysKt.getOutputDir(configuration);
            outputDir.getClass();
            List<WasmIrModuleConfiguration> list = intermediateResult;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (WasmIrModuleConfiguration wasmIrModuleConfiguration : list) {
                WasmCompilerResult wasmCompilerResultCompileWasmIrToBinary = WasmCompilerKt.compileWasmIrToBinary(wasmIrModuleConfiguration, WasmCompilerKt.linkWasmIr(wasmIrModuleConfiguration));
                WasmCompilerKt.writeCompilationResult$default(wasmCompilerResultCompileWasmIrToBinary, outputDir, wasmIrModuleConfiguration.getBaseFileName(), (MessageCollector) null, 8, (Object) null);
                arrayList.add(wasmCompilerResultCompileWasmIrToBinary);
            }
            return new WasmBackendPipelineArtifact(arrayList, outputDir, configuration);
        }
        try {
            perfManager.notifyPhaseStarted(phaseType);
            File outputDir2 = JSConfigurationKeysKt.getOutputDir(configuration);
            outputDir2.getClass();
            List<WasmIrModuleConfiguration> list2 = intermediateResult;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (WasmIrModuleConfiguration wasmIrModuleConfiguration2 : list2) {
                WasmCompilerResult wasmCompilerResultCompileWasmIrToBinary2 = WasmCompilerKt.compileWasmIrToBinary(wasmIrModuleConfiguration2, WasmCompilerKt.linkWasmIr(wasmIrModuleConfiguration2));
                WasmCompilerKt.writeCompilationResult$default(wasmCompilerResultCompileWasmIrToBinary2, outputDir2, wasmIrModuleConfiguration2.getBaseFileName(), (MessageCollector) null, 8, (Object) null);
                arrayList2.add(wasmCompilerResultCompileWasmIrToBinary2);
            }
            return new WasmBackendPipelineArtifact(arrayList2, outputDir2, configuration);
        } finally {
            perfManager.notifyPhaseFinished(phaseType);
        }
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    /* JADX INFO: renamed from: compileNonIncrementally, reason: avoid collision after fix types in other method */
    public List<? extends WasmIrModuleConfiguration> compileNonIncrementally2(CompilerConfiguration configuration, ModulesStructure module, List<String> mainCallArguments) {
        WasmCompilerBase wholeWorldMultiModuleCompiler;
        IrModuleInfo irModuleInfoLoadIr;
        LoweredIrWithExtraArtifacts loweredIrWithExtraArtifactsLowerIr;
        configuration.getClass();
        module.getClass();
        IrFactoryImplForWasmIC irFactoryImplForWasmIC = new IrFactoryImplForWasmIC(new WholeWorldStageController());
        int i = WhenMappings.$EnumSwitchMapping$0[WasmCompilationMode.INSTANCE.wasmCompilationMode(configuration).ordinal()];
        if (i == 1) {
            wholeWorldMultiModuleCompiler = new WholeWorldMultiModuleCompiler(configuration, irFactoryImplForWasmIC);
        } else if (i == 2) {
            KotlinLibrary included = module.getKlibs().getIncluded();
            wholeWorldMultiModuleCompiler = new SingleModuleCompiler(configuration, irFactoryImplForWasmIC, included != null && KotlinLibraryKt.isWasmStdlib(included));
        } else {
            if (i != 3) {
                bu8.a();
                return null;
            }
            wholeWorldMultiModuleCompiler = new WholeWorldCompiler(configuration, irFactoryImplForWasmIC);
        }
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        PhaseType phaseType = PhaseType.TranslationToIr;
        if (perfManager == null) {
            irModuleInfoLoadIr = wholeWorldMultiModuleCompiler.loadIr(module);
        } else {
            try {
                perfManager.notifyPhaseStarted(phaseType);
                IrModuleInfo irModuleInfoLoadIr2 = wholeWorldMultiModuleCompiler.loadIr(module);
                perfManager.notifyPhaseFinished(phaseType);
                irModuleInfoLoadIr = irModuleInfoLoadIr2;
            } catch (Throwable th) {
                perfManager.notifyPhaseFinished(phaseType);
                throw th;
            }
        }
        PerformanceManager perfManager2 = CommonConfigurationKeysKt.getPerfManager(configuration);
        PhaseType phaseType2 = PhaseType.IrLowering;
        if (perfManager2 == null) {
            loweredIrWithExtraArtifactsLowerIr = wholeWorldMultiModuleCompiler.lowerIr(irModuleInfoLoadIr, module.getMainModule(), SetsKt.setOf(new FqName("main")));
        } else {
            try {
                perfManager2.notifyPhaseStarted(phaseType2);
                loweredIrWithExtraArtifactsLowerIr = wholeWorldMultiModuleCompiler.lowerIr(irModuleInfoLoadIr, module.getMainModule(), SetsKt.setOf(new FqName("main")));
                perfManager2.notifyPhaseFinished(phaseType2);
            } catch (Throwable th2) {
                perfManager2.notifyPhaseFinished(phaseType2);
                throw th2;
            }
        }
        PerformanceManager perfManager3 = CommonConfigurationKeysKt.getPerfManager(configuration);
        PhaseType phaseType3 = PhaseType.Backend;
        if (perfManager3 == null) {
            return wholeWorldMultiModuleCompiler.compileIr(loweredIrWithExtraArtifactsLowerIr);
        }
        try {
            perfManager3.notifyPhaseStarted(phaseType3);
            return wholeWorldMultiModuleCompiler.compileIr(loweredIrWithExtraArtifactsLowerIr);
        } finally {
            perfManager3.notifyPhaseFinished(phaseType3);
        }
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public EnvironmentConfigFiles getConfigFiles() {
        return EnvironmentConfigFiles.WASM_CONFIG_FILES;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public /* bridge */ /* synthetic */ WebBackendPipelineArtifact compileIntermediate(List<? extends WasmIrModuleConfiguration> list, CompilerConfiguration compilerConfiguration) {
        return compileIntermediate2((List<WasmIrModuleConfiguration>) list, compilerConfiguration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public /* bridge */ /* synthetic */ List<? extends WasmIrModuleConfiguration> compileNonIncrementally(CompilerConfiguration compilerConfiguration, ModulesStructure modulesStructure, List list) {
        return compileNonIncrementally2(compilerConfiguration, modulesStructure, (List<String>) list);
    }
}
