package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1159bb;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1159bb implements com.android.tools.r8.utils.structural.x<C1159bb> {
    public static final C1159bb c;
    public static final C1159bb d;
    public static final C1159bb e;
    public static final C1159bb f;
    public static final C1159bb g;
    public static final C1159bb h;
    public static final C1159bb i;
    public static final C1159bb j;
    public static final C1159bb k;
    public static final C1159bb l;
    public static final C1159bb m;
    public static final C1159bb n;
    public static final C1159bb[] o;
    public static final /* synthetic */ boolean p = true;
    public final int b;

    static {
        C1159bb c1159bb = new C1159bb(196653);
        c = c1159bb;
        C1159bb c1159bb2 = new C1159bb(46);
        d = c1159bb2;
        C1159bb c1159bb3 = new C1159bb(47);
        C1159bb c1159bb4 = new C1159bb(48);
        e = c1159bb4;
        C1159bb c1159bb5 = new C1159bb(49);
        f = c1159bb5;
        C1159bb c1159bb6 = new C1159bb(50);
        g = c1159bb6;
        C1159bb c1159bb7 = new C1159bb(51);
        h = c1159bb7;
        C1159bb c1159bb8 = new C1159bb(52);
        i = c1159bb8;
        C1159bb c1159bb9 = new C1159bb(53);
        j = c1159bb9;
        C1159bb c1159bb10 = new C1159bb(54);
        C1159bb c1159bb11 = new C1159bb(55);
        k = c1159bb11;
        C1159bb c1159bb12 = new C1159bb(56);
        C1159bb c1159bb13 = new C1159bb(57);
        C1159bb c1159bb14 = new C1159bb(58);
        C1159bb c1159bb15 = new C1159bb(59);
        l = c1159bb15;
        C1159bb c1159bb16 = new C1159bb(60);
        C1159bb c1159bb17 = new C1159bb(61);
        C1159bb c1159bb18 = new C1159bb(62);
        C1159bb c1159bb19 = new C1159bb(63);
        C1159bb c1159bb20 = new C1159bb(64);
        C1159bb c1159bb21 = new C1159bb(65);
        C1159bb c1159bb22 = new C1159bb(66);
        C1159bb c1159bb23 = new C1159bb(67);
        m = c1159bb23;
        n = new C1159bb(68);
        o = new C1159bb[]{c1159bb, c1159bb2, c1159bb3, c1159bb4, c1159bb5, c1159bb6, c1159bb7, c1159bb8, c1159bb9, c1159bb10, c1159bb11, c1159bb12, c1159bb13, c1159bb14, c1159bb15, c1159bb16, c1159bb17, c1159bb18, c1159bb19, c1159bb20, c1159bb21, c1159bb22, c1159bb23};
    }

    public C1159bb(int i2) {
        this.b = i2;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: thg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C1159bb) obj).b();
            }
        }).a(new ToIntFunction() { // from class: uhg
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C1159bb) obj).c();
            }
        });
    }

    public static Iterable<C1159bb> c(final C1159bb c1159bb, final C1159bb c1159bb2) {
        boolean z = p;
        if (!z && !c1159bb.b(c1159bb2)) {
            x1f.a();
            return null;
        }
        if (!z && c1159bb.c() == 65535) {
            x01.a("This method does not handle preview versions");
            return null;
        }
        if (z || c1159bb2.c() != 65535) {
            return (Iterable) Arrays.stream(o).filter(new Predicate() { // from class: vhg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((C1159bb) obj).a(this.b);
                }
            }).filter(new Predicate() { // from class: whg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((C1159bb) obj).b(this.b);
                }
            }).collect(Collectors.toList());
        }
        x01.a("This method does not handle preview versions");
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public int b() {
        return this.b & 65535;
    }

    public int d() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    public final int hashCode() {
        boolean z = com.android.tools.r8.utils.structural.l.c;
        return com.android.tools.r8.utils.structural.l.a(this, (com.android.tools.r8.utils.structural.y<C1159bb>) o());
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: xhg
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C1159bb.a(a);
            }
        };
    }

    public String toString() {
        if (c() == 0) {
            int iB = b();
            StringBuilder sb = new StringBuilder();
            sb.append(iB);
            return sb.toString();
        }
        return b() + "." + c();
    }

    public static C1159bb b(int i2) {
        return new C1159bb(i2);
    }

    public static Iterable<C1159bb> a() {
        C1159bb[] c1159bbArr = o;
        return c(c1159bbArr[0], c1159bbArr[22]);
    }

    public int c() {
        return this.b >>> 16;
    }
}
