package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.InterfaceC1145bO;
import com.android.tools.r8.internal.KB;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class u extends r {
    public final InterfaceC1145bO c;
    public final boolean d;
    public j e;
    public final int f;

    static {
        j jVar = j.e;
        new u(i.a((Integer) 99, new int[]{jVar.b, jVar.c, jVar.d}, (String[]) null, (String[]) null, (String) null, (Integer) null, 124), true);
    }

    public u(InterfaceC1145bO interfaceC1145bO, boolean z) {
        super(0);
        this.c = interfaceC1145bO;
        this.d = z;
        this.e = new j(interfaceC1145bO.mv());
        this.f = interfaceC1145bO.xi();
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
    public final void a(j jVar) {
        KB.c(jVar, "<set-?>");
        this.e = jVar;
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
    public final h b() {
        s.a("unknown kind", !this.d);
        s.a(this.e);
        Integer numValueOf = Integer.valueOf(this.c.k());
        j jVar = this.e;
        return i.a(numValueOf, new int[]{jVar.b, jVar.c, jVar.d}, this.c.d1(), this.c.d2(), this.c.xs(), this.c.pn(), Integer.valueOf(this.f));
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r
    public final j a() {
        return this.e;
    }
}
