package j$.time;

import j$.time.chrono.AbstractC0073i;
import j$.time.chrono.InterfaceC0066b;
import j$.time.chrono.InterfaceC0069e;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import rikka.shizuku.ShizukuApiConstants;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class f implements j$.time.temporal.m, j$.time.temporal.p, InterfaceC0066b, Serializable {
    public static final f d = N(-999999999, 1, 1);
    public static final f e = N(999999999, 12, 31);
    private static final long serialVersionUID = 2942565459149668126L;
    private final int a;
    private final short b;
    private final short c;

    static {
        N(1970, 1, 1);
    }

    public static f O(int i, l lVar, int i2) {
        j$.time.temporal.a.YEAR.C(i);
        j$.time.temporal.a.DAY_OF_MONTH.C(i2);
        return D(i, lVar.getValue(), i2);
    }

    public static f N(int i, int i2, int i3) {
        j$.time.temporal.a.YEAR.C(i);
        j$.time.temporal.a.MONTH_OF_YEAR.C(i2);
        j$.time.temporal.a.DAY_OF_MONTH.C(i3);
        return D(i, i2, i3);
    }

    public static f P(long j) {
        long j2;
        j$.time.temporal.a.EPOCH_DAY.C(j);
        long j3 = 719468 + j;
        if (j3 < 0) {
            long j4 = ((j + 719469) / 146097) - 1;
            j2 = j4 * 400;
            j3 += (-j4) * 146097;
        } else {
            j2 = 0;
        }
        long j5 = ((j3 * 400) + 591) / 146097;
        long j6 = j3 - ((j5 / 400) + (((j5 / 4) + (j5 * 365)) - (j5 / 100)));
        if (j6 < 0) {
            j5--;
            j6 = j3 - ((j5 / 400) + (((j5 / 4) + (365 * j5)) - (j5 / 100)));
        }
        int i = (int) j6;
        int i2 = ((i * 5) + 2) / 153;
        return new f(j$.time.temporal.a.YEAR.w(j5 + j2 + (i2 / 10)), ((i2 + 2) % 12) + 1, (i - (((i2 * 306) + 5) / 10)) + 1);
    }

    public static f E(j$.time.temporal.o oVar) {
        Objects.a(oVar, "temporal");
        f fVar = (f) oVar.v(j$.time.temporal.n.f());
        if (fVar != null) {
            return fVar;
        }
        throw new C0063a("Unable to obtain LocalDate from TemporalAccessor: " + oVar + " of type " + oVar.getClass().getName());
    }

    private static f D(int i, int i2, int i3) {
        int i4 = 28;
        if (i3 > 28) {
            if (i2 != 2) {
                i4 = (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) ? 30 : 31;
            } else {
                j$.time.chrono.u.d.getClass();
                if (j$.time.chrono.u.m(i)) {
                    i4 = 29;
                }
            }
            if (i3 > i4) {
                if (i3 == 29) {
                    throw new C0063a("Invalid date 'February 29' as '" + i + "' is not a leap year");
                }
                throw new C0063a("Invalid date '" + l.F(i2).name() + " " + i3 + "'");
            }
        }
        return new f(i, i2, i3);
    }

    private static f U(int i, int i2, int i3) {
        if (i2 == 2) {
            j$.time.chrono.u.d.getClass();
            i3 = Math.min(i3, j$.time.chrono.u.m((long) i) ? 29 : 28);
        } else if (i2 == 4 || i2 == 6 || i2 == 9 || i2 == 11) {
            i3 = Math.min(i3, 30);
        }
        return new f(i, i2, i3);
    }

    private f(int i, int i2, int i3) {
        this.a = i;
        this.b = (short) i2;
        this.c = (short) i3;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return AbstractC0073i.h(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.s(this);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        if (!aVar.v()) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        int i = e.a[aVar.ordinal()];
        if (i == 1) {
            return j$.time.temporal.w.j(1L, M());
        }
        if (i == 2) {
            return j$.time.temporal.w.j(1L, L() ? 366 : 365);
        }
        if (i == 3) {
            return j$.time.temporal.w.j(1L, (l.F(this.b) != l.FEBRUARY || L()) ? 5L : 4L);
        }
        if (i != 4) {
            return ((j$.time.temporal.a) rVar).j();
        }
        return j$.time.temporal.w.j(1L, this.a <= 0 ? 1000000000L : 999999999L);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return F(rVar);
        }
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.EPOCH_DAY) {
                return t();
            }
            if (rVar != j$.time.temporal.a.PROLEPTIC_MONTH) {
                return F(rVar);
            }
            return ((this.a * 12) + this.b) - 1;
        }
        return rVar.k(this);
    }

    private int F(j$.time.temporal.r rVar) {
        int i;
        int i2 = e.a[((j$.time.temporal.a) rVar).ordinal()];
        short s = this.c;
        int i3 = this.a;
        switch (i2) {
            case 1:
                return s;
            case 2:
                return H();
            case 3:
                i = (s - 1) / 7;
                break;
            case 4:
                return i3 >= 1 ? i3 : 1 - i3;
            case 5:
                return G().getValue();
            case 6:
                i = (s - 1) % 7;
                break;
            case 7:
                return ((H() - 1) % 7) + 1;
            case 8:
                throw new j$.time.temporal.v("Invalid field 'EpochDay' for get() method, use getLong() instead");
            case 9:
                return ((H() - 1) / 7) + 1;
            case 10:
                return this.b;
            case 11:
                throw new j$.time.temporal.v("Invalid field 'ProlepticMonth' for get() method, use getLong() instead");
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                return i3;
            case 13:
                return i3 >= 1 ? 1 : 0;
            default:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        return i + 1;
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final j$.time.chrono.n a() {
        return j$.time.chrono.u.d;
    }

    public final int J() {
        return this.a;
    }

    public final int I() {
        return this.b;
    }

    public final int H() {
        return (l.F(this.b).C(L()) + this.c) - 1;
    }

    public final c G() {
        return c.C(((int) j$.com.android.tools.r8.a.j(t() + 3, 7)) + 1);
    }

    public final boolean L() {
        j$.time.chrono.u uVar = j$.time.chrono.u.d;
        long j = this.a;
        uVar.getClass();
        return j$.time.chrono.u.m(j);
    }

    public final int M() {
        short s = this.b;
        return s != 2 ? (s == 4 || s == 6 || s == 9 || s == 11) ? 30 : 31 : L() ? 29 : 28;
    }

    @Override // j$.time.temporal.m
    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public final f m(j$.time.temporal.p pVar) {
        if (pVar instanceof f) {
            return (f) pVar;
        }
        return (f) pVar.w(this);
    }

    @Override // j$.time.temporal.m
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public final f d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (f) rVar.n(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = e.a[aVar.ordinal()];
        short s = this.c;
        short s2 = this.b;
        int i2 = this.a;
        switch (i) {
            case 1:
                int i3 = (int) j;
                return s == i3 ? this : N(i2, s2, i3);
            case 2:
                return X((int) j);
            case 3:
                return R(j$.com.android.tools.r8.a.l(j - s(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH), 7));
            case 4:
                if (i2 < 1) {
                    j = 1 - j;
                }
                return Y((int) j);
            case 5:
                return R(j - G().getValue());
            case 6:
                return R(j - s(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 7:
                return R(j - s(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 8:
                return P(j);
            case 9:
                return R(j$.com.android.tools.r8.a.l(j - s(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR), 7));
            case 10:
                int i4 = (int) j;
                if (s2 == i4) {
                    return this;
                }
                j$.time.temporal.a.MONTH_OF_YEAR.C(i4);
                return U(i2, i4, s);
            case 11:
                return S(j - (((i2 * 12) + s2) - 1));
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                return Y((int) j);
            case 13:
                return s(j$.time.temporal.a.ERA) == j ? this : Y(1 - i2);
            default:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
    }

    public final f Y(int i) {
        if (this.a == i) {
            return this;
        }
        j$.time.temporal.a.YEAR.C(i);
        return U(i, this.b, this.c);
    }

    public final f X(int i) {
        if (H() == i) {
            return this;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.YEAR;
        int i2 = this.a;
        long j = i2;
        aVar.C(j);
        j$.time.temporal.a.DAY_OF_YEAR.C(i);
        j$.time.chrono.u.d.getClass();
        boolean zM = j$.time.chrono.u.m(j);
        if (i == 366 && !zM) {
            throw new C0063a("Invalid date 'DayOfYear 366' as '" + i2 + "' is not a leap year");
        }
        l lVarF = l.F(((i - 1) / 31) + 1);
        if (i > (lVarF.D(zM) + lVarF.C(zM)) - 1) {
            lVarF = lVarF.G();
        }
        return new f(i2, lVarF.getValue(), (i - lVarF.C(zM)) + 1);
    }

    @Override // j$.time.temporal.m
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public final f e(long j, j$.time.temporal.u uVar) {
        if (!(uVar instanceof j$.time.temporal.b)) {
            return (f) uVar.j(this, j);
        }
        switch (e.b[((j$.time.temporal.b) uVar).ordinal()]) {
            case 1:
                return R(j);
            case 2:
                return R(j$.com.android.tools.r8.a.l(j, 7));
            case 3:
                return S(j);
            case 4:
                return T(j);
            case 5:
                return T(j$.com.android.tools.r8.a.l(j, 10));
            case 6:
                return T(j$.com.android.tools.r8.a.l(j, 100));
            case 7:
                return T(j$.com.android.tools.r8.a.l(j, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return d(j$.com.android.tools.r8.a.f(s(aVar), j), aVar);
            default:
                throw new j$.time.temporal.v("Unsupported unit: " + uVar);
        }
    }

    public final f T(long j) {
        return j == 0 ? this : U(j$.time.temporal.a.YEAR.w(this.a + j), this.b, this.c);
    }

    public final f S(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.a * 12) + (this.b - 1) + j;
        long j3 = 12;
        return U(j$.time.temporal.a.YEAR.w(j$.com.android.tools.r8.a.k(j2, j3)), ((int) j$.com.android.tools.r8.a.j(j2, j3)) + 1, this.c);
    }

    public final f R(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = this.c + j;
        if (j2 > 0) {
            short s = this.b;
            int i = this.a;
            if (j2 <= 28) {
                return new f(i, s, (int) j2);
            }
            if (j2 <= 59) {
                long jM = M();
                if (j2 <= jM) {
                    return new f(i, s, (int) j2);
                }
                if (s < 12) {
                    return new f(i, s + 1, (int) (j2 - jM));
                }
                int i2 = i + 1;
                j$.time.temporal.a.YEAR.C(i2);
                return new f(i2, 1, (int) (j2 - jM));
            }
        }
        return P(j$.com.android.tools.r8.a.f(t(), j));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        return tVar == j$.time.temporal.n.f() ? this : AbstractC0073i.j(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return AbstractC0073i.a(this, mVar);
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final InterfaceC0069e u(j jVar) {
        return h.K(this, jVar);
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final long t() {
        long j = this.a;
        long j2 = this.b;
        long j3 = 365 * j;
        long j4 = (((367 * j2) - 362) / 12) + (j >= 0 ? ((j + 399) / 400) + (((3 + j) / 4) - ((99 + j) / 100)) + j3 : j3 - ((j / (-400)) + ((j / (-4)) - (j / (-100))))) + (this.c - 1);
        if (j2 > 2) {
            j4 = !L() ? j4 - 2 : j4 - 1;
        }
        return j4 - 719528;
    }

    @Override // java.lang.Comparable
    /* renamed from: z, reason: merged with bridge method [inline-methods] */
    public final int compareTo(InterfaceC0066b interfaceC0066b) {
        if (interfaceC0066b instanceof f) {
            return C((f) interfaceC0066b);
        }
        return AbstractC0073i.b(this, interfaceC0066b);
    }

    final int C(f fVar) {
        int i = this.a - fVar.a;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - fVar.b;
        return i2 == 0 ? this.c - fVar.c : i2;
    }

    public final boolean K(f fVar) {
        return fVar instanceof f ? C(fVar) < 0 : t() < fVar.t();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f) && C((f) obj) == 0;
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final int hashCode() {
        int i = this.a;
        return (((i << 11) + (this.b << 6)) + this.c) ^ (i & (-2048));
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public final String toString() {
        int i = this.a;
        int iAbs = Math.abs(i);
        StringBuilder sb = new StringBuilder(10);
        if (iAbs >= 1000) {
            if (i > 9999) {
                sb.append('+');
            }
            sb.append(i);
        } else if (i < 0) {
            sb.append(i - 10000);
            sb.deleteCharAt(1);
        } else {
            sb.append(i + 10000);
            sb.deleteCharAt(0);
        }
        short s = this.b;
        sb.append(s < 10 ? "-0" : "-");
        sb.append((int) s);
        short s2 = this.c;
        sb.append(s2 < 10 ? "-0" : "-");
        sb.append((int) s2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new s((byte) 3, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void Z(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.a);
        dataOutput.writeByte(this.b);
        dataOutput.writeByte(this.c);
    }
}
