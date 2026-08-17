package com.sun.org.apache.xml.internal.dtm;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DTMWSFilter {
    public static final short INHERIT = 3;
    public static final short NOTSTRIP = 1;
    public static final short STRIP = 2;

    short getShouldStripSpace(int i, DTM dtm);
}
