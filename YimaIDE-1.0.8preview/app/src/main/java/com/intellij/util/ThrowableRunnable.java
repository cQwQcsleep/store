package com.intellij.util;

import java.lang.Throwable;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface ThrowableRunnable<T extends Throwable> {
    void run() throws Throwable;
}
