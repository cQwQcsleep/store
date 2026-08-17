package com.android.tools.r8.naming;

import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.kotlin.InterfaceC3298p;
import com.android.tools.r8.naming.M;
import com.android.tools.r8.naming.N;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N {
    public static final /* synthetic */ boolean b = true;
    public final C0333y a;

    public N(C0333y c0333y) {
        this.a = c0333y;
    }

    public final List a() {
        I2 i2A;
        String str;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        for (D2 d2 : this.a.f().e()) {
            InterfaceC3298p interfaceC3298p = d2.x;
            if (interfaceC3298p.e()) {
                ((M) map.computeIfAbsent(interfaceC3298p.o().a.a, new Function() { // from class: u9a
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.b.a((String) obj);
                    }
                })).a(d2);
            } else if (interfaceC3298p.t()) {
                final com.android.tools.r8.kotlin.h0 h0VarQ = interfaceC3298p.q();
                map2.computeIfAbsent(h0VarQ.a, new Function() { // from class: v9a
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return N.a(h0VarQ, (String) obj);
                    }
                });
                ((M) map.computeIfAbsent(h0VarQ.b.a, new Function() { // from class: w9a
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.b.b((String) obj);
                    }
                })).a(d2);
            }
        }
        for (D2 d3 : this.a.f().e()) {
            if (d3.x.r() && (i2A = this.a.A().a(d3.getType())) != null && (str = (String) map2.get(i2A.Y0())) != null) {
                M m = (M) map.get(str);
                if (!b && m == null) {
                    x1f.a();
                    return null;
                }
                m.a(d3);
            }
        }
        if (map.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        final ArrayList arrayList = new ArrayList();
        map.values().forEach(new Consumer() { // from class: x9a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                N.a(arrayList, (M) obj);
            }
        });
        return arrayList;
    }

    public final /* synthetic */ M b(String str) {
        return new M(str, this.a);
    }

    public static boolean a(DataEntryResource dataEntryResource) {
        return dataEntryResource.getName().endsWith(".kotlin_module");
    }

    public final /* synthetic */ M a(String str) {
        return new M(str, this.a);
    }

    public static String a(com.android.tools.r8.kotlin.h0 h0Var, String str) {
        return h0Var.b.a;
    }

    public static /* synthetic */ void a(final List list, M m) {
        Optional optionalA = m.a();
        Objects.requireNonNull(list);
        optionalA.ifPresent(new Consumer() { // from class: q9a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((DataEntryResource) obj);
            }
        });
    }
}
