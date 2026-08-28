package j$.time.chrono;

import j$.time.C0063a;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class I extends AbstractC0065a implements Serializable {
    public static final I d = new I();
    private static final long serialVersionUID = 2775954514031616474L;

    static {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map.put("en", new String[]{"BB", "BE"});
        map.put("th", new String[]{"BB", "BE"});
        map2.put("en", new String[]{"B.B.", "B.E."});
        map2.put("th", new String[]{"พ.ศ.", "ปีก่อนคริสต์กาลที่"});
        map3.put("en", new String[]{"Before Buddhist", "Budhhist Era"});
        map3.put("th", new String[]{"พุทธศักราช", "ปีก่อนคริสต์กาลที่"});
    }

    @Override // j$.time.chrono.n
    public final o A(int i) {
        if (i == 0) {
            return L.BEFORE_BE;
        }
        if (i == 1) {
            return L.BE;
        }
        throw new C0063a("Invalid era: " + i);
    }

    private I() {
    }

    @Override // j$.time.chrono.n
    public final String i() {
        return "ThaiBuddhist";
    }

    @Override // j$.time.chrono.n
    public final String r() {
        return "buddhist";
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0066b l(j$.time.temporal.o oVar) {
        if (oVar instanceof K) {
            return (K) oVar;
        }
        return new K(j$.time.f.E(oVar));
    }

    public final j$.time.temporal.w m(j$.time.temporal.a aVar) {
        int i = H.a[aVar.ordinal()];
        if (i == 1) {
            j$.time.temporal.w wVarJ = j$.time.temporal.a.PROLEPTIC_MONTH.j();
            return j$.time.temporal.w.j(wVarJ.e() + 6516, wVarJ.d() + 6516);
        }
        if (i == 2) {
            j$.time.temporal.w wVarJ2 = j$.time.temporal.a.YEAR.j();
            return j$.time.temporal.w.k((-(wVarJ2.e() + 543)) + 1, wVarJ2.d() + 543);
        }
        if (i == 3) {
            j$.time.temporal.w wVarJ3 = j$.time.temporal.a.YEAR.j();
            return j$.time.temporal.w.j(wVarJ3.e() + 543, wVarJ3.d() + 543);
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
