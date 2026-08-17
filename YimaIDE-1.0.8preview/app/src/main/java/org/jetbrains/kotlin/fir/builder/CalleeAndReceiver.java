package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.references.FirNamedReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/CalleeAndReceiver;", Argument.Delimiters.none, "reference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "receiverForInvoke", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getReference", "()Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "getReceiverForInvoke", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CalleeAndReceiver {
    private final FirExpression receiverForInvoke;
    private final FirNamedReference reference;

    public CalleeAndReceiver(FirNamedReference firNamedReference, FirExpression firExpression) {
        firNamedReference.getClass();
        this.reference = firNamedReference;
        this.receiverForInvoke = firExpression;
    }

    public static /* synthetic */ CalleeAndReceiver copy$default(CalleeAndReceiver calleeAndReceiver, FirNamedReference firNamedReference, FirExpression firExpression, int i, Object obj) {
        if ((i & 1) != 0) {
            firNamedReference = calleeAndReceiver.reference;
        }
        if ((i & 2) != 0) {
            firExpression = calleeAndReceiver.receiverForInvoke;
        }
        return calleeAndReceiver.copy(firNamedReference, firExpression);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirNamedReference getReference() {
        return this.reference;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FirExpression getReceiverForInvoke() {
        return this.receiverForInvoke;
    }

    public final CalleeAndReceiver copy(FirNamedReference reference, FirExpression receiverForInvoke) {
        reference.getClass();
        return new CalleeAndReceiver(reference, receiverForInvoke);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CalleeAndReceiver)) {
            return false;
        }
        CalleeAndReceiver calleeAndReceiver = (CalleeAndReceiver) other;
        return Intrinsics.areEqual(this.reference, calleeAndReceiver.reference) && Intrinsics.areEqual(this.receiverForInvoke, calleeAndReceiver.receiverForInvoke);
    }

    public final FirExpression getReceiverForInvoke() {
        return this.receiverForInvoke;
    }

    public final FirNamedReference getReference() {
        return this.reference;
    }

    public int hashCode() {
        int iHashCode = this.reference.hashCode() * 31;
        FirExpression firExpression = this.receiverForInvoke;
        return iHashCode + (firExpression == null ? 0 : firExpression.hashCode());
    }

    public String toString() {
        return "CalleeAndReceiver(reference=" + this.reference + ", receiverForInvoke=" + this.receiverForInvoke + ')';
    }

    public /* synthetic */ CalleeAndReceiver(FirNamedReference firNamedReference, FirExpression firExpression, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firNamedReference, (i & 2) != 0 ? null : firExpression);
    }
}
