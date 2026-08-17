package com.android.apksig.internal.apk.stamp;

import com.android.apksig.SigningCertificateLineage;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.util.Pair;
import defpackage.t4f;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V2SourceStampSigner {
    public static final int V2_SOURCE_STAMP_BLOCK_ID = 1845461005;
    private final Map<Integer, Map<ContentDigestAlgorithm, byte[]>> mSignatureSchemeDigestInfos;
    private final ApkSigningBlockUtils.SignerConfig mSourceStampSignerConfig;
    private final boolean mSourceStampTimestampEnabled;

    public static class Builder {
        private final Map<Integer, Map<ContentDigestAlgorithm, byte[]>> mSignatureSchemeDigestInfos;
        private final ApkSigningBlockUtils.SignerConfig mSourceStampSignerConfig;
        private boolean mSourceStampTimestampEnabled = true;

        public Builder(ApkSigningBlockUtils.SignerConfig signerConfig, Map<Integer, Map<ContentDigestAlgorithm, byte[]>> map) {
            this.mSourceStampSignerConfig = signerConfig;
            this.mSignatureSchemeDigestInfos = map;
        }

        public V2SourceStampSigner build() {
            return new V2SourceStampSigner(this);
        }

        public Builder setSourceStampTimestampEnabled(boolean z) {
            this.mSourceStampTimestampEnabled = z;
            return this;
        }
    }

    public static final class SourceStampBlock {
        public List<Pair<Integer, byte[]>> signedDigests;
        public List<Pair<Integer, byte[]>> signedStampAttributes;
        public byte[] stampAttributes;
        public byte[] stampCertificate;

        private SourceStampBlock() {
        }
    }

    private V2SourceStampSigner(Builder builder) {
        this.mSourceStampSignerConfig = builder.mSourceStampSignerConfig;
        this.mSignatureSchemeDigestInfos = builder.mSignatureSchemeDigestInfos;
        this.mSourceStampTimestampEnabled = builder.mSourceStampTimestampEnabled;
    }

    private static byte[] encodeStampAttributes(Map<Integer, byte[]> map) {
        Iterator<byte[]> it = map.values().iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().length + 8;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length + 4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(length);
        for (Map.Entry<Integer, byte[]> entry : map.entrySet()) {
            byteBufferAllocate.putInt(entry.getValue().length + 4);
            byteBufferAllocate.putInt(entry.getKey().intValue());
            byteBufferAllocate.put(entry.getValue());
        }
        return byteBufferAllocate.array();
    }

    private Map<Integer, byte[]> generateStampAttributes(SigningCertificateLineage signingCertificateLineage) {
        HashMap map = new HashMap();
        if (this.mSourceStampTimestampEnabled) {
            long epochSecond = Instant.now().getEpochSecond();
            if (epochSecond <= 0) {
                throw new IllegalStateException("Received an invalid value from Instant#getTimestamp: " + epochSecond);
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.putLong(epochSecond);
            map.put(Integer.valueOf(SourceStampConstants.STAMP_TIME_ATTR_ID), byteBufferAllocate.array());
        }
        if (signingCertificateLineage != null) {
            map.put(Integer.valueOf(SourceStampConstants.PROOF_OF_ROTATION_ATTR_ID), signingCertificateLineage.encodeSigningCertificateLineage());
        }
        return map;
    }

    private static void getSignedDigestsFor(int i, Map<Integer, Map<ContentDigestAlgorithm, byte[]>> map, ApkSigningBlockUtils.SignerConfig signerConfig, List<Pair<Integer, byte[]>> list) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (map.containsKey(Integer.valueOf(i))) {
            Map<ContentDigestAlgorithm, byte[]> map2 = map.get(Integer.valueOf(i));
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<ContentDigestAlgorithm, byte[]> entry : map2.entrySet()) {
                arrayList.add(Pair.of(Integer.valueOf(entry.getKey().getId()), entry.getValue()));
            }
            Collections.sort(arrayList, Comparator.comparing(new t4f()));
            list.add(Pair.of(Integer.valueOf(i), ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(ApkSigningBlockUtils.generateSignaturesOverData(signerConfig, ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(arrayList)))));
        }
    }

    public Pair<byte[], Integer> generateSourceStampBlock() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (this.mSourceStampSignerConfig.certificates.isEmpty()) {
            d54.a("No certificates configured for signer");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        getSignedDigestsFor(3, this.mSignatureSchemeDigestInfos, this.mSourceStampSignerConfig, arrayList);
        getSignedDigestsFor(2, this.mSignatureSchemeDigestInfos, this.mSourceStampSignerConfig, arrayList);
        getSignedDigestsFor(1, this.mSignatureSchemeDigestInfos, this.mSourceStampSignerConfig, arrayList);
        Collections.sort(arrayList, Comparator.comparing(new t4f()));
        SourceStampBlock sourceStampBlock = new SourceStampBlock();
        try {
            sourceStampBlock.stampCertificate = this.mSourceStampSignerConfig.certificates.get(0).getEncoded();
            sourceStampBlock.signedDigests = arrayList;
            byte[] bArrEncodeStampAttributes = encodeStampAttributes(generateStampAttributes(this.mSourceStampSignerConfig.signingCertificateLineage));
            sourceStampBlock.stampAttributes = bArrEncodeStampAttributes;
            sourceStampBlock.signedStampAttributes = ApkSigningBlockUtils.generateSignaturesOverData(this.mSourceStampSignerConfig, bArrEncodeStampAttributes);
            return Pair.of(ApkSigningBlockUtils.encodeAsLengthPrefixedElement(ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedElements(new byte[][]{sourceStampBlock.stampCertificate, ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(sourceStampBlock.signedDigests), sourceStampBlock.stampAttributes, ApkSigningBlockUtils.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(sourceStampBlock.signedStampAttributes)})), 1845461005);
        } catch (CertificateEncodingException e) {
            throw new SignatureException("Retrieving the encoded form of the stamp certificate failed", e);
        }
    }

    public static Pair<byte[], Integer> generateSourceStampBlock(ApkSigningBlockUtils.SignerConfig signerConfig, Map<Integer, Map<ContentDigestAlgorithm, byte[]>> map) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        return new Builder(signerConfig, map).build().generateSourceStampBlock();
    }
}
