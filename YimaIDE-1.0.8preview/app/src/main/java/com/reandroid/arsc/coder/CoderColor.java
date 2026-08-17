package com.reandroid.arsc.coder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class CoderColor extends Coder {
    private final int decodedStringLength;

    public CoderColor(int i) {
        this.decodedStringLength = i;
    }

    private static char byteToHex(int i) {
        int i2 = i & 15;
        return (char) (i2 < 10 ? i2 + 48 : i2 + 87);
    }

    private int encode(int[] iArr) {
        int length = iArr.length - 1;
        int i = 0;
        boolean z = length < 6;
        int i2 = length % 4 == 0 ? 0 : -16777216;
        while (length >= 0) {
            int i3 = iArr[length];
            i2 |= i3 << i;
            int i4 = i + 4;
            if (z) {
                i2 |= i3 << i4;
                i += 8;
            } else {
                i = i4;
            }
            length--;
        }
        return i2;
    }

    private static int[] hexToIntegers(String str) {
        int i;
        char[] charArray = str.toUpperCase().toCharArray();
        if (charArray[0] != '#') {
            return null;
        }
        int length = charArray.length;
        int[] iArr = new int[length];
        for (int i2 = 1; i2 < length; i2++) {
            char c = charArray[i2];
            if (c >= '0' && c <= '9') {
                i = c - '0';
            } else {
                if (c < 'A' || c > 'F') {
                    return null;
                }
                i = c - '7';
            }
            iArr[i2] = i;
        }
        return iArr;
    }

    @Override // com.reandroid.arsc.coder.Coder
    public boolean canStartWith(char c) {
        return c == '#';
    }

    @Override // com.reandroid.arsc.coder.Coder
    public String decode(int i) {
        StringBuilder sb = new StringBuilder("#");
        int i2 = this.decodedStringLength - 2;
        int i3 = i2 < 5 ? 8 : 4;
        for (int i4 = i2 * i3; i4 >= 0; i4 -= i3) {
            sb.append(byteToHex(i >> i4));
        }
        return sb.toString();
    }

    @Override // com.reandroid.arsc.coder.Coder
    public EncodeResult encode(String str) {
        int[] iArrHexToIntegers;
        if (str.length() == this.decodedStringLength && (iArrHexToIntegers = hexToIntegers(str)) != null) {
            return new EncodeResult(getValueType(), encode(iArrHexToIntegers));
        }
        return null;
    }
}
