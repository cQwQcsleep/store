package com.reandroid.utils.collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayUtil {
    public static void reverse(Object[] objArr, int i, int i2) {
        int i3 = i2 + i;
        int i4 = i3 / 2;
        int i5 = i3 - 1;
        while (i < i4) {
            Object obj = objArr[i];
            int i6 = i5 - i;
            objArr[i] = objArr[i6];
            objArr[i6] = obj;
            i++;
        }
    }

    public static void reverse(Object[] objArr) {
        reverse(objArr, 0, objArr.length);
    }
}
