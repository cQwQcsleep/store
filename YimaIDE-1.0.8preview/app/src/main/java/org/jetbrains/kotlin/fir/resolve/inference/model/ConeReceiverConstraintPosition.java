package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.resolve.calls.inference.model.ReceiverConstraintPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeReceiverConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ReceiverConstraintPosition;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "receiver", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeReceiverConstraintPosition extends ReceiverConstraintPosition<FirExpression> {
    private final KtSourceElement source;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeReceiverConstraintPosition(FirExpression firExpression, KtSourceElement ktSourceElement) {
        super(firExpression);
        firExpression.getClass();
        this.source = ktSourceElement;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public String toString() {
        return "Receiver " + UtilsKt.render((FirElement) getArgument());
    }
}
