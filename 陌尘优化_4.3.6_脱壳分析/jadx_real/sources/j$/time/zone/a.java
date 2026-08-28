package j$.time.zone;

import j$.time.y;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.TimeZone;

/* loaded from: /workspace/unpacked/classes3.dex */
final class a implements Externalizable {
    private static final long serialVersionUID = -8885321777449118786L;
    private byte a;
    private Serializable b;

    public a() {
    }

    a(byte b, Serializable serializable) {
        this.a = b;
        this.b = serializable;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b = this.a;
        Serializable serializable = this.b;
        objectOutput.writeByte(b);
        if (b == 1) {
            ((f) serializable).writeExternal(objectOutput);
            return;
        }
        if (b == 2) {
            ((b) serializable).writeExternal(objectOutput);
        } else if (b == 3) {
            ((e) serializable).writeExternal(objectOutput);
        } else {
            if (b == 100) {
                ((f) serializable).k(objectOutput);
                return;
            }
            throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        Serializable serializableJ;
        byte b = objectInput.readByte();
        this.a = b;
        if (b == 1) {
            serializableJ = f.j(objectInput);
        } else if (b == 2) {
            long jA = a(objectInput);
            y yVarB = b(objectInput);
            y yVarB2 = b(objectInput);
            if (yVarB.equals(yVarB2)) {
                throw new IllegalArgumentException("Offsets must not be equal");
            }
            serializableJ = new b(jA, yVarB, yVarB2);
        } else if (b == 3) {
            serializableJ = e.b(objectInput);
        } else {
            if (b != 100) {
                throw new StreamCorruptedException("Unknown serialized type");
            }
            serializableJ = new f(TimeZone.getTimeZone(objectInput.readUTF()));
        }
        this.b = serializableJ;
    }

    private Object readResolve() {
        return this.b;
    }

    static void d(y yVar, ObjectOutput objectOutput) {
        int I = yVar.I();
        int i = I % 900 == 0 ? I / 900 : 127;
        objectOutput.writeByte(i);
        if (i == 127) {
            objectOutput.writeInt(I);
        }
    }

    static y b(ObjectInput objectInput) throws IOException {
        byte b = objectInput.readByte();
        return b == 127 ? y.L(objectInput.readInt()) : y.L(b * 900);
    }

    static void c(long j, ObjectOutput objectOutput) {
        if (j >= -4575744000L && j < 10413792000L && j % 900 == 0) {
            int i = (int) ((j + 4575744000L) / 900);
            objectOutput.writeByte((i >>> 16) & 255);
            objectOutput.writeByte((i >>> 8) & 255);
            objectOutput.writeByte(i & 255);
            return;
        }
        objectOutput.writeByte(255);
        objectOutput.writeLong(j);
    }

    static long a(ObjectInput objectInput) {
        if ((objectInput.readByte() & 255) == 255) {
            return objectInput.readLong();
        }
        return ((((r0 << 16) + ((objectInput.readByte() & 255) << 8)) + (objectInput.readByte() & 255)) * 900) - 4575744000L;
    }
}
