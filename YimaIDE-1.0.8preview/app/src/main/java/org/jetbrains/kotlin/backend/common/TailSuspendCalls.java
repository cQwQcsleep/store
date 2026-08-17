package org.jetbrains.kotlin.backend.common;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/TailSuspendCalls;", "", "callSites", "", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "hasNotTailSuspendCalls", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Set;Z)V", "getCallSites", "()Ljava/util/Set;", "getHasNotTailSuspendCalls", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class TailSuspendCalls {
    private final Set<IrCall> callSites;
    private final boolean hasNotTailSuspendCalls;

    public TailSuspendCalls(Set<? extends IrCall> set, boolean z) {
        set.getClass();
        this.callSites = set;
        this.hasNotTailSuspendCalls = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TailSuspendCalls copy$default(TailSuspendCalls tailSuspendCalls, Set set, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            set = tailSuspendCalls.callSites;
        }
        if ((i & 2) != 0) {
            z = tailSuspendCalls.hasNotTailSuspendCalls;
        }
        return tailSuspendCalls.copy(set, z);
    }

    public final Set<IrCall> component1() {
        return this.callSites;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasNotTailSuspendCalls() {
        return this.hasNotTailSuspendCalls;
    }

    public final TailSuspendCalls copy(Set<? extends IrCall> callSites, boolean hasNotTailSuspendCalls) {
        callSites.getClass();
        return new TailSuspendCalls(callSites, hasNotTailSuspendCalls);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TailSuspendCalls)) {
            return false;
        }
        TailSuspendCalls tailSuspendCalls = (TailSuspendCalls) other;
        return Intrinsics.areEqual(this.callSites, tailSuspendCalls.callSites) && this.hasNotTailSuspendCalls == tailSuspendCalls.hasNotTailSuspendCalls;
    }

    public final Set<IrCall> getCallSites() {
        return this.callSites;
    }

    public final boolean getHasNotTailSuspendCalls() {
        return this.hasNotTailSuspendCalls;
    }

    public int hashCode() {
        return (this.callSites.hashCode() * 31) + Boolean.hashCode(this.hasNotTailSuspendCalls);
    }

    public String toString() {
        return "TailSuspendCalls(callSites=" + this.callSites + ", hasNotTailSuspendCalls=" + this.hasNotTailSuspendCalls + Util.C_PARAM_END;
    }
}
