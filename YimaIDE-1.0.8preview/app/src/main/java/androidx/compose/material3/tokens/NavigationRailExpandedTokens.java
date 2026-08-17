package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0011\u0010\u0013\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/tokens/NavigationRailExpandedTokens;", "", "<init>", "()V", "ContainerElevation", "Landroidx/compose/ui/unit/Dp;", "getContainerElevation-D9Ej5fM", "()F", "F", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ContainerWidthMaximum", "getContainerWidthMaximum-D9Ej5fM", "ContainerWidthMinimum", "getContainerWidthMinimum-D9Ej5fM", "ModalContainerElevation", "getModalContainerElevation-D9Ej5fM", "ModalContainerShape", "getModalContainerShape", "TopSpace", "getTopSpace-D9Ej5fM", "ModalContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getModalContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationRailExpandedTokens {
    public static final int $stable = 0;
    private static final float ContainerElevation;
    private static final ShapeKeyTokens ContainerShape;
    private static final float ContainerWidthMaximum;
    private static final float ContainerWidthMinimum;
    public static final NavigationRailExpandedTokens INSTANCE = new NavigationRailExpandedTokens();
    private static final ColorSchemeKeyTokens ModalContainerColor;
    private static final float ModalContainerElevation;
    private static final ShapeKeyTokens ModalContainerShape;
    private static final float TopSpace;

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m1742getLevel0D9Ej5fM();
        ContainerShape = ShapeKeyTokens.CornerNone;
        ContainerWidthMaximum = Dp.m6022constructorimpl(360.0f);
        ContainerWidthMinimum = Dp.m6022constructorimpl(220.0f);
        ModalContainerElevation = elevationTokens.m1744getLevel2D9Ej5fM();
        ModalContainerShape = ShapeKeyTokens.CornerLarge;
        TopSpace = Dp.m6022constructorimpl(44.0f);
        ModalContainerColor = ColorSchemeKeyTokens.SurfaceContainer;
    }

    private NavigationRailExpandedTokens() {
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1947getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getContainerWidthMaximum-D9Ej5fM, reason: not valid java name */
    public final float m1948getContainerWidthMaximumD9Ej5fM() {
        return ContainerWidthMaximum;
    }

    /* JADX INFO: renamed from: getContainerWidthMinimum-D9Ej5fM, reason: not valid java name */
    public final float m1949getContainerWidthMinimumD9Ej5fM() {
        return ContainerWidthMinimum;
    }

    public final ColorSchemeKeyTokens getModalContainerColor() {
        return ModalContainerColor;
    }

    /* JADX INFO: renamed from: getModalContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1950getModalContainerElevationD9Ej5fM() {
        return ModalContainerElevation;
    }

    public final ShapeKeyTokens getModalContainerShape() {
        return ModalContainerShape;
    }

    /* JADX INFO: renamed from: getTopSpace-D9Ej5fM, reason: not valid java name */
    public final float m1951getTopSpaceD9Ej5fM() {
        return TopSpace;
    }
}
