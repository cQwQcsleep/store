package net.i2p.crypto.eddsa;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class EdDSASecurityProvider extends Provider {
    public static final String PROVIDER_NAME = "EdDSA";
    private static final long serialVersionUID = 1210027906682292307L;

    public EdDSASecurityProvider() {
        super("EdDSA", 0.3d, "str4d EdDSA security provider wrapper");
        AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: net.i2p.crypto.eddsa.EdDSASecurityProvider.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                EdDSASecurityProvider.this.setup();
                return null;
            }
        });
    }

    public void setup() {
        put("KeyFactory.EdDSA", "net.i2p.crypto.eddsa.KeyFactory");
        put("KeyPairGenerator.EdDSA", "net.i2p.crypto.eddsa.KeyPairGenerator");
        put("Signature.NONEwithEdDSA", "net.i2p.crypto.eddsa.EdDSAEngine");
        put("Alg.Alias.KeyFactory.1.3.101.112", "EdDSA");
        put("Alg.Alias.KeyFactory.OID.1.3.101.112", "EdDSA");
        put("Alg.Alias.KeyPairGenerator.1.3.101.112", "EdDSA");
        put("Alg.Alias.KeyPairGenerator.OID.1.3.101.112", "EdDSA");
        put("Alg.Alias.Signature.1.3.101.112", EdDSAEngine.SIGNATURE_ALGORITHM);
        put("Alg.Alias.Signature.OID.1.3.101.112", EdDSAEngine.SIGNATURE_ALGORITHM);
    }
}
