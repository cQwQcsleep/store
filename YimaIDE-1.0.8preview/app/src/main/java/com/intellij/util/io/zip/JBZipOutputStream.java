package com.intellij.util.io.zip;

import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class JBZipOutputStream {
    private final JBZipFile myFile;
    private final SeekableByteChannel raf;
    private long writtenOnDisk;
    private static final byte[] LFH_SIG = ZipLong.getBytes(67324752);
    static final byte[] CFH_SIG = ZipLong.getBytes(33639248);
    static final byte[] EOCD_SIG = ZipLong.getBytes(101010256);
    static final byte[] ZIP64_EOCD_LOC_SIG = ZipLong.getBytes(117853008);
    static final byte[] ZIP64_EOCD_SIG = ZipLong.getBytes(101075792);
    private String comment = "";
    private int level = -1;
    private int method = 0;
    private final CRC32 crc = new CRC32();
    private String encoding = null;
    private final Deflater def = new Deflater(this.level, true);
    private final BufferExposingByteArrayOutputStream myBuffer = new BufferExposingByteArrayOutputStream();

    public static class ExtraFieldData {
        private final long length;
        private final long offset;

        public ExtraFieldData(long j, long j2) {
            this.offset = j;
            this.length = j2;
        }
    }

    public JBZipOutputStream(JBZipFile jBZipFile, long j) {
        this.myFile = jBZipFile;
        this.raf = jBZipFile.myArchive;
        this.writtenOnDisk = j;
    }

    private void flushBuffer() throws IOException {
        this.raf.position(this.writtenOnDisk);
        this.raf.write(ByteBuffer.wrap(this.myBuffer.getInternalBuffer(), 0, this.myBuffer.size()));
        this.writtenOnDisk += (long) this.myBuffer.size();
        this.myBuffer.reset();
    }

    private byte[] getBytes(String str) throws ZipException {
        String str2 = this.encoding;
        if (str2 == null) {
            return str.getBytes(StandardCharsets.UTF_8);
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            throw new ZipException(e.getMessage());
        }
    }

    private void prepareNextEntry(JBZipEntry jBZipEntry) {
        if (jBZipEntry.getMethod() == -1) {
            jBZipEntry.setMethod(this.method);
        }
        if (jBZipEntry.getTime() == -1) {
            jBZipEntry.setTime(System.currentTimeMillis());
        }
        if (this.myFile.isZip64()) {
            jBZipEntry.addExtra(new Zip64ExtraField(new ZipUInt64(jBZipEntry.getSize()), new ZipUInt64(jBZipEntry.getCompressedSize()), new ZipUInt64(0L)));
        }
    }

    private void writeCentralDirectoryEnd(long j, long j2) throws IOException {
        writeOut(EOCD_SIG);
        writeOutShort(0);
        writeOutShort(0);
        int size = this.myFile.getEntries().size();
        writeOutShort(size);
        writeOutShort(size);
        writeOutLong(j);
        writeOutLong(j2);
        byte[] bytes = getBytes(this.comment);
        writeOutShort(bytes.length);
        writeOut(bytes);
    }

    private void writeCentralFileHeader(JBZipEntry jBZipEntry) throws IOException {
        writeOut(CFH_SIG);
        writeOutShort((jBZipEntry.getPlatform() << 8) | 20);
        writeOutShort(10);
        writeOutShort(0);
        writeOutShort(jBZipEntry.getMethod());
        writeOutLong(DosTime.javaToDosTime(jBZipEntry.getTime()));
        writeOutLong(jBZipEntry.getCrc());
        writeOutLong(jBZipEntry.getCompressedSize());
        writeOutLong(jBZipEntry.getSize());
        byte[] bytes = getBytes(jBZipEntry.getName());
        writeOutShort(bytes.length);
        byte[] centralDirectoryExtraBytes = jBZipEntry.getCentralDirectoryExtraBytes();
        writeOutShort(centralDirectoryExtraBytes.length);
        String comment = jBZipEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        byte[] bytes2 = getBytes(comment);
        writeOutShort(bytes2.length);
        writeOutShort(0);
        writeOutShort(jBZipEntry.getInternalAttributes());
        writeOutLong(jBZipEntry.getExternalAttributes());
        writeOutLong(Math.min(jBZipEntry.getHeaderOffset(), 4294967295L));
        writeOut(bytes);
        writeOut(centralDirectoryExtraBytes);
        writeOut(bytes2);
    }

    private ExtraFieldData writeLocalFileHeader(JBZipEntry jBZipEntry) throws IOException {
        long written = getWritten();
        jBZipEntry.setHeaderOffset(written);
        if (this.myFile.isZip64()) {
            jBZipEntry.addExtra(new Zip64ExtraField(new ZipUInt64(jBZipEntry.getSize()), new ZipUInt64(jBZipEntry.getCompressedSize()), new ZipUInt64(jBZipEntry.getHeaderOffset())));
        } else if (written >= 4294967295L) {
            nic.a("entry header offset is greater than maximal supported: ", written);
            return null;
        }
        writeOut(LFH_SIG);
        writeOutShort(this.myFile.isZip64() ? 45 : 10);
        writeOutShort(0);
        writeOutShort(jBZipEntry.getMethod());
        writeOutLong(DosTime.javaToDosTime(jBZipEntry.getTime()));
        writeOutLong(jBZipEntry.getCrc());
        if (jBZipEntry.getCompressedSize() >= 4294967295L) {
            throw new IOException("compressed size is greater than maximal supported: " + jBZipEntry.getCompressedSize());
        }
        writeOutLong(jBZipEntry.getCompressedSize());
        if (jBZipEntry.getSize() >= 4294967295L) {
            throw new IOException("size is greater than maximal supported: " + jBZipEntry.getSize());
        }
        writeOutLong(jBZipEntry.getSize());
        byte[] bytes = getBytes(jBZipEntry.getName());
        writeOutShort(bytes.length);
        byte[] localFileHeaderDataExtra = jBZipEntry.getLocalFileHeaderDataExtra();
        writeOutShort(localFileHeaderDataExtra.length);
        writeOut(bytes);
        long written2 = getWritten();
        writeOut(localFileHeaderDataExtra);
        return new ExtraFieldData(written2, localFileHeaderDataExtra.length);
    }

    private void writeOut(byte[] bArr, int i, int i2) throws IOException {
        this.myBuffer.write(bArr, i, i2);
        if (this.myBuffer.size() > 8192) {
            flushBuffer();
        }
    }

    private void writeOutLong(long j) throws IOException {
        writeOut(ZipLong.getBytes(j));
    }

    private void writeOutShort(int i) throws IOException {
        writeOut(ZipShort.getBytes(i));
    }

    private void writeZip64CentralDirectory(long j, long j2) throws IOException {
        long written = getWritten();
        writeOut(ZIP64_EOCD_SIG);
        writeOut(ZipUInt64.getBytes(44L));
        writeOut(ZipShort.getBytes(45));
        writeOut(ZipShort.getBytes(45));
        writeOut(ZipLong.getBytes(0L));
        writeOut(ZipLong.getBytes(0L));
        writeOut(ZipUInt64.getBytes(this.myFile.getEntries().size()));
        writeOut(ZipUInt64.getBytes(this.myFile.getEntries().size()));
        writeOut(ZipUInt64.getBytes(j));
        writeOut(ZipUInt64.getBytes(j2));
        writeOut(ZIP64_EOCD_LOC_SIG);
        writeOut(ZipLong.getBytes(0L));
        writeOut(ZipUInt64.getBytes(written));
        writeOut(ZipLong.getBytes(1L));
    }

    public void ensureFlushed(long j) throws IOException {
        if (j > this.writtenOnDisk) {
            flushBuffer();
        }
    }

    public void finish() throws IOException {
        long written = getWritten();
        Iterator<JBZipEntry> it = this.myFile.getEntries().iterator();
        while (it.hasNext()) {
            writeCentralFileHeader(it.next());
        }
        long written2 = getWritten() - written;
        if (this.myFile.isZip64()) {
            writeZip64CentralDirectory(written2, written);
        }
        writeCentralDirectoryEnd(written2, written);
        flushBuffer();
        this.def.end();
    }

    public long getWritten() {
        return this.writtenOnDisk + ((long) this.myBuffer.size());
    }

    public void putNextEntryBytes(JBZipEntry jBZipEntry, byte[] bArr) throws IOException {
        int length;
        byte[] internalBuffer;
        prepareNextEntry(jBZipEntry);
        this.crc.reset();
        this.crc.update(bArr);
        jBZipEntry.setCrc(this.crc.getValue());
        if (jBZipEntry.getMethod() == 8) {
            this.def.setLevel(this.level);
            BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream();
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(bufferExposingByteArrayOutputStream, this.def);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                length = bufferExposingByteArrayOutputStream.size();
                internalBuffer = bufferExposingByteArrayOutputStream.getInternalBuffer();
            } catch (Throwable th) {
                try {
                    deflaterOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } else {
            length = bArr.length;
            internalBuffer = bArr;
        }
        jBZipEntry.setCompressedSize(length);
        jBZipEntry.setSize(bArr.length);
        writeLocalFileHeader(jBZipEntry);
        writeOut(internalBuffer, 0, length);
    }

    private void writeOut(byte[] bArr) throws IOException {
        writeOut(bArr, 0, bArr.length);
    }
}
