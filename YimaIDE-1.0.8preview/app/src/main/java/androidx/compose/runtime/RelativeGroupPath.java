package androidx.compose.runtime;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/RelativeGroupPath;", "Landroidx/compose/runtime/SourceInformationGroupPath;", "parent", "index", "", "<init>", "(Landroidx/compose/runtime/SourceInformationGroupPath;I)V", "getParent", "()Landroidx/compose/runtime/SourceInformationGroupPath;", "getIndex", "()I", "getIdentity", "", "table", "Landroidx/compose/runtime/SlotTable;", "equals", "", "other", "hashCode", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class RelativeGroupPath extends SourceInformationGroupPath {
    private final int index;
    private final SourceInformationGroupPath parent;

    public RelativeGroupPath(SourceInformationGroupPath sourceInformationGroupPath, int i) {
        super(null);
        this.parent = sourceInformationGroupPath;
        this.index = i;
    }

    public boolean equals(Object other) {
        if (!(other instanceof RelativeGroupPath)) {
            return false;
        }
        RelativeGroupPath relativeGroupPath = (RelativeGroupPath) other;
        return Intrinsics.areEqual(relativeGroupPath.parent, this.parent) && relativeGroupPath.index == this.index;
    }

    @Override // androidx.compose.runtime.SourceInformationGroupPath
    public Object getIdentity(SlotTable table) {
        return new SourceInformationSlotTableGroupIdentity(this.parent.getIdentity(table), this.index);
    }

    public final int getIndex() {
        return this.index;
    }

    public final SourceInformationGroupPath getParent() {
        return this.parent;
    }

    public int hashCode() {
        return (this.index * 31) + this.parent.hashCode();
    }
}
