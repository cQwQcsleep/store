package com.reandroid.dex.header;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class HeaderPiece extends ByteArray implements DirectStreamReader {
    public HeaderPiece() {
    }

    private static boolean isPrintable(char c) {
        if (c <= 'z' && c >= 'a') {
            return true;
        }
        if (c > 'Z' || c < 'A') {
            return c <= '9' && c >= '0';
        }
        return true;
    }

    public static String printChars(byte[] bArr) {
        if (bArr == null) {
            return PsiKeyword.NULL;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            char c = toChar(b);
            if (isPrintable(c)) {
                sb.append(c);
            } else {
                sb.append(HexUtil.toHex2(b));
            }
        }
        return sb.toString();
    }

    public static String printHex(byte[] bArr) {
        if (bArr == null) {
            return PsiKeyword.NULL;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            if (i != 0) {
                sb.append(' ');
            }
            sb.append(HexUtil.toHex2(null, bArr[i]));
        }
        return sb.toString();
    }

    private static char toChar(byte b) {
        return (char) (b & 255);
    }

    @Override // com.reandroid.arsc.item.ByteArray
    public String toString() {
        return printChars(getBytesInternal());
    }

    public HeaderPiece(int i) {
        super(i);
    }
}
