package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmPolymorphicSignatureCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "polymorphicSignatureContainers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "isPolymorphicCall", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmPolymorphicSignatureCallChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJvmPolymorphicSignatureCallChecker INSTANCE = new FirJvmPolymorphicSignatureCallChecker();
    private static final List<ClassId> polymorphicSignatureContainers;

    static {
        ClassId.Companion companion = ClassId.Companion;
        polymorphicSignatureContainers = CollectionsKt.listOf(new ClassId[]{companion.topLevel(new FqName("java.lang.invoke.MethodHandle")), companion.topLevel(new FqName("java.lang.invoke.VarHandle"))});
    }

    private FirJvmPolymorphicSignatureCallChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isPolymorphicCall(FirCallableSymbol<?> symbol) {
        FirValueParameterSymbol firValueParameterSymbol;
        ConeKotlinType coneKotlinTypeArrayElementType$default;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        if (!(symbol instanceof FirFunctionSymbol) || !symbol.getRawStatus().isExternal()) {
            return false;
        }
        List<ClassId> list = polymorphicSignatureContainers;
        ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(symbol);
        return CollectionsKt.contains(list, coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null ? coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId() : null) && (firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.singleOrNull(((FirFunctionSymbol) symbol).getValueParameterSymbols())) != null && firValueParameterSymbol.isVararg() && (coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(firValueParameterSymbol.getResolvedReturnType(), false, 1, null)) != null && (coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeArrayElementType$default)) != null && ConeBuiltinTypeUtilsKt.isAnyOrNullableAny(coneRigidTypeLowerBoundIfFlexible);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default != null && isPolymorphicCall(resolvedCallableSymbol$default)) {
            for (FirExpression firExpression : firFunctionCall.getArgumentList().getArguments()) {
                if (firExpression instanceof FirVarargArgumentsExpression) {
                    for (FirExpression firExpression2 : ((FirVarargArgumentsExpression) firExpression).getArguments()) {
                        if (firExpression2 instanceof FirSpreadArgumentExpression) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirSpreadArgumentExpression) firExpression2).getSource(), FirJvmErrors.INSTANCE.getSPREAD_ON_SIGNATURE_POLYMORPHIC_CALL_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }
                }
            }
        }
    }
}
