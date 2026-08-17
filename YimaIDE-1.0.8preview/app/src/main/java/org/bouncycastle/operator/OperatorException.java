package org.bouncycastle.operator;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class OperatorException extends Exception {
    private Throwable cause;

    public OperatorException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public OperatorException(String str) {
        super(str);
    }
}
