package org.jetbrains.kotlin.cli.pipeline.jvm;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileSystem;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizedResult;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.jvm.JvmBackendExtension;
import org.jetbrains.kotlin.backend.jvm.JvmGeneratorExtensions;
import org.jetbrains.kotlin.backend.jvm.JvmGeneratorExtensionsImpl;
import org.jetbrains.kotlin.backend.jvm.JvmIrCodegenFactory;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendClassResolver;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.backend.jvm.serialization.JvmDescriptorMangler;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.impl.IrModuleFragmentImpl;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;
import org.jetbrains.kotlin.psi2ir.generators.fragments.EvaluatorFragmentInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBackendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFir2IrPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBackendPipelineArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmBackendPipelinePhase extends PipelinePhase<JvmFir2IrPipelineArtifact, JvmBackendPipelineArtifact> {
    public static final JvmBackendPipelinePhase INSTANCE = new JvmBackendPipelinePhase();

    private JvmBackendPipelinePhase() {
        super("JvmBackendPipelineStep", null, SetsKt.setOf(CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE), 2, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JvmBackendPipelineArtifact executePhase(JvmFir2IrPipelineArtifact input) {
        CompilerConfiguration compilerConfigurationCreateConfigurationForModule;
        List<Module> list;
        Iterator<Module> it;
        JvmIrCodegenFactory.BackendInput backendInput;
        JvmIrCodegenFactory.BackendInput moduleChunkBackendInput;
        List actualizedExpectDeclarations;
        input.getClass();
        Fir2IrActualizedResult result = input.getResult();
        CompilerConfiguration configuration = input.getConfiguration();
        VfsBasedProjectEnvironment environment = input.getEnvironment();
        List<KtSourceFile> listComponent4 = input.component4();
        FqName mainClassFqName = input.getMainClassFqName();
        ModuleDescriptor descriptor = result.getIrModuleFragment().getDescriptor();
        BaseDiagnosticsCollector diagnosticsCollector = CLIConfigurationKeysKt.getDiagnosticsCollector(configuration);
        Project project = environment.getProject();
        FirJvmBackendClassResolver firJvmBackendClassResolver = new FirJvmBackendClassResolver(result.getComponents());
        Fir2IrComponents components = result.getComponents();
        IrActualizedResult irActualizedResult = result.getIrActualizedResult();
        JvmIrCodegenFactory.BackendInput backendInput$org_jetbrains_kotlin_cli_jvm = KotlinToJVMBytecodeCompiler.INSTANCE.toBackendInput$org_jetbrains_kotlin_cli_jvm(result, configuration, new FirJvmBackendExtension(components, (irActualizedResult == null || (actualizedExpectDeclarations = irActualizedResult.getActualizedExpectDeclarations()) == null) ? null : VariousUtilsKt.extractFirDeclarations(actualizedExpectDeclarations)));
        JvmIrCodegenFactory jvmIrCodegenFactory = new JvmIrCodegenFactory(configuration, (JvmDescriptorMangler) null, (SymbolTable) null, (JvmGeneratorExtensionsImpl) null, (EvaluatorFragmentInfo) null, (JvmIrCodegenFactory.IdeCodegenSettings) null, 62, (DefaultConstructorMarker) null);
        ModuleChunk moduleChunk = CLIConfigurationKeysKt.getModuleChunk(configuration);
        moduleChunk.getClass();
        List<Module> modules = moduleChunk.getModules();
        modules.getClass();
        VirtualFileSystem fileSystem = VirtualFileManager.getInstance().getFileSystem("file");
        ArrayList arrayList = new ArrayList(modules.size());
        File buildFile = CLIConfigurationKeysKt.getBuildFile(configuration);
        Iterator<Module> it2 = modules.iterator();
        while (it2.hasNext()) {
            Module next = it2.next();
            if (modules.size() == 1) {
                compilerConfigurationCreateConfigurationForModule = configuration;
            } else {
                next.getClass();
                compilerConfigurationCreateConfigurationForModule = CoreEnvironmentUtilsKt.createConfigurationForModule(configuration, next, buildFile);
            }
            ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
            boolean useLightTree = CommonConfigurationKeysKt.getUseLightTree(compilerConfigurationCreateConfigurationForModule);
            if (!useLightTree) {
                list = modules;
                it = it2;
                backendInput = backendInput$org_jetbrains_kotlin_cli_jvm;
                if (useLightTree) {
                    bu8.a();
                    return null;
                }
                next.getClass();
                moduleChunkBackendInput = jvmIrCodegenFactory.getModuleChunkBackendInput(backendInput, CliCompilerUtilsKt.getSourceFiles(next, JvmPipelineUtilsKt.asKtFilesList(listComponent4), fileSystem, list.size() > 1, buildFile));
            } else if (modules.size() == 1) {
                list = modules;
                it = it2;
                backendInput = backendInput$org_jetbrains_kotlin_cli_jvm;
                moduleChunkBackendInput = backendInput;
            } else {
                IrModuleFragment irModuleFragment = backendInput$org_jetbrains_kotlin_cli_jvm.getIrModuleFragment();
                list = modules;
                IrModuleFragmentImpl irModuleFragmentImpl = new IrModuleFragmentImpl(irModuleFragment.getDescriptor());
                List files = irModuleFragment.getFiles();
                List files2 = irModuleFragmentImpl.getFiles();
                Iterator it3 = files.iterator();
                while (it3.hasNext()) {
                    Iterator it4 = it3;
                    Object next2 = it4.next();
                    Iterator<Module> it5 = it2;
                    IrModuleFragmentImpl irModuleFragmentImpl2 = irModuleFragmentImpl;
                    if (next.getSourceFiles().contains(((IrFile) next2).getFileEntry().getName())) {
                        files2.add(next2);
                    }
                    it3 = it4;
                    irModuleFragmentImpl = irModuleFragmentImpl2;
                    it2 = it5;
                }
                it = it2;
                backendInput = backendInput$org_jetbrains_kotlin_cli_jvm;
                moduleChunkBackendInput = JvmIrCodegenFactory.BackendInput.copy$default(backendInput$org_jetbrains_kotlin_cli_jvm, irModuleFragmentImpl, (IrBuiltIns) null, (SymbolTable) null, (List) null, (JvmGeneratorExtensions) null, (JvmBackendExtension) null, (IrPluginContext) null, 126, (Object) null);
            }
            arrayList.add(KotlinToJVMBytecodeCompiler.INSTANCE.runLowerings$org_jetbrains_kotlin_cli_jvm(project, compilerConfigurationCreateConfigurationForModule, descriptor, next, jvmIrCodegenFactory, moduleChunkBackendInput, diagnosticsCollector, firJvmBackendClassResolver));
            modules = list;
            backendInput$org_jetbrains_kotlin_cli_jvm = backendInput;
            firJvmBackendClassResolver = firJvmBackendClassResolver;
            it2 = it;
        }
        ArrayList arrayList2 = new ArrayList(modules.size());
        Iterator it6 = arrayList.iterator();
        it6.getClass();
        while (it6.hasNext()) {
            Object next3 = it6.next();
            next3.getClass();
            JvmIrCodegenFactory.CodegenInput codegenInput = (JvmIrCodegenFactory.CodegenInput) next3;
            ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
            JvmIrCodegenFactory jvmIrCodegenFactory2 = jvmIrCodegenFactory;
            arrayList2.add(KotlinToJVMBytecodeCompiler.INSTANCE.runCodegen$org_jetbrains_kotlin_cli_jvm(codegenInput, codegenInput.getState(), jvmIrCodegenFactory2, diagnosticsCollector, codegenInput.getState().getConfiguration(), false));
            jvmIrCodegenFactory = jvmIrCodegenFactory2;
        }
        return new JvmBackendPipelineArtifact(configuration, environment, mainClassFqName, arrayList2);
    }
}
