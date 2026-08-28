package j$.time.chrono;

import j$.time.AbstractC0064b;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class K extends AbstractC0068d {
    private static final long serialVersionUID = -8722293800195731463L;
    private final transient j$.time.f a;

    K(j$.time.f fVar) {
        Objects.a(fVar, "isoDate");
        this.a = fVar;
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final n a() {
        return I.d;
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final int hashCode() {
        I.d.getClass();
        return this.a.hashCode() ^ 146118545;
    }

    @Override // j$.time.chrono.AbstractC0068d
    public final o D() {
        return J() >= 1 ? L.BE : L.BEFORE_BE;
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
        int i = J.a[aVar.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return this.a.n(rVar);
        }
        if (i != 4) {
            return I.d.m(aVar);
        }
        j$.time.temporal.w wVarJ = j$.time.temporal.a.YEAR.j();
        return j$.time.temporal.w.j(1L, J() <= 0 ? (-(wVarJ.e() + 543)) + 1 : 543 + wVarJ.d());
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            int i = J.a[((j$.time.temporal.a) rVar).ordinal()];
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
        return this.a.J() + 543;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final K d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
            if (s(aVar) == j) {
                return this;
            }
            int[] iArr = J.a;
            int i = iArr[aVar.ordinal()];
            j$.time.f fVar = this.a;
            if (i == 4) {
                int iA = I.d.m(aVar).a(j, aVar);
                int i2 = iArr[aVar.ordinal()];
                if (i2 == 4) {
                    if (J() < 1) {
                        iA = 1 - iA;
                    }
                    return L(fVar.Y(iA - 543));
                }
                if (i2 == 6) {
                    return L(fVar.Y(iA - 543));
                }
                if (i2 == 7) {
                    return L(fVar.Y((-542) - J()));
                }
            } else {
                if (i == 5) {
                    I.d.m(aVar).b(j, aVar);
                    return L(fVar.S(j - (((J() * 12) + fVar.I()) - 1)));
                }
                if (i == 6 || i == 7) {
                }
            }
            return L(fVar.d(j, rVar));
        }
        return (K) super.d(j, rVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: I */
    public final InterfaceC0066b m(j$.time.temporal.p pVar) {
        return (K) super.m(pVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m m(j$.time.f fVar) {
        return (K) super.m(fVar);
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
        return (K) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m e(long j, j$.time.temporal.u uVar) {
        return (K) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: E */
    public final InterfaceC0066b j(long j, j$.time.temporal.u uVar) {
        return (K) super.j(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return (K) super.j(j, bVar);
    }

    private K L(j$.time.f fVar) {
        return fVar.equals(this.a) ? this : new K(fVar);
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
        if (obj instanceof K) {
            return this.a.equals(((K) obj).a);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 8, this);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final InterfaceC0069e u(j$.time.j jVar) {
        return C0071g.D(this, jVar);
    }
}
