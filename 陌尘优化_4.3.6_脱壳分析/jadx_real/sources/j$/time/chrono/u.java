package j$.time.chrono;

import j$.time.C0063a;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class u extends AbstractC0065a implements Serializable {
    public static final u d = new u();
    private static final long serialVersionUID = -1440403870442975015L;

    @Override // j$.time.chrono.n
    public final o A(int i) {
        if (i == 0) {
            return v.BCE;
        }
        if (i == 1) {
            return v.CE;
        }
        throw new C0063a("Invalid era: " + i);
    }

    private u() {
    }

    @Override // j$.time.chrono.n
    public final String i() {
        return "ISO";
    }

    @Override // j$.time.chrono.n
    public final String r() {
        return "iso8601";
    }

    @Override // j$.time.chrono.n
    public final InterfaceC0066b l(j$.time.temporal.o oVar) {
        return j$.time.f.E(oVar);
    }

    @Override // j$.time.chrono.AbstractC0065a, j$.time.chrono.n
    public final InterfaceC0069e o(j$.time.h hVar) {
        return j$.time.h.D(hVar);
    }

    public static boolean m(long j) {
        return (3 & j) == 0 && (j % 100 != 0 || j % 400 == 0);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    Object writeReplace() {
        return new G((byte) 1, this);
    }
}
