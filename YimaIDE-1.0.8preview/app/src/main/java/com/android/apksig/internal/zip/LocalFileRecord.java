package com.android.apksig.internal.zip;

import com.android.apksig.internal.util.ByteBufferSink;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import defpackage.bf9;
import defpackage.cf9;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class LocalFileRecord {
    private static final int COMPRESSED_SIZE_OFFSET = 18;
    private static final int CRC32_OFFSET = 14;
    private static final int DATA_DESCRIPTOR_SIGNATURE = 134695760;
    private static final int DATA_DESCRIPTOR_SIZE_BYTES_WITHOUT_SIGNATURE = 12;
    private static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocate(0);
    private static final int EXTRA_LENGTH_OFFSET = 28;
    private static final int GP_FLAGS_OFFSET = 6;
    private static final int HEADER_SIZE_BYTES = 30;
    private static final int NAME_LENGTH_OFFSET = 26;
    private static final int NAME_OFFSET = 30;
    private static final int RECORD_SIGNATURE = 67324752;
    private static final int UNCOMPRESSED_SIZE_OFFSET = 22;
    private final boolean mDataCompressed;
    private final long mDataSize;
    private final int mDataStartOffset;
    private final ByteBuffer mExtra;
    private final String mName;
    private final int mNameSizeBytes;
    private final long mSize;
    private final long mStartOffsetInArchive;
    private final long mUncompressedDataSize;

    private LocalFileRecord(String str, int i, ByteBuffer byteBuffer, long j, long j2, int i2, long j3, boolean z, long j4) {
        this.mName = str;
        this.mNameSizeBytes = i;
        this.mExtra = byteBuffer;
        this.mStartOffsetInArchive = j;
        this.mSize = j2;
        this.mDataStartOffset = i2;
        this.mDataSize = j3;
        this.mDataCompressed = z;
        this.mUncompressedDataSize = j4;
    }

    private static LocalFileRecord getRecord(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j, boolean z, boolean z2) throws IOException, ZipFormatException {
        int i;
        long j2;
        String name = centralDirectoryRecord.getName();
        int nameSizeBytes = centralDirectoryRecord.getNameSizeBytes();
        int i2 = nameSizeBytes + 30;
        long localFileHeaderOffset = centralDirectoryRecord.getLocalFileHeaderOffset();
        long j3 = ((long) i2) + localFileHeaderOffset;
        if (j3 > j) {
            cf9.a("Local File Header of ", name, " extends beyond start of Central Directory. LFH end: ", j3, ", CD start: ", j);
            return null;
        }
        try {
            ByteBuffer byteBuffer = dataSource.getByteBuffer(localFileHeaderOffset, i2);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            byteBuffer.order(byteOrder);
            int i3 = byteBuffer.getInt();
            if (i3 != RECORD_SIGNATURE) {
                throw new ZipFormatException("Not a Local File Header record for entry " + name + ". Signature: 0x" + Long.toHexString(((long) i3) & 4294967295L));
            }
            int i4 = byteBuffer.getShort(6) & 8;
            boolean z3 = i4 != 0;
            boolean z4 = (centralDirectoryRecord.getGpFlags() & 8) != 0;
            if (z3 != z4) {
                throw new ZipFormatException("Data Descriptor presence mismatch between Local File Header and Central Directory for entry " + name + ". LFH: " + z3 + ", CD: " + z4);
            }
            boolean z5 = false;
            long crc32 = centralDirectoryRecord.getCrc32();
            long compressedSize = centralDirectoryRecord.getCompressedSize();
            long uncompressedSize = centralDirectoryRecord.getUncompressedSize();
            if (!z3) {
                long unsignedInt32 = ZipUtils.getUnsignedInt32(byteBuffer, 14);
                if (unsignedInt32 != crc32) {
                    cf9.a("CRC-32 mismatch between Local File Header and Central Directory for entry ", name, ". LFH: ", unsignedInt32, ", CD: ", crc32);
                    return null;
                }
                long unsignedInt33 = ZipUtils.getUnsignedInt32(byteBuffer, 18);
                if (unsignedInt33 != compressedSize) {
                    cf9.a("Compressed size mismatch between Local File Header and Central Directory for entry ", name, ". LFH: ", unsignedInt33, ", CD: ", compressedSize);
                    return null;
                }
                long unsignedInt34 = ZipUtils.getUnsignedInt32(byteBuffer, 22);
                if (unsignedInt34 != uncompressedSize) {
                    cf9.a("Uncompressed size mismatch between Local File Header and Central Directory for entry ", name, ". LFH: ", unsignedInt34, ", CD: ", uncompressedSize);
                    return null;
                }
            }
            int unsignedInt16 = ZipUtils.getUnsignedInt16(byteBuffer, 26);
            if (unsignedInt16 > nameSizeBytes) {
                throw new ZipFormatException("Name mismatch between Local File Header and Central Directory for entry" + name + ". LFH: " + unsignedInt16 + " bytes, CD: " + nameSizeBytes + " bytes");
            }
            String name2 = CentralDirectoryRecord.getName(byteBuffer, 30, unsignedInt16);
            if (!name.equals(name2)) {
                throw new ZipFormatException("Name mismatch between Local File Header and Central Directory. LFH: \"" + name2 + "\", CD: \"" + name + "\"");
            }
            int unsignedInt17 = ZipUtils.getUnsignedInt16(byteBuffer, 28);
            long j4 = 30 + localFileHeaderOffset + ((long) unsignedInt16);
            long j5 = ((long) unsignedInt17) + j4;
            if (centralDirectoryRecord.getCompressionMethod() != 0) {
                z5 = true;
            }
            long j6 = z5 ? compressedSize : uncompressedSize;
            long j7 = j5 + j6;
            if (j7 > j) {
                throw new ZipFormatException("Local File Header data of " + name + " overlaps with Central Directory. LFH data start: " + j5 + ", LFH data end: " + j7 + ", CD start: " + j);
            }
            ByteBuffer byteBuffer2 = EMPTY_BYTE_BUFFER;
            if (z && unsignedInt17 > 0) {
                byteBuffer2 = dataSource.getByteBuffer(j4, unsignedInt17);
            }
            if (!z2 || i4 == 0) {
                i = unsignedInt17;
                j2 = j7;
            } else {
                long j8 = 12 + j7;
                i = unsignedInt17;
                if (j8 > j) {
                    cf9.a("Data Descriptor of ", name, " overlaps with Central Directory. Data Descriptor end: ", j7, ", CD start: ", j);
                    return null;
                }
                ByteBuffer byteBuffer3 = dataSource.getByteBuffer(j7, 4);
                byteBuffer3.order(byteOrder);
                if (byteBuffer3.getInt() == DATA_DESCRIPTOR_SIGNATURE) {
                    long j9 = 16 + j7;
                    if (j9 > j) {
                        cf9.a("Data Descriptor of ", name, " overlaps with Central Directory. Data Descriptor end: ", j7, ", CD start: ", j);
                        return null;
                    }
                    j2 = j9;
                } else {
                    j2 = j8;
                }
            }
            return new LocalFileRecord(name, nameSizeBytes, byteBuffer2, localFileHeaderOffset, j2 - localFileHeaderOffset, unsignedInt16 + 30 + i, j6, z5, uncompressedSize);
        } catch (IOException e) {
            cia.a("Failed to read Local File Header of ", name, e);
            return null;
        }
    }

    public static byte[] getUncompressedData(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j) throws IOException, ZipFormatException {
        if (centralDirectoryRecord.getUncompressedSize() > 2147483647L) {
            throw new IOException(centralDirectoryRecord.getName() + " too large: " + centralDirectoryRecord.getUncompressedSize());
        }
        try {
            byte[] bArr = new byte[(int) centralDirectoryRecord.getUncompressedSize()];
            outputUncompressedData(dataSource, centralDirectoryRecord, j, new ByteBufferSink(ByteBuffer.wrap(bArr)));
            return bArr;
        } catch (OutOfMemoryError e) {
            throw new IOException(centralDirectoryRecord.getName() + " too large: " + centralDirectoryRecord.getUncompressedSize(), e);
        }
    }

    public static long outputRecordWithDeflateCompressedData(String str, int i, int i2, byte[] bArr, long j, long j2, DataSink dataSink) throws IOException {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bytes.length + 30);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(RECORD_SIGNATURE);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, 20);
        byteBufferAllocate.putShort(ZipUtils.GP_FLAG_EFS);
        byteBufferAllocate.putShort((short) 8);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, i);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, i2);
        ZipUtils.putUnsignedInt32(byteBufferAllocate, j);
        ZipUtils.putUnsignedInt32(byteBufferAllocate, bArr.length);
        ZipUtils.putUnsignedInt32(byteBufferAllocate, j2);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, bytes.length);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, 0);
        byteBufferAllocate.put(bytes);
        if (byteBufferAllocate.hasRemaining()) {
            bf9.a("pos: ", byteBufferAllocate.position(), ", limit: ", byteBufferAllocate.limit());
            return 0L;
        }
        byteBufferAllocate.flip();
        long jRemaining = byteBufferAllocate.remaining();
        dataSink.consume(byteBufferAllocate);
        long length = jRemaining + ((long) bArr.length);
        dataSink.consume(bArr, 0, bArr.length);
        return length;
    }

    public int getDataStartOffsetInRecord() {
        return this.mDataStartOffset;
    }

    public ByteBuffer getExtra() {
        int iCapacity = this.mExtra.capacity();
        ByteBuffer byteBuffer = this.mExtra;
        return iCapacity > 0 ? byteBuffer.slice() : byteBuffer;
    }

    public int getExtraFieldStartOffsetInsideRecord() {
        return this.mNameSizeBytes + 30;
    }

    public String getName() {
        return this.mName;
    }

    public long getSize() {
        return this.mSize;
    }

    public long getStartOffsetInArchive() {
        return this.mStartOffsetInArchive;
    }

    public boolean isDataCompressed() {
        return this.mDataCompressed;
    }

    public long outputRecord(DataSource dataSource, DataSink dataSink) throws IOException {
        long size = getSize();
        dataSource.feed(getStartOffsetInArchive(), size, dataSink);
        return size;
    }

    public long outputRecordWithModifiedExtra(DataSource dataSource, ByteBuffer byteBuffer, DataSink dataSink) throws IOException {
        long startOffsetInArchive = getStartOffsetInArchive();
        int extraFieldStartOffsetInsideRecord = getExtraFieldStartOffsetInsideRecord();
        int iRemaining = byteBuffer.remaining();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(extraFieldStartOffsetInsideRecord + iRemaining);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        dataSource.copyTo(startOffsetInArchive, extraFieldStartOffsetInsideRecord, byteBufferAllocate);
        byteBufferAllocate.put(byteBuffer.slice());
        byteBufferAllocate.flip();
        ZipUtils.setUnsignedInt16(byteBufferAllocate, 28, iRemaining);
        long jRemaining = byteBufferAllocate.remaining();
        dataSink.consume(byteBufferAllocate);
        long size = getSize();
        int i = this.mDataStartOffset;
        long j = size - ((long) i);
        dataSource.feed(startOffsetInArchive + ((long) i), j, dataSink);
        return jRemaining + j;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x006b A[Catch: IOException -> 0x0084, TryCatch #2 {IOException -> 0x0084, blocks: (B:3:0x000b, B:30:0x0088, B:23:0x0063, B:25:0x006b, B:26:0x0083, B:29:0x0087, B:5:0x000f, B:9:0x0025, B:22:0x0062, B:21:0x005f, B:6:0x0015, B:13:0x002c, B:14:0x0056, B:17:0x0059), top: B:41:0x000b, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0087 A[Catch: IOException -> 0x0084, TryCatch #2 {IOException -> 0x0084, blocks: (B:3:0x000b, B:30:0x0088, B:23:0x0063, B:25:0x006b, B:26:0x0083, B:29:0x0087, B:5:0x000f, B:9:0x0025, B:22:0x0062, B:21:0x005f, B:6:0x0015, B:13:0x002c, B:14:0x0056, B:17:0x0059), top: B:41:0x000b, inners: #0 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:25:0x006b, please report this as an issue */
    public void outputUncompressedData(DataSource dataSource, DataSink dataSink) throws IOException, ZipFormatException {
        long j = this.mStartOffsetInArchive + ((long) this.mDataStartOffset);
        try {
            if (!this.mDataCompressed) {
                dataSource.feed(j, this.mDataSize, dataSink);
                return;
            }
            try {
                InflateSinkAdapter inflateSinkAdapter = new InflateSinkAdapter(dataSink);
                try {
                    dataSource.feed(j, this.mDataSize, inflateSinkAdapter);
                    long outputByteCount = inflateSinkAdapter.getOutputByteCount();
                    if (outputByteCount == this.mUncompressedDataSize) {
                        inflateSinkAdapter.close();
                        return;
                    }
                    throw new ZipFormatException("Unexpected size of uncompressed data of " + this.mName + ". Expected: " + this.mUncompressedDataSize + " bytes, actual: " + outputByteCount + " bytes");
                } catch (Throwable th) {
                    try {
                        inflateSinkAdapter.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            } catch (IOException e) {
                if (e.getCause() instanceof DataFormatException) {
                    throw e;
                }
                throw new ZipFormatException("Data of entry " + this.mName + " malformed", e);
            }
            if (e.getCause() instanceof DataFormatException) {
                throw e;
            }
            throw new ZipFormatException("Data of entry " + this.mName + " malformed", e);
        } catch (IOException e2) {
            StringBuilder sb = new StringBuilder("Failed to read data of ");
            sb.append(this.mDataCompressed ? "compressed" : "uncompressed");
            sb.append(" entry ");
            sb.append(this.mName);
            throw new IOException(sb.toString(), e2);
        }
    }

    public static class InflateSinkAdapter implements DataSink, Closeable {
        private boolean mClosed;
        private final DataSink mDelegate;
        private Inflater mInflater;
        private byte[] mInputBuffer;
        private byte[] mOutputBuffer;
        private long mOutputByteCount;

        private InflateSinkAdapter(DataSink dataSink) {
            this.mInflater = new Inflater(true);
            this.mDelegate = dataSink;
        }

        private void checkNotClosed() {
            if (this.mClosed) {
                k2d.a("Closed");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.mClosed = true;
            this.mInputBuffer = null;
            this.mOutputBuffer = null;
            Inflater inflater = this.mInflater;
            if (inflater != null) {
                inflater.end();
                this.mInflater = null;
            }
        }

        @Override // com.android.apksig.util.DataSink
        public void consume(ByteBuffer byteBuffer) throws IOException {
            checkNotClosed();
            if (byteBuffer.hasArray()) {
                consume(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
                byteBuffer.position(byteBuffer.limit());
                return;
            }
            if (this.mInputBuffer == null) {
                this.mInputBuffer = new byte[65536];
            }
            while (byteBuffer.hasRemaining()) {
                int iMin = Math.min(byteBuffer.remaining(), this.mInputBuffer.length);
                byteBuffer.get(this.mInputBuffer, 0, iMin);
                consume(this.mInputBuffer, 0, iMin);
            }
        }

        public long getOutputByteCount() {
            return this.mOutputByteCount;
        }

        @Override // com.android.apksig.util.DataSink
        public void consume(byte[] bArr, int i, int i2) throws IOException {
            checkNotClosed();
            this.mInflater.setInput(bArr, i, i2);
            if (this.mOutputBuffer == null) {
                this.mOutputBuffer = new byte[65536];
            }
            while (!this.mInflater.finished()) {
                try {
                    int iInflate = this.mInflater.inflate(this.mOutputBuffer);
                    if (iInflate == 0) {
                        return;
                    }
                    this.mDelegate.consume(this.mOutputBuffer, 0, iInflate);
                    this.mOutputByteCount += (long) iInflate;
                } catch (DataFormatException e) {
                    dk3.a("Failed to inflate data", e);
                    return;
                }
            }
        }
    }

    public static void outputUncompressedData(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j, DataSink dataSink) throws IOException, ZipFormatException {
        getRecord(dataSource, centralDirectoryRecord, j, false, false).outputUncompressedData(dataSource, dataSink);
    }

    public static LocalFileRecord getRecord(DataSource dataSource, CentralDirectoryRecord centralDirectoryRecord, long j) throws IOException, ZipFormatException {
        return getRecord(dataSource, centralDirectoryRecord, j, true, true);
    }
}
