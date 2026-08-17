package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Kyber {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.kyber.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = BCObjectIdentifiers.kyber512;
            addKeyFactoryAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi$Kyber512", aSN1ObjectIdentifier, new KyberKeyFactorySpi.Kyber512());
            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = BCObjectIdentifiers.kyber768;
            addKeyFactoryAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi$Kyber768", aSN1ObjectIdentifier2, new KyberKeyFactorySpi.Kyber768());
            ASN1ObjectIdentifier aSN1ObjectIdentifier3 = BCObjectIdentifiers.kyber1024;
            addKeyFactoryAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyFactorySpi$Kyber1024", aSN1ObjectIdentifier3, new KyberKeyFactorySpi.Kyber1024());
            configurableProvider.addAlgorithm("KeyPairGenerator.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi");
            addKeyPairGeneratorAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi$Kyber512", aSN1ObjectIdentifier);
            addKeyPairGeneratorAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi$Kyber768", aSN1ObjectIdentifier2);
            addKeyPairGeneratorAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyPairGeneratorSpi$Kyber1024", aSN1ObjectIdentifier3);
            configurableProvider.addAlgorithm("KeyGenerator.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi");
            addKeyGeneratorAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi$Kyber512", aSN1ObjectIdentifier);
            addKeyGeneratorAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi$Kyber768", aSN1ObjectIdentifier2);
            addKeyGeneratorAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberKeyGeneratorSpi$Kyber1024", aSN1ObjectIdentifier3);
            KyberKeyFactorySpi kyberKeyFactorySpi = new KyberKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.KYBER", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Base");
            StringBuilder sb = new StringBuilder("Alg.Alias.Cipher.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier4 = BCObjectIdentifiers.pqc_kem_kyber;
            sb.append(aSN1ObjectIdentifier4);
            configurableProvider.addAlgorithm(sb.toString(), "KYBER");
            addCipherAlgorithm(configurableProvider, "KYBER512", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Kyber512", aSN1ObjectIdentifier);
            addCipherAlgorithm(configurableProvider, "KYBER768", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Kyber768", aSN1ObjectIdentifier2);
            addCipherAlgorithm(configurableProvider, "KYBER1024", "org.bouncycastle.pqc.jcajce.provider.kyber.KyberCipherSpi$Kyber1024", aSN1ObjectIdentifier3);
            registerOid(configurableProvider, aSN1ObjectIdentifier4, "KYBER", kyberKeyFactorySpi);
        }
    }
}
