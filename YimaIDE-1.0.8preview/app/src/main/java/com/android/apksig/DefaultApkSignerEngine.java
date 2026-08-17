package com.android.apksig;

import com.android.apksig.DefaultApkSignerEngine;
import com.android.apksig.KeyConfig;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.stamp.V2SourceStampSigner;
import com.android.apksig.internal.apk.v1.DigestAlgorithm;
import com.android.apksig.internal.apk.v1.V1SchemeSigner;
import com.android.apksig.internal.apk.v1.V1SchemeVerifier;
import com.android.apksig.internal.apk.v2.V2SchemeSigner;
import com.android.apksig.internal.apk.v3.V3SchemeSigner;
import com.android.apksig.internal.apk.v4.V4SchemeSigner;
import com.android.apksig.internal.apk.v4.V4Signature;
import com.android.apksig.internal.jar.ManifestParser;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.internal.util.TeeDataSink;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSinks;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.RunnablesExecutor;
import defpackage.hf3;
import defpackage.rc9;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DefaultApkSignerEngine implements ApkSignerEngine {
    private static final Set<Integer> DISCARDED_SIGNATURE_BLOCK_IDS;
    private OutputApkSigningBlockRequestImpl mAddSigningBlockRequest;
    private OutputJarSignatureRequestImpl mAddV1SignatureRequest;
    private boolean mClosed;
    private final String mCreatedBy;
    private Boolean mDebuggable;
    private final boolean mDebuggableApkPermitted;
    private final Map<String, byte[]> mEmittedSignatureJarEntryData;
    private RunnablesExecutor mExecutor;
    private GetJarEntryDataRequest mInputJarManifestEntryDataRequest;
    private final int mMinSdkVersion;
    private final boolean mOtherSignersSignaturesPreserved;
    private GetJarEntryDataRequest mOutputAndroidManifestEntryDataRequest;
    private final Map<String, GetJarEntryDataDigestRequest> mOutputJarEntryDigestRequests;
    private final Map<String, byte[]> mOutputJarEntryDigests;
    private final Map<String, GetJarEntryDataRequest> mOutputSignatureJarEntryDataRequests;
    private List<Pair<byte[], Integer>> mPreservedSignatureBlocks;
    private List<byte[]> mPreservedV2Signers;
    private Set<String> mSignatureExpectedOutputJarEntryNames;
    private final List<SignerConfig> mSignerConfigs;
    private final SigningCertificateLineage mSigningCertificateLineage;
    private final SignerConfig mSourceStampSignerConfig;
    private final SigningCertificateLineage mSourceStampSigningCertificateLineage;
    private final boolean mSourceStampTimestampEnabled;
    private final List<SignerConfig> mTargetedSignerConfigs;
    private DigestAlgorithm mV1ContentDigestAlgorithm;
    private boolean mV1SignaturePending;
    private List<V1SchemeSigner.SignerConfig> mV1SignerConfigs;
    private final boolean mV1SigningEnabled;
    private boolean mV2SignaturePending;
    private final boolean mV2SigningEnabled;
    private boolean mV3SignaturePending;
    private final boolean mV3SigningEnabled;
    private final boolean mVerityEnabled;

    /* JADX INFO: renamed from: com.android.apksig.DefaultApkSignerEngine$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy;

        static {
            int[] iArr = new int[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.values().length];
            $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy = iArr;
            try {
                iArr[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.SKIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT_BY_ENGINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class Builder {
        private final int mMinSdkVersion;
        private boolean mOtherSignersSignaturesPreserved;
        private List<SignerConfig> mSignerConfigs;
        private SigningCertificateLineage mSigningCertificateLineage;
        private SigningCertificateLineage mSourceStampSigningCertificateLineage;
        private SignerConfig mStampSignerConfig;
        private List<SignerConfig> mTargetedSignerConfigs;
        private boolean mV3SigningEnabled;
        private boolean mSourceStampTimestampEnabled = true;
        private boolean mV1SigningEnabled = true;
        private boolean mV2SigningEnabled = true;
        private int mRotationMinSdkVersion = 33;
        private boolean mRotationTargetsDevRelease = false;
        private boolean mVerityEnabled = false;
        private boolean mDebuggableApkPermitted = true;
        private String mCreatedBy = "1.0 (Android)";
        private boolean mV3SigningExplicitlyDisabled = false;
        private boolean mV3SigningExplicitlyEnabled = false;

        public Builder(List<SignerConfig> list, int i) {
            this.mV3SigningEnabled = true;
            if (list.isEmpty()) {
                w01.a("At least one signer config must be provided");
                throw null;
            }
            if (list.size() > 1) {
                this.mV3SigningEnabled = false;
            }
            this.mSignerConfigs = new ArrayList(list);
            this.mMinSdkVersion = i;
        }

        public static /* synthetic */ int a(SignerConfig signerConfig, SignerConfig signerConfig2) {
            return signerConfig.getMinSdkVersion() - signerConfig2.getMinSdkVersion();
        }

        private SigningCertificateLineage mergeTargetedSigningConfigLineages() throws InvalidKeyException {
            SigningCertificateLineage signingCertificateLineageMergeLineageWith = null;
            int i = 0;
            for (SignerConfig signerConfig : this.mTargetedSignerConfigs) {
                int minSdkVersion = signerConfig.getMinSdkVersion();
                if (minSdkVersion < 28) {
                    pu7.a("Targeted signing config is not supported prior to SDK version 28; received value ", minSdkVersion);
                    return null;
                }
                SigningCertificateLineage signingCertificateLineage = signerConfig.getSigningCertificateLineage();
                if (signingCertificateLineage == null) {
                    try {
                        signingCertificateLineage = new SigningCertificateLineage.Builder(new SigningCertificateLineage.SignerConfig.Builder(signerConfig.mKeyConfig, (X509Certificate) signerConfig.mCertificates.get(0)).build()).build();
                    } catch (NoSuchAlgorithmException | SignatureException | CertificateEncodingException unused) {
                        sle.a("Unable to create a SignerConfig for signer from certificate ", ((X509Certificate) signerConfig.mCertificates.get(0)).getSubjectDN());
                        return null;
                    }
                }
                if (minSdkVersion < 33) {
                    minSdkVersion = 28;
                }
                if (minSdkVersion == i) {
                    pu7.a("Multiple SignerConfigs were found targeting SDK version ", minSdkVersion);
                    return null;
                }
                if (signingCertificateLineageMergeLineageWith == null) {
                    signingCertificateLineageMergeLineageWith = signingCertificateLineage;
                } else {
                    try {
                        signingCertificateLineageMergeLineageWith = signingCertificateLineageMergeLineageWith.mergeLineageWith(signingCertificateLineage);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalStateException("The provided lineage targeting SDK " + minSdkVersion + " is not in the signing history of the other targeted signing configs", e);
                    }
                }
                i = minSdkVersion;
            }
            return signingCertificateLineageMergeLineageWith;
        }

        private void setEnabledSignatureSchemes() {
            boolean z = this.mV3SigningExplicitlyDisabled;
            if (z && this.mV3SigningExplicitlyEnabled) {
                k2d.a("Builder configured to both enable and disable APK Signature Scheme v3 signing");
            } else if (z) {
                this.mV3SigningEnabled = false;
            } else if (this.mV3SigningExplicitlyEnabled) {
                this.mV3SigningEnabled = true;
            }
        }

        private void setTargetedSignerConfigs() throws InvalidKeyException {
            int minSdkVersion;
            this.mSignerConfigs.sort(new Comparator() { // from class: if3
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return DefaultApkSignerEngine.Builder.a((DefaultApkSignerEngine.SignerConfig) obj, (DefaultApkSignerEngine.SignerConfig) obj2);
                }
            });
            this.mTargetedSignerConfigs = new ArrayList();
            for (int i = 0; i < this.mSignerConfigs.size(); i++) {
                if (this.mSignerConfigs.get(i).getMinSdkVersion() > 0) {
                    List<SignerConfig> list = this.mSignerConfigs;
                    this.mTargetedSignerConfigs = list.subList(i, list.size());
                    this.mSignerConfigs = this.mSignerConfigs.subList(0, i);
                    break;
                }
            }
            if (this.mSigningCertificateLineage != null) {
                if (!this.mTargetedSignerConfigs.isEmpty() && this.mRotationMinSdkVersion >= (minSdkVersion = this.mTargetedSignerConfigs.get(0).getMinSdkVersion())) {
                    hf3.a("The rotation-min-sdk-version, ", this.mRotationMinSdkVersion, ", must be less than the first targeted SDK version, ", minSdkVersion);
                    return;
                }
                try {
                    List<SignerConfig> listSortSignerConfigs = this.mSigningCertificateLineage.sortSignerConfigs(this.mSignerConfigs);
                    this.mSignerConfigs = listSortSignerConfigs;
                    SignerConfig signerConfigRemove = listSortSignerConfigs.remove(listSortSignerConfigs.size() - 1);
                    SignerConfig.Builder builder = new SignerConfig.Builder(signerConfigRemove.getName(), signerConfigRemove.getKeyConfig(), signerConfigRemove.getCertificates(), signerConfigRemove.getDeterministicDsaSigning());
                    builder.setLineageForMinSdkVersion(this.mSigningCertificateLineage, this.mRotationMinSdkVersion);
                    builder.setSignerTargetsDevRelease(this.mRotationTargetsDevRelease);
                    this.mTargetedSignerConfigs.add(0, builder.build());
                } catch (IllegalArgumentException e) {
                    mg9.a("Provided signer configs do not match the provided SigningCertificateLineage", e);
                    return;
                }
            }
            this.mSigningCertificateLineage = mergeTargetedSigningConfigLineages();
        }

        public DefaultApkSignerEngine build() throws InvalidKeyException {
            setEnabledSignatureSchemes();
            setTargetedSignerConfigs();
            SigningCertificateLineage signingCertificateLineage = this.mSigningCertificateLineage;
            boolean z = this.mV3SigningEnabled;
            if (signingCertificateLineage != null) {
                if (!z && this.mSignerConfigs.size() > 1) {
                    k2d.a("Provided multiple signers which are part of the SigningCertificateLineage, but not signing with APK Signature Scheme v3");
                    return null;
                }
            } else if (z && this.mSignerConfigs.size() > 1) {
                k2d.a("Multiple signing certificates provided for use with APK Signature Scheme v3 without an accompanying SigningCertificateLineage");
                return null;
            }
            return new DefaultApkSignerEngine(this.mSignerConfigs, this.mTargetedSignerConfigs, this.mStampSignerConfig, this.mSourceStampSigningCertificateLineage, this.mSourceStampTimestampEnabled, this.mMinSdkVersion, this.mV1SigningEnabled, this.mV2SigningEnabled, this.mV3SigningEnabled, this.mVerityEnabled, this.mDebuggableApkPermitted, this.mOtherSignersSignaturesPreserved, this.mCreatedBy, this.mSigningCertificateLineage, null);
        }

        public Builder setCreatedBy(String str) {
            str.getClass();
            this.mCreatedBy = str;
            return this;
        }

        public Builder setDebuggableApkPermitted(boolean z) {
            this.mDebuggableApkPermitted = z;
            return this;
        }

        public Builder setMinSdkVersionForRotation(int i) {
            if (i < 33) {
                this.mRotationMinSdkVersion = 28;
                return this;
            }
            this.mRotationMinSdkVersion = i;
            return this;
        }

        public Builder setOtherSignersSignaturesPreserved(boolean z) {
            this.mOtherSignersSignaturesPreserved = z;
            return this;
        }

        public Builder setRotationTargetsDevRelease(boolean z) {
            this.mRotationTargetsDevRelease = z;
            return this;
        }

        public Builder setSigningCertificateLineage(SigningCertificateLineage signingCertificateLineage) {
            if (signingCertificateLineage != null) {
                this.mV3SigningEnabled = true;
                this.mSigningCertificateLineage = signingCertificateLineage;
            }
            return this;
        }

        public Builder setSourceStampSigningCertificateLineage(SigningCertificateLineage signingCertificateLineage) {
            this.mSourceStampSigningCertificateLineage = signingCertificateLineage;
            return this;
        }

        public Builder setSourceStampTimestampEnabled(boolean z) {
            this.mSourceStampTimestampEnabled = z;
            return this;
        }

        public Builder setStampSignerConfig(SignerConfig signerConfig) {
            this.mStampSignerConfig = signerConfig;
            return this;
        }

        public Builder setV1SigningEnabled(boolean z) {
            this.mV1SigningEnabled = z;
            return this;
        }

        public Builder setV2SigningEnabled(boolean z) {
            this.mV2SigningEnabled = z;
            return this;
        }

        public Builder setV3SigningEnabled(boolean z) {
            this.mV3SigningEnabled = z;
            if (z) {
                this.mV3SigningExplicitlyEnabled = true;
                return this;
            }
            this.mV3SigningExplicitlyDisabled = true;
            return this;
        }

        public Builder setVerityEnabled(boolean z) {
            this.mVerityEnabled = z;
            return this;
        }
    }

    static {
        HashSet hashSet = new HashSet(3);
        DISCARDED_SIGNATURE_BLOCK_IDS = hashSet;
        hashSet.add(Integer.valueOf(ApkSigningBlockUtils.VERITY_PADDING_BLOCK_ID));
        hashSet.add(722016414);
        hashSet.add(1845461005);
    }

    private DefaultApkSignerEngine(List<SignerConfig> list, List<SignerConfig> list2, SignerConfig signerConfig, SigningCertificateLineage signingCertificateLineage, boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str, SigningCertificateLineage signingCertificateLineage2) throws InvalidKeyException {
        List list3 = Collections.EMPTY_LIST;
        this.mPreservedV2Signers = list3;
        this.mPreservedSignatureBlocks = list3;
        this.mV1SignerConfigs = list3;
        this.mSignatureExpectedOutputJarEntryNames = Collections.EMPTY_SET;
        this.mOutputJarEntryDigestRequests = new HashMap();
        this.mOutputJarEntryDigests = new HashMap();
        this.mEmittedSignatureJarEntryData = new HashMap();
        this.mOutputSignatureJarEntryDataRequests = new HashMap();
        this.mExecutor = RunnablesExecutor.MULTI_THREADED;
        if (list.isEmpty() && list2.isEmpty()) {
            w01.a("At least one signer config must be provided");
            throw null;
        }
        this.mV1SigningEnabled = z2;
        this.mV2SigningEnabled = z3;
        this.mV3SigningEnabled = z4;
        this.mVerityEnabled = z5;
        this.mV1SignaturePending = z2;
        this.mV2SignaturePending = z3;
        this.mV3SignaturePending = z4;
        this.mDebuggableApkPermitted = z6;
        this.mOtherSignersSignaturesPreserved = z7;
        this.mCreatedBy = str;
        this.mSignerConfigs = list;
        this.mTargetedSignerConfigs = list2;
        this.mSourceStampSignerConfig = signerConfig;
        this.mSourceStampSigningCertificateLineage = signingCertificateLineage;
        this.mSourceStampTimestampEnabled = z;
        this.mMinSdkVersion = i;
        this.mSigningCertificateLineage = signingCertificateLineage2;
        if (z2) {
            if (!z4) {
                createV1SignerConfigs(list, i);
                return;
            }
            SignerConfig signerConfig2 = !list.isEmpty() ? list.get(0) : list2.get(0);
            if (signingCertificateLineage2 == null || signingCertificateLineage2.getSubLineage((X509Certificate) signerConfig2.mCertificates.get(0)).size() == 1) {
                createV1SignerConfigs(Collections.singletonList(signerConfig2), i);
            } else {
                w01.a("v1 signing enabled but the oldest signer in the SigningCertificateLineage is missing.  Please provide the oldest signer to enable v1 signing");
                throw null;
            }
        }
    }

    private void checkNotClosed() {
        if (this.mClosed) {
            k2d.a("Engine closed");
        }
    }

    private void checkOutputApkNotDebuggableIfDebuggableMustBeRejected() throws SignatureException {
        if (this.mDebuggableApkPermitted) {
            return;
        }
        try {
            if (isOutputApkDebuggable()) {
                throw new SignatureException("APK is debuggable (see android:debuggable attribute) and this engine is configured to refuse to sign debuggable APKs");
            }
        } catch (ApkFormatException e) {
            throw new SignatureException("Failed to determine whether the APK is debuggable", e);
        }
    }

    private void checkSigningBlockDoneIfEnabled() {
        if (this.mV2SignaturePending || this.mV3SignaturePending) {
            OutputApkSigningBlockRequestImpl outputApkSigningBlockRequestImpl = this.mAddSigningBlockRequest;
            if (outputApkSigningBlockRequestImpl == null) {
                k2d.a("Signed APK Signing BLock not yet generated. Skipped outputZipSections()?");
            } else {
                if (!outputApkSigningBlockRequestImpl.isDone()) {
                    k2d.a("APK Signing Block addition of signature(s) requested by outputZipSections() hasn't been fulfilled yet");
                    return;
                }
                this.mAddSigningBlockRequest = null;
                this.mV2SignaturePending = false;
                this.mV3SignaturePending = false;
            }
        }
    }

    private void checkV1SigningDoneIfEnabled() {
        if (this.mV1SignaturePending) {
            OutputJarSignatureRequestImpl outputJarSignatureRequestImpl = this.mAddV1SignatureRequest;
            if (outputJarSignatureRequestImpl == null) {
                k2d.a("v1 signature (JAR signature) not yet generated. Skipped outputJarEntries()?");
                return;
            }
            if (!outputJarSignatureRequestImpl.isDone()) {
                k2d.a("v1 signature (JAR signature) addition requested by outputJarEntries() hasn't been fulfilled");
                return;
            }
            for (Map.Entry<String, byte[]> entry : this.mEmittedSignatureJarEntryData.entrySet()) {
                String key = entry.getKey();
                byte[] value = entry.getValue();
                GetJarEntryDataRequest getJarEntryDataRequest = this.mOutputSignatureJarEntryDataRequests.get(key);
                if (getJarEntryDataRequest == null) {
                    rc9.a("APK entry ", key, " not yet output despite this having been requested");
                    return;
                } else if (!getJarEntryDataRequest.isDone()) {
                    qu7.a("Still waiting to inspect output APK's ", key);
                    return;
                } else if (!Arrays.equals(value, getJarEntryDataRequest.getData())) {
                    rc9.a("Output APK entry ", key, " data differs from what was requested");
                    return;
                }
            }
            this.mV1SignaturePending = false;
        }
    }

    private ApkSigningBlockUtils.SignerConfig createSigningBlockSignerConfig(SignerConfig signerConfig, boolean z, int i) throws InvalidKeyException {
        List<X509Certificate> certificates = signerConfig.getCertificates();
        boolean z2 = false;
        PublicKey publicKey = certificates.get(0).getPublicKey();
        ApkSigningBlockUtils.SignerConfig signerConfig2 = new ApkSigningBlockUtils.SignerConfig();
        signerConfig2.keyConfig = signerConfig.getKeyConfig();
        signerConfig2.certificates = certificates;
        signerConfig2.minSdkVersion = signerConfig.getMinSdkVersion();
        signerConfig2.signerTargetsDevRelease = signerConfig.getSignerTargetsDevRelease();
        signerConfig2.signingCertificateLineage = signerConfig.getSigningCertificateLineage();
        if (i == 0) {
            signerConfig2.signatureAlgorithms = Collections.singletonList(SignatureAlgorithm.RSA_PKCS1_V1_5_WITH_SHA256);
            return signerConfig2;
        }
        if (i == 2) {
            int i2 = this.mMinSdkVersion;
            if (z && this.mVerityEnabled) {
                z2 = true;
            }
            signerConfig2.signatureAlgorithms = V2SchemeSigner.getSuggestedSignatureAlgorithms(publicKey, i2, z2, signerConfig.getDeterministicDsaSigning());
            return signerConfig2;
        }
        if (i == 3) {
            try {
                int i3 = this.mMinSdkVersion;
                if (z && this.mVerityEnabled) {
                    z2 = true;
                }
                signerConfig2.signatureAlgorithms = V3SchemeSigner.getSuggestedSignatureAlgorithms(publicKey, i3, z2, signerConfig.getDeterministicDsaSigning());
                return signerConfig2;
            } catch (InvalidKeyException unused) {
                signerConfig2.signatureAlgorithms = null;
            }
        } else {
            if (i != 4) {
                w01.a("Unknown APK Signature Scheme ID requested");
                return null;
            }
            try {
                signerConfig2.signatureAlgorithms = V4SchemeSigner.getSuggestedSignatureAlgorithms(publicKey, this.mMinSdkVersion, z, signerConfig.getDeterministicDsaSigning());
                return signerConfig2;
            } catch (InvalidKeyException unused2) {
                signerConfig2.signatureAlgorithms = null;
            }
        }
        return signerConfig2;
    }

    private List<ApkSigningBlockUtils.SignerConfig> createSigningBlockSignerConfigs(boolean z, int i) throws InvalidKeyException {
        ArrayList arrayList = new ArrayList(this.mSignerConfigs.size() + this.mTargetedSignerConfigs.size());
        for (int i2 = 0; i2 < this.mSignerConfigs.size(); i2++) {
            arrayList.add(createSigningBlockSignerConfig(this.mSignerConfigs.get(i2), z, i));
        }
        if (i >= 3) {
            for (int i3 = 0; i3 < this.mTargetedSignerConfigs.size(); i3++) {
                arrayList.add(createSigningBlockSignerConfig(this.mTargetedSignerConfigs.get(i3), z, i));
            }
        }
        return arrayList;
    }

    private ApkSigningBlockUtils.SignerConfig createSourceStampSignerConfig() throws InvalidKeyException {
        ApkSigningBlockUtils.SignerConfig signerConfigCreateSigningBlockSignerConfig = createSigningBlockSignerConfig(this.mSourceStampSignerConfig, false, 0);
        SigningCertificateLineage signingCertificateLineage = this.mSourceStampSigningCertificateLineage;
        if (signingCertificateLineage != null) {
            signerConfigCreateSigningBlockSignerConfig.signingCertificateLineage = signingCertificateLineage.getSubLineage(signerConfigCreateSigningBlockSignerConfig.certificates.get(0));
        }
        return signerConfigCreateSigningBlockSignerConfig;
    }

    private void createV1SignerConfigs(List<SignerConfig> list, int i) throws InvalidKeyException {
        this.mV1SignerConfigs = new ArrayList(list.size());
        HashMap map = new HashMap(list.size());
        DigestAlgorithm digestAlgorithm = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            SignerConfig signerConfig = list.get(i2);
            List<X509Certificate> certificates = signerConfig.getCertificates();
            PublicKey publicKey = certificates.get(0).getPublicKey();
            String safeSignerName = V1SchemeSigner.getSafeSignerName(signerConfig.getName());
            Integer num = (Integer) map.put(safeSignerName, Integer.valueOf(i2));
            if (num != null) {
                throw new IllegalArgumentException("Signers #" + (num.intValue() + 1) + " and #" + (i2 + 1) + " have the same name: " + safeSignerName + ". v1 signer names must be unique");
            }
            DigestAlgorithm suggestedSignatureDigestAlgorithm = V1SchemeSigner.getSuggestedSignatureDigestAlgorithm(publicKey, i);
            V1SchemeSigner.SignerConfig signerConfig2 = new V1SchemeSigner.SignerConfig();
            signerConfig2.name = safeSignerName;
            signerConfig2.keyConfig = signerConfig.getKeyConfig();
            signerConfig2.certificates = certificates;
            signerConfig2.signatureDigestAlgorithm = suggestedSignatureDigestAlgorithm;
            signerConfig2.deterministicDsaSigning = signerConfig.getDeterministicDsaSigning();
            if (digestAlgorithm == null || DigestAlgorithm.BY_STRENGTH_COMPARATOR.compare(suggestedSignatureDigestAlgorithm, digestAlgorithm) > 0) {
                digestAlgorithm = suggestedSignatureDigestAlgorithm;
            }
            this.mV1SignerConfigs.add(signerConfig2);
        }
        this.mV1ContentDigestAlgorithm = digestAlgorithm;
        this.mSignatureExpectedOutputJarEntryNames = V1SchemeSigner.getOutputEntryNames(this.mV1SignerConfigs);
    }

    private List<ApkSigningBlockUtils.SignerConfig> createV2SignerConfigs(boolean z) throws InvalidKeyException {
        if (!this.mV3SigningEnabled) {
            return createSigningBlockSignerConfigs(z, 2);
        }
        ArrayList arrayList = new ArrayList();
        SignerConfig signerConfig = !this.mSignerConfigs.isEmpty() ? this.mSignerConfigs.get(0) : this.mTargetedSignerConfigs.get(0);
        SigningCertificateLineage signingCertificateLineage = this.mSigningCertificateLineage;
        if (signingCertificateLineage == null || signingCertificateLineage.getSubLineage((X509Certificate) signerConfig.mCertificates.get(0)).size() == 1) {
            arrayList.add(createSigningBlockSignerConfig(signerConfig, z, 2));
            return arrayList;
        }
        w01.a("v2 signing enabled but the oldest signer in the SigningCertificateLineage is missing.  Please provide the oldest signer to enable v2 signing.");
        return null;
    }

    private List<ApkSigningBlockUtils.SignerConfig> createV3SignerConfigs(boolean z) throws InvalidKeyException {
        return processV3Configs(createSigningBlockSignerConfigs(z, 3));
    }

    private V4SchemeSigner.SignerConfig createV4SignerConfig() throws InvalidKeyException {
        List<ApkSigningBlockUtils.SignerConfig> listCreateSigningBlockSignerConfigs = createSigningBlockSignerConfigs(true, 4);
        if (listCreateSigningBlockSignerConfigs.size() != 1) {
            listCreateSigningBlockSignerConfigs = processV3Configs(listCreateSigningBlockSignerConfigs);
        }
        return new V4SchemeSigner.SignerConfig(listCreateSigningBlockSignerConfigs, processV31SignerConfigs(listCreateSigningBlockSignerConfigs));
    }

    private void forgetOutputApkDebuggableStatus() {
        this.mDebuggable = null;
    }

    private ApkSignerEngine.InputJarEntryInstructions.OutputPolicy getInputJarEntryOutputPolicy(String str) {
        if (this.mSignatureExpectedOutputJarEntryNames.contains(str)) {
            return ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT_BY_ENGINE;
        }
        return (this.mOtherSignersSignaturesPreserved || V1SchemeSigner.isJarEntryDigestNeededInManifest(str)) ? ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT : ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.SKIP;
    }

    private int getMinSdkFromV3SignatureAlgorithms(List<SignatureAlgorithm> list) {
        Iterator<SignatureAlgorithm> it = list.iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            int minSdkVersion = it.next().getMinSdkVersion();
            if (minSdkVersion < i) {
                if (minSdkVersion <= this.mMinSdkVersion || minSdkVersion <= 28) {
                    return minSdkVersion;
                }
                i = minSdkVersion;
            }
        }
        return i;
    }

    private void invalidateV1Signature() {
        if (this.mV1SigningEnabled) {
            this.mV1SignaturePending = true;
        }
        invalidateV2Signature();
    }

    private void invalidateV2Signature() {
        if (this.mV2SigningEnabled) {
            this.mV2SignaturePending = true;
            this.mAddSigningBlockRequest = null;
        }
    }

    private void invalidateV3Signature() {
        if (this.mV3SigningEnabled) {
            this.mV3SignaturePending = true;
            this.mAddSigningBlockRequest = null;
        }
    }

    private boolean isConfiguredWithSigner(List<X509Certificate> list) {
        Iterator<SignerConfig> it = this.mSignerConfigs.iterator();
        while (it.hasNext()) {
            if (list.containsAll(it.next().getCertificates())) {
                return true;
            }
        }
        return false;
    }

    private boolean isDebuggable(String str) {
        return this.mDebuggableApkPermitted || !ApkUtils.ANDROID_MANIFEST_ZIP_ENTRY_NAME.equals(str);
    }

    private boolean isOutputApkDebuggable() throws ApkFormatException {
        Boolean bool = this.mDebuggable;
        if (bool != null) {
            return bool.booleanValue();
        }
        GetJarEntryDataRequest getJarEntryDataRequest = this.mOutputAndroidManifestEntryDataRequest;
        if (getJarEntryDataRequest == null) {
            k2d.a("Cannot determine debuggable status of output APK because AndroidManifest.xml entry contents have not yet been requested");
            return false;
        }
        boolean zIsDone = getJarEntryDataRequest.isDone();
        GetJarEntryDataRequest getJarEntryDataRequest2 = this.mOutputAndroidManifestEntryDataRequest;
        if (!zIsDone) {
            sle.a("Still waiting to inspect output APK's ", getJarEntryDataRequest2.getEntryName());
            return false;
        }
        Boolean boolValueOf = Boolean.valueOf(ApkUtils.getDebuggableFromBinaryAndroidManifest(ByteBuffer.wrap(getJarEntryDataRequest2.getData())));
        this.mDebuggable = boolValueOf;
        return boolValueOf.booleanValue();
    }

    private OutputApkSigningBlockRequestImpl outputZipSectionsInternal(DataSource dataSource, DataSource dataSource2, DataSource dataSource3, boolean z) throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        DataSource dataSource4;
        ApkSigningBlockUtils.SigningSchemeBlockAndDigests signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV2Block;
        ApkSigningBlockUtils.SigningSchemeBlockAndDigests signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV3BlockAndDigests;
        List<Pair<byte[], Integer>> list;
        checkNotClosed();
        checkV1SigningDoneIfEnabled();
        AnonymousClass1 anonymousClass1 = null;
        if (!this.mV2SigningEnabled && !this.mV3SigningEnabled && !isEligibleForSourceStamp()) {
            return null;
        }
        checkOutputApkNotDebuggableIfDebuggableMustBeRejected();
        Pair<DataSource, Integer> pairGenerateApkSigningBlockPadding = ApkSigningBlockUtils.generateApkSigningBlockPadding(dataSource, z);
        DataSource first = pairGenerateApkSigningBlockPadding.getFirst();
        int iIntValue = pairGenerateApkSigningBlockPadding.getSecond().intValue();
        DataSource dataSourceCopyWithModifiedCDOffset = ApkSigningBlockUtils.copyWithModifiedCDOffset(first, dataSource3);
        ArrayList arrayList = new ArrayList();
        if (this.mOtherSignersSignaturesPreserved && (list = this.mPreservedSignatureBlocks) != null && !list.isEmpty()) {
            arrayList.addAll(this.mPreservedSignatureBlocks);
        }
        if (this.mV2SigningEnabled) {
            invalidateV2Signature();
            dataSource4 = dataSource2;
            signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV2Block = V2SchemeSigner.generateApkSignatureSchemeV2Block(this.mExecutor, first, dataSource4, dataSourceCopyWithModifiedCDOffset, createV2SignerConfigs(z), this.mV3SigningEnabled, this.mOtherSignersSignaturesPreserved ? this.mPreservedV2Signers : null);
            arrayList.add(signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV2Block.signingSchemeBlock);
        } else {
            dataSource4 = dataSource2;
            signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV2Block = null;
        }
        if (this.mV3SigningEnabled) {
            invalidateV3Signature();
            List<ApkSigningBlockUtils.SignerConfig> listCreateV3SignerConfigs = createV3SignerConfigs(z);
            List<ApkSigningBlockUtils.SignerConfig> listProcessV31SignerConfigs = processV31SignerConfigs(listCreateV3SignerConfigs);
            if (listProcessV31SignerConfigs != null && listProcessV31SignerConfigs.size() > 0) {
                arrayList.add(new V3SchemeSigner.Builder(first, dataSource4, dataSourceCopyWithModifiedCDOffset, listProcessV31SignerConfigs).setRunnablesExecutor(this.mExecutor).setBlockId(462663009).build().generateApkSignatureSchemeV3BlockAndDigests().signingSchemeBlock);
            }
            V3SchemeSigner.Builder blockId = new V3SchemeSigner.Builder(first, dataSource4, dataSourceCopyWithModifiedCDOffset, listCreateV3SignerConfigs).setRunnablesExecutor(this.mExecutor).setBlockId(-262969152);
            if (listProcessV31SignerConfigs != null && !listProcessV31SignerConfigs.isEmpty()) {
                blockId.setMinSdkVersionForV31(listProcessV31SignerConfigs.stream().mapToInt(new ToIntFunction() { // from class: gf3
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return ((ApkSigningBlockUtils.SignerConfig) obj).minSdkVersion;
                    }
                }).min().orElse(33));
            }
            signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV3BlockAndDigests = blockId.build().generateApkSignatureSchemeV3BlockAndDigests();
            arrayList.add(signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV3BlockAndDigests.signingSchemeBlock);
        } else {
            signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV3BlockAndDigests = null;
        }
        if (isEligibleForSourceStamp()) {
            ApkSigningBlockUtils.SignerConfig signerConfigCreateSourceStampSignerConfig = createSourceStampSignerConfig();
            HashMap map = new HashMap();
            if (this.mV3SigningEnabled) {
                map.put(3, signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV3BlockAndDigests.digestInfo);
            }
            if (this.mV2SigningEnabled) {
                map.put(2, signingSchemeBlockAndDigestsGenerateApkSignatureSchemeV2Block.digestInfo);
            }
            if (this.mV1SigningEnabled) {
                HashMap map2 = new HashMap();
                try {
                    GetJarEntryDataRequest getJarEntryDataRequest = this.mInputJarManifestEntryDataRequest;
                    map2.put(ContentDigestAlgorithm.SHA256, ApkUtils.computeSha256DigestBytes(V1SchemeSigner.generateManifestFile(this.mV1ContentDigestAlgorithm, this.mOutputJarEntryDigests, getJarEntryDataRequest != null ? getJarEntryDataRequest.getData() : null).contents));
                    map.put(1, map2);
                } catch (ApkFormatException e) {
                    g3c.a("Failed to generate manifest file", e);
                    return null;
                }
            }
            arrayList.add(new V2SourceStampSigner.Builder(signerConfigCreateSourceStampSignerConfig, map).setSourceStampTimestampEnabled(this.mSourceStampTimestampEnabled).build().generateSourceStampBlock());
        }
        OutputApkSigningBlockRequestImpl outputApkSigningBlockRequestImpl = new OutputApkSigningBlockRequestImpl(ApkSigningBlockUtils.generateApkSigningBlock(arrayList), iIntValue, anonymousClass1);
        this.mAddSigningBlockRequest = outputApkSigningBlockRequestImpl;
        return outputApkSigningBlockRequestImpl;
    }

    private List<ApkSigningBlockUtils.SignerConfig> processV31SignerConfigs(List<ApkSigningBlockUtils.SignerConfig> list) {
        if (list.size() == 1) {
            return null;
        }
        int iOrElse = list.stream().mapToInt(new ToIntFunction() { // from class: ff3
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((ApkSigningBlockUtils.SignerConfig) obj).minSdkVersion;
            }
        }).min().orElse(28);
        ArrayList arrayList = new ArrayList();
        Iterator<ApkSigningBlockUtils.SignerConfig> it = list.iterator();
        while (it.hasNext()) {
            ApkSigningBlockUtils.SignerConfig next = it.next();
            int i = next.minSdkVersion;
            if (i >= 33 && (i > iOrElse || (i >= iOrElse && next.signerTargetsDevRelease))) {
                arrayList.add(next);
                it.remove();
            }
        }
        return arrayList;
    }

    private List<ApkSigningBlockUtils.SignerConfig> processV3Configs(List<ApkSigningBlockUtils.SignerConfig> list) throws InvalidKeyException {
        int iMax = Math.max(28, this.mMinSdkVersion);
        if (this.mSignerConfigs.isEmpty() && this.mTargetedSignerConfigs.get(0).getMinSdkVersion() > iMax) {
            qf1.a("The provided targeted signer configs do not cover the SDK range for V3 support; either provide the original signer or ensure a signer targets SDK version ", iMax);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = Integer.MAX_VALUE;
        for (int size = list.size() - 1; size >= 0; size--) {
            ApkSigningBlockUtils.SignerConfig signerConfig = list.get(size);
            if (signerConfig.signatureAlgorithms == null) {
                throw new InvalidKeyException("Unsupported key algorithm " + signerConfig.certificates.get(0).getPublicKey().getAlgorithm() + " is not supported for APK Signature Scheme v3 signing");
            }
            if (size == list.size() - 1) {
                signerConfig.maxSdkVersion = Integer.MAX_VALUE;
            } else {
                ApkSigningBlockUtils.SignerConfig signerConfig2 = (ApkSigningBlockUtils.SignerConfig) arrayList.get(arrayList.size() - 1);
                if (signerConfig2.signerTargetsDevRelease) {
                    signerConfig.maxSdkVersion = signerConfig2.minSdkVersion;
                } else {
                    signerConfig.maxSdkVersion = i - 1;
                }
            }
            int i2 = signerConfig.minSdkVersion;
            if (i2 == 34) {
                signerConfig.minSdkVersion = 33;
                signerConfig.signerTargetsDevRelease = true;
            } else if (i2 == 0) {
                signerConfig.minSdkVersion = getMinSdkFromV3SignatureAlgorithms(signerConfig.signatureAlgorithms);
            }
            X509Certificate x509Certificate = signerConfig.certificates.get(0);
            SigningCertificateLineage signingCertificateLineage = signerConfig.signingCertificateLineage;
            if (signingCertificateLineage != null && !signingCertificateLineage.isCertificateLatestInLineage(x509Certificate)) {
                signerConfig.signingCertificateLineage = signerConfig.signingCertificateLineage.getSubLineage(x509Certificate);
            }
            arrayList.add(signerConfig);
            i = signerConfig.minSdkVersion;
            if (signerConfig.signerTargetsDevRelease) {
                if (i < iMax) {
                    break;
                }
            } else {
                if (i <= iMax) {
                    break;
                }
            }
        }
        if (i <= 28 || i <= this.mMinSdkVersion) {
            return arrayList;
        }
        f54.a("Provided key algorithms not supported on all desired Android SDK versions");
        return null;
    }

    @Override // com.android.apksig.ApkSignerEngine, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mClosed = true;
        this.mAddV1SignatureRequest = null;
        this.mInputJarManifestEntryDataRequest = null;
        this.mOutputAndroidManifestEntryDataRequest = null;
        this.mDebuggable = null;
        this.mOutputJarEntryDigestRequests.clear();
        this.mOutputJarEntryDigests.clear();
        this.mEmittedSignatureJarEntryData.clear();
        this.mOutputSignatureJarEntryDataRequests.clear();
        this.mAddSigningBlockRequest = null;
    }

    @Override // com.android.apksig.ApkSignerEngine
    public byte[] generateSourceStampCertificateDigest() throws SignatureException {
        if (this.mSourceStampSignerConfig.getCertificates().isEmpty()) {
            d54.a("No certificates configured for stamp");
            return null;
        }
        try {
            return ApkUtils.computeSha256DigestBytes(this.mSourceStampSignerConfig.getCertificates().get(0).getEncoded());
        } catch (CertificateEncodingException e) {
            throw new SignatureException("Failed to encode source stamp certificate", e);
        }
    }

    @Override // com.android.apksig.ApkSignerEngine
    public Set<String> initWith(byte[] bArr, Set<String> set) {
        V1SchemeVerifier.NamedDigest next;
        Pair<ManifestParser.Section, Map<String, ManifestParser.Section>> manifest = V1SchemeVerifier.parseManifest(bArr, set, new V1SchemeVerifier.Result());
        String jcaMessageDigestAlgorithm = V1SchemeSigner.getJcaMessageDigestAlgorithm(this.mV1ContentDigestAlgorithm);
        for (Map.Entry<String, ManifestParser.Section> entry : manifest.getSecond().entrySet()) {
            String key = entry.getKey();
            if (V1SchemeSigner.isJarEntryDigestNeededInManifest(entry.getKey()) && isDebuggable(key)) {
                Iterator<V1SchemeVerifier.NamedDigest> it = V1SchemeVerifier.getDigestsToVerify(entry.getValue(), "-Digest", this.mMinSdkVersion, Integer.MAX_VALUE).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!next.jcaDigestAlgorithm.equals(jcaMessageDigestAlgorithm));
                if (next != null) {
                    this.mOutputJarEntryDigests.put(key, next.digest);
                }
            }
        }
        return this.mOutputJarEntryDigests.keySet();
    }

    @Override // com.android.apksig.ApkSignerEngine
    public void inputApkSigningBlock(DataSource dataSource) {
        checkNotClosed();
        if (dataSource == null || dataSource.size() == 0 || !this.mOtherSignersSignaturesPreserved) {
            return;
        }
        this.mPreservedSignatureBlocks = new ArrayList();
        try {
            boolean z = false;
            for (Pair<byte[], Integer> pair : ApkSigningBlockUtils.getApkSignatureBlocks(dataSource)) {
                if (pair.getSecond().intValue() == 1896449818) {
                    if (this.mV2SigningEnabled) {
                        List<Pair<List<X509Certificate>, byte[]>> apkSignatureBlockSigners = ApkSigningBlockUtils.getApkSignatureBlockSigners(pair.getFirst());
                        this.mPreservedV2Signers = new ArrayList(apkSignatureBlockSigners.size());
                        for (Pair<List<X509Certificate>, byte[]> pair2 : apkSignatureBlockSigners) {
                            if (!isConfiguredWithSigner(pair2.getFirst())) {
                                this.mPreservedV2Signers.add(pair2.getSecond());
                                z = true;
                            }
                        }
                    } else {
                        this.mPreservedSignatureBlocks.add(pair);
                        z = true;
                    }
                } else if (pair.getSecond().intValue() == -262969152) {
                    if (!this.mV3SigningEnabled) {
                        throw new IllegalStateException("Preserving an existing V3 signature is not supported");
                    }
                    List<Pair<List<X509Certificate>, byte[]>> apkSignatureBlockSigners2 = ApkSigningBlockUtils.getApkSignatureBlockSigners(pair.getFirst());
                    if (apkSignatureBlockSigners2.size() > 1) {
                        throw new IllegalArgumentException("The provided APK signing block contains " + apkSignatureBlockSigners2.size() + " V3 signers; the V3 signature scheme only supports one signer");
                    }
                    if (apkSignatureBlockSigners2.size() == 1 && !isConfiguredWithSigner(apkSignatureBlockSigners2.get(0).getFirst())) {
                        throw new IllegalStateException("The V3 signature scheme only supports one signer; a request was made to preserve the existing V3 signature, but the engine is configured to sign with a different signer");
                    }
                } else if (!DISCARDED_SIGNATURE_BLOCK_IDS.contains(pair.getSecond())) {
                    this.mPreservedSignatureBlocks.add(pair);
                }
            }
            if (this.mV3SigningEnabled && z) {
                k2d.a("Signature scheme V3+ only supports a single signer and cannot be appended to the existing signature scheme blocks");
            }
        } catch (ApkFormatException | IOException | CertificateException e) {
            nrd.a("Unable to parse the provided signing block", e);
        }
    }

    @Override // com.android.apksig.ApkSignerEngine
    public ApkSignerEngine.InputJarEntryInstructions inputJarEntry(String str) {
        checkNotClosed();
        ApkSignerEngine.InputJarEntryInstructions.OutputPolicy inputJarEntryOutputPolicy = getInputJarEntryOutputPolicy(str);
        int i = AnonymousClass1.$SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy[inputJarEntryOutputPolicy.ordinal()];
        if (i == 1) {
            return new ApkSignerEngine.InputJarEntryInstructions(ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.SKIP);
        }
        if (i == 2) {
            return new ApkSignerEngine.InputJarEntryInstructions(ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT);
        }
        if (i != 3) {
            y04.a("Unsupported output policy: ", inputJarEntryOutputPolicy);
            return null;
        }
        if (!"META-INF/MANIFEST.MF".equals(str)) {
            return new ApkSignerEngine.InputJarEntryInstructions(ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT_BY_ENGINE);
        }
        GetJarEntryDataRequest getJarEntryDataRequest = new GetJarEntryDataRequest(str, null);
        this.mInputJarManifestEntryDataRequest = getJarEntryDataRequest;
        return new ApkSignerEngine.InputJarEntryInstructions(ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT_BY_ENGINE, getJarEntryDataRequest);
    }

    @Override // com.android.apksig.ApkSignerEngine
    public ApkSignerEngine.InputJarEntryInstructions.OutputPolicy inputJarEntryRemoved(String str) {
        checkNotClosed();
        return getInputJarEntryOutputPolicy(str);
    }

    @Override // com.android.apksig.ApkSignerEngine
    public boolean isEligibleForSourceStamp() {
        if (this.mSourceStampSignerConfig != null) {
            return this.mV2SigningEnabled || this.mV3SigningEnabled || this.mV1SigningEnabled;
        }
        return false;
    }

    @Override // com.android.apksig.ApkSignerEngine
    public void outputDone() {
        checkNotClosed();
        checkV1SigningDoneIfEnabled();
        checkSigningBlockDoneIfEnabled();
    }

    @Override // com.android.apksig.ApkSignerEngine
    public ApkSignerEngine.OutputJarSignatureRequest outputJarEntries() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, ApkFormatException {
        List<Pair> listSign;
        checkNotClosed();
        AnonymousClass1 anonymousClass1 = null;
        if (!this.mV1SignaturePending) {
            return null;
        }
        GetJarEntryDataRequest getJarEntryDataRequest = this.mInputJarManifestEntryDataRequest;
        if (getJarEntryDataRequest != null && !getJarEntryDataRequest.isDone()) {
            sle.a("Still waiting to inspect input APK's ", this.mInputJarManifestEntryDataRequest.getEntryName());
            return null;
        }
        for (GetJarEntryDataDigestRequest getJarEntryDataDigestRequest : this.mOutputJarEntryDigestRequests.values()) {
            String entryName = getJarEntryDataDigestRequest.getEntryName();
            if (!getJarEntryDataDigestRequest.isDone()) {
                qu7.a("Still waiting to inspect output APK's ", entryName);
                return null;
            }
            this.mOutputJarEntryDigests.put(entryName, getJarEntryDataDigestRequest.getDigest());
        }
        if (isEligibleForSourceStamp()) {
            MessageDigest messageDigest = MessageDigest.getInstance(V1SchemeSigner.getJcaMessageDigestAlgorithm(this.mV1ContentDigestAlgorithm));
            messageDigest.update(generateSourceStampCertificateDigest());
            this.mOutputJarEntryDigests.put("stamp-cert-sha256", messageDigest.digest());
        }
        this.mOutputJarEntryDigestRequests.clear();
        for (GetJarEntryDataRequest getJarEntryDataRequest2 : this.mOutputSignatureJarEntryDataRequests.values()) {
            if (!getJarEntryDataRequest2.isDone()) {
                sle.a("Still waiting to inspect output APK's ", getJarEntryDataRequest2.getEntryName());
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (this.mV2SigningEnabled) {
            arrayList.add(2);
        }
        if (this.mV3SigningEnabled) {
            arrayList.add(3);
        }
        GetJarEntryDataRequest getJarEntryDataRequest3 = this.mInputJarManifestEntryDataRequest;
        byte[] data = getJarEntryDataRequest3 != null ? getJarEntryDataRequest3.getData() : null;
        if (isEligibleForSourceStamp()) {
            data = V1SchemeSigner.generateManifestFile(this.mV1ContentDigestAlgorithm, this.mOutputJarEntryDigests, data).contents;
        }
        byte[] bArr = data;
        checkOutputApkNotDebuggableIfDebuggableMustBeRejected();
        OutputJarSignatureRequestImpl outputJarSignatureRequestImpl = this.mAddV1SignatureRequest;
        if (outputJarSignatureRequestImpl == null || !outputJarSignatureRequestImpl.isDone()) {
            try {
                listSign = V1SchemeSigner.sign(this.mV1SignerConfigs, this.mV1ContentDigestAlgorithm, this.mOutputJarEntryDigests, arrayList, bArr, this.mCreatedBy);
            } catch (CertificateException e) {
                throw new SignatureException("Failed to generate v1 signature", e);
            }
        } else {
            V1SchemeSigner.OutputManifestFile outputManifestFileGenerateManifestFile = V1SchemeSigner.generateManifestFile(this.mV1ContentDigestAlgorithm, this.mOutputJarEntryDigests, bArr);
            if (Arrays.equals(outputManifestFileGenerateManifestFile.contents, this.mEmittedSignatureJarEntryData.get("META-INF/MANIFEST.MF"))) {
                listSign = new ArrayList();
                for (Map.Entry<String, byte[]> entry : this.mEmittedSignatureJarEntryData.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    GetJarEntryDataRequest getJarEntryDataRequest4 = this.mOutputSignatureJarEntryDataRequests.get(key);
                    if (getJarEntryDataRequest4 == null) {
                        listSign.add(Pair.of(key, value));
                    } else if (!Arrays.equals(value, getJarEntryDataRequest4.getData())) {
                        listSign.add(Pair.of(key, value));
                    }
                }
                if (listSign.isEmpty()) {
                    return null;
                }
            } else {
                try {
                    listSign = V1SchemeSigner.signManifest(this.mV1SignerConfigs, this.mV1ContentDigestAlgorithm, arrayList, this.mCreatedBy, outputManifestFileGenerateManifestFile);
                } catch (CertificateException e2) {
                    throw new SignatureException("Failed to generate v1 signature", e2);
                }
            }
        }
        if (listSign.isEmpty()) {
            this.mV1SignaturePending = false;
            return null;
        }
        ArrayList arrayList2 = new ArrayList(listSign.size());
        for (Pair pair : listSign) {
            String str = (String) pair.getFirst();
            byte[] bArr2 = (byte[]) pair.getSecond();
            arrayList2.add(new ApkSignerEngine.OutputJarSignatureRequest.JarEntry(str, bArr2));
            this.mEmittedSignatureJarEntryData.put(str, bArr2);
        }
        OutputJarSignatureRequestImpl outputJarSignatureRequestImpl2 = new OutputJarSignatureRequestImpl(arrayList2, anonymousClass1);
        this.mAddV1SignatureRequest = outputJarSignatureRequestImpl2;
        return outputJarSignatureRequestImpl2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.apksig.ApkSignerEngine
    public ApkSignerEngine.InspectJarEntryRequest outputJarEntry(String str) {
        GetJarEntryDataRequest getJarEntryDataRequest;
        checkNotClosed();
        invalidateV2Signature();
        if (!isDebuggable(str)) {
            forgetOutputApkDebuggableStatus();
        }
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        Object[] objArr4 = 0;
        Object[] objArr5 = 0;
        Object[] objArr6 = 0;
        if (!this.mV1SigningEnabled) {
            if (isDebuggable(str)) {
                return null;
            }
            GetJarEntryDataRequest getJarEntryDataRequest2 = new GetJarEntryDataRequest(str, objArr6 == true ? 1 : 0);
            this.mOutputAndroidManifestEntryDataRequest = getJarEntryDataRequest2;
            return getJarEntryDataRequest2;
        }
        if (V1SchemeSigner.isJarEntryDigestNeededInManifest(str)) {
            invalidateV1Signature();
            GetJarEntryDataDigestRequest getJarEntryDataDigestRequest = new GetJarEntryDataDigestRequest(str, V1SchemeSigner.getJcaMessageDigestAlgorithm(this.mV1ContentDigestAlgorithm), objArr5 == true ? 1 : 0);
            this.mOutputJarEntryDigestRequests.put(str, getJarEntryDataDigestRequest);
            this.mOutputJarEntryDigests.remove(str);
            if (this.mDebuggableApkPermitted || !ApkUtils.ANDROID_MANIFEST_ZIP_ENTRY_NAME.equals(str)) {
                return getJarEntryDataDigestRequest;
            }
            GetJarEntryDataRequest getJarEntryDataRequest3 = new GetJarEntryDataRequest(str, objArr4 == true ? 1 : 0);
            this.mOutputAndroidManifestEntryDataRequest = getJarEntryDataRequest3;
            return new CompoundInspectJarEntryRequest(str, new ApkSignerEngine.InspectJarEntryRequest[]{getJarEntryDataRequest3, getJarEntryDataDigestRequest}, objArr3 == true ? 1 : 0);
        }
        if (!this.mSignatureExpectedOutputJarEntryNames.contains(str)) {
            return null;
        }
        invalidateV1Signature();
        if ("META-INF/MANIFEST.MF".equals(str)) {
            getJarEntryDataRequest = new GetJarEntryDataRequest(str, objArr2 == true ? 1 : 0);
            this.mInputJarManifestEntryDataRequest = getJarEntryDataRequest;
        } else {
            getJarEntryDataRequest = this.mEmittedSignatureJarEntryData.containsKey(str) ? new GetJarEntryDataRequest(str, objArr == true ? 1 : 0) : null;
        }
        if (getJarEntryDataRequest != null) {
            this.mOutputSignatureJarEntryDataRequests.put(str, getJarEntryDataRequest);
        }
        return getJarEntryDataRequest;
    }

    @Override // com.android.apksig.ApkSignerEngine
    public void outputJarEntryRemoved(String str) {
        checkNotClosed();
        invalidateV2Signature();
        if (this.mV1SigningEnabled) {
            if (!V1SchemeSigner.isJarEntryDigestNeededInManifest(str)) {
                if (this.mSignatureExpectedOutputJarEntryNames.contains(str)) {
                    invalidateV1Signature();
                }
            } else {
                invalidateV1Signature();
                this.mOutputJarEntryDigests.remove(str);
                this.mOutputJarEntryDigestRequests.remove(str);
                this.mOutputSignatureJarEntryDataRequests.remove(str);
            }
        }
    }

    @Override // com.android.apksig.ApkSignerEngine
    @Deprecated
    public ApkSignerEngine.OutputApkSigningBlockRequest outputZipSections(DataSource dataSource, DataSource dataSource2, DataSource dataSource3) throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        return outputZipSectionsInternal(dataSource, dataSource2, dataSource3, false);
    }

    @Override // com.android.apksig.ApkSignerEngine
    public ApkSignerEngine.OutputApkSigningBlockRequest2 outputZipSections2(DataSource dataSource, DataSource dataSource2, DataSource dataSource3) throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        return outputZipSectionsInternal(dataSource, dataSource2, dataSource3, true);
    }

    public byte[] produceV4Signature(DataSource dataSource, OutputStream outputStream) throws SignatureException {
        if (outputStream == null) {
            d54.a("Missing V4 output streams.");
            return null;
        }
        try {
            Pair<V4Signature, byte[]> pairGenerateV4Signature = V4SchemeSigner.generateV4Signature(dataSource, createV4SignerConfig());
            pairGenerateV4Signature.getFirst().writeTo(outputStream);
            return pairGenerateV4Signature.getSecond();
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new SignatureException("V4 signing failed", e);
        }
    }

    @Override // com.android.apksig.ApkSignerEngine
    public void setExecutor(RunnablesExecutor runnablesExecutor) {
        this.mExecutor = runnablesExecutor;
    }

    @Override // com.android.apksig.ApkSignerEngine
    public void signV4(DataSource dataSource, File file, boolean z) throws SignatureException {
        if (file == null) {
            if (z) {
                return;
            }
            d54.a("Missing V4 output file.");
        } else {
            try {
                V4SchemeSigner.generateV4Signature(dataSource, createV4SignerConfig(), file);
            } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
                if (!z) {
                    throw new SignatureException("V4 signing failed", e);
                }
            }
        }
    }

    public static class GetJarEntryDataRequest implements ApkSignerEngine.InspectJarEntryRequest {
        private DataSink mDataSink;
        private ByteArrayOutputStream mDataSinkBuf;
        private boolean mDone;
        private final String mEntryName;
        private final Object mLock;

        private GetJarEntryDataRequest(String str) {
            this.mLock = new Object();
            this.mEntryName = str;
        }

        private void checkNotDone() throws IllegalStateException {
            synchronized (this.mLock) {
                try {
                    if (this.mDone) {
                        throw new IllegalStateException("Already done");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getData() {
            byte[] byteArray;
            synchronized (this.mLock) {
                try {
                    if (!this.mDone) {
                        throw new IllegalStateException("Not yet done");
                    }
                    ByteArrayOutputStream byteArrayOutputStream = this.mDataSinkBuf;
                    byteArray = byteArrayOutputStream != null ? byteArrayOutputStream.toByteArray() : new byte[0];
                } catch (Throwable th) {
                    throw th;
                }
            }
            return byteArray;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDone() {
            boolean z;
            synchronized (this.mLock) {
                z = this.mDone;
            }
            return z;
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public void done() {
            synchronized (this.mLock) {
                try {
                    if (this.mDone) {
                        return;
                    }
                    this.mDone = true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public DataSink getDataSink() {
            DataSink dataSink;
            synchronized (this.mLock) {
                try {
                    checkNotDone();
                    if (this.mDataSinkBuf == null) {
                        this.mDataSinkBuf = new ByteArrayOutputStream();
                    }
                    if (this.mDataSink == null) {
                        this.mDataSink = DataSinks.asDataSink(this.mDataSinkBuf);
                    }
                    dataSink = this.mDataSink;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return dataSink;
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public String getEntryName() {
            return this.mEntryName;
        }

        public /* synthetic */ GetJarEntryDataRequest(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }
    }

    public static class OutputApkSigningBlockRequestImpl implements ApkSignerEngine.OutputApkSigningBlockRequest, ApkSignerEngine.OutputApkSigningBlockRequest2 {
        private final byte[] mApkSigningBlock;
        private volatile boolean mDone;
        private final int mPaddingBeforeApkSigningBlock;

        private OutputApkSigningBlockRequestImpl(byte[] bArr, int i) {
            this.mApkSigningBlock = (byte[]) bArr.clone();
            this.mPaddingBeforeApkSigningBlock = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDone() {
            return this.mDone;
        }

        @Override // com.android.apksig.ApkSignerEngine.OutputApkSigningBlockRequest, com.android.apksig.ApkSignerEngine.OutputApkSigningBlockRequest2
        public void done() {
            this.mDone = true;
        }

        @Override // com.android.apksig.ApkSignerEngine.OutputApkSigningBlockRequest, com.android.apksig.ApkSignerEngine.OutputApkSigningBlockRequest2
        public byte[] getApkSigningBlock() {
            return (byte[]) this.mApkSigningBlock.clone();
        }

        @Override // com.android.apksig.ApkSignerEngine.OutputApkSigningBlockRequest2
        public int getPaddingSizeBeforeApkSigningBlock() {
            return this.mPaddingBeforeApkSigningBlock;
        }

        public /* synthetic */ OutputApkSigningBlockRequestImpl(byte[] bArr, int i, AnonymousClass1 anonymousClass1) {
            this(bArr, i);
        }
    }

    public static class CompoundInspectJarEntryRequest implements ApkSignerEngine.InspectJarEntryRequest {
        private final String mEntryName;
        private final Object mLock;
        private final ApkSignerEngine.InspectJarEntryRequest[] mRequests;
        private DataSink mSink;

        private CompoundInspectJarEntryRequest(String str, ApkSignerEngine.InspectJarEntryRequest... inspectJarEntryRequestArr) {
            this.mLock = new Object();
            this.mEntryName = str;
            this.mRequests = inspectJarEntryRequestArr;
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public void done() {
            for (ApkSignerEngine.InspectJarEntryRequest inspectJarEntryRequest : this.mRequests) {
                inspectJarEntryRequest.done();
            }
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public DataSink getDataSink() {
            DataSink dataSink;
            synchronized (this.mLock) {
                try {
                    if (this.mSink == null) {
                        int length = this.mRequests.length;
                        DataSink[] dataSinkArr = new DataSink[length];
                        for (int i = 0; i < length; i++) {
                            dataSinkArr[i] = this.mRequests[i].getDataSink();
                        }
                        this.mSink = new TeeDataSink(dataSinkArr);
                    }
                    dataSink = this.mSink;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return dataSink;
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public String getEntryName() {
            return this.mEntryName;
        }

        public /* synthetic */ CompoundInspectJarEntryRequest(String str, ApkSignerEngine.InspectJarEntryRequest[] inspectJarEntryRequestArr, AnonymousClass1 anonymousClass1) {
            this(str, inspectJarEntryRequestArr);
        }
    }

    public static class GetJarEntryDataDigestRequest implements ApkSignerEngine.InspectJarEntryRequest {
        private DataSink mDataSink;
        private byte[] mDigest;
        private boolean mDone;
        private final String mEntryName;
        private final String mJcaDigestAlgorithm;
        private final Object mLock;
        private MessageDigest mMessageDigest;

        private GetJarEntryDataDigestRequest(String str, String str2) {
            this.mLock = new Object();
            this.mEntryName = str;
            this.mJcaDigestAlgorithm = str2;
        }

        private void checkNotDone() throws IllegalStateException {
            synchronized (this.mLock) {
                try {
                    if (this.mDone) {
                        throw new IllegalStateException("Already done");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getDigest() {
            byte[] bArr;
            synchronized (this.mLock) {
                try {
                    if (!this.mDone) {
                        throw new IllegalStateException("Not yet done");
                    }
                    bArr = (byte[]) this.mDigest.clone();
                } catch (Throwable th) {
                    throw th;
                }
            }
            return bArr;
        }

        private MessageDigest getMessageDigest() {
            MessageDigest messageDigest;
            synchronized (this.mLock) {
                if (this.mMessageDigest == null) {
                    try {
                        this.mMessageDigest = MessageDigest.getInstance(this.mJcaDigestAlgorithm);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(this.mJcaDigestAlgorithm + " MessageDigest not available", e);
                    }
                }
                messageDigest = this.mMessageDigest;
            }
            return messageDigest;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDone() {
            boolean z;
            synchronized (this.mLock) {
                z = this.mDone;
            }
            return z;
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public void done() {
            synchronized (this.mLock) {
                try {
                    if (this.mDone) {
                        return;
                    }
                    this.mDone = true;
                    this.mDigest = getMessageDigest().digest();
                    this.mMessageDigest = null;
                    this.mDataSink = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public DataSink getDataSink() {
            DataSink dataSink;
            synchronized (this.mLock) {
                try {
                    checkNotDone();
                    if (this.mDataSink == null) {
                        this.mDataSink = DataSinks.asDataSink(getMessageDigest());
                    }
                    dataSink = this.mDataSink;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return dataSink;
        }

        @Override // com.android.apksig.ApkSignerEngine.InspectJarEntryRequest
        public String getEntryName() {
            return this.mEntryName;
        }

        public /* synthetic */ GetJarEntryDataDigestRequest(String str, String str2, AnonymousClass1 anonymousClass1) {
            this(str, str2);
        }
    }

    public static class OutputJarSignatureRequestImpl implements ApkSignerEngine.OutputJarSignatureRequest {
        private final List<ApkSignerEngine.OutputJarSignatureRequest.JarEntry> mAdditionalJarEntries;
        private volatile boolean mDone;

        private OutputJarSignatureRequestImpl(List<ApkSignerEngine.OutputJarSignatureRequest.JarEntry> list) {
            this.mAdditionalJarEntries = Collections.unmodifiableList(new ArrayList(list));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDone() {
            return this.mDone;
        }

        @Override // com.android.apksig.ApkSignerEngine.OutputJarSignatureRequest
        public void done() {
            this.mDone = true;
        }

        @Override // com.android.apksig.ApkSignerEngine.OutputJarSignatureRequest
        public List<ApkSignerEngine.OutputJarSignatureRequest.JarEntry> getAdditionalJarEntries() {
            return this.mAdditionalJarEntries;
        }

        public /* synthetic */ OutputJarSignatureRequestImpl(List list, AnonymousClass1 anonymousClass1) {
            this(list);
        }
    }

    public static class SignerConfig {
        private final List<X509Certificate> mCertificates;
        private final boolean mDeterministicDsaSigning;
        private final KeyConfig mKeyConfig;
        private final int mMinSdkVersion;
        private final String mName;
        private final boolean mSignerTargetsDevRelease;
        private final SigningCertificateLineage mSigningCertificateLineage;

        private SignerConfig(Builder builder) {
            this.mName = builder.mName;
            this.mKeyConfig = builder.mKeyConfig;
            this.mCertificates = Collections.unmodifiableList(new ArrayList(builder.mCertificates));
            this.mDeterministicDsaSigning = builder.mDeterministicDsaSigning;
            this.mMinSdkVersion = builder.mMinSdkVersion;
            this.mSignerTargetsDevRelease = builder.mSignerTargetsDevRelease;
            this.mSigningCertificateLineage = builder.mSigningCertificateLineage;
        }

        public static /* synthetic */ PrivateKey a(KeyConfig.Kms kms) {
            return null;
        }

        public List<X509Certificate> getCertificates() {
            return this.mCertificates;
        }

        public boolean getDeterministicDsaSigning() {
            return this.mDeterministicDsaSigning;
        }

        public KeyConfig getKeyConfig() {
            return this.mKeyConfig;
        }

        public int getMinSdkVersion() {
            return this.mMinSdkVersion;
        }

        public String getName() {
            return this.mName;
        }

        @Deprecated
        public PrivateKey getPrivateKey() {
            return (PrivateKey) this.mKeyConfig.match(new Function() { // from class: jf3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((KeyConfig.Jca) obj).privateKey;
                }
            }, new Function() { // from class: kf3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DefaultApkSignerEngine.SignerConfig.a((KeyConfig.Kms) obj);
                }
            });
        }

        public boolean getSignerTargetsDevRelease() {
            return this.mSignerTargetsDevRelease;
        }

        public SigningCertificateLineage getSigningCertificateLineage() {
            return this.mSigningCertificateLineage;
        }

        public static class Builder {
            private final List<X509Certificate> mCertificates;
            private final boolean mDeterministicDsaSigning;
            private final KeyConfig mKeyConfig;
            private int mMinSdkVersion;
            private final String mName;
            private boolean mSignerTargetsDevRelease;
            private SigningCertificateLineage mSigningCertificateLineage;

            @Deprecated
            public Builder(String str, PrivateKey privateKey, List<X509Certificate> list, boolean z) {
                if (str.isEmpty()) {
                    w01.a("Empty name");
                    throw null;
                }
                this.mName = str;
                this.mKeyConfig = new KeyConfig.Jca(privateKey);
                this.mCertificates = new ArrayList(list);
                this.mDeterministicDsaSigning = z;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Builder setSignerTargetsDevRelease(boolean z) {
                if (!z || this.mMinSdkVersion >= 33) {
                    this.mSignerTargetsDevRelease = z;
                    return this;
                }
                w01.a("Rotation can only target a development release for signers targeting 33 or later");
                return null;
            }

            public SignerConfig build() {
                return new SignerConfig(this, null);
            }

            public Builder setLineageForMinSdkVersion(SigningCertificateLineage signingCertificateLineage, int i) {
                if (i < 28) {
                    w01.a("SDK targeted signing config is only supported with the V3 signature scheme on Android P (SDK version 28) and later");
                    return null;
                }
                if (i < 33) {
                    i = 28;
                }
                this.mMinSdkVersion = i;
                if (signingCertificateLineage == null || signingCertificateLineage.isCertificateInLineage(this.mCertificates.get(0))) {
                    this.mSigningCertificateLineage = signingCertificateLineage;
                    return this;
                }
                yba.a("The provided lineage does not contain the signing certificate, ", this.mCertificates.get(0).getSubjectDN(), ", for this SignerConfig");
                return null;
            }

            public Builder setMinSdkVersion(int i) {
                return setLineageForMinSdkVersion(null, i);
            }

            @Deprecated
            public Builder(String str, PrivateKey privateKey, List<X509Certificate> list) {
                this(str, privateKey, list, false);
            }

            public Builder(String str, KeyConfig keyConfig, List<X509Certificate> list) {
                this(str, keyConfig, list, false);
            }

            public Builder(String str, KeyConfig keyConfig, List<X509Certificate> list, boolean z) {
                if (!str.isEmpty()) {
                    this.mName = str;
                    this.mKeyConfig = keyConfig;
                    this.mCertificates = new ArrayList(list);
                    this.mDeterministicDsaSigning = z;
                    return;
                }
                w01.a("Empty name");
                throw null;
            }
        }

        public /* synthetic */ SignerConfig(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }
    }

    public /* synthetic */ DefaultApkSignerEngine(List list, List list2, SignerConfig signerConfig, SigningCertificateLineage signingCertificateLineage, boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str, SigningCertificateLineage signingCertificateLineage2, AnonymousClass1 anonymousClass1) throws InvalidKeyException {
        this(list, list2, signerConfig, signingCertificateLineage, z, i, z2, z3, z4, z5, z6, z7, str, signingCertificateLineage2);
    }
}
