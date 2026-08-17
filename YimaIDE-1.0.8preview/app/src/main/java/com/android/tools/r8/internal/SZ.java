package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SZ extends AbstractC0574Ir implements UN {
    public int c;
    public int d;
    public int e;
    public TZ f = TZ.c;
    public C2903w00 g = C2903w00.u;
    public int h;
    public List i;
    public List j;

    public SZ() {
        List list = Collections.EMPTY_LIST;
        this.i = list;
        this.j = list;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final SZ a(UZ uz) {
        C2903w00 c2903w00;
        if (uz == UZ.m) {
            return this;
        }
        int i = uz.c;
        if ((i & 1) == 1) {
            int i2 = uz.d;
            this.c = 1 | this.c;
            this.d = i2;
        }
        if ((i & 2) == 2) {
            int i3 = uz.e;
            this.c = 2 | this.c;
            this.e = i3;
        }
        if ((i & 4) == 4) {
            TZ tz = uz.f;
            tz.getClass();
            this.c = 4 | this.c;
            this.f = tz;
        }
        if ((uz.c & 8) == 8) {
            C2903w00 c2903w01 = uz.g;
            if ((this.c & 8) != 8 || (c2903w00 = this.g) == C2903w00.u) {
                this.g = c2903w01;
            } else {
                this.g = C2903w00.a(c2903w00).a(c2903w01).f();
            }
            this.c |= 8;
        }
        if ((uz.c & 16) == 16) {
            int i4 = uz.h;
            this.c = 16 | this.c;
            this.h = i4;
        }
        if (!uz.i.isEmpty()) {
            if (this.i.isEmpty()) {
                this.i = uz.i;
                this.c &= -33;
            } else {
                if ((this.c & 32) != 32) {
                    this.i = new ArrayList(this.i);
                    this.c |= 32;
                }
                this.i.addAll(uz.i);
            }
        }
        if (!uz.j.isEmpty()) {
            if (this.j.isEmpty()) {
                this.j = uz.j;
                this.c &= -65;
            } else {
                if ((this.c & 64) != 64) {
                    this.j = new ArrayList(this.j);
                    this.c |= 64;
                }
                this.j.addAll(uz.j);
            }
        }
        this.b = this.b.a(uz.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        UZ uzE = e();
        if (uzE.a()) {
            return uzE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new SZ().a(e());
    }

    public final UZ e() {
        UZ uz = new UZ(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        uz.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        uz.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        uz.f = this.f;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        uz.g = this.g;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        uz.h = this.h;
        if ((i & 32) == 32) {
            this.i = Collections.unmodifiableList(this.i);
            this.c &= -33;
        }
        uz.i = this.i;
        if ((this.c & 64) == 64) {
            this.j = Collections.unmodifiableList(this.j);
            this.c &= -65;
        }
        uz.j = this.j;
        uz.c = i2;
        return uz;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        UZ uz = null;
        try {
            try {
                UZ.n.getClass();
                a(new UZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                UZ uz2 = (UZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    uz = uz2;
                    if (uz != null) {
                        a(uz);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (uz != null) {
                a(uz);
            }
            throw th;
        }
    }
}
