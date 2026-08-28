package j$.time;

import j$.time.chrono.AbstractC0073i;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class p implements j$.time.temporal.m, j$.time.temporal.p, Comparable, Serializable {
    private static final long serialVersionUID = 2287754244819255394L;
    private final h a;
    private final y b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int iCompare;
        p pVar = (p) obj;
        y yVar = pVar.b;
        y yVar2 = this.b;
        boolean zEquals = yVar2.equals(yVar);
        h hVar = pVar.a;
        h hVar2 = this.a;
        if (zEquals) {
            iCompare = hVar2.compareTo(hVar);
        } else {
            hVar2.getClass();
            long jN = AbstractC0073i.n(hVar2, yVar2);
            hVar.getClass();
            iCompare = Long.compare(jN, AbstractC0073i.n(hVar, pVar.b));
            if (iCompare == 0) {
                iCompare = hVar2.b().H() - hVar.b().H();
            }
        }
        return iCompare == 0 ? hVar2.compareTo(hVar) : iCompare;
    }

    static {
        h hVar = h.c;
        y yVar = y.g;
        hVar.getClass();
        C(hVar, yVar);
        h hVar2 = h.d;
        y yVar2 = y.f;
        hVar2.getClass();
        C(hVar2, yVar2);
    }

    public static p C(h hVar, y yVar) {
        return new p(hVar, yVar);
    }

    private p(h hVar, y yVar) {
        Objects.a(hVar, "dateTime");
        this.a = hVar;
        Objects.a(yVar, "offset");
        this.b = yVar;
    }

    private p G(h hVar, y yVar) {
        return (this.a == hVar && this.b.equals(yVar)) ? this : new p(hVar, yVar);
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
            int i = o.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 1) {
                throw new j$.time.temporal.v("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
            }
            if (i == 2) {
                return this.b.I();
            }
            return this.a.k(rVar);
        }
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i = o.a[((j$.time.temporal.a) rVar).ordinal()];
        y yVar = this.b;
        h hVar = this.a;
        if (i != 1) {
            return i != 2 ? hVar.s(rVar) : yVar.I();
        }
        hVar.getClass();
        return AbstractC0073i.n(hVar, yVar);
    }

    public final h F() {
        return this.a;
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return G(this.a.R(fVar), this.b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            int i = o.a[aVar.ordinal()];
            y yVar = this.b;
            h hVar = this.a;
            if (i != 1) {
                if (i == 2) {
                    return G(hVar, y.L(aVar.w(j)));
                }
                return G(hVar.d(j, rVar), yVar);
            }
            Instant instantG = Instant.G(j, hVar.E());
            Objects.a(instantG, "instant");
            Objects.a(yVar, "zone");
            y yVarD = yVar.C().d(instantG);
            return new p(h.L(instantG.D(), instantG.E(), yVarD), yVarD);
        }
        return (p) rVar.n(this, j);
    }

    @Override // j$.time.temporal.m
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final p e(long j, j$.time.temporal.u uVar) {
        if (uVar instanceof j$.time.temporal.b) {
            return G(this.a.e(j, uVar), this.b);
        }
        return (p) uVar.j(this, j);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.h() || tVar == j$.time.temporal.n.j()) {
            return this.b;
        }
        if (tVar == j$.time.temporal.n.k()) {
            return null;
        }
        j$.time.temporal.t tVarF = j$.time.temporal.n.f();
        h hVar = this.a;
        if (tVar == tVarF) {
            return hVar.P();
        }
        if (tVar == j$.time.temporal.n.g()) {
            return hVar.b();
        }
        if (tVar == j$.time.temporal.n.e()) {
            return j$.time.chrono.u.d;
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.NANOS;
        }
        return tVar.a(this);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.EPOCH_DAY;
        h hVar = this.a;
        return mVar.d(hVar.P().t(), aVar).d(hVar.b().S(), j$.time.temporal.a.NANO_OF_DAY).d(this.b.I(), j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.a.equals(pVar.a) && this.b.equals(pVar.b);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public final String toString() {
        return this.a.toString() + this.b.toString();
    }

    private Object writeReplace() {
        return new s((byte) 10, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        this.a.T(objectOutput);
        this.b.O(objectOutput);
    }

    static p E(ObjectInput objectInput) {
        h hVar = h.c;
        f fVar = f.d;
        return new p(h.K(f.N(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), j.R(objectInput)), y.N(objectInput));
    }
}
