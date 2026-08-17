package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirUnnecessarySafeCallChecker;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedForWarningConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJavaUnnecessarySafeCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractFirUnnecessarySafeCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaUnnecessarySafeCallChecker extends AbstractFirUnnecessarySafeCallChecker {
    public static final FirJavaUnnecessarySafeCallChecker INSTANCE = new FirJavaUnnecessarySafeCallChecker();

    private FirJavaUnnecessarySafeCallChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirSafeCallExpression firSafeCallExpression) {
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firSafeCallExpression.getClass();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = new EnhancedForWarningConeSubstitutor(TypeComponentsKt.getTypeContext(checkerContext.getSession()), null, 2, null).substituteOrNull(FirTypeUtilsKt.getResolvedType(firSafeCallExpression.getReceiver()));
        if (coneKotlinTypeSubstituteOrNull == null || (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinTypeSubstituteOrNull)) == null) {
            return;
        }
        checkSafeCallReceiverType(checkerContext, diagnosticReporter, coneKotlinTypeFullyExpandedType, firSafeCallExpression.getSource());
    }
}
