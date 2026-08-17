package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2254oQ {
    public final CB a = new CB();

    public final AbstractC0729Or a() {
        if (this.a.a()) {
            return null;
        }
        R00 r00 = R00.f;
        Q00 q00 = new Q00();
        CB cb = this.a;
        Set setKeySet = cb.c.keySet();
        KB.b(setKeySet, "<get-keys>(...)");
        Iterator it = AbstractC1760ie.a(setKeySet, new BB(cb.c)).iterator();
        while (it.hasNext()) {
            L00 l00 = (L00) ((Lg0) it.next()).a;
            KB.c(l00, "element");
            if ((q00.c & 1) != 1) {
                q00.d = new ArrayList(q00.d);
                q00.c |= 1;
            }
            List list = q00.d;
            O00 o00E = l00.e();
            if (!o00E.a()) {
                defpackage.bk.a();
                return null;
            }
            list.add(o00E);
        }
        return (AbstractC0729Or) q00.c();
    }
}
