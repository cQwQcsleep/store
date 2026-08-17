package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSize;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ShapesKt;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\b\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\"\u0018\u0010\r\u001a\u00020\u000b*\u00020\u000e8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"top", "Landroidx/compose/foundation/shape/CornerBasedShape;", "bottomSize", "Landroidx/compose/foundation/shape/CornerSize;", "bottom", "topSize", "start", "endSize", "end", "startSize", "fromToken", "Landroidx/compose/ui/graphics/Shape;", "Landroidx/compose/material3/Shapes;", "value", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getValue", "(Landroidx/compose/material3/tokens/ShapeKeyTokens;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "LocalShapes", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalShapes", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ShapesKt {
    private static final ProvidableCompositionLocal<Shapes> LocalShapes = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: u8d
        public final Object invoke() {
            return ShapesKt.a();
        }
    });

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ShapeKeyTokens.values().length];
            try {
                iArr[ShapeKeyTokens.CornerExtraLarge.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ShapeKeyTokens.CornerExtraLargeIncreased.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ShapeKeyTokens.CornerExtraExtraLarge.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ShapeKeyTokens.CornerExtraLargeTop.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ShapeKeyTokens.CornerExtraSmall.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ShapeKeyTokens.CornerExtraSmallTop.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ShapeKeyTokens.CornerFull.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ShapeKeyTokens.CornerLarge.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ShapeKeyTokens.CornerLargeIncreased.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ShapeKeyTokens.CornerLargeEnd.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ShapeKeyTokens.CornerLargeTop.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ShapeKeyTokens.CornerMedium.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ShapeKeyTokens.CornerNone.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ShapeKeyTokens.CornerSmall.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ShapeKeyTokens.CornerLargeStart.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static Shapes a() {
        return new Shapes(null, null, null, null, null, 31, null);
    }

    public static final CornerBasedShape bottom(CornerBasedShape cornerBasedShape, CornerSize cornerSize) {
        return CornerBasedShape.copy$default(cornerBasedShape, cornerSize, cornerSize, (CornerSize) null, (CornerSize) null, 12, (Object) null);
    }

    public static /* synthetic */ CornerBasedShape bottom$default(CornerBasedShape cornerBasedShape, CornerSize cornerSize, int i, Object obj) {
        if ((i & 1) != 0) {
            cornerSize = ShapeDefaults.INSTANCE.getCornerNone$material3();
        }
        return bottom(cornerBasedShape, cornerSize);
    }

    public static final CornerBasedShape end(CornerBasedShape cornerBasedShape, CornerSize cornerSize) {
        return CornerBasedShape.copy$default(cornerBasedShape, cornerSize, (CornerSize) null, (CornerSize) null, cornerSize, 6, (Object) null);
    }

    public static /* synthetic */ CornerBasedShape end$default(CornerBasedShape cornerBasedShape, CornerSize cornerSize, int i, Object obj) {
        if ((i & 1) != 0) {
            cornerSize = ShapeDefaults.INSTANCE.getCornerNone$material3();
        }
        return end(cornerBasedShape, cornerSize);
    }

    public static final Shape fromToken(Shapes shapes, ShapeKeyTokens shapeKeyTokens) {
        switch (WhenMappings.$EnumSwitchMapping$0[shapeKeyTokens.ordinal()]) {
            case 1:
                return shapes.getExtraLarge();
            case 2:
                return shapes.getExtraLargeIncreased();
            case 3:
                return shapes.getExtraExtraLarge();
            case 4:
                return top$default(shapes.getExtraLarge(), null, 1, null);
            case 5:
                return shapes.getExtraSmall();
            case 6:
                return top$default(shapes.getExtraSmall(), null, 1, null);
            case 7:
                return RoundedCornerShapeKt.getCircleShape();
            case 8:
                return shapes.getLarge();
            case 9:
                return shapes.getLargeIncreased();
            case 10:
                return end$default(shapes.getLarge(), null, 1, null);
            case 11:
                return top$default(shapes.getLarge(), null, 1, null);
            case 12:
                return shapes.getMedium();
            case 13:
                return RectangleShapeKt.getRectangleShape();
            case 14:
                return shapes.getSmall();
            case 15:
                return start$default(shapes.getLarge(), null, 1, null);
            default:
                bu8.a();
                return null;
        }
    }

    public static final ProvidableCompositionLocal<Shapes> getLocalShapes() {
        return LocalShapes;
    }

    public static final Shape getValue(ShapeKeyTokens shapeKeyTokens, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1629172543, i, -1, "androidx.compose.material3.<get-value> (Shapes.kt:358)");
        }
        Shape shapeFromToken = fromToken(MaterialTheme.INSTANCE.getShapes(composer, 6), shapeKeyTokens);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return shapeFromToken;
    }

    public static final CornerBasedShape start(CornerBasedShape cornerBasedShape, CornerSize cornerSize) {
        return CornerBasedShape.copy$default(cornerBasedShape, (CornerSize) null, cornerSize, cornerSize, (CornerSize) null, 9, (Object) null);
    }

    public static /* synthetic */ CornerBasedShape start$default(CornerBasedShape cornerBasedShape, CornerSize cornerSize, int i, Object obj) {
        if ((i & 1) != 0) {
            cornerSize = ShapeDefaults.INSTANCE.getCornerNone$material3();
        }
        return start(cornerBasedShape, cornerSize);
    }

    public static final CornerBasedShape top(CornerBasedShape cornerBasedShape, CornerSize cornerSize) {
        return CornerBasedShape.copy$default(cornerBasedShape, (CornerSize) null, (CornerSize) null, cornerSize, cornerSize, 3, (Object) null);
    }

    public static /* synthetic */ CornerBasedShape top$default(CornerBasedShape cornerBasedShape, CornerSize cornerSize, int i, Object obj) {
        if ((i & 1) != 0) {
            cornerSize = ShapeDefaults.INSTANCE.getCornerNone$material3();
        }
        return top(cornerBasedShape, cornerSize);
    }
}
