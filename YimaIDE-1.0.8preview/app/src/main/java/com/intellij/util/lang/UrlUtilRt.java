package com.intellij.util.lang;

import java.nio.charset.StandardCharsets;
import org.eclipse.jdt.internal.compiler.classfmt.ExternalAnnotationProvider;
import org.jetbrains.kotlin.backend.jvm.JvmSyntheticAccessorGenerator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class UrlUtilRt {
    /* JADX WARN: Code duplicated, block: B:18:0x0029  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
        if (i == 1) {
            objArr[0] = JvmSyntheticAccessorGenerator.SUPER_QUALIFIER_SUFFIX_MARKER;
        } else if (i == 2 || i == 3) {
            objArr[0] = "com/intellij/util/lang/UrlUtilRt";
        } else if (i != 4) {
            objArr[0] = "url";
        } else {
            objArr[0] = JvmSyntheticAccessorGenerator.SUPER_QUALIFIER_SUFFIX_MARKER;
        }
        if (i == 2 || i == 3) {
            objArr[1] = "unescapePercentSequences";
        } else {
            objArr[1] = "com/intellij/util/lang/UrlUtilRt";
        }
        if (i == 1) {
            objArr[2] = "unescapePercentSequences";
        } else if (i != 2 && i != 3) {
            if (i != 4) {
                objArr[2] = "internProtocol";
            } else {
                objArr[2] = "indexOf";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private static int decode(char c) {
        if (c >= '0' && c <= '9') {
            return c - ExternalAnnotationProvider.NULLABLE;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return c - '7';
    }

    private static int indexOf(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(4);
        }
        int iMin = Math.min(i2, charSequence.length());
        for (int iMax = Math.max(i, 0); iMax < iMin; iMax++) {
            if (charSequence.charAt(iMax) == '%') {
                return iMax;
            }
        }
        return -1;
    }

    public static CharSequence unescapePercentSequences(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(1);
        }
        int iIndexOf = indexOf(charSequence, i, i2);
        if (iIndexOf == -1) {
            CharSequence charSequenceSubSequence = charSequence.subSequence(i, i2);
            if (charSequenceSubSequence == null) {
                $$$reportNull$$$0(2);
            }
            return charSequenceSubSequence;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(charSequence, i, iIndexOf);
        byte[] bArr = null;
        int i3 = 0;
        while (iIndexOf < i2) {
            char cCharAt = charSequence.charAt(iIndexOf);
            if (cCharAt == '%') {
                if (bArr == null) {
                    bArr = new byte[i2 - i];
                } else {
                    i3 = 0;
                }
                while (true) {
                    int i4 = iIndexOf + 2;
                    if (i4 >= i2 || charSequence.charAt(iIndexOf) != '%') {
                        break;
                    }
                    int iDecode = decode(charSequence.charAt(iIndexOf + 1));
                    int iDecode2 = decode(charSequence.charAt(i4));
                    if (iDecode == -1 || iDecode2 == -1) {
                        break;
                    }
                    bArr[i3] = (byte) ((iDecode2 & 15) | ((iDecode & 15) << 4));
                    iIndexOf += 3;
                    i3++;
                }
                if (i3 != 0) {
                    sb.append(new String(bArr, 0, i3, StandardCharsets.UTF_8));
                }
            }
            sb.append(cCharAt);
            iIndexOf++;
        }
        return sb;
    }
}
