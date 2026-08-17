package com.sun.org.apache.xalan.internal.xsltc;

import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.PrintStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProcessorVersion {
    private static int DELTA = 0;
    private static int MAJOR = 1;
    private static int MINOR;

    public static void main(String[] strArr) {
        String str;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder("XSLTC version ");
        sb.append(MAJOR);
        sb.append(Constants.ATTRVAL_THIS);
        sb.append(MINOR);
        if (DELTA > 0) {
            str = Constants.ATTRVAL_THIS + DELTA;
        } else {
            str = "";
        }
        sb.append(str);
        printStream.println(sb.toString());
    }
}
