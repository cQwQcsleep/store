package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated(message = "Not necessary with the introduction of Slider state")
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016R7\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00068F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001c"}, d2 = {"Landroidx/compose/material3/SliderPositions;", "", "initialActiveRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "initialTickFractions", "", "<init>", "(Lkotlin/ranges/ClosedFloatingPointRange;[F)V", "<set-?>", "activeRange", "getActiveRange", "()Lkotlin/ranges/ClosedFloatingPointRange;", "setActiveRange$material3", "(Lkotlin/ranges/ClosedFloatingPointRange;)V", "activeRange$delegate", "Landroidx/compose/runtime/MutableState;", "tickFractions", "getTickFractions", "()[F", "setTickFractions$material3", "([F)V", "tickFractions$delegate", "equals", "", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SliderPositions {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: activeRange$delegate, reason: from kotlin metadata */
    private final MutableState activeRange;

    /* JADX INFO: renamed from: tickFractions$delegate, reason: from kotlin metadata */
    private final MutableState tickFractions;

    public /* synthetic */ SliderPositions(ClosedFloatingPointRange closedFloatingPointRange, float[] fArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RangesKt.rangeTo(0.0f, 1.0f) : closedFloatingPointRange, (i & 2) != 0 ? new float[0] : fArr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SliderPositions)) {
            return false;
        }
        SliderPositions sliderPositions = (SliderPositions) other;
        return Intrinsics.areEqual(getActiveRange(), sliderPositions.getActiveRange()) && Arrays.equals(getTickFractions(), sliderPositions.getTickFractions());
    }

    public final ClosedFloatingPointRange<Float> getActiveRange() {
        return (ClosedFloatingPointRange) this.activeRange.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final float[] getTickFractions() {
        return (float[]) this.tickFractions.getValue();
    }

    public int hashCode() {
        return (getActiveRange().hashCode() * 31) + Arrays.hashCode(getTickFractions());
    }

    public final void setActiveRange$material3(ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        this.activeRange.setValue(closedFloatingPointRange);
    }

    public final void setTickFractions$material3(float[] fArr) {
        this.tickFractions.setValue(fArr);
    }

    public SliderPositions(ClosedFloatingPointRange<Float> closedFloatingPointRange, float[] fArr) {
        this.activeRange = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(closedFloatingPointRange, null, 2, null);
        this.tickFractions = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fArr, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SliderPositions() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }
}
