package com.sun.org.apache.xerces.internal.xs;

import java.util.Map;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSNamedMap extends Map<QName, XSObject> {
    int getLength();

    XSObject item(int i);

    XSObject itemByName(String str, String str2);
}
