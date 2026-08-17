package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DigestUtil {
    private static Digest createDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake256_len)) {
            return new SHAKEDigest(256);
        }
        aca.a("unrecognized digest OID: ", aSN1ObjectIdentifier);
        return null;
    }

    public static Digest getDigest(LMOtsParameters lMOtsParameters) {
        return createDigest(lMOtsParameters.getDigestOID(), lMOtsParameters.getN());
    }

    public static class WrapperDigest implements Digest {
        private final Digest digest;
        private final int length;

        public WrapperDigest(Digest digest, int i) {
            this.digest = digest;
            this.length = i;
        }

        public int doFinal(byte[] bArr, int i) {
            byte[] bArr2 = new byte[this.digest.getDigestSize()];
            this.digest.doFinal(bArr2, 0);
            System.arraycopy(bArr2, 0, bArr, i, this.length);
            return this.length;
        }

        public String getAlgorithmName() {
            return this.digest.getAlgorithmName() + "/" + (this.length * 8);
        }

        public int getDigestSize() {
            return this.length;
        }

        public void reset() {
            this.digest.reset();
        }

        public void update(byte b) {
            this.digest.update(b);
        }

        public void update(byte[] bArr, int i, int i2) {
            this.digest.update(bArr, i, i2);
        }
    }

    public static Digest getDigest(LMSigParameters lMSigParameters) {
        return createDigest(lMSigParameters.getDigestOID(), lMSigParameters.getM());
    }

    private static Digest createDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        Digest digestCreateDigest = createDigest(aSN1ObjectIdentifier);
        return (NISTObjectIdentifiers.id_shake256_len.equals(aSN1ObjectIdentifier) || digestCreateDigest.getDigestSize() != i) ? new WrapperDigest(digestCreateDigest, i) : digestCreateDigest;
    }
}
