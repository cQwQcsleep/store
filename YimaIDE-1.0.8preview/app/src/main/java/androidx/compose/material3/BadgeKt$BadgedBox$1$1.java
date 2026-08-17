package androidx.compose.material3;

import androidx.compose.material3.tokens.BadgeTokens;
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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BadgeKt$BadgedBox$1$1 implements MeasurePolicy {
    public static final BadgeKt$BadgedBox$1$1 INSTANCE = new BadgeKt$BadgedBox$1$1();

    public static Unit a(Placeable placeable, MeasureScope measureScope, Placeable placeable2, Placeable.PlacementScope placementScope) {
        boolean z = placeable.getWidth() > measureScope.mo4551roundToPx0680j_4(BadgeTokens.INSTANCE.m1520getSizeD9Ej5fM());
        float badgeWithContentHorizontalOffset = z ? BadgeKt.getBadgeWithContentHorizontalOffset() : BadgeKt.getBadgeOffset();
        float badgeWithContentVerticalOffset = z ? BadgeKt.getBadgeWithContentVerticalOffset() : BadgeKt.getBadgeOffset();
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, Math.min(placeable2.getWidth() - measureScope.mo4551roundToPx0680j_4(badgeWithContentHorizontalOffset), ((int) placementScope.current(BadgeKt.getBadgeEndRuler(), Float.POSITIVE_INFINITY)) - placeable.getWidth()), Math.max((-placeable.getHeight()) + measureScope.mo4551roundToPx0680j_4(badgeWithContentVerticalOffset), (int) placementScope.current(BadgeKt.getBadgeTopRuler(), Float.NEGATIVE_INFINITY)), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "badge")) {
                final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null));
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    Measurable measurable2 = list.get(i2);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "anchor")) {
                        final Placeable placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(j);
                        return measureScope.layout(placeableMo4605measureBRTryo1.getWidth(), placeableMo4605measureBRTryo1.getHeight(), MapsKt.mapOf(new Pair[]{TuplesKt.to(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(placeableMo4605measureBRTryo1.get(AlignmentLineKt.getFirstBaseline()))), TuplesKt.to(AlignmentLineKt.getLastBaseline(), Integer.valueOf(placeableMo4605measureBRTryo1.get(AlignmentLineKt.getLastBaseline())))}), new Function1() { // from class: androidx.compose.material3.f
                            public final Object invoke(Object obj) {
                                return BadgeKt$BadgedBox$1$1.a(placeableMo4605measureBRTryo0, measureScope, placeableMo4605measureBRTryo1, (Placeable.PlacementScope) obj);
                            }
                        });
                    }
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }
}
