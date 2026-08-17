package org.jetbrains.kotlin.diagnostics.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR(\u0010\t\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/impl/DiagnosticsCollectorImpl;", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "<init>", "()V", "diagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "getDiagnostics", "()Ljava/util/List;", "diagnosticsByFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "getDiagnosticsByFile", "()Ljava/util/Map;", "value", Argument.Delimiters.none, "hasErrors", "getHasErrors", "()Z", "hasWarningsForWError", "getHasWarningsForWError", "report", Argument.Delimiters.none, "diagnostic", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticsCollectorImpl extends BaseDiagnosticsCollector {
    private final Map<KtSourceFile, List<KtDiagnostic>> diagnosticsByFile = new LinkedHashMap();
    private boolean hasErrors;
    private boolean hasWarningsForWError;

    @Override // org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector
    public List<KtDiagnostic> getDiagnostics() {
        Map<KtSourceFile, List<KtDiagnostic>> diagnosticsByFile = getDiagnosticsByFile();
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<KtSourceFile, List<KtDiagnostic>>> it = diagnosticsByFile.entrySet().iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, it.next().getValue());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector
    public Map<KtSourceFile, List<KtDiagnostic>> getDiagnosticsByFile() {
        return this.diagnosticsByFile;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public boolean getHasErrors() {
        return this.hasErrors;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public boolean getHasWarningsForWError() {
        return this.hasWarningsForWError;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public void report(KtDiagnostic diagnostic, DiagnosticContext context) {
        context.getClass();
        if (diagnostic == null || context.isDiagnosticSuppressed(diagnostic)) {
            return;
        }
        Map<KtSourceFile, List<KtDiagnostic>> diagnosticsByFile = getDiagnosticsByFile();
        KtSourceFile containingFile = context.getContainingFile();
        List<KtDiagnostic> arrayList = diagnosticsByFile.get(containingFile);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            diagnosticsByFile.put(containingFile, arrayList);
        }
        arrayList.add(diagnostic);
        boolean z = true;
        this.hasErrors = getHasErrors() || diagnostic.getSeverity().isError();
        if (!getHasWarningsForWError() && !diagnostic.getSeverity().isErrorWhenWError()) {
            z = false;
        }
        this.hasWarningsForWError = z;
    }
}
