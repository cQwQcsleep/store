package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0014\u0010\u000bR\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0013\u0010\u0017\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0018\u0010\u000bR\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0011\u0010#\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007¨\u0006'"}, d2 = {"Landroidx/compose/material3/tokens/SearchViewTokens;", "", "<init>", "()V", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerElevation", "Landroidx/compose/ui/unit/Dp;", "getContainerElevation-D9Ej5fM", "()F", "F", "DividerColor", "getDividerColor", "DockedContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getDockedContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "DockedHeaderContainerHeight", "getDockedHeaderContainerHeight-D9Ej5fM", "FullScreenContainerShape", "getFullScreenContainerShape", "FullScreenHeaderContainerHeight", "getFullScreenHeaderContainerHeight-D9Ej5fM", "HeaderInputTextColor", "getHeaderInputTextColor", "HeaderInputTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getHeaderInputTextFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "HeaderLeadingIconColor", "getHeaderLeadingIconColor", "HeaderSupportingTextColor", "getHeaderSupportingTextColor", "HeaderSupportingTextFont", "getHeaderSupportingTextFont", "HeaderTrailingIconColor", "getHeaderTrailingIconColor", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SearchViewTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens HeaderInputTextColor;
    private static final TypographyKeyTokens HeaderInputTextFont;
    private static final ColorSchemeKeyTokens HeaderLeadingIconColor;
    private static final ColorSchemeKeyTokens HeaderSupportingTextColor;
    private static final TypographyKeyTokens HeaderSupportingTextFont;
    private static final ColorSchemeKeyTokens HeaderTrailingIconColor;
    public static final SearchViewTokens INSTANCE = new SearchViewTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.SurfaceContainerHigh;
    private static final float ContainerElevation = ElevationTokens.INSTANCE.m1745getLevel3D9Ej5fM();
    private static final ColorSchemeKeyTokens DividerColor = ColorSchemeKeyTokens.Outline;
    private static final ShapeKeyTokens DockedContainerShape = ShapeKeyTokens.CornerExtraLarge;
    private static final float DockedHeaderContainerHeight = Dp.m6022constructorimpl(56.0f);
    private static final ShapeKeyTokens FullScreenContainerShape = ShapeKeyTokens.CornerNone;
    private static final float FullScreenHeaderContainerHeight = Dp.m6022constructorimpl(72.0f);

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        HeaderInputTextColor = colorSchemeKeyTokens;
        TypographyKeyTokens typographyKeyTokens = TypographyKeyTokens.BodyLarge;
        HeaderInputTextFont = typographyKeyTokens;
        HeaderLeadingIconColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        HeaderSupportingTextColor = colorSchemeKeyTokens2;
        HeaderSupportingTextFont = typographyKeyTokens;
        HeaderTrailingIconColor = colorSchemeKeyTokens2;
    }

    private SearchViewTokens() {
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2089getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ColorSchemeKeyTokens getDividerColor() {
        return DividerColor;
    }

    public final ShapeKeyTokens getDockedContainerShape() {
        return DockedContainerShape;
    }

    /* JADX INFO: renamed from: getDockedHeaderContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2090getDockedHeaderContainerHeightD9Ej5fM() {
        return DockedHeaderContainerHeight;
    }

    public final ShapeKeyTokens getFullScreenContainerShape() {
        return FullScreenContainerShape;
    }

    /* JADX INFO: renamed from: getFullScreenHeaderContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2091getFullScreenHeaderContainerHeightD9Ej5fM() {
        return FullScreenHeaderContainerHeight;
    }

    public final ColorSchemeKeyTokens getHeaderInputTextColor() {
        return HeaderInputTextColor;
    }

    public final TypographyKeyTokens getHeaderInputTextFont() {
        return HeaderInputTextFont;
    }

    public final ColorSchemeKeyTokens getHeaderLeadingIconColor() {
        return HeaderLeadingIconColor;
    }

    public final ColorSchemeKeyTokens getHeaderSupportingTextColor() {
        return HeaderSupportingTextColor;
    }

    public final TypographyKeyTokens getHeaderSupportingTextFont() {
        return HeaderSupportingTextFont;
    }

    public final ColorSchemeKeyTokens getHeaderTrailingIconColor() {
        return HeaderTrailingIconColor;
    }
}
