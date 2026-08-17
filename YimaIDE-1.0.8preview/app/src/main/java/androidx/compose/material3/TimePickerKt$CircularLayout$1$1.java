package androidx.compose.material3;

import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerKt$CircularLayout$1$1 implements MeasurePolicy {
    final /* synthetic */ float $radiusToSizeRatio;

    public TimePickerKt$CircularLayout$1$1(float f) {
        this.$radiusToSizeRatio = f;
    }

    public static Unit a(Placeable placeable, List list, Placeable placeable2, long j, float f, float f2, Placeable.PlacementScope placementScope) {
        if (placeable != null) {
            Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Placeable placeable3 = (Placeable) list.get(i);
            double d = f;
            double d2 = ((double) (i * f2)) - 1.5707963267948966d;
            Placeable.PlacementScope.place$default(placementScope, placeable3, MathKt.roundToInt((Math.cos(d2) * d) + ((double) ((Constraints.m5975getMaxWidthimpl(j) / 2) - (placeable3.getWidth() / 2)))), MathKt.roundToInt((d * Math.sin(d2)) + ((double) ((Constraints.m5974getMaxHeightimpl(j) / 2) - (placeable3.getHeight() / 2)))), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            Placeable.PlacementScope.place$default(placementScope, placeable2, (Constraints.m5977getMinWidthimpl(j) - placeable2.getWidth()) / 2, (Constraints.m5976getMinHeightimpl(j) - placeable2.getHeight()) / 2, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, final long j) {
        Measurable measurable;
        Measurable measurable2;
        final float fM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j) * this.$radiusToSizeRatio;
        int i = 0;
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        ArrayList arrayList = new ArrayList(list.size());
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        for (int i2 = 0; i2 < size; i2++) {
            Measurable measurable3 = list.get(i2);
            Measurable measurable4 = measurable3;
            if (LayoutIdKt.getLayoutId(measurable4) != LayoutId.Selector && LayoutIdKt.getLayoutId(measurable4) != LayoutId.InnerCircle) {
                arrayList.add(measurable3);
            }
        }
        final ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i3 = 0; i3 < size2; i3++) {
            arrayList2.add(((Measurable) arrayList.get(i3)).mo4605measureBRTryo0(jM5965copyZbe2FdA$default));
        }
        int size3 = list2.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size3) {
                measurable = null;
                break;
            }
            measurable = list.get(i4);
            if (LayoutIdKt.getLayoutId(measurable) == LayoutId.Selector) {
                break;
            }
            i4++;
        }
        Measurable measurable5 = measurable;
        int size4 = list2.size();
        while (true) {
            if (i >= size4) {
                measurable2 = null;
                break;
            }
            measurable2 = list.get(i);
            if (LayoutIdKt.getLayoutId(measurable2) == LayoutId.InnerCircle) {
                break;
            }
            i++;
        }
        Measurable measurable6 = measurable2;
        final float size5 = 6.2831855f / arrayList2.size();
        Placeable placeableMo4605measureBRTryo0 = measurable5 != null ? measurable5.mo4605measureBRTryo0(jM5965copyZbe2FdA$default) : null;
        final Placeable placeableMo4605measureBRTryo1 = measurable6 != null ? measurable6.mo4605measureBRTryo0(jM5965copyZbe2FdA$default) : null;
        final Placeable placeable = placeableMo4605measureBRTryo0;
        return MeasureScope.layout$default(measureScope, Constraints.m5977getMinWidthimpl(j), Constraints.m5976getMinHeightimpl(j), null, new Function1() { // from class: androidx.compose.material3.y4
            public final Object invoke(Object obj) {
                return TimePickerKt$CircularLayout$1$1.a(placeable, arrayList2, placeableMo4605measureBRTryo1, j, fM5974getMaxHeightimpl, size5, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
