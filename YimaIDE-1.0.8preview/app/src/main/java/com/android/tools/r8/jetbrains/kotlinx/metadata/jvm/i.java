package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class i {
    public static final h a(Integer num, int[] iArr, String[] strArr, String[] strArr2, String str, String str2, Integer num2) {
        int[] iArr2 = {1, 0, 3};
        if (strArr == null) {
            strArr = new String[0];
        }
        if (strArr2 == null) {
            strArr2 = new String[0];
        }
        int iIntValue = num2 != null ? num2.intValue() : 0;
        String str3 = str == null ? XmlPullParser.NO_NAMESPACE : str;
        int iIntValue2 = num != null ? num.intValue() : 1;
        if (iArr == null) {
            iArr = new int[0];
        }
        return new h(iArr2, strArr, strArr2, iIntValue, str3, iIntValue2, iArr, str2 == null ? XmlPullParser.NO_NAMESPACE : str2);
    }

    public static /* synthetic */ h a(Integer num, int[] iArr, String[] strArr, String[] strArr2, String str, Integer num2, int i) {
        if ((i & 4) != 0) {
            strArr = null;
        }
        if ((i & 8) != 0) {
            strArr2 = null;
        }
        if ((i & 16) != 0) {
            str = null;
        }
        return a(num, iArr, strArr, strArr2, str, (String) null, (i & 64) != 0 ? null : num2);
    }
}
