package com.android.apksig;

import com.android.apksig.KeyConfig;
import com.android.apksig.SigningCertificateLineage;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.SignatureInfo;
import com.android.apksig.internal.apk.v3.V3SchemeSigner;
import com.android.apksig.internal.apk.v3.V3SigningCertificateLineage;
import com.android.apksig.internal.util.ByteBufferUtils;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.internal.util.RandomAccessFileDataSink;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import com.android.apksig.zip.ZipFormatException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SigningCertificateLineage {
    private static final int CURRENT_VERSION = 1;
    private static final int FIRST_VERSION = 1;
    public static final int MAGIC = 1056913873;
    private static final int PAST_CERT_AUTH = 16;
    private static final int PAST_CERT_INSTALLED_DATA = 1;
    private static final int PAST_CERT_PERMISSION = 4;
    private static final int PAST_CERT_ROLLBACK = 8;
    private static final int PAST_CERT_SHARED_USER_ID = 2;
    private final int mMinSdkVersion;
    private final List<V3SigningCertificateLineage.SigningCertificateNode> mSigningLineage;

    private SigningCertificateLineage(int i, List<V3SigningCertificateLineage.SigningCertificateNode> list) {
        this.mMinSdkVersion = i;
        this.mSigningLineage = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int calculateDefaultFlags() {
        return 23;
    }

    private static int calculateMinSdkVersion(List<V3SigningCertificateLineage.SigningCertificateNode> list) {
        int minSdkVersion;
        if (list == null) {
            w01.a("Can't calculate minimum SDK version of null nodes");
            return 0;
        }
        Iterator<V3SigningCertificateLineage.SigningCertificateNode> it = list.iterator();
        int i = 28;
        while (it.hasNext()) {
            SignatureAlgorithm signatureAlgorithm = it.next().sigAlgorithm;
            if (signatureAlgorithm != null && (minSdkVersion = signatureAlgorithm.getMinSdkVersion()) > i) {
                i = minSdkVersion;
            }
        }
        return i;
    }

    public static boolean checkLineagesCompatibility(SigningCertificateLineage signingCertificateLineage, SigningCertificateLineage signingCertificateLineage2) {
        ArrayList arrayList = signingCertificateLineage == null ? new ArrayList() : new ArrayList(signingCertificateLineage.getCertificatesInLineage());
        ArrayList arrayList2 = signingCertificateLineage2 == null ? new ArrayList() : new ArrayList(signingCertificateLineage2.getCertificatesInLineage());
        if (arrayList.isEmpty()) {
            return true;
        }
        if (arrayList2.isEmpty()) {
            return false;
        }
        if (arrayList2.size() >= arrayList.size() && arrayList2.subList(0, arrayList.size()).equals(arrayList)) {
            return true;
        }
        ArrayList arrayList3 = new ArrayList(arrayList2);
        ArrayList arrayList4 = new ArrayList(arrayList);
        int iLastIndexOf = arrayList3.lastIndexOf(arrayList4.get(arrayList4.size() - 1));
        if (iLastIndexOf >= 0) {
            return arrayList3.subList(0, iLastIndexOf + 1).equals(arrayList4.subList((arrayList.size() - 1) - iLastIndexOf, arrayList4.size()));
        }
        return arrayList.subList(0, arrayList2.size()).equals(arrayList2) && signingCertificateLineage.getSignerCapabilities((X509Certificate) arrayList.get(arrayList2.size() - 1)).hasRollback();
    }

    public static SigningCertificateLineage consolidateLineages(List<SigningCertificateLineage> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        SigningCertificateLineage signingCertificateLineageMergeLineageWith = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            signingCertificateLineageMergeLineageWith = signingCertificateLineageMergeLineageWith.mergeLineageWith(list.get(i));
        }
        return signingCertificateLineageMergeLineageWith;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SigningCertificateLineage createSigningLineage(int i, SignerConfig signerConfig, SignerCapabilities signerCapabilities, SignerConfig signerConfig2, SignerCapabilities signerCapabilities2) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateEncodingException {
        return new SigningCertificateLineage(i, new ArrayList()).spawnFirstDescendant(signerConfig, signerCapabilities).spawnDescendant(signerConfig, signerConfig2, signerCapabilities2);
    }

    private SignatureAlgorithm getSignatureAlgorithm(SignerConfig signerConfig) throws InvalidKeyException {
        return V3SchemeSigner.getSuggestedSignatureAlgorithms(signerConfig.getCertificate().getPublicKey(), this.mMinSdkVersion, false, false).get(0);
    }

    private static SigningCertificateLineage read(ByteBuffer byteBuffer) throws IOException {
        ApkSigningBlockUtils.checkByteOrderLittleEndian(byteBuffer);
        if (byteBuffer.remaining() < 8) {
            w01.a("Improper SigningCertificateLineage format: insufficient data for header.");
            return null;
        }
        if (byteBuffer.getInt() == 1056913873) {
            return read(byteBuffer, byteBuffer.getInt());
        }
        w01.a("Improper SigningCertificateLineage format: MAGIC header mismatch.");
        return null;
    }

    private static SigningCertificateLineage readFromApkDataSource(DataSource dataSource, boolean z, boolean z2) throws IOException, ApkFormatException {
        String str;
        try {
            ApkUtils.ZipSections zipSectionsFindZipSections = ApkUtils.findZipSections(dataSource);
            ArrayList arrayList = new ArrayList();
            if (z) {
                try {
                    arrayList.add(ApkSigningBlockUtils.findSignature(dataSource, zipSectionsFindZipSections, 462663009, new ApkSigningBlockUtils.Result(31)));
                } catch (ApkSigningBlockUtils.SignatureNotFoundException unused) {
                }
            }
            if (z2) {
                try {
                    arrayList.add(ApkSigningBlockUtils.findSignature(dataSource, zipSectionsFindZipSections, -262969152, new ApkSigningBlockUtils.Result(3)));
                } catch (ApkSigningBlockUtils.SignatureNotFoundException unused2) {
                }
            }
            if (arrayList.isEmpty()) {
                if (z && z2) {
                    str = "The provided APK does not contain a valid V3 nor V3.1 signature block.";
                } else if (z) {
                    str = "The provided APK does not contain a valid V3.1 signature block.";
                } else {
                    str = z2 ? "The provided APK does not contain a valid V3 signature block." : "No signature blocks were requested.";
                }
                w01.a(str);
                return null;
            }
            ArrayList arrayList2 = new ArrayList(1);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(((SignatureInfo) it.next()).signatureBlock);
                while (lengthPrefixedSlice.hasRemaining()) {
                    try {
                        arrayList2.add(readFromSignedData(ApkSigningBlockUtils.getLengthPrefixedSlice(ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice))));
                    } catch (IllegalArgumentException unused3) {
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                return arrayList2.size() > 1 ? consolidateLineages(arrayList2) : (SigningCertificateLineage) arrayList2.get(0);
            }
            w01.a("The provided APK does not contain a valid lineage.");
            return null;
        } catch (ZipFormatException e) {
            throw new ApkFormatException(e.getMessage());
        }
    }

    public static SigningCertificateLineage readFromApkFile(File file) throws IOException, ApkFormatException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            SigningCertificateLineage fromApkDataSource = readFromApkDataSource(DataSources.asDataSource(randomAccessFile, 0L, randomAccessFile.length()));
            randomAccessFile.close();
            return fromApkDataSource;
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static SigningCertificateLineage readFromBytes(byte[] bArr) throws IOException {
        return readFromDataSource(DataSources.asDataSource(ByteBuffer.wrap(bArr)));
    }

    public static SigningCertificateLineage readFromDataSource(DataSource dataSource) throws IOException {
        if (dataSource == null) {
            x0e.a("dataSource == null");
            return null;
        }
        ByteBuffer byteBuffer = dataSource.getByteBuffer(0L, (int) dataSource.size());
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return read(byteBuffer);
    }

    public static SigningCertificateLineage readFromFile(File file) throws IOException {
        if (file != null) {
            return readFromDataSource(DataSources.asDataSource(new RandomAccessFile(file, "r")));
        }
        x0e.a("file == null");
        return null;
    }

    public static SigningCertificateLineage readFromSignedData(ByteBuffer byteBuffer) throws IOException, ApkFormatException {
        ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        byteBuffer.getInt();
        byteBuffer.getInt();
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        ArrayList arrayList = new ArrayList(1);
        while (lengthPrefixedSlice.hasRemaining()) {
            ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
            if (lengthPrefixedSlice2.getInt() == 1000370060) {
                arrayList.add(readFromV3AttributeValue(ByteBufferUtils.toByteArray(lengthPrefixedSlice2)));
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList.size() > 1 ? consolidateLineages(arrayList) : (SigningCertificateLineage) arrayList.get(0);
        }
        w01.a("The signed data does not contain a valid lineage.");
        return null;
    }

    public static SigningCertificateLineage readFromV3AttributeValue(byte[] bArr) throws IOException {
        List<V3SigningCertificateLineage.SigningCertificateNode> signingCertificateLineage = V3SigningCertificateLineage.readSigningCertificateLineage(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN));
        return new SigningCertificateLineage(calculateMinSdkVersion(signingCertificateLineage), signingCertificateLineage);
    }

    public static SigningCertificateLineage readV31FromApkDataSource(DataSource dataSource) throws IOException, ApkFormatException {
        return readFromApkDataSource(dataSource, true, false);
    }

    private SigningCertificateLineage spawnFirstDescendant(SignerConfig signerConfig, SignerCapabilities signerCapabilities) {
        if (!this.mSigningLineage.isEmpty()) {
            k2d.a("SigningCertificateLineage already has its first node");
            return null;
        }
        try {
            getSignatureAlgorithm(signerConfig);
            return new SigningCertificateLineage(this.mMinSdkVersion, Collections.singletonList(new V3SigningCertificateLineage.SigningCertificateNode(signerConfig.getCertificate(), null, null, new byte[0], signerCapabilities.getFlags())));
        } catch (InvalidKeyException e) {
            nrd.a("Algorithm associated with first signing certificate invalid on desired platform versions", e);
            return null;
        }
    }

    private ByteBuffer write() {
        byte[] bArrEncodeSigningCertificateLineage = V3SigningCertificateLineage.encodeSigningCertificateLineage(this.mSigningLineage);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrEncodeSigningCertificateLineage.length + 12);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(MAGIC);
        byteBufferAllocate.putInt(1);
        byteBufferAllocate.putInt(bArrEncodeSigningCertificateLineage.length);
        byteBufferAllocate.put(bArrEncodeSigningCertificateLineage);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    public byte[] encodeSigningCertificateLineage() {
        return V3SigningCertificateLineage.encodeSigningCertificateLineage(this.mSigningLineage);
    }

    public byte[] getBytes() {
        return write().array();
    }

    public List<X509Certificate> getCertificatesInLineage() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mSigningLineage.size(); i++) {
            arrayList.add(this.mSigningLineage.get(i).signingCert);
        }
        return arrayList;
    }

    public SignerCapabilities getSignerCapabilities(X509Certificate x509Certificate) {
        if (x509Certificate == null) {
            x0e.a("cert == null");
            return null;
        }
        for (int i = 0; i < this.mSigningLineage.size(); i++) {
            V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode = this.mSigningLineage.get(i);
            if (signingCertificateNode.signingCert.equals(x509Certificate)) {
                return new SignerCapabilities.Builder(signingCertificateNode.flags).build();
            }
        }
        yba.a("Certificate (", x509Certificate.getSubjectDN(), ") not found in the SigningCertificateLineage");
        return null;
    }

    public SigningCertificateLineage getSubLineage(X509Certificate x509Certificate) {
        if (x509Certificate == null) {
            x0e.a("x509Certificate == null");
            return null;
        }
        for (int i = 0; i < this.mSigningLineage.size(); i++) {
            if (this.mSigningLineage.get(i).signingCert.equals(x509Certificate)) {
                return new SigningCertificateLineage(this.mMinSdkVersion, new ArrayList(this.mSigningLineage.subList(0, i + 1)));
            }
        }
        w01.a("Certificate not found in SigningCertificateLineage");
        return null;
    }

    public boolean isCertificateInLineage(X509Certificate x509Certificate) {
        if (x509Certificate == null) {
            x0e.a("cert == null");
            return false;
        }
        for (int i = 0; i < this.mSigningLineage.size(); i++) {
            if (this.mSigningLineage.get(i).signingCert.equals(x509Certificate)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCertificateLatestInLineage(X509Certificate x509Certificate) {
        if (x509Certificate != null) {
            List<V3SigningCertificateLineage.SigningCertificateNode> list = this.mSigningLineage;
            return list.get(list.size() - 1).signingCert.equals(x509Certificate);
        }
        x0e.a("cert == null");
        return false;
    }

    public boolean isSignerInLineage(SignerConfig signerConfig) {
        if (signerConfig != null) {
            return isCertificateInLineage(signerConfig.getCertificate());
        }
        x0e.a("config == null");
        return false;
    }

    public SigningCertificateLineage mergeLineageWith(SigningCertificateLineage signingCertificateLineage) {
        SigningCertificateLineage signingCertificateLineage2;
        SigningCertificateLineage signingCertificateLineage3;
        int i = 0;
        if (signingCertificateLineage.isCertificateInLineage(this.mSigningLineage.get(0).signingCert)) {
            signingCertificateLineage3 = this;
            signingCertificateLineage2 = signingCertificateLineage;
        } else {
            signingCertificateLineage2 = this;
            signingCertificateLineage3 = signingCertificateLineage;
        }
        V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode = signingCertificateLineage3.mSigningLineage.get(0);
        ArrayList arrayList = new ArrayList();
        while (i < signingCertificateLineage2.size()) {
            int i2 = i + 1;
            V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode2 = signingCertificateLineage2.mSigningLineage.get(i);
            if (signingCertificateNode2.signingCert.equals(signingCertificateNode.signingCert)) {
                i = i2;
                break;
            }
            arrayList.add(signingCertificateNode2);
            i = i2;
        }
        if (i == arrayList.size()) {
            w01.a("The provided lineage is not a descendant or an ancestor of this lineage");
            return null;
        }
        arrayList.add(signingCertificateNode);
        int i3 = 1;
        while (i < signingCertificateLineage2.size() && i3 < signingCertificateLineage3.size()) {
            int i4 = i + 1;
            V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode3 = signingCertificateLineage2.mSigningLineage.get(i);
            int i5 = i3 + 1;
            V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode4 = signingCertificateLineage3.mSigningLineage.get(i3);
            if (!signingCertificateNode3.signingCert.equals(signingCertificateNode4.signingCert)) {
                w01.a("The provided lineage diverges from this lineage");
                return null;
            }
            arrayList.add(signingCertificateNode4);
            i = i4;
            i3 = i5;
        }
        while (i < signingCertificateLineage2.size()) {
            arrayList.add(signingCertificateLineage2.mSigningLineage.get(i));
            i++;
        }
        while (i3 < signingCertificateLineage3.size()) {
            arrayList.add(signingCertificateLineage3.mSigningLineage.get(i3));
            i3++;
        }
        return new SigningCertificateLineage(Math.min(this.mMinSdkVersion, signingCertificateLineage.mMinSdkVersion), arrayList);
    }

    public int size() {
        return this.mSigningLineage.size();
    }

    public List<DefaultApkSignerEngine.SignerConfig> sortSignerConfigs(List<DefaultApkSignerEngine.SignerConfig> list) {
        if (list == null) {
            x0e.a("signerConfigs == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < this.mSigningLineage.size(); i++) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                DefaultApkSignerEngine.SignerConfig signerConfig = list.get(i2);
                if (this.mSigningLineage.get(i).signingCert.equals(signerConfig.getCertificates().get(0))) {
                    arrayList.add(signerConfig);
                    break;
                }
            }
        }
        if (arrayList.size() == list.size()) {
            return arrayList;
        }
        w01.a("SignerConfigs supplied which are not present in the SigningCertificateLineage");
        return null;
    }

    public SigningCertificateLineage spawnDescendant(SignerConfig signerConfig, SignerConfig signerConfig2, SignerCapabilities signerCapabilities) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateEncodingException {
        if (signerConfig == null) {
            x0e.a("parent == null");
            return null;
        }
        if (signerConfig2 == null) {
            x0e.a("child == null");
            return null;
        }
        if (signerCapabilities == null) {
            x0e.a("childCapabilities == null");
            return null;
        }
        if (this.mSigningLineage.isEmpty()) {
            w01.a("Cannot spawn descendant signing certificate on an empty SigningCertificateLineage: no parent node");
            return null;
        }
        List<V3SigningCertificateLineage.SigningCertificateNode> list = this.mSigningLineage;
        V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode = list.get(list.size() - 1);
        if (!Arrays.equals(signingCertificateNode.signingCert.getEncoded(), signerConfig.getCertificate().getEncoded())) {
            w01.a("SignerConfig Certificate containing private key to sign the new SigningCertificateLineage record does not match the existing most recent record");
            return null;
        }
        SignatureAlgorithm signatureAlgorithm = getSignatureAlgorithm(signerConfig);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(V3SigningCertificateLineage.encodeSignedData(signerConfig2.getCertificate(), signatureAlgorithm.getId()));
        byteBufferWrap.position(4);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBufferWrap.remaining());
        byteBufferAllocate.put(byteBufferWrap);
        byte[] bArrArray = byteBufferAllocate.array();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(signerConfig.getCertificate());
        ApkSigningBlockUtils.SignerConfig signerConfig3 = new ApkSigningBlockUtils.SignerConfig();
        signerConfig3.keyConfig = signerConfig.getKeyConfig();
        signerConfig3.certificates = arrayList;
        signerConfig3.signatureAlgorithms = Collections.singletonList(signatureAlgorithm);
        List<Pair<Integer, byte[]>> listGenerateSignaturesOverData = ApkSigningBlockUtils.generateSignaturesOverData(signerConfig3, bArrArray);
        SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(listGenerateSignaturesOverData.get(0).getFirst().intValue());
        byte[] second = listGenerateSignaturesOverData.get(0).getSecond();
        signingCertificateNode.sigAlgorithm = signatureAlgorithmFindById;
        V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode2 = new V3SigningCertificateLineage.SigningCertificateNode(signerConfig2.getCertificate(), signatureAlgorithmFindById, null, second, signerCapabilities.getFlags());
        ArrayList arrayList2 = new ArrayList(this.mSigningLineage);
        arrayList2.add(signingCertificateNode2);
        return new SigningCertificateLineage(this.mMinSdkVersion, arrayList2);
    }

    public void updateSignerCapabilities(X509Certificate x509Certificate, SignerCapabilities signerCapabilities) {
        if (x509Certificate == null) {
            x0e.a("config == null");
            return;
        }
        for (int i = 0; i < this.mSigningLineage.size(); i++) {
            V3SigningCertificateLineage.SigningCertificateNode signingCertificateNode = this.mSigningLineage.get(i);
            if (signingCertificateNode.signingCert.equals(x509Certificate)) {
                signingCertificateNode.flags = new SignerCapabilities.Builder(signingCertificateNode.flags).setCallerConfiguredCapabilities(signerCapabilities).build().getFlags();
                return;
            }
        }
        yba.a("Certificate (", x509Certificate.getSubjectDN(), ") not found in the SigningCertificateLineage");
    }

    public void writeToDataSink(DataSink dataSink) throws IOException {
        if (dataSink != null) {
            dataSink.consume(write());
        } else {
            x0e.a("dataSink == null");
        }
    }

    public void writeToFile(File file) throws IOException {
        if (file != null) {
            writeToDataSink(new RandomAccessFileDataSink(new RandomAccessFile(file, "rw")));
        } else {
            x0e.a("file == null");
        }
    }

    public static class SignerCapabilities {
        private final int mCallerConfiguredFlags;
        private final int mFlags;

        private SignerCapabilities(int i, int i2) {
            this.mFlags = i;
            this.mCallerConfiguredFlags = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getFlags() {
            return this.mFlags;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SignerCapabilities) && this.mFlags == ((SignerCapabilities) obj).mFlags;
        }

        public boolean hasAuth() {
            return (this.mFlags & 16) != 0;
        }

        public boolean hasInstalledData() {
            return (this.mFlags & 1) != 0;
        }

        public boolean hasPermission() {
            return (this.mFlags & 4) != 0;
        }

        public boolean hasRollback() {
            return (this.mFlags & 8) != 0;
        }

        public boolean hasSharedUid() {
            return (this.mFlags & 2) != 0;
        }

        public int hashCode() {
            return this.mFlags * 31;
        }

        private SignerCapabilities(int i) {
            this(i, 0);
        }

        public static class Builder {
            private int mCallerConfiguredFlags;
            private int mFlags;

            public Builder() {
                this.mFlags = SigningCertificateLineage.calculateDefaultFlags();
            }

            public SignerCapabilities build() {
                return new SignerCapabilities(this.mFlags, this.mCallerConfiguredFlags);
            }

            public Builder setAuth(boolean z) {
                this.mCallerConfiguredFlags |= 16;
                int i = this.mFlags;
                if (z) {
                    this.mFlags = i | 16;
                    return this;
                }
                this.mFlags = i & (-17);
                return this;
            }

            public Builder setCallerConfiguredCapabilities(SignerCapabilities signerCapabilities) {
                this.mFlags = (signerCapabilities.mCallerConfiguredFlags & signerCapabilities.mFlags) | (this.mFlags & (~signerCapabilities.mCallerConfiguredFlags));
                return this;
            }

            public Builder setInstalledData(boolean z) {
                this.mCallerConfiguredFlags |= 1;
                int i = this.mFlags;
                if (z) {
                    this.mFlags = i | 1;
                    return this;
                }
                this.mFlags = i & (-2);
                return this;
            }

            public Builder setPermission(boolean z) {
                this.mCallerConfiguredFlags |= 4;
                int i = this.mFlags;
                if (z) {
                    this.mFlags = i | 4;
                    return this;
                }
                this.mFlags = i & (-5);
                return this;
            }

            public Builder setRollback(boolean z) {
                this.mCallerConfiguredFlags |= 8;
                int i = this.mFlags;
                if (z) {
                    this.mFlags = i | 8;
                    return this;
                }
                this.mFlags = i & (-9);
                return this;
            }

            public Builder setSharedUid(boolean z) {
                this.mCallerConfiguredFlags |= 2;
                int i = this.mFlags;
                if (z) {
                    this.mFlags = i | 2;
                    return this;
                }
                this.mFlags = i & (-3);
                return this;
            }

            public Builder(int i) {
                this.mFlags = i;
            }
        }
    }

    public static class SignerConfig {
        private final X509Certificate mCertificate;
        private final KeyConfig mKeyConfig;

        private SignerConfig(KeyConfig keyConfig, X509Certificate x509Certificate) {
            this.mKeyConfig = keyConfig;
            this.mCertificate = x509Certificate;
        }

        public static /* synthetic */ PrivateKey b(KeyConfig.Kms kms) {
            return null;
        }

        public X509Certificate getCertificate() {
            return this.mCertificate;
        }

        public KeyConfig getKeyConfig() {
            return this.mKeyConfig;
        }

        @Deprecated
        public PrivateKey getPrivateKey() {
            return (PrivateKey) this.mKeyConfig.match(new Function() { // from class: rbd
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((KeyConfig.Jca) obj).privateKey;
                }
            }, new Function() { // from class: sbd
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SigningCertificateLineage.SignerConfig.b((KeyConfig.Kms) obj);
                }
            });
        }

        public static class Builder {
            private final X509Certificate mCertificate;
            private final KeyConfig mKeyConfig;

            @Deprecated
            public Builder(PrivateKey privateKey, X509Certificate x509Certificate) {
                this.mKeyConfig = new KeyConfig.Jca(privateKey);
                this.mCertificate = x509Certificate;
            }

            public SignerConfig build() {
                return new SignerConfig(this.mKeyConfig, this.mCertificate);
            }

            public Builder(KeyConfig keyConfig, X509Certificate x509Certificate) {
                this.mKeyConfig = keyConfig;
                this.mCertificate = x509Certificate;
            }
        }
    }

    public static class Builder {
        private int mMinSdkVersion;
        private SignerCapabilities mNewCapabilities;
        private final SignerConfig mNewSignerConfig;
        private SignerCapabilities mOriginalCapabilities;
        private final SignerConfig mOriginalSignerConfig;

        public Builder(SignerConfig signerConfig, SignerConfig signerConfig2) {
            if (signerConfig == null || signerConfig2 == null) {
                x0e.a("Can't pass null SignerConfigs when constructing a new SigningCertificateLineage");
                throw null;
            }
            this.mOriginalSignerConfig = signerConfig;
            this.mNewSignerConfig = signerConfig2;
        }

        public SigningCertificateLineage build() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateEncodingException {
            if (this.mMinSdkVersion < 28) {
                this.mMinSdkVersion = 28;
            }
            if (this.mOriginalCapabilities == null) {
                this.mOriginalCapabilities = new SignerCapabilities.Builder().build();
            }
            if (this.mNewSignerConfig == null) {
                return SigningCertificateLineage.createSigningLineage(this.mMinSdkVersion, this.mOriginalSignerConfig, this.mOriginalCapabilities);
            }
            if (this.mNewCapabilities == null) {
                this.mNewCapabilities = new SignerCapabilities.Builder().build();
            }
            return SigningCertificateLineage.createSigningLineage(this.mMinSdkVersion, this.mOriginalSignerConfig, this.mOriginalCapabilities, this.mNewSignerConfig, this.mNewCapabilities);
        }

        public Builder setMinSdkVersion(int i) {
            this.mMinSdkVersion = i;
            return this;
        }

        public Builder setNewCapabilities(SignerCapabilities signerCapabilities) {
            if (signerCapabilities != null) {
                this.mNewCapabilities = signerCapabilities;
                return this;
            }
            x0e.a("signerCapabilities == null");
            return null;
        }

        public Builder setOriginalCapabilities(SignerCapabilities signerCapabilities) {
            if (signerCapabilities != null) {
                this.mOriginalCapabilities = signerCapabilities;
                return this;
            }
            x0e.a("signerCapabilities == null");
            return null;
        }

        public Builder(SignerConfig signerConfig) {
            if (signerConfig != null) {
                this.mOriginalSignerConfig = signerConfig;
                this.mNewSignerConfig = null;
            } else {
                x0e.a("Can't pass null SignerConfigs when constructing a new SigningCertificateLineage");
                throw null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SigningCertificateLineage createSigningLineage(int i, SignerConfig signerConfig, SignerCapabilities signerCapabilities) {
        return new SigningCertificateLineage(i, new ArrayList()).spawnFirstDescendant(signerConfig, signerCapabilities);
    }

    private static SigningCertificateLineage read(ByteBuffer byteBuffer, int i) throws IOException {
        if (i == 1) {
            try {
                List<V3SigningCertificateLineage.SigningCertificateNode> signingCertificateLineage = V3SigningCertificateLineage.readSigningCertificateLineage(ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer));
                return new SigningCertificateLineage(calculateMinSdkVersion(signingCertificateLineage), signingCertificateLineage);
            } catch (ApkFormatException e) {
                dk3.a("Unable to read list of signing certificate nodes in SigningCertificateLineage", e);
                return null;
            }
        }
        w01.a("Improper SigningCertificateLineage format: unrecognized version.");
        return null;
    }

    public SignerCapabilities getSignerCapabilities(SignerConfig signerConfig) {
        if (signerConfig != null) {
            return getSignerCapabilities(signerConfig.getCertificate());
        }
        x0e.a("config == null");
        return null;
    }

    public void updateSignerCapabilities(SignerConfig signerConfig, SignerCapabilities signerCapabilities) {
        if (signerConfig != null) {
            updateSignerCapabilities(signerConfig.getCertificate(), signerCapabilities);
        } else {
            x0e.a("config == null");
        }
    }

    public static SigningCertificateLineage readFromApkDataSource(DataSource dataSource) throws IOException, ApkFormatException {
        return readFromApkDataSource(dataSource, true, true);
    }

    public SigningCertificateLineage spawnDescendant(SignerConfig signerConfig, SignerConfig signerConfig2) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateEncodingException {
        if (signerConfig != null && signerConfig2 != null) {
            return spawnDescendant(signerConfig, signerConfig2, new SignerCapabilities.Builder().build());
        }
        x0e.a("can't add new descendant to lineage with null inputs");
        return null;
    }
}
