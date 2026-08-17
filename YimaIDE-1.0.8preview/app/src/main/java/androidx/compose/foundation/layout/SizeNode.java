package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ#\u0010 \u001a\u00020!*\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001cH\u0016¢\u0006\u0004\b&\u0010'J\u001c\u0010(\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010,\u001a\u00020)H\u0016J\u001c\u0010-\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010.\u001a\u00020)H\u0016J\u001c\u0010/\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010,\u001a\u00020)H\u0016J\u001c\u00100\u001a\u00020)*\u00020*2\u0006\u0010#\u001a\u00020+2\u0006\u0010.\u001a\u00020)H\u0016R\u001c\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR\u001c\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u00020\u001c*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u00061"}, d2 = {"Landroidx/compose/foundation/layout/SizeNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "minWidth", "Landroidx/compose/ui/unit/Dp;", "minHeight", "maxWidth", "maxHeight", "enforceIncoming", "", "<init>", "(FFFFZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMinWidth-D9Ej5fM", "()F", "setMinWidth-0680j_4", "(F)V", "F", "getMinHeight-D9Ej5fM", "setMinHeight-0680j_4", "getMaxWidth-D9Ej5fM", "setMaxWidth-0680j_4", "getMaxHeight-D9Ej5fM", "setMaxHeight-0680j_4", "getEnforceIncoming", "()Z", "setEnforceIncoming", "(Z)V", "targetConstraints", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/unit/Density;", "getTargetConstraints-OenEA2s", "(Landroidx/compose/ui/unit/Density;)J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SizeNode extends Modifier.Node implements LayoutModifierNode {
    private boolean enforceIncoming;
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;

    public /* synthetic */ SizeNode(float f, float f2, float f3, float f4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.Companion.getUnspecified-D9Ej5fM() : f, (i & 2) != 0 ? Dp.Companion.getUnspecified-D9Ej5fM() : f2, (i & 4) != 0 ? Dp.Companion.getUnspecified-D9Ej5fM() : f3, (i & 8) != 0 ? Dp.Companion.getUnspecified-D9Ej5fM() : f4, z, null);
    }

    public static Unit a(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0041  */
    /* JADX INFO: renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    private final long m991getTargetConstraintsOenEA2s(Density density) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (Float.isNaN(this.maxWidth)) {
            i = Integer.MAX_VALUE;
        } else {
            i = density.roundToPx-0680j_4(this.maxWidth);
            if (i < 0) {
                i = 0;
            }
        }
        if (Float.isNaN(this.maxHeight)) {
            i2 = Integer.MAX_VALUE;
        } else {
            i2 = density.roundToPx-0680j_4(this.maxHeight);
            if (i2 < 0) {
                i2 = 0;
            }
        }
        if (Float.isNaN(this.minWidth)) {
            i3 = 0;
        } else {
            i3 = density.roundToPx-0680j_4(this.minWidth);
            if (i3 < 0) {
                i3 = 0;
            }
            if (i3 > i) {
                i3 = i;
            }
            if (i3 == Integer.MAX_VALUE) {
                i3 = 0;
            }
        }
        if (!Float.isNaN(this.minHeight)) {
            int i5 = density.roundToPx-0680j_4(this.minHeight);
            if (i5 < 0) {
                i5 = 0;
            }
            if (i5 > i2) {
                i5 = i2;
            }
            if (i5 != Integer.MAX_VALUE) {
                i4 = i5;
            }
        }
        return ConstraintsKt.Constraints(i3, i, i4, i2);
    }

    public final boolean getEnforceIncoming() {
        return this.enforceIncoming;
    }

    /* JADX INFO: renamed from: getMaxHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxHeight() {
        return this.maxHeight;
    }

    /* JADX INFO: renamed from: getMaxWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMaxWidth() {
        return this.maxWidth;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinHeight() {
        return this.minHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMinWidth() {
        return this.minWidth;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM991getTargetConstraintsOenEA2s = m991getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.getHasFixedHeight-impl(jM991getTargetConstraintsOenEA2s)) {
            return Constraints.getMaxHeight-impl(jM991getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.constrainWidth-K40F9xA(jM991getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.constrainHeight-K40F9xA(jM991getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicHeight(i));
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM991getTargetConstraintsOenEA2s = m991getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.getHasFixedWidth-impl(jM991getTargetConstraintsOenEA2s)) {
            return Constraints.getMaxWidth-impl(jM991getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.constrainHeight-K40F9xA(jM991getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.constrainWidth-K40F9xA(jM991getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicWidth(i));
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m996measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int i;
        int i2;
        int i3;
        int i4;
        long jConstraints;
        long jM991getTargetConstraintsOenEA2s = m991getTargetConstraintsOenEA2s(measureScope);
        if (this.enforceIncoming) {
            jConstraints = ConstraintsKt.constrain-N9IONVI(j, jM991getTargetConstraintsOenEA2s);
        } else {
            if (Float.isNaN(this.minWidth)) {
                i = Constraints.getMinWidth-impl(j);
                int i5 = Constraints.getMaxWidth-impl(jM991getTargetConstraintsOenEA2s);
                if (i > i5) {
                    i = i5;
                }
            } else {
                i = Constraints.getMinWidth-impl(jM991getTargetConstraintsOenEA2s);
            }
            if (Float.isNaN(this.maxWidth)) {
                i2 = Constraints.getMaxWidth-impl(j);
                int i6 = Constraints.getMinWidth-impl(jM991getTargetConstraintsOenEA2s);
                if (i2 < i6) {
                    i2 = i6;
                }
            } else {
                i2 = Constraints.getMaxWidth-impl(jM991getTargetConstraintsOenEA2s);
            }
            if (Float.isNaN(this.minHeight)) {
                i3 = Constraints.getMinHeight-impl(j);
                int i7 = Constraints.getMaxHeight-impl(jM991getTargetConstraintsOenEA2s);
                if (i3 > i7) {
                    i3 = i7;
                }
            } else {
                i3 = Constraints.getMinHeight-impl(jM991getTargetConstraintsOenEA2s);
            }
            if (Float.isNaN(this.maxHeight)) {
                i4 = Constraints.getMaxHeight-impl(j);
                int i8 = Constraints.getMinHeight-impl(jM991getTargetConstraintsOenEA2s);
                if (i4 < i8) {
                    i4 = i8;
                }
            } else {
                i4 = Constraints.getMaxHeight-impl(jM991getTargetConstraintsOenEA2s);
            }
            jConstraints = ConstraintsKt.Constraints(i, i2, i3, i4);
        }
        final Placeable placeable = measurable.measure-BRTryo0(jConstraints);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.a0
            public final Object invoke(Object obj) {
                return SizeNode.a(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM991getTargetConstraintsOenEA2s = m991getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.getHasFixedHeight-impl(jM991getTargetConstraintsOenEA2s)) {
            return Constraints.getMaxHeight-impl(jM991getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.constrainWidth-K40F9xA(jM991getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.constrainHeight-K40F9xA(jM991getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicHeight(i));
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM991getTargetConstraintsOenEA2s = m991getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.getHasFixedWidth-impl(jM991getTargetConstraintsOenEA2s)) {
            return Constraints.getMaxWidth-impl(jM991getTargetConstraintsOenEA2s);
        }
        if (!this.enforceIncoming) {
            i = ConstraintsKt.constrainHeight-K40F9xA(jM991getTargetConstraintsOenEA2s, i);
        }
        return ConstraintsKt.constrainWidth-K40F9xA(jM991getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicWidth(i));
    }

    public final void setEnforceIncoming(boolean z) {
        this.enforceIncoming = z;
    }

    /* JADX INFO: renamed from: setMaxHeight-0680j_4, reason: not valid java name */
    public final void m997setMaxHeight0680j_4(float f) {
        this.maxHeight = f;
    }

    /* JADX INFO: renamed from: setMaxWidth-0680j_4, reason: not valid java name */
    public final void m998setMaxWidth0680j_4(float f) {
        this.maxWidth = f;
    }

    /* JADX INFO: renamed from: setMinHeight-0680j_4, reason: not valid java name */
    public final void m999setMinHeight0680j_4(float f) {
        this.minHeight = f;
    }

    /* JADX INFO: renamed from: setMinWidth-0680j_4, reason: not valid java name */
    public final void m1000setMinWidth0680j_4(float f) {
        this.minWidth = f;
    }

    private SizeNode(float f, float f2, float f3, float f4, boolean z) {
        this.minWidth = f;
        this.minHeight = f2;
        this.maxWidth = f3;
        this.maxHeight = f4;
        this.enforceIncoming = z;
    }

    public /* synthetic */ SizeNode(float f, float f2, float f3, float f4, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z);
    }
}
