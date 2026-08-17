package androidx.compose.material3;

import androidx.compose.material3.tokens.DragHandleTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpSize;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J-\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000fJ1\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J+\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0004\u001a\u00020\u0005*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\r\u001a\u00020\u000e*\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/material3/VerticalDragHandleDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/DragHandleColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/DragHandleColors;", "color", "Landroidx/compose/ui/graphics/Color;", "pressedColor", "draggedColor", "colors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DragHandleColors;", "shapes", "Landroidx/compose/material3/DragHandleShapes;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/DragHandleShapes;", "shape", "Landroidx/compose/ui/graphics/Shape;", "pressedShape", "draggedShape", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DragHandleShapes;", "sizes", "Landroidx/compose/material3/DragHandleSizes;", "size", "Landroidx/compose/ui/unit/DpSize;", "pressedSize", "draggedSize", "sizes-L9TjZb0", "(JJJ)Landroidx/compose/material3/DragHandleSizes;", "Landroidx/compose/material3/ColorScheme;", "getColors", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/DragHandleColors;", "Landroidx/compose/material3/Shapes;", "getShapes", "(Landroidx/compose/material3/Shapes;)Landroidx/compose/material3/DragHandleShapes;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class VerticalDragHandleDefaults {
    public static final int $stable = 0;
    public static final VerticalDragHandleDefaults INSTANCE = new VerticalDragHandleDefaults();
    private static final DragHandleSizes sizes;

    static {
        DragHandleTokens dragHandleTokens = DragHandleTokens.INSTANCE;
        sizes = new DragHandleSizes(DpKt.m6044DpSizeYgX7TsA(dragHandleTokens.m1729getWidthD9Ej5fM(), dragHandleTokens.m1725getHeightD9Ej5fM()), DpKt.m6044DpSizeYgX7TsA(dragHandleTokens.m1728getPressedWidthD9Ej5fM(), dragHandleTokens.m1727getPressedHeightD9Ej5fM()), DpKt.m6044DpSizeYgX7TsA(dragHandleTokens.m1723getDraggedWidthD9Ej5fM(), dragHandleTokens.m1722getDraggedHeightD9Ej5fM()), null);
    }

    private VerticalDragHandleDefaults() {
    }

    private final DragHandleColors getColors(ColorScheme colorScheme) {
        DragHandleColors defaultVerticalDragHandleColorsCached = colorScheme.getDefaultVerticalDragHandleColorsCached();
        if (defaultVerticalDragHandleColorsCached != null) {
            return defaultVerticalDragHandleColorsCached;
        }
        DragHandleTokens dragHandleTokens = DragHandleTokens.INSTANCE;
        DragHandleColors dragHandleColors = new DragHandleColors(ColorSchemeKt.fromToken(colorScheme, dragHandleTokens.getColor()), ColorSchemeKt.fromToken(colorScheme, dragHandleTokens.getPressedColor()), ColorSchemeKt.fromToken(colorScheme, dragHandleTokens.getDraggedColor()), null);
        colorScheme.setDefaultVerticalDragHandleColorsCached$material3(dragHandleColors);
        return dragHandleColors;
    }

    private final DragHandleShapes getShapes(Shapes shapes) {
        DragHandleShapes defaultVerticalDragHandleShapesCached = shapes.getDefaultVerticalDragHandleShapesCached();
        if (defaultVerticalDragHandleShapesCached != null) {
            return defaultVerticalDragHandleShapesCached;
        }
        DragHandleTokens dragHandleTokens = DragHandleTokens.INSTANCE;
        DragHandleShapes dragHandleShapes = new DragHandleShapes(ShapesKt.fromToken(shapes, dragHandleTokens.getShape()), ShapesKt.fromToken(shapes, dragHandleTokens.getPressedShape()), ShapesKt.fromToken(shapes, dragHandleTokens.getDraggedShape()));
        shapes.setDefaultVerticalDragHandleShapesCached$material3(dragHandleShapes);
        return dragHandleShapes;
    }

    /* JADX INFO: renamed from: sizes-L9TjZb0$default, reason: not valid java name */
    public static /* synthetic */ DragHandleSizes m1323sizesL9TjZb0$default(VerticalDragHandleDefaults verticalDragHandleDefaults, long j, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
        }
        long j4 = j;
        if ((i & 2) != 0) {
            j2 = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
        }
        long j5 = j2;
        if ((i & 4) != 0) {
            j3 = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
        }
        return verticalDragHandleDefaults.m1325sizesL9TjZb0(j4, j5, j3);
    }

    public final DragHandleColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1465068453, i, -1, "androidx.compose.material3.VerticalDragHandleDefaults.colors (DragHandle.kt:215)");
        }
        DragHandleColors colors = getColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return colors;
    }

    /* JADX INFO: renamed from: colors-RGew2ao, reason: not valid java name */
    public final DragHandleColors m1324colorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2062501640, i, -1, "androidx.compose.material3.VerticalDragHandleDefaults.colors (DragHandle.kt:234)");
        }
        DragHandleColors colors = getColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (jM3170getUnspecified0d7_KjU == 16) {
            jM3170getUnspecified0d7_KjU = colors.getColor();
        }
        if (jM3170getUnspecified0d7_KjU2 == 16) {
            jM3170getUnspecified0d7_KjU2 = colors.getPressedColor();
        }
        if (jM3170getUnspecified0d7_KjU3 == 16) {
            jM3170getUnspecified0d7_KjU3 = colors.getDraggedColor();
        }
        DragHandleColors dragHandleColors = new DragHandleColors(jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return dragHandleColors;
    }

    public final DragHandleShapes shapes(Shape shape, Shape shape2, Shape shape3, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            shape = null;
        }
        if ((i2 & 2) != 0) {
            shape2 = null;
        }
        if ((i2 & 4) != 0) {
            shape3 = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(650848370, i, -1, "androidx.compose.material3.VerticalDragHandleDefaults.shapes (DragHandle.kt:265)");
        }
        DragHandleShapes shapes = getShapes(MaterialTheme.INSTANCE.getShapes(composer, 6));
        if (shape == null) {
            shape = shapes.getShape();
        }
        if (shape2 == null) {
            shape2 = shapes.getPressedShape();
        }
        if (shape3 == null) {
            shape3 = shapes.getDraggedShape();
        }
        DragHandleShapes dragHandleShapes = new DragHandleShapes(shape, shape2, shape3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return dragHandleShapes;
    }

    public final DragHandleSizes sizes() {
        return sizes;
    }

    /* JADX INFO: renamed from: sizes-L9TjZb0, reason: not valid java name */
    public final DragHandleSizes m1325sizesL9TjZb0(long size, long pressedSize, long draggedSize) {
        DragHandleSizes dragHandleSizes = sizes;
        if (size == InlineClassHelperKt.UnspecifiedPackedFloats) {
            size = dragHandleSizes.getSize();
        }
        if (pressedSize == InlineClassHelperKt.UnspecifiedPackedFloats) {
            pressedSize = dragHandleSizes.getPressedSize();
        }
        long j = pressedSize;
        if (draggedSize == InlineClassHelperKt.UnspecifiedPackedFloats) {
            draggedSize = dragHandleSizes.getDraggedSize();
        }
        return new DragHandleSizes(size, j, draggedSize, null);
    }

    public final DragHandleShapes shapes(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1385335639, i, -1, "androidx.compose.material3.VerticalDragHandleDefaults.shapes (DragHandle.kt:246)");
        }
        DragHandleShapes shapes = getShapes(MaterialTheme.INSTANCE.getShapes(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return shapes;
    }
}
