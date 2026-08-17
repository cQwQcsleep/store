package com.intellij.util.text;

import com.intellij.openapi.util.text.CharFilter;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.text.LiteralFormatUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class LiteralFormatUtil {
    private static final CharFilter UNDERSCORES_FILTER = new CharFilter() { // from class: qc9
        public final boolean accept(char c) {
            return LiteralFormatUtil.a(c);
        }
    };

    /* JADX WARN: Code duplicated, block: B:24:0x0038  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3 || i == 4 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 3 || i == 4 || i == 5) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "com/intellij/util/text/LiteralFormatUtil";
        } else if (i == 2) {
            objArr[0] = "original";
        } else if (i == 3 || i == 4 || i == 5) {
            objArr[0] = "com/intellij/util/text/LiteralFormatUtil";
        } else {
            objArr[0] = "text";
        }
        if (i == 1) {
            objArr[1] = "removeUnderscores";
        } else if (i == 3 || i == 4 || i == 5) {
            objArr[1] = "format";
        } else {
            objArr[1] = "com/intellij/util/text/LiteralFormatUtil";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "format";
            } else if (i != 3 && i != 4 && i != 5) {
                objArr[2] = "removeUnderscores";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3 && i != 4 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ boolean a(char c) {
        return c != '_';
    }

    public static String removeUnderscores(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        String strStrip = StringUtil.strip(str, UNDERSCORES_FILTER);
        if (strStrip == null) {
            $$$reportNull$$$0(1);
        }
        return strStrip;
    }
}
