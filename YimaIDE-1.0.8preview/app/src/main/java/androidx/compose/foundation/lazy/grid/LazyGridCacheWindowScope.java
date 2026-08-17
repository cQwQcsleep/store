package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt;
import androidx.compose.foundation.lazy.layout.CacheWindowScope;
import androidx.compose.foundation.lazy.layout.CachedItem;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u0006\u0010)\u001a\u00020\u00112\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020,0+H\u0016J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0016J\u0010\u00101\u001a\u0002022\u0006\u00100\u001a\u00020\u0011H\u0016J\u0010\u00103\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0016J\u0010\u00107\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u0011H\u0016J\b\u00108\u001a\u00020\u0011H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0014\u0010\u001a\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0014\u0010\u001c\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0014\u0010\u001e\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0014\u0010 \u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0013R\u0016\u0010\"\u001a\u0004\u0018\u00010#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010-\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0013R\u0015\u0010)\u001a\u00020\u0011*\u0002048F¢\u0006\u0006\u001a\u0004\b5\u00106¨\u00069"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridCacheWindowScope;", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "<init>", "()V", "layoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "getLayoutInfo", "()Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "setLayoutInfo", "(Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;)V", "prefetchScope", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;", "getPrefetchScope", "()Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;", "setPrefetchScope", "(Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;)V", "totalItemsCount", "", "getTotalItemsCount", "()I", "hasVisibleItems", "", "getHasVisibleItems", "()Z", "mainAxisExtraSpaceStart", "getMainAxisExtraSpaceStart", "mainAxisExtraSpaceEnd", "getMainAxisExtraSpaceEnd", "firstVisibleLineIndex", "getFirstVisibleLineIndex", "lastVisibleLineIndex", "getLastVisibleLineIndex", "mainAxisViewportSize", "getMainAxisViewportSize", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "schedulePrefetch", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "lineIndex", "onItemPrefetched", "Lkotlin/Function2;", "", "visibleLineCount", "getVisibleLineCount", "getVisibleItemSize", "indexInVisibleLines", "getVisibleLineKey", "", "getVisibleItemLine", "Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;", "getLineIndex", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;)I", "getLastIndexInLine", "getLastLineIndex", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class LazyGridCacheWindowScope implements CacheWindowScope {
    public LazyGridLayoutInfo layoutInfo;
    public LazyGridPrefetchScope prefetchScope;

    public static Unit a(Function2 function2, int i, LazyGridPrefetchResultScope lazyGridPrefetchResultScope) {
        int lineItemCount = lazyGridPrefetchResultScope.getLineItemCount();
        int iMaxOf = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < lineItemCount; i2++) {
            iMaxOf = ComparisonsKt.maxOf(lazyGridPrefetchResultScope.getMainAxisSize(i2), new int[0]);
        }
        if (iMaxOf != Integer.MIN_VALUE) {
            function2.invoke(Integer.valueOf(i), Integer.valueOf(iMaxOf));
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public Density getDensity() {
        LazyGridLayoutInfo layoutInfo = getLayoutInfo();
        LazyGridMeasureResult lazyGridMeasureResult = layoutInfo instanceof LazyGridMeasureResult ? (LazyGridMeasureResult) layoutInfo : null;
        if (lazyGridMeasureResult != null) {
            return lazyGridMeasureResult.getDensity();
        }
        return null;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getFirstVisibleLineIndex() {
        return getLineIndex((LazyGridItemInfo) CollectionsKt.first(getLayoutInfo().getVisibleItemsInfo()));
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public boolean getHasVisibleItems() {
        return !getLayoutInfo().getVisibleItemsInfo().isEmpty();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastIndexInLine(int lineIndex) {
        LazyGridLayoutInfo layoutInfo = getLayoutInfo();
        LazyGridMeasureResult lazyGridMeasureResult = layoutInfo instanceof LazyGridMeasureResult ? (LazyGridMeasureResult) layoutInfo : null;
        if (lazyGridMeasureResult == null) {
            return -1;
        }
        List list = (List) lazyGridMeasureResult.getPrefetchInfoRetriever().invoke(Integer.valueOf(lineIndex));
        if (list.isEmpty()) {
            return -1;
        }
        return (((Number) ((Pair) CollectionsKt.first(list)).getFirst()).intValue() + list.size()) - 1;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastLineIndex() {
        LazyGridLayoutInfo layoutInfo = getLayoutInfo();
        LazyGridMeasureResult lazyGridMeasureResult = layoutInfo instanceof LazyGridMeasureResult ? (LazyGridMeasureResult) layoutInfo : null;
        if (lazyGridMeasureResult == null || getTotalItemsCount() == 0) {
            return -1;
        }
        return ((Number) lazyGridMeasureResult.getLineIndexProvider().invoke(Integer.valueOf(getTotalItemsCount() - 1))).intValue();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastVisibleLineIndex() {
        return getLineIndex((LazyGridItemInfo) CollectionsKt.last(getLayoutInfo().getVisibleItemsInfo()));
    }

    public final LazyGridLayoutInfo getLayoutInfo() {
        LazyGridLayoutInfo lazyGridLayoutInfo = this.layoutInfo;
        if (lazyGridLayoutInfo != null) {
            return lazyGridLayoutInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layoutInfo");
        return null;
    }

    public final int getLineIndex(LazyGridItemInfo lazyGridItemInfo) {
        return LazyGridItemInfoKt.lineIndex(lazyGridItemInfo, getLayoutInfo().getOrientation());
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisExtraSpaceEnd() {
        LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.last(getLayoutInfo().getVisibleItemsInfo());
        return Math.abs(((LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(lazyGridItemInfo, getLayoutInfo().getOrientation()) + LazyGridSnapLayoutInfoProviderKt.sizeOnMainAxis(lazyGridItemInfo, getLayoutInfo().getOrientation())) + getLayoutInfo().getMainAxisItemSpacing()) - getLayoutInfo().getViewportEndOffset());
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisExtraSpaceStart() {
        return Math.abs(RangesKt.coerceAtMost(LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis((LazyGridItemInfo) CollectionsKt.first(getLayoutInfo().getVisibleItemsInfo()), getLayoutInfo().getOrientation()) + getLayoutInfo().getBeforeContentPadding(), 0));
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisViewportSize() {
        return LazyGridLayoutInfoKt.getSingleAxisViewportSize(getLayoutInfo());
    }

    public final LazyGridPrefetchScope getPrefetchScope() {
        LazyGridPrefetchScope lazyGridPrefetchScope = this.prefetchScope;
        if (lazyGridPrefetchScope != null) {
            return lazyGridPrefetchScope;
        }
        Intrinsics.throwUninitializedPropertyAccessException("prefetchScope");
        return null;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getTotalItemsCount() {
        return getLayoutInfo().getTotalItemsCount();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleItemLine(int indexInVisibleLines) {
        return getFirstVisibleLineIndex() + indexInVisibleLines;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleItemSize(int indexInVisibleLines) {
        int firstVisibleLineIndex = indexInVisibleLines + getFirstVisibleLineIndex();
        List<LazyGridItemInfo> visibleItemsInfo = getLayoutInfo().getVisibleItemsInfo();
        ArrayList arrayList = new ArrayList(visibleItemsInfo.size());
        int size = visibleItemsInfo.size();
        for (int i = 0; i < size; i++) {
            LazyGridItemInfo lazyGridItemInfo = visibleItemsInfo.get(i);
            if (getLineIndex(lazyGridItemInfo) == firstVisibleLineIndex) {
                arrayList.add(lazyGridItemInfo);
            }
        }
        int size2 = arrayList.size();
        int iMax = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            iMax = Math.max(LazyGridSnapLayoutInfoProviderKt.sizeOnMainAxis((LazyGridItemInfo) arrayList.get(i2), getLayoutInfo().getOrientation()), iMax);
        }
        return iMax;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleLineCount() {
        return (getLastVisibleLineIndex() - getFirstVisibleLineIndex()) + 1;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public Object getVisibleLineKey(int indexInVisibleLines) {
        Object key;
        int firstVisibleLineIndex = indexInVisibleLines + getFirstVisibleLineIndex();
        List<LazyGridItemInfo> visibleItemsInfo = getLayoutInfo().getVisibleItemsInfo();
        ArrayList arrayList = new ArrayList(visibleItemsInfo.size());
        int size = visibleItemsInfo.size();
        for (int i = 0; i < size; i++) {
            LazyGridItemInfo lazyGridItemInfo = visibleItemsInfo.get(i);
            if (getLineIndex(lazyGridItemInfo) == firstVisibleLineIndex) {
                arrayList.add(lazyGridItemInfo);
            }
        }
        LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) CollectionsKt.firstOrNull(arrayList);
        return (lazyGridItemInfo2 == null || (key = lazyGridItemInfo2.getKey()) == null) ? CachedItem.INSTANCE : key;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> schedulePrefetch(final int lineIndex, final Function2<? super Integer, ? super Integer, Unit> onItemPrefetched) {
        return getPrefetchScope().scheduleLinePrefetch(lineIndex, new Function1() { // from class: androidx.compose.foundation.lazy.grid.a
            public final Object invoke(Object obj) {
                return LazyGridCacheWindowScope.a(onItemPrefetched, lineIndex, (LazyGridPrefetchResultScope) obj);
            }
        });
    }

    public final void setLayoutInfo(LazyGridLayoutInfo lazyGridLayoutInfo) {
        this.layoutInfo = lazyGridLayoutInfo;
    }

    public final void setPrefetchScope(LazyGridPrefetchScope lazyGridPrefetchScope) {
        this.prefetchScope = lazyGridPrefetchScope;
    }
}
