package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0198e3;
import com.android.tools.r8.graph.C0205f3;
import com.android.tools.r8.graph.C0245l1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ot, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2296ot extends AbstractC2303p {
    public static final /* synthetic */ boolean i = true;
    public C0198e3 c;
    public com.android.tools.r8.graph.I2 d;
    public com.android.tools.r8.graph.I2 e;
    public com.android.tools.r8.graph.H2 f;
    public C0245l1 g;
    public C0205f3 h;

    public C2296ot(com.android.tools.r8.graph.B1 b1) {
        super(b1);
    }

    @Override // com.android.tools.r8.internal.G0
    public final void a(int i2) {
        if (i || !d()) {
            ((C0205f3) this.c.a).b(i2);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2303p
    public final void b() {
        boolean z = C0205f3.f;
        this.c = new C0198e3();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    public final C0245l1 c() {
        if (i || d()) {
            return this.g;
        }
        x1f.a();
        return null;
    }

    public final boolean d() {
        return this.g != null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2303p
    public final void b(com.android.tools.r8.graph.I2 i2) {
        if (i || !d()) {
            this.e = i2;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2303p
    public final void a(com.android.tools.r8.graph.H2 h2) {
        if (i || !d()) {
            this.f = h2;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2303p
    public final void a() {
        this.g = this.a.a(this.e, this.d, this.f);
        this.h = (C0205f3) this.c.a;
    }

    @Override // com.android.tools.r8.internal.AbstractC2303p
    public final void a(com.android.tools.r8.graph.I2 i2) {
        if (i || !d()) {
            this.d = i2;
        } else {
            x1f.a();
        }
    }
}
