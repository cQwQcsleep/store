package com.android.apksig.internal.util;

import com.android.apksig.internal.util.VerityTreeBuilder;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class VerityTreeBuilder implements AutoCloseable {
    private static final int CHUNK_SIZE = 4096;
    private static final int DIGEST_PARALLELISM = Math.min(32, Runtime.getRuntime().availableProcessors());
    private static final String JCA_ALGORITHM = "SHA-256";
    private static final int MAX_OUTSTANDING_CHUNKS = 4;
    private static final int MAX_PREFETCH_CHUNKS = 1024;
    private static final int MIN_CHUNKS_PER_WORKER = 8;
    private final ExecutorService mExecutor;
    private final MessageDigest mMd;
    private final byte[] mSalt;

    public VerityTreeBuilder(byte[] bArr) throws NoSuchAlgorithmException {
        int i = DIGEST_PARALLELISM;
        this.mExecutor = new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(4), new ThreadPoolExecutor.CallerRunsPolicy());
        this.mSalt = bArr;
        this.mMd = getNewMessageDigest();
    }

    public static /* synthetic */ void a(VerityTreeBuilder verityTreeBuilder, ByteBuffer byteBuffer, int i, byte[][] bArr, Phaser phaser) {
        MessageDigest messageDigestCloneMessageDigest = verityTreeBuilder.cloneMessageDigest();
        int iCapacity = byteBuffer.capacity();
        int i2 = 0;
        while (i2 < iCapacity) {
            int i3 = i2 + 4096;
            bArr[i] = verityTreeBuilder.saltedDigest(messageDigestCloneMessageDigest, slice(byteBuffer, i2, i3));
            i++;
            i2 = i3;
        }
        phaser.arriveAndDeregister();
    }

    private static int[] calculateLevelOffset(long j, int i) {
        ArrayList arrayList = new ArrayList();
        do {
            j = divideRoundup(j, 4096L) * ((long) i);
            arrayList.add(Long.valueOf(divideRoundup(j, 4096L) * 4096));
        } while (j > 4096);
        int[] iArr = new int[arrayList.size() + 1];
        int i2 = 0;
        iArr[0] = 0;
        while (i2 < arrayList.size()) {
            int i3 = i2 + 1;
            iArr[i3] = iArr[i2] + Math.toIntExact(((Long) arrayList.get((arrayList.size() - i2) - 1)).longValue());
            i2 = i3;
        }
        return iArr;
    }

    private MessageDigest cloneMessageDigest() {
        try {
            try {
                return (MessageDigest) this.mMd.clone();
            } catch (CloneNotSupportedException unused) {
                return getNewMessageDigest();
            }
        } catch (NoSuchAlgorithmException e) {
            mg9.a("Failed to obtain an instance of a previously available message digest", e);
            return null;
        }
    }

    private void digestDataByChunks(DataSource dataSource, DataSink dataSink) throws IOException {
        long size = dataSource.size();
        long j = 4096;
        int iDivideRoundup = (int) divideRoundup(size, 4096L);
        final byte[][] bArr = new byte[iDivideRoundup][];
        final Phaser phaser = new Phaser(1);
        long j2 = 0;
        final int i = 0;
        while (j2 < size) {
            int iMin = (int) (Math.min(4194304 + j2, size) - j2);
            long j3 = iMin;
            int iDivideRoundup2 = (int) divideRoundup(j3, j);
            final ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iDivideRoundup2 * 4096);
            dataSource.copyTo(j2, iMin, byteBufferAllocate);
            byteBufferAllocate.rewind();
            Runnable runnable = new Runnable() { // from class: nbf
                @Override // java.lang.Runnable
                public final void run() {
                    VerityTreeBuilder.a(this.b, byteBufferAllocate, i, bArr, phaser);
                }
            };
            phaser.register();
            this.mExecutor.execute(runnable);
            i += iDivideRoundup2;
            j2 += j3;
            j = 4096;
        }
        phaser.arriveAndAwaitAdvance();
        for (int i2 = 0; i2 < iDivideRoundup; i2++) {
            byte[] bArr2 = bArr[i2];
            dataSink.consume(bArr2, 0, bArr2.length);
        }
    }

    private static long divideRoundup(long j, long j2) {
        return ((j + j2) - 1) / j2;
    }

    private static MessageDigest getNewMessageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(JCA_ALGORITHM);
    }

    private byte[] saltedDigest(MessageDigest messageDigest, ByteBuffer byteBuffer) {
        messageDigest.reset();
        byte[] bArr = this.mSalt;
        if (bArr != null) {
            messageDigest.update(bArr);
        }
        messageDigest.update(byteBuffer);
        return messageDigest.digest();
    }

    private static ByteBuffer slice(ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(0);
        byteBufferDuplicate.limit(i2);
        byteBufferDuplicate.position(i);
        return byteBufferDuplicate.slice();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mExecutor.shutdownNow();
    }

    public ByteBuffer generateVerityTree(DataSource dataSource) throws IOException {
        DataSource dataSourceAsDataSource;
        int digestLength = this.mMd.getDigestLength();
        int[] iArrCalculateLevelOffset = calculateLevelOffset(dataSource.size(), digestLength);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iArrCalculateLevelOffset[iArrCalculateLevelOffset.length - 1]);
        for (int length = iArrCalculateLevelOffset.length - 2; length >= 0; length--) {
            int i = length + 1;
            ByteBufferSink byteBufferSink = new ByteBufferSink(slice(byteBufferAllocate, iArrCalculateLevelOffset[length], iArrCalculateLevelOffset[i]));
            if (length == iArrCalculateLevelOffset.length - 2) {
                digestDataByChunks(dataSource, byteBufferSink);
                dataSourceAsDataSource = dataSource;
            } else {
                dataSourceAsDataSource = DataSources.asDataSource(slice(byteBufferAllocate.asReadOnlyBuffer(), iArrCalculateLevelOffset[i], iArrCalculateLevelOffset[length + 2]));
                digestDataByChunks(dataSourceAsDataSource, byteBufferSink);
            }
            int iDivideRoundup = (int) ((divideRoundup(dataSourceAsDataSource.size(), 4096L) * ((long) digestLength)) % 4096);
            if (iDivideRoundup > 0) {
                int i2 = 4096 - iDivideRoundup;
                byteBufferSink.consume(new byte[i2], 0, i2);
            }
        }
        return byteBufferAllocate;
    }

    public byte[] generateVerityTreeRootHash(DataSource dataSource, DataSource dataSource2, DataSource dataSource3) throws IOException {
        if (dataSource.size() % 4096 != 0) {
            throw new IllegalStateException("APK Signing Block size not a multiple of 4096: " + dataSource.size());
        }
        long size = dataSource.size();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) dataSource3.size());
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        dataSource3.copyTo(0L, (int) dataSource3.size(), byteBufferAllocate);
        byteBufferAllocate.flip();
        ZipUtils.setZipEocdCentralDirectoryOffset(byteBufferAllocate, size);
        return generateVerityTreeRootHash(new ChainedDataSource(dataSource, dataSource2, DataSources.asDataSource(byteBufferAllocate)));
    }

    public byte[] getRootHashFromTree(ByteBuffer byteBuffer) throws IOException {
        return saltedDigest(slice(byteBuffer.asReadOnlyBuffer(), 0, 4096));
    }

    private byte[] saltedDigest(ByteBuffer byteBuffer) {
        return saltedDigest(this.mMd, byteBuffer);
    }

    public byte[] generateVerityTreeRootHash(DataSource dataSource) throws IOException {
        return getRootHashFromTree(generateVerityTree(dataSource));
    }
}
