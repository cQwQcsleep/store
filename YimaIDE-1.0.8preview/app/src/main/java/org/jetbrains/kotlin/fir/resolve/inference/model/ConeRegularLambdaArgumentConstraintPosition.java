package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.resolve.calls.inference.model.OnlyInputTypeConstraintPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeRegularLambdaArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeLambdaArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/OnlyInputTypeConstraintPosition;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymousFunctionReturnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getAnonymousFunctionReturnExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeRegularLambdaArgumentConstraintPosition extends ConeLambdaArgumentConstraintPosition implements OnlyInputTypeConstraintPosition {
    private final FirExpression anonymousFunctionReturnExpression;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeRegularLambdaArgumentConstraintPosition(FirAnonymousFunction firAnonymousFunction, FirExpression firExpression) {
        super(firAnonymousFunction, null);
        firAnonymousFunction.getClass();
        firExpression.getClass();
        this.anonymousFunctionReturnExpression = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.model.ConeLambdaArgumentConstraintPosition
    public FirExpression getAnonymousFunctionReturnExpression() {
        return this.anonymousFunctionReturnExpression;
    }
}
