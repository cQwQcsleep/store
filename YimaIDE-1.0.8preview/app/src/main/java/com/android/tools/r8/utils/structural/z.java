package com.android.tools.r8.utils.structural;

import com.android.tools.r8.internal.AbstractC2956we;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.internal.QK;
import defpackage.rr9;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class z implements u {
    public final /* synthetic */ v a;
    public final /* synthetic */ w b;

    public z(QK qk, QK qk2) {
        this.a = qk;
        this.b = qk2;
    }

    @Override // com.android.tools.r8.utils.structural.w
    public final void a(InterfaceC2045lz interfaceC2045lz, o oVar) {
        ArrayList arrayList = new ArrayList(interfaceC2045lz.keySet());
        arrayList.sort(new rr9());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            ((q) oVar).a.a(iIntValue);
            this.b.a(interfaceC2045lz.get(iIntValue), oVar);
        }
    }

    @Override // com.android.tools.r8.utils.structural.v
    public final int a(InterfaceC2045lz interfaceC2045lz, InterfaceC2045lz interfaceC2045lz2, final AbstractC3519a abstractC3519a) {
        final v vVar = this.a;
        return AbstractC2956we.a(interfaceC2045lz, interfaceC2045lz2, new Comparator() { // from class: kxi
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return vVar.a(obj, obj2, abstractC3519a);
            }
        });
    }
}
