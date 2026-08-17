package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0442Dp {
    public Rc0 a;
    public boolean b;
    public boolean c;

    public C0442Dp() {
        int i = Rc0.h;
        this.a = new Ec0(16);
        this.b = true;
    }

    public static void b(InterfaceC0468Ep interfaceC0468Ep, Object obj) {
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        Nm0[] nm0Arr = C1856jk.o;
        if (C0520Gp.b(nm0Arr[c1856jk.h.ordinal()], obj)) {
            return;
        }
        if (nm0Arr[c1856jk.h.ordinal()].b == Pm0.k && (obj instanceof SN)) {
            return;
        }
        drd.a("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new Object[]{Integer.valueOf(c1856jk.c.g), nm0Arr[c1856jk.h.ordinal()].b, obj.getClass().getName()});
    }

    public final void a(Map.Entry entry) {
        InterfaceC0468Ep interfaceC0468Ep = (InterfaceC0468Ep) entry.getKey();
        Object value = entry.getValue();
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        if (c1856jk.m()) {
            Object objA = a(c1856jk, this.a.get(c1856jk));
            if (objA == null) {
                objA = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objA).add(C0520Gp.a(it.next()));
            }
            this.a.a(c1856jk, objA);
            return;
        }
        Pm0 pm0H = c1856jk.h();
        Pm0 pm0 = Pm0.k;
        Rc0 rc0 = this.a;
        if (pm0H != pm0) {
            rc0.a(c1856jk, C0520Gp.a(value));
            return;
        }
        Object objA2 = a(c1856jk, rc0.get(c1856jk));
        if (objA2 == null) {
            this.a.a(c1856jk, C0520Gp.a(value));
        } else if (objA2 instanceof SN) {
            ((H0) ((SN) objA2)).b((J0) ((TN) value));
        } else {
            this.a.a(c1856jk, ((H0) ((TN) objA2).d()).b((J0) ((TN) value)).build());
        }
    }

    public static Object a(InterfaceC0468Ep interfaceC0468Ep, Object obj) {
        if (obj == null) {
            return obj;
        }
        C1856jk c1856jk = (C1856jk) interfaceC0468Ep;
        if (c1856jk.h() == Pm0.k) {
            if (c1856jk.m()) {
                if (obj instanceof List) {
                    List arrayList = (List) obj;
                    for (int i = 0; i < arrayList.size(); i++) {
                        Object obj2 = arrayList.get(i);
                        Object objBuild = obj2 instanceof SN ? ((SN) obj2).build() : obj2;
                        if (objBuild != obj2) {
                            if (arrayList == obj) {
                                arrayList = new ArrayList(arrayList);
                            }
                            arrayList.set(i, objBuild);
                        }
                    }
                    return arrayList;
                }
                sle.a("Repeated field should contains a List but actually contains type: ", obj.getClass());
                return null;
            }
            if (obj instanceof SN) {
                return ((SN) obj).build();
            }
        }
        return obj;
    }
}
