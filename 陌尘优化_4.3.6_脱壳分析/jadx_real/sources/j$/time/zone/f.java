package j$.time.zone;

import j$.time.Instant;
import j$.time.chrono.AbstractC0073i;
import j$.time.y;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* loaded from: /workspace/unpacked/classes3.dex */
public final class f implements Serializable {
    private static final long[] i = new long[0];
    private static final e[] j = new e[0];
    private static final j$.time.h[] k = new j$.time.h[0];
    private static final b[] l = new b[0];
    private static final long serialVersionUID = 3044319355680032515L;
    private final long[] a;
    private final y[] b;
    private final long[] c;
    private final j$.time.h[] d;
    private final y[] e;
    private final e[] f;
    private final TimeZone g;
    private final transient ConcurrentHashMap h = new ConcurrentHashMap();

    public static f h(y yVar) {
        Objects.a(yVar, "offset");
        return new f(yVar);
    }

    private f(long[] jArr, y[] yVarArr, long[] jArr2, y[] yVarArr2, e[] eVarArr) {
        this.a = jArr;
        this.b = yVarArr;
        this.c = jArr2;
        this.e = yVarArr2;
        this.f = eVarArr;
        if (jArr2.length == 0) {
            this.d = k;
        } else {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < jArr2.length) {
                int i3 = i2 + 1;
                b bVar = new b(jArr2[i2], yVarArr2[i2], yVarArr2[i3]);
                if (bVar.w()) {
                    arrayList.add(bVar.k());
                    arrayList.add(bVar.j());
                } else {
                    arrayList.add(bVar.j());
                    arrayList.add(bVar.k());
                }
                i2 = i3;
            }
            this.d = (j$.time.h[]) arrayList.toArray(new j$.time.h[arrayList.size()]);
        }
        this.g = null;
    }

    private f(y yVar) {
        y[] yVarArr = {yVar};
        this.b = yVarArr;
        long[] jArr = i;
        this.a = jArr;
        this.c = jArr;
        this.d = k;
        this.e = yVarArr;
        this.f = j;
        this.g = null;
    }

    f(TimeZone timeZone) {
        y[] yVarArr = {i(timeZone.getRawOffset())};
        this.b = yVarArr;
        long[] jArr = i;
        this.a = jArr;
        this.c = jArr;
        this.d = k;
        this.e = yVarArr;
        this.f = j;
        this.g = timeZone;
    }

    private static y i(int i2) {
        return y.L(i2 / 1000);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a(this.g != null ? (byte) 100 : (byte) 1, this);
    }

    final void writeExternal(ObjectOutput objectOutput) throws IOException {
        long[] jArr = this.a;
        objectOutput.writeInt(jArr.length);
        for (long j2 : jArr) {
            a.c(j2, objectOutput);
        }
        for (y yVar : this.b) {
            a.d(yVar, objectOutput);
        }
        long[] jArr2 = this.c;
        objectOutput.writeInt(jArr2.length);
        for (long j3 : jArr2) {
            a.c(j3, objectOutput);
        }
        for (y yVar2 : this.e) {
            a.d(yVar2, objectOutput);
        }
        e[] eVarArr = this.f;
        objectOutput.writeByte(eVarArr.length);
        for (e eVar : eVarArr) {
            eVar.writeExternal(objectOutput);
        }
    }

    final void k(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.g.getID());
    }

    static f j(ObjectInput objectInput) throws IOException {
        int i2 = objectInput.readInt();
        long[] jArr = i;
        long[] jArr2 = i2 == 0 ? jArr : new long[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i3] = a.a(objectInput);
        }
        int i4 = i2 + 1;
        y[] yVarArr = new y[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            yVarArr[i5] = a.b(objectInput);
        }
        int i6 = objectInput.readInt();
        if (i6 != 0) {
            jArr = new long[i6];
        }
        long[] jArr3 = jArr;
        for (int i7 = 0; i7 < i6; i7++) {
            jArr3[i7] = a.a(objectInput);
        }
        int i8 = i6 + 1;
        y[] yVarArr2 = new y[i8];
        for (int i9 = 0; i9 < i8; i9++) {
            yVarArr2[i9] = a.b(objectInput);
        }
        int i10 = objectInput.readByte();
        e[] eVarArr = i10 == 0 ? j : new e[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            eVarArr[i11] = e.b(objectInput);
        }
        return new f(jArr2, yVarArr, jArr3, yVarArr2, eVarArr);
    }

    public final y d(Instant instant) {
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            return i(timeZone.getOffset(instant.toEpochMilli()));
        }
        long[] jArr = this.c;
        if (jArr.length == 0) {
            return this.b[0];
        }
        long jD = instant.D();
        int length = this.f.length;
        y[] yVarArr = this.e;
        if (length > 0 && jD > jArr[jArr.length - 1]) {
            b[] bVarArrB = b(c(jD, yVarArr[yVarArr.length - 1]));
            b bVar = null;
            for (int i2 = 0; i2 < bVarArrB.length; i2++) {
                bVar = bVarArrB[i2];
                if (jD < bVar.B()) {
                    return bVar.s();
                }
            }
            return bVar.n();
        }
        int iBinarySearch = Arrays.binarySearch(jArr, jD);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        }
        return yVarArr[iBinarySearch + 1];
    }

    public final List g(j$.time.h hVar) {
        Object objE = e(hVar);
        if (objE instanceof b) {
            return ((b) objE).v();
        }
        return Collections.singletonList((y) objE);
    }

    public final b f(j$.time.h hVar) {
        Object objE = e(hVar);
        if (objE instanceof b) {
            return (b) objE;
        }
        return null;
    }

    private Object e(j$.time.h hVar) {
        Object obj = null;
        y[] yVarArr = this.b;
        int i2 = 0;
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            b[] bVarArrB = b(hVar.G());
            if (bVarArrB.length == 0) {
                return i(timeZone.getOffset(AbstractC0073i.n(hVar, yVarArr[0]) * 1000));
            }
            int length = bVarArrB.length;
            while (i2 < length) {
                b bVar = bVarArrB[i2];
                Object objA = a(hVar, bVar);
                if ((objA instanceof b) || objA.equals(bVar.s())) {
                    return objA;
                }
                i2++;
                obj = objA;
            }
            return obj;
        }
        if (this.c.length == 0) {
            return yVarArr[0];
        }
        int length2 = this.f.length;
        j$.time.h[] hVarArr = this.d;
        if (length2 > 0 && hVar.H(hVarArr[hVarArr.length - 1])) {
            b[] bVarArrB2 = b(hVar.G());
            int length3 = bVarArrB2.length;
            while (i2 < length3) {
                b bVar2 = bVarArrB2[i2];
                Object objA2 = a(hVar, bVar2);
                if ((objA2 instanceof b) || objA2.equals(bVar2.s())) {
                    return objA2;
                }
                i2++;
                obj = objA2;
            }
            return obj;
        }
        int iBinarySearch = Arrays.binarySearch(hVarArr, hVar);
        y[] yVarArr2 = this.e;
        if (iBinarySearch == -1) {
            return yVarArr2[0];
        }
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        } else if (iBinarySearch < hVarArr.length - 1) {
            int i3 = iBinarySearch + 1;
            if (hVarArr[iBinarySearch].equals(hVarArr[i3])) {
                iBinarySearch = i3;
            }
        }
        if ((iBinarySearch & 1) != 0) {
            return yVarArr2[(iBinarySearch / 2) + 1];
        }
        j$.time.h hVar2 = hVarArr[iBinarySearch];
        j$.time.h hVar3 = hVarArr[iBinarySearch + 1];
        int i4 = iBinarySearch / 2;
        y yVar = yVarArr2[i4];
        y yVar2 = yVarArr2[i4 + 1];
        return yVar2.I() > yVar.I() ? new b(hVar2, yVar, yVar2) : new b(hVar3, yVar, yVar2);
    }

    private static Object a(j$.time.h hVar, b bVar) {
        j$.time.h hVarK = bVar.k();
        if (bVar.w()) {
            if (hVar.I(hVarK)) {
                return bVar.s();
            }
            return hVar.I(bVar.j()) ? bVar : bVar.n();
        }
        if (hVar.I(hVarK)) {
            return hVar.I(bVar.j()) ? bVar.s() : bVar;
        }
        return bVar.n();
    }

    private b[] b(int i2) {
        long j2;
        Integer numValueOf = Integer.valueOf(i2);
        ConcurrentHashMap concurrentHashMap = this.h;
        b[] bVarArr = (b[]) concurrentHashMap.get(numValueOf);
        if (bVarArr != null) {
            return bVarArr;
        }
        TimeZone timeZone = this.g;
        if (timeZone == null) {
            e[] eVarArr = this.f;
            b[] bVarArr2 = new b[eVarArr.length];
            for (int i3 = 0; i3 < eVarArr.length; i3++) {
                bVarArr2[i3] = eVarArr[i3].a(i2);
            }
            if (i2 < 2100) {
                concurrentHashMap.putIfAbsent(numValueOf, bVarArr2);
            }
            return bVarArr2;
        }
        b[] bVarArr3 = l;
        if (i2 < 1800) {
            return bVarArr3;
        }
        long jN = AbstractC0073i.n(j$.time.h.J(i2 - 1), this.b[0]);
        int offset = timeZone.getOffset(jN * 1000);
        long j3 = 31968000 + jN;
        while (jN < j3) {
            long j4 = 7776000 + jN;
            long j5 = jN;
            if (offset != timeZone.getOffset(j4 * 1000)) {
                jN = j5;
                while (j4 - jN > 1) {
                    int i4 = offset;
                    long j6 = j3;
                    long jK = j$.com.android.tools.r8.a.k(j4 + jN, 2L);
                    if (timeZone.getOffset(jK * 1000) == i4) {
                        jN = jK;
                    } else {
                        j4 = jK;
                    }
                    offset = i4;
                    j3 = j6;
                }
                j2 = j3;
                int i5 = offset;
                if (timeZone.getOffset(jN * 1000) == i5) {
                    jN = j4;
                }
                y yVarI = i(i5);
                offset = timeZone.getOffset(jN * 1000);
                y yVarI2 = i(offset);
                if (c(jN, yVarI2) == i2) {
                    bVarArr3 = (b[]) Arrays.copyOf(bVarArr3, bVarArr3.length + 1);
                    bVarArr3[bVarArr3.length - 1] = new b(jN, yVarI, yVarI2);
                }
            } else {
                j2 = j3;
                jN = j4;
            }
            j3 = j2;
        }
        if (1916 <= i2 && i2 < 2100) {
            concurrentHashMap.putIfAbsent(numValueOf, bVarArr3);
        }
        return bVarArr3;
    }

    private static int c(long j2, y yVar) {
        return j$.time.f.P(j$.com.android.tools.r8.a.k(j2 + yVar.I(), 86400)).J();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return Objects.equals(this.g, fVar.g) && Arrays.equals(this.a, fVar.a) && Arrays.equals(this.b, fVar.b) && Arrays.equals(this.c, fVar.c) && Arrays.equals(this.e, fVar.e) && Arrays.equals(this.f, fVar.f);
    }

    public final int hashCode() {
        return ((((Objects.hashCode(this.g) ^ Arrays.hashCode(this.a)) ^ Arrays.hashCode(this.b)) ^ Arrays.hashCode(this.c)) ^ Arrays.hashCode(this.e)) ^ Arrays.hashCode(this.f);
    }

    public final String toString() {
        TimeZone timeZone = this.g;
        if (timeZone != null) {
            return "ZoneRules[timeZone=" + timeZone.getID() + "]";
        }
        return "ZoneRules[currentStandardOffset=" + this.b[r1.length - 1] + "]";
    }
}
