package com.sun.org.apache.xerces.internal.xpointer;

import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XNIException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XPointerPart {
    public static final int EVENT_ELEMENT_EMPTY = 2;
    public static final int EVENT_ELEMENT_END = 1;
    public static final int EVENT_ELEMENT_START = 0;

    String getSchemeData();

    String getSchemeName();

    boolean isChildFragmentResolved() throws XNIException;

    boolean isFragmentResolved() throws XNIException;

    void parseXPointer(String str) throws XNIException;

    boolean resolveXPointer(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, int i) throws XNIException;

    void setSchemeData(String str);

    void setSchemeName(String str);
}
