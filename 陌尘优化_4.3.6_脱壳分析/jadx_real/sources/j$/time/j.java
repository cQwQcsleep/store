package j$.time;

import com.shadow.okhttp3.internal.http2.Http2Connection;
import com.shadow.okhttp3.internal.ws.WebSocketProtocol;
import j$.time.chrono.AbstractC0073i;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import rikka.shizuku.ShizukuApiConstants;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class j implements j$.time.temporal.m, j$.time.temporal.p, Comparable, Serializable {
    public static final j e;
    public static final j f;
    public static final j g;
    private static final j[] h = new j[24];
    private static final long serialVersionUID = 6414437269572265201L;
    private final byte a;
    private final byte b;
    private final byte c;
    private final int d;

    static {
        int i = 0;
        while (true) {
            j[] jVarArr = h;
            if (i < jVarArr.length) {
                jVarArr[i] = new j(i, 0, 0, 0);
                i++;
            } else {
                j jVar = jVarArr[0];
                g = jVar;
                j jVar2 = jVarArr[12];
                e = jVar;
                f = new j(23, 59, 59, 999999999);
                return;
            }
        }
    }

    public static j J(int i) {
        j$.time.temporal.a.HOUR_OF_DAY.C(i);
        return h[i];
    }

    public static j L(long j) {
        j$.time.temporal.a.SECOND_OF_DAY.C(j);
        int i = (int) (j / 3600);
        long j2 = j - (i * 3600);
        return D(i, (int) (j2 / 60), (int) (j2 - (r0 * 60)), 0);
    }

    public static j K(long j) {
        j$.time.temporal.a.NANO_OF_DAY.C(j);
        int i = (int) (j / 3600000000000L);
        long j2 = j - (i * 3600000000000L);
        int i2 = (int) (j2 / 60000000000L);
        long j3 = j2 - (i2 * 60000000000L);
        int i3 = (int) (j3 / 1000000000);
        return D(i, i2, i3, (int) (j3 - (i3 * 1000000000)));
    }

    public static j E(j$.time.temporal.o oVar) {
        Objects.a(oVar, "temporal");
        j jVar = (j) oVar.v(j$.time.temporal.n.g());
        if (jVar != null) {
            return jVar;
        }
        throw new C0063a("Unable to obtain LocalTime from TemporalAccessor: " + oVar + " of type " + oVar.getClass().getName());
    }

    private static j D(int i, int i2, int i3, int i4) {
        if ((i2 | i3 | i4) == 0) {
            return h[i];
        }
        return new j(i, i2, i3, i4);
    }

    private j(int i, int i2, int i3, int i4) {
        this.a = (byte) i;
        this.b = (byte) i2;
        this.c = (byte) i3;
        this.d = i4;
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) rVar).D();
        }
        return rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            return F(rVar);
        }
        return j$.time.temporal.n.a(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar instanceof j$.time.temporal.a) {
            if (rVar == j$.time.temporal.a.NANO_OF_DAY) {
                return S();
            }
            if (rVar == j$.time.temporal.a.MICRO_OF_DAY) {
                return S() / 1000;
            }
            return F(rVar);
        }
        return rVar.k(this);
    }

    private int F(j$.time.temporal.r rVar) {
        int i = i.a[((j$.time.temporal.a) rVar).ordinal()];
        byte b = this.b;
        int i2 = this.d;
        byte b2 = this.a;
        switch (i) {
            case 1:
                return i2;
            case 2:
                throw new j$.time.temporal.v("Invalid field 'NanoOfDay' for get() method, use getLong() instead");
            case 3:
                return i2 / 1000;
            case 4:
                throw new j$.time.temporal.v("Invalid field 'MicroOfDay' for get() method, use getLong() instead");
            case 5:
                return i2 / 1000000;
            case 6:
                return (int) (S() / 1000000);
            case 7:
                return this.c;
            case 8:
                return T();
            case 9:
                return b;
            case 10:
                return (b2 * 60) + b;
            case 11:
                return b2 % 12;
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                int i3 = b2 % 12;
                if (i3 % 12 == 0) {
                    return 12;
                }
                return i3;
            case 13:
                return b2;
            case 14:
                if (b2 == 0) {
                    return 24;
                }
                return b2;
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                return b2 / 12;
            default:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
    }

    public final int G() {
        return this.a;
    }

    public final int I() {
        return this.c;
    }

    public final int H() {
        return this.d;
    }

    @Override // j$.time.temporal.m
    /* renamed from: U, reason: merged with bridge method [inline-methods] */
    public final j d(long j, j$.time.temporal.r rVar) {
        if (!(rVar instanceof j$.time.temporal.a)) {
            return (j) rVar.n(this, j);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) rVar;
        aVar.C(j);
        int i = i.a[aVar.ordinal()];
        byte b = this.b;
        byte b2 = this.c;
        int i2 = this.d;
        byte b3 = this.a;
        switch (i) {
            case 1:
                return V((int) j);
            case 2:
                return K(j);
            case 3:
                return V(((int) j) * 1000);
            case 4:
                return K(j * 1000);
            case 5:
                return V(((int) j) * 1000000);
            case 6:
                return K(j * 1000000);
            case 7:
                int i3 = (int) j;
                if (b2 == i3) {
                    return this;
                }
                j$.time.temporal.a.SECOND_OF_MINUTE.C(i3);
                return D(b3, b, i3, i2);
            case 8:
                return Q(j - T());
            case 9:
                int i4 = (int) j;
                if (b == i4) {
                    return this;
                }
                j$.time.temporal.a.MINUTE_OF_HOUR.C(i4);
                return D(b3, i4, b2, i2);
            case 10:
                return O(j - ((b3 * 60) + b));
            case 11:
                return N(j - (b3 % 12));
            case ShizukuApiConstants.SERVER_VERSION /* 12 */:
                if (j == 12) {
                    j = 0;
                }
                return N(j - (b3 % 12));
            case 13:
                int i5 = (int) j;
                if (b3 == i5) {
                    return this;
                }
                j$.time.temporal.a.HOUR_OF_DAY.C(i5);
                return D(i5, b, b2, i2);
            case 14:
                if (j == 24) {
                    j = 0;
                }
                int i6 = (int) j;
                if (b3 == i6) {
                    return this;
                }
                j$.time.temporal.a.HOUR_OF_DAY.C(i6);
                return D(i6, b, b2, i2);
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                return N((j - (b3 / 12)) * 12);
            default:
                throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
    }

    public final j V(int i) {
        if (this.d == i) {
            return this;
        }
        j$.time.temporal.a.NANO_OF_SECOND.C(i);
        return D(this.a, this.b, this.c, i);
    }

    @Override // j$.time.temporal.m
    /* renamed from: M, reason: merged with bridge method [inline-methods] */
    public final j e(long j, j$.time.temporal.u uVar) {
        if (uVar instanceof j$.time.temporal.b) {
            switch (i.b[((j$.time.temporal.b) uVar).ordinal()]) {
                case 1:
                    return P(j);
                case 2:
                    return P((j % 86400000000L) * 1000);
                case 3:
                    return P((j % 86400000) * 1000000);
                case 4:
                    return Q(j);
                case 5:
                    return O(j);
                case 6:
                    return N(j);
                case 7:
                    return N((j % 2) * 12);
                default:
                    throw new j$.time.temporal.v("Unsupported unit: " + uVar);
            }
        }
        return (j) uVar.j(this, j);
    }

    public final j N(long j) {
        if (j == 0) {
            return this;
        }
        return D(((((int) (j % 24)) + this.a) + 24) % 24, this.b, this.c, this.d);
    }

    public final j O(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.a * 60) + this.b;
        int i2 = ((((int) (j % 1440)) + i) + 1440) % 1440;
        return i == i2 ? this : D(i2 / 60, i2 % 60, this.c, this.d);
    }

    public final j Q(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.b * 60) + (this.a * 3600) + this.c;
        int i2 = ((((int) (j % 86400)) + i) + 86400) % 86400;
        return i == i2 ? this : D(i2 / 3600, (i2 / 60) % 60, i2 % 60, this.d);
    }

    public final j P(long j) {
        if (j == 0) {
            return this;
        }
        long jS = S();
        long j2 = (((j % 86400000000000L) + jS) + 86400000000000L) % 86400000000000L;
        return jS == j2 ? this : D((int) (j2 / 3600000000000L), (int) ((j2 / 60000000000L) % 60), (int) ((j2 / 1000000000) % 60), (int) (j2 % 1000000000));
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m j(long j, j$.time.temporal.b bVar) {
        return j == Long.MIN_VALUE ? e(Long.MAX_VALUE, bVar).e(1L, bVar) : e(-j, bVar);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        if (tVar == j$.time.temporal.n.e() || tVar == j$.time.temporal.n.k() || tVar == j$.time.temporal.n.j() || tVar == j$.time.temporal.n.h()) {
            return null;
        }
        if (tVar == j$.time.temporal.n.g()) {
            return this;
        }
        if (tVar == j$.time.temporal.n.f()) {
            return null;
        }
        if (tVar == j$.time.temporal.n.i()) {
            return j$.time.temporal.b.NANOS;
        }
        return tVar.a(this);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(S(), j$.time.temporal.a.NANO_OF_DAY);
    }

    public final int T() {
        return (this.b * 60) + (this.a * 3600) + this.c;
    }

    public final long S() {
        return (this.c * 1000000000) + (this.b * 60000000000L) + (this.a * 3600000000000L) + this.d;
    }

    @Override // java.lang.Comparable
    /* renamed from: C, reason: merged with bridge method [inline-methods] */
    public final int compareTo(j jVar) {
        int iCompare = Integer.compare(this.a, jVar.a);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = Integer.compare(this.b, jVar.b);
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompare3 = Integer.compare(this.c, jVar.c);
        return iCompare3 == 0 ? Integer.compare(this.d, jVar.d) : iCompare3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return this.a == jVar.a && this.b == jVar.b && this.c == jVar.c && this.d == jVar.d;
    }

    @Override // j$.time.temporal.m
    public final j$.time.temporal.m m(f fVar) {
        return (j) AbstractC0073i.a(fVar, this);
    }

    public final int hashCode() {
        long jS = S();
        return (int) (jS ^ (jS >>> 32));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(18);
        byte b = this.a;
        sb.append(b < 10 ? "0" : "");
        sb.append((int) b);
        byte b2 = this.b;
        sb.append(b2 < 10 ? ":0" : ":");
        sb.append((int) b2);
        byte b3 = this.c;
        int i = this.d;
        if (b3 > 0 || i > 0) {
            sb.append(b3 < 10 ? ":0" : ":");
            sb.append((int) b3);
            if (i > 0) {
                sb.append('.');
                if (i % 1000000 == 0) {
                    sb.append(Integer.toString((i / 1000000) + 1000).substring(1));
                } else if (i % 1000 == 0) {
                    sb.append(Integer.toString((i / 1000) + 1000000).substring(1));
                } else {
                    sb.append(Integer.toString(i + Http2Connection.DEGRADED_PONG_TIMEOUT_NS).substring(1));
                }
            }
        }
        return sb.toString();
    }

    private Object writeReplace() {
        return new s((byte) 4, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    final void W(DataOutput dataOutput) {
        byte b = this.c;
        byte b2 = this.a;
        byte b3 = this.b;
        int i = this.d;
        if (i != 0) {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(b3);
            dataOutput.writeByte(b);
            dataOutput.writeInt(i);
            return;
        }
        if (b != 0) {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(b3);
            dataOutput.writeByte(~b);
        } else if (b3 == 0) {
            dataOutput.writeByte(~b2);
        } else {
            dataOutput.writeByte(b2);
            dataOutput.writeByte(~b3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [int] */
    static j R(ObjectInput objectInput) throws IOException {
        int i;
        int i2;
        int i3 = objectInput.readByte();
        byte b = 0;
        if (i3 >= 0) {
            byte b2 = objectInput.readByte();
            if (b2 < 0) {
                ?? r7 = ~b2;
                i2 = 0;
                b = r7;
                i = 0;
            } else {
                byte b3 = objectInput.readByte();
                if (b3 < 0) {
                    i = ~b3;
                    b = b2;
                } else {
                    int i4 = objectInput.readInt();
                    i = b3;
                    i2 = i4;
                    b = b2;
                }
            }
            j$.time.temporal.a.HOUR_OF_DAY.C(i3);
            j$.time.temporal.a.MINUTE_OF_HOUR.C(b);
            j$.time.temporal.a.SECOND_OF_MINUTE.C(i);
            j$.time.temporal.a.NANO_OF_SECOND.C(i2);
            return D(i3, b, i, i2);
        }
        i3 = ~i3;
        i = 0;
        i2 = 0;
        j$.time.temporal.a.HOUR_OF_DAY.C(i3);
        j$.time.temporal.a.MINUTE_OF_HOUR.C(b);
        j$.time.temporal.a.SECOND_OF_MINUTE.C(i);
        j$.time.temporal.a.NANO_OF_SECOND.C(i2);
        return D(i3, b, i, i2);
    }
}
