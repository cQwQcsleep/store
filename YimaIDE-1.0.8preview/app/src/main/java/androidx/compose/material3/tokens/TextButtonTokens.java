package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u001b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007¨\u0006$"}, d2 = {"Landroidx/compose/material3/tokens/TextButtonTokens;", "", "<init>", "()V", "DisabledContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getDisabledContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "DisabledContainerOpacity", "", "getDisabledContainerOpacity", "()F", "DisabledIconColor", "getDisabledIconColor", "DisabledIconOpacity", "getDisabledIconOpacity", "DisabledLabelColor", "getDisabledLabelColor", "DisabledLabelOpacity", "getDisabledLabelOpacity", "FocusedIconColor", "getFocusedIconColor", "FocusedLabelColor", "getFocusedLabelColor", "HoveredIconColor", "getHoveredIconColor", "HoveredLabelColor", "getHoveredLabelColor", "IconColor", "getIconColor", "LabelColor", "getLabelColor", "PressedIconColor", "getPressedIconColor", "PressedLabelColor", "getPressedLabelColor", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextButtonTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens DisabledIconColor;
    private static final float DisabledIconOpacity;
    private static final ColorSchemeKeyTokens DisabledLabelColor;
    private static final float DisabledLabelOpacity;
    private static final ColorSchemeKeyTokens FocusedIconColor;
    private static final ColorSchemeKeyTokens FocusedLabelColor;
    private static final ColorSchemeKeyTokens HoveredIconColor;
    private static final ColorSchemeKeyTokens HoveredLabelColor;
    private static final ColorSchemeKeyTokens IconColor;
    private static final ColorSchemeKeyTokens LabelColor;
    private static final ColorSchemeKeyTokens PressedIconColor;
    private static final ColorSchemeKeyTokens PressedLabelColor;
    public static final TextButtonTokens INSTANCE = new TextButtonTokens();
    private static final ColorSchemeKeyTokens DisabledContainerColor = ColorSchemeKeyTokens.OnSurface;
    private static final float DisabledContainerOpacity = 0.1f;

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurfaceVariant;
        DisabledIconColor = colorSchemeKeyTokens;
        DisabledIconOpacity = 0.38f;
        DisabledLabelColor = colorSchemeKeyTokens;
        DisabledLabelOpacity = 0.38f;
        FocusedIconColor = colorSchemeKeyTokens;
        FocusedLabelColor = colorSchemeKeyTokens;
        HoveredIconColor = colorSchemeKeyTokens;
        HoveredLabelColor = colorSchemeKeyTokens;
        IconColor = colorSchemeKeyTokens;
        LabelColor = colorSchemeKeyTokens;
        PressedIconColor = colorSchemeKeyTokens;
        PressedLabelColor = colorSchemeKeyTokens;
    }

    private TextButtonTokens() {
    }

    public final ColorSchemeKeyTokens getDisabledContainerColor() {
        return DisabledContainerColor;
    }

    public final float getDisabledContainerOpacity() {
        return DisabledContainerOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledIconColor() {
        return DisabledIconColor;
    }

    public final float getDisabledIconOpacity() {
        return DisabledIconOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledLabelColor() {
        return DisabledLabelColor;
    }

    public final float getDisabledLabelOpacity() {
        return DisabledLabelOpacity;
    }

    public final ColorSchemeKeyTokens getFocusedIconColor() {
        return FocusedIconColor;
    }

    public final ColorSchemeKeyTokens getFocusedLabelColor() {
        return FocusedLabelColor;
    }

    public final ColorSchemeKeyTokens getHoveredIconColor() {
        return HoveredIconColor;
    }

    public final ColorSchemeKeyTokens getHoveredLabelColor() {
        return HoveredLabelColor;
    }

    public final ColorSchemeKeyTokens getIconColor() {
        return IconColor;
    }

    public final ColorSchemeKeyTokens getLabelColor() {
        return LabelColor;
    }

    public final ColorSchemeKeyTokens getPressedIconColor() {
        return PressedIconColor;
    }

    public final ColorSchemeKeyTokens getPressedLabelColor() {
        return PressedLabelColor;
    }
}
