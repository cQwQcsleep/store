package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\r\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u000f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0013\u0010\u0011\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0018\u0010\u000b¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/tokens/DockedToolbarTokens;", "", "<init>", "()V", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerHeight", "Landroidx/compose/ui/unit/Dp;", "getContainerHeight-D9Ej5fM", "()F", "F", "ContainerLeadingSpace", "getContainerLeadingSpace-D9Ej5fM", "ContainerMaxSpacing", "getContainerMaxSpacing-D9Ej5fM", "ContainerMinSpacing", "getContainerMinSpacing-D9Ej5fM", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ContainerTrailingSpace", "getContainerTrailingSpace-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DockedToolbarTokens {
    public static final int $stable = 0;
    public static final DockedToolbarTokens INSTANCE = new DockedToolbarTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.SurfaceContainer;
    private static final float ContainerHeight = Dp.m6022constructorimpl(64.0f);
    private static final float ContainerLeadingSpace = Dp.m6022constructorimpl(16.0f);
    private static final float ContainerMaxSpacing = Dp.m6022constructorimpl(32.0f);
    private static final float ContainerMinSpacing = Dp.m6022constructorimpl(4.0f);
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerNone;
    private static final float ContainerTrailingSpace = Dp.m6022constructorimpl(16.0f);

    private DockedToolbarTokens() {
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m1715getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    /* JADX INFO: renamed from: getContainerLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1716getContainerLeadingSpaceD9Ej5fM() {
        return ContainerLeadingSpace;
    }

    /* JADX INFO: renamed from: getContainerMaxSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1717getContainerMaxSpacingD9Ej5fM() {
        return ContainerMaxSpacing;
    }

    /* JADX INFO: renamed from: getContainerMinSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1718getContainerMinSpacingD9Ej5fM() {
        return ContainerMinSpacing;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getContainerTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1719getContainerTrailingSpaceD9Ej5fM() {
        return ContainerTrailingSpace;
    }
}
