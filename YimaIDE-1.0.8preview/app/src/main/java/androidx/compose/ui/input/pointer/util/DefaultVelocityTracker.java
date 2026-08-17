package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0016¢\u0006\u0004\b#\u0010$J\b\u0010%\u001a\u00020\u0015H\u0016J\u001f\u0010&\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0002¢\u0006\u0004\b'\u0010$J\u001f\u0010(\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0002¢\u0006\u0004\b)\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000e¨\u0006*"}, d2 = {"Landroidx/compose/ui/input/pointer/util/DefaultVelocityTracker;", "Landroidx/compose/ui/input/pointer/util/PlatformVelocityTracker;", "<init>", "()V", "strategy", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D$Strategy;", "xVelocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker1D;", "yVelocityTracker", "currentPointerPositionAccumulator", "Landroidx/compose/ui/geometry/Offset;", "getCurrentPointerPositionAccumulator-F1C5BW0$ui", "()J", "setCurrentPointerPositionAccumulator-k-4lQ0M$ui", "(J)V", "J", "lastMoveEventTimeStamp", "", "getLastMoveEventTimeStamp$ui", "setLastMoveEventTimeStamp$ui", "addPosition", "", "timeMillis", "position", "addPosition-Uv8p0NA", "(JJ)V", "calculateVelocity", "Landroidx/compose/ui/unit/Velocity;", "maximumVelocity", "calculateVelocity-AH228Gc", "(J)J", "addPointerInputChange", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/pointer/PointerInputChange;", "offset", "addPointerInputChange-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "resetTracking", "addPointerInputChangeLegacy", "addPointerInputChangeLegacy-Uv8p0NA", "addPointerInputChangeWithFix", "addPointerInputChangeWithFix-Uv8p0NA", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DefaultVelocityTracker implements PlatformVelocityTracker {
    public static final int $stable = 8;
    private long currentPointerPositionAccumulator;
    private long lastMoveEventTimeStamp;
    private final VelocityTracker1D.Strategy strategy;
    private final VelocityTracker1D xVelocityTracker;
    private final VelocityTracker1D yVelocityTracker;

    public DefaultVelocityTracker() {
        VelocityTracker1D.Strategy strategy = VelocityTracker1D.Strategy.Lsq2;
        this.strategy = strategy;
        this.xVelocityTracker = new VelocityTracker1D(false, strategy, 1, null);
        this.yVelocityTracker = new VelocityTracker1D(false, strategy, 1, null);
        this.currentPointerPositionAccumulator = Offset.INSTANCE.m2905getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: addPointerInputChangeLegacy-Uv8p0NA, reason: not valid java name */
    private final void m4562addPointerInputChangeLegacyUv8p0NA(PointerInputChange event, long offset) {
        if (PointerEventKt.changedToDownIgnoreConsumed(event)) {
            this.currentPointerPositionAccumulator = event.getPosition();
            resetTracking();
        }
        long previousPosition = event.getPreviousPosition();
        List<HistoricalChange> historical = event.getHistorical();
        int size = historical.size();
        int i = 0;
        while (i < size) {
            HistoricalChange historicalChange = historical.get(i);
            long jM2893minusMKHz9U = Offset.m2893minusMKHz9U(historicalChange.getPosition(), previousPosition);
            long position = historicalChange.getPosition();
            this.currentPointerPositionAccumulator = Offset.m2894plusMKHz9U(this.currentPointerPositionAccumulator, jM2893minusMKHz9U);
            mo4565addPositionUv8p0NA(historicalChange.getUptimeMillis(), Offset.m2894plusMKHz9U(this.currentPointerPositionAccumulator, offset));
            i++;
            previousPosition = position;
        }
        this.currentPointerPositionAccumulator = Offset.m2894plusMKHz9U(this.currentPointerPositionAccumulator, Offset.m2893minusMKHz9U(event.getPosition(), previousPosition));
        mo4565addPositionUv8p0NA(event.getUptimeMillis(), Offset.m2894plusMKHz9U(this.currentPointerPositionAccumulator, offset));
    }

    /* JADX INFO: renamed from: addPointerInputChangeWithFix-Uv8p0NA, reason: not valid java name */
    private final void m4563addPointerInputChangeWithFixUv8p0NA(PointerInputChange event, long offset) {
        if (PointerEventKt.changedToDownIgnoreConsumed(event)) {
            resetTracking();
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(event)) {
            List<HistoricalChange> historical = event.getHistorical();
            int size = historical.size();
            for (int i = 0; i < size; i++) {
                HistoricalChange historicalChange = historical.get(i);
                mo4565addPositionUv8p0NA(historicalChange.getUptimeMillis(), Offset.m2894plusMKHz9U(historicalChange.getOriginalEventPosition(), offset));
            }
            mo4565addPositionUv8p0NA(event.getUptimeMillis(), Offset.m2894plusMKHz9U(event.getOriginalEventPosition(), offset));
        }
        if (PointerEventKt.changedToUpIgnoreConsumed(event) && event.getUptimeMillis() - this.lastMoveEventTimeStamp > 40) {
            resetTracking();
        }
        this.lastMoveEventTimeStamp = event.getUptimeMillis();
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    /* JADX INFO: renamed from: addPointerInputChange-Uv8p0NA, reason: not valid java name */
    public void mo4564addPointerInputChangeUv8p0NA(PointerInputChange event, long offset) {
        if (VelocityTrackerKt.getVelocityTrackerAddPointsFix()) {
            m4563addPointerInputChangeWithFixUv8p0NA(event, offset);
        } else {
            m4562addPointerInputChangeLegacyUv8p0NA(event, offset);
        }
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    /* JADX INFO: renamed from: addPosition-Uv8p0NA, reason: not valid java name */
    public void mo4565addPositionUv8p0NA(long timeMillis, long position) {
        this.xVelocityTracker.addDataPoint(timeMillis, Float.intBitsToFloat((int) (position >> 32)));
        this.yVelocityTracker.addDataPoint(timeMillis, Float.intBitsToFloat((int) (position & 4294967295L)));
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    /* JADX INFO: renamed from: calculateVelocity-AH228Gc, reason: not valid java name */
    public long mo4566calculateVelocityAH228Gc(long maximumVelocity) {
        if (!(Velocity.m6260getXimpl(maximumVelocity) > 0.0f && Velocity.m6261getYimpl(maximumVelocity) > 0.0f)) {
            InlineClassHelperKt.throwIllegalStateException("maximumVelocity should be a positive value. You specified=" + ((Object) Velocity.m6267toStringimpl(maximumVelocity)));
        }
        return VelocityKt.Velocity(this.xVelocityTracker.calculateVelocity(Velocity.m6260getXimpl(maximumVelocity)), this.yVelocityTracker.calculateVelocity(Velocity.m6261getYimpl(maximumVelocity)));
    }

    /* JADX INFO: renamed from: getCurrentPointerPositionAccumulator-F1C5BW0$ui, reason: not valid java name and from getter */
    public final long getCurrentPointerPositionAccumulator() {
        return this.currentPointerPositionAccumulator;
    }

    /* JADX INFO: renamed from: getLastMoveEventTimeStamp$ui, reason: from getter */
    public final long getLastMoveEventTimeStamp() {
        return this.lastMoveEventTimeStamp;
    }

    @Override // androidx.compose.ui.input.pointer.util.PlatformVelocityTracker
    public void resetTracking() {
        this.xVelocityTracker.resetTracking();
        this.yVelocityTracker.resetTracking();
        this.lastMoveEventTimeStamp = 0L;
    }

    /* JADX INFO: renamed from: setCurrentPointerPositionAccumulator-k-4lQ0M$ui, reason: not valid java name */
    public final void m4568setCurrentPointerPositionAccumulatork4lQ0M$ui(long j) {
        this.currentPointerPositionAccumulator = j;
    }

    public final void setLastMoveEventTimeStamp$ui(long j) {
        this.lastMoveEventTimeStamp = j;
    }
}
