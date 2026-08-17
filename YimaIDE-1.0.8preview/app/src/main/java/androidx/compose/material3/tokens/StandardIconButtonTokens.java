package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u001b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007¨\u0006$"}, d2 = {"Landroidx/compose/material3/tokens/StandardIconButtonTokens;", "", "<init>", "()V", "DisabledColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getDisabledColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "DisabledOpacity", "", "getDisabledOpacity", "()F", "FocusedColor", "getFocusedColor", "HoveredColor", "getHoveredColor", "Color", "getColor", "PressedColor", "getPressedColor", "SelectedFocusedColor", "getSelectedFocusedColor", "SelectedHoveredColor", "getSelectedHoveredColor", "SelectedColor", "getSelectedColor", "SelectedPressedColor", "getSelectedPressedColor", "UnselectedFocusedColor", "getUnselectedFocusedColor", "UnselectedHoveredColor", "getUnselectedHoveredColor", "UnselectedColor", "getUnselectedColor", "UnselectedPressedColor", "getUnselectedPressedColor", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class StandardIconButtonTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens Color;
    private static final ColorSchemeKeyTokens FocusedColor;
    private static final ColorSchemeKeyTokens HoveredColor;
    private static final ColorSchemeKeyTokens PressedColor;
    private static final ColorSchemeKeyTokens SelectedColor;
    private static final ColorSchemeKeyTokens SelectedFocusedColor;
    private static final ColorSchemeKeyTokens SelectedHoveredColor;
    private static final ColorSchemeKeyTokens SelectedPressedColor;
    private static final ColorSchemeKeyTokens UnselectedColor;
    private static final ColorSchemeKeyTokens UnselectedFocusedColor;
    private static final ColorSchemeKeyTokens UnselectedHoveredColor;
    private static final ColorSchemeKeyTokens UnselectedPressedColor;
    public static final StandardIconButtonTokens INSTANCE = new StandardIconButtonTokens();
    private static final ColorSchemeKeyTokens DisabledColor = ColorSchemeKeyTokens.OnSurface;
    private static final float DisabledOpacity = 0.38f;

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurfaceVariant;
        FocusedColor = colorSchemeKeyTokens;
        HoveredColor = colorSchemeKeyTokens;
        Color = colorSchemeKeyTokens;
        PressedColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.Primary;
        SelectedFocusedColor = colorSchemeKeyTokens2;
        SelectedHoveredColor = colorSchemeKeyTokens2;
        SelectedColor = colorSchemeKeyTokens2;
        SelectedPressedColor = colorSchemeKeyTokens2;
        UnselectedFocusedColor = colorSchemeKeyTokens;
        UnselectedHoveredColor = colorSchemeKeyTokens;
        UnselectedColor = colorSchemeKeyTokens;
        UnselectedPressedColor = colorSchemeKeyTokens;
    }

    private StandardIconButtonTokens() {
    }

    public final ColorSchemeKeyTokens getColor() {
        return Color;
    }

    public final ColorSchemeKeyTokens getDisabledColor() {
        return DisabledColor;
    }

    public final float getDisabledOpacity() {
        return DisabledOpacity;
    }

    public final ColorSchemeKeyTokens getFocusedColor() {
        return FocusedColor;
    }

    public final ColorSchemeKeyTokens getHoveredColor() {
        return HoveredColor;
    }

    public final ColorSchemeKeyTokens getPressedColor() {
        return PressedColor;
    }

    public final ColorSchemeKeyTokens getSelectedColor() {
        return SelectedColor;
    }

    public final ColorSchemeKeyTokens getSelectedFocusedColor() {
        return SelectedFocusedColor;
    }

    public final ColorSchemeKeyTokens getSelectedHoveredColor() {
        return SelectedHoveredColor;
    }

    public final ColorSchemeKeyTokens getSelectedPressedColor() {
        return SelectedPressedColor;
    }

    public final ColorSchemeKeyTokens getUnselectedColor() {
        return UnselectedColor;
    }

    public final ColorSchemeKeyTokens getUnselectedFocusedColor() {
        return UnselectedFocusedColor;
    }

    public final ColorSchemeKeyTokens getUnselectedHoveredColor() {
        return UnselectedHoveredColor;
    }

    public final ColorSchemeKeyTokens getUnselectedPressedColor() {
        return UnselectedPressedColor;
    }
}
