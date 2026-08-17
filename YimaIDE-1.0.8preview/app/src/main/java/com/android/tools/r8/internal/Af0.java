package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3214zf0;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Af0 {
    public static final /* synthetic */ boolean c = true;
    public final List a;
    public final int b;

    public Af0(ArrayList arrayList) {
        this.a = arrayList;
        int iCount = (int) arrayList.stream().filter(new Predicate() { // from class: yv
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C3214zf0) obj).b();
            }
        }).count();
        this.b = iCount;
        if (c || iCount >= 1 || arrayList.size() <= 1) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static Af0 a(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        String strConcat = XmlPullParser.NO_NAMESPACE;
        while (true) {
            int iIndexOf = str.indexOf(37, i);
            if (iIndexOf == -1) {
                if (i < length) {
                    strConcat = strConcat.concat(str.substring(i));
                }
                if (!strConcat.isEmpty() || arrayList.isEmpty()) {
                    arrayList.add(new C3214zf0(strConcat));
                }
                return new Af0(arrayList);
            }
            int i3 = iIndexOf + 1;
            if (i3 == length) {
                return null;
            }
            String strConcat2 = strConcat.concat(str.substring(i, iIndexOf));
            char cCharAt = str.charAt(i3);
            if (cCharAt != '%') {
                if (cCharAt != 'b') {
                    if (cCharAt != 'd') {
                        if (cCharAt != 's') {
                            return null;
                        }
                    } else if (!z) {
                        return null;
                    }
                }
                if (!strConcat2.isEmpty()) {
                    arrayList.add(new C3214zf0(strConcat2));
                    strConcat2 = XmlPullParser.NO_NAMESPACE;
                }
                arrayList.add(new C3214zf0(cCharAt, i2));
                i2++;
            } else {
                strConcat2 = strConcat2.concat("%");
            }
            strConcat = strConcat2;
            i = iIndexOf + 2;
        }
    }
}
