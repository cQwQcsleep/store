package com.shadow.kotlin.collections;

/* loaded from: /workspace/unpacked/classes2.dex */
abstract class ArraysKt__ArraysJVMKt {
    public static final void copyOfRangeToIndexCheck(int i, int i2) {
        if (i <= i2) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i + ") is greater than size (" + i2 + ").");
    }
}
