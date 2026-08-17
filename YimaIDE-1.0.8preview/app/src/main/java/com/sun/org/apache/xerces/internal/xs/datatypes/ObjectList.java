package com.sun.org.apache.xerces.internal.xs.datatypes;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ObjectList extends List<Object> {
    @Override // java.util.Collection, java.util.List, com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList
    boolean contains(Object obj);

    int getLength();

    Object item(int i);
}
