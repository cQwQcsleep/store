package org.jetbrains.kotlin.cli.pipeline.jvm;

import com.intellij.mock.MockProject;
import com.intellij.openapi.Disposable;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.extensions.ScriptEvaluationExtension;
import org.jetbrains.kotlin.cli.common.extensions.ShellExtension;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.jvm.JvmScriptPipelinePhase;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmScriptPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmScriptPipelineArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-jvm", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$ProjectEnvironment;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmScriptPipelinePhase extends PipelinePhase<ConfigurationPipelineArtifact, JvmScriptPipelineArtifact> {
    public static final JvmScriptPipelinePhase INSTANCE = new JvmScriptPipelinePhase();

    private JvmScriptPipelinePhase() {
        super("JvmScriptPipelinePhase", null, null, 6, null);
    }

    public static KotlinCoreEnvironment.ProjectEnvironment a(Disposable disposable, CompilerConfiguration compilerConfiguration) {
        KotlinCoreEnvironment.ProjectEnvironment projectEnvironment = new KotlinCoreEnvironment.ProjectEnvironment(disposable, KotlinCoreEnvironment.INSTANCE.getOrCreateApplicationEnvironmentForProduction(disposable, compilerConfiguration), compilerConfiguration);
        projectEnvironment.registerExtensionsFromPlugins(compilerConfiguration);
        return projectEnvironment;
    }

    private static final KotlinCoreEnvironment.ProjectEnvironment executePhase$lambda$1(Lazy<KotlinCoreEnvironment.ProjectEnvironment> lazy) {
        return (KotlinCoreEnvironment.ProjectEnvironment) lazy.getValue();
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JvmScriptPipelineArtifact executePhase(ConfigurationPipelineArtifact input) {
        Object next;
        ExitCode exitCodeEval;
        Object next2;
        input.getClass();
        final CompilerConfiguration configuration = input.getConfiguration();
        final Disposable rootDisposable = input.getRootDisposable();
        if (CLIConfigurationKeysKt.getScriptMode(configuration) && CLIConfigurationKeysKt.getFreeArgsForScript(configuration).isEmpty()) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getSCRIPTING_ERROR(), "Specify script source path to evaluate", null, 4, null);
            return null;
        }
        if (CLIConfigurationKeysKt.getReplMode(configuration) && !CLIConfigurationKeysKt.getFreeArgsForScript(configuration).isEmpty()) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getSCRIPTING_WARNING(), "The arguments are ignored in the REPL mode", null, 4, null);
        }
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: d08
            public final Object invoke() {
                return JvmScriptPipelinePhase.a(rootDisposable, configuration);
            }
        });
        if (CLIConfigurationKeysKt.getScriptMode(configuration) || JVMConfigurationKeysKt.getExpressionToEvaluate(configuration) != null) {
            K2JVMCompilerArguments k2JVMCompilerArguments = new K2JVMCompilerArguments();
            k2JVMCompilerArguments.setScript(true);
            ScriptEvaluationExtension.Companion companion = ScriptEvaluationExtension.INSTANCE;
            MockProject project = executePhase$lambda$1(lazy).getProject();
            project.getClass();
            Iterator<T> it = companion.getInstances(project).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!((ScriptEvaluationExtension) next).isAccepted(k2JVMCompilerArguments));
            ScriptEvaluationExtension scriptEvaluationExtension = (ScriptEvaluationExtension) next;
            if (scriptEvaluationExtension == null) {
                CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getSCRIPTING_ERROR(), "Unable to evaluate script, no scripting plugin loaded", null, 4, null);
                return null;
            }
            exitCodeEval = scriptEvaluationExtension.eval(configuration, executePhase$lambda$1(lazy));
        } else {
            if (!CLIConfigurationKeysKt.getReplMode(configuration)) {
                CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getSCRIPTING_ERROR(), "Kotlin REPL is deprecated and should be enabled explicitly for now; please use the '-Xrepl' option", null, 4, null);
                return null;
            }
            K2JVMCompilerArguments k2JVMCompilerArguments2 = new K2JVMCompilerArguments();
            ShellExtension.Companion companion2 = ShellExtension.INSTANCE;
            MockProject project2 = executePhase$lambda$1(lazy).getProject();
            project2.getClass();
            Iterator<T> it2 = companion2.getInstances(project2).iterator();
            do {
                if (!it2.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it2.next();
            } while (!((ShellExtension) next2).isAccepted(k2JVMCompilerArguments2));
            ShellExtension shellExtension = (ShellExtension) next2;
            if (shellExtension == null) {
                CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getSCRIPTING_ERROR(), "Unable to run REPL, no scripting plugin loaded", null, 4, null);
                return null;
            }
            exitCodeEval = shellExtension.run(k2JVMCompilerArguments2, configuration, executePhase$lambda$1(lazy));
        }
        return new JvmScriptPipelineArtifact(exitCodeEval, configuration);
    }
}
