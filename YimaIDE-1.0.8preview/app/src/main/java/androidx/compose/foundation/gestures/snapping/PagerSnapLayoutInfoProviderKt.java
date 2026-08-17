package androidx.compose.foundation.gestures.snapping;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.pager.PageInfo;
import androidx.compose.foundation.pager.PagerLayoutInfo;
import androidx.compose.foundation.pager.PagerLayoutInfoKt;
import androidx.compose.foundation.pager.PagerSnapDistance;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H\u0000\u001a\u0014\u0010\t\u001a\u00020\n*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0002\u001a\f\u0010\f\u001a\u00020\b*\u00020\u0003H\u0002\u001a\u0017\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0082\b\u001a8\u0010\u0006\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0000¨\u0006\u0018"}, d2 = {"SnapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "pagerSnapDistance", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "calculateFinalSnappingBound", "Lkotlin/Function3;", "", "isScrollingForward", "", "velocity", "dragGestureDelta", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "snapPositionalThreshold", "flingVelocity", "lowerBoundOffset", "upperBoundOffset", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PagerSnapLayoutInfoProviderKt {
    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final PagerState pagerState, final PagerSnapDistance pagerSnapDistance, final Function3<? super Float, ? super Float, ? super Float, Float> function3) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.gestures.snapping.PagerSnapLayoutInfoProviderKt.SnapLayoutInfoProvider.1
            private final Pair<Float, Float> searchForSnappingBounds(SnapPosition snapPosition, float velocity) {
                float f;
                List<PageInfo> visiblePagesInfo = getLayoutInfo().getVisiblePagesInfo();
                PagerState pagerState2 = pagerState;
                int size = visiblePagesInfo.size();
                int i = 0;
                float f2 = Float.NEGATIVE_INFINITY;
                float f3 = Float.POSITIVE_INFINITY;
                while (true) {
                    f = 0.0f;
                    if (i >= size) {
                        break;
                    }
                    PageInfo pageInfo = visiblePagesInfo.get(i);
                    float fCalculateDistanceToDesiredSnapPosition = SnapPositionKt.calculateDistanceToDesiredSnapPosition(PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo()), getLayoutInfo().getBeforeContentPadding(), getLayoutInfo().getAfterContentPadding(), getLayoutInfo().getPageSize(), pageInfo.getOffset(), pageInfo.getIndex(), snapPosition, pagerState2.getPageCount());
                    if (fCalculateDistanceToDesiredSnapPosition <= 0.0f && fCalculateDistanceToDesiredSnapPosition > f2) {
                        f2 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    if (fCalculateDistanceToDesiredSnapPosition >= 0.0f && fCalculateDistanceToDesiredSnapPosition < f3) {
                        f3 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    i++;
                }
                if (f2 == Float.NEGATIVE_INFINITY) {
                    f2 = f3;
                }
                if (f3 == Float.POSITIVE_INFINITY) {
                    f3 = f2;
                }
                if (!pagerState.getCanScrollForward()) {
                    if (PagerSnapLayoutInfoProviderKt.isScrollingForward(pagerState, velocity)) {
                        f2 = 0.0f;
                        f3 = 0.0f;
                    } else {
                        f3 = 0.0f;
                    }
                }
                if (pagerState.getCanScrollBackward()) {
                    f = f2;
                } else if (!PagerSnapLayoutInfoProviderKt.isScrollingForward(pagerState, velocity)) {
                    f3 = 0.0f;
                }
                return TuplesKt.to(Float.valueOf(f), Float.valueOf(f3));
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(float velocity, float decayOffset) {
                int pageSize$foundation = pagerState.getPageSize$foundation() + pagerState.getPageSpacing$foundation();
                if (pageSize$foundation == 0) {
                    return 0.0f;
                }
                PagerState pagerState2 = pagerState;
                int firstVisiblePage = velocity < 0.0f ? pagerState2.getFirstVisiblePage() + 1 : pagerState2.getFirstVisiblePage();
                int iCoerceAtLeast = RangesKt.coerceAtLeast(Math.abs((RangesKt.coerceIn(pagerSnapDistance.calculateTargetPage(firstVisiblePage, RangesKt.coerceIn(((int) (decayOffset / pageSize$foundation)) + firstVisiblePage, 0, pagerState.getPageCount()), velocity, pagerState.getPageSize$foundation(), pagerState.getPageSpacing$foundation()), 0, pagerState.getPageCount()) - firstVisiblePage) * pageSize$foundation) - pageSize$foundation, 0);
                return iCoerceAtLeast == 0 ? iCoerceAtLeast : iCoerceAtLeast * Math.signum(velocity);
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnapOffset(float velocity) {
                Pair<Float, Float> pairSearchForSnappingBounds = searchForSnappingBounds(pagerState.getLayoutInfo().getSnapPosition(), velocity);
                float fFloatValue = ((Number) pairSearchForSnappingBounds.component1()).floatValue();
                float fFloatValue2 = ((Number) pairSearchForSnappingBounds.component2()).floatValue();
                float fFloatValue3 = ((Number) function3.invoke(Float.valueOf(velocity), Float.valueOf(fFloatValue), Float.valueOf(fFloatValue2))).floatValue();
                if (!(fFloatValue3 == fFloatValue || fFloatValue3 == fFloatValue2 || fFloatValue3 == 0.0f)) {
                    InlineClassHelperKt.throwIllegalStateException("Final Snapping Offset Should Be one of " + fFloatValue + ", " + fFloatValue2 + " or 0.0");
                }
                if (isValidDistance(fFloatValue3)) {
                    return fFloatValue3;
                }
                return 0.0f;
            }

            public final PagerLayoutInfo getLayoutInfo() {
                return pagerState.getLayoutInfo();
            }

            public final boolean isValidDistance(float f) {
                return (f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) ? false : true;
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0086 A[RETURN] */
    public static final float calculateFinalSnappingBound(PagerState pagerState, LayoutDirection layoutDirection, float f, float f2, float f3, float f4) {
        boolean zIsScrollingForward = isScrollingForward(pagerState, f2);
        if (pagerState.getLayoutInfo().getOrientation() != Orientation.Vertical && layoutDirection != LayoutDirection.Ltr) {
            zIsScrollingForward = !zIsScrollingForward;
        }
        int pageSize = pagerState.getLayoutInfo().getPageSize();
        float fDragGestureDelta = pageSize == 0 ? 0.0f : dragGestureDelta(pagerState) / pageSize;
        float f5 = fDragGestureDelta - ((int) fDragGestureDelta);
        int iCalculateFinalSnappingItem = LazyListSnapLayoutInfoProviderKt.calculateFinalSnappingItem(pagerState.getDensity(), f2);
        FinalSnappingItem.Companion companion = FinalSnappingItem.INSTANCE;
        if (!FinalSnappingItem.m736equalsimpl0(iCalculateFinalSnappingItem, companion.m740getClosestItembbeMdSM())) {
            if (!FinalSnappingItem.m736equalsimpl0(iCalculateFinalSnappingItem, companion.m741getNextItembbeMdSM())) {
                if (FinalSnappingItem.m736equalsimpl0(iCalculateFinalSnappingItem, companion.m742getPreviousItembbeMdSM())) {
                    return f3;
                }
                return 0.0f;
            }
            return f4;
        }
        if (Math.abs(f5) <= f ? Math.abs(fDragGestureDelta) < Math.abs(pagerState.getPositionThresholdFraction$foundation()) ? Math.abs(f3) >= Math.abs(f4) : !zIsScrollingForward : zIsScrollingForward) {
            return f4;
        }
        return f3;
    }

    private static final void debugLog(Function0<String> function0) {
    }

    private static final float dragGestureDelta(PagerState pagerState) {
        return pagerState.getLayoutInfo().getOrientation() == Orientation.Horizontal ? Float.intBitsToFloat((int) (pagerState.m1238getUpDownDifferenceF1C5BW0$foundation() >> 32)) : Float.intBitsToFloat((int) (pagerState.m1238getUpDownDifferenceF1C5BW0$foundation() & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isScrollingForward(PagerState pagerState, float f) {
        boolean reverseLayout = pagerState.getLayoutInfo().getReverseLayout();
        boolean z = (pagerState.isNotGestureAction$foundation() ? -f : dragGestureDelta(pagerState)) > 0.0f;
        return (z && reverseLayout) || !(z || reverseLayout);
    }
}
