package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J#\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/layout/PaddingValuesModifier;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "<init>", "(Landroidx/compose/foundation/layout/PaddingValues;)V", "getPaddingValues", "()Landroidx/compose/foundation/layout/PaddingValues;", "setPaddingValues", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class PaddingValuesModifier extends Modifier.Node implements LayoutModifierNode {
    private PaddingValues paddingValues;

    public PaddingValuesModifier(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }

    public static Unit a(Placeable placeable, int i, int i2, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, i, i2, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    public final PaddingValues getPaddingValues() {
        return this.paddingValues;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m958measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        float fMo869calculateLeftPaddingu2uoSUM = this.paddingValues.mo869calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection());
        float top = this.paddingValues.getTop();
        float fMo870calculateRightPaddingu2uoSUM = this.paddingValues.mo870calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection());
        float bottom = this.paddingValues.getBottom();
        if (!((Dp.compareTo-0680j_4(fMo869calculateLeftPaddingu2uoSUM, Dp.constructor-impl(0.0f)) >= 0) & (Dp.compareTo-0680j_4(top, Dp.constructor-impl(0.0f)) >= 0) & (Dp.compareTo-0680j_4(fMo870calculateRightPaddingu2uoSUM, Dp.constructor-impl(0.0f)) >= 0) & (Dp.compareTo-0680j_4(bottom, Dp.constructor-impl(0.0f)) >= 0))) {
            InlineClassHelperKt.throwIllegalArgumentException("Padding must be non-negative");
        }
        final int i = measureScope.roundToPx-0680j_4(fMo869calculateLeftPaddingu2uoSUM);
        int i2 = measureScope.roundToPx-0680j_4(fMo870calculateRightPaddingu2uoSUM) + i;
        final int i3 = measureScope.roundToPx-0680j_4(top);
        int i4 = measureScope.roundToPx-0680j_4(bottom) + i3;
        final Placeable placeable = measurable.measure-BRTryo0(ConstraintsKt.offset-NN6Ew-U(j, -i2, -i4));
        return MeasureScope.layout$default(measureScope, ConstraintsKt.constrainWidth-K40F9xA(j, placeable.getWidth() + i2), ConstraintsKt.constrainHeight-K40F9xA(j, placeable.getHeight() + i4), (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.x
            public final Object invoke(Object obj) {
                return PaddingValuesModifier.a(placeable, i, i3, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public final void setPaddingValues(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }
}
