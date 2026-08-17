package com.reandroid.archive;

import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveUtil {
    public static String sanitizePath(String str) {
        if (StringsUtil.isEmpty(str)) {
            return null;
        }
        char[] charArray = str.replace('\\', '/').toCharArray();
        int length = charArray.length;
        StringBuilder sb = new StringBuilder(length);
        int i = length - 1;
        StringBuilder sb2 = null;
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            char c = charArray[i2];
            if (z) {
                sb.append(c);
            } else if (c == '.') {
                if (sb2 == null) {
                    sb2 = new StringBuilder(5);
                }
                sb2.append(c);
            } else if (c == '/') {
                if (i2 == i) {
                    sb.append(c);
                }
                sb2 = null;
            } else {
                sb = new StringBuilder(length);
                if (sb2 != null) {
                    sb.append(sb2.toString());
                    sb2 = null;
                }
                sb.append(c);
                z = true;
            }
        }
        if (sb2 == null) {
            return sb.toString();
        }
        String string = sb2.toString();
        if (string.length() > 2) {
            return string;
        }
        return null;
    }
}
