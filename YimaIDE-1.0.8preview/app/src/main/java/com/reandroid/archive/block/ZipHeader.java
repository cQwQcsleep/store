package com.reandroid.archive.block;

import com.reandroid.archive.ZipSignature;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipHeader extends ZipBlock {
    private static final int OFFSET_signature = 0;
    private final ZipSignature expectedSignature;
    private final int minByteLength;

    public ZipHeader(int i, ZipSignature zipSignature) {
        super(i);
        this.minByteLength = i;
        this.expectedSignature = zipSignature;
    }

    public static boolean isZip64Length(long j) {
        return j == -1 || (j & (-4294967296L)) != 0;
    }

    private int readBasic(InputStream inputStream) throws IOException {
        setBytesLength(getMinByteLength(), false);
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length;
        int i = inputStream.read(bytesInternal, 0, length);
        if (i != length) {
            setBytesLength(i, false);
            if (getSignature() == this.expectedSignature) {
                setSignature(0);
            }
        }
        return i;
    }

    public ZipSignature getExpectedSignature() {
        return this.expectedSignature;
    }

    public int getMinByteLength() {
        return this.minByteLength;
    }

    public ZipSignature getSignature() {
        return ZipSignature.valueOf(getSignatureValue());
    }

    public int getSignatureValue() {
        if (countBytes() < 4) {
            return 0;
        }
        return getInteger(0);
    }

    public boolean isValidSignature() {
        return getSignature() == getExpectedSignature();
    }

    @Override // com.reandroid.archive.block.ZipBlock
    public int readBytes(InputStream inputStream) throws IOException {
        int basic = readBasic(inputStream);
        return getSignature() != getExpectedSignature() ? basic : basic + readNext(inputStream);
    }

    public int readNext(InputStream inputStream) throws IOException {
        return 0;
    }

    public void setSignature(int i) {
        if (countBytes() < 4) {
            return;
        }
        putInteger(0, i);
    }

    public void setSignature(ZipSignature zipSignature) {
        setSignature(zipSignature == null ? 0 : zipSignature.getValue());
    }
}
