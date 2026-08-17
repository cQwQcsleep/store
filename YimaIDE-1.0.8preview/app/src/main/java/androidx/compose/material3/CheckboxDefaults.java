package androidx.compose.material3;

import androidx.compose.material3.tokens.CheckboxTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006JK\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0005*\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\u0015¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/CheckboxDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/CheckboxColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledCheckedColor", "disabledUncheckedColor", "disabledIndeterminateColor", "colors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CheckboxColors;", "defaultCheckboxColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultCheckboxColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/CheckboxColors;", "StrokeWidth", "Landroidx/compose/ui/unit/Dp;", "getStrokeWidth-D9Ej5fM", "()F", "F", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();
    private static final float StrokeWidth = Dp.m6022constructorimpl(2.0f);

    private CheckboxDefaults() {
    }

    public final CheckboxColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-9530498, i, -1, "androidx.compose.material3.CheckboxDefaults.colors (Checkbox.kt:315)");
        }
        CheckboxColors defaultCheckboxColors$material3 = getDefaultCheckboxColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultCheckboxColors$material3;
    }

    /* JADX INFO: renamed from: colors-5tl4gsc, reason: not valid java name */
    public final CheckboxColors m176colors5tl4gsc(long j, long j2, long j3, long j4, long j5, long j6, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i2 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-89536160, i, -1, "androidx.compose.material3.CheckboxDefaults.colors (Checkbox.kt:341)");
        }
        CheckboxColors defaultCheckboxColors$material3 = getDefaultCheckboxColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        Color.Companion companion = Color.INSTANCE;
        CheckboxColors checkboxColorsM163copy2qZNXz8 = defaultCheckboxColors$material3.m163copy2qZNXz8(jM3170getUnspecified0d7_KjU3, companion.m3169getTransparent0d7_KjU(), jM3170getUnspecified0d7_KjU, companion.m3169getTransparent0d7_KjU(), jM3170getUnspecified0d7_KjU4, companion.m3169getTransparent0d7_KjU(), jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return checkboxColorsM163copy2qZNXz8;
    }

    public final CheckboxColors getDefaultCheckboxColors$material3(ColorScheme colorScheme) {
        CheckboxColors defaultCheckboxColorsCached = colorScheme.getDefaultCheckboxColorsCached();
        if (defaultCheckboxColorsCached != null) {
            return defaultCheckboxColorsCached;
        }
        CheckboxTokens checkboxTokens = CheckboxTokens.INSTANCE;
        long jFromToken = ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedIconColor());
        Color.Companion companion = Color.INSTANCE;
        CheckboxColors checkboxColors = new CheckboxColors(jFromToken, companion.m3169getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedContainerColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedDisabledContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedDisabledContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getUnselectedOutlineColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedDisabledContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getUnselectedDisabledOutlineColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, checkboxTokens.getSelectedDisabledContainerColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultCheckboxColorsCached$material3(checkboxColors);
        return checkboxColors;
    }

    /* JADX INFO: renamed from: getStrokeWidth-D9Ej5fM, reason: not valid java name */
    public final float m177getStrokeWidthD9Ej5fM() {
        return StrokeWidth;
    }
}
