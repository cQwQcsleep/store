package com.sun.org.apache.xml.internal.dtm;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DTMAxisTraverser {
    public int first(int i) {
        return next(i, i);
    }

    public abstract int next(int i, int i2);

    public abstract int next(int i, int i2, int i3);

    public int first(int i, int i2) {
        return next(i, i, i2);
    }
}
