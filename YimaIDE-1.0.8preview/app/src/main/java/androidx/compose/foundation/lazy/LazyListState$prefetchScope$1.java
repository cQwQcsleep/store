package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.LazyListState$prefetchScope$1;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0019\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\nH\u0016¨\u0006\u000b"}, d2 = {"androidx/compose/foundation/lazy/LazyListState$prefetchScope$1", "Landroidx/compose/foundation/lazy/LazyListPrefetchScope;", "schedulePrefetch", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "index", "", "onPrefetchFinished", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListPrefetchResultScope;", "", "Lkotlin/ExtensionFunctionType;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyListState$prefetchScope$1 implements LazyListPrefetchScope {
    final /* synthetic */ LazyListState this$0;

    public LazyListState$prefetchScope$1(LazyListState lazyListState) {
        this.this$0 = lazyListState;
    }

    public static Unit a(Function1 function1, int i, LazyListMeasureResult lazyListMeasureResult, LazyLayoutPrefetchState.PrefetchResultScope prefetchResultScope) {
        if (function1 != null) {
            int placeablesCount = prefetchResultScope.getPlaceablesCount();
            int iMo1151getSizeYEO4UFw = 0;
            for (int i2 = 0; i2 < placeablesCount; i2++) {
                iMo1151getSizeYEO4UFw += (int) (lazyListMeasureResult.getOrientation() == Orientation.Vertical ? prefetchResultScope.mo1151getSizeYEO4UFw(i2) & 4294967295L : prefetchResultScope.mo1151getSizeYEO4UFw(i2) >> 32);
            }
            function1.invoke(new LazyListPrefetchResultScopeImpl(i, iMo1151getSizeYEO4UFw));
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchScope
    public LazyLayoutPrefetchState.PrefetchHandle schedulePrefetch(final int index, final Function1<? super LazyListPrefetchResultScope, Unit> onPrefetchFinished) {
        Snapshot.Companion companion = Snapshot.Companion;
        LazyListState lazyListState = this.this$0;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            final LazyListMeasureResult lazyListMeasureResult = (LazyListMeasureResult) lazyListState.layoutInfoState.getValue();
            return this.this$0.getPrefetchState().m1149schedulePrecompositionAndPremeasure_EkL_Y$foundation(index, lazyListMeasureResult.getChildConstraints(), this.this$0.executeRequestsInHighPriorityMode, new Function1() { // from class: tv8
                public final Object invoke(Object obj) {
                    return LazyListState$prefetchScope$1.a(onPrefetchFinished, index, lazyListMeasureResult, (LazyLayoutPrefetchState.PrefetchResultScope) obj);
                }
            });
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }
}
