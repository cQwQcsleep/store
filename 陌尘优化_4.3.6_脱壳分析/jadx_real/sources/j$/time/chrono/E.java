package j$.time.chrono;

import j$.time.AbstractC0064b;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class E extends AbstractC0068d {
    private static final long serialVersionUID = 1300372329181994526L;
    private final transient j$.time.f a;

    E(j$.time.f fVar) {
        Objects.a(fVar, "isoDate");
        this.a = fVar;
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final n a() {
        return C.d;
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final int hashCode() {
        C.d.getClass();
        return this.a.hashCode() ^ (-1990173233);
    }

    @Override // j$.time.chrono.AbstractC0068d
    public final o D() {
        return J() >= 1 ? F.ROC : F.BEFORE_ROC;
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.s(this);
        }
        if (!AbstractC0073i.h(this, rVar)) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        int i = D.a[aVar.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return this.a.n(rVar);
        }
        if (i != 4) {
            return C.d.m(aVar);
        }
        j$.time.temporal.w wVarJ = j$.time.temporal.a.YEAR.j();
        return j$.time.temporal.w.j(1L, J() <= 0 ? (-wVarJ.e()) + 1912 : wVarJ.d() - 1911);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = D.a[((j$.time.temporal.a) rVar).ordinal()];
            if (i == 4) {
                int iJ = J();
                if (iJ < 1) {
                    iJ = 1 - iJ;
                }
                return iJ;
            }
            j$.time.f fVar = this.a;
            if (i == 5) {
                return ((J() * 12) + fVar.I()) - 1;
            }
            if (i == 6) {
                return J();
            }
            if (i != 7) {
                return fVar.s(rVar);
            }
            return J() < 1 ? 0 : 1;
        }
        return rVar.k(this);
    }

    private int J() {
        return this.a.J() - 1911;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final E d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            if (s(aVar) == j) {
                return this;
            }
            int[] iArr = D.a;
            int i = iArr[aVar.ordinal()];
            j$.time.f fVar = this.a;
            if (i == 4) {
                int iA = C.d.m(aVar).a(j, aVar);
                int i2 = iArr[aVar.ordinal()];
                if (i2 == 4) {
                    return L(fVar.Y(J() >= 1 ? iA + 1911 : 1912 - iA));
                }
                if (i2 == 6) {
                    return L(fVar.Y(iA + 1911));
                }
                if (i2 == 7) {
                    return L(fVar.Y(1912 - J()));
                }
            } else {
                if (i == 5) {
                    C.d.m(aVar).b(j, aVar);
                    return L(fVar.S(j - (((J() * 12) + fVar.I()) - 1)));
                }
                if (i == 6 || i == 7) {
                }
            }
            return L(fVar.d(j, rVar));
        }
        return (E) super.d(j, rVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: I */
    public final InterfaceC0066b m(j$.time.temporal.p pVar) {
        return (E) super.m(pVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m m(j$.time.f fVar) {
        return (E) super.m(fVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b H(long j) {
        return L(this.a.T(j));
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b G(long j) {
        return L(this.a.S(j));
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b F(long j) {
        return L(this.a.R(j));
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b, j$.time.temporal.m
    public final InterfaceC0066b e(long j, j$.time.temporal.u uVar) {
        return (E) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m e(long j, j$.time.temporal.u uVar) {
        return (E) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: E */
    public final InterfaceC0066b j(long j, j$.time.temporal.u uVar) {
        return (E) super.j(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return (E) super.j(j, bVar);
    }

    private E L(j$.time.f fVar) {
        return fVar.equals(this.a) ? this : new E(fVar);
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
        if (obj instanceof E) {
            return this.a.equals(((E) obj).a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 7, this);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final InterfaceC0069e u(j$.time.j jVar) {
        return C0071g.D(this, jVar);
    }
}
