package androidx.compose.foundation.text;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u0007\u001a\u00020\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/text/LinksTextMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "shouldMeasureLinks", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class LinksTextMeasurePolicy implements MeasurePolicy {
    private final Function0<Boolean> shouldMeasureLinks;

    public LinksTextMeasurePolicy(Function0<Boolean> function0) {
        this.shouldMeasureLinks = function0;
    }

    public static Unit a(List list, LinksTextMeasurePolicy linksTextMeasurePolicy, Placeable.PlacementScope placementScope) {
        List listMeasureWithTextRangeMeasureConstraints = BasicTextKt.measureWithTextRangeMeasureConstraints(list, linksTextMeasurePolicy.shouldMeasureLinks);
        if (listMeasureWithTextRangeMeasureConstraints != null) {
            int size = listMeasureWithTextRangeMeasureConstraints.size();
            for (int i = 0; i < size; i++) {
                Pair pair = (Pair) listMeasureWithTextRangeMeasureConstraints.get(i);
                Placeable placeable = (Placeable) pair.component1();
                Function0 function0 = (Function0) pair.component2();
                Placeable.PlacementScope.place-70tqf50$default(placementScope, placeable, function0 != null ? ((IntOffset) function0.invoke()).unbox-impl() : IntOffset.Companion.getZero-nOcc-ac(), 0.0f, 2, (Object) null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m1381measure3p2s80s(MeasureScope measureScope, final List<? extends Measurable> list, long j) {
        return MeasureScope.layout$default(measureScope, Constraints.getMaxWidth-impl(j), Constraints.getMaxHeight-impl(j), (Map) null, new Function1() { // from class: androidx.compose.foundation.text.h
            public final Object invoke(Object obj) {
                return LinksTextMeasurePolicy.a(list, this, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }
}
