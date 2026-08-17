package org.jcodings;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class Ptr {
    public static final Ptr NULL = new Ptr(0);
    public int p;

    public Ptr(int i) {
        this.p = i;
    }

    public Ptr() {
        this(0);
    }
}
