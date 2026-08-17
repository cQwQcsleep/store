package androidx.compose.foundation.text;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class AnnotatedStringResolveInlineContentKt$InlineChildren$1$2$1 implements MeasurePolicy {
    public static final AnnotatedStringResolveInlineContentKt$InlineChildren$1$2$1 INSTANCE = new AnnotatedStringResolveInlineContentKt$InlineChildren$1$2$1();

    public static Unit a(List list, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i), 0, 0, 0.0f, 4, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult m1293measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i).measure-BRTryo0(j));
        }
        return MeasureScope.layout$default(measureScope, Constraints.getMaxWidth-impl(j), Constraints.getMaxHeight-impl(j), (Map) null, new Function1() { // from class: androidx.compose.foundation.text.a
            public final Object invoke(Object obj) {
                return AnnotatedStringResolveInlineContentKt$InlineChildren$1$2$1.a(arrayList, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }
}
