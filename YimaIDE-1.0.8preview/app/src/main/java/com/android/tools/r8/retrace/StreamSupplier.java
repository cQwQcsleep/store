package com.android.tools.r8.retrace;

import java.lang.Throwable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface StreamSupplier<E extends Throwable> {
    String getNext() throws Throwable;
}
