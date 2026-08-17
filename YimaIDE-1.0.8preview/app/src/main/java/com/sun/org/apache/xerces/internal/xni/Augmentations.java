package com.sun.org.apache.xerces.internal.xni;

import java.util.Enumeration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Augmentations {
    Object getItem(String str);

    Enumeration<Object> keys();

    Object putItem(String str, Object obj);

    void removeAllItems();

    Object removeItem(String str);
}
