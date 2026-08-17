package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"visibleItemsAverageSize", "", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "calculateContentSize", "singleAxisViewportSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/LazyListLayoutInfo;)I", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyListLayoutInfoKt {
    public static final int calculateContentSize(LazyListLayoutInfo lazyListLayoutInfo) {
        int beforeContentPadding = lazyListLayoutInfo.getBeforeContentPadding() + lazyListLayoutInfo.getAfterContentPadding();
        return lazyListLayoutInfo.getTotalItemsCount() == 0 ? beforeContentPadding : ((visibleItemsAverageSize(lazyListLayoutInfo) - lazyListLayoutInfo.getMainAxisItemSpacing()) * lazyListLayoutInfo.getTotalItemsCount()) + ((lazyListLayoutInfo.getTotalItemsCount() - 1) * lazyListLayoutInfo.getMainAxisItemSpacing()) + beforeContentPadding;
    }

    public static final int getSingleAxisViewportSize(LazyListLayoutInfo lazyListLayoutInfo) {
        return (int) (lazyListLayoutInfo.getOrientation() == Orientation.Vertical ? lazyListLayoutInfo.mo1056getViewportSizeYbymL2g() & 4294967295L : lazyListLayoutInfo.mo1056getViewportSizeYbymL2g() >> 32);
    }

    public static final int visibleItemsAverageSize(LazyListLayoutInfo lazyListLayoutInfo) {
        List<LazyListItemInfo> visibleItemsInfo = lazyListLayoutInfo.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        int size = visibleItemsInfo.size();
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            size2 += visibleItemsInfo.get(i).getSize();
        }
        return (size2 / visibleItemsInfo.size()) + lazyListLayoutInfo.getMainAxisItemSpacing();
    }
}
