package com.android.tools.r8.internal;

import com.android.tools.r8.internal.LM;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.naming.mappinginformation.e;
import com.android.tools.r8.references.MethodReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LM {
    public static final /* synthetic */ boolean d = true;
    public final MethodReference a;
    public List b = null;
    public final ArrayList c = new ArrayList();

    public LM(MethodReference methodReference) {
        this.a = methodReference;
    }

    public final void a() {
        if (this.b == null || this.c.isEmpty()) {
            if (d || this.b != null) {
                return;
            }
            x01.a("Mapped outline positions is null");
            return;
        }
        for (C1405eW c1405eW : this.c) {
            C3331k.b bVar = (C3331k.b) c1405eW.a();
            InterfaceC2386px interfaceC2386px = (InterfaceC2386px) c1405eW.b();
            final C2214nx c2214nx = new C2214nx(16);
            interfaceC2386px.forEach(new BiConsumer() { // from class: fm8
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(c2214nx, (Integer) obj, (Integer) obj2);
                }
            });
            bVar.a(new Consumer() { // from class: gm8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LM.a((e) obj);
                }
            }, new EV(c2214nx, this.a));
        }
    }

    public static void a(Object obj) {
        throw new Kk0();
    }

    public final void a(C2214nx c2214nx, Integer num, Integer num2) {
        int i;
        int iIntValue = num.intValue();
        Iterator it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            JM jm = (JM) it.next();
            if (jm.b.f() == iIntValue) {
                i = jm.a;
                break;
            }
        }
        if (i != -1) {
            c2214nx.b(i, num2.intValue());
        }
    }
}
