package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2689tY;
import com.android.tools.r8.internal.InterfaceC1792j1;
import com.android.tools.r8.internal.InterfaceC1877k1;
import com.android.tools.r8.internal.InterfaceC1963l1;
import com.android.tools.r8.internal.InterfaceC2049m1;
import com.android.tools.r8.internal.InterfaceC2134n1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2689tY {
    public static final /* synthetic */ boolean f = true;
    public InterfaceC1708i1 a;
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final Set d = C1755ib0.a();
    public final C2518rY e = new C2518rY();

    public AbstractC2689tY(InterfaceC1708i1 interfaceC1708i1) {
        this.a = interfaceC1708i1;
    }

    public abstract InterfaceC1963l1 a(C0322w2 c0322w2);

    public abstract AbstractC2689tY a();

    public final void a(com.android.tools.r8.graph.B5 b5, C0322w2 c0322w2) {
        boolean z = f;
        if (!z && !this.a.a(c0322w2) && !this.c.containsKey(c0322w2)) {
            x1f.a();
        } else if (z || this.c.containsKey(b5.getReference())) {
            this.d.add(c0322w2);
        } else {
            x1f.a();
        }
    }

    public final InterfaceC1708i1 b() {
        if (!e()) {
            if (f || this.d.isEmpty()) {
                return this.a;
            }
            x1f.a();
            return null;
        }
        if (!f && !this.e.a()) {
            x1f.a();
            return null;
        }
        this.e.a(this.c);
        final InterfaceC1622h1 interfaceC1622h1C = c();
        this.a.a(new InterfaceC1936kh0() { // from class: gdi
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                interfaceC1622h1C.a((InterfaceC1877k1) obj);
            }
        }, new InterfaceC1936kh0() { // from class: hdi
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                this.a.a(interfaceC1622h1C, (InterfaceC2049m1) obj);
            }
        });
        final ArrayList arrayList = new ArrayList(this.c.size() + this.b.size());
        this.b.values().forEach(new Consumer() { // from class: idi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add(((InterfaceC1792j1) obj).build());
            }
        });
        this.c.values().forEach(new Consumer() { // from class: jdi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(arrayList, (InterfaceC1963l1) obj);
            }
        });
        arrayList.sort(d());
        arrayList.forEach(new Consumer() { // from class: kdi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC1622h1C.a((InterfaceC2134n1) obj);
            }
        });
        return interfaceC1622h1C.build();
    }

    public abstract InterfaceC1792j1 b(com.android.tools.r8.graph.I2 i2);

    public abstract InterfaceC1622h1 c();

    public abstract Comparator d();

    public final boolean e() {
        return (this.b.isEmpty() && this.c.isEmpty()) ? false : true;
    }

    public abstract AbstractC2689tY f();

    public final void a(com.android.tools.r8.graph.I2 i2, Consumer consumer) {
        if (this.a.a(i2) || this.b.containsKey(i2)) {
            consumer.accept(new C2176nY(this));
        }
    }

    public final void a(com.android.tools.r8.graph.I2 i2) {
        if (this.a.a(i2)) {
            return;
        }
        this.b.computeIfAbsent(i2, new Function() { // from class: fdi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((I2) obj);
            }
        });
    }

    public final AbstractC2689tY a(C0322w2 c0322w2, Consumer consumer) {
        InterfaceC1963l1 interfaceC1963l1 = (InterfaceC1963l1) this.c.computeIfAbsent(c0322w2, new Function() { // from class: ddi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C0322w2) obj);
            }
        });
        synchronized (interfaceC1963l1) {
            consumer.accept(interfaceC1963l1);
        }
        return f();
    }

    public final /* synthetic */ void a(InterfaceC1622h1 interfaceC1622h1, InterfaceC2049m1 interfaceC2049m1) {
        if (this.d.contains(interfaceC2049m1.c())) {
            return;
        }
        InterfaceC1963l1 interfaceC1963l1 = (InterfaceC1963l1) this.c.remove(interfaceC2049m1.c());
        if (interfaceC1963l1 != null) {
            interfaceC1622h1.a(interfaceC1963l1.a(interfaceC2049m1).c());
        } else {
            interfaceC1622h1.a(interfaceC2049m1);
        }
    }

    public final /* synthetic */ void a(List list, InterfaceC1963l1 interfaceC1963l1) {
        InterfaceC2049m1 interfaceC2049m1C = interfaceC1963l1.c();
        if (this.d.contains(interfaceC2049m1C.c())) {
            return;
        }
        list.add(interfaceC2049m1C);
    }

    public final AbstractC2689tY a(final Function function) {
        final AbstractC2689tY abstractC2689tYA = a();
        if (!f && !this.d.isEmpty()) {
            x1f.a();
            return null;
        }
        abstractC2689tYA.b.putAll(this.b);
        this.c.forEach(new BiConsumer() { // from class: edi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AbstractC2689tY.a(function, abstractC2689tYA, (C0322w2) obj, (InterfaceC1963l1) obj2);
            }
        });
        return abstractC2689tYA;
    }

    public static /* synthetic */ void a(Function function, AbstractC2689tY abstractC2689tY, C0322w2 c0322w2, InterfaceC1963l1 interfaceC1963l1) {
        C0322w2 c0322w3 = (C0322w2) function.apply(c0322w2);
        InterfaceC1963l1 interfaceC1963l2 = (InterfaceC1963l1) abstractC2689tY.c.put(c0322w3, interfaceC1963l1.a(c0322w3));
        if (f || interfaceC1963l2 == null) {
            return;
        }
        x1f.a();
    }

    public final void b(C0322w2 c0322w2, Consumer consumer) {
        InterfaceC2049m1 interfaceC2049m1B = this.a.b(c0322w2);
        if (interfaceC2049m1B != null) {
            consumer.accept(new C2262oY(this, interfaceC2049m1B));
        } else if (this.c.containsKey(c0322w2)) {
            consumer.accept(new C2348pY(this, c0322w2));
        }
    }
}
