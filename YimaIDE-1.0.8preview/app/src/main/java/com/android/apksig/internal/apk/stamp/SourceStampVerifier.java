package com.android.apksig.internal.apk.stamp;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSignerInfo;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.ApkSupportedSignature;
import com.android.apksig.internal.apk.NoApkSupportedSignaturesException;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.util.ByteBufferUtils;
import com.android.apksig.internal.util.DelegatingX509Certificate;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import java.io.ByteArrayInputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class SourceStampVerifier {
    private SourceStampVerifier() {
    }

    private static void parseStampAttributes(ByteBuffer byteBuffer, X509Certificate x509Certificate, ApkSignerInfo apkSignerInfo) throws ApkFormatException {
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
        int i = 0;
        while (lengthPrefixedSlice.hasRemaining()) {
            i++;
            try {
                ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
                int i2 = lengthPrefixedSlice2.getInt();
                byte[] byteArray = ByteBufferUtils.toByteArray(lengthPrefixedSlice2);
                if (i2 == -1654455305) {
                    readStampCertificateLineage(byteArray, x509Certificate, apkSignerInfo);
                } else if (i2 == -465807034) {
                    long j = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN).getLong();
                    if (j > 0) {
                        apkSignerInfo.timestamp = j;
                    } else {
                        apkSignerInfo.addWarning(38, Long.valueOf(j));
                    }
                } else {
                    apkSignerInfo.addInfoMessage(32, Integer.valueOf(i2));
                }
            } catch (ApkFormatException | BufferUnderflowException unused) {
                apkSignerInfo.addWarning(31, Integer.valueOf(i));
                return;
            }
        }
    }

    private static void readStampCertificateLineage(byte[] bArr, X509Certificate x509Certificate, ApkSignerInfo apkSignerInfo) {
        try {
            List<SourceStampCertificateLineage.SigningCertificateNode> signingCertificateLineage = SourceStampCertificateLineage.readSigningCertificateLineage(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN));
            for (int i = 0; i < signingCertificateLineage.size(); i++) {
                apkSignerInfo.certificateLineage.add(signingCertificateLineage.get(i).signingCert);
            }
            List<X509Certificate> list = apkSignerInfo.certificateLineage;
            if (x509Certificate.equals(list.get(list.size() - 1))) {
                return;
            }
            apkSignerInfo.addWarning(34, new Object[0]);
        } catch (IllegalArgumentException unused) {
            apkSignerInfo.addWarning(34, new Object[0]);
        } catch (SecurityException unused2) {
            apkSignerInfo.addWarning(35, new Object[0]);
        } catch (Exception unused3) {
            apkSignerInfo.addWarning(33, new Object[0]);
        }
    }

    private static X509Certificate verifySourceStampCertificate(ByteBuffer byteBuffer, CertificateFactory certificateFactory, byte[] bArr, ApkSignerInfo apkSignerInfo) throws NoSuchAlgorithmException, ApkFormatException {
        byte[] lengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(byteBuffer);
        try {
            DelegatingX509Certificate guaranteedEncodedFormX509Certificate = new GuaranteedEncodedFormX509Certificate((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(lengthPrefixedByteArray)), lengthPrefixedByteArray);
            apkSignerInfo.certs.add((X509Certificate) guaranteedEncodedFormX509Certificate);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(lengthPrefixedByteArray);
            byte[] bArrDigest = messageDigest.digest();
            if (Arrays.equals(bArr, bArrDigest)) {
                return guaranteedEncodedFormX509Certificate;
            }
            apkSignerInfo.addWarning(27, ApkSigningBlockUtilsLite.toHex(bArrDigest), ApkSigningBlockUtilsLite.toHex(bArr));
            return null;
        } catch (CertificateException e) {
            apkSignerInfo.addWarning(18, e);
            return null;
        }
    }

    private static void verifySourceStampSignature(byte[] bArr, int i, int i2, X509Certificate x509Certificate, ByteBuffer byteBuffer, ApkSignerInfo apkSignerInfo) {
        ArrayList<ApkSupportedSignature> arrayList = new ArrayList(1);
        int i3 = 0;
        while (byteBuffer.hasRemaining()) {
            i3++;
            try {
                ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
                int i4 = lengthPrefixedSlice.getInt();
                byte[] lengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice);
                SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(i4);
                if (signatureAlgorithmFindById == null) {
                    apkSignerInfo.addInfoMessage(19, Integer.valueOf(i4));
                } else {
                    arrayList.add(new ApkSupportedSignature(signatureAlgorithmFindById, lengthPrefixedByteArray));
                }
            } catch (ApkFormatException | BufferUnderflowException unused) {
                apkSignerInfo.addWarning(20, Integer.valueOf(i3));
                return;
            }
        }
        if (arrayList.isEmpty()) {
            apkSignerInfo.addWarning(17, new Object[0]);
            return;
        }
        try {
            for (ApkSupportedSignature apkSupportedSignature : ApkSigningBlockUtilsLite.getSignaturesToVerify(arrayList, i, i2, true)) {
                SignatureAlgorithm signatureAlgorithm = apkSupportedSignature.algorithm;
                String first = signatureAlgorithm.getJcaSignatureAlgorithmAndParams().getFirst();
                AlgorithmParameterSpec second = signatureAlgorithm.getJcaSignatureAlgorithmAndParams().getSecond();
                PublicKey publicKey = x509Certificate.getPublicKey();
                try {
                    Signature signature = Signature.getInstance(first);
                    signature.initVerify(publicKey);
                    if (second != null) {
                        signature.setParameter(second);
                    }
                    signature.update(bArr);
                    if (!signature.verify(apkSupportedSignature.signature)) {
                        apkSignerInfo.addWarning(21, signatureAlgorithm);
                        return;
                    }
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
                    apkSignerInfo.addWarning(22, signatureAlgorithm, e);
                    return;
                }
            }
        } catch (NoApkSupportedSignaturesException e2) {
            StringBuilder sb = new StringBuilder();
            for (ApkSupportedSignature apkSupportedSignature2 : arrayList) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(apkSupportedSignature2.algorithm);
            }
            apkSignerInfo.addWarning(26, sb.toString(), e2);
        }
    }

    public static void verifyV1SourceStamp(ByteBuffer byteBuffer, CertificateFactory certificateFactory, ApkSignerInfo apkSignerInfo, byte[] bArr, byte[] bArr2, int i, int i2) throws NoSuchAlgorithmException, ApkFormatException {
        X509Certificate x509CertificateVerifySourceStampCertificate = verifySourceStampCertificate(byteBuffer, certificateFactory, bArr2, apkSignerInfo);
        if (apkSignerInfo.containsWarnings() || apkSignerInfo.containsErrors()) {
            return;
        }
        verifySourceStampSignature(bArr, i, i2, x509CertificateVerifySourceStampCertificate, ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer), apkSignerInfo);
    }

    public static void verifyV2SourceStamp(ByteBuffer byteBuffer, CertificateFactory certificateFactory, ApkSignerInfo apkSignerInfo, Map<Integer, byte[]> map, byte[] bArr, int i, int i2) throws NoSuchAlgorithmException, ApkFormatException {
        X509Certificate x509CertificateVerifySourceStampCertificate = verifySourceStampCertificate(byteBuffer, certificateFactory, bArr, apkSignerInfo);
        if (apkSignerInfo.containsWarnings() || apkSignerInfo.containsErrors()) {
            return;
        }
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
        HashMap map2 = new HashMap();
        while (lengthPrefixedSlice.hasRemaining()) {
            ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
            int i3 = lengthPrefixedSlice2.getInt();
            map2.put(Integer.valueOf(i3), ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice2));
        }
        for (Map.Entry<Integer, byte[]> entry : map.entrySet()) {
            if (entry.getKey().intValue() == 31) {
                apkSignerInfo.addInfoMessage(39, 31);
            } else {
                if (!map2.containsKey(entry.getKey())) {
                    apkSignerInfo.addWarning(17, new Object[0]);
                    return;
                }
                int i4 = i;
                int i5 = i2;
                ApkSignerInfo apkSignerInfo2 = apkSignerInfo;
                verifySourceStampSignature(entry.getValue(), i4, i5, x509CertificateVerifySourceStampCertificate, (ByteBuffer) map2.get(entry.getKey()), apkSignerInfo2);
                X509Certificate x509Certificate = x509CertificateVerifySourceStampCertificate;
                if (apkSignerInfo2.containsWarnings() || apkSignerInfo2.containsErrors()) {
                    return;
                }
                i2 = i5;
                x509CertificateVerifySourceStampCertificate = x509Certificate;
                apkSignerInfo = apkSignerInfo2;
                i = i4;
            }
        }
        X509Certificate x509Certificate2 = x509CertificateVerifySourceStampCertificate;
        int i6 = i;
        ApkSignerInfo apkSignerInfo3 = apkSignerInfo;
        int i7 = i2;
        if (byteBuffer.hasRemaining()) {
            ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
            ByteBuffer lengthPrefixedSlice4 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
            byte[] bArr2 = new byte[lengthPrefixedSlice3.remaining()];
            lengthPrefixedSlice3.get(bArr2);
            lengthPrefixedSlice3.flip();
            verifySourceStampSignature(bArr2, i6, i7, x509Certificate2, lengthPrefixedSlice4, apkSignerInfo3);
            if (apkSignerInfo3.containsErrors() || apkSignerInfo3.containsWarnings()) {
                return;
            }
            parseStampAttributes(lengthPrefixedSlice3, x509Certificate2, apkSignerInfo3);
        }
    }
}
