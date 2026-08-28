package j$.time.chrono;

import j$.time.C0063a;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class C extends AbstractC0065a implements Serializable {
    public static final C d = new C();
    private static final long serialVersionUID = 1039765215346859963L;

    private C() {
    }

    @Override // j$.time.chrono.n
    public final String i() {
        return "Minguo";
    }

    @Override // j$.time.chrono.n
    public final o A(int i) {
        if (i == 0) {
            return F.BEFORE_ROC;
        }
        if (i == 1) {
            return F.ROC;
        }
        throw new C0063a("Invalid era: " + i);
    }

    @Override // j$.time.chrono.n
    public final String r() {
        return "roc";
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0066b l(j$.time.temporal.o oVar) {
        if (oVar instanceof E) {
            return (E) oVar;
        }
        return new E(j$.time.f.E(oVar));
    }

    public final j$.time.temporal.w m(j$.time.temporal.a aVar) {
        int i = B.a[aVar.ordinal()];
        if (i == 1) {
            j$.time.temporal.w wVarJ = j$.time.temporal.a.PROLEPTIC_MONTH.j();
            return j$.time.temporal.w.j(wVarJ.e() - 22932, wVarJ.d() - 22932);
        }
        if (i == 2) {
            j$.time.temporal.w wVarJ2 = j$.time.temporal.a.YEAR.j();
            return j$.time.temporal.w.k(wVarJ2.d() - 1911, (-wVarJ2.e()) + 1912);
        }
        if (i == 3) {
            j$.time.temporal.w wVarJ3 = j$.time.temporal.a.YEAR.j();
            return j$.time.temporal.w.j(wVarJ3.e() - 1911, wVarJ3.d() - 1911);
        }
        return aVar.j();
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    Object writeReplace() {
        return new G((byte) 1, this);
    }
}
