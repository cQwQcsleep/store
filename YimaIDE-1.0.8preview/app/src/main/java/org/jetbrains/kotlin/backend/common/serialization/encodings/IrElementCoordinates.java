package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/IrElementCoordinates;", "", "startOffset", "", "endOffset", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(II)V", "getStartOffset", "()I", "getEndOffset", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class IrElementCoordinates {
    private final int endOffset;
    private final int startOffset;

    public IrElementCoordinates(int i, int i2) {
        this.startOffset = i;
        this.endOffset = i2;
    }

    public static /* synthetic */ IrElementCoordinates copy$default(IrElementCoordinates irElementCoordinates, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = irElementCoordinates.startOffset;
        }
        if ((i3 & 2) != 0) {
            i2 = irElementCoordinates.endOffset;
        }
        return irElementCoordinates.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getStartOffset() {
        return this.startOffset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEndOffset() {
        return this.endOffset;
    }

    public final IrElementCoordinates copy(int startOffset, int endOffset) {
        return new IrElementCoordinates(startOffset, endOffset);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IrElementCoordinates)) {
            return false;
        }
        IrElementCoordinates irElementCoordinates = (IrElementCoordinates) other;
        return this.startOffset == irElementCoordinates.startOffset && this.endOffset == irElementCoordinates.endOffset;
    }

    public final int getEndOffset() {
        return this.endOffset;
    }

    public final int getStartOffset() {
        return this.startOffset;
    }

    public int hashCode() {
        return (Integer.hashCode(this.startOffset) * 31) + Integer.hashCode(this.endOffset);
    }

    public String toString() {
        return "IrElementCoordinates(startOffset=" + this.startOffset + ", endOffset=" + this.endOffset + Util.C_PARAM_END;
    }
}
