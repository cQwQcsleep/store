package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "", "<init>", "()V", "platformVelocityTracker", "Landroidx/compose/ui/input/pointer/util/PlatformVelocityTracker;", "getPlatformVelocityTracker$ui", "()Landroidx/compose/ui/input/pointer/util/PlatformVelocityTracker;", "addPosition", "", "timeMillis", "", "position", "Landroidx/compose/ui/geometry/Offset;", "addPosition-Uv8p0NA", "(JJ)V", "calculateVelocity", "Landroidx/compose/ui/unit/Velocity;", "calculateVelocity-9UxMQ8M", "()J", "maximumVelocity", "calculateVelocity-AH228Gc", "(J)J", "resetTracking", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class VelocityTracker {
    public static final int $stable = 8;
    private final PlatformVelocityTracker platformVelocityTracker = PlatformVelocityTracker_androidKt.PlatformVelocityTracker();

    /* JADX INFO: renamed from: addPosition-Uv8p0NA, reason: not valid java name */
    public final void m4574addPositionUv8p0NA(long timeMillis, long position) {
        this.platformVelocityTracker.mo4565addPositionUv8p0NA(timeMillis, position);
    }

    /* JADX INFO: renamed from: calculateVelocity-9UxMQ8M, reason: not valid java name */
    public final long m4575calculateVelocity9UxMQ8M() {
        return m4576calculateVelocityAH228Gc(VelocityKt.Velocity(Float.MAX_VALUE, Float.MAX_VALUE));
    }

    /* JADX INFO: renamed from: calculateVelocity-AH228Gc, reason: not valid java name */
    public final long m4576calculateVelocityAH228Gc(long maximumVelocity) {
        return this.platformVelocityTracker.mo4566calculateVelocityAH228Gc(maximumVelocity);
    }

    /* JADX INFO: renamed from: getPlatformVelocityTracker$ui, reason: from getter */
    public final PlatformVelocityTracker getPlatformVelocityTracker() {
        return this.platformVelocityTracker;
    }

    public final void resetTracking() {
        this.platformVelocityTracker.resetTracking();
    }
}
