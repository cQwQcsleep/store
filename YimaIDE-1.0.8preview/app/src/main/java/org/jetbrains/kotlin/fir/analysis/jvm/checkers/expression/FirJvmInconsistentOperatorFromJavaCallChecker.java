package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ3\u0010\b\u001a\u00020\u0010*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmInconsistentOperatorFromJavaCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "CONCURRENT_HASH_MAP_CALLABLE_ID", "Lorg/jetbrains/kotlin/name/CallableId;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)Z", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmInconsistentOperatorFromJavaCallChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJvmInconsistentOperatorFromJavaCallChecker INSTANCE = new FirJvmInconsistentOperatorFromJavaCallChecker();
    private static final CallableId CONCURRENT_HASH_MAP_CALLABLE_ID = new CallableId(ClassId.Companion.fromString$default(ClassId.Companion, "java/util/concurrent/ConcurrentHashMap", false, 2, (Object) null), OperatorNameConventions.CONTAINS);

    private FirJvmInconsistentOperatorFromJavaCallChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunctionSymbol firNamedFunctionSymbol, KtSourceElement ktSourceElement) {
        FirRegularClassSymbol regularClassSymbol;
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol.getFir();
        while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return false;
        }
        if (Intrinsics.areEqual(((FirNamedFunctionSymbol) symbol).getCallableId(), CONCURRENT_HASH_MAP_CALLABLE_ID)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getCONCURRENT_HASH_MAP_CONTAINS_OPERATOR_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return true;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firNamedFunctionSymbol);
        if (coneClassLikeLookupTagContainingClassLookupTag != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) != null) {
            for (FirFunctionSymbol<?> firFunctionSymbol : FirHelpersKt.overriddenFunctions(checkerContext, firNamedFunctionSymbol, regularClassSymbol)) {
                if ((firFunctionSymbol instanceof FirNamedFunctionSymbol) && check(checkerContext, diagnosticReporter, (FirNamedFunctionSymbol) firFunctionSymbol, ktSourceElement)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirValueParameterSymbol firValueParameterSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        if (firFunctionCall.getOrigin() != FirFunctionCallOrigin.Operator) {
            return;
        }
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        FirNamedFunctionSymbol firNamedFunctionSymbol = resolvedCallableSymbol$default instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) resolvedCallableSymbol$default : null;
        if (firNamedFunctionSymbol == null || !Intrinsics.areEqual(firNamedFunctionSymbol.getName(), OperatorNameConventions.CONTAINS) || (firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.singleOrNull(firNamedFunctionSymbol.getValueParameterSymbols())) == null) {
            return;
        }
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(firValueParameterSymbol.getResolvedReturnTypeRef().getConeType());
        if (ConeBuiltinTypeUtilsKt.isAny(coneRigidTypeLowerBoundIfFlexible) || ConeBuiltinTypeUtilsKt.isNullableAny(coneRigidTypeLowerBoundIfFlexible)) {
            check(checkerContext, diagnosticReporter, firNamedFunctionSymbol, firFunctionCall.getCalleeReference().getSource());
        }
    }
}
