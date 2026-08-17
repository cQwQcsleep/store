package org.w3c.dom.ls;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LSException extends RuntimeException {
    public static final short PARSE_ERR = 81;
    public static final short SERIALIZE_ERR = 82;
    private static final long serialVersionUID = 5371691160978884690L;
    public short code;

    public LSException(short s, String str) {
        super(str);
        this.code = s;
    }
}
