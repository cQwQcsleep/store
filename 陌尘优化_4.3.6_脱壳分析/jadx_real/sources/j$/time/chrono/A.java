package j$.time.chrono;

import com.shadow.okhttp3.internal.http2.Http2Connection;
import j$.time.C0063a;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class A implements o, Serializable {
    public static final A d;
    private static final A[] e;
    private static final long serialVersionUID = 1466499369062886794L;
    private final transient int a;
    private final transient j$.time.f b;
    private final transient String c;

    @Override // j$.time.temporal.o
    public final /* synthetic */ boolean f(j$.time.temporal.r rVar) {
        return AbstractC0073i.i(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ int k(j$.time.temporal.r rVar) {
        return AbstractC0073i.f(this, (j$.time.temporal.a) rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ long s(j$.time.temporal.r rVar) {
        return AbstractC0073i.g(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final /* synthetic */ Object v(j$.time.temporal.t tVar) {
        return AbstractC0073i.m(this, tVar);
    }

    static {
        A a = new A(-1, j$.time.f.N(1868, 1, 1), "Meiji");
        d = a;
        e = new A[]{a, new A(0, j$.time.f.N(1912, 7, 30), "Taisho"), new A(1, j$.time.f.N(1926, 12, 25), "Showa"), new A(2, j$.time.f.N(1989, 1, 8), "Heisei"), new A(3, j$.time.f.N(2019, 5, 1), "Reiwa")};
    }

    static A l() {
        return e[r0.length - 1];
    }

    static long D() {
        int iJ = Http2Connection.DEGRADED_PONG_TIMEOUT_NS - l().b.J();
        A[] aArr = e;
        int iJ2 = aArr[0].b.J();
        for (int i = 1; i < aArr.length; i++) {
            A a = aArr[i];
            iJ = Math.min(iJ, (a.b.J() - iJ2) + 1);
            iJ2 = a.b.J();
        }
        return iJ;
    }

    static long C() {
        long jF = j$.time.temporal.a.DAY_OF_YEAR.j().f();
        for (A a : e) {
            jF = Math.min(jF, ((a.b.L() ? 366 : 365) - a.b.H()) + 1);
            if (a.r() != null) {
                jF = Math.min(jF, a.r().b.H() - 1);
            }
        }
        return jF;
    }

    private A(int i, j$.time.f fVar, String str) {
        this.a = i;
        this.b = fVar;
        this.c = str;
    }

    final j$.time.f o() {
        return this.b;
    }

    public static A A(int i) {
        int i2 = i + 1;
        if (i2 >= 0) {
            A[] aArr = e;
            if (i2 < aArr.length) {
                return aArr[i2];
            }
        }
        throw new C0063a("Invalid era: " + i);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(getValue(), j$.time.temporal.a.ERA);
    }

    static A i(j$.time.f fVar) {
        if (fVar.K(z.d)) {
            throw new C0063a("JapaneseDate before Meiji 6 are not supported");
        }
        A[] aArr = e;
        for (int length = aArr.length - 1; length >= 0; length--) {
            A a = aArr[length];
            if (fVar.compareTo(a.b) >= 0) {
                return a;
            }
        }
        return null;
    }

    @Override // j$.time.chrono.o
    public final int getValue() {
        return this.a;
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
        if (rVar != aVar) {
            return j$.time.temporal.n.d(this, rVar);
        }
        return x.d.m(aVar);
    }

    final A r() {
        if (this == l()) {
            return null;
        }
        return A(this.a + 1);
    }

    public final String toString() {
        return this.c;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new G((byte) 5, this);
    }

    final void E(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(this.a);
    }
}
