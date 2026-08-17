package org.jetbrains.kotlin.cli.pipeline.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.js.HelpersKt;
import org.jetbrains.kotlin.cli.js.IcCachesArtifacts;
import org.jetbrains.kotlin.cli.js.IcCachesConfigurationData;
import org.jetbrains.kotlin.cli.js.IcCachesKt;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilationMode;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.LoadWebKlibsKt;
import org.jetbrains.kotlin.ir.backend.js.MainModule;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.ir.backend.js.ic.IncrementalCacheGuard;
import org.jetbrains.kotlin.ir.backend.js.ic.IncrementalCacheGuard$AcquireStatus;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.WebArtifactConfiguration;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeys;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00010\u0004B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000b\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\fJ7\u0010\r\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014H\u0002¢\u0006\u0002\u0010\u0015J/\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014H\u0002¢\u0006\u0002\u0010\u0017J\u001f\u0010\u001c\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0002\u0010\u001fJ/\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0014H&¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00028\u00002\u0006\u0010%\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0002\u0010&R\u0012\u0010\u0018\u001a\u00020\u0019X¤\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelinePhase;", "Output", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelineArtifact;", "IntermediateOutput", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "executePhase", "input", "(Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;)Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelineArtifact;", "compileToBackendIrIncrementally", "cacheDirectory", "cacheGuard", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "mainCallArguments", Argument.Delimiters.none, "(Ljava/lang/String;Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)Ljava/lang/Object;", "compileToBackendIrNonIncrementally", "(Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)Ljava/lang/Object;", "configFiles", "Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "getConfigFiles", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "compileIncrementally", "icCaches", "Lorg/jetbrains/kotlin/cli/js/IcCachesArtifacts;", "(Lorg/jetbrains/kotlin/cli/js/IcCachesArtifacts;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/Object;", "compileNonIncrementally", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;Ljava/util/List;)Ljava/lang/Object;", "compileIntermediate", "intermediateResult", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelineArtifact;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class WebBackendPipelinePhase<Output extends WebBackendPipelineArtifact, IntermediateOutput> extends PipelinePhase<ConfigurationPipelineArtifact, Output> {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IncrementalCacheGuard$AcquireStatus.values().length];
            try {
                iArr[IncrementalCacheGuard$AcquireStatus.CACHE_CLEARED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IncrementalCacheGuard$AcquireStatus.INVALID_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IncrementalCacheGuard$AcquireStatus.OK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebBackendPipelinePhase(String str) {
        super(str, SetsKt.emptySet(), SetsKt.setOf(CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE));
        str.getClass();
    }

    private final IntermediateOutput compileToBackendIrIncrementally(String cacheDirectory, IncrementalCacheGuard cacheGuard, CompilerConfiguration configuration, List<String> mainCallArguments) {
        IcCachesConfigurationData js;
        int i = WhenMappings.$EnumSwitchMapping$0[cacheGuard.acquire().ordinal()];
        boolean z = true;
        if (i == 1) {
            CliDiagnosticReportingKt.reportInfo$default(configuration, "Cache guard file detected, cache directory '" + cacheDirectory + "' cleared", null, 2, null);
        } else {
            if (i == 2) {
                CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getJS_IC_ERROR(), "Cache guard file detected in readonly mode, cache directory '" + cacheDirectory + "' should be cleared", null, 4, null);
                return null;
            }
            if (i != 3) {
                bu8.a();
                return null;
            }
        }
        if (JSConfigurationKeysKt.getWasmCompilation(configuration)) {
            boolean z2 = configuration.getBoolean(WasmConfigurationKeys.WASM_DEBUG);
            boolean z3 = configuration.getBoolean(WasmConfigurationKeys.WASM_GENERATE_WAT);
            if (!configuration.getBoolean(WasmConfigurationKeys.WASM_GENERATE_DWARF) && !JSConfigurationKeysKt.getSourceMap(configuration)) {
                z = false;
            }
            js = new IcCachesConfigurationData.Wasm(z2, z3, z, WasmCompilationMode.INSTANCE.wasmCompilationMode(configuration));
        } else {
            WebArtifactConfiguration artifactConfiguration = JSConfigurationKeysKt.getArtifactConfiguration(configuration);
            artifactConfiguration.getClass();
            js = new IcCachesConfigurationData.Js(artifactConfiguration.getGranularity());
        }
        File outputDir = JSConfigurationKeysKt.getOutputDir(configuration);
        outputDir.getClass();
        IcCachesArtifacts icCachesArtifactsPrepareIcCaches = IcCachesKt.prepareIcCaches(cacheDirectory, js, outputDir, configuration, mainCallArguments);
        cacheGuard.release();
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        if (perfManager != null) {
            perfManager.notifyPhaseFinished(PhaseType.Initialization);
        }
        cacheGuard.tryAcquire();
        IntermediateOutput intermediateoutputCompileIncrementally = compileIncrementally(icCachesArtifactsPrepareIcCaches, configuration);
        cacheGuard.release();
        return intermediateoutputCompileIncrementally;
    }

    private final IntermediateOutput compileToBackendIrNonIncrementally(ConfigurationPipelineArtifact input, CompilerConfiguration configuration, List<String> mainCallArguments) throws IOException {
        Object next;
        String includes = JSConfigurationKeysKt.getIncludes(configuration);
        includes.getClass();
        String canonicalPath = new File(includes).getCanonicalPath();
        Iterator<T> it = JSConfigurationKeysKt.getLibraries(configuration).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(new File((String) next).getCanonicalPath(), canonicalPath));
        String str = (String) next;
        if (str == null) {
            axd.a("No library with name ", includes, " (", canonicalPath, ") found");
            return null;
        }
        ModulesStructure modulesStructure = new ModulesStructure(KotlinCoreEnvironment.INSTANCE.createForProduction(input.getRootDisposable(), configuration, getConfigFiles()).getProject(), new MainModule.Klib(str), configuration, LoadWebKlibsKt.loadWebKlibs(configuration, HelpersKt.getPlatformChecker(configuration)));
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        if (perfManager != null) {
            perfManager.notifyPhaseFinished(PhaseType.Initialization);
        }
        return compileNonIncrementally(configuration, modulesStructure, mainCallArguments);
    }

    public abstract IntermediateOutput compileIncrementally(IcCachesArtifacts icCaches, CompilerConfiguration configuration);

    public abstract Output compileIntermediate(IntermediateOutput intermediateResult, CompilerConfiguration configuration);

    public abstract IntermediateOutput compileNonIncrementally(CompilerConfiguration configuration, ModulesStructure module, List<String> mainCallArguments);

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public Output executePhase(ConfigurationPipelineArtifact input) throws IOException {
        input.getClass();
        CompilerConfiguration configuration = input.getConfiguration();
        String icCacheDirectory = JSConfigurationKeysKt.getIcCacheDirectory(configuration);
        Output output = null;
        CliDiagnosticReportingKt.reportLog$default(configuration, "Produce executable: " + JSConfigurationKeysKt.getOutputDir(configuration), null, 2, null);
        CliDiagnosticReportingKt.reportLog$default(configuration, "Cache directory: " + icCacheDirectory, null, 2, null);
        List<String> listEmptyList = Intrinsics.areEqual(JSConfigurationKeysKt.getCallMainMode(configuration), K2JsArgumentConstants.NO_CALL) ? null : CollectionsKt.emptyList();
        if (icCacheDirectory == null) {
            IntermediateOutput intermediateoutputCompileToBackendIrNonIncrementally = compileToBackendIrNonIncrementally(input, configuration, listEmptyList);
            if (intermediateoutputCompileToBackendIrNonIncrementally != null) {
                return (Output) compileIntermediate(intermediateoutputCompileToBackendIrNonIncrementally, configuration);
            }
            return null;
        }
        IncrementalCacheGuard incrementalCacheGuard = new IncrementalCacheGuard(icCacheDirectory);
        IntermediateOutput intermediateoutputCompileToBackendIrIncrementally = compileToBackendIrIncrementally(icCacheDirectory, incrementalCacheGuard, configuration, listEmptyList);
        incrementalCacheGuard.tryAcquire();
        if (intermediateoutputCompileToBackendIrIncrementally != null) {
            output = (Output) compileIntermediate(intermediateoutputCompileToBackendIrIncrementally, configuration);
        }
        incrementalCacheGuard.release();
        return output;
    }

    public abstract EnvironmentConfigFiles getConfigFiles();
}
