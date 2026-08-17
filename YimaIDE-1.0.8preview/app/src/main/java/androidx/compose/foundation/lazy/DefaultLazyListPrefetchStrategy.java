package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0014\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0015\u001a\u00020\u000f*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\u0014\u0010\u0019\u001a\u00020\u0003*\u00020\u00132\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u001c\u0010\u001b\u001a\u00020\u000f*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/lazy/DefaultLazyListPrefetchStrategy;", "Landroidx/compose/foundation/lazy/LazyListPrefetchStrategy;", "initialNestedPrefetchItemCount", "", "<init>", "(I)V", "indexToPrefetch", "currentPrefetchHandle", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "wasScrollingForward", "", "previousPassItemCount", "previousPassDelta", "", "onScroll", "", "Landroidx/compose/foundation/lazy/LazyListPrefetchScope;", "delta", "layoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "onVisibleItemsUpdated", "onNestedPrefetch", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "firstVisibleItemIndex", "resetPrefetchState", "calculateIndexToPrefetch", "scrollingForward", "evaluatePrefetchForCancellation", "currentPrefetchingIndex", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DefaultLazyListPrefetchStrategy implements LazyListPrefetchStrategy {
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private int indexToPrefetch;
    private final int initialNestedPrefetchItemCount;
    private float previousPassDelta;
    private int previousPassItemCount;
    private boolean wasScrollingForward;

    public DefaultLazyListPrefetchStrategy(int i) {
        this.initialNestedPrefetchItemCount = i;
        this.indexToPrefetch = -1;
        this.previousPassItemCount = -1;
    }

    private final int calculateIndexToPrefetch(LazyListLayoutInfo lazyListLayoutInfo, boolean z) {
        return z ? ((LazyListItemInfo) CollectionsKt.last(lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() + 1 : ((LazyListItemInfo) CollectionsKt.first(lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() - 1;
    }

    private final void evaluatePrefetchForCancellation(LazyListLayoutInfo lazyListLayoutInfo, int i, boolean z) {
        if (i == -1 || lazyListLayoutInfo.getVisibleItemsInfo().isEmpty() || i == calculateIndexToPrefetch(lazyListLayoutInfo, z)) {
            return;
        }
        resetPrefetchState();
    }

    private final void resetPrefetchState() {
        this.indexToPrefetch = -1;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
        if (prefetchHandle != null) {
            prefetchHandle.cancel();
        }
        this.currentPrefetchHandle = null;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onNestedPrefetch(NestedPrefetchScope nestedPrefetchScope, int i) {
        int nestedPrefetchItemCount = nestedPrefetchScope.getNestedPrefetchItemCount() == -1 ? this.initialNestedPrefetchItemCount : nestedPrefetchScope.getNestedPrefetchItemCount();
        for (int i2 = 0; i2 < nestedPrefetchItemCount; i2++) {
            nestedPrefetchScope.schedulePrecomposition(i + i2);
        }
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onScroll(LazyListPrefetchScope lazyListPrefetchScope, float f, LazyListLayoutInfo lazyListLayoutInfo) {
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        if (!lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            boolean z = f < 0.0f;
            int iCalculateIndexToPrefetch = calculateIndexToPrefetch(lazyListLayoutInfo, z);
            if (iCalculateIndexToPrefetch >= 0 && iCalculateIndexToPrefetch < lazyListLayoutInfo.getTotalItemsCount()) {
                if (iCalculateIndexToPrefetch != this.indexToPrefetch) {
                    if (this.wasScrollingForward != z) {
                        resetPrefetchState();
                    }
                    this.wasScrollingForward = z;
                    this.indexToPrefetch = iCalculateIndexToPrefetch;
                    this.currentPrefetchHandle = LazyListPrefetchScope.schedulePrefetch$default(lazyListPrefetchScope, iCalculateIndexToPrefetch, null, 2, null);
                }
                if (z) {
                    LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) CollectionsKt.last(lazyListLayoutInfo.getVisibleItemsInfo());
                    if (((lazyListItemInfo.getOffset() + lazyListItemInfo.getSize()) + lazyListLayoutInfo.getMainAxisItemSpacing()) - lazyListLayoutInfo.getViewportEndOffset() < (-f) && (prefetchHandle2 = this.currentPrefetchHandle) != null) {
                        prefetchHandle2.markAsUrgent();
                    }
                } else if (lazyListLayoutInfo.getViewportStartOffset() - ((LazyListItemInfo) CollectionsKt.first(lazyListLayoutInfo.getVisibleItemsInfo())).getOffset() < f && (prefetchHandle = this.currentPrefetchHandle) != null) {
                    prefetchHandle.markAsUrgent();
                }
            }
        }
        this.previousPassDelta = f;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onVisibleItemsUpdated(LazyListPrefetchScope lazyListPrefetchScope, LazyListLayoutInfo lazyListLayoutInfo) {
        evaluatePrefetchForCancellation(lazyListLayoutInfo, this.indexToPrefetch, this.wasScrollingForward);
        int totalItemsCount = lazyListLayoutInfo.getTotalItemsCount();
        int i = this.previousPassItemCount;
        if (i != -1 && this.previousPassDelta != 0.0f && i != totalItemsCount && !lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            int iCalculateIndexToPrefetch = calculateIndexToPrefetch(lazyListLayoutInfo, this.previousPassDelta < 0.0f);
            if (iCalculateIndexToPrefetch >= 0 && iCalculateIndexToPrefetch < totalItemsCount) {
                this.indexToPrefetch = iCalculateIndexToPrefetch;
                this.currentPrefetchHandle = LazyListPrefetchScope.schedulePrefetch$default(lazyListPrefetchScope, iCalculateIndexToPrefetch, null, 2, null);
            }
        }
        this.previousPassItemCount = totalItemsCount;
    }

    public DefaultLazyListPrefetchStrategy() {
        this(0, 1, null);
    }

    public /* synthetic */ DefaultLazyListPrefetchStrategy(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2 : i);
    }
}
