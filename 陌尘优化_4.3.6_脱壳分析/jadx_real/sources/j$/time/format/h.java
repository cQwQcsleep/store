package j$.time.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/* loaded from: /workspace/unpacked/classes3.dex */
final class h extends j {
    private final boolean g;

    h(j$.time.temporal.r rVar, int i, int i2, boolean z, int i3) {
        super(rVar, i, i2, v.NOT_NEGATIVE, i3);
        this.g = z;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [j$.time.temporal.r, java.lang.Enum] */
    @Override // j$.time.format.j
    final j b() {
        if (this.e == -1) {
            return this;
        }
        return new h(this.a, this.b, this.c, this.g, -1);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [j$.time.temporal.r, java.lang.Enum] */
    @Override // j$.time.format.j
    final j c(int i) {
        return new h(this.a, this.b, this.c, this.g, this.e + i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [j$.time.temporal.r, java.lang.Enum] */
    @Override // j$.time.format.j, j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        ?? r0 = this.a;
        Long lE = qVar.e(r0);
        if (lE == null) {
            return false;
        }
        t tVarB = qVar.b();
        long jLongValue = lE.longValue();
        j$.time.temporal.w wVarJ = r0.j();
        wVarJ.b(jLongValue, r0);
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(wVarJ.e());
        BigDecimal bigDecimalAdd = BigDecimal.valueOf(wVarJ.d()).subtract(bigDecimalValueOf).add(BigDecimal.ONE);
        BigDecimal bigDecimalSubtract = BigDecimal.valueOf(jLongValue).subtract(bigDecimalValueOf);
        RoundingMode roundingMode = RoundingMode.FLOOR;
        BigDecimal bigDecimalDivide = bigDecimalSubtract.divide(bigDecimalAdd, 9, roundingMode);
        BigDecimal bigDecimal = BigDecimal.ZERO;
        if (bigDecimalDivide.compareTo(bigDecimal) != 0) {
            bigDecimal = bigDecimalDivide.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : bigDecimalDivide.stripTrailingZeros();
        }
        int iScale = bigDecimal.scale();
        boolean z = this.g;
        int i = this.b;
        if (iScale != 0) {
            String strSubstring = bigDecimal.setScale(Math.min(Math.max(bigDecimal.scale(), i), this.c), roundingMode).toPlainString().substring(2);
            tVarB.getClass();
            if (z) {
                sb.append('.');
            }
            sb.append(strSubstring);
            return true;
        }
        if (i <= 0) {
            return true;
        }
        if (z) {
            tVarB.getClass();
            sb.append('.');
        }
        for (int i2 = 0; i2 < i; i2++) {
            tVarB.getClass();
            sb.append('0');
        }
        return true;
    }

    @Override // j$.time.format.j
    public final String toString() {
        return "Fraction(" + this.a + "," + this.b + "," + this.c + (this.g ? ",DecimalPoint" : "") + ")";
    }
}
