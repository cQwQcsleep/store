package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirConstructorCallChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirConstructorCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstructorCallChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirConstructorCallChecker INSTANCE = new FirConstructorCallChecker();

    private FirConstructorCallChecker() {
        super(MppCheckerKind.Common);
    }

    public static boolean b(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneKotlinType instanceof ConeCapturedType;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        CheckerContext checkerContext2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirConstructorSymbol resolvedConstructorSymbol$default = FirReferenceUtilsKt.toResolvedConstructorSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedConstructorSymbol$default == null) {
            return;
        }
        ConeKotlinType resolvedReturnType = resolvedConstructorSymbol$default.getResolvedReturnType();
        if (Intrinsics.areEqual(resolvedConstructorSymbol$default.getOrigin(), FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE) && ConeTypeUtilsKt.contains(resolvedReturnType, new Function1() { // from class: b05
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirConstructorCallChecker.b((ConeKotlinType) obj));
            }
        })) {
            checkerContext2 = checkerContext;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getCONSTRUCTOR_OR_SUPERTYPE_ON_TYPEALIAS_WITH_TYPE_PROJECTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            checkerContext2 = checkerContext;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext2, resolvedReturnType);
        if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) == ClassKind.ENUM_CLASS) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getENUM_CLASS_CONSTRUCTOR_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
