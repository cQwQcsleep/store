package com.intellij.util;

import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationKt;
import com.intellij.openapi.util.ThreadLocalCachedByteArray;
import com.intellij.openapi.util.io.DataInputOutputUtilRt;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CompressionUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final boolean DUMP_COMPRESSION_STATS;
    private static final LZ4Compressor compressor;
    private static final LZ4FastDecompressor decompressor;
    private static final AtomicInteger myCompressionRequests;
    private static final AtomicLong myCompressionTime;
    private static final AtomicLong myDecompressedSize;
    private static final AtomicInteger myDecompressionRequests;
    private static final AtomicLong myDecompressionTime;
    private static final AtomicLong mySizeAfterCompression;
    private static final AtomicLong mySizeBeforeCompression;
    private static final ThreadLocalCachedByteArray spareBufferLocal = new ThreadLocalCachedByteArray();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
                str = "@NotNull method %s.%s must not return null";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 13:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
                i2 = 2;
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 13:
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 3:
                objArr[0] = "bytes";
                break;
            case 2:
            default:
                objArr[0] = "out";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "in";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
                objArr[0] = "com/intellij/util/CompressionUtil";
                break;
            case 9:
                objArr[0] = "string";
                break;
            case 13:
                objArr[0] = "compressed";
                break;
        }
        switch (i) {
            case 5:
                objArr[1] = "readCompressedWithoutOriginalBufferLength";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 13:
            default:
                objArr[1] = "com/intellij/util/CompressionUtil";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[1] = "readCompressed";
                break;
            case 10:
            case 11:
            case 12:
                objArr[1] = "compressStringRawBytes";
                break;
            case 14:
                objArr[1] = "uncompressStringRawBytes";
                break;
        }
        switch (i) {
            case 2:
            case 3:
                objArr[2] = "writeCompressedWithoutOriginalBufferLength";
                break;
            case 4:
                objArr[2] = "readCompressedWithoutOriginalBufferLength";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "readCompressed";
                break;
            case 9:
                objArr[2] = "compressStringRawBytes";
                break;
            case 13:
                objArr[2] = "uncompressStringRawBytes";
                break;
            default:
                objArr[2] = "writeCompressed";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
                throw new IllegalStateException(str2);
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 13:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    static {
        LZ4Compressor lZ4CompressorInvoke;
        if (Boolean.getBoolean("idea.use.native.compression")) {
            LZ4Factory lZ4FactoryFastestInstance = LZ4Factory.fastestInstance();
            compressor = lZ4FactoryFastestInstance.fastCompressor();
            decompressor = lZ4FactoryFastestInstance.fastDecompressor();
        } else {
            LZ4FastDecompressor lZ4FastDecompressorInvoke = null;
            try {
                Class<?> clsLoadClass = CompressionUtil.class.getClassLoader().loadClass("com.intellij.util.io.LZ4Compressor");
                MethodHandles.Lookup lookup = MethodHandles.lookup();
                lZ4CompressorInvoke = (LZ4Compressor) lookup.findStaticGetter(clsLoadClass, "INSTANCE", clsLoadClass).invoke();
                try {
                    Class<?> clsLoadClass2 = CompressionUtil.class.getClassLoader().loadClass("com.intellij.util.io.LZ4Decompressor");
                    lZ4FastDecompressorInvoke = (LZ4FastDecompressor) lookup.findStaticGetter(clsLoadClass2, "INSTANCE", clsLoadClass2).invoke();
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                lZ4CompressorInvoke = null;
            }
            if (lZ4CompressorInvoke == null || lZ4FastDecompressorInvoke == null) {
                LZ4Factory lZ4FactoryFastestJavaInstance = LZ4Factory.fastestJavaInstance();
                compressor = lZ4FactoryFastestJavaInstance.fastCompressor();
                decompressor = lZ4FactoryFastestJavaInstance.fastDecompressor();
            } else {
                compressor = lZ4CompressorInvoke;
                decompressor = lZ4FastDecompressorInvoke;
            }
        }
        myCompressionRequests = new AtomicInteger();
        myCompressionTime = new AtomicLong();
        myDecompressionRequests = new AtomicInteger();
        myDecompressionTime = new AtomicLong();
        myDecompressedSize = new AtomicLong();
        mySizeBeforeCompression = new AtomicLong();
        mySizeAfterCompression = new AtomicLong();
        DUMP_COMPRESSION_STATS = SystemProperties.getBooleanProperty("idea.dump.compression.stats", false);
    }

    public static LZ4Compressor compressor() {
        return compressor;
    }

    public static LZ4FastDecompressor decompressor() {
        return decompressor;
    }

    public static byte[] readCompressed(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(6);
        }
        int i = DataInputOutputUtilRt.readINT(dataInput);
        if (i >= 0) {
            byte[] bArr = new byte[i];
            dataInput.readFully(bArr);
            return bArr;
        }
        int i2 = -i;
        byte[] buffer = spareBufferLocal.getBuffer(i2);
        int i3 = DataInputOutputUtilRt.readINT(dataInput) + i2;
        dataInput.readFully(buffer, 0, i2);
        byte[] bArr2 = new byte[i3];
        decompressor().decompress(buffer, 0, bArr2, 0, i3);
        return bArr2;
    }

    public static byte[] readCompressedWithoutOriginalBufferLength(DataInput dataInput, int i) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(4);
        }
        int i2 = DataInputOutputUtilRt.readINT(dataInput);
        byte[] buffer = spareBufferLocal.getBuffer(i2);
        dataInput.readFully(buffer, 0, i2);
        int iIncrementAndGet = myDecompressionRequests.incrementAndGet();
        boolean z = DUMP_COMPRESSION_STATS;
        long jNanoTime = z ? System.nanoTime() : 0L;
        byte[] bArrDecompress = decompressor().decompress(buffer, 0, i);
        long jNanoTime2 = (z ? System.nanoTime() : 0L) - jNanoTime;
        long jAddAndGet = myDecompressedSize.addAndGet(i2);
        long jAddAndGet2 = myDecompressionTime.addAndGet(jNanoTime2);
        if (z && (iIncrementAndGet & 8191) == 0) {
            System.out.println("Decompressed " + iIncrementAndGet + " times, size: " + jAddAndGet + " for " + (jAddAndGet2 / AnimationKt.MillisToNanos) + "ms");
        }
        if (bArrDecompress == null) {
            $$$reportNull$$$0(5);
        }
        return bArrDecompress;
    }

    public static int writeCompressed(DataOutput dataOutput, byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2;
        int i3;
        int i4;
        if (dataOutput == null) {
            $$$reportNull$$$0(0);
        }
        if (bArr == null) {
            $$$reportNull$$$0(1);
        }
        if (i2 > 64) {
            LZ4Compressor lZ4CompressorCompressor = compressor();
            byte[] buffer = spareBufferLocal.getBuffer(lZ4CompressorCompressor.maxCompressedLength(i2));
            bArr2 = bArr;
            i3 = i;
            i4 = i2;
            int iCompress = lZ4CompressorCompressor.compress(bArr2, i3, i4, buffer, 0);
            if (iCompress < i4) {
                DataInputOutputUtilRt.writeINT(dataOutput, -iCompress);
                DataInputOutputUtilRt.writeINT(dataOutput, i4 - iCompress);
                dataOutput.write(buffer, 0, iCompress);
                return iCompress;
            }
        } else {
            bArr2 = bArr;
            i3 = i;
            i4 = i2;
        }
        DataInputOutputUtilRt.writeINT(dataOutput, i4);
        dataOutput.write(bArr2, i3, i4);
        return i4;
    }

    public static int writeCompressedWithoutOriginalBufferLength(DataOutput dataOutput, byte[] bArr, int i) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(2);
        }
        if (bArr == null) {
            $$$reportNull$$$0(3);
        }
        boolean z = DUMP_COMPRESSION_STATS;
        long jNanoTime = z ? System.nanoTime() : 0L;
        LZ4Compressor lZ4CompressorCompressor = compressor();
        byte[] buffer = spareBufferLocal.getBuffer(lZ4CompressorCompressor.maxCompressedLength(i));
        int iCompress = lZ4CompressorCompressor.compress(bArr, 0, i, buffer, 0);
        long jNanoTime2 = (z ? System.nanoTime() : 0L) - jNanoTime;
        AtomicLong atomicLong = mySizeAfterCompression;
        atomicLong.addAndGet(iCompress);
        AtomicLong atomicLong2 = mySizeBeforeCompression;
        atomicLong2.addAndGet(i);
        int iIncrementAndGet = myCompressionRequests.incrementAndGet();
        long jAddAndGet = myCompressionTime.addAndGet(jNanoTime2);
        if (z && (iIncrementAndGet & 8191) == 0) {
            System.out.println("Compressed " + iIncrementAndGet + " times, size:" + atomicLong2 + "->" + atomicLong + " for " + (jAddAndGet / AnimationKt.MillisToNanos) + "ms");
        }
        DataInputOutputUtilRt.writeINT(dataOutput, iCompress);
        dataOutput.write(buffer, 0, iCompress);
        return iCompress;
    }
}
