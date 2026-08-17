package androidx.compose.runtime.tooling;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ0\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "", "groupKey", "", "sourceInfo", "Landroidx/compose/runtime/tooling/SourceInformation;", "groupOffset", "<init>", "(ILandroidx/compose/runtime/tooling/SourceInformation;Ljava/lang/Integer;)V", "getGroupKey", "()I", "getSourceInfo", "()Landroidx/compose/runtime/tooling/SourceInformation;", "getGroupOffset", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(ILandroidx/compose/runtime/tooling/SourceInformation;Ljava/lang/Integer;)Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "equals", "", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final /* data */ class ComposeStackTraceFrame {
    public static final int $stable = 8;
    private final int groupKey;
    private final Integer groupOffset;
    private final SourceInformation sourceInfo;

    public ComposeStackTraceFrame(int i, SourceInformation sourceInformation, Integer num) {
        this.groupKey = i;
        this.sourceInfo = sourceInformation;
        this.groupOffset = num;
    }

    public static /* synthetic */ ComposeStackTraceFrame copy$default(ComposeStackTraceFrame composeStackTraceFrame, int i, SourceInformation sourceInformation, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = composeStackTraceFrame.groupKey;
        }
        if ((i2 & 2) != 0) {
            sourceInformation = composeStackTraceFrame.sourceInfo;
        }
        if ((i2 & 4) != 0) {
            num = composeStackTraceFrame.groupOffset;
        }
        return composeStackTraceFrame.copy(i, sourceInformation, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getGroupKey() {
        return this.groupKey;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SourceInformation getSourceInfo() {
        return this.sourceInfo;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getGroupOffset() {
        return this.groupOffset;
    }

    public final ComposeStackTraceFrame copy(int groupKey, SourceInformation sourceInfo, Integer groupOffset) {
        return new ComposeStackTraceFrame(groupKey, sourceInfo, groupOffset);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComposeStackTraceFrame)) {
            return false;
        }
        ComposeStackTraceFrame composeStackTraceFrame = (ComposeStackTraceFrame) other;
        return this.groupKey == composeStackTraceFrame.groupKey && Intrinsics.areEqual(this.sourceInfo, composeStackTraceFrame.sourceInfo) && Intrinsics.areEqual(this.groupOffset, composeStackTraceFrame.groupOffset);
    }

    public final int getGroupKey() {
        return this.groupKey;
    }

    public final Integer getGroupOffset() {
        return this.groupOffset;
    }

    public final SourceInformation getSourceInfo() {
        return this.sourceInfo;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.groupKey) * 31;
        SourceInformation sourceInformation = this.sourceInfo;
        int iHashCode2 = (iHashCode + (sourceInformation == null ? 0 : sourceInformation.hashCode())) * 31;
        Integer num = this.groupOffset;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "ComposeStackTraceFrame(groupKey=" + this.groupKey + ", sourceInfo=" + this.sourceInfo + ", groupOffset=" + this.groupOffset + ')';
    }
}
