package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.LazyGridSnapLayoutInfoProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0014\u0010\u0015\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u001c\u0010\u0019\u001a\u00020\u0010*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u000bH\u0002J\u0014\u0010\u001c\u001a\u00020\u0003*\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000bH\u0002J\u0014\u0010\u001d\u001a\u00020\u0003*\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000bH\u0002J\b\u0010\u001e\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/lazy/grid/DefaultLazyGridPrefetchStrategy;", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchStrategy;", "initialNestedPrefetchItemCount", "", "<init>", "(I)V", "lineToPrefetch", "currentLinePrefetchHandles", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "wasScrollingForward", "", "previousPassItemCount", "previousPassDelta", "", "onScroll", "", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;", "delta", "layoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "onVisibleItemsUpdated", "onNestedPrefetch", "Landroidx/compose/foundation/lazy/layout/NestedPrefetchScope;", "firstVisibleItemIndex", "evaluatePrefetchForCancellation", "currentPrefetchingLineIndex", "scrollingForward", "calculateLineIndexToPrefetch", "calculateClosestNextItemToPrefetch", "resetPrefetchState", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DefaultLazyGridPrefetchStrategy implements LazyGridPrefetchStrategy {
    private final MutableVector<LazyLayoutPrefetchState.PrefetchHandle> currentLinePrefetchHandles;
    private final int initialNestedPrefetchItemCount;
    private int lineToPrefetch;
    private float previousPassDelta;
    private int previousPassItemCount;
    private boolean wasScrollingForward;

    public DefaultLazyGridPrefetchStrategy(int i) {
        this.initialNestedPrefetchItemCount = i;
        this.lineToPrefetch = -1;
        this.currentLinePrefetchHandles = new MutableVector<>(new LazyLayoutPrefetchState.PrefetchHandle[16], 0);
        this.previousPassItemCount = -1;
    }

    private final int calculateClosestNextItemToPrefetch(LazyGridLayoutInfo lazyGridLayoutInfo, boolean z) {
        return z ? ((LazyGridItemInfo) CollectionsKt.last(lazyGridLayoutInfo.getVisibleItemsInfo())).getIndex() + 1 : ((LazyGridItemInfo) CollectionsKt.first(lazyGridLayoutInfo.getVisibleItemsInfo())).getIndex() - 1;
    }

    private final int calculateLineIndexToPrefetch(LazyGridLayoutInfo lazyGridLayoutInfo, boolean z) {
        if (z) {
            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.last(lazyGridLayoutInfo.getVisibleItemsInfo());
            return (lazyGridLayoutInfo.getOrientation() == Orientation.Vertical ? lazyGridItemInfo.getRow() : lazyGridItemInfo.getColumn()) + 1;
        }
        LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) CollectionsKt.first(lazyGridLayoutInfo.getVisibleItemsInfo());
        return (lazyGridLayoutInfo.getOrientation() == Orientation.Vertical ? lazyGridItemInfo2.getRow() : lazyGridItemInfo2.getColumn()) - 1;
    }

    private final void evaluatePrefetchForCancellation(LazyGridLayoutInfo lazyGridLayoutInfo, int i, boolean z) {
        if (i == -1 || lazyGridLayoutInfo.getVisibleItemsInfo().isEmpty() || i == calculateLineIndexToPrefetch(lazyGridLayoutInfo, z)) {
            return;
        }
        resetPrefetchState();
    }

    private final void resetPrefetchState() {
        this.lineToPrefetch = -1;
        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector = this.currentLinePrefetchHandles;
        Object[] objArr = mutableVector.content;
        int size = mutableVector.getSize();
        for (int i = 0; i < size; i++) {
            ((LazyLayoutPrefetchState.PrefetchHandle) objArr[i]).cancel();
        }
        this.currentLinePrefetchHandles.clear();
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
    public void onNestedPrefetch(NestedPrefetchScope nestedPrefetchScope, int i) {
        int nestedPrefetchItemCount = nestedPrefetchScope.getNestedPrefetchItemCount() == -1 ? this.initialNestedPrefetchItemCount : nestedPrefetchScope.getNestedPrefetchItemCount();
        for (int i2 = 0; i2 < nestedPrefetchItemCount; i2++) {
            nestedPrefetchScope.schedulePrecomposition(i + i2);
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
    public void onScroll(LazyGridPrefetchScope lazyGridPrefetchScope, float f, LazyGridLayoutInfo lazyGridLayoutInfo) {
        if (!lazyGridLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            int i = 0;
            boolean z = f < 0.0f;
            int iCalculateLineIndexToPrefetch = calculateLineIndexToPrefetch(lazyGridLayoutInfo, z);
            int iCalculateClosestNextItemToPrefetch = calculateClosestNextItemToPrefetch(lazyGridLayoutInfo, z);
            if (iCalculateClosestNextItemToPrefetch >= 0 && iCalculateClosestNextItemToPrefetch < lazyGridLayoutInfo.getTotalItemsCount()) {
                if (iCalculateLineIndexToPrefetch != this.lineToPrefetch && iCalculateLineIndexToPrefetch >= 0) {
                    if (this.wasScrollingForward != z) {
                        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector = this.currentLinePrefetchHandles;
                        Object[] objArr = mutableVector.content;
                        int size = mutableVector.getSize();
                        for (int i2 = 0; i2 < size; i2++) {
                            ((LazyLayoutPrefetchState.PrefetchHandle) objArr[i2]).cancel();
                        }
                    }
                    this.wasScrollingForward = z;
                    this.lineToPrefetch = iCalculateLineIndexToPrefetch;
                    this.currentLinePrefetchHandles.clear();
                    MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector2 = this.currentLinePrefetchHandles;
                    mutableVector2.addAll(mutableVector2.getSize(), lazyGridPrefetchScope.scheduleLinePrefetch(iCalculateLineIndexToPrefetch));
                }
                if (z) {
                    LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.last(lazyGridLayoutInfo.getVisibleItemsInfo());
                    if (((LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis(lazyGridItemInfo, lazyGridLayoutInfo.getOrientation()) + LazyGridSnapLayoutInfoProviderKt.sizeOnMainAxis(lazyGridItemInfo, lazyGridLayoutInfo.getOrientation())) + lazyGridLayoutInfo.getMainAxisItemSpacing()) - lazyGridLayoutInfo.getViewportEndOffset() < (-f)) {
                        MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector3 = this.currentLinePrefetchHandles;
                        Object[] objArr2 = mutableVector3.content;
                        int size2 = mutableVector3.getSize();
                        while (i < size2) {
                            ((LazyLayoutPrefetchState.PrefetchHandle) objArr2[i]).markAsUrgent();
                            i++;
                        }
                    }
                } else if (lazyGridLayoutInfo.getViewportStartOffset() - LazyGridSnapLayoutInfoProviderKt.offsetOnMainAxis((LazyGridItemInfo) CollectionsKt.first(lazyGridLayoutInfo.getVisibleItemsInfo()), lazyGridLayoutInfo.getOrientation()) < f) {
                    MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector4 = this.currentLinePrefetchHandles;
                    Object[] objArr3 = mutableVector4.content;
                    int size3 = mutableVector4.getSize();
                    while (i < size3) {
                        ((LazyLayoutPrefetchState.PrefetchHandle) objArr3[i]).markAsUrgent();
                        i++;
                    }
                }
            }
        }
        this.previousPassDelta = f;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchStrategy
    public void onVisibleItemsUpdated(LazyGridPrefetchScope lazyGridPrefetchScope, LazyGridLayoutInfo lazyGridLayoutInfo) {
        evaluatePrefetchForCancellation(lazyGridLayoutInfo, this.lineToPrefetch, this.wasScrollingForward);
        int totalItemsCount = lazyGridLayoutInfo.getTotalItemsCount();
        int i = this.previousPassItemCount;
        if (i != -1 && this.previousPassDelta != 0.0f && i != totalItemsCount && !lazyGridLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            int iCalculateLineIndexToPrefetch = calculateLineIndexToPrefetch(lazyGridLayoutInfo, this.previousPassDelta < 0.0f);
            int iCalculateClosestNextItemToPrefetch = calculateClosestNextItemToPrefetch(lazyGridLayoutInfo, this.previousPassDelta < 0.0f);
            if (iCalculateClosestNextItemToPrefetch >= 0 && iCalculateClosestNextItemToPrefetch < lazyGridLayoutInfo.getTotalItemsCount() && iCalculateLineIndexToPrefetch != this.lineToPrefetch && iCalculateLineIndexToPrefetch >= 0) {
                this.lineToPrefetch = iCalculateLineIndexToPrefetch;
                this.currentLinePrefetchHandles.clear();
                MutableVector<LazyLayoutPrefetchState.PrefetchHandle> mutableVector = this.currentLinePrefetchHandles;
                mutableVector.addAll(mutableVector.getSize(), lazyGridPrefetchScope.scheduleLinePrefetch(iCalculateLineIndexToPrefetch));
            }
        }
        this.previousPassItemCount = totalItemsCount;
    }

    public DefaultLazyGridPrefetchStrategy() {
        this(0, 1, null);
    }

    public /* synthetic */ DefaultLazyGridPrefetchStrategy(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2 : i);
    }
}
