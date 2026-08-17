package com.sun.org.apache.xerces.internal.impl.xs.identity;

import com.sun.org.apache.xerces.internal.xs.ShortList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ValueStore {
    void addValue(Field field, boolean z, Object obj, short s, ShortList shortList);

    void reportError(String str, Object[] objArr);
}
