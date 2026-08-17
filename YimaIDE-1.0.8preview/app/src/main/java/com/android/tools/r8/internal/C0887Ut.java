package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0887Ut;
import com.android.tools.r8.utils.structural.A;
import java.util.function.ToLongFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ut, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0887Ut implements com.android.tools.r8.utils.structural.x<C0887Ut> {
    public static final /* synthetic */ boolean d = true;
    public long b;
    public long c;

    public C0887Ut(int i) {
        this.b = -1L;
        this.c = -1L;
    }

    public static C0887Ut g() {
        return new C0887Ut(0);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean a() {
        boolean z = true;
        boolean z2 = a(-8745955291979317223L) || (this.c & 24) != 0;
        if (!d) {
            if (!b(0) && !b(21) && !b(45) && !b(55) && !b(63) && !b(4) && !b(53) && !b(57) && !b(58) && !b(67) && !b(68)) {
                z = false;
            }
            if (z2 != z) {
                x1f.a();
                return false;
            }
        }
        return z2;
    }

    public final boolean b() {
        boolean zA = a(1729382258252447744L);
        if (!d) {
            if (zA != (b(28) || b(30) || b(59) || b(60))) {
                x1f.a();
                return false;
            }
        }
        return zA;
    }

    public final boolean c() {
        boolean zA = a(2087354105856L);
        if (!d) {
            if (zA != (b(33) || b(34) || b(37) || b(38) || b(39) || b(40))) {
                x1f.a();
                return false;
            }
        }
        return zA;
    }

    public final boolean d() {
        boolean zA = a(1675037245440L);
        if (!d) {
            if (zA != (b(33) || b(34) || b(39) || b(40))) {
                x1f.a();
                return false;
            }
        }
        return zA;
    }

    public final boolean e() {
        return b(42);
    }

    public final boolean f() {
        boolean zA = a(4611686020574871552L);
        if (!d) {
            if (zA != (b(31) || b(62))) {
                x1f.a();
                return false;
            }
        }
        return zA;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: q1f
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0887Ut.a(a);
            }
        };
    }

    public C0887Ut() {
    }

    public final boolean b(int i) {
        if (i < 64) {
            return a(1 << i);
        }
        if (d || i < 128) {
            return (this.c & (1 << (i - 64))) != 0;
        }
        x1f.a();
        return false;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToLongFunction() { // from class: r1f
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((C0887Ut) obj).b;
            }
        }).a(new ToLongFunction() { // from class: s1f
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((C0887Ut) obj).c;
            }
        });
    }

    public final boolean a(long j) {
        return (this.b & j) != 0;
    }

    public final void a(AbstractC0890Uw abstractC0890Uw) {
        int iH2 = abstractC0890Uw.H2();
        if (iH2 < 64) {
            this.b = (1 << iH2) | this.b;
        } else if (!d && iH2 >= 128) {
            x1f.a();
        } else {
            this.c = (1 << (iH2 - 64)) | this.c;
        }
    }
}
