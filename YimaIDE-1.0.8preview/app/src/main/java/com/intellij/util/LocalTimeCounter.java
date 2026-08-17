package com.intellij.util;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public final class LocalTimeCounter {
    private static final AtomicInteger ourCurrentTime = new AtomicInteger();

    public static long currentTime() {
        return ourCurrentTime.incrementAndGet() & 16777215;
    }
}
