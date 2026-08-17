package androidx.compose.foundation.lazy;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsStateKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class LazyListKt$rememberLazyListMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {
    final /* synthetic */ int $beyondBoundsItemCount;
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ GraphicsContext $graphicsContext;
    final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ Function0<LazyListItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyListState $state;
    final /* synthetic */ StickyItemsPlacement $stickyItemsPlacement;
    final /* synthetic */ Alignment.Vertical $verticalAlignment;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyListKt$rememberLazyListMeasurePolicy$1$1(LazyListState lazyListState, boolean z, PaddingValues paddingValues, boolean z2, Function0<? extends LazyListItemProvider> function0, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Alignment.Horizontal horizontal2, Alignment.Vertical vertical2) {
        this.$state = lazyListState;
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z2;
        this.$itemProviderLambda = function0;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$beyondBoundsItemCount = i;
        this.$coroutineScope = coroutineScope;
        this.$graphicsContext = graphicsContext;
        this.$stickyItemsPlacement = stickyItemsPlacement;
        this.$horizontalAlignment = horizontal2;
        this.$verticalAlignment = vertical2;
    }

    public static MeasureResult a(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j, int i, int i2, int i3, int i4, Function1 function1) {
        return lazyLayoutMeasureScope.layout(ConstraintsKt.constrainWidth-K40F9xA(j, i3 + i), ConstraintsKt.constrainHeight-K40F9xA(j, i4 + i2), MapsKt.emptyMap(), function1);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw, reason: not valid java name */
    public final MeasureResult mo1053measure0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, final long j) {
        final int i;
        float spacing;
        long j2;
        ObservableScopeInvalidator.m1154attachToScopeimpl(this.$state.m1068getMeasurementScopeInvalidatorzYiylxw$foundation());
        boolean z = this.$state.getHasLookaheadOccurred() || lazyLayoutMeasureScope.isLookingAhead();
        CheckScrollableContainerConstraintsKt.m350checkScrollableContainerConstraintsK40F9xA(j, this.$isVertical ? Orientation.Vertical : Orientation.Horizontal);
        boolean z2 = this.$isVertical;
        PaddingValues paddingValues = this.$contentPadding;
        int i2 = z2 ? lazyLayoutMeasureScope.roundToPx-0680j_4(paddingValues.mo869calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.roundToPx-0680j_4(PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
        boolean z3 = this.$isVertical;
        PaddingValues paddingValues2 = this.$contentPadding;
        int i3 = z3 ? lazyLayoutMeasureScope.roundToPx-0680j_4(paddingValues2.mo870calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.roundToPx-0680j_4(PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()));
        int i4 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.$contentPadding.getTop());
        int i5 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.$contentPadding.getBottom());
        final int i6 = i4 + i5;
        final int i7 = i2 + i3;
        boolean z4 = this.$isVertical;
        int i8 = z4 ? i6 : i7;
        if (z4 && !this.$reverseLayout) {
            i = i4;
        } else if (z4 && this.$reverseLayout) {
            i = i5;
        } else {
            i = (z4 || this.$reverseLayout) ? i3 : i2;
        }
        final int i9 = i8 - i;
        final long j3 = ConstraintsKt.offset-NN6Ew-U(j, -i7, -i6);
        final LazyListItemProvider lazyListItemProvider = (LazyListItemProvider) this.$itemProviderLambda.invoke();
        lazyListItemProvider.getItemScope().setMaxSize(Constraints.getMaxWidth-impl(j3), Constraints.getMaxHeight-impl(j3));
        if (this.$isVertical) {
            Arrangement.Vertical vertical = this.$verticalArrangement;
            if (vertical == null) {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement when isVertical == true");
                wq6.a();
                return null;
            }
            spacing = vertical.getSpacing();
        } else {
            Arrangement.Horizontal horizontal = this.$horizontalArrangement;
            if (horizontal == null) {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalAlignment when isVertical == false");
                wq6.a();
                return null;
            }
            spacing = horizontal.getSpacing();
        }
        final int i10 = lazyLayoutMeasureScope.roundToPx-0680j_4(spacing);
        final int itemCount = lazyListItemProvider.getItemCount();
        int i11 = this.$isVertical ? Constraints.getMaxHeight-impl(j) - i6 : Constraints.getMaxWidth-impl(j) - i7;
        if (!this.$reverseLayout || i11 > 0) {
            j2 = IntOffset.constructor-impl((((long) i4) & 4294967295L) | (((long) i2) << 32));
        } else {
            boolean z5 = this.$isVertical;
            if (!z5) {
                i2 += i11;
            }
            if (z5) {
                i4 += i11;
            }
            j2 = IntOffset.constructor-impl((((long) i4) & 4294967295L) | (((long) i2) << 32));
        }
        final boolean z6 = this.$isVertical;
        final Alignment.Horizontal horizontal2 = this.$horizontalAlignment;
        final long j4 = j2;
        final Alignment.Vertical vertical2 = this.$verticalAlignment;
        final boolean z7 = this.$reverseLayout;
        final LazyListState lazyListState = this.$state;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider = new LazyListMeasuredItemProvider(j3, z6, lazyListItemProvider, lazyLayoutMeasureScope, itemCount, i10, horizontal2, vertical2, z7, i, i9, j4, lazyListState) { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1
            final /* synthetic */ int $afterContentPadding;
            final /* synthetic */ int $beforeContentPadding;
            final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ int $itemsCount;
            final /* synthetic */ boolean $reverseLayout;
            final /* synthetic */ int $spaceBetweenItems;
            final /* synthetic */ LazyListState $state;
            final /* synthetic */ LazyLayoutMeasureScope $this_LazyLayoutMeasurePolicy;
            final /* synthetic */ Alignment.Vertical $verticalAlignment;
            final /* synthetic */ long $visualItemOffset;

            {
                this.$isVertical = z6;
                this.$this_LazyLayoutMeasurePolicy = lazyLayoutMeasureScope;
                this.$itemsCount = itemCount;
                this.$spaceBetweenItems = i10;
                this.$horizontalAlignment = horizontal2;
                this.$verticalAlignment = vertical2;
                this.$reverseLayout = z7;
                this.$beforeContentPadding = i;
                this.$afterContentPadding = i9;
                this.$visualItemOffset = j4;
                this.$state = lazyListState;
            }

            @Override // androidx.compose.foundation.lazy.LazyListMeasuredItemProvider
            /* JADX INFO: renamed from: createItem-X9ElhV4, reason: not valid java name */
            public LazyListMeasuredItem mo1054createItemX9ElhV4(int index, Object key, Object contentType, List<? extends Placeable> placeables, long constraints) {
                return new LazyListMeasuredItem(index, placeables, this.$isVertical, this.$horizontalAlignment, this.$verticalAlignment, this.$this_LazyLayoutMeasurePolicy.getLayoutDirection(), this.$reverseLayout, this.$beforeContentPadding, this.$afterContentPadding, index == this.$itemsCount + (-1) ? 0 : this.$spaceBetweenItems, this.$visualItemOffset, key, contentType, this.$state.getItemAnimator$foundation(), constraints, null);
            }
        };
        Snapshot.Companion companion = Snapshot.Companion;
        LazyListState lazyListState2 = this.$state;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            int iUpdateScrollPositionIfTheFirstItemWasMoved$foundation = lazyListState2.updateScrollPositionIfTheFirstItemWasMoved$foundation(lazyListItemProvider, lazyListState2.getFirstVisibleItemIndex());
            int firstVisibleItemScrollOffset = lazyListState2.getFirstVisibleItemScrollOffset();
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            LazyListMeasureResult lazyListMeasureResultM1058measureLazyList_s_dbAc = LazyListMeasureKt.m1058measureLazyList_s_dbAc(itemCount, lazyListMeasuredItemProvider, i11, i, i9, i10, iUpdateScrollPositionIfTheFirstItemWasMoved$foundation, firstVisibleItemScrollOffset, (lazyLayoutMeasureScope.isLookingAhead() || !z) ? this.$state.getScrollToBeConsumed() : this.$state.getScrollDeltaBetweenPasses$foundation(), j3, this.$isVertical, this.$verticalArrangement, this.$horizontalArrangement, this.$reverseLayout, lazyLayoutMeasureScope, this.$state.getItemAnimator$foundation(), this.$beyondBoundsItemCount, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyListItemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo()), z, lazyLayoutMeasureScope.isLookingAhead(), this.$coroutineScope, this.$state.m1069getPlacementScopeInvalidatorzYiylxw$foundation(), this.$graphicsContext, this.$stickyItemsPlacement, new Function3() { // from class: androidx.compose.foundation.lazy.c
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LazyListKt$rememberLazyListMeasurePolicy$1$1.a(lazyLayoutMeasureScope, j, i7, i6, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (Function1) obj3);
                }
            });
            LazyListState.applyMeasureResult$foundation$default(this.$state, lazyListMeasureResultM1058measureLazyList_s_dbAc, lazyLayoutMeasureScope.isLookingAhead(), false, 4, null);
            Object prefetchStrategy = this.$state.getPrefetchStrategy();
            CacheWindowLogic cacheWindowLogic = prefetchStrategy instanceof CacheWindowLogic ? (CacheWindowLogic) prefetchStrategy : null;
            if (cacheWindowLogic != null) {
                LazyListKt.keepAroundItems(cacheWindowLogic, lazyListMeasureResultM1058measureLazyList_s_dbAc.getVisibleItemsInfo(), lazyListMeasuredItemProvider);
            }
            return lazyListMeasureResultM1058measureLazyList_s_dbAc;
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }
}
