package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.jvm.JvmPhasesKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.CreatePhaseConfigKt;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.cli.jvm.JvmArgumentsKt;
import org.jetbrains.kotlin.cli.jvm.K2JVMCompilerKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompilerKt;
import org.jetbrains.kotlin.cli.jvm.config.ClassicFrontendSpecificJvmConfigurationKeys;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.incremental.components.ImportTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.load.java.JavaClassesTracker;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\u0006*\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u000f\u001a\u00020\u0006*\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0002¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "<init>", "()V", "fillConfiguration", Argument.Delimiters.none, "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "setupIncrementalCompilationServices", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "setupModuleChunk", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmConfigurationUpdater extends ConfigurationUpdater<K2JVMCompilerArguments> {
    public static final JvmConfigurationUpdater INSTANCE = new JvmConfigurationUpdater();

    private JvmConfigurationUpdater() {
    }

    private final void setupIncrementalCompilationServices(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments, Services services) {
        CommonConfigurationKeysKt.setLookupTracker(compilerConfiguration, (LookupTracker) services.get(LookupTracker.class));
        CommonConfigurationKeysKt.setImportTracker(compilerConfiguration, (ImportTracker) services.get(ImportTracker.class));
        if (UtilsKt.incrementalCompilationIsEnabled(k2JVMCompilerArguments)) {
            CommonConfigurationKeysKt.setExpectActualTracker(compilerConfiguration, (ExpectActualTracker) services.get(ExpectActualTracker.class));
            CommonConfigurationKeysKt.setInlineConstTracker(compilerConfiguration, (InlineConstTracker) services.get(InlineConstTracker.class));
            CommonConfigurationKeysKt.setEnumWhenTracker(compilerConfiguration, (EnumWhenTracker) services.get(EnumWhenTracker.class));
            CommonConfigurationKeysKt.setFileMappingTracker(compilerConfiguration, (ICFileMappingTracker) services.get(ICFileMappingTracker.class));
            JVMConfigurationKeysKt.setIncrementalCompilationComponents(compilerConfiguration, (IncrementalCompilationComponents) services.get(IncrementalCompilationComponents.class));
            compilerConfiguration.putIfNotNull(ClassicFrontendSpecificJvmConfigurationKeys.JAVA_CLASSES_TRACKER, services.get(JavaClassesTracker.class));
        }
    }

    private final void setupModuleChunk(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        File buildFile = CLIConfigurationKeysKt.getBuildFile(compilerConfiguration);
        ModuleChunk moduleChunkConfigureModuleChunk = K2JVMCompilerKt.configureModuleChunk(compilerConfiguration, k2JVMCompilerArguments, buildFile);
        CLIConfigurationKeysKt.setModuleChunk(compilerConfiguration, moduleChunkConfigureModuleChunk);
        if (moduleChunkConfigureModuleChunk.getModules().size() == 1) {
            List<Module> modules = moduleChunkConfigureModuleChunk.getModules();
            modules.getClass();
            Object objSingle = CollectionsKt.single(modules);
            objSingle.getClass();
            CoreEnvironmentUtilsKt.applyModuleProperties(compilerConfiguration, (Module) objSingle, buildFile);
        }
        List<Module> modules2 = moduleChunkConfigureModuleChunk.getModules();
        modules2.getClass();
        KotlinToJVMBytecodeCompilerKt.configureSourceRoots(compilerConfiguration, modules2, buildFile);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater
    public void fillConfiguration(ArgumentsPipelineArtifact<K2JVMCompilerArguments> input, CompilerConfiguration configuration) {
        input.getClass();
        configuration.getClass();
        K2JVMCompilerArguments k2JVMCompilerArguments = (K2JVMCompilerArguments) input.component1();
        Services services = input.getServices();
        CliDiagnosticReportingKt.reportLog$default(configuration, "Configuring the compilation environment", null, 2, null);
        String buildFile = k2JVMCompilerArguments.getBuildFile();
        if (buildFile != null) {
            CLIConfigurationKeysKt.setBuildFile(configuration, new File(buildFile));
        }
        CLIConfigurationKeysKt.setAllowNoSourceFiles(configuration, k2JVMCompilerArguments.getAllowNoSourceFiles());
        JvmArgumentsKt.setupJvmSpecificArguments(configuration, k2JVMCompilerArguments);
        setupIncrementalCompilationServices(configuration, k2JVMCompilerArguments, services);
        PhaseConfig phaseConfigCreatePhaseConfig = CreatePhaseConfigKt.createPhaseConfig(k2JVMCompilerArguments, JvmPhasesKt.getJvmPhases());
        if (k2JVMCompilerArguments.getListPhases()) {
            CreatePhaseConfigKt.listPhases(phaseConfigCreatePhaseConfig, JvmPhasesKt.getJvmPhases());
        }
        CommonConfigurationKeysKt.setPhaseConfig(configuration, phaseConfigCreatePhaseConfig);
        if (JvmArgumentsKt.configureJdkHome(configuration, k2JVMCompilerArguments)) {
            JVMConfigurationKeysKt.setDisableStandardScriptDefinition(configuration, k2JVMCompilerArguments.getDisableStandardScript());
            String moduleName = k2JVMCompilerArguments.getModuleName();
            if (moduleName == null) {
                moduleName = "main";
            }
            CommonConfigurationKeysKt.setModuleName(configuration, moduleName);
            JvmArgumentsKt.configureJavaModulesContentRoots(configuration, k2JVMCompilerArguments);
            JvmArgumentsKt.configureStandardLibs(configuration, CLIConfigurationKeysKt.getKotlinPaths(configuration), k2JVMCompilerArguments);
            JvmArgumentsKt.configureAdvancedJvmOptions(configuration, k2JVMCompilerArguments);
            JvmArgumentsKt.configureKlibPaths(configuration, k2JVMCompilerArguments);
            if (k2JVMCompilerArguments.getExpression() == null) {
                setupModuleChunk(configuration, k2JVMCompilerArguments);
            } else {
                JvmArgumentsKt.configureContentRootsFromClassPath(configuration, k2JVMCompilerArguments);
            }
            configuration.put(JVMConfigurationKeys.DISABLE_STANDARD_SCRIPT_DEFINITION, Boolean.valueOf(k2JVMCompilerArguments.getDisableStandardScript()));
            if (k2JVMCompilerArguments.getScript() || k2JVMCompilerArguments.getExpression() != null) {
                CLIConfigurationKeysKt.setScriptMode(configuration, k2JVMCompilerArguments.getScript());
                CLIConfigurationKeysKt.setFreeArgsForScript(configuration, CollectionsKt.plus(CLIConfigurationKeysKt.getFreeArgsForScript(configuration), k2JVMCompilerArguments.getFreeArgs()));
                JVMConfigurationKeysKt.setExpressionToEvaluate(configuration, k2JVMCompilerArguments.getExpression());
                CLIConfigurationKeysKt.setDefaultExtensionForScripts(configuration, k2JVMCompilerArguments.getDefaultScriptExtension());
            } else {
                CLIConfigurationKeysKt.setReplMode(configuration, k2JVMCompilerArguments.getRepl());
                CLIConfigurationKeysKt.setFreeArgsForScript(configuration, CollectionsKt.plus(CLIConfigurationKeysKt.getFreeArgsForScript(configuration), k2JVMCompilerArguments.getFreeArgs()));
            }
            JvmContentRootsKt.configureJdkClasspathRoots(configuration);
            CommonConfigurationKeysKt.setTargetPlatform(configuration, JvmPlatforms.INSTANCE.getDefaultJvmPlatform());
        }
    }
}
