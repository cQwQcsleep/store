package org.jcodings;

import java.nio.charset.Charset;
import org.jcodings.ascii.AsciiTables;
import org.jcodings.exception.EncodingError;
import org.jcodings.exception.EncodingException;
import org.jcodings.exception.InternalException;
import org.jcodings.util.BytesHash;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public abstract class Encoding implements Cloneable {
    public static final int CHAR_INVALID = -1;
    public static final byte NEW_LINE = 10;
    private static int count;
    private int hashCode;
    private int index;
    private boolean isAsciiCompatible;
    private final boolean isFixedWidth;
    private final boolean isSingleByte;
    protected final int maxLength;
    protected final int minLength;
    private byte[] name;
    private String stringName;
    protected boolean isUnicode = false;
    protected boolean isUTF8 = false;
    private Charset charset = null;
    private boolean isDummy = false;

    public Encoding(String str, int i, int i2) {
        setName(str);
        this.minLength = i;
        this.maxLength = i2;
        boolean z = i == i2;
        this.isFixedWidth = z;
        this.isSingleByte = z && i == 1;
        int i3 = count;
        count = i3 + 1;
        this.index = i3;
        this.isAsciiCompatible = i == 1;
    }

    public static byte asciiToLower(int i) {
        return AsciiTables.ToLowerCaseTable[i];
    }

    public static byte asciiToUpper(int i) {
        return AsciiTables.ToUpperCaseTable[i];
    }

    public static int digitVal(int i) {
        return i - 48;
    }

    public static boolean isAscii(int i) {
        return i < 128;
    }

    public static boolean isMbcAscii(byte b) {
        return (b & 255) < 128;
    }

    public static boolean isWordGraphPrint(int i) {
        return i == 12 || i == 5 || i == 7;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jcodings.exception.InternalException */
    public static Encoding load(String str, String str2) throws InternalException {
        String str3 = str2 + "." + str + "Encoding";
        try {
            Class<?> cls = Class.forName(str3);
            try {
                return (Encoding) cls.getField("INSTANCE").get(cls);
            } catch (Exception unused) {
                throw new InternalException("problem loading encoding <%n>", str3);
            }
        } catch (ClassNotFoundException unused2) {
            throw new InternalException("encoding class <%n> not found", str3);
        }
    }

    public static int odigitVal(int i) {
        return digitVal(i);
    }

    public abstract void applyAllCaseFold(int i, ApplyAllCaseFoldFunction applyAllCaseFoldFunction, Object obj);

    public abstract CaseFoldCodeItem[] caseFoldCodesByString(int i, byte[] bArr, int i2, int i3);

    public abstract int caseMap(IntHolder intHolder, byte[] bArr, IntHolder intHolder2, int i, byte[] bArr2, int i2, int i3);

    public abstract int codeToMbc(int i, byte[] bArr, int i2);

    public abstract int codeToMbcLength(int i);

    public abstract int[] ctypeCodeRange(int i, IntHolder intHolder);

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public Charset getCharset() {
        if (this.charset == null) {
            this.charset = Charset.forName(getCharsetName());
        }
        return this.charset;
    }

    public String getCharsetName() {
        return this.stringName;
    }

    public final int getIndex() {
        return this.index;
    }

    public final byte[] getName() {
        return this.name;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final boolean isAlnum(int i) {
        return isCodeCType(i, 13);
    }

    public final boolean isAlpha(int i) {
        return isCodeCType(i, 1);
    }

    public final boolean isAsciiCompatible() {
        return this.isAsciiCompatible;
    }

    public final boolean isBlank(int i) {
        return isCodeCType(i, 2);
    }

    public final boolean isCntrl(int i) {
        return isCodeCType(i, 3);
    }

    public abstract boolean isCodeCType(int i, int i2);

    public final boolean isDigit(int i) {
        return isCodeCType(i, 4);
    }

    public final boolean isDummy() {
        return this.isDummy;
    }

    public final boolean isFixedWidth() {
        return this.isFixedWidth;
    }

    public final boolean isGraph(int i) {
        return isCodeCType(i, 5);
    }

    public final boolean isLower(int i) {
        return isCodeCType(i, 6);
    }

    public boolean isMbcCrnl(byte[] bArr, int i, int i2) {
        return mbcToCode(bArr, i, i2) == 13 && isNewLine(bArr, i + length(bArr, i, i2), i2);
    }

    public final boolean isMbcHead(byte[] bArr, int i, int i2) {
        return length(bArr, i, i2) != 1;
    }

    public final boolean isMbcWord(byte[] bArr, int i, int i2) {
        return isWord(mbcToCode(bArr, i, i2));
    }

    public final boolean isNewLine(int i) {
        return isCodeCType(i, 0);
    }

    public abstract boolean isNewLine(byte[] bArr, int i, int i2);

    public final boolean isPrint(int i) {
        return isCodeCType(i, 7);
    }

    public final boolean isPunct(int i) {
        return isCodeCType(i, 8);
    }

    public abstract boolean isReverseMatchAllowed(byte[] bArr, int i, int i2);

    public final boolean isSbWord(int i) {
        return isAscii(i) && isWord(i);
    }

    public final boolean isSingleByte() {
        return this.isSingleByte;
    }

    public final boolean isSpace(int i) {
        return isCodeCType(i, 9);
    }

    public final boolean isUTF8() {
        return this.isUTF8;
    }

    public final boolean isUnicode() {
        return this.isUnicode;
    }

    public final boolean isUpper(int i) {
        return isCodeCType(i, 10);
    }

    public final boolean isWord(int i) {
        return isCodeCType(i, 12);
    }

    public final boolean isXDigit(int i) {
        return isCodeCType(i, 11);
    }

    public abstract int leftAdjustCharHead(byte[] bArr, int i, int i2, int i3);

    public abstract int length(byte b);

    public abstract int length(byte[] bArr, int i, int i2);

    public final int maxLength() {
        return this.maxLength;
    }

    @Deprecated
    public final int maxLengthDistance() {
        return maxLength();
    }

    public abstract int mbcCaseFold(int i, byte[] bArr, IntHolder intHolder, int i2, byte[] bArr2);

    public abstract int mbcToCode(byte[] bArr, int i, int i2);

    @Deprecated
    public final int mbcodeStartPosition() {
        return minLength() > 1 ? 0 : 128;
    }

    public final int minLength() {
        return this.minLength;
    }

    public final int prevCharHead(byte[] bArr, int i, int i2, int i3) {
        if (i2 <= i) {
            return -1;
        }
        return leftAdjustCharHead(bArr, i, i2 - 1, i3);
    }

    public abstract int propertyNameToCType(byte[] bArr, int i, int i2);

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jcodings.exception.EncodingException */
    public Encoding replicate(byte[] bArr) throws EncodingException {
        try {
            Encoding encoding = (Encoding) clone();
            encoding.setName(bArr);
            int i = count;
            count = i + 1;
            encoding.index = i;
            return encoding;
        } catch (CloneNotSupportedException unused) {
            throw new EncodingException(EncodingError.ERR_COULD_NOT_REPLICATE, new String(bArr));
        }
    }

    public final int rightAdjustCharHead(byte[] bArr, int i, int i2, int i3) {
        int iLeftAdjustCharHead = leftAdjustCharHead(bArr, i, i2, i3);
        return iLeftAdjustCharHead < i2 ? iLeftAdjustCharHead + length(bArr, iLeftAdjustCharHead, i3) : iLeftAdjustCharHead;
    }

    public final int rightAdjustCharHeadWithPrev(byte[] bArr, int i, int i2, int i3, IntHolder intHolder) {
        int iLeftAdjustCharHead = leftAdjustCharHead(bArr, i, i2, i3);
        if (iLeftAdjustCharHead < i2) {
            if (intHolder != null) {
                intHolder.value = iLeftAdjustCharHead;
            }
            return iLeftAdjustCharHead + length(bArr, iLeftAdjustCharHead, i3);
        }
        if (intHolder != null) {
            intHolder.value = -1;
        }
        return iLeftAdjustCharHead;
    }

    public final void setDummy() {
        this.isDummy = true;
        this.isAsciiCompatible = false;
    }

    public final void setName(byte[] bArr) {
        this.name = bArr;
        this.hashCode = BytesHash.hashCode(bArr, 0, bArr.length);
        this.stringName = new String(bArr);
    }

    public final int step(byte[] bArr, int i, int i2, int i3) {
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                break;
            }
            i += length(bArr, i, i2);
            i3 = i4;
        }
        if (i <= i2) {
            return i;
        }
        return -1;
    }

    public final int stepBack(byte[] bArr, int i, int i2, int i3, int i4) {
        while (i2 != -1) {
            int i5 = i4 - 1;
            if (i4 <= 0) {
                break;
            }
            if (i2 <= i) {
                return -1;
            }
            i2 = leftAdjustCharHead(bArr, i, i2 - 1, i3);
            i4 = i5;
        }
        return i2;
    }

    public final int strByteLengthNull(byte[] bArr, int i, int i2) {
        int length = 0;
        loop0: while (true) {
            if (bArr[length] == 0) {
                int iMinLength = minLength();
                if (iMinLength == 1) {
                    break;
                }
                int i3 = length + 1;
                while (iMinLength > 1) {
                    if (i3 >= bArr.length) {
                        break loop0;
                    }
                    if (bArr[i3] != 0) {
                        break;
                    }
                    i3++;
                    iMinLength--;
                }
                if (iMinLength == 1) {
                    break;
                }
            }
            length += length(bArr, length, i2);
        }
        return length;
    }

    public abstract int strCodeAt(byte[] bArr, int i, int i2, int i3);

    public abstract int strLength(byte[] bArr, int i, int i2);

    public final int strLengthNull(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (true) {
            if (bArr[i] == 0) {
                int iMinLength = minLength();
                if (iMinLength == 1) {
                    break;
                }
                int i4 = i + 1;
                while (iMinLength > 1 && bArr[i4] == 0) {
                    i4++;
                    iMinLength--;
                }
                if (iMinLength == 1) {
                    break;
                }
            }
            i += length(bArr, i, i2);
            i3++;
        }
        return i3;
    }

    public final int strNCmp(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        while (true) {
            int i5 = i4 - 1;
            if (i4 <= 0) {
                return 0;
            }
            if (i >= i2) {
                return bArr2[i3];
            }
            int iMbcToCode = bArr2[i3] - mbcToCode(bArr, i, i2);
            if (iMbcToCode != 0) {
                return iMbcToCode;
            }
            i3++;
            i += length(bArr, i, i2);
            i4 = i5;
        }
    }

    public byte[] toLowerCaseTable() {
        return null;
    }

    public final String toString() {
        return this.stringName;
    }

    public final int xdigitVal(int i) {
        if (isDigit(i)) {
            return digitVal(i);
        }
        return isUpper(i) ? i - 55 : i - 87;
    }

    public static boolean isAscii(byte b) {
        return b >= 0;
    }

    public final void setName(String str) {
        byte[] bytes = str.getBytes();
        this.name = bytes;
        this.hashCode = BytesHash.hashCode(bytes, 0, bytes.length);
        this.stringName = str;
    }

    public static Encoding load(String str) {
        return load(str, "org.jcodings.specific");
    }
}
