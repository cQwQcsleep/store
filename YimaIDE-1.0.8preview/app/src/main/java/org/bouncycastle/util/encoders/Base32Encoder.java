package org.bouncycastle.util.encoders;

import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Base32Encoder implements Encoder {
    private static final byte[] DEAULT_ENCODING_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte DEFAULT_PADDING = 61;
    private final byte[] decodingTable;
    private final byte[] encodingTable;
    private final byte padding;

    public Base32Encoder(byte[] bArr, byte b) {
        this.decodingTable = new byte[CodeEditor.FLAG_DRAW_SOFT_WRAP];
        if (bArr.length != 32) {
            w01.a("encoding table needs to be length 32");
            throw null;
        }
        this.encodingTable = Arrays.clone(bArr);
        this.padding = b;
        initialiseDecodingTable();
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) throws IOException {
        char c9 = this.padding;
        if (c8 != c9) {
            byte[] bArr = this.decodingTable;
            byte b = bArr[c];
            byte b2 = bArr[c2];
            byte b3 = bArr[c3];
            byte b4 = bArr[c4];
            byte b5 = bArr[c5];
            byte b6 = bArr[c6];
            byte b7 = bArr[c7];
            byte b8 = bArr[c8];
            if ((b | b2 | b3 | b4 | b5 | b6 | b7 | b8) < 0) {
                a16.a("invalid characters encountered at end of base32 data");
                return 0;
            }
            outputStream.write((b << 3) | (b2 >> 2));
            outputStream.write((b3 << 1) | (b2 << 6) | (b4 >> 4));
            outputStream.write((b4 << 4) | (b5 >> 1));
            outputStream.write((b5 << 7) | (b6 << 2) | (b7 >> 3));
            outputStream.write(b8 | (b7 << 5));
            return 5;
        }
        if (c7 != c9) {
            byte[] bArr2 = this.decodingTable;
            byte b9 = bArr2[c];
            byte b10 = bArr2[c2];
            byte b11 = bArr2[c3];
            byte b12 = bArr2[c4];
            byte b13 = bArr2[c5];
            byte b14 = bArr2[c6];
            byte b15 = bArr2[c7];
            if ((b9 | b10 | b11 | b12 | b13 | b14 | b15) < 0) {
                a16.a("invalid characters encountered at end of base32 data");
                return 0;
            }
            outputStream.write((b9 << 3) | (b10 >> 2));
            outputStream.write((b11 << 1) | (b10 << 6) | (b12 >> 4));
            outputStream.write((b12 << 4) | (b13 >> 1));
            outputStream.write((b15 >> 3) | (b13 << 7) | (b14 << 2));
            return 4;
        }
        if (c6 != c9) {
            a16.a("invalid characters encountered at end of base32 data");
            return 0;
        }
        if (c5 != c9) {
            byte[] bArr3 = this.decodingTable;
            byte b16 = bArr3[c];
            byte b17 = bArr3[c2];
            byte b18 = bArr3[c3];
            byte b19 = bArr3[c4];
            byte b20 = bArr3[c5];
            if ((b16 | b17 | b18 | b19 | b20) < 0) {
                a16.a("invalid characters encountered at end of base32 data");
                return 0;
            }
            outputStream.write((b16 << 3) | (b17 >> 2));
            outputStream.write((b18 << 1) | (b17 << 6) | (b19 >> 4));
            outputStream.write((b20 >> 1) | (b19 << 4));
            return 3;
        }
        if (c4 == c9) {
            if (c3 != c9) {
                a16.a("invalid characters encountered at end of base32 data");
                return 0;
            }
            byte[] bArr4 = this.decodingTable;
            byte b21 = bArr4[c];
            byte b22 = bArr4[c2];
            if ((b21 | b22) < 0) {
                a16.a("invalid characters encountered at end of base32 data");
                return 0;
            }
            outputStream.write((b22 >> 2) | (b21 << 3));
            return 1;
        }
        byte[] bArr5 = this.decodingTable;
        byte b23 = bArr5[c];
        byte b24 = bArr5[c2];
        byte b25 = bArr5[c3];
        byte b26 = bArr5[c4];
        if ((b23 | b24 | b25 | b26) < 0) {
            a16.a("invalid characters encountered at end of base32 data");
            return 0;
        }
        outputStream.write((b23 << 3) | (b24 >> 2));
        int i = b26 >> 4;
        outputStream.write(i | (b25 << 1) | (b24 << 6));
        return 2;
    }

    private void encodeBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte b = bArr[i];
        int i3 = bArr[i + 1] & UByte.MAX_VALUE;
        int i4 = bArr[i + 2] & UByte.MAX_VALUE;
        int i5 = bArr[i + 3] & UByte.MAX_VALUE;
        byte b2 = bArr[i + 4];
        int i6 = b2 & UByte.MAX_VALUE;
        byte[] bArr3 = this.encodingTable;
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        bArr2[i2 + 1] = bArr3[((b << 2) | (i3 >>> 6)) & 31];
        bArr2[i2 + 2] = bArr3[(i3 >>> 1) & 31];
        bArr2[i2 + 3] = bArr3[((i3 << 4) | (i4 >>> 4)) & 31];
        bArr2[i2 + 4] = bArr3[((i4 << 1) | (i5 >>> 7)) & 31];
        bArr2[i2 + 5] = bArr3[(i5 >>> 2) & 31];
        bArr2[i2 + 6] = bArr3[((i6 >>> 5) | (i5 << 3)) & 31];
        bArr2[i2 + 7] = bArr3[b2 & 31];
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    private int nextI(byte[] bArr, int i, int i2) {
        while (i < i2 && ignore((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[55];
        int i3 = i + i2;
        while (i3 > i && ignore((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        if (i3 == 0) {
            return 0;
        }
        int i5 = i3;
        int i6 = 0;
        while (i5 > i && i6 != 8) {
            if (!ignore((char) bArr[i5 - 1])) {
                i6++;
            }
            i5--;
        }
        int iNextI = nextI(bArr, i, i5);
        int i7 = 0;
        int i8 = 0;
        while (iNextI < i5) {
            int i9 = iNextI + 1;
            byte b = this.decodingTable[bArr[iNextI]];
            int iNextI2 = nextI(bArr, i9, i5);
            int i10 = iNextI2 + 1;
            byte b2 = this.decodingTable[bArr[iNextI2]];
            int iNextI3 = nextI(bArr, i10, i5);
            int i11 = iNextI3 + 1;
            byte b3 = this.decodingTable[bArr[iNextI3]];
            int iNextI4 = nextI(bArr, i11, i5);
            int i12 = iNextI4 + 1;
            byte b4 = this.decodingTable[bArr[iNextI4]];
            int iNextI5 = nextI(bArr, i12, i5);
            int i13 = i4;
            int i14 = iNextI5 + 1;
            byte b5 = this.decodingTable[bArr[iNextI5]];
            int iNextI6 = nextI(bArr, i14, i5);
            int i15 = iNextI6 + 1;
            byte b6 = this.decodingTable[bArr[iNextI6]];
            int iNextI7 = nextI(bArr, i15, i5);
            int i16 = iNextI7 + 1;
            byte b7 = this.decodingTable[bArr[iNextI7]];
            int iNextI8 = nextI(bArr, i16, i5);
            int i17 = iNextI8 + 1;
            byte b8 = this.decodingTable[bArr[iNextI8]];
            if ((b | b2 | b3 | b4 | b5 | b6 | b7 | b8) < 0) {
                a16.a("invalid characters encountered in base32 data");
                return i13;
            }
            bArr2[i7] = (byte) ((b << 3) | (b2 >> 2));
            bArr2[i7 + 1] = (byte) ((b2 << 6) | (b3 << 1) | (b4 >> 4));
            bArr2[i7 + 2] = (byte) ((b4 << 4) | (b5 >> 1));
            int i18 = i7 + 4;
            bArr2[i7 + 3] = (byte) ((b6 << 2) | (b5 << 7) | (b7 >> 3));
            i7 += 5;
            bArr2[i18] = (byte) ((b7 << 5) | b8);
            if (i7 == 55) {
                outputStream.write(bArr2);
                i7 = i13;
            }
            i8 += 5;
            iNextI = nextI(bArr, i17, i5);
            i4 = i13;
        }
        int i19 = i4;
        if (i7 > 0) {
            outputStream.write(bArr2, i19, i7);
        }
        int iNextI9 = nextI(bArr, iNextI, i3);
        int iNextI10 = nextI(bArr, iNextI9 + 1, i3);
        int iNextI11 = nextI(bArr, iNextI10 + 1, i3);
        int iNextI12 = nextI(bArr, iNextI11 + 1, i3);
        int iNextI13 = nextI(bArr, iNextI12 + 1, i3);
        int iNextI14 = nextI(bArr, iNextI13 + 1, i3);
        int iNextI15 = nextI(bArr, iNextI14 + 1, i3);
        return i8 + decodeLastBlock(outputStream, (char) bArr[iNextI9], (char) bArr[iNextI10], (char) bArr[iNextI11], (char) bArr[iNextI12], (char) bArr[iNextI13], (char) bArr[iNextI14], (char) bArr[iNextI15], (char) bArr[nextI(bArr, iNextI15 + 1, i3)]);
    }

    public int encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException {
        int i4 = (i + i2) - 4;
        int i5 = i;
        int i6 = i3;
        while (i5 < i4) {
            encodeBlock(bArr, i5, bArr2, i6);
            i5 += 5;
            i6 += 8;
        }
        int i7 = i2 - (i5 - i);
        if (i7 > 0) {
            byte[] bArr3 = new byte[5];
            System.arraycopy(bArr, i5, bArr3, 0, i7);
            encodeBlock(bArr3, 0, bArr2, i6);
            if (i7 == 1) {
                byte b = this.padding;
                bArr2[i6 + 2] = b;
                bArr2[i6 + 3] = b;
                bArr2[i6 + 4] = b;
                bArr2[i6 + 5] = b;
                bArr2[i6 + 6] = b;
                bArr2[i6 + 7] = b;
            } else if (i7 == 2) {
                byte b2 = this.padding;
                bArr2[i6 + 4] = b2;
                bArr2[i6 + 5] = b2;
                bArr2[i6 + 6] = b2;
                bArr2[i6 + 7] = b2;
            } else if (i7 == 3) {
                byte b3 = this.padding;
                bArr2[i6 + 5] = b3;
                bArr2[i6 + 6] = b3;
                bArr2[i6 + 7] = b3;
            } else if (i7 == 4) {
                bArr2[i6 + 7] = this.padding;
            }
            i6 += 8;
        }
        return i6 - i3;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int i) {
        return ((i + 4) / 5) * 8;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int i) {
        return (i / 8) * 5;
    }

    public void initialiseDecodingTable() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }

    public Base32Encoder() {
        this.decodingTable = new byte[CodeEditor.FLAG_DRAW_SOFT_WRAP];
        this.encodingTable = DEAULT_ENCODING_TABLE;
        this.padding = (byte) 61;
        initialiseDecodingTable();
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        if (i2 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int i3 = i;
        int i4 = i2;
        while (i4 > 0) {
            int iMin = Math.min(45, i4);
            Base32Encoder base32Encoder = this;
            outputStream.write(bArr2, 0, base32Encoder.encode(bArr, i3, iMin, bArr2, 0));
            i3 += iMin;
            i4 -= iMin;
            this = base32Encoder;
        }
        return ((i2 + 2) / 3) * 4;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        byte[] byteArray = Strings.toByteArray(str);
        return decode(byteArray, 0, byteArray.length, outputStream);
    }
}
