package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyFactorySpi;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CMCE {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.cmce.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCEKeyGeneratorSpi");
            CMCEKeyFactorySpi cMCEKeyFactorySpi = new CMCEKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.CMCE", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$Base");
            StringBuilder sb = new StringBuilder("Alg.Alias.Cipher.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = BCObjectIdentifiers.pqc_kem_mceliece;
            sb.append(aSN1ObjectIdentifier);
            configurableProvider.addAlgorithm(sb.toString(), "CMCE");
            addCipherAlgorithm(configurableProvider, "mceliece348864", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$MCE348864", BCObjectIdentifiers.mceliece348864_r3);
            addCipherAlgorithm(configurableProvider, "mceliece460896", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$MCE460896", BCObjectIdentifiers.mceliece460896_r3);
            addCipherAlgorithm(configurableProvider, "mceliece6688128", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$MCE6688128", BCObjectIdentifiers.mceliece6688128_r3);
            addCipherAlgorithm(configurableProvider, "mceliece6960119", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$MCE6960119", BCObjectIdentifiers.mceliece6960119_r3);
            addCipherAlgorithm(configurableProvider, "mceliece8192128", "org.bouncycastle.pqc.jcajce.provider.cmce.CMCECipherSpi$MCE8192128", BCObjectIdentifiers.mceliece8192128_r3);
            registerOid(configurableProvider, aSN1ObjectIdentifier, "CMCE", cMCEKeyFactorySpi);
            registerOidAlgorithmParameters(configurableProvider, aSN1ObjectIdentifier, "CMCE");
        }
    }
}
