package j$.time.chrono;

import j$.time.AbstractC0064b;
import j$.time.C0063a;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class z extends AbstractC0068d {
    static final j$.time.f d = j$.time.f.N(1873, 1, 1);
    private static final long serialVersionUID = -305327627230580483L;
    private final transient j$.time.f a;
    private transient A b;
    private transient int c;

    @Override // j$.time.chrono.AbstractC0068d
    public final o D() {
        return this.b;
    }

    z(j$.time.f fVar) {
        if (fVar.K(d)) {
            throw new C0063a("JapaneseDate before Meiji 6 is not supported");
        }
        A aI = A.i(fVar);
        this.b = aI;
        this.c = (fVar.J() - aI.o().J()) + 1;
        this.a = fVar;
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final n a() {
        return x.d;
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final int hashCode() {
        x.d.getClass();
        return this.a.hashCode() ^ (-688086063);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b, j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH || rVar == j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR || rVar == j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH || rVar == j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR) {
            return false;
        }
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).v();
        }
        return rVar != null && rVar.m(this);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.s(this);
        }
        if (!f(rVar)) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        int i = y.a[aVar.ordinal()];
        j$.time.f fVar = this.a;
        if (i == 1) {
            return j$.time.temporal.w.j(1L, fVar.M());
        }
        A a = this.b;
        if (i != 2) {
            if (i != 3) {
                return x.d.m(aVar);
            }
            int iJ = a.o().J();
            return a.r() != null ? j$.time.temporal.w.j(1L, (r0.o().J() - iJ) + 1) : j$.time.temporal.w.j(1L, 999999999 - iJ);
        }
        A aR = a.r();
        int iH = (aR == null || aR.o().J() != fVar.J()) ? fVar.L() ? 366 : 365 : aR.o().H() - 1;
        if (this.c == 1) {
            iH -= a.o().H() - 1;
        }
        return j$.time.temporal.w.j(1L, iH);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i = y.a[((j$.time.temporal.a) rVar).ordinal()];
        int i2 = this.c;
        A a = this.b;
        j$.time.f fVar = this.a;
        switch (i) {
            case 2:
                return i2 == 1 ? (fVar.H() - a.o().H()) + 1 : fVar.H();
            case 3:
                return i2;
            case 4:
            case 5:
            case 6:
            case 7:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
            case 8:
                return a.getValue();
            default:
                return fVar.s(rVar);
        }
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public final z d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            if (s(aVar) == j) {
                return this;
            }
            int[] iArr = y.a;
            int i = iArr[aVar.ordinal()];
            j$.time.f fVar = this.a;
            if (i == 3 || i == 8 || i == 9) {
                int iA = x.d.m(aVar).a(j, aVar);
                int i2 = iArr[aVar.ordinal()];
                if (i2 == 3) {
                    return L(this.b, iA);
                }
                if (i2 == 8) {
                    return L(A.A(iA), this.c);
                }
                if (i2 == 9) {
                    return K(fVar.Y(iA));
                }
            }
            return K(fVar.d(j, rVar));
        }
        return (z) super.d(j, rVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: I */
    public final InterfaceC0066b m(j$.time.temporal.p pVar) {
        return (z) super.m(pVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m m(j$.time.f fVar) {
        return (z) super.m(fVar);
    }

    private z L(A a, int i) {
        x.d.getClass();
        if (!(a instanceof A)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        int iJ = (a.o().J() + i) - 1;
        if (i != 1 && (iJ < -999999999 || iJ > 999999999 || iJ < a.o().J() || a != A.i(j$.time.f.N(iJ, 1, 1)))) {
            throw new C0063a("Invalid yearOfEra value");
        }
        return K(this.a.Y(iJ));
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final InterfaceC0069e u(j$.time.j jVar) {
        return C0071g.D(this, jVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b H(long j) {
        return K(this.a.T(j));
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b G(long j) {
        return K(this.a.S(j));
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b F(long j) {
        return K(this.a.R(j));
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b, j$.time.temporal.m
    public final InterfaceC0066b e(long j, j$.time.temporal.u uVar) {
        return (z) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m e(long j, j$.time.temporal.u uVar) {
        return (z) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: E */
    public final InterfaceC0066b j(long j, j$.time.temporal.u uVar) {
        return (z) super.j(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return (z) super.j(j, bVar);
    }

    private z K(j$.time.f fVar) {
        return fVar.equals(this.a) ? this : new z(fVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final long t() {
        return this.a.t();
    }

    @Override // j$.time.chrono.AbstractC0068d
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z) {
            return this.a.equals(((z) obj).a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 4, this);
    }
}
