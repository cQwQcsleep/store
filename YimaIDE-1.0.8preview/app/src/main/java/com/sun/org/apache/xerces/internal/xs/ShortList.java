package com.sun.org.apache.xerces.internal.xs;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ShortList extends List<Short> {
    boolean contains(short s);

    int getLength();

    short item(int i) throws XSException;
}
