package org.jetbrains.kotlin.cli.pipeline;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import java.io.IOException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.analyzer.CompilationErrorException;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.CLICompilerKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.environment.UtilKt;
import org.jetbrains.kotlin.cli.common.fir.FirDiagnosticsCompilerResultsReporter;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.GroupingMessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollectorUtil;
import org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.config.phaser.CompilerPhaseKt;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.progress.CompilationCanceledException;
import org.jetbrains.kotlin.progress.CompilationCanceledStatus;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;
import org.jetbrains.kotlin.util.CompilerType;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.UnitStatsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001%B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ/\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0002J+\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0015\u0012\u0002\b\u00030\u00172\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\nH\u0014¢\u0006\u0002\u0010\u001fJ\u0015\u0010 \u001a\u00020!2\u0006\u0010\b\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\"R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010#\u001a\u00020!X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/AbstractCliPipeline;", "A", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", Argument.Delimiters.none, "<init>", "()V", "execute", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "originalMessageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "executeAndReturnPipeLineArtifact", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifactWithExitCode;", "providedDisposable", "Lcom/intellij/openapi/Disposable;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Lcom/intellij/openapi/Disposable;)Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifactWithExitCode;", "runPhasedPipeline", "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "createCompoundPhase", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "getDefaultPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "createPerformanceManager", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;)Lorg/jetbrains/kotlin/util/PerformanceManager;", "isKaptMode", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;)Z", "isNativeOneStage", "()Z", "ExitCodeArtifact", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractCliPipeline<A extends CommonCompilerArguments> {
    private final boolean isNativeOneStage;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0017J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/AbstractCliPipeline$ExitCodeArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifactWithExitCode;", "exitCode", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/ExitCode;)V", "getExitCode", "()Lorg/jetbrains/kotlin/cli/common/ExitCode;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "newConfiguration", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ExitCodeArtifact extends PipelineArtifactWithExitCode {
        private final ExitCode exitCode;

        public ExitCodeArtifact(ExitCode exitCode) {
            exitCode.getClass();
            this.exitCode = exitCode;
        }

        public static /* synthetic */ ExitCodeArtifact copy$default(ExitCodeArtifact exitCodeArtifact, ExitCode exitCode, int i, Object obj) {
            if ((i & 1) != 0) {
                exitCode = exitCodeArtifact.exitCode;
            }
            return exitCodeArtifact.copy(exitCode);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ExitCode getExitCode() {
            return this.exitCode;
        }

        public final ExitCodeArtifact copy(ExitCode exitCode) {
            exitCode.getClass();
            return new ExitCodeArtifact(exitCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ExitCodeArtifact) && this.exitCode == ((ExitCodeArtifact) other).exitCode;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
        public CompilerConfiguration getConfiguration() throws KotlinNothingValueException {
            AddToStdlibKt.shouldNotBeCalled("No Configuration available from this artifact.");
            throw new KotlinNothingValueException();
        }

        @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifactWithExitCode
        public ExitCode getExitCode() {
            return this.exitCode;
        }

        public int hashCode() {
            return this.exitCode.hashCode();
        }

        public String toString() {
            return "ExitCodeArtifact(exitCode=" + this.exitCode + ')';
        }

        @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
        @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
        public PipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
            newConfiguration.getClass();
            return this;
        }
    }

    public static Unit a(GroupingMessageCollector groupingMessageCollector, String str) {
        str.getClass();
        groupingMessageCollector.report(CompilerMessageSeverity.LOGGING, "PERF: ".concat(str), null);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ PipelineArtifactWithExitCode executeAndReturnPipeLineArtifact$default(AbstractCliPipeline abstractCliPipeline, CommonCompilerArguments commonCompilerArguments, Services services, MessageCollector messageCollector, Disposable disposable, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: executeAndReturnPipeLineArtifact");
            return null;
        }
        if ((i & 8) != 0) {
            disposable = null;
        }
        return abstractCliPipeline.executeAndReturnPipeLineArtifact(commonCompilerArguments, services, messageCollector, disposable);
    }

    private static final ExitCodeArtifact executeAndReturnPipeLineArtifact$reportCompilationCanceled(GroupingMessageCollector groupingMessageCollector, CompilationCanceledException compilationCanceledException) {
        CLICompilerKt.reportCompilationCancelled(groupingMessageCollector, compilationCanceledException);
        return new ExitCodeArtifact(ExitCode.OK);
    }

    private static final ExitCodeArtifact executeAndReturnPipeLineArtifact$reportException(GroupingMessageCollector groupingMessageCollector, Throwable th) {
        MessageCollectorUtil.reportException(groupingMessageCollector, th);
        return new ExitCodeArtifact(((th instanceof OutOfMemoryError) || CLICompilerKt.hasOOMCause(th)) ? ExitCode.OOM_ERROR : ExitCode.INTERNAL_ERROR);
    }

    private final PipelineArtifactWithExitCode runPhasedPipeline(ArgumentsPipelineArtifact<? extends A> input) throws IOException {
        ExitCodeArtifact exitCodeArtifact;
        try {
            Object objInvokeToplevel = CompilerPhaseKt.invokeToplevel(createCompoundPhase(input.getArguments()), new PhaseConfig(null, null, null, null, null, null, null, null, false, false, 1023, null), new PipelineContext(input.getPerformanceManager(), isKaptMode(input.getArguments())), input);
            return objInvokeToplevel instanceof PipelineArtifactWithExitCode ? (PipelineArtifactWithExitCode) objInvokeToplevel : new ExitCodeArtifact(ExitCode.OK);
        } catch (PipelineStepException e) {
            exitCodeArtifact = (e.getDefinitelyCompilationError() || CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.checkHasErrors(input.getConfiguration())) ? new ExitCodeArtifact(ExitCode.COMPILATION_ERROR) : new ExitCodeArtifact(ExitCode.OK);
            return exitCodeArtifact;
        } catch (SuccessfulPipelineExecutionException unused) {
            exitCodeArtifact = new ExitCodeArtifact(ExitCode.OK);
            return exitCodeArtifact;
        } finally {
            CompilerConfiguration configuration = input.getConfiguration();
            FirDiagnosticsCompilerResultsReporter.INSTANCE.reportToMessageCollector(CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), CommonConfigurationKeysKt.getMessageCollector(configuration), CLIConfigurationKeysKt.getRenderDiagnosticInternalName(configuration));
        }
    }

    public abstract CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<A>, ?> createCompoundPhase(A arguments);

    public PerformanceManager createPerformanceManager(A arguments, Services services) {
        arguments.getClass();
        services.getClass();
        PerformanceManager defaultPerformanceManager = getDefaultPerformanceManager();
        defaultPerformanceManager.setDetailedPerf(arguments.getDetailedPerf());
        return defaultPerformanceManager;
    }

    public final ExitCode execute(A arguments, Services services, MessageCollector originalMessageCollector) {
        arguments.getClass();
        services.getClass();
        originalMessageCollector.getClass();
        return executeAndReturnPipeLineArtifact$default(this, arguments, services, originalMessageCollector, null, 8, null).getExitCode();
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00e4 A[PHI: r14
      0x00e4: PHI (r14v8 org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline$ExitCodeArtifact) = 
      (r14v3 org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline$ExitCodeArtifact)
      (r14v6 org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline$ExitCodeArtifact)
      (r14v10 org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline$ExitCodeArtifact)
     binds: [B:41:0x00e2, B:50:0x0101, B:54:0x010e] A[DONT_GENERATE, DONT_INLINE]] */
    public final PipelineArtifactWithExitCode executeAndReturnPipeLineArtifact(A arguments, Services services, MessageCollector originalMessageCollector, Disposable providedDisposable) {
        Disposable disposable;
        ExitCodeArtifact exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException;
        arguments.getClass();
        services.getClass();
        originalMessageCollector.getClass();
        ProgressIndicatorAndCompilationCanceledStatus.setCompilationCanceledStatus((CompilationCanceledStatus) services.get(CompilationCanceledStatus.class));
        if (providedDisposable == null) {
            Disposable disposableNewDisposable = Disposer.newDisposable("Disposable for " + Reflection.getOrCreateKotlinClass(CLICompiler.class).getSimpleName() + ".execImpl");
            disposableNewDisposable.getClass();
            disposable = disposableNewDisposable;
        } else {
            disposable = providedDisposable;
        }
        UtilKt.setIdeaIoUseFallback();
        PerformanceManager performanceManagerCreatePerformanceManager = createPerformanceManager(arguments, services);
        performanceManagerCreatePerformanceManager.setCompilerType(CompilerType.K2);
        if (arguments.getReportPerf() || arguments.getDumpPerf() != null) {
            performanceManagerCreatePerformanceManager.enableExtendedStats();
        }
        final GroupingMessageCollector groupingMessageCollector = new GroupingMessageCollector(originalMessageCollector, arguments.getAllWarningsAsErrors(), arguments.getReportAllWarnings());
        try {
            try {
                PipelineArtifactWithExitCode pipelineArtifactWithExitCodeRunPhasedPipeline = runPhasedPipeline(new ArgumentsPipelineArtifact<>(arguments, services, disposable, groupingMessageCollector, performanceManagerCreatePerformanceManager));
                if (!getIsNativeOneStage()) {
                    performanceManagerCreatePerformanceManager.notifyCompilationFinished();
                    if (arguments.getReportPerf()) {
                        try {
                            MessageCollector.report$default(groupingMessageCollector, CompilerMessageSeverity.LOGGING, "PERF: " + performanceManagerCreatePerformanceManager.getTargetInfo(), null, 4, null);
                            UnitStatsKt.forEachStringMeasurement(performanceManagerCreatePerformanceManager, new Function1() { // from class: il
                                public final Object invoke(Object obj) {
                                    return AbstractCliPipeline.a(groupingMessageCollector, (String) obj);
                                }
                            });
                        } catch (CompilationErrorException unused) {
                            groupingMessageCollector = groupingMessageCollector;
                            exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException = new ExitCodeArtifact(ExitCode.COMPILATION_ERROR);
                            groupingMessageCollector.flush();
                            if (providedDisposable == null) {
                                UtilsKt.disposeRootInWriteAction(disposable);
                            }
                            return exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException;
                        } catch (RuntimeException e) {
                            e = e;
                            groupingMessageCollector = groupingMessageCollector;
                            RuntimeException runtimeException = e;
                            Object cause = runtimeException.getCause();
                            exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException = cause instanceof CompilationCanceledException ? executeAndReturnPipeLineArtifact$reportCompilationCanceled(groupingMessageCollector, (CompilationCanceledException) cause) : executeAndReturnPipeLineArtifact$reportException(groupingMessageCollector, runtimeException);
                            groupingMessageCollector.flush();
                            if (providedDisposable == null) {
                                UtilsKt.disposeRootInWriteAction(disposable);
                            }
                            return exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException;
                        } catch (Throwable th) {
                            th = th;
                            groupingMessageCollector = groupingMessageCollector;
                            exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException = executeAndReturnPipeLineArtifact$reportException(groupingMessageCollector, th);
                            groupingMessageCollector.flush();
                            if (providedDisposable == null) {
                                UtilsKt.disposeRootInWriteAction(disposable);
                            }
                            return exitCodeArtifactExecuteAndReturnPipeLineArtifact$reportException;
                        }
                    }
                    if (arguments.getDumpPerf() != null) {
                        String dumpPerf = arguments.getDumpPerf();
                        dumpPerf.getClass();
                        performanceManagerCreatePerformanceManager.dumpPerformanceReport(dumpPerf);
                    }
                }
                if (groupingMessageCollector.hasErrors()) {
                    pipelineArtifactWithExitCodeRunPhasedPipeline = new ExitCodeArtifact(ExitCode.COMPILATION_ERROR);
                }
                groupingMessageCollector.flush();
                if (providedDisposable == null) {
                    UtilsKt.disposeRootInWriteAction(disposable);
                }
                return pipelineArtifactWithExitCodeRunPhasedPipeline;
            } catch (Throwable th2) {
                groupingMessageCollector.flush();
                if (providedDisposable != null) {
                    throw th2;
                }
                UtilsKt.disposeRootInWriteAction(disposable);
                throw th2;
            }
        } catch (RuntimeException e2) {
            e = e2;
        } catch (CompilationErrorException unused2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public abstract PerformanceManager getDefaultPerformanceManager();

    public boolean isKaptMode(A arguments) {
        arguments.getClass();
        return false;
    }

    /* JADX INFO: renamed from: isNativeOneStage, reason: from getter */
    public boolean getIsNativeOneStage() {
        return this.isNativeOneStage;
    }
}
