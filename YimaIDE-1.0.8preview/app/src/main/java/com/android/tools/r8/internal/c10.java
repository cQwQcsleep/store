package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2307p10;
import com.android.tools.r8.internal.X00;
import com.android.tools.r8.internal.c10;
import defpackage.bjg;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class c10 {
    public static final /* synthetic */ boolean f = true;
    public final com.android.tools.r8.graph.B5 a;
    public int b;
    public LinkedList c;
    public LinkedList d;
    public LinkedList e;

    public c10(com.android.tools.r8.graph.B5 b5) {
        this.a = b5;
    }

    public static boolean a(C2307p10 c2307p10) {
        return Stream.of((Object[]) new Y00[]{c2307p10.a, c2307p10.b}).noneMatch(new bjg());
    }

    public final boolean b(X00 x00) {
        Z00 z00 = x00.b;
        z00.getClass();
        Object obj = z00 instanceof C2221o10 ? ((C2307p10) this.e.get(x00.a())).a : (AbstractC1964l10) x00.d.get(0);
        obj.getClass();
        return obj instanceof C0564Ih;
    }

    public final void c() throws C1282d10 {
        LinkedList linkedList = this.c;
        if (linkedList == null || linkedList.isEmpty()) {
            this.e = null;
            this.d = null;
            return;
        }
        C2836vA c2836vA = new C2836vA(16);
        C2836vA c2836vA2 = new C2836vA(16);
        for (X00 x00 : this.c) {
            if (x00.c.isPresent()) {
                Z00 z00 = x00.b;
                z00.getClass();
                if (z00 instanceof C2221o10) {
                    c2836vA.add(x00.a());
                } else {
                    c2836vA2.add(x00.a() / 32);
                }
            }
        }
        int i = 0;
        if (this.d != null) {
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                Y00 y00 = (Y00) this.d.get(i2);
                y00.getClass();
                if (y00 instanceof DL) {
                    c2836vA2.add(i2);
                }
            }
        }
        C1359dx c1359dx = new C1359dx();
        LinkedList linkedList2 = this.e;
        if (linkedList2 != null) {
            Iterator it = linkedList2.iterator();
            int i3 = 0;
            int i4 = 0;
            while (it.hasNext()) {
                it.next();
                if (c2836vA.f(i3)) {
                    c1359dx.b(i3, i3 - i4);
                } else {
                    it.remove();
                    i4++;
                }
                i3++;
            }
            if (!f && !this.e.stream().allMatch(new Predicate() { // from class: cjg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return c10.a((C2307p10) obj);
                }
            })) {
                x1f.a();
                return;
            }
        }
        C1359dx c1359dx2 = new C1359dx();
        LinkedList linkedList3 = this.d;
        if (linkedList3 != null) {
            Iterator it2 = linkedList3.iterator();
            int i5 = 0;
            while (it2.hasNext()) {
                it2.next();
                if (c2836vA2.f(i)) {
                    c1359dx2.b(i, i - i5);
                } else {
                    it2.remove();
                    i5++;
                }
                i++;
            }
            for (Y00 y01 : this.d) {
                y01.getClass();
                if (y01 instanceof C0564Ih) {
                    throw new C1282d10();
                }
            }
            if (!f && !this.d.stream().noneMatch(new bjg())) {
                x1f.a();
                return;
            }
        }
        for (X00 x01 : this.c) {
            if (x01.c.isPresent()) {
                Z00 z01 = x01.b;
                z01.getClass();
                if (z01 instanceof C2221o10) {
                    int i6 = c1359dx.get(x01.a());
                    if (!X00.e && !x01.c.isPresent()) {
                        x1f.a();
                        return;
                    }
                    x01.c = OptionalInt.of(i6);
                } else {
                    int iA = x01.a();
                    int i7 = iA / 32;
                    int i8 = iA % 32;
                    if (!f && !c1359dx2.a(i7)) {
                        x1f.a();
                        return;
                    }
                    int i9 = (c1359dx2.get(i7) * 32) + i8;
                    if (!X00.e && !x01.c.isPresent()) {
                        x1f.a();
                        return;
                    }
                    x01.c = OptionalInt.of(i9);
                }
            }
        }
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void a(X00 x00) {
        if (this.c == null) {
            this.c = new LinkedList();
        }
        this.c.add(x00);
    }

    public final void a(Y00 y00) {
        if (this.d == null) {
            this.d = new LinkedList();
        }
        this.d.add(y00);
    }

    public final void a(Y00 y00, Y00 y01) {
        if (this.e == null) {
            this.e = new LinkedList();
        }
        this.e.add(new C2307p10(y00, y01));
    }

    public final C1366e10 a() throws C1282d10 {
        b();
        c();
        return new C1366e10(this.a, this.b, this.c, this.d, this.e);
    }

    public final void b() {
        if (this.c != null) {
            this.c.removeIf(new Predicate() { // from class: djg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.b((X00) obj);
                }
            });
        }
    }
}
