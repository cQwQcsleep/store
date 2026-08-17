package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.material3.HorizontalCenterOpticallyKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"horizontalCenterOptically", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/foundation/shape/CornerBasedShape;", "maxStartOffset", "Landroidx/compose/ui/unit/Dp;", "maxEndOffset", "horizontalCenterOptically-4j6BHR0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/shape/CornerBasedShape;FF)Landroidx/compose/ui/Modifier;", "Landroidx/compose/material3/ShapeWithHorizontalCenterOptically;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/ShapeWithHorizontalCenterOptically;FF)Landroidx/compose/ui/Modifier;", "CenterOpticallyCoefficient", "", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class HorizontalCenterOpticallyKt {
    public static final float CenterOpticallyCoefficient = 0.11f;

    public static MeasureResult a(float f, float f2, final ShapeWithHorizontalCenterOptically shapeWithHorizontalCenterOptically, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        int width = placeableMo4605measureBRTryo0.getWidth();
        int height = placeableMo4605measureBRTryo0.getHeight();
        final float f3 = -measureScope.mo4557toPx0680j_4(f);
        final float fMo4557toPx0680j_4 = measureScope.mo4557toPx0680j_4(f2);
        return MeasureScope.layout$default(measureScope, width, height, null, new Function1() { // from class: rb6
            public final Object invoke(Object obj) {
                return HorizontalCenterOpticallyKt.d(shapeWithHorizontalCenterOptically, f3, fMo4557toPx0680j_4, placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static MeasureResult b(float f, float f2, CornerBasedShape cornerBasedShape, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        int width = placeableMo4605measureBRTryo0.getWidth();
        int height = placeableMo4605measureBRTryo0.getHeight();
        long jM2949constructorimpl = Size.m2949constructorimpl((((long) Float.floatToRawIntBits(width)) << 32) | (((long) Float.floatToRawIntBits(height)) & 4294967295L));
        final float f3 = -measureScope.mo4557toPx0680j_4(f);
        final float fMo4557toPx0680j_4 = measureScope.mo4557toPx0680j_4(f2);
        final float f4 = (((cornerBasedShape.getTopStart().toPx-TmRCtEA(jM2949constructorimpl, measureScope) + cornerBasedShape.getBottomStart().toPx-TmRCtEA(jM2949constructorimpl, measureScope)) / 2.0f) - ((cornerBasedShape.getTopEnd().toPx-TmRCtEA(jM2949constructorimpl, measureScope) + cornerBasedShape.getBottomEnd().toPx-TmRCtEA(jM2949constructorimpl, measureScope)) / 2.0f)) * 0.11f;
        return MeasureScope.layout$default(measureScope, width, height, null, new Function1() { // from class: sb6
            public final Object invoke(Object obj) {
                return HorizontalCenterOpticallyKt.c(f4, f3, fMo4557toPx0680j_4, placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static Unit c(float f, float f2, float f3, Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, MathKt.roundToInt(RangesKt.coerceIn(f, f2, f3)), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit d(ShapeWithHorizontalCenterOptically shapeWithHorizontalCenterOptically, float f, float f2, Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, MathKt.roundToInt(RangesKt.coerceIn(shapeWithHorizontalCenterOptically.offset(), f, f2)), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0, reason: not valid java name */
    public static final Modifier m497horizontalCenterOptically4j6BHR0(Modifier modifier, final CornerBasedShape cornerBasedShape, final float f, final float f2) {
        return LayoutModifierKt.layout(modifier, new Function3() { // from class: tb6
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HorizontalCenterOpticallyKt.b(f, f2, cornerBasedShape, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m499horizontalCenterOptically4j6BHR0$default(Modifier modifier, CornerBasedShape cornerBasedShape, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = Dp.m6022constructorimpl(0.0f);
        }
        if ((i & 4) != 0) {
            f2 = Dp.m6022constructorimpl(0.0f);
        }
        return m497horizontalCenterOptically4j6BHR0(modifier, cornerBasedShape, f, f2);
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0, reason: not valid java name */
    public static final Modifier m498horizontalCenterOptically4j6BHR0(Modifier modifier, final ShapeWithHorizontalCenterOptically shapeWithHorizontalCenterOptically, final float f, final float f2) {
        return LayoutModifierKt.layout(modifier, new Function3() { // from class: ub6
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HorizontalCenterOpticallyKt.a(f, f2, shapeWithHorizontalCenterOptically, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    /* JADX INFO: renamed from: horizontalCenterOptically-4j6BHR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m500horizontalCenterOptically4j6BHR0$default(Modifier modifier, ShapeWithHorizontalCenterOptically shapeWithHorizontalCenterOptically, float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f = Dp.m6022constructorimpl(0.0f);
        }
        if ((i & 4) != 0) {
            f2 = Dp.m6022constructorimpl(0.0f);
        }
        return m498horizontalCenterOptically4j6BHR0(modifier, shapeWithHorizontalCenterOptically, f, f2);
    }
}
