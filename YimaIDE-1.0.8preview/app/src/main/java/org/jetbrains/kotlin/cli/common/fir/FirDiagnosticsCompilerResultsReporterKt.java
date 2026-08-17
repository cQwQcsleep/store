package org.jetbrains.kotlin.cli.common.fir;

import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"reportToMessageCollector", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "renderDiagnosticName", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDiagnosticsCompilerResultsReporterKt {
    public static final void reportToMessageCollector(BaseDiagnosticsCollector baseDiagnosticsCollector, MessageCollector messageCollector, boolean z) throws IOException {
        baseDiagnosticsCollector.getClass();
        messageCollector.getClass();
        FirDiagnosticsCompilerResultsReporter.INSTANCE.reportToMessageCollector(baseDiagnosticsCollector, messageCollector, z);
    }
}
