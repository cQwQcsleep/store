package com.reandroid.archive.block;

import com.reandroid.arsc.item.ByteArray;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SignatureFooter extends ZipBlock {
    private static final byte[] APK_SIG_BLOCK_MAGIC = {65, 80, 75, 32, 83, 105, 103, 32, 66, 108, 111, 99, 107, 32, 52, 50};
    public static final int MIN_SIZE = 24;
    private static final int OFFSET_magic = 8;
    private static final int OFFSET_size = 0;

    public SignatureFooter() {
        super(24);
        setMagic(APK_SIG_BLOCK_MAGIC);
    }

    public byte[] getMagic() {
        return getBytes(8, APK_SIG_BLOCK_MAGIC.length, false);
    }

    public long getSignatureSize() {
        return getLong(0);
    }

    public boolean isValid() {
        return getSignatureSize() > 24 && ByteArray.equals(APK_SIG_BLOCK_MAGIC, getMagic());
    }

    @Override // com.reandroid.archive.block.ZipBlock
    public int readBytes(InputStream inputStream) throws IOException {
        setBytesLength(24, false);
        byte[] bytesInternal = getBytesInternal();
        return inputStream.read(bytesInternal, 0, bytesInternal.length);
    }

    public void setMagic(byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        setBytesLength(bArr.length + 8, false);
        putBytes(bArr, 0, 8, bArr.length);
    }

    public void setSignatureSize(long j) {
        if (countBytes() < 24) {
            setBytesLength(24, false);
        }
        putLong(0, j);
    }

    public String toString() {
        return getSignatureSize() + " [" + new String(getMagic()) + "]";
    }

    public void updateMagic() {
        setMagic(APK_SIG_BLOCK_MAGIC);
    }
}
