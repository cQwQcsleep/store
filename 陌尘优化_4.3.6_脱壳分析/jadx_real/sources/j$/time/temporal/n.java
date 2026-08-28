package j$.time.temporal;

import j$.time.AbstractC0064b;
import j$.time.C0063a;
import j$.util.Objects;

/* loaded from: /workspace/unpacked/classes3.dex */
public abstract /* synthetic */ class n {
    static final t a = new s(0);
    static final t b = new s(1);
    static final t c = new s(2);
    static final t d = new s(3);
    static final t e = new s(4);
    static final t f = new s(5);
    static final t g = new s(6);

    public static t k() {
        return a;
    }

    public static w d(o oVar, r rVar) {
        if (!(rVar instanceof a)) {
            Objects.a(rVar, "field");
            return rVar.s(oVar);
        }
        if (oVar.f(rVar)) {
            return ((a) rVar).j();
        }
        throw new v(AbstractC0064b.a("Unsupported field: ", rVar));
    }

    public static t e() {
        return b;
    }

    public static int a(o oVar, r rVar) {
        w wVarN = oVar.n(rVar);
        if (!wVarN.h()) {
            throw new v("Invalid field " + rVar + " for get() method, use getLong() instead");
        }
        long jS = oVar.s(rVar);
        if (wVarN.i(jS)) {
            return (int) jS;
        }
        throw new C0063a("Invalid value for " + rVar + " (valid values " + wVarN + "): " + jS);
    }

    public static t i() {
        return c;
    }

    public static t j() {
        return e;
    }

    public static t h() {
        return d;
    }

    public static Object c(o oVar, t tVar) {
        if (tVar == a || tVar == b || tVar == c) {
            return null;
        }
        return tVar.a(oVar);
    }

    public static t f() {
        return f;
    }

    public static t g() {
        return g;
    }

    public static m b(m mVar, long j, u uVar) {
        long j2;
        if (j == Long.MIN_VALUE) {
            mVar = mVar.e(Long.MAX_VALUE, uVar);
            j2 = 1;
        } else {
            j2 = -j;
        }
        return mVar.e(j2, uVar);
    }
}
