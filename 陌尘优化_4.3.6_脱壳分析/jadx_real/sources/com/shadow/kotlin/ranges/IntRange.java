package com.shadow.kotlin.ranges;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class IntRange extends kotlin.ranges.IntProgression {
    private static final IntRange EMPTY = new IntRange(1, 0, 1);

    public final boolean equals(Object obj) {
        if (obj instanceof IntRange) {
            if (!isEmpty() || !((IntRange) obj).isEmpty()) {
                IntRange intRange = (IntRange) obj;
                if (getFirst() != intRange.getFirst() || getLast() != intRange.getLast()) {
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (getFirst() * 31) + getLast();
    }

    public final boolean isEmpty() {
        return getFirst() > getLast();
    }

    public final String toString() {
        return getFirst() + ".." + getLast();
    }
}
