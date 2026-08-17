package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabKt$TabBaselineLayout$2$1 implements MeasurePolicy {
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ Function2<Composer, Integer, Unit> $text;

    /* JADX WARN: Multi-variable type inference failed */
    public TabKt$TabBaselineLayout$2$1(Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3) {
        this.$text = function2;
        this.$icon = function3;
    }

    public static Unit a(Placeable placeable, Placeable placeable2, MeasureScope measureScope, int i, int i2, Integer num, Integer num2, Placeable.PlacementScope placementScope) {
        if (placeable != null && placeable2 != null) {
            num.getClass();
            int iIntValue = num.intValue();
            num2.getClass();
            TabKt.placeTextAndIcon(placementScope, measureScope, placeable, placeable2, i, i2, iIntValue, num2.intValue());
        } else if (placeable != null) {
            TabKt.placeTextOrIcon(placementScope, placeable, i2);
        } else if (placeable2 != null) {
            TabKt.placeTextOrIcon(placementScope, placeable2, i2);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        Placeable placeableMo4605measureBRTryo0;
        Placeable placeableMo4605measureBRTryo1;
        if (this.$text != null) {
            int size = list.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                    wq6.a();
                    return null;
                }
                Measurable measurable = list.get(i);
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "text")) {
                    placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null));
                    break;
                }
                i++;
            }
        } else {
            placeableMo4605measureBRTryo0 = null;
        }
        if (this.$icon != null) {
            int size2 = list.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                    wq6.a();
                    return null;
                }
                Measurable measurable2 = list.get(i2);
                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "icon")) {
                    placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(j);
                    break;
                }
                i2++;
            }
        } else {
            placeableMo4605measureBRTryo1 = null;
        }
        final int iMax = Math.max(placeableMo4605measureBRTryo0 != null ? placeableMo4605measureBRTryo0.getWidth() : 0, placeableMo4605measureBRTryo1 != null ? placeableMo4605measureBRTryo1.getWidth() : 0);
        final int iMax2 = Math.max(measureScope.mo4551roundToPx0680j_4((placeableMo4605measureBRTryo0 == null || placeableMo4605measureBRTryo1 == null) ? TabKt.SmallTabHeight : TabKt.LargeTabHeight), (placeableMo4605measureBRTryo1 != null ? placeableMo4605measureBRTryo1.getHeight() : 0) + (placeableMo4605measureBRTryo0 != null ? placeableMo4605measureBRTryo0.getHeight() : 0) + measureScope.mo4550roundToPxR2X_6o(TabKt.IconDistanceFromBaseline));
        final Integer numValueOf = placeableMo4605measureBRTryo0 != null ? Integer.valueOf(placeableMo4605measureBRTryo0.get(AlignmentLineKt.getFirstBaseline())) : null;
        final Integer numValueOf2 = placeableMo4605measureBRTryo0 != null ? Integer.valueOf(placeableMo4605measureBRTryo0.get(AlignmentLineKt.getLastBaseline())) : null;
        final Placeable placeable = placeableMo4605measureBRTryo0;
        final Placeable placeable2 = placeableMo4605measureBRTryo1;
        return MeasureScope.layout$default(measureScope, iMax, iMax2, null, new Function1() { // from class: androidx.compose.material3.i4
            public final Object invoke(Object obj) {
                return TabKt$TabBaselineLayout$2$1.a(placeable, placeable2, measureScope, iMax, iMax2, numValueOf, numValueOf2, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
