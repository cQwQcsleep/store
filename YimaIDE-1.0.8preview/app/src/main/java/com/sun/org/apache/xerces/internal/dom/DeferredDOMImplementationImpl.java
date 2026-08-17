package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.DOMImplementation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredDOMImplementationImpl extends DOMImplementationImpl {
    static DeferredDOMImplementationImpl singleton = new DeferredDOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }
}
