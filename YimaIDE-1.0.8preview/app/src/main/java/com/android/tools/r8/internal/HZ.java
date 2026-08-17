package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HZ extends AbstractC0574Ir implements UN {
    public int c;
    public List d = Collections.EMPTY_LIST;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final HZ a(IZ iz) {
        if (iz == IZ.f) {
            return this;
        }
        if (!iz.c.isEmpty()) {
            if (this.d.isEmpty()) {
                this.d = iz.c;
                this.c &= -2;
            } else {
                if ((this.c & 1) != 1) {
                    this.d = new ArrayList(this.d);
                    this.c |= 1;
                }
                this.d.addAll(iz.c);
            }
        }
        this.b = this.b.a(iz.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        IZ izE = e();
        if (izE.a()) {
            return izE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new HZ().a(e());
    }

    public final IZ e() {
        IZ iz = new IZ(this);
        if ((this.c & 1) == 1) {
            this.d = Collections.unmodifiableList(this.d);
            this.c &= -2;
        }
        iz.c = this.d;
        return iz;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        IZ iz = null;
        try {
            try {
                IZ.g.getClass();
                a(new IZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                IZ iz2 = (IZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    iz = iz2;
                    if (iz != null) {
                        a(iz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (iz != null) {
                a(iz);
            }
            throw th;
        }
    }
}
