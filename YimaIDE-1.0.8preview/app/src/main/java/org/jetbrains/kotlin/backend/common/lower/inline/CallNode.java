package org.jetbrains.kotlin.backend.common.lower.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrBody;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/inline/CallNode;", "", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "callLocation", "Lorg/jetbrains/kotlin/ir/expressions/IrBody;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/ir/expressions/IrBody;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getCallLocation", "()Lorg/jetbrains/kotlin/ir/expressions/IrBody;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class CallNode {
    private final IrBody callLocation;
    private final IrFunction function;

    public CallNode(IrFunction irFunction, IrBody irBody) {
        irFunction.getClass();
        irBody.getClass();
        this.function = irFunction;
        this.callLocation = irBody;
    }

    public static /* synthetic */ CallNode copy$default(CallNode callNode, IrFunction irFunction, IrBody irBody, int i, Object obj) {
        if ((i & 1) != 0) {
            irFunction = callNode.function;
        }
        if ((i & 2) != 0) {
            irBody = callNode.callLocation;
        }
        return callNode.copy(irFunction, irBody);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrFunction getFunction() {
        return this.function;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IrBody getCallLocation() {
        return this.callLocation;
    }

    public final CallNode copy(IrFunction function, IrBody callLocation) {
        function.getClass();
        callLocation.getClass();
        return new CallNode(function, callLocation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallNode)) {
            return false;
        }
        CallNode callNode = (CallNode) other;
        return Intrinsics.areEqual(this.function, callNode.function) && Intrinsics.areEqual(this.callLocation, callNode.callLocation);
    }

    public final IrBody getCallLocation() {
        return this.callLocation;
    }

    public final IrFunction getFunction() {
        return this.function;
    }

    public int hashCode() {
        return (this.function.hashCode() * 31) + this.callLocation.hashCode();
    }

    public String toString() {
        return "CallNode(function=" + this.function + ", callLocation=" + this.callLocation + Util.C_PARAM_END;
    }
}
