package com.google.crypto.tink.mac.internal;

import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class AesUtil {
    public static final int BLOCK_SIZE = 16;

    private AesUtil() {
    }

    public static byte[] cmacPad(byte[] bArr) {
        if (bArr.length >= 16) {
            w01.a("x must be smaller than a block.");
            return null;
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, 16);
        bArrCopyOf[bArr.length] = UnsignedBytes.MAX_POWER_OF_TWO;
        return bArrCopyOf;
    }

    public static byte[] dbl(byte[] bArr) {
        if (bArr.length != 16) {
            w01.a("value must be a block.");
            return null;
        }
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < 16; i++) {
            byte b = (byte) ((bArr[i] << 1) & 254);
            bArr2[i] = b;
            if (i < 15) {
                bArr2[i] = (byte) (((byte) ((bArr[i + 1] >> 7) & 1)) | b);
            }
        }
        bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & 135)) ^ bArr2[15]);
        return bArr2;
    }
}
