package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/tokens/NavigationRailCollapsedTokens;", "", "<init>", "()V", "ContainerElevation", "Landroidx/compose/ui/unit/Dp;", "getContainerElevation-D9Ej5fM", "()F", "F", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ContainerWidth", "getContainerWidth-D9Ej5fM", "ItemVerticalSpace", "getItemVerticalSpace-D9Ej5fM", "TopSpace", "getTopSpace-D9Ej5fM", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "NarrowContainerWidth", "getNarrowContainerWidth-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationRailCollapsedTokens {
    public static final int $stable = 0;
    public static final NavigationRailCollapsedTokens INSTANCE = new NavigationRailCollapsedTokens();
    private static final float ContainerElevation = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerNone;
    private static final float ContainerWidth = Dp.m6022constructorimpl(96.0f);
    private static final float ItemVerticalSpace = Dp.m6022constructorimpl(4.0f);
    private static final float TopSpace = Dp.m6022constructorimpl(44.0f);
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.Surface;
    private static final float NarrowContainerWidth = Dp.m6022constructorimpl(80.0f);

    private NavigationRailCollapsedTokens() {
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1942getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getContainerWidth-D9Ej5fM, reason: not valid java name */
    public final float m1943getContainerWidthD9Ej5fM() {
        return ContainerWidth;
    }

    /* JADX INFO: renamed from: getItemVerticalSpace-D9Ej5fM, reason: not valid java name */
    public final float m1944getItemVerticalSpaceD9Ej5fM() {
        return ItemVerticalSpace;
    }

    /* JADX INFO: renamed from: getNarrowContainerWidth-D9Ej5fM, reason: not valid java name */
    public final float m1945getNarrowContainerWidthD9Ej5fM() {
        return NarrowContainerWidth;
    }

    /* JADX INFO: renamed from: getTopSpace-D9Ej5fM, reason: not valid java name */
    public final float m1946getTopSpaceD9Ej5fM() {
        return TopSpace;
    }
}
