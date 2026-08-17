package org.jcodings.exception;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class EncodingException extends JCodingsException {
    private static final long serialVersionUID = 1;
    private final EncodingError error;

    public EncodingException(EncodingError encodingError) {
        super(encodingError.getMessage());
        this.error = encodingError;
    }

    public EncodingError getError() {
        return this.error;
    }

    public EncodingException(EncodingError encodingError, String str) {
        super(encodingError.getMessage());
        this.error = encodingError;
    }

    public EncodingException(EncodingError encodingError, byte[] bArr, int i, int i2) {
        super(encodingError.getMessage(), bArr, i, i2);
        this.error = encodingError;
    }

    @Deprecated
    public EncodingException(String str) {
        super(str);
        this.error = null;
    }

    @Deprecated
    public EncodingException(String str, String str2) {
        super(str, str2);
        this.error = null;
    }

    @Deprecated
    public EncodingException(String str, byte[] bArr, int i, int i2) {
        super(str, bArr, i, i2);
        this.error = null;
    }
}
