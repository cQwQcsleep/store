package org.jetbrains.kotlin.fir.analysis.collectors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.analysis.collectors.components.ReportCommitterDiagnosticComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;", Argument.Delimiters.none, "regularComponents", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "reportCommitter", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ReportCommitterDiagnosticComponent;", "<init>", "([Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ReportCommitterDiagnosticComponent;)V", "getRegularComponents", "()[Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "[Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "getReportCommitter", "()Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ReportCommitterDiagnosticComponent;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticCollectorComponents {
    private final AbstractDiagnosticCollectorComponent[] regularComponents;
    private final ReportCommitterDiagnosticComponent reportCommitter;

    public DiagnosticCollectorComponents(AbstractDiagnosticCollectorComponent[] abstractDiagnosticCollectorComponentArr, ReportCommitterDiagnosticComponent reportCommitterDiagnosticComponent) {
        abstractDiagnosticCollectorComponentArr.getClass();
        reportCommitterDiagnosticComponent.getClass();
        this.regularComponents = abstractDiagnosticCollectorComponentArr;
        this.reportCommitter = reportCommitterDiagnosticComponent;
    }

    public final AbstractDiagnosticCollectorComponent[] getRegularComponents() {
        return this.regularComponents;
    }

    public final ReportCommitterDiagnosticComponent getReportCommitter() {
        return this.reportCommitter;
    }
}
