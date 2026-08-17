package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;", "", "<init>", "()V", "eventRotatingIndex", "", "eventRotatingArray", "", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "smoothEventPosition", "Landroidx/compose/ui/geometry/Offset;", "change", "smoothEventPosition-tuRUvjQ", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;)J", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndirectPointerInputEventSmoother {
    private static final int SmoothingFactor = 3;
    private List<IndirectPointerInputChange> eventRotatingArray = new ArrayList();
    private int eventRotatingIndex;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: smoothEventPosition-tuRUvjQ, reason: not valid java name */
    public final long m621smoothEventPositiontuRUvjQ(IndirectPointerInputChange change) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (change.getPosition-F1C5BW0() >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (change.getPosition-F1C5BW0() & 4294967295L));
        if (IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(change)) {
            this.eventRotatingIndex = 0;
            this.eventRotatingArray.clear();
        }
        if (!IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(change) && !IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(change)) {
            int size = this.eventRotatingArray.size();
            List<IndirectPointerInputChange> list = this.eventRotatingArray;
            if (size == 3) {
                int i = this.eventRotatingIndex;
                this.eventRotatingIndex = i + 1;
                list.set(i, change);
            } else {
                list.add(change);
            }
            if (this.eventRotatingIndex == 3) {
                this.eventRotatingIndex = 0;
            }
            List<IndirectPointerInputChange> list2 = this.eventRotatingArray;
            ArrayList arrayList = new ArrayList(list2.size());
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                arrayList.add(Float.valueOf(Float.intBitsToFloat((int) (list2.get(i2).getPosition-F1C5BW0() >> 32))));
            }
            fIntBitsToFloat = (float) CollectionsKt.averageOfFloat(arrayList);
            List<IndirectPointerInputChange> list3 = this.eventRotatingArray;
            ArrayList arrayList2 = new ArrayList(list3.size());
            int size3 = list3.size();
            for (int i3 = 0; i3 < size3; i3++) {
                arrayList2.add(Float.valueOf(Float.intBitsToFloat((int) (list3.get(i3).getPosition-F1C5BW0() & 4294967295L))));
            }
            fIntBitsToFloat2 = (float) CollectionsKt.averageOfFloat(arrayList2);
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
    }
}
