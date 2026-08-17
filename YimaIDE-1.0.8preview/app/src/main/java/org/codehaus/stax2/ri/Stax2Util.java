package org.codehaus.stax2.ri;

import javax.xml.stream.XMLStreamConstants;
import org.codehaus.stax2.validation.ValidatorPair;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class Stax2Util implements XMLStreamConstants {

    public static final class ByteAggregator {
        static final int DEFAULT_BLOCK_ARRAY_SIZE = 100;
        private static final int INITIAL_BLOCK_SIZE = 500;
        private static final byte[] NO_BYTES = new byte[0];
        private int mBlockCount;
        private byte[][] mBlocks;
        private byte[] mSpareBlock;
        private int mTotalLen;

        public byte[] addFullBlock(byte[] bArr) {
            int length = bArr.length;
            byte[][] bArr2 = this.mBlocks;
            if (bArr2 == null) {
                this.mBlocks = new byte[100][];
            } else {
                int length2 = bArr2.length;
                if (this.mBlockCount >= length2) {
                    byte[][] bArr3 = new byte[length2 + length2][];
                    this.mBlocks = bArr3;
                    System.arraycopy(bArr2, 0, bArr3, 0, length2);
                }
            }
            byte[][] bArr4 = this.mBlocks;
            int i = this.mBlockCount;
            bArr4[i] = bArr;
            this.mBlockCount = i + 1;
            int i2 = this.mTotalLen + length;
            this.mTotalLen = i2;
            return new byte[Math.max(i2 >> 1, TerminalTokens.TokenNameWHITESPACE)];
        }

        public byte[] aggregateAll(byte[] bArr, int i) {
            int i2;
            int i3 = this.mTotalLen + i;
            if (i3 == 0) {
                return NO_BYTES;
            }
            byte[] bArr2 = new byte[i3];
            if (this.mBlocks != null) {
                i2 = 0;
                for (int i4 = 0; i4 < this.mBlockCount; i4++) {
                    byte[] bArr3 = this.mBlocks[i4];
                    int length = bArr3.length;
                    System.arraycopy(bArr3, 0, bArr2, i2, length);
                    i2 += length;
                }
            } else {
                i2 = 0;
            }
            System.arraycopy(bArr, 0, bArr2, i2, i);
            this.mSpareBlock = bArr;
            int i5 = i2 + i;
            if (i5 == i3) {
                return bArr2;
            }
            throw new RuntimeException("Internal error: total len assumed to be " + i3 + ", copied " + i5 + " bytes");
        }

        public byte[] startAggregation() {
            this.mTotalLen = 0;
            this.mBlockCount = 0;
            byte[] bArr = this.mSpareBlock;
            if (bArr == null) {
                return new byte[INITIAL_BLOCK_SIZE];
            }
            this.mSpareBlock = null;
            return bArr;
        }
    }

    public static final class TextBuffer {
        private String mText = null;
        private StringBuffer mBuilder = null;

        public void append(String str) {
            int length = str.length();
            if (length > 0) {
                String str2 = this.mText;
                if (str2 != null) {
                    StringBuffer stringBuffer = new StringBuffer(str2.length() + length);
                    this.mBuilder = stringBuffer;
                    stringBuffer.append(this.mText);
                    this.mText = null;
                }
                StringBuffer stringBuffer2 = this.mBuilder;
                if (stringBuffer2 != null) {
                    stringBuffer2.append(str);
                } else {
                    this.mText = str;
                }
            }
        }

        public String get() {
            String str = this.mText;
            if (str != null) {
                return str;
            }
            StringBuffer stringBuffer = this.mBuilder;
            return stringBuffer != null ? stringBuffer.toString() : "";
        }

        public boolean isEmpty() {
            return this.mText == null && this.mBuilder == null;
        }

        public void reset() {
            this.mText = null;
            this.mBuilder = null;
        }
    }

    private Stax2Util() {
    }

    private static final boolean _isSpace(char c) {
        return c <= ' ';
    }

    public static String eventTypeDesc(int i) {
        switch (i) {
            case 1:
                return "START_ELEMENT";
            case 2:
                return "END_ELEMENT";
            case 3:
                return "PROCESSING_INSTRUCTION";
            case 4:
                return "CHARACTERS";
            case 5:
                return "COMMENT";
            case 6:
                return "SPACE";
            case 7:
                return "START_DOCUMENT";
            case 8:
                return "END_DOCUMENT";
            case 9:
                return "ENTITY_REFERENCE";
            case 10:
            default:
                return "[" + i + "]";
            case 11:
                return "DTD";
            case 12:
                return ValidatorPair.ATTR_TYPE_DEFAULT;
        }
    }

    public static String trimSpaces(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (!_isSpace(str.charAt(i))) {
                int i2 = length - 1;
                if (!_isSpace(str.charAt(i2))) {
                    return i == 0 ? str : str.substring(i);
                }
                while (true) {
                    int i3 = i2 - 1;
                    if (i3 <= i || !_isSpace(str.charAt(i3))) {
                        break;
                    }
                    i2 = i3;
                }
                return str.substring(i, i2);
            }
            i++;
        }
        return null;
    }
}
