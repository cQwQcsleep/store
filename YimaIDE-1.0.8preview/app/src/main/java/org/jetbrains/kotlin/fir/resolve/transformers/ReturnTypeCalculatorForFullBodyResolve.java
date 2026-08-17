package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculatorForFullBodyResolve;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "diagnosticKind", "Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "reason", Argument.Delimiters.none, "callableCopyTypeCalculator", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;", "<init>", "(Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;)V", "getCallableCopyTypeCalculator", "()Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;", "tryCalculateReturnTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReturnTypeCalculatorForFullBodyResolve extends ReturnTypeCalculator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReturnTypeCalculatorForFullBodyResolve Contract;
    private static final ReturnTypeCalculatorForFullBodyResolve Default;
    private static final ReturnTypeCalculatorForFullBodyResolve Status;
    private final CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator callableCopyTypeCalculator;
    private final DiagnosticKind diagnosticKind;
    private final String reason;

    static {
        DiagnosticKind diagnosticKind = DiagnosticKind.RecursionInImplicitTypes;
        CallableCopyTypeCalculator.CalculateDeferredForceLazyResolution calculateDeferredForceLazyResolution = CallableCopyTypeCalculator.CalculateDeferredForceLazyResolution.INSTANCE;
        Default = new ReturnTypeCalculatorForFullBodyResolve(diagnosticKind, "Recursion with local function", calculateDeferredForceLazyResolution);
        Status = new ReturnTypeCalculatorForFullBodyResolve(diagnosticKind, "Recursion with local function", CallableCopyTypeCalculator.CalculateDeferredWhenPossible.INSTANCE);
        Contract = new ReturnTypeCalculatorForFullBodyResolve(DiagnosticKind.InferenceError, "Cannot calculate return type during full-body resolution (local class/object?)", calculateDeferredForceLazyResolution);
    }

    private ReturnTypeCalculatorForFullBodyResolve(DiagnosticKind diagnosticKind, String str, CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator deferredCallableCopyTypeCalculator) {
        this.diagnosticKind = diagnosticKind;
        this.reason = str;
        this.callableCopyTypeCalculator = deferredCallableCopyTypeCalculator;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator
    public FirResolvedTypeRef tryCalculateReturnTypeOrNull(FirCallableDeclaration declaration) {
        declaration.getClass();
        FirResolvedTypeRef returnTypeRef = declaration.getReturnTypeRef();
        if (returnTypeRef instanceof FirResolvedTypeRef) {
            return returnTypeRef;
        }
        if (ClassMembersKt.getCanHaveDeferredReturnTypeCalculation(declaration)) {
            return getCallableCopyTypeCalculator().mo617computeReturnType(declaration);
        }
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic(this.reason + ": " + UtilsKt.render(declaration), this.diagnosticKind));
        return firErrorTypeRefBuilder.build();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculatorForFullBodyResolve$Companion;", Argument.Delimiters.none, "<init>", "()V", "Default", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculatorForFullBodyResolve;", "getDefault", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculatorForFullBodyResolve;", "Status", "getStatus", "Contract", "getContract", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ReturnTypeCalculatorForFullBodyResolve getContract() {
            return ReturnTypeCalculatorForFullBodyResolve.Contract;
        }

        public final ReturnTypeCalculatorForFullBodyResolve getDefault() {
            return ReturnTypeCalculatorForFullBodyResolve.Default;
        }

        public final ReturnTypeCalculatorForFullBodyResolve getStatus() {
            return ReturnTypeCalculatorForFullBodyResolve.Status;
        }

        private Companion() {
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator
    public CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator getCallableCopyTypeCalculator() {
        return this.callableCopyTypeCalculator;
    }
}
