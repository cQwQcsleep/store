package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragAndDropStartDetectorScope;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "requestDragAndDropTransfer", "", "offset", "Landroidx/compose/ui/geometry/Offset;", "requestDragAndDropTransfer-k-4lQ0M", "(J)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface DragAndDropStartDetectorScope extends PointerInputScope {
    /* JADX INFO: renamed from: requestDragAndDropTransfer-k-4lQ0M$default, reason: not valid java name */
    static /* synthetic */ void m489requestDragAndDropTransferk4lQ0M$default(DragAndDropStartDetectorScope dragAndDropStartDetectorScope, long j, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: requestDragAndDropTransfer-k-4lQ0M");
            return;
        }
        if ((i & 1) != 0) {
            j = Offset.Companion.getUnspecified-F1C5BW0();
        }
        dragAndDropStartDetectorScope.mo475requestDragAndDropTransferk4lQ0M(j);
    }

    /* JADX INFO: renamed from: requestDragAndDropTransfer-k-4lQ0M */
    void mo475requestDragAndDropTransferk4lQ0M(long offset);
}
