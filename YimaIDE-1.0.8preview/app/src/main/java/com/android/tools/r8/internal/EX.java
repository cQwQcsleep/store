package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface EX extends Predicate {
    boolean apply(Object obj);

    boolean equals(Object obj);

    @Override // java.util.function.Predicate
    default boolean test(Object obj) {
        return apply(obj);
    }
}
