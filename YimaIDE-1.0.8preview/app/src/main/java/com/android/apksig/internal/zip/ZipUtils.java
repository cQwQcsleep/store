package com.android.apksig.internal.zip;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import com.android.apksig.zip.ZipSections;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ZipUtils {
    public static final short COMPRESSION_METHOD_DEFLATED = 8;
    public static final short COMPRESSION_METHOD_STORED = 0;
    public static final short GP_FLAG_DATA_DESCRIPTOR_USED = 8;
    public static final short GP_FLAG_EFS = 2048;
    private static final int UINT16_MAX_VALUE = 65535;
    private static final int ZIP_EOCD_CENTRAL_DIR_OFFSET_FIELD_OFFSET = 16;
    private static final int ZIP_EOCD_CENTRAL_DIR_SIZE_FIELD_OFFSET = 12;
    private static final int ZIP_EOCD_CENTRAL_DIR_TOTAL_RECORD_COUNT_OFFSET = 10;
    private static final int ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET = 20;
    private static final int ZIP_EOCD_REC_MIN_SIZE = 22;
    private static final int ZIP_EOCD_REC_SIG = 101010256;

    public static class DeflateResult {
        public final long inputCrc32;
        public final int inputSizeBytes;
        public final byte[] output;

        public DeflateResult(int i, long j, byte[] bArr) {
            this.inputSizeBytes = i;
            this.inputCrc32 = j;
            this.output = bArr;
        }
    }

    private ZipUtils() {
    }

    public static void assertByteOrderLittleEndian(ByteBuffer byteBuffer) {
        if (byteBuffer.order() == ByteOrder.LITTLE_ENDIAN) {
            return;
        }
        w01.a("ByteBuffer byte order must be little endian");
    }

    public static DeflateResult deflate(ByteBuffer byteBuffer) {
        byte[] bArrArray;
        int iArrayOffset;
        int iRemaining = byteBuffer.remaining();
        if (byteBuffer.hasArray()) {
            bArrArray = byteBuffer.array();
            iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            byteBuffer.position(byteBuffer.limit());
        } else {
            bArrArray = new byte[iRemaining];
            byteBuffer.get(bArrArray);
            iArrayOffset = 0;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArrArray, iArrayOffset, iRemaining);
        long value = crc32.getValue();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater(9, true);
        deflater.setInput(bArrArray, iArrayOffset, iRemaining);
        deflater.finish();
        byte[] bArr = new byte[65536];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr, 0, deflater.deflate(bArr));
        }
        return new DeflateResult(iRemaining, value, byteArrayOutputStream.toByteArray());
    }

    private static Pair<ByteBuffer, Long> findZipEndOfCentralDirectoryRecord(DataSource dataSource, int i) throws IOException {
        if (i < 0 || i > 65535) {
            qf1.a("maxCommentSize: ", i);
            return null;
        }
        long size = dataSource.size();
        if (size < 22) {
            return null;
        }
        int iMin = ((int) Math.min(i, size - 22)) + 22;
        long j = size - ((long) iMin);
        ByteBuffer byteBuffer = dataSource.getByteBuffer(j, iMin);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byteBuffer.order(byteOrder);
        int iFindZipEndOfCentralDirectoryRecord = findZipEndOfCentralDirectoryRecord(byteBuffer);
        if (iFindZipEndOfCentralDirectoryRecord == -1) {
            return null;
        }
        byteBuffer.position(iFindZipEndOfCentralDirectoryRecord);
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBufferSlice.order(byteOrder);
        return Pair.of(byteBufferSlice, Long.valueOf(j + ((long) iFindZipEndOfCentralDirectoryRecord)));
    }

    public static int getUnsignedInt16(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getShort(i) & 65535;
    }

    public static long getUnsignedInt32(ByteBuffer byteBuffer, int i) {
        return ((long) byteBuffer.getInt(i)) & 4294967295L;
    }

    public static long getZipEocdCentralDirectoryOffset(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        return getUnsignedInt32(byteBuffer, byteBuffer.position() + 16);
    }

    public static long getZipEocdCentralDirectorySizeBytes(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        return getUnsignedInt32(byteBuffer, byteBuffer.position() + 12);
    }

    public static int getZipEocdCentralDirectoryTotalRecordCount(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        return getUnsignedInt16(byteBuffer, byteBuffer.position() + 10);
    }

    public static List<CentralDirectoryRecord> parseZipCentralDirectory(DataSource dataSource, ZipSections zipSections) throws IOException, ApkFormatException {
        long zipCentralDirectorySizeBytes = zipSections.getZipCentralDirectorySizeBytes();
        if (zipCentralDirectorySizeBytes > 2147483647L) {
            throw new ApkFormatException("ZIP Central Directory too large: " + zipCentralDirectorySizeBytes);
        }
        long zipCentralDirectoryOffset = zipSections.getZipCentralDirectoryOffset();
        ByteBuffer byteBuffer = dataSource.getByteBuffer(zipCentralDirectoryOffset, (int) zipCentralDirectorySizeBytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int zipCentralDirectoryRecordCount = zipSections.getZipCentralDirectoryRecordCount();
        ArrayList arrayList = new ArrayList(zipCentralDirectoryRecordCount);
        for (int i = 0; i < zipCentralDirectoryRecordCount; i++) {
            int iPosition = byteBuffer.position();
            try {
                CentralDirectoryRecord record = CentralDirectoryRecord.getRecord(byteBuffer);
                if (!record.getName().endsWith("/")) {
                    arrayList.add(record);
                }
            } catch (ZipFormatException e) {
                throw new ApkFormatException("Malformed ZIP Central Directory record #" + (i + 1) + " at file offset " + (zipCentralDirectoryOffset + ((long) iPosition)), e);
            }
        }
        return arrayList;
    }

    public static void putUnsignedInt16(ByteBuffer byteBuffer, int i) {
        if (i < 0 || i > 65535) {
            qf1.a("uint16 value of out range: ", i);
        } else {
            byteBuffer.putShort((short) i);
        }
    }

    public static void putUnsignedInt32(ByteBuffer byteBuffer, long j) {
        if (j < 0 || j > 4294967295L) {
            t01.a("uint32 value of out range: ", j);
        } else {
            byteBuffer.putInt((int) j);
        }
    }

    public static void setUnsignedInt16(ByteBuffer byteBuffer, int i, int i2) {
        if (i2 < 0 || i2 > 65535) {
            qf1.a("uint16 value of out range: ", i2);
        } else {
            byteBuffer.putShort(i, (short) i2);
        }
    }

    public static void setUnsignedInt32(ByteBuffer byteBuffer, int i, long j) {
        if (j < 0 || j > 4294967295L) {
            t01.a("uint32 value of out range: ", j);
        } else {
            byteBuffer.putInt(i, (int) j);
        }
    }

    public static void setZipEocdCentralDirectoryOffset(ByteBuffer byteBuffer, long j) {
        assertByteOrderLittleEndian(byteBuffer);
        setUnsignedInt32(byteBuffer, byteBuffer.position() + 16, j);
    }

    public static void updateZipEocdCommentLen(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        setUnsignedInt16(byteBuffer, byteBuffer.position() + 20, byteBuffer.remaining() - 22);
    }

    public static int getUnsignedInt16(ByteBuffer byteBuffer) {
        return byteBuffer.getShort() & 65535;
    }

    public static long getUnsignedInt32(ByteBuffer byteBuffer) {
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    public static Pair<ByteBuffer, Long> findZipEndOfCentralDirectoryRecord(DataSource dataSource) throws IOException {
        if (dataSource.size() < 22) {
            return null;
        }
        Pair<ByteBuffer, Long> pairFindZipEndOfCentralDirectoryRecord = findZipEndOfCentralDirectoryRecord(dataSource, 0);
        return pairFindZipEndOfCentralDirectoryRecord != null ? pairFindZipEndOfCentralDirectoryRecord : findZipEndOfCentralDirectoryRecord(dataSource, 65535);
    }

    private static int findZipEndOfCentralDirectoryRecord(ByteBuffer byteBuffer) {
        assertByteOrderLittleEndian(byteBuffer);
        int iCapacity = byteBuffer.capacity();
        if (iCapacity < 22) {
            return -1;
        }
        int i = iCapacity - 22;
        int iMin = Math.min(i, 65535);
        for (int i2 = 0; i2 <= iMin; i2++) {
            int i3 = i - i2;
            if (byteBuffer.getInt(i3) == ZIP_EOCD_REC_SIG && getUnsignedInt16(byteBuffer, i3 + 20) == i2) {
                return i3;
            }
        }
        return -1;
    }
}
