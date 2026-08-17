package androidx.compose.material3.tokens;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/tokens/NavigationRailColorTokens;", "", "<init>", "()V", "ItemActiveFocusedStateLayer", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getItemActiveFocusedStateLayer", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ItemActiveHoveredStateLayer", "getItemActiveHoveredStateLayer", "ItemActiveIcon", "getItemActiveIcon", "ItemActiveIndicator", "getItemActiveIndicator", "ItemActiveLabelText", "getItemActiveLabelText", "ItemActivePressedStateLayer", "getItemActivePressedStateLayer", "ItemInactiveFocusedStateLayer", "getItemInactiveFocusedStateLayer", "ItemInactiveHoveredStateLayer", "getItemInactiveHoveredStateLayer", "ItemInactiveIcon", "getItemInactiveIcon", "ItemInactiveLabelText", "getItemInactiveLabelText", "ItemInactivePressedStateLayer", "getItemInactivePressedStateLayer", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationRailColorTokens {
    public static final int $stable = 0;
    public static final NavigationRailColorTokens INSTANCE = new NavigationRailColorTokens();
    private static final ColorSchemeKeyTokens ItemActiveFocusedStateLayer;
    private static final ColorSchemeKeyTokens ItemActiveHoveredStateLayer;
    private static final ColorSchemeKeyTokens ItemActiveIcon;
    private static final ColorSchemeKeyTokens ItemActiveIndicator;
    private static final ColorSchemeKeyTokens ItemActiveLabelText;
    private static final ColorSchemeKeyTokens ItemActivePressedStateLayer;
    private static final ColorSchemeKeyTokens ItemInactiveFocusedStateLayer;
    private static final ColorSchemeKeyTokens ItemInactiveHoveredStateLayer;
    private static final ColorSchemeKeyTokens ItemInactiveIcon;
    private static final ColorSchemeKeyTokens ItemInactiveLabelText;
    private static final ColorSchemeKeyTokens ItemInactivePressedStateLayer;

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSecondaryContainer;
        ItemActiveFocusedStateLayer = colorSchemeKeyTokens;
        ItemActiveHoveredStateLayer = colorSchemeKeyTokens;
        ItemActiveIcon = colorSchemeKeyTokens;
        ItemActiveIndicator = ColorSchemeKeyTokens.SecondaryContainer;
        ItemActiveLabelText = ColorSchemeKeyTokens.Secondary;
        ItemActivePressedStateLayer = colorSchemeKeyTokens;
        ItemInactiveFocusedStateLayer = colorSchemeKeyTokens;
        ItemInactiveHoveredStateLayer = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        ItemInactiveIcon = colorSchemeKeyTokens2;
        ItemInactiveLabelText = colorSchemeKeyTokens2;
        ItemInactivePressedStateLayer = colorSchemeKeyTokens;
    }

    private NavigationRailColorTokens() {
    }

    public final ColorSchemeKeyTokens getItemActiveFocusedStateLayer() {
        return ItemActiveFocusedStateLayer;
    }

    public final ColorSchemeKeyTokens getItemActiveHoveredStateLayer() {
        return ItemActiveHoveredStateLayer;
    }

    public final ColorSchemeKeyTokens getItemActiveIcon() {
        return ItemActiveIcon;
    }

    public final ColorSchemeKeyTokens getItemActiveIndicator() {
        return ItemActiveIndicator;
    }

    public final ColorSchemeKeyTokens getItemActiveLabelText() {
        return ItemActiveLabelText;
    }

    public final ColorSchemeKeyTokens getItemActivePressedStateLayer() {
        return ItemActivePressedStateLayer;
    }

    public final ColorSchemeKeyTokens getItemInactiveFocusedStateLayer() {
        return ItemInactiveFocusedStateLayer;
    }

    public final ColorSchemeKeyTokens getItemInactiveHoveredStateLayer() {
        return ItemInactiveHoveredStateLayer;
    }

    public final ColorSchemeKeyTokens getItemInactiveIcon() {
        return ItemInactiveIcon;
    }

    public final ColorSchemeKeyTokens getItemInactiveLabelText() {
        return ItemInactiveLabelText;
    }

    public final ColorSchemeKeyTokens getItemInactivePressedStateLayer() {
        return ItemInactivePressedStateLayer;
    }
}
