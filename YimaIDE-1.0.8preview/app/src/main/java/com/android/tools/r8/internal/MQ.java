package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MQ<T> extends Iterator<T> {
    default <S extends T> S a(Predicate<T> predicate) {
        while (hasNext()) {
            T next = next();
            if (predicate.test(next)) {
                return next;
            }
        }
        return null;
    }
}
