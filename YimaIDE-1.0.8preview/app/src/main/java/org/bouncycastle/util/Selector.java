package org.bouncycastle.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Selector<T> extends Cloneable {
    Object clone();

    boolean match(T t);
}
