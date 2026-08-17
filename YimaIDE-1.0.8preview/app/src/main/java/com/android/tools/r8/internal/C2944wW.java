package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2944wW extends AbstractC2858vW {
    public final String c;
    public final String d;

    public C2944wW(C2601sW c2601sW, String str, String str2) {
        super(c2601sW);
        this.c = str;
        this.d = str2;
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String a() {
        C3050xi0 c3050xi0D = C3050xi0.d(this.d);
        StringBuilder sb = new StringBuilder();
        String strB = c3050xi0D.b();
        sb.append(AbstractC1732iG.d(C3050xi0.a(C3050xi0.f(strB), strB.length(), strB).b()));
        sb.append(' ');
        sb.append(this.c);
        sb.append('(');
        boolean z = true;
        for (C3050xi0 c3050xi0 : C3050xi0.b(c3050xi0D.b())) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(AbstractC1732iG.d(c3050xi0.b()));
        }
        sb.append(')');
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String b() {
        return "method";
    }
}
