package androidx.compose.ui.input.pointer;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated(message = "Use PointerInputChange.isConsumed and PointerInputChange.consume() instead")
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00038F@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R,\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00038F@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/input/pointer/ConsumedData;", "", "positionChange", "", "downChange", "<init>", "(ZZ)V", "change", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "value", "getPositionChange$annotations", "()V", "getPositionChange", "()Z", "setPositionChange", "(Z)V", "getDownChange$annotations", "getDownChange", "setDownChange", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ConsumedData {
    public static final int $stable = 8;
    private PointerInputChange change;
    private boolean downChange;
    private boolean positionChange;

    public /* synthetic */ ConsumedData(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    @Deprecated(message = "Partial consumption was deprecated. Use PointerEvent.isConsumed and PointerEvent.consume() instead.")
    public static /* synthetic */ void getDownChange$annotations() {
    }

    @Deprecated(message = "Partial consumption was deprecated. Use PointerEvent.isConsumed and PointerEvent.consume() instead.")
    public static /* synthetic */ void getPositionChange$annotations() {
    }

    public final boolean getDownChange() {
        PointerInputChange consumedDelegate$ui;
        PointerInputChange pointerInputChange = this.change;
        if (pointerInputChange != null && (consumedDelegate$ui = pointerInputChange.getConsumedDelegate()) != null) {
            return consumedDelegate$ui.getDownChange();
        }
        PointerInputChange pointerInputChange2 = this.change;
        return pointerInputChange2 != null ? pointerInputChange2.getDownChange() : this.downChange;
    }

    public final boolean getPositionChange() {
        PointerInputChange consumedDelegate$ui;
        PointerInputChange pointerInputChange = this.change;
        if (pointerInputChange != null && (consumedDelegate$ui = pointerInputChange.getConsumedDelegate()) != null) {
            return consumedDelegate$ui.getPositionChange();
        }
        PointerInputChange pointerInputChange2 = this.change;
        return pointerInputChange2 != null ? pointerInputChange2.getPositionChange() : this.positionChange;
    }

    public final void setDownChange(boolean z) {
        PointerInputChange consumedDelegate$ui;
        PointerInputChange pointerInputChange = this.change;
        if (pointerInputChange != null && (consumedDelegate$ui = pointerInputChange.getConsumedDelegate()) != null) {
            consumedDelegate$ui.setDownChange$ui(z);
        }
        PointerInputChange pointerInputChange2 = this.change;
        if (pointerInputChange2 != null) {
            pointerInputChange2.setDownChange$ui(z);
        }
        this.downChange = z;
    }

    public final void setPositionChange(boolean z) {
        PointerInputChange consumedDelegate$ui;
        PointerInputChange pointerInputChange = this.change;
        if (pointerInputChange != null && (consumedDelegate$ui = pointerInputChange.getConsumedDelegate()) != null) {
            consumedDelegate$ui.setPositionChange$ui(z);
        }
        PointerInputChange pointerInputChange2 = this.change;
        if (pointerInputChange2 != null) {
            pointerInputChange2.setPositionChange$ui(z);
        }
        this.positionChange = z;
    }

    public ConsumedData(boolean z, boolean z2) {
        this.positionChange = z;
        this.downChange = z2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ConsumedData() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public ConsumedData(PointerInputChange pointerInputChange) {
        this(pointerInputChange.getPositionChange(), pointerInputChange.getDownChange());
        this.change = pointerInputChange;
    }
}
