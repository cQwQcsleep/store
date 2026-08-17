package com.sun.org.apache.bcel.internal.classfile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassFormatException extends RuntimeException {
    private static final long serialVersionUID = -3569097343160139969L;

    public ClassFormatException() {
    }

    public ClassFormatException(String str) {
        super(str);
    }

    public ClassFormatException(String str, Throwable th) {
        super(str, th);
    }

    public ClassFormatException(Throwable th) {
        super(th);
    }
}
