package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012J5\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeVariadicSpreadChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "checkVarargArguments", K2JsArgumentConstants.CALL, "isObjC", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Z)V", "checkSpreadArgument", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;Z)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeVariadicSpreadChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirNativeVariadicSpreadChecker INSTANCE = new FirNativeVariadicSpreadChecker();

    private FirNativeVariadicSpreadChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkSpreadArgument(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirSpreadArgumentExpression firSpreadArgumentExpression, boolean z) {
        FirExpression expression = firSpreadArgumentExpression.getExpression();
        if (expression instanceof FirFunctionCall) {
            FirFunctionCall firFunctionCall = (FirFunctionCall) expression;
            if (FirNativeHelpersKt.isArrayOfCall(firFunctionCall, checkerContext.getSession())) {
                checkVarargArguments(checkerContext, diagnosticReporter, firFunctionCall, z);
                return;
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firSpreadArgumentExpression.getSource(), z ? FirNativeErrors.INSTANCE.getVARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF() : FirNativeErrors.INSTANCE.getVARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final void checkVarargArguments(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall, boolean z) {
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
        if (mapping == null) {
            return;
        }
        for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
            FirExpression key = entry.getKey();
            if (entry.getValue().getIsVararg()) {
                FirVarargArgumentsExpression firVarargArgumentsExpression = key instanceof FirVarargArgumentsExpression ? (FirVarargArgumentsExpression) key : null;
                if (firVarargArgumentsExpression != null) {
                    for (FirExpression firExpression : firVarargArgumentsExpression.getArguments()) {
                        if (firExpression instanceof FirSpreadArgumentExpression) {
                            INSTANCE.checkSpreadArgument(checkerContext, diagnosticReporter, (FirSpreadArgumentExpression) firExpression, z);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x007c A[EDGE_INSN: B:31:0x007c->B:32:0x007d BREAK  A[LOOP:1: B:26:0x0068->B:43:?]] */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        boolean z;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
        FirFunctionSymbol firFunctionSymbol = resolvedCallableSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol : null;
        if (firFunctionSymbol == null) {
            return;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        if ((valueParameterSymbols instanceof Collection) && valueParameterSymbols.isEmpty()) {
            return;
        }
        Iterator<T> it = valueParameterSymbols.iterator();
        while (it.hasNext()) {
            if (((FirValueParameterSymbol) it.next()).isVararg()) {
                FirSession session = checkerContext.getSession();
                boolean zIsObjCMethod = FirObjCInteropKt.isObjCMethod(firFunctionSymbol, session);
                if (!FirObjCInteropKt.isCFunctionOrGlobalAccessor(firFunctionSymbol, session)) {
                    z = false;
                    break;
                }
                List<FirValueParameterSymbol> valueParameterSymbols2 = firFunctionSymbol.getValueParameterSymbols();
                if (!(valueParameterSymbols2 instanceof Collection) || !valueParameterSymbols2.isEmpty()) {
                    Iterator<T> it2 = valueParameterSymbols2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z = false;
                            break;
                        } else if (((FirValueParameterSymbol) it2.next()).isVararg()) {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = false;
                    break;
                }
                if (zIsObjCMethod || z) {
                    checkVarargArguments(checkerContext, diagnosticReporter, firFunctionCall, zIsObjCMethod);
                    return;
                }
                return;
            }
        }
    }
}
