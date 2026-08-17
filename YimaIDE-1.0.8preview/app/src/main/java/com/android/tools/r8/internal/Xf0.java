package com.android.tools.r8.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class Xf0 {
    public static String a(String str, Object... objArr) {
        int iIndexOf;
        String string;
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj = objArr[i2];
            if (obj == null) {
                string = "null";
            } else {
                try {
                    string = obj.toString();
                } catch (Exception e) {
                    String str2 = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for ".concat(str2), (Throwable) e);
                    string = "<" + str2 + " threw " + e.getClass().getName() + ">";
                }
            }
            objArr[i2] = string;
        }
        StringBuilder sb = new StringBuilder((objArr.length * 16) + str.length());
        int i3 = 0;
        while (i < objArr.length && (iIndexOf = str.indexOf("%s", i3)) != -1) {
            sb.append((CharSequence) str, i3, iIndexOf);
            sb.append(objArr[i]);
            i3 = iIndexOf + 2;
            i++;
        }
        sb.append((CharSequence) str, i3, str.length());
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static String a(int i) {
        if (i <= 1) {
            if (i >= 0) {
                return i == 0 ? "" : "[]";
            }
            w01.a(a("invalid count: %s", Integer.valueOf(i)));
            return null;
        }
        long j = 2 * ((long) i);
        int i2 = (int) j;
        if (i2 == j) {
            char[] cArr = new char[i2];
            int i3 = 2;
            "[]".getChars(0, 2, cArr, 0);
            while (true) {
                int i4 = i2 - i3;
                if (i3 < i4) {
                    System.arraycopy(cArr, 0, cArr, i3, i3);
                    i3 <<= 1;
                } else {
                    System.arraycopy(cArr, 0, cArr, i3, i4);
                    return new String(cArr);
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + j);
        }
    }
}
