package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSAnnotation extends XSObject {
    public static final short SAX_CONTENTHANDLER = 2;
    public static final short W3C_DOM_DOCUMENT = 3;
    public static final short W3C_DOM_ELEMENT = 1;

    String getAnnotationString();

    boolean writeAnnotation(Object obj, short s);
}
