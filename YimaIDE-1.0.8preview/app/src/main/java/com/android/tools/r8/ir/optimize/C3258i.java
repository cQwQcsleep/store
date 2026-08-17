package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.C1744iS;
import com.android.tools.r8.internal.C2427qS;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3258i {
    public static final /* synthetic */ boolean c = true;
    public AbstractC3256h a;
    public AbstractC0439Dm b = AbstractC0439Dm.m();

    public C3258i(AbstractC3256h abstractC3256h) {
        this.a = abstractC3256h;
    }

    public final void a(AbstractC0439Dm abstractC0439Dm) {
        boolean z = c;
        if (!z && abstractC0439Dm == null) {
            x1f.a();
            return;
        }
        if (z || (!this.b.j() ? !this.b.l() : !abstractC0439Dm.d().d())) {
            this.b = abstractC0439Dm;
        } else {
            x1f.a();
        }
    }

    public final boolean b() {
        return this.b.d().d();
    }

    public final void c() {
        if (!this.b.l()) {
            this.b = this.b.a(C2427qS.b());
        } else {
            boolean z = AbstractC0439Dm.a;
            this.b = C1744iS.b;
        }
    }

    public final boolean a() {
        return this.b.h() && !this.b.l();
    }
}
