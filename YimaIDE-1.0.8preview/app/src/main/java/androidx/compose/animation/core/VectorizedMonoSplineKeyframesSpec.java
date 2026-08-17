package androidx.compose.animation.core;

import androidx.collection.IntList;
import androidx.collection.IntObjectMap;
import androidx.compose.animation.core.AnimationVector;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\b\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BA\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u00002\u0006\u0010$\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010%J-\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u00002\u0006\u0010$\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J-\u0010*\u001a\u00028\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010#\u001a\u00028\u00002\u0006\u0010$\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J\u0010\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u000bH\u0002J\u0018\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u000bH\u0002J\u0010\u0010/\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\f\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u0016\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001dR\u0012\u0010\u001e\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u001f\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015¨\u00060"}, d2 = {"Landroidx/compose/animation/core/VectorizedMonoSplineKeyframesSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "timestamps", "Landroidx/collection/IntList;", "keyframes", "Landroidx/collection/IntObjectMap;", "Lkotlin/Pair;", "Landroidx/compose/animation/core/Easing;", "durationMillis", "", "delayMillis", "periodicBias", "", "<init>", "(Landroidx/collection/IntList;Landroidx/collection/IntObjectMap;IIF)V", "getDurationMillis", "()I", "getDelayMillis", "valueVector", "Landroidx/compose/animation/core/AnimationVector;", "velocityVector", "times", "", "monoSpline", "Landroidx/compose/animation/core/MonoSpline;", "values", "", "[[F", "lastInitialValue", "lastTargetValue", "init", "", "initialValue", "targetValue", "initialVelocity", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)V", "getValueFromNanos", "playTimeNanos", "", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "getEasing", "index", "getEasedTimeFromIndex", "timeMillis", "findEntryForTimeMillis", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VectorizedMonoSplineKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private final int delayMillis;
    private final int durationMillis;
    private final IntObjectMap<Pair<V, Easing>> keyframes;
    private V lastInitialValue;
    private V lastTargetValue;
    private MonoSpline monoSpline;
    private final float periodicBias;
    private float[] times;
    private final IntList timestamps;
    private V valueVector;
    private float[][] values;
    private V velocityVector;

    public VectorizedMonoSplineKeyframesSpec(IntList intList, IntObjectMap<Pair<V, Easing>> intObjectMap, int i, int i2, float f) {
        this.timestamps = intList;
        this.keyframes = intObjectMap;
        this.durationMillis = i;
        this.delayMillis = i2;
        this.periodicBias = f;
    }

    private final int findEntryForTimeMillis(int timeMillis) {
        int iBinarySearch$default = IntList.binarySearch$default(this.timestamps, timeMillis, 0, 0, 6, null);
        return iBinarySearch$default < -1 ? -(iBinarySearch$default + 2) : iBinarySearch$default;
    }

    private final float getEasedTimeFromIndex(int index, int timeMillis) {
        float f;
        IntList intList = this.timestamps;
        if (index >= intList._size - 1) {
            f = timeMillis;
        } else {
            int i = intList.get(index);
            int i2 = this.timestamps.get(index + 1);
            if (timeMillis != i) {
                Easing easing = getEasing(index);
                float f2 = timeMillis - i;
                float f3 = i2 - i;
                return ((f3 * easing.transform(f2 / f3)) + i) / 1000.0f;
            }
            f = i;
        }
        return f / 1000.0f;
    }

    private final Easing getEasing(int index) {
        Easing easing;
        Pair<V, Easing> pair = this.keyframes.get(this.timestamps.get(index));
        return (pair == null || (easing = (Easing) pair.getSecond()) == null) ? EasingKt.getLinearEasing() : easing;
    }

    private final void init(V initialValue, V targetValue, V initialVelocity) {
        float[] fArr;
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(initialValue);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialVelocity);
            int i = this.timestamps._size;
            float[] fArr2 = new float[i];
            for (int i2 = 0; i2 < i; i2++) {
                fArr2[i2] = this.timestamps.get(i2) / 1000.0f;
            }
            this.times = fArr2;
        }
        if (this.monoSpline != null && Intrinsics.areEqual(this.lastInitialValue, initialValue) && Intrinsics.areEqual(this.lastTargetValue, targetValue)) {
            return;
        }
        boolean zAreEqual = Intrinsics.areEqual(this.lastInitialValue, initialValue);
        boolean zAreEqual2 = Intrinsics.areEqual(this.lastTargetValue, targetValue);
        this.lastInitialValue = initialValue;
        this.lastTargetValue = targetValue;
        int size = initialValue.getSize();
        float[][] fArr3 = this.values;
        if (fArr3 == null) {
            int i3 = this.timestamps._size;
            fArr3 = new float[i3][];
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = this.timestamps.get(i4);
                Pair<V, Easing> pair = this.keyframes.get(i5);
                if (i5 == 0 && pair == null) {
                    fArr = new float[size];
                    for (int i6 = 0; i6 < size; i6++) {
                        fArr[i6] = initialValue.get$animation_core(i6);
                    }
                } else if (i5 == getDurationMillis() && pair == null) {
                    fArr = new float[size];
                    for (int i7 = 0; i7 < size; i7++) {
                        fArr[i7] = targetValue.get$animation_core(i7);
                    }
                } else {
                    pair.getClass();
                    AnimationVector animationVector = (AnimationVector) pair.getFirst();
                    float[] fArr4 = new float[size];
                    for (int i8 = 0; i8 < size; i8++) {
                        fArr4[i8] = animationVector.get$animation_core(i8);
                    }
                    fArr = fArr4;
                }
                fArr3[i4] = fArr;
            }
            this.values = fArr3;
        } else {
            if (!zAreEqual && !this.keyframes.containsKey(0)) {
                int iBinarySearch$default = IntList.binarySearch$default(this.timestamps, 0, 0, 0, 6, null);
                float[] fArr5 = new float[size];
                for (int i9 = 0; i9 < size; i9++) {
                    fArr5[i9] = initialValue.get$animation_core(i9);
                }
                fArr3[iBinarySearch$default] = fArr5;
            }
            if (!zAreEqual2 && !this.keyframes.containsKey(getDurationMillis())) {
                int iBinarySearch$default2 = IntList.binarySearch$default(this.timestamps, getDurationMillis(), 0, 0, 6, null);
                float[] fArr6 = new float[size];
                for (int i10 = 0; i10 < size; i10++) {
                    fArr6[i10] = targetValue.get$animation_core(i10);
                }
                fArr3[iBinarySearch$default2] = fArr6;
            }
        }
        float[] fArr7 = this.times;
        if (fArr7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("times");
            fArr7 = null;
        }
        this.monoSpline = new MonoSpline(fArr7, fArr3, this.periodicBias);
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / AnimationKt.MillisToNanos);
        Pair<V, Easing> pair = this.keyframes.get(iClampPlayTime);
        if (pair != null) {
            return (V) pair.getFirst();
        }
        if (iClampPlayTime >= getDurationMillis()) {
            return targetValue;
        }
        if (iClampPlayTime <= 0) {
            return initialValue;
        }
        init(initialValue, targetValue, initialVelocity);
        int iFindEntryForTimeMillis = findEntryForTimeMillis(iClampPlayTime);
        V v = this.valueVector;
        v.getClass();
        MonoSpline monoSpline = this.monoSpline;
        monoSpline.getClass();
        monoSpline.getPos(getEasedTimeFromIndex(iFindEntryForTimeMillis, iClampPlayTime), v, iFindEntryForTimeMillis);
        return v;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / AnimationKt.MillisToNanos);
        init(initialValue, targetValue, initialVelocity);
        int iFindEntryForTimeMillis = findEntryForTimeMillis(iClampPlayTime);
        V v = this.velocityVector;
        v.getClass();
        MonoSpline monoSpline = this.monoSpline;
        monoSpline.getClass();
        monoSpline.getSlope(getEasedTimeFromIndex(iFindEntryForTimeMillis, iClampPlayTime), v, iFindEntryForTimeMillis);
        return v;
    }
}
