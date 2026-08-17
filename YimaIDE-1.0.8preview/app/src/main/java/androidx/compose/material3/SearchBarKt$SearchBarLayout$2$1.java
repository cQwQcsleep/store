package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
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
public final class SearchBarKt$SearchBarLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $animationProgress;
    final /* synthetic */ MutableState<BackEventCompat> $currentBackEvent;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState<BackEventCompat> $firstBackEvent;
    final /* synthetic */ MutableWindowInsets $unconsumedInsets;

    public SearchBarKt$SearchBarLayout$2$1(Animatable<Float, AnimationVector1D> animatable, MutableWindowInsets mutableWindowInsets, MutableState<BackEventCompat> mutableState, MutableFloatState mutableFloatState, MutableState<BackEventCompat> mutableState2) {
        this.$animationProgress = animatable;
        this.$unconsumedInsets = mutableWindowInsets;
        this.$currentBackEvent = mutableState;
        this.$finalBackProgress = mutableFloatState;
        this.$firstBackEvent = mutableState2;
    }

    public static Unit a(MeasureScope measureScope, long j, MutableState mutableState, float f, float f2, MutableState mutableState2, int i, Placeable placeable, int i2, Placeable placeable2, int i3, Placeable placeable3, int i4, Placeable.PlacementScope placementScope) {
        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMinMargin);
        int iM820calculatePredictiveBackOffsetXrOvwMX4 = SearchBarKt.m820calculatePredictiveBackOffsetXrOvwMX4(j, iMo4551roundToPx0680j_4, (BackEventCompat) mutableState.getValue(), measureScope.getLayoutDirection(), f, f2);
        int iM821calculatePredictiveBackOffsetYdzo92Q0 = SearchBarKt.m821calculatePredictiveBackOffsetYdzo92Q0(j, iMo4551roundToPx0680j_4, (BackEventCompat) mutableState.getValue(), (BackEventCompat) mutableState2.getValue(), i, measureScope.mo4551roundToPx0680j_4(SearchBarKt.SearchBarPredictiveBackMaxOffsetY), f2);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iM820calculatePredictiveBackOffsetXrOvwMX4, iM821calculatePredictiveBackOffsetYdzo92Q0 + i2, 0.0f, 4, null);
        int i5 = iM821calculatePredictiveBackOffsetYdzo92Q0 + i3;
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, iM820calculatePredictiveBackOffsetXrOvwMX4, i5, 0.0f, 4, null);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, iM820calculatePredictiveBackOffsetXrOvwMX4, i5 + placeable2.getHeight() + i4, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, final long j) {
        Measurable measurable;
        final Placeable placeableMo4605measureBRTryo0;
        int i;
        int iM5974getMaxHeightimpl;
        final float fFloatValue = ((Number) this.$animationProgress.getValue()).floatValue();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Measurable measurable2 = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "InputField")) {
                List<? extends Measurable> list2 = list;
                int size2 = list2.size();
                int i3 = 0;
                while (i3 < size2) {
                    Measurable measurable3 = list.get(i3);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "Surface")) {
                        int size3 = list2.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 >= size3) {
                                measurable = null;
                                break;
                            }
                            measurable = list.get(i4);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "Content")) {
                                break;
                            }
                            i4++;
                        }
                        Measurable measurable4 = measurable;
                        final int top = this.$unconsumedInsets.getTop(measureScope) + measureScope.mo4551roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(SearchBarKt.getSearchBarVerticalPadding());
                        int iM5992constrainWidthK40F9xA = ConstraintsKt.m5992constrainWidthK40F9xA(j, measurable2.maxIntrinsicWidth(Constraints.m5974getMaxHeightimpl(j)));
                        int iM5991constrainHeightK40F9xA = ConstraintsKt.m5991constrainHeightK40F9xA(j, measurable2.minIntrinsicHeight(Constraints.m5975getMaxWidthimpl(j)));
                        int iRoundToInt = MathKt.roundToInt(Constraints.m5975getMaxWidthimpl(j) * 0.9f);
                        int iRoundToInt2 = MathKt.roundToInt(Constraints.m5974getMaxHeightimpl(j) * 0.9f);
                        final float fCalculatePredictiveBackMultiplier = SearchBarKt.calculatePredictiveBackMultiplier(this.$currentBackEvent.getValue(), fFloatValue, this.$finalBackProgress.getFloatValue());
                        int iLerp = MathHelpersKt.lerp(iM5992constrainWidthK40F9xA, iRoundToInt, fCalculatePredictiveBackMultiplier);
                        int i5 = top + iM5991constrainHeightK40F9xA;
                        int iLerp2 = MathHelpersKt.lerp(i5, iRoundToInt2, fCalculatePredictiveBackMultiplier);
                        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
                        int iM5974getMaxHeightimpl2 = Constraints.m5974getMaxHeightimpl(j);
                        int iLerp3 = MathHelpersKt.lerp(iLerp, iM5975getMaxWidthimpl, fFloatValue);
                        final int iLerp4 = MathHelpersKt.lerp(iLerp2, iM5974getMaxHeightimpl2, fFloatValue);
                        final int iLerp5 = MathHelpersKt.lerp(top, 0, fFloatValue);
                        final int iLerp6 = MathHelpersKt.lerp(0, iMo4551roundToPx0680j_4, fFloatValue);
                        final Placeable placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(ConstraintsKt.Constraints(iLerp3, iM5975getMaxWidthimpl, iM5991constrainHeightK40F9xA, iM5991constrainHeightK40F9xA));
                        int width = placeableMo4605measureBRTryo1.getWidth();
                        final Placeable placeableMo4605measureBRTryo2 = measurable3.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(width, iLerp4 - iLerp5));
                        if (measurable4 != null) {
                            if (Constraints.m5970getHasBoundedHeightimpl(j)) {
                                i = 0;
                                iM5974getMaxHeightimpl = RangesKt.coerceAtLeast(Constraints.m5974getMaxHeightimpl(j) - (i5 + iMo4551roundToPx0680j_4), 0);
                            } else {
                                i = 0;
                                iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
                            }
                            placeableMo4605measureBRTryo0 = measurable4.mo4605measureBRTryo0(ConstraintsKt.Constraints(width, width, i, iM5974getMaxHeightimpl));
                        } else {
                            placeableMo4605measureBRTryo0 = null;
                        }
                        final MutableState<BackEventCompat> mutableState = this.$currentBackEvent;
                        final MutableState<BackEventCompat> mutableState2 = this.$firstBackEvent;
                        return MeasureScope.layout$default(measureScope, width, iLerp4, null, new Function1() { // from class: androidx.compose.material3.t3
                            public final Object invoke(Object obj) {
                                return SearchBarKt$SearchBarLayout$2$1.a(measureScope, j, mutableState, fFloatValue, fCalculatePredictiveBackMultiplier, mutableState2, iLerp4, placeableMo4605measureBRTryo2, iLerp5, placeableMo4605measureBRTryo1, top, placeableMo4605measureBRTryo0, iLerp6, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    i3++;
                    measureScope = measureScope;
                    j = j;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }
}
