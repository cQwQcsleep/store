package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.AssistChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ_\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0017\u0010\u0018JK\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007¢\u0006\u0004\b%\u0010&J5\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020\u000f2\b\b\u0002\u0010,\u001a\u00020\u000f2\b\b\u0002\u0010-\u001a\u00020\u0005H\u0007¢\u0006\u0004\b.\u0010/J-\u0010'\u001a\u0002002\b\b\u0002\u0010+\u001a\u00020\u000f2\b\b\u0002\u0010,\u001a\u00020\u000f2\b\b\u0002\u0010-\u001a\u00020\u0005H\u0007¢\u0006\u0004\b1\u00102J\r\u00103\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ_\u00103\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000fH\u0007¢\u0006\u0004\b4\u0010\u0018JK\u00107\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u0005H\u0007¢\u0006\u0004\b8\u0010&R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0018\u0010\u0019\u001a\u00020\f*\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u00105\u001a\u00020\f*\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u001cR\u0011\u00109\u001a\u00020:8G¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006="}, d2 = {"Landroidx/compose/material3/AssistChipDefaults;", "", "<init>", "()V", "Height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM", "()F", "F", "IconSize", "getIconSize-D9Ej5fM", "assistChipColors", "Landroidx/compose/material3/ChipColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ChipColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "leadingIconContentColor", "trailingIconContentColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconContentColor", "disabledTrailingIconContentColor", "assistChipColors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipColors;", "defaultAssistChipColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultAssistChipColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "assistChipElevation", "Landroidx/compose/material3/ChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "assistChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipElevation;", "assistChipBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "borderColor", "disabledBorderColor", "borderWidth", "assistChipBorder-h1eT-Ww", "(ZJJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "Landroidx/compose/material3/ChipBorder;", "assistChipBorder-d_3_b6Q", "(JJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipBorder;", "elevatedAssistChipColors", "elevatedAssistChipColors-oq7We08", "defaultElevatedAssistChipColors", "getDefaultElevatedAssistChipColors$material3", "elevatedAssistChipElevation", "elevatedAssistChipElevation-aqJV_2Y", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AssistChipDefaults {
    public static final int $stable = 0;
    private static final float Height;
    public static final AssistChipDefaults INSTANCE = new AssistChipDefaults();
    private static final float IconSize;

    static {
        AssistChipTokens assistChipTokens = AssistChipTokens.INSTANCE;
        Height = assistChipTokens.m1509getContainerHeightD9Ej5fM();
        IconSize = assistChipTokens.m1518getIconSizeD9Ej5fM();
    }

    private AssistChipDefaults() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Maintained for binary compatibility. Use the assistChipBorder function that returns BorderStroke instead", replaceWith = @ReplaceWith(expression = "assistChipBorder(enabled, borderColor, disabledBorderColor, borderWidth)", imports = {}))
    /* JADX INFO: renamed from: assistChipBorder-d_3_b6Q, reason: not valid java name */
    public final ChipBorder m107assistChipBorderd_3_b6Q(long j, long j2, float f, Composer composer, int i, int i2) {
        long jM3133copywmQWz5c$default;
        long value = (i2 & 1) != 0 ? ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatOutlineColor(), composer, 6) : j;
        if ((i2 & 2) != 0) {
            AssistChipTokens assistChipTokens = AssistChipTokens.INSTANCE;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(assistChipTokens.getFlatDisabledOutlineColor(), composer, 6), assistChipTokens.getFlatDisabledOutlineOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM3133copywmQWz5c$default = j2;
        }
        float fM1517getFlatOutlineWidthD9Ej5fM = (i2 & 4) != 0 ? AssistChipTokens.INSTANCE.m1517getFlatOutlineWidthD9Ej5fM() : f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(382372847, i, -1, "androidx.compose.material3.AssistChipDefaults.assistChipBorder (Chip.kt:1146)");
        }
        ChipBorder chipBorder = new ChipBorder(value, jM3133copywmQWz5c$default, fM1517getFlatOutlineWidthD9Ej5fM, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return chipBorder;
    }

    /* JADX INFO: renamed from: assistChipBorder-h1eT-Ww, reason: not valid java name */
    public final BorderStroke m108assistChipBorderh1eTWw(boolean z, long j, long j2, float f, Composer composer, int i, int i2) {
        long jM3133copywmQWz5c$default;
        long value = (i2 & 2) != 0 ? ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatOutlineColor(), composer, 6) : j;
        if ((i2 & 4) != 0) {
            AssistChipTokens assistChipTokens = AssistChipTokens.INSTANCE;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(assistChipTokens.getFlatDisabledOutlineColor(), composer, 6), assistChipTokens.getFlatDisabledOutlineOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM3133copywmQWz5c$default = j2;
        }
        float fM1517getFlatOutlineWidthD9Ej5fM = (i2 & 8) != 0 ? AssistChipTokens.INSTANCE.m1517getFlatOutlineWidthD9Ej5fM() : f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1458649561, i, -1, "androidx.compose.material3.AssistChipDefaults.assistChipBorder (Chip.kt:1118)");
        }
        if (!z) {
            value = jM3133copywmQWz5c$default;
        }
        BorderStroke borderStrokeM12BorderStrokecXLIe8U = BorderStrokeKt.m12BorderStrokecXLIe8U(fM1517getFlatOutlineWidthD9Ej5fM, value);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return borderStrokeM12BorderStrokecXLIe8U;
    }

    public final ChipColors assistChipColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1961061417, i, -1, "androidx.compose.material3.AssistChipDefaults.assistChipColors (Chip.kt:1013)");
        }
        ChipColors defaultAssistChipColors$material3 = getDefaultAssistChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultAssistChipColors$material3;
    }

    /* JADX INFO: renamed from: assistChipColors-oq7We08, reason: not valid java name */
    public final ChipColors m109assistChipColorsoq7We08(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i2 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i2 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i2 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-391745725, i, -1, "androidx.compose.material3.AssistChipDefaults.assistChipColors (Chip.kt:1039)");
        }
        ChipColors chipColorsM182copyFD3wquc = getDefaultAssistChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m182copyFD3wquc(jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return chipColorsM182copyFD3wquc;
    }

    /* JADX INFO: renamed from: assistChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m110assistChipElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = AssistChipTokens.INSTANCE.m1516getFlatContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = f;
        }
        if ((i2 & 4) != 0) {
            f3 = f;
        }
        if ((i2 & 8) != 0) {
            f4 = f;
        }
        if ((i2 & 16) != 0) {
            f5 = AssistChipTokens.INSTANCE.m1510getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(245366099, i, -1, "androidx.compose.material3.AssistChipDefaults.assistChipElevation (Chip.kt:1092)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        ChipElevation chipElevation = new ChipElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return chipElevation;
    }

    public final ChipColors elevatedAssistChipColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(655175583, i, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipColors (Chip.kt:1157)");
        }
        ChipColors defaultElevatedAssistChipColors$material3 = getDefaultElevatedAssistChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultElevatedAssistChipColors$material3;
    }

    /* JADX INFO: renamed from: elevatedAssistChipColors-oq7We08, reason: not valid java name */
    public final ChipColors m111elevatedAssistChipColorsoq7We08(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, Composer composer, int i, int i2) {
        long jM3170getUnspecified0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i2 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i2 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i2 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i2 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i2 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i2 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i2 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-535762675, i, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipColors (Chip.kt:1183)");
        }
        ChipColors chipColorsM182copyFD3wquc = SuggestionChipDefaults.INSTANCE.getDefaultElevatedSuggestionChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m182copyFD3wquc(jM3170getUnspecified0d7_KjU, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return chipColorsM182copyFD3wquc;
    }

    /* JADX INFO: renamed from: elevatedAssistChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m112elevatedAssistChipElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = AssistChipTokens.INSTANCE.m1511getElevatedContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = AssistChipTokens.INSTANCE.m1515getElevatedPressedContainerElevationD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            f3 = AssistChipTokens.INSTANCE.m1513getElevatedFocusContainerElevationD9Ej5fM();
        }
        if ((i2 & 8) != 0) {
            f4 = AssistChipTokens.INSTANCE.m1514getElevatedHoverContainerElevationD9Ej5fM();
        }
        if ((i2 & 16) != 0) {
            f5 = AssistChipTokens.INSTANCE.m1510getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = AssistChipTokens.INSTANCE.m1512getElevatedDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1457698077, i, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipElevation (Chip.kt:1238)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        ChipElevation chipElevation = new ChipElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return chipElevation;
    }

    public final ChipColors getDefaultAssistChipColors$material3(ColorScheme colorScheme) {
        ChipColors defaultAssistChipColorsCached = colorScheme.getDefaultAssistChipColorsCached();
        if (defaultAssistChipColorsCached != null) {
            return defaultAssistChipColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        AssistChipTokens assistChipTokens = AssistChipTokens.INSTANCE;
        ChipColors chipColors = new ChipColors(jM3169getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getIconColor()), ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getIconColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getDisabledLabelTextColor()), assistChipTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getDisabledIconColor()), assistChipTokens.getDisabledIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getDisabledIconColor()), assistChipTokens.getDisabledIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultAssistChipColorsCached$material3(chipColors);
        return chipColors;
    }

    public final ChipColors getDefaultElevatedAssistChipColors$material3(ColorScheme colorScheme) {
        ChipColors defaultElevatedAssistChipColorsCached = colorScheme.getDefaultElevatedAssistChipColorsCached();
        if (defaultElevatedAssistChipColorsCached != null) {
            return defaultElevatedAssistChipColorsCached;
        }
        AssistChipTokens assistChipTokens = AssistChipTokens.INSTANCE;
        ChipColors chipColors = new ChipColors(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getElevatedContainerColor()), ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getIconColor()), ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getIconColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getElevatedDisabledContainerColor()), assistChipTokens.getElevatedDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getDisabledLabelTextColor()), assistChipTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getDisabledIconColor()), assistChipTokens.getDisabledIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, assistChipTokens.getDisabledIconColor()), assistChipTokens.getDisabledIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultElevatedAssistChipColorsCached$material3(chipColors);
        return chipColors;
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m113getHeightD9Ej5fM() {
        return Height;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m114getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1988153916, i, -1, "androidx.compose.material3.AssistChipDefaults.<get-shape> (Chip.kt:1249)");
        }
        Shape value = ShapesKt.getValue(AssistChipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }
}
