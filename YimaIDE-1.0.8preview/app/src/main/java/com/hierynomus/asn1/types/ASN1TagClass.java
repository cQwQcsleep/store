package com.hierynomus.asn1.types;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public enum ASN1TagClass {
    UNIVERSAL(0),
    APPLICATION(64),
    CONTEXT_SPECIFIC(128),
    PRIVATE(192);

    private int value;

    ASN1TagClass(int i) {
        this.value = i;
    }

    public static ASN1TagClass parseClass(byte b) {
        int i = b & 192;
        for (ASN1TagClass aSN1TagClass : values()) {
            if (aSN1TagClass.value == i) {
                return aSN1TagClass;
            }
        }
        k2d.a("Could not parse ASN.1 Tag Class (should be impossible)");
        return null;
    }

    public int getValue() {
        return this.value;
    }
}
