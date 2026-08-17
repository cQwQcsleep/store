package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001:\u0001!B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nJ\u0006\u0010\u0012\u001a\u00020\u000eJ\u0017\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u0017J\b\u0010\u001a\u001a\u00020\u001bH\u0016J*\u0010\u001c\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text/input/internal/ChangeTracker;", "Landroidx/compose/foundation/text/input/TextFieldBuffer$ChangeList;", "initialChanges", "<init>", "(Landroidx/compose/foundation/text/input/internal/ChangeTracker;)V", "_changes", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/text/input/internal/ChangeTracker$Change;", "_changesTemp", "changeCount", "", "getChangeCount", "()I", "trackChange", "", "preStart", "preEnd", "postLength", "clearChanges", "getRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getRange--jx7JFs", "(I)J", "getOriginalRange", "getOriginalRange--jx7JFs", "toString", "", "appendNewChange", "mergedOverlappingChange", "preMin", "preMax", "postDelta", "Change", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ChangeTracker implements TextFieldBuffer.ChangeList {
    public static final int $stable = 8;
    private MutableVector<Change> _changes;
    private MutableVector<Change> _changesTemp;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/text/input/internal/ChangeTracker$Change;", "", "preStart", "", "preEnd", "originalStart", "originalEnd", "<init>", "(IIII)V", "getPreStart", "()I", "setPreStart", "(I)V", "getPreEnd", "setPreEnd", "getOriginalStart", "setOriginalStart", "getOriginalEnd", "setOriginalEnd", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Change {
        private int originalEnd;
        private int originalStart;
        private int preEnd;
        private int preStart;

        public Change(int i, int i2, int i3, int i4) {
            this.preStart = i;
            this.preEnd = i2;
            this.originalStart = i3;
            this.originalEnd = i4;
        }

        public static /* synthetic */ Change copy$default(Change change, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = change.preStart;
            }
            if ((i5 & 2) != 0) {
                i2 = change.preEnd;
            }
            if ((i5 & 4) != 0) {
                i3 = change.originalStart;
            }
            if ((i5 & 8) != 0) {
                i4 = change.originalEnd;
            }
            return change.copy(i, i2, i3, i4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getPreStart() {
            return this.preStart;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getPreEnd() {
            return this.preEnd;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getOriginalStart() {
            return this.originalStart;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final Change copy(int preStart, int preEnd, int originalStart, int originalEnd) {
            return new Change(preStart, preEnd, originalStart, originalEnd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Change)) {
                return false;
            }
            Change change = (Change) other;
            return this.preStart == change.preStart && this.preEnd == change.preEnd && this.originalStart == change.originalStart && this.originalEnd == change.originalEnd;
        }

        public final int getOriginalEnd() {
            return this.originalEnd;
        }

        public final int getOriginalStart() {
            return this.originalStart;
        }

        public final int getPreEnd() {
            return this.preEnd;
        }

        public final int getPreStart() {
            return this.preStart;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.preStart) * 31) + Integer.hashCode(this.preEnd)) * 31) + Integer.hashCode(this.originalStart)) * 31) + Integer.hashCode(this.originalEnd);
        }

        public final void setOriginalEnd(int i) {
            this.originalEnd = i;
        }

        public final void setOriginalStart(int i) {
            this.originalStart = i;
        }

        public final void setPreEnd(int i) {
            this.preEnd = i;
        }

        public final void setPreStart(int i) {
            this.preStart = i;
        }

        public String toString() {
            return "Change(preStart=" + this.preStart + ", preEnd=" + this.preEnd + ", originalStart=" + this.originalStart + ", originalEnd=" + this.originalEnd + ')';
        }
    }

    public ChangeTracker(ChangeTracker changeTracker) {
        MutableVector<Change> mutableVector;
        this._changes = new MutableVector<>(new Change[16], 0);
        this._changesTemp = new MutableVector<>(new Change[16], 0);
        if (changeTracker == null || (mutableVector = changeTracker._changes) == null) {
            return;
        }
        Object[] objArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            Change change = (Change) objArr[i];
            this._changes.add(new Change(change.getPreStart(), change.getPreEnd(), change.getOriginalStart(), change.getOriginalEnd()));
        }
    }

    private final void appendNewChange(Change mergedOverlappingChange, int preMin, int preMax, int postDelta) {
        int preEnd;
        if (this._changesTemp.getSize() == 0) {
            preEnd = 0;
        } else {
            Change change = (Change) this._changesTemp.last();
            preEnd = change.getPreEnd() - change.getOriginalEnd();
        }
        if (mergedOverlappingChange == null) {
            int i = preMin - preEnd;
            mergedOverlappingChange = new Change(preMin, preMax + postDelta, i, (preMax - preMin) + i);
        } else {
            if (mergedOverlappingChange.getPreStart() > preMin) {
                mergedOverlappingChange.setPreStart(preMin);
                mergedOverlappingChange.setOriginalStart(preMin);
            }
            if (preMax > mergedOverlappingChange.getPreEnd()) {
                int preEnd2 = mergedOverlappingChange.getPreEnd() - mergedOverlappingChange.getOriginalEnd();
                mergedOverlappingChange.setPreEnd(preMax);
                mergedOverlappingChange.setOriginalEnd(preMax - preEnd2);
            }
            mergedOverlappingChange.setPreEnd(mergedOverlappingChange.getPreEnd() + postDelta);
        }
        this._changesTemp.add(mergedOverlappingChange);
    }

    public final void clearChanges() {
        this._changes.clear();
    }

    @Override // androidx.compose.foundation.text.input.TextFieldBuffer.ChangeList
    public int getChangeCount() {
        return this._changes.getSize();
    }

    @Override // androidx.compose.foundation.text.input.TextFieldBuffer.ChangeList
    /* JADX INFO: renamed from: getOriginalRange--jx7JFs */
    public long mo1484getOriginalRangejx7JFs(int changeIndex) {
        Change change = (Change) this._changes.content[changeIndex];
        return TextRangeKt.TextRange(change.getOriginalStart(), change.getOriginalEnd());
    }

    @Override // androidx.compose.foundation.text.input.TextFieldBuffer.ChangeList
    /* JADX INFO: renamed from: getRange--jx7JFs */
    public long mo1485getRangejx7JFs(int changeIndex) {
        Change change = (Change) this._changes.content[changeIndex];
        return TextRangeKt.TextRange(change.getPreStart(), change.getPreEnd());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChangeList(changes=[");
        MutableVector<Change> mutableVector = this._changes;
        Object[] objArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            Change change = (Change) objArr[i];
            sb.append("(" + change.getOriginalStart() + ',' + change.getOriginalEnd() + ")->(" + change.getPreStart() + ',' + change.getPreEnd() + ')');
            if (i < getChangeCount() - 1) {
                sb.append(", ");
            }
        }
        sb.append("])");
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x0051  */
    /* JADX WARN: Code duplicated, block: B:25:0x0053  */
    public final void trackChange(int preStart, int preEnd, int postLength) {
        int preEnd2;
        if (preStart == preEnd && postLength == 0) {
            return;
        }
        int iMin = Math.min(preStart, preEnd);
        int iMax = Math.max(preStart, preEnd);
        int i = postLength - (iMax - iMin);
        Change change = null;
        boolean z = false;
        for (int i2 = 0; i2 < this._changes.getSize(); i2++) {
            Change change2 = (Change) this._changes.content[i2];
            int preStart2 = change2.getPreStart();
            if ((iMin > preStart2 || preStart2 > iMax) && (iMin > (preEnd2 = change2.getPreEnd()) || preEnd2 > iMax)) {
                int preStart3 = change2.getPreStart();
                if (iMin > change2.getPreEnd() || preStart3 > iMin) {
                    int preStart4 = change2.getPreStart();
                    if (iMax > change2.getPreEnd() || preStart4 > iMax) {
                        if (change2.getPreStart() > iMax && !z) {
                            appendNewChange(change, iMin, iMax, i);
                            z = true;
                        }
                        if (z) {
                            change2.setPreStart(change2.getPreStart() + i);
                            change2.setPreEnd(change2.getPreEnd() + i);
                        }
                        this._changesTemp.add(change2);
                    } else if (change == null) {
                        change = change2;
                    } else {
                        change.setPreEnd(change2.getPreEnd());
                        change.setOriginalEnd(change2.getOriginalEnd());
                    }
                } else if (change == null) {
                    change = change2;
                } else {
                    change.setPreEnd(change2.getPreEnd());
                    change.setOriginalEnd(change2.getOriginalEnd());
                }
            } else if (change == null) {
                change = change2;
            } else {
                change.setPreEnd(change2.getPreEnd());
                change.setOriginalEnd(change2.getOriginalEnd());
            }
        }
        if (!z) {
            appendNewChange(change, iMin, iMax, i);
        }
        MutableVector<Change> mutableVector = this._changes;
        this._changes = this._changesTemp;
        this._changesTemp = mutableVector;
        mutableVector.clear();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ChangeTracker() {
        ChangeTracker changeTracker = null;
        this(changeTracker, 1, changeTracker);
    }

    public /* synthetic */ ChangeTracker(ChangeTracker changeTracker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : changeTracker);
    }
}
