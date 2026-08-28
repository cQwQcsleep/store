package j$.time.temporal;

import j$.time.x;
import j$.time.y;

/* loaded from: /workspace/unpacked/classes3.dex */
final class s implements t {
    public final /* synthetic */ int a;

    public /* synthetic */ s(int i) {
        this.a = i;
    }

    @Override // j$.time.temporal.t
    public final Object a(o oVar) {
        switch (this.a) {
            case 0:
                return (x) oVar.v(n.a);
            case 1:
                return (j$.time.chrono.n) oVar.v(n.b);
            case 2:
                return (u) oVar.v(n.c);
            case 3:
                a aVar = a.OFFSET_SECONDS;
                if (oVar.f(aVar)) {
                    return y.L(oVar.k(aVar));
                }
                return null;
            case 4:
                x xVar = (x) oVar.v(n.a);
                return xVar != null ? xVar : (x) oVar.v(n.d);
            case 5:
                a aVar2 = a.EPOCH_DAY;
                if (oVar.f(aVar2)) {
                    return j$.time.f.P(oVar.s(aVar2));
                }
                return null;
            default:
                a aVar3 = a.NANO_OF_DAY;
                if (oVar.f(aVar3)) {
                    return j$.time.j.K(oVar.s(aVar3));
                }
                return null;
        }
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return "ZoneId";
            case 1:
                return "Chronology";
            case 2:
                return "Precision";
            case 3:
                return "ZoneOffset";
            case 4:
                return "Zone";
            case 5:
                return "LocalDate";
            default:
                return "LocalTime";
        }
    }
}
