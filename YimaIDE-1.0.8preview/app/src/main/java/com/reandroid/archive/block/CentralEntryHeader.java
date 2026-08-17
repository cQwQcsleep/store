package com.reandroid.archive.block;

import com.reandroid.archive.ZipSignature;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.io.FilePermissions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CentralEntryHeader extends CommonHeader {
    private static final int OFFSET_commentLength = 32;
    private static final int OFFSET_compressed_size = 20;
    private static final int OFFSET_crc = 16;
    private static final int OFFSET_diskStart = 34;
    private static final int OFFSET_dos_date = 14;
    private static final int OFFSET_dos_time = 12;
    private static final int OFFSET_externalFileAttributes = 38;
    private static final int OFFSET_extraLength = 30;
    private static final int OFFSET_fileName = 46;
    private static final int OFFSET_fileNameLength = 28;
    private static final int OFFSET_general_purpose = 8;
    private static final int OFFSET_internalFileAttributes = 36;
    private static final int OFFSET_localRelativeOffset = 42;
    private static final int OFFSET_method = 10;
    private static final int OFFSET_signature = 0;
    private static final int OFFSET_size = 24;
    private static final int OFFSET_versionExtract = 6;
    private static final int OFFSET_versionMadeBy = 4;
    private String mComment;

    public CentralEntryHeader() {
        super(46, ZipSignature.CENTRAL_FILE, 8);
        setFilePermissionsValue(33188);
    }

    public static CentralEntryHeader fromLocalFileHeader(LocalFileHeader localFileHeader) {
        CentralEntryHeader centralEntryHeader = new CentralEntryHeader();
        centralEntryHeader.setSignature(ZipSignature.CENTRAL_FILE);
        centralEntryHeader.setVersionMadeBy(localFileHeader.getVersionMadeBy());
        centralEntryHeader.setLocalRelativeOffset(localFileHeader.getFileOffset() - ((long) localFileHeader.countBytes()));
        centralEntryHeader.getGeneralPurposeFlag().setValue(localFileHeader.getGeneralPurposeFlag().getValue());
        centralEntryHeader.setMethod(localFileHeader.getMethod());
        centralEntryHeader.setDosTime(localFileHeader.getDosTime());
        centralEntryHeader.setCrc(localFileHeader.getCrc());
        centralEntryHeader.setCompressedSize(localFileHeader.getCompressedSize());
        centralEntryHeader.setSize(localFileHeader.getSize());
        centralEntryHeader.setFileName(localFileHeader.getFileName());
        return centralEntryHeader;
    }

    private int getOffsetZip64LocalRelativeOffset() {
        return getOffsetZip64FieldLength() + 2;
    }

    public String getComment() {
        if (this.mComment == null) {
            this.mComment = decodeComment();
        }
        return this.mComment;
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public int getCommentLength() {
        return getShortUnsigned(32);
    }

    public int getExternalFileAttributes() {
        return getInteger(38);
    }

    public int getFileAttributesId() {
        return getShortUnsigned(38);
    }

    public FilePermissions getFilePermissions() {
        return new FilePermissions() { // from class: com.reandroid.archive.block.CentralEntryHeader.1
            public int get() {
                return CentralEntryHeader.this.getFilePermissionsValue();
            }

            public void set(int i) {
                CentralEntryHeader.this.setFilePermissionsValue(i);
            }
        };
    }

    public int getFilePermissionsValue() {
        return getShortUnsigned(40);
    }

    public int getInternalFileAttributes() {
        return getShortUnsigned(36);
    }

    public long getLocalRelativeOffset() {
        return isZip64() ? getLong(getOffsetZip64LocalRelativeOffset()) : getIntegerUnsigned(42);
    }

    public int getVersionExtract() {
        return getShortUnsigned(6);
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public int getZip64BytesLength() {
        return 12;
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public boolean isZip64Value() {
        return CommonHeader.isZip64Value(getInteger(42));
    }

    public boolean matches(LocalFileHeader localFileHeader) {
        return localFileHeader != null && getCrc() == localFileHeader.getCrc() && Objects.equals(getFileName(), localFileHeader.getFileName());
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public void onUtf8Changed(boolean z) {
        String str = this.mComment;
        if (str != null) {
            setComment(str);
        }
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public int readComment(InputStream inputStream) throws IOException {
        int commentLength = getCommentLength();
        if (commentLength == 0) {
            this.mComment = "";
            return 0;
        }
        setCommentLength(commentLength);
        int i = inputStream.read(getBytesInternal(), getOffsetComment(), commentLength);
        if (i == commentLength) {
            this.mComment = null;
            return commentLength;
        }
        lx0.a("Stream ended before reading comment: read=", i, ", name length=", commentLength);
        return 0;
    }

    public void setComment(String str) {
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        setCommentLength(length);
        if (length == 0) {
            this.mComment = str;
        } else {
            System.arraycopy(bytes, 0, getBytesInternal(), getOffsetComment(), length);
            this.mComment = str;
        }
    }

    public void setCommentLength(int i) {
        setBytesLength(getOffsetComment() + i, false);
        putShort(32, i);
    }

    public void setExternalFileAttributes(int i) {
        putInteger(38, i);
    }

    public void setFileAttributesId(int i) {
        putShort(38, i);
    }

    public void setFilePermissionsValue(int i) {
        putShort(40, i);
    }

    public void setInternalFileAttributes(int i) {
        putShort(36, i);
    }

    public void setLocalRelativeOffset(long j) {
        if (!isZip64Value() && !CommonHeader.isZip64Value(j)) {
            putInteger(42, j);
            return;
        }
        ensureZip64();
        putInteger(42, -1);
        putLong(getOffsetZip64LocalRelativeOffset(), j);
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public void setVersionExtract(int i) {
        putShort(6, i);
    }

    @Override // com.reandroid.archive.block.CommonHeader
    public String toString() {
        boolean z;
        if (countBytes() < getMinByteLength()) {
            return "Invalid";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(getFileOffset());
        sb.append(']');
        String fileName = getFileName();
        if (fileName.length() > 0) {
            sb.append("name=");
            sb.append(fileName);
            z = true;
        } else {
            z = false;
        }
        String comment = getComment();
        if (comment.length() > 0) {
            if (z) {
                sb.append(", ");
            }
            sb.append("comment=");
            sb.append(comment);
            z = true;
        }
        if (z) {
            sb.append(", ");
        }
        sb.append("SIG=");
        sb.append(getSignature());
        sb.append(", versionMadeBy=");
        sb.append(HexUtil.toHex4((short) getVersionMadeBy()));
        sb.append(", versionExtract=");
        sb.append(HexUtil.toHex4((short) getVersionExtract()));
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
        sb.append(", commentLength=");
        sb.append(getCommentLength());
        sb.append(", offset=");
        sb.append(getLocalRelativeOffset());
        sb.append(", internalAttr=");
        sb.append(getInternalFileAttributes());
        sb.append(", externalAttr=");
        sb.append(HexUtil.toHex8(getExternalFileAttributes()));
        sb.append(", attrId=");
        sb.append(getFileAttributesId());
        sb.append(", permissions=");
        sb.append(getFilePermissions());
        return sb.toString();
    }
}
