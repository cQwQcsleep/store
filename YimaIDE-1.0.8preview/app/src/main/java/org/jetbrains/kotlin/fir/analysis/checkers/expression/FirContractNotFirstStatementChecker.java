package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
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
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirErrorContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.diagnostics.ConeContractShouldBeFirstStatement;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u001a\u0010\u0012\u001a\u00020\u000f*\u0004\u0018\u00010\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirContractNotFirstStatementChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "isCorrectlyPlacedIn", Argument.Delimiters.none, "functionDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "isNonFirstStatement", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractNotFirstStatementChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirContractNotFirstStatementChecker INSTANCE = new FirContractNotFirstStatementChecker();

    private FirContractNotFirstStatementChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isCorrectlyPlacedIn(FirFunctionCall firFunctionCall, FirFunction firFunction) {
        List<FirStatement> statements;
        FirBlock body = firFunction.getBody();
        FirStatement firStatement = (body == null || (statements = body.getStatements()) == null) ? null : (FirStatement) CollectionsKt.first(statements);
        if ((firStatement instanceof FirContractCallBlock) && Intrinsics.areEqual(((FirContractCallBlock) firStatement).getCall(), firFunctionCall)) {
            return ((firFunction instanceof FirContractDescriptionOwner) && isNonFirstStatement(((FirContractDescriptionOwner) firFunction).getContractDescription())) ? false : true;
        }
        return false;
    }

    private final boolean isNonFirstStatement(FirContractDescription firContractDescription) {
        if (firContractDescription instanceof FirResolvedContractDescription) {
            return Intrinsics.areEqual(((FirResolvedContractDescription) firContractDescription).getDiagnostic(), ConeContractShouldBeFirstStatement.INSTANCE);
        }
        if (firContractDescription instanceof FirErrorContractDescription) {
            return Intrinsics.areEqual(((FirErrorContractDescription) firContractDescription).getDiagnostic(), ConeContractShouldBeFirstStatement.INSTANCE);
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        CallableId contract = StandardClassIds.Callables.INSTANCE.getContract();
        FirCallableSymbol<?> resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
        if (Intrinsics.areEqual(contract, resolvedCallableSymbol != null ? resolvedCallableSymbol.getCallableId() : null)) {
            FirDeclaration fir = ((FirBasedSymbol) CollectionsKt.last(checkerContext.getContainingDeclarations())).getFir();
            boolean z = fir instanceof FirFunction;
            if (z && isCorrectlyPlacedIn(firFunctionCall, (FirFunction) fir)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONTRACT_NOT_ALLOWED(), (Object) ((z && (((FirFunction) fir).getBody() instanceof FirSingleExpressionBlock)) ? "Contracts are only allowed in function body blocks." : "Contract should be the first statement."), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}
