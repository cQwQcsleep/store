package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.internal.C0785Qv;
import com.android.tools.r8.internal.GX;
import com.android.tools.r8.internal.KN;
import com.android.tools.r8.ir.optimize.AbstractC3253f0;
import com.android.tools.r8.ir.optimize.C3259i0;
import com.android.tools.r8.ir.optimize.k0;
import defpackage.j6h;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3259i0 {
    public static final /* synthetic */ boolean k = true;
    public LinkedHashMap a;
    public LinkedHashMap b;
    public LinkedHashMap c;
    public LinkedHashSet d;
    public LinkedHashMap e;
    public LinkedHashMap f;
    public C0785Qv g;
    public LinkedHashMap h;
    public LinkedHashMap i;
    public final int j;

    public C3259i0(int i, C3259i0 c3259i0) {
        this.j = i;
        if (c3259i0 != null) {
            LinkedHashMap linkedHashMap = c3259i0.a;
            if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                this.a = linkedHashMap2;
                linkedHashMap2.putAll(c3259i0.a);
            }
            LinkedHashMap linkedHashMap3 = c3259i0.b;
            if (linkedHashMap3 != null && !linkedHashMap3.isEmpty()) {
                LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                this.b = linkedHashMap4;
                linkedHashMap4.putAll(c3259i0.b);
            }
            LinkedHashMap linkedHashMap5 = c3259i0.c;
            if (linkedHashMap5 != null && !linkedHashMap5.isEmpty()) {
                LinkedHashMap linkedHashMap6 = new LinkedHashMap();
                this.c = linkedHashMap6;
                linkedHashMap6.putAll(c3259i0.c);
            }
            LinkedHashSet linkedHashSet = c3259i0.d;
            if (linkedHashSet != null && !linkedHashSet.isEmpty()) {
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                this.d = linkedHashSet2;
                linkedHashSet2.addAll(c3259i0.d);
            }
            LinkedHashMap linkedHashMap7 = c3259i0.e;
            if (linkedHashMap7 != null && !linkedHashMap7.isEmpty()) {
                LinkedHashMap linkedHashMap8 = new LinkedHashMap();
                this.e = linkedHashMap8;
                linkedHashMap8.putAll(c3259i0.e);
            }
            LinkedHashMap linkedHashMap9 = c3259i0.f;
            if (linkedHashMap9 != null && !linkedHashMap9.isEmpty()) {
                LinkedHashMap linkedHashMap10 = new LinkedHashMap();
                this.f = linkedHashMap10;
                linkedHashMap10.putAll(c3259i0.f);
            }
            this.g = c3259i0.g;
            LinkedHashMap linkedHashMap11 = c3259i0.h;
            if (linkedHashMap11 != null && !linkedHashMap11.isEmpty()) {
                LinkedHashMap linkedHashMap12 = new LinkedHashMap();
                this.h = linkedHashMap12;
                linkedHashMap12.putAll(c3259i0.h);
            }
            LinkedHashMap linkedHashMap13 = c3259i0.i;
            if (linkedHashMap13 == null || linkedHashMap13.isEmpty()) {
                return;
            }
            LinkedHashMap linkedHashMap14 = new LinkedHashMap();
            this.i = linkedHashMap14;
            linkedHashMap14.putAll(c3259i0.i);
        }
    }

    public final void a(int i) {
        boolean z = k;
        if (!z && i <= 0) {
            x1f.a();
            return;
        }
        if (!z && i >= d()) {
            x1f.a();
            return;
        }
        int iA = a(i, this.a);
        LinkedHashSet linkedHashSet = this.d;
        if (linkedHashSet != null && iA != 0) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext() && iA > 0) {
                it.next();
                it.remove();
                iA--;
            }
        }
        int iA2 = a(a(a(a(a(a(iA, this.e), this.f), this.b), this.c), this.h), this.i);
        if (z || iA2 == 0) {
            return;
        }
        x1f.a();
    }

    public final void b(C0245l1 c0245l1, m0 m0Var) {
        LinkedHashMap linkedHashMap;
        c();
        if (!k && (linkedHashMap = this.f) != null && linkedHashMap.containsKey(c0245l1)) {
            x1f.a();
            return;
        }
        if (this.f == null) {
            this.f = new LinkedHashMap();
        }
        this.f.put(c0245l1, m0Var);
    }

    public final void c() {
        int iD = d();
        if (!k && iD > this.j) {
            x1f.a();
        } else if (iD == this.j) {
            a(1);
        }
    }

    public final int d() {
        int iB = b(this.c) + b(this.b) + b(this.a);
        LinkedHashSet linkedHashSet = this.d;
        return b(this.i) + b(this.h) + b(this.f) + b(this.e) + iB + (linkedHashSet != null ? linkedHashSet.size() : 0);
    }

    public static /* synthetic */ boolean b(C0245l1 c0245l1, k0 k0Var) {
        return k0Var.a == c0245l1;
    }

    public final void b(final C0245l1 c0245l1) {
        LinkedHashMap linkedHashMap = this.e;
        if (linkedHashMap != null) {
            linkedHashMap.keySet().removeIf(new Predicate() { // from class: l6h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C3259i0.b(c0245l1, (k0) obj);
                }
            });
        }
    }

    public final void b() {
        this.g = null;
    }

    public static int b(Map map) {
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public static boolean a(KN kn, int i, AbstractC3253f0 abstractC3253f0) {
        return abstractC3253f0.b == kn && abstractC3253f0.a(i);
    }

    public final void a(final C0245l1 c0245l1) {
        LinkedHashMap linkedHashMap = this.h;
        if (linkedHashMap != null) {
            linkedHashMap.keySet().removeIf(new Predicate() { // from class: q6h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C3259i0.a(c0245l1, (k0) obj);
                }
            });
        }
    }

    public static /* synthetic */ boolean a(C0245l1 c0245l1, k0 k0Var) {
        return k0Var.a == c0245l1;
    }

    public final void a() {
        this.h = null;
        this.i = null;
    }

    public static void a(LinkedHashMap linkedHashMap, final LinkedHashMap linkedHashMap2) {
        linkedHashMap.entrySet().removeIf(new Predicate() { // from class: p6h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C3259i0.a(linkedHashMap2, (Map.Entry) obj);
            }
        });
    }

    public static /* synthetic */ boolean a(Map map, Map.Entry entry) {
        return map.get(entry.getKey()) != entry.getValue();
    }

    public static void a(LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2) {
        Objects.requireNonNull(linkedHashSet2);
        linkedHashSet.removeIf(GX.a(new j6h(linkedHashSet2)));
    }

    public static boolean a(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean a(KN kn, AbstractC3253f0 abstractC3253f0) {
        return abstractC3253f0.b == kn;
    }

    public static int a(int i, Map map) {
        Set setKeySet = map != null ? map.keySet() : null;
        if (setKeySet != null && i != 0) {
            Iterator it = setKeySet.iterator();
            while (it.hasNext() && i > 0) {
                it.next();
                it.remove();
                i--;
            }
        }
        return i;
    }

    public final void a(final KN kn) {
        LinkedHashMap linkedHashMap = this.a;
        if (linkedHashMap != null) {
            linkedHashMap.keySet().removeIf(new Predicate() { // from class: o6h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C3259i0.a(kn, (AbstractC3253f0) obj);
                }
            });
        }
    }

    public final void a(final KN kn, final int i) {
        LinkedHashMap linkedHashMap = this.a;
        if (linkedHashMap != null) {
            linkedHashMap.keySet().removeIf(new Predicate() { // from class: n6h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return C3259i0.a(kn, i, (AbstractC3253f0) obj);
                }
            });
        }
    }

    public final void a(AbstractC3253f0 abstractC3253f0, m0 m0Var) {
        c();
        if (this.a == null) {
            this.a = new LinkedHashMap();
        }
        this.a.put(abstractC3253f0, m0Var);
    }

    public final void a(C0245l1 c0245l1, m0 m0Var) {
        c();
        if (this.c == null) {
            this.c = new LinkedHashMap();
        }
        this.c.put(c0245l1, m0Var);
    }

    public final void a(k0 k0Var, l0 l0Var) {
        LinkedHashMap linkedHashMap;
        c();
        if (!k && (linkedHashMap = this.b) != null && linkedHashMap.containsKey(k0Var)) {
            x1f.a();
            return;
        }
        if (this.e == null) {
            this.e = new LinkedHashMap();
        }
        this.e.put(k0Var, l0Var);
    }

    public C3259i0(int i) {
        this.j = i;
    }
}
