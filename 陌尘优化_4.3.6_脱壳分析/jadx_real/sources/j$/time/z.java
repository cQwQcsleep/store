package j$.time;

import j$.util.Objects;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* loaded from: /workspace/unpacked/classes3.dex */
final class z extends x {
    public static final /* synthetic */ int c = 0;
    private static final long serialVersionUID = 8386373296231747096L;
    private final String a;
    private final transient j$.time.zone.f b;

    static z H(String str) {
        j$.time.zone.f fVarA;
        Objects.a(str, "zoneId");
        int length = str.length();
        if (length >= 2) {
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt != '/' || i == 0) && ((cCharAt < '0' || cCharAt > '9' || i == 0) && ((cCharAt != '~' || i == 0) && ((cCharAt != '.' || i == 0) && ((cCharAt != '_' || i == 0) && ((cCharAt != '+' || i == 0) && (cCharAt != '-' || i == 0))))))))) {
                    throw new C0063a("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
                }
            }
            try {
                fVarA = j$.time.zone.j.a(str, true);
            } catch (j$.time.zone.g unused) {
                fVarA = null;
            }
            return new z(str, fVarA);
        }
        throw new C0063a("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
    }

    z(String str, j$.time.zone.f fVar) {
        this.a = str;
        this.b = fVar;
    }

    @Override // j$.time.x
    public final String i() {
        return this.a;
    }

    @Override // j$.time.x
    public final j$.time.zone.f C() {
        j$.time.zone.f fVar = this.b;
        return fVar != null ? fVar : j$.time.zone.j.a(this.a, false);
    }

    private Object writeReplace() {
        return new s((byte) 7, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.x
    final void G(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeByte(7);
        objectOutput.writeUTF(this.a);
    }

    final void I(DataOutput dataOutput) {
        dataOutput.writeUTF(this.a);
    }
}
