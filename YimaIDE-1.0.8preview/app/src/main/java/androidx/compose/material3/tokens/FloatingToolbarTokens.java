package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u001b\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u001d\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u001f\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010!\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010#\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018¨\u0006%"}, d2 = {"Landroidx/compose/material3/tokens/FloatingToolbarTokens;", "", "<init>", "()V", "ContainerBetweenSpace", "Landroidx/compose/ui/unit/Dp;", "getContainerBetweenSpace-D9Ej5fM", "()F", "F", "ContainerExternalPadding", "getContainerExternalPadding-D9Ej5fM", "ContainerHeight", "getContainerHeight-D9Ej5fM", "ContainerLeadingSpace", "getContainerLeadingSpace-D9Ej5fM", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ContainerTrailingSpace", "getContainerTrailingSpace-D9Ej5fM", "StandardContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getStandardContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "VibrantButtonSelectedContainerColor", "getVibrantButtonSelectedContainerColor", "VibrantButtonSelectedIconColor", "getVibrantButtonSelectedIconColor", "VibrantButtonSelectedTextColor", "getVibrantButtonSelectedTextColor", "VibrantButtonUnselectedIconColor", "getVibrantButtonUnselectedIconColor", "VibrantButtonUnselectedTextColor", "getVibrantButtonUnselectedTextColor", "VibrantContainerColor", "getVibrantContainerColor", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FloatingToolbarTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens StandardContainerColor;
    private static final ColorSchemeKeyTokens VibrantButtonSelectedContainerColor;
    private static final ColorSchemeKeyTokens VibrantButtonSelectedIconColor;
    private static final ColorSchemeKeyTokens VibrantButtonSelectedTextColor;
    private static final ColorSchemeKeyTokens VibrantButtonUnselectedIconColor;
    private static final ColorSchemeKeyTokens VibrantButtonUnselectedTextColor;
    private static final ColorSchemeKeyTokens VibrantContainerColor;
    public static final FloatingToolbarTokens INSTANCE = new FloatingToolbarTokens();
    private static final float ContainerBetweenSpace = Dp.m6022constructorimpl(4.0f);
    private static final float ContainerExternalPadding = Dp.m6022constructorimpl(16.0f);
    private static final float ContainerHeight = Dp.m6022constructorimpl(64.0f);
    private static final float ContainerLeadingSpace = Dp.m6022constructorimpl(8.0f);
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerFull;
    private static final float ContainerTrailingSpace = Dp.m6022constructorimpl(8.0f);

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.SurfaceContainer;
        StandardContainerColor = colorSchemeKeyTokens;
        VibrantButtonSelectedContainerColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurface;
        VibrantButtonSelectedIconColor = colorSchemeKeyTokens2;
        VibrantButtonSelectedTextColor = colorSchemeKeyTokens2;
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.OnPrimaryContainer;
        VibrantButtonUnselectedIconColor = colorSchemeKeyTokens3;
        VibrantButtonUnselectedTextColor = colorSchemeKeyTokens3;
        VibrantContainerColor = ColorSchemeKeyTokens.PrimaryContainer;
    }

    private FloatingToolbarTokens() {
    }

    /* JADX INFO: renamed from: getContainerBetweenSpace-D9Ej5fM, reason: not valid java name */
    public final float m1854getContainerBetweenSpaceD9Ej5fM() {
        return ContainerBetweenSpace;
    }

    /* JADX INFO: renamed from: getContainerExternalPadding-D9Ej5fM, reason: not valid java name */
    public final float m1855getContainerExternalPaddingD9Ej5fM() {
        return ContainerExternalPadding;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m1856getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    /* JADX INFO: renamed from: getContainerLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1857getContainerLeadingSpaceD9Ej5fM() {
        return ContainerLeadingSpace;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getContainerTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1858getContainerTrailingSpaceD9Ej5fM() {
        return ContainerTrailingSpace;
    }

    public final ColorSchemeKeyTokens getStandardContainerColor() {
        return StandardContainerColor;
    }

    public final ColorSchemeKeyTokens getVibrantButtonSelectedContainerColor() {
        return VibrantButtonSelectedContainerColor;
    }

    public final ColorSchemeKeyTokens getVibrantButtonSelectedIconColor() {
        return VibrantButtonSelectedIconColor;
    }

    public final ColorSchemeKeyTokens getVibrantButtonSelectedTextColor() {
        return VibrantButtonSelectedTextColor;
    }

    public final ColorSchemeKeyTokens getVibrantButtonUnselectedIconColor() {
        return VibrantButtonUnselectedIconColor;
    }

    public final ColorSchemeKeyTokens getVibrantButtonUnselectedTextColor() {
        return VibrantButtonUnselectedTextColor;
    }

    public final ColorSchemeKeyTokens getVibrantContainerColor() {
        return VibrantContainerColor;
    }
}
