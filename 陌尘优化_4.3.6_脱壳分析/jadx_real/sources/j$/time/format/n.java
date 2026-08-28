package j$.time.format;

/* loaded from: /workspace/unpacked/classes3.dex */
final class n implements g {
    private final j$.time.temporal.a a;
    private final w b;
    private final c c;
    private volatile j d;

    n(j$.time.temporal.a aVar, w wVar, c cVar) {
        this.a = aVar;
        this.b = wVar;
        this.c = cVar;
    }

    @Override // j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        String strA;
        Long lE = qVar.e(this.a);
        if (lE == null) {
            return false;
        }
        j$.time.chrono.n nVar = (j$.time.chrono.n) qVar.d().v(j$.time.temporal.n.e());
        if (nVar == null || nVar == j$.time.chrono.u.d) {
            c cVar = this.c;
            long jLongValue = lE.longValue();
            w wVar = this.b;
            qVar.c();
            strA = cVar.a.a(jLongValue, wVar);
        } else {
            c cVar2 = this.c;
            long jLongValue2 = lE.longValue();
            w wVar2 = this.b;
            qVar.c();
            strA = cVar2.a.a(jLongValue2, wVar2);
        }
        if (strA != null) {
            sb.append(strA);
            return true;
        }
        if (this.d == null) {
            this.d = new j(this.a, 1, 19, v.NORMAL);
        }
        return this.d.j(qVar, sb);
    }

    public final String toString() {
        w wVar = w.FULL;
        j$.time.temporal.a aVar = this.a;
        w wVar2 = this.b;
        if (wVar2 == wVar) {
            return "Text(" + aVar + ")";
        }
        return "Text(" + aVar + "," + wVar2 + ")";
    }
}
