package com.reandroid.dex.base;

import com.reandroid.arsc.item.BlockItem;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.io.ByteReader;
import com.reandroid.dex.io.StreamUtil;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DexBlockItem extends BlockItem implements SectionTool {
    public DexBlockItem(int i) {
        super(i);
    }

    public static long getSignedNumber(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        long j = 0;
        if (i3 > bArr.length) {
            return 0L;
        }
        int i4 = i3 - 1;
        int i5 = bArr[i4] & 255;
        while (i4 >= i) {
            j = (j << 8) | ((long) (bArr[i4] & 255));
            i4--;
        }
        return i5 > 127 ? (j - ((-1) >>> (64 - (i2 * 8)))) - 1 : j;
    }

    public static int readSleb128(ByteReader byteReader) throws IOException {
        int i = 128;
        int i2 = 0;
        int i3 = 0;
        while (i > 127 && i2 < 5) {
            i = byteReader.read();
            i3 |= (i & 127) << (i2 * 7);
            i2++;
        }
        if (i2 != 5 || i <= 127) {
            int i4 = 32 - (i2 * 7);
            return (i3 << i4) >> i4;
        }
        a16.a("Invalid sleb128 integer");
        return 0;
    }

    public static int readUleb128(ByteReader byteReader, int i) throws IOException {
        int i2 = 0;
        int i3 = 128;
        int i4 = 0;
        while (i3 > 127 && i2 < i) {
            i3 = byteReader.read();
            i4 |= (i3 & 127) << (i2 * 7);
            i2++;
        }
        if (i3 > 127) {
            t8g.a("Invalid uleb128 integer, size = ", i);
            return 0;
        }
        if (i2 <= i || (i3 & 15) <= 7) {
            return i4;
        }
        t8g.a("Encountered valid uleb128 that is out of range, size = ", i);
        return 0;
    }

    public static int readUleb128Large(ByteReader byteReader) throws IOException {
        return readUleb128(byteReader, 5);
    }

    public static int writeSleb128(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (i2 >= 0) {
            while (i2 > 63) {
                bArr[i + i3] = (byte) ((i2 & 127) | 128);
                i3++;
                i2 >>>= 7;
            }
            bArr[i + i3] = (byte) (i2 & 127);
        } else {
            while (i2 < -64) {
                bArr[i + i3] = (byte) ((i2 & 127) | 128);
                i3++;
                i2 >>= 7;
            }
            bArr[i + i3] = (byte) (i2 & 127);
        }
        return i3 + 1;
    }

    public static int writeUleb128(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while ((((long) i2) & 4294967295L) > 127) {
            bArr[i + i3] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
            i3++;
        }
        bArr[i + i3] = (byte) i2;
        return i3 + 1;
    }

    public static int readUleb128(byte[] bArr, int i) throws IOException {
        return readUleb128(StreamUtil.createByteReader(bArr, i));
    }

    public static int readUleb128(ByteReader byteReader) throws IOException {
        return readUleb128(byteReader, 4);
    }

    public static int readUleb128(InputStream inputStream) throws IOException {
        return readUleb128(StreamUtil.createByteReader(inputStream));
    }
}
