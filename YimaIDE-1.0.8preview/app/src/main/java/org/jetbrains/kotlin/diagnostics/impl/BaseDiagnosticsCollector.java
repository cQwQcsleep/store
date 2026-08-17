package org.jetbrains.kotlin.diagnostics.impl;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "()V", "diagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "getDiagnostics", "()Ljava/util/List;", "diagnosticsByFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "getDiagnosticsByFile", "()Ljava/util/Map;", "DoNothing", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class BaseDiagnosticsCollector extends DiagnosticReporter {

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR(\u0010\t\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector$DoNothing;", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "<init>", "()V", "diagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "getDiagnostics", "()Ljava/util/List;", "diagnosticsByFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "getDiagnosticsByFile", "()Ljava/util/Map;", "report", Argument.Delimiters.none, "diagnostic", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "hasErrors", Argument.Delimiters.none, "getHasErrors", "()Z", "hasWarningsForWError", "getHasWarningsForWError", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DoNothing extends BaseDiagnosticsCollector {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
        }

        @Override // org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector
        public List<KtDiagnostic> getDiagnostics() {
            return CollectionsKt.emptyList();
        }

        @Override // org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector
        public Map<KtSourceFile, List<KtDiagnostic>> getDiagnosticsByFile() {
            return MapsKt.emptyMap();
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
        public boolean getHasErrors() {
            return false;
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
        public boolean getHasWarningsForWError() {
            return false;
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
        public void report(KtDiagnostic diagnostic, DiagnosticContext context) {
            context.getClass();
        }
    }

    public abstract List<KtDiagnostic> getDiagnostics();

    public abstract Map<KtSourceFile, List<KtDiagnostic>> getDiagnosticsByFile();
}
