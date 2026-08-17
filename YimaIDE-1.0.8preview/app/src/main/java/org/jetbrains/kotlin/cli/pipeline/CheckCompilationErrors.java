package org.jetbrains.kotlin.cli.pipeline;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.fir.FirDiagnosticsCompilerResultsReporter;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.ActionState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002*\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u0006:\u0001\tB\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/CheckCompilationErrors;", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/Action;", "<init>", "()V", "CheckDiagnosticCollector", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CheckCompilationErrors implements Function3<ActionState, PipelineArtifact, PipelineContext, Unit> {

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0082\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/CheckCompilationErrors$CheckDiagnosticCollector;", "Lorg/jetbrains/kotlin/cli/pipeline/CheckCompilationErrors;", "<init>", "()V", "invoke", Argument.Delimiters.none, "state", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "output", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "c", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "checkHasErrorsAndReportToMessageCollector", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "reportToMessageCollector", "checkHasErrors", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CheckDiagnosticCollector extends CheckCompilationErrors {
        public static final CheckDiagnosticCollector INSTANCE = new CheckDiagnosticCollector();

        private CheckDiagnosticCollector() {
        }

        public final boolean checkHasErrors(CompilerConfiguration configuration) {
            configuration.getClass();
            if (CLIConfigurationKeysKt.getDiagnosticsCollector(configuration).getHasErrors() || CommonConfigurationKeysKt.getMessageCollector(configuration).hasErrors()) {
                return true;
            }
            if (CLIConfigurationKeysKt.getTreatWarningsAsErrors(configuration)) {
                return CLIConfigurationKeysKt.getDiagnosticsCollector(configuration).getHasWarningsForWError();
            }
            return false;
        }

        public final boolean checkHasErrorsAndReportToMessageCollector(CompilerConfiguration configuration) throws IOException {
            configuration.getClass();
            if (!checkHasErrors(configuration)) {
                return false;
            }
            reportToMessageCollector(configuration);
            return true;
        }

        public void invoke(ActionState state, PipelineArtifact output, PipelineContext c) {
            state.getClass();
            output.getClass();
            c.getClass();
            if (checkHasErrors(output.getConfiguration())) {
                throw new PipelineStepException(false, 1, null);
            }
        }

        public final void reportToMessageCollector(CompilerConfiguration configuration) throws IOException {
            configuration.getClass();
            FirDiagnosticsCompilerResultsReporter.INSTANCE.reportToMessageCollector(CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), CommonConfigurationKeysKt.getMessageCollector(configuration), CLIConfigurationKeysKt.getRenderDiagnosticInternalName(configuration));
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ActionState) obj, (PipelineArtifact) obj2, (PipelineContext) obj3);
            return Unit.INSTANCE;
        }
    }
}
