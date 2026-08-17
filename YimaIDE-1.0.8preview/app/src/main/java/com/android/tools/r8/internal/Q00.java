package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q00 extends AbstractC0574Ir implements UN {
    public int c;
    public List d = Collections.EMPTY_LIST;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final Q00 a(R00 r00) {
        if (r00 == R00.f) {
            return this;
        }
        if (!r00.c.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = r00.c;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new ArrayList(this.d);
                    this.c |= 1;
                }
                this.d.addAll(r00.c);
            }
        }
        this.b = this.b.a(r00.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        R00 r00E = e();
        if (r00E.a()) {
            return r00E;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new Q00().a(e());
    }

    public final R00 e() {
        R00 r00 = new R00(this);
        if ((this.c & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
            this.c &= -2;
        }
        r00.c = this.d;
        return r00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        R00 r00 = null;
        try {
            try {
                R00.g.getClass();
                a(new R00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                R00 r01 = (R00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    r00 = r01;
                    if (r00 != null) {
                        a(r00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (r00 != null) {
                a(r00);
            }
            throw th;
        }
    }
}
