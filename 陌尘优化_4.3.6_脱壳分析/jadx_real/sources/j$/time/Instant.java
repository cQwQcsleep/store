package j$.time;

import j$.time.chrono.AbstractC0073i;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class Instant implements j$.time.temporal.m, j$.time.temporal.p, Comparable<Instant>, Serializable {
    public static final Instant c = new Instant(0, 0);
    private static final long serialVersionUID = -665713676816604388L;
    private final long a;
    private final int b;

    @Override // java.lang.Comparable
    public final int compareTo(Instant instant) {
        Instant instant2 = instant;
        int iCompare = Long.compare(this.a, instant2.a);
        return iCompare != 0 ? iCompare : this.b - instant2.b;
    }

    static {
        G(-31557014167219200L, 0L);
        G(31556889864403199L, 999999999L);
    }

    public static Instant G(long j, long j2) {
        return C(j$.com.android.tools.r8.a.f(j, j$.com.android.tools.r8.a.k(j2, 1000000000L)), (int) j$.com.android.tools.r8.a.j(j2, 1000000000L));
    }

    public static Instant F(long j) {
        long j2 = 1000;
        return C(j$.com.android.tools.r8.a.k(j, j2), ((int) j$.com.android.tools.r8.a.j(j, j2)) * 1000000);
    }

    private static Instant C(long j, int i) {
        if ((i | j) == 0) {
            return c;
        }
        if (j < -31557014167219200L || j > 31556889864403199L) {
            throw new C0063a("Instant exceeds minimum or maximum instant");
        }
        return new Instant(j, i);
    }

    private Instant(long j, int i) {
        this.a = j;
        this.b = i;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.INSTANT_SECONDS || rVar == j$.time.temporal.a.NANO_OF_SECOND || rVar == j$.time.temporal.a.MICRO_OF_SECOND || rVar == j$.time.temporal.a.MILLI_OF_SECOND : rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return j$.time.temporal.n.d(this, rVar).a(rVar.k(this), rVar);
        }
        int i = d.a[((j$.time.temporal.a) rVar).ordinal()];
        int i2 = this.b;
        if (i == 1) {
            return i2;
        }
        if (i == 2) {
            return i2 / 1000;
        }
        if (i == 3) {
            return i2 / 1000000;
        }
        if (i == 4) {
            j$.time.temporal.a.INSTANT_SECONDS.w(this.a);
        }
        throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        int i;
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i2 = d.a[((j$.time.temporal.a) rVar).ordinal()];
        int i3 = this.b;
        if (i2 == 1) {
            return i3;
        }
        if (i2 == 2) {
            i = i3 / 1000;
        } else {
            if (i2 != 3) {
                if (i2 == 4) {
                    return this.a;
                }
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
            }
            i = i3 / 1000000;
        }
        return i;
    }

    public final long D() {
        return this.a;
    }

    public final int E() {
        return this.b;
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (Instant) rVar.n(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = d.a[aVar.ordinal()];
        int i2 = this.b;
        long j2 = this.a;
        if (i != 1) {
            if (i == 2) {
                int i3 = ((int) j) * 1000;
                if (i3 != i2) {
                    return C(j2, i3);
                }
            } else if (i == 3) {
                int i4 = ((int) j) * 1000000;
                if (i4 != i2) {
                    return C(j2, i4);
                }
            } else {
                if (i != 4) {
                    throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
                }
                if (j != j2) {
                    return C(j, i2);
                }
            }
        } else if (j != i2) {
            return C(j2, (int) j);
        }
        return this;
    }

    @Override // j$.time.temporal.m
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public final Instant e(long j, j$.time.temporal.u uVar) {
        if (!(uVar instanceof j$.time.temporal.b)) {
            return (Instant) uVar.j(this, j);
        }
        switch (d.b[((j$.time.temporal.b) uVar).ordinal()]) {
            case 1:
                return H(0L, j);
            case 2:
                return H(j / 1000000, (j % 1000000) * 1000);
            case 3:
                return H(j / 1000, (j % 1000) * 1000000);
            case 4:
                return H(j, 0L);
            case 5:
                return H(j$.com.android.tools.r8.a.l(j, 60), 0L);
            case 6:
                return H(j$.com.android.tools.r8.a.l(j, 3600), 0L);
            case 7:
                return H(j$.com.android.tools.r8.a.l(j, 43200), 0L);
            case 8:
                return H(j$.com.android.tools.r8.a.l(j, 86400), 0L);
            default:
                throw new j$.time.temporal.v("Unsupported unit: " + uVar);
        }
    }

    private Instant H(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return G(j$.com.android.tools.r8.a.f(j$.com.android.tools.r8.a.f(this.a, j), j2 / 1000000000), this.b + (j2 % 1000000000));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.NANOS;
        }
        if (tVar == j$.time.temporal.n.e() || tVar == j$.time.temporal.n.k() || tVar == j$.time.temporal.n.j() || tVar == j$.time.temporal.n.h() || tVar == j$.time.temporal.n.f() || tVar == j$.time.temporal.n.g()) {
            return null;
        }
        return tVar.a(this);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(this.a, j$.time.temporal.a.INSTANT_SECONDS).d(this.b, j$.time.temporal.a.NANO_OF_SECOND);
    }

    public long toEpochMilli() {
        int i = this.b;
        long j = this.a;
        return (j >= 0 || i <= 0) ? j$.com.android.tools.r8.a.f(j$.com.android.tools.r8.a.l(j, 1000), i / 1000000) : j$.com.android.tools.r8.a.f(j$.com.android.tools.r8.a.l(j + 1, 1000), (i / 1000000) - 1000);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Instant)) {
            return false;
        }
        Instant instant = (Instant) obj;
        return this.a == instant.a && this.b == instant.b;
    }

    public final int hashCode() {
        long j = this.a;
        return (this.b * 51) + ((int) (j ^ (j >>> 32)));
    }

    public final String toString() {
        return j$.time.format.a.e.a(this);
    }

    private Object writeReplace() {
        return new s((byte) 2, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void J(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.a);
        dataOutput.writeInt(this.b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return (Instant) AbstractC0073i.a(fVar, this);
    }
}
