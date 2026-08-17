package com.android.apksig.internal.apk.stamp;

import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.util.Pair;
import defpackage.t4f;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class V1SourceStampSigner {
    public static final int V1_SOURCE_STAMP_BLOCK_ID = 722016414;

    public static final class SourceStampBlock {
        public List<Pair<Integer, byte[]>> signedDigests;
        public byte[] stampCertificate;

        private SourceStampBlock() {
        }
    }

    private V1SourceStampSigner() {
    }

    public static Pair<byte[], Integer> generateSourceStampBlock(ApkSigningBlockUtils.SignerConfig signerConfig, Map<ContentDigestAlgorithm, byte[]> map) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (signerConfig.certificates.isEmpty()) {
            d54.a("No certificates configured for signer");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<ContentDigestAlgorithm, byte[]> entry : map.entrySet()) {
            arrayList.add(Pair.of(Integer.valueOf(entry.getKey().getId()), entry.getValue()));
        }
        Collections.sort(arrayList, Comparator.comparing(new t4f()));
        SourceStampBlock sourceStampBlock = new SourceStampBlock();
        try {
            sourceStampBlock.stampCertificate = signerConfig.certificates.get(0).getEncoded();
            List<Pair<Integer, byte[]>> listGenerateSignaturesOverData = ApkSigningBlockUtils.generateSignaturesOverData(signerConfig, ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(arrayList));
            sourceStampBlock.signedDigests = listGenerateSignaturesOverData;
            return Pair.of(ApkSigningBlockUtils.encodeAsLengthPrefixedElement(ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(new byte[][]{sourceStampBlock.stampCertificate, ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(listGenerateSignaturesOverData)})), 722016414);
        } catch (CertificateEncodingException e) {
            throw new SignatureException("Retrieving the encoded form of the stamp certificate failed", e);
        }
    }
}
