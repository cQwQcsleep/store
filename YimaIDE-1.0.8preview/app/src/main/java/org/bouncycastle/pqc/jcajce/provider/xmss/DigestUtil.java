package org.bouncycastle.pqc.jcajce.provider.xmss;

import io.github.rosemoe.sora.widget.CodeEditor;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DigestUtil {
    public static Digest getDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(CodeEditor.FLAG_DRAW_SOFT_WRAP);
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        aca.a("unrecognized digest OID: ", aSN1ObjectIdentifier);
        return null;
    }

    public static ASN1ObjectIdentifier getDigestOID(String str) {
        if (str.equals("SHA-256")) {
            return NISTObjectIdentifiers.id_sha256;
        }
        if (str.equals("SHA-512")) {
            return NISTObjectIdentifiers.id_sha512;
        }
        if (str.equals("SHAKE128")) {
            return NISTObjectIdentifiers.id_shake128;
        }
        if (str.equals("SHAKE256")) {
            return NISTObjectIdentifiers.id_shake256;
        }
        w01.a("unrecognized digest: ".concat(str));
        return null;
    }

    public static byte[] getDigestResult(Digest digest) {
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    public static String getXMSSDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha256)) {
            return "SHA256";
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_sha512)) {
            return "SHA512";
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake128)) {
            return "SHAKE128";
        }
        if (aSN1ObjectIdentifier.equals(NISTObjectIdentifiers.id_shake256)) {
            return "SHAKE256";
        }
        aca.a("unrecognized digest OID: ", aSN1ObjectIdentifier);
        return null;
    }

    public static class DoubleDigest implements Digest {
        private SHAKEDigest digest;

        public DoubleDigest(SHAKEDigest sHAKEDigest) {
            this.digest = sHAKEDigest;
        }

        public int doFinal(byte[] bArr, int i) {
            return this.digest.doFinal(bArr, i, getDigestSize());
        }

        public String getAlgorithmName() {
            return this.digest.getAlgorithmName() + "/" + (this.digest.getDigestSize() * 16);
        }

        public int getDigestSize() {
            return this.digest.getDigestSize() * 2;
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
}
