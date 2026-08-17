package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"LazyLayoutScrollScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "scrollScope", "Landroidx/compose/foundation/gestures/ScrollScope;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyGridScrollScopeKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridScrollScopeKt$LazyLayoutScrollScope$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0096\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006¨\u0006\u0017"}, d2 = {"androidx/compose/foundation/lazy/grid/LazyGridScrollScopeKt$LazyLayoutScrollScope$1", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "Landroidx/compose/foundation/gestures/ScrollScope;", "firstVisibleItemIndex", "", "getFirstVisibleItemIndex", "()I", "firstVisibleItemScrollOffset", "getFirstVisibleItemScrollOffset", "lastVisibleItemIndex", "getLastVisibleItemIndex", "itemCount", "getItemCount", "snapToItem", "", "index", "offset", "calculateDistanceTo", "targetIndex", "targetOffset", "scrollBy", "", "pixels", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements LazyLayoutScrollScope, ScrollScope {
        private final /* synthetic */ ScrollScope $$delegate_0;
        final /* synthetic */ LazyGridState $state;

        public AnonymousClass1(ScrollScope scrollScope, LazyGridState lazyGridState) {
            this.$state = lazyGridState;
            this.$$delegate_0 = scrollScope;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int calculateDistanceTo(int targetIndex, int targetOffset) {
            Integer numValueOf;
            LazyGridItemInfo lazyGridItemInfo;
            LazyGridLayoutInfo layoutInfo = this.$state.getLayoutInfo();
            int iVisibleLinesAverageMainAxisSize = 0;
            if (layoutInfo.getVisibleItemsInfo().isEmpty()) {
                return 0;
            }
            int firstVisibleItemIndex = getFirstVisibleItemIndex();
            if (targetIndex > getLastVisibleItemIndex() || firstVisibleItemIndex > targetIndex) {
                int slotsPerLine$foundation = this.$state.getSlotsPerLine$foundation();
                iVisibleLinesAverageMainAxisSize = (LazyGridLayoutInfoKt.visibleLinesAverageMainAxisSize(layoutInfo) * (((targetIndex - getFirstVisibleItemIndex()) + ((slotsPerLine$foundation - 1) * ((targetIndex < getFirstVisibleItemIndex() ? 1 : 0) != 0 ? -1 : 1))) / slotsPerLine$foundation)) - getFirstVisibleItemScrollOffset();
            } else {
                List<LazyGridItemInfo> visibleItemsInfo = layoutInfo.getVisibleItemsInfo();
                int size = visibleItemsInfo.size();
                int i = 0;
                while (true) {
                    numValueOf = null;
                    if (i >= size) {
                        lazyGridItemInfo = null;
                        break;
                    }
                    lazyGridItemInfo = visibleItemsInfo.get(i);
                    if (lazyGridItemInfo.getIndex() == targetIndex) {
                        break;
                    }
                    i++;
                }
                LazyGridItemInfo lazyGridItemInfo2 = lazyGridItemInfo;
                if (layoutInfo.getOrientation() == Orientation.Vertical) {
                    if (lazyGridItemInfo2 != null) {
                        numValueOf = Integer.valueOf(IntOffset.getY-impl(lazyGridItemInfo2.getOffset()));
                    }
                } else if (lazyGridItemInfo2 != null) {
                    numValueOf = Integer.valueOf(IntOffset.getX-impl(lazyGridItemInfo2.getOffset()));
                }
                if (numValueOf != null) {
                    iVisibleLinesAverageMainAxisSize = numValueOf.intValue();
                }
            }
            return iVisibleLinesAverageMainAxisSize + targetOffset;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getFirstVisibleItemIndex() {
            return this.$state.getFirstVisibleItemIndex();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getFirstVisibleItemScrollOffset() {
            return this.$state.getFirstVisibleItemScrollOffset();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getItemCount() {
            return this.$state.getLayoutInfo().getTotalItemsCount();
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public int getLastVisibleItemIndex() {
            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.lastOrNull(this.$state.getLayoutInfo().getVisibleItemsInfo());
            if (lazyGridItemInfo != null) {
                return lazyGridItemInfo.getIndex();
            }
            return 0;
        }

        @Override // androidx.compose.foundation.gestures.ScrollScope
        public float scrollBy(float pixels) {
            return this.$$delegate_0.scrollBy(pixels);
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
        public void snapToItem(int index, int offset) {
            this.$state.snapToItemIndexInternal$foundation(index, offset, true);
        }
    }

    public static final LazyLayoutScrollScope LazyLayoutScrollScope(LazyGridState lazyGridState, ScrollScope scrollScope) {
        return new AnonymousClass1(scrollScope, lazyGridState);
    }
}
