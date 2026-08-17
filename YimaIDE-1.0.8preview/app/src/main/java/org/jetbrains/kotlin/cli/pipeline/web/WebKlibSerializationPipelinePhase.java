package org.jetbrains.kotlin.cli.pipeline.web;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.fir.pipeline.Fir2KlibMetadataSerializer;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.KtDiagnosticReporterWithImplicitIrBasedContext;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.library.KlibElementWithSize;
import org.jetbrains.kotlin.library.KlibSizeInfoKt;
import org.jetbrains.kotlin.library.impl.BuiltInsPlatform;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WebKlibSerializationPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/JsFir2IrPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/web/JsSerializedKlibPipelineArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WebKlibSerializationPipelinePhase extends PipelinePhase<JsFir2IrPipelineArtifact, JsSerializedKlibPipelineArtifact> {
    public static final WebKlibSerializationPipelinePhase INSTANCE = new WebKlibSerializationPipelinePhase();

    private WebKlibSerializationPipelinePhase() {
        super("JsKlibSerializationPipelinePhase", null, null, 6, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JsSerializedKlibPipelineArtifact executePhase(JsFir2IrPipelineArtifact input) {
        List listFlatten;
        PerformanceManager perfManager;
        input.getClass();
        Fir2IrActualizedResult result = input.getResult();
        List<? extends SingleModuleFrontendOutput> listM41component2QYgrGdg = input.m41component2QYgrGdg();
        CompilerConfiguration configuration = input.getConfiguration();
        KtDiagnosticReporterWithImplicitIrBasedContext ktDiagnosticReporterWithImplicitIrBasedContext = new KtDiagnosticReporterWithImplicitIrBasedContext(CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), CommonConfigurationKeysKt.getLanguageVersionSettings(configuration));
        String strComputeOutputKlibPath = WebKlibSerializationPipelinePhaseKt.computeOutputKlibPath(configuration);
        Fir2KlibMetadataSerializer fir2KlibMetadataSerializer = new Fir2KlibMetadataSerializer(configuration, listM41component2QYgrGdg, result, false);
        IncrementalDataProvider incrementalDataProvider = JSConfigurationKeysKt.getIncrementalDataProvider(configuration);
        List serializedData = incrementalDataProvider != null ? KlibKt.getSerializedData(incrementalDataProvider, fir2KlibMetadataSerializer.getSourceFiles()) : null;
        Object obj = configuration.get(CommonConfigurationKeys.MODULE_NAME);
        obj.getClass();
        String str = (String) obj;
        IrModuleFragment irModuleFragment = result.getIrModuleFragment();
        IrBuiltIns irBuiltIns = result.getIrBuiltIns();
        if (serializedData == null) {
            serializedData = CollectionsKt.emptyList();
        }
        KlibKt.serializeModuleIntoKlib(str, configuration, ktDiagnosticReporterWithImplicitIrBasedContext, fir2KlibMetadataSerializer, strComputeOutputKlibPath, irModuleFragment, irBuiltIns, serializedData, JSConfigurationKeysKt.getProduceKlibDir(configuration), JSConfigurationKeysKt.getPerModuleOutputName(configuration), JSConfigurationKeysKt.getWasmCompilation(configuration) ? BuiltInsPlatform.WASM : BuiltInsPlatform.JS, WasmConfigurationKeysKt.getWasmTarget(configuration), CommonConfigurationKeysKt.getPerfManager(configuration));
        KlibElementWithSize klibElementWithSizeLoadSizeInfo = KlibSizeInfoKt.loadSizeInfo(new File(strComputeOutputKlibPath));
        if (klibElementWithSizeLoadSizeInfo != null && (listFlatten = klibElementWithSizeLoadSizeInfo.flatten()) != null && (perfManager = CommonConfigurationKeysKt.getPerfManager(configuration)) != null) {
            perfManager.registerKlibElementStats(listFlatten);
        }
        return new JsSerializedKlibPipelineArtifact(strComputeOutputKlibPath, configuration);
    }
}
