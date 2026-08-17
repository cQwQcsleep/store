package com.android.tools.r8.internal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class K7 {
    static {
        new J7();
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        inputStream.getClass();
        ArrayDeque arrayDeque = new ArrayDeque(20);
        int iMin = Math.min(8192, Math.max(128, Integer.highestOneBit(0) * 2));
        int i = 0;
        while (i < 2147483639) {
            int iMin2 = Math.min(iMin, 2147483639 - i);
            byte[] bArr = new byte[iMin2];
            arrayDeque.add(bArr);
            int i2 = 0;
            while (i2 < iMin2) {
                int i3 = inputStream.read(bArr, i2, iMin2 - i2);
                if (i3 == -1) {
                    if (arrayDeque.isEmpty()) {
                        return new byte[0];
                    }
                    byte[] bArr2 = (byte[]) arrayDeque.remove();
                    if (bArr2.length == i) {
                        return bArr2;
                    }
                    int length = i - bArr2.length;
                    byte[] bArrCopyOf = Arrays.copyOf(bArr2, i);
                    while (length > 0) {
                        byte[] bArr3 = (byte[]) arrayDeque.remove();
                        int iMin3 = Math.min(length, bArr3.length);
                        System.arraycopy(bArr3, 0, bArrCopyOf, i - length, iMin3);
                        length -= iMin3;
                    }
                    return bArrCopyOf;
                }
                i2 += i3;
                i += i3;
            }
            iMin = MB.a(((long) iMin) * ((long) (iMin < 4096 ? 4 : 2)));
        }
        if (inputStream.read() != -1) {
            throw new OutOfMemoryError("input is too large to fit in a byte array");
        }
        if (arrayDeque.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr4 = (byte[]) arrayDeque.remove();
        if (bArr4.length == 2147483639) {
            return bArr4;
        }
        int length2 = 2147483639 - bArr4.length;
        byte[] bArrCopyOf2 = Arrays.copyOf(bArr4, 2147483639);
        while (length2 > 0) {
            byte[] bArr5 = (byte[]) arrayDeque.remove();
            int iMin4 = Math.min(length2, bArr5.length);
            System.arraycopy(bArr5, 0, bArrCopyOf2, 2147483639 - length2, iMin4);
            length2 -= iMin4;
        }
        return bArrCopyOf2;
    }

    public static void a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        inputStream.getClass();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                fileOutputStream.write(bArr, 0, i);
            }
        }
    }
}
