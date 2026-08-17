package com.android.apksig.internal.apk.v3;

import com.android.apksig.ApkVerifier;
import com.android.apksig.SigningCertificateLineage;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.SignatureInfo;
import com.android.apksig.internal.apk.v3.V3SchemeVerifier;
import com.android.apksig.internal.util.ByteBufferUtils;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import com.android.apksig.internal.util.X509CertificateUtils;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.RunnablesExecutor;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V3SchemeVerifier {
    private final DataSource mApk;
    private ByteBuffer mApkSignatureSchemeV3Block;
    private final int mBlockId;
    private final Set<ContentDigestAlgorithm> mContentDigestsToVerify;
    private final RunnablesExecutor mExecutor;
    private final boolean mFullVerification;
    private final int mMaxSdkVersion;
    private final int mMinSdkVersion;
    private final OptionalInt mOptionalRotationMinSdkVersion;
    private final ApkSigningBlockUtils.Result mResult;
    private final ApkUtils.ZipSections mZipSections;

    private V3SchemeVerifier(RunnablesExecutor runnablesExecutor, DataSource dataSource, ApkUtils.ZipSections zipSections, Set<ContentDigestAlgorithm> set, ApkSigningBlockUtils.Result result, int i, int i2, int i3, OptionalInt optionalInt, boolean z) {
        this.mExecutor = runnablesExecutor;
        this.mApk = dataSource;
        this.mZipSections = zipSections;
        this.mContentDigestsToVerify = set;
        this.mResult = result;
        this.mMinSdkVersion = i;
        this.mMaxSdkVersion = i2;
        this.mBlockId = i3;
        this.mOptionalRotationMinSdkVersion = optionalInt;
        this.mFullVerification = z;
    }

    public static /* synthetic */ boolean b(int i) {
        return i == -1029262406;
    }

    private void parseSigner(ByteBuffer byteBuffer, CertificateFactory certificateFactory, ApkSigningBlockUtils.Result.SignerInfo signerInfo) throws NoSuchAlgorithmException, ApkFormatException {
        byte[] encoded;
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        byte[] bArr = new byte[lengthPrefixedSlice.remaining()];
        lengthPrefixedSlice.get(bArr);
        lengthPrefixedSlice.flip();
        signerInfo.signedData = bArr;
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        signerInfo.minSdkVersion = i;
        signerInfo.maxSdkVersion = i2;
        if (i < 0 || i > i2) {
            signerInfo.addError(ApkVerifier.Issue.V3_SIG_INVALID_SDK_VERSIONS, Integer.valueOf(i), Integer.valueOf(i2));
        }
        ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        byte[] lengthPrefixedByteArray = ApkSigningBlockUtils.readLengthPrefixedByteArray(byteBuffer);
        ArrayList arrayList = new ArrayList(1);
        int i3 = 0;
        while (lengthPrefixedSlice2.hasRemaining()) {
            i3++;
            try {
                ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice2);
                int i4 = lengthPrefixedSlice3.getInt();
                byte[] lengthPrefixedByteArray2 = ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice3);
                signerInfo.signatures.add(new ApkSigningBlockUtils.Result.SignerInfo.Signature(i4, lengthPrefixedByteArray2));
                SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(i4);
                if (signatureAlgorithmFindById == null) {
                    signerInfo.addWarning(ApkVerifier.Issue.V3_SIG_UNKNOWN_SIG_ALGORITHM, Integer.valueOf(i4));
                } else {
                    arrayList.add(new ApkSigningBlockUtils.SupportedSignature(signatureAlgorithmFindById, lengthPrefixedByteArray2));
                }
            } catch (ApkFormatException | BufferUnderflowException unused) {
                signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_SIGNATURE, Integer.valueOf(i3));
                return;
            }
        }
        if (signerInfo.signatures.isEmpty()) {
            signerInfo.addError(ApkVerifier.Issue.V3_SIG_NO_SIGNATURES, new Object[0]);
            return;
        }
        try {
            for (ApkSigningBlockUtils.SupportedSignature supportedSignature : ApkSigningBlockUtils.getSignaturesToVerify(arrayList, signerInfo.minSdkVersion, signerInfo.maxSdkVersion)) {
                SignatureAlgorithm signatureAlgorithm = supportedSignature.algorithm;
                String first = signatureAlgorithm.getJcaSignatureAlgorithmAndParams().getFirst();
                AlgorithmParameterSpec second = signatureAlgorithm.getJcaSignatureAlgorithmAndParams().getSecond();
                try {
                    PublicKey publicKeyGeneratePublic = KeyFactory.getInstance(signatureAlgorithm.getJcaKeyAlgorithm()).generatePublic(new X509EncodedKeySpec(lengthPrefixedByteArray));
                    try {
                        Signature signature = Signature.getInstance(first);
                        signature.initVerify(publicKeyGeneratePublic);
                        if (second != null) {
                            signature.setParameter(second);
                        }
                        lengthPrefixedSlice.position(0);
                        signature.update(lengthPrefixedSlice);
                        byte[] bArr2 = supportedSignature.signature;
                        if (!signature.verify(bArr2)) {
                            signerInfo.addError(ApkVerifier.Issue.V3_SIG_DID_NOT_VERIFY, signatureAlgorithm);
                            return;
                        } else {
                            signerInfo.verifiedSignatures.put(signatureAlgorithm, bArr2);
                            this.mContentDigestsToVerify.add(signatureAlgorithm.getContentDigestAlgorithm());
                        }
                    } catch (InvalidAlgorithmParameterException | InvalidKeyException | SignatureException e) {
                        signerInfo.addError(ApkVerifier.Issue.V3_SIG_VERIFY_EXCEPTION, signatureAlgorithm, e);
                        return;
                    }
                } catch (Exception e2) {
                    signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_PUBLIC_KEY, e2);
                    return;
                }
            }
            lengthPrefixedSlice.position(0);
            ByteBuffer lengthPrefixedSlice4 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
            ByteBuffer lengthPrefixedSlice5 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
            int i5 = lengthPrefixedSlice.getInt();
            if (i5 != i) {
                signerInfo.addError(ApkVerifier.Issue.V3_MIN_SDK_VERSION_MISMATCH_BETWEEN_SIGNER_AND_SIGNED_DATA_RECORD, Integer.valueOf(i), Integer.valueOf(i5));
            }
            int i6 = lengthPrefixedSlice.getInt();
            if (i6 != i2) {
                signerInfo.addError(ApkVerifier.Issue.V3_MAX_SDK_VERSION_MISMATCH_BETWEEN_SIGNER_AND_SIGNED_DATA_RECORD, Integer.valueOf(i2), Integer.valueOf(i6));
            }
            ByteBuffer lengthPrefixedSlice6 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
            int i7 = -1;
            while (lengthPrefixedSlice5.hasRemaining()) {
                int i8 = i7 + 1;
                byte[] lengthPrefixedByteArray3 = ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice5);
                try {
                    signerInfo.certs.add(new GuaranteedEncodedFormX509Certificate(X509CertificateUtils.generateCertificate(lengthPrefixedByteArray3, certificateFactory), lengthPrefixedByteArray3));
                    i7 = i8;
                } catch (CertificateException e3) {
                    signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_CERTIFICATE, Integer.valueOf(i8), Integer.valueOf(i7 + 2), e3);
                    return;
                }
            }
            if (signerInfo.certs.isEmpty()) {
                signerInfo.addError(ApkVerifier.Issue.V3_SIG_NO_CERTIFICATES, new Object[0]);
                return;
            }
            X509Certificate x509Certificate = signerInfo.certs.get(0);
            try {
                encoded = ApkSigningBlockUtils.encodePublicKey(x509Certificate.getPublicKey());
            } catch (InvalidKeyException e4) {
                System.out.println("Caught an exception encoding the public key: " + e4);
                e4.printStackTrace();
                encoded = x509Certificate.getPublicKey().getEncoded();
            }
            if (!Arrays.equals(lengthPrefixedByteArray, encoded)) {
                signerInfo.addError(ApkVerifier.Issue.V3_SIG_PUBLIC_KEY_MISMATCH_BETWEEN_CERTIFICATE_AND_SIGNATURES_RECORD, ApkSigningBlockUtils.toHex(encoded), ApkSigningBlockUtils.toHex(lengthPrefixedByteArray));
                return;
            }
            int i9 = 0;
            while (lengthPrefixedSlice4.hasRemaining()) {
                i9++;
                try {
                    ByteBuffer lengthPrefixedSlice7 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice4);
                    signerInfo.contentDigests.add(new ApkSigningBlockUtils.Result.SignerInfo.ContentDigest(lengthPrefixedSlice7.getInt(), ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice7)));
                } catch (ApkFormatException | BufferUnderflowException unused2) {
                    signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_DIGEST, Integer.valueOf(i9));
                    return;
                }
            }
            ArrayList arrayList2 = new ArrayList(signerInfo.signatures.size());
            Iterator<ApkSigningBlockUtils.Result.SignerInfo.Signature> it = signerInfo.signatures.iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf(it.next().getAlgorithmId()));
            }
            ArrayList arrayList3 = new ArrayList(signerInfo.contentDigests.size());
            Iterator<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> it2 = signerInfo.contentDigests.iterator();
            while (it2.hasNext()) {
                arrayList3.add(Integer.valueOf(it2.next().getSignatureAlgorithmId()));
            }
            if (!arrayList2.equals(arrayList3)) {
                signerInfo.addError(ApkVerifier.Issue.V3_SIG_SIG_ALG_MISMATCH_BETWEEN_SIGNATURES_AND_DIGESTS_RECORDS, arrayList2, arrayList3);
                return;
            }
            int i10 = 0;
            boolean z = false;
            while (lengthPrefixedSlice6.hasRemaining()) {
                i10++;
                try {
                    ByteBuffer lengthPrefixedSlice8 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice6);
                    int i11 = lengthPrefixedSlice8.getInt();
                    byte[] byteArray = ByteBufferUtils.toByteArray(lengthPrefixedSlice8);
                    signerInfo.additionalAttributes.add(new ApkSigningBlockUtils.Result.SignerInfo.AdditionalAttribute(i11, byteArray));
                    if (i11 == 1000370060) {
                        try {
                            SigningCertificateLineage fromV3AttributeValue = SigningCertificateLineage.readFromV3AttributeValue(byteArray);
                            signerInfo.signingCertificateLineage = fromV3AttributeValue;
                            if (signerInfo.signingCertificateLineage.size() != fromV3AttributeValue.getSubLineage(signerInfo.certs.get(0)).size()) {
                                signerInfo.addError(ApkVerifier.Issue.V3_SIG_POR_CERT_MISMATCH, new Object[0]);
                            }
                        } catch (IllegalArgumentException unused3) {
                            signerInfo.addError(ApkVerifier.Issue.V3_SIG_POR_CERT_MISMATCH, new Object[0]);
                        } catch (SecurityException unused4) {
                            signerInfo.addError(ApkVerifier.Issue.V3_SIG_POR_DID_NOT_VERIFY, new Object[0]);
                        } catch (Exception unused5) {
                            signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_LINEAGE, new Object[0]);
                        }
                    } else if (i11 == 1436519170) {
                        if (this.mMaxSdkVersion >= 33 && this.mFullVerification) {
                            int i12 = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN).getInt();
                            if (this.mOptionalRotationMinSdkVersion.isPresent()) {
                                int asInt = this.mOptionalRotationMinSdkVersion.getAsInt();
                                if (i12 != asInt) {
                                    signerInfo.addError(ApkVerifier.Issue.V31_ROTATION_MIN_SDK_MISMATCH, Integer.valueOf(i12), Integer.valueOf(asInt));
                                }
                            } else {
                                signerInfo.addError(ApkVerifier.Issue.V31_BLOCK_MISSING, Integer.valueOf(i12));
                            }
                        }
                        z = true;
                    } else if (i11 != -1029262406) {
                        signerInfo.addWarning(ApkVerifier.Issue.V3_SIG_UNKNOWN_ADDITIONAL_ATTRIBUTE, Integer.valueOf(i11));
                    } else if (this.mBlockId != 462663009) {
                        signerInfo.addWarning(ApkVerifier.Issue.V31_ROTATION_TARGETS_DEV_RELEASE_ATTR_ON_V3_SIGNER, new Object[0]);
                    }
                } catch (ApkFormatException | BufferUnderflowException unused6) {
                    signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_ADDITIONAL_ATTRIBUTE, Integer.valueOf(i10));
                    return;
                }
            }
            if (this.mFullVerification && this.mOptionalRotationMinSdkVersion.isPresent() && !z) {
                signerInfo.addWarning(ApkVerifier.Issue.V31_ROTATION_MIN_SDK_ATTR_MISSING, Integer.valueOf(this.mOptionalRotationMinSdkVersion.getAsInt()));
            }
        } catch (ApkSigningBlockUtils.NoSupportedSignaturesException unused7) {
            signerInfo.addError(ApkVerifier.Issue.V3_SIG_NO_SUPPORTED_SIGNATURES, new Object[0]);
        }
    }

    public static boolean signerTargetsDevRelease(ApkSigningBlockUtils.Result.SignerInfo signerInfo) {
        return signerInfo.additionalAttributes.stream().mapToInt(new ToIntFunction() { // from class: b6f
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((ApkSigningBlockUtils.Result.SignerInfo.AdditionalAttribute) obj).getId();
            }
        }).anyMatch(new IntPredicate() { // from class: c6f
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return V3SchemeVerifier.b(i);
            }
        });
    }

    public ApkSigningBlockUtils.Result parseSigners() throws ApkSigningBlockUtils.SignatureNotFoundException, NoSuchAlgorithmException, IOException {
        try {
            if (this.mApkSignatureSchemeV3Block == null) {
                this.mApkSignatureSchemeV3Block = ApkSigningBlockUtils.findSignature(this.mApk, this.mZipSections, this.mBlockId, this.mResult).signatureBlock;
            }
            ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(this.mApkSignatureSchemeV3Block);
            if (!lengthPrefixedSlice.hasRemaining()) {
                this.mResult.addError(ApkVerifier.Issue.V3_SIG_NO_SIGNERS, new Object[0]);
                return this.mResult;
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                int i = 0;
                while (lengthPrefixedSlice.hasRemaining()) {
                    int i2 = i + 1;
                    ApkSigningBlockUtils.Result.SignerInfo signerInfo = new ApkSigningBlockUtils.Result.SignerInfo();
                    signerInfo.index = i;
                    this.mResult.signers.add(signerInfo);
                    try {
                        parseSigner(ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice), certificateFactory, signerInfo);
                        i = i2;
                    } catch (ApkFormatException | BufferUnderflowException unused) {
                        signerInfo.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_SIGNER, new Object[0]);
                        return this.mResult;
                    }
                }
                return this.mResult;
            } catch (CertificateException e) {
                g3c.a("Failed to obtain X.509 CertificateFactory", e);
                return null;
            }
        } catch (ApkFormatException unused2) {
            this.mResult.addError(ApkVerifier.Issue.V3_SIG_MALFORMED_SIGNERS, new Object[0]);
            return this.mResult;
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00de  */
    public ApkSigningBlockUtils.Result verify() throws ApkSigningBlockUtils.SignatureNotFoundException, NoSuchAlgorithmException, IOException {
        ApkUtils.ZipSections zipSections;
        DataSource dataSource = this.mApk;
        if (dataSource == null || (zipSections = this.mZipSections) == null) {
            k2d.a("A non-null apk and zip sections must be specified to verify an APK's v3 signatures");
            return null;
        }
        SignatureInfo signatureInfoFindSignature = ApkSigningBlockUtils.findSignature(dataSource, zipSections, this.mBlockId, this.mResult);
        this.mApkSignatureSchemeV3Block = signatureInfoFindSignature.signatureBlock;
        DataSource dataSourceSlice = this.mApk.slice(0L, signatureInfoFindSignature.apkSigningBlockOffset);
        DataSource dataSource2 = this.mApk;
        long j = signatureInfoFindSignature.centralDirOffset;
        DataSource dataSourceSlice2 = dataSource2.slice(j, signatureInfoFindSignature.eocdOffset - j);
        ByteBuffer byteBuffer = signatureInfoFindSignature.eocd;
        parseSigners();
        if (this.mResult.containsErrors()) {
            return this.mResult;
        }
        ApkSigningBlockUtils.verifyIntegrity(this.mExecutor, dataSourceSlice, dataSourceSlice2, byteBuffer, this.mContentDigestsToVerify, this.mResult);
        TreeMap treeMap = new TreeMap();
        for (ApkSigningBlockUtils.Result.SignerInfo signerInfo : this.mResult.signers) {
            treeMap.put(Integer.valueOf(signerInfo.maxSdkVersion), signerInfo);
        }
        ArrayList arrayList = new ArrayList(this.mResult.signers.size());
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (ApkSigningBlockUtils.Result.SignerInfo signerInfo2 : treeMap.values()) {
            int i4 = signerInfo2.minSdkVersion;
            int i5 = signerInfo2.maxSdkVersion;
            if (i != 0) {
                if (i4 != i2 + 1 && (i4 != i2 || !signerTargetsDevRelease(signerInfo2))) {
                    this.mResult.addError(ApkVerifier.Issue.V3_INCONSISTENT_SDK_VERSIONS, new Object[0]);
                    break;
                }
            } else {
                i = i4;
            }
            SigningCertificateLineage signingCertificateLineage = signerInfo2.signingCertificateLineage;
            if (signingCertificateLineage != null) {
                int size = signingCertificateLineage.size();
                if (size < i3) {
                    this.mResult.addError(ApkVerifier.Issue.V3_INCONSISTENT_LINEAGES, new Object[0]);
                    i2 = i5;
                    break;
                }
                arrayList.add(signerInfo2.signingCertificateLineage);
                i3 = size;
            }
            i2 = i5;
        }
        if (i > this.mMinSdkVersion) {
            this.mResult.addError(ApkVerifier.Issue.V3_MISSING_SDK_VERSIONS, Integer.valueOf(i), Integer.valueOf(i2));
        } else if (i2 < (this.mOptionalRotationMinSdkVersion.isPresent() ? this.mOptionalRotationMinSdkVersion.getAsInt() - 1 : this.mMaxSdkVersion)) {
            this.mResult.addError(ApkVerifier.Issue.V3_MISSING_SDK_VERSIONS, Integer.valueOf(i), Integer.valueOf(i2));
        }
        try {
            this.mResult.signingCertificateLineage = SigningCertificateLineage.consolidateLineages(arrayList);
        } catch (IllegalArgumentException unused) {
            this.mResult.addError(ApkVerifier.Issue.V3_INCONSISTENT_LINEAGES, new Object[0]);
        }
        if (!this.mResult.containsErrors()) {
            this.mResult.verified = true;
        }
        return this.mResult;
    }

    public static class Builder {
        private DataSource mApk;
        private ByteBuffer mApkSignatureSchemeV3Block;
        private Set<ContentDigestAlgorithm> mContentDigestsToVerify;
        private int mMaxSdkVersion;
        private int mMinSdkVersion;
        private ApkSigningBlockUtils.Result mResult;
        private ApkUtils.ZipSections mZipSections;
        private RunnablesExecutor mExecutor = RunnablesExecutor.SINGLE_THREADED;
        private int mBlockId = -262969152;
        private boolean mFullVerification = true;
        private OptionalInt mOptionalRotationMinSdkVersion = OptionalInt.empty();

        public Builder(DataSource dataSource, ApkUtils.ZipSections zipSections, int i, int i2) {
            this.mApk = dataSource;
            this.mZipSections = zipSections;
            this.mMinSdkVersion = i;
            this.mMaxSdkVersion = i2;
        }

        public V3SchemeVerifier build() {
            int i;
            int i2 = this.mBlockId;
            if (i2 == -262969152) {
                this.mMinSdkVersion = Math.max(this.mMinSdkVersion, 28);
                i = 3;
            } else {
                if (i2 != 462663009) {
                    drd.a("Unsupported APK Signature Scheme V3 block ID: 0x%08x", new Object[]{Integer.valueOf(i2)});
                    return null;
                }
                this.mMinSdkVersion = this.mMaxSdkVersion;
                i = 31;
            }
            if (this.mResult == null) {
                this.mResult = new ApkSigningBlockUtils.Result(i);
            }
            if (this.mContentDigestsToVerify == null) {
                this.mContentDigestsToVerify = new HashSet(1);
            }
            V3SchemeVerifier v3SchemeVerifier = new V3SchemeVerifier(this.mExecutor, this.mApk, this.mZipSections, this.mContentDigestsToVerify, this.mResult, this.mMinSdkVersion, this.mMaxSdkVersion, this.mBlockId, this.mOptionalRotationMinSdkVersion, this.mFullVerification);
            ByteBuffer byteBuffer = this.mApkSignatureSchemeV3Block;
            if (byteBuffer != null) {
                v3SchemeVerifier.mApkSignatureSchemeV3Block = byteBuffer;
            }
            return v3SchemeVerifier;
        }

        public Builder setBlockId(int i) {
            this.mBlockId = i;
            return this;
        }

        public Builder setContentDigestsToVerify(Set<ContentDigestAlgorithm> set) {
            this.mContentDigestsToVerify = set;
            return this;
        }

        public Builder setFullVerification(boolean z) {
            this.mFullVerification = z;
            return this;
        }

        public Builder setResult(ApkSigningBlockUtils.Result result) {
            this.mResult = result;
            return this;
        }

        public Builder setRotationMinSdkVersion(int i) {
            this.mOptionalRotationMinSdkVersion = OptionalInt.of(i);
            return this;
        }

        public Builder setRunnablesExecutor(RunnablesExecutor runnablesExecutor) {
            this.mExecutor = runnablesExecutor;
            return this;
        }

        public Builder(ByteBuffer byteBuffer) {
            this.mApkSignatureSchemeV3Block = byteBuffer;
        }
    }

    public static void parseSigners(ByteBuffer byteBuffer, Set<ContentDigestAlgorithm> set, ApkSigningBlockUtils.Result result) throws NoSuchAlgorithmException {
        try {
            new Builder(byteBuffer).setResult(result).setContentDigestsToVerify(set).setFullVerification(false).build().parseSigners();
        } catch (ApkSigningBlockUtils.SignatureNotFoundException | IOException e) {
            mg9.a("An exception was encountered when attempting to parse the signers from the provided APK Signature Scheme v3 block", e);
        }
    }

    public static ApkSigningBlockUtils.Result verify(RunnablesExecutor runnablesExecutor, DataSource dataSource, ApkUtils.ZipSections zipSections, int i, int i2) throws ApkSigningBlockUtils.SignatureNotFoundException, NoSuchAlgorithmException, IOException {
        return new Builder(dataSource, zipSections, i, i2).setRunnablesExecutor(runnablesExecutor).setBlockId(-262969152).build().verify();
    }
}
