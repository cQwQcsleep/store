package com.android.tools.r8.internal;

import com.android.tools.r8.graph.E0;
import defpackage.vbg;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2952wc implements Supplier {
    public static final /* synthetic */ boolean g = true;
    public AbstractC3038xc b;
    public AbstractC1501fd c;
    public com.android.tools.r8.graph.I2 d;
    public com.android.tools.r8.graph.E0 e = null;
    public volatile boolean f = false;

    public C2952wc(AbstractC3038xc abstractC3038xc, AbstractC1501fd abstractC1501fd, com.android.tools.r8.graph.I2 i2) {
        this.b = abstractC3038xc;
        this.c = abstractC1501fd;
        this.d = i2;
    }

    @Override // java.util.function.Supplier
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final com.android.tools.r8.graph.E0 get() {
        if (this.f) {
            return this.e;
        }
        synchronized (this) {
            try {
                if (!this.f) {
                    if (!g && (this.b == null || this.c == null || this.d == null)) {
                        throw new AssertionError();
                    }
                    this.c.a(this.d, new Consumer() { // from class: soi
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.b.a((E0) obj);
                        }
                    });
                    this.b = null;
                    this.c = null;
                    this.d = null;
                    this.f = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        boolean z = g;
        if (!z && !this.f) {
            x1f.a();
            return null;
        }
        if (z || (this.b == null && this.c == null && this.d == null)) {
            return this.e;
        }
        x1f.a();
        return null;
    }

    public final /* synthetic */ void a(com.android.tools.r8.graph.E0 e0) {
        boolean z = g;
        if (!z && e0 == null) {
            x1f.a();
            return;
        }
        if (!z && !this.b.c().a(e0)) {
            x1f.a();
            return;
        }
        if (!z && this.f) {
            x1f.a();
            return;
        }
        com.android.tools.r8.graph.I2 i2 = e0.e;
        com.android.tools.r8.graph.I2 i3 = this.d;
        if (i2 == i3) {
            com.android.tools.r8.graph.E0 e1 = this.e;
            if (e1 == null) {
                this.e = e0;
                return;
            } else {
                this.e = null;
                this.e = this.b.a(e1, e0);
                return;
            }
        }
        vbg.a("Class content provided for type descriptor ", i3.m0(), " actually defines class ", e0.e.m0());
    }
}
