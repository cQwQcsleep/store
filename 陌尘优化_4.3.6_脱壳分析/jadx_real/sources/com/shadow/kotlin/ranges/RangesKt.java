package com.shadow.kotlin.ranges;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.markers.KMappedMarker;
import com.shadow.kotlin.jvm.internal.markers.KMutableList;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class RangesKt {
    public static IntProgression a(IntRange intRange, int i) {
        CloseableKt.checkNotNullParameter(intRange, "<this>");
        boolean z = i > 0;
        Integer numValueOf = Integer.valueOf(i);
        if (!z) {
            throw new IllegalArgumentException("Step must be positive, was: " + numValueOf + '.');
        }
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (intRange.getStep() <= 0) {
            i = -i;
        }
        return new IntProgression(first, last, i);
    }

    public static List asMutableList(Object obj) {
        if (!(obj instanceof KMappedMarker) || (obj instanceof KMutableList)) {
            try {
                return (List) obj;
            } catch (ClassCastException e) {
                CloseableKt.sanitizeStackTrace(e, RangesKt.class.getName());
                throw e;
            }
        }
        ClassCastException classCastException = new ClassCastException((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to kotlin.collections.MutableList");
        CloseableKt.sanitizeStackTrace(classCastException, RangesKt.class.getName());
        throw classCastException;
    }

    public static IntRange b(int i, int i2) {
        return i2 <= Integer.MIN_VALUE ? IntRange.EMPTY : new IntRange(i, i2 - 1, 1);
    }
}
