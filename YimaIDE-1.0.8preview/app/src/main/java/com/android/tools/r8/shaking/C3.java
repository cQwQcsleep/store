package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.XS;
import com.android.tools.r8.internal.YS;
import com.android.tools.r8.internal.ZS;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3 {
    public final ZS a;

    public C3(ZS zs) {
        this.a = zs;
    }

    public boolean a(com.android.tools.r8.graph.I2 i2) {
        String strE0 = i2.E0();
        ZS zs = this.a;
        zs.getClass();
        XS xs = new XS(new YS(zs));
        while (xs.hasNext()) {
            com.android.tools.r8.internal.S0 s0 = (com.android.tools.r8.internal.S0) xs.next();
            if (B3.a(0, 0, ((B3) s0.b).a, strE0)) {
                return !s0.c;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3)) {
            return false;
        }
        C3 c3 = (C3) obj;
        ZS zs = this.a;
        if (zs.d != c3.a.d) {
            return false;
        }
        XS xs = new XS(new YS(zs));
        ZS zs2 = c3.a;
        zs2.getClass();
        XS xs2 = new XS(new YS(zs2));
        while (xs.hasNext()) {
            if (!((com.android.tools.r8.internal.S0) xs.next()).equals((com.android.tools.r8.internal.S0) xs2.next())) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        ZS zs = this.a;
        zs.getClass();
        XS xs = new XS(new YS(zs));
        int iHashCode = 0;
        while (xs.hasNext()) {
            com.android.tools.r8.internal.S0 s0 = (com.android.tools.r8.internal.S0) xs.next();
            iHashCode = (iHashCode * (s0.c ? 1 : 2) * 13) + ((B3) s0.b).a.hashCode();
        }
        return iHashCode;
    }

    public static class a {
        public final ZS a = new ZS();

        public C3 a() {
            return new C3(this.a);
        }

        public a a(boolean z, B3 b3) {
            this.a.a(b3, z);
            return this;
        }
    }

    public static a a() {
        return new a();
    }
}
