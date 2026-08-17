package androidx.compose.material3;

import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JU\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u00020\u0005*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/ShortNavigationBarItemDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/NavigationItemColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/NavigationItemColors;", "selectedIconColor", "Landroidx/compose/ui/graphics/Color;", "selectedTextColor", "selectedIndicatorColor", "unselectedIconColor", "unselectedTextColor", "disabledIconColor", "disabledTextColor", "colors-69fazGs", "(JJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationItemColors;", "defaultShortNavigationBarItemColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultShortNavigationBarItemColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/NavigationItemColors;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ShortNavigationBarItemDefaults {
    public static final int $stable = 0;
    public static final ShortNavigationBarItemDefaults INSTANCE = new ShortNavigationBarItemDefaults();

    private ShortNavigationBarItemDefaults() {
    }

    public final NavigationItemColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(954437293, i, -1, "androidx.compose.material3.ShortNavigationBarItemDefaults.colors (ShortNavigationBar.kt:287)");
        }
        NavigationItemColors defaultShortNavigationBarItemColors$material3 = getDefaultShortNavigationBarItemColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultShortNavigationBarItemColors$material3;
    }

    /* JADX INFO: renamed from: colors-69fazGs, reason: not valid java name */
    public final NavigationItemColors m874colors69fazGs(long j, long j2, long j3, long j4, long j5, long j6, long j7, Composer composer, int i, int i2) {
        long j8;
        long jM3133copywmQWz5c$default;
        long j9;
        long jM3133copywmQWz5c$default2;
        long value = (i2 & 1) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIconColor(), composer, 6) : j;
        long value2 = (i2 & 2) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveLabelTextColor(), composer, 6) : j2;
        long value3 = (i2 & 4) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorColor(), composer, 6) : j3;
        long value4 = (i2 & 8) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemInactiveIconColor(), composer, 6) : j4;
        long value5 = (i2 & 16) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getItemInactiveLabelTextColor(), composer, 6) : j5;
        if ((i2 & 32) != 0) {
            long j10 = value4;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(j10, 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            j8 = j10;
        } else {
            j8 = value4;
            jM3133copywmQWz5c$default = j6;
        }
        if ((i2 & 64) != 0) {
            long j11 = value5;
            jM3133copywmQWz5c$default2 = Color.m3133copywmQWz5c$default(j11, 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            j9 = j11;
        } else {
            j9 = value5;
            jM3133copywmQWz5c$default2 = j7;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1801697574, i, -1, "androidx.compose.material3.ShortNavigationBarItemDefaults.colors (ShortNavigationBar.kt:312)");
        }
        NavigationItemColors navigationItemColorsM672copy4JmcsL4 = getDefaultShortNavigationBarItemColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m672copy4JmcsL4(value, value2, value3, j8, j9, jM3133copywmQWz5c$default, jM3133copywmQWz5c$default2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return navigationItemColorsM672copy4JmcsL4;
    }

    public final NavigationItemColors getDefaultShortNavigationBarItemColors$material3(ColorScheme colorScheme) {
        NavigationItemColors defaultShortNavigationBarItemColorsCached = colorScheme.getDefaultShortNavigationBarItemColorsCached();
        if (defaultShortNavigationBarItemColorsCached != null) {
            return defaultShortNavigationBarItemColorsCached;
        }
        NavigationBarTokens navigationBarTokens = NavigationBarTokens.INSTANCE;
        NavigationItemColors navigationItemColors = new NavigationItemColors(ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemActiveIconColor()), ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemActiveLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemActiveIndicatorColor()), ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemInactiveIconColor()), ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemInactiveLabelTextColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemInactiveIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, navigationBarTokens.getItemInactiveLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultShortNavigationBarItemColorsCached$material3(navigationItemColors);
        return navigationItemColors;
    }
}
