package androidx.compose.material3;

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
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SliderKt$RangeSliderImpl$2$1 implements MeasurePolicy {
    final /* synthetic */ RangeSliderState $state;

    public SliderKt$RangeSliderImpl$2$1(RangeSliderState rangeSliderState) {
        this.$state = rangeSliderState;
    }

    public static Unit a(Placeable placeable, int i, int i2, Placeable placeable2, int i3, int i4, Placeable placeable3, int i5, int i6, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i3, i4, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i5, i6, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (LayoutIdKt.getLayoutId(measurable) == RangeSliderComponents.STARTTHUMB) {
                final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(j);
                List<? extends Measurable> list2 = list;
                int size2 = list2.size();
                int i2 = 0;
                while (i2 < size2) {
                    Measurable measurable2 = list.get(i2);
                    if (LayoutIdKt.getLayoutId(measurable2) == RangeSliderComponents.ENDTHUMB) {
                        final Placeable placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(j);
                        int size3 = list2.size();
                        int i3 = 0;
                        while (i3 < size3) {
                            Measurable measurable3 = list.get(i3);
                            if (LayoutIdKt.getLayoutId(measurable3) == RangeSliderComponents.TRACK) {
                                final Placeable placeableMo4605measureBRTryo2 = measurable3.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(ConstraintsKt.m5995offsetNN6EwU$default(j, (-(placeableMo4605measureBRTryo0.getWidth() + placeableMo4605measureBRTryo1.getWidth())) / 2, 0, 2, null), 0, 0, 0, 0, 11, null));
                                int width = placeableMo4605measureBRTryo2.getWidth() + ((placeableMo4605measureBRTryo0.getWidth() + placeableMo4605measureBRTryo1.getWidth()) / 2);
                                int iMax = Math.max(placeableMo4605measureBRTryo2.getHeight(), Math.max(placeableMo4605measureBRTryo0.getHeight(), placeableMo4605measureBRTryo1.getHeight()));
                                this.$state.setTotalWidth$material3(width);
                                this.$state.updateMinMaxPx$material3();
                                float coercedActiveRangeStartAsFraction$material3 = this.$state.getCoercedActiveRangeStartAsFraction$material3();
                                boolean z = true;
                                boolean z2 = Intrinsics.areEqual(coercedActiveRangeStartAsFraction$material3, ArraysKt.firstOrNull(this.$state.getTickFractions())) || Intrinsics.areEqual(coercedActiveRangeStartAsFraction$material3, ArraysKt.lastOrNull(this.$state.getTickFractions()));
                                float coercedActiveRangeEndAsFraction$material3 = this.$state.getCoercedActiveRangeEndAsFraction$material3();
                                if (!Intrinsics.areEqual(coercedActiveRangeEndAsFraction$material3, ArraysKt.firstOrNull(this.$state.getTickFractions())) && !Intrinsics.areEqual(coercedActiveRangeEndAsFraction$material3, ArraysKt.lastOrNull(this.$state.getTickFractions()))) {
                                    z = false;
                                }
                                final int width2 = placeableMo4605measureBRTryo0.getWidth() / 2;
                                int i4 = placeableMo4605measureBRTryo2.get(SliderKt.getCornerSizeAlignmentLine());
                                int i5 = i4 != Integer.MIN_VALUE ? i4 : 0;
                                final int iRoundToInt = (this.$state.getSteps() <= 0 || z2) ? MathKt.roundToInt(placeableMo4605measureBRTryo2.getWidth() * coercedActiveRangeStartAsFraction$material3) : MathKt.roundToInt((placeableMo4605measureBRTryo2.getWidth() - (i5 * 2)) * coercedActiveRangeStartAsFraction$material3) + i5;
                                int width3 = (placeableMo4605measureBRTryo0.getWidth() - placeableMo4605measureBRTryo1.getWidth()) / 2;
                                final int iRoundToInt2 = (this.$state.getSteps() <= 0 || z) ? MathKt.roundToInt((placeableMo4605measureBRTryo2.getWidth() * coercedActiveRangeEndAsFraction$material3) + width3) : MathKt.roundToInt(((placeableMo4605measureBRTryo2.getWidth() - (i5 * 2)) * coercedActiveRangeEndAsFraction$material3) + width3) + i5;
                                final int height = (iMax - placeableMo4605measureBRTryo2.getHeight()) / 2;
                                final int height2 = (iMax - placeableMo4605measureBRTryo0.getHeight()) / 2;
                                final int height3 = (iMax - placeableMo4605measureBRTryo1.getHeight()) / 2;
                                return MeasureScope.layout$default(measureScope, width, iMax, null, new Function1() { // from class: androidx.compose.material3.w3
                                    public final Object invoke(Object obj) {
                                        return SliderKt$RangeSliderImpl$2$1.a(placeableMo4605measureBRTryo2, width2, height, placeableMo4605measureBRTryo0, iRoundToInt, height2, placeableMo4605measureBRTryo1, iRoundToInt2, height3, (Placeable.PlacementScope) obj);
                                    }
                                }, 4, null);
                            }
                            i3++;
                            j = j;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        wq6.a();
                        return null;
                    }
                    i2++;
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
