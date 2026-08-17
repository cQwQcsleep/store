package com.android.apksig.internal.apk.v1;

import com.android.apksig.ApkVerifier;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.asn1.Asn1BerParser;
import com.android.apksig.internal.asn1.Asn1Class;
import com.android.apksig.internal.asn1.Asn1DecodingException;
import com.android.apksig.internal.asn1.Asn1Field;
import com.android.apksig.internal.asn1.Asn1OpaqueObject;
import com.android.apksig.internal.asn1.Asn1Type;
import com.android.apksig.internal.jar.ManifestParser;
import com.android.apksig.internal.oid.OidConstants;
import com.android.apksig.internal.pkcs7.AlgorithmIdentifier;
import com.android.apksig.internal.pkcs7.Attribute;
import com.android.apksig.internal.pkcs7.ContentInfo;
import com.android.apksig.internal.pkcs7.Pkcs7Constants;
import com.android.apksig.internal.pkcs7.Pkcs7DecodingException;
import com.android.apksig.internal.pkcs7.SignedData;
import com.android.apksig.internal.pkcs7.SignerInfo;
import com.android.apksig.internal.util.ByteBufferUtils;
import com.android.apksig.internal.util.InclusiveIntRange;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.internal.x509.Certificate;
import com.android.apksig.internal.zip.CentralDirectoryRecord;
import com.android.apksig.internal.zip.LocalFileRecord;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSinks;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class V1SchemeVerifier {
    private static final String[] JB_MR2_AND_NEWER_DIGEST_ALGS = {"SHA-512", "SHA-384", "SHA-256", "SHA-1"};
    private static final Map<String, Integer> MIN_SDK_VESION_FROM_WHICH_DIGEST_SUPPORTED_IN_MANIFEST;
    private static final Map<String, String> UPPER_CASE_JCA_DIGEST_ALG_TO_CANONICAL;

    public static class NamedDigest {
        public final byte[] digest;
        public final String jcaDigestAlgorithm;

        private NamedDigest(String str, byte[] bArr) {
            this.jcaDigestAlgorithm = str;
            this.digest = bArr;
        }
    }

    @Asn1Class(type = Asn1Type.CHOICE)
    public static class ObjectIdentifierChoice {

        @Asn1Field(type = Asn1Type.OBJECT_IDENTIFIER)
        public String value;
    }

    @Asn1Class(type = Asn1Type.CHOICE)
    public static class OctetStringChoice {

        @Asn1Field(type = Asn1Type.OCTET_STRING)
        public byte[] value;
    }

    public static class Result {
        public boolean verified;
        public final List<SignerInfo> signers = new ArrayList();
        public final List<SignerInfo> ignoredSigners = new ArrayList();
        private final List<ApkVerifier.IssueWithParams> mWarnings = new ArrayList();
        private final List<ApkVerifier.IssueWithParams> mErrors = new ArrayList();

        public static class SignerInfo {
            public final List<X509Certificate> certChain;
            private final List<ApkVerifier.IssueWithParams> mErrors;
            private final List<ApkVerifier.IssueWithParams> mWarnings;
            public final String name;
            public final String signatureBlockFileName;
            public final String signatureFileName;

            private SignerInfo(String str, String str2, String str3) {
                this.certChain = new ArrayList();
                this.mWarnings = new ArrayList();
                this.mErrors = new ArrayList();
                this.name = str;
                this.signatureBlockFileName = str2;
                this.signatureFileName = str3;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addError(ApkVerifier.Issue issue, Object... objArr) {
                this.mErrors.add(new ApkVerifier.IssueWithParams(issue, objArr));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addWarning(ApkVerifier.Issue issue, Object... objArr) {
                this.mWarnings.add(new ApkVerifier.IssueWithParams(issue, objArr));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public List<ApkVerifier.IssueWithParams> getErrors() {
                return this.mErrors;
            }

            public List<ApkVerifier.IssueWithParams> getWarnings() {
                return this.mWarnings;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addError(ApkVerifier.Issue issue, Object... objArr) {
            this.mErrors.add(new ApkVerifier.IssueWithParams(issue, objArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWarning(ApkVerifier.Issue issue, Object... objArr) {
            this.mWarnings.add(new ApkVerifier.IssueWithParams(issue, objArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean containsErrors() {
            if (!this.mErrors.isEmpty()) {
                return true;
            }
            Iterator<SignerInfo> it = this.signers.iterator();
            while (it.hasNext()) {
                if (it.next().containsErrors()) {
                    return true;
                }
            }
            return false;
        }

        public List<ApkVerifier.IssueWithParams> getErrors() {
            return this.mErrors;
        }

        public List<ApkVerifier.IssueWithParams> getWarnings() {
            return this.mWarnings;
        }
    }

    public static class SignedAttributes {
        private Map<String, List<Asn1OpaqueObject>> mAttrs;

        public SignedAttributes(Collection<Attribute> collection) throws Pkcs7DecodingException {
            HashMap map = new HashMap(collection.size());
            for (Attribute attribute : collection) {
                if (map.put(attribute.attrType, attribute.attrValues) != null) {
                    throw new Pkcs7DecodingException("Duplicate signed attribute: " + attribute.attrType);
                }
            }
            this.mAttrs = map;
        }

        private Asn1OpaqueObject getSingleValue(String str) throws Pkcs7DecodingException {
            List<Asn1OpaqueObject> list = this.mAttrs.get(str);
            if (list == null || list.isEmpty()) {
                return null;
            }
            if (list.size() <= 1) {
                return list.get(0);
            }
            throw new Pkcs7DecodingException("Attribute " + str + " has multiple values");
        }

        public String getSingleObjectIdentifierValue(String str) throws Pkcs7DecodingException {
            Asn1OpaqueObject singleValue = getSingleValue(str);
            if (singleValue == null) {
                return null;
            }
            try {
                return ((ObjectIdentifierChoice) Asn1BerParser.parse(singleValue.getEncoded(), ObjectIdentifierChoice.class)).value;
            } catch (Asn1DecodingException e) {
                throw new Pkcs7DecodingException("Failed to decode OBJECT IDENTIFIER", e);
            }
        }

        public byte[] getSingleOctetStringValue(String str) throws Pkcs7DecodingException {
            Asn1OpaqueObject singleValue = getSingleValue(str);
            if (singleValue == null) {
                return null;
            }
            try {
                return ((OctetStringChoice) Asn1BerParser.parse(singleValue.getEncoded(), OctetStringChoice.class)).value;
            } catch (Asn1DecodingException e) {
                throw new Pkcs7DecodingException("Failed to decode OBJECT IDENTIFIER", e);
            }
        }
    }

    public static class Signer {
        private boolean mIgnored;
        private final String mName;
        private final Result.SignerInfo mResult;
        private byte[] mSigFileBytes;
        private Set<String> mSigFileEntryNames;
        private final CentralDirectoryRecord mSignatureBlockEntry;
        private final CentralDirectoryRecord mSignatureFileEntry;

        private Signer(String str, CentralDirectoryRecord centralDirectoryRecord, CentralDirectoryRecord centralDirectoryRecord2, Result.SignerInfo signerInfo) {
            this.mName = str;
            this.mResult = signerInfo;
            this.mSignatureBlockEntry = centralDirectoryRecord;
            this.mSignatureFileEntry = centralDirectoryRecord2;
        }

        private void checkForStrippedApkSignatures(ManifestParser.Section section, Map<Integer, String> map, Set<Integer> set) {
            String attributeValue = section.getAttributeValue(V1SchemeConstants.SF_ATTRIBUTE_NAME_ANDROID_APK_SIGNED_NAME_STR);
            if (attributeValue == null) {
                if (set.isEmpty()) {
                    return;
                }
                this.mResult.addWarning(ApkVerifier.Issue.JAR_SIG_NO_APK_SIG_STRIP_PROTECTION, this.mSignatureFileEntry.getName());
                return;
            }
            if (map.isEmpty()) {
                return;
            }
            Set<Integer> setKeySet = map.keySet();
            HashSet<Integer> hashSet = new HashSet(1);
            StringTokenizer stringTokenizer = new StringTokenizer(attributeValue, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String strTrim = stringTokenizer.nextToken().trim();
                if (!strTrim.isEmpty()) {
                    try {
                        int i = Integer.parseInt(strTrim);
                        if (setKeySet.contains(Integer.valueOf(i))) {
                            hashSet.add(Integer.valueOf(i));
                        } else {
                            this.mResult.addWarning(ApkVerifier.Issue.JAR_SIG_UNKNOWN_APK_SIG_SCHEME_ID, this.mSignatureFileEntry.getName(), Integer.valueOf(i));
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            for (Integer num : hashSet) {
                num.intValue();
                if (!set.contains(num)) {
                    this.mResult.addError(ApkVerifier.Issue.JAR_SIG_MISSING_APK_SIG_REFERENCED, this.mSignatureFileEntry.getName(), num, map.get(num));
                }
            }
        }

        public static List<X509Certificate> getCertificateChain(List<X509Certificate> list, X509Certificate x509Certificate) {
            ArrayList arrayList = new ArrayList(list);
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(x509Certificate);
            arrayList.remove(x509Certificate);
            while (!x509Certificate.getSubjectDN().equals(x509Certificate.getIssuerDN())) {
                Principal issuerDN = x509Certificate.getIssuerDN();
                boolean z = false;
                for (int i = 0; i < arrayList.size(); i++) {
                    X509Certificate x509Certificate2 = (X509Certificate) arrayList.get(i);
                    if (issuerDN.equals(x509Certificate2.getSubjectDN())) {
                        arrayList.remove(i);
                        arrayList2.add(x509Certificate2);
                        z = true;
                        x509Certificate = x509Certificate2;
                        break;
                    }
                }
                if (!z) {
                    break;
                }
            }
            return arrayList2;
        }

        private boolean verifyManifestDigest(ManifestParser.Section section, boolean z, byte[] bArr, int i, int i2) throws NoSuchAlgorithmException {
            Collection<NamedDigest> digestsToVerify = V1SchemeVerifier.getDigestsToVerify(section, z ? "-Digest" : "-Digest-Manifest", i, i2);
            if (digestsToVerify.isEmpty()) {
                this.mResult.addWarning(ApkVerifier.Issue.JAR_SIG_NO_MANIFEST_DIGEST_IN_SIG_FILE, this.mSignatureFileEntry.getName());
                return false;
            }
            boolean z2 = true;
            for (NamedDigest namedDigest : digestsToVerify) {
                String str = namedDigest.jcaDigestAlgorithm;
                byte[] bArrDigest = V1SchemeVerifier.digest(str, bArr);
                byte[] bArr2 = namedDigest.digest;
                if (!Arrays.equals(bArr2, bArrDigest)) {
                    this.mResult.addWarning(ApkVerifier.Issue.JAR_SIG_ZIP_ENTRY_DIGEST_DID_NOT_VERIFY, "META-INF/MANIFEST.MF", str, this.mSignatureFileEntry.getName(), Base64.getEncoder().encodeToString(bArrDigest), Base64.getEncoder().encodeToString(bArr2));
                    z2 = false;
                }
            }
            return z2;
        }

        private void verifyManifestIndividualSectionDigest(ManifestParser.Section section, boolean z, ManifestParser.Section section2, byte[] bArr, int i, int i2) throws NoSuchAlgorithmException {
            String name = section.getName();
            Collection<NamedDigest> digestsToVerify = V1SchemeVerifier.getDigestsToVerify(section, "-Digest", i, i2);
            if (digestsToVerify.isEmpty()) {
                this.mResult.addError(ApkVerifier.Issue.JAR_SIG_NO_ZIP_ENTRY_DIGEST_IN_SIG_FILE, name, this.mSignatureFileEntry.getName());
                return;
            }
            int startOffset = section2.getStartOffset();
            int sizeBytes = section2.getSizeBytes();
            if (z) {
                int i3 = startOffset + sizeBytes;
                if (bArr[i3 - 1] == 10 && bArr[i3 - 2] == 10) {
                    sizeBytes--;
                }
            }
            for (NamedDigest namedDigest : digestsToVerify) {
                String str = namedDigest.jcaDigestAlgorithm;
                byte[] bArrDigest = V1SchemeVerifier.digest(str, bArr, startOffset, sizeBytes);
                byte[] bArr2 = namedDigest.digest;
                if (!Arrays.equals(bArr2, bArrDigest)) {
                    this.mResult.addError(ApkVerifier.Issue.JAR_SIG_MANIFEST_SECTION_DIGEST_DID_NOT_VERIFY, name, str, this.mSignatureFileEntry.getName(), Base64.getEncoder().encodeToString(bArrDigest), Base64.getEncoder().encodeToString(bArr2));
                }
            }
        }

        private void verifyManifestMainSectionDigest(ManifestParser.Section section, ManifestParser.Section section2, byte[] bArr, int i, int i2) throws NoSuchAlgorithmException {
            Collection<NamedDigest> digestsToVerify = V1SchemeVerifier.getDigestsToVerify(section, "-Digest-Manifest-Main-Attributes", i, i2);
            if (digestsToVerify.isEmpty()) {
                return;
            }
            for (NamedDigest namedDigest : digestsToVerify) {
                String str = namedDigest.jcaDigestAlgorithm;
                byte[] bArrDigest = V1SchemeVerifier.digest(str, bArr, section2.getStartOffset(), section2.getSizeBytes());
                byte[] bArr2 = namedDigest.digest;
                if (!Arrays.equals(bArr2, bArrDigest)) {
                    this.mResult.addError(ApkVerifier.Issue.JAR_SIG_MANIFEST_MAIN_SECTION_DIGEST_DID_NOT_VERIFY, str, this.mSignatureFileEntry.getName(), Base64.getEncoder().encodeToString(bArrDigest), Base64.getEncoder().encodeToString(bArr2));
                }
            }
        }

        private X509Certificate verifySignerInfoAgainstSigFile(SignedData signedData, Collection<X509Certificate> collection, SignerInfo signerInfo, byte[] bArr, int i, int i2) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, Pkcs7DecodingException {
            String str = signerInfo.digestAlgorithm.algorithm;
            String str2 = signerInfo.signatureAlgorithm.algorithm;
            List<InclusiveIntRange> valuesNotIn = InclusiveIntRange.fromTo(i, i2).getValuesNotIn(OidConstants.getSigAlgSupportedApiLevels(str, str2));
            if (!valuesNotIn.isEmpty()) {
                String userFriendlyNameForOid = OidConstants.OidToUserFriendlyNameMapper.getUserFriendlyNameForOid(str);
                String str3 = userFriendlyNameForOid == null ? str : userFriendlyNameForOid;
                String userFriendlyNameForOid2 = OidConstants.OidToUserFriendlyNameMapper.getUserFriendlyNameForOid(str2);
                String str4 = userFriendlyNameForOid2 == null ? str2 : userFriendlyNameForOid2;
                StringBuilder sb = new StringBuilder();
                for (InclusiveIntRange inclusiveIntRange : valuesNotIn) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    if (inclusiveIntRange.getMin() == inclusiveIntRange.getMax()) {
                        sb.append(String.valueOf(inclusiveIntRange.getMin()));
                    } else if (inclusiveIntRange.getMax() == Integer.MAX_VALUE) {
                        sb.append(inclusiveIntRange.getMin() + "+");
                    } else {
                        sb.append(inclusiveIntRange.getMin() + "-" + inclusiveIntRange.getMax());
                    }
                }
                this.mResult.addError(ApkVerifier.Issue.JAR_SIG_UNSUPPORTED_SIG_ALG, this.mSignatureBlockEntry.getName(), str, str2, sb.toString(), str3, str4);
                return null;
            }
            X509Certificate x509CertificateFindCertificate = Certificate.findCertificate(collection, signerInfo.sid);
            if (x509CertificateFindCertificate == null) {
                d54.a("Signing certificate referenced in SignerInfo not found in SignedData");
                return null;
            }
            if (x509CertificateFindCertificate.hasUnsupportedCriticalExtension()) {
                d54.a("Signing certificate has unsupported critical extensions");
                return null;
            }
            boolean[] keyUsage = x509CertificateFindCertificate.getKeyUsage();
            if (keyUsage != null) {
                boolean z = false;
                boolean z2 = keyUsage.length >= 1 && keyUsage[0];
                if (keyUsage.length >= 2 && keyUsage[1]) {
                    z = true;
                }
                if (!z2 && !z) {
                    d54.a("Signing certificate not authorized for use in digital signatures: keyUsage extension missing digitalSignature and nonRepudiation");
                    return null;
                }
            }
            String jcaSignatureAlgorithm = AlgorithmIdentifier.getJcaSignatureAlgorithm(str, str2);
            Signature signature = Signature.getInstance(jcaSignatureAlgorithm);
            PublicKey publicKey = x509CertificateFindCertificate.getPublicKey();
            try {
                signature.initVerify(publicKey);
            } catch (InvalidKeyException e) {
                try {
                    PublicKey publicKeyGeneratePublic = KeyFactory.getInstance(publicKey.getAlgorithm()).generatePublic(new X509EncodedKeySpec(ApkSigningBlockUtils.encodePublicKey(publicKey)));
                    Signature signature2 = Signature.getInstance(jcaSignatureAlgorithm);
                    signature2.initVerify(publicKeyGeneratePublic);
                    signature = signature2;
                } catch (InvalidKeySpecException unused) {
                    throw e;
                }
            }
            Asn1OpaqueObject asn1OpaqueObject = signerInfo.signedAttrs;
            if (asn1OpaqueObject == null) {
                signature.update(bArr);
            } else {
                if (i < 19) {
                    d54.a("APKs with Signed Attributes broken on platforms with API Level < 19");
                    return null;
                }
                try {
                    SignedAttributes signedAttributes = new SignedAttributes(Asn1BerParser.parseImplicitSetOf(asn1OpaqueObject.getEncoded(), Attribute.class));
                    if (i2 >= 24) {
                        String singleObjectIdentifierValue = signedAttributes.getSingleObjectIdentifierValue(Pkcs7Constants.OID_CONTENT_TYPE);
                        if (singleObjectIdentifierValue == null) {
                            throw new SignatureException("No Content Type in signed attributes");
                        }
                        if (!singleObjectIdentifierValue.equals(signedData.encapContentInfo.contentType)) {
                            return null;
                        }
                    }
                    byte[] singleOctetStringValue = signedAttributes.getSingleOctetStringValue(Pkcs7Constants.OID_MESSAGE_DIGEST);
                    if (singleOctetStringValue == null) {
                        throw new SignatureException("No content digest in signed attributes");
                    }
                    if (!Arrays.equals(singleOctetStringValue, MessageDigest.getInstance(AlgorithmIdentifier.getJcaDigestAlgorithm(str)).digest(bArr))) {
                        return null;
                    }
                    ByteBuffer encoded = signerInfo.signedAttrs.getEncoded();
                    signature.update((byte) 49);
                    encoded.position(1);
                    signature.update(encoded);
                } catch (Asn1DecodingException e2) {
                    throw new SignatureException("Failed to parse signed attributes", e2);
                }
            }
            if (signature.verify(ByteBufferUtils.toByteArray(signerInfo.signature.slice()))) {
                return x509CertificateFindCertificate;
            }
            return null;
        }

        public String getName() {
            return this.mName;
        }

        public Result.SignerInfo getResult() {
            return this.mResult;
        }

        public Set<String> getSigFileEntryNames() {
            return this.mSigFileEntryNames;
        }

        public String getSignatureBlockEntryName() {
            return this.mSignatureBlockEntry.getName();
        }

        public String getSignatureFileEntryName() {
            return this.mSignatureFileEntry.getName();
        }

        public boolean isIgnored() {
            return this.mIgnored;
        }

        public void setIgnored() {
            this.mIgnored = true;
        }

        public void verifySigBlockAgainstSigFile(DataSource dataSource, long j, int i, int i2) throws NoSuchAlgorithmException, IOException, ApkFormatException {
            Signer signer;
            try {
                byte[] uncompressedData = LocalFileRecord.getUncompressedData(dataSource, this.mSignatureBlockEntry, j);
                try {
                    this.mSigFileBytes = LocalFileRecord.getUncompressedData(dataSource, this.mSignatureFileEntry, j);
                    try {
                        ContentInfo contentInfo = (ContentInfo) Asn1BerParser.parse(ByteBuffer.wrap(uncompressedData), ContentInfo.class);
                        if (Pkcs7Constants.OID_SIGNED_DATA.equals(contentInfo.contentType)) {
                            SignedData signedData = (SignedData) Asn1BerParser.parse(contentInfo.content.getEncoded(), SignedData.class);
                            if (signedData.signerInfos.isEmpty()) {
                                this.mResult.addError(ApkVerifier.Issue.JAR_SIG_NO_SIGNERS, this.mSignatureBlockEntry.getName());
                                return;
                            }
                            List<SignerInfo> listSingletonList = signedData.signerInfos;
                            if (i < 24) {
                                listSingletonList = Collections.singletonList(listSingletonList.get(0));
                            }
                            SignerInfo signerInfo = null;
                            List<X509Certificate> certificates = null;
                            X509Certificate x509Certificate = null;
                            for (SignerInfo signerInfo2 : listSingletonList) {
                                if (certificates == null) {
                                    try {
                                        certificates = Certificate.parseCertificates(signedData.certificates);
                                    } catch (CertificateException e) {
                                        this.mResult.addError(ApkVerifier.Issue.JAR_SIG_PARSE_EXCEPTION, this.mSignatureBlockEntry.getName(), e);
                                        return;
                                    }
                                }
                                List<X509Certificate> list = certificates;
                                try {
                                    Signer signer2 = this;
                                    int i3 = i;
                                    int i4 = i2;
                                    try {
                                        X509Certificate x509CertificateVerifySignerInfoAgainstSigFile = signer2.verifySignerInfoAgainstSigFile(signedData, list, signerInfo2, this.mSigFileBytes, i3, i4);
                                        signer = signer2;
                                        try {
                                            if (signer.mResult.containsErrors()) {
                                                return;
                                            }
                                            if (x509CertificateVerifySignerInfoAgainstSigFile != null && signerInfo == null) {
                                                x509Certificate = x509CertificateVerifySignerInfoAgainstSigFile;
                                                signerInfo = signerInfo2;
                                            }
                                            this = signer;
                                            certificates = list;
                                            i = i3;
                                            i2 = i4;
                                        } catch (Pkcs7DecodingException e2) {
                                            e = e2;
                                            signer.mResult.addError(ApkVerifier.Issue.JAR_SIG_PARSE_EXCEPTION, signer.mSignatureBlockEntry.getName(), e);
                                            return;
                                        } catch (InvalidKeyException | SignatureException e3) {
                                            e = e3;
                                            signer.mResult.addError(ApkVerifier.Issue.JAR_SIG_VERIFY_EXCEPTION, signer.mSignatureBlockEntry.getName(), signer.mSignatureFileEntry.getName(), e);
                                            return;
                                        }
                                    } catch (Pkcs7DecodingException e4) {
                                        e = e4;
                                        signer = signer2;
                                    } catch (InvalidKeyException | SignatureException e5) {
                                        e = e5;
                                        signer = signer2;
                                    }
                                } catch (Pkcs7DecodingException e6) {
                                    e = e6;
                                    signer = this;
                                } catch (InvalidKeyException | SignatureException e7) {
                                    e = e7;
                                    signer = this;
                                }
                            }
                            Signer signer3 = this;
                            if (signerInfo == null) {
                                signer3.mResult.addError(ApkVerifier.Issue.JAR_SIG_DID_NOT_VERIFY, signer3.mSignatureBlockEntry.getName(), signer3.mSignatureFileEntry.getName());
                                return;
                            }
                            List<X509Certificate> certificateChain = getCertificateChain(certificates, x509Certificate);
                            signer3.mResult.certChain.clear();
                            signer3.mResult.certChain.addAll(certificateChain);
                            return;
                        }
                        try {
                            throw new Asn1DecodingException("Unsupported ContentInfo.contentType: " + contentInfo.contentType);
                        } catch (Asn1DecodingException e8) {
                            e = e8;
                        }
                    } catch (Asn1DecodingException e9) {
                        e = e9;
                    }
                    Asn1DecodingException asn1DecodingException = e;
                    asn1DecodingException.printStackTrace();
                    this.mResult.addError(ApkVerifier.Issue.JAR_SIG_PARSE_EXCEPTION, this.mSignatureBlockEntry.getName(), asn1DecodingException);
                } catch (ZipFormatException e10) {
                    throw new ApkFormatException("Malformed ZIP entry: " + this.mSignatureFileEntry.getName(), e10);
                }
            } catch (ZipFormatException e11) {
                throw new ApkFormatException("Malformed ZIP entry: " + this.mSignatureBlockEntry.getName(), e11);
            }
        }

        public void verifySigFileAgainstManifest(byte[] bArr, ManifestParser.Section section, Map<String, ManifestParser.Section> map, Map<Integer, String> map2, Set<Integer> set, int i, int i2) throws NoSuchAlgorithmException {
            ManifestParser manifestParser = new ManifestParser(this.mSigFileBytes);
            ManifestParser.Section section2 = manifestParser.readSection();
            if (section2.getAttributeValue(Attributes.Name.SIGNATURE_VERSION) == null) {
                this.mResult.addError(ApkVerifier.Issue.JAR_SIG_MISSING_VERSION_ATTR_IN_SIG_FILE, this.mSignatureFileEntry.getName());
                setIgnored();
                return;
            }
            if (i2 >= 24) {
                checkForStrippedApkSignatures(section2, map2, set);
                if (this.mResult.containsErrors()) {
                    return;
                }
            }
            String attributeValue = section2.getAttributeValue("Created-By");
            int i3 = 0;
            boolean z = attributeValue != null ? attributeValue.indexOf("signtool") != -1 : false;
            boolean zVerifyManifestDigest = verifyManifestDigest(section2, z, bArr, i, i2);
            boolean z2 = z;
            if (!z2) {
                verifyManifestMainSectionDigest(section2, section, bArr, i, i2);
            }
            if (this.mResult.containsErrors()) {
                return;
            }
            List<ManifestParser.Section> allSections = manifestParser.readAllSections();
            HashSet hashSet = new HashSet(allSections.size());
            for (ManifestParser.Section section3 : allSections) {
                int i4 = i3 + 1;
                String name = section3.getName();
                if (name == null) {
                    this.mResult.addError(ApkVerifier.Issue.JAR_SIG_UNNNAMED_SIG_FILE_SECTION, this.mSignatureFileEntry.getName(), Integer.valueOf(i4));
                    setIgnored();
                    return;
                }
                if (!hashSet.add(name)) {
                    this.mResult.addError(ApkVerifier.Issue.JAR_SIG_DUPLICATE_SIG_FILE_SECTION, this.mSignatureFileEntry.getName(), name);
                    setIgnored();
                    return;
                }
                if (!zVerifyManifestDigest) {
                    ManifestParser.Section section4 = map.get(name);
                    if (section4 == null) {
                        this.mResult.addError(ApkVerifier.Issue.JAR_SIG_NO_ZIP_ENTRY_DIGEST_IN_SIG_FILE, name, this.mSignatureFileEntry.getName());
                        setIgnored();
                    } else {
                        boolean z3 = z2;
                        verifyManifestIndividualSectionDigest(section3, z3, section4, bArr, i, i2);
                        z2 = z3;
                    }
                }
                i3 = i4;
            }
            this.mSigFileEntryNames = hashSet;
        }
    }

    public static class Signers {
        private Signers() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void verify(DataSource dataSource, long j, List<CentralDirectoryRecord> list, Set<String> set, Map<Integer, String> map, Set<Integer> set2, int i, int i2, Result result) throws NoSuchAlgorithmException, IOException, ApkFormatException {
            HashMap map2 = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            CentralDirectoryRecord centralDirectoryRecord = null;
            for (CentralDirectoryRecord centralDirectoryRecord2 : list) {
                String name = centralDirectoryRecord2.getName();
                if (name.startsWith("META-INF/")) {
                    if (centralDirectoryRecord == null && "META-INF/MANIFEST.MF".equals(name)) {
                        centralDirectoryRecord = centralDirectoryRecord2;
                    } else if (name.endsWith(".SF")) {
                        map2.put(name, centralDirectoryRecord2);
                    } else if (name.endsWith(".RSA") || name.endsWith(".DSA") || name.endsWith(".EC")) {
                        arrayList.add(centralDirectoryRecord2);
                    }
                }
            }
            int i3 = 0;
            if (centralDirectoryRecord == null) {
                result.addError(ApkVerifier.Issue.JAR_SIG_NO_MANIFEST, new Object[0]);
                return;
            }
            DataSource dataSource2 = dataSource;
            long j2 = j;
            try {
                byte[] uncompressedData = LocalFileRecord.getUncompressedData(dataSource2, centralDirectoryRecord, j2);
                Pair<ManifestParser.Section, Map<String, ManifestParser.Section>> manifest = V1SchemeVerifier.parseManifest(uncompressedData, set, result);
                if (result.containsErrors()) {
                    return;
                }
                ManifestParser.Section first = manifest.getFirst();
                Map<String, ManifestParser.Section> second = manifest.getSecond();
                ArrayList<Signer> arrayList2 = new ArrayList(arrayList.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    CentralDirectoryRecord centralDirectoryRecord3 = (CentralDirectoryRecord) it.next();
                    String name2 = centralDirectoryRecord3.getName();
                    int iLastIndexOf = name2.lastIndexOf(46);
                    it = it;
                    if (iLastIndexOf == -1) {
                        f63.a("Signature block file name does not contain extension: ".concat(name2));
                        return;
                    }
                    String strConcat = name2.substring(i3, iLastIndexOf).concat(".SF");
                    CentralDirectoryRecord centralDirectoryRecord4 = (CentralDirectoryRecord) map2.get(strConcat);
                    if (centralDirectoryRecord4 == null) {
                        result.addWarning(ApkVerifier.Issue.JAR_SIG_MISSING_FILE, name2, strConcat);
                    } else {
                        String strSubstring = name2.substring(9);
                        arrayList2.add(new Signer(strSubstring, centralDirectoryRecord3, centralDirectoryRecord4, new Result.SignerInfo(strSubstring, name2, centralDirectoryRecord4.getName())));
                        map2 = map2;
                        i3 = 0;
                    }
                }
                if (arrayList2.isEmpty()) {
                    result.addError(ApkVerifier.Issue.JAR_SIG_NO_SIGNATURES, new Object[0]);
                    return;
                }
                if (arrayList2.size() > 10) {
                    result.addError(ApkVerifier.Issue.JAR_SIG_MAX_SIGNATURES_EXCEEDED, 10, Integer.valueOf(arrayList2.size()));
                    return;
                }
                for (Signer signer : arrayList2) {
                    ArrayList arrayList3 = arrayList2;
                    signer.verifySigBlockAgainstSigFile(dataSource2, j2, i, i2);
                    if (signer.getResult().containsErrors()) {
                        result.signers.add(signer.getResult());
                    }
                    dataSource2 = dataSource;
                    j2 = j;
                    arrayList2 = arrayList3;
                }
                ArrayList<Signer> arrayList4 = arrayList2;
                if (result.containsErrors()) {
                    return;
                }
                ArrayList<Signer> arrayList5 = new ArrayList(arrayList4.size());
                for (Signer signer2 : arrayList4) {
                    byte[] bArr = uncompressedData;
                    Map<String, ManifestParser.Section> map3 = second;
                    ManifestParser.Section section = first;
                    signer2.verifySigFileAgainstManifest(bArr, section, map3, map, set2, i, i2);
                    if (signer2.isIgnored()) {
                        result.ignoredSigners.add(signer2.getResult());
                    } else if (signer2.getResult().containsErrors()) {
                        result.signers.add(signer2.getResult());
                    } else {
                        arrayList5.add(signer2);
                    }
                    uncompressedData = bArr;
                    first = section;
                    second = map3;
                }
                Map<String, ManifestParser.Section> map4 = second;
                if (result.containsErrors()) {
                    return;
                }
                if (arrayList5.isEmpty()) {
                    result.addError(ApkVerifier.Issue.JAR_SIG_NO_SIGNATURES, new Object[0]);
                    return;
                }
                Set<Signer> setVerifyJarEntriesAgainstManifestAndSigners = V1SchemeVerifier.verifyJarEntriesAgainstManifestAndSigners(dataSource, j, list, map4, arrayList5, i, i2, result);
                if (result.containsErrors()) {
                    return;
                }
                HashSet hashSet = new HashSet((result.signers.size() * 2) + 1);
                hashSet.add(centralDirectoryRecord.getName());
                for (Signer signer3 : setVerifyJarEntriesAgainstManifestAndSigners) {
                    hashSet.add(signer3.getSignatureBlockEntryName());
                    hashSet.add(signer3.getSignatureFileEntryName());
                }
                Iterator<CentralDirectoryRecord> it2 = list.iterator();
                while (it2.hasNext()) {
                    String name3 = it2.next().getName();
                    if (name3.startsWith("META-INF/") && !name3.endsWith("/") && !hashSet.contains(name3)) {
                        result.addWarning(ApkVerifier.Issue.JAR_SIG_UNPROTECTED_ZIP_ENTRY, name3);
                    }
                }
                for (Signer signer4 : arrayList5) {
                    if (setVerifyJarEntriesAgainstManifestAndSigners.contains(signer4)) {
                        result.signers.add(signer4.getResult());
                    } else {
                        result.ignoredSigners.add(signer4.getResult());
                    }
                }
                result.verified = true;
            } catch (ZipFormatException e) {
                throw new ApkFormatException("Malformed ZIP entry: " + centralDirectoryRecord.getName(), e);
            }
        }
    }

    static {
        HashMap map = new HashMap(8);
        UPPER_CASE_JCA_DIGEST_ALG_TO_CANONICAL = map;
        map.put("MD5", "MD5");
        map.put("SHA", "SHA-1");
        map.put("SHA1", "SHA-1");
        map.put("SHA-1", "SHA-1");
        map.put("SHA-256", "SHA-256");
        map.put("SHA-384", "SHA-384");
        map.put("SHA-512", "SHA-512");
        HashMap map2 = new HashMap(5);
        MIN_SDK_VESION_FROM_WHICH_DIGEST_SUPPORTED_IN_MANIFEST = map2;
        map2.put("MD5", 0);
        map2.put("SHA-1", 0);
        map2.put("SHA-256", 0);
        map2.put("SHA-384", 9);
        map2.put("SHA-512", 9);
    }

    private V1SchemeVerifier() {
    }

    private static Set<String> checkForDuplicateEntries(List<CentralDirectoryRecord> list, Result result) {
        HashSet hashSet = new HashSet(list.size());
        Iterator<CentralDirectoryRecord> it = list.iterator();
        HashSet hashSet2 = null;
        while (it.hasNext()) {
            String name = it.next().getName();
            if (!hashSet.add(name)) {
                if (hashSet2 == null) {
                    hashSet2 = new HashSet();
                }
                if (hashSet2.add(name)) {
                    result.addError(ApkVerifier.Issue.JAR_SIG_DUPLICATE_ZIP_ENTRY, name);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] digest(String str, byte[] bArr, int i, int i2) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = getMessageDigest(str);
        messageDigest.update(bArr, i, i2);
        return messageDigest.digest();
    }

    private static String getCanonicalJcaMessageDigestAlgorithm(String str) {
        return UPPER_CASE_JCA_DIGEST_ALG_TO_CANONICAL.get(str.toUpperCase(Locale.US));
    }

    private static byte[] getDigest(Collection<NamedDigest> collection, String str) {
        for (NamedDigest namedDigest : collection) {
            if (namedDigest.jcaDigestAlgorithm.equalsIgnoreCase(str)) {
                return namedDigest.digest;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x005e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0060  */
    /* JADX WARN: Code duplicated, block: B:25:0x0066  */
    /* JADX WARN: Code duplicated, block: B:27:0x0072 A[LOOP:1: B:24:0x0064->B:27:0x0072, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:32:0x0085 A[EDGE_INSN: B:32:0x0085->B:33:0x008d BREAK  A[LOOP:1: B:24:0x0064->B:27:0x0072]] */
    /* JADX WARN: Code duplicated, block: B:43:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:23:0x0060, please report this as an issue */
    public static Collection<NamedDigest> getDigestsToVerify(ManifestParser.Section section, String str, int i, int i2) {
        int i3;
        String attributeValue;
        byte[] bArrDecode;
        byte[] digest;
        String canonicalJcaMessageDigestAlgorithm;
        Base64.Decoder decoder = Base64.getDecoder();
        ArrayList arrayList = new ArrayList(1);
        if (i < 18) {
            String attributeValue2 = section.getAttributeValue("Digest-Algorithms");
            if (attributeValue2 == null) {
                attributeValue2 = "SHA SHA1";
            }
            StringTokenizer stringTokenizer = new StringTokenizer(attributeValue2);
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                String attributeValue3 = section.getAttributeValue(strNextToken + str);
                if (attributeValue3 != null && (canonicalJcaMessageDigestAlgorithm = getCanonicalJcaMessageDigestAlgorithm(strNextToken)) != null && getMinSdkVersionFromWhichSupportedInManifestOrSignatureFile(canonicalJcaMessageDigestAlgorithm) <= i) {
                    arrayList.add(new NamedDigest(canonicalJcaMessageDigestAlgorithm, decoder.decode(attributeValue3)));
                    break;
                }
            }
            if (!arrayList.isEmpty()) {
                if (i2 >= 18) {
                    for (String str2 : JB_MR2_AND_NEWER_DIGEST_ALGS) {
                        attributeValue = section.getAttributeValue(getJarDigestAttributeName(str2, str));
                        if (attributeValue == null) {
                            bArrDecode = decoder.decode(attributeValue);
                            digest = getDigest(arrayList, str2);
                            if (digest != null) {
                                arrayList.add(new NamedDigest(str2, bArrDecode));
                                break;
                            }
                            arrayList.add(new NamedDigest(str2, bArrDecode));
                            break;
                        }
                    }
                }
            }
        } else if (i2 >= 18) {
            while (i3 < r11) {
                attributeValue = section.getAttributeValue(getJarDigestAttributeName(str2, str));
                if (attributeValue == null) {
                    bArrDecode = decoder.decode(attributeValue);
                    digest = getDigest(arrayList, str2);
                    if (digest != null && Arrays.equals(digest, bArrDecode)) {
                        break;
                    }
                    arrayList.add(new NamedDigest(str2, bArrDecode));
                    break;
                }
            }
        }
        return arrayList;
    }

    private static String getJarDigestAttributeName(String str, String str2) {
        if ("SHA-1".equalsIgnoreCase(str)) {
            return "SHA1" + str2;
        }
        return str + str2;
    }

    private static MessageDigest getMessageDigest(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(str);
    }

    public static int getMinSdkVersionFromWhichSupportedInManifestOrSignatureFile(String str) {
        Integer num = MIN_SDK_VESION_FROM_WHICH_DIGEST_SUPPORTED_IN_MANIFEST.get(str.toUpperCase(Locale.US));
        if (num != null) {
            return num.intValue();
        }
        return Integer.MAX_VALUE;
    }

    private static List<String> getSignerNames(List<Signer> list) {
        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Signer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        return arrayList;
    }

    private static boolean isJarEntryDigestNeededInManifest(String str) {
        if (str.startsWith("META-INF/")) {
            return false;
        }
        return !str.endsWith("/");
    }

    public static Pair<ManifestParser.Section, Map<String, ManifestParser.Section>> parseManifest(byte[] bArr, Set<String> set, Result result) {
        ManifestParser manifestParser = new ManifestParser(bArr);
        ManifestParser.Section section = manifestParser.readSection();
        List<ManifestParser.Section> allSections = manifestParser.readAllSections();
        HashMap map = new HashMap(allSections.size());
        int i = 0;
        for (ManifestParser.Section section2 : allSections) {
            i++;
            String name = section2.getName();
            if (name == null) {
                result.addError(ApkVerifier.Issue.JAR_SIG_UNNNAMED_MANIFEST_SECTION, Integer.valueOf(i));
            } else if (map.put(name, section2) != null) {
                result.addError(ApkVerifier.Issue.JAR_SIG_DUPLICATE_MANIFEST_SECTION, name);
            } else if (!set.contains(name)) {
                result.addError(ApkVerifier.Issue.JAR_SIG_MISSING_ZIP_ENTRY_REFERENCED_IN_MANIFEST, name);
            }
        }
        return Pair.of(section, map);
    }

    public static List<CentralDirectoryRecord> parseZipCentralDirectory(DataSource dataSource, ApkUtils.ZipSections zipSections) throws IOException, ApkFormatException {
        return ZipUtils.parseZipCentralDirectory(dataSource, zipSections);
    }

    public static Result verify(DataSource dataSource, ApkUtils.ZipSections zipSections, Map<Integer, String> map, Set<Integer> set, int i, int i2) throws NoSuchAlgorithmException, IOException, ApkFormatException {
        if (i > i2) {
            pnd.a("minSdkVersion (", i, ") > maxSdkVersion (", i2, ")");
            return null;
        }
        Result result = new Result();
        List<CentralDirectoryRecord> zipCentralDirectory = parseZipCentralDirectory(dataSource, zipSections);
        Set<String> setCheckForDuplicateEntries = checkForDuplicateEntries(zipCentralDirectory, result);
        if (result.containsErrors()) {
            return result;
        }
        Signers.verify(dataSource, zipSections.getZipCentralDirectoryOffset(), zipCentralDirectory, setCheckForDuplicateEntries, map, set, i, i2, result);
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Set<Signer> verifyJarEntriesAgainstManifestAndSigners(DataSource dataSource, long j, Collection<CentralDirectoryRecord> collection, Map<String, ManifestParser.Section> map, List<Signer> list, int i, int i2, Result result) throws NoSuchAlgorithmException, IOException, ApkFormatException {
        Set<Signer> set;
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, CentralDirectoryRecord.BY_LOCAL_FILE_HEADER_OFFSET_COMPARATOR);
        Iterator it = arrayList.iterator();
        ArrayList arrayList2 = null;
        String str = null;
        while (true) {
            int i3 = 0;
            if (!it.hasNext()) {
                break;
            }
            CentralDirectoryRecord centralDirectoryRecord = (CentralDirectoryRecord) it.next();
            String name = centralDirectoryRecord.getName();
            if (isJarEntryDigestNeededInManifest(name)) {
                ManifestParser.Section section = map.get(name);
                if (section == null) {
                    result.addError(ApkVerifier.Issue.JAR_SIG_NO_ZIP_ENTRY_DIGEST_IN_MANIFEST, name);
                } else {
                    ArrayList arrayList3 = new ArrayList(list.size());
                    for (Signer signer : list) {
                        if (signer.getSigFileEntryNames().contains(name)) {
                            arrayList3.add(signer);
                        }
                    }
                    if (arrayList3.isEmpty()) {
                        result.addError(ApkVerifier.Issue.JAR_SIG_ZIP_ENTRY_NOT_SIGNED, name);
                    } else {
                        if (arrayList2 == null) {
                            str = name;
                            arrayList2 = arrayList3;
                        } else if (!arrayList3.equals(arrayList2)) {
                            result.addError(ApkVerifier.Issue.JAR_SIG_ZIP_ENTRY_SIGNERS_MISMATCH, str, getSignerNames(arrayList2), name, getSignerNames(arrayList3));
                        }
                        ArrayList arrayList4 = new ArrayList(getDigestsToVerify(section, "-Digest", i, i2));
                        if (arrayList4.isEmpty()) {
                            result.addError(ApkVerifier.Issue.JAR_SIG_NO_ZIP_ENTRY_DIGEST_IN_MANIFEST, name);
                        } else {
                            MessageDigest[] messageDigestArr = new MessageDigest[arrayList4.size()];
                            for (int i4 = 0; i4 < arrayList4.size(); i4++) {
                                messageDigestArr[i4] = getMessageDigest(((NamedDigest) arrayList4.get(i4)).jcaDigestAlgorithm);
                            }
                            try {
                                try {
                                    set = null;
                                    try {
                                        LocalFileRecord.outputUncompressedData(dataSource, centralDirectoryRecord, j, DataSinks.asDataSink(messageDigestArr));
                                        while (i3 < arrayList4.size()) {
                                            NamedDigest namedDigest = (NamedDigest) arrayList4.get(i3);
                                            byte[] bArrDigest = messageDigestArr[i3].digest();
                                            Iterator it2 = it;
                                            if (!Arrays.equals(namedDigest.digest, bArrDigest)) {
                                                result.addError(ApkVerifier.Issue.JAR_SIG_ZIP_ENTRY_DIGEST_DID_NOT_VERIFY, name, namedDigest.jcaDigestAlgorithm, "META-INF/MANIFEST.MF", Base64.getEncoder().encodeToString(bArrDigest), Base64.getEncoder().encodeToString(namedDigest.digest));
                                            }
                                            i3++;
                                            it = it2;
                                        }
                                    } catch (IOException e) {
                                        e = e;
                                        cia.a("Failed to read entry: ", name, e);
                                        return set;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    set = null;
                                }
                            } catch (ZipFormatException e3) {
                                throw new ApkFormatException("Malformed ZIP entry: " + name, e3);
                            }
                        }
                        arrayList2 = arrayList2;
                        it = it;
                    }
                }
            }
        }
        if (arrayList2 != null) {
            return new HashSet(arrayList2);
        }
        result.addError(ApkVerifier.Issue.JAR_SIG_NO_SIGNED_ZIP_ENTRIES, new Object[0]);
        return Collections.EMPTY_SET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] digest(String str, byte[] bArr) throws NoSuchAlgorithmException {
        return getMessageDigest(str).digest(bArr);
    }
}
