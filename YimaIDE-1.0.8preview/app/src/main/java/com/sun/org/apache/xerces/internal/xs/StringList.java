package com.sun.org.apache.xerces.internal.xs;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface StringList extends List<String> {
    boolean contains(String str);

    int getLength();

    String item(int i);
}
