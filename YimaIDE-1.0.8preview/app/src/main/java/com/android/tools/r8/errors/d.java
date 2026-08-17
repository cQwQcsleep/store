package com.android.tools.r8.errors;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import defpackage.flg;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class d {
    public final C0473Eu a = AbstractC0551Hu.g();

    public final d a(ArrayList arrayList) {
        arrayList.sort(Comparator.comparing(new flg()));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            D2 d2 = (D2) it.next();
            this.a.a("Enum " + d2.e1() + " was not unboxed.");
        }
        return this;
    }

    public final CheckEnumUnboxedDiagnostic a() {
        return new CheckEnumUnboxedDiagnostic(this.a.a());
    }
}
