package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.NavigationRailCollapsedTokens;
import androidx.compose.material3.tokens.NavigationRailExpandedTokens;
import androidx.compose.material3.tokens.ScrimTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010\u001aJA\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u001cH\u0007¢\u0006\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001b\u001a\u00020\u001c8CX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010\u001e\u001a\u00020\u001c8CX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010$R\u0018\u0010&\u001a\u00020\u0019*\u00020'8CX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Landroidx/compose/material3/WideNavigationRailDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "arrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "modalCollapsedShape", "getModalCollapsedShape", "modalExpandedShape", "getModalExpandedShape", "ModalExpandedProperties", "Landroidx/compose/material3/ModalWideNavigationRailProperties;", "getModalExpandedProperties", "()Landroidx/compose/material3/ModalWideNavigationRailProperties;", "colors", "Landroidx/compose/material3/WideNavigationRailColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/WideNavigationRailColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "modalContainerColor", "modalScrimColor", "modalContentColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/WideNavigationRailColors;", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "getModalContainerColor", "defaultWideWideNavigationRailColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultWideWideNavigationRailColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/WideNavigationRailColors;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailDefaults {
    public static final int $stable = 0;
    public static final WideNavigationRailDefaults INSTANCE = new WideNavigationRailDefaults();
    private static final ModalWideNavigationRailProperties ModalExpandedProperties = WideNavigationRail_androidKt.createDefaultModalWideNavigationRailProperties();

    private WideNavigationRailDefaults() {
    }

    private final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2034736487, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-containerColor> (WideNavigationRail.kt:855)");
        }
        long value = ColorSchemeKt.getValue(NavigationRailCollapsedTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    private final WideNavigationRailColors getDefaultWideWideNavigationRailColors(ColorScheme colorScheme, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1969597361, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-defaultWideWideNavigationRailColors> (WideNavigationRail.kt:862)");
        }
        WideNavigationRailColors defaultWideWideNavigationRailColorsCached = colorScheme.getDefaultWideWideNavigationRailColorsCached();
        if (defaultWideWideNavigationRailColorsCached == null) {
            composer.startReplaceGroup(1297532678);
            int i2 = (i >> 3) & 14;
            defaultWideWideNavigationRailColorsCached = new WideNavigationRailColors(getContainerColor(composer, i2), ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, getContainerColor(composer, i2)), getModalContainerColor(composer, i2), Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(ScrimTokens.INSTANCE.getContainerColor(), composer, 6), 0.32f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.m277contentColorFor4WTKRHQ(colorScheme, getModalContainerColor(composer, i2)), null);
            colorScheme.setDefaultWideWideNavigationRailColorsCached$material3(defaultWideWideNavigationRailColorsCached);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1297515721);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultWideWideNavigationRailColorsCached;
    }

    private final long getModalContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1008951947, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-modalContainerColor> (WideNavigationRail.kt:858)");
        }
        long value = ColorSchemeKt.getValue(NavigationRailExpandedTokens.INSTANCE.getModalContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final WideNavigationRailColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(255272712, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.colors (WideNavigationRail.kt:820)");
        }
        WideNavigationRailColors defaultWideWideNavigationRailColors = getDefaultWideWideNavigationRailColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultWideWideNavigationRailColors;
    }

    /* JADX INFO: renamed from: colors-zjMxDiM, reason: not valid java name */
    public final WideNavigationRailColors m1333colorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        long containerColor = (i2 & 1) != 0 ? INSTANCE.getContainerColor(composer, 6) : j;
        long jM278contentColorForek8zF_U = (i2 & 2) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composer, i & 14) : j2;
        long value = (i2 & 4) != 0 ? ColorSchemeKt.getValue(NavigationRailExpandedTokens.INSTANCE.getModalContainerColor(), composer, 6) : j3;
        long jM3133copywmQWz5c$default = (i2 & 8) != 0 ? Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(ScrimTokens.INSTANCE.getContainerColor(), composer, 6), 0.32f, 0.0f, 0.0f, 0.0f, 14, null) : j4;
        long jM278contentColorForek8zF_U2 = (i2 & 16) != 0 ? ColorSchemeKt.m278contentColorForek8zF_U(value, composer, (i >> 6) & 14) : j5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(239918099, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.colors (WideNavigationRail.kt:846)");
        }
        WideNavigationRailColors wideNavigationRailColorsM1327copyt635Npw = getDefaultWideWideNavigationRailColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i >> 12) & 112).m1327copyt635Npw(containerColor, jM278contentColorForek8zF_U, value, jM3133copywmQWz5c$default, jM278contentColorForek8zF_U2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return wideNavigationRailColorsM1327copyt635Npw;
    }

    public final Arrangement.Vertical getArrangement() {
        return Arrangement.INSTANCE.getTop();
    }

    public final Shape getModalCollapsedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(926228775, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-modalCollapsedShape> (WideNavigationRail.kt:806)");
        }
        Shape shape = getShape(composer, i & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return shape;
    }

    public final ModalWideNavigationRailProperties getModalExpandedProperties() {
        return ModalExpandedProperties;
    }

    public final Shape getModalExpandedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1865689797, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-modalExpandedShape> (WideNavigationRail.kt:810)");
        }
        Shape value = ShapesKt.getValue(NavigationRailExpandedTokens.INSTANCE.getModalContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1756160851, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-shape> (WideNavigationRail.kt:790)");
        }
        Shape value = ShapesKt.getValue(NavigationRailCollapsedTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1769402286, i, -1, "androidx.compose.material3.WideNavigationRailDefaults.<get-windowInsets> (WideNavigationRail.kt:800)");
        }
        WindowInsets systemBarsForVisualComponents = SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.Companion, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.Companion;
        WindowInsets windowInsets = WindowInsetsKt.only-bOOhFvg(systemBarsForVisualComponents, WindowInsetsSides.plus-gK_yJZ4(companion.getVertical-JoeWqyM(), companion.getStart-JoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return windowInsets;
    }
}
