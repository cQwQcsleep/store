package org.jetbrains.kotlin.fir.analysis.collectors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider;
import org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/CheckerRunningDiagnosticCollectorVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/AbstractDiagnosticCollectorVisitor;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;", "components", "Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;", "checkSettings", Argument.Delimiters.none, "checkElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "onDeclarationExit", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class CheckerRunningDiagnosticCollectorVisitor extends AbstractDiagnosticCollectorVisitor {
    private final DiagnosticCollectorComponents components;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckerRunningDiagnosticCollectorVisitor(CheckerContextForProvider checkerContextForProvider, DiagnosticCollectorComponents diagnosticCollectorComponents) {
        super(checkerContextForProvider);
        checkerContextForProvider.getClass();
        diagnosticCollectorComponents.getClass();
        this.components = diagnosticCollectorComponents;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollectorVisitor
    public void checkElement(FirElement element) {
        element.getClass();
        for (AbstractDiagnosticCollectorComponent abstractDiagnosticCollectorComponent : this.components.getRegularComponents()) {
            element.accept(abstractDiagnosticCollectorComponent, getContext());
        }
        element.accept(this.components.getReportCommitter(), getContext());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollectorVisitor
    public void checkSettings() {
        for (AbstractDiagnosticCollectorComponent abstractDiagnosticCollectorComponent : this.components.getRegularComponents()) {
            abstractDiagnosticCollectorComponent.checkSettings(getContext());
        }
    }

    public final DiagnosticCollectorComponents getComponents() {
        return this.components;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollectorVisitor
    public void onDeclarationExit(FirDeclaration declaration) {
        declaration.getClass();
        if (declaration instanceof FirFile) {
            this.components.getReportCommitter().endOfFile((FirFile) declaration, getContext());
        }
    }
}
