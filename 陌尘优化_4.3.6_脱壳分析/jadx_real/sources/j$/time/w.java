package j$.time;

import j$.time.chrono.AbstractC0073i;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class w implements j$.time.temporal.m, j$.time.temporal.p, Comparable, Serializable {
    private static final long serialVersionUID = 4183400860270640070L;
    private final int a;
    private final int b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        w wVar = (w) obj;
        int i = this.a - wVar.a;
        return i == 0 ? this.b - wVar.b : i;
    }

    static {
        j$.time.format.o oVar = new j$.time.format.o();
        oVar.l(j$.time.temporal.a.YEAR, 4, 10, j$.time.format.v.EXCEEDS_PAD);
        oVar.e('-');
        oVar.k(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        oVar.v();
    }

    private w(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private w H(int i, int i2) {
        return (this.a == i && this.b == i2) ? this : new w(i, i2);
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.YEAR || rVar == j$.time.temporal.a.MONTH_OF_YEAR || rVar == j$.time.temporal.a.PROLEPTIC_MONTH || rVar == j$.time.temporal.a.YEAR_OF_ERA || rVar == j$.time.temporal.a.ERA : rVar != null && rVar.m(this);
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
        int i = v.a[((j$.time.temporal.a) rVar).ordinal()];
        if (i == 1) {
            return this.b;
        }
        if (i == 2) {
            return C();
        }
        int i2 = this.a;
        if (i == 3) {
            if (i2 < 1) {
                i2 = 1 - i2;
            }
            return i2;
        }
        if (i == 4) {
            return i2;
        }
        if (i == 5) {
            return i2 < 1 ? 0 : 1;
        }
        throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
    }

    private long C() {
        return ((this.a * 12) + this.b) - 1;
    }

    @Override // j$.time.temporal.m
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public final w d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (w) rVar.n(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = v.a[aVar.ordinal()];
        int i2 = this.a;
        if (i == 1) {
            int i3 = (int) j;
            j$.time.temporal.a.MONTH_OF_YEAR.C(i3);
            return H(i2, i3);
        }
        if (i == 2) {
            return E(j - C());
        }
        int i4 = this.b;
        if (i == 3) {
            if (i2 < 1) {
                j = 1 - j;
            }
            int i5 = (int) j;
            j$.time.temporal.a.YEAR.C(i5);
            return H(i5, i4);
        }
        if (i == 4) {
            int i6 = (int) j;
            j$.time.temporal.a.YEAR.C(i6);
            return H(i6, i4);
        }
        if (i != 5) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        if (s(j$.time.temporal.a.ERA) == j) {
            return this;
        }
        int i7 = 1 - i2;
        j$.time.temporal.a.YEAR.C(i7);
        return H(i7, i4);
    }

    @Override // j$.time.temporal.m
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public final w e(long j, j$.time.temporal.u uVar) {
        if (!(uVar instanceof j$.time.temporal.b)) {
            return (w) uVar.j(this, j);
        }
        switch (v.b[((j$.time.temporal.b) uVar).ordinal()]) {
            case 1:
                return E(j);
            case 2:
                return F(j);
            case 3:
                return F(j$.com.android.tools.r8.a.l(j, 10));
            case 4:
                return F(j$.com.android.tools.r8.a.l(j, 100));
            case 5:
                return F(j$.com.android.tools.r8.a.l(j, 1000));
            case 6:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return d(j$.com.android.tools.r8.a.f(s(aVar), j), aVar);
            default:
                throw new j$.time.temporal.v("Unsupported unit: " + uVar);
        }
    }

    public final w F(long j) {
        return j == 0 ? this : H(j$.time.temporal.a.YEAR.w(this.a + j), this.b);
    }

    public final w E(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.a * 12) + (this.b - 1) + j;
        long j3 = 12;
        return H(j$.time.temporal.a.YEAR.w(j$.com.android.tools.r8.a.k(j2, j3)), ((int) j$.com.android.tools.r8.a.j(j2, j3)) + 1);
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
            return j$.time.temporal.b.MONTHS;
        }
        return j$.time.temporal.n.c(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        if (!AbstractC0073i.p(mVar).equals(j$.time.chrono.u.d)) {
            throw new C0063a("Adjustment only supported on ISO date-time");
        }
        return mVar.d(C(), j$.time.temporal.a.PROLEPTIC_MONTH);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return this.a == wVar.a && this.b == wVar.b;
    }

    public final int hashCode() {
        return (this.b << 27) ^ this.a;
    }

    public final String toString() {
        int i = this.a;
        int iAbs = Math.abs(i);
        StringBuilder sb = new StringBuilder(9);
        if (iAbs >= 1000) {
            sb.append(i);
        } else if (i < 0) {
            sb.append(i - 10000);
            sb.deleteCharAt(1);
        } else {
            sb.append(i + 10000);
            sb.deleteCharAt(0);
        }
        int i2 = this.b;
        sb.append(i2 < 10 ? "-0" : "-");
        sb.append(i2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new s((byte) 12, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void J(DataOutput dataOutput) {
        dataOutput.writeInt(this.a);
        dataOutput.writeByte(this.b);
    }

    static w G(ObjectInput objectInput) throws IOException {
        int i = objectInput.readInt();
        byte b = objectInput.readByte();
        j$.time.temporal.a.YEAR.C(i);
        j$.time.temporal.a.MONTH_OF_YEAR.C(b);
        return new w(i, b);
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return (w) AbstractC0073i.a(fVar, this);
    }
}
