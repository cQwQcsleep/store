package j$.time;

import j$.time.chrono.AbstractC0073i;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class q implements j$.time.temporal.m, j$.time.temporal.p, Comparable, Serializable {
    private static final long serialVersionUID = 7264499704384272492L;
    private final j a;
    private final y b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        q qVar = (q) obj;
        y yVar = qVar.b;
        boolean zEquals = this.b.equals(yVar);
        j jVar = qVar.a;
        j jVar2 = this.a;
        if (zEquals) {
            return jVar2.compareTo(jVar);
        }
        int iCompare = Long.compare(jVar2.S() - (r1.I() * 1000000000), jVar.S() - (qVar.b.I() * 1000000000));
        return iCompare == 0 ? jVar2.compareTo(jVar) : iCompare;
    }

    static {
        j jVar = j.e;
        y yVar = y.g;
        jVar.getClass();
        C(jVar, yVar);
        j jVar2 = j.f;
        y yVar2 = y.f;
        jVar2.getClass();
        C(jVar2, yVar2);
    }

    public static q C(j jVar, y yVar) {
        return new q(jVar, yVar);
    }

    private q(j jVar, y yVar) {
        Objects.a(jVar, "time");
        this.a = jVar;
        Objects.a(yVar, "offset");
        this.b = yVar;
    }

    private q F(j jVar, y yVar) {
        return (this.a == jVar && this.b.equals(yVar)) ? this : new q(jVar, yVar);
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? ((j$.time.temporal.a) rVar).D() || rVar == j$.time.temporal.a.OFFSET_SECONDS : rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return ((j$.time.temporal.a) rVar).j();
            }
            j jVar = this.a;
            jVar.getClass();
            return j$.time.temporal.n.d(jVar, rVar);
        }
        return rVar.s(this);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
                return this.b.I();
            }
            return this.a.s(rVar);
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            j$.time.temporal.a aVar = j$.time.temporal.a.OFFSET_SECONDS;
            j jVar = this.a;
            if (rVar == aVar) {
                return F(jVar, y.L(((j$.time.temporal.a) rVar).w(j)));
            }
            return F(jVar.d(j, rVar), this.b);
        }
        return (q) rVar.n(this, j);
    }

    @Override // j$.time.temporal.m
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final q e(long j, j$.time.temporal.u uVar) {
        if (uVar instanceof j$.time.temporal.b) {
            return F(this.a.e(j, uVar), this.b);
        }
        return (q) uVar.j(this, j);
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
        if (((tVar == j$.time.temporal.n.k()) || (tVar == j$.time.temporal.n.e())) || tVar == j$.time.temporal.n.f()) {
            return null;
        }
        if (tVar == j$.time.temporal.n.g()) {
            return this.a;
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.NANOS;
        }
        return tVar.a(this);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(this.a.S(), j$.time.temporal.a.NANO_OF_DAY).d(this.b.I(), j$.time.temporal.a.OFFSET_SECONDS);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return this.a.equals(qVar.a) && this.b.equals(qVar.b);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public final String toString() {
        return this.a.toString() + this.b.toString();
    }

    private Object writeReplace() {
        return new s((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) {
        this.a.W(objectOutput);
        this.b.O(objectOutput);
    }

    static q E(ObjectInput objectInput) {
        return new q(j.R(objectInput), y.N(objectInput));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return (q) AbstractC0073i.a(fVar, this);
    }
}
