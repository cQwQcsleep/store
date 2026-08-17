package net.i2p.crypto.eddsa;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class Utils {
    public static int bit(byte[] bArr, int i) {
        return (bArr[i >> 3] >> (i & 7)) & 1;
    }

    public static String bytesToHex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(Character.forDigit((b & 240) >> 4, 16));
            sb.append(Character.forDigit(b & 15, 16));
        }
        return sb.toString();
    }

    public static int equal(byte[] bArr, byte[] bArr2) {
        int i = 0;
        for (int i2 = 0; i2 < 32; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return equal(i, 0);
    }

    public static byte[] hexToBytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static int negative(int i) {
        return (i >> 8) & 1;
    }

    public static int equal(int i, int i2) {
        int i3 = i ^ i2;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            i4 |= i3 >> i5;
        }
        return (i4 ^ 1) & 1;
    }
}
