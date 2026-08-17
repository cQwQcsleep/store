package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.collectors.components.ErrorNodeDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferType;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.impl.FirStubReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionWithErrorTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionWithErrorTypeChecker extends FirExpressionChecker<FirStatement> {
    public static final FirExpressionWithErrorTypeChecker INSTANCE = new FirExpressionWithErrorTypeChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DiagnosticKind.values().length];
            try {
                iArr[DiagnosticKind.RecursionInImplicitTypes.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DiagnosticKind.RecursiveTypealiasExpansion.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DiagnosticKind.UnsignedNumbersAreNotPresent.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirExpressionWithErrorTypeChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) throws KotlinIllegalArgumentExceptionWithAttachments {
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (firStatement instanceof FirExpression) {
            FirExpression firExpression = (FirExpression) firStatement;
            ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
            if (!(resolvedType instanceof ConeErrorType) || (firStatement instanceof FirBlock) || (firStatement instanceof FirSafeCallExpression) || (firStatement instanceof FirDesugaredAssignmentValueReferenceExpression) || (firStatement instanceof FirWhenSubjectExpression) || (firStatement instanceof FirSmartCastExpression) || (firStatement instanceof FirCheckedSafeCallSubject) || (firStatement instanceof FirReplExpressionReference) || (firStatement instanceof FirDiagnosticHolder)) {
                return;
            }
            if (firStatement instanceof FirResolvable) {
                FirReference calleeReference = ((FirResolvable) firStatement).getCalleeReference();
                if (calleeReference instanceof FirDiagnosticHolder) {
                    return;
                }
                if ((calleeReference instanceof FirSuperReference) && (((FirSuperReference) calleeReference).getSuperTypeRef() instanceof FirErrorTypeRef)) {
                    return;
                }
                if (calleeReference instanceof FirResolvedNamedReference) {
                    FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(calleeReference);
                    FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
                    if ((firCallableSymbol != null ? firCallableSymbol.getResolvedReturnTypeRef() : null) instanceof FirErrorTypeRef) {
                        return;
                    }
                }
            }
            if (!(firStatement instanceof FirThisReceiverExpression) || ((FirThisReceiverExpression) firStatement).getCalleeReference().getDiagnostic() == null) {
                if ((firStatement instanceof FirAnnotationCall) && (((FirAnnotationCall) firStatement).getAnnotationTypeRef() instanceof FirErrorTypeRef)) {
                    return;
                }
                if ((firStatement instanceof FirTypeOperatorCall) && (((FirTypeOperatorCall) firStatement).getConversionTypeRef() instanceof FirErrorTypeRef)) {
                    return;
                }
                if (((firStatement instanceof FirWhenExpression) && (((FirWhenExpression) firStatement).getCalleeReference() instanceof FirStubReference)) || (source = firExpression.getSource()) == null) {
                    return;
                }
                ConeErrorType coneErrorType = (ConeErrorType) resolvedType;
                ConeDiagnostic diagnostic = coneErrorType.getDiagnostic();
                if (diagnostic instanceof ConeCannotInferType) {
                    return;
                }
                if (diagnostic instanceof ConeSimpleDiagnostic) {
                    int i = WhenMappings.$EnumSwitchMapping$0[((ConeSimpleDiagnostic) diagnostic).getKind().ordinal()];
                    if (i == 1 || i == 2 || i == 3) {
                        return;
                    }
                }
                ErrorNodeDiagnosticCollectorComponent.Companion.reportFirDiagnostic$org_jetbrains_kotlin_checkers$default(ErrorNodeDiagnosticCollectorComponent.INSTANCE, coneErrorType.getDiagnostic(), source, checkerContext, null, diagnosticReporter, null, null, 104, null);
            }
        }
    }
}
