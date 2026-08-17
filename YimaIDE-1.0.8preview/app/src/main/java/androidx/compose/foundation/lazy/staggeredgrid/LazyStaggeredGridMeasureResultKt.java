package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo;
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResultKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\u0014\u0010\f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\r\u001a\u00020\u0004H\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u0018\u0010\u000e\u001a\u00020\u0004*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"findVisibleItem", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "itemIndex", "", "EmptyArray", "", "EmptyLazyStaggeredGridLayoutInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "getEmptyLazyStaggeredGridLayoutInfo", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "visibleItemsAverageSize", "calculateContentSize", "laneCount", "singleAxisViewportSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;)I", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyStaggeredGridMeasureResultKt {
    private static final int[] EmptyArray;
    private static final LazyStaggeredGridMeasureResult EmptyLazyStaggeredGridLayoutInfo;

    static {
        int[] iArr = new int[0];
        EmptyArray = iArr;
        EmptyLazyStaggeredGridLayoutInfo = new LazyStaggeredGridMeasureResult(iArr, iArr, 0.0f, new MeasureResult() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResultKt$EmptyLazyStaggeredGridLayoutInfo$1
            private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
            private final int height;
            private final int width;

            public static /* synthetic */ void getAlignmentLines$annotations() {
            }

            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }

            public int getHeight() {
                return this.height;
            }

            public int getWidth() {
                return this.width;
            }

            public void placeChildren() {
            }
        }, 0.0f, false, false, false, new LazyStaggeredGridSlots(iArr, iArr), new LazyStaggeredGridSpanProvider(new MutableIntervalList()), DensityKt.Density$default(1.0f, 0.0f, 2, (Object) null), 0, CollectionsKt.emptyList(), IntSize.Companion.getZero-YbymL2g(), 0, 0, 0, 0, 0, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), null);
    }

    public static int a(int i, LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo) {
        return lazyStaggeredGridItemInfo.getIndex() - i;
    }

    public static final int calculateContentSize(LazyStaggeredGridLayoutInfo lazyStaggeredGridLayoutInfo, int i) {
        int beforeContentPadding = lazyStaggeredGridLayoutInfo.getBeforeContentPadding() + lazyStaggeredGridLayoutInfo.getAfterContentPadding();
        return (lazyStaggeredGridLayoutInfo.getTotalItemsCount() == 0 || i <= 0) ? beforeContentPadding : (((visibleItemsAverageSize(lazyStaggeredGridLayoutInfo) * lazyStaggeredGridLayoutInfo.getTotalItemsCount()) / i) - lazyStaggeredGridLayoutInfo.getMainAxisItemSpacing()) + beforeContentPadding;
    }

    public static final LazyStaggeredGridItemInfo findVisibleItem(LazyStaggeredGridLayoutInfo lazyStaggeredGridLayoutInfo, final int i) {
        if (lazyStaggeredGridLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            return null;
        }
        int index = ((LazyStaggeredGridItemInfo) CollectionsKt.first(lazyStaggeredGridLayoutInfo.getVisibleItemsInfo())).getIndex();
        if (i > ((LazyStaggeredGridItemInfo) CollectionsKt.last(lazyStaggeredGridLayoutInfo.getVisibleItemsInfo())).getIndex() || index > i) {
            return null;
        }
        return (LazyStaggeredGridItemInfo) CollectionsKt.getOrNull(lazyStaggeredGridLayoutInfo.getVisibleItemsInfo(), CollectionsKt.binarySearch$default(lazyStaggeredGridLayoutInfo.getVisibleItemsInfo(), 0, 0, new Function1() { // from class: cx8
            public final Object invoke(Object obj) {
                return Integer.valueOf(LazyStaggeredGridMeasureResultKt.a(i, (LazyStaggeredGridItemInfo) obj));
            }
        }, 3, (Object) null));
    }

    public static final LazyStaggeredGridMeasureResult getEmptyLazyStaggeredGridLayoutInfo() {
        return EmptyLazyStaggeredGridLayoutInfo;
    }

    public static final int getSingleAxisViewportSize(LazyStaggeredGridLayoutInfo lazyStaggeredGridLayoutInfo) {
        return (int) (lazyStaggeredGridLayoutInfo.getOrientation() == Orientation.Vertical ? lazyStaggeredGridLayoutInfo.getViewportSize() & 4294967295L : lazyStaggeredGridLayoutInfo.getViewportSize() >> 32);
    }

    public static final int visibleItemsAverageSize(LazyStaggeredGridLayoutInfo lazyStaggeredGridLayoutInfo) {
        List<LazyStaggeredGridItemInfo> visibleItemsInfo = lazyStaggeredGridLayoutInfo.getVisibleItemsInfo();
        if (visibleItemsInfo.isEmpty()) {
            return 0;
        }
        int size = visibleItemsInfo.size();
        int size2 = 0;
        for (int i = 0; i < size; i++) {
            LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo = visibleItemsInfo.get(i);
            size2 += (int) (lazyStaggeredGridLayoutInfo.getOrientation() == Orientation.Vertical ? lazyStaggeredGridItemInfo.getSize() & 4294967295L : lazyStaggeredGridItemInfo.getSize() >> 32);
        }
        return (size2 / visibleItemsInfo.size()) + lazyStaggeredGridLayoutInfo.getMainAxisItemSpacing();
    }
}
