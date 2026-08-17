package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C2490r8;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2490r8 implements Iterable {
    public static final C2490r8 e = new C2490r8();
    public static final C2490r8 f = new C2490r8();
    public static final /* synthetic */ boolean g = true;
    public final AbstractC0551Hu b;
    public final AbstractC0551Hu c;
    public AbstractC2554rv d;

    public C2490r8(List list, List list2) {
        boolean z = g;
        if (!z && list.isEmpty()) {
            x1f.a();
            throw null;
        }
        if (!z && list.size() != list2.size()) {
            x1f.a();
            throw null;
        }
        this.b = AbstractC0551Hu.a(list);
        this.c = AbstractC0551Hu.a(list2);
    }

    public final C2490r8 a(final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        final C0919Vz c0919Vz = new C0919Vz(16);
        final Set setC = AbstractC2780ub0.c();
        List listA = C2847vL.a(this.b, new InterfaceC2665tA() { // from class: n7i
            @Override // com.android.tools.r8.internal.InterfaceC2665tA
            public final Object a(int i, Object obj) {
                return C2490r8.a(abstractC3148ys, abstractC3148ys2, setC, c0919Vz, i, (I2) obj);
            }
        }, (List) null);
        if (listA == null) {
            if (g || c0919Vz.isEmpty()) {
                return this;
            }
            x1f.a();
            return null;
        }
        boolean zIsEmpty = c0919Vz.isEmpty();
        List listA2 = this.c;
        if (!zIsEmpty) {
            listA2 = C2847vL.a(listA2, c0919Vz);
        }
        return new C2490r8(listA, listA2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2490r8)) {
            return false;
        }
        C2490r8 c2490r8 = (C2490r8) obj;
        return this.b.equals(c2490r8.b) && this.c.equals(c2490r8.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + (this.b.hashCode() * 31);
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C2320p8(this);
    }

    public final int size() {
        if (g || this.b.size() == this.c.size()) {
            return this.b.size();
        }
        x1f.a();
        return 0;
    }

    public C2490r8() {
        int i = AbstractC0551Hu.c;
        P40 p40 = P40.e;
        this.b = p40;
        this.c = p40;
    }

    public final C2490r8 a(final com.android.tools.r8.graph.I2 i2) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        a(new BiConsumer() { // from class: m7i
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C2490r8.a(i2, arrayList, arrayList2, (I2) obj, obj2);
            }
        });
        return new C2490r8(arrayList, arrayList2);
    }

    public static /* synthetic */ void a(com.android.tools.r8.graph.I2 i2, List list, List list2, com.android.tools.r8.graph.I2 i3, Object obj) {
        if (i3 != i2) {
            list.add(i3);
            list2.add(obj);
        }
    }

    public final Set a() {
        if (this.d == null) {
            this.d = AbstractC2554rv.a(this.c);
        }
        return this.d;
    }

    public static /* synthetic */ com.android.tools.r8.graph.I2 a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, Set set, InterfaceC1981lA interfaceC1981lA, int i, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(abstractC3148ys2, i2);
        if (set.add(i2C)) {
            return i2C;
        }
        interfaceC1981lA.add(i);
        return null;
    }

    public final void a(BiConsumer biConsumer) {
        for (int i = 0; i < size(); i++) {
            biConsumer.accept((com.android.tools.r8.graph.I2) this.b.get(i), this.c.get(i));
        }
    }
}
