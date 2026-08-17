package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B.\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u001d\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J-\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u001d\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tJ#\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R%\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/layout/DerivedHeightModifierNode;", "Landroidx/compose/foundation/layout/InsetsConsumingModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "heightCalc", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;)V", "heightInsets", "calculateInsets", "ancestorConsumedInsets", "insetsInvalidated", "", "update", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DerivedHeightModifierNode extends InsetsConsumingModifierNode implements LayoutModifierNode {
    private Function2<? super WindowInsets, ? super Density, Integer> heightCalc;
    private WindowInsets heightInsets = WindowInsetsKt.WindowInsets();
    private WindowInsets insets;

    public DerivedHeightModifierNode(WindowInsets windowInsets, Function2<? super WindowInsets, ? super Density, Integer> function2) {
        this.insets = windowInsets;
        this.heightCalc = function2;
    }

    public static Unit c(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    public static Unit d(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public WindowInsets calculateInsets(WindowInsets ancestorConsumedInsets) {
        return ancestorConsumedInsets;
    }

    @Override // androidx.compose.foundation.layout.InsetsConsumingModifierNode
    public void insetsInvalidated() {
        this.heightInsets = WindowInsetsKt.exclude(this.insets, getAncestorConsumedInsets());
        super.insetsInvalidated();
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m829measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int iIntValue = ((Number) this.heightCalc.invoke(this.heightInsets, measureScope)).intValue();
        if (iIntValue == 0) {
            return MeasureScope.layout$default(measureScope, 0, 0, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.g
                public final Object invoke(Object obj) {
                    return DerivedHeightModifierNode.c((Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null);
        }
        final Placeable placeable = measurable.measure-BRTryo0(Constraints.copy-Zbe2FdA$default(j, 0, 0, iIntValue, iIntValue, 3, (Object) null));
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), iIntValue, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.h
            public final Object invoke(Object obj) {
                return DerivedHeightModifierNode.d(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public final void update(WindowInsets insets, Function2<? super WindowInsets, ? super Density, Integer> heightCalc) {
        if (Intrinsics.areEqual(this.insets, insets) && heightCalc == this.heightCalc) {
            return;
        }
        this.insets = insets;
        this.heightCalc = heightCalc;
        this.heightInsets = WindowInsetsKt.exclude(insets, getAncestorConsumedInsets());
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }
}
