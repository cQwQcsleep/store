package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u0011\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0082\bJ \u0010\u0016\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J(\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/compose/animation/core/FloatTweenSpec;", "Landroidx/compose/animation/core/FloatAnimationSpec;", "duration", "", "delay", "easing", "Landroidx/compose/animation/core/Easing;", "<init>", "(IILandroidx/compose/animation/core/Easing;)V", "getDuration", "()I", "getDelay", "durationNanos", "", "delayNanos", "getValueFromNanos", "", "playTimeNanos", "initialValue", "targetValue", "initialVelocity", "clampPlayTimeNanos", "getDurationNanos", "getVelocityFromNanos", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FloatTweenSpec implements FloatAnimationSpec {
    public static final int $stable = 0;
    private final int delay;
    private final long delayNanos;
    private final int duration;
    private final long durationNanos;
    private final Easing easing;

    public /* synthetic */ FloatTweenSpec(int i, int i2, Easing easing, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? AnimationConstants.DefaultDurationMillis : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? EasingKt.getFastOutSlowInEasing() : easing);
    }

    private final long clampPlayTimeNanos(long playTimeNanos) {
        long j = playTimeNanos - this.delayNanos;
        long j2 = this.durationNanos;
        if (j < 0) {
            j = 0;
        }
        return j > j2 ? j2 : j;
    }

    public final int getDelay() {
        return this.delay;
    }

    public final int getDuration() {
        return this.duration;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public long getDurationNanos(float initialValue, float targetValue, float initialVelocity) {
        return this.delayNanos + this.durationNanos;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public float getValueFromNanos(long playTimeNanos, float initialValue, float targetValue, float initialVelocity) {
        long j = playTimeNanos - this.delayNanos;
        long j2 = this.durationNanos;
        if (j < 0) {
            j = 0;
        }
        if (j > j2) {
            j = j2;
        }
        float fTransform = this.easing.transform(this.duration == 0 ? 1.0f : j / j2);
        return (initialValue * (1.0f - fTransform)) + (targetValue * fTransform);
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public float getVelocityFromNanos(long playTimeNanos, float initialValue, float targetValue, float initialVelocity) {
        long j = playTimeNanos - this.delayNanos;
        long j2 = this.durationNanos;
        if (j < 0) {
            j = 0;
        }
        long j3 = j > j2 ? j2 : j;
        if (j3 == 0) {
            return initialVelocity;
        }
        return (getValueFromNanos(j3, initialValue, targetValue, initialVelocity) - getValueFromNanos(j3 - AnimationKt.MillisToNanos, initialValue, targetValue, initialVelocity)) * 1000.0f;
    }

    public FloatTweenSpec(int i, int i2, Easing easing) {
        this.duration = i;
        this.delay = i2;
        this.easing = easing;
        this.durationNanos = ((long) i) * AnimationKt.MillisToNanos;
        this.delayNanos = ((long) i2) * AnimationKt.MillisToNanos;
    }

    public FloatTweenSpec() {
        this(0, 0, null, 7, null);
    }
}
