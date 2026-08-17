package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EZ extends AbstractC0600Jr {
    public int e;
    public int f = 6;
    public List g;
    public List h;

    public EZ() {
        List list = Collections.EMPTY_LIST;
        this.g = list;
        this.h = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final EZ a(FZ fz) {
        if (fz == FZ.j) {
            return this;
        }
        if ((fz.d & 1) == 1) {
            int i = fz.e;
            this.e = 1 | this.e;
            this.f = i;
        }
        if (!fz.f.isEmpty()) {
            if (this.g.isEmpty()) {
                this.g = fz.f;
                this.e &= -3;
            } else {
                if ((this.e & 2) != 2) {
                    this.g = new ArrayList(this.g);
                    this.e |= 2;
                }
                this.g.addAll(fz.f);
            }
        }
        if (!fz.g.isEmpty()) {
            if (this.h.isEmpty()) {
                this.h = fz.g;
                this.e &= -5;
            } else {
                if ((this.e & 4) != 4) {
                    this.h = new ArrayList(this.h);
                    this.e |= 4;
                }
                this.h.addAll(fz.g);
            }
        }
        a((Lr) fz);
        this.b = this.b.a(fz.c);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        FZ fzE = e();
        if (fzE.a()) {
            return fzE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new EZ().a(e());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return FZ.j;
    }

    public final FZ e() {
        FZ fz = new FZ(this);
        int i = this.e;
        int i2 = (i & 1) != 1 ? 0 : 1;
        fz.e = this.f;
        if ((i & 2) == 2) {
            this.g = Collections.unmodifiableList(this.g);
            this.e &= -3;
        }
        fz.f = this.g;
        if ((this.e & 4) == 4) {
            this.h = Collections.unmodifiableList(this.h);
            this.e &= -5;
        }
        fz.g = this.h;
        fz.d = i2;
        return fz;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0019  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        FZ fz = null;
        try {
            try {
                a((FZ) FZ.k.a(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                FZ fz2 = (FZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    fz = fz2;
                    if (fz != null) {
                        a(fz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (fz != null) {
                a(fz);
            }
            throw th;
        }
    }
}
