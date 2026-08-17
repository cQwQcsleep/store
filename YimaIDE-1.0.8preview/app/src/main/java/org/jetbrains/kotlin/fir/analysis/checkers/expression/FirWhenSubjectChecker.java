package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import com.intellij.psi.tree.IElementType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenSubjectChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWhenSubjectChecker extends FirExpressionChecker<FirWhenExpression> {
    public static final FirWhenSubjectChecker INSTANCE = new FirWhenSubjectChecker();

    private FirWhenSubjectChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        KtSourceElement source;
        KtSourceElement child$default;
        KtSourceElement source2;
        KtSourceElement source3;
        KtSourceElement source4;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firWhenExpression.getClass();
        FirVariable subjectVariable = firWhenExpression.getSubjectVariable();
        KtSourceElement child$default2 = null;
        FirExpression initializer = subjectVariable != null ? subjectVariable.getInitializer() : null;
        FirVariable subjectVariable2 = firWhenExpression.getSubjectVariable();
        FirVariable firVariable = subjectVariable2 != null ? subjectVariable2 : initializer;
        if (firVariable == null || (source = firVariable.getSource()) == null) {
            return;
        }
        if (Intrinsics.areEqual((initializer == null || (source4 = initializer.getSource()) == null) ? null : source4.getElementType(), KtNodeTypes.DESTRUCTURING_DECLARATION)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_DECLARATION_IN_WHEN_SUBJECT(), (Object) "destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (subjectVariable2 == null || (source3 = subjectVariable2.getSource()) == null) {
            child$default = null;
        } else {
            KtKeywordToken ktKeywordToken = KtTokens.VAR_KEYWORD;
            ktKeywordToken.getClass();
            child$default = FirSourceUtilsKt.getChild$default(source3, (IElementType) ktKeywordToken, 0, 0, false, 14, (Object) null);
        }
        if (child$default != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_DECLARATION_IN_WHEN_SUBJECT(), (Object) "var", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (subjectVariable2 != null && (source2 = subjectVariable2.getSource()) != null) {
            IElementType iElementType = KtNodeTypes.PROPERTY_DELEGATE;
            iElementType.getClass();
            child$default2 = FirSourceUtilsKt.getChild$default(source2, iElementType, 0, 0, false, 14, (Object) null);
        }
        if (child$default2 != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_DECLARATION_IN_WHEN_SUBJECT(), (Object) "delegated property", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            if (subjectVariable2 == null || subjectVariable2.getInitializer() != null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_DECLARATION_IN_WHEN_SUBJECT(), (Object) "variable without initializer", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}
