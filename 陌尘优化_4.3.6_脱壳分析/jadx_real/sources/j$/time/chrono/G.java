package j$.time.chrono;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* loaded from: /workspace/unpacked/classes3.dex */
final class G implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;
    private byte a;
    private Object b;

    public G() {
    }

    G(byte b, Object obj) {
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
                objectOutput.writeUTF(((AbstractC0065a) obj).i());
                return;
            case 2:
                ((C0071g) obj).writeExternal(objectOutput);
                return;
            case 3:
                ((m) obj).writeExternal(objectOutput);
                return;
            case 4:
                z zVar = (z) obj;
                zVar.getClass();
                objectOutput.writeInt(j$.time.temporal.n.a(zVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.n.a(zVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.n.a(zVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 5:
                ((A) obj).E(objectOutput);
                return;
            case 6:
                ((s) obj).writeExternal(objectOutput);
                return;
            case 7:
                E e = (E) obj;
                e.getClass();
                objectOutput.writeInt(j$.time.temporal.n.a(e, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.n.a(e, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.n.a(e, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 8:
                K k = (K) obj;
                k.getClass();
                objectOutput.writeInt(j$.time.temporal.n.a(k, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.n.a(k, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.n.a(k, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 9:
                ((C0072h) obj).writeExternal(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        Object objJ;
        byte b = objectInput.readByte();
        this.a = b;
        switch (b) {
            case 1:
                int i = AbstractC0065a.c;
                objJ = AbstractC0065a.j(objectInput.readUTF());
                break;
            case 2:
                objJ = ((InterfaceC0066b) objectInput.readObject()).u((j$.time.j) objectInput.readObject());
                break;
            case 3:
                objJ = ((InterfaceC0069e) objectInput.readObject()).p((j$.time.y) objectInput.readObject()).h((j$.time.x) objectInput.readObject());
                break;
            case 4:
                j$.time.f fVar = z.d;
                int i2 = objectInput.readInt();
                byte b2 = objectInput.readByte();
                byte b3 = objectInput.readByte();
                x.d.getClass();
                objJ = new z(j$.time.f.N(i2, b2, b3));
                break;
            case 5:
                A a = A.d;
                objJ = A.A(objectInput.readByte());
                break;
            case 6:
                q qVar = (q) objectInput.readObject();
                int i3 = objectInput.readInt();
                byte b4 = objectInput.readByte();
                byte b5 = objectInput.readByte();
                qVar.getClass();
                objJ = s.K(qVar, i3, b4, b5);
                break;
            case 7:
                int i4 = objectInput.readInt();
                byte b6 = objectInput.readByte();
                byte b7 = objectInput.readByte();
                C.d.getClass();
                objJ = new E(j$.time.f.N(i4 + 1911, b6, b7));
                break;
            case 8:
                int i5 = objectInput.readInt();
                byte b8 = objectInput.readByte();
                byte b9 = objectInput.readByte();
                I.d.getClass();
                objJ = new K(j$.time.f.N(i5 - 543, b8, b9));
                break;
            case 9:
                int i6 = C0072h.e;
                objJ = new C0072h(AbstractC0065a.j(objectInput.readUTF()), objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
                break;
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
        this.b = objJ;
    }

    private Object readResolve() {
        return this.b;
    }
}
