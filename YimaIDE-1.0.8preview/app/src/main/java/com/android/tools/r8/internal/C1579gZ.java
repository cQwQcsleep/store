package com.android.tools.r8.internal;

import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1579gZ {
    public final List a;

    public C1579gZ(List list) {
        KB.c(list, "mappingLines");
        this.a = list;
    }

    public final void a(H10 h10) {
        VS vs = new VS();
        for (String str : this.a) {
            if ((AbstractC1679hg0.a(str, " ") || AbstractC1679hg0.a(str, "\t")) && AbstractC1679hg0.a((CharSequence) str, "->")) {
                List listA = AbstractC1679hg0.a(str, new String[]{"->"}, 2, 2);
                String string = AbstractC1679hg0.a((String) listA.get(0)).toString();
                String strA = AbstractC1679hg0.a(string, '(', string);
                int iA = AbstractC1679hg0.a((CharSequence) strA, ' ', false, 6);
                if (iA != -1) {
                    strA = strA.substring(iA + 1, strA.length());
                    KB.b(strA, "substring(...)");
                }
                String string2 = AbstractC1679hg0.a((String) listA.get(1)).toString();
                C1491fW c1491fW = vs.c;
                if (c1491fW != null) {
                    LinkedHashMap linkedHashMap = vs.b;
                    C0843Tb c0843Tb = new C0843Tb((String) c1491fW.c, string2);
                    C1491fW c1491fW2 = vs.c;
                    KB.a(c1491fW2);
                    linkedHashMap.put(c0843Tb, new C0843Tb((String) c1491fW2.b, strA));
                }
            } else if (AbstractC1679hg0.a((CharSequence) str, "->")) {
                List listA2 = AbstractC1679hg0.a(str, new String[]{"->"}, 2, 2);
                String string3 = AbstractC1679hg0.a((String) listA2.get(0)).toString();
                String str2 = (String) listA2.get(1);
                char[] cArr = {' ', '\t', ':'};
                KB.c(str2, "<this>");
                int length = str2.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    char cCharAt = str2.charAt(!z ? i : length);
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 3) {
                            i2 = -1;
                            break;
                        } else if (cCharAt == cArr[i2]) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    boolean z2 = i2 >= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                String string4 = str2.subSequence(i, length + 1).toString();
                vs.c = new C1491fW(string3, string4);
                vs.a.put(string4, string3);
            }
        }
        h10.c = new WS(vs);
    }
}
