package org.bouncycastle.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Memoable {
    Memoable copy();

    void reset(Memoable memoable);
}
