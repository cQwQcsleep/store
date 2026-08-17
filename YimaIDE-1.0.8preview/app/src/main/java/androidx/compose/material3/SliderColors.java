package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJq\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u001fH\u0001¢\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u001fH\u0001¢\u0006\u0004\b'\u0010%J\u0013\u0010(\u001a\u00020\u001f2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010*\u001a\u00020+H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0016\u0010\u0010R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0017\u0010\u0010R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0019\u0010\u0010R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u001a\u0010\u0010¨\u0006,"}, d2 = {"Landroidx/compose/material3/SliderColors;", "", "thumbColor", "Landroidx/compose/ui/graphics/Color;", "activeTrackColor", "activeTickColor", "inactiveTrackColor", "inactiveTickColor", "disabledThumbColor", "disabledActiveTrackColor", "disabledActiveTickColor", "disabledInactiveTrackColor", "disabledInactiveTickColor", "<init>", "(JJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getThumbColor-0d7_KjU", "()J", "J", "getActiveTrackColor-0d7_KjU", "getActiveTickColor-0d7_KjU", "getInactiveTrackColor-0d7_KjU", "getInactiveTickColor-0d7_KjU", "getDisabledThumbColor-0d7_KjU", "getDisabledActiveTrackColor-0d7_KjU", "getDisabledActiveTickColor-0d7_KjU", "getDisabledInactiveTrackColor-0d7_KjU", "getDisabledInactiveTickColor-0d7_KjU", "copy", "copy--K518z4", "(JJJJJJJJJJ)Landroidx/compose/material3/SliderColors;", "enabled", "", "thumbColor-vNxB06k$material3", "(Z)J", "trackColor", "active", "trackColor-WaAFU9c$material3", "(ZZ)J", "tickColor", "tickColor-WaAFU9c$material3", "equals", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SliderColors {
    public static final int $stable = 0;
    private final long activeTickColor;
    private final long activeTrackColor;
    private final long disabledActiveTickColor;
    private final long disabledActiveTrackColor;
    private final long disabledInactiveTickColor;
    private final long disabledInactiveTrackColor;
    private final long disabledThumbColor;
    private final long inactiveTickColor;
    private final long inactiveTrackColor;
    private final long thumbColor;

    private SliderColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10) {
        this.thumbColor = j;
        this.activeTrackColor = j2;
        this.activeTickColor = j3;
        this.inactiveTrackColor = j4;
        this.inactiveTickColor = j5;
        this.disabledThumbColor = j6;
        this.disabledActiveTrackColor = j7;
        this.disabledActiveTickColor = j8;
        this.disabledInactiveTrackColor = j9;
        this.disabledInactiveTickColor = j10;
    }

    /* JADX INFO: renamed from: copy--K518z4$default, reason: not valid java name */
    public static /* synthetic */ SliderColors m881copyK518z4$default(SliderColors sliderColors, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, int i, Object obj) {
        long j11;
        long j12;
        long j13 = (i & 1) != 0 ? sliderColors.thumbColor : j;
        long j14 = (i & 2) != 0 ? sliderColors.activeTrackColor : j2;
        long j15 = (i & 4) != 0 ? sliderColors.activeTickColor : j3;
        long j16 = (i & 8) != 0 ? sliderColors.inactiveTrackColor : j4;
        long j17 = (i & 16) != 0 ? sliderColors.inactiveTickColor : j5;
        long j18 = (i & 32) != 0 ? sliderColors.disabledThumbColor : j6;
        long j19 = (i & 64) != 0 ? sliderColors.disabledActiveTrackColor : j7;
        long j20 = j13;
        long j21 = (i & 128) != 0 ? sliderColors.disabledActiveTickColor : j8;
        long j22 = (i & 256) != 0 ? sliderColors.disabledInactiveTrackColor : j9;
        if ((i & 512) != 0) {
            j12 = j22;
            j11 = sliderColors.disabledInactiveTickColor;
        } else {
            j11 = j10;
            j12 = j22;
        }
        return sliderColors.m882copyK518z4(j20, j14, j15, j16, j17, j18, j19, j21, j12, j11);
    }

    /* JADX INFO: renamed from: copy--K518z4, reason: not valid java name */
    public final SliderColors m882copyK518z4(long thumbColor, long activeTrackColor, long activeTickColor, long inactiveTrackColor, long inactiveTickColor, long disabledThumbColor, long disabledActiveTrackColor, long disabledActiveTickColor, long disabledInactiveTrackColor, long disabledInactiveTickColor) {
        return new SliderColors(thumbColor != 16 ? thumbColor : this.thumbColor, activeTrackColor != 16 ? activeTrackColor : this.activeTrackColor, activeTickColor != 16 ? activeTickColor : this.activeTickColor, inactiveTrackColor != 16 ? inactiveTrackColor : this.inactiveTrackColor, inactiveTickColor != 16 ? inactiveTickColor : this.inactiveTickColor, disabledThumbColor != 16 ? disabledThumbColor : this.disabledThumbColor, disabledActiveTrackColor != 16 ? disabledActiveTrackColor : this.disabledActiveTrackColor, disabledActiveTickColor != 16 ? disabledActiveTickColor : this.disabledActiveTickColor, disabledInactiveTrackColor != 16 ? disabledInactiveTrackColor : this.disabledInactiveTrackColor, disabledInactiveTickColor != 16 ? disabledInactiveTickColor : this.disabledInactiveTickColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SliderColors)) {
            return false;
        }
        SliderColors sliderColors = (SliderColors) other;
        return Color.m3135equalsimpl0(this.thumbColor, sliderColors.thumbColor) && Color.m3135equalsimpl0(this.activeTrackColor, sliderColors.activeTrackColor) && Color.m3135equalsimpl0(this.activeTickColor, sliderColors.activeTickColor) && Color.m3135equalsimpl0(this.inactiveTrackColor, sliderColors.inactiveTrackColor) && Color.m3135equalsimpl0(this.inactiveTickColor, sliderColors.inactiveTickColor) && Color.m3135equalsimpl0(this.disabledThumbColor, sliderColors.disabledThumbColor) && Color.m3135equalsimpl0(this.disabledActiveTrackColor, sliderColors.disabledActiveTrackColor) && Color.m3135equalsimpl0(this.disabledActiveTickColor, sliderColors.disabledActiveTickColor) && Color.m3135equalsimpl0(this.disabledInactiveTrackColor, sliderColors.disabledInactiveTrackColor) && Color.m3135equalsimpl0(this.disabledInactiveTickColor, sliderColors.disabledInactiveTickColor);
    }

    /* JADX INFO: renamed from: getActiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTickColor() {
        return this.activeTickColor;
    }

    /* JADX INFO: renamed from: getActiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveTrackColor() {
        return this.activeTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveTickColor() {
        return this.disabledActiveTickColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveTrackColor() {
        return this.disabledActiveTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveTickColor() {
        return this.disabledInactiveTickColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveTrackColor() {
        return this.disabledInactiveTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledThumbColor() {
        return this.disabledThumbColor;
    }

    /* JADX INFO: renamed from: getInactiveTickColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveTickColor() {
        return this.inactiveTickColor;
    }

    /* JADX INFO: renamed from: getInactiveTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveTrackColor() {
        return this.inactiveTrackColor;
    }

    /* JADX INFO: renamed from: getThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getThumbColor() {
        return this.thumbColor;
    }

    public int hashCode() {
        return (((((((((((((((((Color.m3141hashCodeimpl(this.thumbColor) * 31) + Color.m3141hashCodeimpl(this.activeTrackColor)) * 31) + Color.m3141hashCodeimpl(this.activeTickColor)) * 31) + Color.m3141hashCodeimpl(this.inactiveTrackColor)) * 31) + Color.m3141hashCodeimpl(this.inactiveTickColor)) * 31) + Color.m3141hashCodeimpl(this.disabledThumbColor)) * 31) + Color.m3141hashCodeimpl(this.disabledActiveTrackColor)) * 31) + Color.m3141hashCodeimpl(this.disabledActiveTickColor)) * 31) + Color.m3141hashCodeimpl(this.disabledInactiveTrackColor)) * 31) + Color.m3141hashCodeimpl(this.disabledInactiveTickColor);
    }

    /* JADX INFO: renamed from: thumbColor-vNxB06k$material3, reason: not valid java name */
    public final long m893thumbColorvNxB06k$material3(boolean enabled) {
        return enabled ? this.thumbColor : this.disabledThumbColor;
    }

    /* JADX INFO: renamed from: tickColor-WaAFU9c$material3, reason: not valid java name */
    public final long m894tickColorWaAFU9c$material3(boolean enabled, boolean active) {
        if (enabled) {
            return active ? this.activeTickColor : this.inactiveTickColor;
        }
        return active ? this.disabledActiveTickColor : this.disabledInactiveTickColor;
    }

    /* JADX INFO: renamed from: trackColor-WaAFU9c$material3, reason: not valid java name */
    public final long m895trackColorWaAFU9c$material3(boolean enabled, boolean active) {
        if (enabled) {
            return active ? this.activeTrackColor : this.inactiveTrackColor;
        }
        return active ? this.disabledActiveTrackColor : this.disabledInactiveTrackColor;
    }

    public /* synthetic */ SliderColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10);
    }
}
