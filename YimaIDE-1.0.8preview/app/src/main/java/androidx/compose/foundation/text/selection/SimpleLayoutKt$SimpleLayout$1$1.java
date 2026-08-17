package androidx.compose.foundation.text.selection;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class SimpleLayoutKt$SimpleLayout$1$1 implements MeasurePolicy {
    public static final SimpleLayoutKt$SimpleLayout$1$1 INSTANCE = new SimpleLayoutKt$SimpleLayout$1$1();

    public static Unit a(List list, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Placeable.PlacementScope.place$default(placementScope, (Placeable) list.get(i), 0, 0, 0.0f, 4, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult m1802measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        int iMax = 0;
        int iMax2 = 0;
        for (int i = 0; i < size; i++) {
            Placeable placeable = list.get(i).measure-BRTryo0(j);
            iMax = Math.max(iMax, placeable.getWidth());
            iMax2 = Math.max(iMax2, placeable.getHeight());
            arrayList.add(placeable);
        }
        return MeasureScope.layout$default(measureScope, iMax, iMax2, (Map) null, new Function1() { // from class: androidx.compose.foundation.text.selection.d
            public final Object invoke(Object obj) {
                return SimpleLayoutKt$SimpleLayout$1$1.a(arrayList, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }
}
