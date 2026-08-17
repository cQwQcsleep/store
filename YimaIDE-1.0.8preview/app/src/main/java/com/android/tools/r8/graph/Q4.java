package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0200e5;
import com.android.tools.r8.graph.Q4;
import com.android.tools.r8.graph.T4;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.internal.EX;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q4 {
    public static final /* synthetic */ boolean d = true;
    public T4 a = null;
    public ArrayList b = null;
    public boolean c = false;

    public final T4 a(T4 t4, Collection collection) {
        T4 t5 = this.a;
        if (t5 == null) {
            return t4;
        }
        if (this.b == null) {
            return t5;
        }
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final Set setC = AbstractC2780ub0.c();
        this.b.forEach(new Consumer() { // from class: byb
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(arrayList, arrayList2, setC, arrayList3, (T4) obj);
            }
        });
        if (!setC.isEmpty()) {
            if (arrayList2.isEmpty() && arrayList.isEmpty()) {
                arrayList3.add(T4.b.c);
            } else {
                arrayList3.add(a(setC, collection));
            }
        }
        if (arrayList.isEmpty()) {
            if (arrayList2.size() == 1 && arrayList3.isEmpty()) {
                return (T4) arrayList2.get(0);
            }
            return (arrayList2.isEmpty() && arrayList3.size() == 1) ? (T4) arrayList3.get(0) : new Y4(arrayList2, arrayList3);
        }
        if (arrayList2.isEmpty() && arrayList3.isEmpty() && arrayList.size() == 1) {
            return (T4) arrayList.get(0);
        }
        if (arrayList.size() != 1) {
            return new Z4(arrayList, arrayList2, arrayList3);
        }
        T4.c cVar = (T4.c) arrayList.get(0);
        cVar.getClass();
        if (cVar instanceof C0207f5) {
            return new C0179b5(cVar.n(), arrayList2, arrayList3);
        }
        C0193d5 c0193d5M = cVar.m();
        if (d || c0193d5M != null) {
            return new X4(c0193d5M, arrayList2, arrayList3);
        }
        x1f.a();
        return null;
    }

    public final void a(T4 t4) {
        if (this.a == null) {
            this.a = t4;
            return;
        }
        if (this.b == null) {
            ArrayList arrayList = new ArrayList();
            this.b = arrayList;
            arrayList.add(this.a);
        }
        this.b.add(t4);
    }

    public static /* synthetic */ boolean a(T4.a aVar, T4.a aVar2) {
        return aVar2 == aVar;
    }

    public final /* synthetic */ void a(final List list, final List list2, final Set set, final List list3, T4 t4) {
        t4.a(new Consumer() { // from class: dyb
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(list, (T4.c) obj);
            }
        }, new Consumer() { // from class: eyb
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Q4.a(list2, (C0200e5) obj);
            }
        }, C0822Sg.b(), new Consumer() { // from class: fyb
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Q4.a(set, list3, (T4.a) obj);
            }
        });
    }

    public final /* synthetic */ void a(List list, T4.c cVar) {
        if (list.isEmpty() || this.c || d) {
            list.add(cVar);
        } else {
            x01.a("Unexpected multiple results between program and classpath");
        }
    }

    public static /* synthetic */ void a(List list, final C0200e5 c0200e5) {
        if (AbstractC3179zC.b(list, new EX() { // from class: zxb
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return Q4.a(c0200e5, (C0200e5) obj);
            }
        })) {
            return;
        }
        list.add(c0200e5);
    }

    public static /* synthetic */ boolean a(C0200e5 c0200e5, C0200e5 c0200e6) {
        return c0200e6.d() == c0200e5.d();
    }

    public static /* synthetic */ void a(Set set, List list, final T4.a aVar) {
        if (aVar.t()) {
            set.add(aVar.l());
        }
        if (AbstractC3179zC.b(list, new EX() { // from class: ayb
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return Q4.a(aVar, (T4.a) obj);
            }
        })) {
            return;
        }
        list.add(aVar);
    }

    public static T4.b a(Set set, Collection collection) {
        final Set setA = C1755ib0.a(collection);
        set.forEach(new Consumer() { // from class: cyb
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Q4.a(setA, (T4.b) obj);
            }
        });
        if (setA.isEmpty()) {
            return T4.b.c;
        }
        return new C0186c5(setA);
    }

    public static void a(Set set, T4.b bVar) {
        if (!d && bVar != T4.b.c) {
            bVar.getClass();
            if (!(bVar instanceof C0186c5)) {
                x1f.a();
                return;
            }
        }
        Collection collection = bVar.b;
        if (collection != null) {
            set.addAll(collection);
        }
    }
}
