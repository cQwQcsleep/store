package androidx.compose.animation;

import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/AndroidFlingSpline;", "", "<init>", "()V", "NbSamples", "", "SplinePositions", "", "SplineTimes", "flingPosition", "Landroidx/compose/animation/AndroidFlingSpline$FlingResult;", "time", "", "deceleration", "", "velocity", "friction", "FlingResult", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidFlingSpline {
    public static final int $stable;
    public static final AndroidFlingSpline INSTANCE = new AndroidFlingSpline();
    private static final int NbSamples = 100;
    private static final float[] SplinePositions;
    private static final float[] SplineTimes;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Landroidx/compose/animation/AndroidFlingSpline$FlingResult;", "", "distanceCoefficient", "", "velocityCoefficient", "<init>", "(FF)V", "getDistanceCoefficient", "()F", "getVelocityCoefficient", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class FlingResult {
        public static final int $stable = 0;
        private final float distanceCoefficient;
        private final float velocityCoefficient;

        public FlingResult(float f, float f2) {
            this.distanceCoefficient = f;
            this.velocityCoefficient = f2;
        }

        public static /* synthetic */ FlingResult copy$default(FlingResult flingResult, float f, float f2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = flingResult.distanceCoefficient;
            }
            if ((i & 2) != 0) {
                f2 = flingResult.velocityCoefficient;
            }
            return flingResult.copy(f, f2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getDistanceCoefficient() {
            return this.distanceCoefficient;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getVelocityCoefficient() {
            return this.velocityCoefficient;
        }

        public final FlingResult copy(float distanceCoefficient, float velocityCoefficient) {
            return new FlingResult(distanceCoefficient, velocityCoefficient);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FlingResult)) {
                return false;
            }
            FlingResult flingResult = (FlingResult) other;
            return Float.compare(this.distanceCoefficient, flingResult.distanceCoefficient) == 0 && Float.compare(this.velocityCoefficient, flingResult.velocityCoefficient) == 0;
        }

        public final float getDistanceCoefficient() {
            return this.distanceCoefficient;
        }

        public final float getVelocityCoefficient() {
            return this.velocityCoefficient;
        }

        public int hashCode() {
            return (Float.hashCode(this.distanceCoefficient) * 31) + Float.hashCode(this.velocityCoefficient);
        }

        public String toString() {
            return "FlingResult(distanceCoefficient=" + this.distanceCoefficient + ", velocityCoefficient=" + this.velocityCoefficient + ')';
        }
    }

    static {
        float[] fArr = new float[101];
        SplinePositions = fArr;
        float[] fArr2 = new float[101];
        SplineTimes = fArr2;
        SplineBasedDecayKt.computeSplineInfo(fArr, fArr2, 100);
        $stable = 8;
    }

    private AndroidFlingSpline() {
    }

    public final double deceleration(float velocity, float friction) {
        return Math.log(((double) (Math.abs(velocity) * 0.35f)) / ((double) friction));
    }

    public final FlingResult flingPosition(float time) {
        float f = 0.0f;
        float f2 = 1.0f;
        float fCoerceIn = RangesKt.coerceIn(time, 0.0f, 1.0f);
        int i = (int) (100.0f * fCoerceIn);
        if (i < 100) {
            float f3 = i / 100.0f;
            int i2 = i + 1;
            float[] fArr = SplinePositions;
            float f4 = fArr[i];
            float f5 = (fArr[i2] - f4) / ((i2 / 100.0f) - f3);
            f2 = f4 + ((fCoerceIn - f3) * f5);
            f = f5;
        }
        return new FlingResult(f2, f);
    }
}
