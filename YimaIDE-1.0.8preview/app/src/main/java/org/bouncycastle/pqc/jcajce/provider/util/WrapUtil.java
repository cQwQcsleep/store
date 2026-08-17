package org.bouncycastle.pqc.jcajce.provider.util;

import io.github.rosemoe.sora.widget.CodeEditor;
import java.security.InvalidKeyException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.agreement.kdf.ConcatenationKDFGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.ARIAEngine;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.RFC3394WrapEngine;
import org.bouncycastle.crypto.engines.RFC5649WrapEngine;
import org.bouncycastle.crypto.engines.SEEDEngine;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class WrapUtil {
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

    public static Wrapper getKeyUnwrapper(KTSParameterSpec kTSParameterSpec, byte[] bArr) throws InvalidKeyException {
        Wrapper wrapper = getWrapper(kTSParameterSpec.getKeyAlgorithmName());
        wrapper.init(false, kTSParameterSpec.getKdfAlgorithm() == null ? new KeyParameter(bArr, 0, (kTSParameterSpec.getKeySize() + 7) / 8) : new KeyParameter(makeKeyBytes(kTSParameterSpec, bArr)));
        return wrapper;
    }

    public static Wrapper getKeyWrapper(KTSParameterSpec kTSParameterSpec, byte[] bArr) throws InvalidKeyException {
        Wrapper wrapper = getWrapper(kTSParameterSpec.getKeyAlgorithmName());
        wrapper.init(true, kTSParameterSpec.getKdfAlgorithm() == null ? new KeyParameter(Arrays.copyOfRange(bArr, 0, (kTSParameterSpec.getKeySize() + 7) / 8)) : new KeyParameter(makeKeyBytes(kTSParameterSpec, bArr)));
        return wrapper;
    }

    public static Wrapper getWrapper(String str) {
        if (str.equalsIgnoreCase("AESWRAP") || str.equalsIgnoreCase("AES")) {
            return new RFC3394WrapEngine(new AESEngine());
        }
        if (str.equalsIgnoreCase("ARIA")) {
            return new RFC3394WrapEngine(new ARIAEngine());
        }
        if (str.equalsIgnoreCase("Camellia")) {
            return new RFC3394WrapEngine(new CamelliaEngine());
        }
        if (str.equalsIgnoreCase("SEED")) {
            return new RFC3394WrapEngine(new SEEDEngine());
        }
        if (str.equalsIgnoreCase("AES-KWP")) {
            return new RFC5649WrapEngine(new AESEngine());
        }
        if (str.equalsIgnoreCase("Camellia-KWP")) {
            return new RFC5649WrapEngine(new CamelliaEngine());
        }
        if (str.equalsIgnoreCase("ARIA-KWP")) {
            return new RFC5649WrapEngine(new ARIAEngine());
        }
        c41.a("unknown key algorithm: ".concat(str));
        return null;
    }

    private static byte[] makeKeyBytes(KTSParameterSpec kTSParameterSpec, byte[] bArr) throws InvalidKeyException {
        AlgorithmIdentifier kdfAlgorithm = kTSParameterSpec.getKdfAlgorithm();
        byte[] otherInfo = kTSParameterSpec.getOtherInfo();
        int keySize = (kTSParameterSpec.getKeySize() + 7) / 8;
        byte[] bArr2 = new byte[keySize];
        if (X9ObjectIdentifiers.id_kdf_kdf2.equals(kdfAlgorithm.getAlgorithm())) {
            KDF2BytesGenerator kDF2BytesGenerator = new KDF2BytesGenerator(getDigest(AlgorithmIdentifier.getInstance(kdfAlgorithm.getParameters()).getAlgorithm()));
            kDF2BytesGenerator.init(new KDFParameters(bArr, otherInfo));
            kDF2BytesGenerator.generateBytes(bArr2, 0, keySize);
            return bArr2;
        }
        if (X9ObjectIdentifiers.id_kdf_kdf3.equals(kdfAlgorithm.getAlgorithm())) {
            ConcatenationKDFGenerator concatenationKDFGenerator = new ConcatenationKDFGenerator(getDigest(AlgorithmIdentifier.getInstance(kdfAlgorithm.getParameters()).getAlgorithm()));
            concatenationKDFGenerator.init(new KDFParameters(bArr, otherInfo));
            concatenationKDFGenerator.generateBytes(bArr2, 0, keySize);
            return bArr2;
        }
        if (!NISTObjectIdentifiers.id_shake256.equals(kdfAlgorithm.getAlgorithm())) {
            throw new InvalidKeyException("Unrecognized KDF: " + kdfAlgorithm.getAlgorithm());
        }
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, bArr.length);
        sHAKEDigest.update(otherInfo, 0, otherInfo.length);
        sHAKEDigest.doFinal(bArr2, 0, keySize);
        return bArr2;
    }
}
