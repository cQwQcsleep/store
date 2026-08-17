package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeLambdaArgumentConstraintPositionWithCoercionToUnit;", "Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeLambdaArgumentConstraintPosition;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymousFunctionReturnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getAnonymousFunctionReturnExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeLambdaArgumentConstraintPositionWithCoercionToUnit extends ConeLambdaArgumentConstraintPosition {
    private final FirExpression anonymousFunctionReturnExpression;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeLambdaArgumentConstraintPositionWithCoercionToUnit(FirAnonymousFunction firAnonymousFunction, FirExpression firExpression) {
        super(firAnonymousFunction, null);
        firAnonymousFunction.getClass();
        this.anonymousFunctionReturnExpression = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.model.ConeLambdaArgumentConstraintPosition
    public FirExpression getAnonymousFunctionReturnExpression() {
        return this.anonymousFunctionReturnExpression;
    }
}
