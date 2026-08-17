package org.jetbrains.kotlin.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/ir/SourceRangeInfo;", "", "filePath", "", "startOffset", "", "startLineNumber", "startColumnNumber", "endOffset", "endLineNumber", "endColumnNumber", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;IIIIII)V", "getFilePath", "()Ljava/lang/String;", "getStartOffset", "()I", "getStartLineNumber", "getStartColumnNumber", "getEndOffset", "getEndLineNumber", "getEndColumnNumber", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class SourceRangeInfo {
    private final int endColumnNumber;
    private final int endLineNumber;
    private final int endOffset;
    private final String filePath;
    private final int startColumnNumber;
    private final int startLineNumber;
    private final int startOffset;

    public SourceRangeInfo(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        str.getClass();
        this.filePath = str;
        this.startOffset = i;
        this.startLineNumber = i2;
        this.startColumnNumber = i3;
        this.endOffset = i4;
        this.endLineNumber = i5;
        this.endColumnNumber = i6;
    }

    public static /* synthetic */ SourceRangeInfo copy$default(SourceRangeInfo sourceRangeInfo, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            str = sourceRangeInfo.filePath;
        }
        if ((i7 & 2) != 0) {
            i = sourceRangeInfo.startOffset;
        }
        if ((i7 & 4) != 0) {
            i2 = sourceRangeInfo.startLineNumber;
        }
        if ((i7 & 8) != 0) {
            i3 = sourceRangeInfo.startColumnNumber;
        }
        if ((i7 & 16) != 0) {
            i4 = sourceRangeInfo.endOffset;
        }
        if ((i7 & 32) != 0) {
            i5 = sourceRangeInfo.endLineNumber;
        }
        if ((i7 & 64) != 0) {
            i6 = sourceRangeInfo.endColumnNumber;
        }
        int i8 = i5;
        int i9 = i6;
        int i10 = i4;
        int i11 = i2;
        return sourceRangeInfo.copy(str, i, i11, i3, i10, i8, i9);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getStartOffset() {
        return this.startOffset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getStartLineNumber() {
        return this.startLineNumber;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getStartColumnNumber() {
        return this.startColumnNumber;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEndOffset() {
        return this.endOffset;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getEndLineNumber() {
        return this.endLineNumber;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getEndColumnNumber() {
        return this.endColumnNumber;
    }

    public final SourceRangeInfo copy(String filePath, int startOffset, int startLineNumber, int startColumnNumber, int endOffset, int endLineNumber, int endColumnNumber) {
        filePath.getClass();
        return new SourceRangeInfo(filePath, startOffset, startLineNumber, startColumnNumber, endOffset, endLineNumber, endColumnNumber);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceRangeInfo)) {
            return false;
        }
        SourceRangeInfo sourceRangeInfo = (SourceRangeInfo) other;
        return Intrinsics.areEqual(this.filePath, sourceRangeInfo.filePath) && this.startOffset == sourceRangeInfo.startOffset && this.startLineNumber == sourceRangeInfo.startLineNumber && this.startColumnNumber == sourceRangeInfo.startColumnNumber && this.endOffset == sourceRangeInfo.endOffset && this.endLineNumber == sourceRangeInfo.endLineNumber && this.endColumnNumber == sourceRangeInfo.endColumnNumber;
    }

    public final int getEndColumnNumber() {
        return this.endColumnNumber;
    }

    public final int getEndLineNumber() {
        return this.endLineNumber;
    }

    public final int getEndOffset() {
        return this.endOffset;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final int getStartColumnNumber() {
        return this.startColumnNumber;
    }

    public final int getStartLineNumber() {
        return this.startLineNumber;
    }

    public final int getStartOffset() {
        return this.startOffset;
    }

    public int hashCode() {
        return (((((((((((this.filePath.hashCode() * 31) + Integer.hashCode(this.startOffset)) * 31) + Integer.hashCode(this.startLineNumber)) * 31) + Integer.hashCode(this.startColumnNumber)) * 31) + Integer.hashCode(this.endOffset)) * 31) + Integer.hashCode(this.endLineNumber)) * 31) + Integer.hashCode(this.endColumnNumber);
    }

    public String toString() {
        return "SourceRangeInfo(filePath=" + this.filePath + ", startOffset=" + this.startOffset + ", startLineNumber=" + this.startLineNumber + ", startColumnNumber=" + this.startColumnNumber + ", endOffset=" + this.endOffset + ", endLineNumber=" + this.endLineNumber + ", endColumnNumber=" + this.endColumnNumber + Util.C_PARAM_END;
    }
}
