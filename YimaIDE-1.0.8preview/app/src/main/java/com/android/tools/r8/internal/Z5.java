package com.android.tools.r8.internal;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z5 extends X5 implements InterfaceC1911kQ, InterfaceC1037a6 {
    public static final /* synthetic */ boolean f = true;
    public final Map e;

    public Z5(IdentityHashMap identityHashMap, IdentityHashMap identityHashMap2, IdentityHashMap identityHashMap3) {
        super(identityHashMap, identityHashMap2);
        this.e = identityHashMap3;
    }

    public static Z5 a() {
        return new Z5(new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap());
    }

    @Override // com.android.tools.r8.internal.X5, com.android.tools.r8.internal.InterfaceC1911kQ
    public final Set b(Object obj) {
        Set setB = super.b(obj);
        this.e.remove(obj);
        return setB;
    }

    @Override // com.android.tools.r8.internal.W5
    public final Object c(Object obj) {
        return this.b.get(obj);
    }

    @Override // com.android.tools.r8.internal.W5
    public final Object d(Object obj) {
        Set setA = a(obj);
        if (setA.isEmpty()) {
            return null;
        }
        if (setA.size() == 1) {
            return setA.iterator().next();
        }
        if (f || this.e.containsKey(obj)) {
            return this.e.get(obj);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.X5
    public final Object f(Object obj) {
        Object objF = super.f(obj);
        if (this.e.containsKey(objF) && (a(objF).size() <= 1 || d(objF) == obj)) {
            this.e.remove(objF);
        }
        return objF;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1037a6
    public final void a(final InterfaceC1853ji0 interfaceC1853ji0) {
        a(new BiConsumer() { // from class: p6g
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(interfaceC1853ji0, (Set) obj, obj2);
            }
        });
    }

    public final void a(InterfaceC1853ji0 interfaceC1853ji0, Set set, Object obj) {
        interfaceC1853ji0.a(set, obj, this.e.containsKey(obj) ? d(obj) : null);
    }

    public final void a(InterfaceC1037a6 interfaceC1037a6) {
        interfaceC1037a6.a(new InterfaceC1853ji0() { // from class: q6g
            @Override // com.android.tools.r8.internal.InterfaceC1853ji0
            public final void a(Object obj, Object obj2, Object obj3) {
                this.a.a((Set) obj, obj2, obj3);
            }
        });
    }

    public final void a(Set set, Object obj, Object obj2) {
        a((Iterable) set, obj);
        if (obj2 != null) {
            this.e.put(obj, obj2);
        }
    }
}
