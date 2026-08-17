package io.github.rosemoe.sora.langs.textmate.utils;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class StringUtils {
    private static final Pattern MATCH_PATTERN = Pattern.compile(".*/|\\..*");

    public static boolean checkSurrogate(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isSurrogate(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static int convertUnicodeOffsetToUtf16(String str, int i, boolean z) {
        int i2;
        if (z) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < str.length()) {
                if (i4 == i) {
                    return i3;
                }
                if (Character.isHighSurrogate(str.charAt(i3)) && (i2 = i3 + 1) < str.length() && Character.isLowSurrogate(str.charAt(i2))) {
                    i3 = i2;
                }
                i4++;
                i3++;
            }
        }
        return i;
    }

    public static String getFileNameWithoutExtension(String str) {
        return MATCH_PATTERN.matcher(str).replaceAll("");
    }
}
