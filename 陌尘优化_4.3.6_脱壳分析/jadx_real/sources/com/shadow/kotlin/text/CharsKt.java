package com.shadow.kotlin.text;

import com.shadow.kotlin.ranges.IntRange;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class CharsKt {
    public static void a(int i) {
        IntRange intRange = new IntRange(2, 36, 1);
        if (intRange.getFirst() > i || i > intRange.getLast()) {
            throw new IllegalArgumentException("radix " + i + " was not in valid range " + new IntRange(2, 36, 1));
        }
    }

    public static final boolean equals(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c);
        char upperCase2 = Character.toUpperCase(c2);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }
}
