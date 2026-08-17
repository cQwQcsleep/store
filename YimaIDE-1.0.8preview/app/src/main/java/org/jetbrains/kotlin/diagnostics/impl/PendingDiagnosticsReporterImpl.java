package org.jetbrains.kotlin.diagnostics.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/impl/PendingDiagnosticsReporterImpl;", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "delegate", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "pendingDiagnosticsBySourceFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "hasErrors", Argument.Delimiters.none, "getHasErrors", "()Z", "hasWarningsForWError", "getHasWarningsForWError", "report", Argument.Delimiters.none, "diagnostic", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "checkAndCommitReportsOn", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "commitEverything", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PendingDiagnosticsReporterImpl extends PendingDiagnosticReporter {
    private final DiagnosticReporter delegate;
    private final Map<KtSourceFile, List<KtDiagnostic>> pendingDiagnosticsBySourceFile;

    public PendingDiagnosticsReporterImpl(DiagnosticReporter diagnosticReporter) {
        diagnosticReporter.getClass();
        this.delegate = diagnosticReporter;
        this.pendingDiagnosticsBySourceFile = new LinkedHashMap();
    }

    @Override // org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter
    public void checkAndCommitReportsOn(AbstractKtSourceElement element, DiagnosticContext context, boolean commitEverything) {
        element.getClass();
        context.getClass();
        if (this.pendingDiagnosticsBySourceFile.isEmpty()) {
            return;
        }
        KtSourceFile containingFile = context.getContainingFile();
        Iterator<Map.Entry<KtSourceFile, List<KtDiagnostic>>> it = this.pendingDiagnosticsBySourceFile.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<KtSourceFile, List<KtDiagnostic>> next = it.next();
            KtSourceFile key = next.getKey();
            List<KtDiagnostic> value = next.getValue();
            if (containingFile != null) {
                Intrinsics.areEqual(key, containingFile);
            }
            Iterator<KtDiagnostic> it2 = value.iterator();
            while (it2.hasNext()) {
                KtDiagnostic next2 = it2.next();
                KtDiagnosticWithSource ktDiagnosticWithSource = next2 instanceof KtDiagnosticWithSource ? (KtDiagnosticWithSource) next2 : null;
                AbstractKtSourceElement element2 = ktDiagnosticWithSource != null ? ktDiagnosticWithSource.getElement() : null;
                if (context.isDiagnosticSuppressed(next2)) {
                    if (element2 != null && (Intrinsics.areEqual(element2, element) || (element2.getStartOffset() >= element.getStartOffset() && element2.getEndOffset() <= element.getEndOffset()))) {
                        it2.remove();
                    }
                } else if (Intrinsics.areEqual(element2, element) || commitEverything) {
                    it2.remove();
                    this.delegate.report(next2, context);
                }
            }
            if (value.isEmpty()) {
                it.remove();
            }
        }
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
        if (diagnostic == null) {
            return;
        }
        KtSourceFile containingFile = context.getContainingFile();
        if (containingFile == null) {
            this.delegate.report(diagnostic, context);
            return;
        }
        if (context.isDiagnosticSuppressed(diagnostic)) {
            return;
        }
        Map<KtSourceFile, List<KtDiagnostic>> map = this.pendingDiagnosticsBySourceFile;
        List<KtDiagnostic> arrayList = map.get(containingFile);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(containingFile, arrayList);
        }
        arrayList.add(diagnostic);
    }
}
