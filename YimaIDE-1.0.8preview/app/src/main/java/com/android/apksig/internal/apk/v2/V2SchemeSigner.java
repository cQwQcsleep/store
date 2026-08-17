package com.android.apksig.internal.apk.v2;

import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.RunnablesExecutor;
import defpackage.s4f;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class V2SchemeSigner {
    public static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;

    public static final class V2SignatureSchemeBlock {

        public static final class SignedData {
            public byte[] additionalAttributes;
            public List<byte[]> certificates;
            public List<Pair<Integer, byte[]>> digests;

            private SignedData() {
            }
        }

        public static final class Signer {
            public byte[] publicKey;
            public List<Pair<Integer, byte[]>> signatures;
            public byte[] signedData;

            private Signer() {
            }
        }

        private V2SignatureSchemeBlock() {
        }
    }

    private V2SchemeSigner() {
    }

    private static byte[] generateAdditionalAttributes(boolean z) {
        if (!z) {
            return new byte[0];
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(12);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(8);
        byteBufferAllocate.putInt(V2SchemeConstants.STRIPPING_PROTECTION_ATTR_ID);
        byteBufferAllocate.putInt(3);
        return byteBufferAllocate.array();
    }

    private static Pair<byte[], Integer> generateApkSignatureSchemeV2Block(List<ApkSigningBlockUtils.SignerConfig> list, Map<ContentDigestAlgorithm, byte[]> map, boolean z, List<byte[]> list2) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        if (list.size() > 10) {
            prd.a("APK Signature Scheme v2 only supports a maximum of 10, ", list.size(), " provided");
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        if (list2 != null && list2.size() > 0) {
            arrayList.addAll(list2);
        }
        Iterator<ApkSigningBlockUtils.SignerConfig> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            try {
                arrayList.add(generateSignerBlock(it.next(), map, z));
            } catch (InvalidKeyException e) {
                throw new InvalidKeyException("Signer #" + i + " failed", e);
            } catch (SignatureException e2) {
                throw new SignatureException("Signer #" + i + " failed", e2);
            }
        }
        return Pair.of(ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(new byte[][]{ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(arrayList)}), 1896449818);
    }

    private static byte[] generateSignerBlock(ApkSigningBlockUtils.SignerConfig signerConfig, Map<ContentDigestAlgorithm, byte[]> map, boolean z) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (signerConfig.certificates.isEmpty()) {
            d54.a("No certificates configured for signer");
            return null;
        }
        byte[] bArrEncodePublicKey = ApkSigningBlockUtils.encodePublicKey(signerConfig.certificates.get(0).getPublicKey());
        V2SignatureSchemeBlock.SignedData signedData = new V2SignatureSchemeBlock.SignedData();
        try {
            signedData.certificates = ApkSigningBlockUtils.encodeCertificates(signerConfig.certificates);
            ArrayList arrayList = new ArrayList(signerConfig.signatureAlgorithms.size());
            for (SignatureAlgorithm signatureAlgorithm : signerConfig.signatureAlgorithms) {
                ContentDigestAlgorithm contentDigestAlgorithm = signatureAlgorithm.getContentDigestAlgorithm();
                byte[] bArr = map.get(contentDigestAlgorithm);
                if (bArr == null) {
                    throw new RuntimeException(contentDigestAlgorithm + " content digest for " + signatureAlgorithm + " not computed");
                }
                arrayList.add(Pair.of(Integer.valueOf(signatureAlgorithm.getId()), bArr));
            }
            signedData.digests = arrayList;
            signedData.additionalAttributes = generateAdditionalAttributes(z);
            V2SignatureSchemeBlock.Signer signer = new V2SignatureSchemeBlock.Signer();
            signer.signedData = ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(new byte[][]{ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(signedData.digests), ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(signedData.certificates), signedData.additionalAttributes, new byte[0]});
            signer.publicKey = bArrEncodePublicKey;
            signer.signatures = new ArrayList();
            List<Pair<Integer, byte[]>> listGenerateSignaturesOverData = ApkSigningBlockUtils.generateSignaturesOverData(signerConfig, signer.signedData);
            signer.signatures = listGenerateSignaturesOverData;
            return ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(new byte[][]{signer.signedData, ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(listGenerateSignaturesOverData), signer.publicKey});
        } catch (CertificateEncodingException e) {
            throw new SignatureException("Failed to encode certificates", e);
        }
    }

    public static List<SignatureAlgorithm> getSuggestedSignatureAlgorithms(PublicKey publicKey, int i, boolean z, boolean z2) throws InvalidKeyException {
        String algorithm = publicKey.getAlgorithm();
        if ("RSA".equalsIgnoreCase(algorithm)) {
            if (((RSAKey) publicKey).getModulus().bitLength() > 3072) {
                return Collections.singletonList(SignatureAlgorithm.RSA_PKCS1_V1_5_WITH_SHA512);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(SignatureAlgorithm.RSA_PKCS1_V1_5_WITH_SHA256);
            if (z) {
                arrayList.add(SignatureAlgorithm.VERITY_RSA_PKCS1_V1_5_WITH_SHA256);
            }
            return arrayList;
        }
        if ("DSA".equalsIgnoreCase(algorithm)) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(z2 ? SignatureAlgorithm.DETDSA_WITH_SHA256 : SignatureAlgorithm.DSA_WITH_SHA256);
            if (z) {
                arrayList2.add(SignatureAlgorithm.VERITY_DSA_WITH_SHA256);
            }
            return arrayList2;
        }
        if (!"EC".equalsIgnoreCase(algorithm)) {
            s4f.a("Unsupported key algorithm: ", algorithm);
            return null;
        }
        if (((ECKey) publicKey).getParams().getOrder().bitLength() > 256) {
            return Collections.singletonList(SignatureAlgorithm.ECDSA_WITH_SHA512);
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(SignatureAlgorithm.ECDSA_WITH_SHA256);
        if (z) {
            arrayList3.add(SignatureAlgorithm.VERITY_ECDSA_WITH_SHA256);
        }
        return arrayList3;
    }

    public static ApkSigningBlockUtils.SigningSchemeBlockAndDigests generateApkSignatureSchemeV2Block(RunnablesExecutor runnablesExecutor, DataSource dataSource, DataSource dataSource2, DataSource dataSource3, List<ApkSigningBlockUtils.SignerConfig> list, boolean z, List<byte[]> list2) throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        Pair<List<ApkSigningBlockUtils.SignerConfig>, Map<ContentDigestAlgorithm, byte[]>> pairComputeContentDigests = ApkSigningBlockUtils.computeContentDigests(runnablesExecutor, dataSource, dataSource2, dataSource3, list);
        return new ApkSigningBlockUtils.SigningSchemeBlockAndDigests(generateApkSignatureSchemeV2Block(pairComputeContentDigests.getFirst(), pairComputeContentDigests.getSecond(), z, list2), pairComputeContentDigests.getSecond());
    }

    public static ApkSigningBlockUtils.SigningSchemeBlockAndDigests generateApkSignatureSchemeV2Block(RunnablesExecutor runnablesExecutor, DataSource dataSource, DataSource dataSource2, DataSource dataSource3, List<ApkSigningBlockUtils.SignerConfig> list, boolean z) throws NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException {
        return generateApkSignatureSchemeV2Block(runnablesExecutor, dataSource, dataSource2, dataSource3, list, z, null);
    }
}
