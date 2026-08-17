package com.intellij.util.io;

import java.io.Closeable;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ResourceHandle<T> implements Closeable {
    public abstract T get();
}
