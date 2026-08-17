package org.slf4j.helpers;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class FormattingTuple {
    public static FormattingTuple NULL = new FormattingTuple(null);
    private final Object[] argArray;
    private final String message;
    private final Throwable throwable;

    public FormattingTuple(String str, Object[] objArr, Throwable th) {
        this.message = str;
        this.throwable = th;
        this.argArray = objArr;
    }

    public Object[] getArgArray() {
        return this.argArray;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public FormattingTuple(String str) {
        this(str, null, null);
    }
}
