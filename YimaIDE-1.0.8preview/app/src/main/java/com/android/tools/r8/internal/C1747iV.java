package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1747iV extends NP {
    public static final /* synthetic */ boolean e = true;
    public final C0333y b;
    public final com.android.tools.r8.graph.W3 c;
    public final IdentityHashMap d = new IdentityHashMap();

    public C1747iV(C0333y c0333y, com.android.tools.r8.graph.W3 w3) {
        this.b = c0333y;
        this.c = w3;
    }

    @Override // com.android.tools.r8.internal.NP
    public final Collection a(C1868jt c1868jt) {
        if (!c1868jt.j()) {
            int i = AbstractC0551Hu.c;
            return new Bc0(c1868jt);
        }
        ArrayList<C1661hV> arrayList = new ArrayList();
        Iterator it = c1868jt.b.iterator();
        while (true) {
            C1661hV c1661hV = null;
            if (!it.hasNext()) {
                break;
            }
            com.android.tools.r8.graph.D2 d2 = (com.android.tools.r8.graph.D2) it.next();
            Set setB = b(d2);
            Set setA = a(d2);
            for (C1661hV c1661hV2 : arrayList) {
                c1661hV2.getClass();
                Iterator it2 = setA.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        com.android.tools.r8.graph.D2 d3 = (com.android.tools.r8.graph.D2) it2.next();
                        if (!c1661hV2.a.b.contains(d3) && c1661hV2.c.contains(d3)) {
                            break;
                        }
                    } else {
                        Iterator it3 = setB.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                com.android.tools.r8.graph.D2 d4 = (com.android.tools.r8.graph.D2) it3.next();
                                if (!c1661hV2.a.b.contains(d4) && c1661hV2.d.contains(d4)) {
                                    break;
                                }
                            } else {
                                Iterator it4 = c1661hV2.d.iterator();
                                while (true) {
                                    if (it4.hasNext()) {
                                        com.android.tools.r8.graph.D2 d5 = (com.android.tools.r8.graph.D2) it4.next();
                                        if (d5 != d2 && setB.contains(d5)) {
                                            break;
                                        }
                                    } else {
                                        Iterator it5 = c1661hV2.c.iterator();
                                        while (true) {
                                            if (!it5.hasNext()) {
                                                c1661hV = c1661hV2;
                                                break;
                                            }
                                            com.android.tools.r8.graph.D2 d6 = (com.android.tools.r8.graph.D2) it5.next();
                                            if (d6 != d2 && setA.contains(d6)) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (c1661hV != null) {
                c1661hV.a(setB, d2, setA);
            } else {
                c1661hV = new C1661hV(setB, d2, setA);
                arrayList.add(c1661hV);
            }
            this.d.put(d2, c1661hV.a);
        }
        LinkedList linkedList = new LinkedList();
        Iterator it6 = arrayList.iterator();
        while (it6.hasNext()) {
            C1868jt c1868jt2 = ((C1661hV) it6.next()).a;
            if (!c1868jt2.k()) {
                linkedList.add(c1868jt2);
            } else {
                if (!e && c1868jt2.b.isEmpty()) {
                    x1f.a();
                    return null;
                }
                this.d.remove(c1868jt2.b.getFirst());
            }
        }
        return linkedList;
    }

    public final Set b(com.android.tools.r8.graph.D2 d2) {
        return a(d2, new Function() { // from class: k8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c((D2) obj);
            }
        });
    }

    public final /* synthetic */ Iterable c(com.android.tools.r8.graph.D2 d2) {
        com.android.tools.r8.graph.W3 w3 = this.c;
        C0333y c0333y = this.b;
        w3.getClass();
        return com.android.tools.r8.graph.W3.a(c0333y, d2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final void e() {
        this.d.clear();
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "OnlyDirectlyConnectedOrUnrelatedInterfaces";
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final boolean l() {
        return !this.b.M().S().c;
    }

    public final Set a(com.android.tools.r8.graph.D2 d2) {
        final com.android.tools.r8.graph.W3 w3 = this.c;
        Objects.requireNonNull(w3);
        return a(d2, new Function() { // from class: i8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return w3.b((D2) obj);
            }
        });
    }

    public final Set a(com.android.tools.r8.graph.D2 d2, final Function function) {
        final Sm0 sm0 = new Sm0(new LinkedHashSet());
        sm0.c(d2);
        sm0.a(new Consumer() { // from class: j8h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(sm0, function, (D2) obj);
            }
        });
        if (!e && sm0.b.contains(d2)) {
            x1f.a();
            return null;
        }
        return sm0.b;
    }

    public final /* synthetic */ void a(Sm0 sm0, Function function, com.android.tools.r8.graph.D2 d2) {
        C1868jt c1868jt = (C1868jt) this.d.get(d2);
        if (c1868jt != null) {
            sm0.b((Iterable) c1868jt);
        }
        sm0.b((Iterable) function.apply(d2));
    }
}
