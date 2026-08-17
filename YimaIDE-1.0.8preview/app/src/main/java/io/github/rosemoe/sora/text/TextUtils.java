package io.github.rosemoe.sora.text;

import io.github.rosemoe.sora.util.IntPair;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TextUtils {
    public static int countLeadingSpaceCount(CharSequence charSequence, int i) {
        long jCountLeadingSpacesAndTabs = countLeadingSpacesAndTabs(charSequence);
        return IntPair.getFirst(jCountLeadingSpacesAndTabs) + (i * IntPair.getSecond(jCountLeadingSpacesAndTabs));
    }

    public static long countLeadingSpacesAndTabs(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < charSequence.length(); i3++) {
            char cCharAt = charSequence.charAt(i3);
            if (!isWhitespace(cCharAt)) {
                break;
            }
            if (cCharAt == '\t') {
                i2++;
            } else {
                i++;
            }
        }
        return IntPair.pack(i, i2);
    }

    public static String createIndent(int i, int i2, boolean z) {
        int i3;
        int iMax = Math.max(0, i);
        if (z) {
            i3 = iMax / i2;
            iMax %= i2;
        } else {
            i3 = 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < i3; i4++) {
            sb.append('\t');
        }
        for (int i5 = 0; i5 < iMax; i5++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static long findLeadingAndTrailingWhitespacePos(ContentLine contentLine, int i, int i2) {
        char[] backingCharArray = contentLine.getBackingCharArray();
        while (i < i2 && isWhitespace(backingCharArray[i])) {
            i++;
        }
        if (i != i2) {
            while (i2 > 0 && isWhitespace(backingCharArray[i2 - 1])) {
                i2--;
            }
        }
        return IntPair.pack(i, i2);
    }

    public static int indexOf(CharSequence charSequence, CharSequence charSequence2, boolean z, int i) {
        int length = charSequence.length() - charSequence2.length();
        int length2 = charSequence2.length();
        while (i <= length) {
            for (int i2 = 0; i2 < length2; i2++) {
                char cCharAt = charSequence.charAt(i + i2);
                char cCharAt2 = charSequence2.charAt(i2);
                if (cCharAt != cCharAt2 && (!z || Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2))) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    private static boolean isWhitespace(char c) {
        return c == '\t' || c == ' ';
    }

    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, boolean z, int i) {
        int length = charSequence2.length();
        for (int iMin = Math.min(i, charSequence.length() - length); iMin >= 0; iMin--) {
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = charSequence.charAt(iMin + i2);
                char cCharAt2 = charSequence2.charAt(i2);
                if (cCharAt == cCharAt2 || (z && Character.toLowerCase(cCharAt) == Character.toLowerCase(cCharAt2))) {
                }
            }
            return iMin;
        }
        return -1;
    }

    public static String padStart(String str, char c, int i) {
        if (str.length() >= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i - str.length(); i2++) {
            sb.append(c);
        }
        sb.append(str);
        return sb.toString();
    }

    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence.length() < charSequence2.length()) {
            return false;
        }
        int length = charSequence2.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            char cCharAt2 = charSequence2.charAt(i);
            if (cCharAt != cCharAt2 && (!z || Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2))) {
                return false;
            }
        }
        return true;
    }

    public static long findLeadingAndTrailingWhitespacePos(ContentLine contentLine) {
        return findLeadingAndTrailingWhitespacePos(contentLine, 0, contentLine.length());
    }
}
