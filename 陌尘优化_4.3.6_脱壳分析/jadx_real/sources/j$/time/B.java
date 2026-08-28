package j$.time;

import j$.time.chrono.AbstractC0073i;
import j$.time.chrono.InterfaceC0066b;
import j$.time.chrono.InterfaceC0069e;
import j$.time.chrono.InterfaceC0075k;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.List;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class B implements j$.time.temporal.m, InterfaceC0075k, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;
    private final h a;
    private final y b;
    private final x c;

    @Override // j$.time.chrono.InterfaceC0075k
    public final /* synthetic */ long B() {
        return AbstractC0073i.o(this);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return AbstractC0073i.d(this, (InterfaceC0075k) obj);
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final InterfaceC0069e y() {
        return this.a;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final j$.time.chrono.n a() {
        return ((f) c()).a();
    }

    public static B C(h hVar, x xVar, y yVar) {
        Objects.a(hVar, "localDateTime");
        Objects.a(xVar, "zone");
        if (xVar instanceof y) {
            return new B(hVar, xVar, (y) xVar);
        }
        j$.time.zone.f fVarC = xVar.C();
        List listG = fVarC.g(hVar);
        if (listG.size() == 1) {
            yVar = (y) listG.get(0);
        } else if (listG.size() == 0) {
            j$.time.zone.b bVarF = fVarC.f(hVar);
            hVar = hVar.N(bVarF.m().k());
            yVar = bVarF.n();
        } else if (yVar == null || !listG.contains(yVar)) {
            yVar = (y) listG.get(0);
            Objects.a(yVar, "offset");
        }
        return new B(hVar, xVar, yVar);
    }

    private static B w(long j, int i, x xVar) {
        y yVarD = xVar.C().d(Instant.G(j, i));
        return new B(h.L(j, i, yVarD), xVar, yVarD);
    }

    private B(h hVar, x xVar, y yVar) {
        this.a = hVar;
        this.b = yVar;
        this.c = xVar;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return (rVar instanceof j$.time.temporal.a) || (rVar != null && rVar.m(this));
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.INSTANT_SECONDS || rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return ((j$.time.temporal.a) rVar).j();
            }
            return this.a.n(rVar);
        }
        return rVar.s(this);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = A.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                throw new j$.time.temporal.v("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i == 2) {
                return this.b.I();
            }
            return this.a.k(rVar);
        }
        return AbstractC0073i.e(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i = A.a[((j$.time.temporal.a) rVar).ordinal()];
        return i != 1 ? i != 2 ? this.a.s(rVar) : this.b.I() : AbstractC0073i.o(this);
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final y g() {
        return this.b;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final x q() {
        return this.c;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final InterfaceC0075k h(x xVar) {
        Objects.a(xVar, "zone");
        return this.c.equals(xVar) ? this : C(this.a, xVar, this.b);
    }

    public final h F() {
        return this.a;
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final InterfaceC0066b c() {
        return this.a.P();
    }

    @Override // j$.time.chrono.InterfaceC0075k
    public final j b() {
        return this.a.b();
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return C(h.K(fVar, this.a.b()), this.c, this.b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            int i = A.a[aVar.ordinal()];
            h hVar = this.a;
            x xVar = this.c;
            if (i == 1) {
                return w(j, hVar.E(), xVar);
            }
            y yVar = this.b;
            if (i != 2) {
                return C(hVar.d(j, rVar), xVar, yVar);
            }
            y yVarL = y.L(aVar.w(j));
            return (yVarL.equals(yVar) || !xVar.C().g(hVar).contains(yVarL)) ? this : new B(hVar, xVar, yVarL);
        }
        return (B) rVar.n(this, j);
    }

    @Override // j$.time.temporal.m
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final B e(long j, j$.time.temporal.u uVar) {
        if (!(uVar instanceof j$.time.temporal.b)) {
            return (B) uVar.j(this, j);
        }
        j$.time.temporal.b bVar = (j$.time.temporal.b) uVar;
        int iCompareTo = bVar.compareTo(j$.time.temporal.b.DAYS);
        y yVar = this.b;
        x xVar = this.c;
        h hVar = this.a;
        if (iCompareTo >= 0 && bVar != j$.time.temporal.b.FOREVER) {
            return C(hVar.e(j, uVar), xVar, yVar);
        }
        h hVarM = hVar.e(j, uVar);
        Objects.a(hVarM, "localDateTime");
        Objects.a(yVar, "offset");
        Objects.a(xVar, "zone");
        return xVar.C().g(hVarM).contains(yVar) ? new B(hVarM, xVar, yVar) : w(AbstractC0073i.n(hVarM, yVar), hVarM.E(), xVar);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.f()) {
            return this.a.P();
        }
        return AbstractC0073i.l(this, tVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof B)) {
            return false;
        }
        B b = (B) obj;
        return this.a.equals(b.a) && this.b.equals(b.b) && this.c.equals(b.c);
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ this.b.hashCode()) ^ Integer.rotateLeft(this.c.hashCode(), 3);
    }

    public final String toString() {
        String string = this.a.toString();
        y yVar = this.b;
        String str = string + yVar.toString();
        x xVar = this.c;
        if (yVar == xVar) {
            return str;
        }
        return str + "[" + xVar.toString() + "]";
    }

    private Object writeReplace() {
        return new s((byte) 6, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void G(DataOutput dataOutput) {
        this.a.T(dataOutput);
        this.b.O(dataOutput);
        this.c.G((ObjectOutput) dataOutput);
    }

    static B E(ObjectInput objectInput) {
        h hVar = h.c;
        f fVar = f.d;
        h hVarK = h.K(f.N(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), j.R(objectInput));
        y yVarN = y.N(objectInput);
        x xVar = (x) s.a(objectInput);
        Objects.a(xVar, "zone");
        if ((xVar instanceof y) && !yVarN.equals(xVar)) {
            throw new IllegalArgumentException("ZoneId must match ZoneOffset");
        }
        return new B(hVarK, xVar, yVarN);
    }
}
