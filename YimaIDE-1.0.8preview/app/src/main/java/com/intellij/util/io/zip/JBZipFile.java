package com.intellij.util.io.zip;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.ThreeState;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class JBZipFile implements Closeable {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private long currentCfdOffset;
    private final List<JBZipEntry> entries;
    final SeekableByteChannel myArchive;
    private final Charset myEncoding;
    final boolean myIsReadonly;
    private boolean myIsZip64;
    private JBZipOutputStream myOutputStream;
    private final long mySize;
    private final Map<String, JBZipEntry> nameMap;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
                objArr[0] = "encoding";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            default:
                objArr[0] = "f";
                break;
            case 8:
            case 11:
            case 12:
            case 13:
                objArr[0] = "isZip64";
                break;
            case 9:
                objArr[0] = "channel";
                break;
        }
        objArr[1] = "com/intellij/util/io/zip/JBZipFile";
        if (i == 12) {
            objArr[2] = "populateFromCentralDirectory";
        } else if (i != 13) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "positionAtCentralDirectory";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public JBZipFile(SeekableByteChannel seekableByteChannel, Charset charset, boolean z, ThreeState threeState) throws IOException {
        if (seekableByteChannel == null) {
            $$$reportNull$$$0(9);
        }
        if (charset == null) {
            $$$reportNull$$$0(10);
        }
        if (threeState == null) {
            $$$reportNull$$$0(11);
        }
        this.entries = new ArrayList(509);
        this.nameMap = new ConcurrentHashMap(509);
        this.myEncoding = charset;
        this.myIsReadonly = z;
        long size = seekableByteChannel.size();
        if (z) {
            this.mySize = size;
        } else {
            this.mySize = -1L;
        }
        this.myArchive = seekableByteChannel;
        try {
            if (size > 0) {
                populateFromCentralDirectory(threeState);
            } else {
                this.myIsZip64 = threeState == ThreeState.YES;
                getOutputStream();
            }
        } catch (Throwable th) {
            try {
                this.myArchive.close();
            } catch (IOException e) {
                th.addSuppressed(e);
            }
            throw th;
        }
    }

    private static FileChannel getFileChannel(File file, boolean z) throws IOException {
        Path path = Paths.get(file.getPath(), new String[0]);
        return path.getFileSystem().provider().newFileChannel(path, z ? EnumSet.of(StandardOpenOption.READ) : EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE), new FileAttribute[0]);
    }

    private String getString(byte[] bArr) {
        Charset charset = this.myEncoding;
        return charset == null ? new String(bArr, Charset.defaultCharset()) : new String(bArr, charset);
    }

    private void populateFromCentralDirectory(ThreeState threeState) throws IOException {
        int i = 12;
        if (threeState == null) {
            $$$reportNull$$$0(12);
        }
        positionAtCentralDirectory(threeState);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(Math.min((int) (getSize() - this.myArchive.position()), 65536));
        byteBufferAllocate.position(byteBufferAllocate.limit());
        byte[] bArr = new byte[42];
        byte[] bArr2 = new byte[4];
        readCachedCentralDirectory(byteBufferAllocate, bArr2);
        long value = ZipLong.getValue(bArr2);
        long value2 = ZipLong.getValue(JBZipOutputStream.CFH_SIG);
        while (value == value2) {
            readCachedCentralDirectory(byteBufferAllocate, bArr);
            int value3 = (ZipShort.getValue(bArr, 0) >> 8) & 15;
            int value4 = ZipShort.getValue(bArr, 6);
            long jDosToJavaTime = DosTime.dosToJavaTime(ZipLong.getValue(bArr, 8));
            long value5 = ZipLong.getValue(bArr, i);
            long value6 = ZipLong.getValue(bArr, 16);
            ByteBuffer byteBuffer = byteBufferAllocate;
            long value7 = ZipLong.getValue(bArr, 20);
            int value8 = ZipShort.getValue(bArr, 24);
            long j = value2;
            int value9 = ZipShort.getValue(bArr, 26);
            int value10 = ZipShort.getValue(bArr, 28);
            int value11 = ZipShort.getValue(bArr, 32);
            byte[] bArr3 = bArr2;
            long value12 = ZipLong.getValue(bArr, 34);
            long value13 = ZipLong.getValue(bArr, 38);
            String string = getString(readBytesFromBuf(byteBuffer, value8));
            byte[] bytesFromBuf = readBytesFromBuf(byteBuffer, value9);
            String string2 = getString(readBytesFromBuf(byteBuffer, value10));
            byte[] bArr4 = bArr;
            JBZipEntry jBZipEntry = new JBZipEntry(this);
            jBZipEntry.setName(string);
            jBZipEntry.setHeaderOffset(value13);
            jBZipEntry.setPlatform(value3);
            jBZipEntry.setMethod(value4);
            jBZipEntry.setTime(jDosToJavaTime);
            jBZipEntry.setCrc(value5);
            jBZipEntry.setCompressedSize(value6);
            jBZipEntry.setSize(value7);
            jBZipEntry.setInternalAttributes(value11);
            jBZipEntry.setExternalAttributes(value12);
            jBZipEntry.readExtraFromCentralDirectoryBytes(bytesFromBuf);
            try {
                jBZipEntry.setComment(string2);
            } catch (IllegalArgumentException unused) {
                jBZipEntry.setComment(string2.substring(0, 21845));
            }
            this.nameMap.put(jBZipEntry.getName(), jBZipEntry);
            this.entries.add(jBZipEntry);
            readCachedCentralDirectory(byteBuffer, bArr3);
            value = ZipLong.getValue(bArr3);
            byteBufferAllocate = byteBuffer;
            bArr = bArr4;
            value2 = j;
            bArr2 = bArr3;
            i = 12;
        }
    }

    private void positionAtCentralDirectory(ThreeState threeState) throws IOException {
        boolean zEquals;
        if (threeState == null) {
            $$$reportNull$$$0(13);
        }
        long size = getSize() - 22;
        if (size >= 0) {
            this.myArchive.position(size);
            byte[] bArr = new byte[4];
            this.myArchive.read(ByteBuffer.wrap(bArr));
            zEquals = Arrays.equals(bArr, JBZipOutputStream.EOCD_SIG);
            if (!zEquals) {
                int iMin = Math.min(65536, (int) size);
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iMin);
                int i = iMin - 4;
                long jMin = i;
                loop0: while (true) {
                    size -= jMin;
                    byteBufferAllocate.clear();
                    this.myArchive.position(size);
                    this.myArchive.read(byteBufferAllocate);
                    for (int i2 = i; i2 >= 0; i2--) {
                        byteBufferAllocate.position(i2);
                        byteBufferAllocate.get(bArr);
                        if (Arrays.equals(bArr, JBZipOutputStream.EOCD_SIG)) {
                            size += (long) i2;
                            this.myArchive.position(size);
                            zEquals = true;
                            break loop0;
                        }
                    }
                    if (size <= 0) {
                        break;
                    } else {
                        jMin = Math.min(65532L, size);
                    }
                }
            }
        } else {
            zEquals = false;
        }
        if (!zEquals) {
            throw new ZipException("archive is not a ZIP archive");
        }
        if (this.myArchive.position() > 20) {
            SeekableByteChannel seekableByteChannel = this.myArchive;
            seekableByteChannel.position(seekableByteChannel.position() - 24);
            this.myIsZip64 = Arrays.equals(readBytes(4), JBZipOutputStream.ZIP64_EOCD_LOC_SIG);
        }
        if (!this.myIsZip64) {
            if (threeState.equals(ThreeState.YES)) {
                a16.a("ZIP64 archive was requested but it is not a ZIP64 archive");
                return;
            }
            this.myArchive.position(size + 16);
            byte[] bArr2 = new byte[4];
            readFully(bArr2);
            long value = ZipLong.getValue(bArr2);
            this.currentCfdOffset = value;
            this.myArchive.position(value);
            return;
        }
        if (threeState.equals(ThreeState.NO)) {
            a16.a("Non ZIP64 archive was requested but it is a ZIP64 archive");
            return;
        }
        SeekableByteChannel seekableByteChannel2 = this.myArchive;
        seekableByteChannel2.position(seekableByteChannel2.position() + 4);
        this.myArchive.position(ZipUInt64.getLongValue(readBytes(8)));
        if (!Arrays.equals(readBytes(4), JBZipOutputStream.ZIP64_EOCD_SIG)) {
            a16.a("archive is not a ZIP64 archive");
            return;
        }
        SeekableByteChannel seekableByteChannel3 = this.myArchive;
        seekableByteChannel3.position(seekableByteChannel3.position() + 44);
        long longValue = ZipUInt64.getLongValue(readBytes(8));
        this.currentCfdOffset = longValue;
        this.myArchive.position(longValue);
    }

    private byte[] readBytes(int i) throws IOException {
        if (i <= 0) {
            return ArrayUtilRt.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[i];
        readFully(bArr);
        return bArr;
    }

    private byte[] readBytesFromBuf(ByteBuffer byteBuffer, int i) throws IOException {
        byte[] bArr = new byte[i];
        readCachedCentralDirectory(byteBuffer, bArr);
        return bArr;
    }

    private void readCachedCentralDirectory(ByteBuffer byteBuffer, byte[] bArr) throws IOException {
        if (byteBuffer.remaining() < bArr.length) {
            byteBuffer.compact();
            while (byteBuffer.hasRemaining()) {
                if (this.myArchive.read(byteBuffer) == -1) {
                    if (byteBuffer.position() >= bArr.length) {
                        break;
                    }
                    vo6.a("unexpected EOF");
                    return;
                }
            }
            byteBuffer.flip();
        }
        byteBuffer.get(bArr);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.myOutputStream != null) {
            if (this.entries.isEmpty()) {
                this.myOutputStream.putNextEntryBytes(getOrCreateEntry("/empty.file.marker"), "empty".getBytes(StandardCharsets.US_ASCII));
            }
            this.myOutputStream.finish();
            this.myArchive.truncate(this.myOutputStream.getWritten());
        }
        this.myArchive.close();
    }

    public void ensureFlushed(long j) throws IOException {
        JBZipOutputStream jBZipOutputStream = this.myOutputStream;
        if (jBZipOutputStream != null) {
            jBZipOutputStream.ensureFlushed(j);
        }
    }

    public List<JBZipEntry> getEntries() {
        return this.entries;
    }

    public JBZipEntry getEntry(String str) {
        return this.nameMap.get(str);
    }

    public JBZipEntry getOrCreateEntry(String str) {
        JBZipEntry jBZipEntry = this.nameMap.get(str);
        if (jBZipEntry != null) {
            return jBZipEntry;
        }
        JBZipEntry jBZipEntry2 = new JBZipEntry(str, this);
        this.nameMap.put(str, jBZipEntry2);
        this.entries.add(jBZipEntry2);
        return jBZipEntry2;
    }

    public JBZipOutputStream getOutputStream() throws IOException {
        if (this.myIsReadonly) {
            xba.a("Archive ", this, " is an empty file");
            return null;
        }
        if (this.myOutputStream == null) {
            this.myOutputStream = new JBZipOutputStream(this, this.currentCfdOffset);
        }
        return this.myOutputStream;
    }

    public long getSize() throws IOException {
        long j = this.mySize;
        return j == -1 ? this.myArchive.size() : j;
    }

    public boolean isZip64() {
        return this.myIsZip64;
    }

    public int readByte() throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1);
        if (this.myArchive.read(byteBufferAllocate) < 0) {
            return -1;
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate.get(0) & 255;
    }

    public int readFromPosition(byte[] bArr, int i, int i2, long j) throws IOException {
        int i3;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, i2);
        SeekableByteChannel seekableByteChannel = this.myArchive;
        if (seekableByteChannel instanceof FileChannel) {
            return ((FileChannel) seekableByteChannel).read(byteBufferWrap, j);
        }
        synchronized (seekableByteChannel) {
            this.myArchive.position(j);
            i3 = this.myArchive.read(byteBufferWrap);
        }
        return i3;
    }

    public void readFully(byte[] bArr) throws IOException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        while (byteBufferWrap.hasRemaining()) {
            if (this.myArchive.read(byteBufferWrap) < 0) {
                vo6.a("unexpected EOF");
                return;
            }
        }
    }

    public void readFullyFromPosition(byte[] bArr, long j) throws IOException {
        SeekableByteChannel seekableByteChannel = this.myArchive;
        if (!(seekableByteChannel instanceof FileChannel)) {
            synchronized (seekableByteChannel) {
                this.myArchive.position(j);
                readFully(bArr);
            }
            return;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        int i = 0;
        while (i < bArr.length) {
            int i2 = ((FileChannel) this.myArchive).read(byteBufferWrap, ((long) i) + j);
            if (i2 == 0) {
                vo6.a("unexpected EOF");
                return;
            }
            i += i2;
        }
    }

    public String toString() {
        return "JBZipFile{readonly=" + this.myIsReadonly + '}';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JBZipFile(File file, Charset charset, boolean z) throws IOException {
        this(file, charset, z, ThreeState.NO);
        if (file == null) {
            $$$reportNull$$$0(4);
        }
        if (charset == null) {
            $$$reportNull$$$0(5);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JBZipFile(File file, Charset charset, boolean z, ThreeState threeState) throws IOException {
        this(getFileChannel(file, z), charset, z, threeState);
        if (file == null) {
            $$$reportNull$$$0(6);
        }
        if (charset == null) {
            $$$reportNull$$$0(7);
        }
        if (threeState == null) {
            $$$reportNull$$$0(8);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JBZipFile(File file, boolean z) throws IOException {
        this(file, DEFAULT_CHARSET, z);
        if (file == null) {
            $$$reportNull$$$0(0);
        }
    }
}
