package org.jdom2;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class JDOMException extends Exception {
    private static final long serialVersionUID = 200;

    public JDOMException() {
        super("Error occurred in JDOM application.");
    }

    public JDOMException(String str) {
        super(str);
    }

    public JDOMException(String str, Throwable th) {
        super(str, th);
    }
}
