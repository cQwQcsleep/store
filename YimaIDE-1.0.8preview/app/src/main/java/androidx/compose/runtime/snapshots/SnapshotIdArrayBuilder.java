package androidx.compose.runtime.snapshots;

import androidx.collection.LongList;
import androidx.collection.MutableLongList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\rJ\u000e\u0010\u000e\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdArrayBuilder;", "", "array", "", "Landroidx/compose/runtime/snapshots/SnapshotIdArray;", "<init>", "([J)V", "list", "Landroidx/collection/MutableLongList;", "add", "", "id", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "toArray", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SnapshotIdArrayBuilder {
    public static final int $stable = 8;
    private final MutableLongList list;

    public SnapshotIdArrayBuilder(long[] jArr) {
        MutableLongList mutableLongList;
        if (jArr != null) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
            mutableLongList = new MutableLongList(jArrCopyOf.length);
            mutableLongList.addAll(((LongList) mutableLongList)._size, jArrCopyOf);
        } else {
            mutableLongList = new MutableLongList(0, 1, (DefaultConstructorMarker) null);
        }
        this.list = mutableLongList;
    }

    public final void add(long id) {
        this.list.add(id);
    }

    public final long[] toArray() {
        MutableLongList mutableLongList = this.list;
        int i = ((LongList) mutableLongList)._size;
        if (i == 0) {
            return null;
        }
        long[] jArr = new long[i];
        long[] jArr2 = ((LongList) mutableLongList).content;
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = jArr2[i2];
        }
        return jArr;
    }
}
