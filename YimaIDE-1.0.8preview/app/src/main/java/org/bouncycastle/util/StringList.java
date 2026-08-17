package org.bouncycastle.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface StringList extends Iterable<String> {
    boolean add(String str);

    String get(int i);

    int size();

    String[] toStringArray();

    String[] toStringArray(int i, int i2);
}
