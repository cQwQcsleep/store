package j$.time.format;

import j$.time.y;

/* loaded from: /workspace/unpacked/classes3.dex */
final class i implements g {
    @Override // j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        Long lE = qVar.e(j$.time.temporal.a.INSTANT_SECONDS);
        j$.time.temporal.o oVarD = qVar.d();
        j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_SECOND;
        Long lValueOf = oVarD.f(aVar) ? Long.valueOf(qVar.d().s(aVar)) : null;
        int i = 0;
        if (lE == null) {
            return false;
        }
        long jLongValue = lE.longValue();
        int iW = aVar.w(lValueOf != null ? lValueOf.longValue() : 0L);
        if (jLongValue >= -62167219200L) {
            long j = jLongValue - 253402300800L;
            long jK = j$.com.android.tools.r8.a.k(j, 315569520000L) + 1;
            j$.time.h hVarL = j$.time.h.L(j$.com.android.tools.r8.a.j(j, 315569520000L) - 62167219200L, 0, y.e);
            if (jK > 0) {
                sb.append('+');
                sb.append(jK);
            }
            sb.append(hVarL);
            if (hVarL.F() == 0) {
                sb.append(":00");
            }
        } else {
            long j2 = jLongValue + 62167219200L;
            long j3 = j2 / 315569520000L;
            long j4 = j2 % 315569520000L;
            j$.time.h hVarL2 = j$.time.h.L(j4 - 62167219200L, 0, y.e);
            int length = sb.length();
            sb.append(hVarL2);
            if (hVarL2.F() == 0) {
                sb.append(":00");
            }
            if (j3 < 0) {
                if (hVarL2.G() == -10000) {
                    sb.replace(length, length + 2, Long.toString(j3 - 1));
                } else if (j4 == 0) {
                    sb.insert(length, j3);
                } else {
                    sb.insert(length + 1, Math.abs(j3));
                }
            }
        }
        if (iW > 0) {
            sb.append('.');
            int i2 = 100000000;
            while (true) {
                if (iW <= 0 && i % 3 == 0 && i >= -2) {
                    break;
                }
                int i3 = iW / i2;
                sb.append((char) (i3 + 48));
                iW -= i3 * i2;
                i2 /= 10;
                i++;
            }
        }
        sb.append('Z');
        return true;
    }

    public final String toString() {
        return "Instant()";
    }
}
