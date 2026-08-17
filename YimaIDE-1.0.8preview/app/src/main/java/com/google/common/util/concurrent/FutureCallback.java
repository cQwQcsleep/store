package com.google.common.util.concurrent;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface FutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(V v);
}
