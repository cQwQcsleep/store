package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import defpackage.x0g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3038xc {
    public static final /* synthetic */ boolean c = true;
    public final Map a;
    public final AtomicReference b;

    public AbstractC3038xc(ConcurrentHashMap concurrentHashMap, AbstractC1501fd abstractC1501fd) {
        AtomicReference atomicReference = new AtomicReference();
        this.b = atomicReference;
        if (!c && abstractC1501fd != null && abstractC1501fd.a != c()) {
            x1f.a();
            throw null;
        }
        this.a = concurrentHashMap == null ? new ConcurrentHashMap() : concurrentHashMap;
        atomicReference.set(abstractC1501fd);
    }

    public abstract com.android.tools.r8.graph.E0 a(com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.E0 e1);

    public abstract Supplier a(com.android.tools.r8.graph.E0 e0);

    public final void a(Predicate predicate) {
        com.android.tools.r8.graph.E0 e0;
        if (this.b.get() == null) {
            return;
        }
        AbstractC1501fd abstractC1501fd = (AbstractC1501fd) this.b.get();
        Set<com.android.tools.r8.graph.I2> setC = AbstractC2780ub0.c();
        setC.addAll(this.a.keySet());
        setC.addAll(abstractC1501fd.a());
        for (com.android.tools.r8.graph.I2 i2 : setC) {
            if (predicate.test(i2)) {
                a(i2);
            }
        }
        synchronized (this) {
            try {
                if (this.b.get() == null) {
                    return;
                }
                Iterator it = this.a.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (!setC.contains(entry.getKey()) || (e0 = (com.android.tools.r8.graph.E0) ((Supplier) entry.getValue()).get()) == null) {
                        it.remove();
                    } else {
                        if (!c && e0.e != entry.getKey()) {
                            throw new AssertionError();
                        }
                        entry.setValue(a(e0));
                    }
                }
                this.b.set(null);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final AbstractC0706Nu b() {
        if (this.b.get() != null) {
            x0g.a("Getting all classes from not fully loaded collection.");
            return null;
        }
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        for (Map.Entry entry : this.a.entrySet()) {
            c0629KuE.a((com.android.tools.r8.graph.I2) entry.getKey(), (com.android.tools.r8.graph.E0) ((Supplier) entry.getValue()).get());
        }
        return c0629KuE.b();
    }

    public abstract com.android.tools.r8.graph.V c();

    public String toString() {
        return this.a.size() + " loaded, provider: " + Objects.toString(this.b.get());
    }

    public final com.android.tools.r8.graph.E0 a(final com.android.tools.r8.graph.I2 i2) {
        Supplier supplier;
        Object obj = this.b.get();
        Map map = this.a;
        if (obj == null) {
            Supplier supplier2 = (Supplier) map.get(i2);
            if (supplier2 == null) {
                return null;
            }
            return (com.android.tools.r8.graph.E0) supplier2.get();
        }
        Supplier supplier3 = (Supplier) map.get(i2);
        if (supplier3 != null) {
            return (com.android.tools.r8.graph.E0) supplier3.get();
        }
        synchronized (this) {
            supplier = (Supplier) this.a.computeIfAbsent(i2, new Function() { // from class: esi
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    return this.b.a(i2, (I2) obj2);
                }
            });
        }
        if (supplier == null) {
            return null;
        }
        return (com.android.tools.r8.graph.E0) supplier.get();
    }

    public final /* synthetic */ Supplier a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        if (this.b.get() == null) {
            return null;
        }
        return new C2952wc(this, (AbstractC1501fd) this.b.get(), i2);
    }

    public final ArrayList a() {
        if (this.b.get() == null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.a.values().iterator();
            while (it.hasNext()) {
                com.android.tools.r8.graph.E0 e0 = (com.android.tools.r8.graph.E0) ((Supplier) it.next()).get();
                if (!c && e0 == null) {
                    x1f.a();
                    return null;
                }
                arrayList.add(e0);
            }
            return arrayList;
        }
        x0g.a("Getting all classes from not fully loaded collection.");
        return null;
    }
}
