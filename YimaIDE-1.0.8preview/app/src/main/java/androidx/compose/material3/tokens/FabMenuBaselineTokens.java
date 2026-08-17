package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0017\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0011\u0010\u001b\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007R\u0013\u0010#\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b$\u0010\u0007¨\u0006%"}, d2 = {"Landroidx/compose/material3/tokens/FabMenuBaselineTokens;", "", "<init>", "()V", "CloseButtonBetweenSpace", "Landroidx/compose/ui/unit/Dp;", "getCloseButtonBetweenSpace-D9Ej5fM", "()F", "F", "CloseButtonContainerElevation", "getCloseButtonContainerElevation-D9Ej5fM", "CloseButtonContainerHeight", "getCloseButtonContainerHeight-D9Ej5fM", "CloseButtonContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getCloseButtonContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "CloseButtonContainerWidth", "getCloseButtonContainerWidth-D9Ej5fM", "CloseButtonIconSize", "getCloseButtonIconSize-D9Ej5fM", "ListItemBetweenSpace", "getListItemBetweenSpace-D9Ej5fM", "ListItemContainerElevation", "getListItemContainerElevation-D9Ej5fM", "ListItemContainerHeight", "getListItemContainerHeight-D9Ej5fM", "ListItemContainerShape", "getListItemContainerShape", "ListItemIconLabelSpace", "getListItemIconLabelSpace-D9Ej5fM", "ListItemIconSize", "getListItemIconSize-D9Ej5fM", "ListItemLeadingSpace", "getListItemLeadingSpace-D9Ej5fM", "ListItemTrailingSpace", "getListItemTrailingSpace-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FabMenuBaselineTokens {
    public static final int $stable = 0;
    private static final float CloseButtonContainerElevation;
    private static final float CloseButtonContainerHeight;
    private static final ShapeKeyTokens CloseButtonContainerShape;
    private static final float CloseButtonContainerWidth;
    private static final float CloseButtonIconSize;
    private static final float ListItemBetweenSpace;
    private static final float ListItemContainerElevation;
    private static final float ListItemContainerHeight;
    private static final ShapeKeyTokens ListItemContainerShape;
    private static final float ListItemIconLabelSpace;
    private static final float ListItemIconSize;
    private static final float ListItemLeadingSpace;
    private static final float ListItemTrailingSpace;
    public static final FabMenuBaselineTokens INSTANCE = new FabMenuBaselineTokens();
    private static final float CloseButtonBetweenSpace = Dp.m6022constructorimpl(8.0f);

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        CloseButtonContainerElevation = elevationTokens.m1745getLevel3D9Ej5fM();
        CloseButtonContainerHeight = Dp.m6022constructorimpl(56.0f);
        ShapeKeyTokens shapeKeyTokens = ShapeKeyTokens.CornerFull;
        CloseButtonContainerShape = shapeKeyTokens;
        CloseButtonContainerWidth = Dp.m6022constructorimpl(56.0f);
        CloseButtonIconSize = Dp.m6022constructorimpl(20.0f);
        ListItemBetweenSpace = Dp.m6022constructorimpl(4.0f);
        ListItemContainerElevation = elevationTokens.m1745getLevel3D9Ej5fM();
        ListItemContainerHeight = Dp.m6022constructorimpl(56.0f);
        ListItemContainerShape = shapeKeyTokens;
        ListItemIconLabelSpace = Dp.m6022constructorimpl(8.0f);
        ListItemIconSize = Dp.m6022constructorimpl(24.0f);
        ListItemLeadingSpace = Dp.m6022constructorimpl(24.0f);
        ListItemTrailingSpace = Dp.m6022constructorimpl(24.0f);
    }

    private FabMenuBaselineTokens() {
    }

    /* JADX INFO: renamed from: getCloseButtonBetweenSpace-D9Ej5fM, reason: not valid java name */
    public final float m1782getCloseButtonBetweenSpaceD9Ej5fM() {
        return CloseButtonBetweenSpace;
    }

    /* JADX INFO: renamed from: getCloseButtonContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1783getCloseButtonContainerElevationD9Ej5fM() {
        return CloseButtonContainerElevation;
    }

    /* JADX INFO: renamed from: getCloseButtonContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m1784getCloseButtonContainerHeightD9Ej5fM() {
        return CloseButtonContainerHeight;
    }

    public final ShapeKeyTokens getCloseButtonContainerShape() {
        return CloseButtonContainerShape;
    }

    /* JADX INFO: renamed from: getCloseButtonContainerWidth-D9Ej5fM, reason: not valid java name */
    public final float m1785getCloseButtonContainerWidthD9Ej5fM() {
        return CloseButtonContainerWidth;
    }

    /* JADX INFO: renamed from: getCloseButtonIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1786getCloseButtonIconSizeD9Ej5fM() {
        return CloseButtonIconSize;
    }

    /* JADX INFO: renamed from: getListItemBetweenSpace-D9Ej5fM, reason: not valid java name */
    public final float m1787getListItemBetweenSpaceD9Ej5fM() {
        return ListItemBetweenSpace;
    }

    /* JADX INFO: renamed from: getListItemContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1788getListItemContainerElevationD9Ej5fM() {
        return ListItemContainerElevation;
    }

    /* JADX INFO: renamed from: getListItemContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m1789getListItemContainerHeightD9Ej5fM() {
        return ListItemContainerHeight;
    }

    public final ShapeKeyTokens getListItemContainerShape() {
        return ListItemContainerShape;
    }

    /* JADX INFO: renamed from: getListItemIconLabelSpace-D9Ej5fM, reason: not valid java name */
    public final float m1790getListItemIconLabelSpaceD9Ej5fM() {
        return ListItemIconLabelSpace;
    }

    /* JADX INFO: renamed from: getListItemIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1791getListItemIconSizeD9Ej5fM() {
        return ListItemIconSize;
    }

    /* JADX INFO: renamed from: getListItemLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1792getListItemLeadingSpaceD9Ej5fM() {
        return ListItemLeadingSpace;
    }

    /* JADX INFO: renamed from: getListItemTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m1793getListItemTrailingSpaceD9Ej5fM() {
        return ListItemTrailingSpace;
    }
}
