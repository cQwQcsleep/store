package com.android.tools.r8.internal;

import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xj0 extends RuntimeException {
    public final ExecutionException b;

    public Xj0(ExecutionException executionException) {
        super(executionException);
        this.b = executionException;
    }
}
