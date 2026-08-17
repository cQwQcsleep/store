package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\b\u001a\u00020\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0012\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/layout/BoxMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "alignment", "Landroidx/compose/ui/Alignment;", "propagateMinConstraints", "", "<init>", "(Landroidx/compose/ui/Alignment;Z)V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class BoxMeasurePolicy implements MeasurePolicy {
    private final Alignment alignment;
    private final boolean propagateMinConstraints;

    public BoxMeasurePolicy(Alignment alignment, boolean z) {
        this.alignment = alignment;
        this.propagateMinConstraints = z;
    }

    public static Unit a(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    public static Unit b(Placeable placeable, Measurable measurable, MeasureScope measureScope, int i, int i2, BoxMeasurePolicy boxMeasurePolicy, Placeable.PlacementScope placementScope) {
        BoxKt.placeInBox(placementScope, placeable, measurable, measureScope.getLayoutDirection(), i, i2, boxMeasurePolicy.alignment);
        return Unit.INSTANCE;
    }

    public static Unit c(Placeable[] placeableArr, List list, MeasureScope measureScope, Ref.IntRef intRef, Ref.IntRef intRef2, BoxMeasurePolicy boxMeasurePolicy, Placeable.PlacementScope placementScope) {
        int length = placeableArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Placeable placeable = placeableArr[i];
            placeable.getClass();
            BoxKt.placeInBox(placementScope, placeable, (Measurable) list.get(i2), measureScope.getLayoutDirection(), intRef.element, intRef2.element, boxMeasurePolicy.alignment);
            i++;
            i2++;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final boolean getPropagateMinConstraints() {
        return this.propagateMinConstraints;
    }

    public static /* synthetic */ BoxMeasurePolicy copy$default(BoxMeasurePolicy boxMeasurePolicy, Alignment alignment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            alignment = boxMeasurePolicy.alignment;
        }
        if ((i & 2) != 0) {
            z = boxMeasurePolicy.propagateMinConstraints;
        }
        return boxMeasurePolicy.copy(alignment, z);
    }

    public final BoxMeasurePolicy copy(Alignment alignment, boolean propagateMinConstraints) {
        return new BoxMeasurePolicy(alignment, propagateMinConstraints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BoxMeasurePolicy)) {
            return false;
        }
        BoxMeasurePolicy boxMeasurePolicy = (BoxMeasurePolicy) other;
        return Intrinsics.areEqual(this.alignment, boxMeasurePolicy.alignment) && this.propagateMinConstraints == boxMeasurePolicy.propagateMinConstraints;
    }

    public int hashCode() {
        return (this.alignment.hashCode() * 31) + Boolean.hashCode(this.propagateMinConstraints);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m810measure3p2s80s(final MeasureScope measureScope, final List<? extends Measurable> list, long j) {
        int iMax;
        int iMax2;
        Placeable placeable;
        if (list.isEmpty()) {
            return MeasureScope.layout$default(measureScope, Constraints.getMinWidth-impl(j), Constraints.getMinHeight-impl(j), (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.c
                public final Object invoke(Object obj) {
                    return BoxMeasurePolicy.a((Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null);
        }
        long j2 = this.propagateMinConstraints ? j : Constraints.constructor-impl(j & (-8589934589L));
        if (list.size() == 1) {
            final Measurable measurable = list.get(0);
            if (BoxKt.getMatchesParentSize(measurable)) {
                iMax = Constraints.getMinWidth-impl(j);
                iMax2 = Constraints.getMinHeight-impl(j);
                placeable = measurable.measure-BRTryo0(Constraints.Companion.fixed-JhjzzOo(Constraints.getMinWidth-impl(j), Constraints.getMinHeight-impl(j)));
            } else {
                placeable = measurable.measure-BRTryo0(j2);
                iMax = Math.max(Constraints.getMinWidth-impl(j), placeable.getWidth());
                iMax2 = Math.max(Constraints.getMinHeight-impl(j), placeable.getHeight());
            }
            final int i = iMax;
            final int i2 = iMax2;
            final Placeable placeable2 = placeable;
            return MeasureScope.layout$default(measureScope, i, i2, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.d
                public final Object invoke(Object obj) {
                    return BoxMeasurePolicy.b(placeable2, measurable, measureScope, i, i2, this, (Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null);
        }
        final Placeable[] placeableArr = new Placeable[list.size()];
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Constraints.getMinWidth-impl(j);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = Constraints.getMinHeight-impl(j);
        List<? extends Measurable> list2 = list;
        int size = list2.size();
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            Measurable measurable2 = list.get(i3);
            if (BoxKt.getMatchesParentSize(measurable2)) {
                z = true;
            } else {
                Placeable placeable3 = measurable2.measure-BRTryo0(j2);
                placeableArr[i3] = placeable3;
                intRef.element = Math.max(intRef.element, placeable3.getWidth());
                intRef2.element = Math.max(intRef2.element, placeable3.getHeight());
            }
        }
        if (z) {
            int i4 = intRef.element;
            int i5 = i4 != Integer.MAX_VALUE ? i4 : 0;
            int i6 = intRef2.element;
            long jConstraints = ConstraintsKt.Constraints(i5, i4, i6 != Integer.MAX_VALUE ? i6 : 0, i6);
            int size2 = list2.size();
            for (int i7 = 0; i7 < size2; i7++) {
                Measurable measurable3 = list.get(i7);
                if (BoxKt.getMatchesParentSize(measurable3)) {
                    placeableArr[i7] = measurable3.measure-BRTryo0(jConstraints);
                }
            }
        }
        return MeasureScope.layout$default(measureScope, intRef.element, intRef2.element, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.e
            public final Object invoke(Object obj) {
                return BoxMeasurePolicy.c(placeableArr, list, measureScope, intRef, intRef2, this, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public String toString() {
        return "BoxMeasurePolicy(alignment=" + this.alignment + ", propagateMinConstraints=" + this.propagateMinConstraints + ')';
    }
}
