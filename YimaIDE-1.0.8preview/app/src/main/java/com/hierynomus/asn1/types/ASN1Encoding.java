package com.hierynomus.asn1.types;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public enum ASN1Encoding {
    PRIMITIVE(0),
    CONSTRUCTED(32);

    private int value;

    ASN1Encoding(int i) {
        this.value = i;
    }

    public static ASN1Encoding parseEncoding(byte b) {
        return (b & 32) == 0 ? PRIMITIVE : CONSTRUCTED;
    }

    public int getValue() {
        return this.value;
    }
}
