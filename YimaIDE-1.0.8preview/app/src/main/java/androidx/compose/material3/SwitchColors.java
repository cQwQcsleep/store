package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u00ad\u0001\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b(\u0010)J\u001f\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b1\u0010/J\u001f\u00102\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b3\u0010/J\u001f\u00104\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b5\u0010/J\u0013\u00106\u001a\u00020,2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00108\u001a\u000209H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0016R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001a\u0010\u0016R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001c\u0010\u0016R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001f\u0010\u0016R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b \u0010\u0016R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b!\u0010\u0016R\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\"\u0010\u0016R\u0013\u0010\u000f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b#\u0010\u0016R\u0013\u0010\u0010\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b$\u0010\u0016R\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b%\u0010\u0016R\u0013\u0010\u0012\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b&\u0010\u0016¨\u0006:"}, d2 = {"Landroidx/compose/material3/SwitchColors;", "", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedBorderColor", "checkedIconColor", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedBorderColor", "uncheckedIconColor", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledCheckedBorderColor", "disabledCheckedIconColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "disabledUncheckedBorderColor", "disabledUncheckedIconColor", "<init>", "(JJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCheckedThumbColor-0d7_KjU", "()J", "J", "getCheckedTrackColor-0d7_KjU", "getCheckedBorderColor-0d7_KjU", "getCheckedIconColor-0d7_KjU", "getUncheckedThumbColor-0d7_KjU", "getUncheckedTrackColor-0d7_KjU", "getUncheckedBorderColor-0d7_KjU", "getUncheckedIconColor-0d7_KjU", "getDisabledCheckedThumbColor-0d7_KjU", "getDisabledCheckedTrackColor-0d7_KjU", "getDisabledCheckedBorderColor-0d7_KjU", "getDisabledCheckedIconColor-0d7_KjU", "getDisabledUncheckedThumbColor-0d7_KjU", "getDisabledUncheckedTrackColor-0d7_KjU", "getDisabledUncheckedBorderColor-0d7_KjU", "getDisabledUncheckedIconColor-0d7_KjU", "copy", "copy-Q_H9qLU", "(JJJJJJJJJJJJJJJJ)Landroidx/compose/material3/SwitchColors;", "thumbColor", "enabled", "", "checked", "thumbColor-WaAFU9c$material3", "(ZZ)J", "trackColor", "trackColor-WaAFU9c$material3", "borderColor", "borderColor-WaAFU9c$material3", "iconColor", "iconColor-WaAFU9c$material3", "equals", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SwitchColors {
    public static final int $stable = 0;
    private final long checkedBorderColor;
    private final long checkedIconColor;
    private final long checkedThumbColor;
    private final long checkedTrackColor;
    private final long disabledCheckedBorderColor;
    private final long disabledCheckedIconColor;
    private final long disabledCheckedThumbColor;
    private final long disabledCheckedTrackColor;
    private final long disabledUncheckedBorderColor;
    private final long disabledUncheckedIconColor;
    private final long disabledUncheckedThumbColor;
    private final long disabledUncheckedTrackColor;
    private final long uncheckedBorderColor;
    private final long uncheckedIconColor;
    private final long uncheckedThumbColor;
    private final long uncheckedTrackColor;

    private SwitchColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16) {
        this.checkedThumbColor = j;
        this.checkedTrackColor = j2;
        this.checkedBorderColor = j3;
        this.checkedIconColor = j4;
        this.uncheckedThumbColor = j5;
        this.uncheckedTrackColor = j6;
        this.uncheckedBorderColor = j7;
        this.uncheckedIconColor = j8;
        this.disabledCheckedThumbColor = j9;
        this.disabledCheckedTrackColor = j10;
        this.disabledCheckedBorderColor = j11;
        this.disabledCheckedIconColor = j12;
        this.disabledUncheckedThumbColor = j13;
        this.disabledUncheckedTrackColor = j14;
        this.disabledUncheckedBorderColor = j15;
        this.disabledUncheckedIconColor = j16;
    }

    /* JADX INFO: renamed from: copy-Q_H9qLU$default, reason: not valid java name */
    public static /* synthetic */ SwitchColors m962copyQ_H9qLU$default(SwitchColors switchColors, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, int i, Object obj) {
        long j17;
        long j18;
        long j19 = (i & 1) != 0 ? switchColors.checkedThumbColor : j;
        long j20 = (i & 2) != 0 ? switchColors.checkedTrackColor : j2;
        long j21 = (i & 4) != 0 ? switchColors.checkedBorderColor : j3;
        long j22 = (i & 8) != 0 ? switchColors.checkedIconColor : j4;
        long j23 = (i & 16) != 0 ? switchColors.uncheckedThumbColor : j5;
        long j24 = (i & 32) != 0 ? switchColors.uncheckedTrackColor : j6;
        long j25 = (i & 64) != 0 ? switchColors.uncheckedBorderColor : j7;
        long j26 = j19;
        long j27 = (i & 128) != 0 ? switchColors.uncheckedIconColor : j8;
        long j28 = (i & 256) != 0 ? switchColors.disabledCheckedThumbColor : j9;
        long j29 = (i & 512) != 0 ? switchColors.disabledCheckedTrackColor : j10;
        long j30 = (i & 1024) != 0 ? switchColors.disabledCheckedBorderColor : j11;
        long j31 = (i & 2048) != 0 ? switchColors.disabledCheckedIconColor : j12;
        long j32 = (i & 4096) != 0 ? switchColors.disabledUncheckedThumbColor : j13;
        long j33 = (i & 8192) != 0 ? switchColors.disabledUncheckedTrackColor : j14;
        long j34 = (i & 16384) != 0 ? switchColors.disabledUncheckedBorderColor : j15;
        if ((i & 32768) != 0) {
            j18 = j34;
            j17 = switchColors.disabledUncheckedIconColor;
        } else {
            j17 = j16;
            j18 = j34;
        }
        return switchColors.m964copyQ_H9qLU(j26, j20, j21, j22, j23, j24, j25, j27, j28, j29, j30, j31, j32, j33, j18, j17);
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3, reason: not valid java name */
    public final long m963borderColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedBorderColor : this.uncheckedBorderColor;
        }
        return checked ? this.disabledCheckedBorderColor : this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: copy-Q_H9qLU, reason: not valid java name */
    public final SwitchColors m964copyQ_H9qLU(long checkedThumbColor, long checkedTrackColor, long checkedBorderColor, long checkedIconColor, long uncheckedThumbColor, long uncheckedTrackColor, long uncheckedBorderColor, long uncheckedIconColor, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledCheckedBorderColor, long disabledCheckedIconColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, long disabledUncheckedBorderColor, long disabledUncheckedIconColor) {
        return new SwitchColors(checkedThumbColor != 16 ? checkedThumbColor : this.checkedThumbColor, checkedTrackColor != 16 ? checkedTrackColor : this.checkedTrackColor, checkedBorderColor != 16 ? checkedBorderColor : this.checkedBorderColor, checkedIconColor != 16 ? checkedIconColor : this.checkedIconColor, uncheckedThumbColor != 16 ? uncheckedThumbColor : this.uncheckedThumbColor, uncheckedTrackColor != 16 ? uncheckedTrackColor : this.uncheckedTrackColor, uncheckedBorderColor != 16 ? uncheckedBorderColor : this.uncheckedBorderColor, uncheckedIconColor != 16 ? uncheckedIconColor : this.uncheckedIconColor, disabledCheckedThumbColor != 16 ? disabledCheckedThumbColor : this.disabledCheckedThumbColor, disabledCheckedTrackColor != 16 ? disabledCheckedTrackColor : this.disabledCheckedTrackColor, disabledCheckedBorderColor != 16 ? disabledCheckedBorderColor : this.disabledCheckedBorderColor, disabledCheckedIconColor != 16 ? disabledCheckedIconColor : this.disabledCheckedIconColor, disabledUncheckedThumbColor != 16 ? disabledUncheckedThumbColor : this.disabledUncheckedThumbColor, disabledUncheckedTrackColor != 16 ? disabledUncheckedTrackColor : this.disabledUncheckedTrackColor, disabledUncheckedBorderColor != 16 ? disabledUncheckedBorderColor : this.disabledUncheckedBorderColor, disabledUncheckedIconColor != 16 ? disabledUncheckedIconColor : this.disabledUncheckedIconColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SwitchColors)) {
            return false;
        }
        SwitchColors switchColors = (SwitchColors) other;
        return Color.m3135equalsimpl0(this.checkedThumbColor, switchColors.checkedThumbColor) && Color.m3135equalsimpl0(this.checkedTrackColor, switchColors.checkedTrackColor) && Color.m3135equalsimpl0(this.checkedBorderColor, switchColors.checkedBorderColor) && Color.m3135equalsimpl0(this.checkedIconColor, switchColors.checkedIconColor) && Color.m3135equalsimpl0(this.uncheckedThumbColor, switchColors.uncheckedThumbColor) && Color.m3135equalsimpl0(this.uncheckedTrackColor, switchColors.uncheckedTrackColor) && Color.m3135equalsimpl0(this.uncheckedBorderColor, switchColors.uncheckedBorderColor) && Color.m3135equalsimpl0(this.uncheckedIconColor, switchColors.uncheckedIconColor) && Color.m3135equalsimpl0(this.disabledCheckedThumbColor, switchColors.disabledCheckedThumbColor) && Color.m3135equalsimpl0(this.disabledCheckedTrackColor, switchColors.disabledCheckedTrackColor) && Color.m3135equalsimpl0(this.disabledCheckedBorderColor, switchColors.disabledCheckedBorderColor) && Color.m3135equalsimpl0(this.disabledCheckedIconColor, switchColors.disabledCheckedIconColor) && Color.m3135equalsimpl0(this.disabledUncheckedThumbColor, switchColors.disabledUncheckedThumbColor) && Color.m3135equalsimpl0(this.disabledUncheckedTrackColor, switchColors.disabledUncheckedTrackColor) && Color.m3135equalsimpl0(this.disabledUncheckedBorderColor, switchColors.disabledUncheckedBorderColor) && Color.m3135equalsimpl0(this.disabledUncheckedIconColor, switchColors.disabledUncheckedIconColor);
    }

    /* JADX INFO: renamed from: getCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBorderColor() {
        return this.checkedBorderColor;
    }

    /* JADX INFO: renamed from: getCheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedIconColor() {
        return this.checkedIconColor;
    }

    /* JADX INFO: renamed from: getCheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedThumbColor() {
        return this.checkedThumbColor;
    }

    /* JADX INFO: renamed from: getCheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedTrackColor() {
        return this.checkedTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedBorderColor() {
        return this.disabledCheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedIconColor() {
        return this.disabledCheckedIconColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedThumbColor() {
        return this.disabledCheckedThumbColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedTrackColor() {
        return this.disabledCheckedTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBorderColor() {
        return this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedIconColor() {
        return this.disabledUncheckedIconColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedThumbColor() {
        return this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedTrackColor() {
        return this.disabledUncheckedTrackColor;
    }

    /* JADX INFO: renamed from: getUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBorderColor() {
        return this.uncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getUncheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedIconColor() {
        return this.uncheckedIconColor;
    }

    /* JADX INFO: renamed from: getUncheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedThumbColor() {
        return this.uncheckedThumbColor;
    }

    /* JADX INFO: renamed from: getUncheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedTrackColor() {
        return this.uncheckedTrackColor;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((Color.m3141hashCodeimpl(this.checkedThumbColor) * 31) + Color.m3141hashCodeimpl(this.checkedTrackColor)) * 31) + Color.m3141hashCodeimpl(this.checkedBorderColor)) * 31) + Color.m3141hashCodeimpl(this.checkedIconColor)) * 31) + Color.m3141hashCodeimpl(this.uncheckedThumbColor)) * 31) + Color.m3141hashCodeimpl(this.uncheckedTrackColor)) * 31) + Color.m3141hashCodeimpl(this.uncheckedBorderColor)) * 31) + Color.m3141hashCodeimpl(this.uncheckedIconColor)) * 31) + Color.m3141hashCodeimpl(this.disabledCheckedThumbColor)) * 31) + Color.m3141hashCodeimpl(this.disabledCheckedTrackColor)) * 31) + Color.m3141hashCodeimpl(this.disabledCheckedBorderColor)) * 31) + Color.m3141hashCodeimpl(this.disabledCheckedIconColor)) * 31) + Color.m3141hashCodeimpl(this.disabledUncheckedThumbColor)) * 31) + Color.m3141hashCodeimpl(this.disabledUncheckedTrackColor)) * 31) + Color.m3141hashCodeimpl(this.disabledUncheckedBorderColor)) * 31) + Color.m3141hashCodeimpl(this.disabledUncheckedIconColor);
    }

    /* JADX INFO: renamed from: iconColor-WaAFU9c$material3, reason: not valid java name */
    public final long m981iconColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedIconColor : this.uncheckedIconColor;
        }
        return checked ? this.disabledCheckedIconColor : this.disabledUncheckedIconColor;
    }

    /* JADX INFO: renamed from: thumbColor-WaAFU9c$material3, reason: not valid java name */
    public final long m982thumbColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedThumbColor : this.uncheckedThumbColor;
        }
        return checked ? this.disabledCheckedThumbColor : this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: trackColor-WaAFU9c$material3, reason: not valid java name */
    public final long m983trackColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedTrackColor : this.uncheckedTrackColor;
        }
        return checked ? this.disabledCheckedTrackColor : this.disabledUncheckedTrackColor;
    }

    public /* synthetic */ SwitchColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16);
    }
}
