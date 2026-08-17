package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.HY;
import com.android.tools.r8.threading.ThreadingModule;
import defpackage.s26;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HY {
    public static final /* synthetic */ boolean b = true;
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public static void a(com.android.tools.r8.graph.I2 i2, Map map) {
        com.android.tools.r8.graph.D2 d2A = ((com.android.tools.r8.graph.B5) map.values().iterator().next()).a();
        final ArrayList<C0231j1> arrayList = new ArrayList();
        map.values().forEach(new Consumer() { // from class: d46
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add(((B5) obj).e());
            }
        });
        arrayList.sort(Comparator.comparing(new s26()));
        com.android.tools.r8.graph.H4 h4V = d2A.V();
        h4V.getClass();
        if (!com.android.tools.r8.graph.H4.d) {
            for (C0231j1 c0231j1 : arrayList) {
                if (!com.android.tools.r8.graph.H4.d) {
                    h4V.c(c0231j1);
                }
            }
        }
        h4V.c = C0231j1.v;
        h4V.b.a(arrayList);
    }

    public static /* synthetic */ com.android.tools.r8.graph.B5 b(Supplier supplier, C0322w2 c0322w2) {
        com.android.tools.r8.graph.B5 b5 = (com.android.tools.r8.graph.B5) supplier.get();
        if (b || b5.a().f(b5.getReference()) == null) {
            return b5;
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ Map a(com.android.tools.r8.graph.I2 i2) {
        return new ConcurrentHashMap();
    }

    public final void a(ThreadingModule threadingModule, ExecutorService executorService) {
        C1086ah0.a(this.a, new InterfaceC1766ih0() { // from class: e46
            @Override // com.android.tools.r8.internal.InterfaceC1766ih0
            public final void accept(Object obj, Object obj2) {
                HY.a((I2) obj, (Map) obj2);
            }
        }, threadingModule, executorService);
    }

    public final com.android.tools.r8.graph.B5 a(final Supplier supplier, C0322w2 c0322w2) {
        return (com.android.tools.r8.graph.B5) ((Map) this.a.computeIfAbsent(c0322w2.w0(), new Function() { // from class: f46
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return HY.a((I2) obj);
            }
        })).computeIfAbsent(c0322w2, new Function() { // from class: g46
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return HY.b(supplier, (C0322w2) obj);
            }
        });
    }
}
