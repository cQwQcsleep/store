package org.bouncycastle.pqc.crypto.xwing;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class XWingPrivateKeyParameters extends XWingKeyParameters {
    private final KyberPrivateKeyParameters kybPriv;
    private final X25519PrivateKeyParameters xdhPriv;

    public XWingPrivateKeyParameters(byte[] bArr) {
        super(false);
        this.kybPriv = new KyberPrivateKeyParameters(KyberParameters.kyber768, Arrays.copyOfRange(bArr, 0, bArr.length - 32));
        this.xdhPriv = new X25519PrivateKeyParameters(bArr, bArr.length - 32);
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.kybPriv.getEncoded(), this.xdhPriv.getEncoded());
    }

    public KyberPrivateKeyParameters getKyberPrivateKey() {
        return this.kybPriv;
    }

    public X25519PrivateKeyParameters getXDHPrivateKey() {
        return this.xdhPriv;
    }

    public XWingPrivateKeyParameters(AsymmetricKeyParameter asymmetricKeyParameter, AsymmetricKeyParameter asymmetricKeyParameter2) {
        super(true);
        this.kybPriv = (KyberPrivateKeyParameters) asymmetricKeyParameter;
        this.xdhPriv = (X25519PrivateKeyParameters) asymmetricKeyParameter2;
    }
}
