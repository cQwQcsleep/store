package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F00 extends AbstractC0574Ir implements UN {
    public int c;
    public List d = Collections.EMPTY_LIST;
    public int e = -1;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final F00 a(G00 g00) {
        if (g00 == G00.h) {
            return this;
        }
        if (!g00.d.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = g00.d;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new ArrayList(this.d);
                    this.c |= 1;
                }
                this.d.addAll(g00.d);
            }
        }
        if ((g00.c & 1) == 1) {
            int i = g00.e;
            this.c |= 2;
            this.e = i;
        }
        this.b = this.b.a(g00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        G00 g00E = e();
        if (g00E.a()) {
            return g00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new F00().a(e());
    }

    public final G00 e() {
        G00 g00 = new G00(this);
        int i = this.c;
        if ((i & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
            this.c &= -2;
        }
        g00.d = this.d;
        int i2 = (i & 2) != 2 ? 0 : 1;
        g00.e = this.e;
        g00.c = i2;
        return g00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        G00 g00 = null;
        try {
            try {
                G00.i.getClass();
                a(new G00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                G00 g01 = (G00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    g00 = g01;
                    if (g00 != null) {
                        a(g00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (g00 != null) {
                a(g00);
            }
            throw th;
        }
    }
}
