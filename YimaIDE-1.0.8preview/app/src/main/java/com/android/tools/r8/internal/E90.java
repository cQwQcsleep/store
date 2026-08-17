package com.android.tools.r8.internal;

import com.android.tools.r8.internal.B90;
import com.android.tools.r8.internal.D90;
import com.android.tools.r8.internal.E90;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.retrace.RetraceFrameResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E90 implements RetraceFrameResult {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC2508rO a;
    public final List b;
    public final C1667ha0 c;
    public final K90 d;
    public AbstractC2173nV e = AbstractC2173nV.c;

    public E90(ArrayList arrayList, AbstractC2508rO abstractC2508rO, C1667ha0 c1667ha0, K90 k90) {
        this.a = abstractC2508rO;
        this.b = arrayList;
        this.c = c1667ha0;
        this.d = k90;
        if (f || !arrayList.isEmpty()) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final Stream a(B90 b90) {
        E90 e90;
        C3007x90 c3007x90 = b90.a;
        List<C2935wN> list = b90.b;
        OptionalInt optionalInt = b90.c;
        if (list == null || list.isEmpty()) {
            AbstractC2508rO abstractC2508rOA = this.a.a(c3007x90.b.a);
            Comparator comparator = AbstractC1241ca0.c;
            AbstractC1241ca0 c1072aa0 = abstractC2508rOA instanceof C2423qO ? new C1072aa0(abstractC2508rOA.a().a, OptionalInt.empty()) : new C1158ba0(abstractC2508rOA, OptionalInt.empty());
            int i = AbstractC0551Hu.c;
            return Stream.of(new C90(this, c3007x90, c1072aa0, P40.e, Optional.empty(), optionalInt, this.c));
        }
        ArrayList arrayList = new ArrayList();
        for (C2935wN c2935wN : list) {
            List<C3331k.b> listA = c2935wN.a.a();
            com.android.tools.r8.naming.V v = c2935wN.b;
            if (!listA.isEmpty()) {
                C3331k.b bVar = listA.get(0);
                com.android.tools.r8.naming.N0 n0 = bVar.b;
                ArrayList arrayList2 = new ArrayList(AL.a(1));
                Collections.addAll(arrayList2, bVar);
                int i2 = 1;
                ArrayList arrayList3 = arrayList2;
                while (i2 < listA.size()) {
                    C3331k.b bVar2 = listA.get(i2);
                    if (n0 == null || !n0.equals(bVar2.b)) {
                        e90 = this;
                        e90.a(c3007x90, Optional.ofNullable(v), arrayList3, arrayList, optionalInt);
                        ArrayList arrayList4 = new ArrayList();
                        n0 = bVar2.b;
                        arrayList3 = arrayList4;
                    } else {
                        e90 = this;
                    }
                    arrayList3.add(bVar2);
                    i2++;
                    this = e90;
                }
                this.a(c3007x90, Optional.ofNullable(v), arrayList3, arrayList, optionalInt);
            } else {
                if (!f && v == null) {
                    x1f.a();
                    return null;
                }
                arrayList.add(this.a(Collections.singletonList(D90.a(new C3331k.b(null, v.b().b(), null, v.a()))), Optional.of(v), c3007x90, optionalInt));
            }
        }
        return arrayList.stream();
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0092 A[EDGE_INSN: B:43:0x0092->B:44:0x0093 BREAK  A[LOOP:0: B:25:0x005b->B:59:0x005b]] */
    @Override // com.android.tools.r8.retrace.RetraceResult
    public final boolean isAmbiguous() {
        com.android.tools.r8.naming.N0 n0;
        com.android.tools.r8.naming.N0 n1;
        if (this.e.e()) {
            boolean z = true;
            if (this.b.size() <= 1) {
                B90 b90 = (B90) this.b.get(0);
                List list = b90.b;
                if (list == null) {
                    z = false;
                    break;
                }
                if (list.size() <= 1) {
                    if (!B90.d && b90.b.isEmpty()) {
                        x1f.a();
                        return false;
                    }
                    List<C3331k.b> listA = ((C2935wN) b90.b.get(0)).a.a();
                    if (listA != null && !listA.isEmpty()) {
                        C3331k.b bVar = listA.get(0);
                        Iterator<C3331k.b> it = listA.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            C3331k.b next = it.next();
                            com.android.tools.r8.naming.N0 n2 = next.d;
                            if ((n2 != null && n2.a() != 1 && ((n1 = next.b) == null || n1.a() != next.d.a())) || (next != bVar && ((n0 = next.b) == null || !n0.equals(bVar.b)))) {
                                break;
                            }
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            }
            AbstractC2173nV abstractC2173nVA = AbstractC2173nV.a(z);
            this.e = abstractC2173nVA;
            if (!f && abstractC2173nVA.e()) {
                x1f.a();
                return false;
            }
        }
        return this.e.d();
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameResult, com.android.tools.r8.retrace.RetraceResult
    public final boolean isEmpty() {
        List list = ((B90) this.b.get(0)).b;
        return list == null || list.isEmpty();
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final Stream stream() {
        return this.b.stream().flatMap(new Function() { // from class: n34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((B90) obj);
            }
        });
    }

    public final void a(final C3007x90 c3007x90, final Optional optional, ArrayList arrayList, final ArrayList arrayList2, final OptionalInt optionalInt) {
        com.android.tools.r8.naming.N0 n0;
        if (isAmbiguous()) {
            C3331k.b bVar = (C3331k.b) arrayList.get(0);
            com.android.tools.r8.naming.N0 n1 = bVar.d;
            if (n1 != null && n1.a() != 1 && ((n0 = bVar.b) == null || n0.a() != bVar.d.a())) {
                boolean z = f;
                if (!z && arrayList.size() <= 0) {
                    x1f.a();
                    return;
                }
                if (!z && (((C3331k.b) arrayList.get(0)).d == null || ((C3331k.b) arrayList.get(0)).d.b <= ((C3331k.b) arrayList.get(0)).d.a)) {
                    x1f.a();
                    return;
                }
                final ArrayList arrayList3 = new ArrayList();
                InterfaceC2762uL interfaceC2762uL = new InterfaceC2762uL() { // from class: k34
                    @Override // com.android.tools.r8.internal.InterfaceC2762uL
                    public final void accept(Object obj, int i) {
                        E90.a(arrayList3, (C3331k.b) obj, i);
                    }
                };
                boolean z2 = C2847vL.a;
                for (int i = 0; i < arrayList.size(); i++) {
                    interfaceC2762uL.accept(arrayList.get(i), i);
                }
                arrayList3.forEach(new Consumer() { // from class: l34
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.a(arrayList2, optional, c3007x90, optionalInt, (List) obj);
                    }
                });
                return;
            }
        }
        arrayList2.add(a(C2847vL.a((Collection) arrayList, new Function() { // from class: m34
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return D90.a((C3331k.b) obj);
            }
        }), optional, c3007x90, optionalInt));
    }

    public static void a(List list, final C3331k.b bVar, int i) {
        if (i == 0) {
            for (int i2 = bVar.d.a; i2 <= bVar.d.b; i2++) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new D90(bVar, OptionalInt.of(i2)));
                list.add(arrayList);
            }
            return;
        }
        list.forEach(new Consumer() { // from class: o34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) obj).add(D90.a(bVar));
            }
        });
    }

    public final /* synthetic */ void a(List list, Optional optional, C3007x90 c3007x90, OptionalInt optionalInt, List list2) {
        list.add(a(list2, optional, c3007x90, optionalInt));
    }

    public final C90 a(List list, Optional optional, C3007x90 c3007x90, OptionalInt optionalInt) {
        D90 d90 = (D90) list.get(0);
        C3331k.b bVar = d90.a;
        ClassReference classReference = c3007x90.b.a;
        HashSet hashSet = V90.a;
        return new C90(this, c3007x90, a(V90.a(bVar.c, classReference), d90, optionalInt), list, optional, optionalInt, this.c);
    }

    public final C1072aa0 a(MethodReference methodReference, D90 d90, final OptionalInt optionalInt) {
        com.android.tools.r8.naming.N0 n0;
        final C3331k.b bVar = d90.a;
        OptionalInt optionalInt2 = d90.b;
        if (!isAmbiguous() && (bVar.b == null || optionalInt.orElse(-1) == -1)) {
            Supplier supplier = new Supplier() { // from class: p34
                @Override // java.util.function.Supplier
                public final Object get() {
                    return E90.a(bVar);
                }
            };
            if (!optionalInt2.isPresent()) {
                optionalInt2 = (OptionalInt) supplier.get();
            }
            Comparator comparator = AbstractC1241ca0.c;
            return new C1072aa0(methodReference, optionalInt2);
        }
        if (!optionalInt.isEmpty() && (n0 = bVar.b) != null && n0.a(optionalInt.getAsInt())) {
            Supplier supplier2 = new Supplier() { // from class: q34
                @Override // java.util.function.Supplier
                public final Object get() {
                    return OptionalInt.of(bVar.b(optionalInt.getAsInt()));
                }
            };
            if (!optionalInt2.isPresent()) {
                optionalInt2 = (OptionalInt) supplier2.get();
            }
            Comparator comparator2 = AbstractC1241ca0.c;
            return new C1072aa0(methodReference, optionalInt2);
        }
        Comparator comparator3 = AbstractC1241ca0.c;
        return new C1072aa0(methodReference, optionalInt2);
    }

    public static /* synthetic */ OptionalInt a(C3331k.b bVar) {
        int iA = bVar.a(0);
        if (iA > 0) {
            return OptionalInt.of(iA);
        }
        return OptionalInt.empty();
    }
}
