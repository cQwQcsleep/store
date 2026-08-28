package j$.time;

import j$.time.chrono.AbstractC0073i;
import j$.time.chrono.InterfaceC0066b;
import j$.time.chrono.InterfaceC0069e;
import j$.time.chrono.InterfaceC0075k;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class h implements j$.time.temporal.m, j$.time.temporal.p, InterfaceC0069e, Serializable {
    public static final h c = K(f.d, j.e);
    public static final h d = K(f.e, j.f);
    private static final long serialVersionUID = 6207766400415563566L;
    private final f a;
    private final j b;

    @Override // j$.time.chrono.InterfaceC0069e
    public final InterfaceC0066b c() {
        return this.a;
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final j$.time.chrono.n a() {
        return ((f) c()).a();
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final InterfaceC0075k p(y yVar) {
        return B.C(this, yVar, null);
    }

    public static h J(int i) {
        return new h(f.N(i, 12, 31), j.J(0));
    }

    public static h K(f fVar, j jVar) {
        Objects.a(fVar, "date");
        Objects.a(jVar, "time");
        return new h(fVar, jVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(((f) c()).t(), j$.time.temporal.a.EPOCH_DAY).d(b().S(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public static h L(long j, int i, y yVar) {
        Objects.a(yVar, "offset");
        long j2 = i;
        j$.time.temporal.a.NANO_OF_SECOND.C(j2);
        return new h(f.P(j$.com.android.tools.r8.a.k(j + yVar.I(), 86400)), j.K((((int) j$.com.android.tools.r8.a.j(r5, r7)) * 1000000000) + j2));
    }

    public static h D(j$.time.temporal.o oVar) {
        if (oVar instanceof h) {
            return (h) oVar;
        }
        if (oVar instanceof B) {
            return ((B) oVar).F();
        }
        if (oVar instanceof p) {
            return ((p) oVar).F();
        }
        try {
            return new h(f.E(oVar), j.E(oVar));
        } catch (C0063a e) {
            throw new C0063a("Unable to obtain LocalDateTime from TemporalAccessor: " + oVar + " of type " + oVar.getClass().getName(), e);
        }
    }

    private h(f fVar, j jVar) {
        this.a = fVar;
        this.b = jVar;
    }

    private h S(f fVar, j jVar) {
        return (this.a == fVar && this.b == jVar) ? this : new h(fVar, jVar);
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
            if (((j$.time.temporal.a) rVar).D()) {
                j jVar = this.b;
                jVar.getClass();
                return j$.time.temporal.n.d(jVar, rVar);
            }
            return this.a.n(rVar);
        }
        return rVar.s(this);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).D() ? this.b.k(rVar) : this.a.k(rVar);
        }
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).D() ? this.b.s(rVar) : this.a.s(rVar);
        }
        return rVar.k(this);
    }

    private int C(h hVar) {
        int iC = this.a.C(hVar.a);
        return iC == 0 ? this.b.compareTo(hVar.b) : iC;
    }

    public final f P() {
        return this.a;
    }

    public final int G() {
        return this.a.J();
    }

    @Override // j$.time.chrono.InterfaceC0069e
    public final j b() {
        return this.b;
    }

    public final int F() {
        return this.b.I();
    }

    public final int E() {
        return this.b.H();
    }

    public final h R(f fVar) {
        return S(fVar, this.b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return S(fVar, this.b);
    }

    @Override // j$.time.temporal.m
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public final h d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            boolean zD = ((j$.time.temporal.a) rVar).D();
            j jVar = this.b;
            f fVar = this.a;
            if (zD) {
                return S(fVar, jVar.d(j, rVar));
            }
            return S(fVar.d(j, rVar), jVar);
        }
        return (h) rVar.n(this, j);
    }

    @Override // j$.time.temporal.m
    /* renamed from: M, reason: merged with bridge method [inline-methods] */
    public final h e(long j, j$.time.temporal.u uVar) {
        if (uVar instanceof j$.time.temporal.b) {
            int i = g.a[((j$.time.temporal.b) uVar).ordinal()];
            j jVar = this.b;
            f fVar = this.a;
            switch (i) {
                case 1:
                    return O(this.a, 0L, 0L, 0L, j);
                case 2:
                    h hVarS = S(fVar.R(j / 86400000000L), jVar);
                    return hVarS.O(hVarS.a, 0L, 0L, 0L, (j % 86400000000L) * 1000);
                case 3:
                    h hVarS2 = S(fVar.R(j / 86400000), jVar);
                    return hVarS2.O(hVarS2.a, 0L, 0L, 0L, (j % 86400000) * 1000000);
                case 4:
                    return N(j);
                case 5:
                    return O(this.a, 0L, j, 0L, 0L);
                case 6:
                    return O(this.a, j, 0L, 0L, 0L);
                case 7:
                    h hVarS3 = S(fVar.R(j / 256), jVar);
                    return hVarS3.O(hVarS3.a, (j % 256) * 12, 0L, 0L, 0L);
                default:
                    return S(fVar.e(j, uVar), jVar);
            }
        }
        return (h) uVar.j(this, j);
    }

    public final h N(long j) {
        return O(this.a, 0L, 0L, j, 0L);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    private h O(f fVar, long j, long j2, long j3, long j4) {
        long j5 = j | j2 | j3 | j4;
        j jVarK = this.b;
        if (j5 == 0) {
            return S(fVar, jVarK);
        }
        long j6 = j / 24;
        long j7 = j6 + (j2 / 1440) + (j3 / 86400) + (j4 / 86400000000000L);
        long j8 = 1;
        long j9 = ((j % 24) * 3600000000000L) + ((j2 % 1440) * 60000000000L) + ((j3 % 86400) * 1000000000) + (j4 % 86400000000000L);
        long jS = jVarK.S();
        long j10 = (j9 * j8) + jS;
        long jK = j$.com.android.tools.r8.a.k(j10, 86400000000000L) + (j7 * j8);
        long j11 = j$.com.android.tools.r8.a.j(j10, 86400000000000L);
        if (j11 != jS) {
            jVarK = j.K(j11);
        }
        return S(fVar.R(jK), jVarK);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.f()) {
            return this.a;
        }
        return AbstractC0073i.k(this, tVar);
    }

    @Override // java.lang.Comparable
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final int compareTo(InterfaceC0069e interfaceC0069e) {
        if (interfaceC0069e instanceof h) {
            return C((h) interfaceC0069e);
        }
        return AbstractC0073i.c(this, interfaceC0069e);
    }

    public final boolean H(h hVar) {
        if (hVar instanceof h) {
            return C(hVar) > 0;
        }
        long jT = this.a.t();
        long jT2 = hVar.a.t();
        return jT > jT2 || (jT == jT2 && this.b.S() > hVar.b.S());
    }

    public final boolean I(h hVar) {
        if (hVar instanceof h) {
            return C(hVar) < 0;
        }
        long jT = this.a.t();
        long jT2 = hVar.a.t();
        return jT < jT2 || (jT == jT2 && this.b.S() < hVar.b.S());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.a.equals(hVar.a) && this.b.equals(hVar.b);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public final String toString() {
        return this.a.toString() + "T" + this.b.toString();
    }

    private Object writeReplace() {
        return new s((byte) 5, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void T(DataOutput dataOutput) {
        this.a.Z(dataOutput);
        this.b.W(dataOutput);
    }
}
