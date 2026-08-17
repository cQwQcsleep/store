package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T3 extends U3 {
    public static Integer a(int[] iArr, int i) {
        KB.c(iArr, "<this>");
        if (i < 0 || i > iArr.length - 1) {
            return null;
        }
        return Integer.valueOf(iArr[i]);
    }

    public static List a(Object[] objArr) {
        KB.c(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        KB.b(listAsList, "asList(...)");
        return listAsList;
    }
}
