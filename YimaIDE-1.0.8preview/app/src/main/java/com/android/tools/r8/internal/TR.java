package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1832jW;
import com.android.tools.r8.internal.C0979Yh;
import com.android.tools.r8.internal.TR;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TR extends AbstractC1917kW {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public TR(Map map) {
        if (b || !map.isEmpty()) {
            this.a = map;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static void a(Map map, C0979Yh c0979Yh, AbstractC1832jW abstractC1832jW) {
        AbstractC1832jW c2581sB = abstractC1832jW;
        AbstractC1832jW abstractC1832jW2 = (AbstractC1832jW) map.getOrDefault(c0979Yh, C1293d7.a);
        c2581sB.getClass();
        if (c2581sB instanceof C1293d7) {
            c2581sB = abstractC1832jW2;
        } else {
            abstractC1832jW2.getClass();
            if (!(abstractC1832jW2 instanceof C1293d7)) {
                if ((c2581sB instanceof C3054xk0) || (abstractC1832jW2 instanceof C3054xk0)) {
                    c2581sB = C3054xk0.a;
                } else {
                    C2581sB c2581sBA = c2581sB.a();
                    C2581sB c2581sBA2 = abstractC1832jW2.a();
                    c2581sBA.getClass();
                    int i = AbstractC2554rv.c;
                    C1870jv c1870jv = new C1870jv();
                    c1870jv.a((Iterable) c2581sBA.a);
                    C1870jv c1870jv2 = new C1870jv();
                    c1870jv2.a((Iterable) c2581sBA.b);
                    C1870jv c1870jv3 = new C1870jv();
                    c1870jv3.a((Iterable) c2581sBA.c);
                    boolean z = c2581sBA.d;
                    boolean z2 = c2581sBA.e;
                    boolean z3 = c2581sBA.f;
                    c1870jv.a((Iterable) c2581sBA2.a);
                    c1870jv2.a((Iterable) c2581sBA2.b);
                    c1870jv3.a((Iterable) c2581sBA2.c);
                    c2581sB = new C2581sB(c1870jv.a(), c1870jv2.a(), c1870jv3.a(), z | c2581sBA2.d, z2 | c2581sBA2.e, z3 | c2581sBA2.f);
                }
            }
        }
        map.put(c0979Yh, c2581sB);
    }

    @Override // com.android.tools.r8.internal.AbstractC1917kW
    public final AbstractC1917kW b() {
        boolean z = true;
        boolean z2 = true;
        for (AbstractC1832jW abstractC1832jW : this.a.values()) {
            abstractC1832jW.getClass();
            if (!(abstractC1832jW instanceof C1293d7)) {
                z = false;
            }
            if (!(abstractC1832jW instanceof C3054xk0)) {
                z2 = false;
            }
        }
        if (z) {
            return C1377e7.a;
        }
        if (z2) {
            return C3138yk0.a;
        }
        AbstractC1917kW abstractC1917kWA = a(new BiFunction() { // from class: e1e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return TR.a((C0979Yh) obj, (AbstractC1832jW) obj2);
            }
        });
        boolean z3 = b;
        if (!z3 && (abstractC1917kWA instanceof C1377e7)) {
            x1f.a();
            return null;
        }
        if (z3 || !(abstractC1917kWA instanceof C3138yk0)) {
            return abstractC1917kWA;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1917kW
    public final AbstractC1832jW c() {
        return (AbstractC1832jW) this.a.getOrDefault(C0979Yh.a, C3054xk0.a);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != TR.class) {
            return false;
        }
        return this.a.equals(((TR) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final AbstractC1917kW a(TR tr) {
        tr.getClass();
        final HashMap map = new HashMap(this.a);
        tr.a.forEach(new BiConsumer() { // from class: f1e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TR.a(map, (C0979Yh) obj, (AbstractC1832jW) obj2);
            }
        });
        return map.isEmpty() ? C1377e7.a : new TR(map);
    }

    public final boolean a(BiPredicate biPredicate) {
        for (Map.Entry entry : this.a.entrySet()) {
            if (!biPredicate.test((C0979Yh) entry.getKey(), (AbstractC1832jW) entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1917kW
    public final TR a() {
        return this;
    }

    public static AbstractC1832jW a(C0979Yh c0979Yh, AbstractC1832jW abstractC1832jW) {
        abstractC1832jW.getClass();
        if (abstractC1832jW instanceof C3054xk0) {
            return null;
        }
        return abstractC1832jW.c();
    }

    @Override // com.android.tools.r8.internal.AbstractC1917kW
    public final AbstractC1917kW a(BiFunction biFunction) {
        C0629Ku c0629KuE = null;
        for (Map.Entry entry : this.a.entrySet()) {
            C0979Yh c0979Yh = (C0979Yh) entry.getKey();
            AbstractC1832jW abstractC1832jW = (AbstractC1832jW) entry.getValue();
            AbstractC1832jW abstractC1832jW2 = (AbstractC1832jW) biFunction.apply(c0979Yh, abstractC1832jW);
            if (abstractC1832jW2 != null) {
                if (abstractC1832jW2 != abstractC1832jW) {
                    if (c0629KuE == null) {
                        c0629KuE = AbstractC0706Nu.e();
                        for (Map.Entry entry2 : this.a.entrySet()) {
                            C0979Yh c0979Yh2 = (C0979Yh) entry2.getKey();
                            if (c0979Yh2 == c0979Yh) {
                                break;
                            }
                            c0629KuE.a(c0979Yh2, (AbstractC1832jW) entry2.getValue());
                        }
                    }
                    c0629KuE.a(c0979Yh, abstractC1832jW2);
                } else if (c0629KuE != null) {
                    c0629KuE.a(c0979Yh, abstractC1832jW2);
                }
            }
        }
        if (c0629KuE == null) {
            return this;
        }
        AbstractC0706Nu abstractC0706NuA = c0629KuE.a();
        return abstractC0706NuA.isEmpty() ? C1377e7.a : new TR(abstractC0706NuA);
    }
}
