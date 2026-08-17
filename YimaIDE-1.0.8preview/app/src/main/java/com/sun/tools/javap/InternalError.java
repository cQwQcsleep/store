package com.sun.tools.javap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InternalError extends Error {
    private static final long serialVersionUID = 8114054446416187030L;
    public final Object[] args;

    public InternalError(Throwable th, Object... objArr) {
        super("Internal error", th);
        this.args = objArr;
    }

    public InternalError(Object... objArr) {
        super("Internal error");
        this.args = objArr;
    }
}
