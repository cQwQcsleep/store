package org.bouncycastle.asn1;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ASN1ParsingException extends IllegalStateException {
    private Throwable cause;

    public ASN1ParsingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ASN1ParsingException(String str) {
        super(str);
    }
}
