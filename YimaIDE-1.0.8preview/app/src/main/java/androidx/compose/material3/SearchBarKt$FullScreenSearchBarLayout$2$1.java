package androidx.compose.material3;

import androidx.compose.material3.internal.BackEventProgress;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.material3.internal.SwipeEdge;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SearchBarKt$FullScreenSearchBarLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ MutableState<BackEventProgress.InProgress> $firstInProgressValue;
    final /* synthetic */ MutableState<BackEventProgress.InProgress> $lastInProgressValue;
    final /* synthetic */ SearchBarState $state;
    final /* synthetic */ MutableWindowInsets $unconsumedInsets;

    public SearchBarKt$FullScreenSearchBarLayout$2$1(MutableState<BackEventProgress.InProgress> mutableState, SearchBarState searchBarState, MutableWindowInsets mutableWindowInsets, MutableState<BackEventProgress.InProgress> mutableState2) {
        this.$lastInProgressValue = mutableState;
        this.$state = searchBarState;
        this.$unconsumedInsets = mutableWindowInsets;
        this.$firstInProgressValue = mutableState2;
    }

    public static Unit a(MutableState mutableState, float f, final SearchBarState searchBarState, Placeable placeable, Placeable placeable2, int i, Placeable placeable3, int i2, long j, MeasureScope measureScope, int i3, MutableState mutableState2, int i4, int i5, Placeable.PlacementScope placementScope) {
        BackEventProgress.InProgress inProgress = (BackEventProgress.InProgress) mutableState.getValue();
        int iLerp = MathHelpersKt.lerp(0, inProgress != null ? measure_3p2s80s$lambda$6$endOffsetX(inProgress, j, measureScope, i3, searchBarState) : 0, f);
        BackEventProgress.InProgress inProgress2 = (BackEventProgress.InProgress) mutableState.getValue();
        int iLerp2 = MathHelpersKt.lerp(0, inProgress2 != null ? measure_3p2s80s$lambda$6$endOffsetY(inProgress2, mutableState2, j, i4, measureScope, i5, searchBarState) : 0, f);
        int iLerp3 = MathHelpersKt.lerp(SearchBarKt.getCollapsedBounds(searchBarState).getLeft(), iLerp, searchBarState.getProgress());
        int iLerp4 = MathHelpersKt.lerp(SearchBarKt.getCollapsedBounds(searchBarState).getTop(), iLerp2, searchBarState.getProgress());
        Placeable.PlacementScope.place$default(placementScope, placeable, iLerp3, iLerp4, 0.0f, 4, null);
        int i6 = iLerp4 + i;
        Placeable.PlacementScope.place$default(placementScope, placeable2, iLerp3, i6, 0.0f, 4, null);
        Placeable.PlacementScope.placeWithLayer$default(placementScope, placeable3, iLerp3, i6 + placeable2.getHeight() + i2, 0.0f, new Function1() { // from class: androidx.compose.material3.q3
            public final Object invoke(Object obj) {
                return SearchBarKt$FullScreenSearchBarLayout$2$1.b(searchBarState, (GraphicsLayerScope) obj);
            }
        }, 4, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit b(SearchBarState searchBarState, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(searchBarState.getProgress());
        return Unit.INSTANCE;
    }

    private static final int measure_3p2s80s$lambda$6$endOffsetX(BackEventProgress.InProgress inProgress, long j, MeasureScope measureScope, int i, SearchBarState searchBarState) {
        return RangesKt.coerceAtMost(RangesKt.coerceAtLeast(inProgress.getSwipeEdge() == SwipeEdge.Left ? (Constraints.m5975getMaxWidthimpl(j) - measureScope.mo4551roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin)) - i : measureScope.mo4551roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin), SearchBarKt.getCollapsedBounds(searchBarState).getRight() - i), SearchBarKt.getCollapsedBounds(searchBarState).getLeft());
    }

    private static final int measure_3p2s80s$lambda$6$endOffsetY(BackEventProgress.InProgress inProgress, MutableState<BackEventProgress.InProgress> mutableState, long j, int i, MeasureScope measureScope, int i2, SearchBarState searchBarState) {
        float touchY = inProgress.getTouchY();
        BackEventProgress.InProgress value = mutableState.getValue();
        if (value == null) {
            return 0;
        }
        float touchY2 = touchY - value.getTouchY();
        return RangesKt.coerceAtMost((MathHelpersKt.lerp(0, Math.min(RangesKt.coerceAtLeast(((Constraints.m5974getMaxHeightimpl(j) - i) / 2) - measureScope.mo4551roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin), 0), measureScope.mo4551roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMaxOffsetY)), Math.abs(touchY2) / Constraints.m5974getMaxHeightimpl(j)) * ((int) Math.signum(touchY2))) + i2, SearchBarKt.getCollapsedBounds(searchBarState).getTop());
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final MeasureScope measureScope2 = measureScope;
        List<? extends Measurable> list2 = list;
        final long j2 = j;
        float fTransform = SearchBarKt.transform(this.$lastInProgressValue.getValue());
        Integer numValueOf = Integer.valueOf(SearchBarKt.getCollapsedBounds(this.$state).getWidth());
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : measureScope2.mo4551roundToPx0680j_4(SearchBarKt.getSearchBarMinWidth());
        Integer numValueOf2 = Integer.valueOf(SearchBarKt.getCollapsedBounds(this.$state).getHeight());
        if (numValueOf2.intValue() == 0) {
            numValueOf2 = null;
        }
        int iIntValue2 = numValueOf2 != null ? numValueOf2.intValue() : measureScope2.mo4551roundToPx0680j_4(SearchBarDefaults.INSTANCE.m798getInputFieldHeightD9Ej5fM());
        final int iCoerceAtLeast = RangesKt.coerceAtLeast(MathKt.roundToInt(Constraints.m5975getMaxWidthimpl(j2) * 0.9f), iIntValue);
        final int iCoerceAtLeast2 = RangesKt.coerceAtLeast(MathKt.roundToInt(Constraints.m5974getMaxHeightimpl(j2) * 0.9f), iIntValue2);
        int iLerp = MathHelpersKt.lerp(Constraints.m5975getMaxWidthimpl(j2), iCoerceAtLeast, fTransform);
        int iLerp2 = MathHelpersKt.lerp(Constraints.m5974getMaxHeightimpl(j2), iCoerceAtLeast2, fTransform);
        int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j2, MathHelpersKt.lerp(iIntValue, iLerp, this.$state.getProgress()));
        int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j2, MathHelpersKt.lerp(iIntValue2, iLerp2, this.$state.getProgress()));
        int size = list2.size();
        int i = 0;
        while (i < size) {
            Measurable measurable = list2.get(i);
            final float f = fTransform;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "Surface")) {
                Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(iM5992constrainWidthK40F9xA, iM5991constrainHeightK40F9xA));
                List<? extends Measurable> list3 = list2;
                int size2 = list3.size();
                int i2 = 0;
                while (i2 < size2) {
                    Measurable measurable2 = list2.get(i2);
                    final Placeable placeable = placeableMo4605measureBRTryo0;
                    List<? extends Measurable> list4 = list3;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "InputField")) {
                        final Placeable placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(iM5992constrainWidthK40F9xA, iIntValue2));
                        final int top = this.$unconsumedInsets.getTop(measureScope2) + measureScope2.mo4551roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int iMo4551roundToPx0680j_4 = measureScope2.mo4551roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        final int iLerp3 = MathHelpersKt.lerp(0, top, Math.min(this.$state.getProgress(), 1.0f - f));
                        int iLerp4 = MathHelpersKt.lerp(0, iMo4551roundToPx0680j_4, this.$state.getProgress());
                        int height = placeableMo4605measureBRTryo1.getHeight() + iLerp3 + iLerp4;
                        int size3 = list4.size();
                        int i3 = 0;
                        while (i3 < size3) {
                            Measurable measurable3 = list2.get(i3);
                            int i4 = i3;
                            final int i5 = iLerp4;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "Content")) {
                                final Placeable placeableMo4605measureBRTryo2 = measurable3.mo4605measureBRTryo0(ConstraintsKt.Constraints(iM5992constrainWidthK40F9xA, iM5992constrainWidthK40F9xA, 0, RangesKt.coerceAtLeast(iM5991constrainHeightK40F9xA - height, 0)));
                                int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j2);
                                int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j2);
                                final MutableState<BackEventProgress.InProgress> mutableState = this.$lastInProgressValue;
                                final SearchBarState searchBarState = this.$state;
                                final MutableState<BackEventProgress.InProgress> mutableState2 = this.$firstInProgressValue;
                                return MeasureScope.layout$default(measureScope, iM5975getMaxWidthimpl, iM5974getMaxHeightimpl, null, new Function1() { // from class: androidx.compose.material3.r3
                                    public final Object invoke(Object obj) {
                                        return SearchBarKt$FullScreenSearchBarLayout$2$1.a(mutableState, f, searchBarState, placeable, placeableMo4605measureBRTryo1, iLerp3, placeableMo4605measureBRTryo2, i5, j2, measureScope2, iCoerceAtLeast, mutableState2, iCoerceAtLeast2, top, (Placeable.PlacementScope) obj);
                                    }
                                }, 4, null);
                            }
                            measureScope2 = measureScope;
                            j2 = j;
                            i3 = i4 + 1;
                            iLerp4 = i5;
                            list2 = list;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        wq6.a();
                        return null;
                    }
                    i2++;
                    measureScope2 = measureScope;
                    list2 = list;
                    j2 = j;
                    placeableMo4605measureBRTryo0 = placeable;
                    list3 = list4;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
            i++;
            measureScope2 = measureScope;
            list2 = list;
            j2 = j;
            fTransform = f;
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }
}
