package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ce, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0405Ce extends AbstractC0535He {
    public static final /* synthetic */ boolean d = true;
    public final L8 c;

    public C0405Ce(C0509Ge c0509Ge, L8 l8) {
        super(c0509Ge);
        this.c = l8;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder a(StringBuilder sb) {
        C0509Ge c0509Ge = this.a;
        c0509Ge.getClass();
        sb.append('$');
        sb.append(c0509Ge.b);
        sb.append(this.c.a());
        return sb;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder b(StringBuilder sb) {
        C0509Ge c0509Ge = this.a;
        c0509Ge.getClass();
        sb.append('$');
        sb.append(c0509Ge.b);
        sb.append(this.c.a());
        return sb;
    }
}
