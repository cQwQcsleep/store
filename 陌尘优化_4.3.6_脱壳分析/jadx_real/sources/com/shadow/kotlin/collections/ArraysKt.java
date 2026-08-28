package com.shadow.kotlin.collections;

import com.shadow.kotlin.io.CloseableKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ArraysKt extends ArraysKt__ArraysJVMKt {
    public static List a(Object[] objArr) {
        CloseableKt.checkNotNullParameter(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        CloseableKt.checkNotNullExpressionValue(listAsList, "asList(...)");
        return listAsList;
    }

    public static void b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        CloseableKt.checkNotNullParameter(bArr, "<this>");
        CloseableKt.checkNotNullParameter(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
    }

    public static void c(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        CloseableKt.checkNotNullParameter(objArr, "<this>");
        CloseableKt.checkNotNullParameter(objArr2, "destination");
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static byte[] d(byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2);
        CloseableKt.checkNotNullExpressionValue(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    public static Object[] e(Object[] objArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(objArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i, i2);
        CloseableKt.checkNotNullExpressionValue(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    public static void f(Object[] objArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(objArr, "<this>");
        Arrays.fill(objArr, i, i2, (Object) null);
    }

    public static ArrayList g(Object[] objArr) {
        CloseableKt.checkNotNullParameter(objArr, "<this>");
        return new ArrayList(new ArrayAsCollection(objArr));
    }
}
