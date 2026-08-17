package androidx.compose.material3.tokens;

import androidx.compose.foundation.shape.CornerSize;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0006\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0014\u0010\u001d\u001a\u00020\u001eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0013\u0010 \u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b!\u0010\u0007R\u0013\u0010\"\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b#\u0010\u0007¨\u0006$"}, d2 = {"Landroidx/compose/material3/tokens/SplitButtonLargeTokens;", "", "<init>", "()V", "BetweenSpace", "Landroidx/compose/ui/unit/Dp;", "getBetweenSpace-D9Ej5fM", "()F", "F", "ContainerHeight", "getContainerHeight-D9Ej5fM", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "InnerCornerCornerSize", "Landroidx/compose/foundation/shape/CornerSize;", "getInnerCornerCornerSize", "()Landroidx/compose/foundation/shape/CornerSize;", "InnerHoveredCornerCornerSize", "getInnerHoveredCornerCornerSize", "InnerPressedCornerCornerSize", "getInnerPressedCornerCornerSize", "LeadingButtonLeadingSpace", "getLeadingButtonLeadingSpace-D9Ej5fM", "LeadingButtonTrailingSpace", "getLeadingButtonTrailingSpace-D9Ej5fM", "TrailingIconSize", "getTrailingIconSize-D9Ej5fM", "TrailingInnerSelectedCornerCornerSizePercent", "", "getTrailingInnerSelectedCornerCornerSizePercent", "TrailingButtonLeadingSpace", "getTrailingButtonLeadingSpace-D9Ej5fM", "TrailingButtonTrailingSpace", "getTrailingButtonTrailingSpace-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SplitButtonLargeTokens {
    public static final int $stable = 0;
    private static final CornerSize InnerCornerCornerSize;
    private static final CornerSize InnerHoveredCornerCornerSize;
    private static final CornerSize InnerPressedCornerCornerSize;
    private static final float LeadingButtonLeadingSpace;
    private static final float LeadingButtonTrailingSpace;
    private static final float TrailingButtonLeadingSpace;
    private static final float TrailingButtonTrailingSpace;
    private static final float TrailingIconSize;
    private static final float TrailingInnerSelectedCornerCornerSizePercent;
    public static final SplitButtonLargeTokens INSTANCE = new SplitButtonLargeTokens();
    private static final float BetweenSpace = Dp.m6022constructorimpl(2.0f);
    private static final float ContainerHeight = Dp.m6022constructorimpl(96.0f);
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerFull;

    static {
        ShapeTokens shapeTokens = ShapeTokens.INSTANCE;
        InnerCornerCornerSize = shapeTokens.getCornerValueSmall();
        InnerHoveredCornerCornerSize = shapeTokens.getCornerValueLargeIncreased();
        InnerPressedCornerCornerSize = shapeTokens.getCornerValueLargeIncreased();
        LeadingButtonLeadingSpace = Dp.m6022constructorimpl(48.0f);
        LeadingButtonTrailingSpace = Dp.m6022constructorimpl(48.0f);
        TrailingIconSize = Dp.m6022constructorimpl(38.0f);
        TrailingInnerSelectedCornerCornerSizePercent = 50.0f;
        TrailingButtonLeadingSpace = Dp.m6022constructorimpl(29.0f);
        TrailingButtonTrailingSpace = Dp.m6022constructorimpl(29.0f);
    }

    private SplitButtonLargeTokens() {
    }

    /* JADX INFO: renamed from: getBetweenSpace-D9Ej5fM, reason: not valid java name */
    public final float m2129getBetweenSpaceD9Ej5fM() {
        return BetweenSpace;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2130getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final CornerSize getInnerCornerCornerSize() {
        return InnerCornerCornerSize;
    }

    public final CornerSize getInnerHoveredCornerCornerSize() {
        return InnerHoveredCornerCornerSize;
    }

    public final CornerSize getInnerPressedCornerCornerSize() {
        return InnerPressedCornerCornerSize;
    }

    /* JADX INFO: renamed from: getLeadingButtonLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m2131getLeadingButtonLeadingSpaceD9Ej5fM() {
        return LeadingButtonLeadingSpace;
    }

    /* JADX INFO: renamed from: getLeadingButtonTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m2132getLeadingButtonTrailingSpaceD9Ej5fM() {
        return LeadingButtonTrailingSpace;
    }

    /* JADX INFO: renamed from: getTrailingButtonLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m2133getTrailingButtonLeadingSpaceD9Ej5fM() {
        return TrailingButtonLeadingSpace;
    }

    /* JADX INFO: renamed from: getTrailingButtonTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m2134getTrailingButtonTrailingSpaceD9Ej5fM() {
        return TrailingButtonTrailingSpace;
    }

    /* JADX INFO: renamed from: getTrailingIconSize-D9Ej5fM, reason: not valid java name */
    public final float m2135getTrailingIconSizeD9Ej5fM() {
        return TrailingIconSize;
    }

    public final float getTrailingInnerSelectedCornerCornerSizePercent() {
        return TrailingInnerSelectedCornerCornerSizePercent;
    }
}
