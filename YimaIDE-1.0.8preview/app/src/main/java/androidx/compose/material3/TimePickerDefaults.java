package androidx.compose.material3;

import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006J\u009b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0018\u001a\u00020\u0005*\u00020\u00198@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Landroidx/compose/material3/TimePickerDefaults;", "", "<init>", "()V", "colors", "Landroidx/compose/material3/TimePickerColors;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TimePickerColors;", "clockDialColor", "Landroidx/compose/ui/graphics/Color;", "clockDialSelectedContentColor", "clockDialUnselectedContentColor", "selectorColor", "containerColor", "periodSelectorBorderColor", "periodSelectorSelectedContainerColor", "periodSelectorUnselectedContainerColor", "periodSelectorSelectedContentColor", "periodSelectorUnselectedContentColor", "timeSelectorSelectedContainerColor", "timeSelectorUnselectedContainerColor", "timeSelectorSelectedContentColor", "timeSelectorUnselectedContentColor", "colors-u3YEpmA", "(JJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TimePickerColors;", "defaultTimePickerColors", "Landroidx/compose/material3/ColorScheme;", "getDefaultTimePickerColors$material3", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/TimePickerColors;", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "layoutType-sDNSZnc", "(Landroidx/compose/runtime/Composer;I)I", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerDefaults {
    public static final int $stable = 0;
    public static final TimePickerDefaults INSTANCE = new TimePickerDefaults();

    private TimePickerDefaults() {
    }

    public final TimePickerColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2085808058, i, -1, "androidx.compose.material3.TimePickerDefaults.colors (TimePicker.kt:284)");
        }
        TimePickerColors defaultTimePickerColors$material3 = getDefaultTimePickerColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTimePickerColors$material3;
    }

    /* JADX INFO: renamed from: colors-u3YEpmA, reason: not valid java name */
    public final TimePickerColors m1121colorsu3YEpmA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, Composer composer, int i, int i2, int i3) {
        long jM3170getUnspecified0d7_KjU = (i3 & 1) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j;
        long jM3170getUnspecified0d7_KjU2 = (i3 & 2) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j2;
        long jM3170getUnspecified0d7_KjU3 = (i3 & 4) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j3;
        long jM3170getUnspecified0d7_KjU4 = (i3 & 8) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j4;
        long jM3170getUnspecified0d7_KjU5 = (i3 & 16) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j5;
        long jM3170getUnspecified0d7_KjU6 = (i3 & 32) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j6;
        long jM3170getUnspecified0d7_KjU7 = (i3 & 64) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j7;
        long jM3170getUnspecified0d7_KjU8 = (i3 & 128) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j8;
        long j15 = jM3170getUnspecified0d7_KjU;
        long jM3170getUnspecified0d7_KjU9 = (i3 & 256) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j9;
        long jM3170getUnspecified0d7_KjU10 = (i3 & 512) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j10;
        long jM3170getUnspecified0d7_KjU11 = (i3 & 1024) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j11;
        long jM3170getUnspecified0d7_KjU12 = (i3 & 2048) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j12;
        long jM3170getUnspecified0d7_KjU13 = (i3 & 4096) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j13;
        long jM3170getUnspecified0d7_KjU14 = (i3 & 8192) != 0 ? Color.INSTANCE.m3170getUnspecified0d7_KjU() : j14;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-646352288, i, i2, "androidx.compose.material3.TimePickerDefaults.colors (TimePicker.kt:331)");
        }
        TimePickerColors timePickerColorsM1102copydVHXu7A = getDefaultTimePickerColors$material3(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1102copydVHXu7A(j15, jM3170getUnspecified0d7_KjU4, jM3170getUnspecified0d7_KjU5, jM3170getUnspecified0d7_KjU6, jM3170getUnspecified0d7_KjU2, jM3170getUnspecified0d7_KjU3, jM3170getUnspecified0d7_KjU7, jM3170getUnspecified0d7_KjU8, jM3170getUnspecified0d7_KjU9, jM3170getUnspecified0d7_KjU10, jM3170getUnspecified0d7_KjU11, jM3170getUnspecified0d7_KjU12, jM3170getUnspecified0d7_KjU13, jM3170getUnspecified0d7_KjU14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return timePickerColorsM1102copydVHXu7A;
    }

    public final TimePickerColors getDefaultTimePickerColors$material3(ColorScheme colorScheme) {
        TimePickerColors defaultTimePickerColorsCached = colorScheme.getDefaultTimePickerColorsCached();
        if (defaultTimePickerColorsCached != null) {
            return defaultTimePickerColorsCached;
        }
        TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
        TimePickerColors timePickerColors = new TimePickerColors(ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialSelectorHandleContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorOutlineColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getClockDialUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorSelectedContainerColor()), Color.INSTANCE.m3169getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getPeriodSelectorUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorUnselectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, timePickerTokens.getTimeSelectorUnselectedLabelTextColor()), null);
        colorScheme.setDefaultTimePickerColorsCached$material3(timePickerColors);
        return timePickerColors;
    }

    /* JADX INFO: renamed from: layoutType-sDNSZnc, reason: not valid java name */
    public final int m1122layoutTypesDNSZnc(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(517161502, i, -1, "androidx.compose.material3.TimePickerDefaults.layoutType (TimePicker.kt:381)");
        }
        int defaultTimePickerLayoutType = TimePickerKt.getDefaultTimePickerLayoutType(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTimePickerLayoutType;
    }
}
