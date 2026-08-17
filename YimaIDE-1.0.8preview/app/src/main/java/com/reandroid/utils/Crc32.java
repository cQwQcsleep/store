package com.reandroid.utils;

import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class Crc32 extends Checksum {
    private static final long[] CRC_TABLE;
    private long mCrc = 4294967295L;
    private long mLength;

    static {
        long[] jArr = new long[256];
        CRC_TABLE = jArr;
        for (int i = 0; i < 256; i++) {
            long j = i;
            for (int i2 = 0; i2 < 8; i2++) {
                j = (j & 1) == 1 ? (j >> 1) ^ 3988292384L : j >> 1;
            }
            jArr[i] = j;
        }
    }

    public static long of(File file) throws IOException {
        if (!file.isFile()) {
            s8g.a("No such file: ", file);
            return 0L;
        }
        long length = file.length();
        if (length > 52428800) {
            length = -1;
        }
        return of((int) length, FileUtil.inputStream(file));
    }

    public long getLength() {
        return this.mLength;
    }

    public long getValue() {
        return this.mCrc ^ 4294967295L;
    }

    public void reset() {
        this.mCrc = 4294967295L;
        this.mLength = 0L;
    }

    public void update(byte[] bArr, int i, int i2) {
        long j = this.mCrc;
        int i3 = i + i2;
        long[] jArr = CRC_TABLE;
        while (i < i3) {
            j = (j >> 8) ^ jArr[(int) ((((long) (bArr[i] & 255)) ^ j) & 255)];
            i++;
        }
        this.mCrc = j;
        this.mLength += (long) i2;
    }

    public static long of(byte[] bArr, int i, int i2) {
        Crc32 crc32 = new Crc32();
        crc32.update(bArr, i, i2);
        return crc32.getValue();
    }

    public static long of(byte[] bArr) {
        return of(bArr, 0, bArr.length);
    }

    public static long of(InputStream inputStream) throws IOException {
        return of(-1, inputStream);
    }

    public static long of(int i, InputStream inputStream) throws IOException {
        if (((-16777216) & i) != 0) {
            i = 4194304;
        }
        byte[] bArr = new byte[i];
        Crc32 crc32 = new Crc32();
        while (true) {
            int i2 = inputStream.read(bArr, 0, i);
            if (i2 != -1) {
                crc32.update(bArr, 0, i2);
            } else {
                inputStream.close();
                return crc32.getValue();
            }
        }
    }
}
