package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.resolve.calls.inference.model.LambdaArgumentConstraintPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0001\u0002\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeLambdaArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/LambdaArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymousFunction", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "anonymousFunctionReturnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getAnonymousFunctionReturnExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "toString", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeLambdaArgumentConstraintPositionWithCoercionToUnit;", "Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeRegularLambdaArgumentConstraintPosition;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeLambdaArgumentConstraintPosition extends LambdaArgumentConstraintPosition<FirAnonymousFunction> {
    public /* synthetic */ ConeLambdaArgumentConstraintPosition(FirAnonymousFunction firAnonymousFunction, DefaultConstructorMarker defaultConstructorMarker) {
        this(firAnonymousFunction);
    }

    public abstract FirExpression getAnonymousFunctionReturnExpression();

    public String toString() {
        return "LambdaArgument";
    }

    private ConeLambdaArgumentConstraintPosition(FirAnonymousFunction firAnonymousFunction) {
        super(firAnonymousFunction);
    }
}
