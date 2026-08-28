package j$.time.format;

import com.shadow.okhttp3.internal.connection.RealConnection;
import j$.time.C0063a;

/* loaded from: /workspace/unpacked/classes3.dex */
class j implements g {
    static final long[] f = {0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, RealConnection.IDLE_CONNECTION_HEALTHY_NS};
    final Enum a;
    final int b;
    final int c;
    private final v d;
    final int e;

    /* JADX WARN: Multi-variable type inference failed */
    j(j$.time.temporal.r rVar, int i, int i2, v vVar) {
        this.a = (Enum) rVar;
        this.b = i;
        this.c = i2;
        this.d = vVar;
        this.e = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected j(j$.time.temporal.r rVar, int i, int i2, v vVar, int i3) {
        this.a = (Enum) rVar;
        this.b = i;
        this.c = i2;
        this.d = vVar;
        this.e = i3;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [j$.time.temporal.r, java.lang.Enum] */
    j b() {
        return this.e == -1 ? this : new j(this.a, this.b, this.c, this.d, -1);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [j$.time.temporal.r, java.lang.Enum] */
    j c(int i) {
        int i2 = this.e + i;
        return new j(this.a, this.b, this.c, this.d, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [j$.time.temporal.r, java.lang.Enum, java.lang.Object] */
    @Override // j$.time.format.g
    public boolean j(q qVar, StringBuilder sb) {
        ?? r0 = this.a;
        Long lE = qVar.e(r0);
        if (lE == null) {
            return false;
        }
        long jLongValue = lE.longValue();
        t tVarB = qVar.b();
        String string = jLongValue == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(jLongValue));
        int length = string.length();
        int i = this.c;
        if (length > i) {
            throw new C0063a("Field " + ((Object) r0) + " cannot be printed as the value " + jLongValue + " exceeds the maximum print width of " + i);
        }
        tVarB.getClass();
        int i2 = this.b;
        v vVar = this.d;
        if (jLongValue >= 0) {
            int i3 = d.a[vVar.ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    sb.append('+');
                }
            } else if (i2 < 19 && jLongValue >= f[i2]) {
                sb.append('+');
            }
        } else {
            int i4 = d.a[vVar.ordinal()];
            if (i4 == 1 || i4 == 2 || i4 == 3) {
                sb.append('-');
            } else if (i4 == 4) {
                throw new C0063a("Field " + ((Object) r0) + " cannot be printed as the value " + jLongValue + " cannot be negative according to the SignStyle");
            }
        }
        for (int i5 = 0; i5 < i2 - string.length(); i5++) {
            sb.append('0');
        }
        sb.append(string);
        return true;
    }

    public String toString() {
        Enum r0 = this.a;
        int i = this.c;
        v vVar = this.d;
        int i2 = this.b;
        if (i2 == 1 && i == 19 && vVar == v.NORMAL) {
            return "Value(" + r0 + ")";
        }
        if (i2 == i && vVar == v.NOT_NEGATIVE) {
            return "Value(" + r0 + "," + i2 + ")";
        }
        return "Value(" + r0 + "," + i2 + "," + i + "," + vVar + ")";
    }
}
