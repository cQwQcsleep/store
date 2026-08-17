package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/FunctionParameterShape;", "", "hasDispatchReceiver", "", "hasExtensionReceiver", "contextParameterCount", "", "regularParameterCount", "<init>", "(ZZII)V", "getHasDispatchReceiver", "()Z", "getHasExtensionReceiver", "getContextParameterCount", "()I", "getRegularParameterCount", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class FunctionParameterShape {
    private final int contextParameterCount;
    private final boolean hasDispatchReceiver;
    private final boolean hasExtensionReceiver;
    private final int regularParameterCount;

    public FunctionParameterShape(boolean z, boolean z2, int i, int i2) {
        this.hasDispatchReceiver = z;
        this.hasExtensionReceiver = z2;
        this.contextParameterCount = i;
        this.regularParameterCount = i2;
    }

    public static /* synthetic */ FunctionParameterShape copy$default(FunctionParameterShape functionParameterShape, boolean z, boolean z2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = functionParameterShape.hasDispatchReceiver;
        }
        if ((i3 & 2) != 0) {
            z2 = functionParameterShape.hasExtensionReceiver;
        }
        if ((i3 & 4) != 0) {
            i = functionParameterShape.contextParameterCount;
        }
        if ((i3 & 8) != 0) {
            i2 = functionParameterShape.regularParameterCount;
        }
        return functionParameterShape.copy(z, z2, i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getHasDispatchReceiver() {
        return this.hasDispatchReceiver;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasExtensionReceiver() {
        return this.hasExtensionReceiver;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getContextParameterCount() {
        return this.contextParameterCount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getRegularParameterCount() {
        return this.regularParameterCount;
    }

    public final FunctionParameterShape copy(boolean hasDispatchReceiver, boolean hasExtensionReceiver, int contextParameterCount, int regularParameterCount) {
        return new FunctionParameterShape(hasDispatchReceiver, hasExtensionReceiver, contextParameterCount, regularParameterCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunctionParameterShape)) {
            return false;
        }
        FunctionParameterShape functionParameterShape = (FunctionParameterShape) other;
        return this.hasDispatchReceiver == functionParameterShape.hasDispatchReceiver && this.hasExtensionReceiver == functionParameterShape.hasExtensionReceiver && this.contextParameterCount == functionParameterShape.contextParameterCount && this.regularParameterCount == functionParameterShape.regularParameterCount;
    }

    public final int getContextParameterCount() {
        return this.contextParameterCount;
    }

    public final boolean getHasDispatchReceiver() {
        return this.hasDispatchReceiver;
    }

    public final boolean getHasExtensionReceiver() {
        return this.hasExtensionReceiver;
    }

    public final int getRegularParameterCount() {
        return this.regularParameterCount;
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.hasDispatchReceiver) * 31) + Boolean.hashCode(this.hasExtensionReceiver)) * 31) + Integer.hashCode(this.contextParameterCount)) * 31) + Integer.hashCode(this.regularParameterCount);
    }

    public String toString() {
        return "FunctionParameterShape(hasDispatchReceiver=" + this.hasDispatchReceiver + ", hasExtensionReceiver=" + this.hasExtensionReceiver + ", contextParameterCount=" + this.contextParameterCount + ", regularParameterCount=" + this.regularParameterCount + ')';
    }
}
