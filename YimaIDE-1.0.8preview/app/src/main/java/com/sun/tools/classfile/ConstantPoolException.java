package com.sun.tools.classfile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConstantPoolException extends Exception {
    private static final long serialVersionUID = -2324397349644754565L;
    public final int index;

    public ConstantPoolException(int i) {
        this.index = i;
    }
}
