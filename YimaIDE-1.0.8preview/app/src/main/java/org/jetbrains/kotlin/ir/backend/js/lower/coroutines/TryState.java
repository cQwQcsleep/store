package org.jetbrains.kotlin.ir.backend.js.lower.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/TryState;", "", "tryState", "Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;", "catchState", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;)V", "getTryState", "()Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;", "getCatchState", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class TryState {
    private final SuspendState catchState;
    private final SuspendState tryState;

    public TryState(SuspendState suspendState, SuspendState suspendState2) {
        suspendState.getClass();
        suspendState2.getClass();
        this.tryState = suspendState;
        this.catchState = suspendState2;
    }

    public static /* synthetic */ TryState copy$default(TryState tryState, SuspendState suspendState, SuspendState suspendState2, int i, Object obj) {
        if ((i & 1) != 0) {
            suspendState = tryState.tryState;
        }
        if ((i & 2) != 0) {
            suspendState2 = tryState.catchState;
        }
        return tryState.copy(suspendState, suspendState2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SuspendState getTryState() {
        return this.tryState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SuspendState getCatchState() {
        return this.catchState;
    }

    public final TryState copy(SuspendState tryState, SuspendState catchState) {
        tryState.getClass();
        catchState.getClass();
        return new TryState(tryState, catchState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TryState)) {
            return false;
        }
        TryState tryState = (TryState) other;
        return Intrinsics.areEqual(this.tryState, tryState.tryState) && Intrinsics.areEqual(this.catchState, tryState.catchState);
    }

    public final SuspendState getCatchState() {
        return this.catchState;
    }

    public final SuspendState getTryState() {
        return this.tryState;
    }

    public int hashCode() {
        return (this.tryState.hashCode() * 31) + this.catchState.hashCode();
    }

    public String toString() {
        return "TryState(tryState=" + this.tryState + ", catchState=" + this.catchState + ')';
    }
}
