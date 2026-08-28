package j$.time.chrono;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* renamed from: j$.time.chrono.h, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class C0072h implements Serializable {
    public static final /* synthetic */ int e = 0;
    private static final long serialVersionUID = 57387258289L;
    private final n a;
    final int b;
    final int c;
    final int d;

    static {
        j$.com.android.tools.r8.a.h(new Object[]{j$.time.temporal.b.YEARS, j$.time.temporal.b.MONTHS, j$.time.temporal.b.DAYS});
    }

    C0072h(n nVar, int i, int i2, int i3) {
        this.a = nVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public final String toString() {
        n nVar = this.a;
        int i = this.d;
        int i2 = this.c;
        int i3 = this.b;
        if (i3 == 0 && i2 == 0 && i == 0) {
            return nVar.toString() + " P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nVar.toString());
        sb.append(" P");
        if (i3 != 0) {
            sb.append(i3);
            sb.append('Y');
        }
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        if (i != 0) {
            sb.append(i);
            sb.append('D');
        }
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0072h)) {
            return false;
        }
        C0072h c0072h = (C0072h) obj;
        return this.b == c0072h.b && this.c == c0072h.c && this.d == c0072h.d && this.a.equals(c0072h.a);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ (Integer.rotateLeft(this.d, 16) + (Integer.rotateLeft(this.c, 8) + this.b));
    }

    protected Object writeReplace() {
        return new G((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.a.i());
        objectOutput.writeInt(this.b);
        objectOutput.writeInt(this.c);
        objectOutput.writeInt(this.d);
    }
}
