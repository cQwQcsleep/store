package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUnderscoreHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUnderscoreChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "diagnosticsCheckNeeded", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnderscoreChecker extends FirExpressionChecker<FirStatement> {
    public static final FirUnderscoreChecker INSTANCE = new FirUnderscoreChecker();

    private FirUnderscoreChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean diagnosticsCheckNeeded(FirResolvable expression) {
        CallableId callableId;
        Name callableName;
        String strAsString;
        if (expression.getCalleeReference() instanceof FirErrorNamedReference) {
            return false;
        }
        if (expression instanceof FirImplicitInvokeCall) {
            String strAsString2 = ((FirImplicitInvokeCall) expression).getCalleeReference().getName().asString();
            strAsString2.getClass();
            return FirUnderscoreHelpersKt.isUnderscore(strAsString2);
        }
        if (!(expression instanceof FirCall)) {
            return true;
        }
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(expression.getCalleeReference());
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        FirFunctionSymbol firFunctionSymbol = (FirFunctionSymbol) (resolvedSymbol instanceof FirFunctionSymbol ? resolvedSymbol : null);
        return (firFunctionSymbol == null || (callableId = firFunctionSymbol.getCallableId()) == null || (callableName = callableId.getCallableName()) == null || (strAsString = callableName.asString()) == null || !FirUnderscoreHelpersKt.isUnderscore(strAsString)) ? false : true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (!(firStatement instanceof FirResolvable)) {
            if (firStatement instanceof FirResolvedQualifier) {
                FirUnderscoreHelpersKt.checkUnderscoreDiagnostics(checkerContext, diagnosticReporter, ((FirResolvedQualifier) firStatement).getSource(), true);
            }
        } else {
            FirResolvable firResolvable = (FirResolvable) firStatement;
            if (diagnosticsCheckNeeded(firResolvable)) {
                FirUnderscoreHelpersKt.checkUnderscoreDiagnostics(checkerContext, diagnosticReporter, firResolvable.getCalleeReference().getSource(), true);
            }
        }
    }
}
