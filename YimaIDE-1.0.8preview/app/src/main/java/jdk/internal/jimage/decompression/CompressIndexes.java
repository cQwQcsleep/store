package jdk.internal.jimage.decompression;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CompressIndexes {
    private static final int COMPRESSED_FLAG = 128;
    private static final int HEADER_SHIFT = 5;
    private static final int HEADER_WIDTH = 3;

    public static byte[] compress(int i) {
        if (i < 0) {
            w01.a("value < 0");
            return null;
        }
        int iMin = Math.min(((34 - Integer.numberOfLeadingZeros(i)) >> 3) + 1, 4);
        byte[] bArr = new byte[iMin];
        for (int i2 = 0; i2 < iMin; i2++) {
            bArr[i2] = (byte) (i >> (((iMin - i2) - 1) * 8));
        }
        if (iMin < 4) {
            bArr[0] = (byte) (bArr[0] | ((byte) ((iMin << 5) | 128)));
        }
        return bArr;
    }

    public static int decompress(byte[] bArr, int i) {
        byte b = bArr[i];
        int headerLength = getHeaderLength(b);
        int headerValue = getHeaderValue(b);
        for (int i2 = 1; i2 < headerLength; i2++) {
            headerValue = (headerValue << 8) | (bArr[i + i2] & UByte.MAX_VALUE);
        }
        return headerValue;
    }

    public static List<Integer> decompressFlow(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int headerLength = 0;
        while (headerLength < bArr.length) {
            arrayList.add(Integer.valueOf(decompress(bArr, headerLength)));
            headerLength += getHeaderLength(bArr[headerLength]);
        }
        return arrayList;
    }

    private static int getHeaderLength(byte b) {
        if (isCompressed(b)) {
            return (b >> 5) & 3;
        }
        return 4;
    }

    private static int getHeaderValue(byte b) {
        return isCompressed(b) ? b & 31 : b;
    }

    private static boolean isCompressed(byte b) {
        return (b & ByteCompanionObject.MIN_VALUE) != 0;
    }

    public static int readInt(DataInputStream dataInputStream) throws IOException {
        byte b = dataInputStream.readByte();
        int headerLength = getHeaderLength(b);
        int headerValue = getHeaderValue(b);
        for (int i = 1; i < headerLength; i++) {
            headerValue = (headerValue << 8) | (dataInputStream.readByte() & UByte.MAX_VALUE);
        }
        return headerValue;
    }
}
