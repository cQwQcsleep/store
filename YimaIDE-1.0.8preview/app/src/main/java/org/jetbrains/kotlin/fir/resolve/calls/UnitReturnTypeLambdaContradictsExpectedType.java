package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/UnitReturnTypeLambdaContradictsExpectedType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "lambda", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "wholeLambdaExpectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "sourceForFunctionExpression", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getLambda", "()Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getWholeLambdaExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getSourceForFunctionExpression", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnitReturnTypeLambdaContradictsExpectedType extends ResolutionDiagnostic {
    private final FirAnonymousFunction lambda;
    private final KtSourceElement sourceForFunctionExpression;
    private final ConeKotlinType wholeLambdaExpectedType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnitReturnTypeLambdaContradictsExpectedType(FirAnonymousFunction firAnonymousFunction, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement) {
        super(CandidateApplicability.INAPPLICABLE);
        firAnonymousFunction.getClass();
        coneKotlinType.getClass();
        this.lambda = firAnonymousFunction;
        this.wholeLambdaExpectedType = coneKotlinType;
        this.sourceForFunctionExpression = ktSourceElement;
    }

    public final FirAnonymousFunction getLambda() {
        return this.lambda;
    }

    public final KtSourceElement getSourceForFunctionExpression() {
        return this.sourceForFunctionExpression;
    }

    public final ConeKotlinType getWholeLambdaExpectedType() {
        return this.wholeLambdaExpectedType;
    }
}
