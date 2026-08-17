package com.sun.org.apache.xalan.internal.xsltc.runtime;

import javax.xml.transform.ErrorListener;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MessageHandler {
    public void displayMessage(String str) {
        System.err.println(str);
    }

    public ErrorListener getErrorListener() {
        return null;
    }
}
