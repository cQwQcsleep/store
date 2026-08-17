package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C4 {
    public static final C4 d = new C4(AbstractC0439Dm.m(), Ak0.a, false);
    public static final /* synthetic */ boolean e = true;
    public final AbstractC0439Dm a;
    public final B1 b;
    public final boolean c;

    public C4(AbstractC0439Dm abstractC0439Dm, B1 b1, boolean z) {
        this.a = abstractC0439Dm;
        this.b = b1;
        this.c = z;
    }

    public static AbstractC0439Dm a(AbstractC0439Dm abstractC0439Dm, AbstractC0439Dm abstractC0439Dm2) {
        if (!abstractC0439Dm.equals(abstractC0439Dm2)) {
            if (abstractC0439Dm.l()) {
                return abstractC0439Dm2;
            }
            if (!abstractC0439Dm2.l()) {
                return AbstractC0439Dm.m();
            }
        }
        return abstractC0439Dm;
    }

    public final boolean b() {
        if (this == d) {
            return true;
        }
        if (e || !this.a.l() || !this.b.isUnknown() || this.c) {
            return false;
        }
        x1f.a();
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C4.class == obj.getClass()) {
            C4 c4 = (C4) obj;
            if (this.b.equals(c4.b) && this.a.equals(c4.a) && this.c == c4.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.b, this.a, Boolean.valueOf(this.c));
    }

    public static C4 a(AbstractC0439Dm abstractC0439Dm, B1 b1, boolean z) {
        if (abstractC0439Dm.l() && b1.isUnknown() && !z) {
            return d;
        }
        return new C4(abstractC0439Dm, b1, z);
    }

    public static B4 a() {
        return new B4();
    }
}
