package androidx.compose.foundation.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J#\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010!\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Landroidx/compose/foundation/layout/RecalculateWindowInsetsModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "<init>", "()V", "insets", "Landroidx/compose/foundation/layout/ValueInsets;", "getInsets", "()Landroidx/compose/foundation/layout/ValueInsets;", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "calculateInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "ancestorConsumedInsets", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "minIntrinsicWidth", "height", "maxIntrinsicHeight", "maxIntrinsicWidth", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class RecalculateWindowInsetsModifierNode extends InsetsConsumingModifierNode implements LayoutModifierNode {
    private final ValueInsets insets = new ValueInsets(new InsetsValues(0, 0, 0, 0), "reset");

    public static Unit c(RecalculateWindowInsetsModifierNode recalculateWindowInsetsModifierNode, Measurable measurable, int i, int i2, Placeable.PlacementScope placementScope) {
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        if (coordinates != null) {
            long jPositionInRoot = LayoutCoordinatesKt.positionInRoot(coordinates);
            long j = coordinates.getSize-YbymL2g();
            long j2 = coordinates.localToRoot-MK-Hz9U(Offset.constructor-impl((((long) Float.floatToRawIntBits((int) (j & 4294967295L))) & 4294967295L) | (Float.floatToRawIntBits((int) (j >> 32)) << 32)));
            long j3 = LayoutCoordinatesKt.findRootCoordinates(coordinates).getSize-YbymL2g();
            int iRound = Math.round(Float.intBitsToFloat((int) (jPositionInRoot >> 32)));
            int iRound2 = Math.round(Float.intBitsToFloat((int) (jPositionInRoot & 4294967295L)));
            int iRound3 = ((int) (j3 >> 32)) - Math.round(Float.intBitsToFloat((int) (j2 >> 32)));
            int iRound4 = ((int) (j3 & 4294967295L)) - Math.round(Float.intBitsToFloat((int) (j2 & 4294967295L)));
            InsetsValues value$foundation_layout = recalculateWindowInsetsModifierNode.insets.getValue$foundation_layout();
            if (value$foundation_layout.getLeft() != iRound || value$foundation_layout.getTop() != iRound2 || value$foundation_layout.getRight() != iRound3 || value$foundation_layout.getBottom() != iRound4) {
                recalculateWindowInsetsModifierNode.insets.setValue$foundation_layout(new InsetsValues(iRound, iRound2, iRound3, iRound4));
                recalculateWindowInsetsModifierNode.insetsInvalidated();
            }
        }
        Placeable.PlacementScope.place$default(placementScope, measurable.measure-BRTryo0(Constraints.Companion.fixed-JhjzzOo(i, i2)), 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit d(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        return this.insets.getValue$foundation_layout().getLeft() == -1 ? ancestorConsumedInsets : this.insets;
    }

    public final ValueInsets getInsets() {
        return this.insets;
    }

    public boolean getShouldAutoInvalidate() {
        return false;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m960measure3p2s80s(MeasureScope measureScope, final Measurable measurable, long j) {
        if (Constraints.getHasFixedWidth-impl(j) && Constraints.getHasFixedHeight-impl(j)) {
            final int i = Constraints.getMaxWidth-impl(j);
            final int i2 = Constraints.getMaxHeight-impl(j);
            return MeasureScope.layout$default(measureScope, i, i2, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.z
                public final Object invoke(Object obj) {
                    return RecalculateWindowInsetsModifierNode.c(this.b, measurable, i, i2, (Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null);
        }
        if (this.insets.getValue$foundation_layout().getLeft() != -1) {
            this.insets.setValue$foundation_layout(new InsetsValues(-1, -1, -1, -1));
            insetsInvalidated();
        }
        final Placeable placeable = measurable.measure-BRTryo0(j);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.y
            public final Object invoke(Object obj) {
                return RecalculateWindowInsetsModifierNode.d(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }
}
