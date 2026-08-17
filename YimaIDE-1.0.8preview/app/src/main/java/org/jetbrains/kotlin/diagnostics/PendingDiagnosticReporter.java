package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "()V", "checkAndCommitReportsOn", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "commitEverything", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PendingDiagnosticReporter extends DiagnosticReporter {
    public abstract void checkAndCommitReportsOn(AbstractKtSourceElement element, DiagnosticContext context, boolean commitEverything);
}
