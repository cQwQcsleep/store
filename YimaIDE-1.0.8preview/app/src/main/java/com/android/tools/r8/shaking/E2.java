package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.P40;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class E2 {
    public final AbstractC0551Hu a;

    static {
        int i = AbstractC0551Hu.c;
        P40 p40 = P40.e;
    }

    public E2(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
    }

    public boolean a(com.android.tools.r8.graph.I2 i2) {
        Ck0 it = this.a.iterator();
        while (it.hasNext()) {
            if (((F2) it.next()).a(i2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean b() {
        return this.a.size() == 0;
    }

    public static class a {
        public final C0473Eu a = AbstractC0551Hu.g();

        public E2 a() {
            return new E2(this.a.a());
        }

        public a a(F2 f2) {
            this.a.a(f2);
            return this;
        }
    }

    public static a a() {
        return new a();
    }
}
