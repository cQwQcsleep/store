package j$.time.chrono;

import j$.time.AbstractC0064b;
import j$.util.Objects;

/* renamed from: j$.time.chrono.i, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract /* synthetic */ class AbstractC0073i {
    public static boolean i(o oVar, j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.ERA : rVar != null && rVar.m(oVar);
    }

    public static n p(j$.time.temporal.o oVar) {
        Objects.a(oVar, "temporal");
        n nVar = (n) oVar.v(j$.time.temporal.n.e());
        u uVar = u.d;
        if (nVar != null) {
            return nVar;
        }
        Objects.a(uVar, "defaultObj");
        return uVar;
    }

    public static int e(InterfaceC0075k interfaceC0075k, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = AbstractC0074j.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                throw new j$.time.temporal.v("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i == 2) {
                return interfaceC0075k.g().I();
            }
            return interfaceC0075k.y().k(rVar);
        }
        return j$.time.temporal.n.a(interfaceC0075k, rVar);
    }

    public static int f(o oVar, j$.time.temporal.a aVar) {
        if (aVar == j$.time.temporal.a.ERA) {
            return oVar.getValue();
        }
        return j$.time.temporal.n.a(oVar, aVar);
    }

    public static long g(o oVar, j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.ERA) {
            return oVar.getValue();
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        return rVar.k(oVar);
    }

    public static Object m(o oVar, j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.ERAS;
        }
        return j$.time.temporal.n.c(oVar, tVar);
    }

    public static Object k(InterfaceC0069e interfaceC0069e, j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.k() || tVar == j$.time.temporal.n.j() || tVar == j$.time.temporal.n.h()) {
            return null;
        }
        if (tVar == j$.time.temporal.n.g()) {
            return interfaceC0069e.b();
        }
        if (tVar == j$.time.temporal.n.e()) {
            return interfaceC0069e.a();
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.NANOS;
        }
        return tVar.a(interfaceC0069e);
    }

    public static boolean h(InterfaceC0066b interfaceC0066b, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).v();
        }
        return rVar != null && rVar.m(interfaceC0066b);
    }

    public static long n(InterfaceC0069e interfaceC0069e, j$.time.y yVar) {
        Objects.a(yVar, "offset");
        return ((interfaceC0069e.c().t() * 86400) + interfaceC0069e.b().T()) - yVar.I();
    }

    public static Object l(InterfaceC0075k interfaceC0075k, j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.j() || tVar == j$.time.temporal.n.k()) {
            return interfaceC0075k.q();
        }
        if (tVar == j$.time.temporal.n.h()) {
            return interfaceC0075k.g();
        }
        if (tVar == j$.time.temporal.n.g()) {
            return interfaceC0075k.b();
        }
        if (tVar == j$.time.temporal.n.e()) {
            return interfaceC0075k.a();
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.NANOS;
        }
        return tVar.a(interfaceC0075k);
    }

    public static int c(InterfaceC0069e interfaceC0069e, InterfaceC0069e interfaceC0069e2) {
        int iCompareTo = interfaceC0069e.c().compareTo(interfaceC0069e2.c());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iC = interfaceC0069e.b().compareTo(interfaceC0069e2.b());
        if (iC != 0) {
            return iC;
        }
        return ((AbstractC0065a) interfaceC0069e.a()).i().compareTo(interfaceC0069e2.a().i());
    }

    public static Object j(InterfaceC0066b interfaceC0066b, j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.k() || tVar == j$.time.temporal.n.j() || tVar == j$.time.temporal.n.h() || tVar == j$.time.temporal.n.g()) {
            return null;
        }
        if (tVar == j$.time.temporal.n.e()) {
            return interfaceC0066b.a();
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.DAYS;
        }
        return tVar.a(interfaceC0066b);
    }

    public static j$.time.temporal.m a(InterfaceC0066b interfaceC0066b, j$.time.temporal.m mVar) {
        return mVar.d(interfaceC0066b.t(), j$.time.temporal.a.EPOCH_DAY);
    }

    public static long o(InterfaceC0075k interfaceC0075k) {
        return ((interfaceC0075k.c().t() * 86400) + interfaceC0075k.b().T()) - interfaceC0075k.g().I();
    }

    public static int d(InterfaceC0075k interfaceC0075k, InterfaceC0075k interfaceC0075k2) {
        int iCompare = Long.compare(interfaceC0075k.B(), interfaceC0075k2.B());
        if (iCompare != 0) {
            return iCompare;
        }
        int iH = interfaceC0075k.b().H() - interfaceC0075k2.b().H();
        if (iH != 0) {
            return iH;
        }
        int iX = interfaceC0075k.y().compareTo(interfaceC0075k2.y());
        if (iX != 0) {
            return iX;
        }
        int iCompareTo = interfaceC0075k.q().i().compareTo(interfaceC0075k2.q().i());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        return ((AbstractC0065a) interfaceC0075k.a()).i().compareTo(interfaceC0075k2.a().i());
    }

    public static int b(InterfaceC0066b interfaceC0066b, InterfaceC0066b interfaceC0066b2) {
        int iCompare = Long.compare(interfaceC0066b.t(), interfaceC0066b2.t());
        if (iCompare != 0) {
            return iCompare;
        }
        return ((AbstractC0065a) interfaceC0066b.a()).i().compareTo(interfaceC0066b2.a().i());
    }
}
