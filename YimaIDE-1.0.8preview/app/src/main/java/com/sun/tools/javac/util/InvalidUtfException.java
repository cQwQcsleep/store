package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InvalidUtfException extends Exception {
    private static final long serialVersionUID = 0;
    private final int offset;

    public InvalidUtfException(int i) {
        this.offset = i;
    }

    public int getOffset() {
        return this.offset;
    }
}
