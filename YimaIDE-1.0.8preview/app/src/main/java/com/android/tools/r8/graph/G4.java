package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.G4;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.GX;
import com.android.tools.r8.internal.MX;
import defpackage.hih;
import defpackage.vw5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G4 extends I4 {
    public static final /* synthetic */ boolean c = true;
    public C0231j1[] a;
    public C0231j1[] b;

    public G4(C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2) {
        this.a = c0231j1Arr;
        this.b = c0231j1Arr2;
    }

    @Override // com.android.tools.r8.graph.I4
    public final I4 a(Function function) {
        C0231j1[] c0231j1Arr = new C0231j1[this.a.length];
        C0231j1[] c0231j1Arr2 = new C0231j1[this.b.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            C0231j1[] c0231j1Arr3 = this.a;
            if (i2 < c0231j1Arr3.length) {
                C0231j1 c0231j1 = (C0231j1) function.apply(c0231j1Arr3[i2]);
                c0231j1Arr[i2] = c0231j1;
                if (!c && !c0231j1.g.F()) {
                    x1f.a();
                    return null;
                }
                i2++;
            } else {
                while (true) {
                    C0231j1[] c0231j1Arr4 = this.b;
                    if (i >= c0231j1Arr4.length) {
                        return new G4(c0231j1Arr, c0231j1Arr2);
                    }
                    C0231j1 c0231j2 = (C0231j1) function.apply(c0231j1Arr4[i]);
                    c0231j1Arr2[i] = c0231j2;
                    if (!c && !c0231j2.M0()) {
                        x1f.a();
                        return null;
                    }
                    i++;
                }
            }
        }
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 b(C0322w2 c0322w2, Function function) {
        int i = 0;
        while (true) {
            C0231j1[] c0231j1Arr = this.a;
            if (i >= c0231j1Arr.length) {
                return null;
            }
            C0231j1 c0231j1 = c0231j1Arr[i];
            if (c0322w2.a(c0231j1)) {
                C0231j1 c0231j2 = (C0231j1) function.apply(c0231j1);
                if (!c && !c0231j2.M0()) {
                    x1f.a();
                    return null;
                }
                C0231j1[] c0231j1Arr2 = this.a;
                Consumer consumer = new Consumer() { // from class: yw5
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.e((C0231j1[]) obj);
                    }
                };
                C0231j1 c0231j3 = c0231j1Arr2[i];
                C0231j1[] c0231j1Arr3 = new C0231j1[c0231j1Arr2.length - 1];
                System.arraycopy(c0231j1Arr2, 0, c0231j1Arr3, 0, i);
                System.arraycopy(c0231j1Arr2, i + 1, c0231j1Arr3, i, (c0231j1Arr2.length - i) - 1);
                consumer.accept(c0231j1Arr3);
                c(c0231j2);
                return c0231j2;
            }
            i++;
        }
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 c(C0322w2 c0322w2) {
        C0231j1 c0231j1;
        C0231j1 c0231j2;
        C0231j1[] c0231j1Arr = this.a;
        Consumer consumer = new Consumer() { // from class: zw5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((C0231j1[]) obj);
            }
        };
        int i = 0;
        while (true) {
            if (i >= c0231j1Arr.length) {
                c0231j1 = null;
                break;
            }
            if (c0322w2.a(c0231j1Arr[i])) {
                c0231j1 = c0231j1Arr[i];
                C0231j1[] c0231j1Arr2 = new C0231j1[c0231j1Arr.length - 1];
                System.arraycopy(c0231j1Arr, 0, c0231j1Arr2, 0, i);
                System.arraycopy(c0231j1Arr, i + 1, c0231j1Arr2, i, (c0231j1Arr.length - i) - 1);
                consumer.accept(c0231j1Arr2);
                break;
            }
            i++;
        }
        if (c0231j1 != null) {
            if (c || c0231j1.g.F()) {
                return c0231j1;
            }
            x1f.a();
            return null;
        }
        C0231j1[] c0231j1Arr3 = this.b;
        Consumer consumer2 = new Consumer() { // from class: ax5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.d((C0231j1[]) obj);
            }
        };
        int i2 = 0;
        while (true) {
            if (i2 >= c0231j1Arr3.length) {
                c0231j2 = null;
                break;
            }
            if (c0322w2.a(c0231j1Arr3[i2])) {
                c0231j2 = c0231j1Arr3[i2];
                C0231j1[] c0231j1Arr4 = new C0231j1[c0231j1Arr3.length - 1];
                System.arraycopy(c0231j1Arr3, 0, c0231j1Arr4, 0, i2);
                System.arraycopy(c0231j1Arr3, i2 + 1, c0231j1Arr4, i2, (c0231j1Arr3.length - i2) - 1);
                consumer2.accept(c0231j1Arr4);
                break;
            }
            i2++;
        }
        if (c || c0231j2 == null || c0231j2.M0()) {
            return c0231j2;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void d(Function function) {
        b(h(function));
    }

    @Override // com.android.tools.r8.graph.I4
    public final Iterable e() {
        return AbstractC0728Oq.a(Arrays.asList(this.a), Arrays.asList(this.b));
    }

    @Override // com.android.tools.r8.graph.I4
    public final void f(Function function) {
        a(i(function));
    }

    @Override // com.android.tools.r8.graph.I4
    public final AbstractC1597gi0 g(Function function) {
        for (C0231j1 c0231j1 : this.a) {
            AbstractC1597gi0 abstractC1597gi0 = (AbstractC1597gi0) function.apply(c0231j1);
            if (abstractC1597gi0.c()) {
                return abstractC1597gi0;
            }
        }
        for (C0231j1 c0231j2 : this.b) {
            AbstractC1597gi0 abstractC1597gi1 = (AbstractC1597gi0) function.apply(c0231j2);
            if (abstractC1597gi1.c()) {
                return abstractC1597gi1;
            }
        }
        return C1512fi0.c;
    }

    public final ArrayList h(Function function) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            C0231j1[] c0231j1Arr = this.a;
            if (i >= c0231j1Arr.length) {
                if (!arrayList.isEmpty()) {
                    this.a = (C0231j1[]) com.android.tools.r8.internal.R3.a(this.a, new vw5(), C0231j1.u, this.a.length - arrayList.size());
                }
                return arrayList;
            }
            C0231j1 c0231j1 = c0231j1Arr[i];
            C0231j1 c0231j2 = (C0231j1) function.apply(c0231j1);
            if (!c && c0231j2 == null) {
                x1f.a();
                return null;
            }
            if (c0231j1 != c0231j2 || !c0231j1.g.F()) {
                boolean zF = c0231j2.g.F();
                C0231j1[] c0231j1Arr2 = this.a;
                if (zF) {
                    c0231j1Arr2[i] = c0231j2;
                } else {
                    c0231j1Arr2[i] = null;
                    arrayList.add(c0231j2);
                }
            }
            i++;
        }
    }

    public final ArrayList i(Function function) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            C0231j1[] c0231j1Arr = this.b;
            if (i >= c0231j1Arr.length) {
                break;
            }
            C0231j1 c0231j1 = c0231j1Arr[i];
            C0231j1 c0231j2 = (C0231j1) function.apply(c0231j1);
            if (c0231j1 != c0231j2 || !c0231j1.M0()) {
                boolean zM0 = c0231j2.M0();
                C0231j1[] c0231j1Arr2 = this.b;
                if (zM0) {
                    c0231j1Arr2[i] = c0231j2;
                } else {
                    c0231j1Arr2[i] = null;
                    arrayList.add(c0231j2);
                }
            }
            i++;
        }
        if (!arrayList.isEmpty()) {
            this.b = (C0231j1[]) com.android.tools.r8.internal.R3.a(this.b, new vw5(), C0231j1.u, this.b.length - arrayList.size());
        }
        return arrayList;
    }

    @Override // com.android.tools.r8.graph.I4
    public final Iterable j() {
        if (c || this.b != null) {
            return Arrays.asList(this.b);
        }
        x1f.a();
        return null;
    }

    public final void k() {
        final Set setC = AbstractC2780ub0.c();
        a(new Consumer() { // from class: ww5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                G4.a(setC, (C0231j1) obj);
            }
        }, MX.b);
    }

    public final /* synthetic */ void d(C0231j1[] c0231j1Arr) {
        this.b = c0231j1Arr;
    }

    @Override // com.android.tools.r8.graph.I4
    public final int f() {
        return this.a.length;
    }

    @Override // com.android.tools.r8.graph.I4
    public final String d() {
        return "<method-arraybacking>";
    }

    public final /* synthetic */ void e(C0231j1[] c0231j1Arr) {
        this.a = c0231j1Arr;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void e(Function function) {
        ArrayList arrayListH = h(function);
        a(i(function));
        b(arrayListH);
    }

    @Override // com.android.tools.r8.graph.I4
    public final int g() {
        return this.b.length;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void b() {
        this.b = C0231j1.u;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void b(C0231j1[] c0231j1Arr) {
        C0231j1[] c0231j1Arr2 = C0231j1.u;
        if (c0231j1Arr == null) {
            c0231j1Arr = c0231j1Arr2;
        }
        this.b = c0231j1Arr;
        if (c) {
            return;
        }
        k();
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 b(C0322w2 c0322w2) {
        for (C0231j1 c0231j1 : this.b) {
            if (c0322w2.a(c0231j1)) {
                return c0231j1;
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void i() {
        if (c) {
            return;
        }
        k();
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 b(Predicate predicate) {
        return (C0231j1) GX.a(this.b, predicate);
    }

    @Override // com.android.tools.r8.graph.I4
    public final void b(Collection collection) {
        C0231j1[] c0231j1Arr = new C0231j1[collection.size() + this.b.length];
        C0231j1[] c0231j1Arr2 = this.b;
        System.arraycopy(c0231j1Arr2, 0, c0231j1Arr, 0, c0231j1Arr2.length);
        int length = this.b.length;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            c0231j1Arr[length] = (C0231j1) it.next();
            length++;
        }
        this.b = c0231j1Arr;
        if (c) {
            return;
        }
        k();
    }

    @Override // com.android.tools.r8.graph.I4
    public final void a(Collection collection) {
        C0231j1[] c0231j1Arr = new C0231j1[collection.size() + this.a.length];
        C0231j1[] c0231j1Arr2 = this.a;
        System.arraycopy(c0231j1Arr2, 0, c0231j1Arr, 0, c0231j1Arr2.length);
        int length = this.a.length;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            c0231j1Arr[length] = (C0231j1) it.next();
            length++;
        }
        this.a = c0231j1Arr;
        if (c) {
            return;
        }
        k();
    }

    @Override // com.android.tools.r8.graph.I4
    public final void b(final Function function) {
        final C0231j1[] c0231j1Arr = this.a;
        this.a = C0231j1.u;
        this.a = (C0231j1[]) com.android.tools.r8.internal.R3.a((Object[]) new C0231j1[c0231j1Arr.length], new IntFunction() { // from class: xw5
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return G4.a(function, c0231j1Arr, i);
            }
        });
    }

    public static /* synthetic */ C0231j1 b(Function function, C0231j1[] c0231j1Arr, int i) {
        return (C0231j1) function.apply(c0231j1Arr[i]);
    }

    @Override // com.android.tools.r8.graph.I4
    public final void b(C0231j1 c0231j1) {
        if (c0231j1.g.F()) {
            a(c0231j1);
        } else {
            c(c0231j1);
        }
    }

    @Override // com.android.tools.r8.graph.I4
    public final void a() {
        this.a = C0231j1.u;
    }

    @Override // com.android.tools.r8.graph.I4
    public final int h() {
        return this.a.length + this.b.length;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void a(Set set) {
        C0231j1[] c0231j1Arr = this.a;
        ArrayList arrayList = new ArrayList(c0231j1Arr.length);
        for (C0231j1 c0231j1 : c0231j1Arr) {
            if (!set.contains(c0231j1)) {
                arrayList.add(c0231j1);
            }
        }
        this.a = (C0231j1[]) arrayList.toArray(C0231j1.u);
        C0231j1[] c0231j1Arr2 = this.b;
        ArrayList arrayList2 = new ArrayList(c0231j1Arr2.length);
        for (C0231j1 c0231j2 : c0231j1Arr2) {
            if (!set.contains(c0231j2)) {
                arrayList2.add(c0231j2);
            }
        }
        this.b = (C0231j1[]) arrayList2.toArray(C0231j1.u);
    }

    @Override // com.android.tools.r8.graph.I4
    public final void a(C0231j1[] c0231j1Arr) {
        C0231j1[] c0231j1Arr2 = C0231j1.u;
        if (c0231j1Arr == null) {
            c0231j1Arr = c0231j1Arr2;
        }
        this.a = c0231j1Arr;
        if (c) {
            return;
        }
        k();
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 a(C0322w2 c0322w2) {
        for (C0231j1 c0231j1 : this.a) {
            if (c0322w2.a(c0231j1)) {
                return c0231j1;
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 a(Predicate predicate) {
        return (C0231j1) GX.a(this.a, predicate);
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 a(E2 e2, H2 h2) {
        C0231j1 c0231j1;
        C0231j1[] c0231j1Arr = this.a;
        int length = c0231j1Arr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                c0231j1 = null;
                break;
            }
            c0231j1 = c0231j1Arr[i];
            if (c0231j1.getReference().a(e2, h2)) {
                break;
            }
            i++;
        }
        if (c0231j1 != null) {
            return c0231j1;
        }
        for (C0231j1 c0231j2 : this.b) {
            if (c0231j2.getReference().a(e2, h2)) {
                return c0231j2;
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void a(C0231j1 c0231j1) {
        if (!c && !c0231j1.g.F()) {
            x1f.a();
        } else {
            this.a = (C0231j1[]) com.android.tools.r8.internal.R3.b(this.a, c0231j1);
        }
    }

    @Override // com.android.tools.r8.graph.I4
    public final C0231j1 a(C0322w2 c0322w2, Function function) {
        C0231j1 c0231j1;
        C0231j1[] c0231j1Arr = this.a;
        int i = 0;
        while (true) {
            if (i >= c0231j1Arr.length) {
                c0231j1 = null;
                break;
            }
            C0231j1 c0231j2 = c0231j1Arr[i];
            if (c0322w2.a(c0231j2)) {
                c0231j1 = (C0231j1) function.apply(c0231j2);
                c0231j1Arr[i] = c0231j1;
                break;
            }
            i++;
        }
        if (c || c0231j1 == null || c0231j1.g.F()) {
            return c0231j1;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.graph.I4
    public final Iterable c() {
        if (c || this.a != null) {
            return Arrays.asList(this.a);
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ void c(C0231j1[] c0231j1Arr) {
        this.a = c0231j1Arr;
    }

    @Override // com.android.tools.r8.graph.I4
    public final void c(C0231j1 c0231j1) {
        if (!c && !c0231j1.M0()) {
            x1f.a();
        } else {
            this.b = (C0231j1[]) com.android.tools.r8.internal.R3.b(this.b, c0231j1);
        }
    }

    public static /* synthetic */ C0231j1 a(Function function, C0231j1[] c0231j1Arr, int i) {
        return (C0231j1) function.apply(c0231j1Arr[i]);
    }

    public static /* synthetic */ void a(Set set, C0231j1 c0231j1) {
        boolean zAdd = set.add(c0231j1.getReference());
        if (c || zAdd) {
            return;
        }
        hih.a("Duplicate method `", c0231j1.getReference().m0(), "`");
    }

    @Override // com.android.tools.r8.graph.I4
    public final void c(final Function function) {
        final C0231j1[] c0231j1Arr = this.b;
        this.b = C0231j1.u;
        this.b = (C0231j1[]) com.android.tools.r8.internal.R3.a((Object[]) new C0231j1[c0231j1Arr.length], new IntFunction() { // from class: uw5
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return G4.b(function, c0231j1Arr, i);
            }
        });
    }
}
