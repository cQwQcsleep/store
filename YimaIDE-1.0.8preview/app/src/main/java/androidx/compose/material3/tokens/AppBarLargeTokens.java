package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/material3/tokens/AppBarLargeTokens;", "", "<init>", "()V", "ContainerHeight", "Landroidx/compose/ui/unit/Dp;", "getContainerHeight-D9Ej5fM", "()F", "F", "TitleFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getTitleFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AppBarLargeTokens {
    public static final int $stable = 0;
    public static final AppBarLargeTokens INSTANCE = new AppBarLargeTokens();
    private static final float ContainerHeight = Dp.m6022constructorimpl(152.0f);
    private static final TypographyKeyTokens TitleFont = TypographyKeyTokens.HeadlineMedium;

    private AppBarLargeTokens() {
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m1497getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final TypographyKeyTokens getTitleFont() {
        return TitleFont;
    }
}
