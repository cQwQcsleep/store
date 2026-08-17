package com.android.apksig.internal.apk.v3;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.util.DelegatingX509Certificate;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import com.android.apksig.internal.util.X509CertificateUtils;
import defpackage.d6f;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V3SigningCertificateLineage {
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
            return (Objects.hash(this.signingCert, this.parentSigAlgorithm, this.sigAlgorithm, Integer.valueOf(this.flags)) * 31) + Arrays.hashCode(this.signature);
        }
    }

    public static byte[] encodeSignedData(X509Certificate x509Certificate, int i) {
        try {
            byte[] bArrEncodeAsLengthPrefixedElement = ApkSigningBlockUtils.encodeAsLengthPrefixedElement(x509Certificate.getEncoded());
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrEncodeAsLengthPrefixedElement.length + 4);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.put(bArrEncodeAsLengthPrefixedElement);
            byteBufferAllocate.putInt(i);
            return ApkSigningBlockUtils.encodeAsLengthPrefixedElement(byteBufferAllocate.array());
        } catch (CertificateEncodingException e) {
            g3c.a("Failed to encode V3SigningCertificateLineage certificate", e);
            return null;
        }
    }

    public static byte[] encodeSigningCertificateLineage(List<SigningCertificateNode> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<SigningCertificateNode> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(encodeSigningCertificateNode(it.next()));
        }
        byte[] bArrEncodeAsSequenceOfLengthPrefixedElements = ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(arrayList);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrEncodeAsSequenceOfLengthPrefixedElements.length + 4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(1);
        byteBufferAllocate.put(bArrEncodeAsSequenceOfLengthPrefixedElements);
        return byteBufferAllocate.array();
    }

    public static byte[] encodeSigningCertificateNode(SigningCertificateNode signingCertificateNode) {
        SignatureAlgorithm signatureAlgorithm = signingCertificateNode.parentSigAlgorithm;
        int id = signatureAlgorithm != null ? signatureAlgorithm.getId() : 0;
        SignatureAlgorithm signatureAlgorithm2 = signingCertificateNode.sigAlgorithm;
        int id2 = signatureAlgorithm2 != null ? signatureAlgorithm2.getId() : 0;
        byte[] bArrEncodeSignedData = encodeSignedData(signingCertificateNode.signingCert, id);
        byte[] bArrEncodeAsLengthPrefixedElement = ApkSigningBlockUtils.encodeAsLengthPrefixedElement(signingCertificateNode.signature);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrEncodeSignedData.length + 8 + bArrEncodeAsLengthPrefixedElement.length);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(bArrEncodeSignedData);
        byteBufferAllocate.putInt(signingCertificateNode.flags);
        byteBufferAllocate.putInt(id2);
        byteBufferAllocate.put(bArrEncodeAsLengthPrefixedElement);
        return byteBufferAllocate.array();
    }

    public static List<SigningCertificateNode> readSigningCertificateLineage(ByteBuffer byteBuffer) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            return null;
        }
        ApkSigningBlockUtils.checkByteOrderLittleEndian(byteBuffer);
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
                            ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
                            ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
                            int i3 = lengthPrefixedSlice.getInt();
                            int i4 = lengthPrefixedSlice.getInt();
                            SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(i2);
                            byte[] lengthPrefixedByteArray = ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice);
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
                                    throw new SecurityException("Unable to verify signature of certificate #" + i + " using " + first + " when verifying V3SigningCertificateLineage object");
                                }
                            }
                            lengthPrefixedSlice2.rewind();
                            byte[] lengthPrefixedByteArray2 = ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice2);
                            int i5 = lengthPrefixedSlice2.getInt();
                            if (delegatingX509Certificate != null && i2 != i5) {
                                throw new SecurityException("Signing algorithm ID mismatch for certificate #" + lengthPrefixedSlice + " when verifying V3SigningCertificateLineage object");
                            }
                            DelegatingX509Certificate guaranteedEncodedFormX509Certificate = new GuaranteedEncodedFormX509Certificate(X509CertificateUtils.generateCertificate(lengthPrefixedByteArray2), lengthPrefixedByteArray2);
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
                        dk3.a("Failed to parse V3SigningCertificateLineage object", e);
                        return null;
                    } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
                        e = e2;
                    } catch (CertificateException e3) {
                        e = e3;
                        d6f.a("Failed to decode certificate #", i, " when parsing V3SigningCertificateLineage object", e);
                        return null;
                    }
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e4) {
                    e = e4;
                }
            } catch (ApkFormatException | BufferUnderflowException e5) {
                e = e5;
            }
        } catch (CertificateException e6) {
            e = e6;
        }
        d6f.a("Failed to verify signature over signed data for certificate #", i, " when parsing V3SigningCertificateLineage object", e);
        return null;
    }
}
