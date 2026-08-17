package com.intellij.util.io.zip;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream;
import com.intellij.util.ArrayUtil;
import com.intellij.util.SmartList;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.io.UnsyncByteArrayInputStream;
import com.intellij.util.io.zip.JBZipExtraField;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.List;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class JBZipEntry {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private volatile String comment;
    private volatile long crc;
    private volatile long csize;
    private volatile long externalAttributes;
    private volatile List<JBZipExtraField> extra;
    private volatile long headerOffset;
    private volatile int internalAttributes;
    private volatile int method;
    private final JBZipFile myFile;
    private volatile String name;
    private volatile int platform;
    private volatile long size;
    private volatile long time;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 4) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "field";
                break;
            case 2:
            case 3:
            case 4:
                objArr[0] = "com/intellij/util/io/zip/JBZipEntry";
                break;
            case 5:
                objArr[0] = "bytes";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "extraBytes";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "stream";
                break;
            case 8:
                objArr[0] = "file";
                break;
            case 9:
                objArr[0] = "is";
                break;
            default:
                objArr[0] = "extra";
                break;
        }
        if (i == 2) {
            objArr[1] = "getExtra";
        } else if (i == 3) {
            objArr[1] = "getCentralDirectoryExtraBytes";
        } else if (i != 4) {
            objArr[1] = "com/intellij/util/io/zip/JBZipEntry";
        } else {
            objArr[1] = "getLocalFileHeaderDataExtra";
        }
        switch (i) {
            case 1:
                objArr[2] = "addExtra";
                break;
            case 2:
            case 3:
            case 4:
                break;
            case 5:
                objArr[2] = "assertValidExtraFieldSize";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "readExtraFromCentralDirectoryBytes";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "setDataFromStream";
                break;
            case 8:
                objArr[2] = "setDataFromPath";
                break;
            case 9:
                objArr[2] = "readNBytes";
                break;
            default:
                objArr[2] = "setExtra";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public JBZipEntry(JBZipFile jBZipFile) {
        this.time = -1L;
        this.crc = -1L;
        this.size = -1L;
        this.csize = -1L;
        this.method = -1;
        this.extra = new SmartList();
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.headerOffset = -1L;
        this.name = "";
        this.myFile = jBZipFile;
    }

    private static void assertValidExtraFieldSize(byte[] bArr) {
        if (bArr == null) {
            $$$reportNull$$$0(5);
        }
        if (bArr.length <= 65535) {
            return;
        }
        w01.a("invalid extra field length");
    }

    private static int getUTF8Length(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            i = cCharAt <= 127 ? i + 1 : cCharAt <= 2047 ? i + 2 : i + 3;
        }
        return i;
    }

    private static byte[] readNBytes(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            $$$reportNull$$$0(9);
        }
        byte[] bArrNewByteArray = ArrayUtil.newByteArray(i);
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArrNewByteArray, i2, i - i2);
            if (i3 < 0) {
                u01.a();
                return null;
            }
            i2 += i3;
        }
        return bArrNewByteArray;
    }

    public void addExtra(final JBZipExtraField jBZipExtraField) {
        if (jBZipExtraField == null) {
            $$$reportNull$$$0(1);
        }
        JBZipExtraField jBZipExtraField2 = (JBZipExtraField) ContainerUtil.find(this.extra, new Condition() { // from class: ca7
            public final boolean value(Object obj) {
                return ((JBZipExtraField) obj).getHeaderId().equals(jBZipExtraField.getHeaderId());
            }
        });
        if (jBZipExtraField2 != null) {
            this.extra.remove(jBZipExtraField2);
        }
        this.extra.add(jBZipExtraField);
    }

    public long calcDataOffset() throws IOException {
        long headerOffset = getHeaderOffset();
        byte[] bArr = new byte[4];
        this.myFile.readFullyFromPosition(bArr, 26 + headerOffset);
        return headerOffset + 30 + ((long) ZipShort.getValue(bArr, 0)) + ((long) ZipShort.getValue(bArr, 2));
    }

    public byte[] getCentralDirectoryExtraBytes() throws IOException {
        BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream();
        try {
            for (JBZipExtraField jBZipExtraField : this.extra) {
                bufferExposingByteArrayOutputStream.write(jBZipExtraField.getHeaderId().getBytes());
                bufferExposingByteArrayOutputStream.write(jBZipExtraField.getCentralDirectoryLength().getBytes());
                bufferExposingByteArrayOutputStream.write(jBZipExtraField.getCentralDirectoryData());
            }
            byte[] byteArray = bufferExposingByteArrayOutputStream.toByteArray();
            assertValidExtraFieldSize(byteArray);
            bufferExposingByteArrayOutputStream.close();
            if (byteArray == null) {
                $$$reportNull$$$0(3);
            }
            return byteArray;
        } catch (Throwable th) {
            try {
                bufferExposingByteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public String getComment() {
        return this.comment;
    }

    public long getCompressedSize() {
        return this.csize;
    }

    public long getCrc() {
        return this.crc;
    }

    public long getExternalAttributes() {
        return this.externalAttributes;
    }

    public long getHeaderOffset() {
        return this.headerOffset;
    }

    public InputStream getInputStream() throws IOException {
        this.myFile.ensureFlushed(getHeaderOffset() + 30);
        long jCalcDataOffset = calcDataOffset();
        long compressedSize = getCompressedSize();
        long j = jCalcDataOffset + compressedSize;
        this.myFile.ensureFlushed(j);
        if (this.myFile.getSize() < j) {
            u01.a();
            return null;
        }
        BoundedInputStream boundedInputStream = new BoundedInputStream(jCalcDataOffset, compressedSize);
        int method = getMethod();
        if (method == 0) {
            return boundedInputStream;
        }
        if (method == 8) {
            boundedInputStream.addDummy();
            return new InflaterInputStream(boundedInputStream, new Inflater(true), this.size <= 0 ? 8192 : (int) Math.min(this.size, 8192L));
        }
        throw new ZipException("Found unsupported compression method " + getMethod());
    }

    public int getInternalAttributes() {
        return this.internalAttributes;
    }

    public byte[] getLocalFileHeaderDataExtra() throws IOException {
        BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream();
        try {
            for (JBZipExtraField jBZipExtraField : this.extra) {
                bufferExposingByteArrayOutputStream.write(jBZipExtraField.getHeaderId().getBytes());
                bufferExposingByteArrayOutputStream.write(jBZipExtraField.getLocalFileDataLength().getBytes());
                bufferExposingByteArrayOutputStream.write(jBZipExtraField.getLocalFileDataData());
            }
            byte[] byteArray = bufferExposingByteArrayOutputStream.toByteArray();
            assertValidExtraFieldSize(byteArray);
            bufferExposingByteArrayOutputStream.close();
            if (byteArray == null) {
                $$$reportNull$$$0(4);
            }
            return byteArray;
        } catch (Throwable th) {
            try {
                bufferExposingByteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public int getMethod() {
        return this.method;
    }

    public String getName() {
        return this.name;
    }

    public int getPlatform() {
        return this.platform;
    }

    public long getSize() {
        return this.size;
    }

    public long getTime() {
        return this.time;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    public void readExtraFromCentralDirectoryBytes(byte[] bArr) throws IOException {
        if (bArr == null) {
            $$$reportNull$$$0(6);
        }
        UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(bArr);
        while (unsyncByteArrayInputStream.available() > 0) {
            ZipShort zipShort = new ZipShort(unsyncByteArrayInputStream.readShortLittleEndian());
            JBZipExtraField zip64ExtraField = zipShort.equals(Zip64ExtraField.HEADER_ID) ? new Zip64ExtraField() : new UnrecognizedExtraField(zipShort);
            int shortLittleEndian = unsyncByteArrayInputStream.readShortLittleEndian();
            zip64ExtraField.parseFromCentralDirectoryData(readNBytes(unsyncByteArrayInputStream, shortLittleEndian), 0, shortLittleEndian);
            addExtra(zip64ExtraField);
            if (zip64ExtraField instanceof Zip64ExtraField) {
                Zip64ExtraField zip64ExtraField2 = (Zip64ExtraField) zip64ExtraField;
                ZipUInt64 compressedSize = zip64ExtraField2.getCompressedSize();
                if (compressedSize != null) {
                    setCompressedSize(compressedSize.getLongValue());
                }
                ZipUInt64 size = zip64ExtraField2.getSize();
                if (size != null) {
                    setSize(size.getLongValue());
                }
                ZipUInt64 headerOffset = zip64ExtraField2.getHeaderOffset();
                if (headerOffset != null) {
                    setHeaderOffset(headerOffset.getLongValue());
                }
            }
        }
    }

    public void setComment(String str) {
        if (str == null || str.length() <= 21845 || getUTF8Length(str) <= 65535) {
            this.comment = str;
        } else {
            w01.a("invalid entry comment length");
        }
    }

    public void setCompressedSize(long j) {
        this.csize = j;
    }

    public void setCrc(long j) {
        if (j < 0 || j > 4294967295L) {
            w01.a("invalid entry crc-32");
        } else {
            this.crc = j;
        }
    }

    public void setExternalAttributes(long j) {
        this.externalAttributes = j;
    }

    public void setHeaderOffset(long j) {
        this.headerOffset = j;
    }

    public void setInternalAttributes(int i) {
        this.internalAttributes = i;
    }

    public void setMethod(int i) {
        if (i == 0 || i == 8) {
            this.method = i;
        } else {
            qf1.a("invalid compression method: ", i);
        }
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public void setSize(long j) {
        if (j < 0 || j > 4294967295L) {
            w01.a("invalid entry size");
        } else {
            this.size = j;
        }
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String toString() {
        return this.name;
    }

    public JBZipEntry(String str, JBZipFile jBZipFile) {
        this.time = -1L;
        this.crc = -1L;
        this.size = -1L;
        this.csize = -1L;
        this.method = -1;
        this.extra = new SmartList();
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.headerOffset = -1L;
        this.name = str;
        this.myFile = jBZipFile;
    }

    public class BoundedInputStream extends InputStream {
        private boolean addDummyByte = false;
        private long loc;
        private long remaining;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "b", "com/intellij/util/io/zip/JBZipEntry$BoundedInputStream", "read"));
        }

        public BoundedInputStream(long j, long j2) {
            this.remaining = j2;
            this.loc = j;
        }

        public void addDummy() {
            this.addDummyByte = true;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (bArr == null) {
                $$$reportNull$$$0(0);
            }
            long j = this.remaining;
            if (j <= 0) {
                if (!this.addDummyByte) {
                    return -1;
                }
                this.addDummyByte = false;
                bArr[i] = 0;
                return 1;
            }
            if (i2 <= 0) {
                return 0;
            }
            if (i2 > j) {
                i2 = (int) j;
            }
            int fromPosition = JBZipEntry.this.myFile.readFromPosition(bArr, i, i2, this.loc);
            if (fromPosition > 0) {
                long j2 = fromPosition;
                this.loc += j2;
                this.remaining -= j2;
            }
            return fromPosition;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            long j = this.remaining;
            this.remaining = j - 1;
            if (j <= 0) {
                if (!this.addDummyByte) {
                    return -1;
                }
                this.addDummyByte = false;
                return 0;
            }
            SeekableByteChannel seekableByteChannel = JBZipEntry.this.myFile.myArchive;
            long j2 = this.loc;
            this.loc = 1 + j2;
            seekableByteChannel.position(j2);
            return JBZipEntry.this.myFile.readByte();
        }
    }
}
