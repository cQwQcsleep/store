package org.jetbrains.kotlin.diagnostics.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"deduplicating", "Lorg/jetbrains/kotlin/diagnostics/impl/DeduplicatingDiagnosticReporter;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeduplicatingDiagnosticReporterKt {
    public static final DeduplicatingDiagnosticReporter deduplicating(DiagnosticReporter diagnosticReporter) {
        diagnosticReporter.getClass();
        return diagnosticReporter instanceof DeduplicatingDiagnosticReporter ? (DeduplicatingDiagnosticReporter) diagnosticReporter : new DeduplicatingDiagnosticReporter(diagnosticReporter);
    }
}
