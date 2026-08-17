package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0000\"\u0018\u0010\u0003\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0018\u0010\u0006\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\t"}, d2 = {"visibleLinesAverageMainAxisSize", "", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "singleAxisViewportSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)I", "firstVisibleItemLineIndex", "getFirstVisibleItemLineIndex", "calculateContentSize", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyGridLayoutInfoKt {
    public static final int calculateContentSize(LazyGridLayoutInfo lazyGridLayoutInfo) {
        int beforeContentPadding = lazyGridLayoutInfo.getBeforeContentPadding() + lazyGridLayoutInfo.getAfterContentPadding();
        if (lazyGridLayoutInfo.getTotalItemsCount() == 0) {
            return beforeContentPadding;
        }
        int iCeil = (int) Math.ceil(lazyGridLayoutInfo.getTotalItemsCount() / lazyGridLayoutInfo.getMaxSpan());
        return ((visibleLinesAverageMainAxisSize(lazyGridLayoutInfo) - lazyGridLayoutInfo.getMainAxisItemSpacing()) * iCeil) + ((iCeil - 1) * lazyGridLayoutInfo.getMainAxisItemSpacing()) + beforeContentPadding;
    }

    public static final int getFirstVisibleItemLineIndex(LazyGridLayoutInfo lazyGridLayoutInfo) {
        List<LazyGridItemInfo> visibleItemsInfo = lazyGridLayoutInfo.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        return lazyGridLayoutInfo.getOrientation() == Orientation.Vertical ? ((LazyGridItemInfo) CollectionsKt.first(visibleItemsInfo)).getRow() : ((LazyGridItemInfo) CollectionsKt.first(visibleItemsInfo)).getColumn();
    }

    public static final int getSingleAxisViewportSize(LazyGridLayoutInfo lazyGridLayoutInfo) {
        return (int) (lazyGridLayoutInfo.getOrientation() == Orientation.Vertical ? lazyGridLayoutInfo.mo1090getViewportSizeYbymL2g() & 4294967295L : lazyGridLayoutInfo.mo1090getViewportSizeYbymL2g() >> 32);
    }

    public static final int visibleLinesAverageMainAxisSize(LazyGridLayoutInfo lazyGridLayoutInfo) {
        boolean z = lazyGridLayoutInfo.getOrientation() == Orientation.Vertical;
        List<LazyGridItemInfo> visibleItemsInfo = lazyGridLayoutInfo.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < visibleItemsInfo.size()) {
            int iVisibleLinesAverageMainAxisSize$lineOf = visibleLinesAverageMainAxisSize$lineOf(z, lazyGridLayoutInfo, i);
            if (iVisibleLinesAverageMainAxisSize$lineOf == -1) {
                i++;
            } else {
                int iMax = 0;
                while (i < visibleItemsInfo.size() && visibleLinesAverageMainAxisSize$lineOf(z, lazyGridLayoutInfo, i) == iVisibleLinesAverageMainAxisSize$lineOf) {
                    iMax = Math.max(iMax, (int) (z ? visibleItemsInfo.get(i).mo1088getSizeYbymL2g() & 4294967295L : visibleItemsInfo.get(i).mo1088getSizeYbymL2g() >> 32));
                    i++;
                }
                i2 += iMax;
                i3++;
            }
        }
        return (i2 / i3) + lazyGridLayoutInfo.getMainAxisItemSpacing();
    }

    private static final int visibleLinesAverageMainAxisSize$lineOf(boolean z, LazyGridLayoutInfo lazyGridLayoutInfo, int i) {
        return z ? lazyGridLayoutInfo.getVisibleItemsInfo().get(i).getRow() : lazyGridLayoutInfo.getVisibleItemsInfo().get(i).getColumn();
    }
}
