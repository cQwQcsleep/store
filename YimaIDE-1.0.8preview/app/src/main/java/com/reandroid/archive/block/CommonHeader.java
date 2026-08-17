package com.reandroid.archive.block;

import com.reandroid.archive.Archive;
import com.reandroid.archive.ZipSignature;
import com.reandroid.utils.HexUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class CommonHeader extends ZipHeader {
    private static final int OFFSET_platform = 5;
    private static final int OFFSET_versionMadeBy = 4;
    private final GeneralPurposeFlag generalPurposeFlag;
    private String mFileName;
    private long mFileOffset;
    private final int offsetFileName;
    private final int offsetGeneralPurpose;

    public CommonHeader(int i, ZipSignature zipSignature, int i2) {
        super(i, zipSignature);
        this.offsetFileName = i;
        this.offsetGeneralPurpose = i2;
        GeneralPurposeFlag generalPurposeFlag = new GeneralPurposeFlag(this, i2);
        this.generalPurposeFlag = generalPurposeFlag;
        generalPurposeFlag.setUtf8(true, false);
        setDosTime(35719201L);
    }

    private String decodeFileName() {
        int fileNameLength = getFileNameLength();
        byte[] bytesInternal = getBytesInternal();
        int i = this.offsetFileName;
        int length = bytesInternal.length - i;
        if (length <= 0) {
            return "";
        }
        if (fileNameLength > length) {
            fileNameLength = length;
        }
        return new String(bytesInternal, i, fileNameLength, StandardCharsets.UTF_8);
    }

    private int getOffsetExtra() {
        return this.offsetFileName + getFileNameLength();
    }

    private int getOffsetZip64() {
        return getOffsetExtra();
    }

    private int getOffsetZip64FieldHeader() {
        return getOffsetZip64();
    }

    private int getOffsetZip64Size() {
        return getOffsetZip64FieldLength() + 2;
    }

    private int readExtra(InputStream inputStream) throws IOException {
        int extraLength = getExtraLength();
        if (extraLength == 0) {
            return 0;
        }
        setExtraLength(extraLength);
        int i = inputStream.read(getBytesInternal(), getOffsetExtra(), extraLength);
        if (i == extraLength) {
            return extraLength;
        }
        lx0.a("Stream ended before reading extra bytes: read=", i, ", extra length=", extraLength);
        return 0;
    }

    private int readFileName(InputStream inputStream) throws IOException {
        int fileNameLength = getFileNameLength();
        if (fileNameLength == 0) {
            this.mFileName = "";
            return 0;
        }
        setFileNameLength(fileNameLength);
        int i = inputStream.read(getBytesInternal(), this.offsetFileName, fileNameLength);
        if (i == fileNameLength) {
            this.mFileName = null;
            return fileNameLength;
        }
        lx0.a("Stream ended before reading file name: read=", i, ", name length=", fileNameLength);
        return 0;
    }

    private void setFileNameLength(int i) {
        super.setBytesLength(this.offsetFileName + i + getExtraLength() + getCommentLength(), false);
        putShort(this.offsetGeneralPurpose + 20, i);
    }

    public String decodeComment() {
        int extraLength = getExtraLength();
        byte[] bytesInternal = getBytesInternal();
        int offsetExtra = getOffsetExtra();
        int length = bytesInternal.length - offsetExtra;
        if (length <= 0) {
            return "";
        }
        if (extraLength > length) {
            extraLength = length;
        }
        return new String(bytesInternal, offsetExtra, extraLength, StandardCharsets.UTF_8);
    }

    public void ensureZip64() {
        if (getExtraLength() >= getZip64BytesLength()) {
            return;
        }
        setExtraLength(getZip64BytesLength());
    }

    public int getCommentLength() {
        return 0;
    }

    public long getCompressedSize() {
        return getIntegerUnsigned(getOffsetCompressedSize());
    }

    public long getCrc() {
        return getIntegerUnsigned(this.offsetGeneralPurpose + 8);
    }

    public long getDataSize() {
        return getMethod() == Archive.STORED ? getSize() : getCompressedSize();
    }

    public Date getDate() {
        return Archive.dosToJavaDate(getDosTime());
    }

    public long getDosTime() {
        return getIntegerUnsigned(this.offsetGeneralPurpose + 4);
    }

    public byte[] getExtra() {
        int extraLength = getExtraLength();
        byte[] bArr = new byte[extraLength];
        if (extraLength == 0) {
            return bArr;
        }
        System.arraycopy(getBytesInternal(), getOffsetExtra(), bArr, 0, extraLength);
        return bArr;
    }

    public int getExtraLength() {
        return getShortUnsigned(this.offsetGeneralPurpose + 22);
    }

    public String getFileName() {
        if (this.mFileName == null) {
            this.mFileName = decodeFileName();
        }
        return this.mFileName;
    }

    public int getFileNameLength() {
        return getShortUnsigned(this.offsetGeneralPurpose + 20);
    }

    public long getFileOffset() {
        return this.mFileOffset;
    }

    public GeneralPurposeFlag getGeneralPurposeFlag() {
        return this.generalPurposeFlag;
    }

    public int getMethod() {
        return getShortUnsigned(this.offsetGeneralPurpose + 2);
    }

    public int getOffsetComment() {
        return getOffsetExtra() + getExtraLength();
    }

    public int getOffsetCompressedSize() {
        return this.offsetGeneralPurpose + 12;
    }

    public int getOffsetSize() {
        return this.offsetGeneralPurpose + 16;
    }

    public int getOffsetZip64CompressedSize() {
        return getOffsetExtra() + 8;
    }

    public int getOffsetZip64FieldLength() {
        return getOffsetZip64FieldHeader() + 2;
    }

    public int getPlatform() {
        return getByteUnsigned(5);
    }

    public long getSize() {
        return getIntegerUnsigned(getOffsetSize());
    }

    public int getVersionMadeBy() {
        return getShortUnsigned(4);
    }

    public int getZip64BytesLength() {
        return 20;
    }

    public long getZip64CompressedSize() {
        return getLong(getOffsetZip64CompressedSize());
    }

    public int getZip64FieldHeader() {
        return getShortUnsigned(getOffsetZip64FieldHeader());
    }

    public int getZip64FieldLength() {
        return getShortUnsigned(getOffsetZip64FieldLength());
    }

    public long getZip64Size() {
        return getLong(getOffsetZip64Size());
    }

    public boolean hasDataDescriptor() {
        return getGeneralPurposeFlag().hasDataDescriptor();
    }

    public boolean isUtf8() {
        return getGeneralPurposeFlag().getUtf8();
    }

    public boolean isZip64() {
        return isZip64Value() && getExtraLength() >= getZip64BytesLength();
    }

    public boolean isZip64Value() {
        return isZip64Value(getInteger(getOffsetCompressedSize())) || isZip64Value(getInteger(getOffsetCompressedSize()));
    }

    public void onUtf8Changed(boolean z) {
        String str = this.mFileName;
        if (str != null) {
            setFileName(str);
        }
    }

    public int readComment(InputStream inputStream) throws IOException {
        return 0;
    }

    @Override // com.reandroid.archive.block.ZipHeader
    public int readNext(InputStream inputStream) throws IOException {
        int fileName = readFileName(inputStream) + readExtra(inputStream) + readComment(inputStream);
        this.mFileName = null;
        return fileName;
    }

    public void setCompressedSize(long j) {
        putInteger(getOffsetCompressedSize(), j);
    }

    public void setCrc(long j) {
        putInteger(this.offsetGeneralPurpose + 8, j);
    }

    public void setDataSize(long j) {
        if (getMethod() == Archive.STORED) {
            setSize(j);
        }
        setCompressedSize(j);
    }

    public void setDate(Date date) {
        setDosTime(Archive.javaToDosTime(date));
    }

    public void setDosTime(long j) {
        if (j != -1) {
            putInteger(this.offsetGeneralPurpose + 4, j);
        }
    }

    public void setExtra(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        int length = bArr.length;
        setExtraLength(length);
        if (length == 0) {
            return;
        }
        putBytes(bArr, 0, getOffsetExtra(), length);
    }

    public void setExtraLength(int i) {
        super.setBytesLength(this.offsetFileName + getFileNameLength() + i + getCommentLength(), false);
        putShort(this.offsetGeneralPurpose + 22, i);
    }

    public void setFileName(String str) {
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        getGeneralPurposeFlag().setUtf8(true, false);
        int length = bytes.length;
        setFileNameLength(length);
        if (length == 0) {
            this.mFileName = str;
        } else {
            System.arraycopy(bytes, 0, getBytesInternal(), this.offsetFileName, length);
            this.mFileName = str;
        }
    }

    public void setFileOffset(long j) {
        this.mFileOffset = j;
    }

    public void setHasDataDescriptor(boolean z) {
        getGeneralPurposeFlag().setHasDataDescriptor(z);
    }

    public void setMethod(int i) {
        putShort(this.offsetGeneralPurpose + 2, i);
    }

    public void setPlatform(int i) {
        getBytesInternal()[5] = (byte) i;
    }

    public void setSize(long j) {
        putInteger(getOffsetSize(), j);
    }

    public void setVersionExtract(int i) {
    }

    public void setVersionMadeBy(int i) {
        putShort(4, i);
    }

    public void setZip64CompressedSize(long j) {
        putLong(getOffsetZip64CompressedSize(), j);
    }

    public void setZip64FieldHeader(int i) {
        putShort(getOffsetZip64FieldHeader(), i);
    }

    public void setZip64FieldLength(int i) {
        putShort(getOffsetZip64FieldLength(), i);
    }

    public void setZip64Size(long j) {
        putLong(getOffsetZip64Size(), j);
    }

    public void setZipAlign(int i) {
        if (isZip64()) {
            i += getZip64BytesLength();
        }
        setExtraLength(i);
    }

    public String toString() {
        if (countBytes() < getMinByteLength()) {
            return "Invalid";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(getFileOffset());
        sb.append("] ");
        String fileName = getFileName();
        if (fileName.length() > 0) {
            sb.append("name=");
            sb.append(fileName);
            sb.append(", ");
        }
        sb.append("SIG=");
        sb.append(getSignature());
        sb.append(", versionMadeBy=");
        sb.append(HexUtil.toHex4((short) getVersionMadeBy()));
        sb.append(", platform=");
        sb.append(HexUtil.toHex2((byte) getPlatform()));
        sb.append(", GP={");
        sb.append(getGeneralPurposeFlag());
        sb.append("}, method=");
        sb.append(getMethod());
        sb.append(", date=");
        sb.append(HexUtil.toHex(getDosTime(), 1));
        sb.append(", crc=");
        sb.append(HexUtil.toHex8(getCrc()));
        sb.append(", cSize=");
        sb.append(getCompressedSize());
        sb.append(", size=");
        sb.append(getSize());
        sb.append(", fileNameLength=");
        sb.append(getFileNameLength());
        sb.append(", extraLength=");
        sb.append(getExtraLength());
        return sb.toString();
    }

    public void setDate(long j) {
        setDosTime(Archive.javaToDosTime(j));
    }

    public static class GeneralPurposeFlag {
        private final CommonHeader localFileHeader;
        private final int offset;

        public GeneralPurposeFlag(CommonHeader commonHeader, int i) {
            this.localFileHeader = commonHeader;
            this.offset = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUtf8(boolean z, boolean z2) {
            boolean utf8 = getUtf8();
            if (utf8 == z) {
                return;
            }
            this.localFileHeader.putBit(this.offset + 1, 3, z);
            if (z2) {
                this.localFileHeader.onUtf8Changed(utf8);
            }
        }

        public boolean getEncryption() {
            return this.localFileHeader.getBit(this.offset, 0);
        }

        public boolean getStrongEncryption() {
            return this.localFileHeader.getBit(this.offset, 6);
        }

        public boolean getUtf8() {
            return this.localFileHeader.getBit(this.offset + 1, 3);
        }

        public int getValue() {
            return this.localFileHeader.getShortUnsigned(this.offset);
        }

        public boolean hasDataDescriptor() {
            return this.localFileHeader.getBit(this.offset, 3);
        }

        public void initDefault() {
            setUtf8(false, false);
        }

        public void setEncryption(boolean z) {
            this.localFileHeader.putBit(this.offset, 0, z);
        }

        public void setHasDataDescriptor(boolean z) {
            this.localFileHeader.putBit(this.offset, 3, z);
        }

        public void setStrongEncryption(boolean z) {
            this.localFileHeader.putBit(this.offset, 6, z);
        }

        public void setValue(int i) {
            if (i == getValue()) {
                return;
            }
            boolean utf8 = getUtf8();
            this.localFileHeader.putShort(this.offset, i);
            if (utf8 != getUtf8()) {
                this.localFileHeader.onUtf8Changed(utf8);
            }
        }

        public String toString() {
            return "Enc=" + getEncryption() + ", Descriptor=" + hasDataDescriptor() + ", StrongEnc=" + getStrongEncryption() + ", UTF8=" + getUtf8();
        }

        public void setUtf8(boolean z) {
            setUtf8(z, true);
        }
    }

    public static boolean isZip64Value(long j) {
        return j == 4294967295L || (j & (-4294967296L)) != 0;
    }

    public static boolean isZip64Value(int i) {
        return i == -1;
    }
}
