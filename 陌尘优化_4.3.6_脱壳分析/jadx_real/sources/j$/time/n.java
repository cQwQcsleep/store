package j$.time;

import j$.time.chrono.AbstractC0073i;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class n implements j$.time.temporal.o, j$.time.temporal.p, Comparable, Serializable {
    private static final long serialVersionUID = -939150713474957432L;
    private final int a;
    private final int b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        n nVar = (n) obj;
        int i = this.a - nVar.a;
        return i == 0 ? this.b - nVar.b : i;
    }

    static {
        j$.time.format.o oVar = new j$.time.format.o();
        oVar.f("--");
        oVar.k(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        oVar.e('-');
        oVar.k(j$.time.temporal.a.DAY_OF_MONTH, 2);
        oVar.v();
    }

    private n(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.MONTH_OF_YEAR || rVar == j$.time.temporal.a.DAY_OF_MONTH : rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.MONTH_OF_YEAR) {
            return rVar.j();
        }
        if (rVar != j$.time.temporal.a.DAY_OF_MONTH) {
            return j$.time.temporal.n.d(this, rVar);
        }
        l lVarF = l.F(this.a);
        lVarF.getClass();
        int i = k.a[lVarF.ordinal()];
        return j$.time.temporal.w.k(i != 1 ? (i == 2 || i == 3 || i == 4 || i == 5) ? 30 : 31 : 28, l.F(r5).E());
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        return n(rVar).a(s(rVar), rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        int i;
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i2 = m.a[((j$.time.temporal.a) rVar).ordinal()];
        if (i2 == 1) {
            i = this.b;
        } else {
            if (i2 != 2) {
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
            }
            i = this.a;
        }
        return i;
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.e()) {
            return j$.time.chrono.u.d;
        }
        return j$.time.temporal.n.c(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        if (!AbstractC0073i.p(mVar).equals(j$.time.chrono.u.d)) {
            throw new C0063a("Adjustment only supported on ISO date-time");
        }
        j$.time.temporal.m mVarD = mVar.d(this.a, j$.time.temporal.a.MONTH_OF_YEAR);
        j$.time.temporal.a aVar = j$.time.temporal.a.DAY_OF_MONTH;
        return mVarD.d(Math.min(mVarD.n(aVar).d(), this.b), aVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.a == nVar.a && this.b == nVar.b;
    }

    public final int hashCode() {
        return (this.a << 6) + this.b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("--");
        int i = this.a;
        sb.append(i < 10 ? "0" : "");
        sb.append(i);
        int i2 = this.b;
        sb.append(i2 < 10 ? "-0" : "-");
        sb.append(i2);
        return sb.toString();
    }

    private Object writeReplace() {
        return new s((byte) 13, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void D(DataOutput dataOutput) {
        dataOutput.writeByte(this.a);
        dataOutput.writeByte(this.b);
    }

    static n C(ObjectInput objectInput) throws IOException {
        byte b = objectInput.readByte();
        byte b2 = objectInput.readByte();
        l lVarF = l.F(b);
        Objects.a(lVarF, "month");
        j$.time.temporal.a.DAY_OF_MONTH.C(b2);
        if (b2 > lVarF.E()) {
            throw new C0063a("Illegal value for DayOfMonth field, value " + ((int) b2) + " is not valid for month " + lVarF.name());
        }
        return new n(lVarF.getValue(), b2);
    }
}
