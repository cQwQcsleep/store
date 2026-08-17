package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0916Vw extends Iterator<AbstractC0890Uw>, MQ<AbstractC0890Uw> {
    boolean hasPrevious();

    default AbstractC0890Uw i() {
        if (!hasPrevious()) {
            return null;
        }
        AbstractC0890Uw abstractC0890UwPrevious = previous();
        next();
        return abstractC0890UwPrevious;
    }

    default AbstractC0890Uw m() {
        if (!hasNext()) {
            return null;
        }
        AbstractC0890Uw next = next();
        previous();
        return next;
    }

    AbstractC0890Uw previous();

    @Override // java.util.Iterator, java.util.ListIterator
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
