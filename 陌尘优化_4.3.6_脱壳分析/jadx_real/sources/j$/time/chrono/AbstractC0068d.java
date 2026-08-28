package j$.time.chrono;

import j$.time.AbstractC0064b;
import java.io.Serializable;

/* renamed from: j$.time.chrono.d, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
abstract class AbstractC0068d implements InterfaceC0066b, j$.time.temporal.m, j$.time.temporal.p, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    abstract InterfaceC0066b F(long j);

    abstract InterfaceC0066b G(long j);

    abstract InterfaceC0066b H(long j);

    @Override // j$.time.chrono.InterfaceC0066b, j$.time.temporal.o
    public /* synthetic */ boolean f(j$.time.temporal.r rVar) {
        return AbstractC0073i.h(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ int k(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public /* synthetic */ j$.time.temporal.w n(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ Object v(j$.time.temporal.t tVar) {
        return AbstractC0073i.j(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final /* synthetic */ j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return AbstractC0073i.a(this, mVar);
    }

    @Override // java.lang.Comparable
    /* renamed from: z */
    public final /* synthetic */ int compareTo(InterfaceC0066b interfaceC0066b) {
        return AbstractC0073i.b(this, interfaceC0066b);
    }

    static InterfaceC0066b C(n nVar, j$.time.temporal.m mVar) {
        InterfaceC0066b interfaceC0066b = (InterfaceC0066b) mVar;
        if (nVar.equals(interfaceC0066b.a())) {
            return interfaceC0066b;
        }
        throw new ClassCastException("Chronology mismatch, expected: " + nVar.i() + ", actual: " + interfaceC0066b.a().i());
    }

    AbstractC0068d() {
    }

    @Override // j$.time.temporal.m
    public InterfaceC0066b e(long j, j$.time.temporal.u uVar) {
        boolean z = uVar instanceof j$.time.temporal.b;
        if (!z) {
            if (!z) {
                return C(a(), uVar.j(this, j));
            }
            throw new j$.time.temporal.v("Unsupported unit: " + uVar);
        }
        switch (AbstractC0067c.a[((j$.time.temporal.b) uVar).ordinal()]) {
            case 1:
                return F(j);
            case 2:
                return F(j$.com.android.tools.r8.a.l(j, 7));
            case 3:
                return G(j);
            case 4:
                return H(j);
            case 5:
                return H(j$.com.android.tools.r8.a.l(j, 10));
            case 6:
                return H(j$.com.android.tools.r8.a.l(j, 100));
            case 7:
                return H(j$.com.android.tools.r8.a.l(j, 1000));
            case 8:
                j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
                return d(j$.com.android.tools.r8.a.f(s(aVar), j), (j$.time.temporal.r) aVar);
            default:
                throw new j$.time.temporal.v("Unsupported unit: " + uVar);
        }
    }

    public o D() {
        return a().A(j$.time.temporal.n.a(this, j$.time.temporal.a.ERA));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC0066b) && AbstractC0073i.b(this, (InterfaceC0066b) obj) == 0;
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public int hashCode() {
        long jT = t();
        return a().hashCode() ^ ((int) (jT ^ (jT >>> 32)));
    }

    @Override // j$.time.temporal.m
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public InterfaceC0066b m(j$.time.temporal.p pVar) {
        return C(a(), pVar.w(this));
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public String toString() {
        long jS = s(j$.time.temporal.a.YEAR_OF_ERA);
        long jS2 = s(j$.time.temporal.a.MONTH_OF_YEAR);
        long jS3 = s(j$.time.temporal.a.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(a().toString());
        sb.append(" ");
        sb.append(D());
        sb.append(" ");
        sb.append(jS);
        sb.append(jS2 < 10 ? "-0" : "-");
        sb.append(jS2);
        sb.append(jS3 < 10 ? "-0" : "-");
        sb.append(jS3);
        return sb.toString();
    }

    @Override // j$.time.temporal.m
    public InterfaceC0066b d(long j, j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        return C(a(), rVar.n(this, j));
    }

    @Override // j$.time.temporal.m
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public InterfaceC0066b j(long j, j$.time.temporal.u uVar) {
        return C(a(), j$.time.temporal.n.b(this, j, uVar));
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public InterfaceC0069e u(j$.time.j jVar) {
        return C0071g.D(this, jVar);
    }

    @Override // j$.time.chrono.InterfaceC0066b
    public long t() {
        return s(j$.time.temporal.a.EPOCH_DAY);
    }
}
