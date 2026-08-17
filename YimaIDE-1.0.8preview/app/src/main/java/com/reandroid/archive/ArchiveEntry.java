package com.reandroid.archive;

import com.reandroid.archive.block.CentralEntryHeader;
import com.reandroid.archive.block.LocalFileHeader;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.io.FilePermissions;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveEntry {
    private final LocalFileHeader localFileHeader;

    public ArchiveEntry(LocalFileHeader localFileHeader) {
        this.localFileHeader = localFileHeader;
    }

    public CentralEntryHeader getCentralEntryHeader() {
        CentralEntryHeader centralEntryHeader = this.localFileHeader.getCentralEntryHeader();
        if (centralEntryHeader != null) {
            return centralEntryHeader;
        }
        CentralEntryHeader centralEntryHeaderFromLocalFileHeader = CentralEntryHeader.fromLocalFileHeader(this.localFileHeader);
        this.localFileHeader.setCentralEntryHeader(centralEntryHeaderFromLocalFileHeader);
        return centralEntryHeaderFromLocalFileHeader;
    }

    public String getComment() {
        return getCentralEntryHeader().getComment();
    }

    public long getCompressedSize() {
        return this.localFileHeader.getCompressedSize();
    }

    public long getCrc() {
        return this.localFileHeader.getCrc();
    }

    public long getDataSize() {
        return getMethod() != Archive.DEFLATED ? getSize() : getCompressedSize();
    }

    public long getDosTime() {
        return getCentralEntryHeader().getDosTime();
    }

    public long getFileOffset() {
        return this.localFileHeader.getFileOffset();
    }

    public FilePermissions getFilePermissions() {
        return getCentralEntryHeader().getFilePermissions();
    }

    public int getIndex() {
        return getCentralEntryHeader().getIndex();
    }

    public LocalFileHeader getLocalFileHeader() {
        return this.localFileHeader;
    }

    public int getMethod() {
        return this.localFileHeader.getMethod();
    }

    public String getName() {
        return this.localFileHeader.getFileName();
    }

    public String getSanitizedName() {
        String strSanitizePath = ArchiveUtil.sanitizePath(this.localFileHeader.getFileName());
        if (strSanitizePath != null) {
            return strSanitizePath;
        }
        return ".error_file_path_" + this.localFileHeader.getIndex();
    }

    public long getSize() {
        return this.localFileHeader.getSize();
    }

    public boolean isCompressed() {
        return getMethod() == Archive.DEFLATED;
    }

    public boolean isDirectory() {
        return getDataSize() == 0 && getName().endsWith("/");
    }

    public boolean isFile() {
        return !isDirectory();
    }

    public void setComment(String str) {
        getCentralEntryHeader().setComment(str);
    }

    public void setCompressedSize(long j) {
        this.localFileHeader.setCompressedSize(j);
        getCentralEntryHeader().setCompressedSize(j);
    }

    public void setCrc(long j) {
        this.localFileHeader.setCrc(j);
        getCentralEntryHeader().setCrc(j);
    }

    public void setDosTime(long j) {
        getCentralEntryHeader().setDosTime(j);
        getLocalFileHeader().setDosTime(j);
    }

    public void setMethod(int i) {
        this.localFileHeader.setMethod(i);
        getCentralEntryHeader().setMethod(i);
    }

    public void setName(String str) {
        this.localFileHeader.setFileName(str);
        getCentralEntryHeader().setFileName(str);
    }

    public void setSize(long j) {
        this.localFileHeader.setSize(j);
        getCentralEntryHeader().setSize(j);
    }

    public String toString() {
        return "[" + getFileOffset() + "] " + getName() + getComment() + HexUtil.toHex(" 0x", getCrc(), 8);
    }
}
