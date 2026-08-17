package androidx.compose.material3;

import androidx.compose.foundation.gestures.Orientation;
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
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SliderKt$SliderImpl$2$1 implements MeasurePolicy {
    final /* synthetic */ SliderState $state;

    public SliderKt$SliderImpl$2$1(SliderState sliderState) {
        this.$state = sliderState;
    }

    public static Unit a(Placeable placeable, int i, int i2, Placeable placeable2, int i3, Ref.IntRef intRef, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i, i2, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i3, intRef.element, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int width;
        int iMax;
        int width2;
        int height;
        int iRoundToInt;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (LayoutIdKt.getLayoutId(measurable) == SliderComponents.THUMB) {
                long j2 = j;
                final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(j2);
                int size2 = list.size();
                int i2 = 0;
                while (i2 < size2) {
                    Measurable measurable2 = list.get(i2);
                    if (LayoutIdKt.getLayoutId(measurable2) == SliderComponents.TRACK) {
                        Orientation orientation = this.$state.getOrientation();
                        Orientation orientation2 = Orientation.Vertical;
                        final Placeable placeableMo4605measureBRTryo1 = orientation == orientation2 ? measurable2.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(ConstraintsKt.m5995offsetNN6EwU$default(j2, 0, -placeableMo4605measureBRTryo0.getHeight(), 1, null), 0, 0, 0, 0, 14, null)) : measurable2.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(ConstraintsKt.m5995offsetNN6EwU$default(j, -placeableMo4605measureBRTryo0.getWidth(), 0, 2, null), 0, 0, 0, 0, 11, null));
                        final Ref.IntRef intRef = new Ref.IntRef();
                        float coercedValueAsFraction = this.$state.getCoercedValueAsFraction();
                        boolean z = Intrinsics.areEqual(coercedValueAsFraction, ArraysKt.firstOrNull(this.$state.getTickFractions())) || Intrinsics.areEqual(coercedValueAsFraction, ArraysKt.lastOrNull(this.$state.getTickFractions()));
                        int i3 = placeableMo4605measureBRTryo1.get(SliderKt.getCornerSizeAlignmentLine());
                        int i4 = i3 != Integer.MIN_VALUE ? i3 : 0;
                        if (this.$state.getOrientation() == orientation2) {
                            width = Math.max(placeableMo4605measureBRTryo1.getWidth(), placeableMo4605measureBRTryo0.getWidth());
                            iMax = placeableMo4605measureBRTryo0.getHeight() + placeableMo4605measureBRTryo1.getHeight();
                            width2 = (width - placeableMo4605measureBRTryo1.getWidth()) / 2;
                            height = placeableMo4605measureBRTryo0.getHeight() / 2;
                            iRoundToInt = (width - placeableMo4605measureBRTryo0.getWidth()) / 2;
                            intRef.element = (this.$state.getSteps() <= 0 || z) ? MathKt.roundToInt(placeableMo4605measureBRTryo1.getHeight() * coercedValueAsFraction) : MathKt.roundToInt((placeableMo4605measureBRTryo1.getHeight() - (i4 * 2)) * coercedValueAsFraction) + i4;
                            if (this.$state.getReverseVerticalDirection()) {
                                intRef.element = placeableMo4605measureBRTryo1.getHeight() - intRef.element;
                            }
                        } else {
                            width = placeableMo4605measureBRTryo0.getWidth() + placeableMo4605measureBRTryo1.getWidth();
                            iMax = Math.max(placeableMo4605measureBRTryo1.getHeight(), placeableMo4605measureBRTryo0.getHeight());
                            width2 = placeableMo4605measureBRTryo0.getWidth() / 2;
                            height = (iMax - placeableMo4605measureBRTryo1.getHeight()) / 2;
                            iRoundToInt = (this.$state.getSteps() <= 0 || z) ? MathKt.roundToInt(placeableMo4605measureBRTryo1.getWidth() * coercedValueAsFraction) : MathKt.roundToInt((placeableMo4605measureBRTryo1.getWidth() - (i4 * 2)) * coercedValueAsFraction) + i4;
                            intRef.element = (iMax - placeableMo4605measureBRTryo0.getHeight()) / 2;
                        }
                        final int i5 = height;
                        final int i6 = iRoundToInt;
                        final int i7 = width2;
                        this.$state.updateDimensions$material3(width, iMax);
                        return MeasureScope.layout$default(measureScope, width, iMax, null, new Function1() { // from class: androidx.compose.material3.x3
                            public final Object invoke(Object obj) {
                                return SliderKt$SliderImpl$2$1.a(placeableMo4605measureBRTryo1, i7, i5, placeableMo4605measureBRTryo0, i6, intRef, (Placeable.PlacementScope) obj);
                            }
                        }, 4, null);
                    }
                    i2++;
                    j2 = j;
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
