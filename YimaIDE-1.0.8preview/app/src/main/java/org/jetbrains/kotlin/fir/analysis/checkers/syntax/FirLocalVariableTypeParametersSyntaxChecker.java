package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J=\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0013J5\u0010\u0014\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0015H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirLocalVariableTypeParametersSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lorg/jetbrains/kotlin/psi/KtExpression;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLocalVariableTypeParametersSyntaxChecker extends FirDeclarationSyntaxChecker<FirProperty, KtExpression> {
    public static final FirLocalVariableTypeParametersSyntaxChecker INSTANCE = new FirLocalVariableTypeParametersSyntaxChecker();

    private FirLocalVariableTypeParametersSyntaxChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty, KtLightSourceElement ktLightSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        ktLightSourceElement.getClass();
        if (Intrinsics.areEqual(ktLightSourceElement.getLighterASTNode().getTokenType(), KtNodeTypes.PROPERTY) && LightTreePositioningStrategiesKt.typeParametersList(ktLightSourceElement.getTreeStructure(), ktLightSourceElement.getLighterASTNode()) != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktLightSourceElement, FirErrors.INSTANCE.getLOCAL_VARIABLE_WITH_TYPE_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty, KtPsiSourceElement ktPsiSourceElement, KtExpression ktExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        ktPsiSourceElement.getClass();
        ktExpression.getClass();
        if (!(ktExpression instanceof KtProperty) || ((KtProperty) ktExpression).getTypeParameterList() == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktPsiSourceElement, FirErrors.INSTANCE.getLOCAL_VARIABLE_WITH_TYPE_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirProperty element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return !(source.getKind() instanceof KtFakeSourceElementKind) && (element.getSymbol() instanceof FirLocalPropertySymbol);
    }
}
