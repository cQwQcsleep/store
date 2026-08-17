package androidx.compose.foundation.lazy.grid;

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
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class LazyGridKt$rememberLazyGridMeasurePolicy$1$1 implements LazyLayoutMeasurePolicy {
    final /* synthetic */ PaddingValues $contentPadding;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ GraphicsContext $graphicsContext;
    final /* synthetic */ Arrangement.Horizontal $horizontalArrangement;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ Function0<LazyGridItemProvider> $itemProviderLambda;
    final /* synthetic */ boolean $reverseLayout;
    final /* synthetic */ LazyGridSlotsProvider $slots;
    final /* synthetic */ LazyGridState $state;
    final /* synthetic */ StickyItemsPlacement $stickyItemsScrollBehavior;
    final /* synthetic */ Arrangement.Vertical $verticalArrangement;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyGridKt$rememberLazyGridMeasurePolicy$1$1(LazyGridState lazyGridState, boolean z, PaddingValues paddingValues, boolean z2, Function0<? extends LazyGridItemProvider> function0, LazyGridSlotsProvider lazyGridSlotsProvider, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, CoroutineScope coroutineScope, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement) {
        this.$state = lazyGridState;
        this.$isVertical = z;
        this.$contentPadding = paddingValues;
        this.$reverseLayout = z2;
        this.$itemProviderLambda = function0;
        this.$slots = lazyGridSlotsProvider;
        this.$verticalArrangement = vertical;
        this.$horizontalArrangement = horizontal;
        this.$coroutineScope = coroutineScope;
        this.$graphicsContext = graphicsContext;
        this.$stickyItemsScrollBehavior = stickyItemsPlacement;
    }

    public static ArrayList a(LazyGridSpanLayoutProvider lazyGridSpanLayoutProvider, LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1, int i) {
        LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = lazyGridSpanLayoutProvider.getLineConfiguration(i);
        int firstItemIndex = lineConfiguration.getFirstItemIndex();
        ArrayList arrayList = new ArrayList(lineConfiguration.getSpans().size());
        List<GridItemSpan> spans = lineConfiguration.getSpans();
        int size = spans.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int iM1076getCurrentLineSpanimpl = GridItemSpan.m1076getCurrentLineSpanimpl(spans.get(i3).getPackedValue());
            arrayList.add(TuplesKt.to(Integer.valueOf(firstItemIndex), Constraints.box-impl(lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1.m1095childConstraintsJhjzzOo$foundation(i2, iM1076getCurrentLineSpanimpl))));
            firstItemIndex++;
            i2 += iM1076getCurrentLineSpanimpl;
        }
        return arrayList;
    }

    public static MeasureResult b(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j, int i, int i2, int i3, int i4, Function1 function1) {
        return lazyLayoutMeasureScope.layout(ConstraintsKt.constrainWidth-K40F9xA(j, i3 + i), ConstraintsKt.constrainHeight-K40F9xA(j, i4 + i2), MapsKt.emptyMap(), function1);
    }

    public static int c(LazyGridSpanLayoutProvider lazyGridSpanLayoutProvider, int i) {
        return lazyGridSpanLayoutProvider.getLineIndexOfItem(i);
    }

    /* JADX WARN: Type inference failed for: r28v0, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1, androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider] */
    /* JADX WARN: Type inference failed for: r3v10, types: [androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1, androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider] */
    /* JADX WARN: Type inference failed for: r3v11, types: [androidx.compose.foundation.lazy.grid.e] */
    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy
    /* JADX INFO: renamed from: measure-0kLqBqw */
    public final MeasureResult mo1053measure0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, final long j) {
        float spacing;
        long j2;
        int lineIndexOfItem;
        int firstVisibleItemScrollOffset;
        ObservableScopeInvalidator.m1154attachToScopeimpl(this.$state.m1096getMeasurementScopeInvalidatorzYiylxw$foundation());
        boolean z = this.$state.getHasLookaheadOccurred() || lazyLayoutMeasureScope.isLookingAhead();
        CheckScrollableContainerConstraintsKt.m350checkScrollableContainerConstraintsK40F9xA(j, this.$isVertical ? Orientation.Vertical : Orientation.Horizontal);
        boolean z2 = this.$isVertical;
        PaddingValues paddingValues = this.$contentPadding;
        int i = z2 ? lazyLayoutMeasureScope.roundToPx-0680j_4(paddingValues.mo869calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.roundToPx-0680j_4(PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
        boolean z3 = this.$isVertical;
        PaddingValues paddingValues2 = this.$contentPadding;
        int i2 = z3 ? lazyLayoutMeasureScope.roundToPx-0680j_4(paddingValues2.mo870calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection())) : lazyLayoutMeasureScope.roundToPx-0680j_4(PaddingKt.calculateEndPadding(paddingValues2, lazyLayoutMeasureScope.getLayoutDirection()));
        int i3 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.$contentPadding.getTop());
        int i4 = lazyLayoutMeasureScope.roundToPx-0680j_4(this.$contentPadding.getBottom());
        final int i5 = i3 + i4;
        final int i6 = i + i2;
        boolean z4 = this.$isVertical;
        int i7 = z4 ? i5 : i6;
        if (z4 && !this.$reverseLayout) {
            i2 = i3;
        } else if (z4 && this.$reverseLayout) {
            i2 = i4;
        } else if (!z4 && !this.$reverseLayout) {
            i2 = i;
        }
        final int i8 = i7 - i2;
        long j3 = ConstraintsKt.offset-NN6Ew-U(j, -i6, -i5);
        final LazyGridItemProvider lazyGridItemProvider = (LazyGridItemProvider) this.$itemProviderLambda.invoke();
        final LazyGridSpanLayoutProvider spanLayoutProvider = lazyGridItemProvider.getSpanLayoutProvider();
        final LazyGridSlots lazyGridSlotsMo1080invoke0kLqBqw = this.$slots.mo1080invoke0kLqBqw(lazyLayoutMeasureScope, j3);
        int length = lazyGridSlotsMo1080invoke0kLqBqw.getSizes().length;
        spanLayoutProvider.setSlotsPerLine(length);
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
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement when isVertical == false");
                wq6.a();
                return null;
            }
            spacing = horizontal.getSpacing();
        }
        final int i9 = lazyLayoutMeasureScope.roundToPx-0680j_4(spacing);
        final int itemCount = lazyGridItemProvider.getItemCount();
        int i10 = this.$isVertical ? Constraints.getMaxHeight-impl(j) - i5 : Constraints.getMaxWidth-impl(j) - i6;
        if (!this.$reverseLayout || i10 > 0) {
            j2 = IntOffset.constructor-impl((((long) i) << 32) | (((long) i3) & 4294967295L));
        } else {
            boolean z5 = this.$isVertical;
            if (!z5) {
                i += i10;
            }
            if (z5) {
                i3 += i10;
            }
            j2 = IntOffset.constructor-impl((((long) i) << 32) | (((long) i3) & 4294967295L));
        }
        final long j4 = j2;
        final LazyGridState lazyGridState = this.$state;
        final boolean z6 = this.$isVertical;
        final boolean z7 = this.$reverseLayout;
        final int i11 = i2;
        final ?? r28 = new LazyGridMeasuredItemProvider(lazyGridItemProvider, lazyLayoutMeasureScope, i9, lazyGridState, z6, z7, i11, i8, j4) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1
            final /* synthetic */ int $afterContentPadding;
            final /* synthetic */ int $beforeContentPadding;
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ boolean $reverseLayout;
            final /* synthetic */ LazyGridState $state;
            final /* synthetic */ LazyLayoutMeasureScope $this_LazyLayoutMeasurePolicy;
            final /* synthetic */ long $visualItemOffset;

            {
                this.$this_LazyLayoutMeasurePolicy = lazyLayoutMeasureScope;
                this.$state = lazyGridState;
                this.$isVertical = z6;
                this.$reverseLayout = z7;
                this.$beforeContentPadding = i11;
                this.$afterContentPadding = i8;
                this.$visualItemOffset = j4;
            }

            @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredItemProvider
            /* JADX INFO: renamed from: createItem-O3s9Psw, reason: not valid java name */
            public LazyGridMeasuredItem mo1089createItemO3s9Psw(int index, Object key, Object contentType, int crossAxisSize, int mainAxisSpacing, List<? extends Placeable> placeables, long constraints, int lane, int span) {
                return new LazyGridMeasuredItem(index, key, this.$isVertical, crossAxisSize, mainAxisSpacing, this.$reverseLayout, this.$this_LazyLayoutMeasurePolicy.getLayoutDirection(), this.$beforeContentPadding, this.$afterContentPadding, placeables, this.$visualItemOffset, contentType, this.$state.getItemAnimator$foundation(), constraints, lane, span, null);
            }
        };
        final boolean z8 = this.$isVertical;
        final ?? r3 = new LazyGridMeasuredLineProvider(z8, lazyGridSlotsMo1080invoke0kLqBqw, itemCount, i9, r28, spanLayoutProvider) { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ LazyGridSlots $resolvedSlots;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z8, lazyGridSlotsMo1080invoke0kLqBqw, itemCount, i9, r28, spanLayoutProvider);
                this.$isVertical = z8;
                this.$resolvedSlots = lazyGridSlotsMo1080invoke0kLqBqw;
            }

            @Override // androidx.compose.foundation.lazy.grid.LazyGridMeasuredLineProvider
            public LazyGridMeasuredLine createLine(int index, LazyGridMeasuredItem[] items, List<GridItemSpan> spans, int mainAxisSpacing) {
                return new LazyGridMeasuredLine(index, items, this.$resolvedSlots, spans, this.$isVertical, mainAxisSpacing);
            }
        };
        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.grid.d
            public final Object invoke(Object obj) {
                return LazyGridKt$rememberLazyGridMeasurePolicy$1$1.a(spanLayoutProvider, r3, ((Integer) obj).intValue());
            }
        };
        ?? r4 = new Function1() { // from class: androidx.compose.foundation.lazy.grid.e
            public final Object invoke(Object obj) {
                return Integer.valueOf(LazyGridKt$rememberLazyGridMeasurePolicy$1$1.c(spanLayoutProvider, ((Integer) obj).intValue()));
            }
        };
        Snapshot.Companion companion = Snapshot.Companion;
        LazyGridState lazyGridState2 = this.$state;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            int iUpdateScrollPositionIfTheFirstItemWasMoved$foundation = lazyGridState2.updateScrollPositionIfTheFirstItemWasMoved$foundation(lazyGridItemProvider, lazyGridState2.getFirstVisibleItemIndex());
            if (iUpdateScrollPositionIfTheFirstItemWasMoved$foundation < itemCount || itemCount <= 0) {
                lineIndexOfItem = spanLayoutProvider.getLineIndexOfItem(iUpdateScrollPositionIfTheFirstItemWasMoved$foundation);
                firstVisibleItemScrollOffset = lazyGridState2.getFirstVisibleItemScrollOffset();
            } else {
                lineIndexOfItem = spanLayoutProvider.getLineIndexOfItem(itemCount - 1);
                firstVisibleItemScrollOffset = 0;
            }
            int i12 = lineIndexOfItem;
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            LazyGridMeasureResult lazyGridMeasureResultM1091measureLazyGridt1x4au0 = LazyGridMeasureKt.m1091measureLazyGridt1x4au0(itemCount, r3, r28, i10, i11, i8, i9, i12, firstVisibleItemScrollOffset, (lazyLayoutMeasureScope.isLookingAhead() || !z) ? this.$state.getScrollToBeConsumed() : this.$state.getScrollDeltaBetweenPasses$foundation(), j3, this.$isVertical, this.$verticalArrangement, this.$horizontalArrangement, this.$reverseLayout, lazyLayoutMeasureScope, this.$state.getItemAnimator$foundation(), length, LazyLayoutBeyondBoundsStateKt.calculateLazyLayoutPinnedIndices(lazyGridItemProvider, this.$state.getPinnedItems(), this.$state.getBeyondBoundsInfo()), z, lazyLayoutMeasureScope.isLookingAhead(), this.$state.getApproachLayoutInfo(), this.$coroutineScope, this.$state.m1097getPlacementScopeInvalidatorzYiylxw$foundation(), this.$graphicsContext, function1, r4, this.$stickyItemsScrollBehavior, new Function3() { // from class: androidx.compose.foundation.lazy.grid.f
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LazyGridKt$rememberLazyGridMeasurePolicy$1$1.b(lazyLayoutMeasureScope, j, i6, i5, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (Function1) obj3);
                }
            });
            LazyGridState.applyMeasureResult$foundation$default(this.$state, lazyGridMeasureResultM1091measureLazyGridt1x4au0, lazyLayoutMeasureScope.isLookingAhead(), false, 4, null);
            Object prefetchStrategy = this.$state.getPrefetchStrategy();
            CacheWindowLogic cacheWindowLogic = prefetchStrategy instanceof CacheWindowLogic ? (CacheWindowLogic) prefetchStrategy : null;
            if (cacheWindowLogic != null) {
                LazyGridKt.keepAroundItems(cacheWindowLogic, lazyGridMeasureResultM1091measureLazyGridt1x4au0.getOrientation(), lazyGridMeasureResultM1091measureLazyGridt1x4au0.getVisibleItemsInfo(), r3);
            }
            return lazyGridMeasureResultM1091measureLazyGridt1x4au0;
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }
}
