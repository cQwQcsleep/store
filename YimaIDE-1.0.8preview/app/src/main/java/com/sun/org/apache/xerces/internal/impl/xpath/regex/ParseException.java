package com.sun.org.apache.xerces.internal.impl.xpath.regex;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParseException extends RuntimeException {
    static final long serialVersionUID = -7012400318097691370L;
    final int location;

    public ParseException(String str, int i) {
        super(str);
        this.location = i;
    }

    public int getLocation() {
        return this.location;
    }
}
