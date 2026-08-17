package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReceiverAccessBeforeSuperCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInaccessibleReceiverChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReceiverAccessBeforeSuperCallChecker extends FirExpressionChecker<FirInaccessibleReceiverExpression> {
    public static final FirReceiverAccessBeforeSuperCallChecker INSTANCE = new FirReceiverAccessBeforeSuperCallChecker();

    private FirReceiverAccessBeforeSuperCallChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirInaccessibleReceiverExpression firInaccessibleReceiverExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firInaccessibleReceiverExpression.getClass();
        if (firInaccessibleReceiverExpression.getKind() != InaccessibleReceiverKind.SecondaryConstructor) {
            return;
        }
        Object objLast = CollectionsKt.last(checkerContext.getCallsOrAssignments());
        objLast.getClass();
        FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) objLast;
        if (Intrinsics.areEqual(firInaccessibleReceiverExpression, firQualifiedAccessExpression.getDispatchReceiver()) || Intrinsics.areEqual(firInaccessibleReceiverExpression, firQualifiedAccessExpression.getExtensionReceiver()) || firQualifiedAccessExpression.getContextArguments().contains(firInaccessibleReceiverExpression) || ((firQualifiedAccessExpression instanceof FirImplicitInvokeCall) && ((FirCall) firQualifiedAccessExpression).getArgumentList().getArguments().contains(firInaccessibleReceiverExpression))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINSTANCE_ACCESS_BEFORE_SUPER_CALL(), (Object) "<this>", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            rza.a("Inaccessible receiver ", UtilsKt.render(firInaccessibleReceiverExpression), " isn't found in receivers of a call/access ", UtilsKt.render(firQualifiedAccessExpression));
        }
    }
}
