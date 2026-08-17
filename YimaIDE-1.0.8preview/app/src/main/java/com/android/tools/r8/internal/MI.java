package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MI {
    public OI a;
    public NI b;
    public Integer c;
    public String d;
    public LI e;

    public final void a(MI mi) {
        OI oi = this.a;
        if (oi == null) {
            KB.a("kind");
            throw null;
        }
        NI ni = this.b;
        if (ni == null) {
            KB.a("level");
            throw null;
        }
        Integer num = this.c;
        String str = this.d;
        mi.a = oi;
        mi.b = ni;
        mi.c = num;
        mi.d = str;
        mi.e = new LI(a().a, a().b, a().c);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KmVersionRequirement(kind=");
        OI oi = this.a;
        if (oi == null) {
            KB.a("kind");
            throw null;
        }
        sb.append(oi);
        sb.append(", level=");
        NI ni = this.b;
        if (ni == null) {
            KB.a("level");
            throw null;
        }
        sb.append(ni);
        sb.append(", version=");
        sb.append(a());
        sb.append(", errorCode=");
        sb.append(this.c);
        sb.append(", message=");
        sb.append(this.d);
        sb.append(')');
        return sb.toString();
    }

    public final LI a() {
        LI li = this.e;
        if (li != null) {
            return li;
        }
        KB.a("version");
        throw null;
    }
}
