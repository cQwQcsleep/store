package org.jcodings.exception;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class TranscoderException extends JCodingsException {
    private static final long serialVersionUID = 1;

    public TranscoderException(String str) {
        super(str);
    }

    public TranscoderException(String str, String str2) {
        super(str, str2);
    }

    public TranscoderException(String str, byte[] bArr, int i, int i2) {
        super(str, bArr, i, i2);
    }
}
