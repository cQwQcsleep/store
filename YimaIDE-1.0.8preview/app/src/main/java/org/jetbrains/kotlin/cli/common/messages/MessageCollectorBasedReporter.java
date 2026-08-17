package org.jetbrains.kotlin.cli.common.messages;

import com.intellij.psi.PsiFile;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/MessageCollectorBasedReporter;", "Lorg/jetbrains/kotlin/cli/common/messages/DiagnosticMessageReporter;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "report", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "file", "Lcom/intellij/psi/PsiFile;", "render", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface MessageCollectorBasedReporter extends DiagnosticMessageReporter {
    MessageCollector getMessageCollector();

    @Override // org.jetbrains.kotlin.cli.common.messages.DiagnosticMessageReporter
    default void report(Diagnostic diagnostic, PsiFile file, String render) {
        diagnostic.getClass();
        file.getClass();
        render.getClass();
        getMessageCollector().report(diagnostic.getSeverity().toCompilerMessageSeverity(), render, MessageUtil.psiFileToMessageLocation(file, file.getName(), DiagnosticUtils.getLineAndColumnRange(diagnostic)));
    }
}
