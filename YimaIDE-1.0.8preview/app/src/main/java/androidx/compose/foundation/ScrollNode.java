package androidx.compose.foundation;

import androidx.compose.foundation.ScrollNode;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ#\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u001c\u0010\"\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010#\u001a\u00020\u001eH\u0016J\u001c\u0010$\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u001c\u0010%\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020 2\u0006\u0010#\u001a\u00020\u001eH\u0016J\f\u0010&\u001a\u00020'*\u00020(H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0010\"\u0004\b\u0013\u0010\u0012¨\u0006)"}, d2 = {"Landroidx/compose/foundation/ScrollNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "state", "Landroidx/compose/foundation/ScrollState;", "reverseScrolling", "", "isVertical", "<init>", "(Landroidx/compose/foundation/ScrollState;ZZ)V", "getState", "()Landroidx/compose/foundation/ScrollState;", "setState", "(Landroidx/compose/foundation/ScrollState;)V", "getReverseScrolling", "()Z", "setReverseScrolling", "(Z)V", "setVertical", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ScrollNode extends Modifier.Node implements LayoutModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private boolean isVertical;
    private boolean reverseScrolling;
    private ScrollState state;

    public ScrollNode(ScrollState scrollState, boolean z, boolean z2) {
        this.state = scrollState;
        this.reverseScrolling = z;
        this.isVertical = z2;
    }

    public static Unit a(ScrollNode scrollNode, int i, final Placeable placeable, Placeable.PlacementScope placementScope) {
        int value = scrollNode.state.getValue();
        if (value < 0) {
            value = 0;
        }
        if (value > i) {
            value = i;
        }
        int i2 = scrollNode.reverseScrolling ? value - i : -value;
        boolean z = scrollNode.isVertical;
        final int i3 = z ? 0 : i2;
        final int i4 = z ? i2 : 0;
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: dyc
            public final Object invoke(Object obj) {
                return ScrollNode.measure_3p2s80s$lambda$0$0(placeable, i3, i4, (Placeable.PlacementScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static float b(ScrollNode scrollNode) {
        return scrollNode.state.getValue();
    }

    public static float d(ScrollNode scrollNode) {
        return scrollNode.state.getMaxValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure_3p2s80s$lambda$0$0(Placeable placeable, int i, int i2, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, placeable, i, i2, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        ScrollAxisRange scrollAxisRange = new ScrollAxisRange(new Function0() { // from class: eyc
            public final Object invoke() {
                return Float.valueOf(ScrollNode.b(this.b));
            }
        }, new Function0() { // from class: fyc
            public final Object invoke() {
                return Float.valueOf(ScrollNode.d(this.b));
            }
        }, this.reverseScrolling);
        if (this.isVertical) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        } else {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        }
    }

    public final boolean getReverseScrolling() {
        return this.reverseScrolling;
    }

    public final ScrollState getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (!this.isVertical) {
            i = Integer.MAX_VALUE;
        }
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.isVertical) {
            i = Integer.MAX_VALUE;
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m428measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        CheckScrollableContainerConstraintsKt.m350checkScrollableContainerConstraintsK40F9xA(j, this.isVertical ? Orientation.Vertical : Orientation.Horizontal);
        final Placeable placeable = measurable.measure-BRTryo0(Constraints.copy-Zbe2FdA$default(j, 0, this.isVertical ? Constraints.getMaxWidth-impl(j) : Integer.MAX_VALUE, 0, this.isVertical ? Integer.MAX_VALUE : Constraints.getMaxHeight-impl(j), 5, (Object) null));
        int iCoerceAtMost = RangesKt.coerceAtMost(placeable.getWidth(), Constraints.getMaxWidth-impl(j));
        int iCoerceAtMost2 = RangesKt.coerceAtMost(placeable.getHeight(), Constraints.getMaxHeight-impl(j));
        final int height = placeable.getHeight() - iCoerceAtMost2;
        int width = placeable.getWidth() - iCoerceAtMost;
        if (!this.isVertical) {
            height = width;
        }
        this.state.setMaxValue$foundation(height);
        this.state.setViewportSize$foundation(this.isVertical ? iCoerceAtMost2 : iCoerceAtMost);
        this.state.setContentSize$foundation(this.isVertical ? placeable.getHeight() : placeable.getWidth());
        return MeasureScope.layout$default(measureScope, iCoerceAtMost, iCoerceAtMost2, (Map) null, new Function1() { // from class: cyc
            public final Object invoke(Object obj) {
                return ScrollNode.a(this.b, height, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (!this.isVertical) {
            i = Integer.MAX_VALUE;
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.isVertical) {
            i = Integer.MAX_VALUE;
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    public final void setReverseScrolling(boolean z) {
        this.reverseScrolling = z;
    }

    public final void setState(ScrollState scrollState) {
        this.state = scrollState;
    }

    public final void setVertical(boolean z) {
        this.isVertical = z;
    }
}
