package org.jetbrains.kotlin.backend.common.lower.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/inline/CallEdge;", "", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "callNode", "Lorg/jetbrains/kotlin/backend/common/lower/inline/CallNode;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;Lorg/jetbrains/kotlin/backend/common/lower/inline/CallNode;)V", "getCall", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getCallNode", "()Lorg/jetbrains/kotlin/backend/common/lower/inline/CallNode;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class CallEdge {
    private final IrCall call;
    private final CallNode callNode;

    public CallEdge(IrCall irCall, CallNode callNode) {
        callNode.getClass();
        this.call = irCall;
        this.callNode = callNode;
    }

    public static /* synthetic */ CallEdge copy$default(CallEdge callEdge, IrCall irCall, CallNode callNode, int i, Object obj) {
        if ((i & 1) != 0) {
            irCall = callEdge.call;
        }
        if ((i & 2) != 0) {
            callNode = callEdge.callNode;
        }
        return callEdge.copy(irCall, callNode);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrCall getCall() {
        return this.call;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CallNode getCallNode() {
        return this.callNode;
    }

    public final CallEdge copy(IrCall call, CallNode callNode) {
        callNode.getClass();
        return new CallEdge(call, callNode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallEdge)) {
            return false;
        }
        CallEdge callEdge = (CallEdge) other;
        return Intrinsics.areEqual(this.call, callEdge.call) && Intrinsics.areEqual(this.callNode, callEdge.callNode);
    }

    public final IrCall getCall() {
        return this.call;
    }

    public final CallNode getCallNode() {
        return this.callNode;
    }

    public int hashCode() {
        IrCall irCall = this.call;
        return ((irCall == null ? 0 : irCall.hashCode()) * 31) + this.callNode.hashCode();
    }

    public String toString() {
        return "CallEdge(call=" + this.call + ", callNode=" + this.callNode + Util.C_PARAM_END;
    }
}
