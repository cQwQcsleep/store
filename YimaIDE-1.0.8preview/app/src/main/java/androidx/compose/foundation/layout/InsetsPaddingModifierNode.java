package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.InsetsPaddingModifierNode;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004J#\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/layout/InsetsPaddingModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "<init>", "(Landroidx/compose/foundation/layout/WindowInsets;)V", "calculateInsets", "ancestorConsumedInsets", "insetsInvalidated", "", "update", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class InsetsPaddingModifierNode extends InsetsConsumingModifierNode implements LayoutModifierNode {
    public static final int $stable = 8;
    private WindowInsets insets;

    public InsetsPaddingModifierNode(WindowInsets windowInsets) {
        this.insets = windowInsets;
    }

    public static Unit c(Placeable placeable, int i, int i2, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, i, i2, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        return WindowInsetsKt.union(ancestorConsumedInsets, this.insets);
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public void insetsInvalidated() {
        super.insetsInvalidated();
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m867measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final int left = getConsumedInsets().getLeft(measureScope, measureScope.getLayoutDirection()) - getAncestorConsumedInsets().getLeft(measureScope, measureScope.getLayoutDirection());
        final int top = getConsumedInsets().getTop(measureScope) - getAncestorConsumedInsets().getTop(measureScope);
        int right = (getConsumedInsets().getRight(measureScope, measureScope.getLayoutDirection()) - getAncestorConsumedInsets().getRight(measureScope, measureScope.getLayoutDirection())) + left;
        int bottom = (getConsumedInsets().getBottom(measureScope) - getAncestorConsumedInsets().getBottom(measureScope)) + top;
        final Placeable placeable = measurable.measure-BRTryo0(ConstraintsKt.offset-NN6Ew-U(j, -right, -bottom));
        return MeasureScope.layout$default(measureScope, ConstraintsKt.constrainWidth-K40F9xA(j, placeable.getWidth() + right), ConstraintsKt.constrainHeight-K40F9xA(j, placeable.getHeight() + bottom), (Map) null, new Function1() { // from class: uq6
            public final Object invoke(Object obj) {
                return InsetsPaddingModifierNode.c(placeable, left, top, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public final void update(WindowInsets insets) {
        if (Intrinsics.areEqual(insets, this.insets)) {
            return;
        }
        this.insets = insets;
        insetsInvalidated();
    }
}
