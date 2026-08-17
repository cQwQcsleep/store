package com.sun.org.apache.xerces.internal.impl.io;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UTF8Reader extends Reader {
    private static final boolean DEBUG_READ = false;
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected byte[] fBuffer;
    private MessageFormatter fFormatter;
    protected InputStream fInputStream;
    private Locale fLocale;
    protected int fOffset;
    private int fSurrogate;

    public UTF8Reader(InputStream inputStream, int i, MessageFormatter messageFormatter, Locale locale) {
        this.fSurrogate = -1;
        this.fFormatter = null;
        this.fLocale = null;
        this.fInputStream = inputStream;
        byte[] byteBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getByteBuffer(i);
        this.fBuffer = byteBuffer;
        if (byteBuffer == null) {
            this.fBuffer = new byte[i];
        }
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }

    private void expectedByte(int i, int i2) throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "ExpectedByte", new Object[]{Integer.toString(i), Integer.toString(i2)});
    }

    private void invalidByte(int i, int i2, int i3) throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidByte", new Object[]{Integer.toString(i), Integer.toString(i2)});
    }

    private void invalidSurrogate(int i) throws MalformedByteSequenceException {
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidHighSurrogate", new Object[]{Integer.toHexString(i)});
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ThreadLocalBufferAllocator.getBufferAllocator().returnByteBuffer(this.fBuffer);
        this.fBuffer = null;
        this.fInputStream.close();
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[]{"mark()", "UTF-8"}));
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x019e  */
    /* JADX WARN: Code duplicated, block: B:110:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:112:0x01ad A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:114:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:118:0x01c6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:120:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:121:0x01da  */
    /* JADX WARN: Code duplicated, block: B:123:0x01de  */
    /* JADX WARN: Code duplicated, block: B:124:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ed A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:128:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:132:0x0209 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:134:0x021d  */
    /* JADX WARN: Code duplicated, block: B:135:0x0220  */
    /* JADX WARN: Code duplicated, block: B:138:0x0237  */
    /* JADX WARN: Code duplicated, block: B:140:0x0243  */
    /* JADX WARN: Code duplicated, block: B:155:0x010f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:156:0x0128 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:0x01af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:160:0x01c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:0x01ef A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x020b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:163:0x0225 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:75:0x0105  */
    /* JADX WARN: Code duplicated, block: B:77:0x010d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x011b  */
    /* JADX WARN: Code duplicated, block: B:83:0x0126 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x0137  */
    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int length;
        int i3;
        char c;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15 = this.fSurrogate;
        int i16 = -1;
        if (i15 != -1) {
            i3 = i + 1;
            cArr[i3] = (char) i15;
            this.fSurrogate = -1;
            length = i2 - 1;
        } else {
            length = i2;
            i3 = i;
        }
        int i17 = this.fOffset;
        char c2 = 0;
        if (i17 == 0) {
            byte[] bArr = this.fBuffer;
            if (length > bArr.length) {
                length = bArr.length;
            }
            int i18 = this.fInputStream.read(bArr, 0, length);
            if (i18 == -1) {
                return -1;
            }
            i17 = (i3 - i) + i18;
        } else {
            this.fOffset = 0;
        }
        int i19 = 0;
        while (i19 < i17) {
            byte b = this.fBuffer[i19];
            if (b < 0) {
                break;
            }
            cArr[i3] = (char) b;
            i19++;
            i3++;
        }
        int i20 = i17;
        while (i19 < i17) {
            byte[] bArr2 = this.fBuffer;
            byte b2 = bArr2[i19];
            if (b2 >= 0) {
                i13 = i3 + 1;
                cArr[i3] = (char) b2;
                c = c2;
            } else {
                int i21 = b2 & 255;
                c = c2;
                if ((b2 & 224) != 192 || (b2 & 30) == 0) {
                    if ((b2 & 240) == 224) {
                        int i22 = i19 + 1;
                        if (i22 < i17) {
                            i11 = bArr2[i22] & 255;
                        } else {
                            i11 = this.fInputStream.read();
                            if (i11 == i16) {
                                if (i3 > i) {
                                    this.fBuffer[c] = (byte) i21;
                                    this.fOffset = 1;
                                } else {
                                    expectedByte(2, 3);
                                }
                            }
                            i20++;
                        }
                        if ((i11 & 192) == 128 && ((i21 != 237 || i11 < 160) && ((b2 & 15) != 0 || (i11 & 32) != 0))) {
                            i19 += 2;
                            if (i19 < i17) {
                                i12 = this.fBuffer[i19] & 255;
                            } else {
                                i12 = this.fInputStream.read();
                                if (i12 == i16) {
                                    if (i3 > i) {
                                        byte[] bArr3 = this.fBuffer;
                                        bArr3[c] = (byte) i21;
                                        bArr3[1] = (byte) i11;
                                        this.fOffset = 2;
                                    } else {
                                        expectedByte(3, 3);
                                    }
                                }
                                i20++;
                            }
                            if ((i12 & 192) != 128) {
                                if (i3 > i) {
                                    byte[] bArr4 = this.fBuffer;
                                    bArr4[c] = (byte) i21;
                                    bArr4[1] = (byte) i11;
                                    bArr4[2] = (byte) i12;
                                    this.fOffset = 3;
                                } else {
                                    invalidByte(3, 3, i12);
                                }
                            }
                            int i23 = ((i21 << 12) & 61440) | ((i11 << 6) & 4032) | (i12 & 63);
                            i13 = i3 + 1;
                            cArr[i3] = (char) i23;
                            i20 -= 2;
                        } else if (i3 > i) {
                            byte[] bArr5 = this.fBuffer;
                            bArr5[c] = (byte) i21;
                            bArr5[1] = (byte) i11;
                            this.fOffset = 2;
                        } else {
                            invalidByte(2, 3, i11);
                            i19 += 2;
                            if (i19 < i17) {
                                i12 = this.fBuffer[i19] & 255;
                            } else {
                                i12 = this.fInputStream.read();
                                if (i12 == i16) {
                                    if (i3 > i) {
                                        byte[] bArr6 = this.fBuffer;
                                        bArr6[c] = (byte) i21;
                                        bArr6[1] = (byte) i11;
                                        this.fOffset = 2;
                                    } else {
                                        expectedByte(3, 3);
                                    }
                                }
                                i20++;
                            }
                            if ((i12 & 192) != 128) {
                                if (i3 > i) {
                                    byte[] bArr7 = this.fBuffer;
                                    bArr7[c] = (byte) i21;
                                    bArr7[1] = (byte) i11;
                                    bArr7[2] = (byte) i12;
                                    this.fOffset = 3;
                                } else {
                                    invalidByte(3, 3, i12);
                                }
                            }
                            int i24 = ((i21 << 12) & 61440) | ((i11 << 6) & 4032) | (i12 & 63);
                            i13 = i3 + 1;
                            cArr[i3] = (char) i24;
                            i20 -= 2;
                        }
                    } else if ((b2 & 248) == 240) {
                        int i25 = i19 + 1;
                        if (i25 < i17) {
                            i5 = bArr2[i25] & 255;
                        } else {
                            i5 = this.fInputStream.read();
                            if (i5 == i16) {
                                if (i3 > i) {
                                    this.fBuffer[c] = (byte) i21;
                                    this.fOffset = 1;
                                } else {
                                    expectedByte(2, 4);
                                }
                            }
                            i20++;
                        }
                        if ((i5 & 192) == 128 && ((i5 & 48) != 0 || (b2 & 7) != 0)) {
                            i6 = i19 + 2;
                            if (i6 < i17) {
                                i7 = this.fBuffer[i6] & 255;
                            } else {
                                i7 = this.fInputStream.read();
                                if (i7 == i16) {
                                    if (i3 > i) {
                                        byte[] bArr8 = this.fBuffer;
                                        bArr8[c] = (byte) i21;
                                        bArr8[1] = (byte) i5;
                                        this.fOffset = 2;
                                    } else {
                                        expectedByte(3, 4);
                                    }
                                }
                                i20++;
                            }
                            if ((i7 & 192) == 128) {
                                i19 += 3;
                                if (i19 < i17) {
                                    i8 = this.fBuffer[i19] & 255;
                                } else {
                                    i8 = this.fInputStream.read();
                                    if (i8 == i16) {
                                        if (i3 > i) {
                                            byte[] bArr9 = this.fBuffer;
                                            bArr9[c] = (byte) i21;
                                            bArr9[1] = (byte) i5;
                                            bArr9[2] = (byte) i7;
                                            this.fOffset = 3;
                                        } else {
                                            expectedByte(4, 4);
                                        }
                                    }
                                    i20++;
                                }
                                if ((i8 & 192) == 128) {
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr10 = this.fBuffer;
                                        bArr10[c] = (byte) i21;
                                        bArr10[1] = (byte) i5;
                                        bArr10[2] = (byte) i7;
                                        bArr10[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i26 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i26 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i26 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                } else if (i3 > i) {
                                    byte[] bArr11 = this.fBuffer;
                                    bArr11[c] = (byte) i21;
                                    bArr11[1] = (byte) i5;
                                    bArr11[2] = (byte) i7;
                                    bArr11[3] = (byte) i8;
                                    this.fOffset = 4;
                                } else {
                                    invalidByte(4, 4, i7);
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr12 = this.fBuffer;
                                        bArr12[c] = (byte) i21;
                                        bArr12[1] = (byte) i5;
                                        bArr12[2] = (byte) i7;
                                        bArr12[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i27 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i27 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i27 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                }
                            } else if (i3 > i) {
                                byte[] bArr13 = this.fBuffer;
                                bArr13[c] = (byte) i21;
                                bArr13[1] = (byte) i5;
                                bArr13[2] = (byte) i7;
                                this.fOffset = 3;
                            } else {
                                invalidByte(3, 4, i7);
                                i19 += 3;
                                if (i19 < i17) {
                                    i8 = this.fBuffer[i19] & 255;
                                } else {
                                    i8 = this.fInputStream.read();
                                    if (i8 == i16) {
                                        if (i3 > i) {
                                            byte[] bArr14 = this.fBuffer;
                                            bArr14[c] = (byte) i21;
                                            bArr14[1] = (byte) i5;
                                            bArr14[2] = (byte) i7;
                                            this.fOffset = 3;
                                        } else {
                                            expectedByte(4, 4);
                                        }
                                    }
                                    i20++;
                                }
                                if ((i8 & 192) == 128) {
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr15 = this.fBuffer;
                                        bArr15[c] = (byte) i21;
                                        bArr15[1] = (byte) i5;
                                        bArr15[2] = (byte) i7;
                                        bArr15[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i28 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i28 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i28 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                } else if (i3 > i) {
                                    byte[] bArr16 = this.fBuffer;
                                    bArr16[c] = (byte) i21;
                                    bArr16[1] = (byte) i5;
                                    bArr16[2] = (byte) i7;
                                    bArr16[3] = (byte) i8;
                                    this.fOffset = 4;
                                } else {
                                    invalidByte(4, 4, i7);
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr17 = this.fBuffer;
                                        bArr17[c] = (byte) i21;
                                        bArr17[1] = (byte) i5;
                                        bArr17[2] = (byte) i7;
                                        bArr17[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i29 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i29 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i29 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                }
                            }
                        } else if (i3 > i) {
                            byte[] bArr18 = this.fBuffer;
                            bArr18[c] = (byte) i21;
                            bArr18[1] = (byte) i5;
                            this.fOffset = 2;
                        } else {
                            invalidByte(2, 4, i5);
                            i6 = i19 + 2;
                            if (i6 < i17) {
                                i7 = this.fBuffer[i6] & 255;
                            } else {
                                i7 = this.fInputStream.read();
                                if (i7 == i16) {
                                    if (i3 > i) {
                                        byte[] bArr19 = this.fBuffer;
                                        bArr19[c] = (byte) i21;
                                        bArr19[1] = (byte) i5;
                                        this.fOffset = 2;
                                    } else {
                                        expectedByte(3, 4);
                                    }
                                }
                                i20++;
                            }
                            if ((i7 & 192) == 128) {
                                i19 += 3;
                                if (i19 < i17) {
                                    i8 = this.fBuffer[i19] & 255;
                                } else {
                                    i8 = this.fInputStream.read();
                                    if (i8 == i16) {
                                        if (i3 > i) {
                                            byte[] bArr110 = this.fBuffer;
                                            bArr110[c] = (byte) i21;
                                            bArr110[1] = (byte) i5;
                                            bArr110[2] = (byte) i7;
                                            this.fOffset = 3;
                                        } else {
                                            expectedByte(4, 4);
                                        }
                                    }
                                    i20++;
                                }
                                if ((i8 & 192) == 128) {
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr111 = this.fBuffer;
                                        bArr111[c] = (byte) i21;
                                        bArr111[1] = (byte) i5;
                                        bArr111[2] = (byte) i7;
                                        bArr111[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i210 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i210 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i210 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                } else if (i3 > i) {
                                    byte[] bArr112 = this.fBuffer;
                                    bArr112[c] = (byte) i21;
                                    bArr112[1] = (byte) i5;
                                    bArr112[2] = (byte) i7;
                                    bArr112[3] = (byte) i8;
                                    this.fOffset = 4;
                                } else {
                                    invalidByte(4, 4, i7);
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr113 = this.fBuffer;
                                        bArr113[c] = (byte) i21;
                                        bArr113[1] = (byte) i5;
                                        bArr113[2] = (byte) i7;
                                        bArr113[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i211 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i211 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i211 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                }
                            } else if (i3 > i) {
                                byte[] bArr114 = this.fBuffer;
                                bArr114[c] = (byte) i21;
                                bArr114[1] = (byte) i5;
                                bArr114[2] = (byte) i7;
                                this.fOffset = 3;
                            } else {
                                invalidByte(3, 4, i7);
                                i19 += 3;
                                if (i19 < i17) {
                                    i8 = this.fBuffer[i19] & 255;
                                } else {
                                    i8 = this.fInputStream.read();
                                    if (i8 == i16) {
                                        if (i3 > i) {
                                            byte[] bArr115 = this.fBuffer;
                                            bArr115[c] = (byte) i21;
                                            bArr115[1] = (byte) i5;
                                            bArr115[2] = (byte) i7;
                                            this.fOffset = 3;
                                        } else {
                                            expectedByte(4, 4);
                                        }
                                    }
                                    i20++;
                                }
                                if ((i8 & 192) == 128) {
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr116 = this.fBuffer;
                                        bArr116[c] = (byte) i21;
                                        bArr116[1] = (byte) i5;
                                        bArr116[2] = (byte) i7;
                                        bArr116[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i212 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i212 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i212 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                } else if (i3 > i) {
                                    byte[] bArr117 = this.fBuffer;
                                    bArr117[c] = (byte) i21;
                                    bArr117[1] = (byte) i5;
                                    bArr117[2] = (byte) i7;
                                    bArr117[3] = (byte) i8;
                                    this.fOffset = 4;
                                } else {
                                    invalidByte(4, 4, i7);
                                    i9 = i3 + 1;
                                    if (i9 >= cArr.length) {
                                        byte[] bArr118 = this.fBuffer;
                                        bArr118[c] = (byte) i21;
                                        bArr118[1] = (byte) i5;
                                        bArr118[2] = (byte) i7;
                                        bArr118[3] = (byte) i8;
                                        this.fOffset = 4;
                                    } else {
                                        i10 = ((i21 << 2) & 28) | ((i5 >> 4) & 3);
                                        if (i10 > 16) {
                                            invalidSurrogate(i10);
                                        }
                                        int i213 = i7 & 63;
                                        cArr[i3] = (char) (((i5 & 15) << 2) | (((i10 - 1) << 6) & 960) | 55296 | (i213 >> 4));
                                        i3 += 2;
                                        cArr[i9] = (char) (((i213 << 6) & 960) | 56320 | (i8 & 63));
                                        i20 -= 2;
                                        i4 = 1;
                                    }
                                }
                            }
                        }
                    } else if (i3 > i) {
                        bArr2[c] = (byte) i21;
                        this.fOffset = 1;
                    } else {
                        i4 = 1;
                        invalidByte(1, 1, i21);
                    }
                    return i3 - i;
                }
                i19++;
                if (i19 < i17) {
                    i14 = bArr2[i19] & 255;
                } else {
                    i14 = this.fInputStream.read();
                    if (i14 == i16) {
                        if (i3 > i) {
                            this.fBuffer[c] = (byte) i21;
                            this.fOffset = 1;
                        } else {
                            expectedByte(2, 2);
                        }
                        return i3 - i;
                    }
                    i20++;
                }
                if ((i14 & 192) != 128) {
                    if (i3 > i) {
                        byte[] bArr20 = this.fBuffer;
                        bArr20[c] = (byte) i21;
                        bArr20[1] = (byte) i14;
                        this.fOffset = 2;
                        return i3 - i;
                    }
                    invalidByte(2, 2, i14);
                }
                int i30 = ((i21 << 6) & 1984) | (i14 & 63);
                i13 = i3 + 1;
                cArr[i3] = (char) i30;
                i20 += i16;
                i19 += i4;
                c2 = c;
                i16 = -1;
            }
            i3 = i13;
            i4 = 1;
            i19 += i4;
            c2 = c;
            i16 = -1;
        }
        return i20;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return false;
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.fOffset = 0;
        this.fSurrogate = -1;
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        int length = this.fBuffer.length;
        char[] cArr = new char[length];
        long j2 = j;
        do {
            int i = read(cArr, 0, ((long) length) < j2 ? length : (int) j2);
            if (i <= 0) {
                break;
            }
            j2 -= (long) i;
        } while (j2 > 0);
        return j - j2;
    }

    public UTF8Reader(InputStream inputStream, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 2048, messageFormatter, locale);
    }

    public UTF8Reader(InputStream inputStream) {
        this(inputStream, 2048, new XMLMessageFormatter(), Locale.getDefault());
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = this.fSurrogate;
        if (i7 == -1) {
            int i8 = 0;
            if (this.fOffset == 0) {
                i = this.fInputStream.read();
            } else {
                i = this.fBuffer[0] & 255;
                i8 = 1;
            }
            if (i == -1) {
                return -1;
            }
            if (i < 128) {
                return (char) i;
            }
            if ((i & WinError.ERROR_FORMS_AUTH_REQUIRED) == 192 && (i & 30) != 0) {
                int i9 = i8 == this.fOffset ? this.fInputStream.read() : this.fBuffer[i8] & 255;
                if (i9 == -1) {
                    expectedByte(2, 2);
                }
                if ((i9 & 192) != 128) {
                    invalidByte(2, 2, i9);
                }
                i5 = (i << 6) & 1984;
                i6 = i9 & 63;
            } else {
                if ((i & 240) != 224) {
                    if ((i & Const.CHOP_FRAME) == 240) {
                        if (i8 == this.fOffset) {
                            i2 = this.fInputStream.read();
                        } else {
                            i2 = this.fBuffer[i8] & 255;
                            i8++;
                        }
                        if (i2 == -1) {
                            expectedByte(2, 4);
                        }
                        if ((i2 & 192) != 128 || ((i2 & 48) == 0 && (i & 7) == 0)) {
                            invalidByte(2, 3, i2);
                        }
                        if (i8 == this.fOffset) {
                            i3 = this.fInputStream.read();
                        } else {
                            i3 = this.fBuffer[i8] & 255;
                            i8++;
                        }
                        if (i3 == -1) {
                            expectedByte(3, 4);
                        }
                        if ((i3 & 192) != 128) {
                            invalidByte(3, 3, i3);
                        }
                        int i10 = i8 == this.fOffset ? this.fInputStream.read() : this.fBuffer[i8] & 255;
                        if (i10 == -1) {
                            expectedByte(4, 4);
                        }
                        if ((i10 & 192) != 128) {
                            invalidByte(4, 4, i10);
                        }
                        int i11 = ((i << 2) & 28) | ((i2 >> 4) & 3);
                        if (i11 > 16) {
                            invalidSurrogate(i11);
                        }
                        int i12 = ((i2 << 2) & 60) | (((i11 - 1) << 6) & 960) | 55296 | ((i3 >> 4) & 3);
                        this.fSurrogate = ((i3 << 6) & 960) | 56320 | (i10 & 63);
                        return i12;
                    }
                    invalidByte(1, 1, i);
                    return i7;
                }
                if (i8 == this.fOffset) {
                    i4 = this.fInputStream.read();
                } else {
                    i4 = this.fBuffer[i8] & 255;
                    i8++;
                }
                if (i4 == -1) {
                    expectedByte(2, 3);
                }
                if ((i4 & 192) != 128 || ((i == 237 && i4 >= 160) || ((i & 15) == 0 && (i4 & 32) == 0))) {
                    invalidByte(2, 3, i4);
                }
                int i13 = i8 == this.fOffset ? this.fInputStream.read() : this.fBuffer[i8] & 255;
                if (i13 == -1) {
                    expectedByte(3, 3);
                }
                if ((i13 & 192) != 128) {
                    invalidByte(3, 3, i13);
                }
                i5 = ((i << 12) & 61440) | ((i4 << 6) & 4032);
                i6 = i13 & 63;
            }
            return i5 | i6;
        }
        this.fSurrogate = -1;
        return i7;
    }
}
