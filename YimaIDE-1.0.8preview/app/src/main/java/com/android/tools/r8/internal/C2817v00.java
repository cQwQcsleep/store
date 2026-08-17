package com.android.tools.r8.internal;

import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v00, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2817v00 extends AbstractC0600Jr {
    public int e;
    public List f = Collections.EMPTY_LIST;
    public boolean g;
    public int h;
    public C2903w00 i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public C2903w00 o;
    public int p;
    public C2903w00 q;
    public int r;
    public int s;

    public C2817v00() {
        C2903w00 c2903w00 = C2903w00.u;
        this.i = c2903w00;
        this.o = c2903w00;
        this.q = c2903w00;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final C2817v00 a(C2903w00 c2903w00) {
        C2903w00 c2903w01;
        C2903w00 c2903w02;
        C2903w00 c2903w03;
        C2903w00 c2903w04 = C2903w00.u;
        if (c2903w00 == c2903w04) {
            return this;
        }
        if (!c2903w00.e.isEmpty()) {
            if (this.f.isEmpty()) {
                this.f = c2903w00.e;
                this.e &= -2;
            } else {
                if ((this.e & 1) != 1) {
                    this.f = new ArrayList(this.f);
                    this.e |= 1;
                }
                this.f.addAll(c2903w00.e);
            }
        }
        int i = c2903w00.d;
        if ((i & 1) == 1) {
            boolean z = c2903w00.f;
            this.e |= 2;
            this.g = z;
        }
        if ((i & 2) == 2) {
            int i2 = c2903w00.g;
            this.e |= 4;
            this.h = i2;
        }
        if ((i & 4) == 4) {
            C2903w00 c2903w05 = c2903w00.h;
            if ((this.e & 8) != 8 || (c2903w03 = this.i) == c2903w04) {
                this.i = c2903w05;
            } else {
                this.i = C2903w00.a(c2903w03).a(c2903w05).f();
            }
            this.e |= 8;
        }
        int i3 = c2903w00.d;
        if ((i3 & 8) == 8) {
            int i4 = c2903w00.i;
            this.e |= 16;
            this.j = i4;
        }
        if ((i3 & 16) == 16) {
            int i5 = c2903w00.j;
            this.e |= 32;
            this.k = i5;
        }
        if ((i3 & 32) == 32) {
            int i6 = c2903w00.k;
            this.e |= 64;
            this.l = i6;
        }
        if ((i3 & 64) == 64) {
            int i7 = c2903w00.l;
            this.e |= 128;
            this.m = i7;
        }
        if ((i3 & 128) == 128) {
            int i8 = c2903w00.m;
            this.e |= Fcntl.S_IRUSR;
            this.n = i8;
        }
        if ((i3 & Fcntl.S_IRUSR) == 256) {
            C2903w00 c2903w06 = c2903w00.n;
            if ((this.e & 512) != 512 || (c2903w02 = this.o) == c2903w04) {
                this.o = c2903w06;
            } else {
                this.o = C2903w00.a(c2903w02).a(c2903w06).f();
            }
            this.e |= 512;
        }
        int i9 = c2903w00.d;
        if ((i9 & 512) == 512) {
            int i10 = c2903w00.o;
            this.e |= Fcntl.S_ISGID;
            this.p = i10;
        }
        if ((i9 & Fcntl.S_ISGID) == 1024) {
            C2903w00 c2903w07 = c2903w00.p;
            if ((this.e & Fcntl.S_ISUID) != 2048 || (c2903w01 = this.q) == c2903w04) {
                this.q = c2903w07;
            } else {
                this.q = C2903w00.a(c2903w01).a(c2903w07).f();
            }
            this.e |= Fcntl.S_ISUID;
        }
        int i11 = c2903w00.d;
        if ((i11 & Fcntl.S_ISUID) == 2048) {
            int i12 = c2903w00.q;
            this.e |= 4096;
            this.r = i12;
        }
        if ((i11 & 4096) == 4096) {
            int i13 = c2903w00.r;
            this.e |= 8192;
            this.s = i13;
        }
        a((Lr) c2903w00);
        this.b = this.b.a(c2903w00.c);
        return this;
    }

    public final Object clone() {
        return new C2817v00().a(f());
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0729Or d() {
        return C2903w00.u;
    }

    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final C2903w00 c() {
        C2903w00 c2903w00F = f();
        if (c2903w00F.a()) {
            return c2903w00F;
        }
        defpackage.bk.a();
        return null;
    }

    public final C2903w00 f() {
        C2903w00 c2903w00 = new C2903w00(this);
        int i = this.e;
        if ((i & 1) == 1) {
            this.f = Collections.unmodifiableList(this.f);
            this.e &= -2;
        }
        c2903w00.e = this.f;
        int i2 = (i & 2) != 2 ? 0 : 1;
        c2903w00.f = this.g;
        if ((i & 4) == 4) {
            i2 |= 2;
        }
        c2903w00.g = this.h;
        if ((i & 8) == 8) {
            i2 |= 4;
        }
        c2903w00.h = this.i;
        if ((i & 16) == 16) {
            i2 |= 8;
        }
        c2903w00.i = this.j;
        if ((i & 32) == 32) {
            i2 |= 16;
        }
        c2903w00.j = this.k;
        if ((i & 64) == 64) {
            i2 |= 32;
        }
        c2903w00.k = this.l;
        if ((i & 128) == 128) {
            i2 |= 64;
        }
        c2903w00.l = this.m;
        if ((i & Fcntl.S_IRUSR) == 256) {
            i2 |= 128;
        }
        c2903w00.m = this.n;
        if ((i & 512) == 512) {
            i2 |= Fcntl.S_IRUSR;
        }
        c2903w00.n = this.o;
        if ((i & Fcntl.S_ISGID) == 1024) {
            i2 |= 512;
        }
        c2903w00.o = this.p;
        if ((i & Fcntl.S_ISUID) == 2048) {
            i2 |= Fcntl.S_ISGID;
        }
        c2903w00.p = this.q;
        if ((i & 4096) == 4096) {
            i2 |= Fcntl.S_ISUID;
        }
        c2903w00.q = this.r;
        if ((i & 8192) == 8192) {
            i2 |= 4096;
        }
        c2903w00.r = this.s;
        c2903w00.d = i2;
        return c2903w00;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    @Override // com.android.tools.r8.internal.AbstractC0574Ir
    public final AbstractC0574Ir a(C0638Ld c0638Ld, C0389Bo c0389Bo) throws Throwable {
        C2903w00 c2903w00 = null;
        try {
            try {
                C2903w00.v.getClass();
                a(new C2903w00(c0638Ld, c0389Bo));
                return this;
            } catch (QB e) {
                C2903w00 c2903w01 = (C2903w00) e.b;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    c2903w00 = c2903w01;
                    if (c2903w00 != null) {
                        a(c2903w00);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (c2903w00 != null) {
                a(c2903w00);
            }
            throw th;
        }
    }
}
