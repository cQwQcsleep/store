package com.sun.tools.javac.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LayoutCharacters {
    public static final byte CR = 13;
    public static final int DetailsInc = 2;
    public static final int DiagInc = 4;
    public static final byte EOI = 26;
    public static final byte FF = 12;
    public static final byte LF = 10;
    public static final byte TAB = 9;
    public static final int TabInc = 8;

    static int tabulate(int i) {
        return ((i / 8) * 8) + 8;
    }
}
