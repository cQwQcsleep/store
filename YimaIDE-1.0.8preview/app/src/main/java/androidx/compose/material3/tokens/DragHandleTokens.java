package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0017\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0013\u0010\u0011\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0018\u0010\u000bR\u0013\u0010\u0019\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u001a\u0010\u000bR\u0013\u0010\u001b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u001c\u0010\u000bR\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b \u0010\u000bR\u0013\u0010!\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\"\u0010\u000bR\u0011\u0010#\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0013\u0010%\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b&\u0010\u000bR\u0011\u0010'\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0016R\u0013\u0010)\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b*\u0010\u000b¨\u0006+"}, d2 = {"Landroidx/compose/material3/tokens/DragHandleTokens;", "", "<init>", "()V", "Color", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerWidth", "Landroidx/compose/ui/unit/Dp;", "getContainerWidth-D9Ej5fM", "()F", "F", "DraggedColor", "getDraggedColor", "DraggedElevation", "getDraggedElevation-D9Ej5fM", "DraggedHeight", "getDraggedHeight-D9Ej5fM", "DraggedShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getDraggedShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "DraggedWidth", "getDraggedWidth-D9Ej5fM", "Elevation", "getElevation-D9Ej5fM", "Height", "getHeight-D9Ej5fM", "PressedColor", "getPressedColor", "PressedElevation", "getPressedElevation-D9Ej5fM", "PressedHeight", "getPressedHeight-D9Ej5fM", "PressedShape", "getPressedShape", "PressedWidth", "getPressedWidth-D9Ej5fM", "Shape", "getShape", "Width", "getWidth-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DragHandleTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens DraggedColor;
    private static final float DraggedElevation;
    private static final float DraggedHeight;
    private static final ShapeKeyTokens DraggedShape;
    private static final float DraggedWidth;
    private static final float Elevation;
    private static final float Height;
    private static final ColorSchemeKeyTokens PressedColor;
    private static final float PressedElevation;
    private static final float PressedHeight;
    private static final ShapeKeyTokens PressedShape;
    private static final float PressedWidth;
    private static final ShapeKeyTokens Shape;
    private static final float Width;
    public static final DragHandleTokens INSTANCE = new DragHandleTokens();
    private static final ColorSchemeKeyTokens Color = ColorSchemeKeyTokens.Outline;
    private static final float ContainerWidth = Dp.m6022constructorimpl(24.0f);

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        DraggedColor = colorSchemeKeyTokens;
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        DraggedElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        DraggedHeight = Dp.m6022constructorimpl(52.0f);
        ShapeKeyTokens shapeKeyTokens = ShapeKeyTokens.CornerMedium;
        DraggedShape = shapeKeyTokens;
        DraggedWidth = Dp.m6022constructorimpl(12.0f);
        Elevation = elevationTokens.m1742getLevel0D9Ej5fM();
        Height = Dp.m6022constructorimpl(48.0f);
        PressedColor = colorSchemeKeyTokens;
        PressedElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        PressedHeight = Dp.m6022constructorimpl(52.0f);
        PressedShape = shapeKeyTokens;
        PressedWidth = Dp.m6022constructorimpl(12.0f);
        Shape = ShapeKeyTokens.CornerFull;
        Width = Dp.m6022constructorimpl(4.0f);
    }

    private DragHandleTokens() {
    }

    public final ColorSchemeKeyTokens getColor() {
        return Color;
    }

    /* JADX INFO: renamed from: getContainerWidth-D9Ej5fM, reason: not valid java name */
    public final float m1720getContainerWidthD9Ej5fM() {
        return ContainerWidth;
    }

    public final ColorSchemeKeyTokens getDraggedColor() {
        return DraggedColor;
    }

    /* JADX INFO: renamed from: getDraggedElevation-D9Ej5fM, reason: not valid java name */
    public final float m1721getDraggedElevationD9Ej5fM() {
        return DraggedElevation;
    }

    /* JADX INFO: renamed from: getDraggedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1722getDraggedHeightD9Ej5fM() {
        return DraggedHeight;
    }

    public final ShapeKeyTokens getDraggedShape() {
        return DraggedShape;
    }

    /* JADX INFO: renamed from: getDraggedWidth-D9Ej5fM, reason: not valid java name */
    public final float m1723getDraggedWidthD9Ej5fM() {
        return DraggedWidth;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m1724getElevationD9Ej5fM() {
        return Elevation;
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m1725getHeightD9Ej5fM() {
        return Height;
    }

    public final ColorSchemeKeyTokens getPressedColor() {
        return PressedColor;
    }

    /* JADX INFO: renamed from: getPressedElevation-D9Ej5fM, reason: not valid java name */
    public final float m1726getPressedElevationD9Ej5fM() {
        return PressedElevation;
    }

    /* JADX INFO: renamed from: getPressedHeight-D9Ej5fM, reason: not valid java name */
    public final float m1727getPressedHeightD9Ej5fM() {
        return PressedHeight;
    }

    public final ShapeKeyTokens getPressedShape() {
        return PressedShape;
    }

    /* JADX INFO: renamed from: getPressedWidth-D9Ej5fM, reason: not valid java name */
    public final float m1728getPressedWidthD9Ej5fM() {
        return PressedWidth;
    }

    public final ShapeKeyTokens getShape() {
        return Shape;
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public final float m1729getWidthD9Ej5fM() {
        return Width;
    }
}
