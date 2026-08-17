package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Rainbow {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.rainbow.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.RAINBOW", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.RAINBOW", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = BCObjectIdentifiers.rainbow_III_classic;
            addKeyPairGeneratorAlgorithm(configurableProvider, "RAINBOW-III-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowIIIclassic", aSN1ObjectIdentifier);
            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = BCObjectIdentifiers.rainbow_III_circumzenithal;
            addKeyPairGeneratorAlgorithm(configurableProvider, "RAINBOW-III-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowIIIcircum", aSN1ObjectIdentifier2);
            ASN1ObjectIdentifier aSN1ObjectIdentifier3 = BCObjectIdentifiers.rainbow_III_compressed;
            addKeyPairGeneratorAlgorithm(configurableProvider, "RAINBOW-III-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowIIIcomp", aSN1ObjectIdentifier3);
            ASN1ObjectIdentifier aSN1ObjectIdentifier4 = BCObjectIdentifiers.rainbow_V_classic;
            addKeyPairGeneratorAlgorithm(configurableProvider, "RAINBOW-V-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowVclassic", aSN1ObjectIdentifier4);
            ASN1ObjectIdentifier aSN1ObjectIdentifier5 = BCObjectIdentifiers.rainbow_V_circumzenithal;
            addKeyPairGeneratorAlgorithm(configurableProvider, "RAINBOW-V-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowVcircum", aSN1ObjectIdentifier5);
            ASN1ObjectIdentifier aSN1ObjectIdentifier6 = BCObjectIdentifiers.rainbow_V_compressed;
            addKeyPairGeneratorAlgorithm(configurableProvider, "RAINBOW-V-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyPairGeneratorSpi$RainbowVcomp", aSN1ObjectIdentifier6);
            addSignatureAlgorithm(configurableProvider, "RAINBOW", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$Base", BCObjectIdentifiers.rainbow);
            addSignatureAlgorithm(configurableProvider, "RAINBOW-III-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowIIIclassic", aSN1ObjectIdentifier);
            addSignatureAlgorithm(configurableProvider, "RAINBOW-III-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowIIIcircum", aSN1ObjectIdentifier2);
            addSignatureAlgorithm(configurableProvider, "RAINBOW-III-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowIIIcomp", aSN1ObjectIdentifier3);
            addSignatureAlgorithm(configurableProvider, "RAINBOW-V-CLASSIC", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowVclassic", aSN1ObjectIdentifier4);
            addSignatureAlgorithm(configurableProvider, "RAINBOW-V-CIRCUMZENITHAL", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowVcircum", aSN1ObjectIdentifier5);
            addSignatureAlgorithm(configurableProvider, "RAINBOW-v-COMPRESSED", "org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi$RainbowVcomp", aSN1ObjectIdentifier6);
            RainbowKeyFactorySpi rainbowKeyFactorySpi = new RainbowKeyFactorySpi();
            registerKeyFactoryOid(configurableProvider, aSN1ObjectIdentifier, "RAINBOW", rainbowKeyFactorySpi);
            registerKeyFactoryOid(configurableProvider, aSN1ObjectIdentifier2, "RAINBOW", rainbowKeyFactorySpi);
            registerKeyFactoryOid(configurableProvider, aSN1ObjectIdentifier3, "RAINBOW", rainbowKeyFactorySpi);
            registerKeyFactoryOid(configurableProvider, aSN1ObjectIdentifier4, "RAINBOW", rainbowKeyFactorySpi);
            registerKeyFactoryOid(configurableProvider, aSN1ObjectIdentifier5, "RAINBOW", rainbowKeyFactorySpi);
            registerKeyFactoryOid(configurableProvider, aSN1ObjectIdentifier6, "RAINBOW", rainbowKeyFactorySpi);
        }
    }
}
