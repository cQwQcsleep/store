package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\"\u0018\u0010\t\u001a\u00020\u0005*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00030\rj\b\u0012\u0004\u0012\u00020\u0003`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"updatedVisibleItems", "", "T", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "firstVisibleIndex", "", "lastVisibleIndex", "positionedItems", "stickingItems", "mainAxisOffset", "getMainAxisOffset", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "LazyLayoutMeasuredItemIndexComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutMeasuredItemKt {
    private static final Comparator<LazyLayoutMeasuredItem> LazyLayoutMeasuredItemIndexComparator = new Comparator() { // from class: mu8
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return LazyLayoutMeasuredItemKt.a((LazyLayoutMeasuredItem) obj, (LazyLayoutMeasuredItem) obj2);
        }
    };

    public static int a(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, LazyLayoutMeasuredItem lazyLayoutMeasuredItem2) {
        return Intrinsics.compare(lazyLayoutMeasuredItem.getIndex(), lazyLayoutMeasuredItem2.getIndex());
    }

    private static final int getMainAxisOffset(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        long jMo1063getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo1063getOffsetBjo55l4(0);
        return lazyLayoutMeasuredItem.isVertical() ? IntOffset.getY-impl(jMo1063getOffsetBjo55l4) : IntOffset.getX-impl(jMo1063getOffsetBjo55l4);
    }

    public static final <T extends LazyLayoutMeasuredItem> List<T> updatedVisibleItems(int i, int i2, List<? extends T> list, List<? extends T> list2) {
        if (list.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<T> mutableList = CollectionsKt.toMutableList(list2);
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            T t = list.get(i3);
            int index = t.getIndex();
            if (i <= index && index <= i2) {
                mutableList.add(t);
            }
        }
        CollectionsKt.sortWith(mutableList, LazyLayoutMeasuredItemIndexComparator);
        return mutableList;
    }
}
