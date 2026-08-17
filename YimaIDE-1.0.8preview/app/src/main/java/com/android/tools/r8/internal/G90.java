package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.C2935wN;
import com.android.tools.r8.internal.G90;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.retrace.RetraceMethodResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G90 implements RetraceMethodResult {
    public static final /* synthetic */ boolean e = true;
    public final AbstractC2508rO a;
    public final List b;
    public final C1667ha0 c;
    public AbstractC2173nV d = AbstractC2173nV.c;

    public G90(C3091y90 c3091y90, List list, AbstractC2508rO abstractC2508rO, C1667ha0 c1667ha0) {
        this.b = list;
        this.a = abstractC2508rO;
        this.c = c1667ha0;
        boolean z = e;
        if (!z && c3091y90 == null) {
            x1f.a();
            throw null;
        }
        if (z || !list.isEmpty()) {
            return;
        }
        x1f.a();
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(C1405eW c1405eW, Function function, OptionalInt optionalInt, K90 k90, ArrayList arrayList) {
        List<C2935wN> list = (List) c1405eW.b();
        if (list == null) {
            arrayList.add(new B90((C3007x90) c1405eW.a(), null, optionalInt));
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        for (C2935wN c2935wN : list) {
            List list2 = (List) function.apply(c2935wN);
            if (list2 != null && !list2.isEmpty()) {
                if (k90 != null && k90.b.isPresent()) {
                    AbstractC0551Hu abstractC0551HuH = ((C3331k.b) C2847vL.b(list2)).h();
                    if (!abstractC0551HuH.isEmpty()) {
                        if (!e && abstractC0551HuH.size() != 1) {
                            x01.a("There can only be one outline entry for a line");
                            return;
                        }
                        EV ev = (EV) abstractC0551HuH.get(0);
                        int asInt = k90.b.getAsInt();
                        int iIntValue = ((Integer) ev.a.getOrDefault(Integer.valueOf(asInt), Integer.valueOf(asInt))).intValue();
                        Function functionA = a(iIntValue);
                        OptionalInt optionalIntOf = OptionalInt.of(iIntValue);
                        OptionalInt.empty();
                        a(c1405eW, functionA, optionalIntOf, new K90(k90.a, OptionalInt.empty()), arrayList);
                        return;
                    }
                }
                arrayList2.add(new C2935wN(c2935wN.b, new C3331k.c(list2)));
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        arrayList.add(new B90((C3007x90) c1405eW.a(), arrayList2, optionalInt));
    }

    public static Function b() {
        return new Function() { // from class: gx5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C2935wN) obj).a();
            }
        };
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final boolean isAmbiguous() {
        if (!this.d.e()) {
            return this.d.d();
        }
        if (this.b.size() > 1) {
            this.d = AbstractC2173nV.a;
            return true;
        }
        List list = (List) ((C1405eW) this.b.get(0)).b();
        if (list == null || list.size() < 2) {
            this.d = AbstractC2173nV.b;
            return false;
        }
        com.android.tools.r8.naming.V.b bVarB = ((C3331k.b) C2847vL.b(((C2935wN) list.get(0)).a.a())).c.b();
        for (int i = 1; i < list.size(); i++) {
            if (!bVarB.equals(((C3331k.b) C2847vL.b(((C2935wN) list.get(i)).a.a())).c.b())) {
                this.d = AbstractC2173nV.a;
                return true;
            }
        }
        this.d = AbstractC2173nV.b;
        return false;
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodResult, com.android.tools.r8.retrace.RetraceResult
    public final boolean isEmpty() {
        List list = (List) ((C1405eW) this.b.get(0)).b();
        return list == null || list.isEmpty();
    }

    @Override // com.android.tools.r8.retrace.RetraceMethodResult
    public final E90 narrowByPosition(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt) {
        ArrayList arrayList = new ArrayList();
        K90 k90 = retraceStackTraceContext instanceof K90 ? (K90) retraceStackTraceContext : null;
        boolean z = optionalInt.isPresent() && optionalInt.getAsInt() > 0;
        Function functionA = z ? a(optionalInt.getAsInt()) : b();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a((C1405eW) it.next(), functionA, optionalInt, k90, arrayList);
        }
        if (z && arrayList.isEmpty()) {
            Iterator it2 = this.b.iterator();
            while (it2.hasNext()) {
                a((C1405eW) it2.next(), a(), optionalInt, k90, arrayList);
            }
        }
        if (arrayList.isEmpty()) {
            boolean z2 = optionalInt.isEmpty() || optionalInt.getAsInt() <= 0;
            for (C1405eW c1405eW : this.b) {
                List arrayList2 = new ArrayList();
                if (c1405eW.b() != null && z2) {
                    arrayList2 = C2847vL.a((Collection) c1405eW.b(), new Function() { // from class: ex5
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return G90.a((C2935wN) obj);
                        }
                    });
                }
                arrayList.add(new B90((C3007x90) c1405eW.a(), arrayList2, optionalInt));
            }
        }
        return new E90(arrayList, this.a, this.c, (K90) retraceStackTraceContext);
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final Stream stream() {
        return this.b.stream().flatMap(new Function() { // from class: jx5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C1405eW) obj);
            }
        });
    }

    public static C2935wN a(C2935wN c2935wN) {
        com.android.tools.r8.naming.N0 n0;
        return (c2935wN.a.a().size() == 1 && (n0 = ((C3331k.b) C2847vL.a(c2935wN.a.a())).b) != null && n0.a == 0 && n0.b == 65535) ? c2935wN : new C2935wN(c2935wN.b, C3331k.c.b);
    }

    public final void a(Set set, C3007x90 c3007x90, List list, C2935wN c2935wN) {
        com.android.tools.r8.naming.V.b bVarB;
        com.android.tools.r8.naming.V v = c2935wN.b;
        if (v != null && !isAmbiguous()) {
            bVarB = v.b().b();
        } else {
            bVarB = ((C3331k.b) C2847vL.b(c2935wN.a.a())).c.b();
        }
        if (set.add(bVarB)) {
            list.add(new F90(this, c3007x90, AbstractC1241ca0.a(V90.a(bVarB, c3007x90.b.a)), c2935wN));
        }
    }

    public static List a(int i, C2935wN c2935wN) {
        return c2935wN.a.a(i, false);
    }

    public static Function a(final int i) {
        return new Function() { // from class: fx5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return G90.a(i, (C2935wN) obj);
            }
        };
    }

    public static Function a() {
        return new Function() { // from class: hx5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C2935wN) obj).b();
            }
        };
    }

    public final Stream a(C1405eW c1405eW) {
        AbstractC1241ca0 c1158ba0;
        final C3007x90 c3007x90 = (C3007x90) c1405eW.a();
        List list = (List) c1405eW.b();
        if (list != null && !list.isEmpty()) {
            final HashSet hashSet = new HashSet();
            final ArrayList arrayList = new ArrayList(list.size());
            list.forEach(new Consumer() { // from class: ix5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(hashSet, c3007x90, arrayList, (C2935wN) obj);
                }
            });
            return arrayList.stream();
        }
        AbstractC2508rO abstractC2508rOA = this.a.a(c3007x90.b.a);
        Comparator comparator = AbstractC1241ca0.c;
        if (abstractC2508rOA instanceof C2423qO) {
            c1158ba0 = new C1072aa0(abstractC2508rOA.a().a, OptionalInt.empty());
        } else {
            c1158ba0 = new C1158ba0(abstractC2508rOA, OptionalInt.empty());
        }
        return Stream.of(new F90(this, c3007x90, c1158ba0, null));
    }
}
