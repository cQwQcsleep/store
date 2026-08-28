package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.io.CloseableKt;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ListBuilderKt {
    public static final <E> void resetRange(E[] eArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(eArr, "<this>");
        while (i < i2) {
            eArr[i] = null;
            i++;
        }
    }
}
