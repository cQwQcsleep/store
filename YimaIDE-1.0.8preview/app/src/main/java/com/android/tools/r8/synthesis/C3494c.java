package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.C0702Nq;
import com.android.tools.r8.internal.C1870jv;
import com.android.tools.r8.internal.C2753uC;
import com.android.tools.r8.internal.XR;
import com.android.tools.r8.synthesis.C3494c;
import defpackage.hih;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3494c {
    public static final /* synthetic */ boolean f = true;
    public final S a;
    public final AbstractC0706Nu b;
    public final AbstractC0706Nu c;
    public final AbstractC0706Nu d;
    public final AbstractC2554rv e;

    public C3494c(S s, AbstractC0706Nu abstractC0706Nu, AbstractC0706Nu abstractC0706Nu2, AbstractC0706Nu abstractC0706Nu3, AbstractC2554rv abstractC2554rv) {
        this.a = s;
        this.b = abstractC0706Nu;
        this.c = abstractC0706Nu2;
        this.d = abstractC0706Nu3;
        this.e = abstractC2554rv;
        if (f) {
            return;
        }
        a();
    }

    public static AbstractC0706Nu b(final Map map, AbstractC0706Nu abstractC0706Nu) {
        if (map == null) {
            return abstractC0706Nu;
        }
        abstractC0706Nu.forEach(new BiConsumer() { // from class: uig
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Set) map.computeIfAbsent((I2) obj, new Function() { // from class: vig
                    @Override // java.util.function.Function
                    public final Object apply(Object obj3) {
                        return C3494c.c((I2) obj3);
                    }
                })).addAll((Set) obj2);
            }
        });
        return AbstractC0706Nu.a(map);
    }

    public static /* synthetic */ Set c(I2 i2) {
        return new HashSet();
    }

    public static /* synthetic */ List d(I2 i2) {
        return new ArrayList();
    }

    public final AbstractC0706Nu a(Map map, XR xr, C1870jv c1870jv) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (InterfaceC3501j interfaceC3501j : C2753uC.b(map.values(), Function.identity())) {
            InterfaceC3501j interfaceC3501jA = interfaceC3501j.a(xr);
            if (interfaceC3501jA != null) {
                ((List) identityHashMap.computeIfAbsent(interfaceC3501jA.a(), new Function() { // from class: sig
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return C3494c.d((I2) obj);
                    }
                })).add(interfaceC3501jA);
                if (this.e.contains(interfaceC3501j.a())) {
                    c1870jv.a(interfaceC3501jA.a());
                }
            }
        }
        return AbstractC0706Nu.a(identityHashMap);
    }

    public static /* synthetic */ List b(I2 i2) {
        return new ArrayList();
    }

    public final void b(Consumer consumer) {
        this.e.forEach(consumer);
    }

    public final void a() {
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) this.b.keySet());
        c1870jv.a((Iterable) this.c.keySet());
        final AbstractC2554rv abstractC2554rvA = c1870jv.a();
        this.e.forEach(new Consumer() { // from class: lig
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C3494c.a(abstractC2554rvA, (I2) obj);
            }
        });
    }

    public static /* synthetic */ void a(Set set, I2 i2) {
        if (f || set.contains(i2)) {
            return;
        }
        hih.a("Expected ", i2.m0(), " to be a synthetic");
    }

    public final C0702Nq a(I2 i2) {
        AbstractC0706Nu abstractC0706Nu = this.c;
        Object obj = Collections.EMPTY_LIST;
        Object obj2 = abstractC0706Nu.get(i2);
        if (obj2 == null) {
            obj2 = obj;
        }
        Iterable iterable = (Iterable) obj2;
        Object obj3 = this.b.get(i2);
        if (obj3 != null) {
            obj = obj3;
        }
        return AbstractC0728Oq.a(iterable, (Iterable) obj);
    }

    public final void a(final Consumer consumer) {
        this.b.values().forEach(new Consumer() { // from class: oig
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).forEach(consumer);
            }
        });
        this.c.values().forEach(new Consumer() { // from class: qig
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).forEach(consumer);
            }
        });
    }

    public static AbstractC0706Nu a(final Map map, AbstractC0706Nu abstractC0706Nu) {
        if (map == null) {
            return abstractC0706Nu;
        }
        abstractC0706Nu.forEach(new BiConsumer() { // from class: wig
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((List) map.computeIfAbsent((I2) obj, new Function() { // from class: gig
                    @Override // java.util.function.Function
                    public final Object apply(Object obj3) {
                        return C3494c.b((I2) obj3);
                    }
                })).addAll((List) obj2);
            }
        });
        return AbstractC0706Nu.a(map);
    }

    public static void a(AbstractC0327x0 abstractC0327x0, Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            I2 i2 = (I2) it.next();
            if (!f && abstractC0327x0.c(i2) == null) {
                s22.a("Missing synthetic: ", i2);
                return;
            }
        }
    }
}
