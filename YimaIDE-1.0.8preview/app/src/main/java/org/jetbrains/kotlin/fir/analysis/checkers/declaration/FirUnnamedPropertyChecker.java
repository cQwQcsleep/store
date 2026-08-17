package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnnamedPropertyChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnnamedPropertyChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirUnnamedPropertyChecker INSTANCE = new FirUnnamedPropertyChecker();

    private FirUnnamedPropertyChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirProperty firProperty2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        if (Intrinsics.areEqual(firProperty.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
            KtSourceElement source = firProperty.getSource();
            boolean zAreEqual = Intrinsics.areEqual(source != null ? source.getElementType() : null, KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY);
            if (firProperty.getIsVar() && !zAreEqual) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirErrors.INSTANCE.getUNNAMED_VAR_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firProperty.getDelegate() != null) {
                FirExpression delegate = firProperty.getDelegate();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (delegate != null ? delegate.getSource() : null), FirErrors.INSTANCE.getUNNAMED_DELEGATED_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (zAreEqual || Intrinsics.areEqual(ClassMembersKt.isCatchParameter(firProperty), Boolean.TRUE)) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                firProperty2 = firProperty;
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                firProperty2 = firProperty;
                FirHelpersKt.requireFeatureSupport(checkerContext2, diagnosticReporter2, firProperty2, LanguageFeature.UnnamedLocalVariables, SourceElementPositioningStrategies.INSTANCE.getNAME_IDENTIFIER());
            }
            if (firProperty2.getInitializer() == null && firProperty2.getDelegate() == null && !Intrinsics.areEqual(ClassMembersKt.isCatchParameter(firProperty2), Boolean.TRUE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firProperty2.getSource(), FirErrors.INSTANCE.getMUST_BE_INITIALIZED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
