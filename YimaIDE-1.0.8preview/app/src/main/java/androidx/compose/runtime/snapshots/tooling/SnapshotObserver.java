package androidx.compose.runtime.snapshots.tooling;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u001e\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/snapshots/tooling/SnapshotObserver;", "", "onPreCreate", "Landroidx/compose/runtime/snapshots/tooling/SnapshotInstanceObservers;", "parent", "Landroidx/compose/runtime/snapshots/Snapshot;", "readonly", "", "onCreated", "", "snapshot", "observers", "onPreDispose", "onApplied", "changed", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface SnapshotObserver {
    default void onApplied(Snapshot snapshot, Set<? extends Object> changed) {
    }

    default void onCreated(Snapshot snapshot, Snapshot parent, SnapshotInstanceObservers observers) {
    }

    default SnapshotInstanceObservers onPreCreate(Snapshot parent, boolean readonly) {
        return null;
    }

    default void onPreDispose(Snapshot snapshot) {
    }
}
