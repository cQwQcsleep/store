package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XNIException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XMLErrorHandler {
    void error(String str, String str2, XMLParseException xMLParseException) throws XNIException;

    void fatalError(String str, String str2, XMLParseException xMLParseException) throws XNIException;

    void warning(String str, String str2, XMLParseException xMLParseException) throws XNIException;
}
