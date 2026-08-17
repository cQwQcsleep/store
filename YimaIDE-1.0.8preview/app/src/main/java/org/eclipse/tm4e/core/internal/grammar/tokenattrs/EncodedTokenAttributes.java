package org.eclipse.tm4e.core.internal.grammar.tokenattrs;

import io.github.rosemoe.sora.langs.textmate.folding.IndentRange;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class EncodedTokenAttributes {
    private EncodedTokenAttributes() {
    }

    public static boolean containsBalancedBrackets(int i) {
        return (i & NewHope.POLY_SIZE) != 0;
    }

    public static int getBackground(int i) {
        return (i & IndentRange.MASK_INDENT) >>> 24;
    }

    public static int getFontStyle(int i) {
        return (i & 30720) >>> 11;
    }

    public static int getForeground(int i) {
        return (i & 16744448) >>> 15;
    }

    public static int getLanguageId(int i) {
        return i & 255;
    }

    public static int getTokenType(int i) {
        return (i & 768) >>> 8;
    }

    public static int set(int i, int i2, int i3, Boolean bool, int i4, int i5, int i6) {
        if (i2 == 0) {
            i2 = getLanguageId(i);
        }
        if (i3 == 8) {
            i3 = getTokenType(i);
        }
        int i7 = (bool != null ? !bool.booleanValue() : !containsBalancedBrackets(i)) ? 0 : 1;
        if (i4 == -1) {
            i4 = getFontStyle(i);
        }
        if (i5 == 0) {
            i5 = getForeground(i);
        }
        if (i6 == 0) {
            i6 = getBackground(i);
        }
        return (i3 << 8) | i2 | (i7 << 10) | (i4 << 11) | (i5 << 15) | (i6 << 24);
    }

    public static String toBinaryStr(int i) {
        return new StringBuilder(Integer.toBinaryString(i)).insert(0, "0".repeat(Integer.numberOfLeadingZeros(i))).toString();
    }

    public static String toString(int i) {
        return "{\n  languageId: " + getLanguageId(i) + ",\n  tokenType: " + getTokenType(i) + ",\n  fontStyle: " + getFontStyle(i) + ",\n  foreground: " + getForeground(i) + ",\n  background: " + getBackground(i) + "\n,  containsBalancedBrackets: " + containsBalancedBrackets(i) + "\n}";
    }
}
