package io.github.rosemoe.sora.text;

import android.icu.lang.UCharacter;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TextUtilsP {
    private static final int CARRIAGE_RETURN = 13;
    private static final int LINE_FEED = 10;

    /* JADX WARN: Code duplicated, block: B:17:0x003a A[PHI: r4
      0x003a: PHI (r4v12 int) = 
      (r4v2 int)
      (r4v1 int)
      (r4v3 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v1 int)
      (r4v13 int)
      (r4v15 int)
     binds: [B:90:0x012d, B:69:0x00f7, B:70:0x00f9, B:67:0x00ef, B:61:0x00d8, B:58:0x00cd, B:51:0x00b3, B:46:0x00a2, B:48:0x00a8, B:39:0x008c, B:36:0x0080, B:27:0x005a, B:24:0x004e, B:20:0x0043, B:16:0x0039, B:18:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:29:0x0065 A[PHI: r4 r5
      0x0065: PHI (r4v7 int) = (r4v2 int), (r4v4 int), (r4v6 int), (r4v8 int) binds: [B:87:0x0127, B:44:0x009c, B:33:0x0078, B:28:0x005c] A[DONT_GENERATE, DONT_INLINE]
      0x0065: PHI (r5v5 int) = (r5v1 int), (r5v1 int), (r5v1 int), (r5v8 int) binds: [B:87:0x0127, B:44:0x009c, B:33:0x0078, B:28:0x005c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:82:0x011b A[PHI: r4
      0x011b: PHI (r4v5 int) = (r4v2 int), (r4v6 int) binds: [B:81:0x0119, B:33:0x0078] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:93:0x0133 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:97:0x0135 A[SYNTHETIC] */
    public static int getOffsetForBackspaceKey(CharSequence charSequence, int i) {
        int iCharCount;
        int iCharCount2;
        int iCharCount3;
        if (i <= 1) {
            return 0;
        }
        int iCharCount4 = i;
        int i2 = 0;
        int iCharCount5 = 0;
        int iCharCount6 = 0;
        do {
            int iCodePointBefore = Character.codePointBefore(charSequence, iCharCount4);
            iCharCount4 -= Character.charCount(iCodePointBefore);
            switch (i2) {
                case 0:
                    iCharCount5 = Character.charCount(iCodePointBefore);
                    if (iCodePointBefore == 10) {
                        i2 = 1;
                    } else if (isVariationSelector(iCodePointBefore)) {
                        i2 = 6;
                    } else if (AndroidEmoji.isRegionalIndicatorSymbol(iCodePointBefore)) {
                        i2 = 10;
                    } else if (AndroidEmoji.isEmojiModifier(iCodePointBefore)) {
                        i2 = 4;
                    } else if (iCodePointBefore == AndroidEmoji.COMBINING_ENCLOSING_KEYCAP) {
                        i2 = 2;
                    } else if (AndroidEmoji.isEmoji(iCodePointBefore)) {
                        i2 = 7;
                    } else if (iCodePointBefore == AndroidEmoji.CANCEL_TAG) {
                        i2 = 12;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 1:
                    if (iCodePointBefore == 13) {
                        iCharCount5++;
                    }
                    i2 = 13;
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 2:
                    if (isVariationSelector(iCodePointBefore)) {
                        iCharCount6 = Character.charCount(iCodePointBefore);
                        i2 = 3;
                    } else {
                        if (AndroidEmoji.isKeycapBase(iCodePointBefore)) {
                            iCharCount = Character.charCount(iCodePointBefore);
                            iCharCount5 += iCharCount;
                        }
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 3:
                    if (AndroidEmoji.isKeycapBase(iCodePointBefore)) {
                        iCharCount2 = Character.charCount(iCodePointBefore);
                        iCharCount = iCharCount2 + iCharCount6;
                        iCharCount5 += iCharCount;
                    }
                    i2 = 13;
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 4:
                    if (isVariationSelector(iCodePointBefore)) {
                        iCharCount6 = Character.charCount(iCodePointBefore);
                        i2 = 5;
                    } else if (AndroidEmoji.isEmojiModifierBase(iCodePointBefore)) {
                        iCharCount3 = Character.charCount(iCodePointBefore);
                        iCharCount5 += iCharCount3;
                        i2 = 7;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 5:
                    if (AndroidEmoji.isEmojiModifierBase(iCodePointBefore)) {
                        iCharCount2 = Character.charCount(iCodePointBefore);
                        iCharCount = iCharCount2 + iCharCount6;
                        iCharCount5 += iCharCount;
                    }
                    i2 = 13;
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 6:
                    if (AndroidEmoji.isEmoji(iCodePointBefore)) {
                        iCharCount3 = Character.charCount(iCodePointBefore);
                        iCharCount5 += iCharCount3;
                        i2 = 7;
                        if (iCharCount4 > 0) {
                        }
                        return i - iCharCount5;
                    }
                    if (!isVariationSelector(iCodePointBefore) && UCharacter.getCombiningClass(iCodePointBefore) == 0) {
                        iCharCount = Character.charCount(iCodePointBefore);
                        iCharCount5 += iCharCount;
                    }
                    i2 = 13;
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 7:
                    if (iCodePointBefore == AndroidEmoji.ZERO_WIDTH_JOINER) {
                        i2 = 8;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 8:
                    if (AndroidEmoji.isEmoji(iCodePointBefore)) {
                        iCharCount5 += Character.charCount(iCodePointBefore) + 1;
                        if (AndroidEmoji.isEmojiModifier(iCodePointBefore)) {
                            i2 = 4;
                        } else {
                            i2 = 7;
                        }
                    } else if (isVariationSelector(iCodePointBefore)) {
                        iCharCount6 = Character.charCount(iCodePointBefore);
                        i2 = 9;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 9:
                    if (AndroidEmoji.isEmoji(iCodePointBefore)) {
                        iCharCount5 += iCharCount6 + 1 + Character.charCount(iCodePointBefore);
                        iCharCount6 = 0;
                        i2 = 7;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 10:
                    if (AndroidEmoji.isRegionalIndicatorSymbol(iCodePointBefore)) {
                        iCharCount5 += 2;
                        i2 = 11;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case 11:
                    if (AndroidEmoji.isRegionalIndicatorSymbol(iCodePointBefore)) {
                        iCharCount5 -= 2;
                        i2 = 10;
                    } else {
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                case EditorColorScheme.SCROLL_BAR_THUMB_PRESSED /* 12 */:
                    if (AndroidEmoji.isTagSpecChar(iCodePointBefore)) {
                        iCharCount5 += 2;
                    } else {
                        if (AndroidEmoji.isEmoji(iCodePointBefore)) {
                            iCharCount = Character.charCount(iCodePointBefore);
                            iCharCount5 += iCharCount;
                        } else {
                            iCharCount5 = 2;
                        }
                        i2 = 13;
                    }
                    if (iCharCount4 > 0) {
                    }
                    return i - iCharCount5;
                default:
                    ty8.a("state ", i2, " is unknown");
                    return 0;
            }
        } while (i2 != 13);
        return i - iCharCount5;
    }

    private static boolean isVariationSelector(int i) {
        return UCharacter.hasBinaryProperty(i, 36);
    }
}
