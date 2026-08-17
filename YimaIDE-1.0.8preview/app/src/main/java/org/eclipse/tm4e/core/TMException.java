package org.eclipse.tm4e.core;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class TMException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public TMException(String str) {
        super(str);
    }

    public TMException(String str, Throwable th) {
        super(str, th);
    }
}
