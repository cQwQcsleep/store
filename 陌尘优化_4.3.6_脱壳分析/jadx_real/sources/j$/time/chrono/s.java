package j$.time.chrono;

import j$.time.AbstractC0064b;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import rikka.shizuku.ShizukuApiConstants;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class s extends AbstractC0068d {
    private static final long serialVersionUID = -5207853542612002020L;
    private final transient q a;
    private final transient int b;
    private final transient int c;
    private final transient int d;

    @Override // j$.time.chrono.InterfaceC0066b
    public final n a() {
        return this.a;
    }

    static s K(q qVar, int i, int i2, int i3) {
        return new s(qVar, i, i2, i3);
    }

    static s L(q qVar, long j) {
        return new s(qVar, j);
    }

    private s(q qVar, int i, int i2, int i3) {
        qVar.w(i, i2, i3);
        this.a = qVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    private s(q qVar, long j) {
        int[] iArrC = qVar.C((int) j);
        this.a = qVar;
        this.b = iArrC[0];
        this.c = iArrC[1];
        this.d = iArrC[2];
    }

    @Override // j$.time.chrono.AbstractC0068d
    public final o D() {
        return t.AH;
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
        int i = r.a[aVar.ordinal()];
        int i2 = this.b;
        return i != 1 ? i != 2 ? i != 3 ? this.a.G(aVar) : j$.time.temporal.w.j(1L, 5L) : j$.time.temporal.w.j(1L, r3.E(i2)) : j$.time.temporal.w.j(1L, r3.D(i2, this.c));
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return rVar.k(this);
        }
        int i = r.a[((j$.time.temporal.a) rVar).ordinal()];
        int i2 = this.c;
        int i3 = this.d;
        int i4 = this.b;
        switch (i) {
            case 1:
                return i3;
            case 2:
                return J();
            case 3:
                return ((i3 - 1) / 7) + 1;
            case 4:
                return ((int) j$.com.android.tools.r8.a.j(t() + 3, 7)) + 1;
            case 5:
                return ((i3 - 1) % 7) + 1;
            case 6:
                return ((J() - 1) % 7) + 1;
            case 7:
                return t();
            case 8:
                return ((J() - 1) / 7) + 1;
            case 9:
                return i2;
            case 10:
                return ((i4 * 12) + i2) - 1;
            case 11:
                return i4;
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                return i4;
            case 13:
                return i4 <= 1 ? 0 : 1;
            default:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public final s d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (s) super.d(j, rVar);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        q qVar = this.a;
        qVar.G(aVar).b(j, aVar);
        int i = (int) j;
        int i2 = r.a[aVar.ordinal()];
        int i3 = this.d;
        int i4 = this.c;
        int i5 = this.b;
        switch (i2) {
            case 1:
                return O(i5, i4, i);
            case 2:
                return F(Math.min(i, qVar.E(i5)) - J());
            case 3:
                return F((j - s(j$.time.temporal.a.ALIGNED_WEEK_OF_MONTH)) * 7);
            case 4:
                return F(j - (((int) j$.com.android.tools.r8.a.j(t() + 3, 7)) + 1));
            case 5:
                return F(j - s(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 6:
                return F(j - s(j$.time.temporal.a.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 7:
                return new s(qVar, j);
            case 8:
                return F((j - s(j$.time.temporal.a.ALIGNED_WEEK_OF_YEAR)) * 7);
            case 9:
                return O(i5, i, i3);
            case 10:
                return G(j - (((i5 * 12) + i4) - 1));
            case 11:
                if (i5 < 1) {
                    i = 1 - i;
                }
                return O(i, i4, i3);
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                return O(i, i4, i3);
            case 13:
                return O(1 - i5, i4, i3);
            default:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
    }

    private s O(int i, int i2, int i3) {
        q qVar = this.a;
        int iD = qVar.D(i, i2);
        if (i3 > iD) {
            i3 = iD;
        }
        return new s(qVar, i, i2, i3);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: I */
    public final InterfaceC0066b m(j$.time.temporal.p pVar) {
        return (s) super.m(pVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m m(j$.time.f fVar) {
        return (s) super.m(fVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final long t() {
        return this.a.w(this.b, this.c, this.d);
    }

    private int J() {
        return this.a.v(this.b, this.c) + this.d;
    }

    @Override // j$.time.chrono.AbstractC0068d
    final InterfaceC0066b H(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = this.b + ((int) j);
        int i = (int) j2;
        if (j2 == i) {
            return O(i, this.c, this.d);
        }
        throw new ArithmeticException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: N, reason: merged with bridge method [inline-methods] */
    public final s G(long j) {
        if (j == 0) {
            return this;
        }
        long j2 = (this.b * 12) + (this.c - 1) + j;
        return O(this.a.n(j$.com.android.tools.r8.a.k(j2, 12L)), ((int) j$.com.android.tools.r8.a.j(j2, 12L)) + 1, this.d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: M, reason: merged with bridge method [inline-methods] */
    public final s F(long j) {
        return new s(this.a, t() + j);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b, j$.time.temporal.m
    public final InterfaceC0066b e(long j, j$.time.temporal.u uVar) {
        return (s) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m e(long j, j$.time.temporal.u uVar) {
        return (s) super.e(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    /* renamed from: E */
    public final InterfaceC0066b j(long j, j$.time.temporal.u uVar) {
        return (s) super.j(j, uVar);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return (s) super.j(j, bVar);
    }

    @Override // j$.time.chrono.AbstractC0068d
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.b == sVar.b && this.c == sVar.c && this.d == sVar.d && this.a.equals(sVar.a);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final int hashCode() {
        int iHashCode = this.a.i().hashCode();
        int i = this.b;
        return (iHashCode ^ (i & (-2048))) ^ (((i << 11) + (this.c << 6)) + this.d);
    }

    @Override // j$.time.chrono.AbstractC0068d, j$.time.chrono.InterfaceC0066b
    public final InterfaceC0069e u(j$.time.j jVar) {
        return C0071g.D(this, jVar);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 6, this);
    }

    final void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.a);
        objectOutput.writeInt(j$.time.temporal.n.a(this, j$.time.temporal.a.YEAR));
        objectOutput.writeByte(j$.time.temporal.n.a(this, j$.time.temporal.a.MONTH_OF_YEAR));
        objectOutput.writeByte(j$.time.temporal.n.a(this, j$.time.temporal.a.DAY_OF_MONTH));
    }
}
