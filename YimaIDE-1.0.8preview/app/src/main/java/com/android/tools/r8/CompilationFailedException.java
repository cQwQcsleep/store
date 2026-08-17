package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CompilationFailedException extends Exception {
    private final boolean b;

    public CompilationFailedException(String str) {
        super(str);
        this.b = false;
    }

    public boolean wasCancelled() {
        return this.b;
    }

    public CompilationFailedException(String str, Throwable th, boolean z) {
        super(str, th);
        this.b = z;
    }
}
