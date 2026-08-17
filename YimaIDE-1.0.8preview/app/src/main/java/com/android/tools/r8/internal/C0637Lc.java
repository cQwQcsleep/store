package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0637Lc;
import com.android.tools.r8.utils.structural.A;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0637Lc extends AbstractC2340pQ implements com.android.tools.r8.utils.structural.x {
    public static final C0637Lc d = new C0637Lc(EnumC0611Kc.e);
    public static final C0637Lc e = new C0637Lc(EnumC0611Kc.c);
    public static final C0637Lc f = new C0637Lc(EnumC0611Kc.b);
    public static final C0637Lc g = new C0637Lc(EnumC0611Kc.f);
    public static final C0637Lc h = new C0637Lc(EnumC0611Kc.d);
    public final int b;
    public final EnumC0611Kc c;

    public C0637Lc(EnumC0611Kc enumC0611Kc) {
        this.c = enumC0611Kc;
        this.b = 0;
    }

    public static C0637Lc a(EnumC0611Kc enumC0611Kc) {
        int iOrdinal = enumC0611Kc.ordinal();
        if (iOrdinal == 0) {
            return f;
        }
        if (iOrdinal == 1) {
            return e;
        }
        if (iOrdinal == 2) {
            return h;
        }
        if (iOrdinal == 3) {
            return d;
        }
        if (iOrdinal == 4) {
            return g;
        }
        defpackage.gk0.a("Unexpected ClassNameMapping: ", enumC0611Kc);
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2340pQ
    public final boolean d() {
        EnumC0611Kc enumC0611Kc = this.c;
        enumC0611Kc.getClass();
        return enumC0611Kc != EnumC0611Kc.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2340pQ
    public final boolean e() {
        EnumC0611Kc enumC0611Kc = this.c;
        enumC0611Kc.getClass();
        return enumC0611Kc == EnumC0611Kc.f;
    }

    public final boolean equals(Object obj) {
        if (C0637Lc.class != obj.getClass()) {
            return false;
        }
        C0637Lc c0637Lc = (C0637Lc) obj;
        return this.b == c0637Lc.b && this.c == c0637Lc.c;
    }

    public final int hashCode() {
        return (this.c.ordinal() * 31) + this.b;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: cy8
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0637Lc.a(a);
            }
        };
    }

    @Override // com.android.tools.r8.internal.AbstractC2340pQ
    public final int b() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2340pQ
    public final void b(com.android.tools.r8.utils.structural.o oVar) {
        super.a(oVar);
    }

    public C0637Lc(EnumC0611Kc enumC0611Kc, int i) {
        this.c = enumC0611Kc;
        this.b = i;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: dy8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C0637Lc) obj).b;
            }
        }).a(new ToIntFunction() { // from class: ey8
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C0637Lc) obj).c.ordinal();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC2340pQ
    public final AbstractC2340pQ a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }
}
