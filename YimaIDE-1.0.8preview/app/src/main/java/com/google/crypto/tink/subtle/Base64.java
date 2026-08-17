package com.google.crypto.tink.subtle;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);
    }

    public static class Decoder extends Coder {
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;
        private static final int EQUALS = -2;
        private static final int[] DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, EQUALS, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, EQUALS, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        public Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public int maxOutputSize(int i) {
            return ((i * 3) / 4) + 10;
        }

        /* JADX WARN: Code duplicated, block: B:42:0x00c0  */
        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            int i3 = this.state;
            if (i3 == 6) {
                return Base64.$assertionsDisabled;
            }
            int i4 = i2 + i;
            int i5 = this.value;
            byte[] bArr2 = this.output;
            int[] iArr = this.alphabet;
            int i6 = 0;
            int i7 = i5;
            int i8 = i3;
            int i9 = i;
            while (i9 < i4) {
                if (i8 == 0) {
                    while (true) {
                        int i10 = i9 + 4;
                        if (i10 > i4 || (i7 = (iArr[bArr[i9] & UnsignedBytes.MAX_VALUE] << 18) | (iArr[bArr[i9 + 1] & UnsignedBytes.MAX_VALUE] << 12) | (iArr[bArr[i9 + 2] & UnsignedBytes.MAX_VALUE] << 6) | iArr[bArr[i9 + 3] & UnsignedBytes.MAX_VALUE]) < 0) {
                            break;
                        }
                        bArr2[i6 + 2] = (byte) i7;
                        bArr2[i6 + 1] = (byte) (i7 >> 8);
                        bArr2[i6] = (byte) (i7 >> 16);
                        i6 += 3;
                        i9 = i10;
                    }
                    if (i9 >= i4) {
                        break;
                    }
                }
                int i11 = i9 + 1;
                int i12 = iArr[bArr[i9] & UnsignedBytes.MAX_VALUE];
                if (i8 != 0) {
                    if (i8 != 1) {
                        if (i8 != 2) {
                            if (i8 != 3) {
                                if (i8 != 4) {
                                    if (i8 == 5 && i12 != -1) {
                                        this.state = 6;
                                        return Base64.$assertionsDisabled;
                                    }
                                } else if (i12 == EQUALS) {
                                    i8++;
                                } else if (i12 != -1) {
                                    this.state = 6;
                                    return Base64.$assertionsDisabled;
                                }
                            } else if (i12 >= 0) {
                                int i13 = i12 | (i7 << 6);
                                bArr2[i6 + 2] = (byte) i13;
                                bArr2[i6 + 1] = (byte) (i13 >> 8);
                                bArr2[i6] = (byte) (i13 >> 16);
                                i6 += 3;
                                i7 = i13;
                                i8 = 0;
                            } else if (i12 == EQUALS) {
                                bArr2[i6 + 1] = (byte) (i7 >> 2);
                                bArr2[i6] = (byte) (i7 >> 10);
                                i6 += 2;
                                i8 = 5;
                            } else if (i12 != -1) {
                                this.state = 6;
                                return Base64.$assertionsDisabled;
                            }
                        } else if (i12 >= 0) {
                            i12 |= i7 << 6;
                            i8++;
                            i7 = i12;
                        } else if (i12 == EQUALS) {
                            bArr2[i6] = (byte) (i7 >> 4);
                            i6++;
                            i8 = 4;
                        } else if (i12 != -1) {
                            this.state = 6;
                            return Base64.$assertionsDisabled;
                        }
                    } else if (i12 >= 0) {
                        i12 |= i7 << 6;
                        i8++;
                        i7 = i12;
                    } else if (i12 != -1) {
                        this.state = 6;
                        return Base64.$assertionsDisabled;
                    }
                } else if (i12 >= 0) {
                    i8++;
                    i7 = i12;
                } else if (i12 != -1) {
                    this.state = 6;
                    return Base64.$assertionsDisabled;
                }
                i9 = i11;
            }
            if (!z) {
                this.state = i8;
                this.value = i7;
                this.op = i6;
                return true;
            }
            if (i8 == 1) {
                this.state = 6;
                return Base64.$assertionsDisabled;
            }
            if (i8 == 2) {
                bArr2[i6] = (byte) (i7 >> 4);
                i6++;
            } else if (i8 == 3) {
                int i14 = i6 + 1;
                bArr2[i6] = (byte) (i7 >> 10);
                i6 += 2;
                bArr2[i14] = (byte) (i7 >> 2);
            } else if (i8 == 4) {
                this.state = 6;
                return Base64.$assertionsDisabled;
            }
            this.state = i8;
            this.op = i6;
            return true;
        }
    }

    public static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean doCr;
        public final boolean doNewline;
        public final boolean doPadding;
        private final byte[] tail;
        int tailLen;

        public Encoder(int i, byte[] bArr) {
            this.output = bArr;
            this.doPadding = (i & 1) == 0;
            boolean z = (i & 2) == 0;
            this.doNewline = z;
            this.doCr = (i & 4) != 0;
            this.alphabet = (i & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = z ? 19 : -1;
        }

        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public int maxOutputSize(int i) {
            return ((i * 8) / 5) + 10;
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0050  */
        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            int i5;
            int i6;
            byte b;
            byte b2;
            byte b3;
            int i7;
            int i8;
            byte[] bArr2 = this.alphabet;
            byte[] bArr3 = this.output;
            int i9 = this.count;
            int i10 = i2 + i;
            int i11 = this.tailLen;
            char c = 2;
            int i12 = 0;
            if (i11 != 1) {
                if (i11 == 2 && (i8 = i + 1) <= i10) {
                    byte[] bArr4 = this.tail;
                    i4 = ((bArr4[1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr4[0] & UnsignedBytes.MAX_VALUE) << 16) | (bArr[i] & UnsignedBytes.MAX_VALUE);
                    this.tailLen = 0;
                    i3 = i8;
                } else {
                    i3 = i;
                    i4 = -1;
                }
            } else if (i + 2 <= i10) {
                i3 = i + 2;
                i4 = (bArr[i + 1] & UnsignedBytes.MAX_VALUE) | ((this.tail[0] & UnsignedBytes.MAX_VALUE) << 16) | ((bArr[i] & UnsignedBytes.MAX_VALUE) << 8);
                this.tailLen = 0;
            } else {
                i3 = i;
                i4 = -1;
            }
            if (i4 != -1) {
                bArr3[0] = bArr2[(i4 >> 18) & 63];
                bArr3[1] = bArr2[(i4 >> 12) & 63];
                bArr3[2] = bArr2[(i4 >> 6) & 63];
                bArr3[3] = bArr2[i4 & 63];
                i9--;
                if (i9 == 0) {
                    if (this.doCr) {
                        bArr3[4] = Ascii.CR;
                        i7 = 5;
                    } else {
                        i7 = 4;
                    }
                    i5 = i7 + 1;
                    bArr3[i7] = 10;
                    i9 = 19;
                } else {
                    i5 = 4;
                }
            } else {
                i5 = 0;
            }
            while (true) {
                i3 += 3;
                if (i3 > i10) {
                    break;
                }
                c = c;
                int i13 = ((bArr[i3 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i3] & UnsignedBytes.MAX_VALUE) << 16) | (bArr[i3 + 2] & UnsignedBytes.MAX_VALUE);
                bArr3[i5] = bArr2[(i13 >> 18) & 63];
                bArr3[i5 + 1] = bArr2[(i13 >> 12) & 63];
                bArr3[i5 + 2] = bArr2[(i13 >> 6) & 63];
                bArr3[i5 + 3] = bArr2[i13 & 63];
                int i14 = i5 + 4;
                i9--;
                if (i9 == 0) {
                    if (this.doCr) {
                        bArr3[i14] = Ascii.CR;
                        i14 = i5 + 5;
                    }
                    i5 = i14 + 1;
                    bArr3[i14] = 10;
                    i9 = 19;
                } else {
                    i5 = i14;
                }
            }
            if (z) {
                int i15 = this.tailLen;
                if (i3 - i15 == i10 - 1) {
                    if (i15 > 0) {
                        b3 = this.tail[0];
                        i12 = 1;
                    } else {
                        b3 = bArr[i3];
                    }
                    int i16 = (b3 & UnsignedBytes.MAX_VALUE) << 4;
                    this.tailLen = i15 - i12;
                    bArr3[i5] = bArr2[(i16 >> 6) & 63];
                    int i17 = i5 + 2;
                    bArr3[i5 + 1] = bArr2[i16 & 63];
                    if (this.doPadding) {
                        bArr3[i17] = 61;
                        i17 = i5 + 4;
                        bArr3[i5 + 3] = 61;
                    }
                    if (this.doNewline) {
                        if (this.doCr) {
                            bArr3[i17] = Ascii.CR;
                            i17++;
                        }
                        i6 = i17 + 1;
                        bArr3[i17] = 10;
                        i5 = i6;
                    } else {
                        i5 = i17;
                    }
                } else if (i3 - i15 == i10 - 2) {
                    if (i15 > 1) {
                        b = this.tail[0];
                        i12 = 1;
                    } else {
                        byte b4 = bArr[i3];
                        i3++;
                        b = b4;
                    }
                    int i18 = (b & UnsignedBytes.MAX_VALUE) << 10;
                    if (i15 > 0) {
                        b2 = this.tail[i12];
                        i12++;
                    } else {
                        b2 = bArr[i3];
                    }
                    int i19 = i18 | ((b2 & UnsignedBytes.MAX_VALUE) << 2);
                    this.tailLen = i15 - i12;
                    bArr3[i5] = bArr2[(i19 >> 12) & 63];
                    bArr3[i5 + 1] = bArr2[(i19 >> 6) & 63];
                    int i20 = i5 + 3;
                    bArr3[i5 + 2] = bArr2[i19 & 63];
                    if (this.doPadding) {
                        bArr3[i20] = 61;
                        i20 = i5 + 4;
                    }
                    if (this.doNewline) {
                        if (this.doCr) {
                            bArr3[i20] = Ascii.CR;
                            i20++;
                        }
                        i6 = i20 + 1;
                        bArr3[i20] = 10;
                        i5 = i6;
                    } else {
                        i5 = i20;
                    }
                } else if (this.doNewline && i5 > 0 && i9 != 19) {
                    if (this.doCr) {
                        bArr3[i5] = Ascii.CR;
                        i5++;
                    }
                    i6 = i5 + 1;
                    bArr3[i5] = 10;
                    i5 = i6;
                }
            } else if (i3 == i10 - 1) {
                byte[] bArr5 = this.tail;
                int i21 = this.tailLen;
                this.tailLen = i21 + 1;
                bArr5[i21] = bArr[i3];
            } else if (i3 == i10 - 2) {
                byte[] bArr6 = this.tail;
                int i22 = this.tailLen;
                int i23 = i22 + 1;
                this.tailLen = i23;
                bArr6[i22] = bArr[i3];
                this.tailLen = i22 + 2;
                bArr6[i23] = bArr[i3 + 1];
            }
            this.op = i5;
            this.count = i9;
            return true;
        }
    }

    private Base64() {
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[(i2 * 3) / 4]);
        if (!decoder.process(bArr, i, i2, true)) {
            w01.a("bad base-64");
            return null;
        }
        int i4 = decoder.op;
        byte[] bArr2 = decoder.output;
        if (i4 == bArr2.length) {
            return bArr2;
        }
        byte[] bArr3 = new byte[i4];
        System.arraycopy(bArr2, 0, bArr3, 0, i4);
        return bArr3;
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!encoder.doPadding) {
            int i5 = i2 % 3;
            if (i5 == 1) {
                i4 += 2;
            } else if (i5 == 2) {
                i4 += 3;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (encoder.doNewline && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (encoder.doCr ? 2 : 1);
        }
        encoder.output = new byte[i4];
        encoder.process(bArr, i, i2, true);
        return encoder.output;
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            x01.a(e);
            return null;
        }
    }

    public static byte[] urlSafeDecode(String str) {
        return decode(str, 11);
    }

    public static String urlSafeEncode(byte[] bArr) {
        return encodeToString(bArr, 11);
    }

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return new String(encode(bArr, i, i2, i3), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            x01.a(e);
            return null;
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(UTF_8), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(String str) {
        return decode(str, 2);
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static String encode(byte[] bArr) {
        return encodeToString(bArr, 2);
    }
}
