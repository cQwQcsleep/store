package com.fasterxml.aalto.in;

import com.fasterxml.aalto.util.XmlCharTypes;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class InputCharTypes extends XmlCharTypes {
    private static XmlCharTypes sAsciiCharTypes;
    private static XmlCharTypes sLatin1CharTypes;
    private static final XmlCharTypes sUtf8CharTypes;

    static {
        XmlCharTypes xmlCharTypes = new XmlCharTypes();
        sUtf8CharTypes = xmlCharTypes;
        fillInUtf8Chars(xmlCharTypes.TEXT_CHARS, xmlCharTypes.ATTR_CHARS, xmlCharTypes.NAME_CHARS, xmlCharTypes.DTD_CHARS, xmlCharTypes.OTHER_CHARS);
    }

    public static void fillInIllegalAsciiRange(int[] iArr) {
        for (int i = 128; i <= 255; i++) {
            iArr[i] = 1;
        }
    }

    private static void fillInMultiByteNameRange(int[] iArr) {
        int i;
        for (int i2 = 128; i2 < 256; i2++) {
            if ((i2 & 224) == 192) {
                i = 5;
            } else if ((i2 & 240) == 224) {
                i = 6;
            } else {
                i = (i2 & 248) == 240 ? 7 : 1;
            }
            iArr[i2] = i;
        }
    }

    private static void fillInMultiByteTextRange(int[] iArr) {
        int i;
        for (int i2 = 128; i2 < 256; i2++) {
            if ((i2 & 224) == 192) {
                i = 5;
            } else if ((i2 & 240) == 224) {
                i = 6;
            } else {
                i = (i2 & 248) == 240 ? 7 : 1;
            }
            iArr[i2] = i;
        }
    }

    public static void fillInUtf8Chars(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        XmlCharTypes.fillIn8BitTextRange(iArr);
        fillInMultiByteTextRange(iArr);
        XmlCharTypes.fillIn8BitAttrRange(iArr2);
        fillInMultiByteTextRange(iArr2);
        XmlCharTypes.fillIn8BitNameRange(iArr3);
        fillInMultiByteNameRange(iArr3);
        XmlCharTypes.fillIn8BitDtdRange(iArr4);
        fillInMultiByteTextRange(iArr4);
        iArr5[93] = 11;
        iArr5[62] = 17;
        XmlCharTypes.fillIn8BitTextRange(iArr5);
        fillInMultiByteTextRange(iArr5);
        iArr5[38] = 0;
        iArr5[60] = 0;
        iArr5[93] = 11;
        iArr5[63] = 12;
        iArr5[45] = 13;
    }

    public static final synchronized XmlCharTypes getAsciiCharTypes() {
        try {
            if (sAsciiCharTypes == null) {
                XmlCharTypes xmlCharTypes = new XmlCharTypes();
                sAsciiCharTypes = xmlCharTypes;
                XmlCharTypes.fillInLatin1Chars(xmlCharTypes.TEXT_CHARS, xmlCharTypes.ATTR_CHARS, xmlCharTypes.NAME_CHARS, xmlCharTypes.DTD_CHARS, xmlCharTypes.OTHER_CHARS);
                fillInIllegalAsciiRange(sAsciiCharTypes.TEXT_CHARS);
                fillInIllegalAsciiRange(sAsciiCharTypes.ATTR_CHARS);
                fillInIllegalAsciiRange(sAsciiCharTypes.NAME_CHARS);
                fillInIllegalAsciiRange(sAsciiCharTypes.DTD_CHARS);
                fillInIllegalAsciiRange(sAsciiCharTypes.OTHER_CHARS);
            }
        } catch (Throwable th) {
            throw th;
        }
        return sAsciiCharTypes;
    }

    public static final synchronized XmlCharTypes getLatin1CharTypes() {
        try {
            if (sLatin1CharTypes == null) {
                XmlCharTypes xmlCharTypes = new XmlCharTypes();
                sLatin1CharTypes = xmlCharTypes;
                XmlCharTypes.fillInLatin1Chars(xmlCharTypes.TEXT_CHARS, xmlCharTypes.ATTR_CHARS, xmlCharTypes.NAME_CHARS, xmlCharTypes.DTD_CHARS, xmlCharTypes.OTHER_CHARS);
            }
        } catch (Throwable th) {
            throw th;
        }
        return sLatin1CharTypes;
    }

    public static final XmlCharTypes getUtf8CharTypes() {
        return sUtf8CharTypes;
    }
}
