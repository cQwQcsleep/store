package j$.time;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class y extends x implements j$.time.temporal.o, j$.time.temporal.p, Comparable {
    private static final ConcurrentHashMap c = new ConcurrentHashMap(16, 4);
    private static final ConcurrentHashMap d = new ConcurrentHashMap(16, 4);
    public static final y e = L(0);
    public static final y f = L(-64800);
    public static final y g = L(64800);
    private static final long serialVersionUID = 2357656521762053153L;
    private final int a;
    private final transient String b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static y J(String str) {
        int iM;
        int iM2;
        int iM3;
        char cCharAt;
        Objects.a(str, "offsetId");
        y yVar = (y) d.get(str);
        if (yVar != null) {
            return yVar;
        }
        int length = str.length();
        if (length == 2) {
            str = str.charAt(0) + "0" + str.charAt(1);
        } else {
            if (length != 3) {
                if (length == 5) {
                    iM = M(str, 1, false);
                    iM2 = M(str, 3, false);
                } else if (length == 6) {
                    iM = M(str, 1, false);
                    iM2 = M(str, 4, true);
                } else {
                    if (length == 7) {
                        iM = M(str, 1, false);
                        iM2 = M(str, 3, false);
                        iM3 = M(str, 5, false);
                    } else if (length == 9) {
                        iM = M(str, 1, false);
                        iM2 = M(str, 4, true);
                        iM3 = M(str, 7, true);
                    } else {
                        throw new C0063a("Invalid ID for ZoneOffset, invalid format: ".concat(str));
                    }
                    cCharAt = str.charAt(0);
                    if (cCharAt == '+' && cCharAt != '-') {
                        throw new C0063a("Invalid ID for ZoneOffset, plus/minus not found when expected: ".concat(str));
                    }
                    if (cCharAt == '-') {
                        return K(-iM, -iM2, -iM3);
                    }
                    return K(iM, iM2, iM3);
                }
            }
            iM3 = 0;
            cCharAt = str.charAt(0);
            if (cCharAt == '+') {
            }
            if (cCharAt == '-') {
            }
        }
        iM = M(str, 1, false);
        iM2 = 0;
        iM3 = 0;
        cCharAt = str.charAt(0);
        if (cCharAt == '+') {
        }
        if (cCharAt == '-') {
        }
    }

    private static int M(String str, int i, boolean z) {
        if (z && str.charAt(i - 1) != ':') {
            throw new C0063a("Invalid ID for ZoneOffset, colon not found when expected: " + ((Object) str));
        }
        char cCharAt = str.charAt(i);
        char cCharAt2 = str.charAt(i + 1);
        if (cCharAt >= '0' && cCharAt <= '9' && cCharAt2 >= '0' && cCharAt2 <= '9') {
            return (cCharAt2 - '0') + ((cCharAt - '0') * 10);
        }
        throw new C0063a("Invalid ID for ZoneOffset, non numeric characters found: " + ((Object) str));
    }

    public static y K(int i, int i2, int i3) {
        if (i < -18 || i > 18) {
            throw new C0063a("Zone offset hours not in valid range: value " + i + " is not in the range -18 to 18");
        }
        if (i > 0) {
            if (i2 < 0 || i3 < 0) {
                throw new C0063a("Zone offset minutes and seconds must be positive because hours is positive");
            }
        } else if (i < 0) {
            if (i2 > 0 || i3 > 0) {
                throw new C0063a("Zone offset minutes and seconds must be negative because hours is negative");
            }
        } else if ((i2 > 0 && i3 < 0) || (i2 < 0 && i3 > 0)) {
            throw new C0063a("Zone offset minutes and seconds must have the same sign");
        }
        if (i2 < -59 || i2 > 59) {
            throw new C0063a("Zone offset minutes not in valid range: value " + i2 + " is not in the range -59 to 59");
        }
        if (i3 < -59 || i3 > 59) {
            throw new C0063a("Zone offset seconds not in valid range: value " + i3 + " is not in the range -59 to 59");
        }
        if (Math.abs(i) == 18 && (i2 | i3) != 0) {
            throw new C0063a("Zone offset not in valid range: -18:00 to +18:00");
        }
        return L((i2 * 60) + (i * 3600) + i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static y L(int i) {
        if (i < -64800 || i > 64800) {
            throw new C0063a("Zone offset not in valid range: -18:00 to +18:00");
        }
        if (i % 900 == 0) {
            Integer numValueOf = Integer.valueOf(i);
            ConcurrentHashMap concurrentHashMap = c;
            y yVar = (y) concurrentHashMap.get(numValueOf);
            if (yVar != null) {
                return yVar;
            }
            concurrentHashMap.putIfAbsent(numValueOf, new y(i));
            y yVar2 = (y) concurrentHashMap.get(numValueOf);
            d.putIfAbsent(yVar2.b, yVar2);
            return yVar2;
        }
        return new y(i);
    }

    private y(int i) {
        String string;
        this.a = i;
        if (i == 0) {
            string = "Z";
        } else {
            int iAbs = Math.abs(i);
            StringBuilder sb = new StringBuilder();
            int i2 = iAbs / 3600;
            int i3 = (iAbs / 60) % 60;
            sb.append(i < 0 ? "-" : "+");
            sb.append(i2 < 10 ? "0" : "");
            sb.append(i2);
            sb.append(i3 < 10 ? ":0" : ":");
            sb.append(i3);
            int i4 = iAbs % 60;
            if (i4 != 0) {
                sb.append(i4 < 10 ? ":0" : ":");
                sb.append(i4);
            }
            string = sb.toString();
        }
        this.b = string;
    }

    public final int I() {
        return this.a;
    }

    @Override // j$.time.x
    public final String i() {
        return this.b;
    }

    @Override // j$.time.x
    public final j$.time.zone.f C() {
        return j$.time.zone.f.h(this);
    }

    @Override // j$.time.temporal.o
    public final boolean f(j$.time.temporal.r rVar) {
        return rVar instanceof j$.time.temporal.a ? rVar == j$.time.temporal.a.OFFSET_SECONDS : rVar != null && rVar.m(this);
    }

    @Override // j$.time.temporal.o
    public final j$.time.temporal.w n(j$.time.temporal.r rVar) {
        return j$.time.temporal.n.d(this, rVar);
    }

    @Override // j$.time.temporal.o
    public final int k(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.a;
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        return j$.time.temporal.n.d(this, rVar).a(s(rVar), rVar);
    }

    @Override // j$.time.temporal.o
    public final long s(j$.time.temporal.r rVar) {
        if (rVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.a;
        }
        if (rVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.v(AbstractC0064b.a("Unsupported field: ", rVar));
        }
        return rVar.k(this);
    }

    @Override // j$.time.temporal.o
    public final Object v(j$.time.temporal.t tVar) {
        return (tVar == j$.time.temporal.n.h() || tVar == j$.time.temporal.n.j()) ? this : j$.time.temporal.n.c(this, tVar);
    }

    @Override // j$.time.temporal.p
    public final j$.time.temporal.m w(j$.time.temporal.m mVar) {
        return mVar.d(this.a, j$.time.temporal.a.OFFSET_SECONDS);
    }

    @Override // java.lang.Comparable
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public final int compareTo(y yVar) {
        return yVar.a - this.a;
    }

    @Override // j$.time.x
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof y) {
            return this.a == ((y) obj).a;
        }
        return false;
    }

    @Override // j$.time.x
    public final int hashCode() {
        return this.a;
    }

    @Override // j$.time.x
    public final String toString() {
        return this.b;
    }

    private Object writeReplace() {
        return new s((byte) 8, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.x
    final void G(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeByte(8);
        O(objectOutput);
    }

    final void O(DataOutput dataOutput) {
        int i = this.a;
        int i2 = i % 900 == 0 ? i / 900 : 127;
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(i);
        }
    }

    static y N(ObjectInput objectInput) {
        byte b = objectInput.readByte();
        return b == 127 ? L(objectInput.readInt()) : L(b * 900);
    }
}
