package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2690tZ extends AbstractC0574Ir implements UN {
    public int c;
    public long e;
    public float f;
    public double g;
    public int h;
    public int i;
    public int j;
    public int m;
    public int n;
    public EnumC2776uZ d = EnumC2776uZ.c;
    public C3117yZ k = C3117yZ.h;
    public List l = Collections.EMPTY_LIST;

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C2690tZ a(C2861vZ c2861vZ) {
        C3117yZ c3117yZ;
        if (c2861vZ == C2861vZ.q) {
            return this;
        }
        if ((c2861vZ.c & 1) == 1) {
            EnumC2776uZ enumC2776uZ = c2861vZ.d;
            enumC2776uZ.getClass();
            this.c = 1 | this.c;
            this.d = enumC2776uZ;
        }
        if ((c2861vZ.c & 2) == 2) {
            a(c2861vZ.e);
        }
        int i = c2861vZ.c;
        if ((i & 4) == 4) {
            float f = c2861vZ.f;
            this.c = 4 | this.c;
            this.f = f;
        }
        if ((i & 8) == 8) {
            double d = c2861vZ.g;
            this.c |= 8;
            this.g = d;
        }
        if ((i & 16) == 16) {
            int i2 = c2861vZ.h;
            this.c = 16 | this.c;
            this.h = i2;
        }
        if ((i & 32) == 32) {
            int i3 = c2861vZ.i;
            this.c = 32 | this.c;
            this.i = i3;
        }
        if ((i & 64) == 64) {
            int i4 = c2861vZ.j;
            this.c = 64 | this.c;
            this.j = i4;
        }
        if ((i & 128) == 128) {
            C3117yZ c3117yZ2 = c2861vZ.k;
            if ((this.c & 128) != 128 || (c3117yZ = this.k) == C3117yZ.h) {
                this.k = c3117yZ2;
            } else {
                this.k = new C3033xZ().a(c3117yZ).a(c3117yZ2).e();
            }
            this.c |= 128;
        }
        if (!c2861vZ.l.isEmpty()) {
            if (this.l.isEmpty()) {
                this.l = c2861vZ.l;
                this.c &= -257;
            } else {
                if ((this.c & Fcntl.S_IRUSR) != 256) {
                    this.l = new ArrayList(this.l);
                    this.c |= Fcntl.S_IRUSR;
                }
                this.l.addAll(c2861vZ.l);
            }
        }
        int i5 = c2861vZ.c;
        if ((i5 & Fcntl.S_IRUSR) == 256) {
            int i6 = c2861vZ.m;
            this.c |= 512;
            this.m = i6;
        }
        if ((i5 & 512) == 512) {
            int i7 = c2861vZ.n;
            this.c |= Fcntl.S_ISGID;
            this.n = i7;
        }
        this.b = this.b.a(c2861vZ.b);
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final L0 c() {
        C2861vZ c2861vZE = e();
        if (c2861vZE.a()) {
            return c2861vZE;
        }
        defpackage.bk.a();
        return null;
    }

    public final Object clone() {
        return new C2690tZ().a(e());
    }

    public final C2861vZ e() {
        C2861vZ c2861vZ = new C2861vZ(this);
        int i = this.c;
        int i2 = (i & 1) != 1 ? 0 : 1;
        c2861vZ.d = this.d;
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2861vZ.e = this.e;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c2861vZ.f = this.f;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        c2861vZ.g = this.g;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        c2861vZ.h = this.h;
        if ((i & 32) == 32) {
            i2 |= 32;
        }
        c2861vZ.i = this.i;
        if ((i & 64) == 64) {
            i2 |= 64;
        }
        c2861vZ.j = this.j;
        if ((i & 128) == 128) {
            i2 |= 128;
        }
        c2861vZ.k = this.k;
        if ((i & Fcntl.S_IRUSR) == 256) {
            this.l = Collections.unmodifiableList(this.l);
            this.c &= -257;
        }
        c2861vZ.l = this.l;
        if ((i & 512) == 512) {
            i2 |= Fcntl.S_IRUSR;
        }
        c2861vZ.m = this.m;
        if ((i & Fcntl.S_ISGID) == 1024) {
            i2 |= 512;
        }
        c2861vZ.n = this.n;
        c2861vZ.c = i2;
        return c2861vZ;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C2861vZ c2861vZ = null;
        try {
            try {
                C2861vZ.r.getClass();
                a(new C2861vZ(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C2861vZ c2861vZ2 = (C2861vZ) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c2861vZ = c2861vZ2;
                    if (c2861vZ != null) {
                        a(c2861vZ);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2861vZ != null) {
                a(c2861vZ);
            }
            throw th;
        }
    }

    public final void a(long j) {
        this.c |= 2;
        this.e = j;
    }
}
