package androidx.compose.material3;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/CenteredContentMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class CenteredContentMeasurePolicy implements MeasurePolicy {
    public static Unit a(Ref.IntRef intRef, List list, Placeable.PlacementScope placementScope) {
        int i = intRef.element;
        int size = list.size();
        int width = i;
        for (int i2 = 0; i2 < size; i2++) {
            Placeable placeable = (Placeable) list.get(i2);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, width, 0, 0.0f, 4, null);
            width += placeable.getWidth();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final ArrayList arrayList;
        int iCoerceAtMost;
        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j);
        int iM5976getMinHeightimpl = Constraints.m5976getMinHeightimpl(j);
        int size = list.size();
        if (size < 1) {
            return MeasureScope.layout$default(measureScope, iM5975getMaxWidthimpl, iM5976getMinHeightimpl, null, new Function1() { // from class: androidx.compose.material3.n
                public final Object invoke(Object obj) {
                    return CenteredContentMeasurePolicy.b((Placeable.PlacementScope) obj);
                }
            }, 4, null);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        int i = 0;
        if (Constraints.m5971getHasBoundedWidthimpl(j)) {
            int i2 = iM5975getMaxWidthimpl / size;
            int iCalculateCenteredContentHorizontalPadding = ShortNavigationBarKt.calculateCenteredContentHorizontalPadding(size, iM5975getMaxWidthimpl);
            intRef.element = iCalculateCenteredContentHorizontalPadding;
            int i3 = (iM5975getMaxWidthimpl - (iCalculateCenteredContentHorizontalPadding * 2)) / size;
            List<? extends Measurable> list2 = list;
            int size2 = list2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                int iMaxIntrinsicHeight = list.get(i4).maxIntrinsicHeight(i3);
                if (iM5976getMinHeightimpl < iMaxIntrinsicHeight) {
                    iM5976getMinHeightimpl = RangesKt.coerceAtMost(iMaxIntrinsicHeight, Constraints.m5974getMaxHeightimpl(j));
                }
            }
            ArrayList arrayList2 = new ArrayList(list.size());
            int size3 = list2.size();
            while (i < size3) {
                Measurable measurable = list.get(i);
                int iMaxIntrinsicWidth = measurable.maxIntrinsicWidth(Constraints.m5976getMinHeightimpl(j));
                if (i3 < iMaxIntrinsicWidth) {
                    iCoerceAtMost = RangesKt.coerceAtMost(iMaxIntrinsicWidth, i2);
                    intRef.element -= (iCoerceAtMost - i3) / 2;
                } else {
                    iCoerceAtMost = i3;
                }
                arrayList2.add(measurable.mo4605measureBRTryo0(ConstraintsKt.m5990constrainN9IONVI(j, Constraints.INSTANCE.m5985fixedJhjzzOo(iCoerceAtMost, iM5976getMinHeightimpl))));
                i++;
            }
            arrayList = arrayList2;
        } else {
            arrayList = new ArrayList(list.size());
            int size4 = list.size();
            while (i < size4) {
                arrayList.add(list.get(i).mo4605measureBRTryo0(ConstraintsKt.m5990constrainN9IONVI(j, Constraints.INSTANCE.m5986fixedHeightOenEA2s(iM5976getMinHeightimpl))));
                i++;
            }
        }
        return MeasureScope.layout$default(measureScope, iM5975getMaxWidthimpl, iM5976getMinHeightimpl, null, new Function1() { // from class: androidx.compose.material3.o
            public final Object invoke(Object obj) {
                return CenteredContentMeasurePolicy.a(intRef, arrayList, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
