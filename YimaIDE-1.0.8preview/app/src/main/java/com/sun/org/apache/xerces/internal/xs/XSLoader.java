package com.sun.org.apache.xerces.internal.xs;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.ls.LSInput;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSLoader {
    DOMConfiguration getConfig();

    XSModel load(LSInput lSInput);

    XSModel loadInputList(LSInputList lSInputList);

    XSModel loadURI(String str);

    XSModel loadURIList(StringList stringList);
}
