package org.jetbrains.kotlin.cli.pipeline.web.js;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.CompilationException;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.js.IcCachesArtifacts;
import org.jetbrains.kotlin.cli.js.Ir2JsTransformer;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.pipeline.web.JsBackendPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.ir.backend.js.SourceMapsInfo;
import org.jetbrains.kotlin.ir.backend.js.ic.JsExecutableProducer;
import org.jetbrains.kotlin.ir.backend.js.ic.JsExecutableProducer$BuildResult;
import org.jetbrains.kotlin.ir.backend.js.ic.JsModuleArtifact;
import org.jetbrains.kotlin.ir.backend.js.ic.ModuleArtifact;
import org.jetbrains.kotlin.ir.backend.js.transformers.irToJs.CompilationOutputs;
import org.jetbrains.kotlin.ir.backend.js.transformers.irToJs.CompilationOutputsBuilt;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.WebArtifactConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\t\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J*\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/js/JsBackendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/JsBackendPipelineArtifact;", "<init>", "()V", "configFiles", "Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "getConfigFiles", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "compileIncrementally", "icCaches", "Lorg/jetbrains/kotlin/cli/js/IcCachesArtifacts;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;", "artifactConfiguration", "Lorg/jetbrains/kotlin/js/config/WebArtifactConfiguration;", "compileNonIncrementally", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "mainCallArguments", Argument.Delimiters.none, Argument.Delimiters.none, "compileIntermediate", "intermediateResult", "ir2JsTransformer", "Lorg/jetbrains/kotlin/cli/js/Ir2JsTransformer;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsBackendPipelinePhase extends WebBackendPipelinePhase<JsBackendPipelineArtifact, JsBackendPipelineArtifact> {
    public static final JsBackendPipelinePhase INSTANCE = new JsBackendPipelinePhase();

    private JsBackendPipelinePhase() {
        super("JsBackendPipelinePhase");
    }

    private final CompilationOutputs compileIncrementally(IcCachesArtifacts icCaches, CompilerConfiguration configuration, WebArtifactConfiguration artifactConfiguration) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<ModuleArtifact> artifacts = icCaches.getArtifacts();
        ArrayList arrayList = new ArrayList();
        for (Object obj : artifacts) {
            if (obj instanceof JsModuleArtifact) {
                arrayList.add(obj);
            }
        }
        JsExecutableProducer jsExecutableProducer = new JsExecutableProducer(artifactConfiguration.getModuleName(), artifactConfiguration.getModuleKind(), SourceMapsInfo.Companion.from(configuration), arrayList, true);
        JsExecutableProducer$BuildResult jsExecutableProducer$BuildResultBuildExecutable = jsExecutableProducer.buildExecutable(artifactConfiguration.getGranularity(), false);
        CompilationOutputs compilationOut = jsExecutableProducer$BuildResultBuildExecutable.getCompilationOut();
        List<String> listComponent2 = jsExecutableProducer$BuildResultBuildExecutable.component2();
        compilationOut.writeAll(artifactConfiguration);
        CliDiagnosticReportingKt.reportLog$default(configuration, "Executable production duration (IC): " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms", null, 2, null);
        for (Map.Entry entry : jsExecutableProducer.getStopwatchLaps().entrySet()) {
            CliDiagnosticReportingKt.reportLog$default(configuration, "  " + ((String) entry.getKey()) + ": " + ((int) (((Number) entry.getValue()).longValue() / 1000000.0d)) + "ms", null, 2, null);
        }
        Iterator<String> it = listComponent2.iterator();
        while (it.hasNext()) {
            CliDiagnosticReportingKt.reportLog$default(configuration, "IC module builder rebuilt JS for module [" + new File(it.next()).getName() + ']', null, 2, null);
        }
        return compilationOut;
    }

    private final CompilationOutputs compileNonIncrementally(CompilerConfiguration configuration, Ir2JsTransformer ir2JsTransformer, WebArtifactConfiguration artifactConfiguration) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            CompilationOutputsBuilt compilationOutputsBuiltCompileAndTransformIrNew = ir2JsTransformer.compileAndTransformIrNew();
            CliDiagnosticReportingKt.reportLog$default(configuration, "Executable production duration: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms", null, 2, null);
            compilationOutputsBuiltCompileAndTransformIrNew.writeAll(artifactConfiguration);
            return compilationOutputsBuiltCompileAndTransformIrNew;
        } catch (CompilationException e) {
            CliDiagnosticReportingKt.report(configuration, CliDiagnostics.INSTANCE.getCOMPILER_EXCEPTION(), ExceptionsKt.stackTraceToString(e), CompilerMessageLocation.INSTANCE.create(e.getPath(), e.getLine(), e.getColumn(), e.getContent()));
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public JsBackendPipelineArtifact compileIntermediate(JsBackendPipelineArtifact intermediateResult, CompilerConfiguration configuration) {
        intermediateResult.getClass();
        configuration.getClass();
        return intermediateResult;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public EnvironmentConfigFiles getConfigFiles() {
        return EnvironmentConfigFiles.JS_CONFIG_FILES;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    /* JADX INFO: renamed from: compileNonIncrementally, reason: avoid collision after fix types in other method */
    public JsBackendPipelineArtifact compileNonIncrementally2(CompilerConfiguration configuration, ModulesStructure module, List<String> mainCallArguments) {
        configuration.getClass();
        module.getClass();
        Ir2JsTransformer ir2JsTransformer = new Ir2JsTransformer(configuration, module, CommonConfigurationKeysKt.getMessageCollector(configuration), mainCallArguments);
        WebArtifactConfiguration artifactConfiguration = JSConfigurationKeysKt.getArtifactConfiguration(configuration);
        artifactConfiguration.getClass();
        CompilationOutputs compilationOutputsCompileNonIncrementally = compileNonIncrementally(configuration, ir2JsTransformer, artifactConfiguration);
        if (compilationOutputsCompileNonIncrementally == null) {
            return null;
        }
        File outputDir = JSConfigurationKeysKt.getOutputDir(configuration);
        outputDir.getClass();
        return new JsBackendPipelineArtifact(compilationOutputsCompileNonIncrementally, outputDir, configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public /* bridge */ /* synthetic */ JsBackendPipelineArtifact compileNonIncrementally(CompilerConfiguration compilerConfiguration, ModulesStructure modulesStructure, List list) {
        return compileNonIncrementally2(compilerConfiguration, modulesStructure, (List<String>) list);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebBackendPipelinePhase
    public JsBackendPipelineArtifact compileIncrementally(IcCachesArtifacts icCaches, CompilerConfiguration configuration) {
        icCaches.getClass();
        configuration.getClass();
        WebArtifactConfiguration artifactConfiguration = JSConfigurationKeysKt.getArtifactConfiguration(configuration);
        artifactConfiguration.getClass();
        CompilationOutputs compilationOutputsCompileIncrementally = compileIncrementally(icCaches, configuration, artifactConfiguration);
        File outputDir = JSConfigurationKeysKt.getOutputDir(configuration);
        outputDir.getClass();
        return new JsBackendPipelineArtifact(compilationOutputsCompileIncrementally, outputDir, configuration);
    }
}
