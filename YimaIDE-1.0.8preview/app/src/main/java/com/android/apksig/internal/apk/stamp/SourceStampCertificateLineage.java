package com.android.apksig.internal.apk.stamp;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.util.DelegatingX509Certificate;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import defpackage.d6f;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
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
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SourceStampCertificateLineage {
    private static final int CURRENT_VERSION = 1;
    private static final int FIRST_VERSION = 1;

    public static class SigningCertificateNode {
        public int flags;
        public final SignatureAlgorithm parentSigAlgorithm;
        public SignatureAlgorithm sigAlgorithm;
        public final byte[] signature;
        public final X509Certificate signingCert;

        public SigningCertificateNode(X509Certificate x509Certificate, SignatureAlgorithm signatureAlgorithm, SignatureAlgorithm signatureAlgorithm2, byte[] bArr, int i) {
            this.signingCert = x509Certificate;
            this.parentSigAlgorithm = signatureAlgorithm;
            this.sigAlgorithm = signatureAlgorithm2;
            this.signature = bArr;
            this.flags = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SigningCertificateNode)) {
                return false;
            }
            SigningCertificateNode signingCertificateNode = (SigningCertificateNode) obj;
            return this.signingCert.equals(signingCertificateNode.signingCert) && this.parentSigAlgorithm == signingCertificateNode.parentSigAlgorithm && this.sigAlgorithm == signingCertificateNode.sigAlgorithm && Arrays.equals(this.signature, signingCertificateNode.signature) && this.flags == signingCertificateNode.flags;
        }

        public int hashCode() {
            X509Certificate x509Certificate = this.signingCert;
            int iHashCode = ((x509Certificate == null ? 0 : x509Certificate.hashCode()) + 31) * 31;
            SignatureAlgorithm signatureAlgorithm = this.parentSigAlgorithm;
            int iHashCode2 = (iHashCode + (signatureAlgorithm == null ? 0 : signatureAlgorithm.hashCode())) * 31;
            SignatureAlgorithm signatureAlgorithm2 = this.sigAlgorithm;
            return ((((iHashCode2 + (signatureAlgorithm2 != null ? signatureAlgorithm2.hashCode() : 0)) * 31) + Arrays.hashCode(this.signature)) * 31) + this.flags;
        }
    }

    public static List<SigningCertificateNode> readSigningCertificateLineage(ByteBuffer byteBuffer) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            return null;
        }
        ApkSigningBlockUtilsLite.checkByteOrderLittleEndian(byteBuffer);
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            int i = 0;
            try {
                try {
                    try {
                        try {
                            if (byteBuffer.getInt() != 1) {
                                throw new IllegalArgumentException("Encoded SigningCertificateLineage has a version different than any of which we are aware");
                            }
                            HashSet hashSet = new HashSet();
                            int i2 = 0;
                            DelegatingX509Certificate delegatingX509Certificate = null;
                            while (byteBuffer.hasRemaining()) {
                                i++;
                                ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
                                ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
                                int i3 = lengthPrefixedSlice.getInt();
                                int i4 = lengthPrefixedSlice.getInt();
                                SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(i2);
                                byte[] lengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice);
                                if (delegatingX509Certificate != null) {
                                    String first = signatureAlgorithmFindById.getJcaSignatureAlgorithmAndParams().getFirst();
                                    AlgorithmParameterSpec second = signatureAlgorithmFindById.getJcaSignatureAlgorithmAndParams().getSecond();
                                    PublicKey publicKey = delegatingX509Certificate.getPublicKey();
                                    Signature signature = Signature.getInstance(first);
                                    signature.initVerify(publicKey);
                                    if (second != null) {
                                        signature.setParameter(second);
                                    }
                                    signature.update(lengthPrefixedSlice2);
                                    if (!signature.verify(lengthPrefixedByteArray)) {
                                        throw new SecurityException("Unable to verify signature of certificate #" + i + " using " + first + " when verifying SourceStampCertificateLineage object");
                                    }
                                }
                                lengthPrefixedSlice2.rewind();
                                byte[] lengthPrefixedByteArray2 = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice2);
                                int i5 = lengthPrefixedSlice2.getInt();
                                if (delegatingX509Certificate != null && i2 != i5) {
                                    throw new SecurityException("Signing algorithm ID mismatch for certificate #" + lengthPrefixedSlice + " when verifying SourceStampCertificateLineage object");
                                }
                                DelegatingX509Certificate guaranteedEncodedFormX509Certificate = new GuaranteedEncodedFormX509Certificate((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(lengthPrefixedByteArray2)), lengthPrefixedByteArray2);
                                if (hashSet.contains(guaranteedEncodedFormX509Certificate)) {
                                    throw new SecurityException("Encountered duplicate entries in SigningCertificateLineage at certificate #" + i + ".  All signing certificates should be unique");
                                }
                                hashSet.add(guaranteedEncodedFormX509Certificate);
                                arrayList.add(new SigningCertificateNode(guaranteedEncodedFormX509Certificate, SignatureAlgorithm.findById(i5), SignatureAlgorithm.findById(i4), lengthPrefixedByteArray, i3));
                                delegatingX509Certificate = guaranteedEncodedFormX509Certificate;
                                i2 = i4;
                            }
                            return arrayList;
                        } catch (ApkFormatException | BufferUnderflowException e) {
                            e = e;
                        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
                            e = e2;
                            d6f.a("Failed to verify signature over signed data for certificate #", i, " when parsing SourceStampCertificateLineage object", e);
                            return null;
                        } catch (CertificateException e3) {
                            e = e3;
                            d6f.a("Failed to decode certificate #", i, " when parsing SourceStampCertificateLineage object", e);
                            return null;
                        }
                    } catch (ApkFormatException | BufferUnderflowException e4) {
                        e = e4;
                    }
                } catch (CertificateException e5) {
                    e = e5;
                }
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e6) {
                e = e6;
            }
            dk3.a("Failed to parse SourceStampCertificateLineage object", e);
            return null;
        } catch (CertificateException e7) {
            mg9.a("Failed to obtain X.509 CertificateFactory", e7);
            return null;
        }
    }
}
