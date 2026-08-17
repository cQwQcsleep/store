package com.hierynomus.asn1;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class ASN1ParseException extends RuntimeException {
    public ASN1ParseException(Throwable th, String str, Object... objArr) {
        super(String.format(str, objArr), th);
    }

    public ASN1ParseException(String str) {
        super(str);
    }

    public ASN1ParseException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }
}
