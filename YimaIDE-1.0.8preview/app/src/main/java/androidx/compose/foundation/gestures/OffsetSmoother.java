package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/gestures/OffsetSmoother;", "", "<init>", "()V", "eventRotatingIndex", "", "eventRotatingArray", "", "Landroidx/compose/ui/geometry/Offset;", "smoothEventPosition", "offset", "smoothEventPosition-MK-Hz9U", "(J)J", "reset", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OffsetSmoother {
    public static final int $stable = 8;
    private List<Offset> eventRotatingArray = new ArrayList();
    private int eventRotatingIndex;

    public final void reset() {
        this.eventRotatingIndex = 0;
        this.eventRotatingArray.clear();
    }

    /* JADX INFO: renamed from: smoothEventPosition-MK-Hz9U, reason: not valid java name */
    public final long m631smoothEventPositionMKHz9U(long offset) {
        int size = this.eventRotatingArray.size();
        List<Offset> list = this.eventRotatingArray;
        if (size == 3) {
            int i = this.eventRotatingIndex;
            this.eventRotatingIndex = i + 1;
            list.set(i, Offset.box-impl(offset));
        } else {
            list.add(Offset.box-impl(offset));
        }
        if (this.eventRotatingIndex == 3) {
            this.eventRotatingIndex = 0;
        }
        List<Offset> list2 = this.eventRotatingArray;
        ArrayList arrayList = new ArrayList(list2.size());
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList.add(Float.valueOf(Float.intBitsToFloat((int) (list2.get(i2).unbox-impl() >> 32))));
        }
        float fAverageOfFloat = (float) CollectionsKt.averageOfFloat(arrayList);
        List<Offset> list3 = this.eventRotatingArray;
        ArrayList arrayList2 = new ArrayList(list3.size());
        int size3 = list3.size();
        for (int i3 = 0; i3 < size3; i3++) {
            arrayList2.add(Float.valueOf(Float.intBitsToFloat((int) (4294967295L & list3.get(i3).unbox-impl()))));
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(fAverageOfFloat)) << 32) | (((long) Float.floatToRawIntBits((float) CollectionsKt.averageOfFloat(arrayList2))) & 4294967295L));
    }
}
