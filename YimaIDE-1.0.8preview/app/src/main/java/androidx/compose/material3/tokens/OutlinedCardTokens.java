package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0019\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0007R\u000e\u0010\u0015\u001a\u00020\u0016X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\u0017\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0018\u0010\u000bR\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u001c\u0010\u000bR\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b \u0010\u000bR\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0011\u0010#\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0007R\u0013\u0010%\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b&\u0010\u000bR\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0007R\u0013\u0010)\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b*\u0010\u000bR\u0013\u0010+\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b,\u0010\u000bR\u0011\u0010-\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0007¨\u0006/"}, d2 = {"Landroidx/compose/material3/tokens/OutlinedCardTokens;", "", "<init>", "()V", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerElevation", "Landroidx/compose/ui/unit/Dp;", "getContainerElevation-D9Ej5fM", "()F", "F", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "DisabledContainerElevation", "getDisabledContainerElevation-D9Ej5fM", "DisabledOutlineColor", "getDisabledOutlineColor", "DisabledOutlineOpacity", "", "DraggedContainerElevation", "getDraggedContainerElevation-D9Ej5fM", "DraggedOutlineColor", "getDraggedOutlineColor", "FocusContainerElevation", "getFocusContainerElevation-D9Ej5fM", "FocusOutlineColor", "getFocusOutlineColor", "HoverContainerElevation", "getHoverContainerElevation-D9Ej5fM", "HoverOutlineColor", "getHoverOutlineColor", "IconColor", "getIconColor", "IconSize", "getIconSize-D9Ej5fM", "OutlineColor", "getOutlineColor", "OutlineWidth", "getOutlineWidth-D9Ej5fM", "PressedContainerElevation", "getPressedContainerElevation-D9Ej5fM", "PressedOutlineColor", "getPressedOutlineColor", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OutlinedCardTokens {
    public static final int $stable = 0;
    private static final float ContainerElevation;
    private static final ShapeKeyTokens ContainerShape;
    private static final float DisabledContainerElevation;
    private static final ColorSchemeKeyTokens DisabledOutlineColor;
    public static final float DisabledOutlineOpacity = 0.12f;
    private static final float DraggedContainerElevation;
    private static final ColorSchemeKeyTokens DraggedOutlineColor;
    private static final float FocusContainerElevation;
    private static final ColorSchemeKeyTokens FocusOutlineColor;
    private static final float HoverContainerElevation;
    private static final ColorSchemeKeyTokens HoverOutlineColor;
    private static final ColorSchemeKeyTokens IconColor;
    private static final float IconSize;
    private static final ColorSchemeKeyTokens OutlineColor;
    private static final float OutlineWidth;
    private static final float PressedContainerElevation;
    private static final ColorSchemeKeyTokens PressedOutlineColor;
    public static final OutlinedCardTokens INSTANCE = new OutlinedCardTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.Surface;

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        ContainerShape = ShapeKeyTokens.CornerMedium;
        DisabledContainerElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        DisabledOutlineColor = ColorSchemeKeyTokens.Outline;
        DraggedContainerElevation = elevationTokens.m1745getLevel3D9Ej5fM();
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OutlineVariant;
        DraggedOutlineColor = colorSchemeKeyTokens;
        FocusContainerElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        FocusOutlineColor = ColorSchemeKeyTokens.OnSurface;
        HoverContainerElevation = elevationTokens.m1743getLevel1D9Ej5fM();
        HoverOutlineColor = colorSchemeKeyTokens;
        IconColor = ColorSchemeKeyTokens.Primary;
        IconSize = Dp.m6022constructorimpl(24.0f);
        OutlineColor = colorSchemeKeyTokens;
        OutlineWidth = Dp.m6022constructorimpl(1.0f);
        PressedContainerElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        PressedOutlineColor = colorSchemeKeyTokens;
    }

    private OutlinedCardTokens() {
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1969getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getDisabledContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1970getDisabledContainerElevationD9Ej5fM() {
        return DisabledContainerElevation;
    }

    public final ColorSchemeKeyTokens getDisabledOutlineColor() {
        return DisabledOutlineColor;
    }

    /* JADX INFO: renamed from: getDraggedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1971getDraggedContainerElevationD9Ej5fM() {
        return DraggedContainerElevation;
    }

    public final ColorSchemeKeyTokens getDraggedOutlineColor() {
        return DraggedOutlineColor;
    }

    /* JADX INFO: renamed from: getFocusContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1972getFocusContainerElevationD9Ej5fM() {
        return FocusContainerElevation;
    }

    public final ColorSchemeKeyTokens getFocusOutlineColor() {
        return FocusOutlineColor;
    }

    /* JADX INFO: renamed from: getHoverContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1973getHoverContainerElevationD9Ej5fM() {
        return HoverContainerElevation;
    }

    public final ColorSchemeKeyTokens getHoverOutlineColor() {
        return HoverOutlineColor;
    }

    public final ColorSchemeKeyTokens getIconColor() {
        return IconColor;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1974getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ColorSchemeKeyTokens getOutlineColor() {
        return OutlineColor;
    }

    /* JADX INFO: renamed from: getOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m1975getOutlineWidthD9Ej5fM() {
        return OutlineWidth;
    }

    /* JADX INFO: renamed from: getPressedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1976getPressedContainerElevationD9Ej5fM() {
        return PressedContainerElevation;
    }

    public final ColorSchemeKeyTokens getPressedOutlineColor() {
        return PressedOutlineColor;
    }
}
