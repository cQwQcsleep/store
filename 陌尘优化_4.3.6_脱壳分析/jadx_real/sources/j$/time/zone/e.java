package j$.time.zone;

import j$.time.chrono.u;
import j$.time.l;
import j$.time.temporal.p;
import j$.time.y;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class e implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;
    private final l a;
    private final byte b;
    private final j$.time.c c;
    private final j$.time.j d;
    private final boolean e;
    private final d f;
    private final y g;
    private final y h;
    private final y i;

    e(l lVar, int i, j$.time.c cVar, j$.time.j jVar, boolean z, d dVar, y yVar, y yVar2, y yVar3) {
        this.a = lVar;
        this.b = (byte) i;
        this.c = cVar;
        this.d = jVar;
        this.e = z;
        this.f = dVar;
        this.g = yVar;
        this.h = yVar2;
        this.i = yVar3;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 3, this);
    }

    final void writeExternal(ObjectOutput objectOutput) {
        j$.time.j jVar = this.d;
        boolean z = this.e;
        int iT = z ? 86400 : jVar.T();
        int I = this.g.I();
        y yVar = this.h;
        int I2 = yVar.I() - I;
        y yVar2 = this.i;
        int I3 = yVar2.I() - I;
        int iG = iT % 3600 == 0 ? z ? 24 : jVar.G() : 31;
        int i = I % 900 == 0 ? (I / 900) + 128 : 255;
        int i2 = (I2 == 0 || I2 == 1800 || I2 == 3600) ? I2 / 1800 : 3;
        int i3 = (I3 == 0 || I3 == 1800 || I3 == 3600) ? I3 / 1800 : 3;
        j$.time.c cVar = this.c;
        objectOutput.writeInt((this.a.getValue() << 28) + ((this.b + 32) << 22) + ((cVar == null ? 0 : cVar.getValue()) << 19) + (iG << 14) + (this.f.ordinal() << 12) + (i << 4) + (i2 << 2) + i3);
        if (iG == 31) {
            objectOutput.writeInt(iT);
        }
        if (i == 255) {
            objectOutput.writeInt(I);
        }
        if (i2 == 3) {
            objectOutput.writeInt(yVar.I());
        }
        if (i3 == 3) {
            objectOutput.writeInt(yVar2.I());
        }
    }

    static e b(ObjectInput objectInput) {
        int i = objectInput.readInt();
        l lVarF = l.F(i >>> 28);
        int i2 = ((264241152 & i) >>> 22) - 32;
        int i3 = (3670016 & i) >>> 19;
        j$.time.c cVarC = i3 == 0 ? null : j$.time.c.C(i3);
        int i4 = (507904 & i) >>> 14;
        d dVar = d.values()[(i & 12288) >>> 12];
        int i5 = (i & 4080) >>> 4;
        int i6 = (i & 12) >>> 2;
        int i7 = i & 3;
        j$.time.j jVarL = i4 == 31 ? j$.time.j.L(objectInput.readInt()) : j$.time.j.J(i4 % 24);
        y yVarL = y.L(i5 == 255 ? objectInput.readInt() : (i5 - 128) * 900);
        y yVarL2 = i6 == 3 ? y.L(objectInput.readInt()) : y.L((i6 * 1800) + yVarL.I());
        y yVarL3 = i7 == 3 ? y.L(objectInput.readInt()) : y.L((i7 * 1800) + yVarL.I());
        boolean z = i4 == 24;
        Objects.a(lVarF, "month");
        Objects.a(jVarL, "time");
        Objects.a(dVar, "timeDefnition");
        if (i2 < -28 || i2 > 31 || i2 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (z && !jVarL.equals(j$.time.j.g)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        if (jVarL.H() != 0) {
            throw new IllegalArgumentException("Time's nano-of-second must be zero");
        }
        return new e(lVarF, i2, cVarC, jVarL, z, dVar, yVarL, yVarL2, yVarL3);
    }

    public final b a(int i) {
        j$.time.f fVarO;
        l lVar = this.a;
        j$.time.c cVar = this.c;
        byte b = this.b;
        if (b < 0) {
            u.d.getClass();
            fVarO = j$.time.f.O(i, lVar, lVar.D(u.m(i)) + 1 + b);
            if (cVar != null) {
                final int value = cVar.getValue();
                final int i2 = 1;
                fVarO = fVarO.m(new p() { // from class: j$.time.temporal.q
                    @Override // j$.time.temporal.p
                    public final m w(m mVar) {
                        switch (i2) {
                            case 0:
                                int iK = mVar.k(a.DAY_OF_WEEK);
                                int i3 = value;
                                if (iK == i3) {
                                    return mVar;
                                }
                                return mVar.e(iK - i3 >= 0 ? 7 - r0 : -r0, b.DAYS);
                            default:
                                int iK2 = mVar.k(a.DAY_OF_WEEK);
                                int i4 = value;
                                if (iK2 == i4) {
                                    return mVar;
                                }
                                return mVar.j(i4 - iK2 >= 0 ? 7 - r1 : -r1, b.DAYS);
                        }
                    }
                });
            }
        } else {
            fVarO = j$.time.f.O(i, lVar, b);
            if (cVar != null) {
                final int value2 = cVar.getValue();
                final int i3 = 0;
                fVarO = fVarO.m(new p() { // from class: j$.time.temporal.q
                    @Override // j$.time.temporal.p
                    public final m w(m mVar) {
                        switch (i3) {
                            case 0:
                                int iK = mVar.k(a.DAY_OF_WEEK);
                                int i32 = value2;
                                if (iK == i32) {
                                    return mVar;
                                }
                                return mVar.e(iK - i32 >= 0 ? 7 - r0 : -r0, b.DAYS);
                            default:
                                int iK2 = mVar.k(a.DAY_OF_WEEK);
                                int i4 = value2;
                                if (iK2 == i4) {
                                    return mVar;
                                }
                                return mVar.j(i4 - iK2 >= 0 ? 7 - r1 : -r1, b.DAYS);
                        }
                    }
                });
            }
        }
        if (this.e) {
            fVarO = fVarO.R(1L);
        }
        j$.time.h hVarK = j$.time.h.K(fVarO, this.d);
        int i4 = c.a[this.f.ordinal()];
        y yVar = this.h;
        if (i4 == 1) {
            hVarK = hVarK.N(yVar.I() - y.e.I());
        } else if (i4 == 2) {
            hVarK = hVarK.N(yVar.I() - this.g.I());
        }
        return new b(hVarK, yVar, this.i);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.a == eVar.a && this.b == eVar.b && this.c == eVar.c && this.f == eVar.f && this.d.equals(eVar.d) && this.e == eVar.e && this.g.equals(eVar.g) && this.h.equals(eVar.h) && this.i.equals(eVar.i);
    }

    public final int hashCode() {
        int iT = ((this.d.T() + (this.e ? 1 : 0)) << 15) + (this.a.ordinal() << 11) + ((this.b + 32) << 5);
        j$.time.c cVar = this.c;
        return ((this.g.hashCode() ^ (this.f.ordinal() + (iT + ((cVar == null ? 7 : cVar.ordinal()) << 2)))) ^ this.h.hashCode()) ^ this.i.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TransitionRule[");
        y yVar = this.h;
        y yVar2 = this.i;
        sb.append(yVar.compareTo(yVar2) > 0 ? "Gap " : "Overlap ");
        sb.append(yVar);
        sb.append(" to ");
        sb.append(yVar2);
        sb.append(", ");
        l lVar = this.a;
        byte b = this.b;
        j$.time.c cVar = this.c;
        if (cVar == null) {
            sb.append(lVar.name());
            sb.append(' ');
            sb.append((int) b);
        } else if (b == -1) {
            sb.append(cVar.name());
            sb.append(" on or before last day of ");
            sb.append(lVar.name());
        } else if (b < 0) {
            sb.append(cVar.name());
            sb.append(" on or before last day minus ");
            sb.append((-b) - 1);
            sb.append(" of ");
            sb.append(lVar.name());
        } else {
            sb.append(cVar.name());
            sb.append(" on or after ");
            sb.append(lVar.name());
            sb.append(' ');
            sb.append((int) b);
        }
        sb.append(" at ");
        sb.append(this.e ? "24:00" : this.d.toString());
        sb.append(" ");
        sb.append(this.f);
        sb.append(", standard offset ");
        sb.append(this.g);
        sb.append(']');
        return sb.toString();
    }
}
