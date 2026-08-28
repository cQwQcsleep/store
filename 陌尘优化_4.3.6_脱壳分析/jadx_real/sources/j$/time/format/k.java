package j$.time.format;

/* loaded from: /workspace/unpacked/classes3.dex */
final class k implements g {
    static final String[] d = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS", "+HHmmss", "+HH:mm:ss", "+H", "+Hmm", "+H:mm", "+HMM", "+H:MM", "+HMMss", "+H:MM:ss", "+HMMSS", "+H:MM:SS", "+Hmmss", "+H:mm:ss"};
    static final k e = new k("+HH:MM:ss", "Z");
    private final String a;
    private final int b;
    private final int c;

    static {
        new k("+HH:MM:ss", "0");
    }

    k(String str, String str2) {
        int i = 0;
        while (true) {
            String[] strArr = d;
            if (i < 22) {
                if (strArr[i].equals(str)) {
                    this.b = i;
                    this.c = i % 11;
                    this.a = str2;
                    return;
                }
                i++;
            } else {
                throw new IllegalArgumentException("Invalid zone offset pattern: ".concat(str));
            }
        }
    }

    @Override // j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        Long lE = qVar.e(j$.time.temporal.a.OFFSET_SECONDS);
        boolean z = false;
        if (lE == null) {
            return false;
        }
        long jLongValue = lE.longValue();
        int i = (int) jLongValue;
        if (jLongValue != i) {
            throw new ArithmeticException();
        }
        String str = this.a;
        if (i == 0) {
            sb.append(str);
        } else {
            int iAbs = Math.abs((i / 3600) % 100);
            int iAbs2 = Math.abs((i / 60) % 60);
            int iAbs3 = Math.abs(i % 60);
            int length = sb.length();
            sb.append(i < 0 ? "-" : "+");
            if (this.b >= 11 && iAbs < 10) {
                sb.append((char) (iAbs + 48));
            } else {
                a(false, iAbs, sb);
            }
            int i2 = this.c;
            if ((i2 >= 3 && i2 <= 8) || ((i2 >= 9 && iAbs3 > 0) || (i2 >= 1 && iAbs2 > 0))) {
                a(i2 > 0 && i2 % 2 == 0, iAbs2, sb);
                iAbs += iAbs2;
                if (i2 == 7 || i2 == 8 || (i2 >= 5 && iAbs3 > 0)) {
                    if (i2 > 0 && i2 % 2 == 0) {
                        z = true;
                    }
                    a(z, iAbs3, sb);
                    iAbs += iAbs3;
                }
            }
            if (iAbs == 0) {
                sb.setLength(length);
                sb.append(str);
            }
        }
        return true;
    }

    private static void a(boolean z, int i, StringBuilder sb) {
        sb.append(z ? ":" : "");
        sb.append((char) ((i / 10) + 48));
        sb.append((char) ((i % 10) + 48));
    }

    public final String toString() {
        String strReplace = this.a.replace("'", "''");
        return "Offset(" + d[this.b] + ",'" + strReplace + "')";
    }
}
