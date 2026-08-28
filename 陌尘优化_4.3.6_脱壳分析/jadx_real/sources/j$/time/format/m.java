package j$.time.format;

import j$.time.x;

/* loaded from: /workspace/unpacked/classes3.dex */
final class m implements g {
    public final /* synthetic */ int a;
    private final Object b;

    public /* synthetic */ m(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        switch (this.a) {
            case 0:
                sb.append((String) this.b);
                break;
            default:
                x xVar = (x) qVar.f((b) this.b);
                if (xVar != null) {
                    sb.append(xVar.i());
                    break;
                }
                break;
        }
        return true;
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return "'" + ((String) this.b).replace("'", "''") + "'";
            default:
                return "ZoneRegionId()";
        }
    }
}
