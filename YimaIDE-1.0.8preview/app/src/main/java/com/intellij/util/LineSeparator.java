package com.intellij.util;

import com.intellij.openapi.util.SystemInfo;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public enum LineSeparator {
    LF("\n"),
    CRLF("\r\n"),
    CR("\r");

    private final byte[] myBytes;
    private final String mySeparatorString;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 4 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 4 || i == 5) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "string";
        } else if (i == 2 || i == 3 || i == 4 || i == 5) {
            objArr[0] = "com/intellij/util/LineSeparator";
        } else {
            objArr[0] = "separatorString";
        }
        if (i == 2) {
            objArr[1] = "fromString";
        } else if (i == 3) {
            objArr[1] = "getSeparatorString";
        } else if (i == 4) {
            objArr[1] = "getSeparatorBytes";
        } else if (i != 5) {
            objArr[1] = "com/intellij/util/LineSeparator";
        } else {
            objArr[1] = "getSystemLineSeparator";
        }
        if (i == 1) {
            objArr[2] = "fromString";
        } else if (i != 2 && i != 3 && i != 4 && i != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 4 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    LineSeparator(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.mySeparatorString = str;
        this.myBytes = str.getBytes(StandardCharsets.UTF_8);
    }

    public static LineSeparator getSystemLineSeparator() {
        LineSeparator lineSeparator = SystemInfo.isWindows ? CRLF : LF;
        if (lineSeparator == null) {
            $$$reportNull$$$0(5);
        }
        return lineSeparator;
    }

    public String getSeparatorString() {
        String str = this.mySeparatorString;
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        return str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return super.toString();
    }
}
