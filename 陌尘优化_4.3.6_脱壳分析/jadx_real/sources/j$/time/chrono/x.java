package j$.time.chrono;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class x extends AbstractC0065a implements Serializable {
    public static final x d = new x();
    private static final long serialVersionUID = 459996390165777884L;

    private x() {
    }

    @Override // j$.time.chrono.n
    public final String i() {
        return "Japanese";
    }

    @Override // j$.time.chrono.n
    public final String r() {
        return "japanese";
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0066b l(j$.time.temporal.o oVar) {
        if (oVar instanceof z) {
            return (z) oVar;
        }
        return new z(j$.time.f.E(oVar));
    }

    @Override // j$.time.chrono.n
    public final o A(int i) {
        return A.A(i);
    }

    public final j$.time.temporal.w m(j$.time.temporal.a aVar) {
        switch (w.a[aVar.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                throw new j$.time.temporal.v("Unsupported field: " + aVar);
            case 5:
                return j$.time.temporal.w.k(A.D(), 999999999 - A.l().o().J());
            case 6:
                return j$.time.temporal.w.k(A.C(), j$.time.temporal.a.DAY_OF_YEAR.j().d());
            case 7:
                return j$.time.temporal.w.j(z.d.J(), 999999999L);
            case 8:
                return j$.time.temporal.w.j(A.d.getValue(), A.l().getValue());
            default:
                return aVar.j();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    Object writeReplace() {
        return new G((byte) 1, this);
    }
}
