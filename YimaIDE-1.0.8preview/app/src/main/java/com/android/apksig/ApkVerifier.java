package com.android.apksig;

import com.android.apksig.ApkVerifier;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.internal.apk.ApkSigResult;
import com.android.apksig.internal.apk.ApkSignerInfo;
import com.android.apksig.internal.apk.ApkSigningBlockUtils;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.SignatureInfo;
import com.android.apksig.internal.apk.SignatureNotFoundException;
import com.android.apksig.internal.apk.stamp.V2SourceStampVerifier;
import com.android.apksig.internal.apk.v1.V1SchemeVerifier;
import com.android.apksig.internal.apk.v2.V2SchemeVerifier;
import com.android.apksig.internal.apk.v3.V3SchemeVerifier;
import com.android.apksig.internal.apk.v4.V4SchemeVerifier;
import com.android.apksig.internal.zip.CentralDirectoryRecord;
import com.android.apksig.internal.zip.LocalFileRecord;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import com.android.apksig.util.RunnablesExecutor;
import com.android.apksig.zip.ZipFormatException;
import defpackage.pb0;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkVerifier {
    private static final Set<Issue> LINEAGE_RELATED_ISSUES = new HashSet(Arrays.asList(Issue.V3_SIG_MALFORMED_LINEAGE, Issue.V3_INCONSISTENT_LINEAGES, Issue.V3_SIG_POR_DID_NOT_VERIFY, Issue.V3_SIG_POR_CERT_MISMATCH));
    private static final Map<Integer, String> SUPPORTED_APK_SIG_SCHEME_NAMES = loadSupportedApkSigSchemeNames();
    private final DataSource mApkDataSource;
    private final File mApkFile;
    private final int mMaxSdkVersion;
    private final Integer mMinSdkVersion;
    private final File mV4SignatureFile;

    public static class ApkVerificationIssueAdapter {
        static final Map<Integer, Issue> sVerificationIssueIdToIssue;

        static {
            HashMap map = new HashMap();
            sVerificationIssueIdToIssue = map;
            map.put(1, Issue.V2_SIG_MALFORMED_SIGNERS);
            map.put(2, Issue.V2_SIG_NO_SIGNERS);
            map.put(3, Issue.V2_SIG_MALFORMED_SIGNER);
            map.put(4, Issue.V2_SIG_MALFORMED_SIGNATURE);
            map.put(5, Issue.V2_SIG_NO_SIGNATURES);
            map.put(6, Issue.V2_SIG_MALFORMED_CERTIFICATE);
            map.put(7, Issue.V2_SIG_NO_CERTIFICATES);
            map.put(8, Issue.V2_SIG_MALFORMED_DIGEST);
            map.put(9, Issue.V3_SIG_MALFORMED_SIGNERS);
            map.put(10, Issue.V3_SIG_NO_SIGNERS);
            map.put(11, Issue.V3_SIG_MALFORMED_SIGNER);
            map.put(12, Issue.V3_SIG_MALFORMED_SIGNATURE);
            map.put(13, Issue.V3_SIG_NO_SIGNATURES);
            map.put(14, Issue.V3_SIG_MALFORMED_CERTIFICATE);
            map.put(15, Issue.V3_SIG_NO_CERTIFICATES);
            map.put(16, Issue.V3_SIG_MALFORMED_DIGEST);
            map.put(17, Issue.SOURCE_STAMP_NO_SIGNATURE);
            map.put(18, Issue.SOURCE_STAMP_MALFORMED_CERTIFICATE);
            map.put(19, Issue.SOURCE_STAMP_UNKNOWN_SIG_ALGORITHM);
            map.put(20, Issue.SOURCE_STAMP_MALFORMED_SIGNATURE);
            map.put(21, Issue.SOURCE_STAMP_DID_NOT_VERIFY);
            map.put(22, Issue.SOURCE_STAMP_VERIFY_EXCEPTION);
            map.put(23, Issue.SOURCE_STAMP_EXPECTED_DIGEST_MISMATCH);
            map.put(24, Issue.SOURCE_STAMP_SIGNATURE_BLOCK_WITHOUT_CERT_DIGEST);
            map.put(25, Issue.SOURCE_STAMP_CERT_DIGEST_AND_SIG_BLOCK_MISSING);
            map.put(26, Issue.SOURCE_STAMP_NO_SUPPORTED_SIGNATURE);
            map.put(27, Issue.SOURCE_STAMP_CERTIFICATE_MISMATCH_BETWEEN_SIGNATURE_BLOCK_AND_APK);
            map.put(28, Issue.MALFORMED_APK);
            map.put(29, Issue.UNEXPECTED_EXCEPTION);
            map.put(30, Issue.SOURCE_STAMP_SIG_MISSING);
            map.put(31, Issue.SOURCE_STAMP_MALFORMED_ATTRIBUTE);
            map.put(32, Issue.SOURCE_STAMP_UNKNOWN_ATTRIBUTE);
            map.put(33, Issue.SOURCE_STAMP_MALFORMED_LINEAGE);
            map.put(34, Issue.SOURCE_STAMP_POR_CERT_MISMATCH);
            map.put(35, Issue.SOURCE_STAMP_POR_DID_NOT_VERIFY);
            map.put(36, Issue.JAR_SIG_NO_SIGNATURES);
            map.put(37, Issue.JAR_SIG_PARSE_EXCEPTION);
            map.put(38, Issue.SOURCE_STAMP_INVALID_TIMESTAMP);
            map.put(39, Issue.SOURCE_STAMP_SIGNATURE_SCHEME_NOT_AVAILABLE);
        }

        private ApkVerificationIssueAdapter() {
        }

        public static List<IssueWithParams> getIssuesFromVerificationIssues(List<? extends ApkVerificationIssue> list) {
            ArrayList arrayList = new ArrayList(list.size());
            for (ApkVerificationIssue apkVerificationIssue : list) {
                if (apkVerificationIssue instanceof IssueWithParams) {
                    arrayList.add((IssueWithParams) apkVerificationIssue);
                } else {
                    arrayList.add(new IssueWithParams(sVerificationIssueIdToIssue.get(Integer.valueOf(apkVerificationIssue.getIssueId())), apkVerificationIssue.getParams()));
                }
            }
            return arrayList;
        }
    }

    public static class ByteArray {
        private final byte[] mArray;
        private final int mHashCode;

        private ByteArray(byte[] bArr) {
            this.mArray = bArr;
            this.mHashCode = Arrays.hashCode(bArr);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ByteArray)) {
                return false;
            }
            ByteArray byteArray = (ByteArray) obj;
            return hashCode() == byteArray.hashCode() && Arrays.equals(this.mArray, byteArray.mArray);
        }

        public int hashCode() {
            return this.mHashCode;
        }
    }

    public enum Issue {
        JAR_SIG_NO_SIGNATURES("No JAR signatures"),
        JAR_SIG_MAX_SIGNATURES_EXCEEDED("APK Signature Scheme v1 only supports a maximum of %1$d signers, found %2$d"),
        JAR_SIG_NO_SIGNED_ZIP_ENTRIES("No JAR entries covered by JAR signatures"),
        JAR_SIG_DUPLICATE_ZIP_ENTRY("Duplicate entry: %1$s"),
        JAR_SIG_DUPLICATE_MANIFEST_SECTION("Duplicate section in META-INF/MANIFEST.MF: %1$s"),
        JAR_SIG_UNNNAMED_MANIFEST_SECTION("Malformed META-INF/MANIFEST.MF: invidual section #%1$d does not have a name"),
        JAR_SIG_UNNNAMED_SIG_FILE_SECTION("Malformed %1$s: invidual section #%2$d does not have a name"),
        JAR_SIG_NO_MANIFEST("Missing META-INF/MANIFEST.MF"),
        JAR_SIG_MISSING_ZIP_ENTRY_REFERENCED_IN_MANIFEST("%1$s entry referenced by META-INF/MANIFEST.MF not found in the APK"),
        JAR_SIG_NO_ZIP_ENTRY_DIGEST_IN_MANIFEST("No digest for %1$s in META-INF/MANIFEST.MF"),
        JAR_SIG_NO_ZIP_ENTRY_DIGEST_IN_SIG_FILE("No digest for %1$s in %2$s"),
        JAR_SIG_ZIP_ENTRY_NOT_SIGNED("%1$s entry not signed"),
        JAR_SIG_ZIP_ENTRY_SIGNERS_MISMATCH("Entries %1$s and %3$s are signed with different sets of signers : <%2$s> vs <%4$s>"),
        JAR_SIG_ZIP_ENTRY_DIGEST_DID_NOT_VERIFY("%2$s digest of %1$s does not match the digest specified in %3$s. Expected: <%5$s>, actual: <%4$s>"),
        JAR_SIG_MANIFEST_MAIN_SECTION_DIGEST_DID_NOT_VERIFY("%1$s digest of META-INF/MANIFEST.MF main section does not match the digest specified in %2$s. Expected: <%4$s>, actual: <%3$s>"),
        JAR_SIG_MANIFEST_SECTION_DIGEST_DID_NOT_VERIFY("%2$s digest of META-INF/MANIFEST.MF section for %1$s does not match the digest specified in %3$s. Expected: <%5$s>, actual: <%4$s>"),
        JAR_SIG_NO_MANIFEST_DIGEST_IN_SIG_FILE("%1$s does not specify digest of META-INF/MANIFEST.MF. This slows down verification."),
        JAR_SIG_NO_APK_SIG_STRIP_PROTECTION("APK is signed using APK Signature Scheme v2 but these signatures may be stripped without being detected because %1$s does not contain anti-stripping protections."),
        JAR_SIG_MISSING_FILE("Partial JAR signature. Found: %1$s, missing: %2$s"),
        JAR_SIG_VERIFY_EXCEPTION("Failed to verify JAR signature %1$s against %2$s: %3$s"),
        JAR_SIG_UNSUPPORTED_SIG_ALG("JAR signature %1$s uses digest algorithm %5$s and signature algorithm %6$s which is not supported on API Level(s) %4$s for which this APK is being verified"),
        JAR_SIG_PARSE_EXCEPTION("Failed to parse JAR signature %1$s: %2$s"),
        JAR_SIG_MALFORMED_CERTIFICATE("Malformed certificate in JAR signature %1$s: %2$s"),
        JAR_SIG_DID_NOT_VERIFY("JAR signature %1$s did not verify against %2$s"),
        JAR_SIG_NO_SIGNERS("JAR signature %1$s contains no signers"),
        JAR_SIG_DUPLICATE_SIG_FILE_SECTION("Duplicate section in %1$s: %2$s"),
        JAR_SIG_MISSING_VERSION_ATTR_IN_SIG_FILE("Malformed %1$s: missing Signature-Version attribute"),
        JAR_SIG_UNKNOWN_APK_SIG_SCHEME_ID("JAR signature %1$s references unknown APK signature scheme ID: %2$d"),
        JAR_SIG_MISSING_APK_SIG_REFERENCED("JAR signature %1$s indicates the APK is signed using %3$s but no such signature was found. Signature stripped?"),
        JAR_SIG_UNPROTECTED_ZIP_ENTRY("%1$s not protected by signature. Unauthorized modifications to this JAR entry will not be detected. Delete or move the entry outside of META-INF/."),
        JAR_SIG_MISSING("No JAR signature from this signer"),
        NO_SIG_FOR_TARGET_SANDBOX_VERSION("Missing APK Signature Scheme v2 signature required for target sandbox version %1$d"),
        MIN_SIG_SCHEME_FOR_TARGET_SDK_NOT_MET("Target SDK version %1$d requires a minimum of signature scheme v%2$d; the APK is not signed with this or a later signature scheme"),
        V2_SIG_MISSING("No APK Signature Scheme v2 signature from this signer"),
        V2_SIG_MALFORMED_SIGNERS("Malformed list of signers"),
        V2_SIG_MALFORMED_SIGNER("Malformed signer block"),
        V2_SIG_MALFORMED_PUBLIC_KEY("Malformed public key: %1$s"),
        V2_SIG_MALFORMED_CERTIFICATE("Malformed certificate #%2$d: %3$s"),
        V2_SIG_MALFORMED_SIGNATURE("Malformed APK Signature Scheme v2 signature record #%1$d"),
        V2_SIG_MALFORMED_DIGEST("Malformed APK Signature Scheme v2 digest record #%1$d"),
        V2_SIG_MALFORMED_ADDITIONAL_ATTRIBUTE("Malformed additional attribute #%1$d"),
        V2_SIG_UNKNOWN_APK_SIG_SCHEME_ID("APK Signature Scheme v2 signer: %1$s references unknown APK signature scheme ID: %2$d"),
        V2_SIG_MISSING_APK_SIG_REFERENCED("APK Signature Scheme v2 signature %1$s indicates the APK is signed using %2$s but no such signature was found. Signature stripped?"),
        V2_SIG_MAX_SIGNATURES_EXCEEDED("APK Signature Scheme V2 only supports a maximum of %1$d signers, found %2$d"),
        V2_SIG_NO_SIGNERS("No signers in APK Signature Scheme v2 signature"),
        V2_SIG_UNKNOWN_SIG_ALGORITHM("Unknown signature algorithm: %1$#x"),
        V2_SIG_UNKNOWN_ADDITIONAL_ATTRIBUTE("Unknown additional attribute: ID %1$#x"),
        V2_SIG_VERIFY_EXCEPTION("Failed to verify %1$s signature: %2$s"),
        V2_SIG_DID_NOT_VERIFY("%1$s signature over signed-data did not verify"),
        V2_SIG_NO_SIGNATURES("No signatures"),
        V2_SIG_NO_SUPPORTED_SIGNATURES("No supported signatures: %1$s"),
        V2_SIG_NO_CERTIFICATES("No certificates"),
        V2_SIG_PUBLIC_KEY_MISMATCH_BETWEEN_CERTIFICATE_AND_SIGNATURES_RECORD("Public key mismatch between certificate and signature record: <%1$s> vs <%2$s>"),
        V2_SIG_SIG_ALG_MISMATCH_BETWEEN_SIGNATURES_AND_DIGESTS_RECORDS("Signature algorithms mismatch between signatures and digests records: %1$s vs %2$s"),
        V2_SIG_APK_DIGEST_DID_NOT_VERIFY("APK integrity check failed. %1$s digest mismatch. Expected: <%2$s>, actual: <%3$s>"),
        V3_SIG_MALFORMED_SIGNERS("Malformed list of signers"),
        V3_SIG_MALFORMED_SIGNER("Malformed signer block"),
        V3_SIG_MALFORMED_PUBLIC_KEY("Malformed public key: %1$s"),
        V3_SIG_MALFORMED_CERTIFICATE("Malformed certificate #%2$d: %3$s"),
        V3_SIG_MALFORMED_SIGNATURE("Malformed APK Signature Scheme v3 signature record #%1$d"),
        V3_SIG_MALFORMED_DIGEST("Malformed APK Signature Scheme v3 digest record #%1$d"),
        V3_SIG_MALFORMED_ADDITIONAL_ATTRIBUTE("Malformed additional attribute #%1$d"),
        V3_SIG_NO_SIGNERS("No signers in APK Signature Scheme v3 signature"),
        V3_SIG_MULTIPLE_SIGNERS("Multiple APK Signature Scheme v3 signatures found for a single  platform version."),
        V3_SIG_MULTIPLE_PAST_SIGNERS("Multiple signatures found for pre-v3 signing with an APK  Signature Scheme v3 signer.  Only one allowed."),
        V3_SIG_PAST_SIGNERS_MISMATCH("v3 signer differs from v1/v2 signer without proper signing certificate lineage."),
        V3_SIG_UNKNOWN_SIG_ALGORITHM("Unknown signature algorithm: %1$#x"),
        V3_SIG_UNKNOWN_ADDITIONAL_ATTRIBUTE("Unknown additional attribute: ID %1$#x"),
        V3_SIG_VERIFY_EXCEPTION("Failed to verify %1$s signature: %2$s"),
        V3_SIG_INVALID_SDK_VERSIONS("Invalid SDK Version parameter(s) encountered in APK Signature scheme v3 signature: minSdkVersion %1$s maxSdkVersion: %2$s"),
        V3_SIG_DID_NOT_VERIFY("%1$s signature over signed-data did not verify"),
        V3_SIG_NO_SIGNATURES("No signatures"),
        V3_SIG_NO_SUPPORTED_SIGNATURES("No supported signatures"),
        V3_SIG_NO_CERTIFICATES("No certificates"),
        V3_MIN_SDK_VERSION_MISMATCH_BETWEEN_SIGNER_AND_SIGNED_DATA_RECORD("minSdkVersion mismatch between signed data and signature record: <%1$s> vs <%2$s>"),
        V3_MAX_SDK_VERSION_MISMATCH_BETWEEN_SIGNER_AND_SIGNED_DATA_RECORD("maxSdkVersion mismatch between signed data and signature record: <%1$s> vs <%2$s>"),
        V3_SIG_PUBLIC_KEY_MISMATCH_BETWEEN_CERTIFICATE_AND_SIGNATURES_RECORD("Public key mismatch between certificate and signature record: <%1$s> vs <%2$s>"),
        V3_SIG_SIG_ALG_MISMATCH_BETWEEN_SIGNATURES_AND_DIGESTS_RECORDS("Signature algorithms mismatch between signatures and digests records: %1$s vs %2$s"),
        V3_SIG_APK_DIGEST_DID_NOT_VERIFY("APK integrity check failed. %1$s digest mismatch. Expected: <%2$s>, actual: <%3$s>"),
        V3_SIG_POR_DID_NOT_VERIFY("SigningCertificateLineage attribute containd a proof-of-rotation record with signature(s) that did not verify."),
        V3_SIG_MALFORMED_LINEAGE("Failed to parse the SigningCertificateLineage structure in the APK Signature Scheme v3 signature's additional attributes section."),
        V3_SIG_POR_CERT_MISMATCH("APK signing certificate differs from the associated certificate found in the signer's SigningCertificateLineage."),
        V3_INCONSISTENT_SDK_VERSIONS("APK Signature Scheme v3 signers supported min/max SDK versions are not continuous."),
        V3_MISSING_SDK_VERSIONS("APK Signature Scheme v3 signers supported min/max SDK versions do not cover the entire desired range.  Found min:  %1$s max %2$s"),
        V3_INCONSISTENT_LINEAGES("SigningCertificateLineages targeting different platform versions using APK Signature Scheme v3 are not all a part of the same overall lineage."),
        V31_BLOCK_MISSING("The v3 signer indicates key rotation should be supported starting from SDK version %1$s, but a v3.1 block was not found"),
        V31_ROTATION_MIN_SDK_MISMATCH("The v3 signer indicates key rotation should be supported starting from SDK version %1$s, but the v3.1 block targets %2$s for rotation"),
        V31_ROTATION_MIN_SDK_ATTR_MISSING("APK supports key rotation starting from SDK version %1$s, but the v3 signer does not contain the attribute to detect if this signature is stripped"),
        V31_BLOCK_FOUND_WITHOUT_V3_BLOCK("The APK contains a v3.1 signing block without a v3.0 base block"),
        V31_ROTATION_TARGETS_DEV_RELEASE_ATTR_ON_V3_SIGNER("The rotation-targets-dev-release attribute is only supported on v3.1 signers; this attribute will be ignored by the platform in a v3.0 signer"),
        APK_SIG_BLOCK_UNKNOWN_ENTRY_ID("APK Signing Block contains unknown entry: ID %1$#x"),
        V4_SIG_MALFORMED_SIGNERS("V4 signature has malformed signer block"),
        V4_SIG_UNKNOWN_SIG_ALGORITHM("V4 signature has unknown signing algorithm: %1$#x"),
        V4_SIG_NO_SIGNATURES("V4 signature has no signature found"),
        V4_SIG_NO_SUPPORTED_SIGNATURES("V4 signature has no supported signature"),
        V4_SIG_DID_NOT_VERIFY("%1$s signature over signed-data did not verify"),
        V4_SIG_VERIFY_EXCEPTION("Failed to verify %1$s signature: %2$s"),
        V4_SIG_MALFORMED_PUBLIC_KEY("Malformed public key: %1$s"),
        V4_SIG_MALFORMED_CERTIFICATE("V4 signature has malformed certificate"),
        V4_SIG_NO_CERTIFICATE("V4 signature has no certificate"),
        V4_SIG_PUBLIC_KEY_MISMATCH_BETWEEN_CERTIFICATE_AND_SIGNATURES_RECORD("V4 signature has mismatched certificate and signature: <%1$s> vs <%2$s>"),
        V4_SIG_APK_ROOT_DID_NOT_VERIFY("V4 signature's hash tree root (content digest) did not verity"),
        V4_SIG_APK_TREE_DID_NOT_VERIFY("V4 signature's hash tree did not verity"),
        V4_SIG_MULTIPLE_SIGNERS("V4 signature only supports one signer"),
        V41_SIG_NEEDS_TWO_SIGNERS("V4.1 signature requires two signers"),
        V4_SIG_V2_V3_SIGNERS_MISMATCH("V4 signature and V2/V3 signature have mismatched certificates"),
        V4_SIG_V2_V3_DIGESTS_MISMATCH("V4 signature and V%1$d signature have mismatched digests, V%1$d digest: %2$s, V4 digest: %3$s"),
        V4_SIG_UNEXPECTED_DIGESTS("V4 signature does not have the expected number of digests, found %1$d"),
        V4_SIG_VERSION_NOT_CURRENT("V4 signature format version %1$d is different from the tool's current version %2$d"),
        SOURCE_STAMP_CERT_DIGEST_AND_SIG_BLOCK_MISSING("Neither the source stamp certificate digest file nor the signature block are present in the APK"),
        SOURCE_STAMP_SIG_MISSING("No SourceStamp signature"),
        SOURCE_STAMP_MALFORMED_CERTIFICATE("Malformed certificate: %1$s"),
        SOURCE_STAMP_MALFORMED_SIGNATURE("Malformed SourceStamp signature"),
        SOURCE_STAMP_UNKNOWN_SIG_ALGORITHM("Unknown signature algorithm: %1$#x"),
        SOURCE_STAMP_VERIFY_EXCEPTION("Failed to verify %1$s signature: %2$s"),
        SOURCE_STAMP_DID_NOT_VERIFY("%1$s signature over signed-data did not verify"),
        SOURCE_STAMP_NO_SIGNATURE("No signature"),
        SOURCE_STAMP_NO_SUPPORTED_SIGNATURE("Signature(s) {%1$s} not supported: %2$s"),
        SOURCE_STAMP_CERTIFICATE_MISMATCH_BETWEEN_SIGNATURE_BLOCK_AND_APK("Certificate mismatch between SourceStamp block in APK signing block and SourceStamp file in APK: <%1$s> vs <%2$s>"),
        SOURCE_STAMP_SIGNATURE_BLOCK_WITHOUT_CERT_DIGEST("A source stamp signature block was found without a corresponding certificate digest in the APK"),
        SOURCE_STAMP_EXPECTED_DIGEST_MISMATCH("The source stamp certificate digest in the APK, %1$s, does not match the expected digest, %2$s"),
        SOURCE_STAMP_MALFORMED_ATTRIBUTE("Malformed stamp attribute #%1$d"),
        SOURCE_STAMP_UNKNOWN_ATTRIBUTE("Unknown stamp attribute: ID %1$#x"),
        SOURCE_STAMP_MALFORMED_LINEAGE("Failed to parse the SigningCertificateLineage structure in the source stamp attributes section."),
        SOURCE_STAMP_POR_CERT_MISMATCH("APK signing certificate differs from the associated certificate found in the signer's SigningCertificateLineage."),
        SOURCE_STAMP_POR_DID_NOT_VERIFY("Source stamp SigningCertificateLineage attribute contains a proof-of-rotation record with signature(s) that did not verify."),
        SOURCE_STAMP_INVALID_TIMESTAMP("The source stamp timestamp attribute has an invalid value: %1$d"),
        SOURCE_STAMP_SIGNATURE_SCHEME_NOT_AVAILABLE("No digests are available in the source stamp for signature scheme: %1$d"),
        MALFORMED_APK("Malformed APK; the following exception was caught when attempting to parse the APK: %1$s"),
        UNEXPECTED_EXCEPTION("An unexpected exception was caught when verifying the signature: %1$s");

        private final String mFormat;

        Issue(String str) {
            this.mFormat = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getFormat() {
            return this.mFormat;
        }
    }

    public static class IssueWithParams extends ApkVerificationIssue {
        private final Issue mIssue;
        private final Object[] mParams;

        public IssueWithParams(Issue issue, Object[] objArr) {
            super(issue.mFormat, objArr);
            this.mIssue = issue;
            this.mParams = objArr;
        }

        public Issue getIssue() {
            return this.mIssue;
        }

        @Override // com.android.apksig.ApkVerificationIssue
        public Object[] getParams() {
            return (Object[]) this.mParams.clone();
        }

        @Override // com.android.apksig.ApkVerificationIssue
        public String toString() {
            return String.format(this.mIssue.getFormat(), this.mParams);
        }
    }

    private ApkVerifier(File file, DataSource dataSource, File file2, Integer num, int i) {
        this.mApkFile = file;
        this.mApkDataSource = dataSource;
        this.mV4SignatureFile = file2;
        this.mMinSdkVersion = num;
        this.mMaxSdkVersion = i;
    }

    private static void checkV4Certificate(List<X509Certificate> list, List<X509Certificate> list2, Result result) {
        try {
            if (Arrays.equals(list2.get(0).getEncoded(), list.get(0).getEncoded())) {
                return;
            }
            result.addError(Issue.V4_SIG_V2_V3_SIGNERS_MISMATCH, new Object[0]);
        } catch (CertificateEncodingException e) {
            g3c.a("Failed to encode APK signer cert", e);
        }
    }

    private static void checkV4Signer(List<Result.V3SchemeSignerInfo> list, List<X509Certificate> list2, byte[] bArr, Result result) {
        if (list.size() != 1) {
            result.addError(Issue.V4_SIG_MULTIPLE_SIGNERS, new Object[0]);
        }
        checkV4Certificate(list2, list.get(0).mCerts, result);
        byte[] bArrPickBestDigestForV4 = pickBestDigestForV4(list.get(0).getContentDigests());
        if (Arrays.equals(bArr, bArrPickBestDigestForV4)) {
            return;
        }
        result.addError(Issue.V4_SIG_V2_V3_DIGESTS_MISMATCH, 3, ApkSigningBlockUtils.toHex(bArrPickBestDigestForV4), ApkSigningBlockUtils.toHex(bArr));
    }

    private static void collectApkContentDigests(List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> list, Map<ContentDigestAlgorithm, byte[]> map) {
        for (ApkSigningBlockUtils.Result.SignerInfo.ContentDigest contentDigest : list) {
            SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(contentDigest.getSignatureAlgorithmId());
            if (signatureAlgorithmFindById != null) {
                map.put(signatureAlgorithmFindById.getContentDigestAlgorithm(), contentDigest.getValue());
            }
        }
    }

    public static boolean compareDigests(Map<ContentDigestAlgorithm, byte[]> map, Map<ContentDigestAlgorithm, byte[]> map2) throws NoSuchAlgorithmException {
        HashSet<ContentDigestAlgorithm> hashSet = new HashSet(map.keySet());
        hashSet.retainAll(map2.keySet());
        if (hashSet.isEmpty()) {
            return false;
        }
        for (ContentDigestAlgorithm contentDigestAlgorithm : hashSet) {
            if (!Arrays.equals(map.get(contentDigestAlgorithm), map2.get(contentDigestAlgorithm))) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsLineageErrors(Result result) {
        if (result.containsErrors()) {
            return result.getAllErrors().stream().map(new Function() { // from class: rb0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ApkVerifier.IssueWithParams) obj).getIssue();
                }
            }).anyMatch(new Predicate() { // from class: sb0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ApkVerifier.LINEAGE_RELATED_ISSUES.contains((ApkVerifier.Issue) obj);
                }
            });
        }
        return false;
    }

    private static Result createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus sourceStampVerificationStatus, Issue issue, Object... objArr) {
        Result result = new Result();
        result.addError(issue, objArr);
        return mergeSourceStampResult(sourceStampVerificationStatus, result);
    }

    private static ByteBuffer getAndroidManifestFromApk(DataSource dataSource, ApkUtils.ZipSections zipSections) throws IOException, ApkFormatException {
        try {
            return ApkSigner.getAndroidManifestFromApk(V1SchemeVerifier.parseZipCentralDirectory(dataSource, zipSections), dataSource.slice(0L, zipSections.getZipCentralDirectoryOffset()));
        } catch (ZipFormatException e) {
            throw new ApkFormatException("Failed to read AndroidManifest.xml", e);
        }
    }

    private static Map<ContentDigestAlgorithm, byte[]> getApkContentDigestFromV1SigningScheme(List<CentralDirectoryRecord> list, DataSource dataSource, ApkUtils.ZipSections zipSections) throws IOException, ApkFormatException {
        CentralDirectoryRecord next;
        EnumMap enumMap = new EnumMap(ContentDigestAlgorithm.class);
        Iterator<CentralDirectoryRecord> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!"META-INF/MANIFEST.MF".equals(next.getName()));
        if (next == null) {
            return enumMap;
        }
        try {
            enumMap.put(ContentDigestAlgorithm.SHA256, ApkUtils.computeSha256DigestBytes(LocalFileRecord.getUncompressedData(dataSource, next, zipSections.getZipCentralDirectoryOffset())));
            return enumMap;
        } catch (ZipFormatException e) {
            throw new ApkFormatException("Failed to read APK", e);
        }
    }

    private static ApkSigningBlockUtils.Result getApkContentDigests(DataSource dataSource, ApkUtils.ZipSections zipSections, Set<Integer> set, Map<Integer, String> map, Map<Integer, Map<ContentDigestAlgorithm, byte[]>> map2, int i, int i2, int i3) throws NoSuchAlgorithmException, IOException {
        if (i != 2 && i != 3 && i != 31) {
            return null;
        }
        ApkSigningBlockUtils.Result result = new ApkSigningBlockUtils.Result(i);
        try {
            SignatureInfo signatureInfoFindSignature = ApkSigningBlockUtils.findSignature(dataSource, zipSections, i != 3 ? i != 31 ? 1896449818 : 462663009 : -262969152, result);
            set.add(Integer.valueOf(i));
            HashSet hashSet = new HashSet(1);
            if (i == 2) {
                V2SchemeVerifier.parseSigners(signatureInfoFindSignature.signatureBlock, hashSet, map, set, i2, i3, result);
            } else {
                V3SchemeVerifier.parseSigners(signatureInfoFindSignature.signatureBlock, hashSet, result);
            }
            EnumMap enumMap = new EnumMap(ContentDigestAlgorithm.class);
            Iterator<ApkSigningBlockUtils.Result.SignerInfo> it = result.signers.iterator();
            while (it.hasNext()) {
                for (ApkSigningBlockUtils.Result.SignerInfo.ContentDigest contentDigest : it.next().contentDigests) {
                    SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(contentDigest.getSignatureAlgorithmId());
                    if (signatureAlgorithmFindById != null) {
                        enumMap.put(signatureAlgorithmFindById.getContentDigestAlgorithm(), contentDigest.getValue());
                    }
                }
            }
            map2.put(Integer.valueOf(i), enumMap);
            return result;
        } catch (ApkSigningBlockUtils.SignatureNotFoundException unused) {
            return null;
        }
    }

    private static Map<ContentDigestAlgorithm, byte[]> getApkContentDigestsFromSigningSchemeResult(ApkSigningBlockUtils.Result result) {
        HashMap map = new HashMap();
        Iterator<ApkSigningBlockUtils.Result.SignerInfo> it = result.signers.iterator();
        while (it.hasNext()) {
            collectApkContentDigests(it.next().contentDigests, map);
        }
        return map;
    }

    private static void getContentDigests(List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> list, Map<ContentDigestAlgorithm, byte[]> map) {
        for (ApkSigningBlockUtils.Result.SignerInfo.ContentDigest contentDigest : list) {
            SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(contentDigest.getSignatureAlgorithmId());
            if (signatureAlgorithmFindById != null) {
                map.put(signatureAlgorithmFindById.getContentDigestAlgorithm(), contentDigest.getValue());
            }
        }
    }

    public static Map<ContentDigestAlgorithm, byte[]> getContentDigestsFromResult(Result result, int i) {
        HashMap map = new HashMap();
        if (i == 2 || i == 3 || i == 31) {
            if (i == 2) {
                Iterator<Result.V2SchemeSignerInfo> it = result.getV2SchemeSigners().iterator();
                while (it.hasNext()) {
                    getContentDigests(it.next().getContentDigests(), map);
                }
            } else if (i == 3) {
                Iterator<Result.V3SchemeSignerInfo> it2 = result.getV3SchemeSigners().iterator();
                while (it2.hasNext()) {
                    getContentDigests(it2.next().getContentDigests(), map);
                }
            } else if (i == 31) {
                Iterator<Result.V3SchemeSignerInfo> it3 = result.getV31SchemeSigners().iterator();
                while (it3.hasNext()) {
                    getContentDigests(it3.next().getContentDigests(), map);
                }
            }
        }
        return map;
    }

    public static SigningCertificateLineage getLineageFromResult(Result result, int i, int i2) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateEncodingException {
        if ((i2 != 3 && i2 != 31) || containsLineageErrors(result)) {
            return null;
        }
        List<Result.V3SchemeSignerInfo> v3SchemeSigners = i2 == 3 ? result.getV3SchemeSigners() : result.getV31SchemeSigners();
        if (v3SchemeSigners.isEmpty()) {
            return null;
        }
        Result.V3SchemeSignerInfo v3SchemeSignerInfo = v3SchemeSigners.get(0);
        SigningCertificateLineage signingCertificateLineage = v3SchemeSignerInfo.mSigningCertificateLineage;
        if (signingCertificateLineage != null || v3SchemeSignerInfo.getCertificate() == null) {
            return signingCertificateLineage;
        }
        try {
            return new SigningCertificateLineage.Builder(new SigningCertificateLineage.SignerConfig.Builder((KeyConfig) null, v3SchemeSignerInfo.getCertificate()).build()).build();
        } catch (Exception unused) {
            return null;
        }
    }

    private static int getMinimumSignatureSchemeVersionForTargetSdk(int i) {
        return i >= 30 ? 2 : 1;
    }

    public static Result getSigningBlockResult(DataSource dataSource, ApkUtils.ZipSections zipSections, int i, int i2) throws NoSuchAlgorithmException, IOException {
        HashMap map = new HashMap();
        Map<Integer, String> supportedSchemeNames = getSupportedSchemeNames(i);
        HashSet hashSet = new HashSet(2);
        Result result = new Result();
        result.mergeFrom(getApkContentDigests(dataSource, zipSections, hashSet, supportedSchemeNames, map, i2, i, i));
        return result;
    }

    private static Map<Integer, String> getSupportedSchemeNames(int i) {
        if (i >= 28) {
            return SUPPORTED_APK_SIG_SCHEME_NAMES;
        }
        if (i < 24) {
            return Collections.EMPTY_MAP;
        }
        HashMap map = new HashMap(1);
        map.put(2, SUPPORTED_APK_SIG_SCHEME_NAMES.get(2));
        return map;
    }

    private static Map<Integer, String> loadSupportedApkSigSchemeNames() {
        HashMap map = new HashMap(2);
        map.put(2, "APK Signature Scheme v2");
        map.put(3, "APK Signature Scheme v3");
        return map;
    }

    private static Result mergeSourceStampResult(Result.SourceStampInfo.SourceStampVerificationStatus sourceStampVerificationStatus, Result result) {
        result.mSourceStampInfo = new Result.SourceStampInfo(sourceStampVerificationStatus);
        return result;
    }

    private static byte[] pickBestDigestForV4(List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> list) {
        HashMap map = new HashMap();
        collectApkContentDigests(list, map);
        return ApkSigningBlockUtils.pickBestDigestForV4(map);
    }

    /* JADX WARN: Code duplicated, block: B:169:0x03c5  */
    /* JADX WARN: Code duplicated, block: B:171:0x03d6  */
    /* JADX WARN: Code duplicated, block: B:173:0x03dc  */
    /* JADX WARN: Code duplicated, block: B:174:0x03de  */
    /* JADX WARN: Code duplicated, block: B:177:0x03e5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:178:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:180:0x03eb  */
    /* JADX WARN: Code duplicated, block: B:183:0x03f4  */
    /* JADX WARN: Code duplicated, block: B:185:0x0408  */
    /* JADX WARN: Code duplicated, block: B:187:0x0418  */
    /* JADX WARN: Code duplicated, block: B:191:0x044e  */
    /* JADX WARN: Code duplicated, block: B:193:0x0454  */
    /* JADX WARN: Code duplicated, block: B:195:0x045a  */
    /* JADX WARN: Code duplicated, block: B:196:0x0463  */
    /* JADX WARN: Code duplicated, block: B:199:0x046e  */
    /* JADX WARN: Code duplicated, block: B:202:0x04a0  */
    /* JADX WARN: Code duplicated, block: B:203:0x04b2  */
    /* JADX WARN: Code duplicated, block: B:209:0x04c1  */
    /* JADX WARN: Code duplicated, block: B:229:0x0505  */
    /* JADX WARN: Code duplicated, block: B:231:0x050e  */
    /* JADX WARN: Code duplicated, block: B:232:0x0525  */
    /* JADX WARN: Code duplicated, block: B:234:0x052b  */
    /* JADX WARN: Code duplicated, block: B:235:0x0542  */
    /* JADX WARN: Code duplicated, block: B:237:0x0548  */
    /* JADX WARN: Code duplicated, block: B:240:0x0556 A[LOOP:5: B:238:0x0550->B:240:0x0556, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:241:0x0564  */
    /* JADX WARN: Code duplicated, block: B:243:0x056a  */
    /* JADX WARN: Code duplicated, block: B:246:0x0578 A[LOOP:6: B:244:0x0572->B:246:0x0578, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:248:0x0587  */
    /* JADX WARN: Code duplicated, block: B:271:0x04ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x042d, code lost:
    
        if (r3.isEmpty() != false) goto L247;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Result verify(DataSource dataSource) throws NoSuchAlgorithmException, IOException, ApkFormatException {
        ApkUtils.ZipSections zipSections;
        Map<Integer, String> map;
        HashSet hashSet;
        ByteBuffer androidManifestFromApk;
        DataSource dataSource2;
        ApkUtils.ZipSections zipSections2;
        int i;
        int i2;
        Iterator<Result.V1SchemeSignerInfo> it;
        Iterator<Result.V2SchemeSignerInfo> it2;
        int targetSdkVersionFromBinaryAndroidManifest;
        int minimumSignatureSchemeVersionForTargetSdk;
        byte[] value;
        int i3;
        List<Result.V2SchemeSignerInfo> v2SchemeSigners;
        byte[] bArrPickBestDigestForV4;
        boolean zIsVerifiedUsingV31Scheme;
        int i4;
        List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> contentDigests;
        Issue issue;
        int i5;
        X509Certificate x509Certificate;
        CentralDirectoryRecord next;
        int iOrElse;
        Map<Integer, String> map2;
        int i6 = this.mMaxSdkVersion;
        try {
            ApkUtils.ZipSections zipSectionsFindZipSections = ApkUtils.findZipSections(dataSource);
            int iVerifyAndGetMinSdkVersion = verifyAndGetMinSdkVersion(dataSource, zipSectionsFindZipSections);
            Result result = new Result();
            HashMap map3 = new HashMap();
            Map<Integer, String> supportedSchemeNames = getSupportedSchemeNames(i6);
            HashSet hashSet2 = new HashSet(2);
            if (i6 >= 24) {
                RunnablesExecutor runnablesExecutor = RunnablesExecutor.SINGLE_THREADED;
                if (i6 >= 33) {
                    try {
                        ApkSigningBlockUtils.Result resultVerify = new V3SchemeVerifier.Builder(dataSource, zipSectionsFindZipSections, Math.max(iVerifyAndGetMinSdkVersion, 33), i6).setRunnablesExecutor(runnablesExecutor).setBlockId(462663009).build().verify();
                        hashSet2.add(31);
                        iOrElse = resultVerify.signers.stream().mapToInt(new ToIntFunction() { // from class: qb0
                            @Override // java.util.function.ToIntFunction
                            public final int applyAsInt(Object obj) {
                                return ((ApkSigningBlockUtils.Result.SignerInfo) obj).minSdkVersion;
                            }
                        }).min().orElse(0);
                        try {
                            result.mergeFrom(resultVerify);
                            map3.put(31, getApkContentDigestsFromSigningSchemeResult(resultVerify));
                        } catch (ApkSigningBlockUtils.SignatureNotFoundException unused) {
                        }
                    } catch (ApkSigningBlockUtils.SignatureNotFoundException unused2) {
                        iOrElse = 0;
                    }
                    if (!result.containsErrors()) {
                    }
                    return result;
                }
                iOrElse = 0;
                if (i6 >= 28) {
                    try {
                        map2 = supportedSchemeNames;
                        try {
                            V3SchemeVerifier.Builder blockId = new V3SchemeVerifier.Builder(dataSource, zipSectionsFindZipSections, Math.max(iVerifyAndGetMinSdkVersion, 28), i6).setRunnablesExecutor(runnablesExecutor).setBlockId(-262969152);
                            if (iOrElse > 0) {
                                blockId.setRotationMinSdkVersion(iOrElse);
                            }
                            ApkSigningBlockUtils.Result resultVerify2 = blockId.build().verify();
                            hashSet2.add(3);
                            result.mergeFrom(resultVerify2);
                            map3.put(3, getApkContentDigestsFromSigningSchemeResult(resultVerify2));
                        } catch (ApkSigningBlockUtils.SignatureNotFoundException unused3) {
                            if (hashSet2.contains(31)) {
                                result.addError(Issue.V31_BLOCK_FOUND_WITHOUT_V3_BLOCK, new Object[0]);
                            }
                        }
                    } catch (ApkSigningBlockUtils.SignatureNotFoundException unused4) {
                        map2 = supportedSchemeNames;
                    }
                    if (!result.containsErrors()) {
                    }
                    return result;
                }
                map2 = supportedSchemeNames;
                if (iVerifyAndGetMinSdkVersion < 28 || hashSet2.isEmpty()) {
                    try {
                        zipSections = zipSectionsFindZipSections;
                        map = map2;
                        try {
                            ApkSigningBlockUtils.Result resultVerify3 = V2SchemeVerifier.verify(runnablesExecutor, dataSource, zipSections, map, hashSet2, Math.max(iVerifyAndGetMinSdkVersion, 24), i6);
                            hashSet = hashSet2;
                            i6 = i6;
                            try {
                                hashSet.add(2);
                                result.mergeFrom(resultVerify3);
                                map3.put(2, getApkContentDigestsFromSigningSchemeResult(resultVerify3));
                            } catch (ApkSigningBlockUtils.SignatureNotFoundException unused5) {
                            }
                        } catch (ApkSigningBlockUtils.SignatureNotFoundException unused6) {
                            hashSet = hashSet2;
                            i6 = i6;
                        }
                    } catch (ApkSigningBlockUtils.SignatureNotFoundException unused7) {
                        zipSections = zipSectionsFindZipSections;
                        hashSet = hashSet2;
                        map = map2;
                    }
                    if (!result.containsErrors()) {
                    }
                    return result;
                }
                zipSections = zipSectionsFindZipSections;
                hashSet = hashSet2;
                map = map2;
                File file = this.mV4SignatureFile;
                if (file != null) {
                    ApkSigningBlockUtils.Result resultVerify4 = V4SchemeVerifier.verify(dataSource, file);
                    hashSet.add(4);
                    result.mergeFrom(resultVerify4);
                    if (!result.containsErrors()) {
                    }
                }
                return result;
            }
            zipSections = zipSectionsFindZipSections;
            map = supportedSchemeNames;
            hashSet = hashSet2;
            if (i6 >= 26) {
                ByteBuffer androidManifestFromApk2 = getAndroidManifestFromApk(dataSource, zipSections);
                int targetSandboxVersionFromBinaryAndroidManifest = ApkUtils.getTargetSandboxVersionFromBinaryAndroidManifest(androidManifestFromApk2.slice());
                if (targetSandboxVersionFromBinaryAndroidManifest > 1 && hashSet.isEmpty()) {
                    result.addError(Issue.NO_SIG_FOR_TARGET_SANDBOX_VERSION, Integer.valueOf(targetSandboxVersionFromBinaryAndroidManifest));
                }
                androidManifestFromApk = androidManifestFromApk2;
            } else {
                androidManifestFromApk = null;
            }
            List<CentralDirectoryRecord> zipCentralDirectory = V1SchemeVerifier.parseZipCentralDirectory(dataSource, zipSections);
            if (iVerifyAndGetMinSdkVersion < 24 || hashSet.isEmpty()) {
                dataSource2 = dataSource;
                zipSections2 = zipSections;
                Map<Integer, String> map4 = map;
                i = iVerifyAndGetMinSdkVersion;
                result.mergeFrom(V1SchemeVerifier.verify(dataSource2, zipSections2, map4, hashSet, i, i6));
                map3.put(1, getApkContentDigestFromV1SigningScheme(zipCentralDirectory, dataSource2, zipSections2));
            } else {
                dataSource2 = dataSource;
                zipSections2 = zipSections;
                i = iVerifyAndGetMinSdkVersion;
            }
            if (!result.containsErrors()) {
                try {
                    Iterator<CentralDirectoryRecord> it3 = zipCentralDirectory.iterator();
                    do {
                        if (!it3.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it3.next();
                    } while (!"stamp-cert-sha256".equals(next.getName()));
                    if (next != null) {
                        result.mergeFrom(V2SourceStampVerifier.verify(dataSource2, zipSections2, LocalFileRecord.getUncompressedData(dataSource2, next, zipSections2.getZipCentralDirectoryOffset()), map3, Math.max(i, 30), i6));
                    }
                } catch (SignatureNotFoundException unused8) {
                    result.addWarning(Issue.SOURCE_STAMP_SIG_MISSING, new Object[0]);
                } catch (ZipFormatException e) {
                    throw new ApkFormatException("Failed to read APK", e);
                }
                if (!result.containsErrors()) {
                    if (result.isVerifiedUsingV1Scheme() && result.isVerifiedUsingV2Scheme()) {
                        ArrayList<Result.V1SchemeSignerInfo> arrayList = new ArrayList(result.getV1SchemeSigners());
                        ArrayList<Result.V2SchemeSignerInfo> arrayList2 = new ArrayList(result.getV2SchemeSigners());
                        ArrayList arrayList3 = new ArrayList();
                        ArrayList arrayList4 = new ArrayList();
                        for (Result.V1SchemeSignerInfo v1SchemeSignerInfo : arrayList) {
                            try {
                                arrayList3.add(new ByteArray(v1SchemeSignerInfo.getCertificate().getEncoded()));
                            } catch (CertificateEncodingException e2) {
                                throw new IllegalStateException("Failed to encode JAR signer " + v1SchemeSignerInfo.getName() + " certs", e2);
                            }
                        }
                        for (Result.V2SchemeSignerInfo v2SchemeSignerInfo : arrayList2) {
                            try {
                                arrayList4.add(new ByteArray(v2SchemeSignerInfo.getCertificate().getEncoded()));
                            } catch (CertificateEncodingException e3) {
                                throw new IllegalStateException("Failed to encode APK Signature Scheme v2 signer (index: " + v2SchemeSignerInfo.getIndex() + ") certs", e3);
                            }
                        }
                        for (int i7 = 0; i7 < arrayList3.size(); i7++) {
                            if (!arrayList4.contains((ByteArray) arrayList3.get(i7))) {
                                ((Result.V1SchemeSignerInfo) arrayList.get(i7)).addError(Issue.V2_SIG_MISSING, new Object[0]);
                                break;
                            }
                        }
                        for (int i8 = 0; i8 < arrayList4.size(); i8++) {
                            if (!arrayList3.contains((ByteArray) arrayList4.get(i8))) {
                                ((Result.V2SchemeSignerInfo) arrayList2.get(i8)).addError(Issue.JAR_SIG_MISSING, new Object[0]);
                                break;
                            }
                        }
                    }
                    if (result.isVerifiedUsingV3Scheme() && (result.isVerifiedUsingV1Scheme() || result.isVerifiedUsingV2Scheme())) {
                        SigningCertificateLineage signingCertificateLineage = result.getSigningCertificateLineage();
                        if (result.isVerifiedUsingV1Scheme()) {
                            List<Result.V1SchemeSignerInfo> v1SchemeSigners = result.getV1SchemeSigners();
                            if (v1SchemeSigners.size() != 1) {
                                i5 = 0;
                                result.addError(Issue.V3_SIG_MULTIPLE_PAST_SIGNERS, new Object[0]);
                            } else {
                                i5 = 0;
                            }
                            x509Certificate = (X509Certificate) v1SchemeSigners.get(i5).mCertChain.get(i5);
                        } else {
                            i5 = 0;
                            List<Result.V2SchemeSignerInfo> v2SchemeSigners2 = result.getV2SchemeSigners();
                            if (v2SchemeSigners2.size() != 1) {
                                result.addError(Issue.V3_SIG_MULTIPLE_PAST_SIGNERS, new Object[0]);
                            }
                            x509Certificate = (X509Certificate) v2SchemeSigners2.get(0).mCerts.get(0);
                        }
                        if (signingCertificateLineage == null) {
                            List<Result.V3SchemeSignerInfo> v3SchemeSigners = result.getV3SchemeSigners();
                            if (v3SchemeSigners.size() != 1) {
                                result.addError(Issue.V3_SIG_MULTIPLE_SIGNERS, new Object[i5]);
                            }
                            try {
                                if (!Arrays.equals(x509Certificate.getEncoded(), ((X509Certificate) v3SchemeSigners.get(i5).mCerts.get(i5)).getEncoded())) {
                                    result.addError(Issue.V3_SIG_PAST_SIGNERS_MISMATCH, new Object[i5]);
                                }
                            } catch (CertificateEncodingException e4) {
                                g3c.a("Failed to encode APK Signature Scheme v3 signer cert", e4);
                                return null;
                            }
                        } else {
                            try {
                                if (signingCertificateLineage.getSubLineage(x509Certificate).size() != 1) {
                                    result.addError(Issue.V3_SIG_PAST_SIGNERS_MISMATCH, new Object[0]);
                                }
                            } catch (IllegalArgumentException unused9) {
                                i2 = 0;
                                result.addError(Issue.V3_SIG_PAST_SIGNERS_MISMATCH, new Object[0]);
                            }
                        }
                        i2 = 0;
                    } else {
                        i2 = 0;
                    }
                    if (result.isVerifiedUsingV4Scheme()) {
                        List<Result.V4SchemeSignerInfo> v4SchemeSigners = result.getV4SchemeSigners();
                        List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> contentDigests2 = v4SchemeSigners.get(i2).getContentDigests();
                        if (contentDigests2.size() != 1) {
                            result.addError(Issue.V4_SIG_UNEXPECTED_DIGESTS, Integer.valueOf(contentDigests2.size()));
                            if (!contentDigests2.isEmpty()) {
                                value = contentDigests2.get(0).getValue();
                                if (result.isVerifiedUsingV3Scheme()) {
                                    zIsVerifiedUsingV31Scheme = result.isVerifiedUsingV31Scheme();
                                    if (zIsVerifiedUsingV31Scheme) {
                                        i4 = 2;
                                    } else {
                                        i4 = 1;
                                    }
                                    if (v4SchemeSigners.size() != i4) {
                                        if (zIsVerifiedUsingV31Scheme) {
                                            issue = Issue.V41_SIG_NEEDS_TWO_SIGNERS;
                                        } else {
                                            issue = Issue.V4_SIG_MULTIPLE_SIGNERS;
                                        }
                                        result.addError(issue, new Object[0]);
                                        return result;
                                    }
                                    checkV4Signer(result.getV3SchemeSigners(), v4SchemeSigners.get(0).mCerts, value, result);
                                    if (zIsVerifiedUsingV31Scheme) {
                                        contentDigests = v4SchemeSigners.get(1).getContentDigests();
                                        if (contentDigests.size() != 1) {
                                            result.addError(Issue.V4_SIG_UNEXPECTED_DIGESTS, Integer.valueOf(contentDigests.size()));
                                        }
                                        checkV4Signer(result.getV31SchemeSigners(), v4SchemeSigners.get(1).mCerts, contentDigests.get(0).getValue(), result);
                                    }
                                } else {
                                    if (result.isVerifiedUsingV2Scheme()) {
                                        f63.a("V4 signature must be also verified with V2/V3");
                                        return null;
                                    }
                                    if (v4SchemeSigners.size() != 1) {
                                        i3 = 0;
                                        result.addError(Issue.V4_SIG_MULTIPLE_SIGNERS, new Object[0]);
                                    } else {
                                        i3 = 0;
                                    }
                                    v2SchemeSigners = result.getV2SchemeSigners();
                                    if (v2SchemeSigners.size() != 1) {
                                        result.addError(Issue.V4_SIG_MULTIPLE_SIGNERS, new Object[i3]);
                                    }
                                    checkV4Certificate(v4SchemeSigners.get(i3).mCerts, v2SchemeSigners.get(i3).mCerts, result);
                                    bArrPickBestDigestForV4 = pickBestDigestForV4(v2SchemeSigners.get(i3).getContentDigests());
                                    if (!Arrays.equals(value, bArrPickBestDigestForV4)) {
                                        result.addError(Issue.V4_SIG_V2_V3_DIGESTS_MISMATCH, 2, ApkSigningBlockUtils.toHex(bArrPickBestDigestForV4), ApkSigningBlockUtils.toHex(value));
                                    }
                                }
                                if (androidManifestFromApk == null) {
                                    try {
                                        androidManifestFromApk = getAndroidManifestFromApk(dataSource2, zipSections2);
                                    } catch (ApkFormatException unused10) {
                                    }
                                }
                                if (androidManifestFromApk != null && (minimumSignatureSchemeVersionForTargetSdk = getMinimumSignatureSchemeVersionForTargetSdk((targetSdkVersionFromBinaryAndroidManifest = ApkUtils.getTargetSdkVersionFromBinaryAndroidManifest(androidManifestFromApk.slice())))) > 1 && i6 >= targetSdkVersionFromBinaryAndroidManifest && (minimumSignatureSchemeVersionForTargetSdk == 2 ? !result.isVerifiedUsingV2Scheme() : minimumSignatureSchemeVersionForTargetSdk == 3) && !result.isVerifiedUsingV3Scheme() && !result.isVerifiedUsingV31Scheme()) {
                                    result.addError(Issue.MIN_SIG_SCHEME_FOR_TARGET_SDK_NOT_MET, Integer.valueOf(targetSdkVersionFromBinaryAndroidManifest), Integer.valueOf(minimumSignatureSchemeVersionForTargetSdk));
                                }
                                if (!result.containsErrors()) {
                                    result.setVerified();
                                    if (result.isVerifiedUsingV31Scheme()) {
                                        List<Result.V3SchemeSignerInfo> v31SchemeSigners = result.getV31SchemeSigners();
                                        result.addSignerCertificate(v31SchemeSigners.get(v31SchemeSigners.size() - 1).getCertificate());
                                    } else if (result.isVerifiedUsingV3Scheme()) {
                                        List<Result.V3SchemeSignerInfo> v3SchemeSigners2 = result.getV3SchemeSigners();
                                        result.addSignerCertificate(v3SchemeSigners2.get(v3SchemeSigners2.size() - 1).getCertificate());
                                    } else if (result.isVerifiedUsingV2Scheme()) {
                                        it2 = result.getV2SchemeSigners().iterator();
                                        while (it2.hasNext()) {
                                            result.addSignerCertificate(it2.next().getCertificate());
                                        }
                                    } else {
                                        if (!result.isVerifiedUsingV1Scheme()) {
                                            f63.a("APK verified, but has not verified using any of v1, v2 or v3 schemes");
                                            return null;
                                        }
                                        it = result.getV1SchemeSigners().iterator();
                                        while (it.hasNext()) {
                                            result.addSignerCertificate(it.next().getCertificate());
                                        }
                                    }
                                }
                            }
                        } else {
                            value = contentDigests2.get(0).getValue();
                            if (result.isVerifiedUsingV3Scheme()) {
                                zIsVerifiedUsingV31Scheme = result.isVerifiedUsingV31Scheme();
                                if (zIsVerifiedUsingV31Scheme) {
                                    i4 = 2;
                                } else {
                                    i4 = 1;
                                }
                                if (v4SchemeSigners.size() != i4) {
                                    if (zIsVerifiedUsingV31Scheme) {
                                        issue = Issue.V41_SIG_NEEDS_TWO_SIGNERS;
                                    } else {
                                        issue = Issue.V4_SIG_MULTIPLE_SIGNERS;
                                    }
                                    result.addError(issue, new Object[0]);
                                    return result;
                                }
                                checkV4Signer(result.getV3SchemeSigners(), v4SchemeSigners.get(0).mCerts, value, result);
                                if (zIsVerifiedUsingV31Scheme) {
                                    contentDigests = v4SchemeSigners.get(1).getContentDigests();
                                    if (contentDigests.size() != 1) {
                                        result.addError(Issue.V4_SIG_UNEXPECTED_DIGESTS, Integer.valueOf(contentDigests.size()));
                                    }
                                    checkV4Signer(result.getV31SchemeSigners(), v4SchemeSigners.get(1).mCerts, contentDigests.get(0).getValue(), result);
                                }
                            } else {
                                if (result.isVerifiedUsingV2Scheme()) {
                                    f63.a("V4 signature must be also verified with V2/V3");
                                    return null;
                                }
                                if (v4SchemeSigners.size() != 1) {
                                    i3 = 0;
                                    result.addError(Issue.V4_SIG_MULTIPLE_SIGNERS, new Object[0]);
                                } else {
                                    i3 = 0;
                                }
                                v2SchemeSigners = result.getV2SchemeSigners();
                                if (v2SchemeSigners.size() != 1) {
                                    result.addError(Issue.V4_SIG_MULTIPLE_SIGNERS, new Object[i3]);
                                }
                                checkV4Certificate(v4SchemeSigners.get(i3).mCerts, v2SchemeSigners.get(i3).mCerts, result);
                                bArrPickBestDigestForV4 = pickBestDigestForV4(v2SchemeSigners.get(i3).getContentDigests());
                                if (!Arrays.equals(value, bArrPickBestDigestForV4)) {
                                    result.addError(Issue.V4_SIG_V2_V3_DIGESTS_MISMATCH, 2, ApkSigningBlockUtils.toHex(bArrPickBestDigestForV4), ApkSigningBlockUtils.toHex(value));
                                }
                            }
                            if (androidManifestFromApk == null) {
                                androidManifestFromApk = getAndroidManifestFromApk(dataSource2, zipSections2);
                            }
                            if (androidManifestFromApk != null) {
                                result.addError(Issue.MIN_SIG_SCHEME_FOR_TARGET_SDK_NOT_MET, Integer.valueOf(targetSdkVersionFromBinaryAndroidManifest), Integer.valueOf(minimumSignatureSchemeVersionForTargetSdk));
                            }
                            if (!result.containsErrors()) {
                                result.setVerified();
                                if (result.isVerifiedUsingV31Scheme()) {
                                    List<Result.V3SchemeSignerInfo> v31SchemeSigners2 = result.getV31SchemeSigners();
                                    result.addSignerCertificate(v31SchemeSigners2.get(v31SchemeSigners2.size() - 1).getCertificate());
                                } else if (result.isVerifiedUsingV3Scheme()) {
                                    List<Result.V3SchemeSignerInfo> v3SchemeSigners3 = result.getV3SchemeSigners();
                                    result.addSignerCertificate(v3SchemeSigners3.get(v3SchemeSigners3.size() - 1).getCertificate());
                                } else if (result.isVerifiedUsingV2Scheme()) {
                                    it2 = result.getV2SchemeSigners().iterator();
                                    while (it2.hasNext()) {
                                        result.addSignerCertificate(it2.next().getCertificate());
                                    }
                                } else {
                                    if (!result.isVerifiedUsingV1Scheme()) {
                                        f63.a("APK verified, but has not verified using any of v1, v2 or v3 schemes");
                                        return null;
                                    }
                                    it = result.getV1SchemeSigners().iterator();
                                    while (it.hasNext()) {
                                        result.addSignerCertificate(it.next().getCertificate());
                                    }
                                }
                            }
                        }
                    } else {
                        if (androidManifestFromApk == null) {
                            androidManifestFromApk = getAndroidManifestFromApk(dataSource2, zipSections2);
                        }
                        if (androidManifestFromApk != null) {
                            result.addError(Issue.MIN_SIG_SCHEME_FOR_TARGET_SDK_NOT_MET, Integer.valueOf(targetSdkVersionFromBinaryAndroidManifest), Integer.valueOf(minimumSignatureSchemeVersionForTargetSdk));
                        }
                        if (!result.containsErrors()) {
                            result.setVerified();
                            if (result.isVerifiedUsingV31Scheme()) {
                                List<Result.V3SchemeSignerInfo> v31SchemeSigners3 = result.getV31SchemeSigners();
                                result.addSignerCertificate(v31SchemeSigners3.get(v31SchemeSigners3.size() - 1).getCertificate());
                            } else if (result.isVerifiedUsingV3Scheme()) {
                                List<Result.V3SchemeSignerInfo> v3SchemeSigners4 = result.getV3SchemeSigners();
                                result.addSignerCertificate(v3SchemeSigners4.get(v3SchemeSigners4.size() - 1).getCertificate());
                            } else if (result.isVerifiedUsingV2Scheme()) {
                                it2 = result.getV2SchemeSigners().iterator();
                                while (it2.hasNext()) {
                                    result.addSignerCertificate(it2.next().getCertificate());
                                }
                            } else {
                                if (!result.isVerifiedUsingV1Scheme()) {
                                    f63.a("APK verified, but has not verified using any of v1, v2 or v3 schemes");
                                    return null;
                                }
                                it = result.getV1SchemeSigners().iterator();
                                while (it.hasNext()) {
                                    result.addSignerCertificate(it.next().getCertificate());
                                }
                            }
                        }
                    }
                }
            }
            return result;
        } catch (ZipFormatException e5) {
            throw new ApkFormatException("Malformed APK: not a ZIP archive", e5);
        }
    }

    private int verifyAndGetMinSdkVersion(DataSource dataSource, ApkUtils.ZipSections zipSections) throws IOException, ApkFormatException {
        Integer num = this.mMinSdkVersion;
        if (num == null) {
            int minSdkVersionFromBinaryAndroidManifest = ApkUtils.getMinSdkVersionFromBinaryAndroidManifest(getAndroidManifestFromApk(dataSource, zipSections).slice());
            if (minSdkVersionFromBinaryAndroidManifest <= this.mMaxSdkVersion) {
                return minSdkVersionFromBinaryAndroidManifest;
            }
            pb0.a("minSdkVersion from APK (", minSdkVersionFromBinaryAndroidManifest, ") > maxSdkVersion (", this.mMaxSdkVersion, ")");
            return 0;
        }
        int iIntValue = num.intValue();
        Integer num2 = this.mMinSdkVersion;
        if (iIntValue < 0) {
            aca.a("minSdkVersion must not be negative: ", num2);
            return 0;
        }
        if (num2 == null || num2.intValue() <= this.mMaxSdkVersion) {
            return this.mMinSdkVersion.intValue();
        }
        StringBuilder sb = new StringBuilder("minSdkVersion (");
        sb.append(this.mMinSdkVersion);
        int i = this.mMaxSdkVersion;
        sb.append(") > maxSdkVersion (");
        sb.append(i);
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    private Result verifySourceStamp(DataSource dataSource, String str) {
        CentralDirectoryRecord next;
        ApkVerifier apkVerifier;
        DataSource dataSource2;
        ApkSigningBlockUtils.Result apkContentDigests;
        try {
            try {
                ApkUtils.ZipSections zipSectionsFindZipSections = ApkUtils.findZipSections(dataSource);
                int iVerifyAndGetMinSdkVersion = verifyAndGetMinSdkVersion(dataSource, zipSectionsFindZipSections);
                List<CentralDirectoryRecord> zipCentralDirectory = V1SchemeVerifier.parseZipCentralDirectory(dataSource, zipSectionsFindZipSections);
                Iterator<CentralDirectoryRecord> it = zipCentralDirectory.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!"stamp-cert-sha256".equals(next.getName()));
                if (next == null) {
                    try {
                        ApkSigningBlockUtils.findSignature(dataSource, zipSectionsFindZipSections, 1845461005, new ApkSigningBlockUtils.Result(0));
                        return createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.STAMP_NOT_VERIFIED, Issue.SOURCE_STAMP_SIGNATURE_BLOCK_WITHOUT_CERT_DIGEST, new Object[0]);
                    } catch (ApkSigningBlockUtils.SignatureNotFoundException unused) {
                        return createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.STAMP_MISSING, Issue.SOURCE_STAMP_CERT_DIGEST_AND_SIG_BLOCK_MISSING, new Object[0]);
                    }
                }
                byte[] uncompressedData = LocalFileRecord.getUncompressedData(dataSource, next, zipSectionsFindZipSections.getZipCentralDirectoryOffset());
                if (str != null) {
                    String hex = ApkSigningBlockUtils.toHex(uncompressedData);
                    if (!str.equalsIgnoreCase(hex)) {
                        return createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.CERT_DIGEST_MISMATCH, Issue.SOURCE_STAMP_EXPECTED_DIGEST_MISMATCH, hex, str);
                    }
                }
                HashMap map = new HashMap();
                Map<Integer, String> supportedSchemeNames = getSupportedSchemeNames(this.mMaxSdkVersion);
                HashSet hashSet = new HashSet(2);
                Result result = new Result();
                if (this.mMaxSdkVersion >= 28) {
                    apkVerifier = this;
                    dataSource2 = dataSource;
                    ApkSigningBlockUtils.Result apkContentDigests2 = apkVerifier.getApkContentDigests(dataSource2, zipSectionsFindZipSections, hashSet, supportedSchemeNames, map, 3, Math.max(iVerifyAndGetMinSdkVersion, 28));
                    if (apkContentDigests2 != null && apkContentDigests2.containsErrors()) {
                        result.mergeFrom(apkContentDigests2);
                        return mergeSourceStampResult(Result.SourceStampInfo.SourceStampVerificationStatus.VERIFICATION_ERROR, result);
                    }
                } else {
                    apkVerifier = this;
                    dataSource2 = dataSource;
                }
                if (apkVerifier.mMaxSdkVersion >= 24 && ((iVerifyAndGetMinSdkVersion < 28 || hashSet.isEmpty()) && (apkContentDigests = apkVerifier.getApkContentDigests(dataSource2, zipSectionsFindZipSections, hashSet, supportedSchemeNames, map, 2, Math.max(iVerifyAndGetMinSdkVersion, 24))) != null && apkContentDigests.containsErrors())) {
                    result.mergeFrom(apkContentDigests);
                    return mergeSourceStampResult(Result.SourceStampInfo.SourceStampVerificationStatus.VERIFICATION_ERROR, result);
                }
                if (iVerifyAndGetMinSdkVersion < 24 || hashSet.isEmpty()) {
                    map.put(1, getApkContentDigestFromV1SigningScheme(zipCentralDirectory, dataSource2, zipSectionsFindZipSections));
                }
                ApkSigResult apkSigResultVerify = V2SourceStampVerifier.verify(dataSource2, zipSectionsFindZipSections, uncompressedData, map, iVerifyAndGetMinSdkVersion, apkVerifier.mMaxSdkVersion);
                result.mergeFrom(apkSigResultVerify);
                if (apkSigResultVerify.verified) {
                    result.setVerified();
                    return result;
                }
                result.setWarningsAsErrors(true);
                return result;
            } catch (ApkFormatException | ZipFormatException | IOException e) {
                return createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.VERIFICATION_ERROR, Issue.MALFORMED_APK, e);
            }
        } catch (SignatureNotFoundException unused2) {
            return createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.STAMP_NOT_VERIFIED, Issue.SOURCE_STAMP_SIG_MISSING, new Object[0]);
        } catch (NoSuchAlgorithmException e2) {
            return createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.VERIFICATION_ERROR, Issue.UNEXPECTED_EXCEPTION, e2);
        }
    }

    public static class Builder {
        private final DataSource mApkDataSource;
        private final File mApkFile;
        private int mMaxSdkVersion = Integer.MAX_VALUE;
        private Integer mMinSdkVersion;
        private File mV4SignatureFile;

        public Builder(File file) {
            if (file == null) {
                x0e.a("apk == null");
                throw null;
            }
            this.mApkFile = file;
            this.mApkDataSource = null;
        }

        public ApkVerifier build() {
            return new ApkVerifier(this.mApkFile, this.mApkDataSource, this.mV4SignatureFile, this.mMinSdkVersion, this.mMaxSdkVersion);
        }

        public Builder setMaxCheckedPlatformVersion(int i) {
            this.mMaxSdkVersion = i;
            return this;
        }

        public Builder setMinCheckedPlatformVersion(int i) {
            this.mMinSdkVersion = Integer.valueOf(i);
            return this;
        }

        public Builder setV4SignatureFile(File file) {
            this.mV4SignatureFile = file;
            return this;
        }

        public Builder(DataSource dataSource) {
            if (dataSource != null) {
                this.mApkDataSource = dataSource;
                this.mApkFile = null;
            } else {
                x0e.a("apk == null");
                throw null;
            }
        }
    }

    public static class Result {
        private SigningCertificateLineage mSigningCertificateLineage;
        private SourceStampInfo mSourceStampInfo;
        private boolean mSourceStampVerified;
        private boolean mVerified;
        private boolean mVerifiedUsingV1Scheme;
        private boolean mVerifiedUsingV2Scheme;
        private boolean mVerifiedUsingV31Scheme;
        private boolean mVerifiedUsingV3Scheme;
        private boolean mVerifiedUsingV4Scheme;
        private boolean mWarningsAsErrors;
        private final List<IssueWithParams> mErrors = new ArrayList();
        private final List<IssueWithParams> mWarnings = new ArrayList();
        private final List<X509Certificate> mSignerCerts = new ArrayList();
        private final List<V1SchemeSignerInfo> mV1SchemeSigners = new ArrayList();
        private final List<V1SchemeSignerInfo> mV1SchemeIgnoredSigners = new ArrayList();
        private final List<V2SchemeSignerInfo> mV2SchemeSigners = new ArrayList();
        private final List<V3SchemeSignerInfo> mV3SchemeSigners = new ArrayList();
        private final List<V3SchemeSignerInfo> mV31SchemeSigners = new ArrayList();
        private final List<V4SchemeSignerInfo> mV4SchemeSigners = new ArrayList();

        public static class V1SchemeSignerInfo {
            private final List<X509Certificate> mCertChain;
            private final List<IssueWithParams> mErrors;
            private final String mName;
            private final String mSignatureBlockFileName;
            private final String mSignatureFileName;
            private final List<IssueWithParams> mWarnings;

            private V1SchemeSignerInfo(V1SchemeVerifier.Result.SignerInfo signerInfo) {
                this.mName = signerInfo.name;
                this.mCertChain = signerInfo.certChain;
                this.mSignatureBlockFileName = signerInfo.signatureBlockFileName;
                this.mSignatureFileName = signerInfo.signatureFileName;
                this.mErrors = signerInfo.getErrors();
                this.mWarnings = signerInfo.getWarnings();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addError(Issue issue, Object... objArr) {
                this.mErrors.add(new IssueWithParams(issue, objArr));
            }

            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public X509Certificate getCertificate() {
                if (this.mCertChain.isEmpty()) {
                    return null;
                }
                return this.mCertChain.get(0);
            }

            public List<X509Certificate> getCertificateChain() {
                return this.mCertChain;
            }

            public List<IssueWithParams> getErrors() {
                return this.mErrors;
            }

            public String getName() {
                return this.mName;
            }

            public String getSignatureBlockFileName() {
                return this.mSignatureBlockFileName;
            }

            public String getSignatureFileName() {
                return this.mSignatureFileName;
            }

            public List<IssueWithParams> getWarnings() {
                return this.mWarnings;
            }
        }

        public static class V2SchemeSignerInfo {
            private final List<X509Certificate> mCerts;
            private final List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> mContentDigests;
            private final List<IssueWithParams> mErrors;
            private final int mIndex;
            private final List<IssueWithParams> mWarnings;

            private V2SchemeSignerInfo(ApkSigningBlockUtils.Result.SignerInfo signerInfo) {
                this.mIndex = signerInfo.index;
                this.mCerts = signerInfo.certs;
                this.mErrors = signerInfo.getErrors();
                this.mWarnings = signerInfo.getWarnings();
                this.mContentDigests = signerInfo.contentDigests;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addError(Issue issue, Object... objArr) {
                this.mErrors.add(new IssueWithParams(issue, objArr));
            }

            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public X509Certificate getCertificate() {
                if (this.mCerts.isEmpty()) {
                    return null;
                }
                return this.mCerts.get(0);
            }

            public List<X509Certificate> getCertificates() {
                return this.mCerts;
            }

            public List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> getContentDigests() {
                return this.mContentDigests;
            }

            public List<IssueWithParams> getErrors() {
                return this.mErrors;
            }

            public int getIndex() {
                return this.mIndex;
            }

            public List<IssueWithParams> getWarnings() {
                return this.mWarnings;
            }
        }

        public static class V3SchemeSignerInfo {
            private final List<X509Certificate> mCerts;
            private final List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> mContentDigests;
            private final List<IssueWithParams> mErrors;
            private final int mIndex;
            private final int mMaxSdkVersion;
            private final int mMinSdkVersion;
            private final boolean mRotationTargetsDevRelease;
            private final SigningCertificateLineage mSigningCertificateLineage;
            private final List<IssueWithParams> mWarnings;

            private V3SchemeSignerInfo(ApkSigningBlockUtils.Result.SignerInfo signerInfo) {
                this.mIndex = signerInfo.index;
                this.mCerts = signerInfo.certs;
                this.mErrors = signerInfo.getErrors();
                this.mWarnings = signerInfo.getWarnings();
                this.mContentDigests = signerInfo.contentDigests;
                this.mMinSdkVersion = signerInfo.minSdkVersion;
                this.mMaxSdkVersion = signerInfo.maxSdkVersion;
                this.mSigningCertificateLineage = signerInfo.signingCertificateLineage;
                this.mRotationTargetsDevRelease = signerInfo.additionalAttributes.stream().mapToInt(new ToIntFunction() { // from class: tb0
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return ((ApkSigningBlockUtils.Result.SignerInfo.AdditionalAttribute) obj).getId();
                    }
                }).anyMatch(new IntPredicate() { // from class: ub0
                    @Override // java.util.function.IntPredicate
                    public final boolean test(int i) {
                        return ApkVerifier.Result.V3SchemeSignerInfo.b(i);
                    }
                });
            }

            public static /* synthetic */ boolean b(int i) {
                return i == -1029262406;
            }

            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public X509Certificate getCertificate() {
                if (this.mCerts.isEmpty()) {
                    return null;
                }
                return this.mCerts.get(0);
            }

            public List<X509Certificate> getCertificates() {
                return this.mCerts;
            }

            public List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> getContentDigests() {
                return this.mContentDigests;
            }

            public List<IssueWithParams> getErrors() {
                return this.mErrors;
            }

            public int getIndex() {
                return this.mIndex;
            }

            public int getMaxSdkVersion() {
                return this.mMaxSdkVersion;
            }

            public int getMinSdkVersion() {
                return this.mMinSdkVersion;
            }

            public boolean getRotationTargetsDevRelease() {
                return this.mRotationTargetsDevRelease;
            }

            public SigningCertificateLineage getSigningCertificateLineage() {
                return this.mSigningCertificateLineage;
            }

            public List<IssueWithParams> getWarnings() {
                return this.mWarnings;
            }
        }

        public static class V4SchemeSignerInfo {
            private final List<X509Certificate> mCerts;
            private final List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> mContentDigests;
            private final List<IssueWithParams> mErrors;
            private final int mIndex;
            private final List<IssueWithParams> mWarnings;

            private V4SchemeSignerInfo(ApkSigningBlockUtils.Result.SignerInfo signerInfo) {
                this.mIndex = signerInfo.index;
                this.mCerts = signerInfo.certs;
                this.mErrors = signerInfo.getErrors();
                this.mWarnings = signerInfo.getWarnings();
                this.mContentDigests = signerInfo.contentDigests;
            }

            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public X509Certificate getCertificate() {
                if (this.mCerts.isEmpty()) {
                    return null;
                }
                return this.mCerts.get(0);
            }

            public List<X509Certificate> getCertificates() {
                return this.mCerts;
            }

            public List<ApkSigningBlockUtils.Result.SignerInfo.ContentDigest> getContentDigests() {
                return this.mContentDigests;
            }

            public List<IssueWithParams> getErrors() {
                return this.mErrors;
            }

            public int getIndex() {
                return this.mIndex;
            }

            public List<IssueWithParams> getWarnings() {
                return this.mWarnings;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSignerCertificate(X509Certificate x509Certificate) {
            this.mSignerCerts.add(x509Certificate);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFrom(ApkSigningBlockUtils.Result result) {
            if (result == null) {
                return;
            }
            if (result.containsErrors()) {
                this.mErrors.addAll(result.getErrors());
            }
            if (result.containsWarnings()) {
                this.mWarnings.addAll(result.getWarnings());
            }
            int i = result.signatureSchemeVersion;
            if (i == 0) {
                this.mSourceStampVerified = result.verified;
                if (result.signers.isEmpty()) {
                    return;
                }
                this.mSourceStampInfo = new SourceStampInfo(result.signers.get(0));
                return;
            }
            if (i == 31) {
                this.mVerifiedUsingV31Scheme = result.verified;
                Iterator<ApkSigningBlockUtils.Result.SignerInfo> it = result.signers.iterator();
                while (it.hasNext()) {
                    this.mV31SchemeSigners.add(new V3SchemeSignerInfo(it.next()));
                }
                this.mSigningCertificateLineage = result.signingCertificateLineage;
                return;
            }
            if (i == 2) {
                this.mVerifiedUsingV2Scheme = result.verified;
                Iterator<ApkSigningBlockUtils.Result.SignerInfo> it2 = result.signers.iterator();
                while (it2.hasNext()) {
                    this.mV2SchemeSigners.add(new V2SchemeSignerInfo(it2.next()));
                }
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    w01.a("Unknown Signing Block Scheme Id");
                    return;
                }
                this.mVerifiedUsingV4Scheme = result.verified;
                Iterator<ApkSigningBlockUtils.Result.SignerInfo> it3 = result.signers.iterator();
                while (it3.hasNext()) {
                    this.mV4SchemeSigners.add(new V4SchemeSignerInfo(it3.next()));
                }
                return;
            }
            this.mVerifiedUsingV3Scheme = result.verified;
            Iterator<ApkSigningBlockUtils.Result.SignerInfo> it4 = result.signers.iterator();
            while (it4.hasNext()) {
                this.mV3SchemeSigners.add(new V3SchemeSignerInfo(it4.next()));
            }
            if (this.mSigningCertificateLineage == null) {
                this.mSigningCertificateLineage = result.signingCertificateLineage;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerified() {
            this.mVerified = true;
        }

        public void addError(Issue issue, Object... objArr) {
            this.mErrors.add(new IssueWithParams(issue, objArr));
        }

        public void addWarning(Issue issue, Object... objArr) {
            this.mWarnings.add(new IssueWithParams(issue, objArr));
        }

        public boolean containsErrors() {
            if (!this.mErrors.isEmpty()) {
                return true;
            }
            if (this.mWarningsAsErrors && !this.mWarnings.isEmpty()) {
                return true;
            }
            if (!this.mV1SchemeSigners.isEmpty()) {
                for (V1SchemeSignerInfo v1SchemeSignerInfo : this.mV1SchemeSigners) {
                    if (v1SchemeSignerInfo.containsErrors()) {
                        return true;
                    }
                    if (this.mWarningsAsErrors && !v1SchemeSignerInfo.getWarnings().isEmpty()) {
                        return true;
                    }
                }
            }
            if (!this.mV2SchemeSigners.isEmpty()) {
                for (V2SchemeSignerInfo v2SchemeSignerInfo : this.mV2SchemeSigners) {
                    if (v2SchemeSignerInfo.containsErrors()) {
                        return true;
                    }
                    if (this.mWarningsAsErrors && !v2SchemeSignerInfo.getWarnings().isEmpty()) {
                        return true;
                    }
                }
            }
            if (!this.mV3SchemeSigners.isEmpty()) {
                for (V3SchemeSignerInfo v3SchemeSignerInfo : this.mV3SchemeSigners) {
                    if (v3SchemeSignerInfo.containsErrors()) {
                        return true;
                    }
                    if (this.mWarningsAsErrors && !v3SchemeSignerInfo.getWarnings().isEmpty()) {
                        return true;
                    }
                }
            }
            if (!this.mV31SchemeSigners.isEmpty()) {
                for (V3SchemeSignerInfo v3SchemeSignerInfo2 : this.mV31SchemeSigners) {
                    if (v3SchemeSignerInfo2.containsErrors()) {
                        return true;
                    }
                    if (this.mWarningsAsErrors && !v3SchemeSignerInfo2.getWarnings().isEmpty()) {
                        return true;
                    }
                }
            }
            SourceStampInfo sourceStampInfo = this.mSourceStampInfo;
            if (sourceStampInfo == null) {
                return false;
            }
            if (sourceStampInfo.containsErrors()) {
                return true;
            }
            return this.mWarningsAsErrors && !this.mSourceStampInfo.getWarnings().isEmpty();
        }

        public List<IssueWithParams> getAllErrors() {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mErrors);
            if (this.mWarningsAsErrors) {
                arrayList.addAll(this.mWarnings);
            }
            if (!this.mV1SchemeSigners.isEmpty()) {
                for (V1SchemeSignerInfo v1SchemeSignerInfo : this.mV1SchemeSigners) {
                    arrayList.addAll(v1SchemeSignerInfo.mErrors);
                    if (this.mWarningsAsErrors) {
                        arrayList.addAll(v1SchemeSignerInfo.getWarnings());
                    }
                }
            }
            if (!this.mV2SchemeSigners.isEmpty()) {
                for (V2SchemeSignerInfo v2SchemeSignerInfo : this.mV2SchemeSigners) {
                    arrayList.addAll(v2SchemeSignerInfo.mErrors);
                    if (this.mWarningsAsErrors) {
                        arrayList.addAll(v2SchemeSignerInfo.getWarnings());
                    }
                }
            }
            if (!this.mV3SchemeSigners.isEmpty()) {
                for (V3SchemeSignerInfo v3SchemeSignerInfo : this.mV3SchemeSigners) {
                    arrayList.addAll(v3SchemeSignerInfo.mErrors);
                    if (this.mWarningsAsErrors) {
                        arrayList.addAll(v3SchemeSignerInfo.getWarnings());
                    }
                }
            }
            if (!this.mV31SchemeSigners.isEmpty()) {
                for (V3SchemeSignerInfo v3SchemeSignerInfo2 : this.mV31SchemeSigners) {
                    arrayList.addAll(v3SchemeSignerInfo2.mErrors);
                    if (this.mWarningsAsErrors) {
                        arrayList.addAll(v3SchemeSignerInfo2.getWarnings());
                    }
                }
            }
            SourceStampInfo sourceStampInfo = this.mSourceStampInfo;
            if (sourceStampInfo != null) {
                arrayList.addAll(sourceStampInfo.getErrors());
                if (this.mWarningsAsErrors) {
                    arrayList.addAll(this.mSourceStampInfo.getWarnings());
                }
            }
            return arrayList;
        }

        public List<IssueWithParams> getErrors() {
            if (!this.mWarningsAsErrors) {
                return this.mErrors;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mErrors);
            arrayList.addAll(this.mWarnings);
            return arrayList;
        }

        public List<X509Certificate> getSignerCertificates() {
            return this.mSignerCerts;
        }

        public SigningCertificateLineage getSigningCertificateLineage() {
            return this.mSigningCertificateLineage;
        }

        public SourceStampInfo getSourceStampInfo() {
            return this.mSourceStampInfo;
        }

        public List<V1SchemeSignerInfo> getV1SchemeIgnoredSigners() {
            return this.mV1SchemeIgnoredSigners;
        }

        public List<V1SchemeSignerInfo> getV1SchemeSigners() {
            return this.mV1SchemeSigners;
        }

        public List<V2SchemeSignerInfo> getV2SchemeSigners() {
            return this.mV2SchemeSigners;
        }

        public List<V3SchemeSignerInfo> getV31SchemeSigners() {
            return this.mV31SchemeSigners;
        }

        public List<V3SchemeSignerInfo> getV3SchemeSigners() {
            return this.mV3SchemeSigners;
        }

        public List<V4SchemeSignerInfo> getV4SchemeSigners() {
            return this.mV4SchemeSigners;
        }

        public List<IssueWithParams> getWarnings() {
            return this.mWarnings;
        }

        public boolean isSourceStampVerified() {
            return this.mSourceStampVerified;
        }

        public boolean isVerified() {
            return this.mVerified;
        }

        public boolean isVerifiedUsingV1Scheme() {
            return this.mVerifiedUsingV1Scheme;
        }

        public boolean isVerifiedUsingV2Scheme() {
            return this.mVerifiedUsingV2Scheme;
        }

        public boolean isVerifiedUsingV31Scheme() {
            return this.mVerifiedUsingV31Scheme;
        }

        public boolean isVerifiedUsingV3Scheme() {
            return this.mVerifiedUsingV3Scheme;
        }

        public boolean isVerifiedUsingV4Scheme() {
            return this.mVerifiedUsingV4Scheme;
        }

        public void setWarningsAsErrors(boolean z) {
            this.mWarningsAsErrors = z;
        }

        public static class SourceStampInfo {
            private final List<X509Certificate> mCertificateLineage;
            private final List<X509Certificate> mCertificates;
            private final List<IssueWithParams> mErrors;
            private final List<IssueWithParams> mInfoMessages;
            private final SourceStampVerificationStatus mSourceStampVerificationStatus;
            private final long mTimestamp;
            private final List<IssueWithParams> mWarnings;

            public enum SourceStampVerificationStatus {
                STAMP_VERIFIED,
                STAMP_VERIFICATION_FAILED,
                CERT_DIGEST_MISMATCH,
                STAMP_MISSING,
                STAMP_NOT_VERIFIED,
                VERIFICATION_ERROR
            }

            private SourceStampInfo(ApkSignerInfo apkSignerInfo) {
                this.mCertificates = apkSignerInfo.certs;
                this.mCertificateLineage = apkSignerInfo.certificateLineage;
                List<IssueWithParams> issuesFromVerificationIssues = ApkVerificationIssueAdapter.getIssuesFromVerificationIssues(apkSignerInfo.getErrors());
                this.mErrors = issuesFromVerificationIssues;
                List<IssueWithParams> issuesFromVerificationIssues2 = ApkVerificationIssueAdapter.getIssuesFromVerificationIssues(apkSignerInfo.getWarnings());
                this.mWarnings = issuesFromVerificationIssues2;
                this.mInfoMessages = ApkVerificationIssueAdapter.getIssuesFromVerificationIssues(apkSignerInfo.getInfoMessages());
                if (issuesFromVerificationIssues.isEmpty() && issuesFromVerificationIssues2.isEmpty()) {
                    this.mSourceStampVerificationStatus = SourceStampVerificationStatus.STAMP_VERIFIED;
                } else {
                    this.mSourceStampVerificationStatus = SourceStampVerificationStatus.STAMP_VERIFICATION_FAILED;
                }
                this.mTimestamp = apkSignerInfo.timestamp;
            }

            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            public boolean containsInfoMessages() {
                return !this.mInfoMessages.isEmpty();
            }

            public X509Certificate getCertificate() {
                if (this.mCertificates.isEmpty()) {
                    return null;
                }
                return this.mCertificates.get(0);
            }

            public List<X509Certificate> getCertificatesInLineage() {
                return this.mCertificateLineage;
            }

            public List<IssueWithParams> getErrors() {
                return this.mErrors;
            }

            public List<IssueWithParams> getInfoMessages() {
                return this.mInfoMessages;
            }

            public SourceStampVerificationStatus getSourceStampVerificationStatus() {
                return this.mSourceStampVerificationStatus;
            }

            public long getTimestampEpochSeconds() {
                return this.mTimestamp;
            }

            public List<IssueWithParams> getWarnings() {
                return this.mWarnings;
            }

            public SourceStampInfo(SourceStampVerificationStatus sourceStampVerificationStatus) {
                List list = Collections.EMPTY_LIST;
                this.mCertificates = list;
                this.mCertificateLineage = list;
                this.mErrors = list;
                this.mWarnings = list;
                this.mInfoMessages = list;
                this.mSourceStampVerificationStatus = sourceStampVerificationStatus;
                this.mTimestamp = 0L;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFrom(ApkSigResult apkSigResult) {
            if (apkSigResult.signatureSchemeVersion == 0) {
                this.mSourceStampVerified = apkSigResult.verified;
                if (apkSigResult.mSigners.isEmpty()) {
                    return;
                }
                this.mSourceStampInfo = new SourceStampInfo(apkSigResult.mSigners.get(0));
                return;
            }
            jt6.a("Unknown ApkSigResult Signing Block Scheme Id ", apkSigResult.signatureSchemeVersion);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFrom(V1SchemeVerifier.Result result) {
            this.mVerifiedUsingV1Scheme = result.verified;
            this.mErrors.addAll(result.getErrors());
            this.mWarnings.addAll(result.getWarnings());
            Iterator<V1SchemeVerifier.Result.SignerInfo> it = result.signers.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                this.mV1SchemeSigners.add(new V1SchemeSignerInfo(it.next()));
            }
            Iterator<V1SchemeVerifier.Result.SignerInfo> it2 = result.ignoredSigners.iterator();
            while (it2.hasNext()) {
                this.mV1SchemeIgnoredSigners.add(new V1SchemeSignerInfo(it2.next()));
            }
        }
    }

    private ApkSigningBlockUtils.Result getApkContentDigests(DataSource dataSource, ApkUtils.ZipSections zipSections, Set<Integer> set, Map<Integer, String> map, Map<Integer, Map<ContentDigestAlgorithm, byte[]>> map2, int i, int i2) throws NoSuchAlgorithmException, IOException {
        return getApkContentDigests(dataSource, zipSections, set, map, map2, i, i2, this.mMaxSdkVersion);
    }

    public Result verifySourceStamp(String str) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                DataSource dataSourceAsDataSource = this.mApkDataSource;
                if (dataSourceAsDataSource == null) {
                    if (this.mApkFile != null) {
                        RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.mApkFile, "r");
                        try {
                            dataSourceAsDataSource = DataSources.asDataSource(randomAccessFile2, 0L, randomAccessFile2.length());
                            randomAccessFile = randomAccessFile2;
                        } catch (IOException e) {
                            e = e;
                            randomAccessFile = randomAccessFile2;
                            Result resultCreateSourceStampResultWithError = createSourceStampResultWithError(Result.SourceStampInfo.SourceStampVerificationStatus.VERIFICATION_ERROR, Issue.UNEXPECTED_EXCEPTION, e);
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException unused) {
                                }
                            }
                            return resultCreateSourceStampResultWithError;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = randomAccessFile2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        throw new IllegalStateException("APK not provided");
                    }
                }
                Result resultVerifySourceStamp = verifySourceStamp(dataSourceAsDataSource, str);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused3) {
                    }
                }
                return resultVerifySourceStamp;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public Result verifySourceStamp() {
        return verifySourceStamp(null);
    }

    public Result verify() throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            DataSource dataSourceAsDataSource = this.mApkDataSource;
            if (dataSourceAsDataSource == null) {
                if (this.mApkFile != null) {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.mApkFile, "r");
                    try {
                        dataSourceAsDataSource = DataSources.asDataSource(randomAccessFile2, 0L, randomAccessFile2.length());
                        randomAccessFile = randomAccessFile2;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                } else {
                    throw new IllegalStateException("APK not provided");
                }
            }
            Result resultVerify = verify(dataSourceAsDataSource);
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return resultVerify;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
