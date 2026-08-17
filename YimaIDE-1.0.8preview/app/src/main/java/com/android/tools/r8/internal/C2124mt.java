package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0327x0;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2124mt implements InterfaceC0852Tk {
    public final boolean a;
    public final C0394Bt b;
    public final C2552rt c;

    public C2124mt(C0394Bt c0394Bt, C2552rt c2552rt, boolean z) {
        this.a = z;
        this.b = c0394Bt;
        this.c = c2552rt;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public C2592sM a(AbstractC0327x0 abstractC0327x0, Ch0 ch0) {
        return new C3065xt(ch0).a(this, abstractC0327x0);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final String b() {
        return this.b.d;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final EnumC3077y2 c() {
        return this.b.d();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final boolean d() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final List e() {
        return this.b.b();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final String f() {
        return this.b.e();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final boolean isEmpty() {
        return this.c.k();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0852Tk
    public final Set a() {
        return this.c.c;
    }
}
