package com.android.apksig;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtilsLite;
import com.android.apksig.internal.apk.ApkSigResult;
import com.android.apksig.internal.apk.ApkSignerInfo;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.SignatureInfo;
import com.android.apksig.internal.apk.SignatureNotFoundException;
import com.android.apksig.internal.apk.stamp.V2SourceStampVerifier;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import com.android.apksig.internal.zip.CentralDirectoryRecord;
import com.android.apksig.internal.zip.LocalFileRecord;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import com.android.apksig.zip.ZipFormatException;
import com.android.apksig.zip.ZipSections;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SourceStampVerifier {
    private final DataSource mApkDataSource;
    private final File mApkFile;
    private final int mMaxSdkVersion;
    private final int mMinSdkVersion;

    public static class Result {
        private final List<List<SignerInfo>> mAllSchemeSigners;
        private final List<ApkVerificationIssue> mErrors;
        private SourceStampInfo mSourceStampInfo;
        private final List<SignerInfo> mV1SchemeSigners;
        private final List<SignerInfo> mV2SchemeSigners;
        private final List<SignerInfo> mV31SchemeSigners;
        private final List<SignerInfo> mV3SchemeSigners;
        private boolean mVerified;
        private final List<ApkVerificationIssue> mWarnings;

        public static class SignerInfo {
            public static final int INVALID_SDK_VERSION = -1;
            private X509Certificate mSigningCertificate;
            private final List<ApkVerificationIssue> mErrors = new ArrayList();
            private final List<ApkVerificationIssue> mWarnings = new ArrayList();
            private int mMinSdkVersion = -1;
            private int mMaxSdkVersion = -1;

            public void addVerificationError(int i, Object... objArr) {
                this.mErrors.add(new ApkVerificationIssue(i, objArr));
            }

            public void addVerificationWarning(int i, Object... objArr) {
                this.mWarnings.add(new ApkVerificationIssue(i, objArr));
            }

            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public List<ApkVerificationIssue> getErrors() {
                return this.mErrors;
            }

            public int getMaxSdkVersion() {
                return this.mMaxSdkVersion;
            }

            public int getMinSdkVersion() {
                return this.mMinSdkVersion;
            }

            public X509Certificate getSigningCertificate() {
                return this.mSigningCertificate;
            }

            public List<ApkVerificationIssue> getWarnings() {
                return this.mWarnings;
            }

            public void setMaxSdkVersion(int i) {
                this.mMaxSdkVersion = i;
            }

            public void setMinSdkVersion(int i) {
                this.mMinSdkVersion = i;
            }

            public void setSigningCertificate(X509Certificate x509Certificate) {
                this.mSigningCertificate = x509Certificate;
            }
        }

        public static class SourceStampInfo {
            private static final boolean mWarningsAsErrors = true;
            private final List<X509Certificate> mCertificateLineage;
            private final List<X509Certificate> mCertificates;
            private final List<ApkVerificationIssue> mErrors;
            private final List<ApkVerificationIssue> mInfoMessages;
            private final long mTimestamp;
            private final List<ApkVerificationIssue> mWarnings;

            private SourceStampInfo(ApkSignerInfo apkSignerInfo) {
                ArrayList arrayList = new ArrayList();
                this.mErrors = arrayList;
                ArrayList arrayList2 = new ArrayList();
                this.mWarnings = arrayList2;
                ArrayList arrayList3 = new ArrayList();
                this.mInfoMessages = arrayList3;
                this.mCertificates = apkSignerInfo.certs;
                this.mCertificateLineage = apkSignerInfo.certificateLineage;
                arrayList.addAll(apkSignerInfo.getErrors());
                arrayList2.addAll(apkSignerInfo.getWarnings());
                arrayList3.addAll(apkSignerInfo.getInfoMessages());
                this.mTimestamp = apkSignerInfo.timestamp;
            }

            public boolean containsErrors() {
                return (this.mErrors.isEmpty() && this.mWarnings.isEmpty()) ? false : true;
            }

            public boolean containsInfoMessages() {
                return !this.mInfoMessages.isEmpty();
            }

            public X509Certificate getCertificate() {
                if (this.mCertificates.isEmpty()) {
                    return null;
                }
                return this.mCertificates.get(0);
            }

            public List<X509Certificate> getCertificatesInLineage() {
                return this.mCertificateLineage;
            }

            public List<ApkVerificationIssue> getErrors() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.mErrors);
                arrayList.addAll(this.mWarnings);
                return arrayList;
            }

            public List<ApkVerificationIssue> getInfoMessages() {
                return this.mInfoMessages;
            }

            public long getTimestampEpochSeconds() {
                return this.mTimestamp;
            }

            public List<ApkVerificationIssue> getWarnings() {
                return this.mWarnings;
            }
        }

        public Result() {
            ArrayList arrayList = new ArrayList();
            this.mV1SchemeSigners = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.mV2SchemeSigners = arrayList2;
            ArrayList arrayList3 = new ArrayList();
            this.mV3SchemeSigners = arrayList3;
            ArrayList arrayList4 = new ArrayList();
            this.mV31SchemeSigners = arrayList4;
            this.mAllSchemeSigners = Arrays.asList(arrayList, arrayList2, arrayList3, arrayList4);
            this.mErrors = new ArrayList();
            this.mWarnings = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV1Signer(SignerInfo signerInfo) {
            this.mV1SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Signer(SignerInfo signerInfo) {
            this.mV2SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV31Signer(SignerInfo signerInfo) {
            this.mV31SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV3Signer(SignerInfo signerInfo) {
            this.mV3SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFrom(ApkSigResult apkSigResult) {
            if (apkSigResult.signatureSchemeVersion != 0) {
                jt6.a("Unknown ApkSigResult Signing Block Scheme Id ", apkSigResult.signatureSchemeVersion);
                return;
            }
            this.mVerified = apkSigResult.verified;
            if (apkSigResult.mSigners.isEmpty()) {
                return;
            }
            this.mSourceStampInfo = new SourceStampInfo(apkSigResult.mSigners.get(0));
        }

        public void addVerificationError(int i, Object... objArr) {
            this.mErrors.add(new ApkVerificationIssue(i, objArr));
        }

        public void addVerificationWarning(int i, Object... objArr) {
            this.mWarnings.add(new ApkVerificationIssue(i, objArr));
        }

        public boolean containsErrors() {
            if (!this.mErrors.isEmpty()) {
                return true;
            }
            Iterator<List<SignerInfo>> it = this.mAllSchemeSigners.iterator();
            while (it.hasNext()) {
                Iterator<SignerInfo> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    if (it2.next().containsErrors()) {
                        return true;
                    }
                }
            }
            SourceStampInfo sourceStampInfo = this.mSourceStampInfo;
            return sourceStampInfo != null && sourceStampInfo.containsErrors();
        }

        public List<ApkVerificationIssue> getAllErrors() {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mErrors);
            Iterator<List<SignerInfo>> it = this.mAllSchemeSigners.iterator();
            while (it.hasNext()) {
                Iterator<SignerInfo> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    arrayList.addAll(it2.next().getErrors());
                }
            }
            SourceStampInfo sourceStampInfo = this.mSourceStampInfo;
            if (sourceStampInfo != null) {
                arrayList.addAll(sourceStampInfo.getErrors());
            }
            return arrayList;
        }

        public List<ApkVerificationIssue> getAllWarnings() {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mWarnings);
            Iterator<List<SignerInfo>> it = this.mAllSchemeSigners.iterator();
            while (it.hasNext()) {
                Iterator<SignerInfo> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    arrayList.addAll(it2.next().getWarnings());
                }
            }
            SourceStampInfo sourceStampInfo = this.mSourceStampInfo;
            if (sourceStampInfo != null) {
                arrayList.addAll(sourceStampInfo.getWarnings());
            }
            return arrayList;
        }

        public List<ApkVerificationIssue> getErrors() {
            return this.mErrors;
        }

        public SourceStampInfo getSourceStampInfo() {
            return this.mSourceStampInfo;
        }

        public List<SignerInfo> getV1SchemeSigners() {
            return this.mV1SchemeSigners;
        }

        public List<SignerInfo> getV2SchemeSigners() {
            return this.mV2SchemeSigners;
        }

        public List<SignerInfo> getV31SchemeSigners() {
            return this.mV31SchemeSigners;
        }

        public List<SignerInfo> getV3SchemeSigners() {
            return this.mV3SchemeSigners;
        }

        public List<ApkVerificationIssue> getWarnings() {
            return this.mWarnings;
        }

        public boolean isVerified() {
            return this.mVerified;
        }
    }

    private SourceStampVerifier(File file, DataSource dataSource, int i, int i2) {
        this.mApkFile = file;
        this.mApkDataSource = dataSource;
        this.mMinSdkVersion = i;
        this.mMaxSdkVersion = i2;
    }

    private static Map<ContentDigestAlgorithm, byte[]> getApkContentDigestFromV1SigningScheme(List<CentralDirectoryRecord> list, DataSource dataSource, ZipSections zipSections, Result result) throws IOException, ApkFormatException {
        ArrayList<CentralDirectoryRecord> arrayList = new ArrayList(1);
        EnumMap enumMap = new EnumMap(ContentDigestAlgorithm.class);
        CentralDirectoryRecord centralDirectoryRecord = null;
        for (CentralDirectoryRecord centralDirectoryRecord2 : list) {
            String name = centralDirectoryRecord2.getName();
            if (name != null) {
                if (centralDirectoryRecord == null && "META-INF/MANIFEST.MF".equals(name)) {
                    centralDirectoryRecord = centralDirectoryRecord2;
                } else if (name.startsWith("META-INF/") && (name.endsWith(".RSA") || name.endsWith(".DSA") || name.endsWith(".EC"))) {
                    arrayList.add(centralDirectoryRecord2);
                }
            }
        }
        if (centralDirectoryRecord != null) {
            if (arrayList.isEmpty()) {
                result.addVerificationWarning(36, new Object[0]);
            } else {
                for (CentralDirectoryRecord centralDirectoryRecord3 : arrayList) {
                    try {
                        for (Certificate certificate : CertificateFactory.getInstance("X.509").generateCertificates(new ByteArrayInputStream(LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecord3, zipSections.getZipCentralDirectoryOffset())))) {
                            if (certificate instanceof X509Certificate) {
                                Result.SignerInfo signerInfo = new Result.SignerInfo();
                                signerInfo.setSigningCertificate((X509Certificate) certificate);
                                result.addV1Signer(signerInfo);
                                break;
                            }
                        }
                    } catch (ZipFormatException e) {
                        throw new ApkFormatException("Failed to read APK", e);
                    } catch (CertificateException e2) {
                        result.addVerificationWarning(37, centralDirectoryRecord3.getName(), e2);
                    }
                }
            }
            try {
                enumMap.put(ContentDigestAlgorithm.SHA256, ApkUtilsLite.computeSha256DigestBytes(LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecord, zipSections.getZipCentralDirectoryOffset())));
            } catch (ZipFormatException e3) {
                throw new ApkFormatException("Failed to read APK", e3);
            }
        }
        return enumMap;
    }

    private void parseSigner(ByteBuffer byteBuffer, int i, CertificateFactory certificateFactory, Map<ContentDigestAlgorithm, byte[]> map, Result.SignerInfo signerInfo) throws ApkFormatException {
        boolean z = i == 2;
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
        ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
        ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
        if (i == 31) {
            int i2 = lengthPrefixedSlice.getInt();
            int i3 = lengthPrefixedSlice.getInt();
            signerInfo.setMinSdkVersion(i2);
            signerInfo.setMaxSdkVersion(i3);
            if (i3 < this.mMinSdkVersion || signerInfo.getMinSdkVersion() > this.mMaxSdkVersion) {
                return;
            }
        }
        while (lengthPrefixedSlice2.hasRemaining()) {
            try {
                ByteBuffer lengthPrefixedSlice4 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice2);
                int i4 = lengthPrefixedSlice4.getInt();
                byte[] lengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice4);
                SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(i4);
                if (signatureAlgorithmFindById != null) {
                    map.put(signatureAlgorithmFindById.getContentDigestAlgorithm(), lengthPrefixedByteArray);
                }
            } catch (ApkFormatException | BufferUnderflowException unused) {
                signerInfo.addVerificationWarning(z ? 8 : 16, new Object[0]);
                return;
            }
        }
        if (lengthPrefixedSlice3.hasRemaining()) {
            byte[] lengthPrefixedByteArray2 = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice3);
            try {
                signerInfo.setSigningCertificate(new GuaranteedEncodedFormX509Certificate((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(lengthPrefixedByteArray2)), lengthPrefixedByteArray2));
            } catch (CertificateException unused2) {
                signerInfo.addVerificationWarning(z ? 6 : 14, new Object[0]);
                return;
            }
        }
        if (signerInfo.getSigningCertificate() == null) {
            signerInfo.addVerificationWarning(z ? 7 : 15, new Object[0]);
        }
    }

    private Result verifySourceStamp(DataSource dataSource, String str) {
        SignatureInfo signatureInfoFindSignature;
        CentralDirectoryRecord next;
        SignatureInfo signatureInfoFindSignature2;
        SignatureInfo signatureInfoFindSignature3;
        Result result = new Result();
        try {
            try {
                try {
                    ZipSections zipSectionsFindZipSections = ApkUtilsLite.findZipSections(dataSource);
                    List<CentralDirectoryRecord> zipCentralDirectory = ZipUtils.parseZipCentralDirectory(dataSource, zipSectionsFindZipSections);
                    Iterator<CentralDirectoryRecord> it = zipCentralDirectory.iterator();
                    do {
                        signatureInfoFindSignature = null;
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!"stamp-cert-sha256".equals(next.getName()));
                    int i = 24;
                    if (next == null) {
                        try {
                            ApkSigningBlockUtilsLite.findSignature(dataSource, zipSectionsFindZipSections, 1845461005);
                        } catch (SignatureNotFoundException unused) {
                            i = 25;
                        }
                        result.addVerificationError(i, new Object[0]);
                    } else {
                        byte[] uncompressedData = LocalFileRecord.getUncompressedData(dataSource, next, zipSectionsFindZipSections.getZipCentralDirectoryOffset());
                        if (str != null) {
                            String hex = ApkSigningBlockUtilsLite.toHex(uncompressedData);
                            if (!str.equalsIgnoreCase(hex)) {
                                result.addVerificationError(23, hex, str);
                                return result;
                            }
                        }
                        HashMap map = new HashMap();
                        if (this.mMaxSdkVersion >= 33) {
                            try {
                                signatureInfoFindSignature3 = ApkSigningBlockUtilsLite.findSignature(dataSource, zipSectionsFindZipSections, 462663009);
                            } catch (SignatureNotFoundException unused2) {
                                signatureInfoFindSignature3 = null;
                            }
                            if (signatureInfoFindSignature3 != null) {
                                EnumMap enumMap = new EnumMap(ContentDigestAlgorithm.class);
                                parseSigners(signatureInfoFindSignature3.signatureBlock, 31, enumMap, result);
                                map.put(31, enumMap);
                            }
                        }
                        if (this.mMaxSdkVersion >= 28) {
                            try {
                                signatureInfoFindSignature2 = ApkSigningBlockUtilsLite.findSignature(dataSource, zipSectionsFindZipSections, -262969152);
                            } catch (SignatureNotFoundException unused3) {
                                signatureInfoFindSignature2 = null;
                            }
                            if (signatureInfoFindSignature2 != null) {
                                EnumMap enumMap2 = new EnumMap(ContentDigestAlgorithm.class);
                                parseSigners(signatureInfoFindSignature2.signatureBlock, 3, enumMap2, result);
                                map.put(3, enumMap2);
                            }
                        }
                        if (this.mMaxSdkVersion >= 24 && (this.mMinSdkVersion < 28 || map.isEmpty())) {
                            try {
                                signatureInfoFindSignature = ApkSigningBlockUtilsLite.findSignature(dataSource, zipSectionsFindZipSections, 1896449818);
                            } catch (SignatureNotFoundException unused4) {
                            }
                            if (signatureInfoFindSignature != null) {
                                EnumMap enumMap3 = new EnumMap(ContentDigestAlgorithm.class);
                                parseSigners(signatureInfoFindSignature.signatureBlock, 2, enumMap3, result);
                                map.put(2, enumMap3);
                            }
                        }
                        if (this.mMinSdkVersion < 24 || map.isEmpty()) {
                            map.put(1, getApkContentDigestFromV1SigningScheme(zipCentralDirectory, dataSource, zipSectionsFindZipSections, result));
                        }
                        result.mergeFrom(V2SourceStampVerifier.verify(dataSource, zipSectionsFindZipSections, uncompressedData, map, this.mMinSdkVersion, this.mMaxSdkVersion));
                    }
                } catch (ApkFormatException | ZipFormatException | IOException e) {
                    result.addVerificationError(28, e);
                }
            } catch (SignatureNotFoundException unused5) {
                result.addVerificationError(30, new Object[0]);
            }
        } catch (NoSuchAlgorithmException e2) {
            result.addVerificationError(29, e2);
        }
        return result;
    }

    public void parseSigners(ByteBuffer byteBuffer, int i, Map<ContentDigestAlgorithm, byte[]> map, Result result) {
        boolean z = i == 2;
        try {
            ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
            if (!lengthPrefixedSlice.hasRemaining()) {
                result.addVerificationWarning(z ? 2 : 10, new Object[0]);
                return;
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                while (lengthPrefixedSlice.hasRemaining()) {
                    Result.SignerInfo signerInfo = new Result.SignerInfo();
                    try {
                        try {
                            parseSigner(ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice), i, certificateFactory, map, signerInfo);
                            if (i == 2) {
                                result.addV2Signer(signerInfo);
                            } else if (i == 3) {
                                result.addV3Signer(signerInfo);
                            } else if (i == 31 && signerInfo.getMaxSdkVersion() >= this.mMinSdkVersion && signerInfo.getMinSdkVersion() <= this.mMaxSdkVersion) {
                                result.addV31Signer(signerInfo);
                            }
                        } catch (ApkFormatException | BufferUnderflowException unused) {
                            signerInfo.addVerificationWarning(z ? 3 : 11, new Object[0]);
                            if (i == 2) {
                                result.addV2Signer(signerInfo);
                                return;
                            }
                            if (i == 3) {
                                result.addV3Signer(signerInfo);
                                return;
                            } else {
                                if (i == 31 && signerInfo.getMaxSdkVersion() >= this.mMinSdkVersion && signerInfo.getMinSdkVersion() <= this.mMaxSdkVersion) {
                                    result.addV31Signer(signerInfo);
                                    return;
                                }
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        if (i == 2) {
                            result.addV2Signer(signerInfo);
                            throw th;
                        }
                        if (i == 3) {
                            result.addV3Signer(signerInfo);
                            throw th;
                        }
                        if (i != 31 || signerInfo.getMaxSdkVersion() < this.mMinSdkVersion || signerInfo.getMinSdkVersion() > this.mMaxSdkVersion) {
                            throw th;
                        }
                        result.addV31Signer(signerInfo);
                        throw th;
                    }
                }
            } catch (CertificateException e) {
                g3c.a("Failed to obtain X.509 CertificateFactory", e);
            }
        } catch (ApkFormatException unused2) {
            result.addVerificationWarning(z ? 1 : 9, new Object[0]);
        }
    }

    public static class Builder {
        private final DataSource mApkDataSource;
        private final File mApkFile;
        private int mMinSdkVersion = 1;
        private int mMaxSdkVersion = Integer.MAX_VALUE;

        public Builder(File file) {
            if (file == null) {
                x0e.a("apk == null");
                throw null;
            }
            this.mApkFile = file;
            this.mApkDataSource = null;
        }

        public SourceStampVerifier build() {
            return new SourceStampVerifier(this.mApkFile, this.mApkDataSource, this.mMinSdkVersion, this.mMaxSdkVersion);
        }

        public Builder setMaxCheckedPlatformVersion(int i) {
            this.mMaxSdkVersion = i;
            return this;
        }

        public Builder setMinCheckedPlatformVersion(int i) {
            this.mMinSdkVersion = i;
            return this;
        }

        public Builder(DataSource dataSource) {
            if (dataSource != null) {
                this.mApkDataSource = dataSource;
                this.mApkFile = null;
            } else {
                x0e.a("apk == null");
                throw null;
            }
        }
    }

    public Result verifySourceStamp(String str) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                DataSource dataSourceAsDataSource = this.mApkDataSource;
                if (dataSourceAsDataSource == null) {
                    if (this.mApkFile != null) {
                        RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.mApkFile, "r");
                        try {
                            dataSourceAsDataSource = DataSources.asDataSource(randomAccessFile2, 0L, randomAccessFile2.length());
                            randomAccessFile = randomAccessFile2;
                        } catch (IOException e) {
                            e = e;
                            randomAccessFile = randomAccessFile2;
                            Result result = new Result();
                            result.addVerificationError(29, e);
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException unused) {
                                }
                            }
                            return result;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = randomAccessFile2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        throw new IllegalStateException("APK not provided");
                    }
                }
                Result resultVerifySourceStamp = verifySourceStamp(dataSourceAsDataSource, str);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused3) {
                    }
                }
                return resultVerifySourceStamp;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public Result verifySourceStamp() {
        return verifySourceStamp(null);
    }
}
