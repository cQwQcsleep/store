package org.jetbrains.kotlin.diagnostics.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Triple;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryN;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithoutSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR(\u0010\u000b\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/impl/DeduplicatingDiagnosticReporter;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "delegate", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "hasErrors", Argument.Delimiters.none, "getHasErrors", "()Z", "hasWarningsForWError", "getHasWarningsForWError", "reported", Argument.Delimiters.none, "Lkotlin/Triple;", "Lorg/jetbrains/kotlin/KtSourceFile;", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "report", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeduplicatingDiagnosticReporter extends DiagnosticReporter {
    private final DiagnosticReporter delegate;
    private final Set<Triple<KtSourceFile, AbstractKtSourceElement, KtDiagnosticFactoryN>> reported;

    public DeduplicatingDiagnosticReporter(DiagnosticReporter diagnosticReporter) {
        diagnosticReporter.getClass();
        this.delegate = diagnosticReporter;
        this.reported = new LinkedHashSet();
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public boolean getHasErrors() {
        return this.delegate.getHasErrors();
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public boolean getHasWarningsForWError() {
        return this.delegate.getHasWarningsForWError();
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public void report(KtDiagnostic diagnostic, DiagnosticContext context) {
        context.getClass();
        if (diagnostic != null) {
            if (diagnostic instanceof KtDiagnosticWithoutSource) {
                this.delegate.report(diagnostic, context);
                return;
            }
            if (!(diagnostic instanceof KtDiagnosticWithSource)) {
                bu8.a();
                return;
            }
            KtDiagnosticWithSource ktDiagnosticWithSource = (KtDiagnosticWithSource) diagnostic;
            if (this.reported.add(new Triple<>(context.getContainingFile(), ktDiagnosticWithSource.getElement(), ktDiagnosticWithSource.getFactory()))) {
                this.delegate.report(diagnostic, context);
            }
        }
    }
}
