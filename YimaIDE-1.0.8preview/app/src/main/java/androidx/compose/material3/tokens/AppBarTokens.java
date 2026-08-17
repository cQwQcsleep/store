package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0011\u0010\u0017\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0011\u0010\u001b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0011\u0010\u001f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u0013\u0010%\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b&\u0010\u0007¨\u0006'"}, d2 = {"Landroidx/compose/material3/tokens/AppBarTokens;", "", "<init>", "()V", "AvatarSize", "Landroidx/compose/ui/unit/Dp;", "getAvatarSize-D9Ej5fM", "()F", "F", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerElevation", "getContainerElevation-D9Ej5fM", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "IconButtonSpace", "getIconButtonSpace-D9Ej5fM", "IconSize", "getIconSize-D9Ej5fM", "LeadingIconColor", "getLeadingIconColor", "LeadingSpace", "getLeadingSpace-D9Ej5fM", "OnScrollContainerColor", "getOnScrollContainerColor", "OnScrollContainerElevation", "getOnScrollContainerElevation-D9Ej5fM", "SubtitleColor", "getSubtitleColor", "TitleColor", "getTitleColor", "TrailingIconColor", "getTrailingIconColor", "TrailingSpace", "getTrailingSpace-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AppBarTokens {
    public static final int $stable = 0;
    private static final float ContainerElevation;
    private static final ShapeKeyTokens ContainerShape;
    private static final float IconButtonSpace;
    private static final float IconSize;
    private static final ColorSchemeKeyTokens LeadingIconColor;
    private static final float LeadingSpace;
    private static final ColorSchemeKeyTokens OnScrollContainerColor;
    private static final float OnScrollContainerElevation;
    private static final ColorSchemeKeyTokens SubtitleColor;
    private static final ColorSchemeKeyTokens TitleColor;
    private static final ColorSchemeKeyTokens TrailingIconColor;
    private static final float TrailingSpace;
    public static final AppBarTokens INSTANCE = new AppBarTokens();
    private static final float AvatarSize = Dp.m6022constructorimpl(32.0f);
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.Surface;

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        ContainerShape = ShapeKeyTokens.CornerNone;
        IconButtonSpace = Dp.m6022constructorimpl(0.0f);
        IconSize = Dp.m6022constructorimpl(24.0f);
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        LeadingIconColor = colorSchemeKeyTokens;
        LeadingSpace = Dp.m6022constructorimpl(4.0f);
        OnScrollContainerColor = ColorSchemeKeyTokens.SurfaceContainer;
        OnScrollContainerElevation = elevationTokens.m1744getLevel2D9Ej5fM();
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        SubtitleColor = colorSchemeKeyTokens2;
        TitleColor = colorSchemeKeyTokens;
        TrailingIconColor = colorSchemeKeyTokens2;
        TrailingSpace = Dp.m6022constructorimpl(4.0f);
    }

    private AppBarTokens() {
    }

    /* JADX INFO: renamed from: getAvatarSize-D9Ej5fM, reason: not valid java name */
    public final float m1502getAvatarSizeD9Ej5fM() {
        return AvatarSize;
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1503getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getIconButtonSpace-D9Ej5fM, reason: not valid java name */
    public final float m1504getIconButtonSpaceD9Ej5fM() {
        return IconButtonSpace;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1505getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ColorSchemeKeyTokens getLeadingIconColor() {
        return LeadingIconColor;
    }

    /* JADX INFO: renamed from: getLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1506getLeadingSpaceD9Ej5fM() {
        return LeadingSpace;
    }

    public final ColorSchemeKeyTokens getOnScrollContainerColor() {
        return OnScrollContainerColor;
    }

    /* JADX INFO: renamed from: getOnScrollContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1507getOnScrollContainerElevationD9Ej5fM() {
        return OnScrollContainerElevation;
    }

    public final ColorSchemeKeyTokens getSubtitleColor() {
        return SubtitleColor;
    }

    public final ColorSchemeKeyTokens getTitleColor() {
        return TitleColor;
    }

    public final ColorSchemeKeyTokens getTrailingIconColor() {
        return TrailingIconColor;
    }

    /* JADX INFO: renamed from: getTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1508getTrailingSpaceD9Ej5fM() {
        return TrailingSpace;
    }
}
