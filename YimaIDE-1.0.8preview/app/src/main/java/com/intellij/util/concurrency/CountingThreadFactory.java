package com.intellij.util.concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class CountingThreadFactory implements ThreadFactory {
    protected final AtomicInteger counter = new AtomicInteger();
}
