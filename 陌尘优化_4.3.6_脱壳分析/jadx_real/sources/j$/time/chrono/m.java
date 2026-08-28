package j$.time.chrono;

import j$.time.Instant;
import j$.util.Objects;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.List;

/* loaded from: /workspace/unpacked/classes3.dex */
final class m implements InterfaceC0075k, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final transient C0071g a;
    private final transient j$.time.y b;
    private final transient j$.time.x c;

    @Override // j$.time.chrono.InterfaceC0075k
    public final /* synthetic */ long B() {
        return AbstractC0073i.o(this);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ int k(j$.time.temporal.r rVar) {
        return AbstractC0073i.e(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ Object v(j$.time.temporal.t tVar) {
        return AbstractC0073i.l(this, tVar);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return AbstractC0073i.d(this, (InterfaceC0075k) obj);
    }

    static InterfaceC0075k C(j$.time.x xVar, j$.time.y yVar, C0071g c0071g) {
        Objects.a(c0071g, "localDateTime");
        Objects.a(xVar, "zone");
        if (xVar instanceof j$.time.y) {
            return new m(xVar, (j$.time.y) xVar, c0071g);
        }
        j$.time.zone.f fVarC = xVar.C();
        j$.time.h hVarD = j$.time.h.D(c0071g);
        List listG = fVarC.g(hVarD);
        if (listG.size() == 1) {
            yVar = (j$.time.y) listG.get(0);
        } else if (listG.size() == 0) {
            j$.time.zone.b bVarF = fVarC.f(hVarD);
            c0071g = c0071g.F(bVarF.m().k());
            yVar = bVarF.n();
        } else if (yVar == null || !listG.contains(yVar)) {
            yVar = (j$.time.y) listG.get(0);
        }
        Objects.a(yVar, "offset");
        return new m(xVar, yVar, c0071g);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.INSTANT_SECONDS || rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return ((j$.time.temporal.a) rVar).j();
            }
            return ((C0071g) y()).n(rVar);
        }
        return rVar.s(this);
    }

    static m w(n nVar, j$.time.temporal.m mVar) {
        m mVar2 = (m) mVar;
        if (nVar.equals(mVar2.a())) {
            return mVar2;
        }
        throw new ClassCastException("Chronology mismatch, required: " + nVar.i() + ", actual: " + mVar2.a().i());
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = AbstractC0074j.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                return B();
            }
            if (i == 2) {
                return g().I();
            }
            return ((C0071g) y()).s(rVar);
        }
        return rVar.k(this);
    }

    private m(j$.time.x xVar, j$.time.y yVar, C0071g c0071g) {
        Objects.a(c0071g, "dateTime");
        this.a = c0071g;
        Objects.a(yVar, "offset");
        this.b = yVar;
        Objects.a(xVar, "zone");
        this.c = xVar;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final j$.time.y g() {
        return this.b;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final InterfaceC0066b c() {
        return ((C0071g) y()).c();
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final j$.time.j b() {
        return ((C0071g) y()).b();
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final InterfaceC0069e y() {
        return this.a;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final j$.time.x q() {
        return this.c;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final n a() {
        return c().a();
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final InterfaceC0075k h(j$.time.x xVar) {
        return C(xVar, this.b, this.a);
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return (rVar instanceof j$.time.temporal.a) || (rVar != null && rVar.m(this));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return w(a(), rVar.n(this, j));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        int i = AbstractC0076l.a[aVar.ordinal()];
        if (i == 1) {
            return e(j - AbstractC0073i.o(this), j$.time.temporal.b.SECONDS);
        }
        j$.time.x xVar = this.c;
        C0071g c0071g = this.a;
        if (i != 2) {
            return C(xVar, this.b, c0071g.d(j, rVar));
        }
        j$.time.y yVarL = j$.time.y.L(aVar.w(j));
        c0071g.getClass();
        Instant instantG = Instant.G(AbstractC0073i.n(c0071g, yVarL), c0071g.b().H());
        n nVarA = a();
        j$.time.y yVarD = xVar.C().d(instantG);
        Objects.a(yVarD, "offset");
        return new m(xVar, yVarD, (C0071g) nVarA.o(j$.time.h.L(instantG.D(), instantG.E(), yVarD)));
    }

    @Override // j$.time.temporal.m
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0075k e(long j, j$.time.temporal.u uVar) {
        if (uVar instanceof j$.time.temporal.b) {
            return w(a(), this.a.e(j, uVar).w(this));
        }
        return w(a(), uVar.j(this, j));
    }

    private Object writeReplace() {
        return new G((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.a);
        objectOutput.writeObject(this.b);
        objectOutput.writeObject(this.c);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC0075k) && AbstractC0073i.d(this, (InterfaceC0075k) obj) == 0;
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ this.b.hashCode()) ^ Integer.rotateLeft(this.c.hashCode(), 3);
    }

    public final String toString() {
        String string = this.a.toString();
        j$.time.y yVar = this.b;
        String str = string + yVar.toString();
        j$.time.x xVar = this.c;
        if (yVar == xVar) {
            return str;
        }
        return str + "[" + xVar.toString() + "]";
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(j$.time.f fVar) {
        return w(a(), fVar.w(this));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return w(a(), j$.time.temporal.n.b(this, j, bVar));
    }
}
