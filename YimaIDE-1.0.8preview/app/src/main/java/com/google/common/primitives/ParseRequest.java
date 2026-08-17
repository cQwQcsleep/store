package com.google.common.primitives;

import com.intellij.psi.util.PsiLiteralUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class ParseRequest {
    final int radix;
    final String rawValue;

    private ParseRequest(String str, int i) {
        this.rawValue = str;
        this.radix = i;
    }

    public static ParseRequest fromString(String str) {
        if (str.isEmpty()) {
            throw new NumberFormatException("empty string");
        }
        char cCharAt = str.charAt(0);
        int i = 16;
        if (str.startsWith(PsiLiteralUtil.HEX_PREFIX) || str.startsWith("0X")) {
            str = str.substring(2);
        } else if (cCharAt == '#') {
            str = str.substring(1);
        } else if (cCharAt != '0' || str.length() <= 1) {
            i = 10;
        } else {
            str = str.substring(1);
            i = 8;
        }
        return new ParseRequest(str, i);
    }
}
