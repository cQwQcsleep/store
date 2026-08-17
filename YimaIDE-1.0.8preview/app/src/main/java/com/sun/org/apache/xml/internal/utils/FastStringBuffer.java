package com.sun.org.apache.xml.internal.utils;

import java.lang.reflect.Array;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FastStringBuffer {
    private static final int CARRY_WS = 4;
    static final boolean DEBUG_FORCE_FIXED_CHUNKSIZE = true;
    static final int DEBUG_FORCE_INIT_BITS = 0;
    static final char[] SINGLE_SPACE = {' '};
    public static final int SUPPRESS_BOTH = 3;
    public static final int SUPPRESS_LEADING_WS = 1;
    public static final int SUPPRESS_TRAILING_WS = 2;
    char[][] m_array;
    int m_chunkBits;
    int m_chunkMask;
    int m_chunkSize;
    int m_firstFree;
    FastStringBuffer m_innerFSB;
    int m_lastChunk;
    int m_maxChunkBits;
    int m_rebundleBits;

    private FastStringBuffer(FastStringBuffer fastStringBuffer) {
        this.m_chunkBits = 15;
        this.m_maxChunkBits = 15;
        this.m_rebundleBits = 2;
        this.m_lastChunk = 0;
        this.m_firstFree = 0;
        this.m_innerFSB = null;
        this.m_chunkBits = fastStringBuffer.m_chunkBits;
        this.m_maxChunkBits = fastStringBuffer.m_maxChunkBits;
        int i = fastStringBuffer.m_rebundleBits;
        this.m_rebundleBits = i;
        this.m_chunkSize = fastStringBuffer.m_chunkSize;
        this.m_chunkMask = fastStringBuffer.m_chunkMask;
        this.m_array = fastStringBuffer.m_array;
        this.m_innerFSB = fastStringBuffer.m_innerFSB;
        this.m_lastChunk = fastStringBuffer.m_lastChunk - 1;
        this.m_firstFree = fastStringBuffer.m_chunkSize;
        fastStringBuffer.m_array = new char[16][];
        fastStringBuffer.m_innerFSB = this;
        fastStringBuffer.m_lastChunk = 1;
        fastStringBuffer.m_firstFree = 0;
        int i2 = fastStringBuffer.m_chunkBits + i;
        fastStringBuffer.m_chunkBits = i2;
        int i3 = 1 << i2;
        fastStringBuffer.m_chunkSize = i3;
        fastStringBuffer.m_chunkMask = i3 - 1;
    }

    private void getChars(int i, int i2, char[] cArr, int i3) {
    }

    public static int sendNormalizedSAXcharacters(char[] cArr, int i, int i2, ContentHandler contentHandler, int i3) throws SAXException {
        boolean z = (i3 & 1) != 0;
        boolean z2 = (i3 & 4) != 0;
        int i4 = i3 & 2;
        int i5 = i2 + i;
        if (z) {
            while (i < i5 && XMLCharacterRecognizer.isWhiteSpace(cArr[i])) {
                i++;
            }
            if (i == i5) {
                return i3;
            }
        }
        while (i < i5) {
            int i6 = i;
            while (i6 < i5 && !XMLCharacterRecognizer.isWhiteSpace(cArr[i6])) {
                i6++;
            }
            if (i != i6) {
                if (z2) {
                    contentHandler.characters(SINGLE_SPACE, 0, 1);
                    z2 = false;
                }
                contentHandler.characters(cArr, i, i6 - i);
            }
            i = i6;
            while (i < i5 && XMLCharacterRecognizer.isWhiteSpace(cArr[i])) {
                i++;
            }
            if (i6 != i) {
                z2 = true;
            }
        }
        return (z2 ? 4 : 0) | i4;
    }

    private final void setLength(int i, FastStringBuffer fastStringBuffer) {
        FastStringBuffer fastStringBuffer2;
        int i2 = this.m_chunkBits;
        int i3 = i >>> i2;
        this.m_lastChunk = i3;
        if (i3 == 0 && (fastStringBuffer2 = this.m_innerFSB) != null) {
            fastStringBuffer2.setLength(i, fastStringBuffer);
            return;
        }
        fastStringBuffer.m_chunkBits = i2;
        fastStringBuffer.m_maxChunkBits = this.m_maxChunkBits;
        fastStringBuffer.m_rebundleBits = this.m_rebundleBits;
        fastStringBuffer.m_chunkSize = this.m_chunkSize;
        fastStringBuffer.m_chunkMask = this.m_chunkMask;
        fastStringBuffer.m_array = this.m_array;
        fastStringBuffer.m_innerFSB = this.m_innerFSB;
        fastStringBuffer.m_lastChunk = i3;
        fastStringBuffer.m_firstFree = this.m_chunkMask & i;
    }

    public final void append(FastStringBuffer fastStringBuffer) {
        int length;
        if (fastStringBuffer == null || (length = fastStringBuffer.length()) == 0) {
            return;
        }
        char[] cArr = this.m_array[this.m_lastChunk];
        int i = this.m_chunkSize - this.m_firstFree;
        int i2 = 0;
        while (length > 0) {
            if (i > length) {
                i = length;
            }
            int i3 = fastStringBuffer.m_chunkSize;
            int i4 = ((i2 + i3) - 1) >>> fastStringBuffer.m_chunkBits;
            int i5 = fastStringBuffer.m_chunkMask & i2;
            int i6 = i3 - i5;
            if (i6 > i) {
                i6 = i;
            }
            System.arraycopy(fastStringBuffer.m_array[i4], i5, this.m_array[this.m_lastChunk], this.m_firstFree, i6);
            if (i6 != i) {
                System.arraycopy(fastStringBuffer.m_array[i4 + 1], 0, this.m_array[this.m_lastChunk], this.m_firstFree + i6, i - i6);
            }
            length -= i;
            i2 += i;
            if (length > 0) {
                char[][] cArr2 = this.m_array;
                int length2 = cArr2.length;
                if (this.m_lastChunk + 1 == length2) {
                    char[][] cArr3 = new char[length2 + 16][];
                    System.arraycopy(cArr2, 0, cArr3, 0, length2);
                    this.m_array = cArr3;
                }
                char[][] cArr4 = this.m_array;
                int i7 = this.m_lastChunk + 1;
                this.m_lastChunk = i7;
                if (cArr4[i7] == null) {
                    if (i7 == (1 << this.m_rebundleBits) && this.m_chunkBits < this.m_maxChunkBits) {
                        this.m_innerFSB = new FastStringBuffer(this);
                    }
                    this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
                }
                i = this.m_chunkSize;
                this.m_firstFree = 0;
            }
        }
        this.m_firstFree += i;
    }

    public char charAt(int i) {
        FastStringBuffer fastStringBuffer;
        int i2 = i >>> this.m_chunkBits;
        return (i2 != 0 || (fastStringBuffer = this.m_innerFSB) == null) ? this.m_array[i2][this.m_chunkMask & i] : fastStringBuffer.charAt(this.m_chunkMask & i);
    }

    public String getOneChunkString(int i, int i2, int i3) {
        return new String(this.m_array[i], i2, i3);
    }

    public StringBuffer getString(StringBuffer stringBuffer, int i, int i2, int i3) {
        FastStringBuffer fastStringBuffer;
        FastStringBuffer fastStringBuffer2;
        int i4 = this.m_chunkBits;
        int i5 = (i << i4) + i2 + i3;
        int i6 = i5 >>> i4;
        int i7 = this.m_chunkMask & i5;
        while (i < i6) {
            if (i != 0 || (fastStringBuffer2 = this.m_innerFSB) == null) {
                stringBuffer.append(this.m_array[i], i2, this.m_chunkSize - i2);
            } else {
                fastStringBuffer2.getString(stringBuffer, i2, this.m_chunkSize - i2);
            }
            i++;
            i2 = 0;
        }
        if (i6 == 0 && (fastStringBuffer = this.m_innerFSB) != null) {
            fastStringBuffer.getString(stringBuffer, i2, i7 - i2);
            return stringBuffer;
        }
        if (i7 > i2) {
            stringBuffer.append(this.m_array[i6], i2, i7 - i2);
        }
        return stringBuffer;
    }

    public boolean isWhitespace(int i, int i2) {
        FastStringBuffer fastStringBuffer;
        int i3 = i >>> this.m_chunkBits;
        int i4 = i & this.m_chunkMask;
        int i5 = this.m_chunkSize - i4;
        while (i2 > 0) {
            if (i2 <= i5) {
                i5 = i2;
            }
            if (!((i3 != 0 || (fastStringBuffer = this.m_innerFSB) == null) ? XMLCharacterRecognizer.isWhiteSpace(this.m_array[i3], i4, i5) : fastStringBuffer.isWhitespace(i4, i5))) {
                return false;
            }
            i2 -= i5;
            i3++;
            i5 = this.m_chunkSize;
            i4 = 0;
        }
        return true;
    }

    public final int length() {
        return (this.m_lastChunk << this.m_chunkBits) + this.m_firstFree;
    }

    public final void reset() {
        this.m_lastChunk = 0;
        this.m_firstFree = 0;
        FastStringBuffer fastStringBuffer = this;
        while (true) {
            FastStringBuffer fastStringBuffer2 = fastStringBuffer.m_innerFSB;
            if (fastStringBuffer2 == null) {
                this.m_chunkBits = fastStringBuffer.m_chunkBits;
                this.m_chunkSize = fastStringBuffer.m_chunkSize;
                this.m_chunkMask = fastStringBuffer.m_chunkMask;
                this.m_innerFSB = null;
                char[][] cArr = (char[][]) Array.newInstance((Class<?>) Character.TYPE, 16, 0);
                this.m_array = cArr;
                cArr[0] = new char[this.m_chunkSize];
                return;
            }
            fastStringBuffer = fastStringBuffer2;
        }
    }

    public void sendSAXComment(LexicalHandler lexicalHandler, int i, int i2) throws SAXException {
        lexicalHandler.comment(getString(i, i2).toCharArray(), 0, i2);
    }

    public void sendSAXcharacters(ContentHandler contentHandler, int i, int i2) throws SAXException {
        FastStringBuffer fastStringBuffer;
        FastStringBuffer fastStringBuffer2;
        int i3 = this.m_chunkBits;
        int i4 = i >>> i3;
        int i5 = this.m_chunkMask;
        int i6 = i & i5;
        if (i6 + i2 < i5 && this.m_innerFSB == null) {
            contentHandler.characters(this.m_array[i4], i6, i2);
            return;
        }
        int i7 = i + i2;
        int i8 = i7 >>> i3;
        int i9 = i7 & i5;
        while (i4 < i8) {
            if (i4 != 0 || (fastStringBuffer2 = this.m_innerFSB) == null) {
                contentHandler.characters(this.m_array[i4], i6, this.m_chunkSize - i6);
            } else {
                fastStringBuffer2.sendSAXcharacters(contentHandler, i6, this.m_chunkSize - i6);
            }
            i4++;
            i6 = 0;
        }
        if (i8 == 0 && (fastStringBuffer = this.m_innerFSB) != null) {
            fastStringBuffer.sendSAXcharacters(contentHandler, i6, i9 - i6);
        } else if (i9 > i6) {
            contentHandler.characters(this.m_array[i8], i6, i9 - i6);
        }
    }

    public final int size() {
        return (this.m_lastChunk << this.m_chunkBits) + this.m_firstFree;
    }

    public final String toString() {
        int i = (this.m_lastChunk << this.m_chunkBits) + this.m_firstFree;
        return getString(new StringBuffer(i), 0, 0, i).toString();
    }

    public final void setLength(int i) {
        FastStringBuffer fastStringBuffer;
        int i2 = i >>> this.m_chunkBits;
        this.m_lastChunk = i2;
        if (i2 == 0 && (fastStringBuffer = this.m_innerFSB) != null) {
            fastStringBuffer.setLength(i, this);
            return;
        }
        int i3 = i & this.m_chunkMask;
        this.m_firstFree = i3;
        if (i3 != 0 || i2 <= 0) {
            return;
        }
        this.m_lastChunk = i2 - 1;
        this.m_firstFree = this.m_chunkSize;
    }

    public StringBuffer getString(StringBuffer stringBuffer, int i, int i2) {
        return getString(stringBuffer, i >>> this.m_chunkBits, i & this.m_chunkMask, i2);
    }

    public String getString(int i, int i2) {
        int i3 = this.m_chunkMask;
        int i4 = i & i3;
        int i5 = i >>> this.m_chunkBits;
        if (i4 + i2 < i3 && this.m_innerFSB == null) {
            return getOneChunkString(i5, i4, i2);
        }
        return getString(new StringBuffer(i2), i5, i4, i2).toString();
    }

    public FastStringBuffer(int i, int i2) {
        this(i, i2, 2);
    }

    public FastStringBuffer(int i) {
        this(i, 15, 2);
    }

    public FastStringBuffer() {
        this(10, 15, 2);
    }

    public FastStringBuffer(int i, int i2, int i3) {
        this.m_lastChunk = 0;
        this.m_firstFree = 0;
        this.m_innerFSB = null;
        char[][] cArr = new char[16][];
        this.m_array = cArr;
        this.m_chunkBits = i;
        this.m_maxChunkBits = i;
        this.m_rebundleBits = i3;
        int i4 = 1 << i;
        this.m_chunkSize = i4;
        this.m_chunkMask = i4 - 1;
        cArr[0] = new char[i4];
    }

    public int sendNormalizedSAXcharacters(ContentHandler contentHandler, int i, int i2) throws SAXException {
        FastStringBuffer fastStringBuffer;
        int iSendNormalizedSAXcharacters;
        FastStringBuffer fastStringBuffer2;
        int i3 = i2 + i;
        int i4 = this.m_chunkBits;
        int i5 = i >>> i4;
        int i6 = this.m_chunkMask;
        int i7 = i & i6;
        int i8 = i3 >>> i4;
        int i9 = i3 & i6;
        int i10 = 1;
        while (i5 < i8) {
            if (i5 == 0 && (fastStringBuffer2 = this.m_innerFSB) != null) {
                iSendNormalizedSAXcharacters = fastStringBuffer2.sendNormalizedSAXcharacters(contentHandler, i7, this.m_chunkSize - i7);
            } else {
                iSendNormalizedSAXcharacters = sendNormalizedSAXcharacters(this.m_array[i5], i7, this.m_chunkSize - i7, contentHandler, i10);
            }
            i10 = iSendNormalizedSAXcharacters;
            i5++;
            i7 = 0;
        }
        if (i8 != 0 || (fastStringBuffer = this.m_innerFSB) == null) {
            return i9 > i7 ? sendNormalizedSAXcharacters(this.m_array[i8], i7, i9 - i7, contentHandler, i10 | 2) : i10;
        }
        return fastStringBuffer.sendNormalizedSAXcharacters(contentHandler, i7, i9 - i7);
    }

    public static void sendNormalizedSAXcharacters(char[] cArr, int i, int i2, ContentHandler contentHandler) throws SAXException {
        sendNormalizedSAXcharacters(cArr, i, i2, contentHandler, 3);
    }

    public final void append(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return;
        }
        char[] cArr = this.m_array[this.m_lastChunk];
        int i = this.m_chunkSize - this.m_firstFree;
        int i2 = 0;
        while (length > 0) {
            if (i > length) {
                i = length;
            }
            int i3 = i2 + i;
            str.getChars(i2, i3, this.m_array[this.m_lastChunk], this.m_firstFree);
            length -= i;
            if (length > 0) {
                char[][] cArr2 = this.m_array;
                int length2 = cArr2.length;
                if (this.m_lastChunk + 1 == length2) {
                    char[][] cArr3 = new char[length2 + 16][];
                    System.arraycopy(cArr2, 0, cArr3, 0, length2);
                    this.m_array = cArr3;
                }
                char[][] cArr4 = this.m_array;
                int i4 = this.m_lastChunk + 1;
                this.m_lastChunk = i4;
                if (cArr4[i4] == null) {
                    if (i4 == (1 << this.m_rebundleBits) && this.m_chunkBits < this.m_maxChunkBits) {
                        this.m_innerFSB = new FastStringBuffer(this);
                    }
                    this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
                }
                i = this.m_chunkSize;
                this.m_firstFree = 0;
            }
            i2 = i3;
        }
        this.m_firstFree += i;
    }

    public final void append(StringBuffer stringBuffer) {
        int length;
        if (stringBuffer == null || (length = stringBuffer.length()) == 0) {
            return;
        }
        char[] cArr = this.m_array[this.m_lastChunk];
        int i = this.m_chunkSize - this.m_firstFree;
        int i2 = 0;
        while (length > 0) {
            if (i > length) {
                i = length;
            }
            int i3 = i2 + i;
            stringBuffer.getChars(i2, i3, this.m_array[this.m_lastChunk], this.m_firstFree);
            length -= i;
            if (length > 0) {
                char[][] cArr2 = this.m_array;
                int length2 = cArr2.length;
                if (this.m_lastChunk + 1 == length2) {
                    char[][] cArr3 = new char[length2 + 16][];
                    System.arraycopy(cArr2, 0, cArr3, 0, length2);
                    this.m_array = cArr3;
                }
                char[][] cArr4 = this.m_array;
                int i4 = this.m_lastChunk + 1;
                this.m_lastChunk = i4;
                if (cArr4[i4] == null) {
                    if (i4 == (1 << this.m_rebundleBits) && this.m_chunkBits < this.m_maxChunkBits) {
                        this.m_innerFSB = new FastStringBuffer(this);
                    }
                    this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
                }
                i = this.m_chunkSize;
                this.m_firstFree = 0;
            }
            i2 = i3;
        }
        this.m_firstFree += i;
    }

    public final void append(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        char[] cArr2 = this.m_array[this.m_lastChunk];
        int i3 = this.m_chunkSize - this.m_firstFree;
        while (i2 > 0) {
            if (i3 > i2) {
                i3 = i2;
            }
            System.arraycopy(cArr, i, this.m_array[this.m_lastChunk], this.m_firstFree, i3);
            i2 -= i3;
            i += i3;
            if (i2 > 0) {
                char[][] cArr3 = this.m_array;
                int length = cArr3.length;
                if (this.m_lastChunk + 1 == length) {
                    char[][] cArr4 = new char[length + 16][];
                    System.arraycopy(cArr3, 0, cArr4, 0, length);
                    this.m_array = cArr4;
                }
                char[][] cArr5 = this.m_array;
                int i4 = this.m_lastChunk + 1;
                this.m_lastChunk = i4;
                if (cArr5[i4] == null) {
                    if (i4 == (1 << this.m_rebundleBits) && this.m_chunkBits < this.m_maxChunkBits) {
                        this.m_innerFSB = new FastStringBuffer(this);
                    }
                    this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
                }
                i3 = this.m_chunkSize;
                this.m_firstFree = 0;
            }
        }
        this.m_firstFree += i3;
    }

    public final void append(char c) {
        char[] cArr;
        int i = this.m_lastChunk;
        char[][] cArr2 = this.m_array;
        int length = cArr2.length;
        if (this.m_firstFree < this.m_chunkSize) {
            cArr = cArr2[i];
        } else {
            int length2 = cArr2.length;
            if (i + 1 == length2) {
                char[][] cArr3 = new char[length2 + 16][];
                System.arraycopy(cArr2, 0, cArr3, 0, length2);
                this.m_array = cArr3;
            }
            char[][] cArr4 = this.m_array;
            int i2 = this.m_lastChunk + 1;
            this.m_lastChunk = i2;
            cArr = cArr4[i2];
            if (cArr == null) {
                if (i2 == (1 << this.m_rebundleBits) && this.m_chunkBits < this.m_maxChunkBits) {
                    this.m_innerFSB = new FastStringBuffer(this);
                }
                char[][] cArr5 = this.m_array;
                int i3 = this.m_lastChunk;
                char[] cArr6 = new char[this.m_chunkSize];
                cArr5[i3] = cArr6;
                cArr = cArr6;
            }
            this.m_firstFree = 0;
        }
        int i4 = this.m_firstFree;
        this.m_firstFree = i4 + 1;
        cArr[i4] = c;
    }
}
