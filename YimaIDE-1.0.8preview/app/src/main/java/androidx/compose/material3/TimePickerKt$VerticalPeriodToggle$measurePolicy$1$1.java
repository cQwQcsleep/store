package androidx.compose.material3;

import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1 implements MeasurePolicy {
    public static final TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1 INSTANCE = new TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1();

    public static Unit a(List list, Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, (Placeable) list.get(0), 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.place$default(placementScope, (Placeable) list.get(1), 0, ((Placeable) list.get(0)).getHeight(), 0.0f, 4, null);
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, ((Placeable) list.get(0)).getHeight() - (placeable.getHeight() / 2), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "Spacer")) {
                final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, measureScope.mo4551roundToPx0680j_4(TimePickerTokens.INSTANCE.m2202getPeriodSelectorOutlineWidthD9Ej5fM()), 3, null));
                ArrayList arrayList = new ArrayList(list.size());
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    Measurable measurable2 = list.get(i2);
                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "Spacer")) {
                        arrayList.add(measurable2);
                    }
                }
                final ArrayList arrayList2 = new ArrayList(arrayList.size());
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    arrayList2.add(((Measurable) arrayList.get(i3)).mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, Constraints.m5974getMaxHeightimpl(j) / 2, 3, null)));
                }
                return MeasureScope.layout$default(measureScope, Constraints.m5975getMaxWidthimpl(j), Constraints.m5974getMaxHeightimpl(j), null, new Function1() { // from class: androidx.compose.material3.k5
                    public final Object invoke(Object obj) {
                        return TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.a(arrayList2, placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }
}
