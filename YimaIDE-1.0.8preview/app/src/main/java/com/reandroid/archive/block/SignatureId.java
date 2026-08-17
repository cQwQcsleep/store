package com.reandroid.archive.block;

import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.reandroid.arsc.coder.CoderHex;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.utils.HexUtil;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SignatureId implements Comparable<SignatureId> {
    public static final String FILE_EXT_RAW = ".signature.info.bin";
    public static final SignatureId NULL;
    public static final SignatureId PADDING;
    public static final SignatureId STAMP_V1;
    public static final SignatureId STAMP_V2;
    public static final SignatureId V2;
    public static final SignatureId V3;
    public static final SignatureId V31;
    private static final SignatureId[] VALUES;
    private final int id;
    private final String name;
    private final int sort;

    static {
        SignatureId signatureId = new SignatureId("V2", 1896449818, 0);
        V2 = signatureId;
        SignatureId signatureId2 = new SignatureId("V3", -262969152, 1);
        V3 = signatureId2;
        SignatureId signatureId3 = new SignatureId("V31", 462663009, 2);
        V31 = signatureId3;
        SignatureId signatureId4 = new SignatureId("STAMP_V1", 722016414, 3);
        STAMP_V1 = signatureId4;
        SignatureId signatureId5 = new SignatureId("STAMP_V2", 1845461005, 4);
        STAMP_V2 = signatureId5;
        SignatureId signatureId6 = new SignatureId("PADDING", ApkSigningBlockUtils.VERITY_PADDING_BLOCK_ID, 9999);
        PADDING = signatureId6;
        SignatureId signatureId7 = new SignatureId("NULL", 0, 999);
        NULL = signatureId7;
        VALUES = new SignatureId[]{signatureId, signatureId2, signatureId3, signatureId4, signatureId5, signatureId6, signatureId7};
    }

    private SignatureId(String str, int i, int i2) {
        this.name = str;
        this.id = i;
        this.sort = i2;
    }

    public static SignatureId valueOf(String str) {
        if (str == null) {
            return null;
        }
        if (str.endsWith(FILE_EXT_RAW)) {
            str = str.substring(0, str.length() - 19);
        }
        for (SignatureId signatureId : VALUES) {
            if (str.equalsIgnoreCase(signatureId.name())) {
                return signatureId;
            }
        }
        EncodeResult encodeResultEncode = CoderHex.INS.encode(str);
        if (encodeResultEncode != null) {
            return new SignatureId(null, encodeResultEncode.value, 99);
        }
        return null;
    }

    public static SignatureId[] values() {
        return (SignatureId[]) VALUES.clone();
    }

    @Override // java.lang.Comparable
    public int compareTo(SignatureId signatureId) {
        return Integer.compare(this.sort, signatureId.sort);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.id == ((SignatureId) obj).id;
    }

    public int getId() {
        return this.id;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id));
    }

    public String name() {
        return this.name;
    }

    public String toFileName() {
        if (this.name != null) {
            return this.name + FILE_EXT_RAW;
        }
        return HexUtil.toHex8(this.id) + FILE_EXT_RAW;
    }

    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        return "UNKNOWN(" + HexUtil.toHex8(this.id) + ")";
    }

    public static SignatureId valueOf(int i) {
        for (SignatureId signatureId : VALUES) {
            if (i == signatureId.getId()) {
                return signatureId;
            }
        }
        return new SignatureId(null, i, 99);
    }
}
