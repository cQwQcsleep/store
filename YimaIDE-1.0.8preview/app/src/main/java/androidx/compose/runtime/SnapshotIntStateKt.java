package androidx.compose.runtime;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"androidx/compose/runtime/SnapshotIntStateKt__SnapshotIntStateKt"}, k = 4, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnapshotIntStateKt {
    public static final int getValue(IntState intState, Object obj, KProperty<?> kProperty) {
        return SnapshotIntStateKt__SnapshotIntStateKt.getValue(intState, obj, kProperty);
    }

    public static final MutableIntState mutableIntStateOf(int i) {
        return SnapshotIntStateKt__SnapshotIntStateKt.mutableIntStateOf(i);
    }

    public static final void setValue(MutableIntState mutableIntState, Object obj, KProperty<?> kProperty, int i) {
        SnapshotIntStateKt__SnapshotIntStateKt.setValue(mutableIntState, obj, kProperty, i);
    }
}
