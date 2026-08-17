package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1049aE extends AbstractC0574Ir implements UN {
    public int c;
    public List d;
    public List e;

    public C1049aE() {
        List list = Collections.EMPTY_LIST;
        this.d = list;
        this.e = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C1049aE a(C1473fE c1473fE) {
        if (c1473fE == C1473fE.h) {
            return this;
        }
        if (!c1473fE.c.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = c1473fE.c;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new ArrayList(this.d);
                    this.c |= 1;
                }
                this.d.addAll(c1473fE.c);
            }
        }
        if (!c1473fE.d.isEmpty()) {
            if (this.e.isEmpty()) {
                this.e = c1473fE.d;
                this.c &= -3;
            } else {
                if ((this.c & 2) != 2) {
                    this.e = new ArrayList(this.e);
                    this.c |= 2;
                }
                this.e.addAll(c1473fE.d);
            }
        }
        this.b = this.b.a(c1473fE.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C1473fE c1473fEE = e();
        if (c1473fEE.a()) {
            return c1473fEE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C1049aE().a(e());
    }

    public final C1473fE e() {
        C1473fE c1473fE = new C1473fE(this);
        if ((this.c & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
            this.c &= -2;
        }
        c1473fE.c = this.d;
        if ((this.c & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
            this.c &= -3;
        }
        c1473fE.d = this.e;
        return c1473fE;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C1473fE c1473fE = null;
        try {
            try {
                C1473fE.i.getClass();
                a(new C1473fE(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C1473fE c1473fE2 = (C1473fE) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c1473fE = c1473fE2;
                    if (c1473fE != null) {
                        a(c1473fE);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c1473fE != null) {
                a(c1473fE);
            }
            throw th;
        }
    }
}
