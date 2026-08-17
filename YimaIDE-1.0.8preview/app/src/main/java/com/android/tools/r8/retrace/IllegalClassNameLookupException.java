package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IllegalClassNameLookupException extends RuntimeException {
    private final String b;

    public IllegalClassNameLookupException(String str) {
        this.b = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Illegal lookup of " + this.b + ".";
    }
}
