package com.sun.org.apache.xerces.internal.xs.datatypes;

import com.sun.org.apache.xerces.internal.xs.XSException;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ByteList extends List<Byte> {
    boolean contains(byte b);

    int getLength();

    byte item(int i) throws XSException;

    byte[] toByteArray();
}
