package com.shadow.kotlin.jvm.internal;

import com.shadow.kotlin.io.CloseableKt;
import java.util.Iterator;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ArrayIteratorKt {
    public static final <T> Iterator<T> iterator(T[] tArr) {
        CloseableKt.checkNotNullParameter(tArr, "array");
        return new ArrayIterator(tArr);
    }
}
