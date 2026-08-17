package androidx.compose.material3.internal;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001e\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0007\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\u0005\"\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"\u0014\u0010\u000e\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"HorizontalSemanticsBoundsPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalSemanticsBoundsPadding$annotations", "()V", "getHorizontalSemanticsBoundsPadding", "()F", "F", "VerticalSemanticsBoundsPadding", "getVerticalSemanticsBoundsPadding$annotations", "getVerticalSemanticsBoundsPadding", "IncreaseHorizontalSemanticsBounds", "Landroidx/compose/ui/Modifier;", "getIncreaseHorizontalSemanticsBounds", "()Landroidx/compose/ui/Modifier;", "IncreaseVerticalSemanticsBounds", "getIncreaseVerticalSemanticsBounds", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AccessibilityUtilKt {
    private static final float HorizontalSemanticsBoundsPadding;
    private static final Modifier IncreaseHorizontalSemanticsBounds;
    private static final Modifier IncreaseVerticalSemanticsBounds;
    private static final float VerticalSemanticsBoundsPadding;

    static {
        float fM6022constructorimpl = Dp.m6022constructorimpl(10.0f);
        HorizontalSemanticsBoundsPadding = fM6022constructorimpl;
        float fM6022constructorimpl2 = Dp.m6022constructorimpl(10.0f);
        VerticalSemanticsBoundsPadding = fM6022constructorimpl2;
        Modifier.Companion companion = Modifier.INSTANCE;
        IncreaseHorizontalSemanticsBounds = PaddingKt.padding-VpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(companion, new Function3() { // from class: hu
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AccessibilityUtilKt.f((MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        }), true, new Function1() { // from class: iu
            public final Object invoke(Object obj) {
                return AccessibilityUtilKt.c((SemanticsPropertyReceiver) obj);
            }
        }), fM6022constructorimpl, 0.0f, 2, (Object) null);
        IncreaseVerticalSemanticsBounds = PaddingKt.padding-VpY3zN4$default(SemanticsModifierKt.semantics(LayoutModifierKt.layout(companion, new Function3() { // from class: ju
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AccessibilityUtilKt.d((MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        }), true, new Function1() { // from class: ku
            public final Object invoke(Object obj) {
                return AccessibilityUtilKt.a((SemanticsPropertyReceiver) obj);
            }
        }), 0.0f, fM6022constructorimpl2, 1, (Object) null);
    }

    public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    public static Unit b(Placeable placeable, int i, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, -i, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit c(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    public static MeasureResult d(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(VerticalSemanticsBoundsPadding);
        long value = constraints.getValue();
        int i = iMo4551roundToPx0680j_4 * 2;
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(value, 0, i));
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight() - i, null, new Function1() { // from class: gu
            public final Object invoke(Object obj) {
                return AccessibilityUtilKt.e(placeableMo4605measureBRTryo0, iMo4551roundToPx0680j_4, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static Unit e(Placeable placeable, int i, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, -i, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static MeasureResult f(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final int iMo4551roundToPx0680j_4 = measureScope.mo4551roundToPx0680j_4(HorizontalSemanticsBoundsPadding);
        long value = constraints.getValue();
        int i = iMo4551roundToPx0680j_4 * 2;
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(value, i, 0));
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth() - i, placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: lu
            public final Object invoke(Object obj) {
                return AccessibilityUtilKt.b(placeableMo4605measureBRTryo0, iMo4551roundToPx0680j_4, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static final float getHorizontalSemanticsBoundsPadding() {
        return HorizontalSemanticsBoundsPadding;
    }

    public static /* synthetic */ void getHorizontalSemanticsBoundsPadding$annotations() {
    }

    public static final Modifier getIncreaseHorizontalSemanticsBounds() {
        return IncreaseHorizontalSemanticsBounds;
    }

    public static final Modifier getIncreaseVerticalSemanticsBounds() {
        return IncreaseVerticalSemanticsBounds;
    }

    public static final float getVerticalSemanticsBoundsPadding() {
        return VerticalSemanticsBoundsPadding;
    }

    public static /* synthetic */ void getVerticalSemanticsBoundsPadding$annotations() {
    }
}
