package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KZ extends AbstractC0574Ir implements UN {
    public int c;
    public LZ d = LZ.c;
    public List e = Collections.EMPTY_LIST;
    public UZ f = UZ.m;
    public MZ g = MZ.c;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final KZ a(NZ nz) {
        UZ uz;
        if (nz == NZ.j) {
            return this;
        }
        if ((nz.c & 1) == 1) {
            LZ lz = nz.d;
            lz.getClass();
            this.c = 1 | this.c;
            this.d = lz;
        }
        if (!nz.e.isEmpty()) {
            if (this.e.isEmpty()) {
                this.e = nz.e;
                this.c &= -3;
            } else {
                if ((this.c & 2) != 2) {
                    this.e = new ArrayList(this.e);
                    this.c |= 2;
                }
                this.e.addAll(nz.e);
            }
        }
        if ((nz.c & 2) == 2) {
            UZ uz2 = nz.f;
            if ((this.c & 4) != 4 || (uz = this.f) == UZ.m) {
                this.f = uz2;
            } else {
                this.f = new SZ().a(uz).a(uz2).e();
            }
            this.c |= 4;
        }
        if ((nz.c & 4) == 4) {
            MZ mz = nz.g;
            mz.getClass();
            this.c |= 8;
            this.g = mz;
        }
        this.b = this.b.a(nz.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        NZ nzE = e();
        if (nzE.a()) {
            return nzE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new KZ().a(e());
    }

    public final NZ e() {
        NZ nz = new NZ(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        nz.d = this.d;
        if ((i & 2) == 2) {
            this.e = Collections.unmodifiableList(this.e);
            this.c &= -3;
        }
        nz.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 2;
        }
        nz.f = this.f;
        if ((i & 8) == 8) {
            i2 |= 4;
        }
        nz.g = this.g;
        nz.c = i2;
        return nz;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        NZ nz = null;
        try {
            try {
                NZ.k.getClass();
                a(new NZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                NZ nz2 = (NZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    nz = nz2;
                    if (nz != null) {
                        a(nz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (nz != null) {
                a(nz);
            }
            throw th;
        }
    }
}
