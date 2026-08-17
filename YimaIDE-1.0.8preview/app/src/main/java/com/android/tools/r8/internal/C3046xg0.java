package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3046xg0 {
    public static final HashSet a;
    public static final char[] b;
    public static final int c;
    public static final int d;
    public static final /* synthetic */ boolean e = true;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.android.tools.r8.internal.xg0$a */
    public static final class a {
        public static final a b = new a(0, "USE_MIXED_CASE");
        public static final a c = new a(1, "DONT_USE_MIXED_CASE");

        public a(int i, String str) {
            super(str, i);
        }
    }

    static {
        HashSet hashSet = new HashSet(AbstractC1739iN.a(12));
        Collections.addAll(hashSet, "boolean", "byte", "char", "double", "float", "int", "long", "short", "void", "it", "by", "do");
        a = hashSet;
        b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        c = 62;
        d = 36;
    }

    public static String a(int i, a aVar) {
        char[] cArr = Wf0.a;
        int i2 = aVar == a.b ? c : d;
        int i3 = i2 - 10;
        int i4 = i;
        int i5 = 1;
        for (int i6 = i3; i4 > i6; i6 = i2) {
            i4 = (i4 - 1) / i6;
            i5++;
        }
        char[] cArrCopyOfRange = Arrays.copyOfRange(cArr, 0, i5);
        int i7 = 10;
        int i8 = 0;
        while (i > i3) {
            int i9 = i - 1;
            cArrCopyOfRange[i8] = b[(i9 % i3) + i7];
            i = i9 / i3;
            i3 = i2;
            i7 = 0;
            i8++;
        }
        int i10 = i8 + 1;
        cArrCopyOfRange[i8] = b[(i - 1) + i7];
        boolean z = e;
        if (!z && i10 != cArrCopyOfRange.length) {
            x1f.a();
            return null;
        }
        if (z || !Character.isDigit(cArrCopyOfRange[0])) {
            return new String(cArrCopyOfRange);
        }
        x1f.a();
        return null;
    }
}
