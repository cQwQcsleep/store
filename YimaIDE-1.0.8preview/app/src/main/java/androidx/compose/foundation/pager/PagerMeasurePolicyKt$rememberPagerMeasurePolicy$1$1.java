package androidx.compose.foundation.pager;

import androidx.collection.IntObjectMapKt;
import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Dp$-pageSpacing$0, reason: not valid java name */
    final /* synthetic */ float f56$$v$c$androidxcomposeuiunitDp$pageSpacing$0;
    final /* synthetic */ int $beyondViewportPageCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ Function0<PagerLazyLayoutItemProvider> $itemProviderLambda;
    final /* synthetic */ Orientation $orientation;
    final /* synthetic */ Function0<Integer> $pageCount;
    final /* synthetic */ PageSize $pageSize;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ SnapPosition $snapPosition;
    final /* synthetic */ PagerState $state;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;

    public PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1(PagerState pagerState, Orientation orientation, PaddingValues paddingValues, boolean z, float f, PageSize pageSize, Function0<PagerLazyLayoutItemProvider> function0, Function0<Integer> function1, Alignment.Vertical vertical, Alignment.Horizontal horizontal, int i, SnapPosition snapPosition, CoroutineScope coroutineScope) {
        this.$state = pagerState;
        this.$orientation = orientation;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z;
        this.f56$$v$c$androidxcomposeuiunitDp$pageSpacing$0 = f;
        this.$pageSize = pageSize;
        this.$itemProviderLambda = function0;
        this.$pageCount = function1;
        this.$verticalAlignment = vertical;
        this.$horizontalAlignment = horizontal;
        this.$beyondViewportPageCount = i;
        this.$snapPosition = snapPosition;
        this.$coroutineScope = coroutineScope;
    }

    public static MeasureResult a(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j, int i, int i2, int i3, int i4, Function1 function1) {
        return lazyLayoutMeasureScope.layout(ConstraintsKt.constrainWidth-K40F9xA(j, i3 + i), ConstraintsKt.constrainHeight-K40F9xA(j, i4 + i2), MapsKt.emptyMap(), function1);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw */
    public final MeasureResult mo1053measure0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, final long j) {
        int i;
        long j2;
        ObservableScopeInvalidator.m1154attachToScopeimpl(this.$state.m1235getMeasurementScopeInvalidatorzYiylxw$foundation());
        Orientation orientation = this.$orientation;
        Orientation orientation2 = Orientation.Vertical;
        boolean z = orientation == orientation2;
        CheckScrollableContainerConstraintsKt.m350checkScrollableContainerConstraintsK40F9xA(j, z ? orientation2 : Orientation.Horizontal);
        PaddingValues paddingValues = this.$contentPadding;
        int i2 = z ? lazyLayoutMeasureScope.roundToPx-0680j_4(paddingValues.mo869calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.roundToPx-0680j_4(PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
        PaddingValues paddingValues2 = this.$contentPadding;
        int i3 = z ? lazyLayoutMeasureScope.roundToPx-0680j_4(paddingValues2.mo870calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.roundToPx-0680j_4(PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()));
        int i4 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.$contentPadding.getTop());
        int i5 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.$contentPadding.getBottom());
        final int i6 = i4 + i5;
        final int i7 = i2 + i3;
        int i8 = z ? i6 : i7;
        if (z && !this.$reverseLayout) {
            i = i4;
        } else if (z && this.$reverseLayout) {
            i = i5;
        } else {
            i = (z || this.$reverseLayout) ? i3 : i2;
        }
        int i9 = i8 - i;
        long j3 = ConstraintsKt.offset-NN6Ew-U(j, -i7, -i6);
        this.$state.setDensity$foundation(lazyLayoutMeasureScope);
        int i10 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.f56$$v$c$androidxcomposeuiunitDp$pageSpacing$0);
        int i11 = z ? Constraints.getMaxHeight-impl(j) - i6 : Constraints.getMaxWidth-impl(j) - i7;
        if (!this.$reverseLayout || i11 > 0) {
            j2 = IntOffset.constructor-impl((((long) i2) << 32) | (((long) i4) & 4294967295L));
        } else {
            if (!z) {
                i2 += i11;
            }
            if (z) {
                i4 += i11;
            }
            j2 = IntOffset.constructor-impl((((long) i4) & 4294967295L) | (((long) i2) << 32));
        }
        long j4 = j2;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(this.$pageSize.calculateMainAxisPageSize(lazyLayoutMeasureScope, i11, i10), 0);
        this.$state.m1239setPremeasureConstraintsBRTryo0$foundation(ConstraintsKt.Constraints$default(0, this.$orientation == orientation2 ? Constraints.getMaxWidth-impl(j3) : iCoerceAtLeast, 0, this.$orientation != orientation2 ? Constraints.getMaxHeight-impl(j3) : iCoerceAtLeast, 5, (Object) null));
        PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider = (PagerLazyLayoutItemProvider) this.$itemProviderLambda.invoke();
        int i12 = i11 + i + i9;
        Snapshot.Companion companion = Snapshot.Companion;
        PagerState pagerState = this.$state;
        SnapPosition snapPosition = this.$snapPosition;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            int iMatchScrollPositionWithKey$foundation = pagerState.matchScrollPositionWithKey$foundation(pagerLazyLayoutItemProvider, pagerState.getCurrentPage());
            int iCurrentPageOffset = PagerKt.currentPageOffset(snapPosition, i12, iCoerceAtLeast, i10, i, i9, pagerState.getCurrentPage(), pagerState.getCurrentPageOffsetFraction(), pagerState.getPageCount());
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            int i13 = i11;
            int i14 = i;
            PagerMeasureResult pagerMeasureResultM1232measurePager7L1iB3k = PagerMeasureKt.m1232measurePager7L1iB3k(lazyLayoutMeasureScope, ((Number) this.$pageCount.invoke()).intValue(), pagerLazyLayoutItemProvider, i13, i14, i9, i10, iMatchScrollPositionWithKey$foundation, iCurrentPageOffset, j3, this.$orientation, this.$verticalAlignment, this.$horizontalAlignment, this.$reverseLayout, j4, iCoerceAtLeast, this.$beyondViewportPageCount, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(pagerLazyLayoutItemProvider, this.$state.getPinnedPages(), this.$state.getBeyondBoundsInfo()), this.$snapPosition, this.$state.m1236getPlacementScopeInvalidatorzYiylxw$foundation(), this.$coroutineScope, lazyLayoutMeasureScope, new Function3() { // from class: androidx.compose.foundation.pager.e
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PagerMeasurePolicyKt$rememberPagerMeasurePolicy$1$1.a(lazyLayoutMeasureScope, j, i7, i6, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (Function1) obj3);
                }
            }, IntObjectMapKt.mutableIntObjectMapOf());
            PagerState.applyMeasureResult$foundation$default(this.$state, pagerMeasureResultM1232measurePager7L1iB3k, lazyLayoutMeasureScope.isLookingAhead(), false, 4, null);
            PagerMeasurePolicyKt.keepAroundItems(lazyLayoutMeasureScope, this.$state.getCacheWindowLogic(), pagerMeasureResultM1232measurePager7L1iB3k.getVisiblePagesInfo());
            return pagerMeasureResultM1232measurePager7L1iB3k;
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }
}
