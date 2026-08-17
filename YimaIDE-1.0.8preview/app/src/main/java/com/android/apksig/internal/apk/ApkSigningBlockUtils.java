package com.android.apksig.internal.apk;

import com.android.apksig.ApkVerifier;
import com.android.apksig.KeyConfig;
import com.android.apksig.SignerEngineFactory;
import com.android.apksig.SigningCertificateLineage;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.internal.asn1.Asn1BerParser;
import com.android.apksig.internal.asn1.Asn1DecodingException;
import com.android.apksig.internal.asn1.Asn1DerEncoder;
import com.android.apksig.internal.asn1.Asn1EncodingException;
import com.android.apksig.internal.asn1.Asn1OpaqueObject;
import com.android.apksig.internal.pkcs7.AlgorithmIdentifier;
import com.android.apksig.internal.pkcs7.ContentInfo;
import com.android.apksig.internal.pkcs7.EncapsulatedContentInfo;
import com.android.apksig.internal.pkcs7.IssuerAndSerialNumber;
import com.android.apksig.internal.pkcs7.Pkcs7Constants;
import com.android.apksig.internal.pkcs7.SignedData;
import com.android.apksig.internal.pkcs7.SignerIdentifier;
import com.android.apksig.internal.pkcs7.SignerInfo;
import com.android.apksig.internal.util.ByteBufferDataSource;
import com.android.apksig.internal.util.ChainedDataSource;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.internal.util.VerityTreeBuilder;
import com.android.apksig.internal.util.X509CertificateUtils;
import com.android.apksig.internal.x509.RSAPublicKey;
import com.android.apksig.internal.x509.SubjectPublicKeyInfo;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSinks;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import com.android.apksig.util.RunnablesExecutor;
import com.android.apksig.util.RunnablesProvider;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkSigningBlockUtils {
    public static final int ANDROID_COMMON_PAGE_ALIGNMENT_BYTES = 4096;
    private static final long CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES = 1048576;
    public static final int VERITY_PADDING_BLOCK_ID = 1114793335;
    public static final int VERSION_APK_SIGNATURE_SCHEME_V2 = 2;
    public static final int VERSION_APK_SIGNATURE_SCHEME_V3 = 3;
    public static final int VERSION_APK_SIGNATURE_SCHEME_V31 = 31;
    public static final int VERSION_APK_SIGNATURE_SCHEME_V4 = 4;
    public static final int VERSION_JAR_SIGNATURE_SCHEME = 1;
    public static final int VERSION_SOURCE_STAMP = 0;
    private static final byte[] APK_SIGNING_BLOCK_MAGIC = {65, 80, 75, 32, 83, 105, 103, 32, 66, 108, 111, 99, 107, 32, 52, 50};
    private static final ContentDigestAlgorithm[] V4_CONTENT_DIGEST_ALGORITHMS = {ContentDigestAlgorithm.CHUNKED_SHA512, ContentDigestAlgorithm.VERITY_CHUNKED_SHA256, ContentDigestAlgorithm.CHUNKED_SHA256};

    public static class ChunkDigester implements Runnable {
        private final List<ChunkDigests> chunkDigests;
        private final ChunkSupplier dataSupplier;
        private final DataSink mdSink;
        private final List<MessageDigest> messageDigests;

        private ChunkDigester(ChunkSupplier chunkSupplier, List<ChunkDigests> list) {
            this.dataSupplier = chunkSupplier;
            this.chunkDigests = list;
            this.messageDigests = new ArrayList(list.size());
            Iterator<ChunkDigests> it = list.iterator();
            while (it.hasNext()) {
                try {
                    this.messageDigests.add(it.next().createMessageDigest());
                } catch (NoSuchAlgorithmException e) {
                    rc6.a(e);
                    throw null;
                }
            }
            this.mdSink = DataSinks.asDataSink((MessageDigest[]) this.messageDigests.toArray(new MessageDigest[0]));
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] bArr = new byte[5];
            bArr[0] = -91;
            try {
                ChunkSupplier.Chunk chunk = this.dataSupplier.get();
                while (chunk != null) {
                    int i = chunk.size;
                    if (i > ApkSigningBlockUtils.CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES) {
                        throw new RuntimeException("Chunk size greater than expected: " + i);
                    }
                    ApkSigningBlockUtils.setUnsignedInt32LittleEndian(i, bArr, 1);
                    this.mdSink.consume(bArr, 0, 5);
                    this.mdSink.consume(chunk.data);
                    for (int i2 = 0; i2 < this.chunkDigests.size(); i2++) {
                        ChunkDigests chunkDigests = this.chunkDigests.get(i2);
                        int iDigest = this.messageDigests.get(i2).digest(chunkDigests.concatOfDigestsOfChunks, chunkDigests.getOffset(chunk.chunkIndex), chunkDigests.digestOutputSize);
                        if (iDigest != chunkDigests.digestOutputSize) {
                            throw new RuntimeException("Unexpected output size of " + chunkDigests.algorithm + " digest: " + iDigest);
                        }
                    }
                    chunk = this.dataSupplier.get();
                }
            } catch (IOException | DigestException e) {
                rc6.a(e);
            }
        }
    }

    public static class ChunkDigests {
        private final ContentDigestAlgorithm algorithm;
        private final byte[] concatOfDigestsOfChunks;
        private final int digestOutputSize;

        private ChunkDigests(ContentDigestAlgorithm contentDigestAlgorithm, int i) {
            this.algorithm = contentDigestAlgorithm;
            int chunkDigestOutputSizeBytes = contentDigestAlgorithm.getChunkDigestOutputSizeBytes();
            this.digestOutputSize = chunkDigestOutputSizeBytes;
            byte[] bArr = new byte[(chunkDigestOutputSizeBytes * i) + 5];
            this.concatOfDigestsOfChunks = bArr;
            bArr[0] = 90;
            ApkSigningBlockUtils.setUnsignedInt32LittleEndian(i, bArr, 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MessageDigest createMessageDigest() throws NoSuchAlgorithmException {
            return MessageDigest.getInstance(this.algorithm.getJcaMessageDigestAlgorithm());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getOffset(int i) {
            return (i * this.digestOutputSize) + 5;
        }
    }

    public static class ChunkSupplier implements Supplier<Chunk> {
        private final int[] chunkCounts;
        private final DataSource[] dataSources;
        private final AtomicInteger nextIndex;
        private final int totalChunkCount;

        public static class Chunk {
            private final int chunkIndex;
            private final ByteBuffer data;
            private final int size;

            private Chunk(int i, ByteBuffer byteBuffer, int i2) {
                this.chunkIndex = i;
                this.data = byteBuffer;
                this.size = i2;
            }
        }

        private ChunkSupplier(DataSource[] dataSourceArr) {
            this.dataSources = dataSourceArr;
            this.chunkCounts = new int[dataSourceArr.length];
            int i = 0;
            for (int i2 = 0; i2 < dataSourceArr.length; i2++) {
                long chunkCount = ApkSigningBlockUtils.getChunkCount(dataSourceArr[i2].size(), ApkSigningBlockUtils.CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES);
                if (chunkCount > 2147483647L) {
                    throw new RuntimeException(String.format("Number of chunks in dataSource[%d] is greater than max int.", Integer.valueOf(i2)));
                }
                this.chunkCounts[i2] = (int) chunkCount;
                i = (int) (((long) i) + chunkCount);
            }
            this.totalChunkCount = i;
            this.nextIndex = new AtomicInteger(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.function.Supplier
        public Chunk get() {
            DataSource[] dataSourceArr;
            int andIncrement = this.nextIndex.getAndIncrement();
            if (andIncrement >= 0 && andIncrement < this.totalChunkCount) {
                long j = andIncrement;
                int i = 0;
                while (true) {
                    dataSourceArr = this.dataSources;
                    if (i >= dataSourceArr.length) {
                        break;
                    }
                    int i2 = this.chunkCounts[i];
                    if (j < i2) {
                        break;
                    }
                    j -= (long) i2;
                    i++;
                }
                long size = dataSourceArr[i].size();
                long j2 = j * ApkSigningBlockUtils.CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES;
                int iMin = (int) Math.min(size - j2, ApkSigningBlockUtils.CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES);
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iMin);
                try {
                    this.dataSources[i].copyTo(j2, iMin, byteBufferAllocate);
                    byteBufferAllocate.rewind();
                    return new Chunk(andIncrement, byteBufferAllocate, iMin);
                } catch (IOException e) {
                    mg9.a("Failed to read chunk", e);
                }
            }
            return null;
        }
    }

    public static class NoSupportedSignaturesException extends NoApkSupportedSignaturesException {
        public NoSupportedSignaturesException(String str) {
            super(str);
        }
    }

    public static class Result extends ApkSigResult {
        private final List<ApkVerifier.IssueWithParams> mErrors;
        private final List<ApkVerifier.IssueWithParams> mWarnings;
        public final List<SignerInfo> signers;
        public SigningCertificateLineage signingCertificateLineage;

        public static class SignerInfo extends ApkSignerInfo {
            public int maxSdkVersion;
            public int minSdkVersion;
            public byte[] signedData;
            public SigningCertificateLineage signingCertificateLineage;
            public List<ContentDigest> contentDigests = new ArrayList();
            public Map<ContentDigestAlgorithm, byte[]> verifiedContentDigests = new HashMap();
            public List<Signature> signatures = new ArrayList();
            public Map<SignatureAlgorithm, byte[]> verifiedSignatures = new HashMap();
            public List<AdditionalAttribute> additionalAttributes = new ArrayList();
            private final List<ApkVerifier.IssueWithParams> mWarnings = new ArrayList();
            private final List<ApkVerifier.IssueWithParams> mErrors = new ArrayList();

            public static class AdditionalAttribute {
                private final int mId;
                private final byte[] mValue;

                public AdditionalAttribute(int i, byte[] bArr) {
                    this.mId = i;
                    this.mValue = (byte[]) bArr.clone();
                }

                public int getId() {
                    return this.mId;
                }

                public byte[] getValue() {
                    return (byte[]) this.mValue.clone();
                }
            }

            public static class ContentDigest {
                private final int mSignatureAlgorithmId;
                private final byte[] mValue;

                public ContentDigest(int i, byte[] bArr) {
                    this.mSignatureAlgorithmId = i;
                    this.mValue = bArr;
                }

                public int getSignatureAlgorithmId() {
                    return this.mSignatureAlgorithmId;
                }

                public byte[] getValue() {
                    return this.mValue;
                }
            }

            public static class Signature {
                private final int mAlgorithmId;
                private final byte[] mValue;

                public Signature(int i, byte[] bArr) {
                    this.mAlgorithmId = i;
                    this.mValue = bArr;
                }

                public int getAlgorithmId() {
                    return this.mAlgorithmId;
                }

                public byte[] getValue() {
                    return this.mValue;
                }
            }

            public void addError(ApkVerifier.Issue issue, Object... objArr) {
                this.mErrors.add(new ApkVerifier.IssueWithParams(issue, objArr));
            }

            public void addWarning(ApkVerifier.Issue issue, Object... objArr) {
                this.mWarnings.add(new ApkVerifier.IssueWithParams(issue, objArr));
            }

            @Override // com.android.apksig.internal.apk.ApkSignerInfo
            public boolean containsErrors() {
                return !this.mErrors.isEmpty();
            }

            @Override // com.android.apksig.internal.apk.ApkSignerInfo
            public boolean containsWarnings() {
                return !this.mWarnings.isEmpty();
            }

            @Override // com.android.apksig.internal.apk.ApkSignerInfo
            public List<ApkVerifier.IssueWithParams> getErrors() {
                return this.mErrors;
            }

            @Override // com.android.apksig.internal.apk.ApkSignerInfo
            public List<ApkVerifier.IssueWithParams> getWarnings() {
                return this.mWarnings;
            }
        }

        public Result(int i) {
            super(i);
            this.signingCertificateLineage = null;
            this.signers = new ArrayList();
            this.mWarnings = new ArrayList();
            this.mErrors = new ArrayList();
        }

        public void addError(ApkVerifier.Issue issue, Object... objArr) {
            this.mErrors.add(new ApkVerifier.IssueWithParams(issue, objArr));
        }

        public void addWarning(ApkVerifier.Issue issue, Object... objArr) {
            this.mWarnings.add(new ApkVerifier.IssueWithParams(issue, objArr));
        }

        @Override // com.android.apksig.internal.apk.ApkSigResult
        public boolean containsErrors() {
            if (!this.mErrors.isEmpty()) {
                return true;
            }
            if (this.signers.isEmpty()) {
                return false;
            }
            Iterator<SignerInfo> it = this.signers.iterator();
            while (it.hasNext()) {
                if (it.next().containsErrors()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.android.apksig.internal.apk.ApkSigResult
        public boolean containsWarnings() {
            if (!this.mWarnings.isEmpty()) {
                return true;
            }
            if (this.signers.isEmpty()) {
                return false;
            }
            Iterator<SignerInfo> it = this.signers.iterator();
            while (it.hasNext()) {
                if (it.next().containsWarnings()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.android.apksig.internal.apk.ApkSigResult
        public List<ApkVerifier.IssueWithParams> getErrors() {
            return this.mErrors;
        }

        @Override // com.android.apksig.internal.apk.ApkSigResult
        public List<ApkVerifier.IssueWithParams> getWarnings() {
            return this.mWarnings;
        }
    }

    public static class SignerConfig {
        public List<X509Certificate> certificates;
        public KeyConfig keyConfig;
        public int maxSdkVersion;
        public int minSdkVersion;

        @Deprecated
        public PrivateKey privateKey;
        public List<SignatureAlgorithm> signatureAlgorithms;
        public boolean signerTargetsDevRelease;
        public SigningCertificateLineage signingCertificateLineage;
    }

    public static class SigningSchemeBlockAndDigests {
        public final Map<ContentDigestAlgorithm, byte[]> digestInfo;
        public final Pair<byte[], Integer> signingSchemeBlock;

        public SigningSchemeBlockAndDigests(Pair<byte[], Integer> pair, Map<ContentDigestAlgorithm, byte[]> map) {
            this.signingSchemeBlock = pair;
            this.digestInfo = map;
        }
    }

    public static class SupportedSignature extends ApkSupportedSignature {
        public SupportedSignature(SignatureAlgorithm signatureAlgorithm, byte[] bArr) {
            super(signatureAlgorithm, bArr);
        }
    }

    public static class VerityTreeAndDigest {
        public final ContentDigestAlgorithm contentDigestAlgorithm;
        public final byte[] rootHash;
        public final byte[] tree;

        public VerityTreeAndDigest(ContentDigestAlgorithm contentDigestAlgorithm, byte[] bArr, byte[] bArr2) {
            this.contentDigestAlgorithm = contentDigestAlgorithm;
            this.rootHash = bArr;
            this.tree = bArr2;
        }
    }

    public static /* synthetic */ Runnable a(ChunkSupplier chunkSupplier, List list) {
        return new ChunkDigester(chunkSupplier, list);
    }

    public static void checkByteOrderLittleEndian(ByteBuffer byteBuffer) {
        ApkSigningBlockUtilsLite.checkByteOrderLittleEndian(byteBuffer);
    }

    public static int compareSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm, SignatureAlgorithm signatureAlgorithm2) {
        return ApkSigningBlockUtilsLite.compareSignatureAlgorithm(signatureAlgorithm, signatureAlgorithm2);
    }

    private static void computeApkVerityDigest(DataSource dataSource, DataSource dataSource2, DataSource dataSource3, Map<ContentDigestAlgorithm, byte[]> map) throws NoSuchAlgorithmException, IOException {
        ByteBuffer byteBufferCreateVerityDigestBuffer = createVerityDigestBuffer(true);
        VerityTreeBuilder verityTreeBuilder = new VerityTreeBuilder(new byte[8]);
        try {
            byteBufferCreateVerityDigestBuffer.put(verityTreeBuilder.generateVerityTreeRootHash(dataSource, dataSource2, dataSource3));
            byteBufferCreateVerityDigestBuffer.putLong(dataSource.size() + dataSource2.size() + dataSource3.size());
            map.put(ContentDigestAlgorithm.VERITY_CHUNKED_SHA256, byteBufferCreateVerityDigestBuffer.array());
            verityTreeBuilder.close();
        } catch (Throwable th) {
            try {
                verityTreeBuilder.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static VerityTreeAndDigest computeChunkVerityTreeAndDigest(DataSource dataSource) throws NoSuchAlgorithmException, IOException {
        ByteBuffer byteBufferCreateVerityDigestBuffer = createVerityDigestBuffer(false);
        VerityTreeBuilder verityTreeBuilder = new VerityTreeBuilder(null);
        try {
            ByteBuffer byteBufferGenerateVerityTree = verityTreeBuilder.generateVerityTree(dataSource);
            byteBufferCreateVerityDigestBuffer.put(verityTreeBuilder.getRootHashFromTree(byteBufferGenerateVerityTree));
            VerityTreeAndDigest verityTreeAndDigest = new VerityTreeAndDigest(ContentDigestAlgorithm.VERITY_CHUNKED_SHA256, byteBufferCreateVerityDigestBuffer.array(), byteBufferGenerateVerityTree.array());
            verityTreeBuilder.close();
            return verityTreeAndDigest;
        } catch (Throwable th) {
            try {
                verityTreeBuilder.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static Pair<List<SignerConfig>, Map<ContentDigestAlgorithm, byte[]>> computeContentDigests(RunnablesExecutor runnablesExecutor, DataSource dataSource, DataSource dataSource2, DataSource dataSource3, List<SignerConfig> list) throws SignatureException, NoSuchAlgorithmException, IOException {
        if (list.isEmpty()) {
            w01.a("No signer configs provided. At least one is required");
            return null;
        }
        HashSet hashSet = new HashSet(1);
        Iterator<SignerConfig> it = list.iterator();
        while (it.hasNext()) {
            Iterator<SignatureAlgorithm> it2 = it.next().signatureAlgorithms.iterator();
            while (it2.hasNext()) {
                hashSet.add(it2.next().getContentDigestAlgorithm());
            }
        }
        try {
            return Pair.of(list, computeContentDigests(runnablesExecutor, hashSet, dataSource, dataSource2, dataSource3));
        } catch (IOException e) {
            dk3.a("Failed to read APK being signed", e);
            return null;
        } catch (DigestException e2) {
            throw new SignatureException("Failed to compute digests of APK", e2);
        }
    }

    public static void computeOneMbChunkContentDigests(Set<ContentDigestAlgorithm> set, DataSource[] dataSourceArr, Map<ContentDigestAlgorithm, byte[]> map) throws NoSuchAlgorithmException, DigestException, IOException {
        long j;
        char c;
        int length = dataSourceArr.length;
        int i = 0;
        long chunkCount = 0;
        while (true) {
            j = CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES;
            if (i >= length) {
                break;
            }
            chunkCount += getChunkCount(dataSourceArr[i].size(), CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES);
            i++;
        }
        if (chunkCount > 2147483647L) {
            throw new DigestException("Input too long: " + chunkCount + " chunks");
        }
        int i2 = (int) chunkCount;
        ContentDigestAlgorithm[] contentDigestAlgorithmArr = (ContentDigestAlgorithm[]) set.toArray(new ContentDigestAlgorithm[set.size()]);
        int length2 = contentDigestAlgorithmArr.length;
        MessageDigest[] messageDigestArr = new MessageDigest[length2];
        byte[][] bArr = new byte[contentDigestAlgorithmArr.length][];
        int[] iArr = new int[contentDigestAlgorithmArr.length];
        int i3 = 0;
        while (true) {
            c = 5;
            if (i3 >= contentDigestAlgorithmArr.length) {
                break;
            }
            ContentDigestAlgorithm contentDigestAlgorithm = contentDigestAlgorithmArr[i3];
            int chunkDigestOutputSizeBytes = contentDigestAlgorithm.getChunkDigestOutputSizeBytes();
            iArr[i3] = chunkDigestOutputSizeBytes;
            byte[] bArr2 = new byte[(chunkDigestOutputSizeBytes * i2) + 5];
            bArr2[0] = 90;
            setUnsignedInt32LittleEndian(i2, bArr2, 1);
            bArr[i3] = bArr2;
            messageDigestArr[i3] = MessageDigest.getInstance(contentDigestAlgorithm.getJcaMessageDigestAlgorithm());
            i3++;
        }
        DataSink dataSinkAsDataSink = DataSinks.asDataSink(messageDigestArr);
        byte[] bArr3 = new byte[5];
        bArr3[0] = -91;
        int length3 = dataSourceArr.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length3) {
            int i6 = i5;
            DataSource dataSource = dataSourceArr[i4];
            ContentDigestAlgorithm[] contentDigestAlgorithmArr2 = contentDigestAlgorithmArr;
            int i7 = i6;
            long size = dataSource.size();
            long j2 = 0;
            while (size > 0) {
                byte[][] bArr4 = bArr;
                int[] iArr2 = iArr;
                int iMin = (int) Math.min(size, j);
                setUnsignedInt32LittleEndian(iMin, bArr3, 1);
                for (int i8 = 0; i8 < length2; i8++) {
                    messageDigestArr[i8].update(bArr3);
                }
                long j3 = iMin;
                try {
                    dataSource.feed(j2, j3, dataSinkAsDataSink);
                    int i9 = 0;
                    ContentDigestAlgorithm[] contentDigestAlgorithmArr3 = contentDigestAlgorithmArr2;
                    while (i9 < contentDigestAlgorithmArr3.length) {
                        MessageDigest messageDigest = messageDigestArr[i9];
                        byte[] bArr5 = bArr4[i9];
                        int i10 = iArr2[i9];
                        char c2 = c;
                        int iDigest = messageDigest.digest(bArr5, (i7 * i10) + 5, i10);
                        if (iDigest != i10) {
                            throw new RuntimeException("Unexpected output size of " + messageDigest.getAlgorithm() + " digest: " + iDigest);
                        }
                        i9++;
                        c = c2;
                    }
                    j2 += j3;
                    size -= j3;
                    i7++;
                    contentDigestAlgorithmArr2 = contentDigestAlgorithmArr3;
                    bArr = bArr4;
                    iArr = iArr2;
                    j = CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES;
                } catch (IOException e) {
                    throw new IOException("Failed to read chunk #" + i7, e);
                }
            }
            i4++;
            i5 = i7;
            contentDigestAlgorithmArr = contentDigestAlgorithmArr2;
            j = CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES;
        }
        ContentDigestAlgorithm[] contentDigestAlgorithmArr4 = contentDigestAlgorithmArr;
        byte[][] bArr6 = bArr;
        for (int i11 = 0; i11 < contentDigestAlgorithmArr4.length; i11++) {
            map.put(contentDigestAlgorithmArr4[i11], messageDigestArr[i11].digest(bArr6[i11]));
        }
    }

    public static DataSource copyWithModifiedCDOffset(DataSource dataSource, DataSource dataSource2) throws IOException {
        long size = dataSource.size();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) dataSource2.size());
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        dataSource2.copyTo(0L, (int) dataSource2.size(), byteBufferAllocate);
        byteBufferAllocate.flip();
        ZipUtils.setZipEocdCentralDirectoryOffset(byteBufferAllocate, size);
        return DataSources.asDataSource(byteBufferAllocate);
    }

    private static ByteBuffer createVerityDigestBuffer(boolean z) {
        int chunkDigestOutputSizeBytes = ContentDigestAlgorithm.VERITY_CHUNKED_SHA256.getChunkDigestOutputSizeBytes();
        if (z) {
            chunkDigestOutputSizeBytes += 8;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(chunkDigestOutputSizeBytes);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferAllocate;
    }

    public static byte[] encodeAsLengthPrefixedElement(byte[] bArr) {
        return encodeAsSequenceOfLengthPrefixedElements(new byte[][]{bArr});
    }

    public static byte[] encodeAsSequenceOfLengthPrefixedElements(byte[][] bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length + 4;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        for (byte[] bArr3 : bArr) {
            byteBufferAllocate.putInt(bArr3.length);
            byteBufferAllocate.put(bArr3);
        }
        return byteBufferAllocate.array();
    }

    public static byte[] encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(List<Pair<Integer, byte[]>> list) {
        return ApkSigningBlockUtilsLite.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(list);
    }

    public static List<byte[]> encodeCertificates(List<X509Certificate> list) throws CertificateEncodingException {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<X509Certificate> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getEncoded());
        }
        return arrayList;
    }

    public static byte[] encodePublicKey(PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] encoded = null;
        if ("X.509".equals(publicKey.getFormat())) {
            byte[] encoded2 = publicKey.getEncoded();
            String algorithm = publicKey.getAlgorithm();
            if ("RSA".equals(algorithm) || "1.2.840.113549.1.1.1".equals(algorithm)) {
                try {
                    SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) Asn1BerParser.parse(ByteBuffer.wrap(encoded2), SubjectPublicKeyInfo.class);
                    ByteBuffer byteBuffer = subjectPublicKeyInfo.subjectPublicKey;
                    byte b = byteBuffer.get();
                    RSAPublicKey rSAPublicKey = (RSAPublicKey) Asn1BerParser.parse(byteBuffer, RSAPublicKey.class);
                    if (rSAPublicKey.modulus.compareTo(BigInteger.ZERO) < 0) {
                        byte[] byteArray = rSAPublicKey.modulus.toByteArray();
                        byte[] bArr = new byte[byteArray.length + 1];
                        bArr[0] = 0;
                        System.arraycopy(byteArray, 0, bArr, 1, byteArray.length);
                        rSAPublicKey.modulus = new BigInteger(bArr);
                        byte[] bArrEncode = Asn1DerEncoder.encode(rSAPublicKey);
                        byte[] bArr2 = new byte[bArrEncode.length + 1];
                        bArr2[0] = b;
                        System.arraycopy(bArrEncode, 0, bArr2, 1, bArrEncode.length);
                        subjectPublicKeyInfo.subjectPublicKey = ByteBuffer.wrap(bArr2);
                        encoded2 = Asn1DerEncoder.encode(subjectPublicKeyInfo);
                    }
                } catch (Asn1DecodingException | Asn1EncodingException e) {
                    System.out.println("Caught a exception encoding the public key: " + e);
                    e.printStackTrace();
                }
            }
            encoded = encoded2;
        }
        if (encoded == null) {
            try {
                encoded = ((X509EncodedKeySpec) KeyFactory.getInstance(publicKey.getAlgorithm()).getKeySpec(publicKey, X509EncodedKeySpec.class)).getEncoded();
            } catch (InvalidKeySpecException e2) {
                StringBuilder sb = new StringBuilder("Failed to obtain X.509 encoded form of public key ");
                sb.append(publicKey);
                String name = publicKey.getClass().getName();
                sb.append(" of class ");
                sb.append(name);
                throw new InvalidKeyException(sb.toString(), e2);
            }
        }
        if (encoded != null && encoded.length != 0) {
            return encoded;
        }
        StringBuilder sb2 = new StringBuilder("Failed to obtain X.509 encoded form of public key ");
        sb2.append(publicKey);
        String name2 = publicKey.getClass().getName();
        sb2.append(" of class ");
        sb2.append(name2);
        throw new InvalidKeyException(sb2.toString());
    }

    public static ByteBuffer findApkSignatureSchemeBlock(ByteBuffer byteBuffer, int i, Result result) throws SignatureNotFoundException {
        try {
            return ApkSigningBlockUtilsLite.findApkSignatureSchemeBlock(byteBuffer, i);
        } catch (com.android.apksig.internal.apk.SignatureNotFoundException e) {
            throw new SignatureNotFoundException(e.getMessage());
        }
    }

    public static SignatureInfo findSignature(DataSource dataSource, ApkUtils.ZipSections zipSections, int i, Result result) throws SignatureNotFoundException, IOException {
        try {
            return ApkSigningBlockUtilsLite.findSignature(dataSource, zipSections, i);
        } catch (com.android.apksig.internal.apk.SignatureNotFoundException e) {
            throw new SignatureNotFoundException(e.getMessage());
        }
    }

    public static byte[] generateApkSigningBlock(List<Pair<byte[], Integer>> list) {
        ByteBuffer byteBufferOrder;
        Iterator<Pair<byte[], Integer>> it = list.iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().getFirst().length + 12;
        }
        int i = length + 32;
        int i2 = i % 4096;
        if (i2 != 0) {
            int i3 = 4096 - i2;
            if (i3 < 12) {
                i3 = 8192 - i2;
            }
            byteBufferOrder = ByteBuffer.allocate(i3).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.putLong(i3 - 8);
            byteBufferOrder.putInt(VERITY_PADDING_BLOCK_ID);
            byteBufferOrder.rewind();
            i += i3;
        } else {
            byteBufferOrder = null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        long j = ((long) i) - 8;
        byteBufferAllocate.putLong(j);
        for (Pair<byte[], Integer> pair : list) {
            byte[] first = pair.getFirst();
            int iIntValue = pair.getSecond().intValue();
            byteBufferAllocate.putLong(((long) first.length) + 4);
            byteBufferAllocate.putInt(iIntValue);
            byteBufferAllocate.put(first);
        }
        if (byteBufferOrder != null) {
            byteBufferAllocate.put(byteBufferOrder);
        }
        byteBufferAllocate.putLong(j);
        byteBufferAllocate.put(APK_SIGNING_BLOCK_MAGIC);
        return byteBufferAllocate.array();
    }

    public static Pair<DataSource, Integer> generateApkSigningBlockPadding(DataSource dataSource, boolean z) {
        int i = 0;
        if (z && dataSource.size() % 4096 != 0) {
            int size = (int) (4096 - (dataSource.size() % 4096));
            i = size;
            dataSource = new ChainedDataSource(dataSource, DataSources.asDataSource(ByteBuffer.allocate(size)));
        }
        return Pair.of(dataSource, Integer.valueOf(i));
    }

    public static byte[] generatePkcs7DerEncodedMessage(byte[] bArr, ByteBuffer byteBuffer, List<X509Certificate> list, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws Asn1EncodingException, CertificateEncodingException {
        SignerInfo signerInfo = new SignerInfo();
        signerInfo.version = 1;
        X509Certificate x509Certificate = list.get(0);
        signerInfo.sid = new SignerIdentifier(new IssuerAndSerialNumber(new Asn1OpaqueObject(x509Certificate.getIssuerX500Principal().getEncoded()), x509Certificate.getSerialNumber()));
        signerInfo.digestAlgorithm = algorithmIdentifier;
        signerInfo.signatureAlgorithm = algorithmIdentifier2;
        signerInfo.signature = ByteBuffer.wrap(bArr);
        SignedData signedData = new SignedData();
        signedData.certificates = new ArrayList(list.size());
        Iterator<X509Certificate> it = list.iterator();
        while (it.hasNext()) {
            signedData.certificates.add(new Asn1OpaqueObject(it.next().getEncoded()));
        }
        signedData.version = 1;
        signedData.digestAlgorithms = Collections.singletonList(algorithmIdentifier);
        EncapsulatedContentInfo encapsulatedContentInfo = new EncapsulatedContentInfo(Pkcs7Constants.OID_DATA);
        signedData.encapContentInfo = encapsulatedContentInfo;
        encapsulatedContentInfo.content = byteBuffer;
        signedData.signerInfos = Collections.singletonList(signerInfo);
        ContentInfo contentInfo = new ContentInfo();
        contentInfo.contentType = Pkcs7Constants.OID_SIGNED_DATA;
        contentInfo.content = new Asn1OpaqueObject(Asn1DerEncoder.encode(signedData));
        return Asn1DerEncoder.encode(contentInfo);
    }

    public static List<Pair<Integer, byte[]>> generateSignaturesOverData(SignerConfig signerConfig, byte[] bArr) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        ArrayList arrayList = new ArrayList(signerConfig.signatureAlgorithms.size());
        PublicKey publicKey = signerConfig.certificates.get(0).getPublicKey();
        for (SignatureAlgorithm signatureAlgorithm : signerConfig.signatureAlgorithms) {
            Pair<String, ? extends AlgorithmParameterSpec> jcaSignatureAlgorithmAndParams = signatureAlgorithm.getJcaSignatureAlgorithmAndParams();
            String first = jcaSignatureAlgorithmAndParams.getFirst();
            AlgorithmParameterSpec second = jcaSignatureAlgorithmAndParams.getSecond();
            try {
                byte[] bArrSign = SignerEngineFactory.getImplementation(signerConfig.keyConfig, first, second).sign(bArr);
                try {
                    try {
                        Signature signature = Signature.getInstance(first);
                        signature.initVerify(publicKey);
                        if (second != null) {
                            signature.setParameter(second);
                        }
                        signature.update(bArr);
                        if (!signature.verify(bArrSign)) {
                            throw new SignatureException("Failed to verify generated " + first + " signature using public key from certificate");
                        }
                        arrayList.add(Pair.of(Integer.valueOf(signatureAlgorithm.getId()), bArrSign));
                    } catch (InvalidKeyException e) {
                        throw new InvalidKeyException("Failed to verify generated " + first + " signature using public key from certificate", e);
                    }
                } catch (InvalidAlgorithmParameterException | SignatureException e2) {
                    throw new SignatureException("Failed to verify generated " + first + " signature using public key from certificate", e2);
                }
            } catch (InvalidAlgorithmParameterException | SignatureException e3) {
                throw new SignatureException("Failed to sign using " + first, e3);
            } catch (InvalidKeyException e4) {
                throw new InvalidKeyException("Failed to sign using " + first, e4);
            }
        }
        return arrayList;
    }

    public static List<Pair<List<X509Certificate>, byte[]>> getApkSignatureBlockSigners(byte[] bArr) throws ApkFormatException, CertificateException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer lengthPrefixedSlice = getLengthPrefixedSlice(byteBufferWrap);
        ArrayList arrayList = new ArrayList();
        while (lengthPrefixedSlice.hasRemaining()) {
            ByteBuffer lengthPrefixedSlice2 = getLengthPrefixedSlice(lengthPrefixedSlice);
            byte[] bArr2 = new byte[lengthPrefixedSlice2.remaining()];
            lengthPrefixedSlice2.get(bArr2);
            lengthPrefixedSlice2.rewind();
            ByteBuffer lengthPrefixedSlice3 = getLengthPrefixedSlice(lengthPrefixedSlice2);
            getLengthPrefixedSlice(lengthPrefixedSlice3);
            ByteBuffer lengthPrefixedSlice4 = getLengthPrefixedSlice(lengthPrefixedSlice3);
            ArrayList arrayList2 = new ArrayList();
            while (lengthPrefixedSlice4.hasRemaining()) {
                int i = lengthPrefixedSlice4.getInt();
                byte[] bArr3 = new byte[i];
                if (i > lengthPrefixedSlice4.remaining()) {
                    fq7.a("Cert index ", arrayList2.size() + 1, " under signer index ", arrayList.size() + 1, " size out of range: ", i);
                    return null;
                }
                lengthPrefixedSlice4.get(bArr3);
                arrayList2.add(new GuaranteedEncodedFormX509Certificate(X509CertificateUtils.generateCertificate(bArr3), bArr3));
            }
            arrayList.add(Pair.of(arrayList2, bArr2));
        }
        return arrayList;
    }

    public static List<Pair<byte[], Integer>> getApkSignatureBlocks(DataSource dataSource) throws IOException {
        long size = dataSource.size();
        if (dataSource.size() > 2147483647L || size < 32) {
            t01.a("APK signing block size out of range: ", size);
            return null;
        }
        ByteBuffer byteBuffer = dataSource.getByteBuffer(8L, ((int) dataSource.size()) - 32);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        while (byteBuffer.hasRemaining()) {
            long j = byteBuffer.getLong();
            if (j > 2147483647L || j < 4) {
                throw new IllegalArgumentException("Block index " + (arrayList.size() + 1) + " size out of range: " + j);
            }
            int i = byteBuffer.getInt();
            byte[] bArr = new byte[((int) j) - 4];
            byteBuffer.get(bArr);
            arrayList.add(Pair.of(bArr, Integer.valueOf(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getChunkCount(long j, long j2) {
        return ((j + j2) - 1) / j2;
    }

    public static ByteBuffer getLengthPrefixedSlice(ByteBuffer byteBuffer) throws ApkFormatException {
        return ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
    }

    public static <T extends ApkSupportedSignature> List<T> getSignaturesToVerify(List<T> list, int i, int i2, boolean z) throws NoSupportedSignaturesException {
        try {
            return ApkSigningBlockUtilsLite.getSignaturesToVerify(list, i, i2, z);
        } catch (NoApkSupportedSignaturesException e) {
            throw new NoSupportedSignaturesException(e.getMessage());
        }
    }

    public static byte[] pickBestDigestForV4(Map<ContentDigestAlgorithm, byte[]> map) {
        for (ContentDigestAlgorithm contentDigestAlgorithm : V4_CONTENT_DIGEST_ALGORITHMS) {
            if (map.containsKey(contentDigestAlgorithm)) {
                return map.get(contentDigestAlgorithm);
            }
        }
        return null;
    }

    public static byte[] readLengthPrefixedByteArray(ByteBuffer byteBuffer) throws ApkFormatException {
        return ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setUnsignedInt32LittleEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
        bArr[i2 + 2] = (byte) ((i >> 16) & 255);
        bArr[i2 + 3] = (byte) ((i >> 24) & 255);
    }

    public static String toHex(byte[] bArr) {
        return ApkSigningBlockUtilsLite.toHex(bArr);
    }

    public static void verifyIntegrity(RunnablesExecutor runnablesExecutor, DataSource dataSource, DataSource dataSource2, ByteBuffer byteBuffer, Set<ContentDigestAlgorithm> set, Result result) throws NoSuchAlgorithmException, IOException {
        if (set.isEmpty()) {
            f63.a("No content digests found");
            return;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        int iPosition = byteBuffer.position();
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(byteBuffer);
        byteBufferAllocate.flip();
        byteBuffer.position(iPosition);
        ZipUtils.setZipEocdCentralDirectoryOffset(byteBufferAllocate, dataSource.size());
        try {
            Map<ContentDigestAlgorithm, byte[]> mapComputeContentDigests = computeContentDigests(runnablesExecutor, set, dataSource, dataSource2, new ByteBufferDataSource(byteBufferAllocate));
            if (mapComputeContentDigests.containsKey(ContentDigestAlgorithm.VERITY_CHUNKED_SHA256)) {
                if (dataSource.size() % 4096 != 0) {
                    throw new RuntimeException("APK Signing Block is not aligned on 4k boundary: " + dataSource.size());
                }
                long zipEocdCentralDirectoryOffset = ZipUtils.getZipEocdCentralDirectoryOffset(byteBuffer) - dataSource.size();
                if (zipEocdCentralDirectoryOffset % 4096 != 0) {
                    throw new RuntimeException("APK Signing Block size is not multiple of page size: " + zipEocdCentralDirectoryOffset);
                }
            }
            if (!set.equals(mapComputeContentDigests.keySet())) {
                StringBuilder sb = new StringBuilder("Mismatch between sets of requested and computed content digests . Requested: ");
                sb.append(set);
                Set<ContentDigestAlgorithm> setKeySet = mapComputeContentDigests.keySet();
                sb.append(", computed: ");
                sb.append(setKeySet);
                throw new RuntimeException(sb.toString());
            }
            for (Result.SignerInfo signerInfo : result.signers) {
                for (Result.SignerInfo.ContentDigest contentDigest : signerInfo.contentDigests) {
                    SignatureAlgorithm signatureAlgorithmFindById = SignatureAlgorithm.findById(contentDigest.getSignatureAlgorithmId());
                    if (signatureAlgorithmFindById != null) {
                        ContentDigestAlgorithm contentDigestAlgorithm = signatureAlgorithmFindById.getContentDigestAlgorithm();
                        if (set.contains(contentDigestAlgorithm)) {
                            byte[] value = contentDigest.getValue();
                            byte[] bArr = mapComputeContentDigests.get(contentDigestAlgorithm);
                            if (Arrays.equals(value, bArr)) {
                                signerInfo.verifiedContentDigests.put(contentDigestAlgorithm, bArr);
                            } else {
                                int i = result.signatureSchemeVersion;
                                if (i == 2) {
                                    signerInfo.addError(ApkVerifier.Issue.V2_SIG_APK_DIGEST_DID_NOT_VERIFY, contentDigestAlgorithm, toHex(value), toHex(bArr));
                                } else if (i == 3) {
                                    signerInfo.addError(ApkVerifier.Issue.V3_SIG_APK_DIGEST_DID_NOT_VERIFY, contentDigestAlgorithm, toHex(value), toHex(bArr));
                                }
                            }
                        }
                    }
                }
            }
        } catch (DigestException e) {
            g3c.a("Failed to compute content digests", e);
        }
    }

    public static class SignatureNotFoundException extends Exception {
        private static final long serialVersionUID = 1;

        public SignatureNotFoundException(String str) {
            super(str);
        }

        public SignatureNotFoundException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static <T extends ApkSupportedSignature> List<T> getSignaturesToVerify(List<T> list, int i, int i2) throws NoSupportedSignaturesException {
        return getSignaturesToVerify(list, i, i2, false);
    }

    public static byte[] encodeAsSequenceOfLengthPrefixedElements(List<byte[]> list) {
        return encodeAsSequenceOfLengthPrefixedElements((byte[][]) list.toArray(new byte[list.size()][]));
    }

    public static Map<ContentDigestAlgorithm, byte[]> computeContentDigests(RunnablesExecutor runnablesExecutor, Set<ContentDigestAlgorithm> set, DataSource dataSource, DataSource dataSource2, DataSource dataSource3) throws NoSuchAlgorithmException, DigestException, IOException {
        HashMap map = new HashMap();
        HashSet hashSet = new HashSet();
        for (ContentDigestAlgorithm contentDigestAlgorithm : set) {
            if (contentDigestAlgorithm == ContentDigestAlgorithm.CHUNKED_SHA256 || contentDigestAlgorithm == ContentDigestAlgorithm.CHUNKED_SHA512) {
                hashSet.add(contentDigestAlgorithm);
            }
        }
        computeOneMbChunkContentDigests(runnablesExecutor, hashSet, new DataSource[]{dataSource, dataSource2, dataSource3}, map);
        if (set.contains(ContentDigestAlgorithm.VERITY_CHUNKED_SHA256)) {
            computeApkVerityDigest(dataSource, dataSource2, dataSource3, map);
        }
        return map;
    }

    public static void computeOneMbChunkContentDigests(RunnablesExecutor runnablesExecutor, Set<ContentDigestAlgorithm> set, DataSource[] dataSourceArr, Map<ContentDigestAlgorithm, byte[]> map) throws NoSuchAlgorithmException, DigestException {
        long chunkCount = 0;
        for (DataSource dataSource : dataSourceArr) {
            chunkCount += getChunkCount(dataSource.size(), CONTENT_DIGESTED_CHUNK_MAX_SIZE_BYTES);
        }
        if (chunkCount <= 2147483647L) {
            int i = (int) chunkCount;
            final ArrayList<ChunkDigests> arrayList = new ArrayList(set.size());
            Iterator<ContentDigestAlgorithm> it = set.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else {
                    arrayList.add(new ChunkDigests(it.next(), i));
                }
            }
            final ChunkSupplier chunkSupplier = new ChunkSupplier(dataSourceArr);
            runnablesExecutor.execute(new RunnablesProvider() { // from class: com.android.apksig.internal.apk.a
                @Override // com.android.apksig.util.RunnablesProvider
                public final Runnable createRunnable() {
                    return ApkSigningBlockUtils.a(chunkSupplier, arrayList);
                }
            });
            for (ChunkDigests chunkDigests : arrayList) {
                map.put(chunkDigests.algorithm, chunkDigests.createMessageDigest().digest(chunkDigests.concatOfDigestsOfChunks));
            }
            return;
        }
        throw new DigestException("Input too long: " + chunkCount + " chunks");
    }
}
