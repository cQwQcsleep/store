package com.reandroid.archive.block;

import com.reandroid.archive.ZipSignature;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DataDescriptor extends ZipHeader {
    public static final int MIN_LENGTH = 16;
    private static final int OFFSET_compressed_size = 8;
    private static final int OFFSET_crc = 4;
    private static final int OFFSET_size = 12;

    public DataDescriptor() {
        super(16, ZipSignature.DATA_DESCRIPTOR);
    }

    public static DataDescriptor fromLocalFile(LocalFileHeader localFileHeader) {
        DataDescriptor dataDescriptor = new DataDescriptor();
        dataDescriptor.setSignature(ZipSignature.DATA_DESCRIPTOR);
        dataDescriptor.setSize(localFileHeader.getSize());
        dataDescriptor.setCompressedSize(localFileHeader.getCompressedSize());
        dataDescriptor.setCrc(localFileHeader.getCrc());
        return dataDescriptor;
    }

    public DataDescriptor copy() {
        DataDescriptor dataDescriptor = new DataDescriptor();
        dataDescriptor.setSignature(ZipSignature.DATA_DESCRIPTOR);
        dataDescriptor.setCrc(getCrc());
        dataDescriptor.setCompressedSize(getCompressedSize());
        dataDescriptor.setSize(getSize());
        return dataDescriptor;
    }

    public long getCompressedSize() {
        return getIntegerUnsigned(8);
    }

    public long getCrc() {
        return getIntegerUnsigned(4);
    }

    public long getSize() {
        return getIntegerUnsigned(12);
    }

    public void setCompressedSize(long j) {
        putInteger(8, j);
    }

    public void setCrc(long j) {
        putInteger(4, j);
    }

    public void setSize(long j) {
        putInteger(12, j);
    }

    public String toString() {
        return getSignature() + ", crc=" + HexUtil.toHex8(getCrc()) + ", compressed=" + getCompressedSize() + ", size=" + getSize();
    }
}
