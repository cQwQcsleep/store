package j$.time;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import rikka.shizuku.ShizukuApiConstants;

/* loaded from: /workspace/unpacked/classes3.dex */
final class s implements Externalizable {
    private static final long serialVersionUID = -7683839454370182990L;
    private byte a;
    private Object b;

    public s() {
    }

    s(byte b, Object obj) {
        this.a = b;
        this.b = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b = this.a;
        Object obj = this.b;
        objectOutput.writeByte(b);
        switch (b) {
            case 1:
                ((Duration) obj).writeExternal(objectOutput);
                return;
            case 2:
                ((Instant) obj).J(objectOutput);
                return;
            case 3:
                ((f) obj).Z(objectOutput);
                return;
            case 4:
                ((j) obj).W(objectOutput);
                return;
            case 5:
                ((h) obj).T(objectOutput);
                return;
            case 6:
                ((B) obj).G(objectOutput);
                return;
            case 7:
                ((z) obj).I(objectOutput);
                return;
            case 8:
                ((y) obj).O(objectOutput);
                return;
            case 9:
                ((q) obj).writeExternal(objectOutput);
                return;
            case 10:
                ((p) obj).writeExternal(objectOutput);
                return;
            case 11:
                ((u) obj).G(objectOutput);
                return;
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                ((w) obj).J(objectOutput);
                return;
            case 13:
                ((n) obj).D(objectOutput);
                return;
            case 14:
                ((r) obj).writeExternal(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        byte b = objectInput.readByte();
        this.a = b;
        this.b = b(b, objectInput);
    }

    static Serializable a(ObjectInput objectInput) {
        return b(objectInput.readByte(), objectInput);
    }

    private static Serializable b(byte b, ObjectInput objectInput) throws StreamCorruptedException {
        switch (b) {
            case 1:
                Duration duration = Duration.c;
                return Duration.n(objectInput.readLong(), objectInput.readInt());
            case 2:
                Instant instant = Instant.c;
                return Instant.G(objectInput.readLong(), objectInput.readInt());
            case 3:
                f fVar = f.d;
                return f.N(objectInput.readInt(), objectInput.readByte(), objectInput.readByte());
            case 4:
                return j.R(objectInput);
            case 5:
                h hVar = h.c;
                f fVar2 = f.d;
                return h.K(f.N(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), j.R(objectInput));
            case 6:
                return B.E(objectInput);
            case 7:
                int i = z.c;
                return x.D(objectInput.readUTF());
            case 8:
                return y.N(objectInput);
            case 9:
                return q.E(objectInput);
            case 10:
                return p.E(objectInput);
            case 11:
                int i2 = u.b;
                return u.C(objectInput.readInt());
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                return w.G(objectInput);
            case 13:
                return n.C(objectInput);
            case 14:
                return r.a(objectInput);
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
    }

    private Object readResolve() {
        return this.b;
    }
}
