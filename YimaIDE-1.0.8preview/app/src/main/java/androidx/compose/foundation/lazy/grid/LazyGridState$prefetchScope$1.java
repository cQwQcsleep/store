package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.grid.LazyGridState$prefetchScope$1;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J1\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0019\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0002\b\u000bH\u0016¨\u0006\f"}, d2 = {"androidx/compose/foundation/lazy/grid/LazyGridState$prefetchScope$1", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchScope;", "scheduleLinePrefetch", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "lineIndex", "", "onPrefetchFinished", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchResultScope;", "", "Lkotlin/ExtensionFunctionType;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyGridState$prefetchScope$1 implements LazyGridPrefetchScope {
    final /* synthetic */ LazyGridState this$0;

    public LazyGridState$prefetchScope$1(LazyGridState lazyGridState) {
        this.this$0 = lazyGridState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scheduleLinePrefetch$lambda$0$0$0$0(List list, Ref.IntRef intRef, List list2, Function1 function1, int i, LazyGridMeasureResult lazyGridMeasureResult, LazyLayoutPrefetchState.PrefetchResultScope prefetchResultScope) {
        int placeablesCount = prefetchResultScope.getPlaceablesCount();
        int iMo1151getSizeYEO4UFw = 0;
        for (int i2 = 0; i2 < placeablesCount; i2++) {
            iMo1151getSizeYEO4UFw += (int) (lazyGridMeasureResult.getOrientation() == Orientation.Vertical ? prefetchResultScope.mo1151getSizeYEO4UFw(i2) & 4294967295L : prefetchResultScope.mo1151getSizeYEO4UFw(i2) >> 32);
        }
        if (list != null) {
            list.add(Integer.valueOf(iMo1151getSizeYEO4UFw));
        }
        if (intRef.element != list2.size()) {
            intRef.element++;
        } else if (function1 != null && list != null) {
            function1.invoke(new LazyGridPrefetchResultScopeImpl(i, list));
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> scheduleLinePrefetch(final int lineIndex, final Function1<? super LazyGridPrefetchResultScope, Unit> onPrefetchFinished) {
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = onPrefetchFinished == null ? null : new ArrayList();
        Snapshot.Companion companion = Snapshot.Companion;
        LazyGridState lazyGridState = this.this$0;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            final LazyGridMeasureResult approachLayoutInfo = lazyGridState.getHasLookaheadOccurred() ? lazyGridState.getApproachLayoutInfo() : (LazyGridMeasureResult) lazyGridState.layoutInfoState.getValue();
            if (approachLayoutInfo != null) {
                final Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = 1;
                final List list = (List) approachLayoutInfo.getPrefetchInfoRetriever().invoke(Integer.valueOf(lineIndex));
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Pair pair = (Pair) list.get(i);
                    arrayList.add(lazyGridState.getPrefetchState().m1149schedulePrecompositionAndPremeasure_EkL_Y$foundation(((Number) pair.getFirst()).intValue(), ((Constraints) pair.getSecond()).unbox-impl(), lazyGridState.executeRequestsInHighPriorityMode, new Function1() { // from class: wr8
                        public final Object invoke(Object obj) {
                            return LazyGridState$prefetchScope$1.scheduleLinePrefetch$lambda$0$0$0$0(arrayList2, intRef, list, onPrefetchFinished, lineIndex, approachLayoutInfo, (LazyLayoutPrefetchState.PrefetchResultScope) obj);
                        }
                    }));
                }
                Unit unit = Unit.INSTANCE;
            }
            return arrayList;
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridPrefetchScope
    public List<LazyLayoutPrefetchState.PrefetchHandle> scheduleLinePrefetch(int lineIndex) {
        return scheduleLinePrefetch(lineIndex, null);
    }
}
