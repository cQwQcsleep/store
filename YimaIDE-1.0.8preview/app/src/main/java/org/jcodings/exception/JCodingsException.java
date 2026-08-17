package org.jcodings.exception;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class JCodingsException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public JCodingsException(String str, String str2) {
        super(str.replaceAll("%n", str2));
    }

    public JCodingsException(String str) {
        super(str);
    }

    public JCodingsException(String str, byte[] bArr, int i, int i2) {
        this(str, new String(bArr, i, i2 - i));
    }
}
