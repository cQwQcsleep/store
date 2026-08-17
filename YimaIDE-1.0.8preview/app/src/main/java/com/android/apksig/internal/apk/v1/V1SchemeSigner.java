package com.android.apksig.internal.apk.v1;

import com.android.apksig.KeyConfig;
import com.android.apksig.SignerEngineFactory;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.asn1.Asn1EncodingException;
import com.android.apksig.internal.jar.ManifestWriter;
import com.android.apksig.internal.jar.SignatureFileWriter;
import com.android.apksig.internal.pkcs7.AlgorithmIdentifier;
import com.android.apksig.internal.util.Pair;
import defpackage.s4f;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class V1SchemeSigner {
    private static final String ATTRIBUTE_VALUE_MANIFEST_VERSION = "1.0";
    private static final String ATTRIBUTE_VALUE_SIGNATURE_VERSION = "1.0";
    public static final String MANIFEST_ENTRY_NAME = "META-INF/MANIFEST.MF";
    private static final Attributes.Name ATTRIBUTE_NAME_CREATED_BY = new Attributes.Name("Created-By");
    private static final Attributes.Name SF_ATTRIBUTE_NAME_ANDROID_APK_SIGNED_NAME = new Attributes.Name(V1SchemeConstants.SF_ATTRIBUTE_NAME_ANDROID_APK_SIGNED_NAME_STR);

    /* JADX INFO: renamed from: com.android.apksig.internal.apk.v1.V1SchemeSigner$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$apksig$internal$apk$v1$DigestAlgorithm;

        static {
            int[] iArr = new int[DigestAlgorithm.values().length];
            $SwitchMap$com$android$apksig$internal$apk$v1$DigestAlgorithm = iArr;
            try {
                iArr[DigestAlgorithm.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$apk$v1$DigestAlgorithm[DigestAlgorithm.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class OutputManifestFile {
        public byte[] contents;
        public SortedMap<String, byte[]> individualSectionsContents;
        public Attributes mainSectionAttributes;
    }

    public static class SignerConfig {
        public List<X509Certificate> certificates;
        public boolean deterministicDsaSigning;
        public KeyConfig keyConfig;
        public String name;
        public PrivateKey privateKey;
        public DigestAlgorithm signatureDigestAlgorithm;
    }

    private V1SchemeSigner() {
    }

    private static void checkEntryNameValid(String str) throws ApkFormatException {
        for (char c : str.toCharArray()) {
            if (c == '\r' || c == '\n' || c == 0) {
                throw new ApkFormatException(String.format("Unsupported character 0x%1$02x in ZIP entry name \"%2$s\"", Integer.valueOf(c), str));
            }
        }
    }

    public static OutputManifestFile generateManifestFile(DigestAlgorithm digestAlgorithm, Map<String, byte[]> map, byte[] bArr) throws ApkFormatException {
        Manifest manifest;
        if (bArr != null) {
            try {
                manifest = new Manifest(new ByteArrayInputStream(bArr));
            } catch (IOException e) {
                throw new ApkFormatException("Malformed source META-INF/MANIFEST.MF", e);
            }
        } else {
            manifest = null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Attributes attributes = new Attributes();
        if (manifest != null) {
            attributes.putAll(manifest.getMainAttributes());
        } else {
            attributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        }
        try {
            ManifestWriter.writeMainSection(byteArrayOutputStream, attributes);
            ArrayList<String> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList);
            TreeMap treeMap = new TreeMap();
            String entryDigestAttributeName = getEntryDigestAttributeName(digestAlgorithm);
            for (String str : arrayList) {
                checkEntryNameValid(str);
                byte[] bArr2 = map.get(str);
                Attributes attributes2 = new Attributes();
                attributes2.putValue(entryDigestAttributeName, Base64.getEncoder().encodeToString(bArr2));
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    ManifestWriter.writeIndividualSection(byteArrayOutputStream2, str, attributes2);
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    byteArrayOutputStream.write(byteArray);
                    treeMap.put(str, byteArray);
                } catch (IOException e2) {
                    g3c.a("Failed to write in-memory MANIFEST.MF", e2);
                    return null;
                }
            }
            OutputManifestFile outputManifestFile = new OutputManifestFile();
            outputManifestFile.contents = byteArrayOutputStream.toByteArray();
            outputManifestFile.mainSectionAttributes = attributes;
            outputManifestFile.individualSectionsContents = treeMap;
            return outputManifestFile;
        } catch (IOException e3) {
            g3c.a("Failed to write in-memory MANIFEST.MF", e3);
            return null;
        }
    }

    private static byte[] generateSignatureBlock(SignerConfig signerConfig, byte[] bArr) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException {
        List<X509Certificate> list = signerConfig.certificates;
        PublicKey publicKey = list.get(0).getPublicKey();
        DigestAlgorithm digestAlgorithm = signerConfig.signatureDigestAlgorithm;
        Pair<String, AlgorithmIdentifier> signerInfoSignatureAlgorithm = AlgorithmIdentifier.getSignerInfoSignatureAlgorithm(publicKey, digestAlgorithm, signerConfig.deterministicDsaSigning);
        String first = signerInfoSignatureAlgorithm.getFirst();
        try {
            byte[] bArrSign = SignerEngineFactory.getImplementation(signerConfig.keyConfig, first, null).sign(bArr);
            try {
                Signature signature = Signature.getInstance(first);
                signature.initVerify(publicKey);
                signature.update(bArr);
                if (!signature.verify(bArrSign)) {
                    throw new SignatureException("Signature did not verify");
                }
                try {
                    return ApkSigningBlockUtils.generatePkcs7DerEncodedMessage(bArrSign, null, list, AlgorithmIdentifier.getSignerInfoDigestAlgorithmOid(digestAlgorithm), signerInfoSignatureAlgorithm.getSecond());
                } catch (Asn1EncodingException | CertificateEncodingException unused) {
                    d54.a("Failed to encode signature block");
                    return null;
                }
            } catch (InvalidKeyException e) {
                throw new InvalidKeyException("Failed to verify generated " + first + " signature using public key from certificate", e);
            } catch (SignatureException e2) {
                throw new SignatureException("Failed to verify generated " + first + " signature using public key from certificate", e2);
            }
        } catch (InvalidAlgorithmParameterException | SignatureException e3) {
            throw new SignatureException("Failed to sign using " + first, e3);
        } catch (InvalidKeyException e4) {
            throw new InvalidKeyException("Failed to sign using " + first, e4);
        }
    }

    private static byte[] generateSignatureFile(List<Integer> list, DigestAlgorithm digestAlgorithm, String str, OutputManifestFile outputManifestFile) throws NoSuchAlgorithmException {
        Attributes mainAttributes = new Manifest().getMainAttributes();
        mainAttributes.put(Attributes.Name.SIGNATURE_VERSION, "1.0");
        mainAttributes.put(ATTRIBUTE_NAME_CREATED_BY, str);
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(String.valueOf(iIntValue));
            }
            mainAttributes.put(SF_ATTRIBUTE_NAME_ANDROID_APK_SIGNED_NAME, sb.toString());
        }
        MessageDigest messageDigestInstance = getMessageDigestInstance(digestAlgorithm);
        mainAttributes.putValue(getManifestDigestAttributeName(digestAlgorithm), Base64.getEncoder().encodeToString(messageDigestInstance.digest(outputManifestFile.contents)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            SignatureFileWriter.writeMainSection(byteArrayOutputStream, mainAttributes);
            String entryDigestAttributeName = getEntryDigestAttributeName(digestAlgorithm);
            for (Map.Entry<String, byte[]> entry : outputManifestFile.individualSectionsContents.entrySet()) {
                String key = entry.getKey();
                byte[] bArrDigest = messageDigestInstance.digest(entry.getValue());
                Attributes attributes = new Attributes();
                attributes.putValue(entryDigestAttributeName, Base64.getEncoder().encodeToString(bArrDigest));
                try {
                    SignatureFileWriter.writeIndividualSection(byteArrayOutputStream, key, attributes);
                } catch (IOException e) {
                    g3c.a("Failed to write in-memory .SF file", e);
                    return null;
                }
            }
            if (byteArrayOutputStream.size() > 0 && byteArrayOutputStream.size() % 1024 == 0) {
                try {
                    SignatureFileWriter.writeSectionDelimiter(byteArrayOutputStream);
                } catch (IOException e2) {
                    g3c.a("Failed to write to ByteArrayOutputStream", e2);
                    return null;
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e3) {
            g3c.a("Failed to write in-memory .SF file", e3);
            return null;
        }
    }

    private static String getEntryDigestAttributeName(DigestAlgorithm digestAlgorithm) {
        int i = AnonymousClass1.$SwitchMap$com$android$apksig$internal$apk$v1$DigestAlgorithm[digestAlgorithm.ordinal()];
        if (i == 1) {
            return "SHA1-Digest";
        }
        if (i == 2) {
            return "SHA-256-Digest";
        }
        aca.a("Unexpected content digest algorithm: ", digestAlgorithm);
        return null;
    }

    public static String getJcaMessageDigestAlgorithm(DigestAlgorithm digestAlgorithm) {
        return digestAlgorithm.getJcaMessageDigestAlgorithm();
    }

    private static String getManifestDigestAttributeName(DigestAlgorithm digestAlgorithm) {
        int i = AnonymousClass1.$SwitchMap$com$android$apksig$internal$apk$v1$DigestAlgorithm[digestAlgorithm.ordinal()];
        if (i == 1) {
            return "SHA1-Digest-Manifest";
        }
        if (i == 2) {
            return "SHA-256-Digest-Manifest";
        }
        aca.a("Unexpected content digest algorithm: ", digestAlgorithm);
        return null;
    }

    private static MessageDigest getMessageDigestInstance(DigestAlgorithm digestAlgorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(digestAlgorithm.getJcaMessageDigestAlgorithm());
    }

    public static Set<String> getOutputEntryNames(List<SignerConfig> list) {
        HashSet hashSet = new HashSet((list.size() * 2) + 1);
        for (SignerConfig signerConfig : list) {
            String str = signerConfig.name;
            hashSet.add("META-INF/" + str + ".SF");
            hashSet.add("META-INF/" + str + "." + signerConfig.certificates.get(0).getPublicKey().getAlgorithm().toUpperCase(Locale.US));
        }
        hashSet.add("META-INF/MANIFEST.MF");
        return hashSet;
    }

    public static String getSafeSignerName(String str) {
        if (str.isEmpty()) {
            w01.a("Empty name");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toUpperCase(Locale.US).toCharArray();
        for (int i = 0; i < Math.min(charArray.length, 8); i++) {
            char c = charArray[i];
            if ((c < 'A' || c > 'Z') && !((c >= '0' && c <= '9') || c == '-' || c == '_')) {
                sb.append('_');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static DigestAlgorithm getSuggestedSignatureDigestAlgorithm(PublicKey publicKey, int i) throws InvalidKeyException {
        String algorithm = publicKey.getAlgorithm();
        if ("RSA".equalsIgnoreCase(algorithm) || "1.2.840.113549.1.1.1".equals(algorithm)) {
            return i < 18 ? DigestAlgorithm.SHA1 : DigestAlgorithm.SHA256;
        }
        if ("DSA".equalsIgnoreCase(algorithm)) {
            return i < 21 ? DigestAlgorithm.SHA1 : DigestAlgorithm.SHA256;
        }
        if (!"EC".equalsIgnoreCase(algorithm)) {
            s4f.a("Unsupported key algorithm: ", algorithm);
            return null;
        }
        if (i >= 18) {
            return DigestAlgorithm.SHA256;
        }
        f54.a("ECDSA signatures only supported for minSdkVersion 18 and higher");
        return null;
    }

    public static boolean isJarEntryDigestNeededInManifest(String str) {
        if (str.endsWith("/")) {
            return false;
        }
        if (!str.startsWith("META-INF/") || str.indexOf(47, 9) != -1) {
            return true;
        }
        String lowerCase = str.substring(9).toLowerCase(Locale.US);
        return ("manifest.mf".equals(lowerCase) || lowerCase.endsWith(".sf") || lowerCase.endsWith(".rsa") || lowerCase.endsWith(".dsa") || lowerCase.endsWith(".ec") || lowerCase.startsWith("sig-")) ? false : true;
    }

    public static List<Pair<String, byte[]>> sign(List<SignerConfig> list, DigestAlgorithm digestAlgorithm, Map<String, byte[]> map, List<Integer> list2, byte[] bArr, String str) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, ApkFormatException, CertificateException {
        if (list.isEmpty()) {
            w01.a("At least one signer config must be provided");
            return null;
        }
        if (list.size() <= 10) {
            return signManifest(list, digestAlgorithm, list2, str, generateManifestFile(digestAlgorithm, map, bArr));
        }
        prd.a("APK Signature Scheme v1 only supports a maximum of 10, ", list.size(), " provided");
        return null;
    }

    public static List<Pair<String, byte[]>> signManifest(List<SignerConfig> list, DigestAlgorithm digestAlgorithm, List<Integer> list2, String str, OutputManifestFile outputManifestFile) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException {
        if (list.isEmpty()) {
            w01.a("At least one signer config must be provided");
            return null;
        }
        ArrayList arrayList = new ArrayList((list.size() * 2) + 1);
        byte[] bArrGenerateSignatureFile = generateSignatureFile(list2, digestAlgorithm, str, outputManifestFile);
        for (SignerConfig signerConfig : list) {
            String str2 = signerConfig.name;
            try {
                byte[] bArrGenerateSignatureBlock = generateSignatureBlock(signerConfig, bArrGenerateSignatureFile);
                arrayList.add(Pair.of("META-INF/" + str2 + ".SF", bArrGenerateSignatureFile));
                arrayList.add(Pair.of("META-INF/" + str2 + "." + signerConfig.certificates.get(0).getPublicKey().getAlgorithm().toUpperCase(Locale.US), bArrGenerateSignatureBlock));
            } catch (InvalidKeyException e) {
                throw new InvalidKeyException("Failed to sign using signer \"" + str2 + "\"", e);
            } catch (SignatureException e2) {
                throw new SignatureException("Failed to sign using signer \"" + str2 + "\"", e2);
            } catch (CertificateException e3) {
                throw new CertificateException("Failed to sign using signer \"" + str2 + "\"", e3);
            }
        }
        arrayList.add(Pair.of("META-INF/MANIFEST.MF", outputManifestFile.contents));
        return arrayList;
    }
}
