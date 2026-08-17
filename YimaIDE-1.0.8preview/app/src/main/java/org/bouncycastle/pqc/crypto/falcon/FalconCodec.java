package org.bouncycastle.pqc.crypto.falcon;

import io.github.rosemoe.sora.langs.textmate.folding.IndentRange;
import io.github.rosemoe.sora.widget.CodeEditor;
import kotlin.UByte;
import kotlin.UShort;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class FalconCodec {
    final byte[] max_fg_bits = {0, 8, 8, 8, 8, 8, 7, 7, 6, 6, 5};
    final byte[] max_FG_bits = {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    final byte[] max_sig_bits = {0, 10, 11, 11, 12, 12, 12, 12, 12, 12, 12};

    public int comp_decode(short[] sArr, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 1 << i2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < i5; i9++) {
            if (i8 >= i4) {
                return 0;
            }
            i6 = (i6 << 8) | (bArr[i3 + i8] & UByte.MAX_VALUE);
            i8++;
            int i10 = i6 >>> i7;
            int i11 = i10 & CodeEditor.FLAG_DRAW_SOFT_WRAP;
            int i12 = i10 & 127;
            do {
                if (i7 == 0) {
                    if (i8 >= i4) {
                        return 0;
                    }
                    i6 = (i6 << 8) | (bArr[i3 + i8] & UByte.MAX_VALUE);
                    i8++;
                    i7 = 8;
                }
                i7--;
                if (((i6 >>> i7) & 1) == 0) {
                    i12 += CodeEditor.FLAG_DRAW_SOFT_WRAP;
                } else {
                    if (i11 != 0 && i12 == 0) {
                        return 0;
                    }
                    int i13 = i + i9;
                    if (i11 != 0) {
                        i12 = -i12;
                    }
                    sArr[i13] = (short) i12;
                }
            } while (i12 <= 2047);
            return 0;
        }
        if ((i6 & ((1 << i7) - 1)) != 0) {
            return 0;
        }
        return i8;
    }

    public int comp_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4) {
        int i5;
        int i6 = 1 << i4;
        for (int i7 = 0; i7 < i6; i7++) {
            short s = sArr[i3 + i7];
            if (s < -2047 || s > 2047) {
                return 0;
            }
        }
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < i6; i11++) {
            int i12 = i9 << 1;
            short s2 = sArr[i3 + i11];
            if (s2 < 0) {
                i5 = s2;
                i12 |= 1;
                i5 = -s2;
            }
            i5 = s2;
            int i13 = (i12 << 7) | (i5 & 127);
            int i14 = (i5 >>> 7) + 1;
            i9 = (i13 << i14) | 1;
            i8 = i8 + 8 + i14;
            while (i8 >= 8) {
                i8 -= 8;
                if (bArr != null) {
                    if (i10 >= i2) {
                        return 0;
                    }
                    bArr[i + i10] = (byte) (i9 >>> i8);
                }
                i10++;
            }
        }
        if (i8 <= 0) {
            return i10;
        }
        if (bArr != null) {
            if (i10 >= i2) {
                return 0;
            }
            bArr[i + i10] = (byte) (i9 << (8 - i8));
        }
        return i10 + 1;
    }

    public int modq_decode(short[] sArr, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 1 << i2;
        int i6 = ((i5 * 14) + 7) >> 3;
        if (i6 > i4) {
            return 0;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i5) {
            int i10 = i3 + 1;
            i8 = (i8 << 8) | (bArr[i3] & UByte.MAX_VALUE);
            int i11 = i9 + 8;
            if (i11 >= 14) {
                i9 -= 6;
                int i12 = (i8 >>> i9) & 16383;
                if (i12 >= 12289) {
                    return 0;
                }
                sArr[i + i7] = (short) i12;
                i7++;
            } else {
                i9 = i11;
            }
            i3 = i10;
        }
        if ((i8 & ((1 << i9) - 1)) != 0) {
            return 0;
        }
        return i6;
    }

    public int modq_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4) {
        int i5 = 1 << i4;
        for (int i6 = 0; i6 < i5; i6++) {
            if ((65535 & sArr[i3 + i6]) >= 12289) {
                return 0;
            }
        }
        int i7 = ((i5 * 14) + 7) >> 3;
        if (bArr != null) {
            if (i7 > i2) {
                return 0;
            }
            int i8 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < i5; i10++) {
                i9 = (i9 << 14) | (sArr[i3 + i10] & UShort.MAX_VALUE);
                i8 += 14;
                while (i8 >= 8) {
                    i8 -= 8;
                    bArr[i] = (byte) (i9 >> i8);
                    i++;
                }
            }
            if (i8 > 0) {
                bArr[i] = (byte) (i9 << (8 - i8));
            }
        }
        return i7;
    }

    public int trim_i16_decode(short[] sArr, int i, int i2, int i3, byte[] bArr, int i4, int i5) {
        int i6 = 1 << i2;
        int i7 = ((i6 * i3) + 7) >> 3;
        if (i7 > i5) {
            return 0;
        }
        int i8 = (1 << i3) - 1;
        int i9 = 1 << (i3 - 1);
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i10 < i6) {
            int i13 = i4 + 1;
            i11 = (i11 << 8) | (bArr[i4] & 255);
            i12 += 8;
            while (i12 >= i3 && i10 < i6) {
                i12 -= i3;
                int i14 = (i11 >>> i12) & i8;
                int i15 = i14 | (-(i14 & i9));
                if (i15 == (-i9)) {
                    return 0;
                }
                sArr[i + i10] = (short) (i15 | (-(i15 & i9)));
                i10++;
            }
            i4 = i13;
        }
        if ((i11 & ((1 << i12) - 1)) != 0) {
            return 0;
        }
        return i7;
    }

    public int trim_i16_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4, int i5) {
        int i6 = 1 << i4;
        int i7 = (1 << (i5 - 1)) - 1;
        int i8 = -i7;
        for (int i9 = 0; i9 < i6; i9++) {
            short s = sArr[i3 + i9];
            if (s < i8 || s > i7) {
                return 0;
            }
        }
        int i10 = ((i6 * i5) + 7) >> 3;
        if (bArr != null) {
            if (i10 > i2) {
                return 0;
            }
            int i11 = (1 << i5) - 1;
            int i12 = 0;
            int i13 = 0;
            for (int i14 = 0; i14 < i6; i14++) {
                i13 = (i13 << i5) | (sArr[i3 + i14] & 4095 & i11);
                i12 += i5;
                while (i12 >= 8) {
                    i12 -= 8;
                    bArr[i] = (byte) (i13 >> i12);
                    i++;
                }
            }
            if (i12 > 0) {
                bArr[i] = (byte) (i13 << (8 - i12));
            }
        }
        return i10;
    }

    public int trim_i8_decode(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4, int i5) {
        int i6 = 1 << i2;
        int i7 = ((i6 * i3) + 7) >> 3;
        if (i7 > i5) {
            return 0;
        }
        int i8 = (1 << i3) - 1;
        int i9 = 1 << (i3 - 1);
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i10 < i6) {
            int i13 = i4 + 1;
            i11 = (i11 << 8) | (bArr2[i4] & 255);
            i12 += 8;
            while (i12 >= i3 && i10 < i6) {
                i12 -= i3;
                int i14 = (i11 >>> i12) & i8;
                int i15 = i14 | (-(i14 & i9));
                if (i15 == (-i9)) {
                    return 0;
                }
                bArr[i + i10] = (byte) i15;
                i10++;
            }
            i4 = i13;
        }
        if ((i11 & ((1 << i12) - 1)) != 0) {
            return 0;
        }
        return i7;
    }

    public int trim_i8_encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, int i5) {
        int i6 = 1 << i4;
        int i7 = (1 << (i5 - 1)) - 1;
        int i8 = -i7;
        for (int i9 = 0; i9 < i6; i9++) {
            int i10 = bArr2[i3 + i9];
            if (i10 < i8 || i10 > i7) {
                return 0;
            }
        }
        int i11 = ((i6 * i5) + 7) >> 3;
        if (bArr != null) {
            if (i11 > i2) {
                return 0;
            }
            int i12 = (1 << i5) - 1;
            int i13 = 0;
            int i14 = 0;
            for (int i15 = 0; i15 < i6; i15++) {
                i14 = (i14 << i5) | (bArr2[i3 + i15] & IndentRange.MAX_FOLDING_REGIONS & i12);
                i13 += i5;
                while (i13 >= 8) {
                    i13 -= 8;
                    bArr[i] = (byte) (i14 >>> i13);
                    i++;
                }
            }
            if (i13 > 0) {
                bArr[i] = (byte) (i14 << (8 - i13));
            }
        }
        return i11;
    }
}
