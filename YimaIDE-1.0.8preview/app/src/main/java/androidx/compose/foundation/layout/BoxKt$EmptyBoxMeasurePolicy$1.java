package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class BoxKt$EmptyBoxMeasurePolicy$1 implements MeasurePolicy {
    public static final BoxKt$EmptyBoxMeasurePolicy$1 INSTANCE = new BoxKt$EmptyBoxMeasurePolicy$1();

    public static Unit a(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult m809measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        return MeasureScope.layout$default(measureScope, Constraints.getMinWidth-impl(j), Constraints.getMinHeight-impl(j), (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.b
            public final Object invoke(Object obj) {
                return BoxKt$EmptyBoxMeasurePolicy$1.a((Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }
}
