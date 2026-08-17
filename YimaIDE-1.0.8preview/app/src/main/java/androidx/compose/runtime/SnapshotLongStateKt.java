package androidx.compose.runtime;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"androidx/compose/runtime/SnapshotLongStateKt__SnapshotLongStateKt"}, k = 4, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnapshotLongStateKt {
    public static final long getValue(LongState longState, Object obj, KProperty<?> kProperty) {
        return SnapshotLongStateKt__SnapshotLongStateKt.getValue(longState, obj, kProperty);
    }

    public static final MutableLongState mutableLongStateOf(long j) {
        return SnapshotLongStateKt__SnapshotLongStateKt.mutableLongStateOf(j);
    }

    public static final void setValue(MutableLongState mutableLongState, Object obj, KProperty<?> kProperty, long j) {
        SnapshotLongStateKt__SnapshotLongStateKt.setValue(mutableLongState, obj, kProperty, j);
    }
}
