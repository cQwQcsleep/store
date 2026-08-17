package com.android.tools.r8.naming;

import com.android.tools.r8.internal.C3046xg0;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.naming.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3326h0 {
    public static final /* synthetic */ boolean d = true;
    public final List a;
    public final HashSet b;
    public final C3046xg0.a c;

    public AbstractC3326h0(List list, boolean z) {
        if (!d && list == null) {
            x1f.a();
            throw null;
        }
        this.a = list;
        this.b = new HashSet(list);
        this.c = z ? C3046xg0.a.c : C3046xg0.a.b;
    }

    public final String a(char[] cArr, L l) {
        return cArr + b(cArr, l);
    }

    public String b(char[] cArr, L l) {
        String strA;
        do {
            if (l.b() < this.a.size()) {
                strA = (String) this.a.get(l.c());
            } else {
                do {
                    strA = C3046xg0.a(l.a(), this.c);
                } while (this.b.contains(strA));
            }
        } while (C3046xg0.a.contains(strA));
        return strA;
    }
}
