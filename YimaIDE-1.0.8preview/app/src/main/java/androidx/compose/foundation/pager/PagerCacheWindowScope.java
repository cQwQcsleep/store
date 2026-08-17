package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.CacheWindowScope;
import androidx.compose.foundation.lazy.layout.CachedItem;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u00020\u00042\u0018\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002000/H\u0016J\u0010\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0004H\u0016J\u0010\u00105\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0004H\u0016J\u0010\u00106\u001a\u0002072\u0006\u00104\u001a\u00020\u0004H\u0016J\u0010\u00108\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0016J\b\u00109\u001a\u00020\u0004H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0014\u0010 \u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0017R\u0014\u0010\"\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0017R\u0014\u0010$\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u0017R\u0016\u0010&\u001a\u0004\u0018\u00010'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u00101\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0017¨\u0006:"}, d2 = {"Landroidx/compose/foundation/pager/PagerCacheWindowScope;", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "itemCount", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getItemCount", "()Lkotlin/jvm/functions/Function0;", "layoutInfo", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "getLayoutInfo", "()Landroidx/compose/foundation/pager/PagerMeasureResult;", "setLayoutInfo", "(Landroidx/compose/foundation/pager/PagerMeasureResult;)V", "state", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "getState", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "setState", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;)V", "totalItemsCount", "getTotalItemsCount", "()I", "hasVisibleItems", "", "getHasVisibleItems", "()Z", "mainAxisExtraSpaceStart", "getMainAxisExtraSpaceStart", "mainAxisExtraSpaceEnd", "getMainAxisExtraSpaceEnd", "firstVisibleLineIndex", "getFirstVisibleLineIndex", "lastVisibleLineIndex", "getLastVisibleLineIndex", "mainAxisViewportSize", "getMainAxisViewportSize", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "schedulePrefetch", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "lineIndex", "onItemPrefetched", "Lkotlin/Function2;", "", "visibleLineCount", "getVisibleLineCount", "getVisibleItemSize", "indexInVisibleLines", "getVisibleItemLine", "getVisibleLineKey", "", "getLastIndexInLine", "getLastLineIndex", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class PagerCacheWindowScope implements CacheWindowScope {
    private final Function0<Integer> itemCount;
    public PagerMeasureResult layoutInfo;
    public LazyLayoutPrefetchState state;

    public PagerCacheWindowScope(Function0<Integer> function0) {
        this.itemCount = function0;
    }

    public static Unit a(Function2 function2, PagerCacheWindowScope pagerCacheWindowScope, LazyLayoutPrefetchState.PrefetchResultScope prefetchResultScope) {
        function2.invoke(Integer.valueOf(prefetchResultScope.getIndex()), Integer.valueOf(pagerCacheWindowScope.getLayoutInfo().getPageSize()));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public Density getDensity() {
        return getLayoutInfo().getDensity();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getFirstVisibleLineIndex() {
        return (int) RangesKt.coerceAtLeast(((long) ((MeasuredPage) CollectionsKt.first(getLayoutInfo().getVisiblePagesInfo())).getIndex()) - ((long) getLayoutInfo().getBeyondViewportPageCount()), 0L);
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public boolean getHasVisibleItems() {
        return !getLayoutInfo().getVisiblePagesInfo().isEmpty();
    }

    public final Function0<Integer> getItemCount() {
        return this.itemCount;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastIndexInLine(int lineIndex) {
        return lineIndex;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastLineIndex() {
        if (getTotalItemsCount() == 0) {
            return -1;
        }
        return getTotalItemsCount() - 1;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getLastVisibleLineIndex() {
        return (int) RangesKt.coerceAtMost(((long) ((MeasuredPage) CollectionsKt.last(getLayoutInfo().getVisiblePagesInfo())).getIndex()) + ((long) getLayoutInfo().getBeyondViewportPageCount()), ((long) getTotalItemsCount()) - 1);
    }

    public final PagerMeasureResult getLayoutInfo() {
        PagerMeasureResult pagerMeasureResult = this.layoutInfo;
        if (pagerMeasureResult != null) {
            return pagerMeasureResult;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layoutInfo");
        return null;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisExtraSpaceEnd() {
        return Math.abs(((((MeasuredPage) CollectionsKt.last(getLayoutInfo().getVisiblePagesInfo())).getOffset() + getLayoutInfo().getPageSize()) + getLayoutInfo().getPageSpacing()) - getLayoutInfo().getViewportEndOffset());
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisExtraSpaceStart() {
        return Math.abs(RangesKt.coerceAtMost(((MeasuredPage) CollectionsKt.first(getLayoutInfo().getVisiblePagesInfo())).getOffset() + getLayoutInfo().getBeforeContentPadding(), 0));
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getMainAxisViewportSize() {
        return PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo());
    }

    public final LazyLayoutPrefetchState getState() {
        LazyLayoutPrefetchState lazyLayoutPrefetchState = this.state;
        if (lazyLayoutPrefetchState != null) {
            return lazyLayoutPrefetchState;
        }
        Intrinsics.throwUninitializedPropertyAccessException("state");
        return null;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getTotalItemsCount() {
        return ((Number) this.itemCount.invoke()).intValue();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleItemLine(int indexInVisibleLines) {
        int size = getLayoutInfo().getExtraPagesBefore().size();
        int size2 = getLayoutInfo().getVisiblePagesInfo().size();
        if (indexInVisibleLines < size) {
            return getLayoutInfo().getExtraPagesBefore().get(indexInVisibleLines).getIndex();
        }
        if (indexInVisibleLines >= size && indexInVisibleLines < size + size2) {
            return getLayoutInfo().getVisiblePagesInfo().get(indexInVisibleLines - size).getIndex();
        }
        if (indexInVisibleLines >= size + size2) {
            return getLayoutInfo().getExtraPagesAfter().get((indexInVisibleLines - size) - size2).getIndex();
        }
        return -1;
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleItemSize(int indexInVisibleLines) {
        return getLayoutInfo().getPageSize();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public int getVisibleLineCount() {
        return getLayoutInfo().getExtraPagesBefore().size() + getLayoutInfo().getVisiblePagesInfo().size() + getLayoutInfo().getExtraPagesAfter().size();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public Object getVisibleLineKey(int indexInVisibleLines) {
        int size = getLayoutInfo().getExtraPagesBefore().size();
        int size2 = getLayoutInfo().getVisiblePagesInfo().size();
        if (indexInVisibleLines < size) {
            return getLayoutInfo().getExtraPagesBefore().get(indexInVisibleLines).getKey();
        }
        if (indexInVisibleLines < size || indexInVisibleLines >= size + size2) {
            return indexInVisibleLines >= size + size2 ? getLayoutInfo().getExtraPagesAfter().get((indexInVisibleLines - size) - size2).getKey() : CachedItem.INSTANCE;
        }
        return getLayoutInfo().getVisiblePagesInfo().get(indexInVisibleLines - size).getKey();
    }

    @Override // androidx.compose.foundation.lazy.layout.CacheWindowScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> schedulePrefetch(int lineIndex, final Function2<? super Integer, ? super Integer, Unit> onItemPrefetched) {
        return CollectionsKt.listOf(getState().m1149schedulePrecompositionAndPremeasure_EkL_Y$foundation(lineIndex, getLayoutInfo().getChildConstraints(), true, new Function1() { // from class: androidx.compose.foundation.pager.d
            public final Object invoke(Object obj) {
                return PagerCacheWindowScope.a(onItemPrefetched, this, (LazyLayoutPrefetchState.PrefetchResultScope) obj);
            }
        }));
    }

    public final void setLayoutInfo(PagerMeasureResult pagerMeasureResult) {
        this.layoutInfo = pagerMeasureResult;
    }

    public final void setState(LazyLayoutPrefetchState lazyLayoutPrefetchState) {
        this.state = lazyLayoutPrefetchState;
    }
}
