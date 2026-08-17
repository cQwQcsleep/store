package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.AbstractC0208g;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.sun.jna.platform.linux.Fcntl;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.v2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3470v2 {
    public static final int b;
    public static final AbstractC0551Hu c;
    public int a = 0;

    static {
        new C3470v2().a = 7;
        b = 7;
        c = AbstractC0551Hu.a("public", "private", "protected", "static", "final", "abstract", "volatile", "transient", "synchronized", "native", "strictfp", "synthetic", "bridge", "constructor");
    }

    public final AbstractC0551Hu a() {
        return AbstractC0551Hu.a(new BooleanSupplier() { // from class: pii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.i();
            }
        }, new BooleanSupplier() { // from class: yii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.g();
            }
        }, new BooleanSupplier() { // from class: zii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.h();
            }
        }, new BooleanSupplier() { // from class: aji
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.j();
            }
        }, new BooleanSupplier() { // from class: bji
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.e();
            }
        }, new BooleanSupplier() { // from class: cji
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.b();
            }
        }, new BooleanSupplier() { // from class: qii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.o();
            }
        }, new BooleanSupplier() { // from class: rii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.n();
            }
        }, new BooleanSupplier() { // from class: sii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.l();
            }
        }, new BooleanSupplier() { // from class: tii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.f();
            }
        }, new BooleanSupplier() { // from class: uii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.k();
            }
        }, new BooleanSupplier() { // from class: vii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.m();
            }
        }, new BooleanSupplier() { // from class: wii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.c();
            }
        }, new BooleanSupplier() { // from class: xii
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.d();
            }
        });
    }

    public boolean b(AbstractC0208g abstractC0208g) {
        return (this.a & abstractC0208g.b) == 0;
    }

    public final boolean c() {
        return a(64);
    }

    public final boolean d() {
        return a(65536);
    }

    public boolean e() {
        return a(16);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C3470v2) && this.a == ((C3470v2) obj).a;
    }

    public final boolean f() {
        return a(Fcntl.S_IRUSR);
    }

    public final boolean g() {
        return a(2);
    }

    public boolean h() {
        return a(4);
    }

    public final int hashCode() {
        return this.a;
    }

    public boolean i() {
        return a(1);
    }

    public final boolean j() {
        return a(8);
    }

    public final boolean k() {
        return a(Fcntl.S_ISUID);
    }

    public final boolean l() {
        return a(32);
    }

    public final boolean m() {
        return a(4096);
    }

    public final boolean n() {
        return a(128);
    }

    public final boolean o() {
        return a(64);
    }

    public final String toString() {
        AbstractC0551Hu abstractC0551HuA = a();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = false;
        while (true) {
            AbstractC0551Hu abstractC0551Hu = c;
            if (i >= abstractC0551Hu.size()) {
                return sb.toString();
            }
            if (((BooleanSupplier) abstractC0551HuA.get(i)).getAsBoolean()) {
                if (z) {
                    sb.append(' ');
                } else {
                    z = true;
                }
                sb.append((String) abstractC0551Hu.get(i));
            }
            i++;
        }
    }

    public final boolean b() {
        return a(Fcntl.S_ISGID);
    }

    public boolean a(AbstractC0208g abstractC0208g) {
        int i = abstractC0208g.b;
        int i2 = this.a;
        int i3 = b;
        int i4 = ~i3;
        int i5 = i & i2;
        if ((i4 & i5) == (i2 & i4)) {
            return (i2 & i3) == 0 || (i5 & i3) != 0;
        }
        return false;
    }

    public final boolean a(int i) {
        return (this.a & i) != 0;
    }
}
