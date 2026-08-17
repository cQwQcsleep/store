package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.FilterChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ\u0087\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u001b\u0010\u001cJK\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020\u00052\b\b\u0002\u0010'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u0005H\u0007¢\u0006\u0004\b)\u0010*J[\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\b\b\u0002\u00100\u001a\u00020\u000f2\b\b\u0002\u00101\u001a\u00020\u000f2\b\b\u0002\u00102\u001a\u00020\u000f2\b\b\u0002\u00103\u001a\u00020\u000f2\b\b\u0002\u00104\u001a\u00020\u00052\b\b\u0002\u00105\u001a\u00020\u0005H\u0007¢\u0006\u0004\b6\u00107J\r\u00108\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\rJ\u0087\u0001\u00108\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000fH\u0007¢\u0006\u0004\b9\u0010\u001cJK\u0010<\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020\u00052\b\b\u0002\u0010'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u0005H\u0007¢\u0006\u0004\b=\u0010*R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0018\u0010\u001d\u001a\u00020\f*\u00020\u001e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0018\u0010:\u001a\u00020\f*\u00020\u001e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b;\u0010 R\u0011\u0010>\u001a\u00020?8G¢\u0006\u0006\u001a\u0004\b@\u0010A¨\u0006B"}, d2 = {"Landroidx/compose/material3/FilterChipDefaults;", "", "<init>", "()V", "Height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM", "()F", "F", "IconSize", "getIconSize-D9Ej5fM", "filterChipColors", "Landroidx/compose/material3/SelectableChipColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SelectableChipColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "iconColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "selectedContainerColor", "disabledSelectedContainerColor", "selectedLabelColor", "selectedLeadingIconColor", "selectedTrailingIconColor", "filterChipColors-XqyqHi0", "(JJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SelectableChipColors;", "defaultFilterChipColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultFilterChipColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SelectableChipColors;", "filterChipElevation", "Landroidx/compose/material3/SelectableChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "filterChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SelectableChipElevation;", "filterChipBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "selected", "borderColor", "selectedBorderColor", "disabledBorderColor", "disabledSelectedBorderColor", "borderWidth", "selectedBorderWidth", "filterChipBorder-_7El2pE", "(ZZJJJJFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "elevatedFilterChipColors", "elevatedFilterChipColors-XqyqHi0", "defaultElevatedFilterChipColors", "getDefaultElevatedFilterChipColors$material3", "elevatedFilterChipElevation", "elevatedFilterChipElevation-aqJV_2Y", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FilterChipDefaults {
    public static final int $stable = 0;
    private static final float Height;
    public static final FilterChipDefaults INSTANCE = new FilterChipDefaults();
    private static final float IconSize;

    static {
        FilterChipTokens filterChipTokens = FilterChipTokens.INSTANCE;
        Height = filterChipTokens.m1837getContainerHeightD9Ej5fM();
        IconSize = filterChipTokens.m1853getIconSizeD9Ej5fM();
    }

    private FilterChipDefaults() {
    }

    public final SelectableChipColors elevatedFilterChipColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1082953289, i, -1, "androidx.compose.material3.FilterChipDefaults.elevatedFilterChipColors (Chip.kt:1424)");
        }
        SelectableChipColors defaultElevatedFilterChipColors$material3 = getDefaultElevatedFilterChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultElevatedFilterChipColors$material3;
    }

    /* JADX INFO: renamed from: elevatedFilterChipColors-XqyqHi0, reason: not valid java name */
    public final SelectableChipColors m475elevatedFilterChipColorsXqyqHi0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, Composer composer, int i, int i2, int i3) {
        long jM3170getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        long j13 = jM3170getUnspecified0d7_KjU;
        long jM3170getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j9;
        long jM3170getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j10;
        long jM3170getUnspecified0d7_KjU11 = (i3 & 1024) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j11;
        long jM3170getUnspecified0d7_KjU12 = (i3 & 2048) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j12;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-915841711, i, i2, "androidx.compose.material3.FilterChipDefaults.elevatedFilterChipColors (Chip.kt:1459)");
        }
        SelectableChipColors selectableChipColorsM850copydaRQuJA = getDefaultElevatedFilterChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m850copydaRQuJA(j13, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8, jM3170getUnspecified0d7_KjU9, jM3170getUnspecified0d7_KjU10, jM3170getUnspecified0d7_KjU11, jM3170getUnspecified0d7_KjU12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return selectableChipColorsM850copydaRQuJA;
    }

    /* JADX INFO: renamed from: elevatedFilterChipElevation-aqJV_2Y, reason: not valid java name */
    public final SelectableChipElevation m476elevatedFilterChipElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = FilterChipTokens.INSTANCE.m1839getElevatedContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = FilterChipTokens.INSTANCE.m1843getElevatedPressedContainerElevationD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            f3 = FilterChipTokens.INSTANCE.m1841getElevatedFocusContainerElevationD9Ej5fM();
        }
        if ((i2 & 8) != 0) {
            f4 = FilterChipTokens.INSTANCE.m1842getElevatedHoverContainerElevationD9Ej5fM();
        }
        if ((i2 & 16) != 0) {
            f5 = FilterChipTokens.INSTANCE.m1838getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = FilterChipTokens.INSTANCE.m1840getElevatedDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(684803697, i, -1, "androidx.compose.material3.FilterChipDefaults.elevatedFilterChipElevation (Chip.kt:1530)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        SelectableChipElevation selectableChipElevation = new SelectableChipElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return selectableChipElevation;
    }

    /* JADX INFO: renamed from: filterChipBorder-_7El2pE, reason: not valid java name */
    public final BorderStroke m477filterChipBorder_7El2pE(boolean z, boolean z2, long j, long j2, long j3, long j4, float f, float f2, Composer composer, int i, int i2) {
        long jM3133copywmQWz5c$default;
        long value = (i2 & 4) != 0 ? ColorSchemeKt.getValue(FilterChipTokens.INSTANCE.getFlatUnselectedOutlineColor(), composer, 6) : j;
        long jM3169getTransparent0d7_KjU = (i2 & 8) != 0 ? Color.INSTANCE.m3169getTransparent0d7_KjU() : j2;
        if ((i2 & 16) != 0) {
            FilterChipTokens filterChipTokens = FilterChipTokens.INSTANCE;
            jM3133copywmQWz5c$default = Color.m3133copywmQWz5c$default(ColorSchemeKt.getValue(filterChipTokens.getFlatDisabledUnselectedOutlineColor(), composer, 6), filterChipTokens.getFlatDisabledUnselectedOutlineOpacity(), 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM3133copywmQWz5c$default = j3;
        }
        long jM3169getTransparent0d7_KjU2 = (i2 & 32) != 0 ? Color.INSTANCE.m3169getTransparent0d7_KjU() : j4;
        float fM1851getFlatUnselectedOutlineWidthD9Ej5fM = (i2 & 64) != 0 ? FilterChipTokens.INSTANCE.m1851getFlatUnselectedOutlineWidthD9Ej5fM() : f;
        float fM1847getFlatSelectedOutlineWidthD9Ej5fM = (i2 & 128) != 0 ? FilterChipTokens.INSTANCE.m1847getFlatSelectedOutlineWidthD9Ej5fM() : f2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1138342447, i, -1, "androidx.compose.material3.FilterChipDefaults.filterChipBorder (Chip.kt:1409)");
        }
        if (!z) {
            value = z2 ? jM3169getTransparent0d7_KjU2 : jM3133copywmQWz5c$default;
        } else if (z2) {
            value = jM3169getTransparent0d7_KjU;
        }
        if (z2) {
            fM1851getFlatUnselectedOutlineWidthD9Ej5fM = fM1847getFlatSelectedOutlineWidthD9Ej5fM;
        }
        BorderStroke borderStrokeM12BorderStrokecXLIe8U = BorderStrokeKt.m12BorderStrokecXLIe8U(fM1851getFlatUnselectedOutlineWidthD9Ej5fM, value);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return borderStrokeM12BorderStrokecXLIe8U;
    }

    public final SelectableChipColors filterChipColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1743772077, i, -1, "androidx.compose.material3.FilterChipDefaults.filterChipColors (Chip.kt:1267)");
        }
        SelectableChipColors defaultFilterChipColors$material3 = getDefaultFilterChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultFilterChipColors$material3;
    }

    /* JADX INFO: renamed from: filterChipColors-XqyqHi0, reason: not valid java name */
    public final SelectableChipColors m478filterChipColorsXqyqHi0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, Composer composer, int i, int i2, int i3) {
        long jM3170getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        long j13 = jM3170getUnspecified0d7_KjU;
        long jM3170getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j9;
        long jM3170getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j10;
        long jM3170getUnspecified0d7_KjU11 = (i3 & 1024) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j11;
        long jM3170getUnspecified0d7_KjU12 = (i3 & 2048) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j12;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1831479801, i, i2, "androidx.compose.material3.FilterChipDefaults.filterChipColors (Chip.kt:1302)");
        }
        SelectableChipColors selectableChipColorsM850copydaRQuJA = getDefaultFilterChipColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m850copydaRQuJA(j13, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8, jM3170getUnspecified0d7_KjU9, jM3170getUnspecified0d7_KjU10, jM3170getUnspecified0d7_KjU11, jM3170getUnspecified0d7_KjU12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return selectableChipColorsM850copydaRQuJA;
    }

    /* JADX INFO: renamed from: filterChipElevation-aqJV_2Y, reason: not valid java name */
    public final SelectableChipElevation m479filterChipElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = FilterChipTokens.INSTANCE.m1844getFlatContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = FilterChipTokens.INSTANCE.m1848getFlatSelectedPressedContainerElevationD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            f3 = FilterChipTokens.INSTANCE.m1845getFlatSelectedFocusContainerElevationD9Ej5fM();
        }
        if ((i2 & 8) != 0) {
            f4 = FilterChipTokens.INSTANCE.m1846getFlatSelectedHoverContainerElevationD9Ej5fM();
        }
        if ((i2 & 16) != 0) {
            f5 = FilterChipTokens.INSTANCE.m1838getDraggedContainerElevationD9Ej5fM();
        }
        float f7 = f5;
        if ((i2 & 32) != 0) {
            f6 = f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-757972185, i, -1, "androidx.compose.material3.FilterChipDefaults.filterChipElevation (Chip.kt:1372)");
        }
        float f8 = f6;
        float f9 = f3;
        float f10 = f;
        SelectableChipElevation selectableChipElevation = new SelectableChipElevation(f10, f2, f9, f4, f7, f8, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return selectableChipElevation;
    }

    public final SelectableChipColors getDefaultElevatedFilterChipColors$material3(ColorScheme colorScheme) {
        SelectableChipColors defaultElevatedFilterChipColorsCached = colorScheme.getDefaultElevatedFilterChipColorsCached();
        if (defaultElevatedFilterChipColorsCached != null) {
            return defaultElevatedFilterChipColorsCached;
        }
        FilterChipTokens filterChipTokens = FilterChipTokens.INSTANCE;
        SelectableChipColors selectableChipColors = new SelectableChipColors(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getElevatedUnselectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getUnselectedLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getUnselectedTrailingIconColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getElevatedDisabledContainerColor()), filterChipTokens.getElevatedDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getDisabledLabelTextColor()), filterChipTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getDisabledLeadingIconColor()), filterChipTokens.getDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getDisabledTrailingIconColor()), filterChipTokens.getDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getElevatedSelectedContainerColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getElevatedDisabledContainerColor()), filterChipTokens.getElevatedDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getSelectedLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getSelectedTrailingIconColor()), null);
        colorScheme.setDefaultElevatedFilterChipColorsCached$material3(selectableChipColors);
        return selectableChipColors;
    }

    public final SelectableChipColors getDefaultFilterChipColors$material3(ColorScheme colorScheme) {
        SelectableChipColors defaultFilterChipColorsCached = colorScheme.getDefaultFilterChipColorsCached();
        if (defaultFilterChipColorsCached != null) {
            return defaultFilterChipColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3169getTransparent0d7_KjU = companion.m3169getTransparent0d7_KjU();
        FilterChipTokens filterChipTokens = FilterChipTokens.INSTANCE;
        SelectableChipColors selectableChipColors = new SelectableChipColors(jM3169getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getUnselectedLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getUnselectedTrailingIconColor()), companion.m3169getTransparent0d7_KjU(), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getDisabledLabelTextColor()), filterChipTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getDisabledLeadingIconColor()), filterChipTokens.getDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getDisabledTrailingIconColor()), filterChipTokens.getDisabledTrailingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getFlatSelectedContainerColor()), Color.m3133copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getFlatDisabledSelectedContainerColor()), filterChipTokens.getFlatDisabledSelectedContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getSelectedLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filterChipTokens.getSelectedTrailingIconColor()), null);
        colorScheme.setDefaultFilterChipColorsCached$material3(selectableChipColors);
        return selectableChipColors;
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m480getHeightD9Ej5fM() {
        return Height;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m481getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1598643637, i, -1, "androidx.compose.material3.FilterChipDefaults.<get-shape> (Chip.kt:1541)");
        }
        Shape value = ShapesKt.getValue(FilterChipTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }
}
