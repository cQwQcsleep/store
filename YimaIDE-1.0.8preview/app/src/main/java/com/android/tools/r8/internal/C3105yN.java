package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.T4;
import com.android.tools.r8.internal.C3105yN;
import com.android.tools.r8.internal.C3190zN;
import com.android.tools.r8.internal.UY;
import java.util.AbstractMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3105yN {
    public final Map a;

    public C3105yN(AbstractMap abstractMap) {
        this.a = abstractMap;
    }

    public static C3190zN b(com.android.tools.r8.graph.T4.c cVar) {
        SY sy = UY.d;
        return new C3190zN(cVar, new RY());
    }

    public final void a(C0322w2 c0322w2, final com.android.tools.r8.graph.T4.c cVar, UY uy) {
        ((C3190zN) this.a.computeIfAbsent(c0322w2, IM.a(new Supplier() { // from class: uui
            @Override // java.util.function.Supplier
            public final Object get() {
                return C3105yN.b(cVar);
            }
        }))).b.b.putAll(uy.b);
    }

    public static C3105yN b() {
        return new C3105yN(new ConcurrentHashMap());
    }

    public final void a(C0322w2 c0322w2, final com.android.tools.r8.graph.T4.c cVar, com.android.tools.r8.graph.B5 b5) {
        ((C3190zN) this.a.computeIfAbsent(c0322w2, IM.a(new Supplier() { // from class: vui
            @Override // java.util.function.Supplier
            public final Object get() {
                return C3105yN.a(cVar);
            }
        }))).b.add(b5);
    }

    public final void a(final InterfaceC1853ji0 interfaceC1853ji0) {
        this.a.forEach(new BiConsumer() { // from class: tui
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3190zN c3190zN = (C3190zN) obj2;
                interfaceC1853ji0.a((C0322w2) obj, c3190zN.a, c3190zN.b);
            }
        });
    }

    public final void a(C3105yN c3105yN) {
        c3105yN.a(new InterfaceC1853ji0() { // from class: sui
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                this.a.a((C0322w2) obj, (T4.c) obj2, (UY) obj3);
            }
        });
    }

    public static C3105yN a() {
        return new C3105yN(new IdentityHashMap());
    }

    public static C3190zN a(com.android.tools.r8.graph.T4.c cVar) {
        return new C3190zN(cVar, UY.c());
    }
}
