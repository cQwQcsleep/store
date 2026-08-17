package com.android.tools.r8.internal;

import java.util.ListIterator;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1155bY extends ListIterator {
    default void c(Predicate predicate) {
        while (hasPrevious() && !predicate.test(previous())) {
        }
    }
}
