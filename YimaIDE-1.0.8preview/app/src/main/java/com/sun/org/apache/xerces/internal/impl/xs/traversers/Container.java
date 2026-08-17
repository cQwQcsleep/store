package com.sun.org.apache.xerces.internal.impl.xs.traversers;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class Container {
    static final int THRESHOLD = 5;
    int pos = 0;
    OneAttr[] values;

    public static Container getContainer(int i) {
        return i > 5 ? new LargeContainer(i) : new SmallContainer(i);
    }

    public abstract OneAttr get(String str);

    public abstract void put(String str, OneAttr oneAttr);
}
