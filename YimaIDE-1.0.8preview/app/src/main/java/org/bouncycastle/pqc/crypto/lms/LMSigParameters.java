package org.bouncycastle.pqc.crypto.lms;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class LMSigParameters {
    public static final LMSigParameters lms_sha256_n24_h10;
    public static final LMSigParameters lms_sha256_n24_h15;
    public static final LMSigParameters lms_sha256_n24_h20;
    public static final LMSigParameters lms_sha256_n24_h25;
    public static final LMSigParameters lms_sha256_n24_h5;
    public static final LMSigParameters lms_sha256_n32_h10;
    public static final LMSigParameters lms_sha256_n32_h15;
    public static final LMSigParameters lms_sha256_n32_h20;
    public static final LMSigParameters lms_sha256_n32_h25;
    public static final LMSigParameters lms_sha256_n32_h5;
    public static final LMSigParameters lms_shake256_n24_h10;
    public static final LMSigParameters lms_shake256_n24_h15;
    public static final LMSigParameters lms_shake256_n24_h20;
    public static final LMSigParameters lms_shake256_n24_h25;
    public static final LMSigParameters lms_shake256_n24_h5;
    public static final LMSigParameters lms_shake256_n32_h10;
    public static final LMSigParameters lms_shake256_n32_h15;
    public static final LMSigParameters lms_shake256_n32_h20;
    public static final LMSigParameters lms_shake256_n32_h25;
    public static final LMSigParameters lms_shake256_n32_h5;
    private static Map<Object, LMSigParameters> paramBuilders;
    private final ASN1ObjectIdentifier digestOid;
    private final int h;
    private final int m;
    private final int type;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = NISTObjectIdentifiers.id_sha256;
        lms_sha256_n32_h5 = new LMSigParameters(5, 32, 5, aSN1ObjectIdentifier);
        lms_sha256_n32_h10 = new LMSigParameters(6, 32, 10, aSN1ObjectIdentifier);
        lms_sha256_n32_h15 = new LMSigParameters(7, 32, 15, aSN1ObjectIdentifier);
        lms_sha256_n32_h20 = new LMSigParameters(8, 32, 20, aSN1ObjectIdentifier);
        lms_sha256_n32_h25 = new LMSigParameters(9, 32, 25, aSN1ObjectIdentifier);
        lms_sha256_n24_h5 = new LMSigParameters(10, 24, 5, aSN1ObjectIdentifier);
        lms_sha256_n24_h10 = new LMSigParameters(11, 24, 10, aSN1ObjectIdentifier);
        lms_sha256_n24_h15 = new LMSigParameters(12, 24, 15, aSN1ObjectIdentifier);
        lms_sha256_n24_h20 = new LMSigParameters(13, 24, 20, aSN1ObjectIdentifier);
        lms_sha256_n24_h25 = new LMSigParameters(14, 24, 25, aSN1ObjectIdentifier);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_shake256_len;
        lms_shake256_n32_h5 = new LMSigParameters(15, 32, 5, aSN1ObjectIdentifier2);
        lms_shake256_n32_h10 = new LMSigParameters(16, 32, 10, aSN1ObjectIdentifier2);
        lms_shake256_n32_h15 = new LMSigParameters(17, 32, 15, aSN1ObjectIdentifier2);
        lms_shake256_n32_h20 = new LMSigParameters(18, 32, 20, aSN1ObjectIdentifier2);
        lms_shake256_n32_h25 = new LMSigParameters(19, 32, 25, aSN1ObjectIdentifier2);
        lms_shake256_n24_h5 = new LMSigParameters(20, 24, 5, aSN1ObjectIdentifier2);
        lms_shake256_n24_h10 = new LMSigParameters(21, 24, 10, aSN1ObjectIdentifier2);
        lms_shake256_n24_h15 = new LMSigParameters(22, 24, 15, aSN1ObjectIdentifier2);
        lms_shake256_n24_h20 = new LMSigParameters(23, 24, 20, aSN1ObjectIdentifier2);
        lms_shake256_n24_h25 = new LMSigParameters(24, 24, 25, aSN1ObjectIdentifier2);
        paramBuilders = new HashMap<Object, LMSigParameters>() { // from class: org.bouncycastle.pqc.crypto.lms.LMSigParameters.1
            {
                LMSigParameters lMSigParameters = LMSigParameters.lms_sha256_n32_h5;
                put(Integer.valueOf(lMSigParameters.type), lMSigParameters);
                LMSigParameters lMSigParameters2 = LMSigParameters.lms_sha256_n32_h10;
                put(Integer.valueOf(lMSigParameters2.type), lMSigParameters2);
                LMSigParameters lMSigParameters3 = LMSigParameters.lms_sha256_n32_h15;
                put(Integer.valueOf(lMSigParameters3.type), lMSigParameters3);
                LMSigParameters lMSigParameters4 = LMSigParameters.lms_sha256_n32_h20;
                put(Integer.valueOf(lMSigParameters4.type), lMSigParameters4);
                LMSigParameters lMSigParameters5 = LMSigParameters.lms_sha256_n32_h25;
                put(Integer.valueOf(lMSigParameters5.type), lMSigParameters5);
                LMSigParameters lMSigParameters6 = LMSigParameters.lms_sha256_n24_h5;
                put(Integer.valueOf(lMSigParameters6.type), lMSigParameters6);
                LMSigParameters lMSigParameters7 = LMSigParameters.lms_sha256_n24_h10;
                put(Integer.valueOf(lMSigParameters7.type), lMSigParameters7);
                LMSigParameters lMSigParameters8 = LMSigParameters.lms_sha256_n24_h15;
                put(Integer.valueOf(lMSigParameters8.type), lMSigParameters8);
                LMSigParameters lMSigParameters9 = LMSigParameters.lms_sha256_n24_h20;
                put(Integer.valueOf(lMSigParameters9.type), lMSigParameters9);
                LMSigParameters lMSigParameters10 = LMSigParameters.lms_sha256_n24_h25;
                put(Integer.valueOf(lMSigParameters10.type), lMSigParameters10);
                LMSigParameters lMSigParameters11 = LMSigParameters.lms_shake256_n32_h5;
                put(Integer.valueOf(lMSigParameters11.type), lMSigParameters11);
                LMSigParameters lMSigParameters12 = LMSigParameters.lms_shake256_n32_h10;
                put(Integer.valueOf(lMSigParameters12.type), lMSigParameters12);
                LMSigParameters lMSigParameters13 = LMSigParameters.lms_shake256_n32_h15;
                put(Integer.valueOf(lMSigParameters13.type), lMSigParameters13);
                LMSigParameters lMSigParameters14 = LMSigParameters.lms_shake256_n32_h20;
                put(Integer.valueOf(lMSigParameters14.type), lMSigParameters14);
                LMSigParameters lMSigParameters15 = LMSigParameters.lms_shake256_n32_h25;
                put(Integer.valueOf(lMSigParameters15.type), lMSigParameters15);
                LMSigParameters lMSigParameters16 = LMSigParameters.lms_shake256_n24_h5;
                put(Integer.valueOf(lMSigParameters16.type), lMSigParameters16);
                LMSigParameters lMSigParameters17 = LMSigParameters.lms_shake256_n24_h10;
                put(Integer.valueOf(lMSigParameters17.type), lMSigParameters17);
                LMSigParameters lMSigParameters18 = LMSigParameters.lms_shake256_n24_h15;
                put(Integer.valueOf(lMSigParameters18.type), lMSigParameters18);
                LMSigParameters lMSigParameters19 = LMSigParameters.lms_shake256_n24_h20;
                put(Integer.valueOf(lMSigParameters19.type), lMSigParameters19);
                LMSigParameters lMSigParameters20 = LMSigParameters.lms_shake256_n24_h25;
                put(Integer.valueOf(lMSigParameters20.type), lMSigParameters20);
            }
        };
    }

    public LMSigParameters(int i, int i2, int i3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.type = i;
        this.m = i2;
        this.h = i3;
        this.digestOid = aSN1ObjectIdentifier;
    }

    public static LMSigParameters getParametersForType(int i) {
        return paramBuilders.get(Integer.valueOf(i));
    }

    public ASN1ObjectIdentifier getDigestOID() {
        return this.digestOid;
    }

    public int getH() {
        return this.h;
    }

    public int getM() {
        return this.m;
    }

    public int getType() {
        return this.type;
    }
}
