package androidx.compose.material3;

import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\"\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\"\u0010\u0013\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\"\u0010\u0014\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\"\u0010\u0016\u001a\u00020\u000f*\u00020\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/ChipLayoutMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "<init>", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicHeight", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class ChipLayoutMeasurePolicy implements MeasurePolicy {
    public static Unit a(Placeable placeable, int i, int i2, Placeable placeable2, int i3, Placeable placeable3, int i4, Placeable.PlacementScope placementScope) {
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, Alignment.INSTANCE.getCenterVertically().align(i, i2), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i3, 0, 0.0f, 4, null);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, i3 + placeable2.getWidth(), Alignment.INSTANCE.getCenterVertically().align(i4, i2), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        Integer numValueOf;
        if (!list.isEmpty()) {
            numValueOf = Integer.valueOf(list.get(0).maxIntrinsicHeight(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(list.get(i2).maxIntrinsicHeight(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        } else {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        int size = list.size();
        int iMaxIntrinsicWidth = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iMaxIntrinsicWidth += list.get(i2).maxIntrinsicWidth(i);
        }
        return iMaxIntrinsicWidth;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Measurable measurable;
        Measurable measurable2;
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                measurable = null;
                break;
            }
            measurable = list.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "leadingIcon")) {
                break;
            }
            i++;
        }
        Measurable measurable3 = measurable;
        final Placeable placeableMo4605measureBRTryo0 = measurable3 != null ? measurable3.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null)) : null;
        final int widthOrZero = LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo0);
        final int heightOrZero = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo0);
        int size2 = list2.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "trailingIcon")) {
                break;
            }
            i2++;
        }
        Measurable measurable4 = measurable2;
        Placeable placeableMo4605measureBRTryo1 = measurable4 != null ? measurable4.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null)) : null;
        int widthOrZero2 = LayoutUtilKt.getWidthOrZero(placeableMo4605measureBRTryo1);
        final int heightOrZero2 = LayoutUtilKt.getHeightOrZero(placeableMo4605measureBRTryo1);
        int size3 = list2.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Measurable measurable5 = list.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable5), "label")) {
                final Placeable placeableMo4605measureBRTryo2 = measurable5.mo4605measureBRTryo0(ConstraintsKt.m5995offsetNN6EwU$default(j, -(widthOrZero + widthOrZero2), 0, 2, null));
                int width = placeableMo4605measureBRTryo2.getWidth() + widthOrZero + widthOrZero2;
                final int iMax = Math.max(heightOrZero, Math.max(placeableMo4605measureBRTryo2.getHeight(), heightOrZero2));
                final Placeable placeable = placeableMo4605measureBRTryo1;
                return MeasureScope.layout$default(measureScope, width, iMax, null, new Function1() { // from class: androidx.compose.material3.s
                    public final Object invoke(Object obj) {
                        return ChipLayoutMeasurePolicy.a(placeableMo4605measureBRTryo0, heightOrZero, iMax, placeableMo4605measureBRTryo2, widthOrZero, placeable, heightOrZero2, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        Integer numValueOf;
        if (!list.isEmpty()) {
            numValueOf = Integer.valueOf(list.get(0).minIntrinsicHeight(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(list.get(i2).minIntrinsicHeight(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        } else {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        int size = list.size();
        int iMinIntrinsicWidth = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iMinIntrinsicWidth += list.get(i2).minIntrinsicWidth(i);
        }
        return iMinIntrinsicWidth;
    }
}
