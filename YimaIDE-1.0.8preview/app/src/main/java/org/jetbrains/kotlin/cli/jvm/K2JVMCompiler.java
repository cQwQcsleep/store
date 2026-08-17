package org.jetbrains.kotlin.cli.jvm;

import com.intellij.mock.MockProject;
import com.intellij.openapi.Disposable;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.K1Deprecation;
import org.jetbrains.kotlin.backend.jvm.JvmPhasesKt;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.CreatePhaseConfigKt;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.extensions.ScriptEvaluationExtension;
import org.jetbrains.kotlin.cli.common.extensions.ShellExtension;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageUtil;
import org.jetbrains.kotlin.cli.common.messages.OutputMessageUtil;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.cli.common.profiling.ProfilingCompilerPerformanceManager;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompilerKt;
import org.jetbrains.kotlin.cli.jvm.config.ClassicFrontendSpecificJvmConfigurationKeys;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.jvm.JvmCliPipeline;
import org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase;
import org.jetbrains.kotlin.codegen.CompilationException;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
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
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J*\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u001a\u0010\u0017\u001a\u00020\u0018*\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u000b\u001a\u00020\u0002H\u0014J \u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u001c\u001a\u00020\u0002H\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0014J\u0018\u0010\"\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0014R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/K2JVMCompiler;", "Lorg/jetbrains/kotlin/cli/common/CLICompiler;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "<init>", "()V", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "doExecutePhased", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "basicMessageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "doExecute", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "addPlatformOptions", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "setupPlatformSpecificArgumentsAndServices", "createArguments", "executableScriptFileName", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "createPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "Companion", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JVMCompiler extends CLICompiler<K2JVMCompilerArguments> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final void main(String[] strArr) {
        INSTANCE.main(strArr);
    }

    public void addPlatformOptions(List<String> list, K2JVMCompilerArguments k2JVMCompilerArguments) {
        list.getClass();
        k2JVMCompilerArguments.getClass();
        String[] scriptTemplates = k2JVMCompilerArguments.getScriptTemplates();
        if (scriptTemplates != null) {
            if (!(scriptTemplates.length == 0)) {
                StringBuilder sb = new StringBuilder("plugin:kotlin.scripting:script-templates=");
                String[] scriptTemplates2 = k2JVMCompilerArguments.getScriptTemplates();
                scriptTemplates2.getClass();
                sb.append(ArraysKt.joinToString$default(scriptTemplates2, Argument.Delimiters.default, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                list.add(sb.toString());
            }
        }
        String[] scriptResolverEnvironment = k2JVMCompilerArguments.getScriptResolverEnvironment();
        if (scriptResolverEnvironment != null) {
            if (!(scriptResolverEnvironment.length == 0)) {
                StringBuilder sb2 = new StringBuilder("plugin:kotlin.scripting:script-resolver-environment=");
                String[] scriptResolverEnvironment2 = k2JVMCompilerArguments.getScriptResolverEnvironment();
                scriptResolverEnvironment2.getClass();
                sb2.append(ArraysKt.joinToString$default(scriptResolverEnvironment2, Argument.Delimiters.default, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                list.add(sb2.toString());
            }
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public K2JVMCompilerArguments createArguments() {
        K2JVMCompilerArguments k2JVMCompilerArguments = new K2JVMCompilerArguments();
        if (System.getenv("KOTLIN_REPORT_PERF") != null) {
            k2JVMCompilerArguments.setReportPerf(true);
        }
        return k2JVMCompilerArguments;
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public BinaryVersion createMetadataVersion(int[] versionArray) {
        versionArray.getClass();
        return new MetadataVersion(Arrays.copyOf(versionArray, versionArray.length));
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public PerformanceManager createPerformanceManager(K2JVMCompilerArguments arguments, Services services) {
        arguments.getClass();
        services.getClass();
        PerformanceManager performanceManagerCreateCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm = INSTANCE.createCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm(arguments, services);
        return performanceManagerCreateCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm == null ? getDefaultPerformanceManager() : performanceManagerCreateCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecute(K2JVMCompilerArguments arguments, CompilerConfiguration configuration, Disposable rootDisposable, KotlinPaths paths) throws IOException {
        arguments.getClass();
        configuration.getClass();
        rootDisposable.getClass();
        MessageCollector messageCollector = (MessageCollector) configuration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY);
        PhaseConfig phaseConfigCreatePhaseConfig = CreatePhaseConfigKt.createPhaseConfig(arguments, JvmPhasesKt.getJvmPhases());
        if (arguments.getListPhases()) {
            CreatePhaseConfigKt.listPhases(phaseConfigCreatePhaseConfig, JvmPhasesKt.getJvmPhases());
        }
        CommonConfigurationKeysKt.setPhaseConfig(configuration, phaseConfigCreatePhaseConfig);
        if (!JvmArgumentsKt.configureJdkHome(configuration, arguments)) {
            return ExitCode.COMPILATION_ERROR;
        }
        configuration.put(JVMConfigurationKeys.DISABLE_STANDARD_SCRIPT_DEFINITION, Boolean.valueOf(arguments.getDisableStandardScript()));
        ExitCode exitCodeLoadPlugins = loadPlugins(paths, arguments, configuration, rootDisposable);
        ExitCode exitCode = ExitCode.OK;
        if (exitCodeLoadPlugins != exitCode) {
            return exitCodeLoadPlugins;
        }
        String moduleName = arguments.getModuleName();
        if (moduleName == null) {
            moduleName = "main";
        }
        configuration.put(CommonConfigurationKeys.MODULE_NAME, moduleName);
        JvmArgumentsKt.configureJavaModulesContentRoots(configuration, arguments);
        JvmArgumentsKt.configureStandardLibs(configuration, paths, arguments);
        JvmArgumentsKt.configureAdvancedJvmOptions(configuration, arguments);
        JvmArgumentsKt.configureKlibPaths(configuration, arguments);
        Object obj = null;
        Object obj2 = null;
        if (arguments.getBuildFile() != null || arguments.getVersion() || arguments.getAllowNoSourceFiles() || !(arguments.getScript() || arguments.getExpression() != null || arguments.getRepl() || arguments.getFreeArgs().isEmpty())) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.LOGGING, "Configuring the compilation environment", null, 4, null);
            try {
                String buildFile = arguments.getBuildFile();
                File file = buildFile != null ? new File(buildFile) : null;
                ModuleChunk moduleChunkConfigureModuleChunk = K2JVMCompilerKt.configureModuleChunk(configuration, arguments, file);
                List<Module> modules = moduleChunkConfigureModuleChunk.getModules();
                modules.getClass();
                KotlinToJVMBytecodeCompilerKt.configureSourceRoots(configuration, modules, file);
                JvmContentRootsKt.configureJdkClasspathRoots(configuration);
                String str = (String) configuration.get(CommonConfigurationKeys.DUMP_MODEL);
                KotlinCoreEnvironment kotlinCoreEnvironmentCreateCoreEnvironment = INSTANCE.createCoreEnvironment(rootDisposable, configuration, K2JVMCompilerKt.targetDescription(moduleChunkConfigureModuleChunk));
                if (kotlinCoreEnvironmentCreateCoreEnvironment == null) {
                    PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
                    if (perfManager != null) {
                        perfManager.notifyPhaseFinished(PhaseType.Initialization);
                    }
                    return ExitCode.COMPILATION_ERROR;
                }
                if (kotlinCoreEnvironmentCreateCoreEnvironment.getSourceFiles().isEmpty() && !arguments.getAllowNoSourceFiles() && file == null) {
                    CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.reportToMessageCollector(configuration);
                    if (!arguments.getVersion()) {
                        MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "No source files", null, 4, null);
                        return ExitCode.COMPILATION_ERROR;
                    }
                } else {
                    if (str != null) {
                        JvmFrontendPipelinePhase.INSTANCE.dumpModel(str, modules, configuration, arguments);
                    }
                    boolean zCompileModules$org_jetbrains_kotlin_cli_jvm = KotlinToJVMBytecodeCompiler.INSTANCE.compileModules$org_jetbrains_kotlin_cli_jvm(kotlinCoreEnvironmentCreateCoreEnvironment, file, modules);
                    CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.reportToMessageCollector(configuration);
                    if (!zCompileModules$org_jetbrains_kotlin_cli_jvm) {
                        return ExitCode.COMPILATION_ERROR;
                    }
                }
                return exitCode;
            } catch (CompilationException e) {
                CompilerMessageSeverity compilerMessageSeverity = CompilerMessageSeverity.EXCEPTION;
                String strRenderException = OutputMessageUtil.renderException(e);
                strRenderException.getClass();
                messageCollector.report(compilerMessageSeverity, strRenderException, MessageUtil.psiElementToMessageLocation(e.getElement()));
                CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.reportToMessageCollector(configuration);
                return ExitCode.INTERNAL_ERROR;
            }
        }
        JvmArgumentsKt.configureContentRootsFromClassPath(configuration, arguments);
        JvmContentRootsKt.configureJdkClasspathRoots(configuration);
        if (arguments.getScript() && arguments.getFreeArgs().isEmpty()) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Specify script source path to evaluate", null, 4, null);
            return ExitCode.COMPILATION_ERROR;
        }
        KotlinCoreEnvironment.ProjectEnvironment projectEnvironment = new KotlinCoreEnvironment.ProjectEnvironment(rootDisposable, KotlinCoreEnvironment.INSTANCE.getOrCreateApplicationEnvironmentForProduction(rootDisposable, configuration), configuration);
        projectEnvironment.registerExtensionsFromPlugins(configuration);
        if (arguments.getScript() || arguments.getExpression() != null) {
            ScriptEvaluationExtension.Companion companion = ScriptEvaluationExtension.INSTANCE;
            MockProject project = projectEnvironment.getProject();
            project.getClass();
            for (Object obj3 : companion.getInstances(project)) {
                if (((ScriptEvaluationExtension) obj3).isAccepted(arguments)) {
                    obj2 = obj3;
                    break;
                }
            }
            ScriptEvaluationExtension scriptEvaluationExtension = (ScriptEvaluationExtension) obj2;
            if (scriptEvaluationExtension != null) {
                return scriptEvaluationExtension.eval(arguments, configuration, projectEnvironment);
            }
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Unable to evaluate script, no scripting plugin loaded", null, 4, null);
            return ExitCode.COMPILATION_ERROR;
        }
        if (!arguments.getRepl()) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Kotlin REPL is deprecated and should be enabled explicitly for now; please use the '-Xrepl' option", null, 4, null);
            return ExitCode.COMPILATION_ERROR;
        }
        if (!arguments.getFreeArgs().isEmpty()) {
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.STRONG_WARNING, "The arguments are ignored in the REPL mode", null, 4, null);
        }
        ShellExtension.Companion companion2 = ShellExtension.INSTANCE;
        MockProject project2 = projectEnvironment.getProject();
        project2.getClass();
        for (Object obj4 : companion2.getInstances(project2)) {
            if (((ShellExtension) obj4).isAccepted(arguments)) {
                obj = obj4;
                break;
            }
        }
        ShellExtension shellExtension = (ShellExtension) obj;
        if (shellExtension != null) {
            return shellExtension.run(arguments, configuration, projectEnvironment);
        }
        MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "Unable to run REPL, no scripting plugin loaded", null, 4, null);
        return ExitCode.COMPILATION_ERROR;
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecutePhased(K2JVMCompilerArguments arguments, Services services, MessageCollector basicMessageCollector) {
        arguments.getClass();
        services.getClass();
        basicMessageCollector.getClass();
        return new JvmCliPipeline(getDefaultPerformanceManager()).execute(arguments, services, basicMessageCollector);
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public String executableScriptFileName() {
        return "kotlinc-jvm";
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public TargetPlatform getPlatform() {
        return JvmPlatforms.INSTANCE.getDefaultJvmPlatform();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public void setupPlatformSpecificArgumentsAndServices(CompilerConfiguration configuration, K2JVMCompilerArguments arguments, Services services) {
        configuration.getClass();
        arguments.getClass();
        services.getClass();
        configuration.putIfNotNull(CommonConfigurationKeys.LOOKUP_TRACKER, services.get(LookupTracker.class));
        if (UtilsKt.incrementalCompilationIsEnabled(arguments)) {
            configuration.putIfNotNull(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER, services.get(ExpectActualTracker.class));
            configuration.putIfNotNull(CommonConfigurationKeys.INLINE_CONST_TRACKER, services.get(InlineConstTracker.class));
            configuration.putIfNotNull(CommonConfigurationKeys.ENUM_WHEN_TRACKER, services.get(EnumWhenTracker.class));
            configuration.putIfNotNull(CommonConfigurationKeys.IMPORT_TRACKER, services.get(ImportTracker.class));
            configuration.putIfNotNull(CommonConfigurationKeys.FILE_MAPPING_TRACKER, services.get(ICFileMappingTracker.class));
            configuration.putIfNotNull(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS, services.get(IncrementalCompilationComponents.class));
            configuration.putIfNotNull(ClassicFrontendSpecificJvmConfigurationKeys.JAVA_CLASSES_TRACKER, services.get(JavaClassesTracker.class));
        }
        JvmArgumentsKt.setupJvmSpecificArguments(configuration, arguments);
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0007J\u001f\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/K2JVMCompiler$Companion;", Argument.Delimiters.none, "<init>", "()V", "main", Argument.Delimiters.none, "args", Argument.Delimiters.none, Argument.Delimiters.none, "([Ljava/lang/String;)V", "createCoreEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "targetDescription", "createCustomPerformanceManagerOrNull", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "services", "Lorg/jetbrains/kotlin/config/Services;", "createCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @K1Deprecation
        public final KotlinCoreEnvironment createCoreEnvironment(Disposable rootDisposable, CompilerConfiguration configuration, String targetDescription) {
            rootDisposable.getClass();
            configuration.getClass();
            targetDescription.getClass();
            PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
            if (perfManager != null) {
                perfManager.setTargetDescription(targetDescription);
            }
            CheckCompilationErrors.CheckDiagnosticCollector checkDiagnosticCollector = CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE;
            if (checkDiagnosticCollector.checkHasErrors(configuration)) {
                return null;
            }
            KotlinCoreEnvironment kotlinCoreEnvironmentCreateForProduction = KotlinCoreEnvironment.INSTANCE.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES);
            List<KtFile> sourceFiles = kotlinCoreEnvironmentCreateForProduction.getSourceFiles();
            if (perfManager != null) {
                perfManager.addSourcesStats(sourceFiles.size(), kotlinCoreEnvironmentCreateForProduction.countLinesOfCode(sourceFiles));
            }
            if (checkDiagnosticCollector.checkHasErrors(configuration)) {
                return null;
            }
            return kotlinCoreEnvironmentCreateForProduction;
        }

        public final PerformanceManager createCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm(K2JVMCompilerArguments arguments, Services services) {
            arguments.getClass();
            services.getClass();
            PerformanceManager performanceManager = (PerformanceManager) services.get(PerformanceManager.class);
            if (performanceManager != null) {
                return performanceManager;
            }
            String profileCompilerCommand = arguments.getProfileCompilerCommand();
            if (profileCompilerCommand == null) {
                return null;
            }
            return ProfilingCompilerPerformanceManager.INSTANCE.create(profileCompilerCommand, arguments.getDetailedPerf());
        }

        @JvmStatic
        public final void main(String[] args) {
            args.getClass();
            CLICompiler.INSTANCE.doMain(new K2JVMCompiler(), args);
        }

        private Companion() {
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public /* bridge */ /* synthetic */ void addPlatformOptions(List list, CommonCompilerArguments commonCompilerArguments) {
        addPlatformOptions((List<String>) list, (K2JVMCompilerArguments) commonCompilerArguments);
    }
}
