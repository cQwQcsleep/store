package j$.time.format;

import j$.time.Instant;
import j$.time.chrono.InterfaceC0066b;
import j$.time.x;

/* loaded from: /workspace/unpacked/classes3.dex */
final class p implements j$.time.temporal.o {
    final /* synthetic */ InterfaceC0066b a;
    final /* synthetic */ Instant b;
    final /* synthetic */ j$.time.chrono.n c;
    final /* synthetic */ x d;

    @Override // j$.time.temporal.o
    public final /* synthetic */ int k(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.a(this, rVar);
    }

    p(InterfaceC0066b interfaceC0066b, Instant instant, j$.time.chrono.n nVar, x xVar) {
        this.a = interfaceC0066b;
        this.b = instant;
        this.c = nVar;
        this.d = xVar;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        InterfaceC0066b interfaceC0066b = this.a;
        if (interfaceC0066b != null && rVar.v()) {
            return interfaceC0066b.f(rVar);
        }
        return this.b.f(rVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        InterfaceC0066b interfaceC0066b = this.a;
        if (interfaceC0066b != null && rVar.v()) {
            return interfaceC0066b.n(rVar);
        }
        return j$.time.temporal.n.d(this.b, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        InterfaceC0066b interfaceC0066b = this.a;
        if (interfaceC0066b != null && rVar.v()) {
            return interfaceC0066b.s(rVar);
        }
        return this.b.s(rVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.e()) {
            return this.c;
        }
        if (tVar == j$.time.temporal.n.k()) {
            return this.d;
        }
        if (tVar == j$.time.temporal.n.i()) {
            return this.b.v(tVar);
        }
        return tVar.a(this);
    }

    public final String toString() {
        String str;
        String str2 = "";
        j$.time.chrono.n nVar = this.c;
        if (nVar != null) {
            str = " with chronology " + nVar;
        } else {
            str = "";
        }
        x xVar = this.d;
        if (xVar != null) {
            str2 = " with zone " + xVar;
        }
        return this.b + str + str2;
    }
}
