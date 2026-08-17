package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00060\u0003¢\u0006\u0004\b\b\u0010\tJ)\u0010\n\u001a\u00020\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/text/TextMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "shouldMeasureLinks", "Lkotlin/Function0;", "", "placements", "", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TextMeasurePolicy implements MeasurePolicy {
    private final Function0<List<Rect>> placements;
    private final Function0<Boolean> shouldMeasureLinks;

    /* JADX WARN: Multi-variable type inference failed */
    public TextMeasurePolicy(Function0<Boolean> function0, Function0<? extends List<Rect>> function1) {
        this.shouldMeasureLinks = function0;
        this.placements = function1;
    }

    public static Unit a(List list, List list2, Placeable.PlacementScope placementScope) {
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Pair pair = (Pair) list.get(i);
                Placeable.PlacementScope.place-70tqf50$default(placementScope, (Placeable) pair.component1(), ((IntOffset) pair.component2()).unbox-impl(), 0.0f, 2, (Object) null);
            }
        }
        if (list2 != null) {
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Pair pair2 = (Pair) list2.get(i2);
                Placeable placeable = (Placeable) pair2.component1();
                Function0 function0 = (Function0) pair2.component2();
                Placeable.PlacementScope.place-70tqf50$default(placementScope, placeable, function0 != null ? ((IntOffset) function0.invoke()).unbox-impl() : IntOffset.Companion.getZero-nOcc-ac(), 0.0f, 2, (Object) null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m1448measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(list.size());
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (!(measurable.getParentData() instanceof TextRangeLayoutModifier)) {
                arrayList2.add(measurable);
            }
        }
        List list3 = (List) this.placements.invoke();
        if (list3 != null) {
            ArrayList arrayList3 = new ArrayList(list3.size());
            int size2 = list3.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Rect rect = (Rect) list3.get(i2);
                Pair pair = rect != null ? new Pair(((Measurable) arrayList2.get(i2)).measure-BRTryo0(ConstraintsKt.Constraints$default(0, (int) Math.floor(rect.getRight() - rect.getLeft()), 0, (int) Math.floor(rect.getBottom() - rect.getTop()), 5, (Object) null)), IntOffset.box-impl(IntOffset.constructor-impl((((long) Math.round(rect.getTop())) & 4294967295L) | (((long) Math.round(rect.getLeft())) << 32)))) : null;
                if (pair != null) {
                    arrayList3.add(pair);
                }
            }
            arrayList = arrayList3;
        } else {
            arrayList = null;
        }
        ArrayList arrayList4 = new ArrayList(list.size());
        int size3 = list2.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Measurable measurable2 = list.get(i3);
            if (measurable2.getParentData() instanceof TextRangeLayoutModifier) {
                arrayList4.add(measurable2);
            }
        }
        final List listMeasureWithTextRangeMeasureConstraints = BasicTextKt.measureWithTextRangeMeasureConstraints(arrayList4, this.shouldMeasureLinks);
        return MeasureScope.layout$default(measureScope, Constraints.getMaxWidth-impl(j), Constraints.getMaxHeight-impl(j), (Map) null, new Function1() { // from class: androidx.compose.foundation.text.m
            public final Object invoke(Object obj) {
                return TextMeasurePolicy.a(arrayList, listMeasureWithTextRangeMeasureConstraints, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }
}
