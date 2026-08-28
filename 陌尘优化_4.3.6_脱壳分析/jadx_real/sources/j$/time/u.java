package j$.time;

import j$.time.chrono.AbstractC0073i;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class u implements j$.time.temporal.m, j$.time.temporal.p, Comparable, Serializable {
    public static final /* synthetic */ int b = 0;
    private static final long serialVersionUID = -23038383694477807L;
    private final int a;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.a - ((u) obj).a;
    }

    static {
        j$.time.format.o oVar = new j$.time.format.o();
        oVar.l(j$.time.temporal.a.YEAR, 4, 10, j$.time.format.v.EXCEEDS_PAD);
        oVar.v();
    }

    public static u C(int i) {
        j$.time.temporal.a.YEAR.C(i);
        return new u(i);
    }

    private u(int i) {
        this.a = i;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.YEAR || rVar == j$.time.temporal.a.YEAR_OF_ERA || rVar == j$.time.temporal.a.ERA : rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.YEAR_OF_ERA) {
            return j$.time.temporal.w.j(1L, this.a <= 0 ? 1000000000L : 999999999L);
        }
        return j$.time.temporal.n.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        return n(rVar).a(s(rVar), rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i = t.a[((j$.time.temporal.a) rVar).ordinal()];
        int i2 = this.a;
        if (i == 1) {
            if (i2 < 1) {
                i2 = 1 - i2;
            }
            return i2;
        }
        if (i == 2) {
            return i2;
        }
        if (i == 3) {
            return i2 < 1 ? 0 : 1;
        }
        throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
    }

    @Override // j$.time.temporal.m
    /* renamed from: F, reason: merged with bridge method [inline-methods] */
    public final u d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (u) rVar.n(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = t.a[aVar.ordinal()];
        int i2 = this.a;
        if (i == 1) {
            if (i2 < 1) {
                j = 1 - j;
            }
            return C((int) j);
        }
        if (i == 2) {
            return C((int) j);
        }
        if (i == 3) {
            return s(j$.time.temporal.a.ERA) == j ? this : C(1 - i2);
        }
        throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
    }

    @Override // j$.time.temporal.m
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final u e(long j, j$.time.temporal.u uVar) {
        if (!(uVar instanceof j$.time.temporal.b)) {
            return (u) uVar.j(this, j);
        }
        int i = t.b[((j$.time.temporal.b) uVar).ordinal()];
        if (i == 1) {
            return E(j);
        }
        if (i == 2) {
            return E(j$.com.android.tools.r8.a.l(j, 10));
        }
        if (i == 3) {
            return E(j$.com.android.tools.r8.a.l(j, 100));
        }
        if (i == 4) {
            return E(j$.com.android.tools.r8.a.l(j, 1000));
        }
        if (i == 5) {
            j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
            return d(j$.com.android.tools.r8.a.f(s(aVar), j), aVar);
        }
        throw new j$.time.temporal.v("Unsupported unit: " + uVar);
    }

    public final u E(long j) {
        return j == 0 ? this : C(j$.time.temporal.a.YEAR.w(this.a + j));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.e()) {
            return j$.time.chrono.u.d;
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.YEARS;
        }
        return j$.time.temporal.n.c(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        if (!AbstractC0073i.p(mVar).equals(j$.time.chrono.u.d)) {
            throw new C0063a("Adjustment only supported on ISO date-time");
        }
        return mVar.d(this.a, j$.time.temporal.a.YEAR);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof u) {
            return this.a == ((u) obj).a;
        }
        return false;
    }

    public final int hashCode() {
        return this.a;
    }

    public final String toString() {
        return Integer.toString(this.a);
    }

    private Object writeReplace() {
        return new s((byte) 11, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void G(DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return (u) AbstractC0073i.a(fVar, this);
    }
}
