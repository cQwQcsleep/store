package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\f\u001a\u00020\r*\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\r*\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u000fJ%\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\r¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u0005¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\rH\u0002¢\u0006\u0004\b \u0010!R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\""}, d2 = {"Landroidx/compose/foundation/gestures/TouchSlopDetector;", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "initialPositionChange", "Landroidx/compose/ui/geometry/Offset;", "<init>", "(Landroidx/compose/foundation/gestures/Orientation;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "mainAxis", "", "mainAxis-k-4lQ0M", "(J)F", "crossAxis", "crossAxis-k-4lQ0M", "totalPositionChange", "J", "addPositions", "currentPosition", "previousPosition", "touchSlop", "addPositions-akrDWew", "(JJF)J", "reset", "", "initialPositionAccumulator", "reset-k-4lQ0M", "(J)V", "calculatePostSlopOffset", "calculatePostSlopOffset-tuRUvjQ", "(F)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TouchSlopDetector {
    public static final int $stable = 8;
    private Orientation orientation;
    private long totalPositionChange;

    public /* synthetic */ TouchSlopDetector(Orientation orientation, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : orientation, (i & 2) != 0 ? Offset.Companion.getZero-F1C5BW0() : j, null);
    }

    /* JADX INFO: renamed from: calculatePostSlopOffset-tuRUvjQ, reason: not valid java name */
    private final long m716calculatePostSlopOffsettuRUvjQ(float touchSlop) {
        Orientation orientation = this.orientation;
        long j = this.totalPositionChange;
        if (orientation == null) {
            return Offset.minus-MK-Hz9U(this.totalPositionChange, Offset.times-tuRUvjQ(Offset.div-tuRUvjQ(j, Offset.getDistance-impl(j)), touchSlop));
        }
        float fM720mainAxisk4lQ0M = m720mainAxisk4lQ0M(j) - (Math.signum(m720mainAxisk4lQ0M(this.totalPositionChange)) * touchSlop);
        float fM719crossAxisk4lQ0M = m719crossAxisk4lQ0M(this.totalPositionChange);
        if (this.orientation != Orientation.Horizontal) {
            return Offset.constructor-impl((((long) Float.floatToRawIntBits(fM719crossAxisk4lQ0M)) << 32) | (((long) Float.floatToRawIntBits(fM720mainAxisk4lQ0M)) & 4294967295L));
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(fM719crossAxisk4lQ0M)) & 4294967295L) | (Float.floatToRawIntBits(fM720mainAxisk4lQ0M) << 32));
    }

    /* JADX INFO: renamed from: reset-k-4lQ0M$default, reason: not valid java name */
    public static /* synthetic */ void m717resetk4lQ0M$default(TouchSlopDetector touchSlopDetector, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.Companion.getZero-F1C5BW0();
        }
        touchSlopDetector.m721resetk4lQ0M(j);
    }

    /* JADX INFO: renamed from: addPositions-akrDWew, reason: not valid java name */
    public final long m718addPositionsakrDWew(long currentPosition, long previousPosition, float touchSlop) {
        long j = Offset.plus-MK-Hz9U(this.totalPositionChange, Offset.minus-MK-Hz9U(currentPosition, previousPosition));
        this.totalPositionChange = j;
        return (this.orientation == null ? Offset.getDistance-impl(j) : Math.abs(m720mainAxisk4lQ0M(j))) >= touchSlop ? m716calculatePostSlopOffsettuRUvjQ(touchSlop) : Offset.Companion.getUnspecified-F1C5BW0();
    }

    /* JADX INFO: renamed from: crossAxis-k-4lQ0M, reason: not valid java name */
    public final float m719crossAxisk4lQ0M(long j) {
        return Float.intBitsToFloat((int) (this.orientation == Orientation.Horizontal ? j & 4294967295L : j >> 32));
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    public final float m720mainAxisk4lQ0M(long j) {
        return Float.intBitsToFloat((int) (this.orientation == Orientation.Horizontal ? j >> 32 : j & 4294967295L));
    }

    /* JADX INFO: renamed from: reset-k-4lQ0M, reason: not valid java name */
    public final void m721resetk4lQ0M(long initialPositionAccumulator) {
        this.totalPositionChange = initialPositionAccumulator;
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    private TouchSlopDetector(Orientation orientation, long j) {
        this.orientation = orientation;
        this.totalPositionChange = j;
    }

    public /* synthetic */ TouchSlopDetector(Orientation orientation, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(orientation, j);
    }
}
