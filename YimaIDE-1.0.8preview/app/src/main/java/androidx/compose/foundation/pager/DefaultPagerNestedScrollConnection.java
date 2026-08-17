package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0018*\u00020\u0011H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ \u0010\u001f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0096@¢\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020\u0018*\u00020\u0011H\u0002¢\u0006\u0004\b#\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"Landroidx/compose/foundation/pager/DefaultPagerNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/foundation/pager/PagerState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/gestures/Orientation;)V", "getState", "()Landroidx/compose/foundation/pager/PagerState;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "consumeOnOrientation", "Landroidx/compose/ui/unit/Velocity;", "consumeOnOrientation-QWom1Mo", "(JLandroidx/compose/foundation/gestures/Orientation;)J", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "toFloat", "", "toFloat-k-4lQ0M", "(J)F", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mainAxis", "mainAxis-k-4lQ0M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DefaultPagerNestedScrollConnection implements NestedScrollConnection {
    private final Orientation orientation;
    private final PagerState state;

    public DefaultPagerNestedScrollConnection(PagerState pagerState, Orientation orientation) {
        this.state = pagerState;
        this.orientation = orientation;
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    private final float m1216mainAxisk4lQ0M(long j) {
        return Float.intBitsToFloat((int) (this.orientation == Orientation.Horizontal ? j >> 32 : j & 4294967295L));
    }

    /* JADX INFO: renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    private final float m1217toFloatk4lQ0M(long j) {
        return Float.intBitsToFloat((int) (this.orientation == Orientation.Horizontal ? j >> 32 : j & 4294967295L));
    }

    /* JADX INFO: renamed from: consumeOnOrientation-QWom1Mo, reason: not valid java name */
    public final long m1218consumeOnOrientationQWom1Mo(long j, Orientation orientation) {
        return orientation == Orientation.Vertical ? Velocity.copy-OhffZ5M$default(j, 0.0f, 0.0f, 2, (Object) null) : Velocity.copy-OhffZ5M$default(j, 0.0f, 0.0f, 1, (Object) null);
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final PagerState getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
    public Object m1219onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        return Velocity.box-impl(m1218consumeOnOrientationQWom1Mo(j2, this.orientation));
    }

    /* JADX INFO: renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
    public long m1220onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!NestedScrollSource.equals-impl0(source, NestedScrollSource.Companion.getSideEffect-WNlRxjI()) || m1216mainAxisk4lQ0M(available) == 0.0f) {
            return Offset.Companion.getZero-F1C5BW0();
        }
        throw new CancellationException("Scroll cancelled");
    }

    /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
    public long m1221onPreScrollOzD1aCk(long available, int source) {
        if (!NestedScrollSource.equals-impl0(source, NestedScrollSource.Companion.getUserInput-WNlRxjI()) || Math.abs(this.state.getCurrentPageOffsetFraction()) <= 1.0E-6d || Math.abs(m1217toFloatk4lQ0M(available)) <= 0.0f) {
            return Offset.Companion.getZero-F1C5BW0();
        }
        float currentPageOffsetFraction = this.state.getCurrentPageOffsetFraction() * this.state.getPageSize$foundation();
        float pageSize = ((this.state.getLayoutInfo().getPageSize() + this.state.getLayoutInfo().getPageSpacing()) * (-Math.signum(this.state.getCurrentPageOffsetFraction()))) + currentPageOffsetFraction;
        if (this.state.getCurrentPageOffsetFraction() > 0.0f) {
            pageSize = currentPageOffsetFraction;
            currentPageOffsetFraction = pageSize;
        }
        float fIntBitsToFloat = -this.state.dispatchRawDelta(-RangesKt.coerceIn(m1217toFloatk4lQ0M(available), currentPageOffsetFraction, pageSize));
        float fIntBitsToFloat2 = this.orientation == Orientation.Horizontal ? fIntBitsToFloat : Float.intBitsToFloat((int) (available >> 32));
        if (this.orientation != Orientation.Vertical) {
            fIntBitsToFloat = Float.intBitsToFloat((int) (4294967295L & available));
        }
        return Offset.copy-dBAh8RU(available, fIntBitsToFloat2, fIntBitsToFloat);
    }
}
