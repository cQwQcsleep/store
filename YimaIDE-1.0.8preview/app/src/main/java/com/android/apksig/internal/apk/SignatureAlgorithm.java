package com.android.apksig.internal.apk;

import androidx.core.view.InputDeviceCompat;
import com.android.apksig.internal.util.Pair;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'RSA_PSS_WITH_SHA256' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class SignatureAlgorithm {
    private static final /* synthetic */ SignatureAlgorithm[] $VALUES;
    public static final SignatureAlgorithm DETDSA_WITH_SHA256;
    public static final SignatureAlgorithm DSA_WITH_SHA256;
    public static final SignatureAlgorithm ECDSA_WITH_SHA256;
    public static final SignatureAlgorithm ECDSA_WITH_SHA512;
    public static final SignatureAlgorithm RSA_PKCS1_V1_5_WITH_SHA256;
    public static final SignatureAlgorithm RSA_PKCS1_V1_5_WITH_SHA512;
    public static final SignatureAlgorithm RSA_PSS_WITH_SHA256;
    public static final SignatureAlgorithm RSA_PSS_WITH_SHA512;
    public static final SignatureAlgorithm VERITY_DSA_WITH_SHA256;
    public static final SignatureAlgorithm VERITY_ECDSA_WITH_SHA256;
    public static final SignatureAlgorithm VERITY_RSA_PKCS1_V1_5_WITH_SHA256;
    private final ContentDigestAlgorithm mContentDigestAlgorithm;
    private final int mId;
    private final String mJcaKeyAlgorithm;
    private final int mJcaSigAlgMinSdkVersion;
    private final Pair<String, ? extends AlgorithmParameterSpec> mJcaSignatureAlgAndParams;
    private final int mMinSdkVersion;

    private static /* synthetic */ SignatureAlgorithm[] $values() {
        return new SignatureAlgorithm[]{RSA_PSS_WITH_SHA256, RSA_PSS_WITH_SHA512, RSA_PKCS1_V1_5_WITH_SHA256, RSA_PKCS1_V1_5_WITH_SHA512, ECDSA_WITH_SHA256, ECDSA_WITH_SHA512, DSA_WITH_SHA256, DETDSA_WITH_SHA256, VERITY_RSA_PKCS1_V1_5_WITH_SHA256, VERITY_ECDSA_WITH_SHA256, VERITY_DSA_WITH_SHA256};
    }

    static {
        ContentDigestAlgorithm contentDigestAlgorithm = ContentDigestAlgorithm.CHUNKED_SHA256;
        RSA_PSS_WITH_SHA256 = new SignatureAlgorithm("RSA_PSS_WITH_SHA256", 0, InputDeviceCompat.SOURCE_KEYBOARD, contentDigestAlgorithm, "RSA", Pair.of("SHA256withRSA/PSS", new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1)), 24, 23);
        ContentDigestAlgorithm contentDigestAlgorithm2 = ContentDigestAlgorithm.CHUNKED_SHA512;
        RSA_PSS_WITH_SHA512 = new SignatureAlgorithm("RSA_PSS_WITH_SHA512", 1, AndroidBinXmlParser.Chunk.RES_XML_TYPE_START_ELEMENT, contentDigestAlgorithm2, "RSA", Pair.of("SHA512withRSA/PSS", new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1)), 24, 23);
        RSA_PKCS1_V1_5_WITH_SHA256 = new SignatureAlgorithm("RSA_PKCS1_V1_5_WITH_SHA256", 2, AndroidBinXmlParser.Chunk.RES_XML_TYPE_END_ELEMENT, contentDigestAlgorithm, "RSA", Pair.of("SHA256withRSA", null), 24, 1);
        RSA_PKCS1_V1_5_WITH_SHA512 = new SignatureAlgorithm("RSA_PKCS1_V1_5_WITH_SHA512", 3, 260, contentDigestAlgorithm2, "RSA", Pair.of("SHA512withRSA", null), 24, 1);
        ECDSA_WITH_SHA256 = new SignatureAlgorithm("ECDSA_WITH_SHA256", 4, InputDeviceCompat.SOURCE_DPAD, contentDigestAlgorithm, "EC", Pair.of("SHA256withECDSA", null), 24, 11);
        ECDSA_WITH_SHA512 = new SignatureAlgorithm("ECDSA_WITH_SHA512", 5, 514, contentDigestAlgorithm2, "EC", Pair.of("SHA512withECDSA", null), 24, 11);
        DSA_WITH_SHA256 = new SignatureAlgorithm("DSA_WITH_SHA256", 6, 769, contentDigestAlgorithm, "DSA", Pair.of("SHA256withDSA", null), 24, 1);
        DETDSA_WITH_SHA256 = new SignatureAlgorithm("DETDSA_WITH_SHA256", 7, 769, contentDigestAlgorithm, "DSA", Pair.of("SHA256withDetDSA", null), 24, 1);
        ContentDigestAlgorithm contentDigestAlgorithm3 = ContentDigestAlgorithm.VERITY_CHUNKED_SHA256;
        VERITY_RSA_PKCS1_V1_5_WITH_SHA256 = new SignatureAlgorithm("VERITY_RSA_PKCS1_V1_5_WITH_SHA256", 8, 1057, contentDigestAlgorithm3, "RSA", Pair.of("SHA256withRSA", null), 28, 1);
        VERITY_ECDSA_WITH_SHA256 = new SignatureAlgorithm("VERITY_ECDSA_WITH_SHA256", 9, 1059, contentDigestAlgorithm3, "EC", Pair.of("SHA256withECDSA", null), 28, 11);
        VERITY_DSA_WITH_SHA256 = new SignatureAlgorithm("VERITY_DSA_WITH_SHA256", 10, 1061, contentDigestAlgorithm3, "DSA", Pair.of("SHA256withDSA", null), 28, 1);
        $VALUES = $values();
    }

    private SignatureAlgorithm(String str, int i, int i2, ContentDigestAlgorithm contentDigestAlgorithm, String str2, Pair pair, int i3, int i4) {
        super(str, i);
        this.mId = i2;
        this.mContentDigestAlgorithm = contentDigestAlgorithm;
        this.mJcaKeyAlgorithm = str2;
        this.mJcaSignatureAlgAndParams = pair;
        this.mMinSdkVersion = i3;
        this.mJcaSigAlgMinSdkVersion = i4;
    }

    public static SignatureAlgorithm findById(int i) {
        for (SignatureAlgorithm signatureAlgorithm : values()) {
            if (signatureAlgorithm.getId() == i) {
                return signatureAlgorithm;
            }
        }
        return null;
    }

    public static SignatureAlgorithm valueOf(String str) {
        return (SignatureAlgorithm) Enum.valueOf(SignatureAlgorithm.class, str);
    }

    public static SignatureAlgorithm[] values() {
        return (SignatureAlgorithm[]) $VALUES.clone();
    }

    public ContentDigestAlgorithm getContentDigestAlgorithm() {
        return this.mContentDigestAlgorithm;
    }

    public int getId() {
        return this.mId;
    }

    public String getJcaKeyAlgorithm() {
        return this.mJcaKeyAlgorithm;
    }

    public int getJcaSigAlgMinSdkVersion() {
        return this.mJcaSigAlgMinSdkVersion;
    }

    public Pair<String, ? extends AlgorithmParameterSpec> getJcaSignatureAlgorithmAndParams() {
        return this.mJcaSignatureAlgAndParams;
    }

    public int getMinSdkVersion() {
        return this.mMinSdkVersion;
    }
}
