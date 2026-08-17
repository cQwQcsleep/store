package androidx.compose.animation.core;

import androidx.collection.IntList;
import androidx.collection.IntObjectMap;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.animation.core.AnimationVector;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BE\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011B;\b\u0016\u0012\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\r0\u00130\u0012\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0010\u0010\u0014J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010+J-\u0010,\u001a\u00028\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010/J-\u00100\u001a\u00028\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010(\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010/J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\nH\u0002J \u00104\u001a\u0002022\u0006\u00105\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u000b\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010\u001f\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010 \u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010!\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\"\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Landroidx/compose/animation/core/VectorizedKeyframesSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "timestamps", "Landroidx/collection/IntList;", "keyframes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/animation/core/VectorizedKeyframeSpecElementInfo;", "durationMillis", "", "delayMillis", "defaultEasing", "Landroidx/compose/animation/core/Easing;", "initialArcMode", "Landroidx/compose/animation/core/ArcMode;", "<init>", "(Landroidx/collection/IntList;Landroidx/collection/IntObjectMap;IILandroidx/compose/animation/core/Easing;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "", "Lkotlin/Pair;", "(Ljava/util/Map;II)V", "getDurationMillis", "()I", "getDelayMillis", "I", "modes", "", "times", "", "valueVector", "Landroidx/compose/animation/core/AnimationVector;", "velocityVector", "lastInitialValue", "lastTargetValue", "posArray", "slopeArray", "arcSpline", "Landroidx/compose/animation/core/ArcSpline;", "init", "", "initialValue", "targetValue", "initialVelocity", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)V", "getValueFromNanos", "playTimeNanos", "", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "getEasedTime", "", "timeMillis", "getEasedTimeFromIndex", "index", "asFraction", "", "findEntryForTimeMillis", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VectorizedKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private ArcSpline arcSpline;
    private final Easing defaultEasing;
    private final int delayMillis;
    private final int durationMillis;
    private final int initialArcMode;
    private final IntObjectMap<VectorizedKeyframeSpecElementInfo<V>> keyframes;
    private V lastInitialValue;
    private V lastTargetValue;
    private int[] modes;
    private float[] posArray;
    private float[] slopeArray;
    private float[] times;
    private final IntList timestamps;
    private V valueVector;
    private V velocityVector;

    public VectorizedKeyframesSpec(Map<Integer, ? extends Pair<? extends V, ? extends Easing>> map, int i, int i2) {
        MutableIntList mutableIntList = new MutableIntList(map.size() + 2);
        Iterator<Map.Entry<Integer, ? extends Pair<? extends V, ? extends Easing>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            mutableIntList.add(it.next().getKey().intValue());
        }
        if (!map.containsKey(0)) {
            mutableIntList.add(0, 0);
        }
        if (!map.containsKey(Integer.valueOf(i))) {
            mutableIntList.add(i);
        }
        mutableIntList.sort();
        MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(0, 1, null);
        for (Map.Entry<Integer, ? extends Pair<? extends V, ? extends Easing>> entry : map.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            Pair<? extends V, ? extends Easing> value = entry.getValue();
            mutableIntObjectMap.set(iIntValue, new VectorizedKeyframeSpecElementInfo((AnimationVector) value.getFirst(), (Easing) value.getSecond(), ArcMode.INSTANCE.m224getArcLinear9TMq4(), null));
        }
        this(mutableIntList, mutableIntObjectMap, i, i2, EasingKt.getLinearEasing(), ArcMode.INSTANCE.m224getArcLinear9TMq4(), null);
    }

    private final int findEntryForTimeMillis(int timeMillis) {
        int iBinarySearch$default = IntList.binarySearch$default(this.timestamps, timeMillis, 0, 0, 6, null);
        return iBinarySearch$default < -1 ? -(iBinarySearch$default + 2) : iBinarySearch$default;
    }

    private final float getEasedTime(int timeMillis) {
        return getEasedTimeFromIndex(findEntryForTimeMillis(timeMillis), timeMillis, false);
    }

    private final float getEasedTimeFromIndex(int index, int timeMillis, boolean asFraction) {
        Easing easing;
        float f;
        IntList intList = this.timestamps;
        if (index >= intList._size - 1) {
            f = timeMillis;
        } else {
            int i = intList.get(index);
            int i2 = this.timestamps.get(index + 1);
            if (timeMillis != i) {
                int i3 = i2 - i;
                VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo = this.keyframes.get(i);
                if (vectorizedKeyframeSpecElementInfo == null || (easing = vectorizedKeyframeSpecElementInfo.getEasing()) == null) {
                    easing = this.defaultEasing;
                }
                float f2 = i3;
                float fTransform = easing.transform((timeMillis - i) / f2);
                return asFraction ? fTransform : ((f2 * fTransform) + i) / 1000.0f;
            }
            f = i;
        }
        return f / 1000.0f;
    }

    private final void init(V initialValue, V targetValue, V initialVelocity) {
        float[] fArr;
        boolean z = this.arcSpline != VectorizedAnimationSpecKt.EmptyArcSpline;
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(initialValue);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialVelocity);
            int i = this.timestamps._size;
            float[] fArr2 = new float[i];
            for (int i2 = 0; i2 < i; i2++) {
                fArr2[i2] = this.timestamps.get(i2) / 1000.0f;
            }
            this.times = fArr2;
            int i3 = this.timestamps._size;
            int[] iArr = new int[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo = this.keyframes.get(this.timestamps.get(i4));
                int iM264getArcMode9TMq4 = vectorizedKeyframeSpecElementInfo != null ? vectorizedKeyframeSpecElementInfo.m264getArcMode9TMq4() : this.initialArcMode;
                if (!ArcMode.m218equalsimpl0(iM264getArcMode9TMq4, ArcMode.INSTANCE.m224getArcLinear9TMq4())) {
                    z = true;
                }
                iArr[i4] = iM264getArcMode9TMq4;
            }
            this.modes = iArr;
        }
        if (z) {
            if (this.arcSpline != VectorizedAnimationSpecKt.EmptyArcSpline && Intrinsics.areEqual(this.lastInitialValue, initialValue) && Intrinsics.areEqual(this.lastTargetValue, targetValue)) {
                return;
            }
            this.lastInitialValue = initialValue;
            this.lastTargetValue = targetValue;
            int size = (initialValue.getSize() % 2) + initialValue.getSize();
            this.posArray = new float[size];
            this.slopeArray = new float[size];
            int i5 = this.timestamps._size;
            float[][] fArr3 = new float[i5][];
            for (int i6 = 0; i6 < i5; i6++) {
                int i7 = this.timestamps.get(i6);
                VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo2 = this.keyframes.get(i7);
                if (i7 == 0 && vectorizedKeyframeSpecElementInfo2 == null) {
                    fArr = new float[size];
                    for (int i8 = 0; i8 < size; i8++) {
                        fArr[i8] = initialValue.get$animation_core(i8);
                    }
                } else if (i7 == getDurationMillis() && vectorizedKeyframeSpecElementInfo2 == null) {
                    fArr = new float[size];
                    for (int i9 = 0; i9 < size; i9++) {
                        fArr[i9] = targetValue.get$animation_core(i9);
                    }
                } else {
                    vectorizedKeyframeSpecElementInfo2.getClass();
                    AnimationVector vectorValue = vectorizedKeyframeSpecElementInfo2.getVectorValue();
                    float[] fArr4 = new float[size];
                    for (int i10 = 0; i10 < size; i10++) {
                        fArr4[i10] = vectorValue.get$animation_core(i10);
                    }
                    fArr = fArr4;
                }
                fArr3[i6] = fArr;
            }
            this.arcSpline = new ArcSpline(this.modes, this.times, fArr3);
        }
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
        AnimationVector vectorValue;
        AnimationVector vectorValue2;
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / AnimationKt.MillisToNanos);
        VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo = this.keyframes.get(iClampPlayTime);
        if (vectorizedKeyframeSpecElementInfo != null) {
            return (V) vectorizedKeyframeSpecElementInfo.getVectorValue();
        }
        if (iClampPlayTime >= getDurationMillis()) {
            return targetValue;
        }
        if (iClampPlayTime <= 0) {
            return initialValue;
        }
        init(initialValue, targetValue, initialVelocity);
        V v = this.valueVector;
        v.getClass();
        int i = 0;
        if (this.arcSpline != VectorizedAnimationSpecKt.EmptyArcSpline) {
            float easedTime = getEasedTime(iClampPlayTime);
            float[] fArr = this.posArray;
            this.arcSpline.getPos(easedTime, fArr);
            int length = fArr.length;
            while (i < length) {
                v.set$animation_core(i, fArr[i]);
                i++;
            }
            return v;
        }
        int iFindEntryForTimeMillis = findEntryForTimeMillis(iClampPlayTime);
        float easedTimeFromIndex = getEasedTimeFromIndex(iFindEntryForTimeMillis, iClampPlayTime, true);
        VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo2 = this.keyframes.get(this.timestamps.get(iFindEntryForTimeMillis));
        if (vectorizedKeyframeSpecElementInfo2 != null && (vectorValue2 = vectorizedKeyframeSpecElementInfo2.getVectorValue()) != null) {
            initialValue = (V) vectorValue2;
        }
        VectorizedKeyframeSpecElementInfo<V> vectorizedKeyframeSpecElementInfo3 = this.keyframes.get(this.timestamps.get(iFindEntryForTimeMillis + 1));
        if (vectorizedKeyframeSpecElementInfo3 != null && (vectorValue = vectorizedKeyframeSpecElementInfo3.getVectorValue()) != null) {
            targetValue = (V) vectorValue;
        }
        int size = v.getSize();
        while (i < size) {
            v.set$animation_core(i, (initialValue.get$animation_core(i) * (1.0f - easedTimeFromIndex)) + (targetValue.get$animation_core(i) * easedTimeFromIndex));
            i++;
        }
        return v;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        long jClampPlayTime = VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / AnimationKt.MillisToNanos);
        if (jClampPlayTime < 0) {
            return initialVelocity;
        }
        init(initialValue, targetValue, initialVelocity);
        V v = this.velocityVector;
        v.getClass();
        int i = 0;
        if (this.arcSpline != VectorizedAnimationSpecKt.EmptyArcSpline) {
            float easedTime = getEasedTime((int) jClampPlayTime);
            float[] fArr = this.slopeArray;
            this.arcSpline.getSlope(easedTime, fArr);
            int length = fArr.length;
            while (i < length) {
                v.set$animation_core(i, fArr[i]);
                i++;
            }
        } else {
            AnimationVector valueFromMillis = VectorizedAnimationSpecKt.getValueFromMillis(this, jClampPlayTime - 1, initialValue, targetValue, initialVelocity);
            AnimationVector valueFromMillis2 = VectorizedAnimationSpecKt.getValueFromMillis(this, jClampPlayTime, initialValue, targetValue, initialVelocity);
            int size = valueFromMillis.getSize();
            while (i < size) {
                v.set$animation_core(i, (valueFromMillis.get$animation_core(i) - valueFromMillis2.get$animation_core(i)) * 1000.0f);
                i++;
            }
        }
        return v;
    }

    private VectorizedKeyframesSpec(IntList intList, IntObjectMap<VectorizedKeyframeSpecElementInfo<V>> intObjectMap, int i, int i2, Easing easing, int i3) {
        this.timestamps = intList;
        this.keyframes = intObjectMap;
        this.durationMillis = i;
        this.delayMillis = i2;
        this.defaultEasing = easing;
        this.initialArcMode = i3;
        this.modes = VectorizedAnimationSpecKt.EmptyIntArray;
        this.times = VectorizedAnimationSpecKt.EmptyFloatArray;
        this.posArray = VectorizedAnimationSpecKt.EmptyFloatArray;
        this.slopeArray = VectorizedAnimationSpecKt.EmptyFloatArray;
        this.arcSpline = VectorizedAnimationSpecKt.EmptyArcSpline;
    }

    public /* synthetic */ VectorizedKeyframesSpec(Map map, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, i, (i3 & 4) != 0 ? 0 : i2);
    }

    public /* synthetic */ VectorizedKeyframesSpec(IntList intList, IntObjectMap intObjectMap, int i, int i2, Easing easing, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(intList, intObjectMap, i, i2, easing, i3);
    }
}
