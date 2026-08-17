package com.google.common.collect;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public abstract class ForwardingObject {
    public abstract Object delegate();

    public String toString() {
        return delegate().toString();
    }
}
