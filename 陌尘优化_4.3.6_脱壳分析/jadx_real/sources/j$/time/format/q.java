package j$.time.format;

import j$.time.C0063a;
import j$.time.Instant;
import j$.time.chrono.InterfaceC0066b;
import j$.time.x;
import j$.util.Objects;
import java.util.Locale;

/* loaded from: /workspace/unpacked/classes3.dex */
final class q {
    private j$.time.temporal.o a;
    private a b;
    private int c;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [j$.time.format.p] */
    q(Instant instant, a aVar) {
        j$.time.chrono.n nVarB = aVar.b();
        if (nVarB != null) {
            j$.time.chrono.n nVar = (j$.time.chrono.n) instant.v(j$.time.temporal.n.e());
            x xVar = (x) instant.v(j$.time.temporal.n.k());
            InterfaceC0066b interfaceC0066bL = null;
            nVarB = Objects.equals(nVarB, nVar) ? null : nVarB;
            Objects.equals(null, xVar);
            if (nVarB != null) {
                j$.time.chrono.n nVar2 = nVarB != null ? nVarB : nVar;
                if (nVarB != null) {
                    if (instant.f(j$.time.temporal.a.EPOCH_DAY)) {
                        interfaceC0066bL = nVar2.l(instant);
                    } else if (nVarB != j$.time.chrono.u.d || nVar != null) {
                        for (j$.time.temporal.a aVar2 : j$.time.temporal.a.values()) {
                            if (aVar2.v() && instant.f(aVar2)) {
                                throw new C0063a("Unable to apply override chronology '" + nVarB + "' because the temporal object being formatted contains date fields but does not represent a whole date: " + instant);
                            }
                        }
                    }
                }
                instant = new p(interfaceC0066bL, instant, nVar2, xVar);
            }
        }
        this.a = instant;
        this.b = aVar;
    }

    final j$.time.temporal.o d() {
        return this.a;
    }

    final Locale c() {
        return this.b.d();
    }

    final t b() {
        return this.b.c();
    }

    final void g() {
        this.c++;
    }

    final void a() {
        this.c--;
    }

    final Object f(b bVar) {
        j$.time.temporal.o oVar = this.a;
        Object objV = oVar.v(bVar);
        if (objV != null || this.c != 0) {
            return objV;
        }
        throw new C0063a("Unable to extract " + bVar + " from temporal " + oVar);
    }

    final Long e(j$.time.temporal.r rVar) {
        int i = this.c;
        j$.time.temporal.o oVar = this.a;
        if (i <= 0 || oVar.f(rVar)) {
            return Long.valueOf(oVar.s(rVar));
        }
        return null;
    }

    public final String toString() {
        return this.a.toString();
    }
}
