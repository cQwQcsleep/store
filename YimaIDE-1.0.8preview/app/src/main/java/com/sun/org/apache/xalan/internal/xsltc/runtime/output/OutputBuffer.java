package com.sun.org.apache.xalan.internal.xsltc.runtime.output;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
interface OutputBuffer {
    OutputBuffer append(char c);

    OutputBuffer append(String str);

    OutputBuffer append(char[] cArr, int i, int i2);

    String close();
}
