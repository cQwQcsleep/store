package org.jetbrains.kotlin.ir.backend.js.lower.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/LoopBounds;", "", "headState", "Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;", "exitState", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;)V", "getHeadState", "()Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;", "getExitState", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class LoopBounds {
    private final SuspendState exitState;
    private final SuspendState headState;

    public LoopBounds(SuspendState suspendState, SuspendState suspendState2) {
        suspendState.getClass();
        suspendState2.getClass();
        this.headState = suspendState;
        this.exitState = suspendState2;
    }

    public static /* synthetic */ LoopBounds copy$default(LoopBounds loopBounds, SuspendState suspendState, SuspendState suspendState2, int i, Object obj) {
        if ((i & 1) != 0) {
            suspendState = loopBounds.headState;
        }
        if ((i & 2) != 0) {
            suspendState2 = loopBounds.exitState;
        }
        return loopBounds.copy(suspendState, suspendState2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SuspendState getHeadState() {
        return this.headState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SuspendState getExitState() {
        return this.exitState;
    }

    public final LoopBounds copy(SuspendState headState, SuspendState exitState) {
        headState.getClass();
        exitState.getClass();
        return new LoopBounds(headState, exitState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoopBounds)) {
            return false;
        }
        LoopBounds loopBounds = (LoopBounds) other;
        return Intrinsics.areEqual(this.headState, loopBounds.headState) && Intrinsics.areEqual(this.exitState, loopBounds.exitState);
    }

    public final SuspendState getExitState() {
        return this.exitState;
    }

    public final SuspendState getHeadState() {
        return this.headState;
    }

    public int hashCode() {
        return (this.headState.hashCode() * 31) + this.exitState.hashCode();
    }

    public String toString() {
        return "LoopBounds(headState=" + this.headState + ", exitState=" + this.exitState + ')';
    }
}
