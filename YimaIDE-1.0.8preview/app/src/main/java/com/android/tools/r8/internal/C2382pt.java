package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2382pt extends M0 {
    public static final /* synthetic */ boolean j = true;
    public com.android.tools.r8.graph.E4 c;
    public com.android.tools.r8.graph.I2 d;
    public com.android.tools.r8.graph.I2 e;
    public com.android.tools.r8.graph.H2 f;
    public ArrayList g;
    public C0322w2 h;
    public com.android.tools.r8.graph.F4 i;

    public C2382pt(com.android.tools.r8.graph.B1 b1) {
        super(b1);
    }

    @Override // com.android.tools.r8.internal.M0
    public final void a() {
        this.h = this.a.a(this.e, this.a.a(this.d, this.g), this.f);
        this.i = (com.android.tools.r8.graph.F4) this.c.a;
    }

    @Override // com.android.tools.r8.internal.M0
    public final void b() {
        boolean z = com.android.tools.r8.graph.F4.f;
        this.c = new com.android.tools.r8.graph.E4();
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = new ArrayList();
        this.h = null;
        this.i = null;
    }

    public final C0322w2 c() {
        if (j || d()) {
            return this.h;
        }
        x1f.a();
        return null;
    }

    public final boolean d() {
        return this.h != null;
    }

    @Override // com.android.tools.r8.internal.M0
    public final void c(com.android.tools.r8.graph.I2 i2) {
        if (j || !d()) {
            this.d = i2;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.M0
    public final void b(com.android.tools.r8.graph.I2 i2) {
        if (j || !d()) {
            this.e = i2;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.M0
    public final void a(com.android.tools.r8.graph.H2 h2) {
        if (j || !d()) {
            this.f = h2;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.G0
    public final void a(int i) {
        if (j || !d()) {
            ((com.android.tools.r8.graph.F4) this.c.a).b(i);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.M0
    public final void a(com.android.tools.r8.graph.I2 i2) {
        if (j || !d()) {
            this.g.add(i2);
        } else {
            x1f.a();
        }
    }
}
