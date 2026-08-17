package com.sun.org.apache.xerces.internal.xs;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSObjectList extends List<XSObject> {
    int getLength();

    XSObject item(int i);
}
