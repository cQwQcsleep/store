package j$.time.chrono;

import j$.util.Objects;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* renamed from: j$.time.chrono.g, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0071g implements InterfaceC0069e, j$.time.temporal.m, j$.time.temporal.p, Serializable {
    private static final long serialVersionUID = 4556003607393004514L;
    private final transient InterfaceC0066b a;
    private final transient j$.time.j b;

    @Override // j$.time.temporal.o
    public final /* synthetic */ Object v(j$.time.temporal.t tVar) {
        return AbstractC0073i.k(this, tVar);
    }

    @Override // java.lang.Comparable
    /* renamed from: x */
    public final /* synthetic */ int compareTo(InterfaceC0069e interfaceC0069e) {
        return AbstractC0073i.c(this, interfaceC0069e);
    }

    static C0071g D(InterfaceC0066b interfaceC0066b, j$.time.j jVar) {
        return new C0071g(interfaceC0066b, jVar);
    }

    static C0071g C(n nVar, j$.time.temporal.m mVar) {
        C0071g c0071g = (C0071g) mVar;
        if (nVar.equals(c0071g.a.a())) {
            return c0071g;
        }
        throw new ClassCastException("Chronology mismatch, required: " + nVar.i() + ", actual: " + c0071g.a.a().i());
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final n a() {
        return this.a.a();
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return C(this.a.a(), j$.time.temporal.n.b(this, j, bVar));
    }

    private C0071g(InterfaceC0066b interfaceC0066b, j$.time.j jVar) {
        Objects.a(jVar, "time");
        this.a = interfaceC0066b;
        this.b = jVar;
    }

    private C0071g I(j$.time.temporal.m mVar, j$.time.j jVar) {
        InterfaceC0066b interfaceC0066b = this.a;
        return (interfaceC0066b == mVar && this.b == jVar) ? this : new C0071g(AbstractC0068d.C(interfaceC0066b.a(), mVar), jVar);
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final InterfaceC0066b c() {
        return this.a;
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final j$.time.j b() {
        return this.b;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar != null && rVar.m(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        return aVar.v() || aVar.D();
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (!((j$.time.temporal.a) rVar).D()) {
                return this.a.n(rVar);
            }
            j$.time.j jVar = this.b;
            jVar.getClass();
            return j$.time.temporal.n.d(jVar, rVar);
        }
        return rVar.s(this);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).D() ? this.b.k(rVar) : this.a.k(rVar);
        }
        return n(rVar).a(s(rVar), rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).D() ? this.b.s(rVar) : this.a.s(rVar);
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(j$.time.f fVar) {
        return I(fVar, this.b);
    }

    @Override // j$.time.temporal.m
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public final C0071g d(long j, j$.time.temporal.r rVar) {
        boolean z = rVar instanceof j$.time.temporal.a;
        InterfaceC0066b interfaceC0066b = this.a;
        if (z) {
            boolean zD = ((j$.time.temporal.a) rVar).D();
            j$.time.j jVar = this.b;
            if (zD) {
                return I(interfaceC0066b, jVar.d(j, rVar));
            }
            return I(interfaceC0066b.d(j, rVar), jVar);
        }
        return C(interfaceC0066b.a(), rVar.n(this, j));
    }

    @Override // j$.time.temporal.m
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public final C0071g e(long j, j$.time.temporal.u uVar) {
        boolean z = uVar instanceof j$.time.temporal.b;
        InterfaceC0066b interfaceC0066b = this.a;
        if (z) {
            int i = AbstractC0070f.a[((j$.time.temporal.b) uVar).ordinal()];
            j$.time.j jVar = this.b;
            switch (i) {
                case 1:
                    return G(this.a, 0L, 0L, 0L, j);
                case 2:
                    C0071g c0071gI = I(interfaceC0066b.e(j / 86400000000L, (j$.time.temporal.u) j$.time.temporal.b.DAYS), jVar);
                    return c0071gI.G(c0071gI.a, 0L, 0L, 0L, (j % 86400000000L) * 1000);
                case 3:
                    C0071g c0071gI2 = I(interfaceC0066b.e(j / 86400000, (j$.time.temporal.u) j$.time.temporal.b.DAYS), jVar);
                    return c0071gI2.G(c0071gI2.a, 0L, 0L, 0L, (j % 86400000) * 1000000);
                case 4:
                    return F(j);
                case 5:
                    return G(this.a, 0L, j, 0L, 0L);
                case 6:
                    return G(this.a, j, 0L, 0L, 0L);
                case 7:
                    C0071g c0071gI3 = I(interfaceC0066b.e(j / 256, (j$.time.temporal.u) j$.time.temporal.b.DAYS), jVar);
                    return c0071gI3.G(c0071gI3.a, (j % 256) * 12, 0L, 0L, 0L);
                default:
                    return I(interfaceC0066b.e(j, uVar), jVar);
            }
        }
        return C(interfaceC0066b.a(), uVar.j(this, j));
    }

    final C0071g F(long j) {
        return G(this.a, 0L, 0L, j, 0L);
    }

    private C0071g G(InterfaceC0066b interfaceC0066b, long j, long j2, long j3, long j4) {
        long j5 = j | j2 | j3 | j4;
        j$.time.j jVarK = this.b;
        if (j5 == 0) {
            return I(interfaceC0066b, jVarK);
        }
        long j6 = j2 / 1440;
        long j7 = j / 24;
        long j8 = (j2 % 1440) * 60000000000L;
        long j9 = ((j % 24) * 3600000000000L) + j8 + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long jS = jVarK.S();
        long j10 = j9 + jS;
        long jK = j$.com.android.tools.r8.a.k(j10, 86400000000000L) + j7 + j6 + (j3 / 86400) + (j4 / 86400000000000L);
        long j11 = j$.com.android.tools.r8.a.j(j10, 86400000000000L);
        if (j11 != jS) {
            jVarK = j$.time.j.K(j11);
        }
        return I(interfaceC0066b.e(jK, (j$.time.temporal.u) j$.time.temporal.b.DAYS), jVarK);
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final InterfaceC0075k p(j$.time.y yVar) {
        return m.C(yVar, null, this);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(c().t(), j$.time.temporal.a.EPOCH_DAY).d(b().S(), j$.time.temporal.a.NANO_OF_DAY);
    }

    private Object writeReplace() {
        return new G((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.a);
        objectOutput.writeObject(this.b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC0069e) && AbstractC0073i.c(this, (InterfaceC0069e) obj) == 0;
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public final String toString() {
        return this.a.toString() + "T" + this.b.toString();
    }
}
