package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u008f\u0001\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001b\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001d\u0010\u001aJ\u001f\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0001¢\u0006\u0004\b\u001f\u0010\u001aJ\u0013\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\"\u001a\u00020#H\u0016R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u000f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006$"}, d2 = {"Landroidx/compose/material3/SelectableChipColors;", "", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "leadingIconColor", "trailingIconColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "selectedContainerColor", "disabledSelectedContainerColor", "selectedLabelColor", "selectedLeadingIconColor", "selectedTrailingIconColor", "<init>", "(JJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "copy", "copy-daRQuJA", "(JJJJJJJJJJJJJ)Landroidx/compose/material3/SelectableChipColors;", "enabled", "", "selected", "containerColor-WaAFU9c$material3", "(ZZ)J", "labelColor-WaAFU9c$material3", "leadingIconContentColor", "leadingIconContentColor-WaAFU9c$material3", "trailingIconContentColor", "trailingIconContentColor-WaAFU9c$material3", "equals", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SelectableChipColors {
    public static final int $stable = 0;
    private final long containerColor;
    private final long disabledContainerColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconColor;
    private final long disabledSelectedContainerColor;
    private final long disabledTrailingIconColor;
    private final long labelColor;
    private final long leadingIconColor;
    private final long selectedContainerColor;
    private final long selectedLabelColor;
    private final long selectedLeadingIconColor;
    private final long selectedTrailingIconColor;
    private final long trailingIconColor;

    private SelectableChipColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13) {
        this.containerColor = j;
        this.labelColor = j2;
        this.leadingIconColor = j3;
        this.trailingIconColor = j4;
        this.disabledContainerColor = j5;
        this.disabledLabelColor = j6;
        this.disabledLeadingIconColor = j7;
        this.disabledTrailingIconColor = j8;
        this.selectedContainerColor = j9;
        this.disabledSelectedContainerColor = j10;
        this.selectedLabelColor = j11;
        this.selectedLeadingIconColor = j12;
        this.selectedTrailingIconColor = j13;
    }

    /* JADX INFO: renamed from: copy-daRQuJA$default, reason: not valid java name */
    public static /* synthetic */ SelectableChipColors m848copydaRQuJA$default(SelectableChipColors selectableChipColors, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, int i, Object obj) {
        long j14;
        long j15;
        long j16 = (i & 1) != 0 ? selectableChipColors.containerColor : j;
        long j17 = (i & 2) != 0 ? selectableChipColors.labelColor : j2;
        long j18 = (i & 4) != 0 ? selectableChipColors.leadingIconColor : j3;
        long j19 = (i & 8) != 0 ? selectableChipColors.trailingIconColor : j4;
        long j20 = (i & 16) != 0 ? selectableChipColors.disabledContainerColor : j5;
        long j21 = (i & 32) != 0 ? selectableChipColors.disabledLabelColor : j6;
        long j22 = (i & 64) != 0 ? selectableChipColors.disabledLeadingIconColor : j7;
        long j23 = j16;
        long j24 = (i & 128) != 0 ? selectableChipColors.disabledTrailingIconColor : j8;
        long j25 = (i & 256) != 0 ? selectableChipColors.selectedContainerColor : j9;
        long j26 = (i & 512) != 0 ? selectableChipColors.disabledSelectedContainerColor : j10;
        long j27 = (i & 1024) != 0 ? selectableChipColors.selectedLabelColor : j11;
        long j28 = (i & 2048) != 0 ? selectableChipColors.selectedLeadingIconColor : j12;
        if ((i & 4096) != 0) {
            j15 = j28;
            j14 = selectableChipColors.selectedTrailingIconColor;
        } else {
            j14 = j13;
            j15 = j28;
        }
        return selectableChipColors.m850copydaRQuJA(j23, j17, j18, j19, j20, j21, j22, j24, j25, j26, j27, j15, j14);
    }

    /* JADX INFO: renamed from: containerColor-WaAFU9c$material3, reason: not valid java name */
    public final long m849containerColorWaAFU9c$material3(boolean enabled, boolean selected) {
        if (enabled) {
            return !selected ? this.containerColor : this.selectedContainerColor;
        }
        return selected ? this.disabledSelectedContainerColor : this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: copy-daRQuJA, reason: not valid java name */
    public final SelectableChipColors m850copydaRQuJA(long containerColor, long labelColor, long leadingIconColor, long trailingIconColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconColor, long disabledTrailingIconColor, long selectedContainerColor, long disabledSelectedContainerColor, long selectedLabelColor, long selectedLeadingIconColor, long selectedTrailingIconColor) {
        return new SelectableChipColors(containerColor != 16 ? containerColor : this.containerColor, labelColor != 16 ? labelColor : this.labelColor, leadingIconColor != 16 ? leadingIconColor : this.leadingIconColor, trailingIconColor != 16 ? trailingIconColor : this.trailingIconColor, disabledContainerColor != 16 ? disabledContainerColor : this.disabledContainerColor, disabledLabelColor != 16 ? disabledLabelColor : this.disabledLabelColor, disabledLeadingIconColor != 16 ? disabledLeadingIconColor : this.disabledLeadingIconColor, disabledTrailingIconColor != 16 ? disabledTrailingIconColor : this.disabledTrailingIconColor, selectedContainerColor != 16 ? selectedContainerColor : this.selectedContainerColor, disabledSelectedContainerColor != 16 ? disabledSelectedContainerColor : this.disabledSelectedContainerColor, selectedLabelColor != 16 ? selectedLabelColor : this.selectedLabelColor, selectedLeadingIconColor != 16 ? selectedLeadingIconColor : this.selectedLeadingIconColor, selectedTrailingIconColor != 16 ? selectedTrailingIconColor : this.selectedTrailingIconColor, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SelectableChipColors)) {
            return false;
        }
        SelectableChipColors selectableChipColors = (SelectableChipColors) other;
        return Color.m3135equalsimpl0(this.containerColor, selectableChipColors.containerColor) && Color.m3135equalsimpl0(this.labelColor, selectableChipColors.labelColor) && Color.m3135equalsimpl0(this.leadingIconColor, selectableChipColors.leadingIconColor) && Color.m3135equalsimpl0(this.trailingIconColor, selectableChipColors.trailingIconColor) && Color.m3135equalsimpl0(this.disabledContainerColor, selectableChipColors.disabledContainerColor) && Color.m3135equalsimpl0(this.disabledLabelColor, selectableChipColors.disabledLabelColor) && Color.m3135equalsimpl0(this.disabledLeadingIconColor, selectableChipColors.disabledLeadingIconColor) && Color.m3135equalsimpl0(this.disabledTrailingIconColor, selectableChipColors.disabledTrailingIconColor) && Color.m3135equalsimpl0(this.selectedContainerColor, selectableChipColors.selectedContainerColor) && Color.m3135equalsimpl0(this.disabledSelectedContainerColor, selectableChipColors.disabledSelectedContainerColor) && Color.m3135equalsimpl0(this.selectedLabelColor, selectableChipColors.selectedLabelColor) && Color.m3135equalsimpl0(this.selectedLeadingIconColor, selectableChipColors.selectedLeadingIconColor) && Color.m3135equalsimpl0(this.selectedTrailingIconColor, selectableChipColors.selectedTrailingIconColor);
    }

    public int hashCode() {
        return (((((((((((((((((((((((Color.m3141hashCodeimpl(this.containerColor) * 31) + Color.m3141hashCodeimpl(this.labelColor)) * 31) + Color.m3141hashCodeimpl(this.leadingIconColor)) * 31) + Color.m3141hashCodeimpl(this.trailingIconColor)) * 31) + Color.m3141hashCodeimpl(this.disabledContainerColor)) * 31) + Color.m3141hashCodeimpl(this.disabledLabelColor)) * 31) + Color.m3141hashCodeimpl(this.disabledLeadingIconColor)) * 31) + Color.m3141hashCodeimpl(this.disabledTrailingIconColor)) * 31) + Color.m3141hashCodeimpl(this.selectedContainerColor)) * 31) + Color.m3141hashCodeimpl(this.disabledSelectedContainerColor)) * 31) + Color.m3141hashCodeimpl(this.selectedLabelColor)) * 31) + Color.m3141hashCodeimpl(this.selectedLeadingIconColor)) * 31) + Color.m3141hashCodeimpl(this.selectedTrailingIconColor);
    }

    /* JADX INFO: renamed from: labelColor-WaAFU9c$material3, reason: not valid java name */
    public final long m851labelColorWaAFU9c$material3(boolean enabled, boolean selected) {
        if (enabled) {
            return !selected ? this.labelColor : this.selectedLabelColor;
        }
        return this.disabledLabelColor;
    }

    /* JADX INFO: renamed from: leadingIconContentColor-WaAFU9c$material3, reason: not valid java name */
    public final long m852leadingIconContentColorWaAFU9c$material3(boolean enabled, boolean selected) {
        if (enabled) {
            return !selected ? this.leadingIconColor : this.selectedLeadingIconColor;
        }
        return this.disabledLeadingIconColor;
    }

    /* JADX INFO: renamed from: trailingIconContentColor-WaAFU9c$material3, reason: not valid java name */
    public final long m853trailingIconContentColorWaAFU9c$material3(boolean enabled, boolean selected) {
        if (enabled) {
            return !selected ? this.trailingIconColor : this.selectedTrailingIconColor;
        }
        return this.disabledTrailingIconColor;
    }

    public /* synthetic */ SelectableChipColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13);
    }
}
