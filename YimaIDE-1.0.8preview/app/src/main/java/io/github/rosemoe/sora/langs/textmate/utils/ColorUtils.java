package io.github.rosemoe.sora.langs.textmate.utils;

import android.graphics.Color;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ColorUtils {
    public static int parseRGBAToARGB(String str) {
        if (str.charAt(0) != '#') {
            return Color.parseColor(str);
        }
        long j = Long.parseLong(str.substring(1), 16);
        if (str.length() == 7) {
            return (int) (4278190080L | j);
        }
        if (str.length() != 9) {
            w01.a("Unknown color");
            return 0;
        }
        return (((int) (j & 255)) << 24) | ((((int) (j >> 24)) & 255) << 16) | ((((int) (j >> 16)) & 255) << 8) | (((int) (j >> 8)) & 255);
    }
}
