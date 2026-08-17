package org.jetbrains.kotlin.cli.pipeline.web;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.builtins.DefaultBuiltIns;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.web.WebFir2IrPipelinePhase;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.backend.Fir2IrCommonMemberStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.descriptors.FirModuleDescriptor;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.ConvertToIrKt;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.ir.backend.js.lower.serialization.ir.JsManglerIr;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.types.IrTypeSystemContextImpl;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.KotlinLibraryKt;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WebFir2IrPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/web/JsFir2IrPipelineArtifact;", "<init>", "()V", "executePhase", "input", "transformFirToIr", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "moduleStructure", "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "firOutputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WebFir2IrPipelinePhase extends PipelinePhase<WebFrontendPipelineArtifact, JsFir2IrPipelineArtifact> {
    public static final WebFir2IrPipelinePhase INSTANCE = new WebFir2IrPipelinePhase();

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.pipeline.web.WebFir2IrPipelinePhase$transformFirToIr$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<IrBuiltIns, IrTypeSystemContextImpl> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, IrTypeSystemContextImpl.class, "<init>", "<init>(Lorg/jetbrains/kotlin/ir/IrBuiltIns;)V", 0);
        }

        public final IrTypeSystemContextImpl invoke(IrBuiltIns irBuiltIns) {
            irBuiltIns.getClass();
            return new IrTypeSystemContextImpl(irBuiltIns);
        }
    }

    private WebFir2IrPipelinePhase() {
        super("JsFir2IrPipelinePhase", SetsKt.setOf(PerformanceNotifications.TranslationToIrStarted.INSTANCE), SetsKt.setOf(new Function3[]{PerformanceNotifications.TranslationToIrFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}));
    }

    public static List a(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        return CollectionsKt.emptyList();
    }

    public static Unit b(List list, IrModuleFragment irModuleFragment) {
        irModuleFragment.getClass();
        ModuleDescriptor descriptor = irModuleFragment.getDescriptor();
        FirModuleDescriptor firModuleDescriptor = descriptor instanceof FirModuleDescriptor ? (FirModuleDescriptor) descriptor : null;
        if (firModuleDescriptor != null) {
            firModuleDescriptor.setAllDependencyModules(list);
        }
        return Unit.INSTANCE;
    }

    private final Fir2IrActualizedResult transformFirToIr(ModulesStructure moduleStructure, List<SingleModuleFrontendOutput> firOutputs, BaseDiagnosticsCollector diagnosticsReporter) {
        Fir2IrExtensions.Default r1 = Fir2IrExtensions.Default.INSTANCE;
        ArrayList arrayList = new ArrayList();
        List<KotlinLibrary> all = moduleStructure.getKlibs().getAll();
        final ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(all, 10));
        KotlinBuiltIns companion = null;
        for (KotlinLibrary kotlinLibrary : all) {
            ModuleDescriptorImpl moduleDescriptorImplCreateDescriptorOptionalBuiltIns = KlibKt.getJsFactories().getDefaultDeserializedDescriptorFactory().createDescriptorOptionalBuiltIns(kotlinLibrary, CommonConfigurationKeysKt.getLanguageVersionSettings(moduleStructure.getCompilerConfiguration()), new LockBasedStorageManager("ModulesStructure"), companion, LookupTracker.DO_NOTHING.INSTANCE);
            arrayList.add(moduleDescriptorImplCreateDescriptorOptionalBuiltIns);
            moduleDescriptorImplCreateDescriptorOptionalBuiltIns.setDependencies(new ArrayList(arrayList));
            if (KotlinLibraryKt.isJsStdlib(kotlinLibrary) || KotlinLibraryKt.isWasmStdlib(kotlinLibrary)) {
                companion = moduleDescriptorImplCreateDescriptorOptionalBuiltIns.getBuiltIns();
            }
            arrayList2.add(moduleDescriptorImplCreateDescriptorOptionalBuiltIns);
        }
        List<? extends SingleModuleFrontendOutput> listM573constructorimpl = AllModulesFrontendOutput.m573constructorimpl(firOutputs);
        Fir2IrConfiguration fir2IrConfigurationForKlibCompilation = Fir2IrConfiguration.INSTANCE.forKlibCompilation(moduleStructure.getCompilerConfiguration(), diagnosticsReporter);
        List compilerExtensions = ExtensionPointUtilsKt.getCompilerExtensions(moduleStructure.getCompilerConfiguration(), IrGenerationExtension.Companion);
        JsManglerIr jsManglerIr = JsManglerIr.INSTANCE;
        Fir2IrVisibilityConverter.Default r5 = Fir2IrVisibilityConverter.Default.INSTANCE;
        if (companion == null) {
            companion = DefaultBuiltIns.Companion.getInstance();
        }
        return ConvertToIrKt.m579convertToIrAndActualizeMT2kVtw(listM573constructorimpl, r1, fir2IrConfigurationForKlibCompilation, compilerExtensions, jsManglerIr, r5, companion, AnonymousClass1.INSTANCE, null, new Function1() { // from class: olf
            public final Object invoke(Object obj) {
                return WebFir2IrPipelinePhase.a((Fir2IrComponents) obj);
            }
        }, (512 & 512) != 0 ? new Fir2IrCommonMemberStorage() : null, (512 & 1024) != 0 ? new Function1() { // from class: zx2
            public final Object invoke(Object obj) {
                return ConvertToIrKt.a((IrModuleFragment) obj);
            }
        } : new Function1() { // from class: plf
            public final Object invoke(Object obj) {
                return WebFir2IrPipelinePhase.b(arrayList2, (IrModuleFragment) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JsFir2IrPipelineArtifact executePhase(WebFrontendPipelineArtifact input) {
        input.getClass();
        List<? extends SingleModuleFrontendOutput> listM45component1QYgrGdg = input.m45component1QYgrGdg();
        CompilerConfiguration configuration = input.getConfiguration();
        ModulesStructure moduleStructure = input.getModuleStructure();
        boolean hasErrors = input.getHasErrors();
        BaseDiagnosticsCollector diagnosticsCollector = CLIConfigurationKeysKt.getDiagnosticsCollector(configuration);
        Fir2IrActualizedResult fir2IrActualizedResultTransformFirToIr = transformFirToIr(moduleStructure, listM45component1QYgrGdg, diagnosticsCollector);
        if (!JSConfigurationKeysKt.getWasmCompilation(configuration)) {
            WebFir2IrPipelinePhaseKt.access$runJsKlibCallCheckers(diagnosticsCollector, configuration, listM45component1QYgrGdg, fir2IrActualizedResultTransformFirToIr);
        }
        return new JsFir2IrPipelineArtifact(fir2IrActualizedResultTransformFirToIr, listM45component1QYgrGdg, configuration, hasErrors || CommonConfigurationKeysKt.getMessageCollector(configuration).hasErrors() || diagnosticsCollector.getHasErrors(), null);
    }
}
